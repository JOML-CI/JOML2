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
 * Generated implementation of {@link Double3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Double3Impl implements Double3 {

    public double x;
    public double y;
    public double z;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3SegOpsUnsafe()
                        : new Double3SegOpsMS();
        static final Double3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3BbOpsUnsafe()
                        : new Double3BbOpsApi();
        static final Double3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3RawOpsUnsafe()
                        : new Double3RawOpsApi();
    }

    public Double3Impl() {
    }

    public Double3Impl(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Double3Impl(Double3R src) {
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
    public Double3 add(Double3R other, @Mutated Double3 dest) {
        double otherY = other.y();
        double otherZ = other.z();
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
    public Double3 add(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
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
    public Double3 div(double scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x / scalar;
        d.y = this.y / scalar;
        d.z = this.z / scalar;
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
    public Double3 div(Double3R other, @Mutated Double3 dest) {
        double otherY = other.y();
        double otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x / other.x();
        d.y = this.y / otherY;
        d.z = this.z / otherZ;
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x / otherX;
        d.y = this.y / otherY;
        d.z = this.z / otherZ;
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
    public Double3 fma(double b, Double3R c, @Mutated Double3 dest) {
        double cX = c.x();
        double cY = c.y();
        double cZ = c.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            d.z = java.lang.Math.fma(this.z, b, cZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
            d.z = ((this.z) * (b) + (cZ));
            return d;
        }
    }


    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY},
     * {@code cZ}), i.e. compute {@code this * b + (cX, cY, cZ)} per component and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 fma(double b, double cX, double cY, double cZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            d.z = java.lang.Math.fma(this.z, b, cZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
            d.z = ((this.z) * (b) + (cZ));
            return d;
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
    public Double3 fma(Double3R b, Double3R c, @Mutated Double3 dest) {
        double bX = b.x();
        double bY = b.y();
        double bZ = b.z();
        double cX = c.x();
        double cY = c.y();
        double cZ = c.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            d.z = java.lang.Math.fma(this.z, bZ, cZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
            d.z = ((this.z) * (bZ) + (cZ));
            return d;
        }
    }


    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}) and add
     * ({@code cX}, {@code cY}, {@code cZ}), i.e. compute {@code this * (bX, bY, bZ) + (cX, cY, cZ)}
     * per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 fma(double bX, double bY, double bZ, double cX, double cY, double cZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            d.z = java.lang.Math.fma(this.z, bZ, cZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
            d.z = ((this.z) * (bZ) + (cZ));
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
    public Double3 mul(double scalar, @Mutated Double3 dest) {
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
    public Double3 mul(Double3R other, @Mutated Double3 dest) {
        double otherY = other.y();
        double otherZ = other.z();
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
    public Double3 mul(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
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
    public Double3 negate(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
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
    public Double3 sub(Double3R other, @Mutated Double3 dest) {
        double otherY = other.y();
        double otherZ = other.z();
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
    public Double3 sub(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
    }


    /**
     * Set this vector to the unit vector {@code (r cos(2 PI v), r sin(2 PI v), 2u - 1)} with
     * {@code r = 2 sqrt(u (1 - u))}: samples uniformly distributed in {@code [0, 1)} give a
     * direction uniformly distributed on the unit sphere ({@code makeRandomDirection} draws them
     * from a {@link java.util.Random}).
     * <p>
     * Valid input: {@code u} must lie in {@code [0, 1]}.
     *
     * @param u the sample that sets the height {@code z = 2u - 1}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @param v the fraction of a full turn about the z axis, counter-clockwise from the x axis,
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed direction
     * @return this
     */
    @Mutated public Double3 makeUniformDirection(double u, double v) {
        if (Math.useFma()) {
            double _t1 = v * 6.283185307179586;
            double _t2 = Math.sin(_t1);
            double _t5 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            this.x = _t5 * Math.cosFromSin(_t2, _t1);
            this.y = _t5 * _t2;
            this.z = java.lang.Math.fma(2.0, u, -1.0);
            return this;
        } else {
            double _t1 = v * 6.283185307179586;
            double _t2 = Math.sin(_t1);
            double _t5 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            this.x = _t5 * Math.cosFromSin(_t2, _t1);
            this.y = _t5 * _t2;
            this.z = ((2.0) * (u) - (1.0));
            return this;
        }
    }


    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    public @Mutated Double3 set(Double3R v) {
        double vY = v.y();
        double vZ = v.z();
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
    @Mutated public Double3 set(double vX, double vY, double vZ) {
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
    public Double3 set(double s, @Mutated Double3 dest) {
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
        d.x = (float) (this.x);
        d.y = (float) (this.y);
        d.z = (float) (this.z);
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
    public Byte3 toByte(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
        d.z = (byte) (this.z);
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
    public Byte3 toByte(RoundingMode roundingMode, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toByte(dest); }
            case FLOOR -> {
                d.x = (byte) Math.floor(this.x);
                d.y = (byte) Math.floor(this.y);
                d.z = (byte) Math.floor(this.z);
            }
            case CEILING -> {
                d.x = (byte) Math.ceil(this.x);
                d.y = (byte) Math.ceil(this.y);
                d.z = (byte) Math.ceil(this.z);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y)));
                d.z = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.z)));
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (byte) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (byte) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (byte) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
            }
            case HALF_EVEN -> {
                d.x = (byte) Math.rint(this.x);
                d.y = (byte) Math.rint(this.y);
                d.z = (byte) Math.rint(this.z);
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
    public Short3 toShort(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
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
    public Short3 toShort(RoundingMode roundingMode, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toShort(dest); }
            case FLOOR -> {
                d.x = (short) Math.floor(this.x);
                d.y = (short) Math.floor(this.y);
                d.z = (short) Math.floor(this.z);
            }
            case CEILING -> {
                d.x = (short) Math.ceil(this.x);
                d.y = (short) Math.ceil(this.y);
                d.z = (short) Math.ceil(this.z);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y)));
                d.z = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.z)));
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (short) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (short) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (short) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
            }
            case HALF_EVEN -> {
                d.x = (short) Math.rint(this.x);
                d.y = (short) Math.rint(this.y);
                d.z = (short) Math.rint(this.z);
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
    public Int3 toInt(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = (int) (this.x);
        d.y = (int) (this.y);
        d.z = (int) (this.z);
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
    public Int3 toInt(RoundingMode roundingMode, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toInt(dest); }
            case FLOOR -> {
                d.x = (int) Math.floor(this.x);
                d.y = (int) Math.floor(this.y);
                d.z = (int) Math.floor(this.z);
            }
            case CEILING -> {
                d.x = (int) Math.ceil(this.x);
                d.y = (int) Math.ceil(this.y);
                d.z = (int) Math.ceil(this.z);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y)));
                d.z = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.z)));
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (int) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (int) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (int) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
            }
            case HALF_EVEN -> {
                d.x = (int) Math.rint(this.x);
                d.y = (int) Math.rint(this.y);
                d.z = (int) Math.rint(this.z);
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
    public Long3 toLong(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = (long) (this.x);
        d.y = (long) (this.y);
        d.z = (long) (this.z);
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
    public Long3 toLong(RoundingMode roundingMode, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toLong(dest); }
            case FLOOR -> {
                d.x = (long) Math.floor(this.x);
                d.y = (long) Math.floor(this.y);
                d.z = (long) Math.floor(this.z);
            }
            case CEILING -> {
                d.x = (long) Math.ceil(this.x);
                d.y = (long) Math.ceil(this.y);
                d.z = (long) Math.ceil(this.z);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.x = Math.round(this.x);
                d.y = Math.round(this.y);
                d.z = Math.round(this.z);
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.x = (long) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (long) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
                d.z = (long) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z));
            }
            case HALF_EVEN -> {
                d.x = (long) Math.rint(this.x);
                d.y = (long) Math.rint(this.y);
                d.z = (long) Math.rint(this.z);
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
    @Mutated public Double3 makeZero() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
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
    public Double3 bezier(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p2Y = p2.y();
        double p2Z = p2.z();
        double p3Y = p3.y();
        double p3Z = p3.z();
        Double3Impl d = (Double3Impl) dest;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        d.x = Math.fma(p1.x(), _t7, this.x * _t8) + Math.fma(p2.x(), _t6, p3.x() * _t2);
        d.y = Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        d.z = Math.fma(p1Z, _t7, this.z * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        return d;
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z})
     * and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}, {@code p1Z}) and
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) pull it towards themselves but are generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 bezier(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Double3 bezier2(Double3R p1, Double3R p2, double t, @Mutated Double3 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p2Z = p2.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4));
            d.y = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4));
            d.z = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, this.z * _t4));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = ((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4))));
            d.y = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4))));
            d.z = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (this.z * _t4))));
            return d;
        }
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}, {@code p1Z})
     * pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 bezier2(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4));
            d.y = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4));
            d.z = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, this.z * _t4));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = ((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4))));
            d.y = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4))));
            d.z = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (this.z * _t4))));
            return d;
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
    public Double3 bezier2Tangent(Double3R p1, Double3R p2, double t, @Mutated Double3 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p2Z = p2.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1);
            d.y = java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1);
            d.z = java.lang.Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = ((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1));
            d.y = ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1));
            d.z = ((p1Z - this.z) * (_t2) + ((p2Z - p1Z) * _t1));
            return d;
        }
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y}, {@code p2Z})
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}, {@code p1Z})
     * pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 bezier2Tangent(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1);
            d.y = java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1);
            d.z = java.lang.Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = ((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1));
            d.y = ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1));
            d.z = ((p1Z - this.z) * (_t2) + ((p2Z - p1Z) * _t1));
            return d;
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
    public Double3 bezierTangent(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        return bezierTangent(p1.x(), p1.y(), p1.z(), p2.x(), p2.y(), p2.z(), p3.x(), p3.y(), p3.z(), t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z}) and ends at
     * ({@code p3X}, {@code p3Y}, {@code p3Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}, {@code p1Z}) and
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) pull it towards themselves but are generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 bezierTangent(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            d.x = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5));
            d.y = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5));
            d.z = java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            d.x = ((p3X - p2X) * (_t2) + (((p1X - this.x) * (_t6) + ((p2X - p1X) * _t5))));
            d.y = ((p3Y - p2Y) * (_t2) + (((p1Y - this.y) * (_t6) + ((p2Y - p1Y) * _t5))));
            d.z = ((p3Z - p2Z) * (_t2) + (((p1Z - this.z) * (_t6) + ((p2Z - p1Z) * _t5))));
            return d;
        }
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
    public Double3 catmullRom(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        return catmullRom(p1.x(), p1.y(), p1.z(), p2.x(), p2.y(), p2.z(), p3.x(), p3.y(), p3.z(), t, dest);
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z})
     * to ({@code p2X}, {@code p2Y}, {@code p2Z}), with this vector as the control point before the
     * segment and ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it and store
     * the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) at {@code t = 1}. This vector and ({@code p3X},
     * {@code p3Y}, {@code p3Z}) are the spline's neighbouring points, i.e. the point before
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and the point after ({@code p2X}, {@code p2Y},
     * {@code p2Z}): they only shape the tangents at the segment's two end points and are not
     * themselves on the segment. For a spline through the points {@code p[0..n]}, the segment from
     * {@code p[i]} to {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of
     * this vector and {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 catmullRom(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = t * t;
        double _t1 = t * _t0;
        d.x = 0.5 * (Math.fma(2.0, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)) * _t1));
        d.y = 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)) * _t1));
        d.z = 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - this.z)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, this.z, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - this.z)) * _t1));
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
    public Double3 catmullRomTangent(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        return catmullRomTangent(p1.x(), p1.y(), p1.z(), p2.x(), p2.y(), p2.z(), p3.x(), p3.y(), p3.z(), t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}) to ({@code p2X},
     * {@code p2Y}, {@code p2Z}), with this vector as the control point before the segment and
     * ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) at {@code t = 1}. This vector and ({@code p3X},
     * {@code p3Y}, {@code p3Z}) are the spline's neighbouring points, i.e. the point before
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and the point after ({@code p2X}, {@code p2Y},
     * {@code p2Z}): they only shape the tangents at the segment's two end points and are not
     * themselves on the segment. For a spline through the points {@code p[0..n]}, the segment from
     * {@code p[i]} to {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of
     * this vector and {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 catmullRomTangent(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = t * t;
        d.x = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)), _t0, p2X - this.x));
        d.y = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)), _t0, p2Y - this.y));
        d.z = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, this.z, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - this.z)), _t0, p2Z - this.z));
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
    public Double3 hermite(Double3R t0, Double3R v1, Double3R t1, double t, @Mutated Double3 dest) {
        double t0Y = t0.y();
        double t0Z = t0.z();
        double v1Y = v1.y();
        double v1Z = v1.z();
        double t1Y = t1.y();
        double t1Z = t1.z();
        Double3Impl d = (Double3Impl) dest;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        d.x = Math.fma(this.x, _t10, t0.x() * _t7) + Math.fma(t1.x(), _t5, v1.x() * _t9);
        d.y = Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        d.z = Math.fma(this.z, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        return d;
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}) and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}) at {@code t = 1}; the two tangents set its direction and speed at those end
     * points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 hermite(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        d.x = Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        d.y = Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        d.z = Math.fma(this.z, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
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
    public Double3 hermiteTangent(Double3R t0, Double3R v1, Double3R t1, double t, @Mutated Double3 dest) {
        double t0Y = t0.y();
        double t0Z = t0.z();
        double v1Y = v1.y();
        double v1Z = v1.z();
        double t1Y = t1.y();
        double t1Z = t1.z();
        Double3Impl d = (Double3Impl) dest;
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        d.x = Math.fma(this.x, _t6, t0.x() * _t9) + Math.fma(t1.x(), _t8, v1.x() * _t7);
        d.y = Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        d.z = Math.fma(this.z, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        return d;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}) with the tangent
     * ({@code t1X}, {@code t1Y}, {@code t1Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}) at {@code t = 1}; the two tangents set its direction and speed at those end
     * points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 hermiteTangent(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        d.x = Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        d.y = Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        d.z = Math.fma(this.z, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
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
    public Double3 lerp(Double3R other, double t, @Mutated Double3 dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            return d;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     * using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 lerp(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
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
    public Double3 lerp(Double3R other, Double3R t, @Mutated Double3 dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double tX = t.x();
        double tY = t.y();
        double tZ = t.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(tZ, otherZ - this.z, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
            d.z = ((tZ) * (otherZ - this.z) + (this.z));
            return d;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     * using the interpolation factor ({@code tX}, {@code tY}, {@code tZ}) and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 lerp(double otherX, double otherY, double otherZ, double tX, double tY, double tZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(tZ, otherZ - this.z, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
            d.z = ((tZ) * (otherZ - this.z) + (this.z));
            return d;
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
     * opposite directions, whose arc lies in no particular plane, the direction turns through a
     * perpendicular of this vector. The angle is computed with {@code atan2}, and vectors of any
     * finite length are handled: when their squared lengths leave the {@code double} range, they
     * are first scaled exactly by powers of two.
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
    public Double3 slerp(Double3R other, double t, @Mutated Double3 dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        if (Math.useFma()) return slerp_fma(otherX, otherY, otherZ, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, t, dest);
    }

    /** Private store group 0 of {@code slerp}: computes and stores it; reached only through it. */
    private void slerp_s56f5a5dd_c0_fma(Double3Impl _dst, double _t14, double _t55, double _sp0, double _t41, double _t20, double _t42, double _t17, double _t40) {
        _dst.x = java.lang.Math.fma(_t14, _t55, _sp0 * _t41);
        _dst.y = java.lang.Math.fma(_t20, _t55, _sp0 * _t42);
        _dst.z = java.lang.Math.fma(_t17, _t55, _sp0 * _t40);
    }

    /** Private store group 0 of {@code slerp}: computes and stores it; reached only through it. */
    private void slerp_s56f5a5dd_c0_mulAdd(Double3Impl _dst, double _t14, double _t55, double _sp0, double _t41, double _t20, double _t42, double _t17, double _t40) {
        _dst.x = ((_t14) * (_t55) + (_sp0 * _t41));
        _dst.y = ((_t20) * (_t55) + (_sp0 * _t42));
        _dst.z = ((_t17) * (_t55) + (_sp0 * _t40));
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private void slerp_s56f5a5dd_tail_fma(Double3Impl _dst, double t, double _t8, double _t7, double _t46, double _t26, double _t14, double _t41, double _t20, double _t42, double _t17, double _t40) {
        double _t24 = t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        slerp_s56f5a5dd_c0_fma(_dst, _t14, _t24 * Math.cos(_t50), _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46)), _t41, _t20, _t42, _t17, _t40);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private void slerp_s56f5a5dd_tail_mulAdd(Double3Impl _dst, double t, double _t8, double _t7, double _t46, double _t26, double _t14, double _t41, double _t20, double _t42, double _t17, double _t40) {
        double _t24 = t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        slerp_s56f5a5dd_c0_mulAdd(_dst, _t14, _t24 * Math.cos(_t50), _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46)), _t41, _t20, _t42, _t17, _t40);
    }


    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) using the interpolation factor {@code t}: the direction turns at a constant
     * rate along the shorter arc between the two directions, and the length changes linearly
     * between the two lengths and store the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through a
     * perpendicular of this vector. The angle is computed with {@code atan2}, and vectors of any
     * finite length are handled: when their squared lengths leave the {@code double} range, they
     * are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 slerp(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        if (Math.useFma()) return slerp_fma(otherX, otherY, otherZ, t, dest);
        return slerp_mulAdd(otherX, otherY, otherZ, t, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 slerp_fma(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        double _r0 = this.z;
        double _r1 = this.x;
        double _r2 = this.y;
        double _t7 = java.lang.Math.fma(_r0, _r0, java.lang.Math.fma(_r1, _r1, _r2 * _r2));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, dest);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, dest);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _r1 * _t9;
        double _t17 = _r0 * _t9;
        double _t20 = _r2 * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        double _t33 = java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17));
        double _t34 = java.lang.Math.fma(otherX, _t12, -(_t26 * _t14));
        double _t35 = java.lang.Math.fma(otherY, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        return slerp_s960f8c7a_1_fma(otherX, otherY, otherZ, t, dest, (Double3Impl) dest, _t7, _t8, _t14, _t17, _t20, _t26, java.lang.Math.fma(_t39, _t17, _t33), java.lang.Math.fma(_t39, _t14, _t34), java.lang.Math.fma(_t39, _t20, _t35));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 slerp_mulAdd(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        double _r0 = this.z;
        double _r1 = this.x;
        double _r2 = this.y;
        double _t7 = ((_r0) * (_r0) + (((_r1) * (_r1) + (_r2 * _r2))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, dest);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, dest);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _r1 * _t9;
        double _t17 = _r0 * _t9;
        double _t20 = _r2 * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        double _t33 = ((otherZ) * (_t12) - (_t26 * _t17));
        double _t34 = ((otherX) * (_t12) - (_t26 * _t14));
        double _t35 = ((otherY) * (_t12) - (_t26 * _t20));
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        return slerp_s960f8c7a_1_mulAdd(otherX, otherY, otherZ, t, dest, (Double3Impl) dest, _t7, _t8, _t14, _t17, _t20, _t26, ((_t39) * (_t17) + (_t33)), ((_t39) * (_t14) + (_t34)), ((_t39) * (_t20) + (_t35)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_s960f8c7a_1_fma(double otherX, double otherY, double otherZ, double t, Double3 dest, Double3Impl d, double _t7, double _t8, double _t14, double _t17, double _t20, double _t26, double _t40, double _t41, double _t42) {
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, dest);
        slerp_s56f5a5dd_tail_fma(d, t, _t8, _t7, _t46, _t26, _t14, _t41, _t20, _t42, _t17, _t40);
        return d;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_s960f8c7a_1_mulAdd(double otherX, double otherY, double otherZ, double t, Double3 dest, Double3Impl d, double _t7, double _t8, double _t14, double _t17, double _t20, double _t26, double _t40, double _t41, double _t42) {
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, dest);
        slerp_s56f5a5dd_tail_mulAdd(d, t, _t8, _t7, _t46, _t26, _t14, _t41, _t20, _t42, _t17, _t40);
        return d;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_fma(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        double _r0 = this.x;
        double _r1 = this.y;
        double _r2 = this.z;
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_r0, _r1, _r2);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _r2 * _t2;
        double _t13 = _r0 * _t2;
        double _t14 = _r1 * _t2;
        double _t15 = java.lang.Math.min(_t2, _t1);
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_s4977c373_1_fma(otherX, otherY, otherZ, t, (Double3Impl) dest, _r0, _r1, _r2, _t9, _t10, _t11, 1.0 / _t15, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, _t24 * _t25, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_mulAdd(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        double _r0 = this.x;
        double _r1 = this.y;
        double _r2 = this.z;
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_r0, _r1, _r2);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _r2 * _t2;
        double _t13 = _r0 * _t2;
        double _t14 = _r1 * _t2;
        double _t15 = java.lang.Math.min(_t2, _t1);
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_s4977c373_1_mulAdd(otherX, otherY, otherZ, t, (Double3Impl) dest, _r0, _r1, _r2, _t9, _t10, _t11, 1.0 / _t15, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, _t24 * _t25, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_1_fma(double otherX, double otherY, double otherZ, double t, Double3Impl d, double _r0, double _r1, double _r2, double _t9, double _t10, double _t11, double _t15_inv, double _t28, double _t31, double _t33, double _t35, double _t46, double _t48, double _t49, double _t50, double _t52) {
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        return slerp_degenerate_s4977c373_2_fma(otherX, otherY, otherZ, t, d, _r0, _r1, _r2, _t15_inv, _t31, _t33, _t35, _t46, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t85, _t86, _t87, java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)), _t91, (1.0 / java.lang.Math.sqrt(_t91)), t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_1_mulAdd(double otherX, double otherY, double otherZ, double t, Double3Impl d, double _r0, double _r1, double _r2, double _t9, double _t10, double _t11, double _t15_inv, double _t28, double _t31, double _t33, double _t35, double _t46, double _t48, double _t49, double _t50, double _t52) {
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        return slerp_degenerate_s4977c373_2_mulAdd(otherX, otherY, otherZ, t, d, _r0, _r1, _r2, _t15_inv, _t31, _t33, _t35, _t46, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t85, _t86, _t87, ((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))), _t91, (1.0 / java.lang.Math.sqrt(_t91)), t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_2_fma(double otherX, double otherY, double otherZ, double t, Double3Impl d, double _r0, double _r1, double _r2, double _t15_inv, double _t31, double _t33, double _t35, double _t46, double _t48, double _t53, double _t69, double _t70, double _t71, double _t85, double _t86, double _t87, double _t88, double _t91, double _t93, double _t95) {
        double _t99 = _t48 * Math.sin(_t95);
        double _t100 = _t48 * Math.cos(_t95);
        double _t104, _t105, _t106;
        if (_t91 > 0.0) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        d.x = _t46 > 0.0 ? java.lang.Math.fma(_t99, _t53 < 0.0 ? _t88 <= 5.048709793414476E-29 ? _t69 : _t104 : _t104, _t100 * _t33) * _t15_inv : java.lang.Math.fma(t, otherX - _r0, _r0);
        d.y = _t46 > 0.0 ? java.lang.Math.fma(_t99, _t53 < 0.0 ? _t88 <= 5.048709793414476E-29 ? _t71 : _t106 : _t106, _t100 * _t35) * _t15_inv : java.lang.Math.fma(t, otherY - _r1, _r1);
        d.z = _t46 > 0.0 ? java.lang.Math.fma(_t99, _t53 < 0.0 ? _t88 <= 5.048709793414476E-29 ? _t70 : _t105 : _t105, _t100 * _t31) * _t15_inv : java.lang.Math.fma(t, otherZ - _r2, _r2);
        return d;
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_2_mulAdd(double otherX, double otherY, double otherZ, double t, Double3Impl d, double _r0, double _r1, double _r2, double _t15_inv, double _t31, double _t33, double _t35, double _t46, double _t48, double _t53, double _t69, double _t70, double _t71, double _t85, double _t86, double _t87, double _t88, double _t91, double _t93, double _t95) {
        double _t99 = _t48 * Math.sin(_t95);
        double _t100 = _t48 * Math.cos(_t95);
        double _t104, _t105, _t106;
        if (_t91 > 0.0) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        d.x = _t46 > 0.0 ? ((_t99) * (_t53 < 0.0 ? _t88 <= 5.048709793414476E-29 ? _t69 : _t104 : _t104) + (_t100 * _t33)) * _t15_inv : ((t) * (otherX - _r0) + (_r0));
        d.y = _t46 > 0.0 ? ((_t99) * (_t53 < 0.0 ? _t88 <= 5.048709793414476E-29 ? _t71 : _t106 : _t106) + (_t100 * _t35)) * _t15_inv : ((t) * (otherY - _r1) + (_r1));
        d.z = _t46 > 0.0 ? ((_t99) * (_t53 < 0.0 ? _t88 <= 5.048709793414476E-29 ? _t70 : _t105 : _t105) + (_t100 * _t31)) * _t15_inv : ((t) * (otherZ - _r2) + (_r2));
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
    public Double3 absolute(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
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
    public Double3 acos(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.acos(this.x);
        d.y = Math.acos(this.y);
        d.z = Math.acos(this.z);
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
    public Double3 addScaled(Double3R b, double scalar, @Mutated Double3 dest) {
        double bX = b.x();
        double bY = b.y();
        double bZ = b.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            d.z = java.lang.Math.fma(scalar, bZ, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
            d.z = ((scalar) * (bZ) + (this.z));
            return d;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}) scaled by {@code scalar} to this vector and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}, {@code bZ}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double3 addScaled(double bX, double bY, double bZ, double scalar, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            d.z = java.lang.Math.fma(scalar, bZ, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
            d.z = ((scalar) * (bZ) + (this.z));
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
    public Double3 addScaled(Double3R b, Double3R c, @Mutated Double3 dest) {
        double bX = b.x();
        double bY = b.y();
        double bZ = b.z();
        double cX = c.x();
        double cY = c.y();
        double cZ = c.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            d.z = java.lang.Math.fma(bZ, cZ, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            d.z = ((bZ) * (cZ) + (this.z));
            return d;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}) scaled by ({@code cX}, {@code cY}, {@code cZ}) to
     * this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 addScaled(double bX, double bY, double bZ, double cX, double cY, double cZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            d.z = java.lang.Math.fma(bZ, cZ, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            d.z = ((bZ) * (cZ) + (this.z));
            return d;
        }
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
    public double angleBetween(Double3R other) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        if (Math.useFma()) {
            double _t6 = java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
            double _t7 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _t8 = java.lang.Math.fma(otherZ, this.x, -(otherX * this.z));
            double _ct0 = java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_fma(otherX, otherY, otherZ);
            return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            double _t6 = ((otherZ) * (this.y) - (otherY * this.z));
            double _t7 = ((otherY) * (this.x) - (otherX * this.y));
            double _t8 = ((otherZ) * (this.x) - (otherX * this.z));
            double _ct0 = ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_mulAdd(otherX, otherY, otherZ);
            return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))));
        }
    }


    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the angle in radians between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public double angleBetween(double otherX, double otherY, double otherZ) {
        if (Math.useFma()) {
            double _t6 = java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
            double _t7 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _t8 = java.lang.Math.fma(otherZ, this.x, -(otherX * this.z));
            double _ct0 = java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_fma(otherX, otherY, otherZ);
            return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            double _t6 = ((otherZ) * (this.y) - (otherY * this.z));
            double _t7 = ((otherY) * (this.x) - (otherX * this.y));
            double _t8 = ((otherZ) * (this.x) - (otherX * this.z));
            double _ct0 = ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_mulAdd(otherX, otherY, otherZ);
            return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))));
        }
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate_fma(double otherX, double otherY, double otherZ) {
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(this.x, this.y, this.z);
        double _t8 = otherZ * _t0;
        double _t9 = this.y * _t1;
        double _t10 = otherY * _t0;
        double _t11 = this.z * _t1;
        double _t12 = this.x * _t1;
        double _t13 = otherX * _t0;
        double _t20 = java.lang.Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = java.lang.Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = java.lang.Math.fma(_t8, _t12, -(_t13 * _t11));
        double _t23 = unitScale(_t21, _t22, _t20);
        double _t27 = _t20 * _t23;
        double _t28 = _t21 * _t23;
        double _t29 = _t22 * _t23;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate_mulAdd(double otherX, double otherY, double otherZ) {
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(this.x, this.y, this.z);
        double _t8 = otherZ * _t0;
        double _t9 = this.y * _t1;
        double _t10 = otherY * _t0;
        double _t11 = this.z * _t1;
        double _t12 = this.x * _t1;
        double _t13 = otherX * _t0;
        double _t20 = ((_t8) * (_t9) - (_t10 * _t11));
        double _t21 = ((_t10) * (_t12) - (_t13 * _t9));
        double _t22 = ((_t8) * (_t12) - (_t13 * _t11));
        double _t23 = unitScale(_t21, _t22, _t20);
        double _t27 = _t20 * _t23;
        double _t28 = _t21 * _t23;
        double _t29 = _t22 * _t23;
        return Math.atan2(java.lang.Math.sqrt(((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t23);
    }


    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 asin(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.asin(this.x);
        d.y = Math.asin(this.y);
        d.z = Math.asin(this.z);
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
    public Double3 atan(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.atan(this.x);
        d.y = Math.atan(this.y);
        d.z = Math.atan(this.z);
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
    public Double3 atan2(double x, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.atan2(this.x, x);
        d.y = Math.atan2(this.y, x);
        d.z = Math.atan2(this.z, x);
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
    public Double3 atan2(Double3R x, @Mutated Double3 dest) {
        double xY = x.y();
        double xZ = x.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.atan2(this.x, x.x());
        d.y = Math.atan2(this.y, xY);
        d.z = Math.atan2(this.z, xZ);
        return d;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}, {@code xZ}) (the denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xX the {@code x} component of the vector {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the vector {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the vector {@code (xX, xY, xZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 atan2(double xX, double xY, double xZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.atan2(this.x, xX);
        d.y = Math.atan2(this.y, xY);
        d.z = Math.atan2(this.z, xZ);
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
    public Double3 cbrt(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.cbrt(this.x);
        d.y = Math.cbrt(this.y);
        d.z = Math.cbrt(this.z);
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
    public Double3 ceil(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.ceil(this.x);
        d.y = Math.ceil(this.y);
        d.z = Math.ceil(this.z);
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
    public Double3 clamp(double min, double max, @Mutated Double3 dest) {
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
    public Double3 clamp(Double3R min, Double3R max, @Mutated Double3 dest) {
        double minY = min.y();
        double minZ = min.z();
        double maxY = max.y();
        double maxZ = max.z();
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
    public Double3 clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        return d;
    }


    /**
     * Compute the point on the line segment between {@code lineStart} and {@code lineEnd} that is
     * closest to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param lineStart the start point of the line segment
     * @param lineEnd the end point of the line segment
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointOnLine(Double3R lineStart, Double3R lineEnd, @Mutated Double3 dest) {
        double lineStartX = lineStart.x();
        double lineStartY = lineStart.y();
        double lineStartZ = lineStart.z();
        Double3Impl d = (Double3Impl) dest;
        double _t0 = lineEnd.z() - lineStartZ;
        double _t1 = lineEnd.x() - lineStartX;
        double _t2 = lineEnd.y() - lineStartY;
        double _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, Math.fma(_t0, this.z - lineStartZ, Math.fma(_t1, this.x - lineStartX, _t2 * (this.y - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            d.x = Math.fma(_t1, _t14, lineStartX);
            d.y = Math.fma(_t2, _t14, lineStartY);
            d.z = Math.fma(_t0, _t14, lineStartZ);
        } else {
            d.x = lineStartX;
            d.y = lineStartY;
            d.z = lineStartZ;
        }
        return d;
    }


    /**
     * Compute the point on the line segment between ({@code lineStartX}, {@code lineStartY},
     * {@code lineStartZ}) and ({@code lineEndX}, {@code lineEndY}, {@code lineEndZ}) that is
     * closest to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param lineStartX the {@code x} component of the vector
     *        {@code (lineStartX, lineStartY, lineStartZ)}
     * @param lineStartY the {@code y} component of the vector
     *        {@code (lineStartX, lineStartY, lineStartZ)}
     * @param lineStartZ the {@code z} component of the vector
     *        {@code (lineStartX, lineStartY, lineStartZ)}
     * @param lineEndX the {@code x} component of the vector {@code (lineEndX, lineEndY, lineEndZ)}
     * @param lineEndY the {@code y} component of the vector {@code (lineEndX, lineEndY, lineEndZ)}
     * @param lineEndZ the {@code z} component of the vector {@code (lineEndX, lineEndY, lineEndZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointOnLine(double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, Math.fma(_t0, this.z - lineStartZ, Math.fma(_t1, this.x - lineStartX, _t2 * (this.y - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            d.x = Math.fma(_t1, _t14, lineStartX);
            d.y = Math.fma(_t2, _t14, lineStartY);
            d.z = Math.fma(_t0, _t14, lineStartZ);
        } else {
            d.x = lineStartX;
            d.y = lineStartY;
            d.z = lineStartZ;
        }
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
        return this.z + (this.x + this.y);
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public double compMax() {
        return java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public double compMin() {
        return java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public double compMul() {
        return this.z * this.x * this.y;
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
    public Double3 copySign(double sign, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.copySign(this.x, sign);
        d.y = Math.copySign(this.y, sign);
        d.z = Math.copySign(this.z, sign);
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
    public Double3 copySign(Double3R sign, @Mutated Double3 dest) {
        double signY = sign.y();
        double signZ = sign.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.copySign(this.x, sign.x());
        d.y = Math.copySign(this.y, signY);
        d.z = Math.copySign(this.z, signZ);
        return d;
    }


    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}, {@code signZ}) onto the
     * corresponding component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param signX the {@code x} component of the vector {@code (signX, signY, signZ)}
     * @param signY the {@code y} component of the vector {@code (signX, signY, signZ)}
     * @param signZ the {@code z} component of the vector {@code (signX, signY, signZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 copySign(double signX, double signY, double signZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.copySign(this.x, signX);
        d.y = Math.copySign(this.y, signY);
        d.z = Math.copySign(this.z, signZ);
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
    public Double3 cos(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.cos(this.x);
        d.y = Math.cos(this.y);
        d.z = Math.cos(this.z);
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
    public Double3 cosh(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.cosh(this.x);
        d.y = Math.cosh(this.y);
        d.z = Math.cosh(this.z);
        return d;
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
    public Double3 cross(Double3R other, @Mutated Double3 dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(otherZ, _rd1, -(otherY * this.z));
            d.y = java.lang.Math.fma(otherX, this.z, -(otherZ * _rd0));
            d.z = java.lang.Math.fma(otherY, _rd0, -(otherX * _rd1));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((otherZ) * (_rd1) - (otherY * this.z));
            d.y = ((otherX) * (this.z) - (otherZ * _rd0));
            d.z = ((otherY) * (_rd0) - (otherX * _rd1));
            return d;
        }
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
    public Double3 cross(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(otherZ, _rd1, -(otherY * this.z));
            d.y = java.lang.Math.fma(otherX, this.z, -(otherZ * _rd0));
            d.z = java.lang.Math.fma(otherY, _rd0, -(otherX * _rd1));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((otherZ) * (_rd1) - (otherY * this.z));
            d.y = ((otherX) * (this.z) - (otherZ * _rd0));
            d.z = ((otherY) * (_rd0) - (otherX * _rd1));
            return d;
        }
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
    public Double3 degrees(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.toDegrees(this.x);
        d.y = Math.toDegrees(this.y);
        d.z = Math.toDegrees(this.z);
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
    public double distance(Double3R other) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        if (Math.useFma()) {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }


    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the distance between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public double distance(double otherX, double otherY, double otherZ) {
        if (Math.useFma()) {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
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
    public double distanceSquared(Double3R other) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        if (Math.useFma()) {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public double distanceSquared(double otherX, double otherY, double otherZ) {
        if (Math.useFma()) {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = this.z - otherZ;
            double _t1 = this.x - otherX;
            double _t2 = this.y - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
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
    public double dot(Double3R other) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        if (Math.useFma()) {
            return java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y));
        } else {
            return ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))));
        }
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public double dot(double otherX, double otherY, double otherZ) {
        if (Math.useFma()) {
            return java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y));
        } else {
            return ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))));
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
    public Double3 exp(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.exp(this.x);
        d.y = Math.exp(this.y);
        d.z = Math.exp(this.z);
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
    public Double3 exp2(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.pow(2.0, this.x);
        d.y = Math.pow(2.0, this.y);
        d.z = Math.pow(2.0, this.z);
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
    public Double3 expm1(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.expm1(this.x);
        d.y = Math.expm1(this.y);
        d.z = Math.expm1(this.z);
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
    public Double3 faceforward(Double3R I, Double3R Nref, @Mutated Double3 dest) {
        double IX = I.x();
        double IY = I.y();
        double IZ = I.z();
        double NrefX = Nref.x();
        double NrefY = Nref.y();
        double NrefZ = Nref.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t3;
            d.y = this.y * _t3;
            d.z = this.z * _t3;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t3;
            d.y = this.y * _t3;
            d.z = this.z * _t3;
            return d;
        }
    }


    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ), (IX, IY, IZ))} is
     * negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}) as judged by the reference vector ({@code NrefX}, {@code NrefY},
     * {@code NrefZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 faceforward(double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t3;
            d.y = this.y * _t3;
            d.z = this.z * _t3;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t3;
            d.y = this.y * _t3;
            d.z = this.z * _t3;
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
    public Double3 floor(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.floor(this.x);
        d.y = Math.floor(this.y);
        d.z = Math.floor(this.z);
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
    public Double3 fract(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(this.x - Math.floor(this.x), 0.9999999999999999);
        d.y = java.lang.Math.min(this.y - Math.floor(this.y), 0.9999999999999999);
        d.z = java.lang.Math.min(this.z - Math.floor(this.z), 0.9999999999999999);
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
    public Double3 hypot(double y, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.hypot(this.x, y);
        d.y = Math.hypot(this.y, y);
        d.z = Math.hypot(this.z, y);
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
    public Double3 hypot(Double3R y, @Mutated Double3 dest) {
        double yY = y.y();
        double yZ = y.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.hypot(this.x, y.x());
        d.y = Math.hypot(this.y, yY);
        d.z = Math.hypot(this.z, yZ);
        return d;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY},
     * {@code yZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 hypot(double yX, double yY, double yZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.hypot(this.x, yX);
        d.y = Math.hypot(this.y, yY);
        d.z = Math.hypot(this.z, yZ);
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
    public Double3 inverse(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = 1.0 / this.x;
        d.y = 1.0 / this.y;
        d.z = 1.0 / this.z;
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
    public Double3 inverseSqrt(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = (1.0 / java.lang.Math.sqrt(this.x));
        d.y = (1.0 / java.lang.Math.sqrt(this.y));
        d.z = (1.0 / java.lang.Math.sqrt(this.z));
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
        if (Math.useFma()) {
            return java.lang.Math.sqrt(java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        } else {
            return java.lang.Math.sqrt(((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y)))));
        }
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public double lengthSquared() {
        if (Math.useFma()) {
            return java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        } else {
            return ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
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
    public Double3 log(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.log(this.x);
        d.y = Math.log(this.y);
        d.z = Math.log(this.z);
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
    public Double3 log10(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.log10(this.x);
        d.y = Math.log10(this.y);
        d.z = Math.log10(this.z);
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
    public Double3 log1p(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.log1p(this.x);
        d.y = Math.log1p(this.y);
        d.z = Math.log1p(this.z);
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
    public Double3 log2(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.log2(this.x);
        d.y = Math.log2(this.y);
        d.z = Math.log2(this.z);
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
    public double manhattanDistance(Double3R other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y()) + java.lang.Math.abs(this.z - other.z());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public double manhattanDistance(double otherX, double otherY, double otherZ) {
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public double manhattanLength() {
        return java.lang.Math.abs(this.x) + java.lang.Math.abs(this.y) + java.lang.Math.abs(this.z);
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
    public Double3 max(double scalar, @Mutated Double3 dest) {
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(Double3R other, @Mutated Double3 dest) {
        double otherY = other.y();
        double otherZ = other.z();
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(double scalar, @Mutated Double3 dest) {
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(Double3R other, @Mutated Double3 dest) {
        double otherY = other.y();
        double otherZ = other.z();
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
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
    public Double3 mod(double y, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = flooredMod(this.x, y);
        d.y = flooredMod(this.y, y);
        d.z = flooredMod(this.z, y);
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
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mod(Double3R y, @Mutated Double3 dest) {
        double yX = y.x();
        double yY = y.y();
        double yZ = y.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
        d.z = flooredMod(this.z, yZ);
        return d;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}) ({@code x % y}, plus {@code y} when that remainder is non-zero and its sign
     * differs from {@code y}'s - exactly Kotlin's {@code mod}) and store the result in
     * {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (yX, yY, yZ)} must be non-zero.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mod(double yX, double yY, double yZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
        d.z = flooredMod(this.z, yZ);
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
    public Double3 nextDown(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.nextDown(this.x);
        d.y = Math.nextDown(this.y);
        d.z = Math.nextDown(this.z);
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
    public Double3 nextUp(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.nextUp(this.x);
        d.y = Math.nextUp(this.y);
        d.z = Math.nextUp(this.z);
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
    public Double3 normalize(@Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            return d;
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
    public Double3 normalizeMul(double length, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
            } else {
                d.x = 0.0;
                d.y = 0.0;
                d.z = 0.0;
            }
            return d;
        }
    }


    /**
     * Compute the signed angle in radians between this vector and {@code other}, positive when the
     * rotation from this vector to {@code other} is counter-clockwise as seen from the direction of
     * the given normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: {@code normal} must be non-zero.
     *
     * @param other the vector to measure the signed angle to
     * @param normal the reference axis that defines the sign of the angle
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise as seen from the
     *        direction of the given normal
     */
    public double orientedAngle(Double3R other, Double3R normal) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double normalX = normal.x();
        double normalY = normal.y();
        double normalZ = normal.z();
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = Math.fma(otherY, this.x, -(otherX * this.y));
        double _t10 = Math.fma(otherX, this.z, -(otherZ * this.x));
        double _t11 = Math.fma(otherZ, this.y, -(otherY * this.z));
        double _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return (Math.useFma() ? orientedAngle_degenerate_fma(otherX, otherY, otherZ, normalX, normalY, normalZ) : orientedAngle_degenerate_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ));
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? -_t18 : _t18;
    }


    /**
     * Compute the signed angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), positive when the rotation from this vector to ({@code otherX},
     * {@code otherY}, {@code otherZ}) is counter-clockwise as seen from the direction of the given
     * normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @return the signed angle in radians between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ}), positive when the rotation from this vector to ({@code otherX},
     *        {@code otherY}, {@code otherZ}) is counter-clockwise as seen from the direction of the
     *        given normal
     */
    public double orientedAngle(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = Math.fma(otherY, this.x, -(otherX * this.y));
        double _t10 = Math.fma(otherX, this.z, -(otherZ * this.x));
        double _t11 = Math.fma(otherZ, this.y, -(otherY * this.z));
        double _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return (Math.useFma() ? orientedAngle_degenerate_fma(otherX, otherY, otherZ, normalX, normalY, normalZ) : orientedAngle_degenerate_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ));
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? -_t18 : _t18;
    }

    /** Private tail of {@code orientedAngle_degenerate}; reached only through it. */
    private double orientedAngle_degenerate_s1c8ffe1d_tail_fma(double _t11, double _t14, double _t13, double _t10, double _t24, double _t23, double _t9, double _t12, double normalZ, double _t0, double normalX, double normalY) {
        double _t25 = java.lang.Math.fma(_t11, _t14, -(_t13 * _t10));
        double _t27 = unitScale(_t24, _t25, _t23);
        double _t31 = _t23 * _t27;
        double _t32 = _t24 * _t27;
        double _t33 = _t25 * _t27;
        double _t40 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t33, _t33, _t32 * _t32))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return java.lang.Math.fma(normalZ * _t0, _t31, java.lang.Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0 ? -_t40 : _t40;
    }

    /** Private tail of {@code orientedAngle_degenerate}; reached only through it. */
    private double orientedAngle_degenerate_s1c8ffe1d_tail_mulAdd(double _t11, double _t14, double _t13, double _t10, double _t24, double _t23, double _t9, double _t12, double normalZ, double _t0, double normalX, double normalY) {
        double _t25 = ((_t11) * (_t14) - (_t13 * _t10));
        double _t27 = unitScale(_t24, _t25, _t23);
        double _t31 = _t23 * _t27;
        double _t32 = _t24 * _t27;
        double _t33 = _t25 * _t27;
        double _t40 = Math.atan2(java.lang.Math.sqrt(((_t31) * (_t31) + (((_t33) * (_t33) + (_t32 * _t32))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t27);
        return ((normalZ * _t0) * (_t31) + (((normalX * _t0) * (_t32) + (normalY * _t0 * _t33)))) < 0.0 ? -_t40 : _t40;
    }

    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate_fma(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherY * _t1;
        double _t10 = this.x * _t2;
        double _t11 = otherX * _t1;
        double _t12 = this.y * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = this.z * _t2;
        return orientedAngle_degenerate_s1c8ffe1d_tail_fma(_t11, _t14, _t13, _t10, java.lang.Math.fma(_t13, _t12, -(_t9 * _t14)), java.lang.Math.fma(_t9, _t10, -(_t11 * _t12)), _t9, _t12, normalZ, unitScale(normalX, normalY, normalZ), normalX, normalY);
    }

    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate_mulAdd(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherY * _t1;
        double _t10 = this.x * _t2;
        double _t11 = otherX * _t1;
        double _t12 = this.y * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = this.z * _t2;
        return orientedAngle_degenerate_s1c8ffe1d_tail_mulAdd(_t11, _t14, _t13, _t10, ((_t13) * (_t12) - (_t9 * _t14)), ((_t9) * (_t10) - (_t11 * _t12)), _t9, _t12, normalZ, unitScale(normalX, normalY, normalZ), normalX, normalY);
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
    public Double3x3 outerProduct(Double3R row, @Mutated Double3x3 dest) {
        double rowX = row.x();
        double rowY = row.y();
        double rowZ = row.z();
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m20 = rowX * this.z;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.m21 = rowY * this.z;
        d.m02 = rowZ * this.x;
        d.m12 = rowZ * this.y;
        d.m22 = rowZ * this.z;
        d.properties = 0;
        return d;
    }


    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}, {@code rowZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 outerProduct(double rowX, double rowY, double rowZ, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m20 = rowX * this.z;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.m21 = rowY * this.z;
        d.m02 = rowZ * this.x;
        d.m12 = rowZ * this.y;
        d.m22 = rowZ * this.z;
        d.properties = 0;
        return d;
    }


    /**
     * Compute a vector perpendicular to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 perpendicular(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        if (java.lang.Math.abs(this.z) < java.lang.Math.abs(this.x)) {
            double _buf0 = this.y;
            d.y = -this.x;
            d.z = 0.0;
            d.x = _buf0;
        } else {
            d.x = 0.0;
            double _buf0 = this.z;
            d.z = -this.y;
            d.y = _buf0;
        }
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
    public Double3 pow(double exponent, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.pow(this.x, exponent);
        d.y = Math.pow(this.y, exponent);
        d.z = Math.pow(this.z, exponent);
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
    public Double3 pow(Double3R exponent, @Mutated Double3 dest) {
        double exponentY = exponent.y();
        double exponentZ = exponent.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.pow(this.x, exponent.x());
        d.y = Math.pow(this.y, exponentY);
        d.z = Math.pow(this.z, exponentZ);
        return d;
    }


    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponentX the {@code x} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @param exponentY the {@code y} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @param exponentZ the {@code z} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 pow(double exponentX, double exponentY, double exponentZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.pow(this.x, exponentX);
        d.y = Math.pow(this.y, exponentY);
        d.z = Math.pow(this.z, exponentZ);
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
    public Double3 project(Double3R onto, @Mutated Double3 dest) {
        double ontoX = onto.x();
        double ontoY = onto.y();
        double ontoZ = onto.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t7 = java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y)) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            d.x = ontoX * _t7;
            d.y = ontoY * _t7;
            d.z = ontoZ * _t7;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t7 = ((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            d.x = ontoX * _t7;
            d.y = ontoY * _t7;
            d.z = ontoZ * _t7;
            return d;
        }
    }


    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}, {@code ontoZ}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code (ontoX, ontoY, ontoZ)} must be non-zero.
     *
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @param ontoZ the {@code z} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 project(double ontoX, double ontoY, double ontoZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t7 = java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y)) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            d.x = ontoX * _t7;
            d.y = ontoY * _t7;
            d.z = ontoZ * _t7;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t7 = ((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            d.x = ontoX * _t7;
            d.y = ontoY * _t7;
            d.z = ontoZ * _t7;
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
    public Double3 projectOnPlane(Double3R normal, @Mutated Double3 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        double normalZ = normal.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            d.x = java.lang.Math.fma(-normalX, _t2, this.x);
            d.y = java.lang.Math.fma(-normalY, _t2, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t2, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            d.x = ((-normalX) * (_t2) + (this.x));
            d.y = ((-normalY) * (_t2) + (this.y));
            d.z = ((-normalZ) * (_t2) + (this.z));
            return d;
        }
    }


    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 projectOnPlane(double normalX, double normalY, double normalZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            d.x = java.lang.Math.fma(-normalX, _t2, this.x);
            d.y = java.lang.Math.fma(-normalY, _t2, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t2, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t2 = ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            d.x = ((-normalX) * (_t2) + (this.x));
            d.y = ((-normalY) * (_t2) + (this.y));
            d.z = ((-normalZ) * (_t2) + (this.z));
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
    public Double3 radians(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.toRadians(this.x);
        d.y = Math.toRadians(this.y);
        d.z = Math.toRadians(this.z);
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
    public Double3 reflect(Double3R normal, @Mutated Double3 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        double normalZ = normal.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            d.x = java.lang.Math.fma(-normalX, _t3, this.x);
            d.y = java.lang.Math.fma(-normalY, _t3, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t3, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = 2.0 * ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            d.x = ((-normalX) * (_t3) + (this.x));
            d.y = ((-normalY) * (_t3) + (this.y));
            d.z = ((-normalZ) * (_t3) + (this.z));
            return d;
        }
    }


    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 reflect(double normalX, double normalY, double normalZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            d.x = java.lang.Math.fma(-normalX, _t3, this.x);
            d.y = java.lang.Math.fma(-normalY, _t3, this.y);
            d.z = java.lang.Math.fma(-normalZ, _t3, this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t3 = 2.0 * ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            d.x = ((-normalX) * (_t3) + (this.x));
            d.y = ((-normalY) * (_t3) + (this.y));
            d.z = ((-normalZ) * (_t3) + (this.z));
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
    public Double3 refract(Double3R normal, double eta, @Mutated Double3 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        double normalZ = normal.z();
        Double3Impl d = (Double3Impl) dest;
        double _t3 = Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        double _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0), eta * eta, 1.0);
        double _t10 = Math.fma(eta, _t3, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            d.x = Math.fma(eta, this.x, -(normalX * _t10));
            d.y = Math.fma(eta, this.y, -(normalY * _t10));
            d.z = Math.fma(eta, this.z, -(normalZ * _t10));
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
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
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length; this vector must have
     * unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    public Double3 refract(double normalX, double normalY, double normalZ, double eta, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t3 = Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        double _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0), eta * eta, 1.0);
        double _t10 = Math.fma(eta, _t3, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            d.x = Math.fma(eta, this.x, -(normalX * _t10));
            d.y = Math.fma(eta, this.y, -(normalY * _t10));
            d.z = Math.fma(eta, this.z, -(normalZ * _t10));
        } else {
            d.x = 0.0;
            d.y = 0.0;
            d.z = 0.0;
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
    public Double3 round(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.rint(this.x);
        d.y = Math.rint(this.y);
        d.z = Math.rint(this.z);
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
    public Double3 sign(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
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
    public Double3 sin(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.sin(this.x);
        d.y = Math.sin(this.y);
        d.z = Math.sin(this.z);
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
    public Double3 sinh(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.sinh(this.x);
        d.y = Math.sinh(this.y);
        d.z = Math.sinh(this.z);
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
    public Double3 smoothstep(double edge0, double edge1, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0) * _t0_inv));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0) * _t0_inv));
            double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.z - edge0) * _t0_inv));
            d.x = java.lang.Math.fma(-2.0, _t10, 3.0) * _t10 * _t10;
            d.y = java.lang.Math.fma(-2.0, _t11, 3.0) * _t11 * _t11;
            d.z = java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0) * _t0_inv));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0) * _t0_inv));
            double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.z - edge0) * _t0_inv));
            d.x = ((-2.0) * (_t10) + (3.0)) * _t10 * _t10;
            d.y = ((-2.0) * (_t11) + (3.0)) * _t11 * _t11;
            d.z = ((-2.0) * (_t12) + (3.0)) * _t12 * _t12;
            return d;
        }
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
    public Double3 smoothstep(Double3R edge0, Double3R edge1, @Mutated Double3 dest) {
        return smoothstep(edge0.x(), edge0.y(), edge0.z(), edge1.x(), edge1.y(), edge1.z(), dest);
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}) and the upper edge
     * ({@code edge1X}, {@code edge1Y}, {@code edge1Z}), yielding 0 at or below the lower edge and 1
     * at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (edge0X, edge0Y, edge0Z)} and {@code (edge1X, edge1Y, edge1Z)} must
     * differ in every component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y, edge0Z)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y, edge0Z)}
     * @param edge0Z the {@code z} component of the vector {@code (edge0X, edge0Y, edge0Z)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y, edge1Z)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y, edge1Z)}
     * @param edge1Z the {@code z} component of the vector {@code (edge1X, edge1Y, edge1Z)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 smoothstep(double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.z - edge0Z) / (edge1Z - edge0Z)));
            d.x = java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12;
            d.y = java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13;
            d.z = java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.z - edge0Z) / (edge1Z - edge0Z)));
            d.x = ((-2.0) * (_t12) + (3.0)) * _t12 * _t12;
            d.y = ((-2.0) * (_t13) + (3.0)) * _t13 * _t13;
            d.z = ((-2.0) * (_t14) + (3.0)) * _t14 * _t14;
            return d;
        }
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
    public Double3 sqrt(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.sqrt(this.x);
        d.y = java.lang.Math.sqrt(this.y);
        d.z = java.lang.Math.sqrt(this.z);
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
    public Double3 step(double edge, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x < edge ? 0.0 : 1.0;
        d.y = this.y < edge ? 0.0 : 1.0;
        d.z = this.z < edge ? 0.0 : 1.0;
        return d;
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
    public Double3 step(Double3R edge, @Mutated Double3 dest) {
        double edgeY = edge.y();
        double edgeZ = edge.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x < edge.x() ? 0.0 : 1.0;
        d.y = this.y < edgeY ? 0.0 : 1.0;
        d.z = this.z < edgeZ ? 0.0 : 1.0;
        return d;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 step(double edgeX, double edgeY, double edgeZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x < edgeX ? 0.0 : 1.0;
        d.y = this.y < edgeY ? 0.0 : 1.0;
        d.z = this.z < edgeZ ? 0.0 : 1.0;
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
    public Double3 tan(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.tan(this.x);
        d.y = Math.tan(this.y);
        d.z = Math.tan(this.z);
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
    public Double3 tanh(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.tanh(this.x);
        d.y = Math.tanh(this.y);
        d.z = Math.tanh(this.z);
        return d;
    }


    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize((p1 - this) x (p2 - this))} - it points to the side from which the vertices
     * {@code this}, {@code p1}, {@code p2} appear counter-clockwise (a degenerate triangle yields
     * the zero vector) and store the result in {@code dest}.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the second vertex of the triangle (this vector is the first)
     * @param p2 the third vertex of the triangle
     * @param dest will hold the result
     * @return dest
     */
    public Double3 triangleNormal(Double3R p1, Double3R p2, @Mutated Double3 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p2Z = p2.z();
        Double3Impl d = (Double3Impl) dest;
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return (Math.useFma() ? triangleNormal_degenerate_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z, dest) : triangleNormal_degenerate_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, dest));
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        d.x = _t12 * _t19;
        d.y = _t14 * _t19;
        d.z = _t13 * _t19;
        return d;
    }


    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize(((p1X, p1Y, p1Z) - this) x ((p2X, p2Y, p2Z) - this))} - it points to the
     * side from which the vertices {@code this}, ({@code p1X}, {@code p1Y}, {@code p1Z}),
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) appear counter-clockwise (a degenerate triangle
     * yields the zero vector) and store the result in {@code dest}.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 triangleNormal(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return (Math.useFma() ? triangleNormal_degenerate_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z, dest) : triangleNormal_degenerate_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, dest));
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        d.x = _t12 * _t19;
        d.y = _t14 * _t19;
        d.z = _t13 * _t19;
        return d;
    }

    /** Private store group 0 of {@code triangleNormal_degenerate}: computes and stores it; reached only through it. */
    private void triangleNormal_degenerate_s2f821417_c0(Double3Impl _dst, double _t76, double _t77, double _t72, double _t73, double _t71) {
        _dst.x = _t76 != 0.0 ? _t77 * _t72 : 0.0;
        _dst.y = _t76 != 0.0 ? _t77 * _t73 : 0.0;
        _dst.z = _t76 != 0.0 ? _t77 * _t71 : 0.0;
    }

    /** Private tail of {@code triangleNormal_degenerate}; reached only through it. */
    private void triangleNormal_degenerate_s2f821417_tail_fma(Double3Impl _dst, double _t43, double _t45, double _t40, double _t44, double _t52, double _t53, double _t54, double _t55) {
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t64 = java.lang.Math.fma(_t52, _t53, -(_t54 * _t55));
        double _t65 = java.lang.Math.fma(_t54, _t56, -(_t57 * _t53));
        double _t66 = java.lang.Math.fma(_t57, _t55, -(_t52 * _t56));
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _t76 = java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, _t73 * _t73));
        triangleNormal_degenerate_s2f821417_c0(_dst, _t76, (1.0 / java.lang.Math.sqrt(_t76)), _t72, _t73, _t71);
    }

    /** Private tail of {@code triangleNormal_degenerate}; reached only through it. */
    private void triangleNormal_degenerate_s2f821417_tail_mulAdd(Double3Impl _dst, double _t43, double _t45, double _t40, double _t44, double _t52, double _t53, double _t54, double _t55) {
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t64 = ((_t52) * (_t53) - (_t54 * _t55));
        double _t65 = ((_t54) * (_t56) - (_t57 * _t53));
        double _t66 = ((_t57) * (_t55) - (_t52 * _t56));
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _t76 = ((_t71) * (_t71) + (((_t72) * (_t72) + (_t73 * _t73))));
        triangleNormal_degenerate_s2f821417_c0(_dst, _t76, (1.0 / java.lang.Math.sqrt(_t76)), _t72, _t73, _t71);
    }

    /**
     * Out-of-range path of {@code triangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 triangleNormal_degenerate_fma(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.z;
        double _r1 = this.x;
        double _r2 = this.y;
        double _t19 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(p1Z)), java.lang.Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r1), java.lang.Math.abs(p1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_r2))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = _r1 * _t19;
        double _t32 = _r2 * _t19;
        double _t34 = _r0 * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        triangleNormal_degenerate_s2f821417_tail_fma(d, _t43, _t45, _t40, _t44, _t38 * _t44, _t41 * _t45, _t39 * _t44, _t42 * _t45);
        return d;
    }

    /**
     * Out-of-range path of {@code triangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 triangleNormal_degenerate_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _r0 = this.z;
        double _r1 = this.x;
        double _r2 = this.y;
        double _t19 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(p1Z)), java.lang.Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r1), java.lang.Math.abs(p1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_r2))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = _r1 * _t19;
        double _t32 = _r2 * _t19;
        double _t34 = _r0 * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        triangleNormal_degenerate_s2f821417_tail_mulAdd(d, _t43, _t45, _t40, _t44, _t38 * _t44, _t41 * _t45, _t39 * _t44, _t42 * _t45);
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
    public Double3 trunc(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x >= 0.0 ? Math.floor(this.x) : Math.ceil(this.x);
        d.y = this.y >= 0.0 ? Math.floor(this.y) : Math.ceil(this.y);
        d.z = this.z >= 0.0 ? Math.floor(this.z) : Math.ceil(this.z);
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
    public Double3 ulp(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.ulp(this.x);
        d.y = Math.ulp(this.y);
        d.z = Math.ulp(this.z);
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
    public Double4 xyz0(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 0.0;
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
    public Double4 xyz1(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 1.0;
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
    public Double3 preMul(Double3x3R mat, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(mat.m02(), this.z, java.lang.Math.fma(mat.m00(), _rd0, mat.m01() * _rd1));
            d.y = java.lang.Math.fma(mat.m12(), this.z, java.lang.Math.fma(mat.m10(), _rd0, mat.m11() * _rd1));
            d.z = java.lang.Math.fma(mat.m22(), this.z, java.lang.Math.fma(mat.m20(), _rd0, mat.m21() * _rd1));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((mat.m02()) * (this.z) + (((mat.m00()) * (_rd0) + (mat.m01() * _rd1))));
            d.y = ((mat.m12()) * (this.z) + (((mat.m10()) * (_rd0) + (mat.m11() * _rd1))));
            d.z = ((mat.m22()) * (this.z) + (((mat.m20()) * (_rd0) + (mat.m21() * _rd1))));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulDirection(Double3x4R mat, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(mat.m02(), this.z, java.lang.Math.fma(mat.m00(), _rd0, mat.m01() * _rd1));
            d.y = java.lang.Math.fma(mat.m12(), this.z, java.lang.Math.fma(mat.m10(), _rd0, mat.m11() * _rd1));
            d.z = java.lang.Math.fma(mat.m22(), this.z, java.lang.Math.fma(mat.m20(), _rd0, mat.m21() * _rd1));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((mat.m02()) * (this.z) + (((mat.m00()) * (_rd0) + (mat.m01() * _rd1))));
            d.y = ((mat.m12()) * (this.z) + (((mat.m10()) * (_rd0) + (mat.m11() * _rd1))));
            d.z = ((mat.m22()) * (this.z) + (((mat.m20()) * (_rd0) + (mat.m21() * _rd1))));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulDirection(Double4x4R mat, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(mat.m02(), this.z, java.lang.Math.fma(mat.m00(), _rd0, mat.m01() * _rd1));
            d.y = java.lang.Math.fma(mat.m12(), this.z, java.lang.Math.fma(mat.m10(), _rd0, mat.m11() * _rd1));
            d.z = java.lang.Math.fma(mat.m22(), this.z, java.lang.Math.fma(mat.m20(), _rd0, mat.m21() * _rd1));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((mat.m02()) * (this.z) + (((mat.m00()) * (_rd0) + (mat.m01() * _rd1))));
            d.y = ((mat.m12()) * (this.z) + (((mat.m10()) * (_rd0) + (mat.m11() * _rd1))));
            d.z = ((mat.m22()) * (this.z) + (((mat.m20()) * (_rd0) + (mat.m21() * _rd1))));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulPosition(Double3x4R mat, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, java.lang.Math.fma(mat.m01(), _rd1, java.lang.Math.fma(mat.m02(), this.z, mat.m03())));
            d.y = java.lang.Math.fma(mat.m10(), _rd0, java.lang.Math.fma(mat.m11(), _rd1, java.lang.Math.fma(mat.m12(), this.z, mat.m13())));
            d.z = java.lang.Math.fma(mat.m20(), _rd0, java.lang.Math.fma(mat.m21(), _rd1, java.lang.Math.fma(mat.m22(), this.z, mat.m23())));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((mat.m00()) * (_rd0) + (((mat.m01()) * (_rd1) + (((mat.m02()) * (this.z) + (mat.m03()))))));
            d.y = ((mat.m10()) * (_rd0) + (((mat.m11()) * (_rd1) + (((mat.m12()) * (this.z) + (mat.m13()))))));
            d.z = ((mat.m20()) * (_rd0) + (((mat.m21()) * (_rd1) + (((mat.m22()) * (this.z) + (mat.m23()))))));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulPosition(Double4x4R mat, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, java.lang.Math.fma(mat.m01(), _rd1, java.lang.Math.fma(mat.m02(), this.z, mat.m03())));
            d.y = java.lang.Math.fma(mat.m10(), _rd0, java.lang.Math.fma(mat.m11(), _rd1, java.lang.Math.fma(mat.m12(), this.z, mat.m13())));
            d.z = java.lang.Math.fma(mat.m20(), _rd0, java.lang.Math.fma(mat.m21(), _rd1, java.lang.Math.fma(mat.m22(), this.z, mat.m23())));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _rd0 = this.x;
            double _rd1 = this.y;
            d.x = ((mat.m00()) * (_rd0) + (((mat.m01()) * (_rd1) + (((mat.m02()) * (this.z) + (mat.m03()))))));
            d.y = ((mat.m10()) * (_rd0) + (((mat.m11()) * (_rd1) + (((mat.m12()) * (this.z) + (mat.m13()))))));
            d.z = ((mat.m20()) * (_rd0) + (((mat.m21()) * (_rd1) + (((mat.m22()) * (this.z) + (mat.m23()))))));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1},
     * then perform a perspective divide - i.e. compute {@code r = mat * (this, 1)} and return
     * {@code r.xyz / r.w} and store the result in {@code dest}.
     * <p>
     * Valid input: this vector must not be mapped to {@code w = 0} by {@code mat}.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulProject(Double4x4R mat, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t2_inv = 1.0 / Math.fma(mat.m30(), this.x, Math.fma(mat.m31(), this.y, Math.fma(mat.m32(), this.z, mat.m33())));
        double _rd0 = this.x;
        double _rd1 = this.y;
        d.x = Math.fma(mat.m00(), _rd0, Math.fma(mat.m01(), _rd1, Math.fma(mat.m02(), this.z, mat.m03()))) * _t2_inv;
        d.y = Math.fma(mat.m10(), _rd0, Math.fma(mat.m11(), _rd1, Math.fma(mat.m12(), this.z, mat.m13()))) * _t2_inv;
        d.z = Math.fma(mat.m20(), _rd0, Math.fma(mat.m21(), _rd1, Math.fma(mat.m22(), this.z, mat.m23()))) * _t2_inv;
        return d;
    }


    /**
     * Rotate this vector by the quaternion {@code quat}, i.e. compute {@code q * this * q^-1} and
     * store the result in {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotate(DoubleQuatR quat, @Mutated Double3 dest) {
        double quatX = quat.x();
        double quatY = quat.y();
        double quatZ = quat.z();
        double quatW = quat.w();
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(quatX, this.y, -(quatY * this.x));
        double _t10 = 2.0 * Math.fma(quatZ, this.x, -(quatX * this.z));
        double _t11 = 2.0 * Math.fma(quatY, this.z, -(quatZ * this.y));
        d.x = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, this.x)));
        d.y = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.y)));
        d.z = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, this.z)));
        return d;
    }


    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}), i.e. compute {@code q * this * q^-1} and store the result in {@code dest}.
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
    public Double3 rotate(double quatX, double quatY, double quatZ, double quatW, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(quatX, this.y, -(quatY * this.x));
        double _t10 = 2.0 * Math.fma(quatZ, this.x, -(quatX * this.z));
        double _t11 = 2.0 * Math.fma(quatY, this.z, -(quatZ * this.y));
        d.x = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, this.x)));
        d.y = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.y)));
        d.z = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, this.z)));
        return d;
    }


    /**
     * Rotate this vector by the quaternion {@code quat} about the point {@code pivot}, i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateAround(DoubleQuatR quat, Double3R pivot, @Mutated Double3 dest) {
        double quatX = quat.x();
        double quatY = quat.y();
        double quatZ = quat.z();
        double quatW = quat.w();
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.y - pivot.y();
        double _t1 = this.x - pivot.x();
        double _t2 = this.z - pivot.z();
        double _t12 = 2.0 * Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * Math.fma(quatY, _t2, -(quatZ * _t0));
        d.x = Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, this.x)));
        d.y = Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, this.y)));
        d.z = Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, this.z)));
        return d;
    }


    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}) about the point ({@code pivotX}, {@code pivotY}, {@code pivotZ}), i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateAround(double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.y - pivotY;
        double _t1 = this.x - pivotX;
        double _t2 = this.z - pivotZ;
        double _t12 = 2.0 * Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * Math.fma(quatY, _t2, -(quatZ * _t0));
        d.x = Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, this.x)));
        d.y = Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, this.y)));
        d.z = Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, this.z)));
        return d;
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis {@code axis} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateAxis(double angle, Double3R axis, @Mutated Double3 dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) and store the result in {@code dest}.
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
    public Double3 rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated Double3 dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        Double3Impl d = (Double3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        double _rd0 = this.x;
        double _rd1 = this.y;
        d.x = Math.fma(_t3, axisX * _t5, Math.fma(_rd0, _t1, Math.fma(axisY, this.z, -(axisZ * _rd1)) * _t0));
        d.y = Math.fma(_t3, axisY * _t5, Math.fma(_rd1, _t1, Math.fma(axisZ, _rd0, -(axisX * this.z)) * _t0));
        d.z = Math.fma(_t3, axisZ * _t5, Math.fma(this.z, _t1, Math.fma(axisX, _rd1, -(axisY * _rd0)) * _t0));
        return d;
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis {@code axis} through the point
     * {@code pivot} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateAxisAround(double angle, Double3R axis, Double3R pivot, @Mutated Double3 dest) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        double pivotZ = pivot.z();
        Double3Impl d = (Double3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = this.x - pivotX;
        double _t3 = this.z - pivotZ;
        double _t4 = this.y - pivotY;
        double _t5 = 1.0 - _t1;
        double _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        d.x = Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX)));
        d.y = Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY)));
        d.z = Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ)));
        return d;
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) through the point ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateAxisAround(double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = this.x - pivotX;
        double _t3 = this.z - pivotZ;
        double _t4 = this.y - pivotY;
        double _t5 = 1.0 - _t1;
        double _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        d.x = Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX)));
        d.y = Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY)));
        d.z = Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ)));
        return d;
    }


    /**
     * Rotate this vector by the inverse of the given rotation and store the result in {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateInverse(DoubleQuatR quat, @Mutated Double3 dest) {
        double quatX = quat.x();
        double quatY = quat.y();
        double quatZ = quat.z();
        double quatW = quat.w();
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(quatX, this.z, -(quatZ * this.x));
        double _t10 = 2.0 * Math.fma(quatY, this.x, -(quatX * this.y));
        double _t11 = 2.0 * Math.fma(quatZ, this.y, -(quatY * this.z));
        d.x = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, this.x)));
        d.y = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, this.y)));
        d.z = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.z)));
        return d;
    }


    /**
     * Rotate this vector by the inverse of the given rotation and store the result in {@code dest}.
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
    public Double3 rotateInverse(double quatX, double quatY, double quatZ, double quatW, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(quatX, this.z, -(quatZ * this.x));
        double _t10 = 2.0 * Math.fma(quatY, this.x, -(quatX * this.y));
        double _t11 = 2.0 * Math.fma(quatZ, this.y, -(quatY * this.z));
        d.x = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, this.x)));
        d.y = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, this.y)));
        d.z = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.z)));
        return d;
    }


    /**
     * Rotate this vector by {@code angle} radians about the X axis and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateX(double angle, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.y;
            d.x = this.x;
            d.y = java.lang.Math.fma(_rd0, _t1, -(this.z * _t0));
            d.z = java.lang.Math.fma(_rd0, _t0, this.z * _t1);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.y;
            d.x = this.x;
            d.y = ((_rd0) * (_t1) - (this.z * _t0));
            d.z = ((_rd0) * (_t0) + (this.z * _t1));
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the X axis through the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateXAround(double angle, Double3R pivot, @Mutated Double3 dest) {
        double pivotY = pivot.y();
        double pivotZ = pivot.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            d.x = this.x;
            d.y = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotY));
            d.z = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotZ));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            d.x = this.x;
            d.y = ((_t2) * (_t1) + (((-_t3) * (_t0) + (pivotY))));
            d.z = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotZ))));
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the X axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateXAround(double angle, double pivotX, double pivotY, double pivotZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            d.x = this.x;
            d.y = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotY));
            d.z = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotZ));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            d.x = this.x;
            d.y = ((_t2) * (_t1) + (((-_t3) * (_t0) + (pivotY))));
            d.z = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotZ))));
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the Y axis and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateY(double angle, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(_rd0, _t1, this.z * _t0);
            d.y = this.y;
            d.z = java.lang.Math.fma(this.z, _t1, -(_rd0 * _t0));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.x;
            d.x = ((_rd0) * (_t1) + (this.z * _t0));
            d.y = this.y;
            d.z = ((this.z) * (_t1) - (_rd0 * _t0));
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the Y axis through the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateYAround(double angle, Double3R pivot, @Mutated Double3 dest) {
        double pivotX = pivot.x();
        double pivotZ = pivot.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            d.x = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotX));
            d.y = this.y;
            d.z = java.lang.Math.fma(_t3, _t1, java.lang.Math.fma(-_t2, _t0, pivotZ));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            d.x = ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotX))));
            d.y = this.y;
            d.z = ((_t3) * (_t1) + (((-_t2) * (_t0) + (pivotZ))));
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the Y axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateYAround(double angle, double pivotX, double pivotY, double pivotZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            d.x = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotX));
            d.y = this.y;
            d.z = java.lang.Math.fma(_t3, _t1, java.lang.Math.fma(-_t2, _t0, pivotZ));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            d.x = ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotX))));
            d.y = this.y;
            d.z = ((_t3) * (_t1) + (((-_t2) * (_t0) + (pivotZ))));
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the Z axis and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateZ(double angle, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(_rd0, _t1, -(this.y * _t0));
            d.y = java.lang.Math.fma(_rd0, _t0, this.y * _t1);
            d.z = this.z;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.x;
            d.x = ((_rd0) * (_t1) - (this.y * _t0));
            d.y = ((_rd0) * (_t0) + (this.y * _t1));
            d.z = this.z;
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the Z axis through the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateZAround(double angle, Double3R pivot, @Mutated Double3 dest) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotX));
            d.y = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotY));
            d.z = this.z;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = ((_t2) * (_t1) + (((-_t3) * (_t0) + (pivotX))));
            d.y = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotY))));
            d.z = this.z;
            return d;
        }
    }


    /**
     * Rotate this vector by {@code angle} radians about the Z axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateZAround(double angle, double pivotX, double pivotY, double pivotZ, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotX));
            d.y = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotY));
            d.z = this.z;
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = ((_t2) * (_t1) + (((-_t3) * (_t0) + (pivotX))));
            d.y = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotY))));
            d.z = this.z;
            return d;
        }
    }

    public double x() { return this.x; }
    public double y() { return this.y; }
    public double z() { return this.z; }

    public Double2 xx(@Mutated Double2 dest) {
        double _v0 = this.x;
        Double2Impl d = (Double2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Double2 xy(@Mutated Double2 dest) {
        double _v1 = this.y;
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Double2 xz(@Mutated Double2 dest) {
        double _v1 = this.z;
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Double2 yx(@Mutated Double2 dest) {
        double _v1 = this.x;
        Double2Impl d = (Double2Impl) dest;
        d.x = this.y;
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
        double _v1 = this.z;
        Double2Impl d = (Double2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Double2 zx(@Mutated Double2 dest) {
        double _v1 = this.x;
        Double2Impl d = (Double2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Double2 zy(@Mutated Double2 dest) {
        double _v1 = this.y;
        Double2Impl d = (Double2Impl) dest;
        d.x = this.z;
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
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 xyz(@Mutated Double3 dest) {
        double _v1 = this.y;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x;
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
        double _v1 = this.z;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 xzz(@Mutated Double3 dest) {
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 yxx(@Mutated Double3 dest) {
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.y;
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
        double _v1 = this.x;
        double _v2 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.y;
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

    public Double3 yzx(@Mutated Double3 dest) {
        double _v1 = this.z;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.y;
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
        double _v1 = this.z;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 zxx(@Mutated Double3 dest) {
        double _v1 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Double3 zxy(@Mutated Double3 dest) {
        double _v1 = this.x;
        double _v2 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.z;
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

    public Double3 zyx(@Mutated Double3 dest) {
        double _v1 = this.y;
        double _v2 = this.x;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Double3 zyy(@Mutated Double3 dest) {
        double _v1 = this.y;
        Double3Impl d = (Double3Impl) dest;
        d.x = this.z;
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
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 xyyz(@Mutated Double4 dest) {
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
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
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 xyzz(@Mutated Double4 dest) {
        double _v1 = this.y;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
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
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 xzyz(@Mutated Double4 dest) {
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
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
        double _v1 = this.z;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 xzzz(@Mutated Double4 dest) {
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 yxxx(@Mutated Double4 dest) {
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
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
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
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

    public Double4 yxzx(@Mutated Double4 dest) {
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
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
        double _v1 = this.x;
        double _v2 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
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

    public Double4 yzxx(@Mutated Double4 dest) {
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
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
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
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

    public Double4 yzzx(@Mutated Double4 dest) {
        double _v1 = this.z;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
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
        double _v1 = this.z;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zxxx(@Mutated Double4 dest) {
        double _v1 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Double4 zxxy(@Mutated Double4 dest) {
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
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

    public Double4 zxyx(@Mutated Double4 dest) {
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Double4 zxyy(@Mutated Double4 dest) {
        double _v1 = this.x;
        double _v2 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
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

    public Double4 zyxx(@Mutated Double4 dest) {
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Double4 zyxy(@Mutated Double4 dest) {
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
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

    public Double4 zyyx(@Mutated Double4 dest) {
        double _v1 = this.y;
        double _v2 = this.x;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Double4 zyyy(@Mutated Double4 dest) {
        double _v1 = this.y;
        Double4Impl d = (Double4Impl) dest;
        d.x = this.z;
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

    @Override public String toString() {
        return "Double3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double3Impl)) return false;
        Double3Impl o = (Double3Impl) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y)
            && Double.doubleToLongBits(z) == Double.doubleToLongBits(o.z);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y)
            && Double.isFinite(z);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y)
            || Double.isNaN(z);
    }

    @Override public boolean equalsEpsilon(Double3R other, double epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }
    public @Mutated Double3 load(double[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    @Mutated public Double3 load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double3 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Double3 loadRelative(DoubleBuffer buf) {
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
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Double3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Double3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Double3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Double3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Double3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public Double3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public Double3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.x;
        dest[offset + 1] = (float) this.y;
        dest[offset + 2] = (float) this.z;
        return dest;
    }
    public @Mutated Double3 load(float[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    @Mutated public Double3 load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double3 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Double3 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }
    public Double3 loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public Double3 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public Double3 loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3 r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return r;
    }
    public Double3 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public Double3 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    @Mutated public Double3 loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }
    public Double3 loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
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
