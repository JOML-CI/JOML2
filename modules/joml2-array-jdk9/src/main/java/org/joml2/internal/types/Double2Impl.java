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
 * Generated implementation of {@link Double2} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Double2Impl implements Double2 {

    public double[] data;

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
        data = new double[2];
    }

    public Double2Impl(double x, double y) {
        double[] dd = this.data = new double[2];
        dd[0] = x;
        dd[1] = y;
    }

    public Double2Impl(Double2R src) {
        double[] dd = this.data = new double[2];
        dd[0] = src.x();
        dd[1] = src.y();
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
        return add(other.x(), other.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
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
    public Double2 div(double scalar, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[0] / scalar;
        dd[1] = sd[1] / scalar;
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
    public Double2 div(Double2R other, @Mutated Double2 dest) {
        return div(other.x(), other.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[0] / otherX;
        dd[1] = sd[1] / otherY;
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
    public Double2 fma(double b, Double2R c, @Mutated Double2 dest) {
        return fma(b, c.x(), c.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], b, cX);
        dd[1] = Math.fma(sd[1], b, cY);
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
    public Double2 fma(Double2R b, Double2R c, @Mutated Double2 dest) {
        return fma(b.x(), b.y(), c.x(), c.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], bX, cX);
        dd[1] = Math.fma(sd[1], bY, cY);
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
    public Double2 mul(double scalar, @Mutated Double2 dest) {
        return mul(scalar, scalar, dest);
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
        return mul(other.x(), other.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
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
    public Double2 negate(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
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
    public Double2 sub(Double2R other, @Mutated Double2 dest) {
        return sub(other.x(), other.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        return dest;
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
        double[] dd = this.data;
        double _t0 = u * 6.283185307179586;
        double _t1 = Math.sin(_t0);
        dd[0] = Math.cosFromSin(_t1, _t0);
        dd[1] = _t1;
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
        return set(v.x(), v.y());
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
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
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
    public Float2 toFloat(@Mutated Float2 dest) {
        double[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
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
    public Byte2 toByte(@Mutated Byte2 dest) {
        double[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
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
    public Byte2 toByte(RoundingMode roundingMode, @Mutated Byte2 dest) {
        double[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        return toByte_sa565cc46_1(roundingMode, dest, sd, dd);
    }

    /** Piece 2 of {@code toByte}, split to fit the inline budget; reached only through it. */
    private Byte2 toByte_sa565cc46_1(RoundingMode roundingMode, Byte2 dest, double[] sd, byte[] dd) {
        switch (roundingMode) {
            case TRUNCATE: return toByte(dest);
            case FLOOR: {
                dd[0] = (byte) Math.floor(sd[0]);
                dd[1] = (byte) Math.floor(sd[1]);
            } break;
            case CEILING: {
                dd[0] = (byte) Math.ceil(sd[0]);
                dd[1] = (byte) Math.ceil(sd[1]);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                dd[0] = (byte) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
                dd[1] = (byte) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
            } break;
            case HALF_AWAY_FROM_ZERO: {
                dd[0] = (byte) (Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (byte) (Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
            } break;
            case HALF_EVEN: {
                dd[0] = (byte) Math.rint(sd[0]);
                dd[1] = (byte) Math.rint(sd[1]);
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
        double[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
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
    public Short2 toShort(RoundingMode roundingMode, @Mutated Short2 dest) {
        double[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        return toShort_sc4cef7f2_1(roundingMode, dest, sd, dd);
    }

    /** Piece 2 of {@code toShort}, split to fit the inline budget; reached only through it. */
    private Short2 toShort_sc4cef7f2_1(RoundingMode roundingMode, Short2 dest, double[] sd, short[] dd) {
        switch (roundingMode) {
            case TRUNCATE: return toShort(dest);
            case FLOOR: {
                dd[0] = (short) Math.floor(sd[0]);
                dd[1] = (short) Math.floor(sd[1]);
            } break;
            case CEILING: {
                dd[0] = (short) Math.ceil(sd[0]);
                dd[1] = (short) Math.ceil(sd[1]);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                dd[0] = (short) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
                dd[1] = (short) (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
            } break;
            case HALF_AWAY_FROM_ZERO: {
                dd[0] = (short) (Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (short) (Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
            } break;
            case HALF_EVEN: {
                dd[0] = (short) Math.rint(sd[0]);
                dd[1] = (short) Math.rint(sd[1]);
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
        double[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
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
    public Int2 toInt(RoundingMode roundingMode, @Mutated Int2 dest) {
        double[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        return toInt_sf8e5a1b6_1(roundingMode, dest, sd, dd);
    }

    /** Piece 2 of {@code toInt}, split to fit the inline budget; reached only through it. */
    private Int2 toInt_sf8e5a1b6_1(RoundingMode roundingMode, Int2 dest, double[] sd, int[] dd) {
        switch (roundingMode) {
            case TRUNCATE: return toInt(dest);
            case FLOOR: {
                dd[0] = (int) Math.floor(sd[0]);
                dd[1] = (int) Math.floor(sd[1]);
            } break;
            case CEILING: {
                dd[0] = (int) Math.ceil(sd[0]);
                dd[1] = (int) Math.ceil(sd[1]);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                dd[0] = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
                dd[1] = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
            } break;
            case HALF_AWAY_FROM_ZERO: {
                dd[0] = (int) (Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (int) (Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
            } break;
            case HALF_EVEN: {
                dd[0] = (int) Math.rint(sd[0]);
                dd[1] = (int) Math.rint(sd[1]);
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
        double[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = (long) (sd[0]);
        dd[1] = (long) (sd[1]);
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
    public Long2 toLong(RoundingMode roundingMode, @Mutated Long2 dest) {
        double[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE: return toLong(dest);
            case FLOOR: {
                dd[0] = (long) Math.floor(sd[0]);
                dd[1] = (long) Math.floor(sd[1]);
            } break;
            case CEILING: {
                dd[0] = (long) Math.ceil(sd[0]);
                dd[1] = (long) Math.ceil(sd[1]);
            } break;
            case HALF_TOWARD_POSITIVE_INFINITY: {
                dd[0] = Math.round(sd[0]);
                dd[1] = Math.round(sd[1]);
            } break;
            case HALF_AWAY_FROM_ZERO: {
                dd[0] = (long) (Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (long) (Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
            } break;
            case HALF_EVEN: {
                dd[0] = (long) Math.rint(sd[0]);
                dd[1] = (long) Math.rint(sd[1]);
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
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dd[0] = Math.fma(p1X, _t7, sd[0] * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        dd[1] = Math.fma(p1Y, _t7, sd[1] * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
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
    public Double2 bezier2(Double2R p1, Double2R p2, double t, @Mutated Double2 dest) {
        return bezier2(p1.x(), p1.y(), p2.x(), p2.y(), t, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dd[0] = Math.fma(p2X, _t0, Math.fma(p1X, _t3, sd[0] * _t4));
        dd[1] = Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, sd[1] * _t4));
        return dest;
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
        return bezier2Tangent(p1.x(), p1.y(), p2.x(), p2.y(), t, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dd[0] = Math.fma(p1X - sd[0], _t2, (p2X - p1X) * _t1);
        dd[1] = Math.fma(p1Y - sd[1], _t2, (p2Y - p1Y) * _t1);
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
        return bezierTangent(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y(), t, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dd[0] = Math.fma(p3X - p2X, _t2, Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
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
    public Double2 catmullRom(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest) {
        return catmullRom(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y(), t, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = t * t;
        double _t1 = t * _t0;
        dd[0] = 0.5 * (Math.fma(2.0, p1X, t * (p2X - sd[0])) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, sd[0], Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - sd[0])) * _t1));
        dd[1] = 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - sd[1])) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, sd[1], Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - sd[1])) * _t1));
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
    public Double2 catmullRomTangent(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest) {
        return catmullRomTangent(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y(), t, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = t * t;
        dd[0] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, sd[0], Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - sd[0])), _t0, p2X - sd[0]));
        dd[1] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, sd[1], Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - sd[1])), _t0, p2Y - sd[1]));
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dd[0] = Math.fma(sd[0], _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        dd[1] = Math.fma(sd[1], _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dd[0] = Math.fma(sd[0], _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        dd[1] = Math.fma(sd[1], _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
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
    public Double2 lerp(Double2R other, double t, @Mutated Double2 dest) {
        return lerp(other.x(), other.y(), t, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(t, otherX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherY - sd[1], sd[1]);
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
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 lerp(Double2R other, Double2R t, @Mutated Double2 dest) {
        return lerp(other.x(), other.y(), t.x(), t.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(tX, otherX - sd[0], sd[0]);
        dd[1] = Math.fma(tY, otherY - sd[1], sd[1]);
        return dest;
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
        return slerp(other.x(), other.y(), t, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t5 = Math.fma(sd[0], sd[0], sd[1] * sd[1]);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, t, dest);
        double _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, t, dest);
        double _t7 = (1.0 / Math.sqrt(_t5));
        double _t10 = (1.0 / Math.sqrt(_t6));
        double _t12 = sd[0] * _t7;
        double _t16 = sd[1] * _t7;
        double _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        double _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        double _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        double _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, t, dest);
        return slerp_s8cbadd06_1(t, dest, dd, _t12, _t16, t * Math.sqrt(_t6) + (1.0 - t) * Math.sqrt(_t5), _t21, _t31, _t32, _t35);
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double2 slerp_s8cbadd06_1(double t, Double2 dest, double[] dd, double _t12, double _t16, double _t20, double _t21, double _t31, double _t32, double _t35) {
        double _t39 = t * Math.atan2(Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        dd[0] = Math.fma(_t12, _t44, _sp0 * _t31);
        dd[1] = Math.fma(_t16, _t44, _sp0 * _t32);
        return dest;
    }



    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double2 slerp_degenerate(double otherX, double otherY, double t, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(sd[0], sd[1], sd[0]);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = sd[0] * _t2;
        double _t10 = sd[1] * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / Math.sqrt(_t18));
        double _t23 = (1.0 / Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = Math.fma(_t47, _t25, _t43);
        double _t49 = Math.fma(_t47, _t27, _t44);
        return slerp_degenerate_sa4e43335_1(otherX, otherY, t, dest, sd, dd, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2 slerp_degenerate_sa4e43335_1(double otherX, double otherY, double t, Double2 dest, double[] sd, double[] dd, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49, double _t51) {
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / Math.sqrt(_t60));
        double _t64 = t * Math.atan2(Math.sqrt(_t60), _t38 * _t51);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_sa4e43335_2(otherX, otherY, t, dest, sd, dd, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2 slerp_degenerate_sa4e43335_2(double otherX, double otherY, double t, Double2 dest, double[] sd, double[] dd, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t38, double _t48, double _t49, double _t68, double _t69, double _t72, double _t73) {
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dd[0] = Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv;
                    dd[1] = Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv;
                } else {
                    dd[0] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                    dd[1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
                }
            } else {
                dd[0] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                dd[1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
            }
        } else {
            dd[0] = Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = Math.fma(t, otherY - sd[1], sd[1]);
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
    public Double2 absolute(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.abs(sd[0]);
        dd[1] = Math.abs(sd[1]);
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
    public Double2 acos(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.acos(sd[0]);
        dd[1] = Math.acos(sd[1]);
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
    public Double2 addScaled(Double2R b, double scalar, @Mutated Double2 dest) {
        return addScaled(b.x(), b.y(), scalar, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(scalar, bX, sd[0]);
        dd[1] = Math.fma(scalar, bY, sd[1]);
        return dest;
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
        return addScaled(b.x(), b.y(), c.x(), c.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(bX, cX, sd[0]);
        dd[1] = Math.fma(bY, cY, sd[1]);
        return dest;
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
        return angleBetween(other.x(), other.y());
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
        double[] sd = this.data;
        double _ct0 = Math.abs(Math.fma(otherY, sd[0], -(otherX * sd[1])));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, sd[0], otherY * sd[1]));
    }



    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate(double otherX, double otherY) {
        double[] sd = this.data;
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(sd[0], sd[1], sd[0]);
        double _t6 = otherY * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = otherX * _t0;
        double _t9 = sd[1] * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.asin(sd[0]);
        dd[1] = Math.asin(sd[1]);
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
    public Double2 atan(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.atan(sd[0]);
        dd[1] = Math.atan(sd[1]);
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
    public Double2 atan2(double x, @Mutated Double2 dest) {
        return atan2(x, x, dest);
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
        return atan2(x.x(), x.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.atan2(sd[0], xX);
        dd[1] = Math.atan2(sd[1], xY);
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
    public Double2 cbrt(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.cbrt(sd[0]);
        dd[1] = Math.cbrt(sd[1]);
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
    public Double2 ceil(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.ceil(sd[0]);
        dd[1] = Math.ceil(sd[1]);
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
    public Double2 clamp(double min, double max, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], min), max);
        dd[1] = Math.min(Math.max(sd[1], min), max);
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
    public Double2 clamp(Double2R min, Double2R max, @Mutated Double2 dest) {
        return clamp(min.x(), min.y(), max.x(), max.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        return dest;
    }


    /**
     * Compute the sum of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public double compAdd() {
        double[] sd = this.data;
        return sd[0] + sd[1];
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public double compMax() {
        double[] sd = this.data;
        return Math.max(sd[0], sd[1]);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public double compMin() {
        double[] sd = this.data;
        return Math.min(sd[0], sd[1]);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public double compMul() {
        double[] sd = this.data;
        return sd[0] * sd[1];
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
        return copySign(sign, sign, dest);
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
        return copySign(sign.x(), sign.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.copySign(sd[0], signX);
        dd[1] = Math.copySign(sd[1], signY);
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
    public Double2 cos(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.cos(sd[0]);
        dd[1] = Math.cos(sd[1]);
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
    public Double2 cosh(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.cosh(sd[0]);
        dd[1] = Math.cosh(sd[1]);
        return dest;
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
        return cross(other.x(), other.y());
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
        double[] sd = this.data;
        return Math.fma(otherY, sd[0], -(otherX * sd[1]));
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.toDegrees(sd[0]);
        dd[1] = Math.toDegrees(sd[1]);
        return dest;
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
        return distance(other.x(), other.y());
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
        double[] sd = this.data;
        double _t0 = sd[0] - otherX;
        double _t1 = sd[1] - otherY;
        return Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
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
        return distanceSquared(other.x(), other.y());
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
        double[] sd = this.data;
        double _t0 = sd[0] - otherX;
        double _t1 = sd[1] - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
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
        return dot(other.x(), other.y());
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
        double[] sd = this.data;
        return Math.fma(otherX, sd[0], otherY * sd[1]);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.exp(sd[0]);
        dd[1] = Math.exp(sd[1]);
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
    public Double2 exp2(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.pow(2.0, sd[0]);
        dd[1] = Math.pow(2.0, sd[1]);
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
    public Double2 expm1(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.expm1(sd[0]);
        dd[1] = Math.expm1(sd[1]);
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
    public Double2 faceforward(Double2R I, Double2R Nref, @Mutated Double2 dest) {
        return faceforward(I.x(), I.y(), Nref.x(), Nref.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        if (Math.fma(IX, NrefX, IY * NrefY) < 0.0) {
            dd[0] = sd[0];
            dd[1] = sd[1];
        } else {
            dd[0] = -sd[0];
            dd[1] = -sd[1];
        }
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.floor(sd[0]);
        dd[1] = Math.floor(sd[1]);
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
    public Double2 fract(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.min(sd[0] - Math.floor(sd[0]), 0.9999999999999999);
        dd[1] = Math.min(sd[1] - Math.floor(sd[1]), 0.9999999999999999);
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
    public Double2 hypot(double y, @Mutated Double2 dest) {
        return hypot(y, y, dest);
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
        return hypot(y.x(), y.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.hypot(sd[0], yX);
        dd[1] = Math.hypot(sd[1], yY);
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
    public Double2 inverse(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = 1.0 / sd[0];
        dd[1] = 1.0 / sd[1];
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
    public Double2 inverseSqrt(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = (1.0 / Math.sqrt(sd[0]));
        dd[1] = (1.0 / Math.sqrt(sd[1]));
        return dest;
    }


    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this vector
     */
    public double length() {
        double[] sd = this.data;
        return Math.sqrt(Math.fma(sd[0], sd[0], sd[1] * sd[1]));
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public double lengthSquared() {
        double[] sd = this.data;
        return Math.fma(sd[0], sd[0], sd[1] * sd[1]);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.log(sd[0]);
        dd[1] = Math.log(sd[1]);
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
    public Double2 log10(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.log10(sd[0]);
        dd[1] = Math.log10(sd[1]);
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
    public Double2 log1p(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.log1p(sd[0]);
        dd[1] = Math.log1p(sd[1]);
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
    public Double2 log2(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.log2(sd[0]);
        dd[1] = Math.log2(sd[1]);
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
    public double manhattanDistance(Double2R other) {
        return manhattanDistance(other.x(), other.y());
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
        double[] sd = this.data;
        return Math.abs(sd[0] - otherX) + Math.abs(sd[1] - otherY);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public double manhattanLength() {
        double[] sd = this.data;
        return Math.abs(sd[0]) + Math.abs(sd[1]);
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
        return max(scalar, scalar, dest);
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
        return max(other.x(), other.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
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
    public Double2 min(double scalar, @Mutated Double2 dest) {
        return min(scalar, scalar, dest);
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
        return min(other.x(), other.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
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
    public Double2 mod(double y, @Mutated Double2 dest) {
        return mod(y, y, dest);
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
        return mod(y.x(), y.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
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
    public Double2 nextDown(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.nextDown(sd[0]);
        dd[1] = Math.nextDown(sd[1]);
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
    public Double2 nextUp(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.nextUp(sd[0]);
        dd[1] = Math.nextUp(sd[1]);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t1 = Math.fma(sd[0], sd[0], sd[1] * sd[1]);
        double _t2 = (1.0 / Math.sqrt(_t1));
        if (_t1 != 0.0) {
            dd[0] = sd[0] * _t2;
            dd[1] = sd[1] * _t2;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
        }
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t1 = Math.fma(sd[0], sd[0], sd[1] * sd[1]);
        double _t3 = length * (1.0 / Math.sqrt(_t1));
        if (_t1 != 0.0) {
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
        }
        return dest;
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
        return orientedAngle(other.x(), other.y());
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
        double[] sd = this.data;
        double _t2 = Math.fma(otherY, sd[0], -(otherX * sd[1]));
        double _ct0 = Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate(otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, sd[0], otherY * sd[1]));
    }



    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate(double otherX, double otherY) {
        double[] sd = this.data;
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(sd[0], sd[1], sd[0]);
        double _t6 = otherY * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = otherX * _t0;
        double _t9 = sd[1] * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
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
        return outerProduct(row.x(), row.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = rowX * sd[0];
        dd[1] = rowX * sd[1];
        double _buf1 = rowY * sd[0];
        dd[3] = rowY * sd[1];
        dd[0] = _buf0;
        dd[2] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2 pow(double exponent, @Mutated Double2 dest) {
        return pow(exponent, exponent, dest);
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
        return pow(exponent.x(), exponent.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponentX);
        dd[1] = Math.pow(sd[1], exponentY);
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
    public Double2 project(Double2R onto, @Mutated Double2 dest) {
        return project(onto.x(), onto.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t5 = Math.fma(ontoX, sd[0], ontoY * sd[1]) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        dd[0] = ontoX * _t5;
        dd[1] = ontoY * _t5;
        return dest;
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
        return projectOnPlane(normal.x(), normal.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t1 = Math.fma(normalX, sd[0], normalY * sd[1]);
        dd[0] = Math.fma(-normalX, _t1, sd[0]);
        dd[1] = Math.fma(-normalY, _t1, sd[1]);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.toRadians(sd[0]);
        dd[1] = Math.toRadians(sd[1]);
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
    public Double2 reflect(Double2R normal, @Mutated Double2 dest) {
        return reflect(normal.x(), normal.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t2 = 2.0 * Math.fma(normalX, sd[0], normalY * sd[1]);
        dd[0] = Math.fma(-normalX, _t2, sd[0]);
        dd[1] = Math.fma(-normalY, _t2, sd[1]);
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
     * Valid input: {@code normal} must have unit length; this vector must have unit length.
     *
     * @param normal the normal of the refracting surface
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    public Double2 refract(Double2R normal, double eta, @Mutated Double2 dest) {
        return refract(normal.x(), normal.y(), eta, dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t2 = Math.fma(normalX, sd[0], normalY * sd[1]);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, Math.sqrt(Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            dd[0] = Math.fma(eta, sd[0], -(normalX * _t9));
            dd[1] = Math.fma(eta, sd[1], -(normalY * _t9));
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
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
    public Double2 round(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.rint(sd[0]);
        dd[1] = Math.rint(sd[1]);
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
    public Double2 sign(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
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
    public Double2 sin(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.sin(sd[0]);
        dd[1] = Math.sin(sd[1]);
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
    public Double2 sinh(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.sinh(sd[0]);
        dd[1] = Math.sinh(sd[1]);
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
    public Double2 smoothstep(double edge0, double edge1, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t7 = Math.max(0.0, Math.min(1.0, (sd[0] - edge0) * _t0_inv));
        double _t8 = Math.max(0.0, Math.min(1.0, (sd[1] - edge0) * _t0_inv));
        dd[0] = Math.fma(-2.0, _t7, 3.0) * _t7 * _t7;
        dd[1] = Math.fma(-2.0, _t8, 3.0) * _t8 * _t8;
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
    public Double2 smoothstep(Double2R edge0, Double2R edge1, @Mutated Double2 dest) {
        return smoothstep(edge0.x(), edge0.y(), edge1.x(), edge1.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t8 = Math.max(0.0, Math.min(1.0, (sd[0] - edge0X) / (edge1X - edge0X)));
        double _t9 = Math.max(0.0, Math.min(1.0, (sd[1] - edge0Y) / (edge1Y - edge0Y)));
        dd[0] = Math.fma(-2.0, _t8, 3.0) * _t8 * _t8;
        dd[1] = Math.fma(-2.0, _t9, 3.0) * _t9 * _t9;
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
    public Double2 sqrt(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.sqrt(sd[0]);
        dd[1] = Math.sqrt(sd[1]);
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
    public Double2 step(double edge, @Mutated Double2 dest) {
        return step(edge, edge, dest);
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
        return step(edge.x(), edge.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[0] < edgeX ? 0.0 : 1.0;
        dd[1] = sd[1] < edgeY ? 0.0 : 1.0;
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
    public Double2 tan(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.tan(sd[0]);
        dd[1] = Math.tan(sd[1]);
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
    public Double2 tanh(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.tanh(sd[0]);
        dd[1] = Math.tanh(sd[1]);
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
    public Double2 trunc(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[0] >= 0.0 ? Math.floor(sd[0]) : Math.ceil(sd[0]);
        dd[1] = sd[1] >= 0.0 ? Math.floor(sd[1]) : Math.ceil(sd[1]);
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
    public Double2 ulp(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.ulp(sd[0]);
        dd[1] = Math.ulp(sd[1]);
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
    public Double2 preMul(Double2x2R mat, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] matData = ((Double2x2Impl) mat).data;
        double[] dd = ((Double2Impl) dest).data;
        double _buf0 = Math.fma(matData[0], sd[0], matData[2] * sd[1]);
        dd[1] = Math.fma(matData[1], sd[0], matData[3] * sd[1]);
        dd[0] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] matData = ((Double2x3Impl) mat).data;
        double[] dd = ((Double2Impl) dest).data;
        double _buf0 = Math.fma(matData[0], sd[0], matData[2] * sd[1]);
        dd[1] = Math.fma(matData[1], sd[0], matData[3] * sd[1]);
        dd[0] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] matData = ((Double3x3Impl) mat).data;
        double[] dd = ((Double2Impl) dest).data;
        double _buf0 = Math.fma(matData[0], sd[0], matData[3] * sd[1]);
        dd[1] = Math.fma(matData[1], sd[0], matData[4] * sd[1]);
        dd[0] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] matData = ((Double4x4Impl) mat).data;
        double[] dd = ((Double2Impl) dest).data;
        double _buf0 = Math.fma(matData[0], sd[0], Math.fma(matData[4], sd[1], matData[12]));
        dd[1] = Math.fma(matData[1], sd[0], Math.fma(matData[5], sd[1], matData[13]));
        dd[0] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] matData = ((Double2x3Impl) mat).data;
        double[] dd = ((Double2Impl) dest).data;
        double _buf0 = Math.fma(matData[0], sd[0], Math.fma(matData[2], sd[1], matData[4]));
        dd[1] = Math.fma(matData[1], sd[0], Math.fma(matData[3], sd[1], matData[5]));
        dd[0] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] matData = ((Double3x3Impl) mat).data;
        double[] dd = ((Double2Impl) dest).data;
        double _buf0 = Math.fma(matData[0], sd[0], Math.fma(matData[3], sd[1], matData[6]));
        dd[1] = Math.fma(matData[1], sd[0], Math.fma(matData[4], sd[1], matData[7]));
        dd[0] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[0] = _buf0;
        return dest;
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
        return rotateAround(angle, pivot.x(), pivot.y(), dest);
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
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = sd[0] - pivotX;
        double _t3 = sd[1] - pivotY;
        dd[0] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX));
        dd[1] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY));
        return dest;
    }

    public double x() { return data[0]; }
    public double y() { return data[1]; }

    public Double2 xx(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Double2 xy(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Double2 yx(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Double2 yy(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Double3 xxx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Double3 xxy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 xyx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 xyy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 yxx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 yxy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 yyx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 yyy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Double4 xxxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xxxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xxyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xxyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xyxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xyxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xyyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xyyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yxxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yxxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yxyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yxyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yyxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yyxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yyyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yyyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Double2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2Impl)) return false;
        Double2Impl o = (Double2Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1]);
    }

    @Override public boolean equalsEpsilon(Double2R other, double epsilon) {
        return Math.abs(data[0] - other.x()) <= epsilon
            && Math.abs(data[1] - other.y()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        return dest;
    }
    public @Mutated Double2 load(double[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Double2 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Double2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Double2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Double2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        return dest;
    }
    public @Mutated Double2 load(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Double2 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public Double2 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public Double2 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public Double2 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
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
