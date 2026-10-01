// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Double4Impl implements Double4 {

    public double x;
    public double y;
    public double z;
    public double w;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4SegOpsUnsafe()
                        : new Double4SegOpsMS();
        static final Double4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4BbOpsUnsafe()
                        : new Double4BbOpsApi();
        static final Double4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4RawOpsUnsafe()
                        : new Double4RawOpsApi();
    }

    public Double4Impl() {
        w = 1;
    }

    public Double4Impl(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Double4Impl(Double4R src) {
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
    public Double4 add(Double4R other, @Mutated Double4 dest) {
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
    public Double4 add(double otherX, double otherY, double otherZ, double otherW, @Mutated Double4 dest) {
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
    public Double4 div(double scalar, @Mutated Double4 dest) {
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
    public Double4 div(Double4R other, @Mutated Double4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 div(double otherX, double otherY, double otherZ, double otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x / otherX;
        d.y = this.y / otherY;
        d.z = this.z / otherZ;
        d.w = this.w / otherW;
        return d;
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
    public Double4 fma(double b, Double4R c, @Mutated Double4 dest) {
        return fma(b, c.x(), c.y(), c.z(), c.w(), dest);
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
    public Double4 fma(double b, double cX, double cY, double cZ, double cW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.fma(this.x, b, cX);
        d.y = Math.fma(this.y, b, cY);
        d.z = Math.fma(this.z, b, cZ);
        d.w = Math.fma(this.w, b, cW);
        return d;
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
    public Double4 fma(Double4R b, Double4R c, @Mutated Double4 dest) {
        return fma(b.x(), b.y(), b.z(), b.w(), c.x(), c.y(), c.z(), c.w(), dest);
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
    public Double4 fma(double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.fma(this.x, bX, cX);
        d.y = Math.fma(this.y, bY, cY);
        d.z = Math.fma(this.z, bZ, cZ);
        d.w = Math.fma(this.w, bW, cW);
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
    public Double4 mul(double scalar, @Mutated Double4 dest) {
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
    public Double4 mul(Double4R other, @Mutated Double4 dest) {
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
    public Double4 mul(double otherX, double otherY, double otherZ, double otherW, @Mutated Double4 dest) {
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
    public Double4 sub(Double4R other, @Mutated Double4 dest) {
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
    public Double4 sub(double otherX, double otherY, double otherZ, double otherW, @Mutated Double4 dest) {
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
    @Mutated public Double4 makeUniformDirection(double u, double v, double w) {
        double _t0 = Math.sqrt(u);
        double _t1 = v * 6.283185307179586;
        double _t3 = w * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sqrt(1.0 - u);
        double _t6 = Math.sin(_t3);
        this.x = Math.cosFromSin(_t4, _t1) * _t5;
        this.y = _t4 * _t5;
        this.z = Math.cosFromSin(_t6, _t3) * _t0;
        this.w = _t6 * _t0;
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
    public @Mutated Double4 set(Double4R v) {
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
    @Mutated public Double4 set(double vX, double vY, double vZ, double vW) {
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
    public Double4 set(double s, @Mutated Double4 dest) {
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
        d.x = (float) (this.x);
        d.y = (float) (this.y);
        d.z = (float) (this.z);
        d.w = (float) (this.w);
        return d;
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
        Byte4Impl d = (Byte4Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toByte(dest); }
            case FLOOR -> {
                d.x = (byte) Math.floor(this.x);
                d.y = (byte) Math.floor(this.y);
                d.z = (byte) Math.floor(this.z);
                d.w = (byte) Math.floor(this.w);
            }
            case CEILING -> {
                d.x = (byte) Math.ceil(this.x);
                d.y = (byte) Math.ceil(this.y);
                d.z = (byte) Math.ceil(this.z);
                d.w = (byte) Math.ceil(this.w);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = (byte) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (byte) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.y)));
                d.z = (byte) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.z)));
                d.w = (byte) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.w)));
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (byte) (Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (byte) (Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (byte) (Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
                d.w = (byte) (Math.abs(this.w - Math.rint(this.w)) == 0.5 ? this.w + Math.copySign(0.5, this.w) : Math.rint(this.w));
            }
            case HALF_EVEN -> {
                d.x = (byte) Math.rint(this.x);
                d.y = (byte) Math.rint(this.y);
                d.z = (byte) Math.rint(this.z);
                d.w = (byte) Math.rint(this.w);
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
        Short4Impl d = (Short4Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toShort(dest); }
            case FLOOR -> {
                d.x = (short) Math.floor(this.x);
                d.y = (short) Math.floor(this.y);
                d.z = (short) Math.floor(this.z);
                d.w = (short) Math.floor(this.w);
            }
            case CEILING -> {
                d.x = (short) Math.ceil(this.x);
                d.y = (short) Math.ceil(this.y);
                d.z = (short) Math.ceil(this.z);
                d.w = (short) Math.ceil(this.w);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = (short) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (short) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.y)));
                d.z = (short) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.z)));
                d.w = (short) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.w)));
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (short) (Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (short) (Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (short) (Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
                d.w = (short) (Math.abs(this.w - Math.rint(this.w)) == 0.5 ? this.w + Math.copySign(0.5, this.w) : Math.rint(this.w));
            }
            case HALF_EVEN -> {
                d.x = (short) Math.rint(this.x);
                d.y = (short) Math.rint(this.y);
                d.z = (short) Math.rint(this.z);
                d.w = (short) Math.rint(this.w);
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
        Int4Impl d = (Int4Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toInt(dest); }
            case FLOOR -> {
                d.x = (int) Math.floor(this.x);
                d.y = (int) Math.floor(this.y);
                d.z = (int) Math.floor(this.z);
                d.w = (int) Math.floor(this.w);
            }
            case CEILING -> {
                d.x = (int) Math.ceil(this.x);
                d.y = (int) Math.ceil(this.y);
                d.z = (int) Math.ceil(this.z);
                d.w = (int) Math.ceil(this.w);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.y)));
                d.z = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.z)));
                d.w = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(this.w)));
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (int) (Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (int) (Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (int) (Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
                d.w = (int) (Math.abs(this.w - Math.rint(this.w)) == 0.5 ? this.w + Math.copySign(0.5, this.w) : Math.rint(this.w));
            }
            case HALF_EVEN -> {
                d.x = (int) Math.rint(this.x);
                d.y = (int) Math.rint(this.y);
                d.z = (int) Math.rint(this.z);
                d.w = (int) Math.rint(this.w);
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
        Long4Impl d = (Long4Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toLong(dest); }
            case FLOOR -> {
                d.x = (long) Math.floor(this.x);
                d.y = (long) Math.floor(this.y);
                d.z = (long) Math.floor(this.z);
                d.w = (long) Math.floor(this.w);
            }
            case CEILING -> {
                d.x = (long) Math.ceil(this.x);
                d.y = (long) Math.ceil(this.y);
                d.z = (long) Math.ceil(this.z);
                d.w = (long) Math.ceil(this.w);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = Math.round(this.x);
                d.y = Math.round(this.y);
                d.z = Math.round(this.z);
                d.w = Math.round(this.w);
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (long) (Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (long) (Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (long) (Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
                d.w = (long) (Math.abs(this.w - Math.rint(this.w)) == 0.5 ? this.w + Math.copySign(0.5, this.w) : Math.rint(this.w));
            }
            case HALF_EVEN -> {
                d.x = (long) Math.rint(this.x);
                d.y = (long) Math.rint(this.y);
                d.z = (long) Math.rint(this.z);
                d.w = (long) Math.rint(this.w);
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
    @Mutated public Double4 makeZero() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 0.0;
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
    public Double4 bezier(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        return bezier(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), p3.x(), p3.y(), p3.z(), p3.w(), t, dest);
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
    public Double4 bezier(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        d.x = Math.fma(p1X, _t7, this.x * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        d.y = Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        d.z = Math.fma(p1Z, _t7, this.z * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        d.w = Math.fma(p1W, _t7, this.w * _t8) + Math.fma(p2W, _t6, p3W * _t2);
        return d;
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
    public Double4 bezier2(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
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
    public Double4 bezier2(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        d.x = Math.fma(p2X, _t0, Math.fma(p1X, _t3, this.x * _t4));
        d.y = Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, this.y * _t4));
        d.z = Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, this.z * _t4));
        d.w = Math.fma(p2W, _t0, Math.fma(p1W, _t3, this.w * _t4));
        return d;
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
    public Double4 bezier2Tangent(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
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
    public Double4 bezier2Tangent(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        d.x = Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1);
        d.y = Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1);
        d.z = Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1);
        d.w = Math.fma(p1W - this.w, _t2, (p2W - p1W) * _t1);
        return d;
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
    public Double4 bezierTangent(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        return bezierTangent(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), p3.x(), p3.y(), p3.z(), p3.w(), t, dest);
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
    public Double4 bezierTangent(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        d.x = Math.fma(p3X - p2X, _t2, Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5));
        d.y = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5));
        d.z = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5));
        d.w = Math.fma(p3W - p2W, _t2, Math.fma(p1W - this.w, _t6, (p2W - p1W) * _t5));
        return d;
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
    public Double4 catmullRom(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        return catmullRom(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), p3.x(), p3.y(), p3.z(), p3.w(), t, dest);
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
    public Double4 catmullRom(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = t * t;
        double _t1 = t * _t0;
        d.x = 0.5 * (Math.fma(2.0, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)) * _t1));
        d.y = 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)) * _t1));
        d.z = 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - this.z)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, this.z, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - this.z)) * _t1));
        return catmullRom_scde09e05_1(p1W, p2W, p3W, t, d, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom}, split to fit the inline budget; reached only through it. */
    private Double4 catmullRom_scde09e05_1(double p1W, double p2W, double p3W, double t, Double4Impl d, double _t0, double _t1) {
        d.w = 0.5 * (Math.fma(2.0, p1W, t * (p2W - this.w)) + Math.fma(Math.fma(-5.0, p1W, Math.fma(2.0, this.w, Math.fma(4.0, p2W, -p3W))), _t0, Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - this.w)) * _t1));
        return d;
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
    public Double4 catmullRomTangent(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        return catmullRomTangent(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), p3.x(), p3.y(), p3.z(), p3.w(), t, dest);
    }

    /** Private store group 0 of {@code catmullRomTangent}: computes and stores it; reached only through it. */
    private void catmullRomTangent_s61a63d9e_c0(Double4Impl _dst, double t, double p1X, double _r0, double p2X, double p3X, double _t0, double p1Y, double _r1, double p2Y, double p3Y, double p1Z, double _r2, double p2Z, double p3Z, double p1W, double _r3, double p2W, double p3W) {
        _dst.x = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _r0, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _r0)), _t0, p2X - _r0));
        _dst.y = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _r1, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _r1)), _t0, p2Y - _r1));
        _dst.z = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, _r2, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _r2)), _t0, p2Z - _r2));
        _dst.w = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1W, Math.fma(2.0, _r3, Math.fma(4.0, p2W, -p3W))), Math.fma(3.0 * Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _r3)), _t0, p2W - _r3));
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
    public Double4 catmullRomTangent(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _r0 = this.x;
        double _r1 = this.y;
        double _r2 = this.z;
        double _r3 = this.w;
        catmullRomTangent_s61a63d9e_c0(d, t, p1X, _r0, p2X, p3X, t * t, p1Y, _r1, p2Y, p3Y, p1Z, _r2, p2Z, p3Z, p1W, _r3, p2W, p3W);
        return d;
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
    public Double4 hermite(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
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
    public Double4 hermite(double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        d.x = Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        d.y = Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        d.z = Math.fma(this.z, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        d.w = Math.fma(this.w, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9);
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
    public Double4 hermiteTangent(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
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
    public Double4 hermiteTangent(double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        d.x = Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        d.y = Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        d.z = Math.fma(this.z, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        d.w = Math.fma(this.w, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7);
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
    public Double4 lerp(Double4R other, double t, @Mutated Double4 dest) {
        return lerp(other.x(), other.y(), other.z(), other.w(), t, dest);
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
    public Double4 lerp(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.fma(t, otherX - this.x, this.x);
        d.y = Math.fma(t, otherY - this.y, this.y);
        d.z = Math.fma(t, otherZ - this.z, this.z);
        d.w = Math.fma(t, otherW - this.w, this.w);
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
    public Double4 lerp(Double4R other, Double4R t, @Mutated Double4 dest) {
        return lerp(other.x(), other.y(), other.z(), other.w(), t.x(), t.y(), t.z(), t.w(), dest);
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
    public Double4 lerp(double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.fma(tX, otherX - this.x, this.x);
        d.y = Math.fma(tY, otherY - this.y, this.y);
        d.z = Math.fma(tZ, otherZ - this.z, this.z);
        d.w = Math.fma(tW, otherW - this.w, this.w);
        return d;
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
     * the {@code double} range, they are first scaled exactly by powers of two.
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
    public Double4 slerp(Double4R other, double t, @Mutated Double4 dest) {
        return slerp(other.x(), other.y(), other.z(), other.w(), t, dest);
    }

    /** Private store group 0 of {@code slerp}: computes and stores it; reached only through it. */
    private void slerp_s5e1be5aa_c0(Double4Impl _dst, double _t16, double _t66, double _sp0, double _t51, double _t24, double _t52, double _t21, double _t50, double _t19, double _t49) {
        _dst.x = Math.fma(_t16, _t66, _sp0 * _t51);
        _dst.y = Math.fma(_t24, _t66, _sp0 * _t52);
        _dst.z = Math.fma(_t21, _t66, _sp0 * _t50);
        _dst.w = Math.fma(_t19, _t66, _sp0 * _t49);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private void slerp_s5e1be5aa_tail(Double4Impl _dst, double t, double _t10, double _t9, double _t57, double _t31, double _t16, double _t51, double _t24, double _t52, double _t21, double _t50, double _t19, double _t49) {
        double _t28 = t * Math.sqrt(_t10) + (1.0 - t) * Math.sqrt(_t9);
        double _t61 = t * Math.atan2(Math.sqrt(_t57), _t31);
        slerp_s5e1be5aa_c0(_dst, _t16, _t28 * Math.cos(_t61), _t28 * Math.sin(_t61) * (1.0 / Math.sqrt(_t57)), _t51, _t24, _t52, _t21, _t50, _t19, _t49);
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
     * the {@code double} range, they are first scaled exactly by powers of two.
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
    public Double4 slerp(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated Double4 dest) {
        double _r0 = this.w;
        double _r1 = this.z;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t9 = Math.fma(_r0, _r0, Math.fma(_r1, _r1, Math.fma(_r2, _r2, _r3 * _r3)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t, dest);
        double _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t, dest);
        double _t11 = (1.0 / Math.sqrt(_t9));
        double _t14 = (1.0 / Math.sqrt(_t10));
        double _t16 = _r2 * _t11;
        double _t19 = _r0 * _t11;
        double _t21 = _r1 * _t11;
        double _t24 = _r3 * _t11;
        double _t31 = Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_s604033a3_1(otherX, otherY, otherZ, otherW, t, dest, (Double4Impl) dest, _t9, _t10, _t14, _t16, _t19, _t21, _t24, _t31, Math.fma(otherW, _t14, -(_t31 * _t19)), Math.fma(otherZ, _t14, -(_t31 * _t21)), Math.fma(otherX, _t14, -(_t31 * _t16)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_s604033a3_1(double otherX, double otherY, double otherZ, double otherW, double t, Double4 dest, Double4Impl d, double _t9, double _t10, double _t14, double _t16, double _t19, double _t21, double _t24, double _t31, double _t40, double _t41, double _t42) {
        double _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = Math.fma(_t48, _t19, _t40);
        double _t50 = Math.fma(_t48, _t21, _t41);
        double _t51 = Math.fma(_t48, _t16, _t42);
        double _t52 = Math.fma(_t48, _t24, _t43);
        double _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t, dest);
        slerp_s5e1be5aa_tail(d, t, _t10, _t9, _t57, _t31, _t16, _t51, _t24, _t52, _t21, _t50, _t19, _t49);
        return d;
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate(Double4R other, double t, @Mutated Double4 dest) {
        return slerp_degenerate(other.x(), other.y(), other.z(), other.w(), t, dest);
    }

    /** Private store group 0 of {@code slerp_degenerate}: computes and stores it; reached only through it. */
    private void slerp_degenerate_s5e1be5aa_c0(Double4Impl _dst, double _t58, double _t114, double _t63, double _t102, double _t50, double _t122, double _t115, double _t47, double _t25_inv, double t, double otherX, double _r2, double _t120, double _t49, double otherY, double _r3, double _t51, double _t123, double _t45, double otherZ, double _r0, double _t121, double _t43, double otherW, double _r1) {
        _dst.x = _t58 > 0.0 ? Math.fma(_t114, _t63 < 0.0 ? _t102 <= 5.048709793414476E-29 ? _t50 : _t122 : _t122, _t115 * _t47) * _t25_inv : Math.fma(t, otherX - _r2, _r2);
        _dst.y = _t58 > 0.0 ? Math.fma(_t114, _t63 < 0.0 ? _t102 <= 5.048709793414476E-29 ? _t47 : _t120 : _t120, _t115 * _t49) * _t25_inv : Math.fma(t, otherY - _r3, _r3);
        _dst.z = _t58 > 0.0 ? Math.fma(_t114, _t63 < 0.0 ? _t102 <= 5.048709793414476E-29 ? _t51 : _t123 : _t123, _t115 * _t45) * _t25_inv : Math.fma(t, otherZ - _r0, _r0);
        _dst.w = _t58 > 0.0 ? Math.fma(_t114, _t63 < 0.0 ? _t102 <= 5.048709793414476E-29 ? _t45 : _t121 : _t121, _t115 * _t43) * _t25_inv : Math.fma(t, otherW - _r1, _r1);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private void slerp_degenerate_s5e1be5aa_tail(Double4Impl _dst, double _t37, double _t21, double _t22, double _t23, double _t24, double _t36, double t, double _t25, double _t7, double _t8, double _t40, double _t17, double _t18, double _t19, double _t20, double _t25_inv, double otherX, double _r2, double otherY, double _r3, double otherZ, double _r0, double otherW, double _r1) {
        double _t41 = (1.0 / Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        slerp_degenerate_s5e1be5aa_tail2(_dst, Math.fma(_t40, _t17, -(_t63 * _t43)), _t43, Math.fma(_t40, _t18, -(_t63 * _t45)), _t45, Math.fma(_t40, _t19, -(_t63 * _t47)), _t47, Math.fma(_t40, _t20, -(_t63 * _t49)), _t49, t, _t63, t * Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * Math.sqrt(_t37) * (_t25 / _t8), -_t49, -_t43, _t36 * _t37, _t25_inv, otherX, _r2, otherY, _r3, otherZ, _r0, otherW, _r1);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private void slerp_degenerate_s5e1be5aa_tail2(Double4Impl _dst, double _t72, double _t43, double _t73, double _t45, double _t74, double _t47, double _t75, double _t49, double t, double _t63, double _t60, double _t50, double _t51, double _t58, double _t25_inv, double otherX, double _r2, double otherY, double _r3, double otherZ, double _r0, double otherW, double _r1) {
        double _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = Math.fma(_t80, _t43, _t72);
        double _t82 = Math.fma(_t80, _t45, _t73);
        double _t83 = Math.fma(_t80, _t47, _t74);
        double _t84 = Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(Math.abs(_t83), Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t110 = t * Math.atan2(Math.sqrt(_t106), _t63 * _t90);
        slerp_degenerate_s5e1be5aa_tail3(_dst, _t106, (1.0 / Math.sqrt(_t106)), _t100, _t47, _t97, _t45, _t99, _t50, _t98, _t51, _t58, _t60 * Math.sin(_t110), _t63, Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))), _t60 * Math.cos(_t110), _t25_inv, t, otherX, _r2, _t49, otherY, _r3, otherZ, _r0, _t43, otherW, _r1);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private void slerp_degenerate_s5e1be5aa_tail3(Double4Impl _dst, double _t106, double _t108, double _t100, double _t47, double _t97, double _t45, double _t99, double _t50, double _t98, double _t51, double _t58, double _t114, double _t63, double _t102, double _t115, double _t25_inv, double t, double otherX, double _r2, double _t49, double otherY, double _r3, double otherZ, double _r0, double _t43, double otherW, double _r1) {
        double _t120, _t121, _t122, _t123;
        if (_t106 > 0.0) {
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
        slerp_degenerate_s5e1be5aa_c0(_dst, _t58, _t114, _t63, _t102, _t50, _t122, _t115, _t47, _t25_inv, t, otherX, _r2, _t120, _t49, otherY, _r3, _t51, _t123, _t45, otherZ, _r0, _t121, _t43, otherW, _r1);
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _r0 = this.z;
        double _r1 = this.w;
        double _r2 = this.x;
        double _r3 = this.y;
        double _t7 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        double _t8 = unitScale(_r0, _r1, Math.max(Math.abs(_r2), Math.abs(_r3)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _r1 * _t8;
        double _t22 = _r0 * _t8;
        double _t23 = _r2 * _t8;
        double _t24 = _r3 * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        slerp_degenerate_s5e1be5aa_tail(d, Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24))), _t21, _t22, _t23, _t24, _t36, t, _t25, _t7, _t8, (1.0 / Math.sqrt(_t36)), _t17, _t18, _t19, _t20, 1.0 / _t25, otherX, _r2, otherY, _r3, otherZ, _r0, otherW, _r1);
        return d;
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private Double4 slerp_degenerate(double otherX, double otherY, double otherZ, double otherW, double t) {
        return slerp_degenerate(otherX, otherY, otherZ, otherW, t, Joml.RETURN_NEW ? Joml.double4() : this);
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
    public Double4 absolute(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.abs(this.x);
        d.y = Math.abs(this.y);
        d.z = Math.abs(this.z);
        d.w = Math.abs(this.w);
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
    public Double4 addScaled(Double4R b, double scalar, @Mutated Double4 dest) {
        return addScaled(b.x(), b.y(), b.z(), b.w(), scalar, dest);
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
    public Double4 addScaled(double bX, double bY, double bZ, double bW, double scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.fma(scalar, bX, this.x);
        d.y = Math.fma(scalar, bY, this.y);
        d.z = Math.fma(scalar, bZ, this.z);
        d.w = Math.fma(scalar, bW, this.w);
        return d;
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
    public Double4 addScaled(Double4R b, Double4R c, @Mutated Double4 dest) {
        return addScaled(b.x(), b.y(), b.z(), b.w(), c.x(), c.y(), c.z(), c.w(), dest);
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
    public Double4 addScaled(double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.fma(bX, cX, this.x);
        d.y = Math.fma(bY, cY, this.y);
        d.z = Math.fma(bZ, cZ, this.z);
        d.w = Math.fma(bW, cW, this.w);
        return d;
    }


    /**
     * Compute the angle in radians between this vector and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}
     */
    public double angleBetween(Double4R other) {
        return angleBetween(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
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
    public double angleBetween(double otherX, double otherY, double otherZ, double otherW) {
        double _t12 = Math.fma(otherW, this.z, -(otherZ * this.w));
        double _t13 = Math.fma(otherW, this.y, -(otherY * this.w));
        double _t14 = Math.fma(otherZ, this.y, -(otherY * this.z));
        double _t15 = Math.fma(otherW, this.x, -(otherX * this.w));
        double _t16 = Math.fma(otherY, this.x, -(otherX * this.y));
        double _t17 = Math.fma(otherZ, this.x, -(otherX * this.z));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY, otherZ, otherW);
        return Math.atan2(Math.sqrt(_ct0), Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y))));
    }


    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate(Double4R other) {
        return angleBetween_degenerate(other.x(), other.y(), other.z(), other.w());
    }

    /** Private tail of {@code angleBetween_degenerate}; reached only through it. */
    private double angleBetween_degenerate_s7cee32ca_tail(double _t16, double _t21, double _t22, double _t19, double _t18, double _t23, double _t20, double _t17, double _t37, double _t38, double _t36) {
        double _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        double _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        double _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        double _t51 = unitScale(Math.max(Math.abs(_t37), Math.abs(_t38)), Math.max(Math.abs(_t39), Math.abs(_t40)), Math.max(Math.abs(_t41), Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }


    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate(double otherX, double otherY, double otherZ, double otherW) {
        double _t6 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        double _t7 = unitScale(this.z, this.w, Math.max(Math.abs(this.x), Math.abs(this.y)));
        double _t16 = otherW * _t6;
        double _t17 = this.z * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = this.w * _t7;
        double _t20 = otherY * _t6;
        double _t21 = this.x * _t7;
        double _t22 = otherX * _t6;
        double _t23 = this.y * _t7;
        return angleBetween_degenerate_s7cee32ca_tail(_t16, _t21, _t22, _t19, _t18, _t23, _t20, _t17, Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t17, -(_t18 * _t19)));
    }


    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
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
    public Double4 atan2(double x, @Mutated Double4 dest) {
        return atan2(x, x, x, x, dest);
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
    public Double4 atan2(Double4R x, @Mutated Double4 dest) {
        return atan2(x.x(), x.y(), x.z(), x.w(), dest);
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
    public Double4 atan2(double xX, double xY, double xZ, double xW, @Mutated Double4 dest) {
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
    public Double4 clamp(double min, double max, @Mutated Double4 dest) {
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
    public Double4 clamp(Double4R min, Double4R max, @Mutated Double4 dest) {
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
    public Double4 clamp(double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW, @Mutated Double4 dest) {
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
    public double compAdd() {
        return this.w + (this.z + (this.x + this.y));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public double compMax() {
        return Math.max(Math.max(Math.max(this.x, this.y), this.z), this.w);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public double compMin() {
        return Math.min(Math.min(Math.min(this.x, this.y), this.z), this.w);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public double compMul() {
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
    public Double4 copySign(double sign, @Mutated Double4 dest) {
        return copySign(sign, sign, sign, sign, dest);
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
    public Double4 copySign(Double4R sign, @Mutated Double4 dest) {
        return copySign(sign.x(), sign.y(), sign.z(), sign.w(), dest);
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
    public Double4 copySign(double signX, double signY, double signZ, double signW, @Mutated Double4 dest) {
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
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cross(Double4R v, Double4R w, @Mutated Double4 dest) {
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
    public Double4 cross(double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t12 = Math.fma(vY, wZ, -(vZ * wY));
        double _t13 = Math.fma(vZ, wW, -(vW * wZ));
        double _t14 = Math.fma(vY, wW, -(vW * wY));
        double _t15 = Math.fma(vX, wZ, -(vZ * wX));
        double _t16 = Math.fma(vX, wW, -(vW * wX));
        double _t17 = Math.fma(vX, wY, -(vY * wX));
        double _buf0 = Math.fma(this.w, _t12, Math.fma(this.y, _t13, -(this.z * _t14)));
        double _buf1 = Math.fma(-this.w, _t15, Math.fma(this.z, _t16, -(this.x * _t13)));
        double _buf2 = Math.fma(this.w, _t17, Math.fma(this.x, _t14, -(this.y * _t16)));
        d.w = Math.fma(-this.z, _t17, Math.fma(this.y, _t15, -(this.x * _t12)));
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    public double distance(Double4R other) {
        return distance(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the distance between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     *        {@code otherW})
     */
    public double distance(double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = this.w - otherW;
        double _t1 = this.z - otherZ;
        double _t2 = this.x - otherX;
        double _t3 = this.y - otherY;
        return Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public double distanceSquared(Double4R other) {
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
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
    public double distanceSquared(double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = this.w - otherW;
        double _t1 = this.z - otherZ;
        double _t2 = this.x - otherX;
        double _t3 = this.y - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public double dot(Double4R other) {
        return dot(other.x(), other.y(), other.z(), other.w());
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
    public double dot(double otherX, double otherY, double otherZ, double otherW) {
        return Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
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
    public Double4 exp2(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.pow(2.0, this.x);
        d.y = Math.pow(2.0, this.y);
        d.z = Math.pow(2.0, this.z);
        d.w = Math.pow(2.0, this.w);
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
    public Double4 expm1(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.expm1(this.x);
        d.y = Math.expm1(this.y);
        d.z = Math.expm1(this.z);
        d.w = Math.expm1(this.w);
        return d;
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
    public Double4 faceforward(Double4R I, Double4R Nref, @Mutated Double4 dest) {
        return faceforward(I.x(), I.y(), I.z(), I.w(), Nref.x(), Nref.y(), Nref.z(), Nref.w(), dest);
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
    public Double4 faceforward(double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        if (Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0) {
            d.x = this.x;
            d.y = this.y;
            d.z = this.z;
            d.w = this.w;
        } else {
            d.x = -this.x;
            d.y = -this.y;
            d.z = -this.z;
            d.w = -this.w;
        }
        return d;
    }


    /**
     * Compute the floor of each component of this vector and store the result in {@code dest}.
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
    public Double4 fract(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(this.x - Math.floor(this.x), 0.9999999999999999);
        d.y = Math.min(this.y - Math.floor(this.y), 0.9999999999999999);
        d.z = Math.min(this.z - Math.floor(this.z), 0.9999999999999999);
        d.w = Math.min(this.w - Math.floor(this.w), 0.9999999999999999);
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
    public Double4 hypot(double y, @Mutated Double4 dest) {
        return hypot(y, y, y, y, dest);
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
    public Double4 hypot(Double4R y, @Mutated Double4 dest) {
        return hypot(y.x(), y.y(), y.z(), y.w(), dest);
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
    public Double4 hypot(double yX, double yY, double yZ, double yW, @Mutated Double4 dest) {
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
    public Double4 inverse(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = 1.0 / this.x;
        d.y = 1.0 / this.y;
        d.z = 1.0 / this.z;
        d.w = 1.0 / this.w;
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
    public Double4 inverseSqrt(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = (1.0 / Math.sqrt(this.x));
        d.y = (1.0 / Math.sqrt(this.y));
        d.z = (1.0 / Math.sqrt(this.z));
        d.w = (1.0 / Math.sqrt(this.w));
        return d;
    }


    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this vector
     */
    public double length() {
        return Math.sqrt(Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y))));
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public double lengthSquared() {
        return Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
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
    public double manhattanDistance(Double4R other) {
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
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
    public double manhattanDistance(double otherX, double otherY, double otherZ, double otherW) {
        return Math.abs(this.x - otherX) + Math.abs(this.y - otherY) + Math.abs(this.z - otherZ) + Math.abs(this.w - otherW);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public double manhattanLength() {
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z) + Math.abs(this.w);
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
    public Double4 max(double scalar, @Mutated Double4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
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
    public Double4 max(Double4R other, @Mutated Double4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 max(double otherX, double otherY, double otherZ, double otherW, @Mutated Double4 dest) {
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 min(double scalar, @Mutated Double4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
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
    public Double4 min(Double4R other, @Mutated Double4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 min(double otherX, double otherY, double otherZ, double otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
        return d;
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
    public Double4 mod(double y, @Mutated Double4 dest) {
        return mod(y, y, y, y, dest);
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
    public Double4 mod(Double4R y, @Mutated Double4 dest) {
        return mod(y.x(), y.y(), y.z(), y.w(), dest);
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
    public Double4 mod(double yX, double yY, double yZ, double yW, @Mutated Double4 dest) {
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 normalize(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t3 = Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        double _t4 = (1.0 / Math.sqrt(_t3));
        if (_t3 != 0.0) {
            d.x = this.x * _t4;
            d.y = this.y * _t4;
            d.z = this.z * _t4;
            d.w = this.w * _t4;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
        }
        return d;
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
    public Double4 normalizeMul(double length, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t3 = Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        double _t5 = length * (1.0 / Math.sqrt(_t3));
        if (_t3 != 0.0) {
            d.x = this.x * _t5;
            d.y = this.y * _t5;
            d.z = this.z * _t5;
            d.w = this.w * _t5;
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
        }
        return d;
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
    public Double4x4 outerProduct(Double4R row, @Mutated Double4x4 dest) {
        return outerProduct(row.x(), row.y(), row.z(), row.w(), dest);
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
    public Double4x4 outerProduct(double rowX, double rowY, double rowZ, double rowW, @Mutated Double4x4 dest) {
        Double4x4Impl d = (Double4x4Impl) dest;
        double _buf0 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m20 = rowX * this.z;
        d.m30 = rowX * this.w;
        double _buf1 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.m21 = rowY * this.z;
        d.m31 = rowY * this.w;
        double _buf2 = rowZ * this.x;
        d.m12 = rowZ * this.y;
        d.m22 = rowZ * this.z;
        d.m32 = rowZ * this.w;
        double _buf3 = rowW * this.x;
        d.m13 = rowW * this.y;
        d.m23 = rowW * this.z;
        d.m33 = rowW * this.w;
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.m03 = _buf3;
        d.properties = 0;
        return d;
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
    public Double4 pow(double exponent, @Mutated Double4 dest) {
        return pow(exponent, exponent, exponent, exponent, dest);
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
    public Double4 pow(Double4R exponent, @Mutated Double4 dest) {
        return pow(exponent.x(), exponent.y(), exponent.z(), exponent.w(), dest);
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
    public Double4 pow(double exponentX, double exponentY, double exponentZ, double exponentW, @Mutated Double4 dest) {
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
    public Double4 project(Double4R onto, @Mutated Double4 dest) {
        return project(onto.x(), onto.y(), onto.z(), onto.w(), dest);
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
    public Double4 project(double ontoX, double ontoY, double ontoZ, double ontoW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t9 = Math.fma(ontoW, this.w, Math.fma(ontoZ, this.z, Math.fma(ontoX, this.x, ontoY * this.y))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        d.x = ontoX * _t9;
        d.y = ontoY * _t9;
        d.z = ontoZ * _t9;
        d.w = ontoW * _t9;
        return d;
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
    public Double4 projectOnPlane(Double4R normal, @Mutated Double4 dest) {
        return projectOnPlane(normal.x(), normal.y(), normal.z(), normal.w(), dest);
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
    public Double4 projectOnPlane(double normalX, double normalY, double normalZ, double normalW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t3 = Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        d.x = Math.fma(-normalX, _t3, this.x);
        d.y = Math.fma(-normalY, _t3, this.y);
        d.z = Math.fma(-normalZ, _t3, this.z);
        d.w = Math.fma(-normalW, _t3, this.w);
        return d;
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
    public Double4 radians(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.toRadians(this.x);
        d.y = Math.toRadians(this.y);
        d.z = Math.toRadians(this.z);
        d.w = Math.toRadians(this.w);
        return d;
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
    public Double4 reflect(Double4R normal, @Mutated Double4 dest) {
        return reflect(normal.x(), normal.y(), normal.z(), normal.w(), dest);
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
    public Double4 reflect(double normalX, double normalY, double normalZ, double normalW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t4 = 2.0 * Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        d.x = Math.fma(-normalX, _t4, this.x);
        d.y = Math.fma(-normalY, _t4, this.y);
        d.z = Math.fma(-normalZ, _t4, this.z);
        d.w = Math.fma(-normalW, _t4, this.w);
        return d;
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
    public Double4 refract(Double4R normal, double eta, @Mutated Double4 dest) {
        return refract(normal.x(), normal.y(), normal.z(), normal.w(), eta, dest);
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
    public Double4 refract(double normalX, double normalY, double normalZ, double normalW, double eta, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t4 = Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, Math.sqrt(Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            d.x = Math.fma(eta, this.x, -(normalX * _t11));
            d.y = Math.fma(eta, this.y, -(normalY * _t11));
            d.z = Math.fma(eta, this.z, -(normalZ * _t11));
            d.w = Math.fma(eta, this.w, -(normalW * _t11));
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
            d.w = 0.0;
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
    public Double4 sinh(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.sinh(this.x);
        d.y = Math.sinh(this.y);
        d.z = Math.sinh(this.z);
        d.w = Math.sinh(this.w);
        return d;
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
    public Double4 smoothstep(double edge0, double edge1, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = Math.max(0.0, Math.min(1.0, (this.x - edge0) * _t0_inv));
        double _t14 = Math.max(0.0, Math.min(1.0, (this.y - edge0) * _t0_inv));
        double _t15 = Math.max(0.0, Math.min(1.0, (this.z - edge0) * _t0_inv));
        double _t16 = Math.max(0.0, Math.min(1.0, (this.w - edge0) * _t0_inv));
        d.x = Math.fma(-2.0, _t13, 3.0) * _t13 * _t13;
        d.y = Math.fma(-2.0, _t14, 3.0) * _t14 * _t14;
        d.z = Math.fma(-2.0, _t15, 3.0) * _t15 * _t15;
        d.w = Math.fma(-2.0, _t16, 3.0) * _t16 * _t16;
        return d;
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
    public Double4 smoothstep(Double4R edge0, Double4R edge1, @Mutated Double4 dest) {
        return smoothstep(edge0.x(), edge0.y(), edge0.z(), edge0.w(), edge1.x(), edge1.y(), edge1.z(), edge1.w(), dest);
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
    public Double4 smoothstep(double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t16 = Math.max(0.0, Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
        double _t17 = Math.max(0.0, Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (this.z - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (this.w - edge0W) / (edge1W - edge0W)));
        d.x = Math.fma(-2.0, _t16, 3.0) * _t16 * _t16;
        d.y = Math.fma(-2.0, _t17, 3.0) * _t17 * _t17;
        d.z = Math.fma(-2.0, _t18, 3.0) * _t18 * _t18;
        d.w = Math.fma(-2.0, _t19, 3.0) * _t19 * _t19;
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
    public Double4 sqrt(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.sqrt(this.x);
        d.y = Math.sqrt(this.y);
        d.z = Math.sqrt(this.z);
        d.w = Math.sqrt(this.w);
        return d;
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
    public Double4 step(double edge, @Mutated Double4 dest) {
        return step(edge, edge, edge, edge, dest);
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
    public Double4 step(Double4R edge, @Mutated Double4 dest) {
        return step(edge.x(), edge.y(), edge.z(), edge.w(), dest);
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
    public Double4 step(double edgeX, double edgeY, double edgeZ, double edgeW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x < edgeX ? 0.0 : 1.0;
        d.y = this.y < edgeY ? 0.0 : 1.0;
        d.z = this.z < edgeZ ? 0.0 : 1.0;
        d.w = this.w < edgeW ? 0.0 : 1.0;
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
    public Double4 trunc(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x >= 0.0 ? Math.floor(this.x) : Math.ceil(this.x);
        d.y = this.y >= 0.0 ? Math.floor(this.y) : Math.ceil(this.y);
        d.z = this.z >= 0.0 ? Math.floor(this.z) : Math.ceil(this.z);
        d.w = this.w >= 0.0 ? Math.floor(this.w) : Math.ceil(this.w);
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
    public Double4 preMul(Double4x4R mat, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _buf0 = Math.fma(mat.m03(), this.w, Math.fma(mat.m02(), this.z, Math.fma(mat.m00(), this.x, mat.m01() * this.y)));
        double _buf1 = Math.fma(mat.m13(), this.w, Math.fma(mat.m12(), this.z, Math.fma(mat.m10(), this.x, mat.m11() * this.y)));
        double _buf2 = Math.fma(mat.m23(), this.w, Math.fma(mat.m22(), this.z, Math.fma(mat.m20(), this.x, mat.m21() * this.y)));
        d.w = Math.fma(mat.m33(), this.w, Math.fma(mat.m32(), this.z, Math.fma(mat.m30(), this.x, mat.m31() * this.y)));
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 rotate(DoubleQuatR quat, @Mutated Double4 dest) {
        return rotate(quat.x(), quat.y(), quat.z(), quat.w(), dest);
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
    public Double4 rotate(double quatX, double quatY, double quatZ, double quatW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t9 = 2.0 * Math.fma(quatX, this.y, -(quatY * this.x));
        double _t10 = 2.0 * Math.fma(quatZ, this.x, -(quatX * this.z));
        double _t11 = 2.0 * Math.fma(quatY, this.z, -(quatZ * this.y));
        d.x = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, this.x)));
        d.y = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.y)));
        d.z = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, this.z)));
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
    public Double4 rotateAxis(double angle, Double3R axis, @Mutated Double4 dest) {
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
    public Double4 rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated Double4 dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        Double4Impl d = (Double4Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        double _buf0 = Math.fma(_t3, axisX * _t5, Math.fma(this.x, _t1, Math.fma(axisY, this.z, -(axisZ * this.y)) * _t0));
        double _buf1 = Math.fma(_t3, axisY * _t5, Math.fma(this.y, _t1, Math.fma(axisZ, this.x, -(axisX * this.z)) * _t0));
        d.z = Math.fma(_t3, axisZ * _t5, Math.fma(this.z, _t1, Math.fma(axisX, this.y, -(axisY * this.x)) * _t0));
        d.w = this.w;
        d.x = _buf0;
        d.y = _buf1;
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
    public Double4 rotateInverse(DoubleQuatR quat, @Mutated Double4 dest) {
        return rotateInverse(quat.x(), quat.y(), quat.z(), quat.w(), dest);
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
    public Double4 rotateInverse(double quatX, double quatY, double quatZ, double quatW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t9 = 2.0 * Math.fma(quatX, this.z, -(quatZ * this.x));
        double _t10 = 2.0 * Math.fma(quatY, this.x, -(quatX * this.y));
        double _t11 = 2.0 * Math.fma(quatZ, this.y, -(quatY * this.z));
        d.x = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, this.x)));
        d.y = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, this.y)));
        d.z = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.z)));
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
    public Double4 rotateX(double angle, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        d.x = this.x;
        double _buf0 = Math.fma(this.y, _t1, -(this.z * _t0));
        d.z = Math.fma(this.y, _t0, this.z * _t1);
        d.w = this.w;
        d.y = _buf0;
        return d;
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
    public Double4 rotateY(double angle, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(this.x, _t1, this.z * _t0);
        d.y = this.y;
        d.z = Math.fma(this.z, _t1, -(this.x * _t0));
        d.w = this.w;
        d.x = _buf0;
        return d;
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
    public Double4 rotateZ(double angle, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(this.x, _t1, -(this.y * _t0));
        d.y = Math.fma(this.x, _t0, this.y * _t1);
        d.z = this.z;
        d.w = this.w;
        d.x = _buf0;
        return d;
    }

    public double x() { return this.x; }
    public double y() { return this.y; }
    public double z() { return this.z; }
    public double w() { return this.w; }

    public Double2 xx(@Mutated Double2 dest) {
        double _v0 = this.x;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Double2 xy(@Mutated Double2 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 xz(@Mutated Double2 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 xw(@Mutated Double2 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 yx(@Mutated Double2 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 yy(@Mutated Double2 dest) {
        double _v0 = this.y;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Double2 yz(@Mutated Double2 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 yw(@Mutated Double2 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 zx(@Mutated Double2 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 zy(@Mutated Double2 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 zz(@Mutated Double2 dest) {
        double _v0 = this.z;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Double2 zw(@Mutated Double2 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 wx(@Mutated Double2 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 wy(@Mutated Double2 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 wz(@Mutated Double2 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Double2 ww(@Mutated Double2 dest) {
        double _v0 = this.w;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Double3 xxx(@Mutated Double3 dest) {
        double _v0 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Double3 xxy(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 xxz(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 xxw(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 xyx(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 xyy(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 xyz(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 xyw(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 xzx(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 xzy(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 xzz(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 xzw(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 xwx(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 xwy(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 xwz(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 xww(@Mutated Double3 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 yxx(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 yxy(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 yxz(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 yxw(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 yyx(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 yyy(@Mutated Double3 dest) {
        double _v0 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Double3 yyz(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 yyw(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 yzx(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 yzy(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 yzz(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 yzw(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 ywx(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 ywy(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 ywz(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 yww(@Mutated Double3 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 zxx(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 zxy(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 zxz(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 zxw(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 zyx(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 zyy(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 zyz(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 zyw(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 zzx(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 zzy(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 zzz(@Mutated Double3 dest) {
        double _v0 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Double3 zzw(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 zwx(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 zwy(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 zwz(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 zww(@Mutated Double3 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 wxx(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 wxy(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 wxz(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 wxw(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 wyx(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 wyy(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 wyz(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 wyw(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 wzx(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 wzy(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 wzz(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 wzw(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Double3 wwx(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 wwy(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 wwz(@Mutated Double3 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Double3 www(@Mutated Double3 dest) {
        double _v0 = this.w;
        Double3Impl d = (Double3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Double4 xxxx(@Mutated Double4 dest) {
        double _v0 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 xxxy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 xxxz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 xxxw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 xxyx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 xxyy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 xxyz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xxyw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xxzx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 xxzy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xxzz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 xxzw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xxwx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 xxwy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xxwz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xxww(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 xyxx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 xyxy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 xyxz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 xyxw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 xyyx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 xyyy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 xyyz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xyyw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xyzx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 xyzy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 xyzz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 xyzw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.z;
        double _v3 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 xywx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 xywy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 xywz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        double _v3 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 xyww(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 xzxx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 xzxy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 xzxz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 xzxw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 xzyx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 xzyy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 xzyz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 xzyw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        double _v3 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 xzzx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 xzzy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xzzz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 xzzw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xzwx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 xzwy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        double _v3 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 xzwz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 xzww(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 xwxx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 xwxy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 xwxz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 xwxw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 xwyx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 xwyy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 xwyz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        double _v3 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 xwyw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 xwzx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 xwzy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        double _v3 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 xwzz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 xwzw(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 xwwx(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 xwwy(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xwwz(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xwww(@Mutated Double4 dest) {
        double _v0 = this.x;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 yxxx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 yxxy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 yxxz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yxxw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yxyx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 yxyy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 yxyz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 yxyw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 yxzx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 yxzy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 yxzz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 yxzw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        double _v3 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 yxwx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 yxwy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 yxwz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        double _v3 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 yxww(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 yyxx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 yyxy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 yyxz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yyxw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yyyx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 yyyy(@Mutated Double4 dest) {
        double _v0 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 yyyz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 yyyw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 yyzx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yyzy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 yyzz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 yyzw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yywx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yywy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 yywz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yyww(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 yzxx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 yzxy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 yzxz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 yzxw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        double _v3 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 yzyx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 yzyy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 yzyz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 yzyw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 yzzx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yzzy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 yzzz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 yzzw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 yzwx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        double _v3 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 yzwy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 yzwz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 yzww(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.z;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 ywxx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 ywxy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 ywxz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        double _v3 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 ywxw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 ywyx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 ywyy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 ywyz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 ywyw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 ywzx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        double _v3 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 ywzy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 ywzz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 ywzw(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 ywwx(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 ywwy(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 ywwz(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 ywww(@Mutated Double4 dest) {
        double _v0 = this.y;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zxxx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zxxy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zxxz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 zxxw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zxyx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 zxyy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 zxyz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 zxyw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        double _v3 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 zxzx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 zxzy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 zxzz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 zxzw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 zxwx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 zxwy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        double _v3 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 zxwz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 zxww(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 zyxx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 zyxy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 zyxz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 zyxw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        double _v3 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 zyyx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zyyy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zyyz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 zyyw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zyzx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 zyzy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 zyzz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 zyzw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 zywx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        double _v3 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 zywy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 zywz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 zyww(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 zzxx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zzxy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zzxz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 zzxw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zzyx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zzyy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zzyz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 zzyw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        double _v2 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zzzx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 zzzy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 zzzz(@Mutated Double4 dest) {
        double _v0 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 zzzw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 zzwx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zzwy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zzwz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 zzww(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zwxx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 zwxy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        double _v3 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 zwxz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 zwxw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 zwyx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        double _v3 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 zwyy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 zwyz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 zwyw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 zwzx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 zwzy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 zwzz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 zwzw(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 zwwx(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zwwy(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zwwz(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 zwww(@Mutated Double4 dest) {
        double _v0 = this.z;
        double _v1 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 wxxx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 wxxy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wxxz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wxxw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 wxyx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 wxyy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 wxyz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        double _v3 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 wxyw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 wxzx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 wxzy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        double _v3 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 wxzz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 wxzw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 wxwx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 wxwy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 wxwz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 wxww(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 wyxx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 wyxy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 wyxz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        double _v3 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 wyxw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 wyyx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wyyy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 wyyz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wyyw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 wyzx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        double _v3 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 wyzy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 wyzz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 wyzw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 wywx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 wywy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 wywz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 wyww(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 wzxx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 wzxy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        double _v3 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 wzxz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 wzxw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 wzyx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        double _v3 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Double4 wzyy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 wzyz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 wzyw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Double4 wzzx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wzzy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wzzz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 wzzw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 wzwx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 wzwy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Double4 wzwz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 wzww(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Double4 wwxx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 wwxy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wwxz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wwxw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 wwyx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wwyy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 wwyz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wwyw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 wwzx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wwzy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 wwzz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 wwzw(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Double4 wwwx(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 wwwy(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 wwwz(@Mutated Double4 dest) {
        double _v0 = this.w;
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Double4 wwww(@Mutated Double4 dest) {
        double _v0 = this.w;
        Double4Impl d = (Double4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Double4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double4Impl)) return false;
        Double4Impl o = (Double4Impl) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y)
            && Double.doubleToLongBits(z) == Double.doubleToLongBits(o.z)
            && Double.doubleToLongBits(w) == Double.doubleToLongBits(o.w);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(w) ^ (Double.doubleToLongBits(w) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y)
            && Double.isFinite(z)
            && Double.isFinite(w);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y)
            || Double.isNaN(z)
            || Double.isNaN(w);
    }

    @Override public boolean equalsEpsilon(Double4R other, double epsilon) {
        return Math.abs(x - other.x()) <= epsilon
            && Math.abs(y - other.y()) <= epsilon
            && Math.abs(z - other.z()) <= epsilon
            && Math.abs(w - other.w()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated Double4 load(double[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Double4 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Double4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Double4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Double4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public Double4 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.x;
        dest[offset + 1] = (float) this.y;
        dest[offset + 2] = (float) this.z;
        dest[offset + 3] = (float) this.w;
        return dest;
    }
    public @Mutated Double4 load(float[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Double4 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public Double4 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public Double4 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public Double4 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    public Double4 loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }

    /** Double-precision twin of {@link #flooredMod(float, float)}. */
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
