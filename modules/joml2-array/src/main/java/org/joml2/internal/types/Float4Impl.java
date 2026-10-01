// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float4} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Float4Impl implements Float4 {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4SegOpsUnsafe()
                        : new Float4SegOpsMS();
        static final Float4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4BbOpsUnsafe()
                        : new Float4BbOpsApi();
        static final Float4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4RawOpsUnsafe()
                        : new Float4RawOpsApi();
    }

    public Float4Impl() {
        data = new float[4];
        data[3] = 1;
    }

    public Float4Impl(float x, float y, float z, float w) {
        float[] dd = this.data = new float[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = w;
    }

    public Float4Impl(Float4R src) {
        float[] dd = this.data = new float[4];
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
    public Float4 add(Float4R other, @Mutated Float4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double4 add(Float4R other, @Mutated Double4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
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
    public Float4 add(float otherX, float otherY, float otherZ, float otherW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public Double4 add(float otherX, float otherY, float otherZ, float otherW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
    }


    /**
     * Divide each component of this vector by {@code scalar} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Float4 div(float scalar, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] / scalar;
        dd[1] = sd[1] / scalar;
        dd[2] = sd[2] / scalar;
        dd[3] = sd[3] / scalar;
        return dest;
    }


    /**
     * Divide each component of this vector by {@code scalar} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 div(float scalar, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] / scalar;
        dd[1] = sd[1] / scalar;
        dd[2] = sd[2] / scalar;
        dd[3] = sd[3] / scalar;
        return dest;
    }


    /**
     * Divide this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Float4 div(Float4R other, @Mutated Float4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] / other.x();
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        return dest;
    }


    /**
     * Divide this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 div(Float4R other, @Mutated Double4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] / other.x();
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        return dest;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
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
    public Float4 div(float otherX, float otherY, float otherZ, float otherW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] / otherX;
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        return dest;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public Double4 div(float otherX, float otherY, float otherZ, float otherW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] / otherX;
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        return dest;
    }


    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Float4 fma(float b, Float4R c, @Mutated Float4 dest) {
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], b, cX);
            dd[1] = java.lang.Math.fma(sd[1], b, cY);
            dd[2] = java.lang.Math.fma(sd[2], b, cZ);
            dd[3] = java.lang.Math.fma(sd[3], b, cW);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((sd[0]) * (b) + (cX));
            dd[1] = ((sd[1]) * (b) + (cY));
            dd[2] = ((sd[2]) * (b) + (cZ));
            dd[3] = ((sd[3]) * (b) + (cW));
            return dest;
        }
    }


    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double4 fma(float b, Float4R c, @Mutated Double4 dest) {
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], b, cX);
            dd[1] = java.lang.Math.fma(sd[1], b, cY);
            dd[2] = java.lang.Math.fma(sd[2], b, cZ);
            dd[3] = java.lang.Math.fma(sd[3], b, cW);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (b) + (cX));
            dd[1] = ((sd[1]) * (b) + (cY));
            dd[2] = ((sd[2]) * (b) + (cZ));
            dd[3] = ((sd[3]) * (b) + (cW));
            return dest;
        }
    }


    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}, {@code cZ},
     * {@code cW}), i.e. compute {@code this * b + (cX, cY, cZ, cW)} per component and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 fma(float b, float cX, float cY, float cZ, float cW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], b, cX);
            dd[1] = java.lang.Math.fma(sd[1], b, cY);
            dd[2] = java.lang.Math.fma(sd[2], b, cZ);
            dd[3] = java.lang.Math.fma(sd[3], b, cW);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((sd[0]) * (b) + (cX));
            dd[1] = ((sd[1]) * (b) + (cY));
            dd[2] = ((sd[2]) * (b) + (cZ));
            dd[3] = ((sd[3]) * (b) + (cW));
            return dest;
        }
    }


    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}, {@code cZ},
     * {@code cW}), i.e. compute {@code this * b + (cX, cY, cZ, cW)} per component and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 fma(float b, float cX, float cY, float cZ, float cW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], b, cX);
            dd[1] = java.lang.Math.fma(sd[1], b, cY);
            dd[2] = java.lang.Math.fma(sd[2], b, cZ);
            dd[3] = java.lang.Math.fma(sd[3], b, cW);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (b) + (cX));
            dd[1] = ((sd[1]) * (b) + (cY));
            dd[2] = ((sd[2]) * (b) + (cZ));
            dd[3] = ((sd[3]) * (b) + (cW));
            return dest;
        }
    }


    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Float4 fma(Float4R b, Float4R c, @Mutated Float4 dest) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], bX, cX);
            dd[1] = java.lang.Math.fma(sd[1], bY, cY);
            dd[2] = java.lang.Math.fma(sd[2], bZ, cZ);
            dd[3] = java.lang.Math.fma(sd[3], bW, cW);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((sd[0]) * (bX) + (cX));
            dd[1] = ((sd[1]) * (bY) + (cY));
            dd[2] = ((sd[2]) * (bZ) + (cZ));
            dd[3] = ((sd[3]) * (bW) + (cW));
            return dest;
        }
    }


    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double4 fma(Float4R b, Float4R c, @Mutated Double4 dest) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], bX, cX);
            dd[1] = java.lang.Math.fma(sd[1], bY, cY);
            dd[2] = java.lang.Math.fma(sd[2], bZ, cZ);
            dd[3] = java.lang.Math.fma(sd[3], bW, cW);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (bX) + (cX));
            dd[1] = ((sd[1]) * (bY) + (cY));
            dd[2] = ((sd[2]) * (bZ) + (cZ));
            dd[3] = ((sd[3]) * (bW) + (cW));
            return dest;
        }
    }


    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 fma(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], bX, cX);
            dd[1] = java.lang.Math.fma(sd[1], bY, cY);
            dd[2] = java.lang.Math.fma(sd[2], bZ, cZ);
            dd[3] = java.lang.Math.fma(sd[3], bW, cW);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((sd[0]) * (bX) + (cX));
            dd[1] = ((sd[1]) * (bY) + (cY));
            dd[2] = ((sd[2]) * (bZ) + (cZ));
            dd[3] = ((sd[3]) * (bW) + (cW));
            return dest;
        }
    }


    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 fma(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], bX, cX);
            dd[1] = java.lang.Math.fma(sd[1], bY, cY);
            dd[2] = java.lang.Math.fma(sd[2], bZ, cZ);
            dd[3] = java.lang.Math.fma(sd[3], bW, cW);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (bX) + (cX));
            dd[1] = ((sd[1]) * (bY) + (cY));
            dd[2] = ((sd[2]) * (bZ) + (cZ));
            dd[3] = ((sd[3]) * (bW) + (cW));
            return dest;
        }
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
    public Float4 mul(float scalar, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        return dest;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(float scalar, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
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
    public Float4 mul(Float4R other, @Mutated Float4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        dd[3] = otherW * sd[3];
        return dest;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(Float4R other, @Mutated Double4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        dd[3] = otherW * sd[3];
        return dest;
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
    public Float4 mul(float otherX, float otherY, float otherZ, float otherW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public Double4 mul(float otherX, float otherY, float otherZ, float otherW, @Mutated Double4 dest) {
        float[] sd = this.data;
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
    public Float4 negate(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        return dest;
    }


    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 negate(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
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
    public Float4 sub(Float4R other, @Mutated Float4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double4 sub(Float4R other, @Mutated Double4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
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
    public Float4 sub(float otherX, float otherY, float otherZ, float otherW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public Double4 sub(float otherX, float otherY, float otherZ, float otherW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
    }


    /**
     * Set this vector to the unit vector
     * {@code (sqrt(1 - u) cos(2 PI v), sqrt(1 - u) sin(2 PI v), sqrt(u) cos(2 PI w), sqrt(u) sin(2 PI w))}:
     * samples uniformly distributed in {@code [0, 1)} give a direction uniformly distributed on the
     * unit sphere of four dimensions ({@code makeRandomDirection} draws them from a
     * {@link java.util.Random}).
     * <p>
     * Valid input: {@code u} must lie in {@code [0, 1]}.
     *
     * @param u the sample that splits the unit length between {@code (x, y)} and {@code (z, w)},
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed direction
     * @param v the fraction of a full turn of {@code (x, y)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @param w the fraction of a full turn of {@code (z, w)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @return this
     */
    @Mutated public Float4 makeUniformDirection(float u, float v, float w) {
        float[] dd = this.data;
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u);
        float _t6 = Math.sin(_t3);
        dd[0] = Math.cosFromSin(_t4, _t1) * _t5;
        dd[1] = _t4 * _t5;
        dd[2] = Math.cosFromSin(_t6, _t3) * _t0;
        dd[3] = _t6 * _t0;
        return this;
    }


    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    public @Mutated Float4 set(Float4R v) {
        float vY = v.y();
        float vZ = v.z();
        float vW = v.w();
        float[] dd = this.data;
        dd[0] = v.x();
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
        return this;
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
    @Mutated public Float4 set(float vX, float vY, float vZ, float vW) {
        float[] dd = this.data;
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
    public Float4 set(float s, @Mutated Float4 dest) {
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        dd[3] = s;
        return dest;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Double4 set(float s, @Mutated Double4 dest) {
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        dd[3] = s;
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
        float[] sd = this.data;
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
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Byte4 toByte(@Mutated Byte4 dest) {
        float[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
        dd[3] = (byte) (sd[3]);
        return dest;
    }


    /**
     * Convert this vector to {@code byte} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    public Byte4 toByte(RoundingMode roundingMode, @Mutated Byte4 dest) {
        float[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toByte(dest); }
            case FLOOR -> {
                dd[0] = (byte) Math.floor(sd[0]);
                dd[1] = (byte) Math.floor(sd[1]);
                dd[2] = (byte) Math.floor(sd[2]);
                dd[3] = (byte) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (byte) Math.ceil(sd[0]);
                dd[1] = (byte) Math.ceil(sd[1]);
                dd[2] = (byte) Math.ceil(sd[2]);
                dd[3] = (byte) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = (byte) Math.round(sd[0]);
                dd[1] = (byte) Math.round(sd[1]);
                dd[2] = (byte) Math.round(sd[2]);
                dd[3] = (byte) Math.round(sd[3]);
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (byte) (sd[0] >= 0 ? Math.floor(sd[0] + 0.5) : Math.ceil(sd[0] - 0.5));
                dd[1] = (byte) (sd[1] >= 0 ? Math.floor(sd[1] + 0.5) : Math.ceil(sd[1] - 0.5));
                dd[2] = (byte) (sd[2] >= 0 ? Math.floor(sd[2] + 0.5) : Math.ceil(sd[2] - 0.5));
                dd[3] = (byte) (sd[3] >= 0 ? Math.floor(sd[3] + 0.5) : Math.ceil(sd[3] - 0.5));
            }
            case HALF_EVEN -> {
                dd[0] = (byte) Math.rint(sd[0]);
                dd[1] = (byte) Math.rint(sd[1]);
                dd[2] = (byte) Math.rint(sd[2]);
                dd[3] = (byte) Math.rint(sd[3]);
            }
        }
        return dest;
    }


    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Short4 toShort(@Mutated Short4 dest) {
        float[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
        dd[3] = (short) (sd[3]);
        return dest;
    }


    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    public Short4 toShort(RoundingMode roundingMode, @Mutated Short4 dest) {
        float[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toShort(dest); }
            case FLOOR -> {
                dd[0] = (short) Math.floor(sd[0]);
                dd[1] = (short) Math.floor(sd[1]);
                dd[2] = (short) Math.floor(sd[2]);
                dd[3] = (short) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (short) Math.ceil(sd[0]);
                dd[1] = (short) Math.ceil(sd[1]);
                dd[2] = (short) Math.ceil(sd[2]);
                dd[3] = (short) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = (short) Math.round(sd[0]);
                dd[1] = (short) Math.round(sd[1]);
                dd[2] = (short) Math.round(sd[2]);
                dd[3] = (short) Math.round(sd[3]);
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (short) (sd[0] >= 0 ? Math.floor(sd[0] + 0.5) : Math.ceil(sd[0] - 0.5));
                dd[1] = (short) (sd[1] >= 0 ? Math.floor(sd[1] + 0.5) : Math.ceil(sd[1] - 0.5));
                dd[2] = (short) (sd[2] >= 0 ? Math.floor(sd[2] + 0.5) : Math.ceil(sd[2] - 0.5));
                dd[3] = (short) (sd[3] >= 0 ? Math.floor(sd[3] + 0.5) : Math.ceil(sd[3] - 0.5));
            }
            case HALF_EVEN -> {
                dd[0] = (short) Math.rint(sd[0]);
                dd[1] = (short) Math.rint(sd[1]);
                dd[2] = (short) Math.rint(sd[2]);
                dd[3] = (short) Math.rint(sd[3]);
            }
        }
        return dest;
    }


    /**
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int4 toInt(@Mutated Int4 dest) {
        float[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
        dd[2] = (int) (sd[2]);
        dd[3] = (int) (sd[3]);
        return dest;
    }


    /**
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    public Int4 toInt(RoundingMode roundingMode, @Mutated Int4 dest) {
        float[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toInt(dest); }
            case FLOOR -> {
                dd[0] = (int) Math.floor(sd[0]);
                dd[1] = (int) Math.floor(sd[1]);
                dd[2] = (int) Math.floor(sd[2]);
                dd[3] = (int) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (int) Math.ceil(sd[0]);
                dd[1] = (int) Math.ceil(sd[1]);
                dd[2] = (int) Math.ceil(sd[2]);
                dd[3] = (int) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = Math.round(sd[0]);
                dd[1] = Math.round(sd[1]);
                dd[2] = Math.round(sd[2]);
                dd[3] = Math.round(sd[3]);
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (int) (sd[0] >= 0 ? Math.floor(sd[0] + 0.5) : Math.ceil(sd[0] - 0.5));
                dd[1] = (int) (sd[1] >= 0 ? Math.floor(sd[1] + 0.5) : Math.ceil(sd[1] - 0.5));
                dd[2] = (int) (sd[2] >= 0 ? Math.floor(sd[2] + 0.5) : Math.ceil(sd[2] - 0.5));
                dd[3] = (int) (sd[3] >= 0 ? Math.floor(sd[3] + 0.5) : Math.ceil(sd[3] - 0.5));
            }
            case HALF_EVEN -> {
                dd[0] = (int) Math.rint(sd[0]);
                dd[1] = (int) Math.rint(sd[1]);
                dd[2] = (int) Math.rint(sd[2]);
                dd[3] = (int) Math.rint(sd[3]);
            }
        }
        return dest;
    }


    /**
     * Convert this vector to {@code long} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 toLong(@Mutated Long4 dest) {
        float[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = (long) (sd[0]);
        dd[1] = (long) (sd[1]);
        dd[2] = (long) (sd[2]);
        dd[3] = (long) (sd[3]);
        return dest;
    }


    /**
     * Convert this vector to {@code long} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    public Long4 toLong(RoundingMode roundingMode, @Mutated Long4 dest) {
        float[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toLong(dest); }
            case FLOOR -> {
                dd[0] = (long) Math.floor(sd[0]);
                dd[1] = (long) Math.floor(sd[1]);
                dd[2] = (long) Math.floor(sd[2]);
                dd[3] = (long) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (long) Math.ceil(sd[0]);
                dd[1] = (long) Math.ceil(sd[1]);
                dd[2] = (long) Math.ceil(sd[2]);
                dd[3] = (long) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = Math.round((double) (sd[0]));
                dd[1] = Math.round((double) (sd[1]));
                dd[2] = Math.round((double) (sd[2]));
                dd[3] = Math.round((double) (sd[3]));
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (long) (sd[0] >= 0 ? Math.floor(sd[0] + 0.5) : Math.ceil(sd[0] - 0.5));
                dd[1] = (long) (sd[1] >= 0 ? Math.floor(sd[1] + 0.5) : Math.ceil(sd[1] - 0.5));
                dd[2] = (long) (sd[2] >= 0 ? Math.floor(sd[2] + 0.5) : Math.ceil(sd[2] - 0.5));
                dd[3] = (long) (sd[3] >= 0 ? Math.floor(sd[3] + 0.5) : Math.ceil(sd[3] - 0.5));
            }
            case HALF_EVEN -> {
                dd[0] = (long) Math.rint(sd[0]);
                dd[1] = (long) Math.rint(sd[1]);
                dd[2] = (long) Math.rint(sd[2]);
                dd[3] = (long) Math.rint(sd[3]);
            }
        }
        return dest;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float4 makeZero() {
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        return this;
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezier(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest) {
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dd[0] = Math.fma(p1.x(), _t7, sd[0] * _t8) + Math.fma(p2.x(), _t6, p3.x() * _t2);
        dd[1] = Math.fma(p1Y, _t7, sd[1] * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = Math.fma(p1Z, _t7, sd[2] * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        dd[3] = Math.fma(p1W, _t7, sd[3] * _t8) + Math.fma(p2W, _t6, p3W * _t2);
        return dest;
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezier(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest) {
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dd[0] = Math.fma(p1.x(), _t7, sd[0] * _t8) + Math.fma(p2.x(), _t6, p3.x() * _t2);
        dd[1] = Math.fma(p1Y, _t7, sd[1] * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = Math.fma(p1Z, _t7, sd[2] * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        dd[3] = Math.fma(p1W, _t7, sd[3] * _t8) + Math.fma(p2W, _t6, p3W * _t2);
        return dest;
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W})
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}, {@code p3W}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) pull it
     * towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezier(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dd[0] = Math.fma(p1X, _t7, sd[0] * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        dd[1] = Math.fma(p1Y, _t7, sd[1] * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = Math.fma(p1Z, _t7, sd[2] * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        dd[3] = Math.fma(p1W, _t7, sd[3] * _t8) + Math.fma(p2W, _t6, p3W * _t2);
        return dest;
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W})
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}, {@code p3W}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) pull it
     * towards themselves but are generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezier(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dd[0] = Math.fma(p1X, _t7, sd[0] * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        dd[1] = Math.fma(p1Y, _t7, sd[1] * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = Math.fma(p1Z, _t7, sd[2] * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        dd[3] = Math.fma(p1W, _t7, sd[3] * _t8) + Math.fma(p2W, _t6, p3W * _t2);
        return dest;
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezier2(Float4R p1, Float4R p2, float t, @Mutated Float4 dest) {
        return bezier2(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), t, dest);
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezier2(Float4R p1, Float4R p2, float t, @Mutated Double4 dest) {
        return bezier2(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), t, dest);
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezier2(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            dd[0] = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, sd[0] * _t4));
            dd[1] = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, sd[1] * _t4));
            dd[2] = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, sd[2] * _t4));
            dd[3] = java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, sd[3] * _t4));
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            dd[0] = ((p2X) * (_t0) + (((p1X) * (_t3) + (sd[0] * _t4))));
            dd[1] = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (sd[1] * _t4))));
            dd[2] = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (sd[2] * _t4))));
            dd[3] = ((p2W) * (_t0) + (((p1W) * (_t3) + (sd[3] * _t4))));
            return dest;
        }
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) pulls it towards itself but is generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezier2(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            dd[0] = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, sd[0] * _t4));
            dd[1] = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, sd[1] * _t4));
            dd[2] = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, sd[2] * _t4));
            dd[3] = java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, sd[3] * _t4));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            dd[0] = ((p2X) * (_t0) + (((p1X) * (_t3) + (sd[0] * _t4))));
            dd[1] = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (sd[1] * _t4))));
            dd[2] = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (sd[2] * _t4))));
            dd[3] = ((p2W) * (_t0) + (((p1W) * (_t3) + (sd[3] * _t4))));
            return dest;
        }
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezier2Tangent(Float4R p1, Float4R p2, float t, @Mutated Float4 dest) {
        return bezier2Tangent(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezier2Tangent(Float4R p1, Float4R p2, float t, @Mutated Double4 dest) {
        return bezier2Tangent(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezier2Tangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            dd[0] = java.lang.Math.fma(p1X - sd[0], _t2, (p2X - p1X) * _t1);
            dd[1] = java.lang.Math.fma(p1Y - sd[1], _t2, (p2Y - p1Y) * _t1);
            dd[2] = java.lang.Math.fma(p1Z - sd[2], _t2, (p2Z - p1Z) * _t1);
            dd[3] = java.lang.Math.fma(p1W - sd[3], _t2, (p2W - p1W) * _t1);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            dd[0] = ((p1X - sd[0]) * (_t2) + ((p2X - p1X) * _t1));
            dd[1] = ((p1Y - sd[1]) * (_t2) + ((p2Y - p1Y) * _t1));
            dd[2] = ((p1Z - sd[2]) * (_t2) + ((p2Z - p1Z) * _t1));
            dd[3] = ((p1W - sd[3]) * (_t2) + ((p2W - p1W) * _t1));
            return dest;
        }
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) pulls it towards itself but is generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezier2Tangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            dd[0] = java.lang.Math.fma(p1X - sd[0], _t2, (p2X - p1X) * _t1);
            dd[1] = java.lang.Math.fma(p1Y - sd[1], _t2, (p2Y - p1Y) * _t1);
            dd[2] = java.lang.Math.fma(p1Z - sd[2], _t2, (p2Z - p1Z) * _t1);
            dd[3] = java.lang.Math.fma(p1W - sd[3], _t2, (p2W - p1W) * _t1);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            dd[0] = ((p1X - sd[0]) * (_t2) + ((p2X - p1X) * _t1));
            dd[1] = ((p1Y - sd[1]) * (_t2) + ((p2Y - p1Y) * _t1));
            dd[2] = ((p1Z - sd[2]) * (_t2) + ((p2Z - p1Z) * _t1));
            dd[3] = ((p1W - sd[3]) * (_t2) + ((p2W - p1W) * _t1));
            return dest;
        }
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezierTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dd[0] = Math.fma(p3.x() - p2X, _t2, Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        dd[3] = Math.fma(p3W - p2W, _t2, Math.fma(p1W - sd[3], _t6, (p2W - p1W) * _t5));
        return dest;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezierTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dd[0] = Math.fma(p3.x() - p2X, _t2, Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        dd[3] = Math.fma(p3W - p2W, _t2, Math.fma(p1W - sd[3], _t6, (p2W - p1W) * _t5));
        return dest;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z},
     * {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) and store the
     * result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}, {@code p3W}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) pull it
     * towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 bezierTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dd[0] = Math.fma(p3X - p2X, _t2, Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        dd[3] = Math.fma(p3W - p2W, _t2, Math.fma(p1W - sd[3], _t6, (p2W - p1W) * _t5));
        return dest;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z},
     * {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) and store the
     * result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}, {@code p3W}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) pull it
     * towards themselves but are generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bezierTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dd[0] = Math.fma(p3X - p2X, _t2, Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        dd[3] = Math.fma(p3W - p2W, _t2, Math.fma(p1W - sd[3], _t6, (p2W - p1W) * _t5));
        return dest;
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from {@code p1} to {@code p2}, with this
     * vector as the control point before the segment and {@code p3} as the control point after it
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through {@code p1} at {@code t = 0} and through {@code p2} at {@code t = 1}.
     * This vector and {@code p3} are the spline's neighbouring points, i.e. the point before
     * {@code p1} and the point after {@code p2}: they only shape the tangents at the segment's two
     * end points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 catmullRom(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3X = p3.x();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        if (Math.useFma()) return catmullRom_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRom_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from {@code p1} to {@code p2}, with this
     * vector as the control point before the segment and {@code p3} as the control point after it
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through {@code p1} at {@code t = 0} and through {@code p2} at {@code t = 1}.
     * This vector and {@code p3} are the spline's neighbouring points, i.e. the point before
     * {@code p1} and the point after {@code p2}: they only shape the tangents at the segment's two
     * end points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 catmullRom(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3X = p3.x();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        if (Math.useFma()) return catmullRom_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRom_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) to ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the
     * control point before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as
     * the control point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) at
     * {@code t = 0} and through ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) at
     * {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) are the
     * spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) and the point after ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}): they
     * only shape the tangents at the segment's two end points and are not themselves on the
     * segment. For a spline through the points {@code p[0..n]}, the segment from {@code p[i]} to
     * {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of this vector and
     * {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 catmullRom(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        if (Math.useFma()) return catmullRom_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRom_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }

    /** {@code catmullRom} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 catmullRom_fma(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = t * t;
        float _t1 = t * _t0;
        dd[0] = 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - sd[0])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, sd[0], java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - sd[0])) * _t1));
        dd[1] = 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - sd[1])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, sd[1], java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - sd[1])) * _t1));
        dd[2] = 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - sd[2])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, sd[2], java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - sd[2])) * _t1));
        return catmullRom_s11cc5fb3_1_fma(p1W, p2W, p3W, t, dest, sd, dd, _t0, _t1);
    }

    /** {@code catmullRom} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 catmullRom_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = t * t;
        float _t1 = t * _t0;
        dd[0] = 0.5f * (((2.0f) * (p1X) + (t * (p2X - sd[0]))) + ((((-5.0f) * (p1X) + (((2.0f) * (sd[0]) + (((4.0f) * (p2X) - (p3X))))))) * (_t0) + (((-3.0f) * (p2X) + (((3.0f) * (p1X) + (p3X - sd[0])))) * _t1)));
        dd[1] = 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - sd[1]))) + ((((-5.0f) * (p1Y) + (((2.0f) * (sd[1]) + (((4.0f) * (p2Y) - (p3Y))))))) * (_t0) + (((-3.0f) * (p2Y) + (((3.0f) * (p1Y) + (p3Y - sd[1])))) * _t1)));
        dd[2] = 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - sd[2]))) + ((((-5.0f) * (p1Z) + (((2.0f) * (sd[2]) + (((4.0f) * (p2Z) - (p3Z))))))) * (_t0) + (((-3.0f) * (p2Z) + (((3.0f) * (p1Z) + (p3Z - sd[2])))) * _t1)));
        return catmullRom_s11cc5fb3_1_mulAdd(p1W, p2W, p3W, t, dest, sd, dd, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom}, split to fit the inline budget; reached only through it. */
    private Float4 catmullRom_s11cc5fb3_1_fma(float p1W, float p2W, float p3W, float t, Float4 dest, float[] sd, float[] dd, float _t0, float _t1) {
        dd[3] = 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - sd[3])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, sd[3], java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - sd[3])) * _t1));
        return dest;
    }

    /** Piece 2 of {@code catmullRom}, split to fit the inline budget; reached only through it. */
    private Float4 catmullRom_s11cc5fb3_1_mulAdd(float p1W, float p2W, float p3W, float t, Float4 dest, float[] sd, float[] dd, float _t0, float _t1) {
        dd[3] = 0.5f * (((2.0f) * (p1W) + (t * (p2W - sd[3]))) + ((((-5.0f) * (p1W) + (((2.0f) * (sd[3]) + (((4.0f) * (p2W) - (p3W))))))) * (_t0) + (((-3.0f) * (p2W) + (((3.0f) * (p1W) + (p3W - sd[3])))) * _t1)));
        return dest;
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) to ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the
     * control point before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as
     * the control point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) at
     * {@code t = 0} and through ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) at
     * {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) are the
     * spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) and the point after ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}): they
     * only shape the tangents at the segment's two end points and are not themselves on the
     * segment. For a spline through the points {@code p[0..n]}, the segment from {@code p[i]} to
     * {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of this vector and
     * {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 catmullRom(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        if (Math.useFma()) return catmullRom_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRom_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }

    /** {@code catmullRom} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 catmullRom_fma(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = t * t;
        float _t1 = t * _t0;
        dd[0] = 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - sd[0])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, sd[0], java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - sd[0])) * _t1));
        dd[1] = 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - sd[1])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, sd[1], java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - sd[1])) * _t1));
        dd[2] = 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - sd[2])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, sd[2], java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - sd[2])) * _t1));
        return catmullRom_se25ca14e_1_fma(p1W, p2W, p3W, t, dest, sd, dd, _t0, _t1);
    }

    /** {@code catmullRom} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 catmullRom_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = t * t;
        float _t1 = t * _t0;
        dd[0] = 0.5f * (((2.0f) * (p1X) + (t * (p2X - sd[0]))) + ((((-5.0f) * (p1X) + (((2.0f) * (sd[0]) + (((4.0f) * (p2X) - (p3X))))))) * (_t0) + (((-3.0f) * (p2X) + (((3.0f) * (p1X) + (p3X - sd[0])))) * _t1)));
        dd[1] = 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - sd[1]))) + ((((-5.0f) * (p1Y) + (((2.0f) * (sd[1]) + (((4.0f) * (p2Y) - (p3Y))))))) * (_t0) + (((-3.0f) * (p2Y) + (((3.0f) * (p1Y) + (p3Y - sd[1])))) * _t1)));
        dd[2] = 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - sd[2]))) + ((((-5.0f) * (p1Z) + (((2.0f) * (sd[2]) + (((4.0f) * (p2Z) - (p3Z))))))) * (_t0) + (((-3.0f) * (p2Z) + (((3.0f) * (p1Z) + (p3Z - sd[2])))) * _t1)));
        return catmullRom_se25ca14e_1_mulAdd(p1W, p2W, p3W, t, dest, sd, dd, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom}, split to fit the inline budget; reached only through it. */
    private Double4 catmullRom_se25ca14e_1_fma(float p1W, float p2W, float p3W, float t, Double4 dest, float[] sd, double[] dd, float _t0, float _t1) {
        dd[3] = 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - sd[3])) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, sd[3], java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - sd[3])) * _t1));
        return dest;
    }

    /** Piece 2 of {@code catmullRom}, split to fit the inline budget; reached only through it. */
    private Double4 catmullRom_se25ca14e_1_mulAdd(float p1W, float p2W, float p3W, float t, Double4 dest, float[] sd, double[] dd, float _t0, float _t1) {
        dd[3] = 0.5f * (((2.0f) * (p1W) + (t * (p2W - sd[3]))) + ((((-5.0f) * (p1W) + (((2.0f) * (sd[3]) + (((4.0f) * (p2W) - (p3W))))))) * (_t0) + (((-3.0f) * (p2W) + (((3.0f) * (p1W) + (p3W - sd[3])))) * _t1)));
        return dest;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from {@code p1} to {@code p2}, with this vector as the control
     * point before the segment and {@code p3} as the control point after it and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through {@code p1} at {@code t = 0} and through {@code p2} at {@code t = 1}.
     * This vector and {@code p3} are the spline's neighbouring points, i.e. the point before
     * {@code p1} and the point after {@code p2}: they only shape the tangents at the segment's two
     * end points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 catmullRomTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3X = p3.x();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        if (Math.useFma()) return catmullRomTangent_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRomTangent_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from {@code p1} to {@code p2}, with this vector as the control
     * point before the segment and {@code p3} as the control point after it and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through {@code p1} at {@code t = 0} and through {@code p2} at {@code t = 1}.
     * This vector and {@code p3} are the spline's neighbouring points, i.e. the point before
     * {@code p1} and the point after {@code p2}: they only shape the tangents at the segment's two
     * end points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 catmullRomTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float p3X = p3.x();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float p3W = p3.w();
        if (Math.useFma()) return catmullRomTangent_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRomTangent_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) to
     * ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the control point
     * before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as the control
     * point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) at
     * {@code t = 0} and through ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) at
     * {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) are the
     * spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) and the point after ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}): they
     * only shape the tangents at the segment's two end points and are not themselves on the
     * segment. For a spline through the points {@code p[0..n]}, the segment from {@code p[i]} to
     * {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of this vector and
     * {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 catmullRomTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        if (Math.useFma()) return catmullRomTangent_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRomTangent_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }

    /** {@code catmullRomTangent} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 catmullRomTangent_fma(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = t * t;
        dd[0] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, sd[0], java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - sd[0])), _t0, p2X - sd[0]));
        dd[1] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, sd[1], java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - sd[1])), _t0, p2Y - sd[1]));
        dd[2] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, sd[2], java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - sd[2])), _t0, p2Z - sd[2]));
        return catmullRomTangent_s8231dca0_1_fma(p1W, p2W, p3W, t, dest, sd, dd, _t0);
    }

    /** {@code catmullRomTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 catmullRomTangent_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = t * t;
        dd[0] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1X) + (((2.0f) * (sd[0]) + (((4.0f) * (p2X) - (p3X))))))) + (((3.0f * ((-3.0f) * (p2X) + (((3.0f) * (p1X) + (p3X - sd[0]))))) * (_t0) + (p2X - sd[0]))));
        dd[1] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1Y) + (((2.0f) * (sd[1]) + (((4.0f) * (p2Y) - (p3Y))))))) + (((3.0f * ((-3.0f) * (p2Y) + (((3.0f) * (p1Y) + (p3Y - sd[1]))))) * (_t0) + (p2Y - sd[1]))));
        dd[2] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1Z) + (((2.0f) * (sd[2]) + (((4.0f) * (p2Z) - (p3Z))))))) + (((3.0f * ((-3.0f) * (p2Z) + (((3.0f) * (p1Z) + (p3Z - sd[2]))))) * (_t0) + (p2Z - sd[2]))));
        return catmullRomTangent_s8231dca0_1_mulAdd(p1W, p2W, p3W, t, dest, sd, dd, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent}, split to fit the inline budget; reached only through it. */
    private Float4 catmullRomTangent_s8231dca0_1_fma(float p1W, float p2W, float p3W, float t, Float4 dest, float[] sd, float[] dd, float _t0) {
        dd[3] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, sd[3], java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - sd[3])), _t0, p2W - sd[3]));
        return dest;
    }

    /** Piece 2 of {@code catmullRomTangent}, split to fit the inline budget; reached only through it. */
    private Float4 catmullRomTangent_s8231dca0_1_mulAdd(float p1W, float p2W, float p3W, float t, Float4 dest, float[] sd, float[] dd, float _t0) {
        dd[3] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1W) + (((2.0f) * (sd[3]) + (((4.0f) * (p2W) - (p3W))))))) + (((3.0f * ((-3.0f) * (p2W) + (((3.0f) * (p1W) + (p3W - sd[3]))))) * (_t0) + (p2W - sd[3]))));
        return dest;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) to
     * ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the control point
     * before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as the control
     * point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) at
     * {@code t = 0} and through ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) at
     * {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) are the
     * spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) and the point after ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}): they
     * only shape the tangents at the segment's two end points and are not themselves on the
     * segment. For a spline through the points {@code p[0..n]}, the segment from {@code p[i]} to
     * {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of this vector and
     * {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 catmullRomTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        if (Math.useFma()) return catmullRomTangent_fma(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRomTangent_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }

    /** {@code catmullRomTangent} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 catmullRomTangent_fma(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = t * t;
        dd[0] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, sd[0], java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - sd[0])), _t0, p2X - sd[0]));
        dd[1] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, sd[1], java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - sd[1])), _t0, p2Y - sd[1]));
        dd[2] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, sd[2], java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - sd[2])), _t0, p2Z - sd[2]));
        return catmullRomTangent_scdeb5917_1_fma(p1W, p2W, p3W, t, dest, sd, dd, _t0);
    }

    /** {@code catmullRomTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 catmullRomTangent_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = t * t;
        dd[0] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1X) + (((2.0f) * (sd[0]) + (((4.0f) * (p2X) - (p3X))))))) + (((3.0f * ((-3.0f) * (p2X) + (((3.0f) * (p1X) + (p3X - sd[0]))))) * (_t0) + (p2X - sd[0]))));
        dd[1] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1Y) + (((2.0f) * (sd[1]) + (((4.0f) * (p2Y) - (p3Y))))))) + (((3.0f * ((-3.0f) * (p2Y) + (((3.0f) * (p1Y) + (p3Y - sd[1]))))) * (_t0) + (p2Y - sd[1]))));
        dd[2] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1Z) + (((2.0f) * (sd[2]) + (((4.0f) * (p2Z) - (p3Z))))))) + (((3.0f * ((-3.0f) * (p2Z) + (((3.0f) * (p1Z) + (p3Z - sd[2]))))) * (_t0) + (p2Z - sd[2]))));
        return catmullRomTangent_scdeb5917_1_mulAdd(p1W, p2W, p3W, t, dest, sd, dd, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent}, split to fit the inline budget; reached only through it. */
    private Double4 catmullRomTangent_scdeb5917_1_fma(float p1W, float p2W, float p3W, float t, Double4 dest, float[] sd, double[] dd, float _t0) {
        dd[3] = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, sd[3], java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - sd[3])), _t0, p2W - sd[3]));
        return dest;
    }

    /** Piece 2 of {@code catmullRomTangent}, split to fit the inline budget; reached only through it. */
    private Double4 catmullRomTangent_scdeb5917_1_mulAdd(float p1W, float p2W, float p3W, float t, Double4 dest, float[] sd, double[] dd, float _t0) {
        dd[3] = 0.5f * ((t) * (2.0f * ((-5.0f) * (p1W) + (((2.0f) * (sd[3]) + (((4.0f) * (p2W) - (p3W))))))) + (((3.0f * ((-3.0f) * (p2W) + (((3.0f) * (p1W) + (p3W - sd[3]))))) * (_t0) + (p2W - sd[3]))));
        return dest;
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * {@code t0} and ends at {@code v1} with the tangent {@code t1} and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 hermite(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Float4 dest) {
        return hermite(t0.x(), t0.y(), t0.z(), t0.w(), v1.x(), v1.y(), v1.z(), v1.w(), t1.x(), t1.y(), t1.z(), t1.w(), t, dest);
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * {@code t0} and ends at {@code v1} with the tangent {@code t1} and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 hermite(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Double4 dest) {
        return hermite(t0.x(), t0.y(), t0.z(), t0.w(), v1.x(), v1.y(), v1.z(), v1.w(), t1.x(), t1.y(), t1.z(), t1.w(), t, dest);
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z},
     * {@code t1W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) at {@code t = 1}; the two tangents set its direction and speed at
     * those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0W the {@code w} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1W the {@code w} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1W the {@code w} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 hermite(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dd[0] = Math.fma(sd[0], _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        dd[1] = Math.fma(sd[1], _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        dd[2] = Math.fma(sd[2], _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        dd[3] = Math.fma(sd[3], _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9);
        return dest;
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z},
     * {@code t1W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) at {@code t = 1}; the two tangents set its direction and speed at
     * those end points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0W the {@code w} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1W the {@code w} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1W the {@code w} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 hermite(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dd[0] = Math.fma(sd[0], _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        dd[1] = Math.fma(sd[1], _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        dd[2] = Math.fma(sd[2], _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        dd[3] = Math.fma(sd[3], _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9);
        return dest;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent {@code t0} and ends at
     * {@code v1} with the tangent {@code t1} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 hermiteTangent(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Float4 dest) {
        return hermiteTangent(t0.x(), t0.y(), t0.z(), t0.w(), v1.x(), v1.y(), v1.z(), v1.w(), t1.x(), t1.y(), t1.z(), t1.w(), t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent {@code t0} and ends at
     * {@code v1} with the tangent {@code t1} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 hermiteTangent(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Double4 dest) {
        return hermiteTangent(t0.x(), t0.y(), t0.z(), t0.w(), v1.x(), v1.y(), v1.z(), v1.w(), t1.x(), t1.y(), t1.z(), t1.w(), t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}, {@code v1W})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}, {@code t1W}) and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) at {@code t = 1}; the two tangents set its direction and speed at
     * those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0W the {@code w} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1W the {@code w} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1W the {@code w} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 hermiteTangent(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dd[0] = Math.fma(sd[0], _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        dd[1] = Math.fma(sd[1], _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        dd[2] = Math.fma(sd[2], _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        dd[3] = Math.fma(sd[3], _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7);
        return dest;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}, {@code v1W})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}, {@code t1W}) and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) at {@code t = 1}; the two tangents set its direction and speed at
     * those end points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0W the {@code w} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1W the {@code w} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1W the {@code w} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 hermiteTangent(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dd[0] = Math.fma(sd[0], _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        dd[1] = Math.fma(sd[1], _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        dd[2] = Math.fma(sd[2], _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        dd[3] = Math.fma(sd[3], _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7);
        return dest;
    }


    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 lerp(Float4R other, float t, @Mutated Float4 dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 lerp(Float4R other, float t, @Mutated Double4 dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 lerp(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 lerp(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 lerp(Float4R other, Float4R t, @Mutated Float4 dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float tX = t.x();
        float tY = t.y();
        float tZ = t.z();
        float tW = t.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(tX, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(tY, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(tZ, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(tW, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((tX) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((tY) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((tZ) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((tW) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 lerp(Float4R other, Float4R t, @Mutated Double4 dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float tX = t.x();
        float tY = t.y();
        float tZ = t.z();
        float tW = t.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(tX, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(tY, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(tZ, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(tW, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((tX) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((tY) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((tZ) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((tW) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor ({@code tX}, {@code tY}, {@code tZ},
     * {@code tW}) and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tW the {@code w} component of the vector {@code (tX, tY, tZ, tW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 lerp(float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(tX, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(tY, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(tZ, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(tW, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((tX) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((tY) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((tZ) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((tW) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor ({@code tX}, {@code tY}, {@code tZ},
     * {@code tW}) and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tW the {@code w} component of the vector {@code (tX, tY, tZ, tW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 lerp(float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(tX, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(tY, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(tZ, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(tW, otherW - sd[3], sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((tX) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((tY) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((tZ) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((tW) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }


    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths and store the result in
     * {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 slerp(Float4R other, float t, @Mutated Float4 dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }


    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths and store the result in
     * {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 slerp(Float4R other, float t, @Mutated Double4 dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }


    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths and store the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 slerp(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        if (Math.useFma()) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 slerp_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t9 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = sd[0] * _t11;
        float _t19 = sd[3] * _t11;
        float _t21 = sd[2] * _t11;
        float _t24 = sd[1] * _t11;
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_s5cedc029_1_fma(otherX, otherY, otherZ, otherW, t, dest, dd, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, java.lang.Math.fma(otherW, _t14, -(_t31 * _t19)), java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 slerp_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t9 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = sd[0] * _t11;
        float _t19 = sd[3] * _t11;
        float _t21 = sd[2] * _t11;
        float _t24 = sd[1] * _t11;
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_s5cedc029_1_mulAdd(otherX, otherY, otherZ, otherW, t, dest, dd, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, ((otherW) * (_t14) - (_t31 * _t19)), ((otherZ) * (_t14) - (_t31 * _t21)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_s5cedc029_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, Float4 dest, float[] dd, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t40, float _t41) {
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        float _t66 = _t28 * Math.cos(_t61);
        dd[0] = java.lang.Math.fma(_t16, _t66, _sp0 * _t51);
        dd[1] = java.lang.Math.fma(_t24, _t66, _sp0 * _t52);
        dd[2] = java.lang.Math.fma(_t21, _t66, _sp0 * _t50);
        dd[3] = java.lang.Math.fma(_t19, _t66, _sp0 * _t49);
        return dest;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_s5cedc029_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Float4 dest, float[] dd, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t40, float _t41) {
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        float _t66 = _t28 * Math.cos(_t61);
        dd[0] = ((_t16) * (_t66) + (_sp0 * _t51));
        dd[1] = ((_t24) * (_t66) + (_sp0 * _t52));
        dd[2] = ((_t21) * (_t66) + (_sp0 * _t50));
        dd[3] = ((_t19) * (_t66) + (_sp0 * _t49));
        return dest;
    }


    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths and store the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 slerp(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        if (Math.useFma()) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 slerp_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t9 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = sd[0] * _t11;
        float _t19 = sd[3] * _t11;
        float _t21 = sd[2] * _t11;
        float _t24 = sd[1] * _t11;
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_sa6ac05c0_1_fma(otherX, otherY, otherZ, otherW, t, dest, dd, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, java.lang.Math.fma(otherW, _t14, -(_t31 * _t19)), java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 slerp_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t9 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = sd[0] * _t11;
        float _t19 = sd[3] * _t11;
        float _t21 = sd[2] * _t11;
        float _t24 = sd[1] * _t11;
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_sa6ac05c0_1_mulAdd(otherX, otherY, otherZ, otherW, t, dest, dd, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, ((otherW) * (_t14) - (_t31 * _t19)), ((otherZ) * (_t14) - (_t31 * _t21)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_sa6ac05c0_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, Double4 dest, double[] dd, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t40, float _t41) {
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        float _t66 = _t28 * Math.cos(_t61);
        dd[0] = java.lang.Math.fma(_t16, _t66, _sp0 * _t51);
        dd[1] = java.lang.Math.fma(_t24, _t66, _sp0 * _t52);
        dd[2] = java.lang.Math.fma(_t21, _t66, _sp0 * _t50);
        dd[3] = java.lang.Math.fma(_t19, _t66, _sp0 * _t49);
        return dest;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_sa6ac05c0_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Double4 dest, double[] dd, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t40, float _t41) {
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        float _t66 = _t28 * Math.cos(_t61);
        dd[0] = ((_t16) * (_t66) + (_sp0 * _t51));
        dd[1] = ((_t24) * (_t66) + (_sp0 * _t52));
        dd[2] = ((_t21) * (_t66) + (_sp0 * _t50));
        dd[3] = ((_t19) * (_t66) + (_sp0 * _t49));
        return dest;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float4 slerp_degenerate_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = sd[3] * _t8;
        float _t22 = sd[2] * _t8;
        float _t23 = sd[0] * _t8;
        float _t24 = sd[1] * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dd[0] = java.lang.Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv;
                    dd[1] = java.lang.Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv;
                    dd[2] = java.lang.Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv;
                    dd[3] = java.lang.Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv;
                } else {
                    dd[0] = java.lang.Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                    dd[1] = java.lang.Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                    dd[2] = java.lang.Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                    dd[3] = java.lang.Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
                }
            } else {
                dd[0] = java.lang.Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                dd[1] = java.lang.Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                dd[2] = java.lang.Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                dd[3] = java.lang.Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
            }
        } else {
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
        }
        return dest;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float4 slerp_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = sd[3] * _t8;
        float _t22 = sd[2] * _t8;
        float _t23 = sd[0] * _t8;
        float _t24 = sd[1] * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dd[0] = ((_t114) * (_t50) + (_t115 * _t47)) * _t25_inv;
                    dd[1] = ((_t114) * (_t47) + (_t115 * _t49)) * _t25_inv;
                    dd[2] = ((_t114) * (_t51) + (_t115 * _t45)) * _t25_inv;
                    dd[3] = ((_t114) * (_t45) + (_t115 * _t43)) * _t25_inv;
                } else {
                    dd[0] = ((_t114) * (_t122) + (_t115 * _t47)) * _t25_inv;
                    dd[1] = ((_t114) * (_t120) + (_t115 * _t49)) * _t25_inv;
                    dd[2] = ((_t114) * (_t123) + (_t115 * _t45)) * _t25_inv;
                    dd[3] = ((_t114) * (_t121) + (_t115 * _t43)) * _t25_inv;
                }
            } else {
                dd[0] = ((_t114) * (_t122) + (_t115 * _t47)) * _t25_inv;
                dd[1] = ((_t114) * (_t120) + (_t115 * _t49)) * _t25_inv;
                dd[2] = ((_t114) * (_t123) + (_t115 * _t45)) * _t25_inv;
                dd[3] = ((_t114) * (_t121) + (_t115 * _t43)) * _t25_inv;
            }
        } else {
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
        }
        return dest;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = sd[3] * _t8;
        float _t22 = sd[2] * _t8;
        float _t23 = sd[0] * _t8;
        float _t24 = sd[1] * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dd[0] = java.lang.Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv;
                    dd[1] = java.lang.Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv;
                    dd[2] = java.lang.Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv;
                    dd[3] = java.lang.Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv;
                } else {
                    dd[0] = java.lang.Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                    dd[1] = java.lang.Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                    dd[2] = java.lang.Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                    dd[3] = java.lang.Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
                }
            } else {
                dd[0] = java.lang.Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                dd[1] = java.lang.Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                dd[2] = java.lang.Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                dd[3] = java.lang.Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
            }
        } else {
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
        }
        return dest;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = sd[3] * _t8;
        float _t22 = sd[2] * _t8;
        float _t23 = sd[0] * _t8;
        float _t24 = sd[1] * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dd[0] = ((_t114) * (_t50) + (_t115 * _t47)) * _t25_inv;
                    dd[1] = ((_t114) * (_t47) + (_t115 * _t49)) * _t25_inv;
                    dd[2] = ((_t114) * (_t51) + (_t115 * _t45)) * _t25_inv;
                    dd[3] = ((_t114) * (_t45) + (_t115 * _t43)) * _t25_inv;
                } else {
                    dd[0] = ((_t114) * (_t122) + (_t115 * _t47)) * _t25_inv;
                    dd[1] = ((_t114) * (_t120) + (_t115 * _t49)) * _t25_inv;
                    dd[2] = ((_t114) * (_t123) + (_t115 * _t45)) * _t25_inv;
                    dd[3] = ((_t114) * (_t121) + (_t115 * _t43)) * _t25_inv;
                }
            } else {
                dd[0] = ((_t114) * (_t122) + (_t115 * _t47)) * _t25_inv;
                dd[1] = ((_t114) * (_t120) + (_t115 * _t49)) * _t25_inv;
                dd[2] = ((_t114) * (_t123) + (_t115 * _t45)) * _t25_inv;
                dd[3] = ((_t114) * (_t121) + (_t115 * _t43)) * _t25_inv;
            }
        } else {
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
        }
        return dest;
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
    public Float4 absolute(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
        dd[3] = java.lang.Math.abs(sd[3]);
        return dest;
    }


    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 absolute(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
        dd[3] = java.lang.Math.abs(sd[3]);
        return dest;
    }


    /**
     * Compute the arc cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 acos(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.acos(sd[0]);
        dd[1] = Math.acos(sd[1]);
        dd[2] = Math.acos(sd[2]);
        dd[3] = Math.acos(sd[3]);
        return dest;
    }


    /**
     * Compute the arc cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 acos(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.acos(sd[0]);
        dd[1] = Math.acos(sd[1]);
        dd[2] = Math.acos(sd[2]);
        dd[3] = Math.acos(sd[3]);
        return dest;
    }


    /**
     * Add {@code b} scaled by {@code scalar} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param scalar the factor to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Float4 addScaled(Float4R b, float scalar, @Mutated Float4 dest) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(scalar, bX, sd[0]);
            dd[1] = java.lang.Math.fma(scalar, bY, sd[1]);
            dd[2] = java.lang.Math.fma(scalar, bZ, sd[2]);
            dd[3] = java.lang.Math.fma(scalar, bW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((scalar) * (bX) + (sd[0]));
            dd[1] = ((scalar) * (bY) + (sd[1]));
            dd[2] = ((scalar) * (bZ) + (sd[2]));
            dd[3] = ((scalar) * (bW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Add {@code b} scaled by {@code scalar} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param scalar the factor to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double4 addScaled(Float4R b, float scalar, @Mutated Double4 dest) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(scalar, bX, sd[0]);
            dd[1] = java.lang.Math.fma(scalar, bY, sd[1]);
            dd[2] = java.lang.Math.fma(scalar, bZ, sd[2]);
            dd[3] = java.lang.Math.fma(scalar, bW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((scalar) * (bX) + (sd[0]));
            dd[1] = ((scalar) * (bY) + (sd[1]));
            dd[2] = ((scalar) * (bZ) + (sd[2]));
            dd[3] = ((scalar) * (bW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by {@code scalar} to this vector
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) by before
     *        adding
     * @param dest will hold the result
     * @return dest
     */
    public Float4 addScaled(float bX, float bY, float bZ, float bW, float scalar, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(scalar, bX, sd[0]);
            dd[1] = java.lang.Math.fma(scalar, bY, sd[1]);
            dd[2] = java.lang.Math.fma(scalar, bZ, sd[2]);
            dd[3] = java.lang.Math.fma(scalar, bW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((scalar) * (bX) + (sd[0]));
            dd[1] = ((scalar) * (bY) + (sd[1]));
            dd[2] = ((scalar) * (bZ) + (sd[2]));
            dd[3] = ((scalar) * (bW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by {@code scalar} to this vector
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) by before
     *        adding
     * @param dest will hold the result
     * @return dest
     */
    public Double4 addScaled(float bX, float bY, float bZ, float bW, float scalar, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(scalar, bX, sd[0]);
            dd[1] = java.lang.Math.fma(scalar, bY, sd[1]);
            dd[2] = java.lang.Math.fma(scalar, bZ, sd[2]);
            dd[3] = java.lang.Math.fma(scalar, bW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((scalar) * (bX) + (sd[0]));
            dd[1] = ((scalar) * (bY) + (sd[1]));
            dd[2] = ((scalar) * (bZ) + (sd[2]));
            dd[3] = ((scalar) * (bW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Add {@code b} scaled by {@code c} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param c the per-component factors to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Float4 addScaled(Float4R b, Float4R c, @Mutated Float4 dest) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(bX, cX, sd[0]);
            dd[1] = java.lang.Math.fma(bY, cY, sd[1]);
            dd[2] = java.lang.Math.fma(bZ, cZ, sd[2]);
            dd[3] = java.lang.Math.fma(bW, cW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((bX) * (cX) + (sd[0]));
            dd[1] = ((bY) * (cY) + (sd[1]));
            dd[2] = ((bZ) * (cZ) + (sd[2]));
            dd[3] = ((bW) * (cW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Add {@code b} scaled by {@code c} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param c the per-component factors to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double4 addScaled(Float4R b, Float4R c, @Mutated Double4 dest) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(bX, cX, sd[0]);
            dd[1] = java.lang.Math.fma(bY, cY, sd[1]);
            dd[2] = java.lang.Math.fma(bZ, cZ, sd[2]);
            dd[3] = java.lang.Math.fma(bW, cW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((bX) * (cX) + (sd[0]));
            dd[1] = ((bY) * (cY) + (sd[1]));
            dd[2] = ((bZ) * (cZ) + (sd[2]));
            dd[3] = ((bW) * (cW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by ({@code cX}, {@code cY},
     * {@code cZ}, {@code cW}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 addScaled(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(bX, cX, sd[0]);
            dd[1] = java.lang.Math.fma(bY, cY, sd[1]);
            dd[2] = java.lang.Math.fma(bZ, cZ, sd[2]);
            dd[3] = java.lang.Math.fma(bW, cW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((bX) * (cX) + (sd[0]));
            dd[1] = ((bY) * (cY) + (sd[1]));
            dd[2] = ((bZ) * (cZ) + (sd[2]));
            dd[3] = ((bW) * (cW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by ({@code cX}, {@code cY},
     * {@code cZ}, {@code cW}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 addScaled(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(bX, cX, sd[0]);
            dd[1] = java.lang.Math.fma(bY, cY, sd[1]);
            dd[2] = java.lang.Math.fma(bZ, cZ, sd[2]);
            dd[3] = java.lang.Math.fma(bW, cW, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((bX) * (cX) + (sd[0]));
            dd[1] = ((bY) * (cY) + (sd[1]));
            dd[2] = ((bZ) * (cZ) + (sd[2]));
            dd[3] = ((bW) * (cW) + (sd[3]));
            return dest;
        }
    }


    /**
     * Compute the angle in radians between this vector and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}
     */
    public float angleBetween(Float4R other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        float _t12 = Math.fma(otherW, sd[2], -(otherZ * sd[3]));
        float _t13 = Math.fma(otherW, sd[1], -(otherY * sd[3]));
        float _t14 = Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        float _t15 = Math.fma(otherW, sd[0], -(otherX * sd[3]));
        float _t16 = Math.fma(otherY, sd[0], -(otherX * sd[1]));
        float _t17 = Math.fma(otherZ, sd[0], -(otherX * sd[2]));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return (Math.useFma() ? angleBetween_degenerate_fma(otherX, otherY, otherZ, otherW) : angleBetween_degenerate_mulAdd(otherX, otherY, otherZ, otherW));
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherW, sd[3], Math.fma(otherZ, sd[2], Math.fma(otherX, sd[0], otherY * sd[1]))));
    }


    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the angle in radians between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public float angleBetween(float otherX, float otherY, float otherZ, float otherW) {
        float[] sd = this.data;
        float _t12 = Math.fma(otherW, sd[2], -(otherZ * sd[3]));
        float _t13 = Math.fma(otherW, sd[1], -(otherY * sd[3]));
        float _t14 = Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        float _t15 = Math.fma(otherW, sd[0], -(otherX * sd[3]));
        float _t16 = Math.fma(otherY, sd[0], -(otherX * sd[1]));
        float _t17 = Math.fma(otherZ, sd[0], -(otherX * sd[2]));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return (Math.useFma() ? angleBetween_degenerate_fma(otherX, otherY, otherZ, otherW) : angleBetween_degenerate_mulAdd(otherX, otherY, otherZ, otherW));
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherW, sd[3], Math.fma(otherZ, sd[2], Math.fma(otherX, sd[0], otherY * sd[1]))));
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private float angleBetween_degenerate_fma(float otherX, float otherY, float otherZ, float otherW) {
        float[] sd = this.data;
        float _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        float _t16 = otherW * _t6;
        float _t17 = sd[2] * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = sd[3] * _t7;
        float _t20 = otherY * _t6;
        float _t21 = sd[0] * _t7;
        float _t22 = otherX * _t6;
        float _t23 = sd[1] * _t7;
        float _t36 = java.lang.Math.fma(_t16, _t17, -(_t18 * _t19));
        float _t37 = java.lang.Math.fma(_t20, _t21, -(_t22 * _t23));
        float _t38 = java.lang.Math.fma(_t18, _t21, -(_t22 * _t17));
        float _t39 = java.lang.Math.fma(_t16, _t21, -(_t22 * _t19));
        float _t40 = java.lang.Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = java.lang.Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        return angleBetween_degenerate_sd601ab17_1_fma(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t51, _t36 * _t51, _t41 * _t51, _t40 * _t51, _t39 * _t51, _t37 * _t51, _t38 * _t51);
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private float angleBetween_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW) {
        float[] sd = this.data;
        float _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        float _t16 = otherW * _t6;
        float _t17 = sd[2] * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = sd[3] * _t7;
        float _t20 = otherY * _t6;
        float _t21 = sd[0] * _t7;
        float _t22 = otherX * _t6;
        float _t23 = sd[1] * _t7;
        float _t36 = ((_t16) * (_t17) - (_t18 * _t19));
        float _t37 = ((_t20) * (_t21) - (_t22 * _t23));
        float _t38 = ((_t18) * (_t21) - (_t22 * _t17));
        float _t39 = ((_t16) * (_t21) - (_t22 * _t19));
        float _t40 = ((_t18) * (_t23) - (_t20 * _t17));
        float _t41 = ((_t16) * (_t23) - (_t20 * _t19));
        float _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        return angleBetween_degenerate_sd601ab17_1_mulAdd(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t51, _t36 * _t51, _t41 * _t51, _t40 * _t51, _t39 * _t51, _t37 * _t51, _t38 * _t51);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private float angleBetween_degenerate_sd601ab17_1_fma(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t51, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63) {
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t58, _t58, java.lang.Math.fma(_t59, _t59, java.lang.Math.fma(_t60, _t60, java.lang.Math.fma(_t61, _t61, java.lang.Math.fma(_t62, _t62, _t63 * _t63)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private float angleBetween_degenerate_sd601ab17_1_mulAdd(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t51, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63) {
        return Math.atan2((float) java.lang.Math.sqrt(((_t58) * (_t58) + (((_t59) * (_t59) + (((_t60) * (_t60) + (((_t61) * (_t61) + (((_t62) * (_t62) + (_t63 * _t63))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t51);
    }


    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 asin(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.asin(sd[0]);
        dd[1] = Math.asin(sd[1]);
        dd[2] = Math.asin(sd[2]);
        dd[3] = Math.asin(sd[3]);
        return dest;
    }


    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 asin(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.asin(sd[0]);
        dd[1] = Math.asin(sd[1]);
        dd[2] = Math.asin(sd[2]);
        dd[3] = Math.asin(sd[3]);
        return dest;
    }


    /**
     * Compute the arc tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 atan(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.atan(sd[0]);
        dd[1] = Math.atan(sd[1]);
        dd[2] = Math.atan(sd[2]);
        dd[3] = Math.atan(sd[3]);
        return dest;
    }


    /**
     * Compute the arc tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 atan(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan(sd[0]);
        dd[1] = Math.atan(sd[1]);
        dd[2] = Math.atan(sd[2]);
        dd[3] = Math.atan(sd[3]);
        return dest;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} {@code x} (the denominator) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the value to take the arc tangent over (the denominator)
     * @param dest will hold the result
     * @return dest
     */
    public Float4 atan2(float x, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], x);
        dd[1] = Math.atan2(sd[1], x);
        dd[2] = Math.atan2(sd[2], x);
        dd[3] = Math.atan2(sd[3], x);
        return dest;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} {@code x} (the denominator) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the value to take the arc tangent over (the denominator)
     * @param dest will hold the result
     * @return dest
     */
    public Double4 atan2(float x, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], x);
        dd[1] = Math.atan2(sd[1], x);
        dd[2] = Math.atan2(sd[2], x);
        dd[3] = Math.atan2(sd[3], x);
        return dest;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of {@code x} (the
     * denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the vector of denominators, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Float4 atan2(Float4R x, @Mutated Float4 dest) {
        float xY = x.y();
        float xZ = x.z();
        float xW = x.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], x.x());
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
        dd[3] = Math.atan2(sd[3], xW);
        return dest;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of {@code x} (the
     * denominator) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the vector of denominators, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Double4 atan2(Float4R x, @Mutated Double4 dest) {
        float xY = x.y();
        float xZ = x.z();
        float xW = x.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], x.x());
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
        dd[3] = Math.atan2(sd[3], xW);
        return dest;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}, {@code xZ}, {@code xW}) (the denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xX the {@code x} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xY the {@code y} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xZ the {@code z} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xW the {@code w} component of the vector {@code (xX, xY, xZ, xW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 atan2(float xX, float xY, float xZ, float xW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], xX);
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
        dd[3] = Math.atan2(sd[3], xW);
        return dest;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}, {@code xZ}, {@code xW}) (the denominator) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xX the {@code x} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xY the {@code y} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xZ the {@code z} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xW the {@code w} component of the vector {@code (xX, xY, xZ, xW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 atan2(float xX, float xY, float xZ, float xW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], xX);
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
        dd[3] = Math.atan2(sd[3], xW);
        return dest;
    }


    /**
     * Compute the cube root of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 cbrt(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.cbrt(sd[0]);
        dd[1] = Math.cbrt(sd[1]);
        dd[2] = Math.cbrt(sd[2]);
        dd[3] = Math.cbrt(sd[3]);
        return dest;
    }


    /**
     * Compute the cube root of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cbrt(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.cbrt(sd[0]);
        dd[1] = Math.cbrt(sd[1]);
        dd[2] = Math.cbrt(sd[2]);
        dd[3] = Math.cbrt(sd[3]);
        return dest;
    }


    /**
     * Compute the ceiling of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 ceil(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.ceil(sd[0]);
        dd[1] = Math.ceil(sd[1]);
        dd[2] = Math.ceil(sd[2]);
        dd[3] = Math.ceil(sd[3]);
        return dest;
    }


    /**
     * Compute the ceiling of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 ceil(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.ceil(sd[0]);
        dd[1] = Math.ceil(sd[1]);
        dd[2] = Math.ceil(sd[2]);
        dd[3] = Math.ceil(sd[3]);
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
    public Float4 clamp(float min, float max, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], min), max);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], min), max);
        return dest;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Double4 clamp(float min, float max, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], min), max);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], min), max);
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
    public Float4 clamp(Float4R min, Float4R max, @Mutated Float4 dest) {
        float minY = min.y();
        float minZ = min.z();
        float minW = min.w();
        float maxY = max.y();
        float maxZ = max.z();
        float maxW = max.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], minW), maxW);
        return dest;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Double4 clamp(Float4R min, Float4R max, @Mutated Double4 dest) {
        float minY = min.y();
        float minZ = min.z();
        float minW = min.w();
        float maxY = max.y();
        float maxZ = max.z();
        float maxW = max.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], minW), maxW);
        return dest;
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
    public Float4 clamp(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], minW), maxW);
        return dest;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public Double4 clamp(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], minW), maxW);
        return dest;
    }


    /**
     * Compute the sum of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public float compAdd() {
        float[] sd = this.data;
        return sd[3] + (sd[2] + (sd[0] + sd[1]));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public float compMax() {
        float[] sd = this.data;
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(sd[0], sd[1]), sd[2]), sd[3]);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public float compMin() {
        float[] sd = this.data;
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(sd[0], sd[1]), sd[2]), sd[3]);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public float compMul() {
        float[] sd = this.data;
        return sd[3] * sd[2] * sd[0] * sd[1];
    }


    /**
     * Copy the sign of {@code sign} onto each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    public Float4 copySign(float sign, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], sign);
        dd[1] = Math.copySign(sd[1], sign);
        dd[2] = Math.copySign(sd[2], sign);
        dd[3] = Math.copySign(sd[3], sign);
        return dest;
    }


    /**
     * Copy the sign of {@code sign} onto each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    public Double4 copySign(float sign, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], sign);
        dd[1] = Math.copySign(sd[1], sign);
        dd[2] = Math.copySign(sd[2], sign);
        dd[3] = Math.copySign(sd[3], sign);
        return dest;
    }


    /**
     * Copy the sign of each component of {@code sign} onto the corresponding component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    public Float4 copySign(Float4R sign, @Mutated Float4 dest) {
        float signY = sign.y();
        float signZ = sign.z();
        float signW = sign.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], sign.x());
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
        dd[3] = Math.copySign(sd[3], signW);
        return dest;
    }


    /**
     * Copy the sign of each component of {@code sign} onto the corresponding component of this
     * vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    public Double4 copySign(Float4R sign, @Mutated Double4 dest) {
        float signY = sign.y();
        float signZ = sign.z();
        float signW = sign.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], sign.x());
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
        dd[3] = Math.copySign(sd[3], signW);
        return dest;
    }


    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}, {@code signZ},
     * {@code signW}) onto the corresponding component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param signX the {@code x} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signY the {@code y} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signZ the {@code z} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signW the {@code w} component of the vector {@code (signX, signY, signZ, signW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 copySign(float signX, float signY, float signZ, float signW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], signX);
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
        dd[3] = Math.copySign(sd[3], signW);
        return dest;
    }


    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}, {@code signZ},
     * {@code signW}) onto the corresponding component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param signX the {@code x} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signY the {@code y} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signZ the {@code z} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signW the {@code w} component of the vector {@code (signX, signY, signZ, signW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 copySign(float signX, float signY, float signZ, float signW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], signX);
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
        dd[3] = Math.copySign(sd[3], signW);
        return dest;
    }


    /**
     * Compute the cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 cos(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.cos(sd[0]);
        dd[1] = Math.cos(sd[1]);
        dd[2] = Math.cos(sd[2]);
        dd[3] = Math.cos(sd[3]);
        return dest;
    }


    /**
     * Compute the cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cos(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.cos(sd[0]);
        dd[1] = Math.cos(sd[1]);
        dd[2] = Math.cos(sd[2]);
        dd[3] = Math.cos(sd[3]);
        return dest;
    }


    /**
     * Compute the hyperbolic cosine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 cosh(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.cosh(sd[0]);
        dd[1] = Math.cosh(sd[1]);
        dd[2] = Math.cosh(sd[2]);
        dd[3] = Math.cosh(sd[3]);
        return dest;
    }


    /**
     * Compute the hyperbolic cosine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cosh(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.cosh(sd[0]);
        dd[1] = Math.cosh(sd[1]);
        dd[2] = Math.cosh(sd[2]);
        dd[3] = Math.cosh(sd[3]);
        return dest;
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
    public Float4 cross(Float4R v, Float4R w, @Mutated Float4 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float vW = v.w();
        float wX = w.x();
        float wY = w.y();
        float wZ = w.z();
        float wW = w.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = Math.fma(_rd3, _t12, Math.fma(_rd1, _t13, -(_rd2 * _t14)));
        dd[1] = Math.fma(-_rd3, _t15, Math.fma(_rd2, _t16, -(_rd0 * _t13)));
        dd[2] = Math.fma(_rd3, _t17, Math.fma(_rd0, _t14, -(_rd1 * _t16)));
        dd[3] = Math.fma(-_rd2, _t17, Math.fma(_rd1, _t15, -(_rd0 * _t12)));
        return dest;
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cross(Float4R v, Float4R w, @Mutated Double4 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float vW = v.w();
        float wX = w.x();
        float wY = w.y();
        float wZ = w.z();
        float wW = w.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        dd[0] = Math.fma(sd[3], _t12, Math.fma(sd[1], _t13, -(sd[2] * _t14)));
        dd[1] = Math.fma(-sd[3], _t15, Math.fma(sd[2], _t16, -(sd[0] * _t13)));
        dd[2] = Math.fma(sd[3], _t17, Math.fma(sd[0], _t14, -(sd[1] * _t16)));
        dd[3] = Math.fma(-sd[2], _t17, Math.fma(sd[1], _t15, -(sd[0] * _t12)));
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
    public Float4 cross(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = Math.fma(_rd3, _t12, Math.fma(_rd1, _t13, -(_rd2 * _t14)));
        dd[1] = Math.fma(-_rd3, _t15, Math.fma(_rd2, _t16, -(_rd0 * _t13)));
        dd[2] = Math.fma(_rd3, _t17, Math.fma(_rd0, _t14, -(_rd1 * _t16)));
        dd[3] = Math.fma(-_rd2, _t17, Math.fma(_rd1, _t15, -(_rd0 * _t12)));
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public Double4 cross(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        dd[0] = Math.fma(sd[3], _t12, Math.fma(sd[1], _t13, -(sd[2] * _t14)));
        dd[1] = Math.fma(-sd[3], _t15, Math.fma(sd[2], _t16, -(sd[0] * _t13)));
        dd[2] = Math.fma(sd[3], _t17, Math.fma(sd[0], _t14, -(sd[1] * _t16)));
        dd[3] = Math.fma(-sd[2], _t17, Math.fma(sd[1], _t15, -(sd[0] * _t12)));
        return dest;
    }


    /**
     * Compute the value converted from radians to degrees of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 degrees(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.toDegrees(sd[0]);
        dd[1] = Math.toDegrees(sd[1]);
        dd[2] = Math.toDegrees(sd[2]);
        dd[3] = Math.toDegrees(sd[3]);
        return dest;
    }


    /**
     * Compute the value converted from radians to degrees of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 degrees(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.toDegrees(sd[0]);
        dd[1] = Math.toDegrees(sd[1]);
        dd[2] = Math.toDegrees(sd[2]);
        dd[3] = Math.toDegrees(sd[3]);
        return dest;
    }


    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    public float distance(Float4R other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }


    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the distance between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     *        {@code otherW})
     */
    public float distance(float otherX, float otherY, float otherZ, float otherW) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public float distanceSquared(Float4R other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
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
    public float distanceSquared(float otherX, float otherY, float otherZ, float otherW) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float[] sd = this.data;
            float _t0 = sd[3] - otherW;
            float _t1 = sd[2] - otherZ;
            float _t2 = sd[0] - otherX;
            float _t3 = sd[1] - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public float dot(Float4R other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.fma(otherW, sd[3], java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1])));
        } else {
            float[] sd = this.data;
            return ((otherW) * (sd[3]) + (((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1]))))));
        }
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
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
    public float dot(float otherX, float otherY, float otherZ, float otherW) {
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.fma(otherW, sd[3], java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1])));
        } else {
            float[] sd = this.data;
            return ((otherW) * (sd[3]) + (((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1]))))));
        }
    }


    /**
     * Compute the base-e exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 exp(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.exp(sd[0]);
        dd[1] = Math.exp(sd[1]);
        dd[2] = Math.exp(sd[2]);
        dd[3] = Math.exp(sd[3]);
        return dest;
    }


    /**
     * Compute the base-e exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 exp(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.exp(sd[0]);
        dd[1] = Math.exp(sd[1]);
        dd[2] = Math.exp(sd[2]);
        dd[3] = Math.exp(sd[3]);
        return dest;
    }


    /**
     * Compute the base-2 exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 exp2(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.pow(2.0f, sd[0]);
        dd[1] = Math.pow(2.0f, sd[1]);
        dd[2] = Math.pow(2.0f, sd[2]);
        dd[3] = Math.pow(2.0f, sd[3]);
        return dest;
    }


    /**
     * Compute the base-2 exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 exp2(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(2.0f, sd[0]);
        dd[1] = Math.pow(2.0f, sd[1]);
        dd[2] = Math.pow(2.0f, sd[2]);
        dd[3] = Math.pow(2.0f, sd[3]);
        return dest;
    }


    /**
     * Compute the base-e exponential minus one of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 expm1(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.expm1(sd[0]);
        dd[1] = Math.expm1(sd[1]);
        dd[2] = Math.expm1(sd[2]);
        dd[3] = Math.expm1(sd[3]);
        return dest;
    }


    /**
     * Compute the base-e exponential minus one of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 expm1(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.expm1(sd[0]);
        dd[1] = Math.expm1(sd[1]);
        dd[2] = Math.expm1(sd[2]);
        dd[3] = Math.expm1(sd[3]);
        return dest;
    }


    /**
     * Return this vector unchanged when {@code dot(Nref, I)} is negative, and negated otherwise -
     * orienting it against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @param dest will hold the result
     * @return dest
     */
    public Float4 faceforward(Float4R I, Float4R Nref, @Mutated Float4 dest) {
        float IX = I.x();
        float IY = I.y();
        float IZ = I.z();
        float IW = I.w();
        float NrefX = Nref.x();
        float NrefY = Nref.y();
        float NrefZ = Nref.z();
        float NrefW = Nref.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        }
    }


    /**
     * Return this vector unchanged when {@code dot(Nref, I)} is negative, and negated otherwise -
     * orienting it against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @param dest will hold the result
     * @return dest
     */
    public Double4 faceforward(Float4R I, Float4R Nref, @Mutated Double4 dest) {
        float IX = I.x();
        float IY = I.y();
        float IZ = I.z();
        float IW = I.w();
        float NrefX = Nref.x();
        float NrefY = Nref.y();
        float NrefZ = Nref.z();
        float NrefW = Nref.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        }
    }


    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))}
     * is negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}, {@code IW}) as judged by the reference vector ({@code NrefX},
     * {@code NrefY}, {@code NrefZ}, {@code NrefW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IW the {@code w} component of the vector {@code (IX, IY, IZ, IW)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefW the {@code w} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 faceforward(float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        }
    }


    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))}
     * is negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}, {@code IW}) as judged by the reference vector ({@code NrefX},
     * {@code NrefY}, {@code NrefZ}, {@code NrefW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IW the {@code w} component of the vector {@code (IX, IY, IZ, IW)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefW the {@code w} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 faceforward(float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        }
    }


    /**
     * Compute the floor of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 floor(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.floor(sd[0]);
        dd[1] = Math.floor(sd[1]);
        dd[2] = Math.floor(sd[2]);
        dd[3] = Math.floor(sd[3]);
        return dest;
    }


    /**
     * Compute the floor of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 floor(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.floor(sd[0]);
        dd[1] = Math.floor(sd[1]);
        dd[2] = Math.floor(sd[2]);
        dd[3] = Math.floor(sd[3]);
        return dest;
    }


    /**
     * Compute the fractional part of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 fract(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0] - Math.floor(sd[0]), 0.99999994f);
        dd[1] = java.lang.Math.min(sd[1] - Math.floor(sd[1]), 0.99999994f);
        dd[2] = java.lang.Math.min(sd[2] - Math.floor(sd[2]), 0.99999994f);
        dd[3] = java.lang.Math.min(sd[3] - Math.floor(sd[3]), 0.99999994f);
        return dest;
    }


    /**
     * Compute the fractional part of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 fract(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0] - Math.floor(sd[0]), 0.99999994f);
        dd[1] = java.lang.Math.min(sd[1] - Math.floor(sd[1]), 0.99999994f);
        dd[2] = java.lang.Math.min(sd[2] - Math.floor(sd[2]), 0.99999994f);
        dd[3] = java.lang.Math.min(sd[3] - Math.floor(sd[3]), 0.99999994f);
        return dest;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} {@code y} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the other operand
     * @param dest will hold the result
     * @return dest
     */
    public Float4 hypot(float y, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], y);
        dd[1] = Math.hypot(sd[1], y);
        dd[2] = Math.hypot(sd[2], y);
        dd[3] = Math.hypot(sd[3], y);
        return dest;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} {@code y} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the other operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4 hypot(float y, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], y);
        dd[1] = Math.hypot(sd[1], y);
        dd[2] = Math.hypot(sd[2], y);
        dd[3] = Math.hypot(sd[3], y);
        return dest;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of {@code y} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the vector of other operands, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Float4 hypot(Float4R y, @Mutated Float4 dest) {
        float yY = y.y();
        float yZ = y.z();
        float yW = y.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], y.x());
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
        dd[3] = Math.hypot(sd[3], yW);
        return dest;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of {@code y} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the vector of other operands, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Double4 hypot(Float4R y, @Mutated Double4 dest) {
        float yY = y.y();
        float yZ = y.z();
        float yW = y.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], y.x());
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
        dd[3] = Math.hypot(sd[3], yW);
        return dest;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yW the {@code w} component of the vector {@code (yX, yY, yZ, yW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 hypot(float yX, float yY, float yZ, float yW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], yX);
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
        dd[3] = Math.hypot(sd[3], yW);
        return dest;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yW the {@code w} component of the vector {@code (yX, yY, yZ, yW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 hypot(float yX, float yY, float yZ, float yW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], yX);
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
        dd[3] = Math.hypot(sd[3], yW);
        return dest;
    }


    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 inverse(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = 1.0f / sd[0];
        dd[1] = 1.0f / sd[1];
        dd[2] = 1.0f / sd[2];
        dd[3] = 1.0f / sd[3];
        return dest;
    }


    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 inverse(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = 1.0f / sd[0];
        dd[1] = 1.0f / sd[1];
        dd[2] = 1.0f / sd[2];
        dd[3] = 1.0f / sd[3];
        return dest;
    }


    /**
     * Compute the inverse square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 inverseSqrt(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = (1.0f / (float) java.lang.Math.sqrt(sd[0]));
        dd[1] = (1.0f / (float) java.lang.Math.sqrt(sd[1]));
        dd[2] = (1.0f / (float) java.lang.Math.sqrt(sd[2]));
        dd[3] = (1.0f / (float) java.lang.Math.sqrt(sd[3]));
        return dest;
    }


    /**
     * Compute the inverse square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 inverseSqrt(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = (1.0f / (float) java.lang.Math.sqrt(sd[0]));
        dd[1] = (1.0f / (float) java.lang.Math.sqrt(sd[1]));
        dd[2] = (1.0f / (float) java.lang.Math.sqrt(sd[2]));
        dd[3] = (1.0f / (float) java.lang.Math.sqrt(sd[3]));
        return dest;
    }


    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this vector
     */
    public float length() {
        if (Math.useFma()) {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]))));
        } else {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1])))))));
        }
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public float lengthSquared() {
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        } else {
            float[] sd = this.data;
            return ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
        }
    }


    /**
     * Compute the natural logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 log(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.log(sd[0]);
        dd[1] = Math.log(sd[1]);
        dd[2] = Math.log(sd[2]);
        dd[3] = Math.log(sd[3]);
        return dest;
    }


    /**
     * Compute the natural logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 log(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log(sd[0]);
        dd[1] = Math.log(sd[1]);
        dd[2] = Math.log(sd[2]);
        dd[3] = Math.log(sd[3]);
        return dest;
    }


    /**
     * Compute the base-10 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 log10(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.log10(sd[0]);
        dd[1] = Math.log10(sd[1]);
        dd[2] = Math.log10(sd[2]);
        dd[3] = Math.log10(sd[3]);
        return dest;
    }


    /**
     * Compute the base-10 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 log10(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log10(sd[0]);
        dd[1] = Math.log10(sd[1]);
        dd[2] = Math.log10(sd[2]);
        dd[3] = Math.log10(sd[3]);
        return dest;
    }


    /**
     * Compute the natural logarithm of one plus the value of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 log1p(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.log1p(sd[0]);
        dd[1] = Math.log1p(sd[1]);
        dd[2] = Math.log1p(sd[2]);
        dd[3] = Math.log1p(sd[3]);
        return dest;
    }


    /**
     * Compute the natural logarithm of one plus the value of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 log1p(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log1p(sd[0]);
        dd[1] = Math.log1p(sd[1]);
        dd[2] = Math.log1p(sd[2]);
        dd[3] = Math.log1p(sd[3]);
        return dest;
    }


    /**
     * Compute the base-2 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 log2(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.log2(sd[0]);
        dd[1] = Math.log2(sd[1]);
        dd[2] = Math.log2(sd[2]);
        dd[3] = Math.log2(sd[3]);
        return dest;
    }


    /**
     * Compute the base-2 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 log2(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log2(sd[0]);
        dd[1] = Math.log2(sd[1]);
        dd[2] = Math.log2(sd[2]);
        dd[3] = Math.log2(sd[3]);
        return dest;
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public float manhattanDistance(Float4R other) {
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - other.x()) + java.lang.Math.abs(sd[1] - other.y()) + java.lang.Math.abs(sd[2] - other.z()) + java.lang.Math.abs(sd[3] - other.w());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
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
    public float manhattanDistance(float otherX, float otherY, float otherZ, float otherW) {
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherX) + java.lang.Math.abs(sd[1] - otherY) + java.lang.Math.abs(sd[2] - otherZ) + java.lang.Math.abs(sd[3] - otherW);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public float manhattanLength() {
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0]) + java.lang.Math.abs(sd[1]) + java.lang.Math.abs(sd[2]) + java.lang.Math.abs(sd[3]);
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 max(float scalar, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        dd[2] = java.lang.Math.max(sd[2], scalar);
        dd[3] = java.lang.Math.max(sd[3], scalar);
        return dest;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 max(float scalar, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        dd[2] = java.lang.Math.max(sd[2], scalar);
        dd[3] = java.lang.Math.max(sd[3], scalar);
        return dest;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 max(Float4R other, @Mutated Float4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        dd[3] = java.lang.Math.max(sd[3], otherW);
        return dest;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 max(Float4R other, @Mutated Double4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        dd[3] = java.lang.Math.max(sd[3], otherW);
        return dest;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 max(float otherX, float otherY, float otherZ, float otherW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        dd[3] = java.lang.Math.max(sd[3], otherW);
        return dest;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 max(float otherX, float otherY, float otherZ, float otherW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        dd[3] = java.lang.Math.max(sd[3], otherW);
        return dest;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 min(float scalar, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        dd[2] = java.lang.Math.min(sd[2], scalar);
        dd[3] = java.lang.Math.min(sd[3], scalar);
        return dest;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 min(float scalar, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        dd[2] = java.lang.Math.min(sd[2], scalar);
        dd[3] = java.lang.Math.min(sd[3], scalar);
        return dest;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 min(Float4R other, @Mutated Float4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        dd[3] = java.lang.Math.min(sd[3], otherW);
        return dest;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 min(Float4R other, @Mutated Double4 dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        dd[3] = java.lang.Math.min(sd[3], otherW);
        return dest;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 min(float otherX, float otherY, float otherZ, float otherW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        dd[3] = java.lang.Math.min(sd[3], otherW);
        return dest;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 min(float otherX, float otherY, float otherZ, float otherW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        dd[3] = java.lang.Math.min(sd[3], otherW);
        return dest;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: {@code y} must be non-zero.
     *
     * @param y the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Float4 mod(float y, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = flooredMod(sd[0], y);
        dd[1] = flooredMod(sd[1], y);
        dd[2] = flooredMod(sd[2], y);
        dd[3] = flooredMod(sd[3], y);
        return dest;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code y} must be non-zero.
     *
     * @param y the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mod(float y, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = flooredMod(sd[0], y);
        dd[1] = flooredMod(sd[1], y);
        dd[2] = flooredMod(sd[2], y);
        dd[3] = flooredMod(sd[3], y);
        return dest;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Float4 mod(Float4R y, @Mutated Float4 dest) {
        float yX = y.x();
        float yY = y.y();
        float yZ = y.z();
        float yW = y.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
        dd[2] = flooredMod(sd[2], yZ);
        dd[3] = flooredMod(sd[3], yW);
        return dest;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mod(Float4R y, @Mutated Double4 dest) {
        float yX = y.x();
        float yY = y.y();
        float yZ = y.z();
        float yW = y.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
        dd[2] = flooredMod(sd[2], yZ);
        dd[3] = flooredMod(sd[3], yW);
        return dest;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) ({@code x % y}, plus {@code y} when that remainder is non-zero and
     * its sign differs from {@code y}'s - exactly Kotlin's {@code mod}) and store the result in
     * {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (yX, yY, yZ, yW)} must be non-zero.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yW the {@code w} component of the vector {@code (yX, yY, yZ, yW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 mod(float yX, float yY, float yZ, float yW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
        dd[2] = flooredMod(sd[2], yZ);
        dd[3] = flooredMod(sd[3], yW);
        return dest;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) ({@code x % y}, plus {@code y} when that remainder is non-zero and
     * its sign differs from {@code y}'s - exactly Kotlin's {@code mod}) and store the result in
     * {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (yX, yY, yZ, yW)} must be non-zero.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yW the {@code w} component of the vector {@code (yX, yY, yZ, yW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mod(float yX, float yY, float yZ, float yW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
        dd[2] = flooredMod(sd[2], yZ);
        dd[3] = flooredMod(sd[3], yW);
        return dest;
    }


    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 nextDown(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.nextDown(sd[0]);
        dd[1] = Math.nextDown(sd[1]);
        dd[2] = Math.nextDown(sd[2]);
        dd[3] = Math.nextDown(sd[3]);
        return dest;
    }


    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 nextDown(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.nextDown(sd[0]);
        dd[1] = Math.nextDown(sd[1]);
        dd[2] = Math.nextDown(sd[2]);
        dd[3] = Math.nextDown(sd[3]);
        return dest;
    }


    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 nextUp(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.nextUp(sd[0]);
        dd[1] = Math.nextUp(sd[1]);
        dd[2] = Math.nextUp(sd[2]);
        dd[3] = Math.nextUp(sd[3]);
        return dest;
    }


    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 nextUp(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.nextUp(sd[0]);
        dd[1] = Math.nextUp(sd[1]);
        dd[2] = Math.nextUp(sd[2]);
        dd[3] = Math.nextUp(sd[3]);
        return dest;
    }


    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 normalize(@Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        }
    }


    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 normalize(@Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        }
    }


    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @param dest will hold the result
     * @return dest
     */
    public Float4 normalizeMul(float length, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t5;
                dd[1] = sd[1] * _t5;
                dd[2] = sd[2] * _t5;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t5;
                dd[1] = sd[1] * _t5;
                dd[2] = sd[2] * _t5;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        }
    }


    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @param dest will hold the result
     * @return dest
     */
    public Double4 normalizeMul(float length, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t5;
                dd[1] = sd[1] * _t5;
                dd[2] = sd[2] * _t5;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                dd[0] = sd[0] * _t5;
                dd[1] = sd[1] * _t5;
                dd[2] = sd[2] * _t5;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0f;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = 0.0f;
            }
            return dest;
        }
    }


    /**
     * Compute the outer product of this vector and {@code row} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @param dest will hold the result
     * @return dest
     */
    public Float4x4 outerProduct(Float4R row, @Mutated Float4x4 dest) {
        float rowX = row.x();
        float rowY = row.y();
        float rowZ = row.z();
        float rowW = row.w();
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = rowX * _rd0;
        dd[1] = rowX * _rd1;
        dd[2] = rowX * _rd2;
        dd[3] = rowX * _rd3;
        dd[4] = rowY * _rd0;
        dd[5] = rowY * _rd1;
        dd[6] = rowY * _rd2;
        dd[7] = rowY * _rd3;
        dd[8] = rowZ * _rd0;
        dd[9] = rowZ * _rd1;
        dd[10] = rowZ * _rd2;
        dd[11] = rowZ * _rd3;
        dd[12] = rowW * _rd0;
        dd[13] = rowW * _rd1;
        dd[14] = rowW * _rd2;
        dd[15] = rowW * _rd3;
        ((Float4x4Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the outer product of this vector and {@code row} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 outerProduct(Float4R row, @Mutated Double4x4 dest) {
        float rowX = row.x();
        float rowY = row.y();
        float rowZ = row.z();
        float rowW = row.w();
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        dd[0] = rowX * sd[0];
        dd[1] = rowX * sd[1];
        dd[2] = rowX * sd[2];
        dd[3] = rowX * sd[3];
        dd[4] = rowY * sd[0];
        dd[5] = rowY * sd[1];
        dd[6] = rowY * sd[2];
        dd[7] = rowY * sd[3];
        dd[8] = rowZ * sd[0];
        dd[9] = rowZ * sd[1];
        dd[10] = rowZ * sd[2];
        dd[11] = rowZ * sd[3];
        dd[12] = rowW * sd[0];
        dd[13] = rowW * sd[1];
        dd[14] = rowW * sd[2];
        dd[15] = rowW * sd[3];
        ((Double4x4Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}, {@code rowZ},
     * {@code rowW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowW the {@code w} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4x4 outerProduct(float rowX, float rowY, float rowZ, float rowW, @Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = rowX * _rd0;
        dd[1] = rowX * _rd1;
        dd[2] = rowX * _rd2;
        dd[3] = rowX * _rd3;
        dd[4] = rowY * _rd0;
        dd[5] = rowY * _rd1;
        dd[6] = rowY * _rd2;
        dd[7] = rowY * _rd3;
        dd[8] = rowZ * _rd0;
        dd[9] = rowZ * _rd1;
        dd[10] = rowZ * _rd2;
        dd[11] = rowZ * _rd3;
        dd[12] = rowW * _rd0;
        dd[13] = rowW * _rd1;
        dd[14] = rowW * _rd2;
        dd[15] = rowW * _rd3;
        ((Float4x4Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}, {@code rowZ},
     * {@code rowW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowW the {@code w} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 outerProduct(float rowX, float rowY, float rowZ, float rowW, @Mutated Double4x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        dd[0] = rowX * sd[0];
        dd[1] = rowX * sd[1];
        dd[2] = rowX * sd[2];
        dd[3] = rowX * sd[3];
        dd[4] = rowY * sd[0];
        dd[5] = rowY * sd[1];
        dd[6] = rowY * sd[2];
        dd[7] = rowY * sd[3];
        dd[8] = rowZ * sd[0];
        dd[9] = rowZ * sd[1];
        dd[10] = rowZ * sd[2];
        dd[11] = rowZ * sd[3];
        dd[12] = rowW * sd[0];
        dd[13] = rowW * sd[1];
        dd[14] = rowW * sd[2];
        dd[15] = rowW * sd[3];
        ((Double4x4Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    public Float4 pow(float exponent, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponent);
        dd[1] = Math.pow(sd[1], exponent);
        dd[2] = Math.pow(sd[2], exponent);
        dd[3] = Math.pow(sd[3], exponent);
        return dest;
    }


    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    public Double4 pow(float exponent, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponent);
        dd[1] = Math.pow(sd[1], exponent);
        dd[2] = Math.pow(sd[2], exponent);
        dd[3] = Math.pow(sd[3], exponent);
        return dest;
    }


    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    public Float4 pow(Float4R exponent, @Mutated Float4 dest) {
        float exponentY = exponent.y();
        float exponentZ = exponent.z();
        float exponentW = exponent.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponent.x());
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
        dd[3] = Math.pow(sd[3], exponentW);
        return dest;
    }


    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    public Double4 pow(Float4R exponent, @Mutated Double4 dest) {
        float exponentY = exponent.y();
        float exponentZ = exponent.z();
        float exponentW = exponent.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponent.x());
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
        dd[3] = Math.pow(sd[3], exponentW);
        return dest;
    }


    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}, {@code exponentW}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponentX the {@code x} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentY the {@code y} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentZ the {@code z} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentW the {@code w} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 pow(float exponentX, float exponentY, float exponentZ, float exponentW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponentX);
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
        dd[3] = Math.pow(sd[3], exponentW);
        return dest;
    }


    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}, {@code exponentW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponentX the {@code x} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentY the {@code y} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentZ the {@code z} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentW the {@code w} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 pow(float exponentX, float exponentY, float exponentZ, float exponentW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponentX);
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
        dd[3] = Math.pow(sd[3], exponentW);
        return dest;
    }


    /**
     * Project this vector onto {@code onto} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @param dest will hold the result
     * @return dest
     */
    public Float4 project(Float4R onto, @Mutated Float4 dest) {
        float ontoX = onto.x();
        float ontoY = onto.y();
        float ontoZ = onto.z();
        float ontoW = onto.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t9 = java.lang.Math.fma(ontoW, sd[3], java.lang.Math.fma(ontoZ, sd[2], java.lang.Math.fma(ontoX, sd[0], ontoY * sd[1]))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t9 = ((ontoW) * (sd[3]) + (((ontoZ) * (sd[2]) + (((ontoX) * (sd[0]) + (ontoY * sd[1])))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        }
    }


    /**
     * Project this vector onto {@code onto} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @param dest will hold the result
     * @return dest
     */
    public Double4 project(Float4R onto, @Mutated Double4 dest) {
        float ontoX = onto.x();
        float ontoY = onto.y();
        float ontoZ = onto.z();
        float ontoW = onto.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t9 = java.lang.Math.fma(ontoW, sd[3], java.lang.Math.fma(ontoZ, sd[2], java.lang.Math.fma(ontoX, sd[0], ontoY * sd[1]))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t9 = ((ontoW) * (sd[3]) + (((ontoZ) * (sd[2]) + (((ontoX) * (sd[0]) + (ontoY * sd[1])))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        }
    }


    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}, {@code ontoZ}, {@code ontoW}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: {@code (ontoX, ontoY, ontoZ, ontoW)} must be non-zero.
     *
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoZ the {@code z} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoW the {@code w} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 project(float ontoX, float ontoY, float ontoZ, float ontoW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t9 = java.lang.Math.fma(ontoW, sd[3], java.lang.Math.fma(ontoZ, sd[2], java.lang.Math.fma(ontoX, sd[0], ontoY * sd[1]))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t9 = ((ontoW) * (sd[3]) + (((ontoZ) * (sd[2]) + (((ontoX) * (sd[0]) + (ontoY * sd[1])))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        }
    }


    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}, {@code ontoZ}, {@code ontoW}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (ontoX, ontoY, ontoZ, ontoW)} must be non-zero.
     *
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoZ the {@code z} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoW the {@code w} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 project(float ontoX, float ontoY, float ontoZ, float ontoW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t9 = java.lang.Math.fma(ontoW, sd[3], java.lang.Math.fma(ontoZ, sd[2], java.lang.Math.fma(ontoX, sd[0], ontoY * sd[1]))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t9 = ((ontoW) * (sd[3]) + (((ontoZ) * (sd[2]) + (((ontoX) * (sd[0]) + (ontoY * sd[1])))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        }
    }


    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @param dest will hold the result
     * @return dest
     */
    public Float4 projectOnPlane(Float4R normal, @Mutated Float4 dest) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t3, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t3, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t3, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t3, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t3) + (sd[0]));
            dd[1] = ((-normalY) * (_t3) + (sd[1]));
            dd[2] = ((-normalZ) * (_t3) + (sd[2]));
            dd[3] = ((-normalW) * (_t3) + (sd[3]));
            return dest;
        }
    }


    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @param dest will hold the result
     * @return dest
     */
    public Double4 projectOnPlane(Float4R normal, @Mutated Double4 dest) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t3, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t3, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t3, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t3, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t3) + (sd[0]));
            dd[1] = ((-normalY) * (_t3) + (sd[1]));
            dd[2] = ((-normalZ) * (_t3) + (sd[2]));
            dd[3] = ((-normalW) * (_t3) + (sd[3]));
            return dest;
        }
    }


    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 projectOnPlane(float normalX, float normalY, float normalZ, float normalW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t3, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t3, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t3, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t3, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t3 = ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t3) + (sd[0]));
            dd[1] = ((-normalY) * (_t3) + (sd[1]));
            dd[2] = ((-normalZ) * (_t3) + (sd[2]));
            dd[3] = ((-normalW) * (_t3) + (sd[3]));
            return dest;
        }
    }


    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 projectOnPlane(float normalX, float normalY, float normalZ, float normalW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t3, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t3, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t3, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t3, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t3 = ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t3) + (sd[0]));
            dd[1] = ((-normalY) * (_t3) + (sd[1]));
            dd[2] = ((-normalZ) * (_t3) + (sd[2]));
            dd[3] = ((-normalW) * (_t3) + (sd[3]));
            return dest;
        }
    }


    /**
     * Compute the value converted from degrees to radians of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 radians(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.toRadians(sd[0]);
        dd[1] = Math.toRadians(sd[1]);
        dd[2] = Math.toRadians(sd[2]);
        dd[3] = Math.toRadians(sd[3]);
        return dest;
    }


    /**
     * Compute the value converted from degrees to radians of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 radians(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.toRadians(sd[0]);
        dd[1] = Math.toRadians(sd[1]);
        dd[2] = Math.toRadians(sd[2]);
        dd[3] = Math.toRadians(sd[3]);
        return dest;
    }


    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @param dest will hold the result
     * @return dest
     */
    public Float4 reflect(Float4R normal, @Mutated Float4 dest) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t4, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t4, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t4, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t4, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = 2.0f * ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t4) + (sd[0]));
            dd[1] = ((-normalY) * (_t4) + (sd[1]));
            dd[2] = ((-normalZ) * (_t4) + (sd[2]));
            dd[3] = ((-normalW) * (_t4) + (sd[3]));
            return dest;
        }
    }


    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @param dest will hold the result
     * @return dest
     */
    public Double4 reflect(Float4R normal, @Mutated Double4 dest) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t4, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t4, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t4, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t4, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = 2.0f * ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t4) + (sd[0]));
            dd[1] = ((-normalY) * (_t4) + (sd[1]));
            dd[2] = ((-normalZ) * (_t4) + (sd[2]));
            dd[3] = ((-normalW) * (_t4) + (sd[3]));
            return dest;
        }
    }


    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 reflect(float normalX, float normalY, float normalZ, float normalW, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t4, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t4, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t4, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t4, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t4 = 2.0f * ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t4) + (sd[0]));
            dd[1] = ((-normalY) * (_t4) + (sd[1]));
            dd[2] = ((-normalZ) * (_t4) + (sd[2]));
            dd[3] = ((-normalW) * (_t4) + (sd[3]));
            return dest;
        }
    }


    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 reflect(float normalX, float normalY, float normalZ, float normalW, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t4, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t4, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t4, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t4, sd[3]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t4 = 2.0f * ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t4) + (sd[0]));
            dd[1] = ((-normalY) * (_t4) + (sd[1]));
            dd[2] = ((-normalZ) * (_t4) + (sd[2]));
            dd[3] = ((-normalW) * (_t4) + (sd[3]));
            return dest;
        }
    }


    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * Valid input: {@code normal} must have unit length; this vector must have unit length.
     *
     * @param normal the normal of the refracting surface
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    public Float4 refract(Float4R normal, float eta, @Mutated Float4 dest) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dd[0] = Math.fma(eta, sd[0], -(normalX * _t11));
            dd[1] = Math.fma(eta, sd[1], -(normalY * _t11));
            dd[2] = Math.fma(eta, sd[2], -(normalZ * _t11));
            dd[3] = Math.fma(eta, sd[3], -(normalW * _t11));
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code normal} must have unit length; this vector must have unit length.
     *
     * @param normal the normal of the refracting surface
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    public Double4 refract(Float4R normal, float eta, @Mutated Double4 dest) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dd[0] = Math.fma(eta, sd[0], -(normalX * _t11));
            dd[1] = Math.fma(eta, sd[1], -(normalY * _t11));
            dd[2] = Math.fma(eta, sd[2], -(normalZ * _t11));
            dd[3] = Math.fma(eta, sd[3], -(normalW * _t11));
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length; this vector
     * must have unit length.
     *
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    public Float4 refract(float normalX, float normalY, float normalZ, float normalW, float eta, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dd[0] = Math.fma(eta, sd[0], -(normalX * _t11));
            dd[1] = Math.fma(eta, sd[1], -(normalY * _t11));
            dd[2] = Math.fma(eta, sd[2], -(normalZ * _t11));
            dd[3] = Math.fma(eta, sd[3], -(normalW * _t11));
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length; this vector
     * must have unit length.
     *
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    public Double4 refract(float normalX, float normalY, float normalZ, float normalW, float eta, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dd[0] = Math.fma(eta, sd[0], -(normalX * _t11));
            dd[1] = Math.fma(eta, sd[1], -(normalY * _t11));
            dd[2] = Math.fma(eta, sd[2], -(normalZ * _t11));
            dd[3] = Math.fma(eta, sd[3], -(normalW * _t11));
        } else {
            dd[0] = 0.0f;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = 0.0f;
        }
        return dest;
    }


    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 round(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.rint(sd[0]);
        dd[1] = Math.rint(sd[1]);
        dd[2] = Math.rint(sd[2]);
        dd[3] = Math.rint(sd[3]);
        return dest;
    }


    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 round(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.rint(sd[0]);
        dd[1] = Math.rint(sd[1]);
        dd[2] = Math.rint(sd[2]);
        dd[3] = Math.rint(sd[3]);
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
    public Float4 sign(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        dd[3] = Math.signum(sd[3]);
        return dest;
    }


    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 sign(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        dd[3] = Math.signum(sd[3]);
        return dest;
    }


    /**
     * Compute the sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 sin(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.sin(sd[0]);
        dd[1] = Math.sin(sd[1]);
        dd[2] = Math.sin(sd[2]);
        dd[3] = Math.sin(sd[3]);
        return dest;
    }


    /**
     * Compute the sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 sin(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.sin(sd[0]);
        dd[1] = Math.sin(sd[1]);
        dd[2] = Math.sin(sd[2]);
        dd[3] = Math.sin(sd[3]);
        return dest;
    }


    /**
     * Compute the hyperbolic sine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 sinh(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.sinh(sd[0]);
        dd[1] = Math.sinh(sd[1]);
        dd[2] = Math.sinh(sd[2]);
        dd[3] = Math.sinh(sd[3]);
        return dest;
    }


    /**
     * Compute the hyperbolic sine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 sinh(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.sinh(sd[0]);
        dd[1] = Math.sinh(sd[1]);
        dd[2] = Math.sinh(sd[2]);
        dd[3] = Math.sinh(sd[3]);
        return dest;
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    public Float4 smoothstep(float edge0, float edge1, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[0] - edge0) * _t0_inv));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[1] - edge0) * _t0_inv));
        float _t15 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[2] - edge0) * _t0_inv));
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[3] - edge0) * _t0_inv));
        dd[0] = Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13;
        dd[1] = Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14;
        dd[2] = Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15;
        dd[3] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        return dest;
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    public Double4 smoothstep(float edge0, float edge1, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[0] - edge0) * _t0_inv));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[1] - edge0) * _t0_inv));
        float _t15 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[2] - edge0) * _t0_inv));
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[3] - edge0) * _t0_inv));
        dd[0] = Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13;
        dd[1] = Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14;
        dd[2] = Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15;
        dd[3] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        return dest;
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    public Float4 smoothstep(Float4R edge0, Float4R edge1, @Mutated Float4 dest) {
        float edge0X = edge0.x();
        float edge0Y = edge0.y();
        float edge0Z = edge0.z();
        float edge0W = edge0.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[0] - edge0X) / (edge1.x() - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[1] - edge0Y) / (edge1.y() - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[2] - edge0Z) / (edge1.z() - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[3] - edge0W) / (edge1.w() - edge0W)));
        dd[0] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        dd[1] = Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        dd[2] = Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        dd[3] = Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return dest;
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    public Double4 smoothstep(Float4R edge0, Float4R edge1, @Mutated Double4 dest) {
        float edge0X = edge0.x();
        float edge0Y = edge0.y();
        float edge0Z = edge0.z();
        float edge0W = edge0.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[0] - edge0X) / (edge1.x() - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[1] - edge0Y) / (edge1.y() - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[2] - edge0Z) / (edge1.z() - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[3] - edge0W) / (edge1.w() - edge0W)));
        dd[0] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        dd[1] = Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        dd[2] = Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        dd[3] = Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return dest;
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) and the upper
     * edge ({@code edge1X}, {@code edge1Y}, {@code edge1Z}, {@code edge1W}), yielding 0 at or below
     * the lower edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (edge0X, edge0Y, edge0Z, edge0W)} and
     * {@code (edge1X, edge1Y, edge1Z, edge1W)} must differ in every component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Z the {@code z} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0W the {@code w} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Z the {@code z} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1W the {@code w} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 smoothstep(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[0] - edge0X) / (edge1X - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[1] - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[2] - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[3] - edge0W) / (edge1W - edge0W)));
        dd[0] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        dd[1] = Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        dd[2] = Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        dd[3] = Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return dest;
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) and the upper
     * edge ({@code edge1X}, {@code edge1Y}, {@code edge1Z}, {@code edge1W}), yielding 0 at or below
     * the lower edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (edge0X, edge0Y, edge0Z, edge0W)} and
     * {@code (edge1X, edge1Y, edge1Z, edge1W)} must differ in every component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Z the {@code z} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0W the {@code w} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Z the {@code z} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1W the {@code w} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 smoothstep(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[0] - edge0X) / (edge1X - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[1] - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[2] - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (sd[3] - edge0W) / (edge1W - edge0W)));
        dd[0] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        dd[1] = Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        dd[2] = Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        dd[3] = Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return dest;
    }


    /**
     * Compute the square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 sqrt(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = (float) java.lang.Math.sqrt(sd[0]);
        dd[1] = (float) java.lang.Math.sqrt(sd[1]);
        dd[2] = (float) java.lang.Math.sqrt(sd[2]);
        dd[3] = (float) java.lang.Math.sqrt(sd[3]);
        return dest;
    }


    /**
     * Compute the square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 sqrt(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = (float) java.lang.Math.sqrt(sd[0]);
        dd[1] = (float) java.lang.Math.sqrt(sd[1]);
        dd[2] = (float) java.lang.Math.sqrt(sd[2]);
        dd[3] = (float) java.lang.Math.sqrt(sd[3]);
        return dest;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than {@code edge}, and to
     * {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    public Float4 step(float edge, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] < edge ? 0.0f : 1.0f;
        dd[1] = sd[1] < edge ? 0.0f : 1.0f;
        dd[2] = sd[2] < edge ? 0.0f : 1.0f;
        dd[3] = sd[3] < edge ? 0.0f : 1.0f;
        return dest;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than {@code edge}, and to
     * {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    public Double4 step(float edge, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] < edge ? 0.0f : 1.0f;
        dd[1] = sd[1] < edge ? 0.0f : 1.0f;
        dd[2] = sd[2] < edge ? 0.0f : 1.0f;
        dd[3] = sd[3] < edge ? 0.0f : 1.0f;
        return dest;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    public Float4 step(Float4R edge, @Mutated Float4 dest) {
        float edgeY = edge.y();
        float edgeZ = edge.z();
        float edgeW = edge.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] < edge.x() ? 0.0f : 1.0f;
        dd[1] = sd[1] < edgeY ? 0.0f : 1.0f;
        dd[2] = sd[2] < edgeZ ? 0.0f : 1.0f;
        dd[3] = sd[3] < edgeW ? 0.0f : 1.0f;
        return dest;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    public Double4 step(Float4R edge, @Mutated Double4 dest) {
        float edgeY = edge.y();
        float edgeZ = edge.z();
        float edgeW = edge.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] < edge.x() ? 0.0f : 1.0f;
        dd[1] = sd[1] < edgeY ? 0.0f : 1.0f;
        dd[2] = sd[2] < edgeZ ? 0.0f : 1.0f;
        dd[3] = sd[3] < edgeW ? 0.0f : 1.0f;
        return dest;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeW the {@code w} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 step(float edgeX, float edgeY, float edgeZ, float edgeW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] < edgeX ? 0.0f : 1.0f;
        dd[1] = sd[1] < edgeY ? 0.0f : 1.0f;
        dd[2] = sd[2] < edgeZ ? 0.0f : 1.0f;
        dd[3] = sd[3] < edgeW ? 0.0f : 1.0f;
        return dest;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeW the {@code w} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 step(float edgeX, float edgeY, float edgeZ, float edgeW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] < edgeX ? 0.0f : 1.0f;
        dd[1] = sd[1] < edgeY ? 0.0f : 1.0f;
        dd[2] = sd[2] < edgeZ ? 0.0f : 1.0f;
        dd[3] = sd[3] < edgeW ? 0.0f : 1.0f;
        return dest;
    }


    /**
     * Compute the tangent of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 tan(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.tan(sd[0]);
        dd[1] = Math.tan(sd[1]);
        dd[2] = Math.tan(sd[2]);
        dd[3] = Math.tan(sd[3]);
        return dest;
    }


    /**
     * Compute the tangent of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 tan(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.tan(sd[0]);
        dd[1] = Math.tan(sd[1]);
        dd[2] = Math.tan(sd[2]);
        dd[3] = Math.tan(sd[3]);
        return dest;
    }


    /**
     * Compute the hyperbolic tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 tanh(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.tanh(sd[0]);
        dd[1] = Math.tanh(sd[1]);
        dd[2] = Math.tanh(sd[2]);
        dd[3] = Math.tanh(sd[3]);
        return dest;
    }


    /**
     * Compute the hyperbolic tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 tanh(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.tanh(sd[0]);
        dd[1] = Math.tanh(sd[1]);
        dd[2] = Math.tanh(sd[2]);
        dd[3] = Math.tanh(sd[3]);
        return dest;
    }


    /**
     * Compute the truncated value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 trunc(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0] >= 0.0f ? Math.floor(sd[0]) : Math.ceil(sd[0]);
        dd[1] = sd[1] >= 0.0f ? Math.floor(sd[1]) : Math.ceil(sd[1]);
        dd[2] = sd[2] >= 0.0f ? Math.floor(sd[2]) : Math.ceil(sd[2]);
        dd[3] = sd[3] >= 0.0f ? Math.floor(sd[3]) : Math.ceil(sd[3]);
        return dest;
    }


    /**
     * Compute the truncated value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 trunc(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] >= 0.0f ? Math.floor(sd[0]) : Math.ceil(sd[0]);
        dd[1] = sd[1] >= 0.0f ? Math.floor(sd[1]) : Math.ceil(sd[1]);
        dd[2] = sd[2] >= 0.0f ? Math.floor(sd[2]) : Math.ceil(sd[2]);
        dd[3] = sd[3] >= 0.0f ? Math.floor(sd[3]) : Math.ceil(sd[3]);
        return dest;
    }


    /**
     * Compute the unit in the last place (ulp) of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 ulp(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = Math.ulp(sd[0]);
        dd[1] = Math.ulp(sd[1]);
        dd[2] = Math.ulp(sd[2]);
        dd[3] = Math.ulp(sd[3]);
        return dest;
    }


    /**
     * Compute the unit in the last place (ulp) of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 ulp(@Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.ulp(sd[0]);
        dd[1] = Math.ulp(sd[1]);
        dd[2] = Math.ulp(sd[2]);
        dd[3] = Math.ulp(sd[3]);
        return dest;
    }


    /**
     * Pre-multiply {@code mat} onto this vector, i.e. compute {@code mat * this} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Float4 preMul(Float4x4R mat, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] matData = ((Float4x4Impl) mat).data;
        float[] dd = ((Float4Impl) dest).data;
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        float _rd3 = sd[3];
        dd[0] = Math.fma(matData[12], _rd3, Math.fma(matData[8], _rd2, Math.fma(matData[0], _rd0, matData[4] * _rd1)));
        dd[1] = Math.fma(matData[13], _rd3, Math.fma(matData[9], _rd2, Math.fma(matData[1], _rd0, matData[5] * _rd1)));
        dd[2] = Math.fma(matData[14], _rd3, Math.fma(matData[10], _rd2, Math.fma(matData[2], _rd0, matData[6] * _rd1)));
        dd[3] = Math.fma(matData[15], _rd3, Math.fma(matData[11], _rd2, Math.fma(matData[3], _rd0, matData[7] * _rd1)));
        return dest;
    }


    /**
     * Pre-multiply {@code mat} onto this vector, i.e. compute {@code mat * this} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double4 preMul(Float4x4R mat, @Mutated Double4 dest) {
        float[] sd = this.data;
        float[] matData = ((Float4x4Impl) mat).data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.fma(matData[12], sd[3], Math.fma(matData[8], sd[2], Math.fma(matData[0], sd[0], matData[4] * sd[1])));
        dd[1] = Math.fma(matData[13], sd[3], Math.fma(matData[9], sd[2], Math.fma(matData[1], sd[0], matData[5] * sd[1])));
        dd[2] = Math.fma(matData[14], sd[3], Math.fma(matData[10], sd[2], Math.fma(matData[2], sd[0], matData[6] * sd[1])));
        dd[3] = Math.fma(matData[15], sd[3], Math.fma(matData[11], sd[2], Math.fma(matData[3], sd[0], matData[7] * sd[1])));
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion {@code quat}, i.e.
     * compute {@code q * this.xyz * q^-1}, leaving {@code w} unchanged, and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotate(FloatQuatR quat, @Mutated Float4 dest) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[1], -(quatY * sd[0]));
        float _t10 = 2.0f * Math.fma(quatZ, sd[0], -(quatX * sd[2]));
        float _t11 = 2.0f * Math.fma(quatY, sd[2], -(quatZ * sd[1]));
        dd[0] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[1])));
        dd[2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion {@code quat}, i.e.
     * compute {@code q * this.xyz * q^-1}, leaving {@code w} unchanged, and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotate(FloatQuatR quat, @Mutated Double4 dest) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[1], -(quatY * sd[0]));
        float _t10 = 2.0f * Math.fma(quatZ, sd[0], -(quatX * sd[2]));
        float _t11 = 2.0f * Math.fma(quatY, sd[2], -(quatZ * sd[1]));
        dd[0] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[1])));
        dd[2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion ({@code quatX},
     * {@code quatY}, {@code quatZ}, {@code quatW}), i.e. compute {@code q * this.xyz * q^-1},
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotate(float quatX, float quatY, float quatZ, float quatW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[1], -(quatY * sd[0]));
        float _t10 = 2.0f * Math.fma(quatZ, sd[0], -(quatX * sd[2]));
        float _t11 = 2.0f * Math.fma(quatY, sd[2], -(quatZ * sd[1]));
        dd[0] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[1])));
        dd[2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion ({@code quatX},
     * {@code quatY}, {@code quatZ}, {@code quatW}), i.e. compute {@code q * this.xyz * q^-1},
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotate(float quatX, float quatY, float quatZ, float quatW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[1], -(quatY * sd[0]));
        float _t10 = 2.0f * Math.fma(quatZ, sd[0], -(quatX * sd[2]));
        float _t11 = 2.0f * Math.fma(quatY, sd[2], -(quatZ * sd[1]));
        dd[0] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[1])));
        dd[2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis {@code axis}, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotateAxis(float angle, Float3R axis, @Mutated Float4 dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis {@code axis}, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateAxis(float angle, Float3R axis, @Mutated Double4 dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis ({@code axisX}, {@code axisY}, {@code axisZ}), leaving {@code w} unchanged, and store
     * the result in {@code dest}.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated Float4 dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        float _rd0 = sd[0];
        float _rd1 = sd[1];
        float _rd2 = sd[2];
        dd[0] = Math.fma(_t3, axisX * _t5, Math.fma(_rd0, _t1, Math.fma(axisY, _rd2, -(axisZ * _rd1)) * _t0));
        dd[1] = Math.fma(_t3, axisY * _t5, Math.fma(_rd1, _t1, Math.fma(axisZ, _rd0, -(axisX * _rd2)) * _t0));
        dd[2] = Math.fma(_t3, axisZ * _t5, Math.fma(_rd2, _t1, Math.fma(axisX, _rd1, -(axisY * _rd0)) * _t0));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis ({@code axisX}, {@code axisY}, {@code axisZ}), leaving {@code w} unchanged, and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated Double4 dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        dd[0] = Math.fma(_t3, axisX * _t5, Math.fma(sd[0], _t1, Math.fma(axisY, sd[2], -(axisZ * sd[1])) * _t0));
        dd[1] = Math.fma(_t3, axisY * _t5, Math.fma(sd[1], _t1, Math.fma(axisZ, sd[0], -(axisX * sd[2])) * _t0));
        dd[2] = Math.fma(_t3, axisZ * _t5, Math.fma(sd[2], _t1, Math.fma(axisX, sd[1], -(axisY * sd[0])) * _t0));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotateInverse(FloatQuatR quat, @Mutated Float4 dest) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[2], -(quatZ * sd[0]));
        float _t10 = 2.0f * Math.fma(quatY, sd[0], -(quatX * sd[1]));
        float _t11 = 2.0f * Math.fma(quatZ, sd[1], -(quatY * sd[2]));
        dd[0] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, sd[1])));
        dd[2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateInverse(FloatQuatR quat, @Mutated Double4 dest) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[2], -(quatZ * sd[0]));
        float _t10 = 2.0f * Math.fma(quatY, sd[0], -(quatX * sd[1]));
        float _t11 = 2.0f * Math.fma(quatZ, sd[1], -(quatY * sd[2]));
        dd[0] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, sd[1])));
        dd[2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotateInverse(float quatX, float quatY, float quatZ, float quatW, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[2], -(quatZ * sd[0]));
        float _t10 = 2.0f * Math.fma(quatY, sd[0], -(quatX * sd[1]));
        float _t11 = 2.0f * Math.fma(quatZ, sd[1], -(quatY * sd[2]));
        dd[0] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, sd[1])));
        dd[2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateInverse(float quatX, float quatY, float quatZ, float quatW, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _t9 = 2.0f * Math.fma(quatX, sd[2], -(quatZ * sd[0]));
        float _t10 = 2.0f * Math.fma(quatY, sd[0], -(quatX * sd[1]));
        float _t11 = 2.0f * Math.fma(quatZ, sd[1], -(quatY * sd[2]));
        dd[0] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, sd[1])));
        dd[2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[2])));
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the X
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotateX(float angle, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            float _rd0 = sd[1];
            float _rd1 = sd[2];
            dd[0] = sd[0];
            dd[1] = java.lang.Math.fma(_rd0, _t1, -(_rd1 * _t0));
            dd[2] = java.lang.Math.fma(_rd0, _t0, _rd1 * _t1);
            dd[3] = sd[3];
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            float _rd0 = sd[1];
            float _rd1 = sd[2];
            dd[0] = sd[0];
            dd[1] = ((_rd0) * (_t1) - (_rd1 * _t0));
            dd[2] = ((_rd0) * (_t0) + (_rd1 * _t1));
            dd[3] = sd[3];
            return dest;
        }
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the X
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateX(float angle, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            dd[0] = sd[0];
            dd[1] = java.lang.Math.fma(sd[1], _t1, -(sd[2] * _t0));
            dd[2] = java.lang.Math.fma(sd[1], _t0, sd[2] * _t1);
            dd[3] = sd[3];
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            dd[0] = sd[0];
            dd[1] = ((sd[1]) * (_t1) - (sd[2] * _t0));
            dd[2] = ((sd[1]) * (_t0) + (sd[2] * _t1));
            dd[3] = sd[3];
            return dest;
        }
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Y
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotateY(float angle, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            float _rd0 = sd[0];
            float _rd1 = sd[2];
            dd[0] = java.lang.Math.fma(_rd0, _t1, _rd1 * _t0);
            dd[1] = sd[1];
            dd[2] = java.lang.Math.fma(_rd1, _t1, -(_rd0 * _t0));
            dd[3] = sd[3];
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            float _rd0 = sd[0];
            float _rd1 = sd[2];
            dd[0] = ((_rd0) * (_t1) + (_rd1 * _t0));
            dd[1] = sd[1];
            dd[2] = ((_rd1) * (_t1) - (_rd0 * _t0));
            dd[3] = sd[3];
            return dest;
        }
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Y
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateY(float angle, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            dd[0] = java.lang.Math.fma(sd[0], _t1, sd[2] * _t0);
            dd[1] = sd[1];
            dd[2] = java.lang.Math.fma(sd[2], _t1, -(sd[0] * _t0));
            dd[3] = sd[3];
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            dd[0] = ((sd[0]) * (_t1) + (sd[2] * _t0));
            dd[1] = sd[1];
            dd[2] = ((sd[2]) * (_t1) - (sd[0] * _t0));
            dd[3] = sd[3];
            return dest;
        }
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Z
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Float4 rotateZ(float angle, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            float _rd0 = sd[0];
            float _rd1 = sd[1];
            dd[0] = java.lang.Math.fma(_rd0, _t1, -(_rd1 * _t0));
            dd[1] = java.lang.Math.fma(_rd0, _t0, _rd1 * _t1);
            dd[2] = sd[2];
            dd[3] = sd[3];
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            float _rd0 = sd[0];
            float _rd1 = sd[1];
            dd[0] = ((_rd0) * (_t1) - (_rd1 * _t0));
            dd[1] = ((_rd0) * (_t0) + (_rd1 * _t1));
            dd[2] = sd[2];
            dd[3] = sd[3];
            return dest;
        }
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Z
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateZ(float angle, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            dd[0] = java.lang.Math.fma(sd[0], _t1, -(sd[1] * _t0));
            dd[1] = java.lang.Math.fma(sd[0], _t0, sd[1] * _t1);
            dd[2] = sd[2];
            dd[3] = sd[3];
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            dd[0] = ((sd[0]) * (_t1) - (sd[1] * _t0));
            dd[1] = ((sd[0]) * (_t0) + (sd[1] * _t1));
            dd[2] = sd[2];
            dd[3] = sd[3];
            return dest;
        }
    }

    public float x() { return data[0]; }
    public float y() { return data[1]; }
    public float z() { return data[2]; }
    public float w() { return data[3]; }

    public Float2 xx(@Mutated Float2 dest) {
        float[] dd = ((Float2Impl) dest).data;
        float _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Float2 xy(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Float2 xz(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Float2 xw(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Float2 yx(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Float2 yy(@Mutated Float2 dest) {
        float[] dd = ((Float2Impl) dest).data;
        float _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Float2 yz(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Float2 yw(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Float2 zx(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Float2 zy(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Float2 zz(@Mutated Float2 dest) {
        float[] dd = ((Float2Impl) dest).data;
        float _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Float2 zw(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Float2 wx(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        return dest;
    }

    public Float2 wy(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        return dest;
    }

    public Float2 wz(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        return dest;
    }

    public Float2 ww(@Mutated Float2 dest) {
        float[] dd = ((Float2Impl) dest).data;
        float _v0 = this.data[3];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Float3 xxx(@Mutated Float3 dest) {
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Float3 xxy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 xxz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 xxw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 xyx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 xyy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 xyz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 xyw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 xzx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 xzy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 xzz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 xzw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 xwx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 xwy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 xwz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 xww(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 yxx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 yxy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 yxz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 yxw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 yyx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 yyy(@Mutated Float3 dest) {
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Float3 yyz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 yyw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 yzx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 yzy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 yzz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 yzw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 ywx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 ywy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 ywz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 yww(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 zxx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 zxy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 zxz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 zxw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 zyx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 zyy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 zyz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 zyw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 zzx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 zzy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 zzz(@Mutated Float3 dest) {
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Float3 zzw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 zwx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 zwy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 zwz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 zww(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 wxx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 wxy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 wxz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 wxw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 wyx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 wyy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 wyz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 wyw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 wzx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 wzy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Float3 wzz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Float3 wzw(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Float3 wwx(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 wwy(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 wwz(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Float3 www(@Mutated Float3 dest) {
        float[] dd = ((Float3Impl) dest).data;
        float _v0 = this.data[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Float4 xxxx(@Mutated Float4 dest) {
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xxxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xxxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xxxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xxyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xxyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xxyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xxyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xxzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xxzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xxzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xxzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xxwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xxwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xxwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xxww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xyxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xyxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xyxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xyxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xyyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xyyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xyyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xyyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xyzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xyzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xyzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xyzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        float _v3 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 xywx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xywy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xywz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        float _v3 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 xyww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xzxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xzxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xzxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xzxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xzyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xzyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xzyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xzyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        float _v3 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 xzzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xzzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xzzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xzzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xzwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xzwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        float _v3 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 xzwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xzww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xwxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xwxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xwxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xwxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xwyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xwyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xwyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        float _v3 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 xwyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xwzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xwzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        float _v3 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 xwzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xwzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 xwwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[0];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 xwwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xwwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 xwww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yxxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yxxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yxxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yxxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yxyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yxyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yxyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yxyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yxzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yxzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yxzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yxzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        float _v3 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 yxwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yxwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yxwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        float _v3 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 yxww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yyxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yyxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yyxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yyxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yyyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yyyy(@Mutated Float4 dest) {
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yyyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yyyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yyzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yyzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yyzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yyzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yywx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yywy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yywz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yyww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yzxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yzxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yzxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yzxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        float _v3 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 yzyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yzyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yzyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yzyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yzzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yzzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yzzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yzzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 yzwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        float _v3 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 yzwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 yzwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 yzww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 ywxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 ywxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 ywxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        float _v3 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 ywxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 ywyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 ywyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 ywyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 ywyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 ywzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        float _v3 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 ywzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 ywzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 ywzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 ywwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 ywwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[1];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 ywwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 ywww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zxxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zxxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zxxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zxxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zxyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zxyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zxyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zxyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        float _v3 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 zxzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zxzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zxzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zxzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zxwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zxwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        float _v3 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 zxwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zxww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zyxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zyxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zyxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zyxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        float _v3 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 zyyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zyyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zyyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zyyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zyzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zyzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zyzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zyzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zywx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        float _v3 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 zywy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zywz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zyww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zzxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zzxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zzxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zzxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zzyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zzyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zzyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zzyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        float _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zzzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zzzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zzzz(@Mutated Float4 dest) {
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zzzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zzwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zzwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zzwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zzww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zwxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zwxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        float _v3 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 zwxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zwxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zwyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        float _v3 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 zwyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zwyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zwyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zwzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zwzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zwzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zwzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 zwwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zwwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        float _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 zwwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[2];
        float _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 zwww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wxxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wxxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wxxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wxxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wxyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wxyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wxyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[1];
        float _v3 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 wxyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wxzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wxzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        float _v3 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 wxzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wxzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wxwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wxwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wxwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wxww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wyxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wyxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wyxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        float _v3 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 wyxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wyyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wyyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wyyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wyyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wyzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        float _v3 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 wyzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wyzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wyzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wywx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wywy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wywz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wyww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wzxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wzxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        float _v3 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 wzxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wzxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wzyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        float _v3 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Float4 wzyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wzyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wzyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wzzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wzzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wzzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v1 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wzzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wzwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wzwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wzwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wzww(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wwxx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wwxy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wwxz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wwxw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wwyx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wwyy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wwyz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        float _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wwyw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wwzx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        float _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wwzy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        float _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Float4 wwzz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wwzw(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Float4 wwwx(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wwwy(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wwwz(@Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = sd[3];
        float _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Float4 wwww(@Mutated Float4 dest) {
        float[] dd = ((Float4Impl) dest).data;
        float _v0 = this.data[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Float4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float4Impl)) return false;
        Float4Impl o = (Float4Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(Float4R other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.x()) <= epsilon
            && java.lang.Math.abs(data[1] - other.y()) <= epsilon
            && java.lang.Math.abs(data[2] - other.z()) <= epsilon
            && java.lang.Math.abs(data[3] - other.w()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated Float4 load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public Float4 load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Float4 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public Float4 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Float4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Float4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public Float4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Float4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public Float4 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public Float4 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated Float4 load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public Float4 load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Float4 loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public Float4 loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public Float4 loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public Float4 loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4 r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public Float4 storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public Float4 loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public Float4 loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public Float4 loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-126, 2^126], so zero and subnormal
     * values scale up without overflow and the largest floats land in [2, 4).
     */
    private static float unitScale(float a, float b, float c) {
        int e = java.lang.Math.max(java.lang.Math.max(Float.floatToRawIntBits(a) & 0x7F800000,
                Float.floatToRawIntBits(b) & 0x7F800000), Float.floatToRawIntBits(c) & 0x7F800000);
        return Float.intBitsToFloat(0x7F000000 - java.lang.Math.min(java.lang.Math.max(e, 0x00800000), 0x7E800000));
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-1022, 2^1022], so zero and subnormal
     * values scale up without overflow and the largest doubles land in [2, 4).
     */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }

    /**
     * The floored remainder of x and y, exactly kotlin.Double.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static double flooredMod(double x, double y) {
        double q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p53 && java.lang.Math.abs(y) <= Double.MAX_VALUE) {
            double r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        double r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
