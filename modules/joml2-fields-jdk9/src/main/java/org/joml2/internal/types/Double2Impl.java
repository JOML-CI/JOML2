// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Double2Impl implements Double2 {

    public double x;
    public double y;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2BbOpsUnsafe()
                        : new Double2BbOpsApi();
        static final Double2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2RawOpsUnsafe()
                        : new Double2RawOpsApi();
    }

    public Double2Impl() {
    }

    public Double2Impl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Double2Impl(Double2R src) {
        this.x = src.x();
        this.y = src.y();
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
    public Double2 add(Double2R other, @Mutated Double2 dest) {
        double otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 add(double otherX, double otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
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
    public Double2 div(double scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x / scalar;
        d.y = this.y / scalar;
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
    public Double2 div(Double2R other, @Mutated Double2 dest) {
        double otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x / other.x();
        d.y = this.y / otherY;
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 div(double otherX, double otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x / otherX;
        d.y = this.y / otherY;
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
    public Double2 fma(double b, Double2R c, @Mutated Double2 dest) {
        double cX = c.x();
        double cY = c.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
            return d;
        }
    }


    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}), i.e.
     * compute {@code this * b + (cX, cY)} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 fma(double b, double cX, double cY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.x, b, cX);
            d.y = java.lang.Math.fma(this.y, b, cY);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.x) * (b) + (cX));
            d.y = ((this.y) * (b) + (cY));
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
    public Double2 fma(Double2R b, Double2R c, @Mutated Double2 dest) {
        double bX = b.x();
        double bY = b.y();
        double cX = c.x();
        double cY = c.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
            return d;
        }
    }


    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}) and add ({@code cX},
     * {@code cY}), i.e. compute {@code this * (bX, bY) + (cX, cY)} per component and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 fma(double bX, double bY, double cX, double cY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.x, bX, cX);
            d.y = java.lang.Math.fma(this.y, bY, cY);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.x) * (bX) + (cX));
            d.y = ((this.y) * (bY) + (cY));
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
    public Double2 mul(double scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
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
    public Double2 mul(Double2R other, @Mutated Double2 dest) {
        double otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(double otherX, double otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
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
    public Double2 negate(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
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
    public Double2 sub(Double2R other, @Mutated Double2 dest) {
        double otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 sub(double otherX, double otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Set this vector to the unit vector at the angle {@code 2 PI u} counter-clockwise from the x
     * axis: samples uniformly distributed in {@code [0, 1)} give a direction uniformly distributed
     * on the unit circle ({@code makeRandomDirection} draws them from a {@link java.util.Random}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param u the fraction of a full turn counter-clockwise from the x axis, uniformly distributed
     *        in {@code [0, 1)} for a uniformly distributed direction
     * @return this
     */
    @Mutated public Double2 makeUniformDirection(double u) {
        double _t0 = u * 6.283185307179586;
        double _t1 = Math.sin(_t0);
        this.x = Math.cosFromSin(_t1, _t0);
        this.y = _t1;
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
    public @Mutated Double2 set(Double2R v) {
        double vY = v.y();
        this.x = v.x();
        this.y = vY;
        return this;
    }


    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this
     */
    @Mutated public Double2 set(double vX, double vY) {
        this.x = vX;
        this.y = vY;
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
    public Double2 set(double s, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = s;
        d.y = s;
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
    public Float2 toFloat(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = (float) (this.x);
        d.y = (float) (this.y);
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
    public Byte2 toByte(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
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
    public Byte2 toByte(RoundingMode roundingMode, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        switch (roundingMode) {
            case TRUNCATE: return toByte(dest);
            case FLOOR: {
                d.x = (byte) Math.floor(this.x);
                d.y = (byte) Math.floor(this.y);
            } break;
            case CEILING: {
                d.x = (byte) Math.ceil(this.x);
                d.y = (byte) Math.ceil(this.y);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                d.x = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y)));
            } break;
            case HALF_AWAY_FROM_ZERO: {
                d.x = (byte) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (byte) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
            } break;
            case HALF_EVEN: {
                d.x = (byte) Math.rint(this.x);
                d.y = (byte) Math.rint(this.y);
            } break;
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
    public Short2 toShort(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
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
    public Short2 toShort(RoundingMode roundingMode, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        switch (roundingMode) {
            case TRUNCATE: return toShort(dest);
            case FLOOR: {
                d.x = (short) Math.floor(this.x);
                d.y = (short) Math.floor(this.y);
            } break;
            case CEILING: {
                d.x = (short) Math.ceil(this.x);
                d.y = (short) Math.ceil(this.y);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                d.x = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y)));
            } break;
            case HALF_AWAY_FROM_ZERO: {
                d.x = (short) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (short) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
            } break;
            case HALF_EVEN: {
                d.x = (short) Math.rint(this.x);
                d.y = (short) Math.rint(this.y);
            } break;
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
    public Int2 toInt(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = (int) (this.x);
        d.y = (int) (this.y);
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
    public Int2 toInt(RoundingMode roundingMode, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        switch (roundingMode) {
            case TRUNCATE: return toInt(dest);
            case FLOOR: {
                d.x = (int) Math.floor(this.x);
                d.y = (int) Math.floor(this.y);
            } break;
            case CEILING: {
                d.x = (int) Math.ceil(this.x);
                d.y = (int) Math.ceil(this.y);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                d.x = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x)));
                d.y = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y)));
            } break;
            case HALF_AWAY_FROM_ZERO: {
                d.x = (int) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (int) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
            } break;
            case HALF_EVEN: {
                d.x = (int) Math.rint(this.x);
                d.y = (int) Math.rint(this.y);
            } break;
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
    public Long2 toLong(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = (long) (this.x);
        d.y = (long) (this.y);
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
    public Long2 toLong(RoundingMode roundingMode, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        switch (roundingMode) {
            case TRUNCATE: return toLong(dest);
            case FLOOR: {
                d.x = (long) Math.floor(this.x);
                d.y = (long) Math.floor(this.y);
            } break;
            case CEILING: {
                d.x = (long) Math.ceil(this.x);
                d.y = (long) Math.ceil(this.y);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                d.x = Math.round(this.x);
                d.y = Math.round(this.y);
            } break;
            case HALF_AWAY_FROM_ZERO: {
                d.x = (long) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x));
                d.y = (long) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y));
            } break;
            case HALF_EVEN: {
                d.x = (long) Math.rint(this.x);
                d.y = (long) Math.rint(this.y);
            } break;
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
    @Mutated public Double2 makeZero() {
        this.x = 0.0;
        this.y = 0.0;
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
    public Double2 bezier(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest) {
        return bezier(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y(), t, dest);
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}) and ({@code p2X}, {@code p2Y}) and ends at ({@code p3X},
     * {@code p3Y}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y})
     * at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}) and ({@code p2X},
     * {@code p2Y}) pull it towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 bezier(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = 1.0 - t;
            double _t1 = t * t;
            double _t2 = t * _t1;
            double _t3 = _t0 * _t0;
            double _t6 = 3.0 * _t0 * _t1;
            double _t7 = 3.0 * t * _t3;
            double _t8 = _t0 * _t3;
            d.x = java.lang.Math.fma(p1X, _t7, this.x * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2);
            d.y = java.lang.Math.fma(p1Y, _t7, this.y * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = 1.0 - t;
            double _t1 = t * t;
            double _t2 = t * _t1;
            double _t3 = _t0 * _t0;
            double _t6 = 3.0 * _t0 * _t1;
            double _t7 = 3.0 * t * _t3;
            double _t8 = _t0 * _t3;
            d.x = ((p1X) * (_t7) + (this.x * _t8)) + ((p2X) * (_t6) + (p3X * _t2));
            d.y = ((p1Y) * (_t7) + (this.y * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2));
            return d;
        }
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
    public Double2 bezier2(Double2R p1, Double2R p2, double t, @Mutated Double2 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4));
            d.y = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = ((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4))));
            d.y = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4))));
            return d;
        }
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}) and ends at ({@code p2X}, {@code p2Y}) and store the
     * result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y})
     * at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}) pulls it towards itself but is
     * generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 bezier2(double p1X, double p1Y, double p2X, double p2Y, double t, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4));
            d.y = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            d.x = ((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4))));
            d.y = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4))));
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
    public Double2 bezier2Tangent(Double2R p1, Double2R p2, double t, @Mutated Double2 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1);
            d.y = java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = ((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1));
            d.y = ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1));
            return d;
        }
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}) and ends at ({@code p2X}, {@code p2Y}) and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y})
     * at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}) pulls it towards itself but is
     * generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 bezier2Tangent(double p1X, double p1Y, double p2X, double p2Y, double t, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1);
            d.y = java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            d.x = ((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1));
            d.y = ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1));
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
    public Double2 bezierTangent(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p3X = p3.x();
        double p3Y = p3.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            d.x = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5));
            d.y = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            d.x = ((p3X - p2X) * (_t2) + (((p1X - this.x) * (_t6) + ((p2X - p1X) * _t5))));
            d.y = ((p3Y - p2Y) * (_t2) + (((p1Y - this.y) * (_t6) + ((p2Y - p1Y) * _t5))));
            return d;
        }
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}) and ({@code p2X}, {@code p2Y}) and ends at ({@code p3X}, {@code p3Y}) and store
     * the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y})
     * at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}) and ({@code p2X},
     * {@code p2Y}) pull it towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 bezierTangent(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            d.x = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5));
            d.y = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            d.x = ((p3X - p2X) * (_t2) + (((p1X - this.x) * (_t6) + ((p2X - p1X) * _t5))));
            d.y = ((p3Y - p2Y) * (_t2) + (((p1Y - this.y) * (_t6) + ((p2Y - p1Y) * _t5))));
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
    public Double2 catmullRom(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p3X = p3.x();
        double p3Y = p3.y();
        Double2Impl d = (Double2Impl) dest;
        double _t0 = t * t;
        double _t1 = t * _t0;
        d.x = 0.5 * (Math.fma(2.0, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)) * _t1));
        d.y = 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)) * _t1));
        return d;
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}) to
     * ({@code p2X}, {@code p2Y}), with this vector as the control point before the segment and
     * ({@code p3X}, {@code p3Y}) as the control point after it and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}) at {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}) are
     * the spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}) and the
     * point after ({@code p2X}, {@code p2Y}): they only shape the tangents at the segment's two end
     * points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 catmullRom(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _t0 = t * t;
        double _t1 = t * _t0;
        d.x = 0.5 * (Math.fma(2.0, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)) * _t1));
        d.y = 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)) * _t1));
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
    public Double2 catmullRomTangent(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p3X = p3.x();
        double p3Y = p3.y();
        Double2Impl d = (Double2Impl) dest;
        double _t0 = t * t;
        d.x = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)), _t0, p2X - this.x));
        d.y = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)), _t0, p2Y - this.y));
        return d;
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}) to ({@code p2X}, {@code p2Y}),
     * with this vector as the control point before the segment and ({@code p3X}, {@code p3Y}) as
     * the control point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}) at {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}) are
     * the spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}) and the
     * point after ({@code p2X}, {@code p2Y}): they only shape the tangents at the segment's two end
     * points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 catmullRomTangent(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _t0 = t * t;
        d.x = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)), _t0, p2X - this.x));
        d.y = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)), _t0, p2Y - this.y));
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
    public Double2 hermite(Double2R t0, Double2R v1, Double2R t1, double t, @Mutated Double2 dest) {
        return hermite(t0.x(), t0.y(), v1.x(), v1.y(), t1.x(), t1.y(), t, dest);
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}) and ends at ({@code v1X}, {@code v1Y}) with the tangent
     * ({@code t1X}, {@code t1Y}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y})
     * at {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 hermite(double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t2 = t * _t0;
            double _t5 = t * java.lang.Math.fma(t, t, -t);
            double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
            double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
            double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
            d.x = java.lang.Math.fma(this.x, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9);
            d.y = java.lang.Math.fma(this.y, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t2 = t * _t0;
            double _t5 = t * ((t) * (t) - (t));
            double _t7 = ((t - 2.0) * (_t0) + (t));
            double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
            double _t10 = ((2.0) * (_t2) + (((-3.0) * (_t0) + (1.0))));
            d.x = ((this.x) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9));
            d.y = ((this.y) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9));
            return d;
        }
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
    public Double2 hermiteTangent(Double2R t0, Double2R v1, Double2R t1, double t, @Mutated Double2 dest) {
        return hermiteTangent(t0.x(), t0.y(), v1.x(), v1.y(), t1.x(), t1.y(), t, dest);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y})
     * and ends at ({@code v1X}, {@code v1Y}) with the tangent ({@code t1X}, {@code t1Y}) and store
     * the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y})
     * at {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 hermiteTangent(double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
            double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
            double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
            double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
            d.x = java.lang.Math.fma(this.x, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7);
            d.y = java.lang.Math.fma(this.y, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = t * t;
            double _t6 = 6.0 * ((t) * (t) - (t));
            double _t7 = 6.0 * ((-t) * (t) + (t));
            double _t8 = ((3.0) * (_t0) - (t + t));
            double _t9 = ((3.0) * (_t0) + (((-4.0) * (t) + (1.0))));
            d.x = ((this.x) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7));
            d.y = ((this.y) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7));
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
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 lerp(Double2R other, double t, @Mutated Double2 dest) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            return d;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}) using the
     * interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}) (interpolation factor {@code 1}). Each linearly interpolated
     * component is {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in
     * {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding of
     * {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 lerp(double otherX, double otherY, double t, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
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
    public Double2 lerp(Double2R other, Double2R t, @Mutated Double2 dest) {
        double otherX = other.x();
        double otherY = other.y();
        double tX = t.x();
        double tY = t.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
            return d;
        }
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}) using the
     * interpolation factor ({@code tX}, {@code tY}) and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}) (interpolation factor {@code 1}). Each linearly interpolated
     * component is {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in
     * {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding of
     * {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param tX the {@code x} component of the vector {@code (tX, tY)}
     * @param tY the {@code y} component of the vector {@code (tX, tY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 lerp(double otherX, double otherY, double tX, double tY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(tX, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(tY, otherY - this.y, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((tX) * (otherX - this.x) + (this.x));
            d.y = ((tY) * (otherY - this.y) + (this.y));
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
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * counter-clockwise perpendicular {@code (-y, x)} of this vector. The angle is computed with
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
    public Double2 slerp(Double2R other, double t, @Mutated Double2 dest) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) return slerp_fma(otherX, otherY, t, dest);
        return slerp_mulAdd(otherX, otherY, t, dest);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private void slerp_s3e62c569_tail_fma(Double2Impl _dst, double t, double _t6, double _t5, double _t35, double _t21, double _t12, double _t31, double _t16, double _t32) {
        double _t20 = t * java.lang.Math.sqrt(_t6) + (1.0 - t) * java.lang.Math.sqrt(_t5);
        double _t39 = t * Math.atan2(java.lang.Math.sqrt(_t35), _t21);
        {
            double _t44 = _t20 * Math.cos(_t39);
            double _sp0 = _t20 * Math.sin(_t39) * (1.0 / java.lang.Math.sqrt(_t35));
            _dst.x = java.lang.Math.fma(_t12, _t44, _sp0 * _t31);
            _dst.y = java.lang.Math.fma(_t16, _t44, _sp0 * _t32);
        }
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private void slerp_s3e62c569_tail_mulAdd(Double2Impl _dst, double t, double _t6, double _t5, double _t35, double _t21, double _t12, double _t31, double _t16, double _t32) {
        double _t20 = t * java.lang.Math.sqrt(_t6) + (1.0 - t) * java.lang.Math.sqrt(_t5);
        double _t39 = t * Math.atan2(java.lang.Math.sqrt(_t35), _t21);
        {
            double _t44 = _t20 * Math.cos(_t39);
            double _sp0 = _t20 * Math.sin(_t39) * (1.0 / java.lang.Math.sqrt(_t35));
            _dst.x = ((_t12) * (_t44) + (_sp0 * _t31));
            _dst.y = ((_t16) * (_t44) + (_sp0 * _t32));
        }
    }


    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY}) using the
     * interpolation factor {@code t}: the direction turns at a constant rate along the shorter arc
     * between the two directions, and the length changes linearly between the two lengths and store
     * the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * counter-clockwise perpendicular {@code (-y, x)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code double} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 slerp(double otherX, double otherY, double t, @Mutated Double2 dest) {
        if (Math.useFma()) return slerp_fma(otherX, otherY, t, dest);
        return slerp_mulAdd(otherX, otherY, t, dest);
    }

    /** {@code slerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2 slerp_fma(double otherX, double otherY, double t, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _r0 = this.x;
        double _r1 = this.y;
        double _t5 = java.lang.Math.fma(_r0, _r0, _r1 * _r1);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, t, dest);
        double _t6 = java.lang.Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, t, dest);
        double _t7 = (1.0 / java.lang.Math.sqrt(_t5));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t12 = _r0 * _t7;
        double _t16 = _r1 * _t7;
        double _t21 = java.lang.Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        double _t26 = java.lang.Math.fma(otherX, _t10, -(_t21 * _t12));
        double _t27 = java.lang.Math.fma(otherY, _t10, -(_t21 * _t16));
        double _t30 = -java.lang.Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = java.lang.Math.fma(_t30, _t12, _t26);
        double _t32 = java.lang.Math.fma(_t30, _t16, _t27);
        double _t35 = java.lang.Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, t, dest);
        slerp_s3e62c569_tail_fma(d, t, _t6, _t5, _t35, _t21, _t12, _t31, _t16, _t32);
        return d;
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2 slerp_mulAdd(double otherX, double otherY, double t, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _r0 = this.x;
        double _r1 = this.y;
        double _t5 = ((_r0) * (_r0) + (_r1 * _r1));
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, t, dest);
        double _t6 = ((otherX) * (otherX) + (otherY * otherY));
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, t, dest);
        double _t7 = (1.0 / java.lang.Math.sqrt(_t5));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t12 = _r0 * _t7;
        double _t16 = _r1 * _t7;
        double _t21 = ((otherX * _t10) * (_t12) + (otherY * _t10 * _t16));
        double _t26 = ((otherX) * (_t10) - (_t21 * _t12));
        double _t27 = ((otherY) * (_t10) - (_t21 * _t16));
        double _t30 = -((_t26) * (_t12) + (_t27 * _t16));
        double _t31 = ((_t30) * (_t12) + (_t26));
        double _t32 = ((_t30) * (_t16) + (_t27));
        double _t35 = ((_t31) * (_t31) + (_t32 * _t32));
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, t, dest);
        slerp_s3e62c569_tail_mulAdd(d, t, _t6, _t5, _t35, _t21, _t12, _t31, _t16, _t32);
        return d;
    }

    /** Private store group 0 of {@code slerp_degenerate}: computes and stores it; reached only through it. */
    private void slerp_degenerate_s3e62c569_c0_fma(Double2Impl _dst, double _t35, double _t68, double _t38, double _t56, double _t28, double _t73, double _t69, double _t25, double _t11_inv, double t, double otherX, double _r0, double _t72, double _t27, double otherY, double _r1) {
        _dst.x = _t35 > 0.0 ? java.lang.Math.fma(_t68, _t38 < 0.0 ? _t56 <= 5.048709793414476E-29 ? _t28 : _t73 : _t73, _t69 * _t25) * _t11_inv : java.lang.Math.fma(t, otherX - _r0, _r0);
        _dst.y = _t35 > 0.0 ? java.lang.Math.fma(_t68, _t38 < 0.0 ? _t56 <= 5.048709793414476E-29 ? _t25 : _t72 : _t72, _t69 * _t27) * _t11_inv : java.lang.Math.fma(t, otherY - _r1, _r1);
    }

    /** Private store group 0 of {@code slerp_degenerate}: computes and stores it; reached only through it. */
    private void slerp_degenerate_s3e62c569_c0_mulAdd(Double2Impl _dst, double _t35, double _t68, double _t38, double _t56, double _t28, double _t73, double _t69, double _t25, double _t11_inv, double t, double otherX, double _r0, double _t72, double _t27, double otherY, double _r1) {
        _dst.x = _t35 > 0.0 ? ((_t68) * (_t38 < 0.0 ? _t56 <= 5.048709793414476E-29 ? _t28 : _t73 : _t73) + (_t69 * _t25)) * _t11_inv : ((t) * (otherX - _r0) + (_r0));
        _dst.y = _t35 > 0.0 ? ((_t68) * (_t38 < 0.0 ? _t56 <= 5.048709793414476E-29 ? _t25 : _t72 : _t72) + (_t69 * _t27)) * _t11_inv : ((t) * (otherY - _r1) + (_r1));
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private void slerp_degenerate_s3e62c569_tail_fma(Double2Impl _dst, double _t22, double _t8, double _t38, double _t27, double _t43, double _t25, double t, double _t37, double _t28, double _t35, double _t11_inv, double otherX, double _r0, double otherY, double _r1) {
        double _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = java.lang.Math.fma(_t47, _t25, _t43);
        double _t49 = java.lang.Math.fma(_t47, _t27, _t44);
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        slerp_degenerate_s3e62c569_c0_fma(_dst, _t35, _t37 * Math.sin(_t64), _t38, java.lang.Math.fma(_t48, _t48, _t49 * _t49), _t28, _t73, _t37 * Math.cos(_t64), _t25, _t11_inv, t, otherX, _r0, _t72, _t27, otherY, _r1);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private void slerp_degenerate_s3e62c569_tail_mulAdd(Double2Impl _dst, double _t22, double _t8, double _t38, double _t27, double _t43, double _t25, double t, double _t37, double _t28, double _t35, double _t11_inv, double otherX, double _r0, double otherY, double _r1) {
        double _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        double _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        double _t48 = ((_t47) * (_t25) + (_t43));
        double _t49 = ((_t47) * (_t27) + (_t44));
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        slerp_degenerate_s3e62c569_c0_mulAdd(_dst, _t35, _t37 * Math.sin(_t64), _t38, ((_t48) * (_t48) + (_t49 * _t49)), _t28, _t73, _t37 * Math.cos(_t64), _t25, _t11_inv, t, otherX, _r0, _t72, _t27, otherY, _r1);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double2 slerp_degenerate_fma(double otherX, double otherY, double t, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _r0 = this.x;
        double _r1 = this.y;
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_r0, _r1, _r0);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _r0 * _t2;
        double _t10 = _r1 * _t2;
        double _t11 = java.lang.Math.min(_t2, _t1);
        double _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        slerp_degenerate_s3e62c569_tail_fma(d, _t22, _t8, _t38, _t27, java.lang.Math.fma(_t22, _t7, -(_t38 * _t25)), _t25, t, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), -_t27, _t18 * _t19, 1.0 / _t11, otherX, _r0, otherY, _r1);
        return d;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double2 slerp_degenerate_mulAdd(double otherX, double otherY, double t, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _r0 = this.x;
        double _r1 = this.y;
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_r0, _r1, _r0);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _r0 * _t2;
        double _t10 = _r1 * _t2;
        double _t11 = java.lang.Math.min(_t2, _t1);
        double _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        double _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        slerp_degenerate_s3e62c569_tail_mulAdd(d, _t22, _t8, _t38, _t27, ((_t22) * (_t7) - (_t38 * _t25)), _t25, t, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), -_t27, _t18 * _t19, 1.0 / _t11, otherX, _r0, otherY, _r1);
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
    public Double2 absolute(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
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
    public Double2 acos(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.acos(this.x);
        d.y = Math.acos(this.y);
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
    public Double2 addScaled(Double2R b, double scalar, @Mutated Double2 dest) {
        double bX = b.x();
        double bY = b.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
            return d;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}) scaled by {@code scalar} to this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double2 addScaled(double bX, double bY, double scalar, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(scalar, bX, this.x);
            d.y = java.lang.Math.fma(scalar, bY, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((scalar) * (bX) + (this.x));
            d.y = ((scalar) * (bY) + (this.y));
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
    public Double2 addScaled(Double2R b, Double2R c, @Mutated Double2 dest) {
        double bX = b.x();
        double bY = b.y();
        double cX = c.x();
        double cY = c.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            return d;
        }
    }


    /**
     * Add ({@code bX}, {@code bY}) scaled by ({@code cX}, {@code cY}) to this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 addScaled(double bX, double bY, double cX, double cY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(bX, cX, this.x);
            d.y = java.lang.Math.fma(bY, cY, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((bX) * (cX) + (this.x));
            d.y = ((bY) * (cY) + (this.y));
            return d;
        }
    }


    /**
     * Compute the angle in radians between this vector and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}
     */
    public double angleBetween(Double2R other) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.abs(java.lang.Math.fma(otherY, this.x, -(otherX * this.y)));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_fma(otherX, otherY);
            return Math.atan2(_ct0, java.lang.Math.fma(otherX, this.x, otherY * this.y));
        } else {
            double _ct0 = java.lang.Math.abs(((otherY) * (this.x) - (otherX * this.y)));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_mulAdd(otherX, otherY);
            return Math.atan2(_ct0, ((otherX) * (this.x) + (otherY * this.y)));
        }
    }


    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the angle in radians between this vector and ({@code otherX}, {@code otherY})
     */
    public double angleBetween(double otherX, double otherY) {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.abs(java.lang.Math.fma(otherY, this.x, -(otherX * this.y)));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_fma(otherX, otherY);
            return Math.atan2(_ct0, java.lang.Math.fma(otherX, this.x, otherY * this.y));
        } else {
            double _ct0 = java.lang.Math.abs(((otherY) * (this.x) - (otherX * this.y)));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_mulAdd(otherX, otherY);
            return Math.atan2(_ct0, ((otherX) * (this.x) + (otherY * this.y)));
        }
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate_fma(double otherX, double otherY) {
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(this.x, this.y, this.x);
        double _t6 = otherY * _t0;
        double _t7 = this.x * _t1;
        double _t8 = otherX * _t0;
        double _t9 = this.y * _t1;
        double _t12 = java.lang.Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), java.lang.Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate_mulAdd(double otherX, double otherY) {
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(this.x, this.y, this.x);
        double _t6 = otherY * _t0;
        double _t7 = this.x * _t1;
        double _t8 = otherX * _t0;
        double _t9 = this.y * _t1;
        double _t12 = ((_t6) * (_t7) - (_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), ((_t8) * (_t7) + (_t6 * _t9)) * _t13);
    }


    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2 asin(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.asin(this.x);
        d.y = Math.asin(this.y);
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
    public Double2 atan(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.atan(this.x);
        d.y = Math.atan(this.y);
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
    public Double2 atan2(double x, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.atan2(this.x, x);
        d.y = Math.atan2(this.y, x);
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
    public Double2 atan2(Double2R x, @Mutated Double2 dest) {
        double xY = x.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.atan2(this.x, x.x());
        d.y = Math.atan2(this.y, xY);
        return d;
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}) (the denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xX the {@code x} component of the vector {@code (xX, xY)}
     * @param xY the {@code y} component of the vector {@code (xX, xY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 atan2(double xX, double xY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.atan2(this.x, xX);
        d.y = Math.atan2(this.y, xY);
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
    public Double2 cbrt(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.cbrt(this.x);
        d.y = Math.cbrt(this.y);
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
    public Double2 ceil(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.ceil(this.x);
        d.y = Math.ceil(this.y);
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
    public Double2 clamp(double min, double max, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
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
    public Double2 clamp(Double2R min, Double2R max, @Mutated Double2 dest) {
        double minY = min.y();
        double maxY = max.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 clamp(double minX, double minY, double maxX, double maxY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
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
        return this.x + this.y;
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public double compMax() {
        return java.lang.Math.max(this.x, this.y);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public double compMin() {
        return java.lang.Math.min(this.x, this.y);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public double compMul() {
        return this.x * this.y;
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
    public Double2 copySign(double sign, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.copySign(this.x, sign);
        d.y = Math.copySign(this.y, sign);
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
    public Double2 copySign(Double2R sign, @Mutated Double2 dest) {
        double signY = sign.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.copySign(this.x, sign.x());
        d.y = Math.copySign(this.y, signY);
        return d;
    }


    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}) onto the corresponding
     * component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param signX the {@code x} component of the vector {@code (signX, signY)}
     * @param signY the {@code y} component of the vector {@code (signX, signY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 copySign(double signX, double signY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.copySign(this.x, signX);
        d.y = Math.copySign(this.y, signY);
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
    public Double2 cos(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.cos(this.x);
        d.y = Math.cos(this.y);
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
    public Double2 cosh(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.cosh(this.x);
        d.y = Math.cosh(this.y);
        return d;
    }


    /**
     * Compute the 2D cross product of this vector and {@code other}, in that order.
     * <p>
     * It is the z component of the cross product of the two vectors extended by {@code z = 0}, i.e.
     * the signed area of the parallelogram they span: positive when {@code other} points
     * counter-clockwise of this vector (with the x axis pointing right and the y axis pointing up).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @return the 2D cross product of this vector and {@code other}, in that order
     */
    public double cross(Double2R other) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) {
            return java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
        } else {
            return ((otherY) * (this.x) - (otherX * this.y));
        }
    }


    /**
     * Compute the 2D cross product of this vector and ({@code otherX}, {@code otherY}), in that
     * order.
     * <p>
     * It is the z component of the cross product of the two vectors extended by {@code z = 0}, i.e.
     * the signed area of the parallelogram they span: positive when ({@code otherX},
     * {@code otherY}) points counter-clockwise of this vector (with the x axis pointing right and
     * the y axis pointing up).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the 2D cross product of this vector and ({@code otherX}, {@code otherY}), in that
     *        order
     */
    public double cross(double otherX, double otherY) {
        if (Math.useFma()) {
            return java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
        } else {
            return ((otherY) * (this.x) - (otherX * this.y));
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
    public Double2 degrees(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.toDegrees(this.x);
        d.y = Math.toDegrees(this.y);
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
    public double distance(Double2R other) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, _t1 * _t1));
        } else {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (_t1 * _t1)));
        }
    }


    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the distance between this vector and ({@code otherX}, {@code otherY})
     */
    public double distance(double otherX, double otherY) {
        if (Math.useFma()) {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, _t1 * _t1));
        } else {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (_t1 * _t1)));
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
    public double distanceSquared(Double2R other) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return java.lang.Math.fma(_t0, _t0, _t1 * _t1);
        } else {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return ((_t0) * (_t0) + (_t1 * _t1));
        }
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY})
     */
    public double distanceSquared(double otherX, double otherY) {
        if (Math.useFma()) {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return java.lang.Math.fma(_t0, _t0, _t1 * _t1);
        } else {
            double _t0 = this.x - otherX;
            double _t1 = this.y - otherY;
            return ((_t0) * (_t0) + (_t1 * _t1));
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
    public double dot(Double2R other) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) {
            return java.lang.Math.fma(otherX, this.x, otherY * this.y);
        } else {
            return ((otherX) * (this.x) + (otherY * this.y));
        }
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY})
     */
    public double dot(double otherX, double otherY) {
        if (Math.useFma()) {
            return java.lang.Math.fma(otherX, this.x, otherY * this.y);
        } else {
            return ((otherX) * (this.x) + (otherY * this.y));
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
    public Double2 exp(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.exp(this.x);
        d.y = Math.exp(this.y);
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
    public Double2 exp2(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.pow(2.0, this.x);
        d.y = Math.pow(2.0, this.y);
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
    public Double2 expm1(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.expm1(this.x);
        d.y = Math.expm1(this.y);
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
    public Double2 faceforward(Double2R I, Double2R Nref, @Mutated Double2 dest) {
        double IX = I.x();
        double IY = I.y();
        double NrefX = Nref.x();
        double NrefY = Nref.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = java.lang.Math.fma(IX, NrefX, IY * NrefY) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t2;
            d.y = this.y * _t2;
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = ((IX) * (NrefX) + (IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t2;
            d.y = this.y * _t2;
            return d;
        }
    }


    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY), (IX, IY))} is negative, and
     * negated otherwise - orienting it against the incident direction ({@code IX}, {@code IY}) as
     * judged by the reference vector ({@code NrefX}, {@code NrefY}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY)}
     * @param IY the {@code y} component of the vector {@code (IX, IY)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 faceforward(double IX, double IY, double NrefX, double NrefY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = java.lang.Math.fma(IX, NrefX, IY * NrefY) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t2;
            d.y = this.y * _t2;
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = ((IX) * (NrefX) + (IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            d.x = this.x * _t2;
            d.y = this.y * _t2;
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
    public Double2 floor(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.floor(this.x);
        d.y = Math.floor(this.y);
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
    public Double2 fract(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(this.x - Math.floor(this.x), 0.9999999999999999);
        d.y = java.lang.Math.min(this.y - Math.floor(this.y), 0.9999999999999999);
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
    public Double2 hypot(double y, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.hypot(this.x, y);
        d.y = Math.hypot(this.y, y);
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
    public Double2 hypot(Double2R y, @Mutated Double2 dest) {
        double yY = y.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.hypot(this.x, y.x());
        d.y = Math.hypot(this.y, yY);
        return d;
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY)}
     * @param yY the {@code y} component of the vector {@code (yX, yY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 hypot(double yX, double yY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.hypot(this.x, yX);
        d.y = Math.hypot(this.y, yY);
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
    public Double2 inverse(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = 1.0 / this.x;
        d.y = 1.0 / this.y;
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
    public Double2 inverseSqrt(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = (1.0 / java.lang.Math.sqrt(this.x));
        d.y = (1.0 / java.lang.Math.sqrt(this.y));
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
            return java.lang.Math.sqrt(java.lang.Math.fma(this.x, this.x, this.y * this.y));
        } else {
            return java.lang.Math.sqrt(((this.x) * (this.x) + (this.y * this.y)));
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
            return java.lang.Math.fma(this.x, this.x, this.y * this.y);
        } else {
            return ((this.x) * (this.x) + (this.y * this.y));
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
    public Double2 log(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.log(this.x);
        d.y = Math.log(this.y);
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
    public Double2 log10(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.log10(this.x);
        d.y = Math.log10(this.y);
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
    public Double2 log1p(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.log1p(this.x);
        d.y = Math.log1p(this.y);
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
    public Double2 log2(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.log2(this.x);
        d.y = Math.log2(this.y);
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
    public double manhattanDistance(Double2R other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY})
     */
    public double manhattanDistance(double otherX, double otherY) {
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public double manhattanLength() {
        return java.lang.Math.abs(this.x) + java.lang.Math.abs(this.y);
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
    public Double2 max(double scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
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
    public Double2 max(Double2R other, @Mutated Double2 dest) {
        double otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 max(double otherX, double otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
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
    public Double2 min(double scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
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
    public Double2 min(Double2R other, @Mutated Double2 dest) {
        double otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 min(double otherX, double otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
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
    public Double2 mod(double y, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = flooredMod(this.x, y);
        d.y = flooredMod(this.y, y);
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
    public Double2 mod(Double2R y, @Mutated Double2 dest) {
        double yX = y.x();
        double yY = y.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
        return d;
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY})
     * ({@code x % y}, plus {@code y} when that remainder is non-zero and its sign differs from
     * {@code y}'s - exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (yX, yY)} must be non-zero.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY)}
     * @param yY the {@code y} component of the vector {@code (yX, yY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mod(double yX, double yY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = flooredMod(this.x, yX);
        d.y = flooredMod(this.y, yY);
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
    public Double2 nextDown(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.nextDown(this.x);
        d.y = Math.nextDown(this.y);
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
    public Double2 nextUp(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.nextUp(this.x);
        d.y = Math.nextUp(this.y);
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
    public Double2 normalize(@Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = java.lang.Math.fma(this.x, this.x, this.y * this.y);
            double _t2 = (1.0 / java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0) {
                d.x = this.x * _t2;
                d.y = this.y * _t2;
            } else {
                d.x = 0.0;
                d.y = 0.0;
            }
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = ((this.x) * (this.x) + (this.y * this.y));
            double _t2 = (1.0 / java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0) {
                d.x = this.x * _t2;
                d.y = this.y * _t2;
            } else {
                d.x = 0.0;
                d.y = 0.0;
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
    public Double2 normalizeMul(double length, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = java.lang.Math.fma(this.x, this.x, this.y * this.y);
            double _t3 = length * (1.0 / java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
            } else {
                d.x = 0.0;
                d.y = 0.0;
            }
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = ((this.x) * (this.x) + (this.y * this.y));
            double _t3 = length * (1.0 / java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
            } else {
                d.x = 0.0;
                d.y = 0.0;
            }
            return d;
        }
    }


    /**
     * Compute the signed angle in radians between this vector and {@code other}, positive when the
     * rotation from this vector to {@code other} is counter-clockwise (with the x axis pointing
     * right and the y axis pointing up).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the signed angle to
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise (with the x axis
     *        pointing right and the y axis pointing up)
     */
    public double orientedAngle(Double2R other) {
        double otherX = other.x();
        double otherY = other.y();
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _ct0 = java.lang.Math.abs(_t2);
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_fma(otherX, otherY);
            return Math.atan2(Math.copySign(_ct0, _t2), java.lang.Math.fma(otherX, this.x, otherY * this.y));
        } else {
            double _t2 = ((otherY) * (this.x) - (otherX * this.y));
            double _ct0 = java.lang.Math.abs(_t2);
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_mulAdd(otherX, otherY);
            return Math.atan2(Math.copySign(_ct0, _t2), ((otherX) * (this.x) + (otherY * this.y)));
        }
    }


    /**
     * Compute the signed angle in radians between this vector and ({@code otherX}, {@code otherY}),
     * positive when the rotation from this vector to ({@code otherX}, {@code otherY}) is
     * counter-clockwise (with the x axis pointing right and the y axis pointing up).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the signed angle in radians between this vector and ({@code otherX}, {@code otherY}),
     *        positive when the rotation from this vector to ({@code otherX}, {@code otherY}) is
     *        counter-clockwise (with the x axis pointing right and the y axis pointing up)
     */
    public double orientedAngle(double otherX, double otherY) {
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _ct0 = java.lang.Math.abs(_t2);
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_fma(otherX, otherY);
            return Math.atan2(Math.copySign(_ct0, _t2), java.lang.Math.fma(otherX, this.x, otherY * this.y));
        } else {
            double _t2 = ((otherY) * (this.x) - (otherX * this.y));
            double _ct0 = java.lang.Math.abs(_t2);
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_mulAdd(otherX, otherY);
            return Math.atan2(Math.copySign(_ct0, _t2), ((otherX) * (this.x) + (otherY * this.y)));
        }
    }

    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate_fma(double otherX, double otherY) {
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(this.x, this.y, this.x);
        double _t6 = otherY * _t0;
        double _t7 = this.x * _t1;
        double _t8 = otherX * _t0;
        double _t9 = this.y * _t1;
        double _t14 = java.lang.Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(java.lang.Math.abs(_t19), java.lang.Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate_mulAdd(double otherX, double otherY) {
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(this.x, this.y, this.x);
        double _t6 = otherY * _t0;
        double _t7 = this.x * _t1;
        double _t8 = otherX * _t0;
        double _t9 = this.y * _t1;
        double _t14 = ((_t6) * (_t7) - (_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(java.lang.Math.abs(_t19), ((_t8) * (_t7) + (_t6 * _t9)) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
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
    public Double2x2 outerProduct(Double2R row, @Mutated Double2x2 dest) {
        double rowX = row.x();
        double rowY = row.y();
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
        d.properties = 0;
        return d;
    }


    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 outerProduct(double rowX, double rowY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = rowX * this.x;
        d.m10 = rowX * this.y;
        d.m01 = rowY * this.x;
        d.m11 = rowY * this.y;
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
    public Double2 pow(double exponent, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.pow(this.x, exponent);
        d.y = Math.pow(this.y, exponent);
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
    public Double2 pow(Double2R exponent, @Mutated Double2 dest) {
        double exponentY = exponent.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.pow(this.x, exponent.x());
        d.y = Math.pow(this.y, exponentY);
        return d;
    }


    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponentX the {@code x} component of the vector {@code (exponentX, exponentY)}
     * @param exponentY the {@code y} component of the vector {@code (exponentX, exponentY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 pow(double exponentX, double exponentY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.pow(this.x, exponentX);
        d.y = Math.pow(this.y, exponentY);
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
    public Double2 project(Double2R onto, @Mutated Double2 dest) {
        double ontoX = onto.x();
        double ontoY = onto.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t5 = java.lang.Math.fma(ontoX, this.x, ontoY * this.y) / java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY);
            d.x = ontoX * _t5;
            d.y = ontoY * _t5;
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t5 = ((ontoX) * (this.x) + (ontoY * this.y)) / ((ontoX) * (ontoX) + (ontoY * ontoY));
            d.x = ontoX * _t5;
            d.y = ontoY * _t5;
            return d;
        }
    }


    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (ontoX, ontoY)} must be non-zero.
     *
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 project(double ontoX, double ontoY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t5 = java.lang.Math.fma(ontoX, this.x, ontoY * this.y) / java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY);
            d.x = ontoX * _t5;
            d.y = ontoY * _t5;
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t5 = ((ontoX) * (this.x) + (ontoY * this.y)) / ((ontoX) * (ontoX) + (ontoY * ontoY));
            d.x = ontoX * _t5;
            d.y = ontoY * _t5;
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
    public Double2 projectOnPlane(Double2R normal, @Mutated Double2 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = java.lang.Math.fma(normalX, this.x, normalY * this.y);
            d.x = java.lang.Math.fma(-normalX, _t1, this.x);
            d.y = java.lang.Math.fma(-normalY, _t1, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = ((normalX) * (this.x) + (normalY * this.y));
            d.x = ((-normalX) * (_t1) + (this.x));
            d.y = ((-normalY) * (_t1) + (this.y));
            return d;
        }
    }


    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 projectOnPlane(double normalX, double normalY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = java.lang.Math.fma(normalX, this.x, normalY * this.y);
            d.x = java.lang.Math.fma(-normalX, _t1, this.x);
            d.y = java.lang.Math.fma(-normalY, _t1, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t1 = ((normalX) * (this.x) + (normalY * this.y));
            d.x = ((-normalX) * (_t1) + (this.x));
            d.y = ((-normalY) * (_t1) + (this.y));
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
    public Double2 radians(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.toRadians(this.x);
        d.y = Math.toRadians(this.y);
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
    public Double2 reflect(Double2R normal, @Mutated Double2 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = 2.0 * java.lang.Math.fma(normalX, this.x, normalY * this.y);
            d.x = java.lang.Math.fma(-normalX, _t2, this.x);
            d.y = java.lang.Math.fma(-normalY, _t2, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = 2.0 * ((normalX) * (this.x) + (normalY * this.y));
            d.x = ((-normalX) * (_t2) + (this.x));
            d.y = ((-normalY) * (_t2) + (this.y));
            return d;
        }
    }


    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 reflect(double normalX, double normalY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = 2.0 * java.lang.Math.fma(normalX, this.x, normalY * this.y);
            d.x = java.lang.Math.fma(-normalX, _t2, this.x);
            d.y = java.lang.Math.fma(-normalY, _t2, this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = 2.0 * ((normalX) * (this.x) + (normalY * this.y));
            d.x = ((-normalX) * (_t2) + (this.x));
            d.y = ((-normalY) * (_t2) + (this.y));
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
    public Double2 refract(Double2R normal, double eta, @Mutated Double2 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = java.lang.Math.fma(normalX, this.x, normalY * this.y);
            double _t6 = java.lang.Math.fma(-java.lang.Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
            double _t9 = java.lang.Math.fma(eta, _t2, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6)));
            if (_t6 >= 0.0) {
                d.x = java.lang.Math.fma(eta, this.x, -(normalX * _t9));
                d.y = java.lang.Math.fma(eta, this.y, -(normalY * _t9));
            } else {
                d.x = 0.0;
                d.y = 0.0;
            }
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = ((normalX) * (this.x) + (normalY * this.y));
            double _t6 = ((-((-_t2) * (_t2) + (1.0))) * (eta * eta) + (1.0));
            double _t9 = ((eta) * (_t2) + (java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6))));
            if (_t6 >= 0.0) {
                d.x = ((eta) * (this.x) - (normalX * _t9));
                d.y = ((eta) * (this.y) - (normalY * _t9));
            } else {
                d.x = 0.0;
                d.y = 0.0;
            }
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
     * Valid input: {@code (normalX, normalY)} must have unit length; this vector must have unit
     * length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    public Double2 refract(double normalX, double normalY, double eta, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = java.lang.Math.fma(normalX, this.x, normalY * this.y);
            double _t6 = java.lang.Math.fma(-java.lang.Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
            double _t9 = java.lang.Math.fma(eta, _t2, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6)));
            if (_t6 >= 0.0) {
                d.x = java.lang.Math.fma(eta, this.x, -(normalX * _t9));
                d.y = java.lang.Math.fma(eta, this.y, -(normalY * _t9));
            } else {
                d.x = 0.0;
                d.y = 0.0;
            }
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t2 = ((normalX) * (this.x) + (normalY * this.y));
            double _t6 = ((-((-_t2) * (_t2) + (1.0))) * (eta * eta) + (1.0));
            double _t9 = ((eta) * (_t2) + (java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6))));
            if (_t6 >= 0.0) {
                d.x = ((eta) * (this.x) - (normalX * _t9));
                d.y = ((eta) * (this.y) - (normalY * _t9));
            } else {
                d.x = 0.0;
                d.y = 0.0;
            }
            return d;
        }
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
    public Double2 round(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.rint(this.x);
        d.y = Math.rint(this.y);
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
    public Double2 sign(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
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
    public Double2 sin(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.sin(this.x);
        d.y = Math.sin(this.y);
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
    public Double2 sinh(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.sinh(this.x);
        d.y = Math.sinh(this.y);
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
    public Double2 smoothstep(double edge0, double edge1, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t7 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0) * _t0_inv));
            double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0) * _t0_inv));
            d.x = java.lang.Math.fma(-2.0, _t7, 3.0) * _t7 * _t7;
            d.y = java.lang.Math.fma(-2.0, _t8, 3.0) * _t8 * _t8;
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t7 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0) * _t0_inv));
            double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0) * _t0_inv));
            d.x = ((-2.0) * (_t7) + (3.0)) * _t7 * _t7;
            d.y = ((-2.0) * (_t8) + (3.0)) * _t8 * _t8;
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
    public Double2 smoothstep(Double2R edge0, Double2R edge1, @Mutated Double2 dest) {
        double edge0X = edge0.x();
        double edge0Y = edge0.y();
        double edge1X = edge1.x();
        double edge1Y = edge1.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            d.x = java.lang.Math.fma(-2.0, _t8, 3.0) * _t8 * _t8;
            d.y = java.lang.Math.fma(-2.0, _t9, 3.0) * _t9 * _t9;
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            d.x = ((-2.0) * (_t8) + (3.0)) * _t8 * _t8;
            d.y = ((-2.0) * (_t9) + (3.0)) * _t9 * _t9;
            return d;
        }
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}) and the upper edge ({@code edge1X},
     * {@code edge1Y}), yielding 0 at or below the lower edge and 1 at or above the upper edge and
     * store the result in {@code dest}.
     * <p>
     * Valid input: {@code (edge0X, edge0Y)} and {@code (edge1X, edge1Y)} must differ in every
     * component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 smoothstep(double edge0X, double edge0Y, double edge1X, double edge1Y, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            d.x = java.lang.Math.fma(-2.0, _t8, 3.0) * _t8 * _t8;
            d.y = java.lang.Math.fma(-2.0, _t9, 3.0) * _t9 * _t9;
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            d.x = ((-2.0) * (_t8) + (3.0)) * _t8 * _t8;
            d.y = ((-2.0) * (_t9) + (3.0)) * _t9 * _t9;
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
    public Double2 sqrt(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.sqrt(this.x);
        d.y = java.lang.Math.sqrt(this.y);
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
    public Double2 step(double edge, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x < edge ? 0.0 : 1.0;
        d.y = this.y < edge ? 0.0 : 1.0;
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
    public Double2 step(Double2R edge, @Mutated Double2 dest) {
        double edgeY = edge.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x < edge.x() ? 0.0 : 1.0;
        d.y = this.y < edgeY ? 0.0 : 1.0;
        return d;
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 step(double edgeX, double edgeY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x < edgeX ? 0.0 : 1.0;
        d.y = this.y < edgeY ? 0.0 : 1.0;
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
    public Double2 tan(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.tan(this.x);
        d.y = Math.tan(this.y);
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
    public Double2 tanh(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.tanh(this.x);
        d.y = Math.tanh(this.y);
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
    public Double2 trunc(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x >= 0.0 ? Math.floor(this.x) : Math.ceil(this.x);
        d.y = this.y >= 0.0 ? Math.floor(this.y) : Math.ceil(this.y);
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
    public Double2 ulp(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.ulp(this.x);
        d.y = Math.ulp(this.y);
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
    public Double2 preMul(Double2x2R mat, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, mat.m01() * this.y);
            d.y = java.lang.Math.fma(mat.m10(), _rd0, mat.m11() * this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = ((mat.m00()) * (_rd0) + (mat.m01() * this.y));
            d.y = ((mat.m10()) * (_rd0) + (mat.m11() * this.y));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xy}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double2 preMulDirection(Double2x3R mat, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, mat.m01() * this.y);
            d.y = java.lang.Math.fma(mat.m10(), _rd0, mat.m11() * this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = ((mat.m00()) * (_rd0) + (mat.m01() * this.y));
            d.y = ((mat.m10()) * (_rd0) + (mat.m11() * this.y));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xy}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double2 preMulDirection(Double3x3R mat, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, mat.m01() * this.y);
            d.y = java.lang.Math.fma(mat.m10(), _rd0, mat.m11() * this.y);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = ((mat.m00()) * (_rd0) + (mat.m01() * this.y));
            d.y = ((mat.m10()) * (_rd0) + (mat.m11() * this.y));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as the point {@code (x, y, 0, 1)} of the
     * xy-plane - i.e. compute {@code (mat * (this, 0, 1)).xy}, applying the full affine transform
     * including translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double2 preMulPosition(Double4x4R mat, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, java.lang.Math.fma(mat.m01(), this.y, mat.m03()));
            d.y = java.lang.Math.fma(mat.m10(), _rd0, java.lang.Math.fma(mat.m11(), this.y, mat.m13()));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = ((mat.m00()) * (_rd0) + (((mat.m01()) * (this.y) + (mat.m03()))));
            d.y = ((mat.m10()) * (_rd0) + (((mat.m11()) * (this.y) + (mat.m13()))));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xy}, applying the full affine transform including
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double2 preMulPosition(Double2x3R mat, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, java.lang.Math.fma(mat.m01(), this.y, mat.m02()));
            d.y = java.lang.Math.fma(mat.m10(), _rd0, java.lang.Math.fma(mat.m11(), this.y, mat.m12()));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = ((mat.m00()) * (_rd0) + (((mat.m01()) * (this.y) + (mat.m02()))));
            d.y = ((mat.m10()) * (_rd0) + (((mat.m11()) * (this.y) + (mat.m12()))));
            return d;
        }
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xy}, applying the full affine transform including
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double2 preMulPosition(Double3x3R mat, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(mat.m00(), _rd0, java.lang.Math.fma(mat.m01(), this.y, mat.m02()));
            d.y = java.lang.Math.fma(mat.m10(), _rd0, java.lang.Math.fma(mat.m11(), this.y, mat.m12()));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _rd0 = this.x;
            d.x = ((mat.m00()) * (_rd0) + (((mat.m01()) * (this.y) + (mat.m02()))));
            d.y = ((mat.m10()) * (_rd0) + (((mat.m11()) * (this.y) + (mat.m12()))));
            return d;
        }
    }


    /**
     * Rotate this vector counter-clockwise about the origin by {@code angle} radians and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rotate(double angle, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.x;
            d.x = java.lang.Math.fma(_rd0, _t1, -(this.y * _t0));
            d.y = java.lang.Math.fma(_rd0, _t0, this.y * _t1);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _rd0 = this.x;
            d.x = ((_rd0) * (_t1) - (this.y * _t0));
            d.y = ((_rd0) * (_t0) + (this.y * _t1));
            return d;
        }
    }


    /**
     * Rotate this vector counter-clockwise by {@code angle} radians about the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rotateAround(double angle, Double2R pivot, @Mutated Double2 dest) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotX));
            d.y = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotY));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = ((_t2) * (_t1) + (((-_t3) * (_t0) + (pivotX))));
            d.y = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotY))));
            return d;
        }
    }


    /**
     * Rotate this vector counter-clockwise by {@code angle} radians about the point
     * ({@code pivotX}, {@code pivotY}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rotateAround(double angle, double pivotX, double pivotY, @Mutated Double2 dest) {
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotX));
            d.y = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotY));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            d.x = ((_t2) * (_t1) + (((-_t3) * (_t0) + (pivotX))));
            d.y = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotY))));
            return d;
        }
    }

    public double x() { return this.x; }
    public double y() { return this.y; }

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

    @Override public String toString() {
        return "Double2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2Impl)) return false;
        Double2Impl o = (Double2Impl) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y);
    }

    @Override public boolean equalsEpsilon(Double2R other, double epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }
    public @Mutated Double2 load(double[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }
    @Mutated public Double2 load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Double2 loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 2);
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
    public Double2 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Double2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Double2 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public Double2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Double2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.x;
        dest[offset + 1] = (float) this.y;
        return dest;
    }
    public @Mutated Double2 load(float[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }
    @Mutated public Double2 load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Double2 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    public Double2 loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public Double2 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public Double2 loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2 r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return r;
    }
    public Double2 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public Double2 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
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
