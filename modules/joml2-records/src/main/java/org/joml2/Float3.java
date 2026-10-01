// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Immutable 3D vector of single-precision {@code float} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param x the {@code x} component
 * @param y the {@code y} component
 * @param z the {@code z} component
 */
public record Float3(float x, float y, float z) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 12;

    /** The zero vector (all components 0). */
    public static final Float3 ZERO = new Float3(0, 0, 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public Float3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public Float3() {
        this(0, 0, 0);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public Float3(float s) {
        this(s, s, s);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public Float3(float v0, Float2 v1) {
        this(v0, v1.x(), v1.y());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public Float3(Float2 v0, float v1) {
        this(v0.x(), v0.y(), v1);
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public float x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public float y() { return y; }
    /** {@return the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    public float z() { return z; }

    /**
     * Create a direction uniformly distributed on the unit sphere, drawing the 2 samples of
     * {@code makeUniformDirection} from {@code rng}, each with {@code rng.nextFloat()}, in
     * parameter order.
     * <p>
     * Valid input: any value.
     *
     * @param rng the random number generator to draw the 2 samples from
     * @return the resulting vector
     */
    public static Float3 makeRandomDirection(java.util.Random rng) {
        return makeUniformDirection(rng.nextFloat(), rng.nextFloat());
    }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Float3 add(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(otherX + this.x, otherY + this.y, otherZ + this.z);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, returning the result as
     * a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Float3 add(float otherX, float otherY, float otherZ) {
        return new Float3(otherX + this.x, otherY + this.y, otherZ + this.z);
    }


    /**
     * Divide each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Float3 div(float scalar) {
        return new Float3(this.x / scalar, this.y / scalar, this.z / scalar);
    }


    /**
     * Divide this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Float3 div(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(this.x / otherX, this.y / otherY, this.z / otherZ);
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}),
     * returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Float3 div(float otherX, float otherY, float otherZ) {
        return new Float3(this.x / otherX, this.y / otherY, this.z / otherZ);
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
    public Float3 fma(float b, Float3 c) {
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        return new Float3(Math.fma(this.x, b, cX), Math.fma(this.y, b, cY), Math.fma(this.z, b, cZ));
    }


    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY},
     * {@code cZ}), i.e. compute {@code this * b + (cX, cY, cZ)} per component, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return the resulting vector
     */
    public Float3 fma(float b, float cX, float cY, float cZ) {
        return new Float3(Math.fma(this.x, b, cX), Math.fma(this.y, b, cY), Math.fma(this.z, b, cZ));
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
    public Float3 fma(Float3 b, Float3 c) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        return new Float3(Math.fma(this.x, bX, cX), Math.fma(this.y, bY, cY), Math.fma(this.z, bZ, cZ));
    }


    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}) and add
     * ({@code cX}, {@code cY}, {@code cZ}), i.e. compute {@code this * (bX, bY, bZ) + (cX, cY, cZ)}
     * per component, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return the resulting vector
     */
    public Float3 fma(float bX, float bY, float bZ, float cX, float cY, float cZ) {
        return new Float3(Math.fma(this.x, bX, cX), Math.fma(this.y, bY, cY), Math.fma(this.z, bZ, cZ));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Float3 mul(float scalar) {
        return new Float3(scalar * this.x, scalar * this.y, scalar * this.z);
    }


    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Float3 mul(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(otherX * this.x, otherY * this.y, otherZ * this.z);
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Float3 mul(float otherX, float otherY, float otherZ) {
        return new Float3(otherX * this.x, otherY * this.y, otherZ * this.z);
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 negate() {
        return new Float3(-this.x, -this.y, -this.z);
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Float3 sub(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(this.x - otherX, this.y - otherY, this.z - otherZ);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Float3 sub(float otherX, float otherY, float otherZ) {
        return new Float3(this.x - otherX, this.y - otherY, this.z - otherZ);
    }


    /**
     * Create the unit vector {@code (r cos(2 PI v), r sin(2 PI v), 2u - 1)} with
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
     * @return the resulting vector
     */
    public static Float3 makeUniformDirection(float u, float v) {
        float _t1 = v * 6.2831855f;
        float _t2 = Math.sin(_t1);
        float _t5 = 2.0f * (float) java.lang.Math.sqrt(u * (1.0f - u));
        return new Float3(_t5 * Math.cosFromSin(_t2, _t1), _t5 * _t2, Math.fma(2.0f, u, -1.0f));
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Float3 set(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        return new Float3(vX, vY, vZ);
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 set(float vX, float vY, float vZ) {
        return new Float3(vX, vY, vZ);
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Float3 set(float s) {
        return new Float3(s, s, s);
    }


    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double3} holding the result
     */
    public Double3 toDouble() {
        return new Double3(this.x, this.y, this.z);
    }


    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte3} holding the result
     */
    public Byte3 toByte() {
        return new Byte3((byte) (this.x), (byte) (this.y), (byte) (this.z));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte3 toByte_half_away_from_zero() {
        return new Byte3((byte) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (byte) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (byte) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)));
    }


    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Byte3} holding the result
     */
    public Byte3 toByte(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toByte();
            case FLOOR -> new Byte3((byte) Math.floor(this.x), (byte) Math.floor(this.y), (byte) Math.floor(this.z));
            case CEILING -> new Byte3((byte) Math.ceil(this.x), (byte) Math.ceil(this.y), (byte) Math.ceil(this.z));
            case HALF_TOWARD_POSITIVE_INFINITY -> new Byte3((byte) Math.round(this.x), (byte) Math.round(this.y), (byte) Math.round(this.z));
            case HALF_AWAY_FROM_ZERO -> toByte_half_away_from_zero();
            case HALF_EVEN -> new Byte3((byte) Math.rint(this.x), (byte) Math.rint(this.y), (byte) Math.rint(this.z));
        };
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short3} holding the result
     */
    public Short3 toShort() {
        return new Short3((short) (this.x), (short) (this.y), (short) (this.z));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short3 toShort_half_away_from_zero() {
        return new Short3((short) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (short) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (short) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)));
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Short3} holding the result
     */
    public Short3 toShort(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toShort();
            case FLOOR -> new Short3((short) Math.floor(this.x), (short) Math.floor(this.y), (short) Math.floor(this.z));
            case CEILING -> new Short3((short) Math.ceil(this.x), (short) Math.ceil(this.y), (short) Math.ceil(this.z));
            case HALF_TOWARD_POSITIVE_INFINITY -> new Short3((short) Math.round(this.x), (short) Math.round(this.y), (short) Math.round(this.z));
            case HALF_AWAY_FROM_ZERO -> toShort_half_away_from_zero();
            case HALF_EVEN -> new Short3((short) Math.rint(this.x), (short) Math.rint(this.y), (short) Math.rint(this.z));
        };
    }


    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int3} holding the result
     */
    public Int3 toInt() {
        return new Int3((int) (this.x), (int) (this.y), (int) (this.z));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int3 toInt_half_away_from_zero() {
        return new Int3((int) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (int) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (int) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)));
    }


    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Int3} holding the result
     */
    public Int3 toInt(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toInt();
            case FLOOR -> new Int3((int) Math.floor(this.x), (int) Math.floor(this.y), (int) Math.floor(this.z));
            case CEILING -> new Int3((int) Math.ceil(this.x), (int) Math.ceil(this.y), (int) Math.ceil(this.z));
            case HALF_TOWARD_POSITIVE_INFINITY -> new Int3(Math.round(this.x), Math.round(this.y), Math.round(this.z));
            case HALF_AWAY_FROM_ZERO -> toInt_half_away_from_zero();
            case HALF_EVEN -> new Int3((int) Math.rint(this.x), (int) Math.rint(this.y), (int) Math.rint(this.z));
        };
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long3} holding the result
     */
    public Long3 toLong() {
        return new Long3((long) (this.x), (long) (this.y), (long) (this.z));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toLong(RoundingMode)}; reached only through it. */
    private Long3 toLong_half_away_from_zero() {
        return new Long3((long) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (long) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (long) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)));
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Long3} holding the result
     */
    public Long3 toLong(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toLong();
            case FLOOR -> new Long3((long) Math.floor(this.x), (long) Math.floor(this.y), (long) Math.floor(this.z));
            case CEILING -> new Long3((long) Math.ceil(this.x), (long) Math.ceil(this.y), (long) Math.ceil(this.z));
            case HALF_TOWARD_POSITIVE_INFINITY -> new Long3(Math.round((double) (this.x)), Math.round((double) (this.y)), Math.round((double) (this.z)));
            case HALF_AWAY_FROM_ZERO -> toLong_half_away_from_zero();
            case HALF_EVEN -> new Long3((long) Math.rint(this.x), (long) Math.rint(this.y), (long) Math.rint(this.z));
        };
    }


    /**
     * Create an all-zero vector.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting vector
     */
    public static Float3 makeZero() {
        return Float3.ZERO;
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
    public Float3 bezier(Float3 p1, Float3 p2, Float3 p3, float t) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p3X = p3.x();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        return new Float3(Math.fma(p1X, _t7, this.x * _t8) + Math.fma(p2X, _t6, p3X * _t2), Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2), Math.fma(p1Z, _t7, this.z * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z})
     * and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 bezier(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        return new Float3(Math.fma(p1X, _t7, this.x * _t8) + Math.fma(p2X, _t6, p3X * _t2), Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2), Math.fma(p1Z, _t7, this.z * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
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
    public Float3 bezier2(Float3 p1, Float3 p2, float t) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        return new Float3(Math.fma(p2X, _t0, Math.fma(p1X, _t3, this.x * _t4)), Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, this.y * _t4)), Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, this.z * _t4)));
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 bezier2(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        return new Float3(Math.fma(p2X, _t0, Math.fma(p1X, _t3, this.x * _t4)), Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, this.y * _t4)), Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, this.z * _t4)));
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
    public Float3 bezier2Tangent(Float3 p1, Float3 p2, float t) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        return new Float3(Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1), Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y}, {@code p2Z}),
     * returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 bezier2Tangent(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        return new Float3(Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1), Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1));
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
    public Float3 bezierTangent(Float3 p1, Float3 p2, Float3 p3, float t) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p3X = p3.x();
        float p3Y = p3.y();
        float p3Z = p3.z();
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        return new Float3(Math.fma(p3X - p2X, _t2, Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5)), Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5)), Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5)));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z}) and ends at
     * ({@code p3X}, {@code p3Y}, {@code p3Z}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 bezierTangent(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        return new Float3(Math.fma(p3X - p2X, _t2, Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5)), Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5)), Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5)));
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
    public Float3 catmullRom(Float3 p1, Float3 p2, Float3 p3, float t) {
        return catmullRom(p1.x(), p1.y(), p1.z(), p2.x(), p2.y(), p2.z(), p3.x(), p3.y(), p3.z(), t);
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z})
     * to ({@code p2X}, {@code p2Y}, {@code p2Z}), with this vector as the control point before the
     * segment and ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it, returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Float3 catmullRom(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _t0 = t * t;
        float _t1 = t * _t0;
        return new Float3(0.5f * (Math.fma(2.0f, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, this.x, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - this.x)) * _t1)), 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, this.y, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - this.y)) * _t1)), 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - this.z)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, this.z, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - this.z)) * _t1)));
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
    public Float3 catmullRomTangent(Float3 p1, Float3 p2, Float3 p3, float t) {
        return catmullRomTangent(p1.x(), p1.y(), p1.z(), p2.x(), p2.y(), p2.z(), p3.x(), p3.y(), p3.z(), t);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}) to ({@code p2X},
     * {@code p2Y}, {@code p2Z}), with this vector as the control point before the segment and
     * ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it, returning the result
     * as a value.
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
     * @return the resulting vector
     */
    public Float3 catmullRomTangent(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _t0 = t * t;
        return new Float3(0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, this.x, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - this.x)), _t0, p2X - this.x)), 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, this.y, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - this.y)), _t0, p2Y - this.y)), 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, this.z, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - this.z)), _t0, p2Z - this.z)));
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
    public Float3 hermite(Float3 t0, Float3 v1, Float3 t1, float t) {
        float t0X = t0.x();
        float t0Y = t0.y();
        float t0Z = t0.z();
        float v1X = v1.x();
        float v1Y = v1.y();
        float v1Z = v1.z();
        float t1X = t1.x();
        float t1Y = t1.y();
        float t1Z = t1.z();
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        return new Float3(Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9), Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9), Math.fma(this.z, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 hermite(float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        return new Float3(Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9), Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9), Math.fma(this.z, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
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
    public Float3 hermiteTangent(Float3 t0, Float3 v1, Float3 t1, float t) {
        float t0X = t0.x();
        float t0Y = t0.y();
        float t0Z = t0.z();
        float v1X = v1.x();
        float v1Y = v1.y();
        float v1Z = v1.z();
        float t1X = t1.x();
        float t1Y = t1.y();
        float t1Z = t1.z();
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        return new Float3(Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7), Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7), Math.fma(this.z, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}) with the tangent
     * ({@code t1X}, {@code t1Y}, {@code t1Z}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 hermiteTangent(float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        return new Float3(Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7), Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7), Math.fma(this.z, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
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
    public Float3 lerp(Float3 other, float t) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y), Math.fma(t, otherZ - this.z, this.z));
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     * using the interpolation factor {@code t}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 lerp(float otherX, float otherY, float otherZ, float t) {
        return new Float3(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y), Math.fma(t, otherZ - this.z, this.z));
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
    public Float3 lerp(Float3 other, Float3 t) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float tX = t.x();
        float tY = t.y();
        float tZ = t.z();
        return new Float3(Math.fma(tX, otherX - this.x, this.x), Math.fma(tY, otherY - this.y, this.y), Math.fma(tZ, otherZ - this.z, this.z));
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     * using the interpolation factor ({@code tX}, {@code tY}, {@code tZ}), returning the result as
     * a value.
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
     * @return the resulting vector
     */
    public Float3 lerp(float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        return new Float3(Math.fma(tX, otherX - this.x, this.x), Math.fma(tY, otherY - this.y, this.y), Math.fma(tZ, otherZ - this.z, this.z));
    }


    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths, returning the result as
     * a value.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through a
     * perpendicular of this vector. The angle is computed with {@code atan2}, and vectors of any
     * finite length are handled: when their squared lengths leave the {@code float} range, they are
     * first scaled exactly by powers of two.
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
    public Float3 slerp(Float3 other, float t) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float _t7 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, t);
        float _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = this.x * _t9;
        float _t17 = this.z * _t9;
        float _t20 = this.y * _t9;
        float _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        Float3 _r = slerp_s680adccd_tail(otherX, _t12, _t26, _t14, otherY, _t20, Math.fma(otherZ, _t12, -(_t26 * _t17)), _t17, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate(otherX, otherY, otherZ, t);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private Float3 slerp_s680adccd_tail(float otherX, float _t12, float _t26, float _t14, float otherY, float _t20, float _t33, float _t17, float t, float _t8, float _t7) {
        float _t34 = Math.fma(otherX, _t12, -(_t26 * _t14));
        float _t35 = Math.fma(otherY, _t12, -(_t26 * _t20));
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return null;
        float _t24 = t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * Math.sin(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        float _t55 = _t24 * Math.cos(_t50);
        return new Float3(Math.fma(_t14, _t55, _sp0 * _t41), Math.fma(_t20, _t55, _sp0 * _t42), Math.fma(_t17, _t55, _sp0 * _t40));
    }


    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) using the interpolation factor {@code t}: the direction turns at a constant
     * rate along the shorter arc between the two directions, and the length changes linearly
     * between the two lengths, returning the result as a value.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through a
     * perpendicular of this vector. The angle is computed with {@code atan2}, and vectors of any
     * finite length are handled: when their squared lengths leave the {@code float} range, they are
     * first scaled exactly by powers of two.
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
     * @return the resulting vector
     */
    public Float3 slerp(float otherX, float otherY, float otherZ, float t) {
        float _t7 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, t);
        float _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = this.x * _t9;
        float _t17 = this.z * _t9;
        float _t20 = this.y * _t9;
        float _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        Float3 _r = slerp_s680adccd_tail(otherX, _t12, _t26, _t14, otherY, _t20, Math.fma(otherZ, _t12, -(_t26 * _t17)), _t17, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate(otherX, otherY, otherZ, t);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Float3 slerp_degenerate_s680adccd_tail(float _t36, float _t37, float _t35, float _t33, float _t31, float _t28, float _t9, float _t10, float _t11, float t, float _t48, float _t46, float _t15_inv, float otherX, float otherY, float otherZ) {
        float _t49, _t50, _t52;
        if (_t36 < _t37) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        float _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = Math.fma(_t73, _t31, _t61);
        float _t75 = Math.fma(_t73, _t33, _t62);
        float _t76 = Math.fma(_t73, _t35, _t63);
        return slerp_degenerate_s680adccd_tail2(_t74, unitScale(_t75, _t76, _t74), _t75, _t76, t, _t53, _t48, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t46, _t33, _t15_inv, otherX, _t35, otherY, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Float3 slerp_degenerate_s680adccd_tail2(float _t74, float _t78, float _t75, float _t76, float t, float _t53, float _t48, float _t69, float _t70, float _t71, float _t46, float _t33, float _t15_inv, float otherX, float _t35, float otherY, float _t31, float otherZ) {
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t104, _t105, _t106;
        if (_t91 > 0.0f) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        return slerp_degenerate_s680adccd_tail3(_t46, _t48 * Math.sin(_t95), _t53, Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)), _t69, _t104, _t48 * Math.cos(_t95), _t33, _t15_inv, t, otherX, _t71, _t106, _t35, otherY, _t70, _t105, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Float3 slerp_degenerate_s680adccd_tail3(float _t46, float _t99, float _t53, float _t88, float _t69, float _t104, float _t100, float _t33, float _t15_inv, float t, float otherX, float _t71, float _t106, float _t35, float otherY, float _t70, float _t105, float _t31, float otherZ) {
        float _sfx0, _sfx1, _sfx2;
        if (_t46 > 0.0f) {
            if (_t53 < 0.0f) {
                if (_t88 <= 1.4551915E-11f) {
                    _sfx0 = Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv;
                    _sfx1 = Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv;
                    _sfx2 = Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv;
                } else {
                    _sfx0 = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                    _sfx1 = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                    _sfx2 = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
                }
            } else {
                _sfx0 = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                _sfx1 = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                _sfx2 = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
            }
        } else {
            _sfx0 = Math.fma(t, otherX - this.x, this.x);
            _sfx1 = Math.fma(t, otherY - this.y, this.y);
            _sfx2 = Math.fma(t, otherZ - this.z, this.z);
        }
        return new Float3(_sfx0, _sfx1, _sfx2);
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float3 slerp_degenerate(float otherX, float otherY, float otherZ, float t) {
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(this.x, this.y, this.z);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = this.z * _t2;
        float _t13 = this.x * _t2;
        float _t14 = this.y * _t2;
        float _t15 = java.lang.Math.min(_t2, _t1);
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        return slerp_degenerate_s680adccd_tail(java.lang.Math.abs(_t31), java.lang.Math.abs(_t33), _t29 * _t14, _t33, _t31, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t9, _t10, _t11, t, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t24 * _t25, 1.0f / _t15, otherX, otherY, otherZ);
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 absolute() {
        return new Float3(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y), java.lang.Math.abs(this.z));
    }


    /**
     * Compute the arc cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Float3 acos() {
        return new Float3(Math.acos(this.x), Math.acos(this.y), Math.acos(this.z));
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
    public Float3 addScaled(Float3 b, float scalar) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        return new Float3(Math.fma(scalar, bX, this.x), Math.fma(scalar, bY, this.y), Math.fma(scalar, bZ, this.z));
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}) scaled by {@code scalar} to this vector, returning
     * the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}, {@code bZ}) by before adding
     * @return the resulting vector
     */
    public Float3 addScaled(float bX, float bY, float bZ, float scalar) {
        return new Float3(Math.fma(scalar, bX, this.x), Math.fma(scalar, bY, this.y), Math.fma(scalar, bZ, this.z));
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
    public Float3 addScaled(Float3 b, Float3 c) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        return new Float3(Math.fma(bX, cX, this.x), Math.fma(bY, cY, this.y), Math.fma(bZ, cZ, this.z));
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}) scaled by ({@code cX}, {@code cY}, {@code cZ}) to
     * this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return the resulting vector
     */
    public Float3 addScaled(float bX, float bY, float bZ, float cX, float cY, float cZ) {
        return new Float3(Math.fma(bX, cX, this.x), Math.fma(bY, cY, this.y), Math.fma(bZ, cZ, this.z));
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
    public float angleBetween(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float _t6 = Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t7 = Math.fma(otherY, this.x, -(otherX * this.y));
        float _t8 = Math.fma(otherZ, this.x, -(otherX * this.z));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY, otherZ);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
    }


    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the angle in radians between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public float angleBetween(float otherX, float otherY, float otherZ) {
        float _t6 = Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t7 = Math.fma(otherY, this.x, -(otherX * this.y));
        float _t8 = Math.fma(otherZ, this.x, -(otherX * this.z));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY, otherZ);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
    }


    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private float angleBetween_degenerate(float otherX, float otherY, float otherZ) {
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(this.x, this.y, this.z);
        float _t8 = otherZ * _t0;
        float _t9 = this.y * _t1;
        float _t10 = otherY * _t0;
        float _t11 = this.z * _t1;
        float _t12 = this.x * _t1;
        float _t13 = otherX * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        float _t23 = unitScale(_t21, _t22, _t20);
        float _t27 = _t20 * _t23;
        float _t28 = _t21 * _t23;
        float _t29 = _t22 * _t23;
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }


    /**
     * Compute the arc sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Float3 asin() {
        return new Float3(Math.asin(this.x), Math.asin(this.y), Math.asin(this.z));
    }


    /**
     * Compute the arc tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 atan() {
        return new Float3(Math.atan(this.x), Math.atan(this.y), Math.atan(this.z));
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
    public Float3 atan2(float x) {
        return new Float3(Math.atan2(this.x, x), Math.atan2(this.y, x), Math.atan2(this.z, x));
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
    public Float3 atan2(Float3 x) {
        float xX = x.x();
        float xY = x.y();
        float xZ = x.z();
        return new Float3(Math.atan2(this.x, xX), Math.atan2(this.y, xY), Math.atan2(this.z, xZ));
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}, {@code xZ}) (the denominator), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xX the {@code x} component of the vector {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the vector {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the vector {@code (xX, xY, xZ)}
     * @return the resulting vector
     */
    public Float3 atan2(float xX, float xY, float xZ) {
        return new Float3(Math.atan2(this.x, xX), Math.atan2(this.y, xY), Math.atan2(this.z, xZ));
    }


    /**
     * Compute the cube root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 cbrt() {
        return new Float3(Math.cbrt(this.x), Math.cbrt(this.y), Math.cbrt(this.z));
    }


    /**
     * Compute the ceiling of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 ceil() {
        return new Float3(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z));
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
    public Float3 clamp(float min, float max) {
        return new Float3(java.lang.Math.min(java.lang.Math.max(this.x, min), max), java.lang.Math.min(java.lang.Math.max(this.y, min), max), java.lang.Math.min(java.lang.Math.max(this.z, min), max));
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
    public Float3 clamp(Float3 min, Float3 max) {
        float minX = min.x();
        float minY = min.y();
        float minZ = min.z();
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        return new Float3(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY), java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 clamp(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new Float3(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY), java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
    }


    /**
     * Compute the point on the line segment between {@code lineStart} and {@code lineEnd} that is
     * closest to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param lineStart the start point of the line segment
     * @param lineEnd the end point of the line segment
     * @return the resulting vector
     */
    public Float3 closestPointOnLine(Float3 lineStart, Float3 lineEnd) {
        float lineStartX = lineStart.x();
        float lineStartY = lineStart.y();
        float lineStartZ = lineStart.z();
        float _t0 = lineEnd.z() - lineStartZ;
        float _t1 = lineEnd.x() - lineStartX;
        float _t2 = lineEnd.y() - lineStartY;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, Math.fma(_t0, this.z - lineStartZ, Math.fma(_t1, this.x - lineStartX, _t2 * (this.y - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            return new Float3(Math.fma(_t1, _t14, lineStartX), Math.fma(_t2, _t14, lineStartY), Math.fma(_t0, _t14, lineStartZ));
        } else {
            return new Float3(lineStartX, lineStartY, lineStartZ);
        }
    }


    /**
     * Compute the point on the line segment between ({@code lineStartX}, {@code lineStartY},
     * {@code lineStartZ}) and ({@code lineEndX}, {@code lineEndY}, {@code lineEndZ}) that is
     * closest to this vector, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 closestPointOnLine(float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, Math.fma(_t0, this.z - lineStartZ, Math.fma(_t1, this.x - lineStartX, _t2 * (this.y - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            return new Float3(Math.fma(_t1, _t14, lineStartX), Math.fma(_t2, _t14, lineStartY), Math.fma(_t0, _t14, lineStartZ));
        } else {
            return new Float3(lineStartX, lineStartY, lineStartZ);
        }
    }


    /**
     * Compute the sum of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public float compAdd() {
        return this.z + (this.x + this.y);
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public float compMax() {
        return java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public float compMin() {
        return java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public float compMul() {
        return this.z * this.x * this.y;
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
    public Float3 copySign(float sign) {
        return new Float3(Math.copySign(this.x, sign), Math.copySign(this.y, sign), Math.copySign(this.z, sign));
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
    public Float3 copySign(Float3 sign) {
        float signX = sign.x();
        float signY = sign.y();
        float signZ = sign.z();
        return new Float3(Math.copySign(this.x, signX), Math.copySign(this.y, signY), Math.copySign(this.z, signZ));
    }


    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}, {@code signZ}) onto the
     * corresponding component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param signX the {@code x} component of the vector {@code (signX, signY, signZ)}
     * @param signY the {@code y} component of the vector {@code (signX, signY, signZ)}
     * @param signZ the {@code z} component of the vector {@code (signX, signY, signZ)}
     * @return the resulting vector
     */
    public Float3 copySign(float signX, float signY, float signZ) {
        return new Float3(Math.copySign(this.x, signX), Math.copySign(this.y, signY), Math.copySign(this.z, signZ));
    }


    /**
     * Compute the cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 cos() {
        return new Float3(Math.cos(this.x), Math.cos(this.y), Math.cos(this.z));
    }


    /**
     * Compute the hyperbolic cosine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 cosh() {
        return new Float3(Math.cosh(this.x), Math.cosh(this.y), Math.cosh(this.z));
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @return the resulting vector
     */
    public Float3 cross(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(Math.fma(otherZ, this.y, -(otherY * this.z)), Math.fma(otherX, this.z, -(otherZ * this.x)), Math.fma(otherY, this.x, -(otherX * this.y)));
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Float3 cross(float otherX, float otherY, float otherZ) {
        return new Float3(Math.fma(otherZ, this.y, -(otherY * this.z)), Math.fma(otherX, this.z, -(otherZ * this.x)), Math.fma(otherY, this.x, -(otherX * this.y)));
    }


    /**
     * Compute the value converted from radians to degrees of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 degrees() {
        return new Float3(Math.toDegrees(this.x), Math.toDegrees(this.y), Math.toDegrees(this.z));
    }


    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    public float distance(Float3 other) {
        float _t0 = this.z - other.z();
        float _t1 = this.x - other.x();
        float _t2 = this.y - other.y();
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }


    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the distance between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public float distance(float otherX, float otherY, float otherZ) {
        float _t0 = this.z - otherZ;
        float _t1 = this.x - otherX;
        float _t2 = this.y - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public float distanceSquared(Float3 other) {
        float _t0 = this.z - other.z();
        float _t1 = this.x - other.x();
        float _t2 = this.y - other.y();
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
    public float distanceSquared(float otherX, float otherY, float otherZ) {
        float _t0 = this.z - otherZ;
        float _t1 = this.x - otherX;
        float _t2 = this.y - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public float dot(Float3 other) {
        return dot(other.x(), other.y(), other.z());
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
    public float dot(float otherX, float otherY, float otherZ) {
        return Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y));
    }


    /**
     * Compute the base-e exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 exp() {
        return new Float3(Math.exp(this.x), Math.exp(this.y), Math.exp(this.z));
    }


    /**
     * Compute the base-2 exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 exp2() {
        return new Float3(Math.pow(2.0f, this.x), Math.pow(2.0f, this.y), Math.pow(2.0f, this.z));
    }


    /**
     * Compute the base-e exponential minus one of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 expm1() {
        return new Float3(Math.expm1(this.x), Math.expm1(this.y), Math.expm1(this.z));
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
    public Float3 faceforward(Float3 I, Float3 Nref) {
        float _t3 = Math.fma(I.z(), Nref.z(), Math.fma(I.x(), Nref.x(), I.y() * Nref.y())) < 0.0f ? 1.0f : -1.0f;
        return new Float3(this.x * _t3, this.y * _t3, this.z * _t3);
    }


    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ), (IX, IY, IZ))} is
     * negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}) as judged by the reference vector ({@code NrefX}, {@code NrefY},
     * {@code NrefZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @return the resulting vector
     */
    public Float3 faceforward(float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        float _t3 = Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)) < 0.0f ? 1.0f : -1.0f;
        return new Float3(this.x * _t3, this.y * _t3, this.z * _t3);
    }


    /**
     * Compute the floor of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 floor() {
        return new Float3(Math.floor(this.x), Math.floor(this.y), Math.floor(this.z));
    }


    /**
     * Compute the fractional part of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 fract() {
        return new Float3(java.lang.Math.min(this.x - Math.floor(this.x), 0.99999994f), java.lang.Math.min(this.y - Math.floor(this.y), 0.99999994f), java.lang.Math.min(this.z - Math.floor(this.z), 0.99999994f));
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
    public Float3 hypot(float y) {
        return new Float3(Math.hypot(this.x, y), Math.hypot(this.y, y), Math.hypot(this.z, y));
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
    public Float3 hypot(Float3 y) {
        float yX = y.x();
        float yY = y.y();
        float yZ = y.z();
        return new Float3(Math.hypot(this.x, yX), Math.hypot(this.y, yY), Math.hypot(this.z, yZ));
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY},
     * {@code yZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ)}
     * @return the resulting vector
     */
    public Float3 hypot(float yX, float yY, float yZ) {
        return new Float3(Math.hypot(this.x, yX), Math.hypot(this.y, yY), Math.hypot(this.z, yZ));
    }


    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector, returning the result
     * as a value.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @return the resulting vector
     */
    public Float3 inverse() {
        return new Float3(1.0f / this.x, 1.0f / this.y, 1.0f / this.z);
    }


    /**
     * Compute the inverse square root of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float3 inverseSqrt() {
        return new Float3((1.0f / (float) java.lang.Math.sqrt(this.x)), (1.0f / (float) java.lang.Math.sqrt(this.y)), (1.0f / (float) java.lang.Math.sqrt(this.z)));
    }


    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this vector
     */
    public float length() {
        return (float) java.lang.Math.sqrt(Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public float lengthSquared() {
        return Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
    }


    /**
     * Compute the natural logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float3 log() {
        return new Float3(Math.log(this.x), Math.log(this.y), Math.log(this.z));
    }


    /**
     * Compute the base-10 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float3 log10() {
        return new Float3(Math.log10(this.x), Math.log10(this.y), Math.log10(this.z));
    }


    /**
     * Compute the natural logarithm of one plus the value of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @return the resulting vector
     */
    public Float3 log1p() {
        return new Float3(Math.log1p(this.x), Math.log1p(this.y), Math.log1p(this.z));
    }


    /**
     * Compute the base-2 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float3 log2() {
        return new Float3(Math.log2(this.x), Math.log2(this.y), Math.log2(this.z));
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public float manhattanDistance(Float3 other) {
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
    public float manhattanDistance(float otherX, float otherY, float otherZ) {
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public float manhattanLength() {
        return java.lang.Math.abs(this.x) + java.lang.Math.abs(this.y) + java.lang.Math.abs(this.z);
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
    public Float3 max(float scalar) {
        return new Float3(java.lang.Math.max(this.x, scalar), java.lang.Math.max(this.y, scalar), java.lang.Math.max(this.z, scalar));
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
    public Float3 max(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY), java.lang.Math.max(this.z, otherZ));
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Float3 max(float otherX, float otherY, float otherZ) {
        return new Float3(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY), java.lang.Math.max(this.z, otherZ));
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
    public Float3 min(float scalar) {
        return new Float3(java.lang.Math.min(this.x, scalar), java.lang.Math.min(this.y, scalar), java.lang.Math.min(this.z, scalar));
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
    public Float3 min(Float3 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        return new Float3(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY), java.lang.Math.min(this.z, otherZ));
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Float3 min(float otherX, float otherY, float otherZ) {
        return new Float3(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY), java.lang.Math.min(this.z, otherZ));
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
    public Float3 mod(float y) {
        return new Float3(flooredMod(this.x, y), flooredMod(this.y, y), flooredMod(this.z, y));
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
    public Float3 mod(Float3 y) {
        float yX = y.x();
        float yY = y.y();
        float yZ = y.z();
        return new Float3(flooredMod(this.x, yX), flooredMod(this.y, yY), flooredMod(this.z, yZ));
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}) ({@code x % y}, plus {@code y} when that remainder is non-zero and its sign
     * differs from {@code y}'s - exactly Kotlin's {@code mod}), returning the result as a value.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (yX, yY, yZ)} must be non-zero.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ)}
     * @return the resulting vector
     */
    public Float3 mod(float yX, float yY, float yZ) {
        return new Float3(flooredMod(this.x, yX), flooredMod(this.y, yY), flooredMod(this.z, yZ));
    }


    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 nextDown() {
        return new Float3(Math.nextDown(this.x), Math.nextDown(this.y), Math.nextDown(this.z));
    }


    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 nextUp() {
        return new Float3(Math.nextUp(this.x), Math.nextUp(this.y), Math.nextUp(this.z));
    }


    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector), returning the
     * result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 normalize() {
        float _t2 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            return new Float3(this.x * _t3, this.y * _t3, this.z * _t3);
        } else {
            return Float3.ZERO;
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
    public Float3 normalizeMul(float length) {
        float _t2 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        float _t4 = length * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            return new Float3(this.x * _t4, this.y * _t4, this.z * _t4);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Compute the signed angle in radians between this vector and {@code other}, positive when the
     * rotation from this vector to {@code other} is counter-clockwise as seen from the direction of
     * the given normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: {@code normal} must be non-zero.
     *
     * @param other the vector to measure the signed angle to
     * @param normal the reference axis that defines the sign of the angle
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise as seen from the
     *        direction of the given normal
     */
    public float orientedAngle(Float3 other, Float3 normal) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t9 = Math.fma(otherY, this.x, -(otherX * this.y));
        float _t10 = Math.fma(otherX, this.z, -(otherZ * this.x));
        float _t11 = Math.fma(otherZ, this.y, -(otherY * this.z));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return orientedAngle_degenerate(otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t18 = Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0f ? -_t18 : _t18;
    }


    /**
     * Compute the signed angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), positive when the rotation from this vector to ({@code otherX},
     * {@code otherY}, {@code otherZ}) is counter-clockwise as seen from the direction of the given
     * normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
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
    public float orientedAngle(float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t9 = Math.fma(otherY, this.x, -(otherX * this.y));
        float _t10 = Math.fma(otherX, this.z, -(otherZ * this.x));
        float _t11 = Math.fma(otherZ, this.y, -(otherY * this.z));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return orientedAngle_degenerate(otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t18 = Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0f ? -_t18 : _t18;
    }

    /** Private tail of {@code orientedAngle_degenerate}; reached only through it. */
    private float orientedAngle_degenerate_s2503f92b_tail(float _t11, float _t14, float _t13, float _t10, float _t24, float _t23, float _t9, float _t12, float normalZ, float _t0, float normalX, float normalY) {
        float _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        float _t27 = unitScale(_t24, _t25, _t23);
        float _t31 = _t23 * _t27;
        float _t32 = _t24 * _t27;
        float _t33 = _t25 * _t27;
        float _t40 = Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(normalZ * _t0, _t31, Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }


    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private float orientedAngle_degenerate(float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(this.x, this.y, this.z);
        float _t9 = otherY * _t1;
        float _t10 = this.x * _t2;
        float _t11 = otherX * _t1;
        float _t12 = this.y * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = this.z * _t2;
        return orientedAngle_degenerate_s2503f92b_tail(_t11, _t14, _t13, _t10, Math.fma(_t13, _t12, -(_t9 * _t14)), Math.fma(_t9, _t10, -(_t11 * _t12)), _t9, _t12, normalZ, unitScale(normalX, normalY, normalZ), normalX, normalY);
    }


    /**
     * Compute the outer product of this vector and {@code row}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @return the resulting matrix
     */
    public Float3x3 outerProduct(Float3 row) {
        float rowX = row.x();
        float rowY = row.y();
        float rowZ = row.z();
        return new Float3x3(rowX * this.x, rowY * this.x, rowZ * this.x, rowX * this.y, rowY * this.y, rowZ * this.y, rowX * this.z, rowY * this.z, rowZ * this.z, 0);
    }


    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}, {@code rowZ}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ)}
     * @return the resulting matrix
     */
    public Float3x3 outerProduct(float rowX, float rowY, float rowZ) {
        return new Float3x3(rowX * this.x, rowY * this.x, rowZ * this.x, rowX * this.y, rowY * this.y, rowZ * this.y, rowX * this.z, rowY * this.z, rowZ * this.z, 0);
    }


    /**
     * Compute a vector perpendicular to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 perpendicular() {
        if (java.lang.Math.abs(this.z) < java.lang.Math.abs(this.x)) {
            return new Float3(this.y, -this.x, 0.0f);
        } else {
            return new Float3(0.0f, this.z, -this.y);
        }
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
    public Float3 pow(float exponent) {
        return new Float3(Math.pow(this.x, exponent), Math.pow(this.y, exponent), Math.pow(this.z, exponent));
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
    public Float3 pow(Float3 exponent) {
        float exponentX = exponent.x();
        float exponentY = exponent.y();
        float exponentZ = exponent.z();
        return new Float3(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY), Math.pow(this.z, exponentZ));
    }


    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}), returning the result as a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponentX the {@code x} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @param exponentY the {@code y} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @param exponentZ the {@code z} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @return the resulting vector
     */
    public Float3 pow(float exponentX, float exponentY, float exponentZ) {
        return new Float3(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY), Math.pow(this.z, exponentZ));
    }


    /**
     * Project this vector onto {@code onto}, returning the result as a value.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @return the resulting vector
     */
    public Float3 project(Float3 onto) {
        float ontoX = onto.x();
        float ontoY = onto.y();
        float ontoZ = onto.z();
        float _t7 = Math.fma(ontoZ, this.z, Math.fma(ontoX, this.x, ontoY * this.y)) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        return new Float3(ontoX * _t7, ontoY * _t7, ontoZ * _t7);
    }


    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}, {@code ontoZ}), returning the result
     * as a value.
     * <p>
     * Valid input: {@code (ontoX, ontoY, ontoZ)} must be non-zero.
     *
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @param ontoZ the {@code z} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @return the resulting vector
     */
    public Float3 project(float ontoX, float ontoY, float ontoZ) {
        float _t7 = Math.fma(ontoZ, this.z, Math.fma(ontoX, this.x, ontoY * this.y)) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        return new Float3(ontoX * _t7, ontoY * _t7, ontoZ * _t7);
    }


    /**
     * Project this vector onto the plane with the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @return the resulting vector
     */
    public Float3 projectOnPlane(Float3 normal) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float _t2 = Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        return new Float3(Math.fma(-normalX, _t2, this.x), Math.fma(-normalY, _t2, this.y), Math.fma(-normalZ, _t2, this.z));
    }


    /**
     * Project this vector onto the plane with the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @return the resulting vector
     */
    public Float3 projectOnPlane(float normalX, float normalY, float normalZ) {
        float _t2 = Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        return new Float3(Math.fma(-normalX, _t2, this.x), Math.fma(-normalY, _t2, this.y), Math.fma(-normalZ, _t2, this.z));
    }


    /**
     * Compute the value converted from degrees to radians of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 radians() {
        return new Float3(Math.toRadians(this.x), Math.toRadians(this.y), Math.toRadians(this.z));
    }


    /**
     * Reflect this vector about the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @return the resulting vector
     */
    public Float3 reflect(Float3 normal) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float _t3 = 2.0f * Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        return new Float3(Math.fma(-normalX, _t3, this.x), Math.fma(-normalY, _t3, this.y), Math.fma(-normalZ, _t3, this.z));
    }


    /**
     * Reflect this vector about the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @return the resulting vector
     */
    public Float3 reflect(float normalX, float normalY, float normalZ) {
        float _t3 = 2.0f * Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        return new Float3(Math.fma(-normalX, _t3, this.x), Math.fma(-normalY, _t3, this.y), Math.fma(-normalZ, _t3, this.z));
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
    public Float3 refract(Float3 normal, float eta) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float _t3 = Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            return new Float3(Math.fma(eta, this.x, -(normalX * _t10)), Math.fma(eta, this.y, -(normalY * _t10)), Math.fma(eta, this.z, -(normalZ * _t10)));
        } else {
            return Float3.ZERO;
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
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length; this vector must have
     * unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @return the resulting vector
     */
    public Float3 refract(float normalX, float normalY, float normalZ, float eta) {
        float _t3 = Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            return new Float3(Math.fma(eta, this.x, -(normalX * _t10)), Math.fma(eta, this.y, -(normalY * _t10)), Math.fma(eta, this.z, -(normalZ * _t10)));
        } else {
            return Float3.ZERO;
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
    public Float3 round() {
        return new Float3(Math.rint(this.x), Math.rint(this.y), Math.rint(this.z));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 sign() {
        return new Float3(Math.signum(this.x), Math.signum(this.y), Math.signum(this.z));
    }


    /**
     * Compute the sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 sin() {
        return new Float3(Math.sin(this.x), Math.sin(this.y), Math.sin(this.z));
    }


    /**
     * Compute the hyperbolic sine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 sinh() {
        return new Float3(Math.sinh(this.x), Math.sinh(this.y), Math.sinh(this.z));
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
    public Float3 smoothstep(float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t10 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.x - edge0) * _t0_inv));
        float _t11 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.y - edge0) * _t0_inv));
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.z - edge0) * _t0_inv));
        return new Float3(Math.fma(-2.0f, _t10, 3.0f) * _t10 * _t10, Math.fma(-2.0f, _t11, 3.0f) * _t11 * _t11, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
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
    public Float3 smoothstep(Float3 edge0, Float3 edge1) {
        float edge0X = edge0.x();
        float edge0Y = edge0.y();
        float edge0Z = edge0.z();
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.x - edge0X) / (edge1.x() - edge0X)));
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.y - edge0Y) / (edge1.y() - edge0Y)));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.z - edge0Z) / (edge1.z() - edge0Z)));
        return new Float3(Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}) and the upper edge
     * ({@code edge1X}, {@code edge1Y}, {@code edge1Z}), yielding 0 at or below the lower edge and 1
     * at or above the upper edge, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 smoothstep(float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        return new Float3(Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
    }


    /**
     * Compute the square root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @return the resulting vector
     */
    public Float3 sqrt() {
        return new Float3((float) java.lang.Math.sqrt(this.x), (float) java.lang.Math.sqrt(this.y), (float) java.lang.Math.sqrt(this.z));
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
    public Float3 step(float edge) {
        return new Float3(this.x < edge ? 0.0f : 1.0f, this.y < edge ? 0.0f : 1.0f, this.z < edge ? 0.0f : 1.0f);
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
    public Float3 step(Float3 edge) {
        float edgeX = edge.x();
        float edgeY = edge.y();
        float edgeZ = edge.z();
        return new Float3(this.x < edgeX ? 0.0f : 1.0f, this.y < edgeY ? 0.0f : 1.0f, this.z < edgeZ ? 0.0f : 1.0f);
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @return the resulting vector
     */
    public Float3 step(float edgeX, float edgeY, float edgeZ) {
        return new Float3(this.x < edgeX ? 0.0f : 1.0f, this.y < edgeY ? 0.0f : 1.0f, this.z < edgeZ ? 0.0f : 1.0f);
    }


    /**
     * Compute the tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 tan() {
        return new Float3(Math.tan(this.x), Math.tan(this.y), Math.tan(this.z));
    }


    /**
     * Compute the hyperbolic tangent of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 tanh() {
        return new Float3(Math.tanh(this.x), Math.tanh(this.y), Math.tanh(this.z));
    }


    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize((p1 - this) x (p2 - this))} - it points to the side from which the vertices
     * {@code this}, {@code p1}, {@code p2} appear counter-clockwise (a degenerate triangle yields
     * the zero vector), returning the result as a value.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code float} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the second vertex of the triangle (this vector is the first)
     * @param p2 the third vertex of the triangle
     * @return the resulting vector
     */
    public Float3 triangleNormal(Float3 p1, Float3 p2) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float _t0 = p1Y - this.y;
        float _t1 = p2Z - this.z;
        float _t2 = p1Z - this.z;
        float _t3 = p2Y - this.y;
        float _t4 = p1X - this.x;
        float _t5 = p2X - this.x;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return triangleNormal_degenerate(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return new Float3(_t12 * _t19, _t14 * _t19, _t13 * _t19);
    }


    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize(((p1X, p1Y, p1Z) - this) x ((p2X, p2Y, p2Z) - this))} - it points to the
     * side from which the vertices {@code this}, ({@code p1X}, {@code p1Y}, {@code p1Z}),
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) appear counter-clockwise (a degenerate triangle
     * yields the zero vector), returning the result as a value.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code float} range, the edges are first scaled exactly by
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
     * @return the resulting vector
     */
    public Float3 triangleNormal(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _t0 = p1Y - this.y;
        float _t1 = p2Z - this.z;
        float _t2 = p1Z - this.z;
        float _t3 = p2Y - this.y;
        float _t4 = p1X - this.x;
        float _t5 = p2X - this.x;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return triangleNormal_degenerate(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        return new Float3(_t12 * _t19, _t14 * _t19, _t13 * _t19);
    }

    /** Private tail of {@code triangleNormal_degenerate}; reached only through it. */
    private Float3 triangleNormal_degenerate_s7577080b_tail(float _t38, float _t44, float _t41, float _t45, float _t39, float _t42, float _t43, float _t40) {
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t64 = Math.fma(_t52, _t53, -(_t54 * _t55));
        float _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        float _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            return new Float3(_t77 * _t72, _t77 * _t73, _t77 * _t71);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Out-of-range path of {@code triangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Float3 triangleNormal_degenerate(float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _t19 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(this.z), java.lang.Math.abs(p1Z)), java.lang.Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(p1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(this.y))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        float _t30 = this.x * _t19;
        float _t32 = this.y * _t19;
        float _t34 = this.z * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        return triangleNormal_degenerate_s7577080b_tail(_t38, unitScale(_t38, _t39, _t40), _t41, unitScale(_t42, _t41, _t43), _t39, _t42, _t43, _t40);
    }


    /**
     * Compute the truncated value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 trunc() {
        return new Float3(this.x >= 0.0f ? Math.floor(this.x) : Math.ceil(this.x), this.y >= 0.0f ? Math.floor(this.y) : Math.ceil(this.y), this.z >= 0.0f ? Math.floor(this.z) : Math.ceil(this.z));
    }


    /**
     * Compute the unit in the last place (ulp) of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 ulp() {
        return new Float3(Math.ulp(this.x), Math.ulp(this.y), Math.ulp(this.z));
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 xyz0() {
        return new Float4(this.x, this.y, this.z, 0.0f);
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 xyz1() {
        return new Float4(this.x, this.y, this.z, 1.0f);
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
    public Float3 preMul(Float3x3 mat) {
        return new Float3(Math.fma(mat.m02(), this.z, Math.fma(mat.m00(), this.x, mat.m01() * this.y)), Math.fma(mat.m12(), this.z, Math.fma(mat.m10(), this.x, mat.m11() * this.y)), Math.fma(mat.m22(), this.z, Math.fma(mat.m20(), this.x, mat.m21() * this.y)));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Float3 preMulDirection(Float3x4 mat) {
        return new Float3(Math.fma(mat.m02(), this.z, Math.fma(mat.m00(), this.x, mat.m01() * this.y)), Math.fma(mat.m12(), this.z, Math.fma(mat.m10(), this.x, mat.m11() * this.y)), Math.fma(mat.m22(), this.z, Math.fma(mat.m20(), this.x, mat.m21() * this.y)));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Float3 preMulDirection(Float4x4 mat) {
        return new Float3(Math.fma(mat.m02(), this.z, Math.fma(mat.m00(), this.x, mat.m01() * this.y)), Math.fma(mat.m12(), this.z, Math.fma(mat.m10(), this.x, mat.m11() * this.y)), Math.fma(mat.m22(), this.z, Math.fma(mat.m20(), this.x, mat.m21() * this.y)));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Float3 preMulPosition(Float3x4 mat) {
        return new Float3(Math.fma(mat.m00(), this.x, Math.fma(mat.m01(), this.y, Math.fma(mat.m02(), this.z, mat.m03()))), Math.fma(mat.m10(), this.x, Math.fma(mat.m11(), this.y, Math.fma(mat.m12(), this.z, mat.m13()))), Math.fma(mat.m20(), this.x, Math.fma(mat.m21(), this.y, Math.fma(mat.m22(), this.z, mat.m23()))));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Float3 preMulPosition(Float4x4 mat) {
        return new Float3(Math.fma(mat.m00(), this.x, Math.fma(mat.m01(), this.y, Math.fma(mat.m02(), this.z, mat.m03()))), Math.fma(mat.m10(), this.x, Math.fma(mat.m11(), this.y, Math.fma(mat.m12(), this.z, mat.m13()))), Math.fma(mat.m20(), this.x, Math.fma(mat.m21(), this.y, Math.fma(mat.m22(), this.z, mat.m23()))));
    }


    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1},
     * then perform a perspective divide - i.e. compute {@code r = mat * (this, 1)} and return
     * {@code r.xyz / r.w}, returning the result as a value.
     * <p>
     * Valid input: this vector must not be mapped to {@code w = 0} by {@code mat}.
     *
     * @param mat the matrix to apply
     * @return the resulting vector
     */
    public Float3 preMulProject(Float4x4 mat) {
        float _t2_inv = 1.0f / Math.fma(mat.m30(), this.x, Math.fma(mat.m31(), this.y, Math.fma(mat.m32(), this.z, mat.m33())));
        return new Float3(Math.fma(mat.m00(), this.x, Math.fma(mat.m01(), this.y, Math.fma(mat.m02(), this.z, mat.m03()))) * _t2_inv, Math.fma(mat.m10(), this.x, Math.fma(mat.m11(), this.y, Math.fma(mat.m12(), this.z, mat.m13()))) * _t2_inv, Math.fma(mat.m20(), this.x, Math.fma(mat.m21(), this.y, Math.fma(mat.m22(), this.z, mat.m23()))) * _t2_inv);
    }


    /**
     * Rotate this vector by the quaternion {@code quat}, i.e. compute {@code q * this * q^-1},
     * returning the result as a value.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @return the resulting vector
     */
    public Float3 rotate(FloatQuat quat) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float _t9 = 2.0f * Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * Math.fma(quatY, this.z, -(quatZ * this.y));
        return new Float3(Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.y))), Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, this.z))));
    }


    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}), i.e. compute {@code q * this * q^-1}, returning the result as a value.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @return the resulting vector
     */
    public Float3 rotate(float quatX, float quatY, float quatZ, float quatW) {
        float _t9 = 2.0f * Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * Math.fma(quatY, this.z, -(quatZ * this.y));
        return new Float3(Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.y))), Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, this.z))));
    }


    /**
     * Rotate this vector by the quaternion {@code quat} about the point {@code pivot}, i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p}, returning the result as a value.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param pivot the pivot point
     * @return the resulting vector
     */
    public Float3 rotateAround(FloatQuat quat, Float3 pivot) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float _t0 = this.y - pivot.y();
        float _t1 = this.x - pivot.x();
        float _t2 = this.z - pivot.z();
        float _t12 = 2.0f * Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * Math.fma(quatY, _t2, -(quatZ * _t0));
        return new Float3(Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, this.x))), Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, this.y))), Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, this.z))));
    }


    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}) about the point ({@code pivotX}, {@code pivotY}, {@code pivotZ}), i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float3 rotateAround(float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = this.y - pivotY;
        float _t1 = this.x - pivotX;
        float _t2 = this.z - pivotZ;
        float _t12 = 2.0f * Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * Math.fma(quatY, _t2, -(quatZ * _t0));
        return new Float3(Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, this.x))), Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, this.y))), Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, this.z))));
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis {@code axis}, returning the result
     * as a value.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting vector
     */
    public Float3 rotateAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        return new Float3(Math.fma(_t3, axisX * _t5, Math.fma(this.x, _t1, Math.fma(axisY, this.z, -(axisZ * this.y)) * _t0)), Math.fma(_t3, axisY * _t5, Math.fma(this.y, _t1, Math.fma(axisZ, this.x, -(axisX * this.z)) * _t0)), Math.fma(_t3, axisZ * _t5, Math.fma(this.z, _t1, Math.fma(axisX, this.y, -(axisY * this.x)) * _t0)));
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}), returning the result as a value.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting vector
     */
    public Float3 rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        return new Float3(Math.fma(_t3, axisX * _t5, Math.fma(this.x, _t1, Math.fma(axisY, this.z, -(axisZ * this.y)) * _t0)), Math.fma(_t3, axisY * _t5, Math.fma(this.y, _t1, Math.fma(axisZ, this.x, -(axisX * this.z)) * _t0)), Math.fma(_t3, axisZ * _t5, Math.fma(this.z, _t1, Math.fma(axisX, this.y, -(axisY * this.x)) * _t0)));
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis {@code axis} through the point
     * {@code pivot}, returning the result as a value.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param pivot the pivot point
     * @return the resulting vector
     */
    public Float3 rotateAxisAround(float angle, Float3 axis, Float3 pivot) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        float pivotZ = pivot.z();
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.x - pivotX;
        float _t3 = this.z - pivotZ;
        float _t4 = this.y - pivotY;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        return new Float3(Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX))), Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY))), Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ))));
    }


    /**
     * Rotate this vector by {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) through the point ({@code pivotX}, {@code pivotY}, {@code pivotZ}), returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Float3 rotateAxisAround(float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.x - pivotX;
        float _t3 = this.z - pivotZ;
        float _t4 = this.y - pivotY;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        return new Float3(Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX))), Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY))), Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ))));
    }


    /**
     * Rotate this vector by the inverse of the given rotation, returning the result as a value.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @return the resulting vector
     */
    public Float3 rotateInverse(FloatQuat quat) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float _t9 = 2.0f * Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * Math.fma(quatZ, this.y, -(quatY * this.z));
        return new Float3(Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, this.y))), Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.z))));
    }


    /**
     * Rotate this vector by the inverse of the given rotation, returning the result as a value.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @return the resulting vector
     */
    public Float3 rotateInverse(float quatX, float quatY, float quatZ, float quatW) {
        float _t9 = 2.0f * Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * Math.fma(quatZ, this.y, -(quatY * this.z));
        return new Float3(Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, this.y))), Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.z))));
    }


    /**
     * Rotate this vector by {@code angle} radians about the X axis, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting vector
     */
    public Float3 rotateX(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3(this.x, Math.fma(this.y, _t1, -(this.z * _t0)), Math.fma(this.y, _t0, this.z * _t1));
    }


    /**
     * Rotate this vector by {@code angle} radians about the X axis through the point {@code pivot},
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return the resulting vector
     */
    public Float3 rotateXAround(float angle, Float3 pivot) {
        float pivotY = pivot.y();
        float pivotZ = pivot.z();
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.y - pivotY;
        float _t3 = this.z - pivotZ;
        return new Float3(this.x, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY)), Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ)));
    }


    /**
     * Rotate this vector by {@code angle} radians about the X axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return the resulting vector
     */
    public Float3 rotateXAround(float angle, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.y - pivotY;
        float _t3 = this.z - pivotZ;
        return new Float3(this.x, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY)), Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ)));
    }


    /**
     * Rotate this vector by {@code angle} radians about the Y axis, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting vector
     */
    public Float3 rotateY(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3(Math.fma(this.x, _t1, this.z * _t0), this.y, Math.fma(this.z, _t1, -(this.x * _t0)));
    }


    /**
     * Rotate this vector by {@code angle} radians about the Y axis through the point {@code pivot},
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return the resulting vector
     */
    public Float3 rotateYAround(float angle, Float3 pivot) {
        float pivotX = pivot.x();
        float pivotZ = pivot.z();
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.x - pivotX;
        float _t3 = this.z - pivotZ;
        return new Float3(Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX)), this.y, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ)));
    }


    /**
     * Rotate this vector by {@code angle} radians about the Y axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return the resulting vector
     */
    public Float3 rotateYAround(float angle, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.x - pivotX;
        float _t3 = this.z - pivotZ;
        return new Float3(Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX)), this.y, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ)));
    }


    /**
     * Rotate this vector by {@code angle} radians about the Z axis, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting vector
     */
    public Float3 rotateZ(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3(Math.fma(this.x, _t1, -(this.y * _t0)), Math.fma(this.x, _t0, this.y * _t1), this.z);
    }


    /**
     * Rotate this vector by {@code angle} radians about the Z axis through the point {@code pivot},
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return the resulting vector
     */
    public Float3 rotateZAround(float angle, Float3 pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.x - pivotX;
        float _t3 = this.y - pivotY;
        return new Float3(Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)), Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)), this.z);
    }


    /**
     * Rotate this vector by {@code angle} radians about the Z axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return the resulting vector
     */
    public Float3 rotateZAround(float angle, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = this.x - pivotX;
        float _t3 = this.y - pivotY;
        return new Float3(Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)), Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)), this.z);
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     */
    public Float3 withX(float x) {
        return new Float3(x, this.y(), this.z());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     */
    public Float3 withY(float y) {
        return new Float3(this.x(), y, this.z());
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Float3 withXY(float x, float y) {
        return new Float3(x, y, this.z());
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param z the new value of the {@code z} component
     */
    public Float3 withZ(float z) {
        return new Float3(this.x(), this.y(), z);
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Float3 withXZ(float x, float z) {
        return new Float3(x, this.y(), z);
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Float3 withYZ(float y, float z) {
        return new Float3(this.x(), y, z);
    }

    /**
     * {@return a copy of this vector with the XYZ components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Float3 withXYZ(float x, float y, float z) {
        return new Float3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 xx() {
        return new Float2(x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 xy() {
        return new Float2(x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 xz() {
        return new Float2(x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 yx() {
        return new Float2(y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 yy() {
        return new Float2(y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 yz() {
        return new Float2(y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 zx() {
        return new Float2(z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 zy() {
        return new Float2(z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 zz() {
        return new Float2(z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xxx() {
        return new Float3(x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xxy() {
        return new Float3(x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xxz() {
        return new Float3(x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xyx() {
        return new Float3(x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xyy() {
        return new Float3(x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xyz() {
        return new Float3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xzx() {
        return new Float3(x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xzy() {
        return new Float3(x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xzz() {
        return new Float3(x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yxx() {
        return new Float3(y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yxy() {
        return new Float3(y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yxz() {
        return new Float3(y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yyx() {
        return new Float3(y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yyy() {
        return new Float3(y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yyz() {
        return new Float3(y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yzx() {
        return new Float3(y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yzy() {
        return new Float3(y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yzz() {
        return new Float3(y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zxx() {
        return new Float3(z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zxy() {
        return new Float3(z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zxz() {
        return new Float3(z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zyx() {
        return new Float3(z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zyy() {
        return new Float3(z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zyz() {
        return new Float3(z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zzx() {
        return new Float3(z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zzy() {
        return new Float3(z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zzz() {
        return new Float3(z, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxxx() {
        return new Float4(x, x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxxy() {
        return new Float4(x, x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxxz() {
        return new Float4(x, x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxyx() {
        return new Float4(x, x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxyy() {
        return new Float4(x, x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxyz() {
        return new Float4(x, x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxzx() {
        return new Float4(x, x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxzy() {
        return new Float4(x, x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxzz() {
        return new Float4(x, x, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyxx() {
        return new Float4(x, y, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyxy() {
        return new Float4(x, y, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyxz() {
        return new Float4(x, y, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyyx() {
        return new Float4(x, y, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyyy() {
        return new Float4(x, y, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyyz() {
        return new Float4(x, y, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyzx() {
        return new Float4(x, y, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyzy() {
        return new Float4(x, y, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyzz() {
        return new Float4(x, y, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzxx() {
        return new Float4(x, z, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzxy() {
        return new Float4(x, z, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzxz() {
        return new Float4(x, z, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzyx() {
        return new Float4(x, z, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzyy() {
        return new Float4(x, z, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzyz() {
        return new Float4(x, z, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzzx() {
        return new Float4(x, z, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzzy() {
        return new Float4(x, z, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzzz() {
        return new Float4(x, z, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxxx() {
        return new Float4(y, x, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxxy() {
        return new Float4(y, x, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxxz() {
        return new Float4(y, x, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxyx() {
        return new Float4(y, x, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxyy() {
        return new Float4(y, x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxyz() {
        return new Float4(y, x, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxzx() {
        return new Float4(y, x, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxzy() {
        return new Float4(y, x, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxzz() {
        return new Float4(y, x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyxx() {
        return new Float4(y, y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyxy() {
        return new Float4(y, y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyxz() {
        return new Float4(y, y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyyx() {
        return new Float4(y, y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyyy() {
        return new Float4(y, y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyyz() {
        return new Float4(y, y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyzx() {
        return new Float4(y, y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyzy() {
        return new Float4(y, y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyzz() {
        return new Float4(y, y, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzxx() {
        return new Float4(y, z, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzxy() {
        return new Float4(y, z, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzxz() {
        return new Float4(y, z, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzyx() {
        return new Float4(y, z, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzyy() {
        return new Float4(y, z, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzyz() {
        return new Float4(y, z, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzzx() {
        return new Float4(y, z, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzzy() {
        return new Float4(y, z, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzzz() {
        return new Float4(y, z, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxxx() {
        return new Float4(z, x, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxxy() {
        return new Float4(z, x, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxxz() {
        return new Float4(z, x, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxyx() {
        return new Float4(z, x, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxyy() {
        return new Float4(z, x, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxyz() {
        return new Float4(z, x, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxzx() {
        return new Float4(z, x, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxzy() {
        return new Float4(z, x, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxzz() {
        return new Float4(z, x, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyxx() {
        return new Float4(z, y, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyxy() {
        return new Float4(z, y, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyxz() {
        return new Float4(z, y, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyyx() {
        return new Float4(z, y, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyyy() {
        return new Float4(z, y, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyyz() {
        return new Float4(z, y, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyzx() {
        return new Float4(z, y, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyzy() {
        return new Float4(z, y, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyzz() {
        return new Float4(z, y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzxx() {
        return new Float4(z, z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzxy() {
        return new Float4(z, z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzxz() {
        return new Float4(z, z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzyx() {
        return new Float4(z, z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzyy() {
        return new Float4(z, z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzyz() {
        return new Float4(z, z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzzx() {
        return new Float4(z, z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzzy() {
        return new Float4(z, z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzzz() {
        return new Float4(z, z, z, z);
    }

    @Override public String toString() {
        return "Float3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float3)) return false;
        Float3 o = (Float3) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(o.x)
            && Float.floatToIntBits(y) == Float.floatToIntBits(o.y)
            && Float.floatToIntBits(z) == Float.floatToIntBits(o.z);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(x);
        h = 31 * h + Float.floatToIntBits(y);
        h = 31 * h + Float.floatToIntBits(z);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(x)
            && Float.isFinite(y)
            && Float.isFinite(z);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(x)
            || Float.isNaN(y)
            || Float.isNaN(z);
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
    public boolean equalsEpsilon(Float3 other, float epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3SegOpsUnsafe()
                        : new Float3SegOpsMS();
        static final Float3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3BbOpsUnsafe()
                        : new Float3BbOpsApi();
        static final Float3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3RawOpsUnsafe()
                        : new Float3RawOpsApi();
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
    public float[] store(float[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
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
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        return new Float3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
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
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
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
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Float3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Float3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 12);
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
     */
    public Float3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

    /**
     * Load the elements from the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }


    /**
     * Store the elements into the given array, converting each element to {@code double}, starting
     * at the given offset.
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
        dest[offset + 2] = this.z;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        return new Float3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
     * at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
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
     * Store the elements into the given buffer, converting each element to {@code double}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
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
     * Store the elements into the given buffer, converting each element to {@code double}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Float3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3 loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Float3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3 loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3 r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 24);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3 storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Float3} holding the loaded elements
     */
    public static Float3 loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(offset, src);
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
