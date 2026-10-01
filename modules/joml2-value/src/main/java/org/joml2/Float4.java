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
 * Immutable 4D vector of single-precision {@code float} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
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
 * @param w the {@code w} component
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record Float4(float x, float y, float z, float w) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

    /** The zero vector (all components 0). */
    public static final Float4 ZERO = new Float4(0, 0, 0, 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public Float4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Create a new instance initialized to the homogeneous default {@code (0, 0, 0, 1)}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public Float4() {
        this(0, 0, 0, 1);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public Float4(float s) {
        this(s, s, s, s);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public Float4(float v0, float v1, Float2 v2) {
        this(v0, v1, v2.x(), v2.y());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public Float4(float v0, Float2 v1, float v2) {
        this(v0, v1.x(), v1.y(), v2);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public Float4(float v0, Float3 v1) {
        this(v0, v1.x(), v1.y(), v1.z());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public Float4(Float2 v0, float v1, float v2) {
        this(v0.x(), v0.y(), v1, v2);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public Float4(Float2 v0, Float2 v1) {
        this(v0.x(), v0.y(), v1.x(), v1.y());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public Float4(Float3 v0, float v1) {
        this(v0.x(), v0.y(), v0.z(), v1);
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public float x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public float y() { return y; }
    /** {@return the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    public float z() { return z; }
    /** {@return the {@code w} component} <p>Valid input: any value, NaN and the infinities included. */
    public float w() { return w; }

    /**
     * Create a direction uniformly distributed on the unit sphere of four dimensions, drawing the 3
     * samples of {@code makeUniformDirection} from {@code rng}, each with {@code rng.nextFloat()},
     * in parameter order.
     * <p>
     * Valid input: any value.
     *
     * @param rng the random number generator to draw the 3 samples from
     * @return the resulting vector
     */
    public static Float4 makeRandomDirection(java.util.Random rng) {
        return makeUniformDirection(rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
    }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Float4 add(Float4 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        return new Float4(otherX + this.x, otherY + this.y, otherZ + this.z, otherW + this.w);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Float4 add(float otherX, float otherY, float otherZ, float otherW) {
        return new Float4(otherX + this.x, otherY + this.y, otherZ + this.z, otherW + this.w);
    }


    /**
     * Divide each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Float4 div(float scalar) {
        return new Float4(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
    }


    /**
     * Divide this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Float4 div(Float4 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        return new Float4(this.x / otherX, this.y / otherY, this.z / otherZ, this.w / otherW);
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Float4 div(float otherX, float otherY, float otherZ, float otherW) {
        return new Float4(this.x / otherX, this.y / otherY, this.z / otherZ, this.w / otherW);
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
    public Float4 fma(float b, Float4 c) {
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        return new Float4(Math.fma(this.x, b, cX), Math.fma(this.y, b, cY), Math.fma(this.z, b, cZ), Math.fma(this.w, b, cW));
    }


    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}, {@code cZ},
     * {@code cW}), i.e. compute {@code this * b + (cX, cY, cZ, cW)} per component, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @return the resulting vector
     */
    public Float4 fma(float b, float cX, float cY, float cZ, float cW) {
        return new Float4(Math.fma(this.x, b, cX), Math.fma(this.y, b, cY), Math.fma(this.z, b, cZ), Math.fma(this.w, b, cW));
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
    public Float4 fma(Float4 b, Float4 c) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        return new Float4(Math.fma(this.x, bX, cX), Math.fma(this.y, bY, cY), Math.fma(this.z, bZ, cZ), Math.fma(this.w, bW, cW));
    }


    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component, returning the result as a
     * value.
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
     * @return the resulting vector
     */
    public Float4 fma(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        return new Float4(Math.fma(this.x, bX, cX), Math.fma(this.y, bY, cY), Math.fma(this.z, bZ, cZ), Math.fma(this.w, bW, cW));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Float4 mul(float scalar) {
        return new Float4(scalar * this.x, scalar * this.y, scalar * this.z, scalar * this.w);
    }


    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Float4 mul(Float4 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        return new Float4(otherX * this.x, otherY * this.y, otherZ * this.z, otherW * this.w);
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Float4 mul(float otherX, float otherY, float otherZ, float otherW) {
        return new Float4(otherX * this.x, otherY * this.y, otherZ * this.z, otherW * this.w);
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 negate() {
        return new Float4(-this.x, -this.y, -this.z, -this.w);
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Float4 sub(Float4 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        return new Float4(this.x - otherX, this.y - otherY, this.z - otherZ, this.w - otherW);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Float4 sub(float otherX, float otherY, float otherZ, float otherW) {
        return new Float4(this.x - otherX, this.y - otherY, this.z - otherZ, this.w - otherW);
    }


    /**
     * Create the unit vector
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
     * @return the resulting vector
     */
    public static Float4 makeUniformDirection(float u, float v, float w) {
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u);
        float _t6 = Math.sin(_t3);
        return new Float4(Math.cosFromSin(_t4, _t1) * _t5, _t4 * _t5, Math.cosFromSin(_t6, _t3) * _t0, _t6 * _t0);
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Float4 set(Float4 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float vW = v.w();
        return new Float4(vX, vY, vZ, vW);
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the vector {@code (vX, vY, vZ, vW)}
     * @return the resulting vector
     */
    public Float4 set(float vX, float vY, float vZ, float vW) {
        return new Float4(vX, vY, vZ, vW);
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Float4 set(float s) {
        return new Float4(s, s, s, s);
    }


    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double4} holding the result
     */
    public Double4 toDouble() {
        return new Double4(this.x, this.y, this.z, this.w);
    }


    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte4} holding the result
     */
    public Byte4 toByte() {
        return new Byte4((byte) (this.x), (byte) (this.y), (byte) (this.z), (byte) (this.w));
    }

    /** Private {@code RoundingMode.FLOOR} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte4 toByte_floor() {
        return new Byte4((byte) Math.floor(this.x), (byte) Math.floor(this.y), (byte) Math.floor(this.z), (byte) Math.floor(this.w));
    }

    /** Private {@code RoundingMode.CEILING} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte4 toByte_ceiling() {
        return new Byte4((byte) Math.ceil(this.x), (byte) Math.ceil(this.y), (byte) Math.ceil(this.z), (byte) Math.ceil(this.w));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte4 toByte_half_toward_positive_infinity() {
        return new Byte4((byte) Math.round(this.x), (byte) Math.round(this.y), (byte) Math.round(this.z), (byte) Math.round(this.w));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte4 toByte_half_away_from_zero() {
        return new Byte4((byte) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (byte) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (byte) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)), (byte) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5)));
    }

    /** Private {@code RoundingMode.HALF_EVEN} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte4 toByte_half_even() {
        return new Byte4((byte) Math.rint(this.x), (byte) Math.rint(this.y), (byte) Math.rint(this.z), (byte) Math.rint(this.w));
    }


    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Byte4} holding the result
     */
    public Byte4 toByte(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toByte();
            case FLOOR -> toByte_floor();
            case CEILING -> toByte_ceiling();
            case HALF_TOWARD_POSITIVE_INFINITY -> toByte_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toByte_half_away_from_zero();
            case HALF_EVEN -> toByte_half_even();
        };
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short4} holding the result
     */
    public Short4 toShort() {
        return new Short4((short) (this.x), (short) (this.y), (short) (this.z), (short) (this.w));
    }

    /** Private {@code RoundingMode.FLOOR} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short4 toShort_floor() {
        return new Short4((short) Math.floor(this.x), (short) Math.floor(this.y), (short) Math.floor(this.z), (short) Math.floor(this.w));
    }

    /** Private {@code RoundingMode.CEILING} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short4 toShort_ceiling() {
        return new Short4((short) Math.ceil(this.x), (short) Math.ceil(this.y), (short) Math.ceil(this.z), (short) Math.ceil(this.w));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short4 toShort_half_toward_positive_infinity() {
        return new Short4((short) Math.round(this.x), (short) Math.round(this.y), (short) Math.round(this.z), (short) Math.round(this.w));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short4 toShort_half_away_from_zero() {
        return new Short4((short) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (short) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (short) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)), (short) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5)));
    }

    /** Private {@code RoundingMode.HALF_EVEN} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short4 toShort_half_even() {
        return new Short4((short) Math.rint(this.x), (short) Math.rint(this.y), (short) Math.rint(this.z), (short) Math.rint(this.w));
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Short4} holding the result
     */
    public Short4 toShort(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toShort();
            case FLOOR -> toShort_floor();
            case CEILING -> toShort_ceiling();
            case HALF_TOWARD_POSITIVE_INFINITY -> toShort_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toShort_half_away_from_zero();
            case HALF_EVEN -> toShort_half_even();
        };
    }


    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int4} holding the result
     */
    public Int4 toInt() {
        return new Int4((int) (this.x), (int) (this.y), (int) (this.z), (int) (this.w));
    }

    /** Private {@code RoundingMode.FLOOR} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int4 toInt_floor() {
        return new Int4((int) Math.floor(this.x), (int) Math.floor(this.y), (int) Math.floor(this.z), (int) Math.floor(this.w));
    }

    /** Private {@code RoundingMode.CEILING} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int4 toInt_ceiling() {
        return new Int4((int) Math.ceil(this.x), (int) Math.ceil(this.y), (int) Math.ceil(this.z), (int) Math.ceil(this.w));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int4 toInt_half_toward_positive_infinity() {
        return new Int4(Math.round(this.x), Math.round(this.y), Math.round(this.z), Math.round(this.w));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int4 toInt_half_away_from_zero() {
        return new Int4((int) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (int) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (int) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)), (int) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5)));
    }

    /** Private {@code RoundingMode.HALF_EVEN} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int4 toInt_half_even() {
        return new Int4((int) Math.rint(this.x), (int) Math.rint(this.y), (int) Math.rint(this.z), (int) Math.rint(this.w));
    }


    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Int4} holding the result
     */
    public Int4 toInt(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toInt();
            case FLOOR -> toInt_floor();
            case CEILING -> toInt_ceiling();
            case HALF_TOWARD_POSITIVE_INFINITY -> toInt_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toInt_half_away_from_zero();
            case HALF_EVEN -> toInt_half_even();
        };
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long4} holding the result
     */
    public Long4 toLong() {
        return new Long4((long) (this.x), (long) (this.y), (long) (this.z), (long) (this.w));
    }

    /** Private {@code RoundingMode.FLOOR} body of {@code toLong(RoundingMode)}; reached only through it. */
    private Long4 toLong_floor() {
        return new Long4((long) Math.floor(this.x), (long) Math.floor(this.y), (long) Math.floor(this.z), (long) Math.floor(this.w));
    }

    /** Private {@code RoundingMode.CEILING} body of {@code toLong(RoundingMode)}; reached only through it. */
    private Long4 toLong_ceiling() {
        return new Long4((long) Math.ceil(this.x), (long) Math.ceil(this.y), (long) Math.ceil(this.z), (long) Math.ceil(this.w));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toLong(RoundingMode)}; reached only through it. */
    private Long4 toLong_half_toward_positive_infinity() {
        return new Long4(Math.round((double) (this.x)), Math.round((double) (this.y)), Math.round((double) (this.z)), Math.round((double) (this.w)));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toLong(RoundingMode)}; reached only through it. */
    private Long4 toLong_half_away_from_zero() {
        return new Long4((long) (this.x >= 0 ? Math.floor(this.x + 0.5) : Math.ceil(this.x - 0.5)), (long) (this.y >= 0 ? Math.floor(this.y + 0.5) : Math.ceil(this.y - 0.5)), (long) (this.z >= 0 ? Math.floor(this.z + 0.5) : Math.ceil(this.z - 0.5)), (long) (this.w >= 0 ? Math.floor(this.w + 0.5) : Math.ceil(this.w - 0.5)));
    }

    /** Private {@code RoundingMode.HALF_EVEN} body of {@code toLong(RoundingMode)}; reached only through it. */
    private Long4 toLong_half_even() {
        return new Long4((long) Math.rint(this.x), (long) Math.rint(this.y), (long) Math.rint(this.z), (long) Math.rint(this.w));
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @return a new {@code Long4} holding the result
     */
    public Long4 toLong(RoundingMode roundingMode) {
        return switch (roundingMode) {
            case TRUNCATE -> toLong();
            case FLOOR -> toLong_floor();
            case CEILING -> toLong_ceiling();
            case HALF_TOWARD_POSITIVE_INFINITY -> toLong_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toLong_half_away_from_zero();
            case HALF_EVEN -> toLong_half_even();
        };
    }


    /**
     * Create an all-zero vector.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting vector
     */
    public static Float4 makeZero() {
        return Float4.ZERO;
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
    public Float4 bezier(Float4 p1, Float4 p2, Float4 p3, float t) {
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
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        return new Float4(Math.fma(p1X, _t7, this.x * _t8) + Math.fma(p2X, _t6, p3X * _t2), Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2), Math.fma(p1Z, _t7, this.z * _t8) + Math.fma(p2Z, _t6, p3Z * _t2), Math.fma(p1W, _t7, this.w * _t8) + Math.fma(p2W, _t6, p3W * _t2));
    }


    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}),
     * returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 bezier(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        return new Float4(Math.fma(p1X, _t7, this.x * _t8) + Math.fma(p2X, _t6, p3X * _t2), Math.fma(p1Y, _t7, this.y * _t8) + Math.fma(p2Y, _t6, p3Y * _t2), Math.fma(p1Z, _t7, this.z * _t8) + Math.fma(p2Z, _t6, p3Z * _t2), Math.fma(p1W, _t7, this.w * _t8) + Math.fma(p2W, _t6, p3W * _t2));
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
    public Float4 bezier2(Float4 p1, Float4 p2, float t) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        return new Float4(Math.fma(p2X, _t0, Math.fma(p1X, _t3, this.x * _t4)), Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, this.y * _t4)), Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, this.z * _t4)), Math.fma(p2W, _t0, Math.fma(p1W, _t3, this.w * _t4)));
    }


    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 bezier2(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        return new Float4(Math.fma(p2X, _t0, Math.fma(p1X, _t3, this.x * _t4)), Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, this.y * _t4)), Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, this.z * _t4)), Math.fma(p2W, _t0, Math.fma(p1W, _t3, this.w * _t4)));
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
    public Float4 bezier2Tangent(Float4 p1, Float4 p2, float t) {
        float p1X = p1.x();
        float p1Y = p1.y();
        float p1Z = p1.z();
        float p1W = p1.w();
        float p2X = p2.x();
        float p2Y = p2.y();
        float p2Z = p2.z();
        float p2W = p2.w();
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        return new Float4(Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1), Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1), Math.fma(p1W - this.w, _t2, (p2W - p1W) * _t1));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 bezier2Tangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        return new Float4(Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1), Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1), Math.fma(p1W - this.w, _t2, (p2W - p1W) * _t1));
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
    public Float4 bezierTangent(Float4 p1, Float4 p2, Float4 p3, float t) {
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
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        return new Float4(Math.fma(p3X - p2X, _t2, Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5)), Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5)), Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5)), Math.fma(p3W - p2W, _t2, Math.fma(p1W - this.w, _t6, (p2W - p1W) * _t5)));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z},
     * {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}), returning the
     * result as a value.
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
     * @return the resulting vector
     */
    public Float4 bezierTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        return new Float4(Math.fma(p3X - p2X, _t2, Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5)), Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5)), Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5)), Math.fma(p3W - p2W, _t2, Math.fma(p1W - this.w, _t6, (p2W - p1W) * _t5)));
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
    public Float4 catmullRom(Float4 p1, Float4 p2, Float4 p3, float t) {
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
        float _t0 = t * t;
        float _t1 = t * _t0;
        float _sfx0 = 0.5f * (Math.fma(2.0f, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, this.x, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - this.x)) * _t1));
        float _sfx1 = 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, this.y, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - this.y)) * _t1));
        return catmullRom_s8bdfa76_tail(p1Z, t, p2Z, p3Z, _t0, _t1, p1W, p2W, p3W, _sfx0, _sfx1);
    }

    /** Private tail of {@code catmullRom}; reached only through it. */
    private Float4 catmullRom_s8bdfa76_tail(float p1Z, float t, float p2Z, float p3Z, float _t0, float _t1, float p1W, float p2W, float p3W, float _sfx0, float _sfx1) {
        float _sfx2 = 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - this.z)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, this.z, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - this.z)) * _t1));
        float _sfx3 = 0.5f * (Math.fma(2.0f, p1W, t * (p2W - this.w)) + Math.fma(Math.fma(-5.0f, p1W, Math.fma(2.0f, this.w, Math.fma(4.0f, p2W, -p3W))), _t0, Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - this.w)) * _t1));
        return new Float4(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) to ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the
     * control point before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as
     * the control point after it, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 catmullRom(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = t * t;
        float _t1 = t * _t0;
        float _sfx0 = 0.5f * (Math.fma(2.0f, p1X, t * (p2X - this.x)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, this.x, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - this.x)) * _t1));
        float _sfx1 = 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - this.y)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, this.y, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - this.y)) * _t1));
        return catmullRom_s8bdfa76_tail(p1Z, t, p2Z, p3Z, _t0, _t1, p1W, p2W, p3W, _sfx0, _sfx1);
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
    public Float4 catmullRomTangent(Float4 p1, Float4 p2, Float4 p3, float t) {
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
        float _t0 = t * t;
        float _sfx0 = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, this.x, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - this.x)), _t0, p2X - this.x));
        float _sfx1 = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, this.y, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - this.y)), _t0, p2Y - this.y));
        return catmullRomTangent_s8bdfa76_tail(t, p1Z, p2Z, p3Z, _t0, p1W, p2W, p3W, _sfx0, _sfx1);
    }

    /** Private tail of {@code catmullRomTangent}; reached only through it. */
    private Float4 catmullRomTangent_s8bdfa76_tail(float t, float p1Z, float p2Z, float p3Z, float _t0, float p1W, float p2W, float p3W, float _sfx0, float _sfx1) {
        float _sfx2 = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, this.z, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - this.z)), _t0, p2Z - this.z));
        float _sfx3 = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1W, Math.fma(2.0f, this.w, Math.fma(4.0f, p2W, -p3W))), Math.fma(3.0f * Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - this.w)), _t0, p2W - this.w));
        return new Float4(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) to
     * ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the control point
     * before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as the control
     * point after it, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 catmullRomTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = t * t;
        float _sfx0 = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, this.x, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - this.x)), _t0, p2X - this.x));
        float _sfx1 = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, this.y, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - this.y)), _t0, p2Y - this.y));
        return catmullRomTangent_s8bdfa76_tail(t, p1Z, p2Z, p3Z, _t0, p1W, p2W, p3W, _sfx0, _sfx1);
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
    public Float4 hermite(Float4 t0, Float4 v1, Float4 t1, float t) {
        float t0X = t0.x();
        float t0Y = t0.y();
        float t0Z = t0.z();
        float t0W = t0.w();
        float v1X = v1.x();
        float v1Y = v1.y();
        float v1Z = v1.z();
        float v1W = v1.w();
        float t1X = t1.x();
        float t1Y = t1.y();
        float t1Z = t1.z();
        float t1W = t1.w();
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        return new Float4(Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9), Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9), Math.fma(this.z, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9), Math.fma(this.w, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
    }


    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z},
     * {@code t1W}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 hermite(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        return new Float4(Math.fma(this.x, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9), Math.fma(this.y, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9), Math.fma(this.z, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9), Math.fma(this.w, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
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
    public Float4 hermiteTangent(Float4 t0, Float4 v1, Float4 t1, float t) {
        float t0X = t0.x();
        float t0Y = t0.y();
        float t0Z = t0.z();
        float t0W = t0.w();
        float v1X = v1.x();
        float v1Y = v1.y();
        float v1Z = v1.z();
        float v1W = v1.w();
        float t1X = t1.x();
        float t1Y = t1.y();
        float t1Z = t1.z();
        float t1W = t1.w();
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        return new Float4(Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7), Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7), Math.fma(this.z, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7), Math.fma(this.w, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
    }


    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}, {@code v1W})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}, {@code t1W}), returning the result
     * as a value.
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
     * @return the resulting vector
     */
    public Float4 hermiteTangent(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        return new Float4(Math.fma(this.x, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7), Math.fma(this.y, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7), Math.fma(this.z, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7), Math.fma(this.w, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
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
    public Float4 lerp(Float4 other, float t) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        return new Float4(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y), Math.fma(t, otherZ - this.z, this.z), Math.fma(t, otherW - this.w, this.w));
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor {@code t}, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 lerp(float otherX, float otherY, float otherZ, float otherW, float t) {
        return new Float4(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y), Math.fma(t, otherZ - this.z, this.z), Math.fma(t, otherW - this.w, this.w));
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
    public Float4 lerp(Float4 other, Float4 t) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float tX = t.x();
        float tY = t.y();
        float tZ = t.z();
        float tW = t.w();
        return new Float4(Math.fma(tX, otherX - this.x, this.x), Math.fma(tY, otherY - this.y, this.y), Math.fma(tZ, otherZ - this.z, this.z), Math.fma(tW, otherW - this.w, this.w));
    }


    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor ({@code tX}, {@code tY}, {@code tZ},
     * {@code tW}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 lerp(float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        return new Float4(Math.fma(tX, otherX - this.x, this.x), Math.fma(tY, otherY - this.y, this.y), Math.fma(tZ, otherZ - this.z, this.z), Math.fma(tW, otherW - this.w, this.w));
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
     * @return the resulting vector
     */
    public Float4 slerp(Float4 other, float t) {
        return slerp(other.x(), other.y(), other.z(), other.w(), t);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private Float4 slerp_s351bd8ee_tail(float otherX, float _t14, float _t31, float _t16, float otherY, float _t24, float _t40, float _t19, float _t41, float _t21, float t, float _t10, float _t9) {
        float _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return null;
        float _t28 = t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        float _t66 = _t28 * Math.cos(_t61);
        return new Float4(Math.fma(_t16, _t66, _sp0 * _t51), Math.fma(_t24, _t66, _sp0 * _t52), Math.fma(_t21, _t66, _sp0 * _t50), Math.fma(_t19, _t66, _sp0 * _t49));
    }


    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 slerp(float otherX, float otherY, float otherZ, float otherW, float t) {
        float _t9 = Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t);
        float _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = this.x * _t11;
        float _t19 = this.w * _t11;
        float _t21 = this.z * _t11;
        float _t24 = this.y * _t11;
        float _t31 = Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        Float4 _r = slerp_s351bd8ee_tail(otherX, _t14, _t31, _t16, otherY, _t24, Math.fma(otherW, _t14, -(_t31 * _t19)), _t19, Math.fma(otherZ, _t14, -(_t31 * _t21)), _t21, t, _t10, _t9);
        return _r != null ? _r : slerp_degenerate(otherX, otherY, otherZ, otherW, t);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Float4 slerp_degenerate_s351bd8ee_tail(float _t43, float _t36, float _t37, float t, float _t25, float _t7, float _t8, float _t40, float _t17, float _t18, float _t45, float _t19, float _t47, float _t20, float _t49, float _t50, float _t25_inv, float otherX, float otherY, float otherZ, float otherW) {
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        return slerp_degenerate_s351bd8ee_tail2(Math.fma(_t80, _t45, _t73), Math.fma(_t80, _t43, _t72), Math.fma(_t80, _t47, _t74), Math.fma(_t80, _t49, _t75), t, _t63, t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8), _t47, _t45, _t50, -_t43, _t36 * _t37, _t25_inv, otherX, _t49, otherY, otherZ, _t43, otherW);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Float4 slerp_degenerate_s351bd8ee_tail2(float _t82, float _t81, float _t83, float _t84, float t, float _t63, float _t60, float _t47, float _t45, float _t50, float _t51, float _t58, float _t25_inv, float otherX, float _t49, float otherY, float otherZ, float _t43, float otherW) {
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
        }
        return slerp_degenerate_s351bd8ee_tail3(_t106, _t108, _t98, _t51, _t58, _t60 * Math.sin(_t110), _t63, Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))), _t50, _t122, _t60 * Math.cos(_t110), _t47, _t25_inv, t, otherX, _t120, _t49, otherY, _t45, otherZ, _t121, _t43, otherW);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Float4 slerp_degenerate_s351bd8ee_tail3(float _t106, float _t108, float _t98, float _t51, float _t58, float _t114, float _t63, float _t102, float _t50, float _t122, float _t115, float _t47, float _t25_inv, float t, float otherX, float _t120, float _t49, float otherY, float _t45, float otherZ, float _t121, float _t43, float otherW) {
        float _sfx0, _sfx1;
        if (_t58 > 0.0f) {
            if (_t63 < 0.0f) {
                if (_t102 <= 1.4551915E-11f) {
                    _sfx0 = Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv;
                    _sfx1 = Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv;
                } else {
                    _sfx0 = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                    _sfx1 = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                }
            } else {
                _sfx0 = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                _sfx1 = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
            }
        } else {
            _sfx0 = Math.fma(t, otherX - this.x, this.x);
            _sfx1 = Math.fma(t, otherY - this.y, this.y);
        }
        return slerp_degenerate_s351bd8ee_tail4(_t58, _t114, _t63, _t102, _t51, _t106 > 0.0f ? _t108 * _t98 : _t51, _t115, _t45, _t25_inv, t, otherZ, _t121, _t43, otherW, _sfx0, _sfx1);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Float4 slerp_degenerate_s351bd8ee_tail4(float _t58, float _t114, float _t63, float _t102, float _t51, float _t123, float _t115, float _t45, float _t25_inv, float t, float otherZ, float _t121, float _t43, float otherW, float _sfx0, float _sfx1) {
        float _sfx2, _sfx3;
        if (_t58 > 0.0f) {
            if (_t63 < 0.0f) {
                if (_t102 <= 1.4551915E-11f) {
                    _sfx2 = Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv;
                    _sfx3 = Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv;
                } else {
                    _sfx2 = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                    _sfx3 = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
                }
            } else {
                _sfx2 = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                _sfx3 = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
            }
        } else {
            _sfx2 = Math.fma(t, otherZ - this.z, this.z);
            _sfx3 = Math.fma(t, otherW - this.w, this.w);
        }
        return new Float4(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Float4 slerp_degenerate(float otherX, float otherY, float otherZ, float otherW, float t) {
        float _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(this.z, this.w, java.lang.Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = this.w * _t8;
        float _t22 = this.z * _t8;
        float _t23 = this.x * _t8;
        float _t24 = this.y * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t49 = _t41 * _t24;
        return slerp_degenerate_s351bd8ee_tail(_t41 * _t21, _t36, _t37, t, _t25, _t7, _t8, (1.0f / (float) java.lang.Math.sqrt(_t36)), _t17, _t18, _t41 * _t22, _t19, _t41 * _t23, _t20, _t49, -_t49, 1.0f / _t25, otherX, otherY, otherZ, otherW);
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 absolute() {
        return new Float4(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y), java.lang.Math.abs(this.z), java.lang.Math.abs(this.w));
    }


    /**
     * Compute the arc cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Float4 acos() {
        return new Float4(Math.acos(this.x), Math.acos(this.y), Math.acos(this.z), Math.acos(this.w));
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
    public Float4 addScaled(Float4 b, float scalar) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        return new Float4(Math.fma(scalar, bX, this.x), Math.fma(scalar, bY, this.y), Math.fma(scalar, bZ, this.z), Math.fma(scalar, bW, this.w));
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by {@code scalar} to this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) by before
     *        adding
     * @return the resulting vector
     */
    public Float4 addScaled(float bX, float bY, float bZ, float bW, float scalar) {
        return new Float4(Math.fma(scalar, bX, this.x), Math.fma(scalar, bY, this.y), Math.fma(scalar, bZ, this.z), Math.fma(scalar, bW, this.w));
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
    public Float4 addScaled(Float4 b, Float4 c) {
        float bX = b.x();
        float bY = b.y();
        float bZ = b.z();
        float bW = b.w();
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        float cW = c.w();
        return new Float4(Math.fma(bX, cX, this.x), Math.fma(bY, cY, this.y), Math.fma(bZ, cZ, this.z), Math.fma(bW, cW, this.w));
    }


    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by ({@code cX}, {@code cY},
     * {@code cZ}, {@code cW}) to this vector, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 addScaled(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        return new Float4(Math.fma(bX, cX, this.x), Math.fma(bY, cY, this.y), Math.fma(bZ, cZ, this.z), Math.fma(bW, cW, this.w));
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
    public float angleBetween(Float4 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        float _t12 = Math.fma(otherW, this.z, -(otherZ * this.w));
        float _t13 = Math.fma(otherW, this.y, -(otherY * this.w));
        float _t14 = Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t15 = Math.fma(otherW, this.x, -(otherX * this.w));
        float _t16 = Math.fma(otherY, this.x, -(otherX * this.y));
        float _t17 = Math.fma(otherZ, this.x, -(otherX * this.z));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y))));
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
        float _t12 = Math.fma(otherW, this.z, -(otherZ * this.w));
        float _t13 = Math.fma(otherW, this.y, -(otherY * this.w));
        float _t14 = Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t15 = Math.fma(otherW, this.x, -(otherX * this.w));
        float _t16 = Math.fma(otherY, this.x, -(otherX * this.y));
        float _t17 = Math.fma(otherZ, this.x, -(otherX * this.z));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y))));
    }

    /** Private tail of {@code angleBetween_degenerate}; reached only through it. */
    private float angleBetween_degenerate_s543b568e_tail(float _t16, float _t21, float _t22, float _t19, float _t18, float _t23, float _t20, float _t17, float _t37, float _t38, float _t36) {
        float _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        float _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }


    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private float angleBetween_degenerate(float otherX, float otherY, float otherZ, float otherW) {
        float _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(this.z, this.w, java.lang.Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y)));
        float _t16 = otherW * _t6;
        float _t17 = this.z * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = this.w * _t7;
        float _t20 = otherY * _t6;
        float _t21 = this.x * _t7;
        float _t22 = otherX * _t6;
        float _t23 = this.y * _t7;
        return angleBetween_degenerate_s543b568e_tail(_t16, _t21, _t22, _t19, _t18, _t23, _t20, _t17, Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t17, -(_t18 * _t19)));
    }


    /**
     * Compute the arc sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Float4 asin() {
        return new Float4(Math.asin(this.x), Math.asin(this.y), Math.asin(this.z), Math.asin(this.w));
    }


    /**
     * Compute the arc tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 atan() {
        return new Float4(Math.atan(this.x), Math.atan(this.y), Math.atan(this.z), Math.atan(this.w));
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
    public Float4 atan2(float x) {
        return new Float4(Math.atan2(this.x, x), Math.atan2(this.y, x), Math.atan2(this.z, x), Math.atan2(this.w, x));
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
    public Float4 atan2(Float4 x) {
        float xX = x.x();
        float xY = x.y();
        float xZ = x.z();
        float xW = x.w();
        return new Float4(Math.atan2(this.x, xX), Math.atan2(this.y, xY), Math.atan2(this.z, xZ), Math.atan2(this.w, xW));
    }


    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}, {@code xZ}, {@code xW}) (the denominator), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xX the {@code x} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xY the {@code y} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xZ the {@code z} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xW the {@code w} component of the vector {@code (xX, xY, xZ, xW)}
     * @return the resulting vector
     */
    public Float4 atan2(float xX, float xY, float xZ, float xW) {
        return new Float4(Math.atan2(this.x, xX), Math.atan2(this.y, xY), Math.atan2(this.z, xZ), Math.atan2(this.w, xW));
    }


    /**
     * Compute the cube root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 cbrt() {
        return new Float4(Math.cbrt(this.x), Math.cbrt(this.y), Math.cbrt(this.z), Math.cbrt(this.w));
    }


    /**
     * Compute the ceiling of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 ceil() {
        return new Float4(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z), Math.ceil(this.w));
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
    public Float4 clamp(float min, float max) {
        return new Float4(java.lang.Math.min(java.lang.Math.max(this.x, min), max), java.lang.Math.min(java.lang.Math.max(this.y, min), max), java.lang.Math.min(java.lang.Math.max(this.z, min), max), java.lang.Math.min(java.lang.Math.max(this.w, min), max));
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
    public Float4 clamp(Float4 min, Float4 max) {
        float minX = min.x();
        float minY = min.y();
        float minZ = min.z();
        float minW = min.w();
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        float maxW = max.w();
        return new Float4(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY), java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ), java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW));
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}), returning the
     * result as a value.
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
     * @return the resulting vector
     */
    public Float4 clamp(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        return new Float4(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY), java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ), java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW));
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
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z), this.w);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public float compMin() {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z), this.w);
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
     * Copy the sign of {@code sign} onto each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @return the resulting vector
     */
    public Float4 copySign(float sign) {
        return new Float4(Math.copySign(this.x, sign), Math.copySign(this.y, sign), Math.copySign(this.z, sign), Math.copySign(this.w, sign));
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
    public Float4 copySign(Float4 sign) {
        float signX = sign.x();
        float signY = sign.y();
        float signZ = sign.z();
        float signW = sign.w();
        return new Float4(Math.copySign(this.x, signX), Math.copySign(this.y, signY), Math.copySign(this.z, signZ), Math.copySign(this.w, signW));
    }


    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}, {@code signZ},
     * {@code signW}) onto the corresponding component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param signX the {@code x} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signY the {@code y} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signZ the {@code z} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signW the {@code w} component of the vector {@code (signX, signY, signZ, signW)}
     * @return the resulting vector
     */
    public Float4 copySign(float signX, float signY, float signZ, float signW) {
        return new Float4(Math.copySign(this.x, signX), Math.copySign(this.y, signY), Math.copySign(this.z, signZ), Math.copySign(this.w, signW));
    }


    /**
     * Compute the cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 cos() {
        return new Float4(Math.cos(this.x), Math.cos(this.y), Math.cos(this.z), Math.cos(this.w));
    }


    /**
     * Compute the hyperbolic cosine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 cosh() {
        return new Float4(Math.cosh(this.x), Math.cosh(this.y), Math.cosh(this.z), Math.cosh(this.w));
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @return the resulting vector
     */
    public Float4 cross(Float4 v, Float4 w) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float vW = v.w();
        float wX = w.x();
        float wY = w.y();
        float wZ = w.z();
        float wW = w.w();
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        return new Float4(Math.fma(this.w, _t12, Math.fma(this.y, _t13, -(this.z * _t14))), Math.fma(-this.w, _t15, Math.fma(this.z, _t16, -(this.x * _t13))), Math.fma(this.w, _t17, Math.fma(this.x, _t14, -(this.y * _t16))), Math.fma(-this.z, _t17, Math.fma(this.y, _t15, -(this.x * _t12))));
    }


    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 cross(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        return new Float4(Math.fma(this.w, _t12, Math.fma(this.y, _t13, -(this.z * _t14))), Math.fma(-this.w, _t15, Math.fma(this.z, _t16, -(this.x * _t13))), Math.fma(this.w, _t17, Math.fma(this.x, _t14, -(this.y * _t16))), Math.fma(-this.z, _t17, Math.fma(this.y, _t15, -(this.x * _t12))));
    }


    /**
     * Compute the value converted from radians to degrees of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 degrees() {
        return new Float4(Math.toDegrees(this.x), Math.toDegrees(this.y), Math.toDegrees(this.z), Math.toDegrees(this.w));
    }


    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    public float distance(Float4 other) {
        float _t0 = this.w - other.w();
        float _t1 = this.z - other.z();
        float _t2 = this.x - other.x();
        float _t3 = this.y - other.y();
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
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
        float _t0 = this.w - otherW;
        float _t1 = this.z - otherZ;
        float _t2 = this.x - otherX;
        float _t3 = this.y - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public float distanceSquared(Float4 other) {
        float _t0 = this.w - other.w();
        float _t1 = this.z - other.z();
        float _t2 = this.x - other.x();
        float _t3 = this.y - other.y();
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
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
        float _t0 = this.w - otherW;
        float _t1 = this.z - otherZ;
        float _t2 = this.x - otherX;
        float _t3 = this.y - otherY;
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
    public float dot(Float4 other) {
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
    public float dot(float otherX, float otherY, float otherZ, float otherW) {
        return Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
    }


    /**
     * Compute the base-e exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 exp() {
        return new Float4(Math.exp(this.x), Math.exp(this.y), Math.exp(this.z), Math.exp(this.w));
    }


    /**
     * Compute the base-2 exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 exp2() {
        return new Float4(Math.pow(2.0f, this.x), Math.pow(2.0f, this.y), Math.pow(2.0f, this.z), Math.pow(2.0f, this.w));
    }


    /**
     * Compute the base-e exponential minus one of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 expm1() {
        return new Float4(Math.expm1(this.x), Math.expm1(this.y), Math.expm1(this.z), Math.expm1(this.w));
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
    public Float4 faceforward(Float4 I, Float4 Nref) {
        float _t4 = Math.fma(I.w(), Nref.w(), Math.fma(I.z(), Nref.z(), Math.fma(I.x(), Nref.x(), I.y() * Nref.y()))) < 0.0f ? 1.0f : -1.0f;
        return new Float4(this.x * _t4, this.y * _t4, this.z * _t4, this.w * _t4);
    }


    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))}
     * is negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}, {@code IW}) as judged by the reference vector ({@code NrefX},
     * {@code NrefY}, {@code NrefZ}, {@code NrefW}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 faceforward(float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        float _t4 = Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
        return new Float4(this.x * _t4, this.y * _t4, this.z * _t4, this.w * _t4);
    }


    /**
     * Compute the floor of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 floor() {
        return new Float4(Math.floor(this.x), Math.floor(this.y), Math.floor(this.z), Math.floor(this.w));
    }


    /**
     * Compute the fractional part of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 fract() {
        return new Float4(java.lang.Math.min(this.x - Math.floor(this.x), 0.99999994f), java.lang.Math.min(this.y - Math.floor(this.y), 0.99999994f), java.lang.Math.min(this.z - Math.floor(this.z), 0.99999994f), java.lang.Math.min(this.w - Math.floor(this.w), 0.99999994f));
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
    public Float4 hypot(float y) {
        return new Float4(Math.hypot(this.x, y), Math.hypot(this.y, y), Math.hypot(this.z, y), Math.hypot(this.w, y));
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
    public Float4 hypot(Float4 y) {
        float yX = y.x();
        float yY = y.y();
        float yZ = y.z();
        float yW = y.w();
        return new Float4(Math.hypot(this.x, yX), Math.hypot(this.y, yY), Math.hypot(this.z, yZ), Math.hypot(this.w, yW));
    }


    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yW the {@code w} component of the vector {@code (yX, yY, yZ, yW)}
     * @return the resulting vector
     */
    public Float4 hypot(float yX, float yY, float yZ, float yW) {
        return new Float4(Math.hypot(this.x, yX), Math.hypot(this.y, yY), Math.hypot(this.z, yZ), Math.hypot(this.w, yW));
    }


    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector, returning the result
     * as a value.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @return the resulting vector
     */
    public Float4 inverse() {
        return new Float4(1.0f / this.x, 1.0f / this.y, 1.0f / this.z, 1.0f / this.w);
    }


    /**
     * Compute the inverse square root of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float4 inverseSqrt() {
        return new Float4((1.0f / (float) java.lang.Math.sqrt(this.x)), (1.0f / (float) java.lang.Math.sqrt(this.y)), (1.0f / (float) java.lang.Math.sqrt(this.z)), (1.0f / (float) java.lang.Math.sqrt(this.w)));
    }


    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this vector
     */
    public float length() {
        return (float) java.lang.Math.sqrt(Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y))));
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public float lengthSquared() {
        return Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
    }


    /**
     * Compute the natural logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float4 log() {
        return new Float4(Math.log(this.x), Math.log(this.y), Math.log(this.z), Math.log(this.w));
    }


    /**
     * Compute the base-10 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float4 log10() {
        return new Float4(Math.log10(this.x), Math.log10(this.y), Math.log10(this.z), Math.log10(this.w));
    }


    /**
     * Compute the natural logarithm of one plus the value of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @return the resulting vector
     */
    public Float4 log1p() {
        return new Float4(Math.log1p(this.x), Math.log1p(this.y), Math.log1p(this.z), Math.log1p(this.w));
    }


    /**
     * Compute the base-2 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Float4 log2() {
        return new Float4(Math.log2(this.x), Math.log2(this.y), Math.log2(this.z), Math.log2(this.w));
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public float manhattanDistance(Float4 other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y()) + java.lang.Math.abs(this.z - other.z()) + java.lang.Math.abs(this.w - other.w());
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
     * Set each component of this vector to the larger of itself and {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return the resulting vector
     */
    public Float4 max(float scalar) {
        return new Float4(java.lang.Math.max(this.x, scalar), java.lang.Math.max(this.y, scalar), java.lang.Math.max(this.z, scalar), java.lang.Math.max(this.w, scalar));
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
    public Float4 max(Float4 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        return new Float4(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY), java.lang.Math.max(this.z, otherZ), java.lang.Math.max(this.w, otherW));
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}), returning the result as a
     * value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Float4 max(float otherX, float otherY, float otherZ, float otherW) {
        return new Float4(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY), java.lang.Math.max(this.z, otherZ), java.lang.Math.max(this.w, otherW));
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
    public Float4 min(float scalar) {
        return new Float4(java.lang.Math.min(this.x, scalar), java.lang.Math.min(this.y, scalar), java.lang.Math.min(this.z, scalar), java.lang.Math.min(this.w, scalar));
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
    public Float4 min(Float4 other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        return new Float4(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY), java.lang.Math.min(this.z, otherZ), java.lang.Math.min(this.w, otherW));
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}), returning the result as a
     * value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Float4 min(float otherX, float otherY, float otherZ, float otherW) {
        return new Float4(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY), java.lang.Math.min(this.z, otherZ), java.lang.Math.min(this.w, otherW));
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
    public Float4 mod(float y) {
        return new Float4(flooredMod(this.x, y), flooredMod(this.y, y), flooredMod(this.z, y), flooredMod(this.w, y));
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
    public Float4 mod(Float4 y) {
        float yX = y.x();
        float yY = y.y();
        float yZ = y.z();
        float yW = y.w();
        return new Float4(flooredMod(this.x, yX), flooredMod(this.y, yY), flooredMod(this.z, yZ), flooredMod(this.w, yW));
    }


    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) ({@code x % y}, plus {@code y} when that remainder is non-zero and
     * its sign differs from {@code y}'s - exactly Kotlin's {@code mod}), returning the result as a
     * value.
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
     * @return the resulting vector
     */
    public Float4 mod(float yX, float yY, float yZ, float yW) {
        return new Float4(flooredMod(this.x, yX), flooredMod(this.y, yY), flooredMod(this.z, yZ), flooredMod(this.w, yW));
    }


    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 nextDown() {
        return new Float4(Math.nextDown(this.x), Math.nextDown(this.y), Math.nextDown(this.z), Math.nextDown(this.w));
    }


    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 nextUp() {
        return new Float4(Math.nextUp(this.x), Math.nextUp(this.y), Math.nextUp(this.z), Math.nextUp(this.w));
    }


    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector), returning the
     * result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float4 normalize() {
        float _t3 = Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            return new Float4(this.x * _t4, this.y * _t4, this.z * _t4, this.w * _t4);
        } else {
            return Float4.ZERO;
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
    public Float4 normalizeMul(float length) {
        float _t3 = Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            return new Float4(this.x * _t5, this.y * _t5, this.z * _t5, this.w * _t5);
        } else {
            return Float4.ZERO;
        }
    }


    /**
     * Compute the outer product of this vector and {@code row}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @return the resulting matrix
     */
    public Float4x4 outerProduct(Float4 row) {
        float rowX = row.x();
        float rowY = row.y();
        float rowZ = row.z();
        float rowW = row.w();
        return new Float4x4(rowX * this.x, rowY * this.x, rowZ * this.x, rowW * this.x, rowX * this.y, rowY * this.y, rowZ * this.y, rowW * this.y, rowX * this.z, rowY * this.z, rowZ * this.z, rowW * this.z, rowX * this.w, rowY * this.w, rowZ * this.w, rowW * this.w, 0);
    }


    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}, {@code rowZ},
     * {@code rowW}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowW the {@code w} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @return the resulting matrix
     */
    public Float4x4 outerProduct(float rowX, float rowY, float rowZ, float rowW) {
        return new Float4x4(rowX * this.x, rowY * this.x, rowZ * this.x, rowW * this.x, rowX * this.y, rowY * this.y, rowZ * this.y, rowW * this.y, rowX * this.z, rowY * this.z, rowZ * this.z, rowW * this.z, rowX * this.w, rowY * this.w, rowZ * this.w, rowW * this.w, 0);
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
    public Float4 pow(float exponent) {
        return new Float4(Math.pow(this.x, exponent), Math.pow(this.y, exponent), Math.pow(this.z, exponent), Math.pow(this.w, exponent));
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
    public Float4 pow(Float4 exponent) {
        float exponentX = exponent.x();
        float exponentY = exponent.y();
        float exponentZ = exponent.z();
        float exponentW = exponent.w();
        return new Float4(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY), Math.pow(this.z, exponentZ), Math.pow(this.w, exponentW));
    }


    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}, {@code exponentW}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 pow(float exponentX, float exponentY, float exponentZ, float exponentW) {
        return new Float4(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY), Math.pow(this.z, exponentZ), Math.pow(this.w, exponentW));
    }


    /**
     * Project this vector onto {@code onto}, returning the result as a value.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @return the resulting vector
     */
    public Float4 project(Float4 onto) {
        float ontoX = onto.x();
        float ontoY = onto.y();
        float ontoZ = onto.z();
        float ontoW = onto.w();
        float _t9 = Math.fma(ontoW, this.w, Math.fma(ontoZ, this.z, Math.fma(ontoX, this.x, ontoY * this.y))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        return new Float4(ontoX * _t9, ontoY * _t9, ontoZ * _t9, ontoW * _t9);
    }


    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}, {@code ontoZ}, {@code ontoW}),
     * returning the result as a value.
     * <p>
     * Valid input: {@code (ontoX, ontoY, ontoZ, ontoW)} must be non-zero.
     *
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoZ the {@code z} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoW the {@code w} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @return the resulting vector
     */
    public Float4 project(float ontoX, float ontoY, float ontoZ, float ontoW) {
        float _t9 = Math.fma(ontoW, this.w, Math.fma(ontoZ, this.z, Math.fma(ontoX, this.x, ontoY * this.y))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        return new Float4(ontoX * _t9, ontoY * _t9, ontoZ * _t9, ontoW * _t9);
    }


    /**
     * Project this vector onto the plane with the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @return the resulting vector
     */
    public Float4 projectOnPlane(Float4 normal) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        float _t3 = Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        return new Float4(Math.fma(-normalX, _t3, this.x), Math.fma(-normalY, _t3, this.y), Math.fma(-normalZ, _t3, this.z), Math.fma(-normalW, _t3, this.w));
    }


    /**
     * Project this vector onto the plane with the given normal, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 projectOnPlane(float normalX, float normalY, float normalZ, float normalW) {
        float _t3 = Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        return new Float4(Math.fma(-normalX, _t3, this.x), Math.fma(-normalY, _t3, this.y), Math.fma(-normalZ, _t3, this.z), Math.fma(-normalW, _t3, this.w));
    }


    /**
     * Compute the value converted from degrees to radians of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 radians() {
        return new Float4(Math.toRadians(this.x), Math.toRadians(this.y), Math.toRadians(this.z), Math.toRadians(this.w));
    }


    /**
     * Reflect this vector about the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @return the resulting vector
     */
    public Float4 reflect(Float4 normal) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        float _t4 = 2.0f * Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        return new Float4(Math.fma(-normalX, _t4, this.x), Math.fma(-normalY, _t4, this.y), Math.fma(-normalZ, _t4, this.z), Math.fma(-normalW, _t4, this.w));
    }


    /**
     * Reflect this vector about the given normal, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 reflect(float normalX, float normalY, float normalZ, float normalW) {
        float _t4 = 2.0f * Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        return new Float4(Math.fma(-normalX, _t4, this.x), Math.fma(-normalY, _t4, this.y), Math.fma(-normalZ, _t4, this.z), Math.fma(-normalW, _t4, this.w));
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
    public Float4 refract(Float4 normal, float eta) {
        float normalX = normal.x();
        float normalY = normal.y();
        float normalZ = normal.z();
        float normalW = normal.w();
        float _t4 = Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            return new Float4(Math.fma(eta, this.x, -(normalX * _t11)), Math.fma(eta, this.y, -(normalY * _t11)), Math.fma(eta, this.z, -(normalZ * _t11)), Math.fma(eta, this.w, -(normalW * _t11)));
        } else {
            return Float4.ZERO;
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
     * @return the resulting vector
     */
    public Float4 refract(float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _t4 = Math.fma(normalW, this.w, Math.fma(normalZ, this.z, Math.fma(normalX, this.x, normalY * this.y)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            return new Float4(Math.fma(eta, this.x, -(normalX * _t11)), Math.fma(eta, this.y, -(normalY * _t11)), Math.fma(eta, this.z, -(normalZ * _t11)), Math.fma(eta, this.w, -(normalW * _t11)));
        } else {
            return Float4.ZERO;
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
    public Float4 round() {
        return new Float4(Math.rint(this.x), Math.rint(this.y), Math.rint(this.z), Math.rint(this.w));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 sign() {
        return new Float4(Math.signum(this.x), Math.signum(this.y), Math.signum(this.z), Math.signum(this.w));
    }


    /**
     * Compute the sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 sin() {
        return new Float4(Math.sin(this.x), Math.sin(this.y), Math.sin(this.z), Math.sin(this.w));
    }


    /**
     * Compute the hyperbolic sine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 sinh() {
        return new Float4(Math.sinh(this.x), Math.sinh(this.y), Math.sinh(this.z), Math.sinh(this.w));
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
    public Float4 smoothstep(float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.x - edge0) * _t0_inv));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.y - edge0) * _t0_inv));
        float _t15 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.z - edge0) * _t0_inv));
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.w - edge0) * _t0_inv));
        return new Float4(Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14, Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
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
    public Float4 smoothstep(Float4 edge0, Float4 edge1) {
        float edge0X = edge0.x();
        float edge0Y = edge0.y();
        float edge0Z = edge0.z();
        float edge0W = edge0.w();
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.x - edge0X) / (edge1.x() - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.y - edge0Y) / (edge1.y() - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.z - edge0Z) / (edge1.z() - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.w - edge0W) / (edge1.w() - edge0W)));
        return new Float4(Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
    }


    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) and the upper
     * edge ({@code edge1X}, {@code edge1Y}, {@code edge1Z}, {@code edge1W}), yielding 0 at or below
     * the lower edge and 1 at or above the upper edge, returning the result as a value.
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
     * @return the resulting vector
     */
    public Float4 smoothstep(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.x - edge0X) / (edge1X - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.y - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.z - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (this.w - edge0W) / (edge1W - edge0W)));
        return new Float4(Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
    }


    /**
     * Compute the square root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @return the resulting vector
     */
    public Float4 sqrt() {
        return new Float4((float) java.lang.Math.sqrt(this.x), (float) java.lang.Math.sqrt(this.y), (float) java.lang.Math.sqrt(this.z), (float) java.lang.Math.sqrt(this.w));
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
    public Float4 step(float edge) {
        return new Float4(this.x < edge ? 0.0f : 1.0f, this.y < edge ? 0.0f : 1.0f, this.z < edge ? 0.0f : 1.0f, this.w < edge ? 0.0f : 1.0f);
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
    public Float4 step(Float4 edge) {
        float edgeX = edge.x();
        float edgeY = edge.y();
        float edgeZ = edge.z();
        float edgeW = edge.w();
        return new Float4(this.x < edgeX ? 0.0f : 1.0f, this.y < edgeY ? 0.0f : 1.0f, this.z < edgeZ ? 0.0f : 1.0f, this.w < edgeW ? 0.0f : 1.0f);
    }


    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeW the {@code w} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @return the resulting vector
     */
    public Float4 step(float edgeX, float edgeY, float edgeZ, float edgeW) {
        return new Float4(this.x < edgeX ? 0.0f : 1.0f, this.y < edgeY ? 0.0f : 1.0f, this.z < edgeZ ? 0.0f : 1.0f, this.w < edgeW ? 0.0f : 1.0f);
    }


    /**
     * Compute the tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 tan() {
        return new Float4(Math.tan(this.x), Math.tan(this.y), Math.tan(this.z), Math.tan(this.w));
    }


    /**
     * Compute the hyperbolic tangent of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 tanh() {
        return new Float4(Math.tanh(this.x), Math.tanh(this.y), Math.tanh(this.z), Math.tanh(this.w));
    }


    /**
     * Compute the truncated value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 trunc() {
        return new Float4(this.x >= 0.0f ? Math.floor(this.x) : Math.ceil(this.x), this.y >= 0.0f ? Math.floor(this.y) : Math.ceil(this.y), this.z >= 0.0f ? Math.floor(this.z) : Math.ceil(this.z), this.w >= 0.0f ? Math.floor(this.w) : Math.ceil(this.w));
    }


    /**
     * Compute the unit in the last place (ulp) of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float4 ulp() {
        return new Float4(Math.ulp(this.x), Math.ulp(this.y), Math.ulp(this.z), Math.ulp(this.w));
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
    public Float4 preMul(Float4x4 mat) {
        return new Float4(Math.fma(mat.m03(), this.w, Math.fma(mat.m02(), this.z, Math.fma(mat.m00(), this.x, mat.m01() * this.y))), Math.fma(mat.m13(), this.w, Math.fma(mat.m12(), this.z, Math.fma(mat.m10(), this.x, mat.m11() * this.y))), Math.fma(mat.m23(), this.w, Math.fma(mat.m22(), this.z, Math.fma(mat.m20(), this.x, mat.m21() * this.y))), Math.fma(mat.m33(), this.w, Math.fma(mat.m32(), this.z, Math.fma(mat.m30(), this.x, mat.m31() * this.y))));
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion {@code quat}, i.e.
     * compute {@code q * this.xyz * q^-1}, leaving {@code w} unchanged, returning the result as a
     * value.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @return the resulting vector
     */
    public Float4 rotate(FloatQuat quat) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float _t9 = 2.0f * Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * Math.fma(quatY, this.z, -(quatZ * this.y));
        return new Float4(Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.y))), Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, this.z))), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion ({@code quatX},
     * {@code quatY}, {@code quatZ}, {@code quatW}), i.e. compute {@code q * this.xyz * q^-1},
     * leaving {@code w} unchanged, returning the result as a value.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @return the resulting vector
     */
    public Float4 rotate(float quatX, float quatY, float quatZ, float quatW) {
        float _t9 = 2.0f * Math.fma(quatX, this.y, -(quatY * this.x));
        float _t10 = 2.0f * Math.fma(quatZ, this.x, -(quatX * this.z));
        float _t11 = 2.0f * Math.fma(quatY, this.z, -(quatZ * this.y));
        return new Float4(Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.y))), Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, this.z))), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis {@code axis}, leaving {@code w} unchanged, returning the result as a value.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting vector
     */
    public Float4 rotateAxis(float angle, Float3 axis) {
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
        return new Float4(Math.fma(_t3, axisX * _t5, Math.fma(this.x, _t1, Math.fma(axisY, this.z, -(axisZ * this.y)) * _t0)), Math.fma(_t3, axisY * _t5, Math.fma(this.y, _t1, Math.fma(axisZ, this.x, -(axisX * this.z)) * _t0)), Math.fma(_t3, axisZ * _t5, Math.fma(this.z, _t1, Math.fma(axisX, this.y, -(axisY * this.x)) * _t0)), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis ({@code axisX}, {@code axisY}, {@code axisZ}), leaving {@code w} unchanged, returning
     * the result as a value.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting vector
     */
    public Float4 rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        return new Float4(Math.fma(_t3, axisX * _t5, Math.fma(this.x, _t1, Math.fma(axisY, this.z, -(axisZ * this.y)) * _t0)), Math.fma(_t3, axisY * _t5, Math.fma(this.y, _t1, Math.fma(axisZ, this.x, -(axisX * this.z)) * _t0)), Math.fma(_t3, axisZ * _t5, Math.fma(this.z, _t1, Math.fma(axisX, this.y, -(axisY * this.x)) * _t0)), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, returning the result as a value.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @return the resulting vector
     */
    public Float4 rotateInverse(FloatQuat quat) {
        float quatX = quat.x();
        float quatY = quat.y();
        float quatZ = quat.z();
        float quatW = quat.w();
        float _t9 = 2.0f * Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * Math.fma(quatZ, this.y, -(quatY * this.z));
        return new Float4(Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, this.y))), Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.z))), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, returning the result as a value.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @return the resulting vector
     */
    public Float4 rotateInverse(float quatX, float quatY, float quatZ, float quatW) {
        float _t9 = 2.0f * Math.fma(quatX, this.z, -(quatZ * this.x));
        float _t10 = 2.0f * Math.fma(quatY, this.x, -(quatX * this.y));
        float _t11 = 2.0f * Math.fma(quatZ, this.y, -(quatY * this.z));
        return new Float4(Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, this.x))), Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, this.y))), Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, this.z))), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the X
     * axis, leaving {@code w} unchanged, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting vector
     */
    public Float4 rotateX(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float4(this.x, Math.fma(this.y, _t1, -(this.z * _t0)), Math.fma(this.y, _t0, this.z * _t1), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Y
     * axis, leaving {@code w} unchanged, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting vector
     */
    public Float4 rotateY(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float4(Math.fma(this.x, _t1, this.z * _t0), this.y, Math.fma(this.z, _t1, -(this.x * _t0)), this.w);
    }


    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Z
     * axis, leaving {@code w} unchanged, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting vector
     */
    public Float4 rotateZ(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float4(Math.fma(this.x, _t1, -(this.y * _t0)), Math.fma(this.x, _t0, this.y * _t1), this.z, this.w);
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     */
    public Float4 withX(float x) {
        return new Float4(x, this.y(), this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     */
    public Float4 withY(float y) {
        return new Float4(this.x(), y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Float4 withXY(float x, float y) {
        return new Float4(x, y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param z the new value of the {@code z} component
     */
    public Float4 withZ(float z) {
        return new Float4(this.x(), this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Float4 withXZ(float x, float z) {
        return new Float4(x, this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Float4 withYZ(float y, float z) {
        return new Float4(this.x(), y, z, this.w());
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
    public Float4 withXYZ(float x, float y, float z) {
        return new Float4(x, y, z, this.w());
    }

    /**
     * {@return a copy of this vector with the W component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param w the new value of the {@code w} component
     */
    public Float4 withW(float w) {
        return new Float4(this.x(), this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the XW components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param w the new value of the {@code w} component
     */
    public Float4 withXW(float x, float w) {
        return new Float4(x, this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the YW components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     * @param w the new value of the {@code w} component
     */
    public Float4 withYW(float y, float w) {
        return new Float4(this.x(), y, this.z(), w);
    }

    /**
     * {@return a copy of this vector with the XYW components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     * @param w the new value of the {@code w} component
     */
    public Float4 withXYW(float x, float y, float w) {
        return new Float4(x, y, this.z(), w);
    }

    /**
     * {@return a copy of this vector with the ZW components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Float4 withZW(float z, float w) {
        return new Float4(this.x(), this.y(), z, w);
    }

    /**
     * {@return a copy of this vector with the XZW components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Float4 withXZW(float x, float z, float w) {
        return new Float4(x, this.y(), z, w);
    }

    /**
     * {@return a copy of this vector with the YZW components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Float4 withYZW(float y, float z, float w) {
        return new Float4(this.x(), y, z, w);
    }

    /**
     * {@return a copy of this vector with the XYZW components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Float4 withXYZW(float x, float y, float z, float w) {
        return new Float4(x, y, z, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 xw() {
        return new Float2(x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 yw() {
        return new Float2(y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 zw() {
        return new Float2(z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 wx() {
        return new Float2(w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 wy() {
        return new Float2(w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 wz() {
        return new Float2(w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float2 ww() {
        return new Float2(w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xxw() {
        return new Float3(x, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xyw() {
        return new Float3(x, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xzw() {
        return new Float3(x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xwx() {
        return new Float3(x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xwy() {
        return new Float3(x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xwz() {
        return new Float3(x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 xww() {
        return new Float3(x, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yxw() {
        return new Float3(y, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yyw() {
        return new Float3(y, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yzw() {
        return new Float3(y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 ywx() {
        return new Float3(y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 ywy() {
        return new Float3(y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 ywz() {
        return new Float3(y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 yww() {
        return new Float3(y, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zxw() {
        return new Float3(z, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zyw() {
        return new Float3(z, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zzw() {
        return new Float3(z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zwx() {
        return new Float3(z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zwy() {
        return new Float3(z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zwz() {
        return new Float3(z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 zww() {
        return new Float3(z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wxx() {
        return new Float3(w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wxy() {
        return new Float3(w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wxz() {
        return new Float3(w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wxw() {
        return new Float3(w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wyx() {
        return new Float3(w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wyy() {
        return new Float3(w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wyz() {
        return new Float3(w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wyw() {
        return new Float3(w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wzx() {
        return new Float3(w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wzy() {
        return new Float3(w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wzz() {
        return new Float3(w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wzw() {
        return new Float3(w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wwx() {
        return new Float3(w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wwy() {
        return new Float3(w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 wwz() {
        return new Float3(w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float3 www() {
        return new Float3(w, w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxxw() {
        return new Float4(x, x, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxyw() {
        return new Float4(x, x, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxzw() {
        return new Float4(x, x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxwx() {
        return new Float4(x, x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxwy() {
        return new Float4(x, x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxwz() {
        return new Float4(x, x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xxww() {
        return new Float4(x, x, w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyxw() {
        return new Float4(x, y, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyyw() {
        return new Float4(x, y, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyzw() {
        return new Float4(x, y, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xywx() {
        return new Float4(x, y, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xywy() {
        return new Float4(x, y, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xywz() {
        return new Float4(x, y, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xyww() {
        return new Float4(x, y, w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzxw() {
        return new Float4(x, z, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzyw() {
        return new Float4(x, z, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzzw() {
        return new Float4(x, z, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzwx() {
        return new Float4(x, z, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzwy() {
        return new Float4(x, z, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzwz() {
        return new Float4(x, z, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xzww() {
        return new Float4(x, z, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwxx() {
        return new Float4(x, w, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwxy() {
        return new Float4(x, w, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwxz() {
        return new Float4(x, w, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwxw() {
        return new Float4(x, w, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwyx() {
        return new Float4(x, w, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwyy() {
        return new Float4(x, w, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwyz() {
        return new Float4(x, w, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwyw() {
        return new Float4(x, w, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwzx() {
        return new Float4(x, w, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwzy() {
        return new Float4(x, w, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwzz() {
        return new Float4(x, w, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwzw() {
        return new Float4(x, w, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwwx() {
        return new Float4(x, w, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwwy() {
        return new Float4(x, w, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwwz() {
        return new Float4(x, w, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 xwww() {
        return new Float4(x, w, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxxw() {
        return new Float4(y, x, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxyw() {
        return new Float4(y, x, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxzw() {
        return new Float4(y, x, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxwx() {
        return new Float4(y, x, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxwy() {
        return new Float4(y, x, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxwz() {
        return new Float4(y, x, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yxww() {
        return new Float4(y, x, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyxw() {
        return new Float4(y, y, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyyw() {
        return new Float4(y, y, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyzw() {
        return new Float4(y, y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yywx() {
        return new Float4(y, y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yywy() {
        return new Float4(y, y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yywz() {
        return new Float4(y, y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yyww() {
        return new Float4(y, y, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzxw() {
        return new Float4(y, z, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzyw() {
        return new Float4(y, z, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzzw() {
        return new Float4(y, z, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzwx() {
        return new Float4(y, z, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzwy() {
        return new Float4(y, z, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzwz() {
        return new Float4(y, z, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 yzww() {
        return new Float4(y, z, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywxx() {
        return new Float4(y, w, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywxy() {
        return new Float4(y, w, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywxz() {
        return new Float4(y, w, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywxw() {
        return new Float4(y, w, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywyx() {
        return new Float4(y, w, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywyy() {
        return new Float4(y, w, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywyz() {
        return new Float4(y, w, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywyw() {
        return new Float4(y, w, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywzx() {
        return new Float4(y, w, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywzy() {
        return new Float4(y, w, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywzz() {
        return new Float4(y, w, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywzw() {
        return new Float4(y, w, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywwx() {
        return new Float4(y, w, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywwy() {
        return new Float4(y, w, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywwz() {
        return new Float4(y, w, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 ywww() {
        return new Float4(y, w, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxxw() {
        return new Float4(z, x, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxyw() {
        return new Float4(z, x, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxzw() {
        return new Float4(z, x, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxwx() {
        return new Float4(z, x, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxwy() {
        return new Float4(z, x, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxwz() {
        return new Float4(z, x, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zxww() {
        return new Float4(z, x, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyxw() {
        return new Float4(z, y, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyyw() {
        return new Float4(z, y, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyzw() {
        return new Float4(z, y, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zywx() {
        return new Float4(z, y, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zywy() {
        return new Float4(z, y, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zywz() {
        return new Float4(z, y, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zyww() {
        return new Float4(z, y, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzxw() {
        return new Float4(z, z, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzyw() {
        return new Float4(z, z, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzzw() {
        return new Float4(z, z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzwx() {
        return new Float4(z, z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzwy() {
        return new Float4(z, z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzwz() {
        return new Float4(z, z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zzww() {
        return new Float4(z, z, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwxx() {
        return new Float4(z, w, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwxy() {
        return new Float4(z, w, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwxz() {
        return new Float4(z, w, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwxw() {
        return new Float4(z, w, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwyx() {
        return new Float4(z, w, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwyy() {
        return new Float4(z, w, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwyz() {
        return new Float4(z, w, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwyw() {
        return new Float4(z, w, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwzx() {
        return new Float4(z, w, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwzy() {
        return new Float4(z, w, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwzz() {
        return new Float4(z, w, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwzw() {
        return new Float4(z, w, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwwx() {
        return new Float4(z, w, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwwy() {
        return new Float4(z, w, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwwz() {
        return new Float4(z, w, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 zwww() {
        return new Float4(z, w, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxxx() {
        return new Float4(w, x, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxxy() {
        return new Float4(w, x, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxxz() {
        return new Float4(w, x, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxxw() {
        return new Float4(w, x, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxyx() {
        return new Float4(w, x, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxyy() {
        return new Float4(w, x, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxyz() {
        return new Float4(w, x, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxyw() {
        return new Float4(w, x, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxzx() {
        return new Float4(w, x, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxzy() {
        return new Float4(w, x, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxzz() {
        return new Float4(w, x, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxzw() {
        return new Float4(w, x, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxwx() {
        return new Float4(w, x, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxwy() {
        return new Float4(w, x, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxwz() {
        return new Float4(w, x, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wxww() {
        return new Float4(w, x, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyxx() {
        return new Float4(w, y, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyxy() {
        return new Float4(w, y, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyxz() {
        return new Float4(w, y, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyxw() {
        return new Float4(w, y, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyyx() {
        return new Float4(w, y, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyyy() {
        return new Float4(w, y, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyyz() {
        return new Float4(w, y, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyyw() {
        return new Float4(w, y, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyzx() {
        return new Float4(w, y, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyzy() {
        return new Float4(w, y, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyzz() {
        return new Float4(w, y, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyzw() {
        return new Float4(w, y, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wywx() {
        return new Float4(w, y, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wywy() {
        return new Float4(w, y, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wywz() {
        return new Float4(w, y, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wyww() {
        return new Float4(w, y, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzxx() {
        return new Float4(w, z, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzxy() {
        return new Float4(w, z, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzxz() {
        return new Float4(w, z, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzxw() {
        return new Float4(w, z, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzyx() {
        return new Float4(w, z, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzyy() {
        return new Float4(w, z, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzyz() {
        return new Float4(w, z, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzyw() {
        return new Float4(w, z, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzzx() {
        return new Float4(w, z, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzzy() {
        return new Float4(w, z, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzzz() {
        return new Float4(w, z, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzzw() {
        return new Float4(w, z, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzwx() {
        return new Float4(w, z, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzwy() {
        return new Float4(w, z, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzwz() {
        return new Float4(w, z, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wzww() {
        return new Float4(w, z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwxx() {
        return new Float4(w, w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwxy() {
        return new Float4(w, w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwxz() {
        return new Float4(w, w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwxw() {
        return new Float4(w, w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwyx() {
        return new Float4(w, w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwyy() {
        return new Float4(w, w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwyz() {
        return new Float4(w, w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwyw() {
        return new Float4(w, w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwzx() {
        return new Float4(w, w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwzy() {
        return new Float4(w, w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwzz() {
        return new Float4(w, w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwzw() {
        return new Float4(w, w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwwx() {
        return new Float4(w, w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwwy() {
        return new Float4(w, w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwwz() {
        return new Float4(w, w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Float4 wwww() {
        return new Float4(w, w, w, w);
    }

    @Override public String toString() {
        return "Float4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float4)) return false;
        Float4 o = (Float4) obj;
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

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(x)
            && Float.isFinite(y)
            && Float.isFinite(z)
            && Float.isFinite(w);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(x)
            || Float.isNaN(y)
            || Float.isNaN(z)
            || Float.isNaN(w);
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
    public boolean equalsEpsilon(Float4 other, float epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon
            && java.lang.Math.abs(w - other.w()) <= epsilon;
    }

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
        dest[offset + 3] = this.w;
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        return new Float4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(float[] src) { return load(src, 0); }

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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(FloatBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 4);
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(ByteBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
     */
    public Float4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadUnsafe(long address) {
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(long offset, MemorySegment src) {
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
        dest[offset + 3] = this.w;
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        return new Float4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(double[] src) { return load(src, 0); }

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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 load(DoubleBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4 loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 4);
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadDouble(ByteBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4 loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4 r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public Float4 storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadDoubleUnsafe(long address) {
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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

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
     * @return a new {@code Float4} holding the loaded elements
     */
    public static Float4 loadDouble(long offset, MemorySegment src) {
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
