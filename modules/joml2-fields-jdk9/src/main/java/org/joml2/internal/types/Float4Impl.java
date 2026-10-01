// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Float4Impl implements Float4 {
    public float x;
    public float y;
    public float z;
    public float w;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float4BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float4BbOpsUnsafe()
            : new Float4BbOpsApi();
        static final Float4RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float4RawOpsUnsafe()
            : new Float4RawOpsApi();
    }

    public Float4Impl() {
        w = 1;
    }

    public Float4Impl(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Float4Impl(Float4R src) {
        Float4Impl srcImpl = (Float4Impl) src;
        this.x = srcImpl.x;
        this.y = srcImpl.y;
        this.z = srcImpl.z;
        this.w = srcImpl.w;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Double4Impl d = (Double4Impl) dest;
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
        Float4Impl d = (Float4Impl) dest;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x / scalar;
        d.y = this.y / scalar;
        d.z = this.z / scalar;
        d.w = this.w / scalar;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x / scalar;
        d.y = this.y / scalar;
        d.z = this.z / scalar;
        d.w = this.w / scalar;
        return d;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x / otherX;
        d.y = this.y / otherY;
        d.z = this.z / otherZ;
        d.w = this.w / otherW;
        return d;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x / otherX;
        d.y = this.y / otherY;
        d.z = this.z / otherZ;
        d.w = this.w / otherW;
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x / otherX;
        d.y = this.y / otherY;
        d.z = this.z / otherZ;
        d.w = this.w / otherW;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x / otherX;
        d.y = this.y / otherY;
        d.z = this.z / otherZ;
        d.w = this.w / otherW;
        return d;
    }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Float4 fma(float b, Float4R c, @Mutated Float4 dest) {
        Float4Impl cImpl = (Float4Impl) c;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        float cW = cImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            d.z = java.lang.Math.fma(this.z, b, cZ);
            d.w = java.lang.Math.fma(this.w, b, cW);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
            d.z = ((this.z) * (b) + (cZ));
            d.w = ((this.w) * (b) + (cW));
            return d;
        }
    }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component, and store the result in {@code dest}.
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
        Float4Impl cImpl = (Float4Impl) c;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        float cW = cImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            d.z = java.lang.Math.fma(this.z, b, cZ);
            d.w = java.lang.Math.fma(this.w, b, cW);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
            d.z = ((this.z) * (b) + (cZ));
            d.w = ((this.w) * (b) + (cW));
            return d;
        }
    }

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}, {@code cZ},
     * {@code cW}), i.e. compute {@code this * b + (cX, cY, cZ, cW)} per component, and store the
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            d.z = java.lang.Math.fma(this.z, b, cZ);
            d.w = java.lang.Math.fma(this.w, b, cW);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
            d.z = ((this.z) * (b) + (cZ));
            d.w = ((this.w) * (b) + (cW));
            return d;
        }
    }

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}, {@code cZ},
     * {@code cW}), i.e. compute {@code this * b + (cX, cY, cZ, cW)} per component, and store the
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            d.z = java.lang.Math.fma(this.z, b, cZ);
            d.w = java.lang.Math.fma(this.w, b, cW);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
            d.z = ((this.z) * (b) + (cZ));
            d.w = ((this.w) * (b) + (cW));
            return d;
        }
    }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Float4 fma(Float4R b, Float4R c, @Mutated Float4 dest) {
        Float4Impl bImpl = (Float4Impl) b;
        Float4Impl cImpl = (Float4Impl) c;
        float bX = bImpl.x;
        float bY = bImpl.y;
        float bZ = bImpl.z;
        float bW = bImpl.w;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        float cW = cImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            d.z = java.lang.Math.fma(this.z, bZ, cZ);
            d.w = java.lang.Math.fma(this.w, bW, cW);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
            d.z = ((this.z) * (bZ) + (cZ));
            d.w = ((this.w) * (bW) + (cW));
            return d;
        }
    }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component, and store the result in {@code dest}.
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
        Float4Impl bImpl = (Float4Impl) b;
        Float4Impl cImpl = (Float4Impl) c;
        float bX = bImpl.x;
        float bY = bImpl.y;
        float bZ = bImpl.z;
        float bW = bImpl.w;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        float cW = cImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            d.z = java.lang.Math.fma(this.z, bZ, cZ);
            d.w = java.lang.Math.fma(this.w, bW, cW);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
            d.z = ((this.z) * (bZ) + (cZ));
            d.w = ((this.w) * (bW) + (cW));
            return d;
        }
    }

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component, and store the result in
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            d.z = java.lang.Math.fma(this.z, bZ, cZ);
            d.w = java.lang.Math.fma(this.w, bW, cW);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
            d.z = ((this.z) * (bZ) + (cZ));
            d.w = ((this.w) * (bW) + (cW));
            return d;
        }
    }

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component, and store the result in
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            d.z = java.lang.Math.fma(this.z, bZ, cZ);
            d.w = java.lang.Math.fma(this.w, bW, cW);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
            d.z = ((this.z) * (bZ) + (cZ));
            d.w = ((this.w) * (bW) + (cW));
            return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        d.w = scalar * this.w;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        d.w = scalar * this.w;
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
    public Float4 mul(Float4R other, @Mutated Float4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        d.w = otherW * this.w;
        return d;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Double4Impl d = (Double4Impl) dest;
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
        Float4Impl d = (Float4Impl) dest;
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
    public Float4 negate(@Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = -this.w;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = -this.w;
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
    public Float4 sub(Float4R other, @Mutated Float4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Double4Impl d = (Double4Impl) dest;
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
        Float4Impl d = (Float4Impl) dest;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
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
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u);
        this.x = Math.cosWithSin(_t1) * _t4;
        this.y = Math.sinWithCos(_t1) * _t4;
        this.z = Math.cosWithSin(_t3) * _t0;
        this.w = Math.sinWithCos(_t3) * _t0;
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
    @Mutated public Float4 set(Float4R v) {
        Float4Impl vImpl = (Float4Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        float vZ = vImpl.z;
        float vW = vImpl.w;
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.w = vW;
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
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.w = vW;
        return this;
    }

    /**
     * Compute a vector with every component {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Float4 set(float s, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        d.w = s;
        return d;
    }

    /**
     * Compute a vector with every component {@code s} and store the result in {@code dest}.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        d.w = s;
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
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code int} range, then wrapped to the low 8 bits (so values outside the
     * {@code byte} range wrap), NaN converted to 0.
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
        switch (roundingMode) {
            case TRUNCATE: return toByte(dest);
            case FLOOR: {
                Byte4Impl d = (Byte4Impl) dest;
                d.x = (byte) Math.floor(this.x);
                d.y = (byte) Math.floor(this.y);
                d.z = (byte) Math.floor(this.z);
                d.w = (byte) Math.floor(this.w);
                return dest;
            }
            case CEILING: {
                Byte4Impl d = (Byte4Impl) dest;
                d.x = (byte) Math.ceil(this.x);
                d.y = (byte) Math.ceil(this.y);
                d.z = (byte) Math.ceil(this.z);
                d.w = (byte) Math.ceil(this.w);
                return dest;
            }
            case HALF_TOWARD_POSITIVE_INFINITY: {
                Byte4Impl d = (Byte4Impl) dest;
                d.x = (byte) Math.round(this.x);
                d.y = (byte) Math.round(this.y);
                d.z = (byte) Math.round(this.z);
                d.w = (byte) Math.round(this.w);
                return dest;
            }
            case HALF_AWAY_FROM_ZERO: return toByte_halfAwayFromZero(dest);
        }
        {
            Byte4Impl d = (Byte4Impl) dest;
            d.x = (byte) Math.rint(this.x);
            d.y = (byte) Math.rint(this.y);
            d.z = (byte) Math.rint(this.z);
            d.w = (byte) Math.rint(this.w);
            return dest;
        }
    }

    /** {@code toByte(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Byte4 toByte_halfAwayFromZero(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5));
        d.y = (byte) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5));
        d.z = (byte) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5));
        d.w = (byte) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5));
        return dest;
    }

    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code int} range, then wrapped to the low 16 bits (so values outside the
     * {@code short} range wrap), NaN converted to 0.
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
        switch (roundingMode) {
            case TRUNCATE: return toShort(dest);
            case FLOOR: {
                Short4Impl d = (Short4Impl) dest;
                d.x = (short) Math.floor(this.x);
                d.y = (short) Math.floor(this.y);
                d.z = (short) Math.floor(this.z);
                d.w = (short) Math.floor(this.w);
                return dest;
            }
            case CEILING: {
                Short4Impl d = (Short4Impl) dest;
                d.x = (short) Math.ceil(this.x);
                d.y = (short) Math.ceil(this.y);
                d.z = (short) Math.ceil(this.z);
                d.w = (short) Math.ceil(this.w);
                return dest;
            }
            case HALF_TOWARD_POSITIVE_INFINITY: {
                Short4Impl d = (Short4Impl) dest;
                d.x = (short) Math.round(this.x);
                d.y = (short) Math.round(this.y);
                d.z = (short) Math.round(this.z);
                d.w = (short) Math.round(this.w);
                return dest;
            }
            case HALF_AWAY_FROM_ZERO: return toShort_halfAwayFromZero(dest);
        }
        {
            Short4Impl d = (Short4Impl) dest;
            d.x = (short) Math.rint(this.x);
            d.y = (short) Math.rint(this.y);
            d.z = (short) Math.rint(this.z);
            d.w = (short) Math.rint(this.w);
            return dest;
        }
    }

    /** {@code toShort(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Short4 toShort_halfAwayFromZero(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5));
        d.y = (short) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5));
        d.z = (short) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5));
        d.w = (short) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5));
        return dest;
    }

    /**
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code int} range, NaN converted to 0.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int4 toInt(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = (int) (this.x);
        d.y = (int) (this.y);
        d.z = (int) (this.z);
        d.w = (int) (this.w);
        return d;
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
        switch (roundingMode) {
            case TRUNCATE: return toInt(dest);
            case FLOOR: {
                Int4Impl d = (Int4Impl) dest;
                d.x = (int) Math.floor(this.x);
                d.y = (int) Math.floor(this.y);
                d.z = (int) Math.floor(this.z);
                d.w = (int) Math.floor(this.w);
                return dest;
            }
            case CEILING: {
                Int4Impl d = (Int4Impl) dest;
                d.x = (int) Math.ceil(this.x);
                d.y = (int) Math.ceil(this.y);
                d.z = (int) Math.ceil(this.z);
                d.w = (int) Math.ceil(this.w);
                return dest;
            }
            case HALF_TOWARD_POSITIVE_INFINITY: {
                Int4Impl d = (Int4Impl) dest;
                d.x = Math.round(this.x);
                d.y = Math.round(this.y);
                d.z = Math.round(this.z);
                d.w = Math.round(this.w);
                return dest;
            }
            case HALF_AWAY_FROM_ZERO: return toInt_halfAwayFromZero(dest);
        }
        {
            Int4Impl d = (Int4Impl) dest;
            d.x = (int) Math.rint(this.x);
            d.y = (int) Math.rint(this.y);
            d.z = (int) Math.rint(this.z);
            d.w = (int) Math.rint(this.w);
            return dest;
        }
    }

    /** {@code toInt(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Int4 toInt_halfAwayFromZero(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = (int) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5));
        d.y = (int) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5));
        d.z = (int) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5));
        d.w = (int) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5));
        return dest;
    }

    /**
     * Convert this vector to {@code long} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code long} range, NaN converted to 0.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 toLong(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = (long) (this.x);
        d.y = (long) (this.y);
        d.z = (long) (this.z);
        d.w = (long) (this.w);
        return d;
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
        switch (roundingMode) {
            case TRUNCATE: return toLong(dest);
            case FLOOR: {
                Long4Impl d = (Long4Impl) dest;
                d.x = (long) Math.floor(this.x);
                d.y = (long) Math.floor(this.y);
                d.z = (long) Math.floor(this.z);
                d.w = (long) Math.floor(this.w);
                return dest;
            }
            case CEILING: {
                Long4Impl d = (Long4Impl) dest;
                d.x = (long) Math.ceil(this.x);
                d.y = (long) Math.ceil(this.y);
                d.z = (long) Math.ceil(this.z);
                d.w = (long) Math.ceil(this.w);
                return dest;
            }
            case HALF_TOWARD_POSITIVE_INFINITY: {
                Long4Impl d = (Long4Impl) dest;
                d.x = Math.round((double) (this.x));
                d.y = Math.round((double) (this.y));
                d.z = Math.round((double) (this.z));
                d.w = Math.round((double) (this.w));
                return dest;
            }
            case HALF_AWAY_FROM_ZERO: return toLong_halfAwayFromZero(dest);
        }
        {
            Long4Impl d = (Long4Impl) dest;
            d.x = (long) Math.rint(this.x);
            d.y = (long) Math.rint(this.y);
            d.z = (long) Math.rint(this.z);
            d.w = (long) Math.rint(this.w);
            return dest;
        }
    }

    /** {@code toLong(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Long4 toLong_halfAwayFromZero(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = (long) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5));
        d.y = (long) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5));
        d.z = (long) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5));
        d.w = (long) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5));
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
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
        return this;
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3}, and store the result in
     * {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        return bezier(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, p3Impl.x, p3Impl.y, p3Impl.z, p3Impl.w, t, dest);
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3}, and store the result in
     * {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        return bezier(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, p3Impl.x, p3Impl.y, p3Impl.z, p3Impl.w, t, dest);
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}),
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
        if (!org.joml2.internal.FmaSwitch.ON) return bezier_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        d.x = java.lang.Math.fma(p1X, _t7, this.x * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2);
        d.y = java.lang.Math.fma(p1Y, _t7, this.y * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2);
        d.z = java.lang.Math.fma(p1Z, _t7, this.z * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2);
        d.w = java.lang.Math.fma(p1W, _t7, this.w * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2);
        return d;
    }

    /** {@code bezier} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 bezier_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        d.x = ((p1X) * (_t7) + (this.x * _t8)) + ((p2X) * (_t6) + (p3X * _t2));
        d.y = ((p1Y) * (_t7) + (this.y * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2));
        d.z = ((p1Z) * (_t7) + (this.z * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2));
        d.w = ((p1W) * (_t7) + (this.w * _t8)) + ((p2W) * (_t6) + (p3W * _t2));
        return d;
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}),
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
        if (!org.joml2.internal.FmaSwitch.ON) return bezier_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        d.x = java.lang.Math.fma(p1X, _t7, this.x * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2);
        d.y = java.lang.Math.fma(p1Y, _t7, this.y * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2);
        d.z = java.lang.Math.fma(p1Z, _t7, this.z * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2);
        d.w = java.lang.Math.fma(p1W, _t7, this.w * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2);
        return d;
    }

    /** {@code bezier} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 bezier_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        d.x = ((p1X) * (_t7) + (this.x * _t8)) + ((p2X) * (_t6) + (p3X * _t2));
        d.y = ((p1Y) * (_t7) + (this.y * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2));
        d.z = ((p1Z) * (_t7) + (this.z * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2));
        d.w = ((p1W) * (_t7) + (this.w * _t8)) + ((p2W) * (_t6) + (p3W * _t2));
        return d;
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2}, and store the result in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        return bezier2(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, t, dest);
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2}, and store the result in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        return bezier2(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, t, dest);
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            d.x = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4));
            d.y = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4));
            d.z = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, this.z * _t4));
            d.w = java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, this.w * _t4));
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            d.x = ((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4))));
            d.y = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4))));
            d.z = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (this.z * _t4))));
            d.w = ((p2W) * (_t0) + (((p1W) * (_t3) + (this.w * _t4))));
            return d;
        }
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            d.x = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4));
            d.y = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4));
            d.z = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, this.z * _t4));
            d.w = java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, this.w * _t4));
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            d.x = ((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4))));
            d.y = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4))));
            d.z = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (this.z * _t4))));
            d.w = ((p2W) * (_t0) + (((p1W) * (_t3) + (this.w * _t4))));
            return d;
        }
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2}, and store the result in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        return bezier2Tangent(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, t, dest);
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2}, and store the result in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        return bezier2Tangent(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, t, dest);
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            d.x = java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1);
            d.y = java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1);
            d.z = java.lang.Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1);
            d.w = java.lang.Math.fma(p1W - this.w, _t2, (p2W - p1W) * _t1);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            d.x = ((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1));
            d.y = ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1));
            d.z = ((p1Z - this.z) * (_t2) + ((p2Z - p1Z) * _t1));
            d.w = ((p1W - this.w) * (_t2) + ((p2W - p1W) * _t1));
            return d;
        }
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            d.x = java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1);
            d.y = java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1);
            d.z = java.lang.Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1);
            d.w = java.lang.Math.fma(p1W - this.w, _t2, (p2W - p1W) * _t1);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            d.x = ((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1));
            d.y = ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1));
            d.z = ((p1Z - this.z) * (_t2) + ((p2Z - p1Z) * _t1));
            d.w = ((p1W - this.w) * (_t2) + ((p2W - p1W) * _t1));
            return d;
        }
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3}, and store the result in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        return bezierTangent(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, p3Impl.x, p3Impl.y, p3Impl.z, p3Impl.w, t, dest);
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3}, and store the result in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        return bezierTangent(p1Impl.x, p1Impl.y, p1Impl.z, p1Impl.w, p2Impl.x, p2Impl.y, p2Impl.z, p2Impl.w, p3Impl.x, p3Impl.y, p3Impl.z, p3Impl.w, t, dest);
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z},
     * {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}), and store the
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
        if (!org.joml2.internal.FmaSwitch.ON) return bezierTangent_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        d.x = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5));
        d.y = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5));
        d.z = java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5));
        d.w = java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - this.w, _t6, (p2W - p1W) * _t5));
        return d;
    }

    /** {@code bezierTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 bezierTangent_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        d.x = ((p3X - p2X) * (_t2) + (((p1X - this.x) * (_t6) + ((p2X - p1X) * _t5))));
        d.y = ((p3Y - p2Y) * (_t2) + (((p1Y - this.y) * (_t6) + ((p2Y - p1Y) * _t5))));
        d.z = ((p3Z - p2Z) * (_t2) + (((p1Z - this.z) * (_t6) + ((p2Z - p1Z) * _t5))));
        d.w = ((p3W - p2W) * (_t2) + (((p1W - this.w) * (_t6) + ((p2W - p1W) * _t5))));
        return d;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z},
     * {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}), and store the
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
        if (!org.joml2.internal.FmaSwitch.ON) return bezierTangent_mulAdd(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        d.x = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5));
        d.y = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5));
        d.z = java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5));
        d.w = java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - this.w, _t6, (p2W - p1W) * _t5));
        return d;
    }

    /** {@code bezierTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 bezierTangent_mulAdd(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        d.x = ((p3X - p2X) * (_t2) + (((p1X - this.x) * (_t6) + ((p2X - p1X) * _t5))));
        d.y = ((p3Y - p2Y) * (_t2) + (((p1Y - this.y) * (_t6) + ((p2Y - p1Y) * _t5))));
        d.z = ((p3Z - p2Z) * (_t2) + (((p1Z - this.z) * (_t6) + ((p2Z - p1Z) * _t5))));
        d.w = ((p3W - p2W) * (_t2) + (((p1W - this.w) * (_t6) + ((p2W - p1W) * _t5))));
        return d;
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from {@code p1} to {@code p2}, with this
     * vector as the control point before the segment and {@code p3} as the control point after it,
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        float p1X = p1Impl.x;
        float p1Y = p1Impl.y;
        float p1Z = p1Impl.z;
        float p1W = p1Impl.w;
        float p2X = p2Impl.x;
        float p2Y = p2Impl.y;
        float p2Z = p2Impl.z;
        float p2W = p2Impl.w;
        float p3X = p3Impl.x;
        float p3Y = p3Impl.y;
        float p3Z = p3Impl.z;
        float p3W = p3Impl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s56104e12_c0_fma(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s56104e12_c0_mulAdd(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        }
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from {@code p1} to {@code p2}, with this
     * vector as the control point before the segment and {@code p3} as the control point after it,
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        float p1X = p1Impl.x;
        float p1Y = p1Impl.y;
        float p1Z = p1Impl.z;
        float p1W = p1Impl.w;
        float p2X = p2Impl.x;
        float p2Y = p2Impl.y;
        float p2Z = p2Impl.z;
        float p2W = p2Impl.w;
        float p3X = p3Impl.x;
        float p3Y = p3Impl.y;
        float p3Z = p3Impl.z;
        float p3W = p3Impl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s68ab4d77_c0_fma(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s68ab4d77_c0_mulAdd(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        }
    }

    /** Private store group 0 of {@code catmullRom}: computes and stores it; reached only through it. */
    private void catmullRom_s56104e12_c0_fma(Float4Impl _dst, float p1X, float t, float p2X, float _r0, float p3X, float _t0, float _t1, float p1Y, float p2Y, float _r1, float p3Y, float p1Z, float p2Z, float _r2, float p3Z, float p1W, float p2W, float _r3, float p3W) {
        _dst.x = 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _r0)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _r0, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _r0)) * _t1));
        _dst.y = 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _r1)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _r1, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _r1)) * _t1));
        _dst.z = 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _r2)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _r2, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _r2)) * _t1));
        _dst.w = 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - _r3)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _r3, java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _r3)) * _t1));
    }

    /** Private store group 0 of {@code catmullRom}: computes and stores it; reached only through it. */
    private void catmullRom_s56104e12_c0_mulAdd(Float4Impl _dst, float p1X, float t, float p2X, float _r0, float p3X, float _t0, float _t1, float p1Y, float p2Y, float _r1, float p3Y, float p1Z, float p2Z, float _r2, float p3Z, float p1W, float p2W, float _r3, float p3W) {
        _dst.x = 0.5f * (((2.0f) * (p1X) + (t * (p2X - _r0))) + ((((((2.0f) * (_r0) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _r0))) - (3.0f) * (p2X)) * _t1)));
        _dst.y = 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _r1))) + ((((((2.0f) * (_r1) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _r1))) - (3.0f) * (p2Y)) * _t1)));
        _dst.z = 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _r2))) + ((((((2.0f) * (_r2) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _r2))) - (3.0f) * (p2Z)) * _t1)));
        _dst.w = 0.5f * (((2.0f) * (p1W) + (t * (p2W - _r3))) + ((((((2.0f) * (_r3) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) * (_t0) + (((((3.0f) * (p1W) + (p3W - _r3))) - (3.0f) * (p2W)) * _t1)));
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) to ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the
     * control point before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as
     * the control point after it, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s56104e12_c0_fma(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s56104e12_c0_mulAdd(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        }
    }

    /** Private store group 0 of {@code catmullRom}: computes and stores it; reached only through it. */
    private void catmullRom_s68ab4d77_c0_fma(Double4Impl _dst, float p1X, float t, float p2X, float _r0, float p3X, float _t0, float _t1, float p1Y, float p2Y, float _r1, float p3Y, float p1Z, float p2Z, float _r2, float p3Z, float p1W, float p2W, float _r3, float p3W) {
        _dst.x = 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _r0)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _r0, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _r0)) * _t1));
        _dst.y = 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _r1)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _r1, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _r1)) * _t1));
        _dst.z = 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _r2)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _r2, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _r2)) * _t1));
        _dst.w = 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - _r3)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _r3, java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _r3)) * _t1));
    }

    /** Private store group 0 of {@code catmullRom}: computes and stores it; reached only through it. */
    private void catmullRom_s68ab4d77_c0_mulAdd(Double4Impl _dst, float p1X, float t, float p2X, float _r0, float p3X, float _t0, float _t1, float p1Y, float p2Y, float _r1, float p3Y, float p1Z, float p2Z, float _r2, float p3Z, float p1W, float p2W, float _r3, float p3W) {
        _dst.x = 0.5f * (((2.0f) * (p1X) + (t * (p2X - _r0))) + ((((((2.0f) * (_r0) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _r0))) - (3.0f) * (p2X)) * _t1)));
        _dst.y = 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _r1))) + ((((((2.0f) * (_r1) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _r1))) - (3.0f) * (p2Y)) * _t1)));
        _dst.z = 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _r2))) + ((((((2.0f) * (_r2) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _r2))) - (3.0f) * (p2Z)) * _t1)));
        _dst.w = 0.5f * (((2.0f) * (p1W) + (t * (p2W - _r3))) + ((((((2.0f) * (_r3) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) * (_t0) + (((((3.0f) * (p1W) + (p3W - _r3))) - (3.0f) * (p2W)) * _t1)));
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) to ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the
     * control point before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as
     * the control point after it, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s68ab4d77_c0_fma(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            float _t0 = t * t;
            catmullRom_s68ab4d77_c0_mulAdd(d, p1X, t, p2X, _r0, p3X, _t0, t * _t0, p1Y, p2Y, _r1, p3Y, p1Z, p2Z, _r2, p3Z, p1W, p2W, _r3, p3W);
            return d;
        }
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from {@code p1} to {@code p2}, with this vector as the control
     * point before the segment and {@code p3} as the control point after it, and store the result
     * in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        float p1X = p1Impl.x;
        float p1Y = p1Impl.y;
        float p1Z = p1Impl.z;
        float p1W = p1Impl.w;
        float p2X = p2Impl.x;
        float p2Y = p2Impl.y;
        float p2Z = p2Impl.z;
        float p2W = p2Impl.w;
        float p3X = p3Impl.x;
        float p3Y = p3Impl.y;
        float p3Z = p3Impl.z;
        float p3W = p3Impl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s56104e12_c0_fma(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s56104e12_c0_mulAdd(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        }
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from {@code p1} to {@code p2}, with this vector as the control
     * point before the segment and {@code p3} as the control point after it, and store the result
     * in {@code dest}.
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
        Float4Impl p1Impl = (Float4Impl) p1;
        Float4Impl p2Impl = (Float4Impl) p2;
        Float4Impl p3Impl = (Float4Impl) p3;
        float p1X = p1Impl.x;
        float p1Y = p1Impl.y;
        float p1Z = p1Impl.z;
        float p1W = p1Impl.w;
        float p2X = p2Impl.x;
        float p2Y = p2Impl.y;
        float p2Z = p2Impl.z;
        float p2W = p2Impl.w;
        float p3X = p3Impl.x;
        float p3Y = p3Impl.y;
        float p3Z = p3Impl.z;
        float p3W = p3Impl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s68ab4d77_c0_fma(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s68ab4d77_c0_mulAdd(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        }
    }

    /** Private store group 0 of {@code catmullRomTangent}: computes and stores it; reached only through it. */
    private void catmullRomTangent_s56104e12_c0_fma(Float4Impl _dst, float t, float p1X, float _r0, float p2X, float p3X, float _t0, float p1Y, float _r1, float p2Y, float p3Y, float p1Z, float _r2, float p2Z, float p3Z, float p1W, float _r3, float p2W, float p3W) {
        _dst.x = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _r0, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _r0)), _t0, p2X - _r0));
        _dst.y = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _r1, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _r1)), _t0, p2Y - _r1));
        _dst.z = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _r2, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _r2)), _t0, p2Z - _r2));
        _dst.w = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _r3, java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _r3)), _t0, p2W - _r3));
    }

    /** Private store group 0 of {@code catmullRomTangent}: computes and stores it; reached only through it. */
    private void catmullRomTangent_s56104e12_c0_mulAdd(Float4Impl _dst, float t, float p1X, float _r0, float p2X, float p3X, float _t0, float p1Y, float _r1, float p2Y, float p3Y, float p1Z, float _r2, float p2Z, float p3Z, float p1W, float _r3, float p2W, float p3W) {
        _dst.x = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r0) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _r0))) - (3.0f) * (p2X))) * (_t0) + (p2X - _r0))));
        _dst.y = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r1) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _r1))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _r1))));
        _dst.z = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r2) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _r2))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _r2))));
        _dst.w = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r3) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) + (((3.0f * ((((3.0f) * (p1W) + (p3W - _r3))) - (3.0f) * (p2W))) * (_t0) + (p2W - _r3))));
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) to
     * ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the control point
     * before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as the control
     * point after it, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s56104e12_c0_fma(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s56104e12_c0_mulAdd(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        }
    }

    /** Private store group 0 of {@code catmullRomTangent}: computes and stores it; reached only through it. */
    private void catmullRomTangent_s68ab4d77_c0_fma(Double4Impl _dst, float t, float p1X, float _r0, float p2X, float p3X, float _t0, float p1Y, float _r1, float p2Y, float p3Y, float p1Z, float _r2, float p2Z, float p3Z, float p1W, float _r3, float p2W, float p3W) {
        _dst.x = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _r0, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _r0)), _t0, p2X - _r0));
        _dst.y = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _r1, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _r1)), _t0, p2Y - _r1));
        _dst.z = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _r2, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _r2)), _t0, p2Z - _r2));
        _dst.w = 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _r3, java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _r3)), _t0, p2W - _r3));
    }

    /** Private store group 0 of {@code catmullRomTangent}: computes and stores it; reached only through it. */
    private void catmullRomTangent_s68ab4d77_c0_mulAdd(Double4Impl _dst, float t, float p1X, float _r0, float p2X, float p3X, float _t0, float p1Y, float _r1, float p2Y, float p3Y, float p1Z, float _r2, float p2Z, float p3Z, float p1W, float _r3, float p2W, float p3W) {
        _dst.x = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r0) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _r0))) - (3.0f) * (p2X))) * (_t0) + (p2X - _r0))));
        _dst.y = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r1) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _r1))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _r1))));
        _dst.z = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r2) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _r2))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _r2))));
        _dst.w = 0.5f * ((t) * (2.0f * ((((2.0f) * (_r3) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) + (((3.0f * ((((3.0f) * (p1W) + (p3W - _r3))) - (3.0f) * (p2W))) * (_t0) + (p2W - _r3))));
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) to
     * ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the control point
     * before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as the control
     * point after it, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s68ab4d77_c0_fma(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _r0 = this.x;
            float _r1 = this.y;
            float _r2 = this.z;
            float _r3 = this.w;
            catmullRomTangent_s68ab4d77_c0_mulAdd(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
            return d;
        }
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
        Float4Impl t0Impl = (Float4Impl) t0;
        Float4Impl v1Impl = (Float4Impl) v1;
        Float4Impl t1Impl = (Float4Impl) t1;
        return hermite(t0Impl.x, t0Impl.y, t0Impl.z, t0Impl.w, v1Impl.x, v1Impl.y, v1Impl.z, v1Impl.w, t1Impl.x, t1Impl.y, t1Impl.z, t1Impl.w, t, dest);
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
        Float4Impl t0Impl = (Float4Impl) t0;
        Float4Impl v1Impl = (Float4Impl) v1;
        Float4Impl t1Impl = (Float4Impl) t1;
        return hermite(t0Impl.x, t0Impl.y, t0Impl.z, t0Impl.w, v1Impl.x, v1Impl.y, v1Impl.z, v1Impl.w, t1Impl.x, t1Impl.y, t1Impl.z, t1Impl.w, t, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return hermite_mulAdd(t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        d.x = java.lang.Math.fma(this.x, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9);
        d.y = java.lang.Math.fma(this.y, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9);
        d.z = java.lang.Math.fma(this.z, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9);
        d.w = java.lang.Math.fma(this.w, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9);
        return d;
    }

    /** {@code hermite} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 hermite_mulAdd(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        d.x = ((this.x) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9));
        d.y = ((this.y) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9));
        d.z = ((this.z) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9));
        d.w = ((this.w) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9));
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return hermite_mulAdd(t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        d.x = java.lang.Math.fma(this.x, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9);
        d.y = java.lang.Math.fma(this.y, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9);
        d.z = java.lang.Math.fma(this.z, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9);
        d.w = java.lang.Math.fma(this.w, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9);
        return d;
    }

    /** {@code hermite} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 hermite_mulAdd(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        d.x = ((this.x) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9));
        d.y = ((this.y) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9));
        d.z = ((this.z) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9));
        d.w = ((this.w) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9));
        return d;
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
        Float4Impl t0Impl = (Float4Impl) t0;
        Float4Impl v1Impl = (Float4Impl) v1;
        Float4Impl t1Impl = (Float4Impl) t1;
        return hermiteTangent(t0Impl.x, t0Impl.y, t0Impl.z, t0Impl.w, v1Impl.x, v1Impl.y, v1Impl.z, v1Impl.w, t1Impl.x, t1Impl.y, t1Impl.z, t1Impl.w, t, dest);
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
        Float4Impl t0Impl = (Float4Impl) t0;
        Float4Impl v1Impl = (Float4Impl) v1;
        Float4Impl t1Impl = (Float4Impl) t1;
        return hermiteTangent(t0Impl.x, t0Impl.y, t0Impl.z, t0Impl.w, v1Impl.x, v1Impl.y, v1Impl.z, v1Impl.w, t1Impl.x, t1Impl.y, t1Impl.z, t1Impl.w, t, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return hermiteTangent_mulAdd(t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        d.x = java.lang.Math.fma(this.x, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7);
        d.y = java.lang.Math.fma(this.y, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7);
        d.z = java.lang.Math.fma(this.z, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7);
        d.w = java.lang.Math.fma(this.w, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7);
        return d;
    }

    /** {@code hermiteTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 hermiteTangent_mulAdd(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        d.x = ((this.x) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7));
        d.y = ((this.y) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7));
        d.z = ((this.z) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7));
        d.w = ((this.w) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7));
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return hermiteTangent_mulAdd(t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        d.x = java.lang.Math.fma(this.x, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7);
        d.y = java.lang.Math.fma(this.y, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7);
        d.z = java.lang.Math.fma(this.z, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7);
        d.w = java.lang.Math.fma(this.w, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7);
        return d;
    }

    /** {@code hermiteTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 hermiteTangent_mulAdd(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        d.x = ((this.x) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7));
        d.y = ((this.y) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7));
        d.z = ((this.z) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7));
        d.w = ((this.w) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7));
        return d;
    }

    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 lerp(Float4R other, float t, @Mutated Float4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            d.w = ((t) * (otherW - this.w) + (this.w));
            return d;
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            d.w = ((t) * (otherW - this.w) + (this.w));
            return d;
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            d.w = ((t) * (otherW - this.w) + (this.w));
            return d;
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            d.w = ((t) * (otherW - this.w) + (this.w));
            return d;
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 lerp(Float4R other, Float4R t, @Mutated Float4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        Float4Impl tImpl = (Float4Impl) t;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        float tX = tImpl.x;
        float tY = tImpl.y;
        float tZ = tImpl.z;
        float tW = tImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(tZ, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(tW, otherW - this.w, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
            d.z = ((tZ) * (otherZ - this.z) + (this.z));
            d.w = ((tW) * (otherW - this.w) + (this.w));
            return d;
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
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
        Float4Impl otherImpl = (Float4Impl) other;
        Float4Impl tImpl = (Float4Impl) t;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        float tX = tImpl.x;
        float tY = tImpl.y;
        float tZ = tImpl.z;
        float tW = tImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(tZ, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(tW, otherW - this.w, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
            d.z = ((tZ) * (otherZ - this.z) + (this.z));
            d.w = ((tW) * (otherW - this.w) + (this.w));
            return d;
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(tZ, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(tW, otherW - this.w, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
            d.z = ((tZ) * (otherZ - this.z) + (this.z));
            d.w = ((tW) * (otherW - this.w) + (this.w));
            return d;
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(tZ, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(tW, otherW - this.w, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
            d.z = ((tZ) * (otherZ - this.z) + (this.z));
            d.w = ((tW) * (otherW - this.w) + (this.w));
            return d;
        }
    }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths, and store the result in
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths, and store the result in
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }

    /**
     * Private store group 0 of {@code slerp}: computes and stores it. Shared by 2 identical private
     * paths of {@code slerp}; reached only through it.
     */
    private void slerp_s78d3828a_c0_fma(Float4Impl _dst, float _t16, float _t64, float _sp0, float _t51, float _t24, float _t52, float _t21, float _t50, float _t19, float _t49) {
        _dst.x = java.lang.Math.fma(_t16, _t64, _sp0 * _t51);
        _dst.y = java.lang.Math.fma(_t24, _t64, _sp0 * _t52);
        _dst.z = java.lang.Math.fma(_t21, _t64, _sp0 * _t50);
        _dst.w = java.lang.Math.fma(_t19, _t64, _sp0 * _t49);
    }

    /**
     * Private tail of {@code slerp}. Shared by 2 identical private paths of {@code slerp}; reached
     * only through it.
     */
    private void slerp_s78d3828a_tail_fma(Float4Impl _dst, float t, float _t10, float _t9, float _t57, float _t31, float _t16, float _t51, float _t24, float _t52, float _t21, float _t50, float _t19, float _t49) {
        float _t28 = t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        slerp_s78d3828a_c0_fma(_dst, _t16, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)), _t51, _t24, _t52, _t21, _t50, _t19, _t49);
    }

    /**
     * Private tail of {@code slerp}. Shared by 2 identical private paths of {@code slerp}; reached
     * only through it.
     */
    private void slerp_s78d3828a_tail_mulAdd(Float4Impl _dst, float t, float _t10, float _t9, float _t57, float _t31, float _t16, float _t51, float _t24, float _t52, float _t21, float _t50, float _t19, float _t49) {
        float _t28 = t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        {
            float _t64 = _t28 * Math.cosWithSin(_t61);
            float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
            _dst.x = ((_t16) * (_t64) + (_sp0 * _t51));
            _dst.y = ((_t24) * (_t64) + (_sp0 * _t52));
            _dst.z = ((_t21) * (_t64) + (_sp0 * _t50));
            _dst.w = ((_t19) * (_t64) + (_sp0 * _t49));
        }
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 slerp_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = java.lang.Math.fma(_r0, _r0, java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r2, _r2, _r3 * _r3)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_s5cedc029_1_fma(otherX, otherY, otherZ, otherW, t, dest, (Float4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, java.lang.Math.fma(otherW, _t14, -(_t31 * _t19)), java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21)), java.lang.Math.fma(otherX, _t14, -(_t31 * _t16)), java.lang.Math.fma(otherY, _t14, -(_t31 * _t24)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 slerp_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = ((_r0) * (_r0) + (((_r1) * (_r1) + (((_r2) * (_r2) + (_r3 * _r3))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_s5cedc029_1_mulAdd(otherX, otherY, otherZ, otherW, t, dest, (Float4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, ((otherW) * (_t14) - (_t31 * _t19)), ((otherZ) * (_t14) - (_t31 * _t21)), ((otherX) * (_t14) - (_t31 * _t16)), ((otherY) * (_t14) - (_t31 * _t24)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_s5cedc029_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, Float4 dest, Float4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        slerp_s78d3828a_tail_fma(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_s5cedc029_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Float4 dest, Float4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        slerp_s78d3828a_tail_mulAdd(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /**
     * Private store group 0 of {@code slerp}: computes and stores it. Shared by 2 identical private
     * paths of {@code slerp}; reached only through it.
     */
    private void slerp_s1e4ea7ff_c0_fma(Double4Impl _dst, float _t16, float _t64, float _sp0, float _t51, float _t24, float _t52, float _t21, float _t50, float _t19, float _t49) {
        _dst.x = java.lang.Math.fma(_t16, _t64, _sp0 * _t51);
        _dst.y = java.lang.Math.fma(_t24, _t64, _sp0 * _t52);
        _dst.z = java.lang.Math.fma(_t21, _t64, _sp0 * _t50);
        _dst.w = java.lang.Math.fma(_t19, _t64, _sp0 * _t49);
    }

    /**
     * Private tail of {@code slerp}. Shared by 2 identical private paths of {@code slerp}; reached
     * only through it.
     */
    private void slerp_s1e4ea7ff_tail_fma(Double4Impl _dst, float t, float _t10, float _t9, float _t57, float _t31, float _t16, float _t51, float _t24, float _t52, float _t21, float _t50, float _t19, float _t49) {
        float _t28 = t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        slerp_s1e4ea7ff_c0_fma(_dst, _t16, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)), _t51, _t24, _t52, _t21, _t50, _t19, _t49);
    }

    /**
     * Private tail of {@code slerp}. Shared by 2 identical private paths of {@code slerp}; reached
     * only through it.
     */
    private void slerp_s1e4ea7ff_tail_mulAdd(Double4Impl _dst, float t, float _t10, float _t9, float _t57, float _t31, float _t16, float _t51, float _t24, float _t52, float _t21, float _t50, float _t19, float _t49) {
        float _t28 = t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        {
            float _t64 = _t28 * Math.cosWithSin(_t61);
            float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
            _dst.x = ((_t16) * (_t64) + (_sp0 * _t51));
            _dst.y = ((_t24) * (_t64) + (_sp0 * _t52));
            _dst.z = ((_t21) * (_t64) + (_sp0 * _t50));
            _dst.w = ((_t19) * (_t64) + (_sp0 * _t49));
        }
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 slerp_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = java.lang.Math.fma(_r0, _r0, java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r2, _r2, _r3 * _r3)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_sa6ac05c0_1_fma(otherX, otherY, otherZ, otherW, t, dest, (Double4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, java.lang.Math.fma(otherW, _t14, -(_t31 * _t19)), java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21)), java.lang.Math.fma(otherX, _t14, -(_t31 * _t16)), java.lang.Math.fma(otherY, _t14, -(_t31 * _t24)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 slerp_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = ((_r0) * (_r0) + (((_r1) * (_r1) + (((_r2) * (_r2) + (_r3 * _r3))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_sa6ac05c0_1_mulAdd(otherX, otherY, otherZ, otherW, t, dest, (Double4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, ((otherW) * (_t14) - (_t31 * _t19)), ((otherZ) * (_t14) - (_t31 * _t21)), ((otherX) * (_t14) - (_t31 * _t16)), ((otherY) * (_t14) - (_t31 * _t24)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_sa6ac05c0_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, Double4 dest, Double4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, dest);
        slerp_s1e4ea7ff_tail_fma(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_sa6ac05c0_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Double4 dest, Double4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
        slerp_s1e4ea7ff_tail_mulAdd(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float4 slerp_degenerate_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s30e7730a_1_fma(otherX, otherY, otherZ, otherW, t, (Float4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float4 slerp_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Float4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s30e7730a_1_mulAdd(otherX, otherY, otherZ, otherW, t, (Float4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s30e7730a_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s30e7730a_2_fma(otherX, otherY, otherZ, otherW, t, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s30e7730a_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s30e7730a_2_mulAdd(otherX, otherY, otherZ, otherW, t, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, ((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s30e7730a_2_fma(float otherX, float otherY, float otherZ, float otherW, float t, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t50 : _t121 : _t121, _t114 * _t47) * _t25_inv : java.lang.Math.fma(t, otherX - _r2, _r2);
        d.y = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t47 : _t119 : _t119, _t114 * _t49) * _t25_inv : java.lang.Math.fma(t, otherY - _r3, _r3);
        return slerp_degenerate_s30e7730a_3_fma(otherZ, otherW, t, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s30e7730a_2_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t50 : _t121 : _t121) + (_t114 * _t47)) * _t25_inv : ((t) * (otherX - _r2) + (_r2));
        d.y = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t47 : _t119 : _t119) + (_t114 * _t49)) * _t25_inv : ((t) * (otherY - _r3) + (_r3));
        return slerp_degenerate_s30e7730a_3_mulAdd(otherZ, otherW, t, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s30e7730a_3_fma(float otherZ, float otherW, float t, Float4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t51 : _t122 : _t122, _t114 * _t45) * _t25_inv : java.lang.Math.fma(t, otherZ - _r0, _r0);
        d.w = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t45 : _t120 : _t120, _t114 * _t43) * _t25_inv : java.lang.Math.fma(t, otherW - _r1, _r1);
        return d;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s30e7730a_3_mulAdd(float otherZ, float otherW, float t, Float4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t51 : _t122 : _t122) + (_t114 * _t45)) * _t25_inv : ((t) * (otherZ - _r0) + (_r0));
        d.w = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t45 : _t120 : _t120) + (_t114 * _t43)) * _t25_inv : ((t) * (otherW - _r1) + (_r1));
        return d;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate_fma(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s2b9a0b3d_1_fma(otherX, otherY, otherZ, otherW, t, (Double4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, @Mutated Double4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s2b9a0b3d_1_mulAdd(otherX, otherY, otherZ, otherW, t, (Double4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s2b9a0b3d_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s2b9a0b3d_2_fma(otherX, otherY, otherZ, otherW, t, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s2b9a0b3d_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s2b9a0b3d_2_mulAdd(otherX, otherY, otherZ, otherW, t, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, ((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s2b9a0b3d_2_fma(float otherX, float otherY, float otherZ, float otherW, float t, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t50 : _t121 : _t121, _t114 * _t47) * _t25_inv : java.lang.Math.fma(t, otherX - _r2, _r2);
        d.y = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t47 : _t119 : _t119, _t114 * _t49) * _t25_inv : java.lang.Math.fma(t, otherY - _r3, _r3);
        return slerp_degenerate_s2b9a0b3d_3_fma(otherZ, otherW, t, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s2b9a0b3d_2_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t50 : _t121 : _t121) + (_t114 * _t47)) * _t25_inv : ((t) * (otherX - _r2) + (_r2));
        d.y = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t47 : _t119 : _t119) + (_t114 * _t49)) * _t25_inv : ((t) * (otherY - _r3) + (_r3));
        return slerp_degenerate_s2b9a0b3d_3_mulAdd(otherZ, otherW, t, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s2b9a0b3d_3_fma(float otherZ, float otherW, float t, Double4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t51 : _t122 : _t122, _t114 * _t45) * _t25_inv : java.lang.Math.fma(t, otherZ - _r0, _r0);
        d.w = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t45 : _t120 : _t120, _t114 * _t43) * _t25_inv : java.lang.Math.fma(t, otherW - _r1, _r1);
        return d;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s2b9a0b3d_3_mulAdd(float otherZ, float otherW, float t, Double4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t51 : _t122 : _t122) + (_t114 * _t45)) * _t25_inv : ((t) * (otherZ - _r0) + (_r0));
        d.w = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= 1.4551915E-11f ? _t45 : _t120 : _t120) + (_t114 * _t43)) * _t25_inv : ((t) * (otherW - _r1) + (_r1));
        return d;
    }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths, and store the result in
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
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param epsilon the squared sine of the angle between the two vectors at or below which
     *        vectors more than a right angle apart count as opposite, spanning no plane (the
     *        direction then turns through a fixed perpendicular of this vector); the overload
     *        without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 slerp(Float4R other, float t, float epsilon, @Mutated Float4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
    }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths, and store the result in
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
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param epsilon the squared sine of the angle between the two vectors at or below which
     *        vectors more than a right angle apart count as opposite, spanning no plane (the
     *        direction then turns through a fixed perpendicular of this vector); the overload
     *        without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 slerp(Float4R other, float t, float epsilon, @Mutated Double4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths, and store the result in {@code dest}.
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
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param epsilon the squared sine of the angle between the two vectors at or below which
     *        vectors more than a right angle apart count as opposite, spanning no plane (the
     *        direction then turns through a fixed perpendicular of this vector); the overload
     *        without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 slerp(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Float4 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 slerp_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Float4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = java.lang.Math.fma(_r0, _r0, java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r2, _r2, _r3 * _r3)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_s85be0bdb_1_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest, (Float4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, java.lang.Math.fma(otherW, _t14, -(_t31 * _t19)), java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21)), java.lang.Math.fma(otherX, _t14, -(_t31 * _t16)), java.lang.Math.fma(otherY, _t14, -(_t31 * _t24)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 slerp_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Float4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = ((_r0) * (_r0) + (((_r1) * (_r1) + (((_r2) * (_r2) + (_r3 * _r3))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_s85be0bdb_1_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest, (Float4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, ((otherW) * (_t14) - (_t31 * _t19)), ((otherZ) * (_t14) - (_t31 * _t21)), ((otherX) * (_t14) - (_t31 * _t16)), ((otherY) * (_t14) - (_t31 * _t24)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_s85be0bdb_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Float4 dest, Float4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        slerp_s78d3828a_tail_fma(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_s85be0bdb_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Float4 dest, Float4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        slerp_s78d3828a_tail_mulAdd(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths, and store the result in {@code dest}.
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
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param epsilon the squared sine of the angle between the two vectors at or below which
     *        vectors more than a right angle apart count as opposite, spanning no plane (the
     *        direction then turns through a fixed perpendicular of this vector); the overload
     *        without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 slerp(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Double4 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return slerp_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 slerp_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Double4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = java.lang.Math.fma(_r0, _r0, java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r2, _r2, _r3 * _r3)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_sa1ed34a6_1_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest, (Double4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, java.lang.Math.fma(otherW, _t14, -(_t31 * _t19)), java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21)), java.lang.Math.fma(otherX, _t14, -(_t31 * _t16)), java.lang.Math.fma(otherY, _t14, -(_t31 * _t24)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 slerp_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Double4 dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t9 = ((_r0) * (_r0) + (((_r1) * (_r1) + (((_r2) * (_r2) + (_r3 * _r3))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _r2 * _t11;
        float _t19 = _r0 * _t11;
        float _t21 = _r1 * _t11;
        float _t24 = _r3 * _t11;
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_sa1ed34a6_1_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest, (Double4Impl) dest, _t9, _t10, _t16, _t19, _t21, _t24, _t31, ((otherW) * (_t14) - (_t31 * _t19)), ((otherZ) * (_t14) - (_t31 * _t21)), ((otherX) * (_t14) - (_t31 * _t16)), ((otherY) * (_t14) - (_t31 * _t24)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_sa1ed34a6_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Double4 dest, Double4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        slerp_s1e4ea7ff_tail_fma(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_sa1ed34a6_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Double4 dest, Double4Impl d, float _t9, float _t10, float _t16, float _t19, float _t21, float _t24, float _t31, float _t40, float _t41, float _t42, float _t43) {
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, dest);
        slerp_s1e4ea7ff_tail_mulAdd(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float4 slerp_degenerate_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Float4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s484dd9be_1_fma(otherX, otherY, otherZ, otherW, t, epsilon, (Float4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float4 slerp_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Float4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s484dd9be_1_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, (Float4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s484dd9be_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s484dd9be_2_fma(otherX, otherY, otherZ, otherW, t, epsilon, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s484dd9be_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s484dd9be_2_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, ((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s484dd9be_2_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t50 : _t121 : _t121, _t114 * _t47) * _t25_inv : java.lang.Math.fma(t, otherX - _r2, _r2);
        d.y = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t47 : _t119 : _t119, _t114 * _t49) * _t25_inv : java.lang.Math.fma(t, otherY - _r3, _r3);
        return slerp_degenerate_s484dd9be_3_fma(otherZ, otherW, t, epsilon, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s484dd9be_2_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Float4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t50 : _t121 : _t121) + (_t114 * _t47)) * _t25_inv : ((t) * (otherX - _r2) + (_r2));
        d.y = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t47 : _t119 : _t119) + (_t114 * _t49)) * _t25_inv : ((t) * (otherY - _r3) + (_r3));
        return slerp_degenerate_s484dd9be_3_mulAdd(otherZ, otherW, t, epsilon, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s484dd9be_3_fma(float otherZ, float otherW, float t, float epsilon, Float4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t51 : _t122 : _t122, _t114 * _t45) * _t25_inv : java.lang.Math.fma(t, otherZ - _r0, _r0);
        d.w = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t45 : _t120 : _t120, _t114 * _t43) * _t25_inv : java.lang.Math.fma(t, otherW - _r1, _r1);
        return d;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Float4 slerp_degenerate_s484dd9be_3_mulAdd(float otherZ, float otherW, float t, float epsilon, Float4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t51 : _t122 : _t122) + (_t114 * _t45)) * _t25_inv : ((t) * (otherZ - _r0) + (_r0));
        d.w = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t45 : _t120 : _t120) + (_t114 * _t43)) * _t25_inv : ((t) * (otherW - _r1) + (_r1));
        return d;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Double4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s4b3e5ae1_1_fma(otherX, otherY, otherZ, otherW, t, epsilon, (Double4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, @Mutated Double4 dest) {
        float _r0 = this.z;
        float _r1 = this.w;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_r0, _r1, Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _r1 * _t8;
        float _t22 = _r0 * _t8;
        float _t23 = _r2 * _t8;
        float _t24 = _r3 * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s4b3e5ae1_1_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, (Double4Impl) dest, _r0, _r1, _r2, _r3, _t17, _t18, _t19, _t20, 1.0f / _t25, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t43, _t41 * _t22, _t41 * _t23, _t49, -_t49, -_t43, _t36 * _t37, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s4b3e5ae1_1_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s4b3e5ae1_2_fma(otherX, otherY, otherZ, otherW, t, epsilon, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s4b3e5ae1_1_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t17, float _t18, float _t19, float _t20, float _t25_inv, float _t40, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60) {
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
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        return slerp_degenerate_s4b3e5ae1_2_mulAdd(otherX, otherY, otherZ, otherW, t, epsilon, d, _r0, _r1, _r2, _r3, _t25_inv, _t43, _t45, _t47, _t49, _t50, _t51, _t58, _t60, _t63, _t90, _t81 * _t90, _t82 * _t90, _t83 * _t90, _t84 * _t90, ((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s4b3e5ae1_2_fma(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t50 : _t121 : _t121, _t114 * _t47) * _t25_inv : java.lang.Math.fma(t, otherX - _r2, _r2);
        d.y = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t47 : _t119 : _t119, _t114 * _t49) * _t25_inv : java.lang.Math.fma(t, otherY - _r3, _r3);
        return slerp_degenerate_s4b3e5ae1_3_fma(otherZ, otherW, t, epsilon, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s4b3e5ae1_2_mulAdd(float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, Double4Impl d, float _r0, float _r1, float _r2, float _r3, float _t25_inv, float _t43, float _t45, float _t47, float _t49, float _t50, float _t51, float _t58, float _t60, float _t63, float _t90, float _t97, float _t98, float _t99, float _t100, float _t102) {
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        d.x = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t50 : _t121 : _t121) + (_t114 * _t47)) * _t25_inv : ((t) * (otherX - _r2) + (_r2));
        d.y = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t47 : _t119 : _t119) + (_t114 * _t49)) * _t25_inv : ((t) * (otherY - _r3) + (_r3));
        return slerp_degenerate_s4b3e5ae1_3_mulAdd(otherZ, otherW, t, epsilon, d, _r0, _r1, _t25_inv, _t43, _t45, _t51, _t58, _t63, _t102, _t113, _t114, _t120, _t122);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s4b3e5ae1_3_fma(float otherZ, float otherW, float t, float epsilon, Double4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t51 : _t122 : _t122, _t114 * _t45) * _t25_inv : java.lang.Math.fma(t, otherZ - _r0, _r0);
        d.w = _t58 > 0.0f ? java.lang.Math.fma(_t113, _t63 < 0.0f ? _t102 <= epsilon ? _t45 : _t120 : _t120, _t114 * _t43) * _t25_inv : java.lang.Math.fma(t, otherW - _r1, _r1);
        return d;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_degenerate_s4b3e5ae1_3_mulAdd(float otherZ, float otherW, float t, float epsilon, Double4Impl d, float _r0, float _r1, float _t25_inv, float _t43, float _t45, float _t51, float _t58, float _t63, float _t102, float _t113, float _t114, float _t120, float _t122) {
        d.z = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t51 : _t122 : _t122) + (_t114 * _t45)) * _t25_inv : ((t) * (otherZ - _r0) + (_r0));
        d.w = _t58 > 0.0f ? ((_t113) * (_t63 < 0.0f ? _t102 <= epsilon ? _t45 : _t120 : _t120) + (_t114 * _t43)) * _t25_inv : ((t) * (otherW - _r1) + (_r1));
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
        d.w = java.lang.Math.abs(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
        d.w = java.lang.Math.abs(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.acos(this.x);
        d.y = Math.acos(this.y);
        d.z = Math.acos(this.z);
        d.w = Math.acos(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.acos(this.x);
        d.y = Math.acos(this.y);
        d.z = Math.acos(this.z);
        d.w = Math.acos(this.w);
        return d;
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
        Float4Impl bImpl = (Float4Impl) b;
        float bX = bImpl.x;
        float bY = bImpl.y;
        float bZ = bImpl.z;
        float bW = bImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            d.z = java.lang.Math.fma(scalar, bZ, this.z);
            d.w = java.lang.Math.fma(scalar, bW, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
            d.z = ((scalar) * (bZ) + (this.z));
            d.w = ((scalar) * (bW) + (this.w));
            return d;
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
        Float4Impl bImpl = (Float4Impl) b;
        float bX = bImpl.x;
        float bY = bImpl.y;
        float bZ = bImpl.z;
        float bW = bImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            d.z = java.lang.Math.fma(scalar, bZ, this.z);
            d.w = java.lang.Math.fma(scalar, bW, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
            d.z = ((scalar) * (bZ) + (this.z));
            d.w = ((scalar) * (bW) + (this.w));
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            d.z = java.lang.Math.fma(scalar, bZ, this.z);
            d.w = java.lang.Math.fma(scalar, bW, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
            d.z = ((scalar) * (bZ) + (this.z));
            d.w = ((scalar) * (bW) + (this.w));
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            d.z = java.lang.Math.fma(scalar, bZ, this.z);
            d.w = java.lang.Math.fma(scalar, bW, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
            d.z = ((scalar) * (bZ) + (this.z));
            d.w = ((scalar) * (bW) + (this.w));
            return d;
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
        Float4Impl bImpl = (Float4Impl) b;
        Float4Impl cImpl = (Float4Impl) c;
        float bX = bImpl.x;
        float bY = bImpl.y;
        float bZ = bImpl.z;
        float bW = bImpl.w;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        float cW = cImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            d.z = java.lang.Math.fma(bZ, cZ, this.z);
            d.w = java.lang.Math.fma(bW, cW, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            d.z = ((bZ) * (cZ) + (this.z));
            d.w = ((bW) * (cW) + (this.w));
            return d;
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
        Float4Impl bImpl = (Float4Impl) b;
        Float4Impl cImpl = (Float4Impl) c;
        float bX = bImpl.x;
        float bY = bImpl.y;
        float bZ = bImpl.z;
        float bW = bImpl.w;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        float cW = cImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            d.z = java.lang.Math.fma(bZ, cZ, this.z);
            d.w = java.lang.Math.fma(bW, cW, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            d.z = ((bZ) * (cZ) + (this.z));
            d.w = ((bW) * (cW) + (this.w));
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            d.z = java.lang.Math.fma(bZ, cZ, this.z);
            d.w = java.lang.Math.fma(bW, cW, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            d.z = ((bZ) * (cZ) + (this.z));
            d.w = ((bW) * (cW) + (this.w));
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            d.z = java.lang.Math.fma(bZ, cZ, this.z);
            d.w = java.lang.Math.fma(bW, cW, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            d.z = ((bZ) * (cZ) + (this.z));
            d.w = ((bW) * (cW) + (this.w));
            return d;
        }
    }

    /**
     * Compute the angle in radians between this vector and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared norm of their wedge (exterior) product would
     * leave the {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude; {@code other} may have
     * components of any finite magnitude.
     *
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}
     */
    public float angleBetween(Float4R other) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return angleBetween_fma(otherX, otherY, otherZ, otherW);
        return angleBetween_mulAdd(otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared norm of their wedge (exterior) product would
     * leave the {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude;
     * {@code (otherX, otherY, otherZ, otherW)} may have components of any finite magnitude.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the angle in radians between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public float angleBetween(float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.FmaSwitch.ON) return angleBetween_fma(otherX, otherY, otherZ, otherW);
        return angleBetween_mulAdd(otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private float angleBetween_fma(float otherX, float otherY, float otherZ, float otherW) {
        float _t6 = otherZ * -this.w;
        float _t7 = otherY * -this.w;
        float _t8 = -(otherY * this.z);
        float _t9 = -otherX * this.w;
        float _t10 = -otherX * this.y;
        float _t11 = -otherX * this.z;
        float _t24 = java.lang.Math.fma(otherW, this.z, _t6) - java.lang.Math.fma(otherZ, this.w, _t6);
        float _t25 = java.lang.Math.fma(otherW, this.y, _t7) - java.lang.Math.fma(otherY, this.w, _t7);
        float _t26 = java.lang.Math.fma(otherZ, this.y, _t8) - java.lang.Math.fma(otherY, this.z, _t8);
        float _t27 = java.lang.Math.fma(otherW, this.x, _t9) - java.lang.Math.fma(otherX, this.w, _t9);
        float _t28 = java.lang.Math.fma(otherY, this.x, _t10) - java.lang.Math.fma(otherX, this.y, _t10);
        float _t29 = java.lang.Math.fma(otherZ, this.x, _t11) - java.lang.Math.fma(otherX, this.z, _t11);
        float _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return angleBetween_degenerate_fma(otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))));
    }

    /** {@code angleBetween} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private float angleBetween_mulAdd(float otherX, float otherY, float otherZ, float otherW) {
        float _t6 = otherZ * -this.w;
        float _t7 = otherY * -this.w;
        float _t8 = -(otherY * this.z);
        float _t9 = -otherX * this.w;
        float _t10 = -otherX * this.y;
        float _t11 = -otherX * this.z;
        float _t24 = ((otherW) * (this.z) + (_t6)) - ((otherZ) * (this.w) + (_t6));
        float _t25 = ((otherW) * (this.y) + (_t7)) - ((otherY) * (this.w) + (_t7));
        float _t26 = ((otherZ) * (this.y) + (_t8)) - ((otherY) * (this.z) + (_t8));
        float _t27 = ((otherW) * (this.x) + (_t9)) - ((otherX) * (this.w) + (_t9));
        float _t28 = ((otherY) * (this.x) + (_t10)) - ((otherX) * (this.y) + (_t10));
        float _t29 = ((otherZ) * (this.x) + (_t11)) - ((otherX) * (this.z) + (_t11));
        float _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return angleBetween_degenerate_mulAdd(otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))))));
    }

    /** Private tail of {@code angleBetween_degenerate}; reached only through it. */
    private float angleBetween_degenerate_s543b568e_tail_fma(float _t18, float _t21, float _t32, float _t22, float _t17, float _t16, float _t33, float _t19, float _t23, float _t34, float _t20, float _t35, float _t49, float _t48) {
        float _t50 = java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32);
        float _t51 = java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33);
        float _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** Private tail of {@code angleBetween_degenerate}; reached only through it. */
    private float angleBetween_degenerate_s543b568e_tail_mulAdd(float _t18, float _t21, float _t32, float _t22, float _t17, float _t16, float _t33, float _t19, float _t23, float _t34, float _t20, float _t35, float _t49, float _t48) {
        float _t50 = ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32));
        float _t51 = ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33));
        float _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private float angleBetween_degenerate_fma(float otherX, float otherY, float otherZ, float otherW) {
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(this.z, this.w, Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y)));
        float _t16 = otherW * _t6;
        float _t17 = this.z * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = this.w * _t7;
        float _t20 = otherY * _t6;
        float _t21 = this.x * _t7;
        float _t22 = otherX * _t6;
        float _t23 = this.y * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        return angleBetween_degenerate_s543b568e_tail_fma(_t18, _t21, -_t22 * _t17, _t22, _t17, _t16, _t22 * -_t19, _t19, _t23, -(_t20 * _t17), _t20, _t20 * -_t19, java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30));
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private float angleBetween_degenerate_mulAdd(float otherX, float otherY, float otherZ, float otherW) {
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(this.z, this.w, Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y)));
        float _t16 = otherW * _t6;
        float _t17 = this.z * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = this.w * _t7;
        float _t20 = otherY * _t6;
        float _t21 = this.x * _t7;
        float _t22 = otherX * _t6;
        float _t23 = this.y * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        return angleBetween_degenerate_s543b568e_tail_mulAdd(_t18, _t21, -_t22 * _t17, _t22, _t17, _t16, _t22 * -_t19, _t19, _t23, -(_t20 * _t17), _t20, _t20 * -_t19, ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)));
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.asin(this.x);
        d.y = Math.asin(this.y);
        d.z = Math.asin(this.z);
        d.w = Math.asin(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.asin(this.x);
        d.y = Math.asin(this.y);
        d.z = Math.asin(this.z);
        d.w = Math.asin(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.atan(this.x);
        d.y = Math.atan(this.y);
        d.z = Math.atan(this.z);
        d.w = Math.atan(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.atan(this.x);
        d.y = Math.atan(this.y);
        d.z = Math.atan(this.z);
        d.w = Math.atan(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.atan2(this.x, x);
        d.y = Math.atan2(this.y, x);
        d.z = Math.atan2(this.z, x);
        d.w = Math.atan2(this.w, x);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.atan2(this.x, x);
        d.y = Math.atan2(this.y, x);
        d.z = Math.atan2(this.z, x);
        d.w = Math.atan2(this.w, x);
        return d;
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
        Float4Impl xImpl = (Float4Impl) x;
        float xX = xImpl.x;
        float xY = xImpl.y;
        float xZ = xImpl.z;
        float xW = xImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.atan2(this.x, xX);
        d.y = Math.atan2(this.y, xY);
        d.z = Math.atan2(this.z, xZ);
        d.w = Math.atan2(this.w, xW);
        return d;
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
        Float4Impl xImpl = (Float4Impl) x;
        float xX = xImpl.x;
        float xY = xImpl.y;
        float xZ = xImpl.z;
        float xW = xImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.atan2(this.x, xX);
        d.y = Math.atan2(this.y, xY);
        d.z = Math.atan2(this.z, xZ);
        d.w = Math.atan2(this.w, xW);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.atan2(this.x, xX);
        d.y = Math.atan2(this.y, xY);
        d.z = Math.atan2(this.z, xZ);
        d.w = Math.atan2(this.w, xW);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.atan2(this.x, xX);
        d.y = Math.atan2(this.y, xY);
        d.z = Math.atan2(this.z, xZ);
        d.w = Math.atan2(this.w, xW);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.cbrt(this.x);
        d.y = Math.cbrt(this.y);
        d.z = Math.cbrt(this.z);
        d.w = Math.cbrt(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.cbrt(this.x);
        d.y = Math.cbrt(this.y);
        d.z = Math.cbrt(this.z);
        d.w = Math.cbrt(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.ceil(this.x);
        d.y = Math.ceil(this.y);
        d.z = Math.ceil(this.z);
        d.w = Math.ceil(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.ceil(this.x);
        d.y = Math.ceil(this.y);
        d.z = Math.ceil(this.z);
        d.w = Math.ceil(this.w);
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
    public Float4 clamp(float min, float max, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
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
    public Float4 clamp(Float4R min, Float4R max, @Mutated Float4 dest) {
        Float4Impl minImpl = (Float4Impl) min;
        Float4Impl maxImpl = (Float4Impl) max;
        float minX = minImpl.x;
        float minY = minImpl.y;
        float minZ = minImpl.z;
        float minW = minImpl.w;
        float maxX = maxImpl.x;
        float maxY = maxImpl.y;
        float maxZ = maxImpl.z;
        float maxW = maxImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.min(Math.max(this.x, minX), maxX);
        d.y = Math.min(Math.max(this.y, minY), maxY);
        d.z = Math.min(Math.max(this.z, minZ), maxZ);
        d.w = Math.min(Math.max(this.w, minW), maxW);
        return d;
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
        Float4Impl minImpl = (Float4Impl) min;
        Float4Impl maxImpl = (Float4Impl) max;
        float minX = minImpl.x;
        float minY = minImpl.y;
        float minZ = minImpl.z;
        float minW = minImpl.w;
        float maxX = maxImpl.x;
        float maxY = maxImpl.y;
        float maxZ = maxImpl.z;
        float maxW = maxImpl.w;
        Double4Impl d = (Double4Impl) dest;
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
        Float4Impl d = (Float4Impl) dest;
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
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public float compAdd() {
        return this.w + (this.z + (this.x + this.y));
    }

    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public float compMax() {
        return Math.max(Math.max(Math.max(this.x, this.y), this.z), this.w);
    }

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public float compMin() {
        return Math.min(Math.min(Math.min(this.x, this.y), this.z), this.w);
    }

    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public float compMul() {
        return this.w * this.z * this.x * this.y;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.copySign(this.x, sign);
        d.y = Math.copySign(this.y, sign);
        d.z = Math.copySign(this.z, sign);
        d.w = Math.copySign(this.w, sign);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.copySign(this.x, sign);
        d.y = Math.copySign(this.y, sign);
        d.z = Math.copySign(this.z, sign);
        d.w = Math.copySign(this.w, sign);
        return d;
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
        Float4Impl signImpl = (Float4Impl) sign;
        float signX = signImpl.x;
        float signY = signImpl.y;
        float signZ = signImpl.z;
        float signW = signImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.copySign(this.x, signX);
        d.y = Math.copySign(this.y, signY);
        d.z = Math.copySign(this.z, signZ);
        d.w = Math.copySign(this.w, signW);
        return d;
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
        Float4Impl signImpl = (Float4Impl) sign;
        float signX = signImpl.x;
        float signY = signImpl.y;
        float signZ = signImpl.z;
        float signW = signImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.copySign(this.x, signX);
        d.y = Math.copySign(this.y, signY);
        d.z = Math.copySign(this.z, signZ);
        d.w = Math.copySign(this.w, signW);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.copySign(this.x, signX);
        d.y = Math.copySign(this.y, signY);
        d.z = Math.copySign(this.z, signZ);
        d.w = Math.copySign(this.w, signW);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.copySign(this.x, signX);
        d.y = Math.copySign(this.y, signY);
        d.z = Math.copySign(this.z, signZ);
        d.w = Math.copySign(this.w, signW);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.cos(this.x);
        d.y = Math.cos(this.y);
        d.z = Math.cos(this.z);
        d.w = Math.cos(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.cos(this.x);
        d.y = Math.cos(this.y);
        d.z = Math.cos(this.z);
        d.w = Math.cos(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.cosh(this.x);
        d.y = Math.cosh(this.y);
        d.z = Math.cosh(this.z);
        d.w = Math.cosh(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.cosh(this.x);
        d.y = Math.cosh(this.y);
        d.z = Math.cosh(this.z);
        d.w = Math.cosh(this.w);
        return d;
    }

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent), and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Float4 cross(Float4R v, Float4R w, @Mutated Float4 dest) {
        Float4Impl vImpl = (Float4Impl) v;
        Float4Impl wImpl = (Float4Impl) w;
        float vX = vImpl.x;
        float vY = vImpl.y;
        float vZ = vImpl.z;
        float vW = vImpl.w;
        float wX = wImpl.x;
        float wY = wImpl.y;
        float wZ = wImpl.z;
        float wW = wImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return cross_fma(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
        return cross_mulAdd(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
    }

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent), and store the result in {@code dest}.
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
        Float4Impl vImpl = (Float4Impl) v;
        Float4Impl wImpl = (Float4Impl) w;
        float vX = vImpl.x;
        float vY = vImpl.y;
        float vZ = vImpl.z;
        float vW = vImpl.w;
        float wX = wImpl.x;
        float wY = wImpl.y;
        float wZ = wImpl.z;
        float wW = wImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) return cross_fma(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
        return cross_mulAdd(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
    }

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return cross_fma(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
        return cross_mulAdd(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
    }

    /** {@code cross} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 cross_fma(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        float _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        float _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        float _t27 = java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9);
        float _t28 = java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10);
        float _t29 = java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11);
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = java.lang.Math.fma(this.w, _t24, java.lang.Math.fma(_rd1, _t25, -_rd2 * _t26));
        d.y = java.lang.Math.fma(-this.w, _t27, java.lang.Math.fma(_rd2, _t28, -_rd0 * _t25));
        d.z = java.lang.Math.fma(this.w, _t29, java.lang.Math.fma(_rd0, _t26, -(_rd1 * _t28)));
        d.w = java.lang.Math.fma(-_rd2, _t29, java.lang.Math.fma(_rd1, _t27, -_rd0 * _t24));
        return d;
    }

    /** {@code cross} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 cross_mulAdd(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        float _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        float _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        float _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        float _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        float _t29 = ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11));
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = ((this.w) * (_t24) + (((_rd1) * (_t25) - (_rd2 * _t26))));
        d.y = ((((_rd2) * (_t28) - (_rd0 * _t25))) - (this.w) * (_t27));
        d.z = ((this.w) * (_t29) + (((_rd0) * (_t26) - (_rd1 * _t28))));
        d.w = ((((_rd1) * (_t27) - (_rd0 * _t24))) - (_rd2) * (_t29));
        return d;
    }

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) return cross_fma(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
        return cross_mulAdd(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
    }

    /** {@code cross} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 cross_fma(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        float _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        float _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        float _t27 = java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9);
        float _t28 = java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10);
        float _t29 = java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11);
        d.x = java.lang.Math.fma(this.w, _t24, java.lang.Math.fma(this.y, _t25, -this.z * _t26));
        d.y = java.lang.Math.fma(-this.w, _t27, java.lang.Math.fma(this.z, _t28, -this.x * _t25));
        d.z = java.lang.Math.fma(this.w, _t29, java.lang.Math.fma(this.x, _t26, -(this.y * _t28)));
        d.w = java.lang.Math.fma(-this.z, _t29, java.lang.Math.fma(this.y, _t27, -this.x * _t24));
        return d;
    }

    /** {@code cross} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 cross_mulAdd(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        float _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        float _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        float _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        float _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        float _t29 = ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11));
        d.x = ((this.w) * (_t24) + (((this.y) * (_t25) - (this.z * _t26))));
        d.y = ((((this.z) * (_t28) - (this.x * _t25))) - (this.w) * (_t27));
        d.z = ((this.w) * (_t29) + (((this.x) * (_t26) - (this.y * _t28))));
        d.w = ((((this.y) * (_t27) - (this.x * _t24))) - (this.z) * (_t29));
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.toDegrees(this.x);
        d.y = Math.toDegrees(this.y);
        d.z = Math.toDegrees(this.z);
        d.w = Math.toDegrees(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.toDegrees(this.x);
        d.y = Math.toDegrees(this.y);
        d.z = Math.toDegrees(this.z);
        d.w = Math.toDegrees(this.w);
        return d;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = this.w - otherW;
            float _t1 = this.z - otherZ;
            float _t2 = this.x - otherX;
            float _t3 = this.y - otherY;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            return ((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            return ((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.exp(this.x);
        d.y = Math.exp(this.y);
        d.z = Math.exp(this.z);
        d.w = Math.exp(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.exp(this.x);
        d.y = Math.exp(this.y);
        d.z = Math.exp(this.z);
        d.w = Math.exp(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.pow(2.0f, this.x);
        d.y = Math.pow(2.0f, this.y);
        d.z = Math.pow(2.0f, this.z);
        d.w = Math.pow(2.0f, this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.pow(2.0f, this.x);
        d.y = Math.pow(2.0f, this.y);
        d.z = Math.pow(2.0f, this.z);
        d.w = Math.pow(2.0f, this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.expm1(this.x);
        d.y = Math.expm1(this.y);
        d.z = Math.expm1(this.z);
        d.w = Math.expm1(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.expm1(this.x);
        d.y = Math.expm1(this.y);
        d.z = Math.expm1(this.z);
        d.w = Math.expm1(this.w);
        return d;
    }

    /**
     * Orient this vector against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} (kept when {@code dot(Nref, I)} is negative, negated otherwise) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @param dest will hold the result
     * @return dest
     */
    public Float4 faceforward(Float4R I, Float4R Nref, @Mutated Float4 dest) {
        Float4Impl IImpl = (Float4Impl) I;
        Float4Impl NrefImpl = (Float4Impl) Nref;
        float IX = IImpl.x;
        float IY = IImpl.y;
        float IZ = IImpl.z;
        float IW = IImpl.w;
        float NrefX = NrefImpl.x;
        float NrefY = NrefImpl.y;
        float NrefZ = NrefImpl.z;
        float NrefW = NrefImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
        }
    }

    /**
     * Orient this vector against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} (kept when {@code dot(Nref, I)} is negative, negated otherwise) and store the
     * result in {@code dest}.
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
        Float4Impl IImpl = (Float4Impl) I;
        Float4Impl NrefImpl = (Float4Impl) Nref;
        float IX = IImpl.x;
        float IY = IImpl.y;
        float IZ = IImpl.z;
        float IW = IImpl.w;
        float NrefX = NrefImpl.x;
        float NrefY = NrefImpl.y;
        float NrefZ = NrefImpl.z;
        float NrefW = NrefImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
        }
    }

    /**
     * Orient this vector against the incident direction ({@code IX}, {@code IY}, {@code IZ},
     * {@code IW}) as judged by the reference vector ({@code NrefX}, {@code NrefY}, {@code NrefZ},
     * {@code NrefW}) (kept when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))} is
     * negative, negated otherwise) and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
        }
    }

    /**
     * Orient this vector against the incident direction ({@code IX}, {@code IY}, {@code IZ},
     * {@code IW}) as judged by the reference vector ({@code NrefX}, {@code NrefY}, {@code NrefZ},
     * {@code NrefW}) (kept when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))} is
     * negative, negated otherwise) and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
            return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.floor(this.x);
        d.y = Math.floor(this.y);
        d.z = Math.floor(this.z);
        d.w = Math.floor(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.floor(this.x);
        d.y = Math.floor(this.y);
        d.z = Math.floor(this.z);
        d.w = Math.floor(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.min(this.x - Math.floor(this.x), 0.99999994f);
        d.y = Math.min(this.y - Math.floor(this.y), 0.99999994f);
        d.z = Math.min(this.z - Math.floor(this.z), 0.99999994f);
        d.w = Math.min(this.w - Math.floor(this.w), 0.99999994f);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(this.x - Math.floor(this.x), 0.99999994f);
        d.y = Math.min(this.y - Math.floor(this.y), 0.99999994f);
        d.z = Math.min(this.z - Math.floor(this.z), 0.99999994f);
        d.w = Math.min(this.w - Math.floor(this.w), 0.99999994f);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.hypot(this.x, y);
        d.y = Math.hypot(this.y, y);
        d.z = Math.hypot(this.z, y);
        d.w = Math.hypot(this.w, y);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.hypot(this.x, y);
        d.y = Math.hypot(this.y, y);
        d.z = Math.hypot(this.z, y);
        d.w = Math.hypot(this.w, y);
        return d;
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
        Float4Impl yImpl = (Float4Impl) y;
        float yX = yImpl.x;
        float yY = yImpl.y;
        float yZ = yImpl.z;
        float yW = yImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.hypot(this.x, yX);
        d.y = Math.hypot(this.y, yY);
        d.z = Math.hypot(this.z, yZ);
        d.w = Math.hypot(this.w, yW);
        return d;
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
        Float4Impl yImpl = (Float4Impl) y;
        float yX = yImpl.x;
        float yY = yImpl.y;
        float yZ = yImpl.z;
        float yW = yImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.hypot(this.x, yX);
        d.y = Math.hypot(this.y, yY);
        d.z = Math.hypot(this.z, yZ);
        d.w = Math.hypot(this.w, yW);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.hypot(this.x, yX);
        d.y = Math.hypot(this.y, yY);
        d.z = Math.hypot(this.z, yZ);
        d.w = Math.hypot(this.w, yW);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.hypot(this.x, yX);
        d.y = Math.hypot(this.y, yY);
        d.z = Math.hypot(this.z, yZ);
        d.w = Math.hypot(this.w, yW);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = 1.0f / this.x;
        d.y = 1.0f / this.y;
        d.z = 1.0f / this.z;
        d.w = 1.0f / this.w;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = 1.0f / this.x;
        d.y = 1.0f / this.y;
        d.z = 1.0f / this.z;
        d.w = 1.0f / this.w;
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = (1.0f / (float) java.lang.Math.sqrt(this.x));
        d.y = (1.0f / (float) java.lang.Math.sqrt(this.y));
        d.z = (1.0f / (float) java.lang.Math.sqrt(this.z));
        d.w = (1.0f / (float) java.lang.Math.sqrt(this.w));
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = (1.0f / (float) java.lang.Math.sqrt(this.x));
        d.y = (1.0f / (float) java.lang.Math.sqrt(this.y));
        d.z = (1.0f / (float) java.lang.Math.sqrt(this.z));
        d.w = (1.0f / (float) java.lang.Math.sqrt(this.w));
        return d;
    }

    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this vector
     */
    public float length() {
        if (org.joml2.internal.FmaSwitch.ON) {
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))));
        } else {
            return (float) java.lang.Math.sqrt(((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y)))))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        } else {
            return ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.log(this.x);
        d.y = Math.log(this.y);
        d.z = Math.log(this.z);
        d.w = Math.log(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.log(this.x);
        d.y = Math.log(this.y);
        d.z = Math.log(this.z);
        d.w = Math.log(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.log10(this.x);
        d.y = Math.log10(this.y);
        d.z = Math.log10(this.z);
        d.w = Math.log10(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.log10(this.x);
        d.y = Math.log10(this.y);
        d.z = Math.log10(this.z);
        d.w = Math.log10(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.log1p(this.x);
        d.y = Math.log1p(this.y);
        d.z = Math.log1p(this.z);
        d.w = Math.log1p(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.log1p(this.x);
        d.y = Math.log1p(this.y);
        d.z = Math.log1p(this.z);
        d.w = Math.log1p(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.log2(this.x);
        d.y = Math.log2(this.y);
        d.z = Math.log2(this.z);
        d.w = Math.log2(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.log2(this.x);
        d.y = Math.log2(this.y);
        d.z = Math.log2(this.z);
        d.w = Math.log2(this.w);
        return d;
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ) + java.lang.Math.abs(this.w - otherW);
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
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ) + java.lang.Math.abs(this.w - otherW);
    }

    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public float manhattanLength() {
        return java.lang.Math.abs(this.x) + java.lang.Math.abs(this.y) + java.lang.Math.abs(this.z) + java.lang.Math.abs(this.w);
    }

    /**
     * Compute the larger of each component of this vector and {@code scalar} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 max(float scalar, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.max(this.x, scalar);
        d.y = Math.max(this.y, scalar);
        d.z = Math.max(this.z, scalar);
        d.w = Math.max(this.w, scalar);
        return d;
    }

    /**
     * Compute the larger of each component of this vector and {@code scalar} and store the result
     * in {@code dest}.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.max(this.x, scalar);
        d.y = Math.max(this.y, scalar);
        d.z = Math.max(this.z, scalar);
        d.w = Math.max(this.w, scalar);
        return d;
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 max(Float4R other, @Mutated Float4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        d.w = Math.max(this.w, otherW);
        return d;
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        d.w = Math.max(this.w, otherW);
        return d;
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        d.w = Math.max(this.w, otherW);
        return d;
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        d.w = Math.max(this.w, otherW);
        return d;
    }

    /**
     * Compute the smaller of each component of this vector and {@code scalar} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 min(float scalar, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.min(this.x, scalar);
        d.y = Math.min(this.y, scalar);
        d.z = Math.min(this.z, scalar);
        d.w = Math.min(this.w, scalar);
        return d;
    }

    /**
     * Compute the smaller of each component of this vector and {@code scalar} and store the result
     * in {@code dest}.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(this.x, scalar);
        d.y = Math.min(this.y, scalar);
        d.z = Math.min(this.z, scalar);
        d.w = Math.min(this.w, scalar);
        return d;
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Float4 min(Float4R other, @Mutated Float4 dest) {
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
        return d;
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
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
        Float4Impl otherImpl = (Float4Impl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherW = otherImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
        return d;
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
        return d;
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
        return d;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} (for a
     * component {@code a} and its divisor {@code b}: {@code a % b}, plus {@code b} when that
     * remainder is non-zero and its sign differs from {@code b}'s - exactly Kotlin's {@code mod})
     * and store the result in {@code dest}.
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
        Float4Impl d = (Float4Impl) dest;
        d.x = flooredMod(this.x, y);
        d.y = flooredMod(this.y, y);
        d.z = flooredMod(this.z, y);
        d.w = flooredMod(this.w, y);
        return d;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} (for a
     * component {@code a} and its divisor {@code b}: {@code a % b}, plus {@code b} when that
     * remainder is non-zero and its sign differs from {@code b}'s - exactly Kotlin's {@code mod})
     * and store the result in {@code dest}.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = flooredMod(this.x, y);
        d.y = flooredMod(this.y, y);
        d.z = flooredMod(this.z, y);
        d.w = flooredMod(this.w, y);
        return d;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} (for a
     * component {@code a} and its divisor {@code b}: {@code a % b}, plus {@code b} when that
     * remainder is non-zero and its sign differs from {@code b}'s - exactly Kotlin's {@code mod})
     * and store the result in {@code dest}.
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
        Float4Impl yImpl = (Float4Impl) y;
        float yX = yImpl.x;
        float yY = yImpl.y;
        float yZ = yImpl.z;
        float yW = yImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
        d.z = flooredMod(this.z, yZ);
        d.w = flooredMod(this.w, yW);
        return d;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} (for a
     * component {@code a} and its divisor {@code b}: {@code a % b}, plus {@code b} when that
     * remainder is non-zero and its sign differs from {@code b}'s - exactly Kotlin's {@code mod})
     * and store the result in {@code dest}.
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
        Float4Impl yImpl = (Float4Impl) y;
        float yX = yImpl.x;
        float yY = yImpl.y;
        float yZ = yImpl.z;
        float yW = yImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
        d.z = flooredMod(this.z, yZ);
        d.w = flooredMod(this.w, yW);
        return d;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) (for a component {@code a} and its divisor {@code b}: {@code a % b},
     * plus {@code b} when that remainder is non-zero and its sign differs from {@code b}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
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
        Float4Impl d = (Float4Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
        d.z = flooredMod(this.z, yZ);
        d.w = flooredMod(this.w, yW);
        return d;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) (for a component {@code a} and its divisor {@code b}: {@code a % b},
     * plus {@code b} when that remainder is non-zero and its sign differs from {@code b}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
        d.z = flooredMod(this.z, yZ);
        d.w = flooredMod(this.w, yW);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.nextDown(this.x);
        d.y = Math.nextDown(this.y);
        d.z = Math.nextDown(this.z);
        d.w = Math.nextDown(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.nextDown(this.x);
        d.y = Math.nextDown(this.y);
        d.z = Math.nextDown(this.z);
        d.w = Math.nextDown(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.nextUp(this.x);
        d.y = Math.nextUp(this.y);
        d.z = Math.nextUp(this.z);
        d.w = Math.nextUp(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.nextUp(this.x);
        d.y = Math.nextUp(this.y);
        d.z = Math.nextUp(this.z);
        d.w = Math.nextUp(this.w);
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        }
    }

    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector), and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @param dest will hold the result
     * @return dest
     */
    public Float4 normalizeMul(float length, @Mutated Float4 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t5;
                d.y = this.y * _t5;
                d.z = this.z * _t5;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t5;
                d.y = this.y * _t5;
                d.z = this.z * _t5;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        }
    }

    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t5;
                d.y = this.y * _t5;
                d.z = this.z * _t5;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t5;
                d.y = this.y * _t5;
                d.z = this.z * _t5;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
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
        Float4Impl rowImpl = (Float4Impl) row;
        float rowX = rowImpl.x;
        float rowY = rowImpl.y;
        float rowZ = rowImpl.z;
        float rowW = rowImpl.w;
        Float4x4Impl d = (Float4x4Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m20 = rowX * this.z;
        d.m30 = rowX * this.w;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.m21 = rowY * this.z;
        d.m31 = rowY * this.w;
        d.m02 = rowZ * this.x;
        d.m12 = rowZ * this.y;
        d.m22 = rowZ * this.z;
        d.m32 = rowZ * this.w;
        d.m03 = rowW * this.x;
        d.m13 = rowW * this.y;
        d.m23 = rowW * this.z;
        d.m33 = rowW * this.w;
        d.properties = 0;
        return d;
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
        Float4Impl rowImpl = (Float4Impl) row;
        float rowX = rowImpl.x;
        float rowY = rowImpl.y;
        float rowZ = rowImpl.z;
        float rowW = rowImpl.w;
        Double4x4Impl d = (Double4x4Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m20 = rowX * this.z;
        d.m30 = rowX * this.w;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.m21 = rowY * this.z;
        d.m31 = rowY * this.w;
        d.m02 = rowZ * this.x;
        d.m12 = rowZ * this.y;
        d.m22 = rowZ * this.z;
        d.m32 = rowZ * this.w;
        d.m03 = rowW * this.x;
        d.m13 = rowW * this.y;
        d.m23 = rowW * this.z;
        d.m33 = rowW * this.w;
        d.properties = 0;
        return d;
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
        Float4x4Impl d = (Float4x4Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m20 = rowX * this.z;
        d.m30 = rowX * this.w;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.m21 = rowY * this.z;
        d.m31 = rowY * this.w;
        d.m02 = rowZ * this.x;
        d.m12 = rowZ * this.y;
        d.m22 = rowZ * this.z;
        d.m32 = rowZ * this.w;
        d.m03 = rowW * this.x;
        d.m13 = rowW * this.y;
        d.m23 = rowW * this.z;
        d.m33 = rowW * this.w;
        d.properties = 0;
        return d;
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
        Double4x4Impl d = (Double4x4Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m20 = rowX * this.z;
        d.m30 = rowX * this.w;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.m21 = rowY * this.z;
        d.m31 = rowY * this.w;
        d.m02 = rowZ * this.x;
        d.m12 = rowZ * this.y;
        d.m22 = rowZ * this.z;
        d.m32 = rowZ * this.w;
        d.m03 = rowW * this.x;
        d.m13 = rowW * this.y;
        d.m23 = rowW * this.z;
        d.m33 = rowW * this.w;
        d.properties = 0;
        return d;
    }

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    public Float4 pow(float exponent, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.pow(this.x, exponent);
        d.y = Math.pow(this.y, exponent);
        d.z = Math.pow(this.z, exponent);
        d.w = Math.pow(this.w, exponent);
        return d;
    }

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.pow(this.x, exponent);
        d.y = Math.pow(this.y, exponent);
        d.z = Math.pow(this.z, exponent);
        d.w = Math.pow(this.w, exponent);
        return d;
    }

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    public Float4 pow(Float4R exponent, @Mutated Float4 dest) {
        Float4Impl exponentImpl = (Float4Impl) exponent;
        float exponentX = exponentImpl.x;
        float exponentY = exponentImpl.y;
        float exponentZ = exponentImpl.z;
        float exponentW = exponentImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.pow(this.x, exponentX);
        d.y = Math.pow(this.y, exponentY);
        d.z = Math.pow(this.z, exponentZ);
        d.w = Math.pow(this.w, exponentW);
        return d;
    }

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
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
        Float4Impl exponentImpl = (Float4Impl) exponent;
        float exponentX = exponentImpl.x;
        float exponentY = exponentImpl.y;
        float exponentZ = exponentImpl.z;
        float exponentW = exponentImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.pow(this.x, exponentX);
        d.y = Math.pow(this.y, exponentY);
        d.z = Math.pow(this.z, exponentZ);
        d.w = Math.pow(this.w, exponentW);
        return d;
    }

    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}, {@code exponentW}) and store the result in {@code dest}.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.pow(this.x, exponentX);
        d.y = Math.pow(this.y, exponentY);
        d.z = Math.pow(this.z, exponentZ);
        d.w = Math.pow(this.w, exponentW);
        return d;
    }

    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}, {@code exponentW}) and store the result in {@code dest}.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.pow(this.x, exponentX);
        d.y = Math.pow(this.y, exponentY);
        d.z = Math.pow(this.z, exponentZ);
        d.w = Math.pow(this.w, exponentW);
        return d;
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
        Float4Impl ontoImpl = (Float4Impl) onto;
        float ontoX = ontoImpl.x;
        float ontoY = ontoImpl.y;
        float ontoZ = ontoImpl.z;
        float ontoW = ontoImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t9 = java.lang.Math.fma(ontoW, this.w, java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t9 = ((ontoW) * (this.w) + (((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
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
        Float4Impl ontoImpl = (Float4Impl) onto;
        float ontoX = ontoImpl.x;
        float ontoY = ontoImpl.y;
        float ontoZ = ontoImpl.z;
        float ontoW = ontoImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t9 = java.lang.Math.fma(ontoW, this.w, java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t9 = ((ontoW) * (this.w) + (((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t9 = java.lang.Math.fma(ontoW, this.w, java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t9 = ((ontoW) * (this.w) + (((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t9 = java.lang.Math.fma(ontoW, this.w, java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t9 = ((ontoW) * (this.w) + (((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            d.x = ontoX * _t9;
            d.y = ontoY * _t9;
            d.z = ontoZ * _t9;
            d.w = ontoW * _t9;
            return d;
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
        Float4Impl normalImpl = (Float4Impl) normal;
        float normalX = normalImpl.x;
        float normalY = normalImpl.y;
        float normalZ = normalImpl.z;
        float normalW = normalImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t3, this.x);
            d.y = java.lang.Math.fma(-normalY, _t3, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t3, this.z);
            d.w = java.lang.Math.fma(-normalW, _t3, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t3));
            d.y = ((this.y) - (normalY) * (_t3));
            d.z = ((this.z) - (normalZ) * (_t3));
            d.w = ((this.w) - (normalW) * (_t3));
            return d;
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
        Float4Impl normalImpl = (Float4Impl) normal;
        float normalX = normalImpl.x;
        float normalY = normalImpl.y;
        float normalZ = normalImpl.z;
        float normalW = normalImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t3, this.x);
            d.y = java.lang.Math.fma(-normalY, _t3, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t3, this.z);
            d.w = java.lang.Math.fma(-normalW, _t3, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t3));
            d.y = ((this.y) - (normalY) * (_t3));
            d.z = ((this.z) - (normalZ) * (_t3));
            d.w = ((this.w) - (normalW) * (_t3));
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t3, this.x);
            d.y = java.lang.Math.fma(-normalY, _t3, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t3, this.z);
            d.w = java.lang.Math.fma(-normalW, _t3, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t3 = ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t3));
            d.y = ((this.y) - (normalY) * (_t3));
            d.z = ((this.z) - (normalZ) * (_t3));
            d.w = ((this.w) - (normalW) * (_t3));
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t3, this.x);
            d.y = java.lang.Math.fma(-normalY, _t3, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t3, this.z);
            d.w = java.lang.Math.fma(-normalW, _t3, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t3 = ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t3));
            d.y = ((this.y) - (normalY) * (_t3));
            d.z = ((this.z) - (normalZ) * (_t3));
            d.w = ((this.w) - (normalW) * (_t3));
            return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.toRadians(this.x);
        d.y = Math.toRadians(this.y);
        d.z = Math.toRadians(this.z);
        d.w = Math.toRadians(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.toRadians(this.x);
        d.y = Math.toRadians(this.y);
        d.z = Math.toRadians(this.z);
        d.w = Math.toRadians(this.w);
        return d;
    }

    /**
     * Reflect this vector off the plane with the given normal (its component along the normal is
     * negated) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @param dest will hold the result
     * @return dest
     */
    public Float4 reflect(Float4R normal, @Mutated Float4 dest) {
        Float4Impl normalImpl = (Float4Impl) normal;
        float normalX = normalImpl.x;
        float normalY = normalImpl.y;
        float normalZ = normalImpl.z;
        float normalW = normalImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t4, this.x);
            d.y = java.lang.Math.fma(-normalY, _t4, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t4, this.z);
            d.w = java.lang.Math.fma(-normalW, _t4, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = 2.0f * ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t4));
            d.y = ((this.y) - (normalY) * (_t4));
            d.z = ((this.z) - (normalZ) * (_t4));
            d.w = ((this.w) - (normalW) * (_t4));
            return d;
        }
    }

    /**
     * Reflect this vector off the plane with the given normal (its component along the normal is
     * negated) and store the result in {@code dest}.
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
        Float4Impl normalImpl = (Float4Impl) normal;
        float normalX = normalImpl.x;
        float normalY = normalImpl.y;
        float normalZ = normalImpl.z;
        float normalW = normalImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t4, this.x);
            d.y = java.lang.Math.fma(-normalY, _t4, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t4, this.z);
            d.w = java.lang.Math.fma(-normalW, _t4, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = 2.0f * ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t4));
            d.y = ((this.y) - (normalY) * (_t4));
            d.z = ((this.z) - (normalZ) * (_t4));
            d.w = ((this.w) - (normalW) * (_t4));
            return d;
        }
    }

    /**
     * Reflect this vector off the plane with the given normal (its component along the normal is
     * negated) and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t4, this.x);
            d.y = java.lang.Math.fma(-normalY, _t4, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t4, this.z);
            d.w = java.lang.Math.fma(-normalW, _t4, this.w);
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t4 = 2.0f * ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t4));
            d.y = ((this.y) - (normalY) * (_t4));
            d.z = ((this.z) - (normalZ) * (_t4));
            d.w = ((this.w) - (normalW) * (_t4));
            return d;
        }
    }

    /**
     * Reflect this vector off the plane with the given normal (its component along the normal is
     * negated) and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = 2.0f * java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
            d.x = java.lang.Math.fma(-normalX, _t4, this.x);
            d.y = java.lang.Math.fma(-normalY, _t4, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t4, this.z);
            d.w = java.lang.Math.fma(-normalW, _t4, this.w);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t4 = 2.0f * ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
            d.x = ((this.x) - (normalX) * (_t4));
            d.y = ((this.y) - (normalY) * (_t4));
            d.z = ((this.z) - (normalZ) * (_t4));
            d.w = ((this.w) - (normalW) * (_t4));
            return d;
        }
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it is mirrored as {@code reflect} mirrors it instead of passing straight through. Negate the
     * normal for a vector leaving through the surface.
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
        Float4Impl normalImpl = (Float4Impl) normal;
        float normalX = normalImpl.x;
        float normalY = normalImpl.y;
        float normalZ = normalImpl.z;
        float normalW = normalImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return refract_mulAdd(normalX, normalY, normalZ, normalW, eta, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t4 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            d.x = java.lang.Math.fma(eta, this.x, -(normalX * _t11));
            d.y = java.lang.Math.fma(eta, this.y, -(normalY * _t11));
            d.z = java.lang.Math.fma(eta, this.z, -(normalZ * _t11));
            d.w = java.lang.Math.fma(eta, this.w, -(normalW * _t11));
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it is mirrored as {@code reflect} mirrors it instead of passing straight through. Negate the
     * normal for a vector leaving through the surface.
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
        Float4Impl normalImpl = (Float4Impl) normal;
        float normalX = normalImpl.x;
        float normalY = normalImpl.y;
        float normalZ = normalImpl.z;
        float normalW = normalImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return refract_mulAdd(normalX, normalY, normalZ, normalW, eta, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t4 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            d.x = java.lang.Math.fma(eta, this.x, -(normalX * _t11));
            d.y = java.lang.Math.fma(eta, this.y, -(normalY * _t11));
            d.z = java.lang.Math.fma(eta, this.z, -(normalZ * _t11));
            d.w = java.lang.Math.fma(eta, this.w, -(normalW * _t11));
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it is mirrored as {@code reflect} mirrors it instead of passing straight through. Negate the
     * normal for a vector leaving through the surface.
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
        if (!org.joml2.internal.FmaSwitch.ON) return refract_mulAdd(normalX, normalY, normalZ, normalW, eta, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t4 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            d.x = java.lang.Math.fma(eta, this.x, -(normalX * _t11));
            d.y = java.lang.Math.fma(eta, this.y, -(normalY * _t11));
            d.z = java.lang.Math.fma(eta, this.z, -(normalZ * _t11));
            d.w = java.lang.Math.fma(eta, this.w, -(normalW * _t11));
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
    }

    /** {@code refract} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 refract_mulAdd(float normalX, float normalY, float normalZ, float normalW, float eta, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t4 = ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            d.x = ((eta) * (this.x) - (normalX * _t11));
            d.y = ((eta) * (this.y) - (normalY * _t11));
            d.z = ((eta) * (this.z) - (normalZ * _t11));
            d.w = ((eta) * (this.w) - (normalW * _t11));
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it is mirrored as {@code reflect} mirrors it instead of passing straight through. Negate the
     * normal for a vector leaving through the surface.
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
        if (!org.joml2.internal.FmaSwitch.ON) return refract_mulAdd(normalX, normalY, normalZ, normalW, eta, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t4 = java.lang.Math.fma(normalW, this.w, java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            d.x = java.lang.Math.fma(eta, this.x, -(normalX * _t11));
            d.y = java.lang.Math.fma(eta, this.y, -(normalY * _t11));
            d.z = java.lang.Math.fma(eta, this.z, -(normalZ * _t11));
            d.w = java.lang.Math.fma(eta, this.w, -(normalW * _t11));
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
    }

    /** {@code refract} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 refract_mulAdd(float normalX, float normalY, float normalZ, float normalW, float eta, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t4 = ((normalW) * (this.w) + (((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            d.x = ((eta) * (this.x) - (normalX * _t11));
            d.y = ((eta) * (this.y) - (normalY * _t11));
            d.z = ((eta) * (this.z) - (normalZ * _t11));
            d.w = ((eta) * (this.w) - (normalW * _t11));
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.rint(this.x);
        d.y = Math.rint(this.y);
        d.z = Math.rint(this.z);
        d.w = Math.rint(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.rint(this.x);
        d.y = Math.rint(this.y);
        d.z = Math.rint(this.z);
        d.w = Math.rint(this.w);
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
    public Float4 sign(@Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
        d.w = Math.signum(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
        d.w = Math.signum(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.sin(this.x);
        d.y = Math.sin(this.y);
        d.z = Math.sin(this.z);
        d.w = Math.sin(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.sin(this.x);
        d.y = Math.sin(this.y);
        d.z = Math.sin(this.z);
        d.w = Math.sin(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.sinh(this.x);
        d.y = Math.sinh(this.y);
        d.z = Math.sinh(this.z);
        d.w = Math.sinh(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.sinh(this.x);
        d.y = Math.sinh(this.y);
        d.z = Math.sinh(this.z);
        d.w = Math.sinh(this.w);
        return d;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * {@code edge0} to 1 at {@code edge1}: 0 at or beyond {@code edge0} on the side away from
     * {@code edge1}, 1 at or beyond {@code edge1} (a falling step when {@code edge0} is the
     * greater), and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the edge the step is 0 at
     * @param edge1 the edge the step is 1 at
     * @param dest will hold the result
     * @return dest
     */
    public Float4 smoothstep(float edge0, float edge1, @Mutated Float4 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return smoothstep_mulAdd(edge0, edge1, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0) * _t0_inv));
        d.x = java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13;
        d.y = java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14;
        d.z = java.lang.Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15;
        d.w = java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        return d;
    }

    /** {@code smoothstep} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 smoothstep_mulAdd(float edge0, float edge1, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0) * _t0_inv));
        d.x = ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13;
        d.y = ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14;
        d.z = ((3.0f) - (2.0f) * (_t15)) * _t15 * _t15;
        d.w = ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16;
        return d;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * {@code edge0} to 1 at {@code edge1}: 0 at or beyond {@code edge0} on the side away from
     * {@code edge1}, 1 at or beyond {@code edge1} (a falling step when {@code edge0} is the
     * greater), and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the edge the step is 0 at
     * @param edge1 the edge the step is 1 at
     * @param dest will hold the result
     * @return dest
     */
    public Double4 smoothstep(float edge0, float edge1, @Mutated Double4 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return smoothstep_mulAdd(edge0, edge1, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0) * _t0_inv));
        d.x = java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13;
        d.y = java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14;
        d.z = java.lang.Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15;
        d.w = java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        return d;
    }

    /** {@code smoothstep} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 smoothstep_mulAdd(float edge0, float edge1, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0) * _t0_inv));
        d.x = ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13;
        d.y = ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14;
        d.z = ((3.0f) - (2.0f) * (_t15)) * _t15 * _t15;
        d.w = ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16;
        return d;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * {@code edge0} to 1 at {@code edge1}: 0 at or beyond {@code edge0} on the side away from
     * {@code edge1}, 1 at or beyond {@code edge1} (a falling step when {@code edge0} is the
     * greater), and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the edge the step is 0 at
     * @param edge1 the edge the step is 1 at
     * @param dest will hold the result
     * @return dest
     */
    public Float4 smoothstep(Float4R edge0, Float4R edge1, @Mutated Float4 dest) {
        Float4Impl edge0Impl = (Float4Impl) edge0;
        Float4Impl edge1Impl = (Float4Impl) edge1;
        float edge0X = edge0Impl.x;
        float edge0Y = edge0Impl.y;
        float edge0Z = edge0Impl.z;
        float edge0W = edge0Impl.w;
        float edge1X = edge1Impl.x;
        float edge1Y = edge1Impl.y;
        float edge1Z = edge1Impl.z;
        float edge1W = edge1Impl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return smoothstep_mulAdd(edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0W) / (edge1W - edge0W)));
        d.x = java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        d.y = java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        d.z = java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        d.w = java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return d;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * {@code edge0} to 1 at {@code edge1}: 0 at or beyond {@code edge0} on the side away from
     * {@code edge1}, 1 at or beyond {@code edge1} (a falling step when {@code edge0} is the
     * greater), and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the edge the step is 0 at
     * @param edge1 the edge the step is 1 at
     * @param dest will hold the result
     * @return dest
     */
    public Double4 smoothstep(Float4R edge0, Float4R edge1, @Mutated Double4 dest) {
        Float4Impl edge0Impl = (Float4Impl) edge0;
        Float4Impl edge1Impl = (Float4Impl) edge1;
        float edge0X = edge0Impl.x;
        float edge0Y = edge0Impl.y;
        float edge0Z = edge0Impl.z;
        float edge0W = edge0Impl.w;
        float edge1X = edge1Impl.x;
        float edge1Y = edge1Impl.y;
        float edge1Z = edge1Impl.z;
        float edge1W = edge1Impl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return smoothstep_mulAdd(edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0W) / (edge1W - edge0W)));
        d.x = java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        d.y = java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        d.z = java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        d.w = java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return d;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) to 1 at ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}, {@code edge1W}): 0 at or beyond ({@code edge0X},
     * {@code edge0Y}, {@code edge0Z}, {@code edge0W}) on the side away from ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}, {@code edge1W}), 1 at or beyond ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}, {@code edge1W}) (a falling step when ({@code edge0X},
     * {@code edge0Y}, {@code edge0Z}, {@code edge0W}) is the greater), and store the result in
     * {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return smoothstep_mulAdd(edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0W) / (edge1W - edge0W)));
        d.x = java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        d.y = java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        d.z = java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        d.w = java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return d;
    }

    /** {@code smoothstep} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 smoothstep_mulAdd(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0W) / (edge1W - edge0W)));
        d.x = ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16;
        d.y = ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17;
        d.z = ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18;
        d.w = ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19;
        return d;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) to 1 at ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}, {@code edge1W}): 0 at or beyond ({@code edge0X},
     * {@code edge0Y}, {@code edge0Z}, {@code edge0W}) on the side away from ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}, {@code edge1W}), 1 at or beyond ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}, {@code edge1W}) (a falling step when ({@code edge0X},
     * {@code edge0Y}, {@code edge0Z}, {@code edge0W}) is the greater), and store the result in
     * {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return smoothstep_mulAdd(edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0W) / (edge1W - edge0W)));
        d.x = java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        d.y = java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        d.z = java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        d.w = java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return d;
    }

    /** {@code smoothstep} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 smoothstep_mulAdd(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (this.w - edge0W) / (edge1W - edge0W)));
        d.x = ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16;
        d.y = ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17;
        d.z = ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18;
        d.w = ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19;
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = (float) java.lang.Math.sqrt(this.x);
        d.y = (float) java.lang.Math.sqrt(this.y);
        d.z = (float) java.lang.Math.sqrt(this.z);
        d.w = (float) java.lang.Math.sqrt(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = (float) java.lang.Math.sqrt(this.x);
        d.y = (float) java.lang.Math.sqrt(this.y);
        d.z = (float) java.lang.Math.sqrt(this.z);
        d.w = (float) java.lang.Math.sqrt(this.w);
        return d;
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than {@code edge}
     * and {@code 1} otherwise, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    public Float4 step(float edge, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x < edge ? 0.0f : 1.0f;
        d.y = this.y < edge ? 0.0f : 1.0f;
        d.z = this.z < edge ? 0.0f : 1.0f;
        d.w = this.w < edge ? 0.0f : 1.0f;
        return d;
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than {@code edge}
     * and {@code 1} otherwise, and store the result in {@code dest}.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x < edge ? 0.0f : 1.0f;
        d.y = this.y < edge ? 0.0f : 1.0f;
        d.z = this.z < edge ? 0.0f : 1.0f;
        d.w = this.w < edge ? 0.0f : 1.0f;
        return d;
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than the
     * corresponding component of the given edge and {@code 1} otherwise, and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    public Float4 step(Float4R edge, @Mutated Float4 dest) {
        Float4Impl edgeImpl = (Float4Impl) edge;
        float edgeX = edgeImpl.x;
        float edgeY = edgeImpl.y;
        float edgeZ = edgeImpl.z;
        float edgeW = edgeImpl.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x < edgeX ? 0.0f : 1.0f;
        d.y = this.y < edgeY ? 0.0f : 1.0f;
        d.z = this.z < edgeZ ? 0.0f : 1.0f;
        d.w = this.w < edgeW ? 0.0f : 1.0f;
        return d;
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than the
     * corresponding component of the given edge and {@code 1} otherwise, and store the result in
     * {@code dest}.
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
        Float4Impl edgeImpl = (Float4Impl) edge;
        float edgeX = edgeImpl.x;
        float edgeY = edgeImpl.y;
        float edgeZ = edgeImpl.z;
        float edgeW = edgeImpl.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x < edgeX ? 0.0f : 1.0f;
        d.y = this.y < edgeY ? 0.0f : 1.0f;
        d.z = this.z < edgeZ ? 0.0f : 1.0f;
        d.w = this.w < edgeW ? 0.0f : 1.0f;
        return d;
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than the
     * corresponding component of the given edge and {@code 1} otherwise, and store the result in
     * {@code dest}.
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
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x < edgeX ? 0.0f : 1.0f;
        d.y = this.y < edgeY ? 0.0f : 1.0f;
        d.z = this.z < edgeZ ? 0.0f : 1.0f;
        d.w = this.w < edgeW ? 0.0f : 1.0f;
        return d;
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than the
     * corresponding component of the given edge and {@code 1} otherwise, and store the result in
     * {@code dest}.
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x < edgeX ? 0.0f : 1.0f;
        d.y = this.y < edgeY ? 0.0f : 1.0f;
        d.z = this.z < edgeZ ? 0.0f : 1.0f;
        d.w = this.w < edgeW ? 0.0f : 1.0f;
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.tan(this.x);
        d.y = Math.tan(this.y);
        d.z = Math.tan(this.z);
        d.w = Math.tan(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.tan(this.x);
        d.y = Math.tan(this.y);
        d.z = Math.tan(this.z);
        d.w = Math.tan(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.tanh(this.x);
        d.y = Math.tanh(this.y);
        d.z = Math.tanh(this.z);
        d.w = Math.tanh(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.tanh(this.x);
        d.y = Math.tanh(this.y);
        d.z = Math.tanh(this.z);
        d.w = Math.tanh(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x >= 0.0f ? Math.floor(this.x) : Math.ceil(this.x);
        d.y = this.y >= 0.0f ? Math.floor(this.y) : Math.ceil(this.y);
        d.z = this.z >= 0.0f ? Math.floor(this.z) : Math.ceil(this.z);
        d.w = this.w >= 0.0f ? Math.floor(this.w) : Math.ceil(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x >= 0.0f ? Math.floor(this.x) : Math.ceil(this.x);
        d.y = this.y >= 0.0f ? Math.floor(this.y) : Math.ceil(this.y);
        d.z = this.z >= 0.0f ? Math.floor(this.z) : Math.ceil(this.z);
        d.w = this.w >= 0.0f ? Math.floor(this.w) : Math.ceil(this.w);
        return d;
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
        Float4Impl d = (Float4Impl) dest;
        d.x = Math.ulp(this.x);
        d.y = Math.ulp(this.y);
        d.z = Math.ulp(this.z);
        d.w = Math.ulp(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.ulp(this.x);
        d.y = Math.ulp(this.y);
        d.z = Math.ulp(this.z);
        d.w = Math.ulp(this.w);
        return d;
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
        Float4x4Impl matImpl = (Float4x4Impl) mat;
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(mat, dest);
        Float4Impl d = (Float4Impl) dest;
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = java.lang.Math.fma(matImpl.m03, this.w, java.lang.Math.fma(matImpl.m02, _rd2, java.lang.Math.fma(matImpl.m00, _rd0, matImpl.m01 * _rd1)));
        d.y = java.lang.Math.fma(matImpl.m13, this.w, java.lang.Math.fma(matImpl.m12, _rd2, java.lang.Math.fma(matImpl.m10, _rd0, matImpl.m11 * _rd1)));
        d.z = java.lang.Math.fma(matImpl.m23, this.w, java.lang.Math.fma(matImpl.m22, _rd2, java.lang.Math.fma(matImpl.m20, _rd0, matImpl.m21 * _rd1)));
        d.w = java.lang.Math.fma(matImpl.m33, this.w, java.lang.Math.fma(matImpl.m32, _rd2, java.lang.Math.fma(matImpl.m30, _rd0, matImpl.m31 * _rd1)));
        return d;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 preMul_mulAdd(Float4x4R mat, @Mutated Float4 dest) {
        Float4x4Impl matImpl = (Float4x4Impl) mat;
        Float4Impl d = (Float4Impl) dest;
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = ((matImpl.m03) * (this.w) + (((matImpl.m02) * (_rd2) + (((matImpl.m00) * (_rd0) + (matImpl.m01 * _rd1))))));
        d.y = ((matImpl.m13) * (this.w) + (((matImpl.m12) * (_rd2) + (((matImpl.m10) * (_rd0) + (matImpl.m11 * _rd1))))));
        d.z = ((matImpl.m23) * (this.w) + (((matImpl.m22) * (_rd2) + (((matImpl.m20) * (_rd0) + (matImpl.m21 * _rd1))))));
        d.w = ((matImpl.m33) * (this.w) + (((matImpl.m32) * (_rd2) + (((matImpl.m30) * (_rd0) + (matImpl.m31 * _rd1))))));
        return d;
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
        Float4x4Impl matImpl = (Float4x4Impl) mat;
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(mat, dest);
        Double4Impl d = (Double4Impl) dest;
        d.x = java.lang.Math.fma(matImpl.m03, this.w, java.lang.Math.fma(matImpl.m02, this.z, java.lang.Math.fma(matImpl.m00, this.x, matImpl.m01 * this.y)));
        d.y = java.lang.Math.fma(matImpl.m13, this.w, java.lang.Math.fma(matImpl.m12, this.z, java.lang.Math.fma(matImpl.m10, this.x, matImpl.m11 * this.y)));
        d.z = java.lang.Math.fma(matImpl.m23, this.w, java.lang.Math.fma(matImpl.m22, this.z, java.lang.Math.fma(matImpl.m20, this.x, matImpl.m21 * this.y)));
        d.w = java.lang.Math.fma(matImpl.m33, this.w, java.lang.Math.fma(matImpl.m32, this.z, java.lang.Math.fma(matImpl.m30, this.x, matImpl.m31 * this.y)));
        return d;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 preMul_mulAdd(Float4x4R mat, @Mutated Double4 dest) {
        Float4x4Impl matImpl = (Float4x4Impl) mat;
        Double4Impl d = (Double4Impl) dest;
        d.x = ((matImpl.m03) * (this.w) + (((matImpl.m02) * (this.z) + (((matImpl.m00) * (this.x) + (matImpl.m01 * this.y))))));
        d.y = ((matImpl.m13) * (this.w) + (((matImpl.m12) * (this.z) + (((matImpl.m10) * (this.x) + (matImpl.m11 * this.y))))));
        d.z = ((matImpl.m23) * (this.w) + (((matImpl.m22) * (this.z) + (((matImpl.m20) * (this.x) + (matImpl.m21 * this.y))))));
        d.w = ((matImpl.m33) * (this.w) + (((matImpl.m32) * (this.z) + (((matImpl.m30) * (this.x) + (matImpl.m31 * this.y))))));
        return d;
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
        FloatQuatImpl quatImpl = (FloatQuatImpl) quat;
        float quatX = quatImpl.x;
        float quatY = quatImpl.y;
        float quatZ = quatImpl.z;
        float quatW = quatImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, this.z, -(quatZ * this.y));
        d.x = java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.y)));
        d.z = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, this.z)));
        d.w = this.w;
        return d;
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
        FloatQuatImpl quatImpl = (FloatQuatImpl) quat;
        float quatX = quatImpl.x;
        float quatY = quatImpl.y;
        float quatZ = quatImpl.z;
        float quatW = quatImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, this.z, -(quatZ * this.y));
        d.x = java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.y)));
        d.z = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, this.z)));
        d.w = this.w;
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, this.z, -(quatZ * this.y));
        d.x = java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.y)));
        d.z = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, this.z)));
        d.w = this.w;
        return d;
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 rotate_mulAdd(float quatX, float quatY, float quatZ, float quatW, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t9 = 2.0f * ((quatX) * (this.y) - (quatY * this.x));
        float _t10 = 2.0f * ((quatZ) * (this.x) - (quatX * this.z));
        float _t11 = 2.0f * ((quatY) * (this.z) - (quatZ * this.y));
        d.x = ((quatY) * (_t9) + (((((quatW) * (_t11) + (this.x))) - (quatZ) * (_t10))));
        d.y = ((quatZ) * (_t11) + (((((quatW) * (_t10) + (this.y))) - (quatX) * (_t9))));
        d.z = ((quatX) * (_t10) + (((((quatW) * (_t9) + (this.z))) - (quatY) * (_t11))));
        d.w = this.w;
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, this.z, -(quatZ * this.y));
        d.x = java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.y)));
        d.z = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, this.z)));
        d.w = this.w;
        return d;
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 rotate_mulAdd(float quatX, float quatY, float quatZ, float quatW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t9 = 2.0f * ((quatX) * (this.y) - (quatY * this.x));
        float _t10 = 2.0f * ((quatZ) * (this.x) - (quatX * this.z));
        float _t11 = 2.0f * ((quatY) * (this.z) - (quatZ * this.y));
        d.x = ((quatY) * (_t9) + (((((quatW) * (_t11) + (this.x))) - (quatZ) * (_t10))));
        d.y = ((quatZ) * (_t11) + (((((quatW) * (_t10) + (this.y))) - (quatX) * (_t9))));
        d.z = ((quatX) * (_t10) + (((((quatW) * (_t9) + (this.z))) - (quatY) * (_t11))));
        d.w = this.w;
        return d;
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
        Float3Impl axisImpl = (Float3Impl) axis;
        return rotateAxis(angle, axisImpl.x, axisImpl.y, axisImpl.z, dest);
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
        Float3Impl axisImpl = (Float3Impl) axis;
        return rotateAxis(angle, axisImpl.x, axisImpl.y, axisImpl.z, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        float _rd0 = this.x;
        float _rd1 = this.y;
        d.x = java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_rd0, _t0, java.lang.Math.fma(axisY, this.z, -(axisZ * _rd1)) * _t1));
        d.y = java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_rd1, _t0, java.lang.Math.fma(axisZ, _rd0, -(axisX * this.z)) * _t1));
        d.z = java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(this.z, _t0, java.lang.Math.fma(axisX, _rd1, -(axisY * _rd0)) * _t1));
        d.w = this.w;
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        float _rd0 = this.x;
        float _rd1 = this.y;
        d.x = ((_t2) * (axisX * _t5) + (((_rd0) * (_t0) + (((axisY) * (this.z) - (axisZ * _rd1)) * _t1))));
        d.y = ((_t2) * (axisY * _t5) + (((_rd1) * (_t0) + (((axisZ) * (_rd0) - (axisX * this.z)) * _t1))));
        d.z = ((_t2) * (axisZ * _t5) + (((this.z) * (_t0) + (((axisX) * (_rd1) - (axisY * _rd0)) * _t1))));
        d.w = this.w;
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        d.x = java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(this.x, _t0, java.lang.Math.fma(axisY, this.z, -(axisZ * this.y)) * _t1));
        d.y = java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(this.y, _t0, java.lang.Math.fma(axisZ, this.x, -(axisX * this.z)) * _t1));
        d.z = java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(this.z, _t0, java.lang.Math.fma(axisX, this.y, -(axisY * this.x)) * _t1));
        d.w = this.w;
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        d.x = ((_t2) * (axisX * _t5) + (((this.x) * (_t0) + (((axisY) * (this.z) - (axisZ * this.y)) * _t1))));
        d.y = ((_t2) * (axisY * _t5) + (((this.y) * (_t0) + (((axisZ) * (this.x) - (axisX * this.z)) * _t1))));
        d.z = ((_t2) * (axisZ * _t5) + (((this.z) * (_t0) + (((axisX) * (this.y) - (axisY * this.x)) * _t1))));
        d.w = this.w;
        return d;
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
        FloatQuatImpl quatImpl = (FloatQuatImpl) quat;
        float quatX = quatImpl.x;
        float quatY = quatImpl.y;
        float quatZ = quatImpl.z;
        float quatW = quatImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return rotateInverse_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, this.y, -(quatY * this.z));
        d.x = java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, this.y)));
        d.z = java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.z)));
        d.w = this.w;
        return d;
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
        FloatQuatImpl quatImpl = (FloatQuatImpl) quat;
        float quatX = quatImpl.x;
        float quatY = quatImpl.y;
        float quatZ = quatImpl.z;
        float quatW = quatImpl.w;
        if (!org.joml2.internal.FmaSwitch.ON) return rotateInverse_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, this.y, -(quatY * this.z));
        d.x = java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, this.y)));
        d.z = java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.z)));
        d.w = this.w;
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateInverse_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Float4Impl d = (Float4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, this.y, -(quatY * this.z));
        d.x = java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, this.y)));
        d.z = java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.z)));
        d.w = this.w;
        return d;
    }

    /** {@code rotateInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4 rotateInverse_mulAdd(float quatX, float quatY, float quatZ, float quatW, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _t9 = 2.0f * ((quatX) * (this.z) - (quatZ * this.x));
        float _t10 = 2.0f * ((quatY) * (this.x) - (quatX * this.y));
        float _t11 = 2.0f * ((quatZ) * (this.y) - (quatY * this.z));
        d.x = ((quatZ) * (_t9) + (((((quatW) * (_t11) + (this.x))) - (quatY) * (_t10))));
        d.y = ((quatX) * (_t10) + (((((quatW) * (_t9) + (this.y))) - (quatZ) * (_t11))));
        d.z = ((quatY) * (_t11) + (((((quatW) * (_t10) + (this.z))) - (quatX) * (_t9))));
        d.w = this.w;
        return d;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateInverse_mulAdd(quatX, quatY, quatZ, quatW, dest);
        Double4Impl d = (Double4Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, this.y, -(quatY * this.z));
        d.x = java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, this.x)));
        d.y = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, this.y)));
        d.z = java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.z)));
        d.w = this.w;
        return d;
    }

    /** {@code rotateInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4 rotateInverse_mulAdd(float quatX, float quatY, float quatZ, float quatW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _t9 = 2.0f * ((quatX) * (this.z) - (quatZ * this.x));
        float _t10 = 2.0f * ((quatY) * (this.x) - (quatX * this.y));
        float _t11 = 2.0f * ((quatZ) * (this.y) - (quatY * this.z));
        d.x = ((quatZ) * (_t9) + (((((quatW) * (_t11) + (this.x))) - (quatY) * (_t10))));
        d.y = ((quatX) * (_t10) + (((((quatW) * (_t9) + (this.y))) - (quatZ) * (_t11))));
        d.z = ((quatY) * (_t11) + (((((quatW) * (_t10) + (this.z))) - (quatX) * (_t9))));
        d.w = this.w;
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.y;
            d.x = this.x;
            d.y = java.lang.Math.fma(_rd0, _t0, -(this.z * _t1));
            d.z = java.lang.Math.fma(_rd0, _t1, this.z * _t0);
            d.w = this.w;
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.y;
            d.x = this.x;
            d.y = ((_rd0) * (_t0) - (this.z * _t1));
            d.z = ((_rd0) * (_t1) + (this.z * _t0));
            d.w = this.w;
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.x = this.x;
            d.y = java.lang.Math.fma(this.y, _t0, -(this.z * _t1));
            d.z = java.lang.Math.fma(this.y, _t1, this.z * _t0);
            d.w = this.w;
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.x = this.x;
            d.y = ((this.y) * (_t0) - (this.z * _t1));
            d.z = ((this.y) * (_t1) + (this.z * _t0));
            d.w = this.w;
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.x;
            d.x = java.lang.Math.fma(_rd0, _t0, this.z * _t1);
            d.y = this.y;
            d.z = java.lang.Math.fma(this.z, _t0, -(_rd0 * _t1));
            d.w = this.w;
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.x;
            d.x = ((_rd0) * (_t0) + (this.z * _t1));
            d.y = this.y;
            d.z = ((this.z) * (_t0) - (_rd0 * _t1));
            d.w = this.w;
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.x = java.lang.Math.fma(this.x, _t0, this.z * _t1);
            d.y = this.y;
            d.z = java.lang.Math.fma(this.z, _t0, -(this.x * _t1));
            d.w = this.w;
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.x = ((this.x) * (_t0) + (this.z * _t1));
            d.y = this.y;
            d.z = ((this.z) * (_t0) - (this.x * _t1));
            d.w = this.w;
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.x;
            d.x = java.lang.Math.fma(_rd0, _t0, -(this.y * _t1));
            d.y = java.lang.Math.fma(_rd0, _t1, this.y * _t0);
            d.z = this.z;
            d.w = this.w;
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.x;
            d.x = ((_rd0) * (_t0) - (this.y * _t1));
            d.y = ((_rd0) * (_t1) + (this.y * _t0));
            d.z = this.z;
            d.w = this.w;
            return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.x = java.lang.Math.fma(this.x, _t0, -(this.y * _t1));
            d.y = java.lang.Math.fma(this.x, _t1, this.y * _t0);
            d.z = this.z;
            d.w = this.w;
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.x = ((this.x) * (_t0) - (this.y * _t1));
            d.y = ((this.x) * (_t1) + (this.y * _t0));
            d.z = this.z;
            d.w = this.w;
            return d;
        }
    }

    public float x() { return this.x; }
    public float y() { return this.y; }
    public float z() { return this.z; }
    public float w() { return this.w; }

    public Float2 xx(@Mutated Float2 dest) {
        float _v0 = this.x;
        Float2Impl d = (Float2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Float2 xy(@Mutated Float2 dest) {
        float _v1 = this.y;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Float2 xz(@Mutated Float2 dest) {
        float _v1 = this.z;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Float2 xw(@Mutated Float2 dest) {
        float _v1 = this.w;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Float2 yx(@Mutated Float2 dest) {
        float _v1 = this.x;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Float2 yy(@Mutated Float2 dest) {
        float _v0 = this.y;
        Float2Impl d = (Float2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Float2 yz(@Mutated Float2 dest) {
        float _v1 = this.z;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Float2 yw(@Mutated Float2 dest) {
        float _v1 = this.w;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Float2 zx(@Mutated Float2 dest) {
        float _v1 = this.x;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Float2 zy(@Mutated Float2 dest) {
        float _v1 = this.y;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Float2 zz(@Mutated Float2 dest) {
        float _v0 = this.z;
        Float2Impl d = (Float2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Float2 zw(@Mutated Float2 dest) {
        float _v1 = this.w;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Float2 wx(@Mutated Float2 dest) {
        float _v1 = this.x;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Float2 wy(@Mutated Float2 dest) {
        float _v1 = this.y;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Float2 wz(@Mutated Float2 dest) {
        float _v1 = this.z;
        Float2Impl d = (Float2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Float2 ww(@Mutated Float2 dest) {
        float _v0 = this.w;
        Float2Impl d = (Float2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Float3 xxx(@Mutated Float3 dest) {
        float _v0 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Float3 xxy(@Mutated Float3 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 xxz(@Mutated Float3 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 xxw(@Mutated Float3 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 xyx(@Mutated Float3 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 xyy(@Mutated Float3 dest) {
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 xyz(@Mutated Float3 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 xyw(@Mutated Float3 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 xzx(@Mutated Float3 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 xzy(@Mutated Float3 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 xzz(@Mutated Float3 dest) {
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 xzw(@Mutated Float3 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 xwx(@Mutated Float3 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 xwy(@Mutated Float3 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 xwz(@Mutated Float3 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 xww(@Mutated Float3 dest) {
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 yxx(@Mutated Float3 dest) {
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 yxy(@Mutated Float3 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 yxz(@Mutated Float3 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 yxw(@Mutated Float3 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 yyx(@Mutated Float3 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 yyy(@Mutated Float3 dest) {
        float _v0 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Float3 yyz(@Mutated Float3 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 yyw(@Mutated Float3 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 yzx(@Mutated Float3 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 yzy(@Mutated Float3 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 yzz(@Mutated Float3 dest) {
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 yzw(@Mutated Float3 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 ywx(@Mutated Float3 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 ywy(@Mutated Float3 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 ywz(@Mutated Float3 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 yww(@Mutated Float3 dest) {
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 zxx(@Mutated Float3 dest) {
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 zxy(@Mutated Float3 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 zxz(@Mutated Float3 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 zxw(@Mutated Float3 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 zyx(@Mutated Float3 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 zyy(@Mutated Float3 dest) {
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 zyz(@Mutated Float3 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 zyw(@Mutated Float3 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 zzx(@Mutated Float3 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 zzy(@Mutated Float3 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 zzz(@Mutated Float3 dest) {
        float _v0 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Float3 zzw(@Mutated Float3 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 zwx(@Mutated Float3 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 zwy(@Mutated Float3 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 zwz(@Mutated Float3 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 zww(@Mutated Float3 dest) {
        float _v1 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 wxx(@Mutated Float3 dest) {
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 wxy(@Mutated Float3 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 wxz(@Mutated Float3 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 wxw(@Mutated Float3 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 wyx(@Mutated Float3 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 wyy(@Mutated Float3 dest) {
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 wyz(@Mutated Float3 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 wyw(@Mutated Float3 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 wzx(@Mutated Float3 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 wzy(@Mutated Float3 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Float3 wzz(@Mutated Float3 dest) {
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Float3 wzw(@Mutated Float3 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Float3 wwx(@Mutated Float3 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 wwy(@Mutated Float3 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 wwz(@Mutated Float3 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Float3 www(@Mutated Float3 dest) {
        float _v0 = this.w;
        Float3Impl d = (Float3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Float4 xxxx(@Mutated Float4 dest) {
        float _v0 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 xxxy(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 xxxz(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 xxxw(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 xxyx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 xxyy(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 xxyz(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xxyw(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xxzx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 xxzy(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xxzz(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 xxzw(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xxwx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 xxwy(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xxwz(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xxww(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 xyxx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 xyxy(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 xyxz(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 xyxw(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 xyyx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 xyyy(@Mutated Float4 dest) {
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 xyyz(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xyyw(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xyzx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 xyzy(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 xyzz(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 xyzw(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        float _v3 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 xywx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 xywy(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 xywz(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        float _v3 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 xyww(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 xzxx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 xzxy(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 xzxz(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 xzxw(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 xzyx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 xzyy(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 xzyz(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 xzyw(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        float _v3 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 xzzx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 xzzy(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xzzz(@Mutated Float4 dest) {
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 xzzw(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xzwx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 xzwy(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        float _v3 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 xzwz(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 xzww(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 xwxx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 xwxy(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 xwxz(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 xwxw(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 xwyx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 xwyy(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 xwyz(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        float _v3 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 xwyw(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 xwzx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 xwzy(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        float _v3 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 xwzz(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 xwzw(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 xwwx(@Mutated Float4 dest) {
        float _v0 = this.x;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 xwwy(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xwwz(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 xwww(@Mutated Float4 dest) {
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 yxxx(@Mutated Float4 dest) {
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 yxxy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 yxxz(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yxxw(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yxyx(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 yxyy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 yxyz(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 yxyw(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 yxzx(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 yxzy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 yxzz(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 yxzw(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        float _v3 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 yxwx(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 yxwy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 yxwz(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        float _v3 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 yxww(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 yyxx(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 yyxy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 yyxz(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yyxw(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yyyx(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 yyyy(@Mutated Float4 dest) {
        float _v0 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 yyyz(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 yyyw(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 yyzx(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yyzy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 yyzz(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 yyzw(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yywx(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yywy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 yywz(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yyww(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 yzxx(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 yzxy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 yzxz(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 yzxw(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        float _v3 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 yzyx(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 yzyy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 yzyz(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 yzyw(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 yzzx(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yzzy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 yzzz(@Mutated Float4 dest) {
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 yzzw(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 yzwx(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        float _v3 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 yzwy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 yzwz(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 yzww(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 ywxx(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 ywxy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 ywxz(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        float _v3 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 ywxw(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 ywyx(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 ywyy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 ywyz(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 ywyw(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 ywzx(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        float _v3 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 ywzy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 ywzz(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 ywzw(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 ywwx(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 ywwy(@Mutated Float4 dest) {
        float _v0 = this.y;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 ywwz(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 ywww(@Mutated Float4 dest) {
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 zxxx(@Mutated Float4 dest) {
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 zxxy(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zxxz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 zxxw(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zxyx(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 zxyy(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 zxyz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 zxyw(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        float _v3 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 zxzx(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 zxzy(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 zxzz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 zxzw(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 zxwx(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 zxwy(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        float _v3 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 zxwz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 zxww(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 zyxx(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 zyxy(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 zyxz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 zyxw(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        float _v3 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 zyyx(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zyyy(@Mutated Float4 dest) {
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 zyyz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 zyyw(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zyzx(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 zyzy(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 zyzz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 zyzw(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 zywx(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        float _v3 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 zywy(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 zywz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 zyww(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 zzxx(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 zzxy(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zzxz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 zzxw(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zzyx(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zzyy(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 zzyz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 zzyw(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        float _v2 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zzzx(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 zzzy(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 zzzz(@Mutated Float4 dest) {
        float _v0 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 zzzw(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 zzwx(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zzwy(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zzwz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 zzww(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 zwxx(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 zwxy(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        float _v3 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 zwxz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 zwxw(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 zwyx(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        float _v3 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 zwyy(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 zwyz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 zwyw(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 zwzx(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 zwzy(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 zwzz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 zwzw(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 zwwx(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zwwy(@Mutated Float4 dest) {
        float _v1 = this.w;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 zwwz(@Mutated Float4 dest) {
        float _v0 = this.z;
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 zwww(@Mutated Float4 dest) {
        float _v1 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 wxxx(@Mutated Float4 dest) {
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 wxxy(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wxxz(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wxxw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 wxyx(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 wxyy(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 wxyz(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.y;
        float _v3 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 wxyw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 wxzx(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 wxzy(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        float _v3 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 wxzz(@Mutated Float4 dest) {
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 wxzw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 wxwx(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 wxwy(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 wxwz(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 wxww(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 wyxx(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 wyxy(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 wyxz(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        float _v3 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 wyxw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 wyyx(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wyyy(@Mutated Float4 dest) {
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 wyyz(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wyyw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 wyzx(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        float _v3 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 wyzy(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 wyzz(@Mutated Float4 dest) {
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 wyzw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 wywx(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 wywy(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 wywz(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 wyww(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 wzxx(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 wzxy(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        float _v3 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 wzxz(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 wzxw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 wzyx(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        float _v3 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Float4 wzyy(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Float4 wzyz(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Float4 wzyw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Float4 wzzx(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wzzy(@Mutated Float4 dest) {
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wzzz(@Mutated Float4 dest) {
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 wzzw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 wzwx(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 wzwy(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Float4 wzwz(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 wzww(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Float4 wwxx(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 wwxy(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wwxz(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wwxw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 wwyx(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wwyy(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 wwyz(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        float _v2 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wwyw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 wwzx(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        float _v2 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wwzy(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        float _v2 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Float4 wwzz(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Float4 wwzw(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Float4 wwwx(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.x;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 wwwy(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.y;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 wwwz(@Mutated Float4 dest) {
        float _v0 = this.w;
        float _v1 = this.z;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Float4 wwww(@Mutated Float4 dest) {
        float _v0 = this.w;
        Float4Impl d = (Float4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Float4(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float4Impl)) return false;
        Float4Impl o = (Float4Impl) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(o.x)
            && Float.floatToIntBits(y) == Float.floatToIntBits(o.y)
            && Float.floatToIntBits(z) == Float.floatToIntBits(o.z)
            && Float.floatToIntBits(w) == Float.floatToIntBits(o.w);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(x);
        h = 31 * h + Float.floatToIntBits(y);
        h = 31 * h + Float.floatToIntBits(z);
        h = 31 * h + Float.floatToIntBits(w);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(x)
            && Float.isFinite(y)
            && Float.isFinite(z)
            && Float.isFinite(w);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(x)
            || Float.isNaN(y)
            || Float.isNaN(z)
            || Float.isNaN(w);
    }

    @Override public boolean equalsEpsilon(Float4R other, float epsilon) {
        Float4Impl otherImpl = (Float4Impl) other;
        return java.lang.Math.abs(x - otherImpl.x) <= epsilon
            && java.lang.Math.abs(y - otherImpl.y) <= epsilon
            && java.lang.Math.abs(z - otherImpl.z) <= epsilon
            && java.lang.Math.abs(w - otherImpl.w) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }

    @Mutated public Float4 load(float[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
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

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public Float4 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public Float4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public Float4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public Float4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }

    @Mutated public Float4 load(double[] src, int offset) {
        this.x = (float) src[offset];
        this.y = (float) src[offset + 1];
        this.z = (float) src[offset + 2];
        this.w = (float) src[offset + 3];
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

    public ByteBuffer storeDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public Float4 loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float4 loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }

    @Mutated public Float4 loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public Float4 storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    @Mutated public Float4 loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
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
}
