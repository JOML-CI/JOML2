// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Immutable 2D vector of double-precision {@code double} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param x the {@code x} component
 * @param y the {@code y} component
 */
public record Double2(double x, double y) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

    /** The zero vector (all components 0). */
    public static final Double2 ZERO = new Double2(0, 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public Double2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public Double2() {
        this(0, 0);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public Double2(double s) {
        this(s, s);
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public double x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public double y() { return y; }

    /**
     * Create a direction uniformly distributed on the unit circle, drawing the sample of
     * {@code makeUniformDirection} from {@code rng}, each with {@code rng.nextDouble()}, in
     * parameter order.
     * <p>
     * Valid input: any value.
     *
     * @param rng the random number generator to draw the sample from
     * @return the resulting vector
     */
    public static Double2 makeRandomDirection(java.util.Random rng) {
        return makeUniformDirection(rng.nextDouble());
    }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Double2 add(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        return new Double2(otherX + this.x, otherY + this.y);
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Double2 add(double otherX, double otherY) {
        return new Double2(otherX + this.x, otherY + this.y);
    }


    /**
     * Divide each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Double2 div(double scalar) {
        return new Double2(this.x / scalar, this.y / scalar);
    }


    /**
     * Divide this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Double2 div(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        return new Double2(this.x / otherX, this.y / otherY);
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}), returning the result
     * as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Double2 div(double otherX, double otherY) {
        return new Double2(this.x / otherX, this.y / otherY);
    }


    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @return the resulting vector
     */
    public Double2 fma(double b, Double2 c) {
        double cX = c.x();
        double cY = c.y();
        return new Double2(Math.fma(this.x, b, cX), Math.fma(this.y, b, cY));
    }


    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}), i.e.
     * compute {@code this * b + (cX, cY)} per component, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @return the resulting vector
     */
    public Double2 fma(double b, double cX, double cY) {
        return new Double2(Math.fma(this.x, b, cX), Math.fma(this.y, b, cY));
    }


    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @return the resulting vector
     */
    public Double2 fma(Double2 b, Double2 c) {
        double bX = b.x();
        double bY = b.y();
        double cX = c.x();
        double cY = c.y();
        return new Double2(Math.fma(this.x, bX, cX), Math.fma(this.y, bY, cY));
    }


    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}) and add ({@code cX},
     * {@code cY}), i.e. compute {@code this * (bX, bY) + (cX, cY)} per component, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @return the resulting vector
     */
    public Double2 fma(double bX, double bY, double cX, double cY) {
        return new Double2(Math.fma(this.x, bX, cX), Math.fma(this.y, bY, cY));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Double2 mul(double scalar) {
        return new Double2(scalar * this.x, scalar * this.y);
    }


    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Double2 mul(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        return new Double2(otherX * this.x, otherY * this.y);
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}), returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Double2 mul(double otherX, double otherY) {
        return new Double2(otherX * this.x, otherY * this.y);
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 negate() {
        return new Double2(-this.x, -this.y);
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Double2 sub(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        return new Double2(this.x - otherX, this.y - otherY);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Double2 sub(double otherX, double otherY) {
        return new Double2(this.x - otherX, this.y - otherY);
    }


    /**
     * Create the unit vector at the angle {@code 2 PI u} counter-clockwise from the x axis: samples
     * uniformly distributed in {@code [0, 1)} give a direction uniformly distributed on the unit
     * circle ({@code makeRandomDirection} draws them from a {@link java.util.Random}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param u the fraction of a full turn counter-clockwise from the x axis, uniformly distributed
     *        in {@code [0, 1)} for a uniformly distributed direction
     * @return the resulting vector
     */
    public static Double2 makeUniformDirection(double u) {
        double _t0 = u * 6.283185307179586;
        double _t1 = Math.sin(_t0);
        return new Double2(Math.cosFromSin(_t1, _t0), _t1);
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Double2 set(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        return new Double2(vX, vY);
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting vector
     */
    public Double2 set(double vX, double vY) {
        return new Double2(vX, vY);
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Double2 set(double s) {
        return new Double2(s, s);
    }


    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float2} holding the result
     */
    public Float2 toFloat() {
        return new Float2((float) (this.x), (float) (this.y));
    }


    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte2} holding the result
     */
    public Byte2 toByte() {
        return new Byte2((byte) (this.x), (byte) (this.y));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte2 toByte_half_toward_positive_infinity() {
        return new Byte2((byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x))), (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y))));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte2 toByte_half_away_from_zero() {
        return new Byte2((byte) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (byte) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)));
    }


    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Byte2} holding the result
     */
    public Byte2 toByte(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toByte();
            case FLOOR -> new Byte2((byte) Math.floor(this.x), (byte) Math.floor(this.y));
            case CEILING -> new Byte2((byte) Math.ceil(this.x), (byte) Math.ceil(this.y));
            case HALF_TOWARD_POSITIVE_INFINITY -> toByte_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toByte_half_away_from_zero();
            case HALF_EVEN -> new Byte2((byte) Math.rint(this.x), (byte) Math.rint(this.y));
        };
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short2} holding the result
     */
    public Short2 toShort() {
        return new Short2((short) (this.x), (short) (this.y));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short2 toShort_half_toward_positive_infinity() {
        return new Short2((short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x))), (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y))));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short2 toShort_half_away_from_zero() {
        return new Short2((short) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (short) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)));
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Short2} holding the result
     */
    public Short2 toShort(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toShort();
            case FLOOR -> new Short2((short) Math.floor(this.x), (short) Math.floor(this.y));
            case CEILING -> new Short2((short) Math.ceil(this.x), (short) Math.ceil(this.y));
            case HALF_TOWARD_POSITIVE_INFINITY -> toShort_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toShort_half_away_from_zero();
            case HALF_EVEN -> new Short2((short) Math.rint(this.x), (short) Math.rint(this.y));
        };
    }


    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int2} holding the result
     */
    public Int2 toInt() {
        return new Int2((int) (this.x), (int) (this.y));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int2 toInt_half_toward_positive_infinity() {
        return new Int2((int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x))), (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y))));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int2 toInt_half_away_from_zero() {
        return new Int2((int) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (int) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)));
    }


    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Int2} holding the result
     */
    public Int2 toInt(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toInt();
            case FLOOR -> new Int2((int) Math.floor(this.x), (int) Math.floor(this.y));
            case CEILING -> new Int2((int) Math.ceil(this.x), (int) Math.ceil(this.y));
            case HALF_TOWARD_POSITIVE_INFINITY -> toInt_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toInt_half_away_from_zero();
            case HALF_EVEN -> new Int2((int) Math.rint(this.x), (int) Math.rint(this.y));
        };
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long2} holding the result
     */
    public Long2 toLong() {
        return new Long2((long) (this.x), (long) (this.y));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toLong(RoundingMode)}; reached only through it. */
    private Long2 toLong_half_away_from_zero() {
        return new Long2((long) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (long) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)));
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Long2} holding the result
     */
    public Long2 toLong(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toLong();
            case FLOOR -> new Long2((long) Math.floor(this.x), (long) Math.floor(this.y));
            case CEILING -> new Long2((long) Math.ceil(this.x), (long) Math.ceil(this.y));
            case HALF_TOWARD_POSITIVE_INFINITY -> new Long2(Math.round(this.x), Math.round(this.y));
            case HALF_AWAY_FROM_ZERO -> toLong_half_away_from_zero();
            case HALF_EVEN -> new Long2((long) Math.rint(this.x), (long) Math.rint(this.y));
        };
    }


    /**
     * Create an all-zero vector.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting vector
     */
    public static Double2 makeZero() {
        return Double2.ZERO;
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 bezier(Double2 p1, Double2 p2, Double2 p3, double t) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p3X = p3.x();
        double p3Y = p3.y();
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        return new Double2(Math.fma(p1X, _t7, this.x * _t8) + Math.fma(p2X, _t6, p3X * _t2), Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}) and ({@code p2X}, {@code p2Y}) and ends at ({@code p3X},
     * {@code p3Y}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 bezier(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        return new Double2(Math.fma(p1X, _t7, this.x * _t8) + Math.fma(p2X, _t6, p3X * _t2), Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 bezier2(Double2 p1, Double2 p2, double t) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        return new Double2(Math.fma(p2X, _t0, Math.fma(p1X, _t3, this.x * _t4)), Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, this.y * _t4)));
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}) and ends at ({@code p2X}, {@code p2Y}), returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Double2 bezier2(double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        return new Double2(Math.fma(p2X, _t0, Math.fma(p1X, _t3, this.x * _t4)), Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, this.y * _t4)));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 bezier2Tangent(Double2 p1, Double2 p2, double t) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        return new Double2(Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}) and ends at ({@code p2X}, {@code p2Y}), returning the result as a
     * value.
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
     * @return the resulting vector
     */
    public Double2 bezier2Tangent(double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        return new Double2(Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 bezierTangent(Double2 p1, Double2 p2, Double2 p3, double t) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p3X = p3.x();
        double p3Y = p3.y();
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        return new Double2(Math.fma(p3X - p2X, _t2, Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5)), Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5)));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}) and ({@code p2X}, {@code p2Y}) and ends at ({@code p3X}, {@code p3Y}), returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Double2 bezierTangent(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        return new Double2(Math.fma(p3X - p2X, _t2, Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5)), Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5)));
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from {@code p1} to {@code p2}, with this
     * vector as the control point before the segment and {@code p3} as the control point after it,
     * returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 catmullRom(Double2 p1, Double2 p2, Double2 p3, double t) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p3X = p3.x();
        double p3Y = p3.y();
        double _t0 = t * t;
        double _t1 = t * _t0;
        return new Double2(0.5 * (Math.fma(2.0, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)) * _t1)), 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)) * _t1)));
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}) to
     * ({@code p2X}, {@code p2Y}), with this vector as the control point before the segment and
     * ({@code p3X}, {@code p3Y}) as the control point after it, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 catmullRom(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _t0 = t * t;
        double _t1 = t * _t0;
        return new Double2(0.5 * (Math.fma(2.0, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)) * _t1)), 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)) * _t1)));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from {@code p1} to {@code p2}, with this vector as the control
     * point before the segment and {@code p3} as the control point after it, returning the result
     * as a value.
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
     * @return the resulting vector
     */
    public Double2 catmullRomTangent(Double2 p1, Double2 p2, Double2 p3, double t) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p3X = p3.x();
        double p3Y = p3.y();
        double _t0 = t * t;
        return new Double2(0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)), _t0, p2X - this.x)), 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)), _t0, p2Y - this.y)));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}) to ({@code p2X}, {@code p2Y}),
     * with this vector as the control point before the segment and ({@code p3X}, {@code p3Y}) as
     * the control point after it, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 catmullRomTangent(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _t0 = t * t;
        return new Double2(0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, this.x, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - this.x)), _t0, p2X - this.x)), 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, this.y, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - this.y)), _t0, p2Y - this.y)));
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * {@code t0} and ends at {@code v1} with the tangent {@code t1}, returning the result as a
     * value.
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
     * @return the resulting vector
     */
    public Double2 hermite(Double2 t0, Double2 v1, Double2 t1, double t) {
        double t0X = t0.x();
        double t0Y = t0.y();
        double v1X = v1.x();
        double v1Y = v1.y();
        double t1X = t1.x();
        double t1Y = t1.y();
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        return new Double2(Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9), Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}) and ends at ({@code v1X}, {@code v1Y}) with the tangent
     * ({@code t1X}, {@code t1Y}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 hermite(double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        return new Double2(Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9), Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent {@code t0} and ends at
     * {@code v1} with the tangent {@code t1}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 hermiteTangent(Double2 t0, Double2 v1, Double2 t1, double t) {
        double t0X = t0.x();
        double t0Y = t0.y();
        double v1X = v1.x();
        double v1Y = v1.y();
        double t1X = t1.x();
        double t1Y = t1.y();
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        return new Double2(Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7), Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y})
     * and ends at ({@code v1X}, {@code v1Y}) with the tangent ({@code t1X}, {@code t1Y}), returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Double2 hermiteTangent(double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        return new Double2(Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7), Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
    }


    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 lerp(Double2 other, double t) {
        double otherX = other.x();
        double otherY = other.y();
        return new Double2(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y));
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}) using the
     * interpolation factor {@code t}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 lerp(double otherX, double otherY, double t) {
        return new Double2(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y));
    }


    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 lerp(Double2 other, Double2 t) {
        double otherX = other.x();
        double otherY = other.y();
        double tX = t.x();
        double tY = t.y();
        return new Double2(Math.fma(tX, otherX - this.x, this.x), Math.fma(tY, otherY - this.y, this.y));
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}) using the
     * interpolation factor ({@code tX}, {@code tY}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 lerp(double otherX, double otherY, double tX, double tY) {
        return new Double2(Math.fma(tX, otherX - this.x, this.x), Math.fma(tY, otherY - this.y, this.y));
    }


    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths, returning the result as
     * a value.
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
     * @return the resulting vector
     */
    public Double2 slerp(Double2 other, double t) {
        double otherX = other.x();
        double otherY = other.y();
        double _t5 = Math.fma(this.x, this.x, this.y * this.y);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, t);
        double _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, t);
        double _t7 = (1.0 / java.lang.Math.sqrt(_t5));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t12 = this.x * _t7;
        double _t16 = this.y * _t7;
        double _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        Double2 _r = slerp_s17076746_tail(otherY, _t10, _t21, _t16, Math.fma(otherX, _t10, -(_t21 * _t12)), _t12, t, _t6, _t5);
        return _r != null ? _r : slerp_degenerate(otherX, otherY, t);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private Double2 slerp_s17076746_tail(double otherY, double _t10, double _t21, double _t16, double _t26, double _t12, double t, double _t6, double _t5) {
        double _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        double _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return null;
        double _t20 = t * java.lang.Math.sqrt(_t6) + (1.0 - t) * java.lang.Math.sqrt(_t5);
        double _t39 = t * Math.atan2(java.lang.Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / java.lang.Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        return new Double2(Math.fma(_t12, _t44, _sp0 * _t31), Math.fma(_t16, _t44, _sp0 * _t32));
    }


    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY}) using the
     * interpolation factor {@code t}: the direction turns at a constant rate along the shorter arc
     * between the two directions, and the length changes linearly between the two lengths,
     * returning the result as a value.
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
     * @return the resulting vector
     */
    public Double2 slerp(double otherX, double otherY, double t) {
        double _t5 = Math.fma(this.x, this.x, this.y * this.y);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, t);
        double _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, t);
        double _t7 = (1.0 / java.lang.Math.sqrt(_t5));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t12 = this.x * _t7;
        double _t16 = this.y * _t7;
        double _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        Double2 _r = slerp_s17076746_tail(otherY, _t10, _t21, _t16, Math.fma(otherX, _t10, -(_t21 * _t12)), _t12, t, _t6, _t5);
        return _r != null ? _r : slerp_degenerate(otherX, otherY, t);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double2 slerp_degenerate_s17076746_tail(double _t48, double _t49, double t, double _t38, double _t37, double _t25, double _t28, double _t35, double _t11_inv, double otherX, double _t27, double otherY) {
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t68 = _t37 * Math.sin(_t64);
        double _t69 = _t37 * Math.cos(_t64);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t35 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    return new Double2(Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    return new Double2(Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                return new Double2(Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            return new Double2(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y));
        }
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double2 slerp_degenerate(double otherX, double otherY, double t) {
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(this.x, this.y, this.x);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = this.x * _t2;
        double _t10 = this.y * _t2;
        double _t11 = java.lang.Math.min(_t2, _t1);
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_s17076746_tail(Math.fma(_t47, _t25, _t43), Math.fma(_t47, _t27, _t44), t, _t38, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t25, -_t27, _t18 * _t19, 1.0 / _t11, otherX, _t27, otherY);
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 absolute() {
        return new Double2(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y));
    }


    /**
     * Compute the arc cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Double2 acos() {
        return new Double2(Math.acos(this.x), Math.acos(this.y));
    }


    /**
     * Add {@code b} scaled by {@code scalar} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param scalar the factor to scale {@code b} by before adding
     * @return the resulting vector
     */
    public Double2 addScaled(Double2 b, double scalar) {
        double bX = b.x();
        double bY = b.y();
        return new Double2(Math.fma(scalar, bX, this.x), Math.fma(scalar, bY, this.y));
    }


    /**
     * Add ({@code bX}, {@code bY}) scaled by {@code scalar} to this vector, returning the result as
     * a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}) by before adding
     * @return the resulting vector
     */
    public Double2 addScaled(double bX, double bY, double scalar) {
        return new Double2(Math.fma(scalar, bX, this.x), Math.fma(scalar, bY, this.y));
    }


    /**
     * Add {@code b} scaled by {@code c} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param c the per-component factors to scale {@code b} by before adding
     * @return the resulting vector
     */
    public Double2 addScaled(Double2 b, Double2 c) {
        double bX = b.x();
        double bY = b.y();
        double cX = c.x();
        double cY = c.y();
        return new Double2(Math.fma(bX, cX, this.x), Math.fma(bY, cY, this.y));
    }


    /**
     * Add ({@code bX}, {@code bY}) scaled by ({@code cX}, {@code cY}) to this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @return the resulting vector
     */
    public Double2 addScaled(double bX, double bY, double cX, double cY) {
        return new Double2(Math.fma(bX, cX, this.x), Math.fma(bY, cY, this.y));
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
    public double angleBetween(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        double _ct0 = java.lang.Math.abs(Math.fma(otherY, this.x, -(otherX * this.y)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, this.x, otherY * this.y));
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
        double _ct0 = java.lang.Math.abs(Math.fma(otherY, this.x, -(otherX * this.y)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, this.x, otherY * this.y));
    }


    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate(double otherX, double otherY) {
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(this.x, this.y, this.x);
        double _t6 = otherY * _t0;
        double _t7 = this.x * _t1;
        double _t8 = otherX * _t0;
        double _t9 = this.y * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }


    /**
     * Compute the arc sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Double2 asin() {
        return new Double2(Math.asin(this.x), Math.asin(this.y));
    }


    /**
     * Compute the arc tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 atan() {
        return new Double2(Math.atan(this.x), Math.atan(this.y));
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} {@code x} (the denominator), returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the value to take the arc tangent over (the denominator)
     * @return the resulting vector
     */
    public Double2 atan2(double x) {
        return new Double2(Math.atan2(this.x, x), Math.atan2(this.y, x));
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of {@code x} (the
     * denominator), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the vector of denominators, one per component
     * @return the resulting vector
     */
    public Double2 atan2(Double2 x) {
        double xX = x.x();
        double xY = x.y();
        return new Double2(Math.atan2(this.x, xX), Math.atan2(this.y, xY));
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}) (the denominator), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xX the {@code x} component of the vector {@code (xX, xY)}
     * @param xY the {@code y} component of the vector {@code (xX, xY)}
     * @return the resulting vector
     */
    public Double2 atan2(double xX, double xY) {
        return new Double2(Math.atan2(this.x, xX), Math.atan2(this.y, xY));
    }


    /**
     * Compute the cube root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 cbrt() {
        return new Double2(Math.cbrt(this.x), Math.cbrt(this.y));
    }


    /**
     * Compute the ceiling of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 ceil() {
        return new Double2(Math.ceil(this.x), Math.ceil(this.y));
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max}, returning the result
     * as a value.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return the resulting vector
     */
    public Double2 clamp(double min, double max) {
        return new Double2(java.lang.Math.min(java.lang.Math.max(this.x, min), max), java.lang.Math.min(java.lang.Math.max(this.y, min), max));
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max}, returning the result
     * as a value.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return the resulting vector
     */
    public Double2 clamp(Double2 min, Double2 max) {
        double minX = min.x();
        double minY = min.y();
        double maxX = max.x();
        double maxY = max.y();
        return new Double2(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}), returning the result as a value.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @return the resulting vector
     */
    public Double2 clamp(double minX, double minY, double maxX, double maxY) {
        return new Double2(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
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
     * Copy the sign of {@code sign} onto each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @return the resulting vector
     */
    public Double2 copySign(double sign) {
        return new Double2(Math.copySign(this.x, sign), Math.copySign(this.y, sign));
    }


    /**
     * Copy the sign of each component of {@code sign} onto the corresponding component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @return the resulting vector
     */
    public Double2 copySign(Double2 sign) {
        double signX = sign.x();
        double signY = sign.y();
        return new Double2(Math.copySign(this.x, signX), Math.copySign(this.y, signY));
    }


    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}) onto the corresponding
     * component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param signX the {@code x} component of the vector {@code (signX, signY)}
     * @param signY the {@code y} component of the vector {@code (signX, signY)}
     * @return the resulting vector
     */
    public Double2 copySign(double signX, double signY) {
        return new Double2(Math.copySign(this.x, signX), Math.copySign(this.y, signY));
    }


    /**
     * Compute the cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 cos() {
        return new Double2(Math.cos(this.x), Math.cos(this.y));
    }


    /**
     * Compute the hyperbolic cosine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 cosh() {
        return new Double2(Math.cosh(this.x), Math.cosh(this.y));
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
    public double cross(Double2 other) {
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
        return Math.fma(otherY, this.x, -(otherX * this.y));
    }


    /**
     * Compute the value converted from radians to degrees of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 degrees() {
        return new Double2(Math.toDegrees(this.x), Math.toDegrees(this.y));
    }


    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    public double distance(Double2 other) {
        double _t0 = this.x - other.x();
        double _t1 = this.y - other.y();
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
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
        double _t0 = this.x - otherX;
        double _t1 = this.y - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public double distanceSquared(Double2 other) {
        double _t0 = this.x - other.x();
        double _t1 = this.y - other.y();
        return Math.fma(_t0, _t0, _t1 * _t1);
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
        double _t0 = this.x - otherX;
        double _t1 = this.y - otherY;
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
    public double dot(Double2 other) {
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
        return Math.fma(otherX, this.x, otherY * this.y);
    }


    /**
     * Compute the base-e exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 exp() {
        return new Double2(Math.exp(this.x), Math.exp(this.y));
    }


    /**
     * Compute the base-2 exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 exp2() {
        return new Double2(Math.pow(2.0, this.x), Math.pow(2.0, this.y));
    }


    /**
     * Compute the base-e exponential minus one of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 expm1() {
        return new Double2(Math.expm1(this.x), Math.expm1(this.y));
    }


    /**
     * Return this vector unchanged when {@code dot(Nref, I)} is negative, and negated otherwise -
     * orienting it against the incident direction {@code I} as judged by the reference vector
     * {@code Nref}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @return the resulting vector
     */
    public Double2 faceforward(Double2 I, Double2 Nref) {
        double _t2 = Math.fma(I.x(), Nref.x(), I.y() * Nref.y()) < 0.0 ? 1.0 : -1.0;
        return new Double2(this.x * _t2, this.y * _t2);
    }


    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY), (IX, IY))} is negative, and
     * negated otherwise - orienting it against the incident direction ({@code IX}, {@code IY}) as
     * judged by the reference vector ({@code NrefX}, {@code NrefY}), returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY)}
     * @param IY the {@code y} component of the vector {@code (IX, IY)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY)}
     * @return the resulting vector
     */
    public Double2 faceforward(double IX, double IY, double NrefX, double NrefY) {
        double _t2 = Math.fma(IX, NrefX, IY * NrefY) < 0.0 ? 1.0 : -1.0;
        return new Double2(this.x * _t2, this.y * _t2);
    }


    /**
     * Compute the floor of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 floor() {
        return new Double2(Math.floor(this.x), Math.floor(this.y));
    }


    /**
     * Compute the fractional part of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 fract() {
        return new Double2(java.lang.Math.min(this.x - Math.floor(this.x), 0.9999999999999999), java.lang.Math.min(this.y - Math.floor(this.y), 0.9999999999999999));
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} {@code y}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the other operand
     * @return the resulting vector
     */
    public Double2 hypot(double y) {
        return new Double2(Math.hypot(this.x, y), Math.hypot(this.y, y));
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of {@code y}, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the vector of other operands, one per component
     * @return the resulting vector
     */
    public Double2 hypot(Double2 y) {
        double yX = y.x();
        double yY = y.y();
        return new Double2(Math.hypot(this.x, yX), Math.hypot(this.y, yY));
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY)}
     * @param yY the {@code y} component of the vector {@code (yX, yY)}
     * @return the resulting vector
     */
    public Double2 hypot(double yX, double yY) {
        return new Double2(Math.hypot(this.x, yX), Math.hypot(this.y, yY));
    }


    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector, returning the result
     * as a value.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @return the resulting vector
     */
    public Double2 inverse() {
        return new Double2(1.0 / this.x, 1.0 / this.y);
    }


    /**
     * Compute the inverse square root of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double2 inverseSqrt() {
        return new Double2((1.0 / java.lang.Math.sqrt(this.x)), (1.0 / java.lang.Math.sqrt(this.y)));
    }


    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this vector
     */
    public double length() {
        return java.lang.Math.sqrt(Math.fma(this.x, this.x, this.y * this.y));
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public double lengthSquared() {
        return Math.fma(this.x, this.x, this.y * this.y);
    }


    /**
     * Compute the natural logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double2 log() {
        return new Double2(Math.log(this.x), Math.log(this.y));
    }


    /**
     * Compute the base-10 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double2 log10() {
        return new Double2(Math.log10(this.x), Math.log10(this.y));
    }


    /**
     * Compute the natural logarithm of one plus the value of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @return the resulting vector
     */
    public Double2 log1p() {
        return new Double2(Math.log1p(this.x), Math.log1p(this.y));
    }


    /**
     * Compute the base-2 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double2 log2() {
        return new Double2(Math.log2(this.x), Math.log2(this.y));
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public double manhattanDistance(Double2 other) {
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
     * Set each component of this vector to the larger of itself and {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return the resulting vector
     */
    public Double2 max(double scalar) {
        return new Double2(java.lang.Math.max(this.x, scalar), java.lang.Math.max(this.y, scalar));
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @return the resulting vector
     */
    public Double2 max(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        return new Double2(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY));
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Double2 max(double otherX, double otherY) {
        return new Double2(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY));
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return the resulting vector
     */
    public Double2 min(double scalar) {
        return new Double2(java.lang.Math.min(this.x, scalar), java.lang.Math.min(this.y, scalar));
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @return the resulting vector
     */
    public Double2 min(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        return new Double2(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY));
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Double2 min(double otherX, double otherY) {
        return new Double2(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY));
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}), returning the result as a value.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: {@code y} must be non-zero.
     *
     * @param y the divisor
     * @return the resulting vector
     */
    public Double2 mod(double y) {
        return new Double2(flooredMod(this.x, y), flooredMod(this.y, y));
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}), returning the result as a value.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @return the resulting vector
     */
    public Double2 mod(Double2 y) {
        double yX = y.x();
        double yY = y.y();
        return new Double2(flooredMod(this.x, yX), flooredMod(this.y, yY));
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY})
     * ({@code x % y}, plus {@code y} when that remainder is non-zero and its sign differs from
     * {@code y}'s - exactly Kotlin's {@code mod}), returning the result as a value.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (yX, yY)} must be non-zero.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY)}
     * @param yY the {@code y} component of the vector {@code (yX, yY)}
     * @return the resulting vector
     */
    public Double2 mod(double yX, double yY) {
        return new Double2(flooredMod(this.x, yX), flooredMod(this.y, yY));
    }


    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 nextDown() {
        return new Double2(Math.nextDown(this.x), Math.nextDown(this.y));
    }


    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 nextUp() {
        return new Double2(Math.nextUp(this.x), Math.nextUp(this.y));
    }


    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector), returning the
     * result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double2 normalize() {
        double _t1 = Math.fma(this.x, this.x, this.y * this.y);
        double _t2 = (1.0 / java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0) {
            return new Double2(this.x * _t2, this.y * _t2);
        } else {
            return Double2.ZERO;
        }
    }


    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @return the resulting vector
     */
    public Double2 normalizeMul(double length) {
        double _t1 = Math.fma(this.x, this.x, this.y * this.y);
        double _t3 = length * (1.0 / java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0) {
            return new Double2(this.x * _t3, this.y * _t3);
        } else {
            return Double2.ZERO;
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
    public double orientedAngle(Double2 other) {
        double otherX = other.x();
        double otherY = other.y();
        double _t2 = Math.fma(otherY, this.x, -(otherX * this.y));
        double _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate(otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, this.x, otherY * this.y));
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
        double _t2 = Math.fma(otherY, this.x, -(otherX * this.y));
        double _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate(otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, this.x, otherY * this.y));
    }


    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate(double otherX, double otherY) {
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(this.x, this.y, this.x);
        double _t6 = otherY * _t0;
        double _t7 = this.x * _t1;
        double _t8 = otherX * _t0;
        double _t9 = this.y * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }


    /**
     * Compute the outer product of this vector and {@code row}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @return the resulting matrix
     */
    public Double2x2 outerProduct(Double2 row) {
        double rowX = row.x();
        double rowY = row.y();
        return new Double2x2(rowX * this.x, rowY * this.x, rowX * this.y, rowY * this.y, 0);
    }


    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY)}
     * @return the resulting matrix
     */
    public Double2x2 outerProduct(double rowX, double rowY) {
        return new Double2x2(rowX * this.x, rowY * this.x, rowX * this.y, rowY * this.y, 0);
    }


    /**
     * Raise each component of this vector to the power of {@code exponent}, returning the result as
     * a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @return the resulting vector
     */
    public Double2 pow(double exponent) {
        return new Double2(Math.pow(this.x, exponent), Math.pow(this.y, exponent));
    }


    /**
     * Raise each component of this vector to the power of {@code exponent}, returning the result as
     * a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @return the resulting vector
     */
    public Double2 pow(Double2 exponent) {
        double exponentX = exponent.x();
        double exponentY = exponent.y();
        return new Double2(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY));
    }


    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY}),
     * returning the result as a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponentX the {@code x} component of the vector {@code (exponentX, exponentY)}
     * @param exponentY the {@code y} component of the vector {@code (exponentX, exponentY)}
     * @return the resulting vector
     */
    public Double2 pow(double exponentX, double exponentY) {
        return new Double2(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY));
    }


    /**
     * Project this vector onto {@code onto}, returning the result as a value.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @return the resulting vector
     */
    public Double2 project(Double2 onto) {
        double ontoX = onto.x();
        double ontoY = onto.y();
        double _t5 = Math.fma(ontoX, this.x, ontoY * this.y) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        return new Double2(ontoX * _t5, ontoY * _t5);
    }


    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}), returning the result as a value.
     * <p>
     * Valid input: {@code (ontoX, ontoY)} must be non-zero.
     *
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY)}
     * @return the resulting vector
     */
    public Double2 project(double ontoX, double ontoY) {
        double _t5 = Math.fma(ontoX, this.x, ontoY * this.y) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        return new Double2(ontoX * _t5, ontoY * _t5);
    }


    /**
     * Project this vector onto the plane with the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @return the resulting vector
     */
    public Double2 projectOnPlane(Double2 normal) {
        double normalX = normal.x();
        double normalY = normal.y();
        double _t1 = Math.fma(normalX, this.x, normalY * this.y);
        return new Double2(Math.fma(-normalX, _t1, this.x), Math.fma(-normalY, _t1, this.y));
    }


    /**
     * Project this vector onto the plane with the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code (normalX, normalY)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY)}
     * @return the resulting vector
     */
    public Double2 projectOnPlane(double normalX, double normalY) {
        double _t1 = Math.fma(normalX, this.x, normalY * this.y);
        return new Double2(Math.fma(-normalX, _t1, this.x), Math.fma(-normalY, _t1, this.y));
    }


    /**
     * Compute the value converted from degrees to radians of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 radians() {
        return new Double2(Math.toRadians(this.x), Math.toRadians(this.y));
    }


    /**
     * Reflect this vector about the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @return the resulting vector
     */
    public Double2 reflect(Double2 normal) {
        double normalX = normal.x();
        double normalY = normal.y();
        double _t2 = 2.0 * Math.fma(normalX, this.x, normalY * this.y);
        return new Double2(Math.fma(-normalX, _t2, this.x), Math.fma(-normalY, _t2, this.y));
    }


    /**
     * Reflect this vector about the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code (normalX, normalY)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY)}
     * @return the resulting vector
     */
    public Double2 reflect(double normalX, double normalY) {
        double _t2 = 2.0 * Math.fma(normalX, this.x, normalY * this.y);
        return new Double2(Math.fma(-normalX, _t2, this.x), Math.fma(-normalY, _t2, this.y));
    }


    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Double2 refract(Double2 normal, double eta) {
        double normalX = normal.x();
        double normalY = normal.y();
        double _t2 = Math.fma(normalX, this.x, normalY * this.y);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            return new Double2(Math.fma(eta, this.x, -(normalX * _t9)), Math.fma(eta, this.y, -(normalY * _t9)));
        } else {
            return Double2.ZERO;
        }
    }


    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Double2 refract(double normalX, double normalY, double eta) {
        double _t2 = Math.fma(normalX, this.x, normalY * this.y);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            return new Double2(Math.fma(eta, this.x, -(normalX * _t9)), Math.fma(eta, this.y, -(normalY * _t9)));
        } else {
            return Double2.ZERO;
        }
    }


    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 round() {
        return new Double2(Math.rint(this.x), Math.rint(this.y));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 sign() {
        return new Double2(Math.signum(this.x), Math.signum(this.y));
    }


    /**
     * Compute the sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 sin() {
        return new Double2(Math.sin(this.x), Math.sin(this.y));
    }


    /**
     * Compute the hyperbolic sine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 sinh() {
        return new Double2(Math.sinh(this.x), Math.sinh(this.y));
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge, returning the result as a value.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @return the resulting vector
     */
    public Double2 smoothstep(double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t7 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0) * _t0_inv));
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0) * _t0_inv));
        return new Double2(Math.fma(-2.0, _t7, 3.0) * _t7 * _t7, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge, returning the result as a value.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @return the resulting vector
     */
    public Double2 smoothstep(Double2 edge0, Double2 edge1) {
        double edge0X = edge0.x();
        double edge0Y = edge0.y();
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1.x() - edge0X)));
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1.y() - edge0Y)));
        return new Double2(Math.fma(-2.0, _t8, 3.0) * _t8 * _t8, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}) and the upper edge ({@code edge1X},
     * {@code edge1Y}), yielding 0 at or below the lower edge and 1 at or above the upper edge,
     * returning the result as a value.
     * <p>
     * Valid input: {@code (edge0X, edge0Y)} and {@code (edge1X, edge1Y)} must differ in every
     * component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y)}
     * @return the resulting vector
     */
    public Double2 smoothstep(double edge0X, double edge0Y, double edge1X, double edge1Y) {
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
        return new Double2(Math.fma(-2.0, _t8, 3.0) * _t8 * _t8, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
    }


    /**
     * Compute the square root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @return the resulting vector
     */
    public Double2 sqrt() {
        return new Double2(java.lang.Math.sqrt(this.x), java.lang.Math.sqrt(this.y));
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than {@code edge}, and to
     * {@code 1} otherwise, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @return the resulting vector
     */
    public Double2 step(double edge) {
        return new Double2(this.x < edge ? 0.0 : 1.0, this.y < edge ? 0.0 : 1.0);
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @return the resulting vector
     */
    public Double2 step(Double2 edge) {
        double edgeX = edge.x();
        double edgeY = edge.y();
        return new Double2(this.x < edgeX ? 0.0 : 1.0, this.y < edgeY ? 0.0 : 1.0);
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY)}
     * @return the resulting vector
     */
    public Double2 step(double edgeX, double edgeY) {
        return new Double2(this.x < edgeX ? 0.0 : 1.0, this.y < edgeY ? 0.0 : 1.0);
    }


    /**
     * Compute the tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 tan() {
        return new Double2(Math.tan(this.x), Math.tan(this.y));
    }


    /**
     * Compute the hyperbolic tangent of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 tanh() {
        return new Double2(Math.tanh(this.x), Math.tanh(this.y));
    }


    /**
     * Compute the truncated value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 trunc() {
        return new Double2(this.x >= 0.0 ? Math.floor(this.x) : Math.ceil(this.x), this.y >= 0.0 ? Math.floor(this.y) : Math.ceil(this.y));
    }


    /**
     * Compute the unit in the last place (ulp) of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double2 ulp() {
        return new Double2(Math.ulp(this.x), Math.ulp(this.y));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, i.e. compute {@code mat * this}, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Double2 preMul(Double2x2 mat) {
        return new Double2(Math.fma(mat.m00(), this.x, mat.m01() * this.y), Math.fma(mat.m10(), this.x, mat.m11() * this.y));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xy}, applying only rotation and scale and ignoring
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Double2 preMulDirection(Double2x3 mat) {
        return new Double2(Math.fma(mat.m00(), this.x, mat.m01() * this.y), Math.fma(mat.m10(), this.x, mat.m11() * this.y));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xy}, applying only rotation and scale and ignoring
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Double2 preMulDirection(Double3x3 mat) {
        return new Double2(Math.fma(mat.m00(), this.x, mat.m01() * this.y), Math.fma(mat.m10(), this.x, mat.m11() * this.y));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as the point {@code (x, y, 0, 1)} of the
     * xy-plane - i.e. compute {@code (mat * (this, 0, 1)).xy}, applying the full affine transform
     * including translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Double2 preMulPosition(Double4x4 mat) {
        return new Double2(Math.fma(mat.m00(), this.x, Math.fma(mat.m01(), this.y, mat.m03())), Math.fma(mat.m10(), this.x, Math.fma(mat.m11(), this.y, mat.m13())));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xy}, applying the full affine transform including
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Double2 preMulPosition(Double2x3 mat) {
        return new Double2(Math.fma(mat.m00(), this.x, Math.fma(mat.m01(), this.y, mat.m02())), Math.fma(mat.m10(), this.x, Math.fma(mat.m11(), this.y, mat.m12())));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xy}, applying the full affine transform including
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Double2 preMulPosition(Double3x3 mat) {
        return new Double2(Math.fma(mat.m00(), this.x, Math.fma(mat.m01(), this.y, mat.m02())), Math.fma(mat.m10(), this.x, Math.fma(mat.m11(), this.y, mat.m12())));
    }


    /**
     * Rotate this vector counter-clockwise about the origin by {@code angle} radians, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting vector
     */
    public Double2 rotate(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double2(Math.fma(this.x, _t1, -(this.y * _t0)), Math.fma(this.x, _t0, this.y * _t1));
    }


    /**
     * Rotate this vector counter-clockwise by {@code angle} radians about the point {@code pivot},
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return the resulting vector
     */
    public Double2 rotateAround(double angle, Double2 pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = this.x - pivotX;
        double _t3 = this.y - pivotY;
        return new Double2(Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)), Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
    }


    /**
     * Rotate this vector counter-clockwise by {@code angle} radians about the point
     * ({@code pivotX}, {@code pivotY}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return the resulting vector
     */
    public Double2 rotateAround(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = this.x - pivotX;
        double _t3 = this.y - pivotY;
        return new Double2(Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)), Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     */
    public Double2 withX(double x) {
        return new Double2(x, this.y());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     */
    public Double2 withY(double y) {
        return new Double2(this.x(), y);
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Double2 withXY(double x, double y) {
        return new Double2(x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 xx() {
        return new Double2(x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 xy() {
        return new Double2(x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 yx() {
        return new Double2(y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 yy() {
        return new Double2(y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xxx() {
        return new Double3(x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xxy() {
        return new Double3(x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xyx() {
        return new Double3(x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xyy() {
        return new Double3(x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yxx() {
        return new Double3(y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yxy() {
        return new Double3(y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yyx() {
        return new Double3(y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yyy() {
        return new Double3(y, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxxx() {
        return new Double4(x, x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxxy() {
        return new Double4(x, x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxyx() {
        return new Double4(x, x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxyy() {
        return new Double4(x, x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyxx() {
        return new Double4(x, y, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyxy() {
        return new Double4(x, y, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyyx() {
        return new Double4(x, y, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyyy() {
        return new Double4(x, y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxxx() {
        return new Double4(y, x, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxxy() {
        return new Double4(y, x, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxyx() {
        return new Double4(y, x, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxyy() {
        return new Double4(y, x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyxx() {
        return new Double4(y, y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyxy() {
        return new Double4(y, y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyyx() {
        return new Double4(y, y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyyy() {
        return new Double4(y, y, y, y);
    }

    @Override public String toString() {
        return "Double2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2)) return false;
        Double2 o = (Double2) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y);
    }

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Double2 other, double epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon;
    }

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


    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store(double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        return new Double2(_c0, _c1);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer store(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2 loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 2);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 16);
        return r;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }


    /**
     * Store the elements into the given array, converting each element to {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) {
        dest[offset] = (float) this.x;
        dest[offset + 1] = (float) this.y;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        return new Double2(_c0, _c1);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer store(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 2);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double2} holding the loaded elements
     */
    public static Double2 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2 loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2 r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double2 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double2 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
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
