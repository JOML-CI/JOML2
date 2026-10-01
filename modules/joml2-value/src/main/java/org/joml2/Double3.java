// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

/**
 * Immutable 3D vector of double-precision {@code double} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a finite
 * one only under an infinite {@code epsilon}; a NaN component never compares equal to anything.
 *
 * @param x the {@code x} component
 * @param y the {@code y} component
 * @param z the {@code z} component
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record Double3(double x, double y, double z) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 24;

    /** The zero vector (all components 0). */
    public static final Double3 ZERO = new Double3(0, 0, 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public Double3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: the method reads no input.
     */
    public Double3() {
        this(0, 0, 0);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public Double3(double s) {
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
    public Double3(double v0, Double2 v1) {
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
    public Double3(Double2 v0, double v1) {
        this(v0.x(), v0.y(), v1);
    }

    /**
     * Create an array of {@code length} elements, each {@link #ZERO}.
     * <p>
     * The array is flat where the JVM lets this library create one: when the application exports
     * {@code java.base/jdk.internal.value} to it
     * ({@code --add-exports java.base/jdk.internal.value=org.joml2}, or {@code =ALL-UNNAMED} on the
     * classpath). Its elements are then stored inline, without an object each: reading one follows
     * no reference and storing one allocates nothing. Such an array holds no {@code null} - storing
     * one throws {@link NullPointerException} - and an element written by one thread while another
     * reads it can be read torn, part old, part new. Without the export it is an ordinary array,
     * filled the same way.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @return the new array
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Double3[] newArray(int length) {
        return (Double3[]) org.joml2.internal.value.FlatArrays.newArray(Double3.class, length, ZERO);
    }

    /**
     * Create an array of {@code length} elements, each {@code init}.
     * <p>
     * The array is flat where the JVM lets this library create one: when the application exports
     * {@code java.base/jdk.internal.value} to it
     * ({@code --add-exports java.base/jdk.internal.value=org.joml2}, or {@code =ALL-UNNAMED} on the
     * classpath). Its elements are then stored inline, without an object each: reading one follows
     * no reference and storing one allocates nothing. Such an array holds no {@code null} - storing
     * one throws {@link NullPointerException} - and an element written by one thread while another
     * reads it can be read torn, part old, part new. Without the export it is an ordinary array,
     * filled the same way.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @param init the value every element starts as
     * @return the new array
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Double3[] newArray(int length, Double3 init) {
        return (Double3[]) org.joml2.internal.value.FlatArrays.newArray(Double3.class, length, init);
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public double x() { return x; }

    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public double y() { return y; }

    /** {@return the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    public double z() { return z; }

    /**
     * Create a direction uniformly distributed on the unit sphere, drawing the 2 samples of
     * {@code makeUniformDirection} from {@code rng}, each with {@code rng.nextDouble()}, in
     * parameter order.
     * <p>
     * Valid input: any value.
     *
     * @param rng the random number generator to draw the 2 samples from
     * @return the resulting vector
     */
    public static Double3 makeRandomDirection(java.util.Random rng) {
        return makeUniformDirection(rng.nextDouble(), rng.nextDouble());
    }

    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Double3 add(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        return new Double3(otherX + this.x, otherY + this.y, otherZ + this.z);
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
    public Double3 add(double otherX, double otherY, double otherZ) {
        return new Double3(otherX + this.x, otherY + this.y, otherZ + this.z);
    }

    /**
     * Divide each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Double3 div(double scalar) {
        return new Double3(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    /**
     * Divide this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Double3 div(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        return new Double3(this.x / otherX, this.y / otherY, this.z / otherZ);
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
    public Double3 div(double otherX, double otherY, double otherZ) {
        return new Double3(this.x / otherX, this.y / otherY, this.z / otherZ);
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
    public Double3 fma(double b, Double3 c) {
        double cX = c.x;
        double cY = c.y;
        double cZ = c.z;
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(this.x, b, cX), java.lang.Math.fma(this.y, b, cY), java.lang.Math.fma(this.z, b, cZ));
        } else {
            return new Double3(((this.x) * (b) + (cX)), ((this.y) * (b) + (cY)), ((this.z) * (b) + (cZ)));
        }
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
    public Double3 fma(double b, double cX, double cY, double cZ) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(this.x, b, cX), java.lang.Math.fma(this.y, b, cY), java.lang.Math.fma(this.z, b, cZ));
        } else {
            return new Double3(((this.x) * (b) + (cX)), ((this.y) * (b) + (cY)), ((this.z) * (b) + (cZ)));
        }
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
    public Double3 fma(Double3 b, Double3 c) {
        double bX = b.x;
        double bY = b.y;
        double bZ = b.z;
        double cX = c.x;
        double cY = c.y;
        double cZ = c.z;
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(this.x, bX, cX), java.lang.Math.fma(this.y, bY, cY), java.lang.Math.fma(this.z, bZ, cZ));
        } else {
            return new Double3(((this.x) * (bX) + (cX)), ((this.y) * (bY) + (cY)), ((this.z) * (bZ) + (cZ)));
        }
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
    public Double3 fma(double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(this.x, bX, cX), java.lang.Math.fma(this.y, bY, cY), java.lang.Math.fma(this.z, bZ, cZ));
        } else {
            return new Double3(((this.x) * (bX) + (cX)), ((this.y) * (bY) + (cY)), ((this.z) * (bZ) + (cZ)));
        }
    }

    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Double3 mul(double scalar) {
        return new Double3(scalar * this.x, scalar * this.y, scalar * this.z);
    }

    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Double3 mul(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        return new Double3(otherX * this.x, otherY * this.y, otherZ * this.z);
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
    public Double3 mul(double otherX, double otherY, double otherZ) {
        return new Double3(otherX * this.x, otherY * this.y, otherZ * this.z);
    }

    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 negate() {
        return new Double3(-this.x, -this.y, -this.z);
    }

    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Double3 sub(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        return new Double3(this.x - otherX, this.y - otherY, this.z - otherZ);
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
    public Double3 sub(double otherX, double otherY, double otherZ) {
        return new Double3(this.x - otherX, this.y - otherY, this.z - otherZ);
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
    public static Double3 makeUniformDirection(double u, double v) {
        if (Math.useFma()) {
            double _t1 = v * 6.283185307179586;
            double _t2 = Math.sin(_t1);
            double _t5 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            return new Double3(_t5 * Math.cosFromSin(_t2, _t1), _t5 * _t2, java.lang.Math.fma(2.0, u, -1.0));
        } else {
            double _t1 = v * 6.283185307179586;
            double _t2 = Math.sin(_t1);
            double _t5 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            return new Double3(_t5 * Math.cosFromSin(_t2, _t1), _t5 * _t2, ((2.0) * (u) - (1.0)));
        }
    }

    /**
     * Return a vector with the values of {@code v}: that vector itself (an immutable value needs no
     * copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Double3 set(Double3 v) {
        double vX = v.x;
        double vY = v.y;
        double vZ = v.z;
        return new Double3(vX, vY, vZ);
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
    public Double3 set(double vX, double vY, double vZ) {
        return new Double3(vX, vY, vZ);
    }

    /**
     * Compute a vector with every component {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Double3 set(double s) {
        return new Double3(s, s, s);
    }

    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float3} holding the result
     */
    public Float3 toFloat() {
        return new Float3((float) (this.x), (float) (this.y), (float) (this.z));
    }

    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code int} range, then wrapped to the low 8 bits (so values outside the
     * {@code byte} range wrap), NaN converted to 0.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte3} holding the result
     */
    public Byte3 toByte() {
        return new Byte3((byte) (this.x), (byte) (this.y), (byte) (this.z));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte3 toByte_half_toward_positive_infinity() {
        return new Byte3((byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x))), (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y))), (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.z))));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toByte(RoundingMode)}; reached only through it. */
    private Byte3 toByte_half_away_from_zero() {
        return new Byte3((byte) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (byte) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)), (byte) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z)));
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
            case HALF_TOWARD_POSITIVE_INFINITY -> toByte_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toByte_half_away_from_zero();
            case HALF_EVEN -> new Byte3((byte) Math.rint(this.x), (byte) Math.rint(this.y), (byte) Math.rint(this.z));
        };
    }

    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code int} range, then wrapped to the low 16 bits (so values outside the
     * {@code short} range wrap), NaN converted to 0.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short3} holding the result
     */
    public Short3 toShort() {
        return new Short3((short) (this.x), (short) (this.y), (short) (this.z));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short3 toShort_half_toward_positive_infinity() {
        return new Short3((short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x))), (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y))), (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.z))));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toShort(RoundingMode)}; reached only through it. */
    private Short3 toShort_half_away_from_zero() {
        return new Short3((short) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (short) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)), (short) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z)));
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
            case HALF_TOWARD_POSITIVE_INFINITY -> toShort_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toShort_half_away_from_zero();
            case HALF_EVEN -> new Short3((short) Math.rint(this.x), (short) Math.rint(this.y), (short) Math.rint(this.z));
        };
    }

    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code int} range, NaN converted to 0.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int3} holding the result
     */
    public Int3 toInt() {
        return new Int3((int) (this.x), (int) (this.y), (int) (this.z));
    }

    /** Private {@code RoundingMode.HALF_TOWARD_POSITIVE_INFINITY} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int3 toInt_half_toward_positive_infinity() {
        return new Int3((int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.x))), (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.y))), (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(this.z))));
    }

    /** Private {@code RoundingMode.HALF_AWAY_FROM_ZERO} body of {@code toInt(RoundingMode)}; reached only through it. */
    private Int3 toInt_half_away_from_zero() {
        return new Int3((int) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (int) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)), (int) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z)));
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
            case HALF_TOWARD_POSITIVE_INFINITY -> toInt_half_toward_positive_infinity();
            case HALF_AWAY_FROM_ZERO -> toInt_half_away_from_zero();
            case HALF_EVEN -> new Int3((int) Math.rint(this.x), (int) Math.rint(this.y), (int) Math.rint(this.z));
        };
    }

    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast as Java narrows it: truncated toward zero,
     * saturated to the {@code long} range, NaN converted to 0.
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
        return new Long3((long) (java.lang.Math.abs(this.x - Math.rint(this.x)) == 0.5 ? this.x + Math.copySign(0.5, this.x) : Math.rint(this.x)), (long) (java.lang.Math.abs(this.y - Math.rint(this.y)) == 0.5 ? this.y + Math.copySign(0.5, this.y) : Math.rint(this.y)), (long) (java.lang.Math.abs(this.z - Math.rint(this.z)) == 0.5 ? this.z + Math.copySign(0.5, this.z) : Math.rint(this.z)));
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
            case HALF_TOWARD_POSITIVE_INFINITY -> new Long3(Math.round(this.x), Math.round(this.y), Math.round(this.z));
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
    public static Double3 makeZero() {
        return Double3.ZERO;
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
    public Double3 bezier(Double3 p1, Double3 p2, Double3 p3, double t) {
        return bezier(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z, p3.x, p3.y, p3.z, t);
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
    public Double3 bezier(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Math.useFma()) {
            double _t0 = 1.0 - t;
            double _t1 = t * t;
            double _t2 = t * _t1;
            double _t3 = _t0 * _t0;
            double _t6 = 3.0 * _t0 * _t1;
            double _t7 = 3.0 * t * _t3;
            double _t8 = _t0 * _t3;
            return new Double3(java.lang.Math.fma(p1X, _t7, this.x * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2), java.lang.Math.fma(p1Y, _t7, this.y * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2), java.lang.Math.fma(p1Z, _t7, this.z * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        } else {
            double _t0 = 1.0 - t;
            double _t1 = t * t;
            double _t2 = t * _t1;
            double _t3 = _t0 * _t0;
            double _t6 = 3.0 * _t0 * _t1;
            double _t7 = 3.0 * t * _t3;
            double _t8 = _t0 * _t3;
            return new Double3(((p1X) * (_t7) + (this.x * _t8)) + ((p2X) * (_t6) + (p3X * _t2)), ((p1Y) * (_t7) + (this.y * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)), ((p1Z) * (_t7) + (this.z * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        }
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
    public Double3 bezier2(Double3 p1, Double3 p2, double t) {
        double p1X = p1.x;
        double p1Y = p1.y;
        double p1Z = p1.z;
        double p2X = p2.x;
        double p2Y = p2.y;
        double p2Z = p2.z;
        if (Math.useFma()) {
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            return new Double3(java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4)), java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4)), java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, this.z * _t4)));
        } else {
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            return new Double3(((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4)))), ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4)))), ((p2Z) * (_t0) + (((p1Z) * (_t3) + (this.z * _t4)))));
        }
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
    public Double3 bezier2(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Math.useFma()) {
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            return new Double3(java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, this.x * _t4)), java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, this.y * _t4)), java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, this.z * _t4)));
        } else {
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            return new Double3(((p2X) * (_t0) + (((p1X) * (_t3) + (this.x * _t4)))), ((p2Y) * (_t0) + (((p1Y) * (_t3) + (this.y * _t4)))), ((p2Z) * (_t0) + (((p1Z) * (_t3) + (this.z * _t4)))));
        }
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
    public Double3 bezier2Tangent(Double3 p1, Double3 p2, double t) {
        double p1X = p1.x;
        double p1Y = p1.y;
        double p1Z = p1.z;
        double p2X = p2.x;
        double p2Y = p2.y;
        double p2Z = p2.z;
        if (Math.useFma()) {
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            return new Double3(java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1), java.lang.Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1));
        } else {
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            return new Double3(((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1)), ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1)), ((p1Z - this.z) * (_t2) + ((p2Z - p1Z) * _t1)));
        }
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
    public Double3 bezier2Tangent(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Math.useFma()) {
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            return new Double3(java.lang.Math.fma(p1X - this.x, _t2, (p2X - p1X) * _t1), java.lang.Math.fma(p1Y - this.y, _t2, (p2Y - p1Y) * _t1), java.lang.Math.fma(p1Z - this.z, _t2, (p2Z - p1Z) * _t1));
        } else {
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            return new Double3(((p1X - this.x) * (_t2) + ((p2X - p1X) * _t1)), ((p1Y - this.y) * (_t2) + ((p2Y - p1Y) * _t1)), ((p1Z - this.z) * (_t2) + ((p2Z - p1Z) * _t1)));
        }
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
    public Double3 bezierTangent(Double3 p1, Double3 p2, Double3 p3, double t) {
        return bezierTangent(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z, p3.x, p3.y, p3.z, t);
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
    public Double3 bezierTangent(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Math.useFma()) {
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            return new Double3(java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - this.x, _t6, (p2X - p1X) * _t5)), java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - this.y, _t6, (p2Y - p1Y) * _t5)), java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - this.z, _t6, (p2Z - p1Z) * _t5)));
        } else {
            double _t1 = 1.0 - t;
            double _t2 = 3.0 * t * t;
            double _t5 = 6.0 * t * _t1;
            double _t6 = 3.0 * _t1 * _t1;
            return new Double3(((p3X - p2X) * (_t2) + (((p1X - this.x) * (_t6) + ((p2X - p1X) * _t5)))), ((p3Y - p2Y) * (_t2) + (((p1Y - this.y) * (_t6) + ((p2Y - p1Y) * _t5)))), ((p3Z - p2Z) * (_t2) + (((p1Z - this.z) * (_t6) + ((p2Z - p1Z) * _t5)))));
        }
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
    public Double3 catmullRom(Double3 p1, Double3 p2, Double3 p3, double t) {
        return catmullRom(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z, p3.x, p3.y, p3.z, t);
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
    public Double3 catmullRom(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!Math.useFma()) return catmullRom_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        double _t0 = t * t;
        double _t1 = t * _t0;
        return new Double3(0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - this.x)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, this.x, java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - this.x)) * _t1)), 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - this.y)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, this.y, java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - this.y)) * _t1)), 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - this.z)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, this.z, java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - this.z)) * _t1)));
    }

    /** {@code catmullRom} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 catmullRom_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _t0 = t * t;
        double _t1 = t * _t0;
        return new Double3(0.5 * (((2.0) * (p1X) + (t * (p2X - this.x))) + ((((((2.0) * (this.x) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - this.x))) - (3.0) * (p2X)) * _t1))), 0.5 * (((2.0) * (p1Y) + (t * (p2Y - this.y))) + ((((((2.0) * (this.y) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - this.y))) - (3.0) * (p2Y)) * _t1))), 0.5 * (((2.0) * (p1Z) + (t * (p2Z - this.z))) + ((((((2.0) * (this.z) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - this.z))) - (3.0) * (p2Z)) * _t1))));
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
    public Double3 catmullRomTangent(Double3 p1, Double3 p2, Double3 p3, double t) {
        return catmullRomTangent(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z, p3.x, p3.y, p3.z, t);
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
    public Double3 catmullRomTangent(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!Math.useFma()) return catmullRomTangent_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        double _t0 = t * t;
        return new Double3(0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, this.x, java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - this.x)), _t0, p2X - this.x)), 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, this.y, java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - this.y)), _t0, p2Y - this.y)), 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, this.z, java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - this.z)), _t0, p2Z - this.z)));
    }

    /** {@code catmullRomTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 catmullRomTangent_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _t0 = t * t;
        return new Double3(0.5 * ((t) * (2.0 * ((((2.0) * (this.x) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - this.x))) - (3.0) * (p2X))) * (_t0) + (p2X - this.x)))), 0.5 * ((t) * (2.0 * ((((2.0) * (this.y) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - this.y))) - (3.0) * (p2Y))) * (_t0) + (p2Y - this.y)))), 0.5 * ((t) * (2.0 * ((((2.0) * (this.z) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - this.z))) - (3.0) * (p2Z))) * (_t0) + (p2Z - this.z)))));
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
    public Double3 hermite(Double3 t0, Double3 v1, Double3 t1, double t) {
        double t0X = t0.x;
        double t0Y = t0.y;
        double t0Z = t0.z;
        double v1X = v1.x;
        double v1Y = v1.y;
        double v1Z = v1.z;
        double t1X = t1.x;
        double t1Y = t1.y;
        double t1Z = t1.z;
        if (!Math.useFma()) return hermite_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        return new Double3(java.lang.Math.fma(this.x, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9), java.lang.Math.fma(this.y, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9), java.lang.Math.fma(this.z, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
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
    public Double3 hermite(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!Math.useFma()) return hermite_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        return new Double3(java.lang.Math.fma(this.x, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9), java.lang.Math.fma(this.y, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9), java.lang.Math.fma(this.z, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
    }

    /** {@code hermite} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 hermite_mulAdd(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        return new Double3(((this.x) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)), ((this.y) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)), ((this.z) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
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
    public Double3 hermiteTangent(Double3 t0, Double3 v1, Double3 t1, double t) {
        double t0X = t0.x;
        double t0Y = t0.y;
        double t0Z = t0.z;
        double v1X = v1.x;
        double v1Y = v1.y;
        double v1Z = v1.z;
        double t1X = t1.x;
        double t1Y = t1.y;
        double t1Z = t1.z;
        if (!Math.useFma()) return hermiteTangent_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        return new Double3(java.lang.Math.fma(this.x, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7), java.lang.Math.fma(this.y, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7), java.lang.Math.fma(this.z, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
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
    public Double3 hermiteTangent(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!Math.useFma()) return hermiteTangent_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        return new Double3(java.lang.Math.fma(this.x, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7), java.lang.Math.fma(this.y, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7), java.lang.Math.fma(this.z, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
    }

    /** {@code hermiteTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 hermiteTangent_mulAdd(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        return new Double3(((this.x) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)), ((this.y) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)), ((this.z) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
    }

    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting vector
     */
    public Double3 lerp(Double3 other, double t) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(t, otherX - this.x, this.x), java.lang.Math.fma(t, otherY - this.y, this.y), java.lang.Math.fma(t, otherZ - this.z, this.z));
        } else {
            return new Double3(((t) * (otherX - this.x) + (this.x)), ((t) * (otherY - this.y) + (this.y)), ((t) * (otherZ - this.z) + (this.z)));
        }
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
     * than the other one (1e17 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting vector
     */
    public Double3 lerp(double otherX, double otherY, double otherZ, double t) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(t, otherX - this.x, this.x), java.lang.Math.fma(t, otherY - this.y, this.y), java.lang.Math.fma(t, otherZ - this.z, this.z));
        } else {
            return new Double3(((t) * (otherX - this.x) + (this.x)), ((t) * (otherY - this.y) + (this.y)), ((t) * (otherZ - this.z) + (this.z)));
        }
    }

    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @return the resulting vector
     */
    public Double3 lerp(Double3 other, Double3 t) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double tX = t.x;
        double tY = t.y;
        double tZ = t.z;
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(tX, otherX - this.x, this.x), java.lang.Math.fma(tY, otherY - this.y, this.y), java.lang.Math.fma(tZ, otherZ - this.z, this.z));
        } else {
            return new Double3(((tX) * (otherX - this.x) + (this.x)), ((tY) * (otherY - this.y) + (this.y)), ((tZ) * (otherZ - this.z) + (this.z)));
        }
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
     * than the other one (1e17 towards 1 ends at 0).
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
    public Double3 lerp(double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(tX, otherX - this.x, this.x), java.lang.Math.fma(tY, otherY - this.y, this.y), java.lang.Math.fma(tZ, otherZ - this.z, this.z));
        } else {
            return new Double3(((tX) * (otherX - this.x) + (this.x)), ((tY) * (otherY - this.y) + (this.y)), ((tZ) * (otherZ - this.z) + (this.z)));
        }
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
     * @return the resulting vector
     */
    public Double3 slerp(Double3 other, double t) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        if (!Math.useFma()) return slerp_mulAdd(otherX, otherY, otherZ, t);
        double _t7 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = this.x * _t9;
        double _t17 = this.z * _t9;
        double _t20 = this.y * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        Double3 _r = slerp_s3f564b11_tail_fma(otherX, _t12, _t26, _t14, otherY, _t20, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), _t17, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate_fma(otherX, otherY, otherZ, t);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private Double3 slerp_s3f564b11_tail_fma(double otherX, double _t12, double _t26, double _t14, double otherY, double _t20, double _t33, double _t17, double t, double _t8, double _t7) {
        double _t34 = java.lang.Math.fma(otherX, _t12, -(_t26 * _t14));
        double _t35 = java.lang.Math.fma(otherY, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return null;
        double _t24 = t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        return new Double3(java.lang.Math.fma(_t14, _t55, _sp0 * _t41), java.lang.Math.fma(_t20, _t55, _sp0 * _t42), java.lang.Math.fma(_t17, _t55, _sp0 * _t40));
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private Double3 slerp_s3f564b11_tail_mulAdd(double otherX, double _t12, double _t26, double _t14, double otherY, double _t20, double _t33, double _t17, double t, double _t8, double _t7) {
        double _t34 = ((otherX) * (_t12) - (_t26 * _t14));
        double _t35 = ((otherY) * (_t12) - (_t26 * _t20));
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return null;
        double _t24 = t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        return new Double3(((_t14) * (_t55) + (_sp0 * _t41)), ((_t20) * (_t55) + (_sp0 * _t42)), ((_t17) * (_t55) + (_sp0 * _t40)));
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
     * @return the resulting vector
     */
    public Double3 slerp(double otherX, double otherY, double otherZ, double t) {
        if (!Math.useFma()) return slerp_mulAdd(otherX, otherY, otherZ, t);
        double _t7 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = this.x * _t9;
        double _t17 = this.z * _t9;
        double _t20 = this.y * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        Double3 _r = slerp_s3f564b11_tail_fma(otherX, _t12, _t26, _t14, otherY, _t20, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), _t17, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate_fma(otherX, otherY, otherZ, t);
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 slerp_mulAdd(double otherX, double otherY, double otherZ, double t) {
        double _t7 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = this.x * _t9;
        double _t17 = this.z * _t9;
        double _t20 = this.y * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        Double3 _r = slerp_s3f564b11_tail_mulAdd(otherX, _t12, _t26, _t14, otherY, _t20, ((otherZ) * (_t12) - (_t26 * _t17)), _t17, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate_mulAdd(otherX, otherY, otherZ, t);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s3f564b11_tail_fma(double _t36, double _t37, double _t35, double _t33, double _t31, double _t28, double _t9, double _t10, double _t11, double t, double _t48, double _t46, double _t15_inv, double otherX, double otherY, double otherZ) {
        double _t49, _t50, _t52;
        if (_t36 < _t37) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        return slerp_degenerate_s3f564b11_tail2_fma(_t74, unitScale(_t75, _t76, _t74), _t75, _t76, t, _t53, _t48, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t46, _t33, _t15_inv, otherX, _t35, otherY, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s3f564b11_tail_mulAdd(double _t36, double _t37, double _t35, double _t33, double _t31, double _t28, double _t9, double _t10, double _t11, double t, double _t48, double _t46, double _t15_inv, double otherX, double otherY, double otherZ) {
        double _t49, _t50, _t52;
        if (_t36 < _t37) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        return slerp_degenerate_s3f564b11_tail2_mulAdd(_t74, unitScale(_t75, _t76, _t74), _t75, _t76, t, _t53, _t48, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t46, _t33, _t15_inv, otherX, _t35, otherY, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s3f564b11_tail2_fma(double _t74, double _t78, double _t75, double _t76, double t, double _t53, double _t48, double _t69, double _t70, double _t71, double _t46, double _t33, double _t15_inv, double otherX, double _t35, double otherY, double _t31, double otherZ) {
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
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
        return slerp_degenerate_s3f564b11_tail3_fma(_t46, _t48 * Math.sin(_t95), _t53, java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)), _t69, _t104, _t48 * Math.cos(_t95), _t33, _t15_inv, t, otherX, _t71, _t106, _t35, otherY, _t70, _t105, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s3f564b11_tail2_mulAdd(double _t74, double _t78, double _t75, double _t76, double t, double _t53, double _t48, double _t69, double _t70, double _t71, double _t46, double _t33, double _t15_inv, double otherX, double _t35, double otherY, double _t31, double otherZ) {
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
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
        return slerp_degenerate_s3f564b11_tail3_mulAdd(_t46, _t48 * Math.sin(_t95), _t53, ((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))), _t69, _t104, _t48 * Math.cos(_t95), _t33, _t15_inv, t, otherX, _t71, _t106, _t35, otherY, _t70, _t105, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s3f564b11_tail3_fma(double _t46, double _t99, double _t53, double _t88, double _t69, double _t104, double _t100, double _t33, double _t15_inv, double t, double otherX, double _t71, double _t106, double _t35, double otherY, double _t70, double _t105, double _t31, double otherZ) {
        double _sfx0, _sfx1, _sfx2;
        if (_t46 > 0.0) {
            if (_t53 < 0.0) {
                if (_t88 <= 5.048709793414476E-29) {
                    _sfx0 = java.lang.Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv;
                    _sfx1 = java.lang.Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv;
                    _sfx2 = java.lang.Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv;
                } else {
                    _sfx0 = java.lang.Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                    _sfx1 = java.lang.Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                    _sfx2 = java.lang.Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
                }
            } else {
                _sfx0 = java.lang.Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                _sfx1 = java.lang.Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                _sfx2 = java.lang.Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
            }
        } else {
            _sfx0 = java.lang.Math.fma(t, otherX - this.x, this.x);
            _sfx1 = java.lang.Math.fma(t, otherY - this.y, this.y);
            _sfx2 = java.lang.Math.fma(t, otherZ - this.z, this.z);
        }
        return new Double3(_sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s3f564b11_tail3_mulAdd(double _t46, double _t99, double _t53, double _t88, double _t69, double _t104, double _t100, double _t33, double _t15_inv, double t, double otherX, double _t71, double _t106, double _t35, double otherY, double _t70, double _t105, double _t31, double otherZ) {
        double _sfx0, _sfx1, _sfx2;
        if (_t46 > 0.0) {
            if (_t53 < 0.0) {
                if (_t88 <= 5.048709793414476E-29) {
                    _sfx0 = ((_t99) * (_t69) + (_t100 * _t33)) * _t15_inv;
                    _sfx1 = ((_t99) * (_t71) + (_t100 * _t35)) * _t15_inv;
                    _sfx2 = ((_t99) * (_t70) + (_t100 * _t31)) * _t15_inv;
                } else {
                    _sfx0 = ((_t99) * (_t104) + (_t100 * _t33)) * _t15_inv;
                    _sfx1 = ((_t99) * (_t106) + (_t100 * _t35)) * _t15_inv;
                    _sfx2 = ((_t99) * (_t105) + (_t100 * _t31)) * _t15_inv;
                }
            } else {
                _sfx0 = ((_t99) * (_t104) + (_t100 * _t33)) * _t15_inv;
                _sfx1 = ((_t99) * (_t106) + (_t100 * _t35)) * _t15_inv;
                _sfx2 = ((_t99) * (_t105) + (_t100 * _t31)) * _t15_inv;
            }
        } else {
            _sfx0 = ((t) * (otherX - this.x) + (this.x));
            _sfx1 = ((t) * (otherY - this.y) + (this.y));
            _sfx2 = ((t) * (otherZ - this.z) + (this.z));
        }
        return new Double3(_sfx0, _sfx1, _sfx2);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_fma(double otherX, double otherY, double otherZ, double t) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = this.z * _t2;
        double _t13 = this.x * _t2;
        double _t14 = this.y * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        return slerp_degenerate_s3f564b11_tail_fma(java.lang.Math.abs(_t31), java.lang.Math.abs(_t33), _t29 * _t14, _t33, _t31, (1.0 / java.lang.Math.sqrt(_t24)), _t9, _t10, _t11, t, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t24 * _t25, 1.0 / _t15, otherX, otherY, otherZ);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_mulAdd(double otherX, double otherY, double otherZ, double t) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = this.z * _t2;
        double _t13 = this.x * _t2;
        double _t14 = this.y * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        return slerp_degenerate_s3f564b11_tail_mulAdd(java.lang.Math.abs(_t31), java.lang.Math.abs(_t33), _t29 * _t14, _t33, _t31, (1.0 / java.lang.Math.sqrt(_t24)), _t9, _t10, _t11, t, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t24 * _t25, 1.0 / _t15, otherX, otherY, otherZ);
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
     * finite length are handled: when their squared lengths leave the {@code double} range, they
     * are first scaled exactly by powers of two.
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
     *        without it uses {@code 2^-94}
     * @return the resulting vector
     */
    public Double3 slerp(Double3 other, double t, double epsilon) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        if (!Math.useFma()) return slerp_mulAdd(otherX, otherY, otherZ, t, epsilon);
        double _t7 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = this.x * _t9;
        double _t17 = this.z * _t9;
        double _t20 = this.y * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        Double3 _r = slerp_s4af23da0_tail_fma(otherX, _t12, _t26, _t14, otherY, _t20, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), _t17, epsilon, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon);
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private Double3 slerp_s4af23da0_tail_fma(double otherX, double _t12, double _t26, double _t14, double otherY, double _t20, double _t33, double _t17, double epsilon, double t, double _t8, double _t7) {
        double _t34 = java.lang.Math.fma(otherX, _t12, -(_t26 * _t14));
        double _t35 = java.lang.Math.fma(otherY, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return null;
        double _t24 = t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        return new Double3(java.lang.Math.fma(_t14, _t55, _sp0 * _t41), java.lang.Math.fma(_t20, _t55, _sp0 * _t42), java.lang.Math.fma(_t17, _t55, _sp0 * _t40));
    }

    /** Private tail of {@code slerp}; reached only through it. */
    private Double3 slerp_s4af23da0_tail_mulAdd(double otherX, double _t12, double _t26, double _t14, double otherY, double _t20, double _t33, double _t17, double epsilon, double t, double _t8, double _t7) {
        double _t34 = ((otherX) * (_t12) - (_t26 * _t14));
        double _t35 = ((otherY) * (_t12) - (_t26 * _t20));
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return null;
        double _t24 = t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        return new Double3(((_t14) * (_t55) + (_sp0 * _t41)), ((_t20) * (_t55) + (_sp0 * _t42)), ((_t17) * (_t55) + (_sp0 * _t40)));
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
     * finite length are handled: when their squared lengths leave the {@code double} range, they
     * are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param epsilon the squared sine of the angle between the two vectors at or below which
     *        vectors more than a right angle apart count as opposite, spanning no plane (the
     *        direction then turns through a fixed perpendicular of this vector); the overload
     *        without it uses {@code 2^-94}
     * @return the resulting vector
     */
    public Double3 slerp(double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (!Math.useFma()) return slerp_mulAdd(otherX, otherY, otherZ, t, epsilon);
        double _t7 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = this.x * _t9;
        double _t17 = this.z * _t9;
        double _t20 = this.y * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        Double3 _r = slerp_s4af23da0_tail_fma(otherX, _t12, _t26, _t14, otherY, _t20, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), _t17, epsilon, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 slerp_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _t7 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, epsilon);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = this.x * _t9;
        double _t17 = this.z * _t9;
        double _t20 = this.y * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        Double3 _r = slerp_s4af23da0_tail_mulAdd(otherX, _t12, _t26, _t14, otherY, _t20, ((otherZ) * (_t12) - (_t26 * _t17)), _t17, epsilon, t, _t8, _t7);
        return _r != null ? _r : slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, epsilon);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s4af23da0_tail_fma(double _t36, double _t37, double _t35, double _t33, double _t31, double _t28, double _t9, double _t10, double _t11, double t, double _t48, double _t46, double epsilon, double _t15_inv, double otherX, double otherY, double otherZ) {
        double _t49, _t50, _t52;
        if (_t36 < _t37) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        return slerp_degenerate_s4af23da0_tail2_fma(java.lang.Math.fma(_t73, _t33, _t62), java.lang.Math.fma(_t73, _t35, _t63), java.lang.Math.fma(_t73, _t31, _t61), t, _t53, _t48, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t46, epsilon, _t33, _t15_inv, otherX, _t35, otherY, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s4af23da0_tail_mulAdd(double _t36, double _t37, double _t35, double _t33, double _t31, double _t28, double _t9, double _t10, double _t11, double t, double _t48, double _t46, double epsilon, double _t15_inv, double otherX, double otherY, double otherZ) {
        double _t49, _t50, _t52;
        if (_t36 < _t37) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        return slerp_degenerate_s4af23da0_tail2_mulAdd(((_t73) * (_t33) + (_t62)), ((_t73) * (_t35) + (_t63)), ((_t73) * (_t31) + (_t61)), t, _t53, _t48, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t46, epsilon, _t33, _t15_inv, otherX, _t35, otherY, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s4af23da0_tail2_fma(double _t75, double _t76, double _t74, double t, double _t53, double _t48, double _t69, double _t70, double _t71, double _t46, double epsilon, double _t33, double _t15_inv, double otherX, double _t35, double otherY, double _t31, double otherZ) {
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
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
        return slerp_degenerate_s4af23da0_tail3_fma(_t46, _t48 * Math.sin(_t95), _t53, java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)), epsilon, _t69, _t104, _t48 * Math.cos(_t95), _t33, _t15_inv, t, otherX, _t71, _t106, _t35, otherY, _t70, _t105, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s4af23da0_tail2_mulAdd(double _t75, double _t76, double _t74, double t, double _t53, double _t48, double _t69, double _t70, double _t71, double _t46, double epsilon, double _t33, double _t15_inv, double otherX, double _t35, double otherY, double _t31, double otherZ) {
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
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
        return slerp_degenerate_s4af23da0_tail3_mulAdd(_t46, _t48 * Math.sin(_t95), _t53, ((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))), epsilon, _t69, _t104, _t48 * Math.cos(_t95), _t33, _t15_inv, t, otherX, _t71, _t106, _t35, otherY, _t70, _t105, _t31, otherZ);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s4af23da0_tail3_fma(double _t46, double _t99, double _t53, double _t88, double epsilon, double _t69, double _t104, double _t100, double _t33, double _t15_inv, double t, double otherX, double _t71, double _t106, double _t35, double otherY, double _t70, double _t105, double _t31, double otherZ) {
        double _sfx0, _sfx1, _sfx2;
        if (_t46 > 0.0) {
            if (_t53 < 0.0) {
                if (_t88 <= epsilon) {
                    _sfx0 = java.lang.Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv;
                    _sfx1 = java.lang.Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv;
                    _sfx2 = java.lang.Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv;
                } else {
                    _sfx0 = java.lang.Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                    _sfx1 = java.lang.Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                    _sfx2 = java.lang.Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
                }
            } else {
                _sfx0 = java.lang.Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                _sfx1 = java.lang.Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                _sfx2 = java.lang.Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
            }
        } else {
            _sfx0 = java.lang.Math.fma(t, otherX - this.x, this.x);
            _sfx1 = java.lang.Math.fma(t, otherY - this.y, this.y);
            _sfx2 = java.lang.Math.fma(t, otherZ - this.z, this.z);
        }
        return new Double3(_sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code slerp_degenerate}; reached only through it. */
    private Double3 slerp_degenerate_s4af23da0_tail3_mulAdd(double _t46, double _t99, double _t53, double _t88, double epsilon, double _t69, double _t104, double _t100, double _t33, double _t15_inv, double t, double otherX, double _t71, double _t106, double _t35, double otherY, double _t70, double _t105, double _t31, double otherZ) {
        double _sfx0, _sfx1, _sfx2;
        if (_t46 > 0.0) {
            if (_t53 < 0.0) {
                if (_t88 <= epsilon) {
                    _sfx0 = ((_t99) * (_t69) + (_t100 * _t33)) * _t15_inv;
                    _sfx1 = ((_t99) * (_t71) + (_t100 * _t35)) * _t15_inv;
                    _sfx2 = ((_t99) * (_t70) + (_t100 * _t31)) * _t15_inv;
                } else {
                    _sfx0 = ((_t99) * (_t104) + (_t100 * _t33)) * _t15_inv;
                    _sfx1 = ((_t99) * (_t106) + (_t100 * _t35)) * _t15_inv;
                    _sfx2 = ((_t99) * (_t105) + (_t100 * _t31)) * _t15_inv;
                }
            } else {
                _sfx0 = ((_t99) * (_t104) + (_t100 * _t33)) * _t15_inv;
                _sfx1 = ((_t99) * (_t106) + (_t100 * _t35)) * _t15_inv;
                _sfx2 = ((_t99) * (_t105) + (_t100 * _t31)) * _t15_inv;
            }
        } else {
            _sfx0 = ((t) * (otherX - this.x) + (this.x));
            _sfx1 = ((t) * (otherY - this.y) + (this.y));
            _sfx2 = ((t) * (otherZ - this.z) + (this.z));
        }
        return new Double3(_sfx0, _sfx1, _sfx2);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_fma(double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = this.z * _t2;
        double _t13 = this.x * _t2;
        double _t14 = this.y * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        return slerp_degenerate_s4af23da0_tail_fma(java.lang.Math.abs(_t31), java.lang.Math.abs(_t33), _t29 * _t14, _t33, _t31, (1.0 / java.lang.Math.sqrt(_t24)), _t9, _t10, _t11, t, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t24 * _t25, epsilon, 1.0 / _t15, otherX, otherY, otherZ);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = this.z * _t2;
        double _t13 = this.x * _t2;
        double _t14 = this.y * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        return slerp_degenerate_s4af23da0_tail_mulAdd(java.lang.Math.abs(_t31), java.lang.Math.abs(_t33), _t29 * _t14, _t33, _t31, (1.0 / java.lang.Math.sqrt(_t24)), _t9, _t10, _t11, t, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t24 * _t25, epsilon, 1.0 / _t15, otherX, otherY, otherZ);
    }

    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 absolute() {
        return new Double3(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y), java.lang.Math.abs(this.z));
    }

    /**
     * Compute the arc cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Double3 acos() {
        return new Double3(Math.acos(this.x), Math.acos(this.y), Math.acos(this.z));
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
    public Double3 addScaled(Double3 b, double scalar) {
        double bX = b.x;
        double bY = b.y;
        double bZ = b.z;
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(scalar, bX, this.x), java.lang.Math.fma(scalar, bY, this.y), java.lang.Math.fma(scalar, bZ, this.z));
        } else {
            return new Double3(((scalar) * (bX) + (this.x)), ((scalar) * (bY) + (this.y)), ((scalar) * (bZ) + (this.z)));
        }
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
    public Double3 addScaled(double bX, double bY, double bZ, double scalar) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(scalar, bX, this.x), java.lang.Math.fma(scalar, bY, this.y), java.lang.Math.fma(scalar, bZ, this.z));
        } else {
            return new Double3(((scalar) * (bX) + (this.x)), ((scalar) * (bY) + (this.y)), ((scalar) * (bZ) + (this.z)));
        }
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
    public Double3 addScaled(Double3 b, Double3 c) {
        double bX = b.x;
        double bY = b.y;
        double bZ = b.z;
        double cX = c.x;
        double cY = c.y;
        double cZ = c.z;
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(bX, cX, this.x), java.lang.Math.fma(bY, cY, this.y), java.lang.Math.fma(bZ, cZ, this.z));
        } else {
            return new Double3(((bX) * (cX) + (this.x)), ((bY) * (cY) + (this.y)), ((bZ) * (cZ) + (this.z)));
        }
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
    public Double3 addScaled(double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(bX, cX, this.x), java.lang.Math.fma(bY, cY, this.y), java.lang.Math.fma(bZ, cZ, this.z));
        } else {
            return new Double3(((bX) * (cX) + (this.x)), ((bY) * (cY) + (this.y)), ((bZ) * (cZ) + (this.z)));
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
     * Valid input: this vector may have components of any finite magnitude; {@code other} may have
     * components of any finite magnitude.
     *
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}
     */
    public double angleBetween(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        if (Math.useFma()) {
            double _t6 = java.lang.Math.fma(otherZ, this.y, otherY * -this.z);
            double _t7 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _t8 = java.lang.Math.fma(otherZ, this.x, otherX * -this.z);
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
     * Valid input: this vector may have components of any finite magnitude;
     * {@code (otherX, otherY, otherZ)} may have components of any finite magnitude.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the angle in radians between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public double angleBetween(double otherX, double otherY, double otherZ) {
        if (Math.useFma()) {
            double _t6 = java.lang.Math.fma(otherZ, this.y, otherY * -this.z);
            double _t7 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _t8 = java.lang.Math.fma(otherZ, this.x, otherX * -this.z);
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
        double _t20 = java.lang.Math.fma(_t8, _t9, _t10 * -_t11);
        double _t21 = java.lang.Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = java.lang.Math.fma(_t8, _t12, _t13 * -_t11);
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
     * Compute the arc sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return the resulting vector
     */
    public Double3 asin() {
        return new Double3(Math.asin(this.x), Math.asin(this.y), Math.asin(this.z));
    }

    /**
     * Compute the arc tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 atan() {
        return new Double3(Math.atan(this.x), Math.atan(this.y), Math.atan(this.z));
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
    public Double3 atan2(double x) {
        return new Double3(Math.atan2(this.x, x), Math.atan2(this.y, x), Math.atan2(this.z, x));
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
    public Double3 atan2(Double3 x) {
        double xX = x.x;
        double xY = x.y;
        double xZ = x.z;
        return new Double3(Math.atan2(this.x, xX), Math.atan2(this.y, xY), Math.atan2(this.z, xZ));
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
    public Double3 atan2(double xX, double xY, double xZ) {
        return new Double3(Math.atan2(this.x, xX), Math.atan2(this.y, xY), Math.atan2(this.z, xZ));
    }

    /**
     * Compute the cube root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 cbrt() {
        return new Double3(Math.cbrt(this.x), Math.cbrt(this.y), Math.cbrt(this.z));
    }

    /**
     * Compute the ceiling of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 ceil() {
        return new Double3(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z));
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
    public Double3 clamp(double min, double max) {
        return new Double3(Math.min(Math.max(this.x, min), max), Math.min(Math.max(this.y, min), max), Math.min(Math.max(this.z, min), max));
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
    public Double3 clamp(Double3 min, Double3 max) {
        double minX = min.x;
        double minY = min.y;
        double minZ = min.z;
        double maxX = max.x;
        double maxY = max.y;
        double maxZ = max.z;
        return new Double3(Math.min(Math.max(this.x, minX), maxX), Math.min(Math.max(this.y, minY), maxY), Math.min(Math.max(this.z, minZ), maxZ));
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
    public Double3 clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new Double3(Math.min(Math.max(this.x, minX), maxX), Math.min(Math.max(this.y, minY), maxY), Math.min(Math.max(this.z, minZ), maxZ));
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
    public Double3 closestPointOnLine(Double3 lineStart, Double3 lineEnd) {
        return closestPointOnLine(lineStart.x, lineStart.y, lineStart.z, lineEnd.x, lineEnd.y, lineEnd.z);
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
    public Double3 closestPointOnLine(double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (Math.useFma()) {
            double _t0 = lineEndZ - lineStartZ;
            double _t1 = lineEndX - lineStartX;
            double _t2 = lineEndY - lineStartY;
            double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
            double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, this.z - lineStartZ, java.lang.Math.fma(_t1, this.x - lineStartX, _t2 * (this.y - lineStartY))) / _t10));
            if (_t10 > 0.0) {
                return new Double3(java.lang.Math.fma(_t1, _t14, lineStartX), java.lang.Math.fma(_t2, _t14, lineStartY), java.lang.Math.fma(_t0, _t14, lineStartZ));
            } else {
                return new Double3(lineStartX, lineStartY, lineStartZ);
            }
        } else {
            double _t0 = lineEndZ - lineStartZ;
            double _t1 = lineEndX - lineStartX;
            double _t2 = lineEndY - lineStartY;
            double _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
            double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (this.z - lineStartZ) + (((_t1) * (this.x - lineStartX) + (_t2 * (this.y - lineStartY))))) / _t10));
            if (_t10 > 0.0) {
                return new Double3(((_t1) * (_t14) + (lineStartX)), ((_t2) * (_t14) + (lineStartY)), ((_t0) * (_t14) + (lineStartZ)));
            } else {
                return new Double3(lineStartX, lineStartY, lineStartZ);
            }
        }
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
        return Math.max(Math.max(this.x, this.y), this.z);
    }

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public double compMin() {
        return Math.min(Math.min(this.x, this.y), this.z);
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
     * Copy the sign of {@code sign} onto each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @return the resulting vector
     */
    public Double3 copySign(double sign) {
        return new Double3(Math.copySign(this.x, sign), Math.copySign(this.y, sign), Math.copySign(this.z, sign));
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
    public Double3 copySign(Double3 sign) {
        double signX = sign.x;
        double signY = sign.y;
        double signZ = sign.z;
        return new Double3(Math.copySign(this.x, signX), Math.copySign(this.y, signY), Math.copySign(this.z, signZ));
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
    public Double3 copySign(double signX, double signY, double signZ) {
        return new Double3(Math.copySign(this.x, signX), Math.copySign(this.y, signY), Math.copySign(this.z, signZ));
    }

    /**
     * Compute the cosine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 cos() {
        return new Double3(Math.cos(this.x), Math.cos(this.y), Math.cos(this.z));
    }

    /**
     * Compute the hyperbolic cosine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 cosh() {
        return new Double3(Math.cosh(this.x), Math.cosh(this.y), Math.cosh(this.z));
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
    public Double3 cross(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(otherZ, this.y, -(otherY * this.z)), java.lang.Math.fma(otherX, this.z, -(otherZ * this.x)), java.lang.Math.fma(otherY, this.x, -(otherX * this.y)));
        } else {
            return new Double3(((otherZ) * (this.y) - (otherY * this.z)), ((otherX) * (this.z) - (otherZ * this.x)), ((otherY) * (this.x) - (otherX * this.y)));
        }
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
    public Double3 cross(double otherX, double otherY, double otherZ) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(otherZ, this.y, -(otherY * this.z)), java.lang.Math.fma(otherX, this.z, -(otherZ * this.x)), java.lang.Math.fma(otherY, this.x, -(otherX * this.y)));
        } else {
            return new Double3(((otherZ) * (this.y) - (otherY * this.z)), ((otherX) * (this.z) - (otherZ * this.x)), ((otherY) * (this.x) - (otherX * this.y)));
        }
    }

    /**
     * Compute the value converted from radians to degrees of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 degrees() {
        return new Double3(Math.toDegrees(this.x), Math.toDegrees(this.y), Math.toDegrees(this.z));
    }

    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    public double distance(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
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
    public double distanceSquared(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
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
    public double dot(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
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
     * Compute the base-e exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 exp() {
        return new Double3(Math.exp(this.x), Math.exp(this.y), Math.exp(this.z));
    }

    /**
     * Compute the base-2 exponential of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 exp2() {
        return new Double3(Math.pow(2.0, this.x), Math.pow(2.0, this.y), Math.pow(2.0, this.z));
    }

    /**
     * Compute the base-e exponential minus one of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 expm1() {
        return new Double3(Math.expm1(this.x), Math.expm1(this.y), Math.expm1(this.z));
    }

    /**
     * Orient this vector against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} (kept when {@code dot(Nref, I)} is negative, negated otherwise), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @return the resulting vector
     */
    public Double3 faceforward(Double3 I, Double3 Nref) {
        double IX = I.x;
        double IY = I.y;
        double IZ = I.z;
        double NrefX = Nref.x;
        double NrefY = Nref.y;
        double NrefZ = Nref.z;
        if (Math.useFma()) {
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
        } else {
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
        }
    }

    /**
     * Orient this vector against the incident direction ({@code IX}, {@code IY}, {@code IZ}) as
     * judged by the reference vector ({@code NrefX}, {@code NrefY}, {@code NrefZ}) (kept when
     * {@code dot((NrefX, NrefY, NrefZ), (IX, IY, IZ))} is negative, negated otherwise), returning
     * the result as a value.
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
    public Double3 faceforward(double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        if (Math.useFma()) {
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
        } else {
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
        }
    }

    /**
     * Compute the floor of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 floor() {
        return new Double3(Math.floor(this.x), Math.floor(this.y), Math.floor(this.z));
    }

    /**
     * Compute the fractional part of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 fract() {
        return new Double3(Math.min(this.x - Math.floor(this.x), 0.9999999999999999), Math.min(this.y - Math.floor(this.y), 0.9999999999999999), Math.min(this.z - Math.floor(this.z), 0.9999999999999999));
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
    public Double3 hypot(double y) {
        return new Double3(Math.hypot(this.x, y), Math.hypot(this.y, y), Math.hypot(this.z, y));
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
    public Double3 hypot(Double3 y) {
        double yX = y.x;
        double yY = y.y;
        double yZ = y.z;
        return new Double3(Math.hypot(this.x, yX), Math.hypot(this.y, yY), Math.hypot(this.z, yZ));
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
    public Double3 hypot(double yX, double yY, double yZ) {
        return new Double3(Math.hypot(this.x, yX), Math.hypot(this.y, yY), Math.hypot(this.z, yZ));
    }

    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector, returning the result
     * as a value.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @return the resulting vector
     */
    public Double3 inverse() {
        return new Double3(1.0 / this.x, 1.0 / this.y, 1.0 / this.z);
    }

    /**
     * Compute the inverse square root of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double3 inverseSqrt() {
        return new Double3((1.0 / java.lang.Math.sqrt(this.x)), (1.0 / java.lang.Math.sqrt(this.y)), (1.0 / java.lang.Math.sqrt(this.z)));
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
     * Compute the natural logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double3 log() {
        return new Double3(Math.log(this.x), Math.log(this.y), Math.log(this.z));
    }

    /**
     * Compute the base-10 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double3 log10() {
        return new Double3(Math.log10(this.x), Math.log10(this.y), Math.log10(this.z));
    }

    /**
     * Compute the natural logarithm of one plus the value of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @return the resulting vector
     */
    public Double3 log1p() {
        return new Double3(Math.log1p(this.x), Math.log1p(this.y), Math.log1p(this.z));
    }

    /**
     * Compute the base-2 logarithm of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return the resulting vector
     */
    public Double3 log2() {
        return new Double3(Math.log2(this.x), Math.log2(this.y), Math.log2(this.z));
    }

    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public double manhattanDistance(Double3 other) {
        return java.lang.Math.abs(this.x - other.x) + java.lang.Math.abs(this.y - other.y) + java.lang.Math.abs(this.z - other.z);
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
     * Compute the larger of each component of this vector and {@code scalar}, returning the result
     * as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return the resulting vector
     */
    public Double3 max(double scalar) {
        return new Double3(Math.max(this.x, scalar), Math.max(this.y, scalar), Math.max(this.z, scalar));
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @return the resulting vector
     */
    public Double3 max(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        return new Double3(Math.max(this.x, otherX), Math.max(this.y, otherY), Math.max(this.z, otherZ));
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Double3 max(double otherX, double otherY, double otherZ) {
        return new Double3(Math.max(this.x, otherX), Math.max(this.y, otherY), Math.max(this.z, otherZ));
    }

    /**
     * Compute the smaller of each component of this vector and {@code scalar}, returning the result
     * as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return the resulting vector
     */
    public Double3 min(double scalar) {
        return new Double3(Math.min(this.x, scalar), Math.min(this.y, scalar), Math.min(this.z, scalar));
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @return the resulting vector
     */
    public Double3 min(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        return new Double3(Math.min(this.x, otherX), Math.min(this.y, otherY), Math.min(this.z, otherZ));
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Double3 min(double otherX, double otherY, double otherZ) {
        return new Double3(Math.min(this.x, otherX), Math.min(this.y, otherY), Math.min(this.z, otherZ));
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} (for a
     * component {@code a} and its divisor {@code b}: {@code a % b}, plus {@code b} when that
     * remainder is non-zero and its sign differs from {@code b}'s - exactly Kotlin's {@code mod}),
     * returning the result as a value.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: {@code y} must be non-zero.
     *
     * @param y the divisor
     * @return the resulting vector
     */
    public Double3 mod(double y) {
        return new Double3(flooredMod(this.x, y), flooredMod(this.y, y), flooredMod(this.z, y));
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} (for a
     * component {@code a} and its divisor {@code b}: {@code a % b}, plus {@code b} when that
     * remainder is non-zero and its sign differs from {@code b}'s - exactly Kotlin's {@code mod}),
     * returning the result as a value.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @return the resulting vector
     */
    public Double3 mod(Double3 y) {
        double yX = y.x;
        double yY = y.y;
        double yZ = y.z;
        return new Double3(flooredMod(this.x, yX), flooredMod(this.y, yY), flooredMod(this.z, yZ));
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}) (for a component {@code a} and its divisor {@code b}: {@code a % b}, plus
     * {@code b} when that remainder is non-zero and its sign differs from {@code b}'s - exactly
     * Kotlin's {@code mod}), returning the result as a value.
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
    public Double3 mod(double yX, double yY, double yZ) {
        return new Double3(flooredMod(this.x, yX), flooredMod(this.y, yY), flooredMod(this.z, yZ));
    }

    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 nextDown() {
        return new Double3(Math.nextDown(this.x), Math.nextDown(this.y), Math.nextDown(this.z));
    }

    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 nextUp() {
        return new Double3(Math.nextUp(this.x), Math.nextUp(this.y), Math.nextUp(this.z));
    }

    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector), returning the
     * result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 normalize() {
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
            } else {
                return Double3.ZERO;
            }
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
    public Double3 normalizeMul(double length) {
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                return new Double3(this.x * _t4, this.y * _t4, this.z * _t4);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                return new Double3(this.x * _t4, this.y * _t4, this.z * _t4);
            } else {
                return Double3.ZERO;
            }
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
     * Valid input: {@code normal} must be non-zero; this vector may have components of any finite
     * magnitude; {@code other} may have components of any finite magnitude; {@code normal} may have
     * components of any finite magnitude.
     *
     * @param other the vector to measure the signed angle to
     * @param normal the reference axis that defines the sign of the angle
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise as seen from the
     *        direction of the given normal
     */
    public double orientedAngle(Double3 other, Double3 normal) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double normalX = normal.x;
        double normalY = normal.y;
        double normalZ = normal.z;
        if (!Math.useFma()) return orientedAngle_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
        double _t10 = java.lang.Math.fma(otherX, this.z, -(otherZ * this.x));
        double _t11 = java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _ct0 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_fma(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        return java.lang.Math.fma(_t9, normalZ * _t7, java.lang.Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? -_t18 : _t18;
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
     * Valid input: {@code (normalX, normalY, normalZ)} must be non-zero; this vector may have
     * components of any finite magnitude; {@code (otherX, otherY, otherZ)} may have components of
     * any finite magnitude; {@code (normalX, normalY, normalZ)} may have components of any finite
     * magnitude.
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
        if (!Math.useFma()) return orientedAngle_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
        double _t10 = java.lang.Math.fma(otherX, this.z, -(otherZ * this.x));
        double _t11 = java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _ct0 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_fma(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        return java.lang.Math.fma(_t9, normalZ * _t7, java.lang.Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? -_t18 : _t18;
    }

    /** {@code orientedAngle} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double orientedAngle_mulAdd(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = ((otherY) * (this.x) - (otherX * this.y));
        double _t10 = ((otherX) * (this.z) - (otherZ * this.x));
        double _t11 = ((otherZ) * (this.y) - (otherY * this.z));
        double _ct0 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))));
        return ((_t9) * (normalZ * _t7) + (((_t10) * (normalY * _t7) + (_t11 * (normalX * _t7))))) < 0.0 ? -_t18 : _t18;
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
     * Compute the outer product of this vector and {@code row}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @return the resulting matrix
     */
    public Double3x3 outerProduct(Double3 row) {
        double rowX = row.x;
        double rowY = row.y;
        double rowZ = row.z;
        return new Double3x3(rowX * this.x, rowY * this.x, rowZ * this.x, rowX * this.y, rowY * this.y, rowZ * this.y, rowX * this.z, rowY * this.z, rowZ * this.z, 0);
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
    public Double3x3 outerProduct(double rowX, double rowY, double rowZ) {
        return new Double3x3(rowX * this.x, rowY * this.x, rowZ * this.x, rowX * this.y, rowY * this.y, rowZ * this.y, rowX * this.z, rowY * this.z, rowZ * this.z, 0);
    }

    /**
     * Compute a vector perpendicular to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 perpendicular() {
        if (java.lang.Math.abs(this.z) < java.lang.Math.abs(this.x)) {
            return new Double3(this.y, -this.x, 0.0);
        } else {
            return new Double3(0.0, this.z, -this.y);
        }
    }

    /**
     * Raise each component of this vector to the power of {@code exponent}, returning the result as
     * a value.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @return the resulting vector
     */
    public Double3 pow(double exponent) {
        return new Double3(Math.pow(this.x, exponent), Math.pow(this.y, exponent), Math.pow(this.z, exponent));
    }

    /**
     * Raise each component of this vector to the power of {@code exponent}, returning the result as
     * a value.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @return the resulting vector
     */
    public Double3 pow(Double3 exponent) {
        double exponentX = exponent.x;
        double exponentY = exponent.y;
        double exponentZ = exponent.z;
        return new Double3(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY), Math.pow(this.z, exponentZ));
    }

    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}), returning the result as a value.
     * <p>
     * A zero component raised to a negative exponent gives an infinite result, as {@code Math.pow}
     * does.
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
    public Double3 pow(double exponentX, double exponentY, double exponentZ) {
        return new Double3(Math.pow(this.x, exponentX), Math.pow(this.y, exponentY), Math.pow(this.z, exponentZ));
    }

    /**
     * Project this vector onto {@code onto}, returning the result as a value.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @return the resulting vector
     */
    public Double3 project(Double3 onto) {
        double ontoX = onto.x;
        double ontoY = onto.y;
        double ontoZ = onto.z;
        if (Math.useFma()) {
            double _t7 = java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y)) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            return new Double3(ontoX * _t7, ontoY * _t7, ontoZ * _t7);
        } else {
            double _t7 = ((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            return new Double3(ontoX * _t7, ontoY * _t7, ontoZ * _t7);
        }
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
    public Double3 project(double ontoX, double ontoY, double ontoZ) {
        if (Math.useFma()) {
            double _t7 = java.lang.Math.fma(ontoZ, this.z, java.lang.Math.fma(ontoX, this.x, ontoY * this.y)) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            return new Double3(ontoX * _t7, ontoY * _t7, ontoZ * _t7);
        } else {
            double _t7 = ((ontoZ) * (this.z) + (((ontoX) * (this.x) + (ontoY * this.y)))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            return new Double3(ontoX * _t7, ontoY * _t7, ontoZ * _t7);
        }
    }

    /**
     * Project this vector onto the plane with the given normal, returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @return the resulting vector
     */
    public Double3 projectOnPlane(Double3 normal) {
        double normalX = normal.x;
        double normalY = normal.y;
        double normalZ = normal.z;
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            return new Double3(java.lang.Math.fma(-normalX, _t2, this.x), java.lang.Math.fma(-normalY, _t2, this.y), java.lang.Math.fma(-normalZ, _t2, this.z));
        } else {
            double _t2 = ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            return new Double3(((this.x) - (normalX) * (_t2)), ((this.y) - (normalY) * (_t2)), ((this.z) - (normalZ) * (_t2)));
        }
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
    public Double3 projectOnPlane(double normalX, double normalY, double normalZ) {
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            return new Double3(java.lang.Math.fma(-normalX, _t2, this.x), java.lang.Math.fma(-normalY, _t2, this.y), java.lang.Math.fma(-normalZ, _t2, this.z));
        } else {
            double _t2 = ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            return new Double3(((this.x) - (normalX) * (_t2)), ((this.y) - (normalY) * (_t2)), ((this.z) - (normalZ) * (_t2)));
        }
    }

    /**
     * Compute the value converted from degrees to radians of each component of this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 radians() {
        return new Double3(Math.toRadians(this.x), Math.toRadians(this.y), Math.toRadians(this.z));
    }

    /**
     * Reflect this vector off the plane with the given normal (its component along the normal is
     * negated), returning the result as a value.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @return the resulting vector
     */
    public Double3 reflect(Double3 normal) {
        double normalX = normal.x;
        double normalY = normal.y;
        double normalZ = normal.z;
        if (Math.useFma()) {
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            return new Double3(java.lang.Math.fma(-normalX, _t3, this.x), java.lang.Math.fma(-normalY, _t3, this.y), java.lang.Math.fma(-normalZ, _t3, this.z));
        } else {
            double _t3 = 2.0 * ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            return new Double3(((this.x) - (normalX) * (_t3)), ((this.y) - (normalY) * (_t3)), ((this.z) - (normalZ) * (_t3)));
        }
    }

    /**
     * Reflect this vector off the plane with the given normal (its component along the normal is
     * negated), returning the result as a value.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length.
     *
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @return the resulting vector
     */
    public Double3 reflect(double normalX, double normalY, double normalZ) {
        if (Math.useFma()) {
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            return new Double3(java.lang.Math.fma(-normalX, _t3, this.x), java.lang.Math.fma(-normalY, _t3, this.y), java.lang.Math.fma(-normalZ, _t3, this.z));
        } else {
            double _t3 = 2.0 * ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            return new Double3(((this.x) - (normalX) * (_t3)), ((this.y) - (normalY) * (_t3)), ((this.z) - (normalZ) * (_t3)));
        }
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), returning
     * the result as a value.
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
     * @return the resulting vector
     */
    public Double3 refract(Double3 normal, double eta) {
        double normalX = normal.x;
        double normalY = normal.y;
        double normalZ = normal.z;
        if (Math.useFma()) {
            double _t3 = java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
            double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
            if (_t7 >= 0.0) {
                return new Double3(java.lang.Math.fma(eta, this.x, -(normalX * _t10)), java.lang.Math.fma(eta, this.y, -(normalY * _t10)), java.lang.Math.fma(eta, this.z, -(normalZ * _t10)));
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t3 = ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
            double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
            if (_t7 >= 0.0) {
                return new Double3(((eta) * (this.x) - (normalX * _t10)), ((eta) * (this.y) - (normalY * _t10)), ((eta) * (this.z) - (normalZ * _t10)));
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), returning
     * the result as a value.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it is mirrored as {@code reflect} mirrors it instead of passing straight through. Negate the
     * normal for a vector leaving through the surface.
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
    public Double3 refract(double normalX, double normalY, double normalZ, double eta) {
        if (Math.useFma()) {
            double _t3 = java.lang.Math.fma(normalZ, this.z, java.lang.Math.fma(normalX, this.x, normalY * this.y));
            double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
            double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
            if (_t7 >= 0.0) {
                return new Double3(java.lang.Math.fma(eta, this.x, -(normalX * _t10)), java.lang.Math.fma(eta, this.y, -(normalY * _t10)), java.lang.Math.fma(eta, this.z, -(normalZ * _t10)));
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t3 = ((normalZ) * (this.z) + (((normalX) * (this.x) + (normalY * this.y))));
            double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
            double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
            if (_t7 >= 0.0) {
                return new Double3(((eta) * (this.x) - (normalX * _t10)), ((eta) * (this.y) - (normalY * _t10)), ((eta) * (this.z) - (normalZ * _t10)));
            } else {
                return Double3.ZERO;
            }
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
    public Double3 round() {
        return new Double3(Math.rint(this.x), Math.rint(this.y), Math.rint(this.z));
    }

    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 sign() {
        return new Double3(Math.signum(this.x), Math.signum(this.y), Math.signum(this.z));
    }

    /**
     * Compute the sine of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 sin() {
        return new Double3(Math.sin(this.x), Math.sin(this.y), Math.sin(this.z));
    }

    /**
     * Compute the hyperbolic sine of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 sinh() {
        return new Double3(Math.sinh(this.x), Math.sinh(this.y), Math.sinh(this.z));
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * {@code edge0} to 1 at {@code edge1}: 0 at or beyond {@code edge0} on the side away from
     * {@code edge1}, 1 at or beyond {@code edge1} (a falling step when {@code edge0} is the
     * greater), returning the result as a value.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the edge the step is 0 at
     * @param edge1 the edge the step is 1 at
     * @return the resulting vector
     */
    public Double3 smoothstep(double edge0, double edge1) {
        if (Math.useFma()) {
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t10 = Math.max(0.0, Math.min(1.0, (this.x - edge0) * _t0_inv));
            double _t11 = Math.max(0.0, Math.min(1.0, (this.y - edge0) * _t0_inv));
            double _t12 = Math.max(0.0, Math.min(1.0, (this.z - edge0) * _t0_inv));
            return new Double3(java.lang.Math.fma(-2.0, _t10, 3.0) * _t10 * _t10, java.lang.Math.fma(-2.0, _t11, 3.0) * _t11 * _t11, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        } else {
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t10 = Math.max(0.0, Math.min(1.0, (this.x - edge0) * _t0_inv));
            double _t11 = Math.max(0.0, Math.min(1.0, (this.y - edge0) * _t0_inv));
            double _t12 = Math.max(0.0, Math.min(1.0, (this.z - edge0) * _t0_inv));
            return new Double3(((3.0) - (2.0) * (_t10)) * _t10 * _t10, ((3.0) - (2.0) * (_t11)) * _t11 * _t11, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        }
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * {@code edge0} to 1 at {@code edge1}: 0 at or beyond {@code edge0} on the side away from
     * {@code edge1}, 1 at or beyond {@code edge1} (a falling step when {@code edge0} is the
     * greater), returning the result as a value.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the edge the step is 0 at
     * @param edge1 the edge the step is 1 at
     * @return the resulting vector
     */
    public Double3 smoothstep(Double3 edge0, Double3 edge1) {
        double edge0X = edge0.x;
        double edge0Y = edge0.y;
        double edge0Z = edge0.z;
        double edge1X = edge1.x;
        double edge1Y = edge1.y;
        double edge1Z = edge1.z;
        if (Math.useFma()) {
            double _t12 = Math.max(0.0, Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t13 = Math.max(0.0, Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = Math.max(0.0, Math.min(1.0, (this.z - edge0Z) / (edge1Z - edge0Z)));
            return new Double3(java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        } else {
            double _t12 = Math.max(0.0, Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t13 = Math.max(0.0, Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = Math.max(0.0, Math.min(1.0, (this.z - edge0Z) / (edge1Z - edge0Z)));
            return new Double3(((3.0) - (2.0) * (_t12)) * _t12 * _t12, ((3.0) - (2.0) * (_t13)) * _t13 * _t13, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        }
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * ({@code edge0X}, {@code edge0Y}, {@code edge0Z}) to 1 at ({@code edge1X}, {@code edge1Y},
     * {@code edge1Z}): 0 at or beyond ({@code edge0X}, {@code edge0Y}, {@code edge0Z}) on the side
     * away from ({@code edge1X}, {@code edge1Y}, {@code edge1Z}), 1 at or beyond ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}) (a falling step when ({@code edge0X}, {@code edge0Y},
     * {@code edge0Z}) is the greater), returning the result as a value.
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
    public Double3 smoothstep(double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (Math.useFma()) {
            double _t12 = Math.max(0.0, Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t13 = Math.max(0.0, Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = Math.max(0.0, Math.min(1.0, (this.z - edge0Z) / (edge1Z - edge0Z)));
            return new Double3(java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        } else {
            double _t12 = Math.max(0.0, Math.min(1.0, (this.x - edge0X) / (edge1X - edge0X)));
            double _t13 = Math.max(0.0, Math.min(1.0, (this.y - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = Math.max(0.0, Math.min(1.0, (this.z - edge0Z) / (edge1Z - edge0Z)));
            return new Double3(((3.0) - (2.0) * (_t12)) * _t12 * _t12, ((3.0) - (2.0) * (_t13)) * _t13 * _t13, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        }
    }

    /**
     * Compute the square root of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @return the resulting vector
     */
    public Double3 sqrt() {
        return new Double3(java.lang.Math.sqrt(this.x), java.lang.Math.sqrt(this.y), java.lang.Math.sqrt(this.z));
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than {@code edge}
     * and {@code 1} otherwise, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @return the resulting vector
     */
    public Double3 step(double edge) {
        return new Double3(this.x < edge ? 0.0 : 1.0, this.y < edge ? 0.0 : 1.0, this.z < edge ? 0.0 : 1.0);
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than the
     * corresponding component of the given edge and {@code 1} otherwise, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @return the resulting vector
     */
    public Double3 step(Double3 edge) {
        double edgeX = edge.x;
        double edgeY = edge.y;
        double edgeZ = edge.z;
        return new Double3(this.x < edgeX ? 0.0 : 1.0, this.y < edgeY ? 0.0 : 1.0, this.z < edgeZ ? 0.0 : 1.0);
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than the
     * corresponding component of the given edge and {@code 1} otherwise, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @return the resulting vector
     */
    public Double3 step(double edgeX, double edgeY, double edgeZ) {
        return new Double3(this.x < edgeX ? 0.0 : 1.0, this.y < edgeY ? 0.0 : 1.0, this.z < edgeZ ? 0.0 : 1.0);
    }

    /**
     * Compute the tangent of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 tan() {
        return new Double3(Math.tan(this.x), Math.tan(this.y), Math.tan(this.z));
    }

    /**
     * Compute the hyperbolic tangent of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 tanh() {
        return new Double3(Math.tanh(this.x), Math.tanh(this.y), Math.tanh(this.z));
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize((p1 - this) x (p2 - this))} - it points to the side from which the vertices
     * {@code this}, {@code p1}, {@code p2} appear counter-clockwise (a degenerate triangle yields
     * the zero vector), returning the result as a value.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude; {@code p1} may have
     * components of any finite magnitude; {@code p2} may have components of any finite magnitude.
     *
     * @param p1 the second vertex of the triangle (this vector is the first)
     * @param p2 the third vertex of the triangle
     * @return the resulting vector
     */
    public Double3 triangleNormal(Double3 p1, Double3 p2) {
        double p1X = p1.x;
        double p1Y = p1.y;
        double p1Z = p1.z;
        double p2X = p2.x;
        double p2Y = p2.y;
        double p2Z = p2.z;
        if (!Math.useFma()) return triangleNormal_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = java.lang.Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = java.lang.Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return triangleNormal_degenerate_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        return new Double3(_t12 * _t19, _t14 * _t19, _t13 * _t19);
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize(((p1X, p1Y, p1Z) - this) x ((p2X, p2Y, p2Z) - this))} - it points to the
     * side from which the vertices {@code this}, ({@code p1X}, {@code p1Y}, {@code p1Z}),
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) appear counter-clockwise (a degenerate triangle
     * yields the zero vector), returning the result as a value.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude; {@code (p1X, p1Y, p1Z)}
     * may have components of any finite magnitude; {@code (p2X, p2Y, p2Z)} may have components of
     * any finite magnitude.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @return the resulting vector
     */
    public Double3 triangleNormal(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (!Math.useFma()) return triangleNormal_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = java.lang.Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = java.lang.Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return triangleNormal_degenerate_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        return new Double3(_t12 * _t19, _t14 * _t19, _t13 * _t19);
    }

    /** {@code triangleNormal} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 triangleNormal_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = ((_t0) * (_t1) - (_t2 * _t3));
        double _t13 = ((_t4) * (_t3) - (_t0 * _t5));
        double _t14 = ((_t2) * (_t5) - (_t4 * _t1));
        double _ct0 = ((_t13) * (_t13) + (((_t12) * (_t12) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return triangleNormal_degenerate_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        return new Double3(_t12 * _t19, _t14 * _t19, _t13 * _t19);
    }

    /** Private tail of {@code triangleNormal_degenerate}; reached only through it. */
    private Double3 triangleNormal_degenerate_s5566d617_tail_fma(double _t38, double _t44, double _t41, double _t45, double _t39, double _t42, double _t43, double _t40) {
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
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
        double _t77 = (1.0 / java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0) {
            return new Double3(_t77 * _t72, _t77 * _t73, _t77 * _t71);
        } else {
            return Double3.ZERO;
        }
    }

    /** Private tail of {@code triangleNormal_degenerate}; reached only through it. */
    private Double3 triangleNormal_degenerate_s5566d617_tail_mulAdd(double _t38, double _t44, double _t41, double _t45, double _t39, double _t42, double _t43, double _t40) {
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
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
        double _t77 = (1.0 / java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0) {
            return new Double3(_t77 * _t72, _t77 * _t73, _t77 * _t71);
        } else {
            return Double3.ZERO;
        }
    }

    /**
     * Out-of-range path of {@code triangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 triangleNormal_degenerate_fma(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(this.z), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(this.y))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = this.x * _t19;
        double _t32 = this.y * _t19;
        double _t34 = this.z * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        return triangleNormal_degenerate_s5566d617_tail_fma(_t38, unitScale(_t38, _t39, _t40), _t41, unitScale(_t42, _t41, _t43), _t39, _t42, _t43, _t40);
    }

    /**
     * Out-of-range path of {@code triangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 triangleNormal_degenerate_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(this.z), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(this.y))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = this.x * _t19;
        double _t32 = this.y * _t19;
        double _t34 = this.z * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        return triangleNormal_degenerate_s5566d617_tail_mulAdd(_t38, unitScale(_t38, _t39, _t40), _t41, unitScale(_t42, _t41, _t43), _t39, _t42, _t43, _t40);
    }

    /**
     * Compute the truncated value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 trunc() {
        return new Double3(this.x >= 0.0 ? Math.floor(this.x) : Math.ceil(this.x), this.y >= 0.0 ? Math.floor(this.y) : Math.ceil(this.y), this.z >= 0.0 ? Math.floor(this.z) : Math.ceil(this.z));
    }

    /**
     * Compute the angle in radians between this vector and {@code other}, unless this vector or
     * {@code other} has zero length.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude; {@code other} may have
     * components of any finite magnitude.
     *
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}, or an empty
     *        {@code OptionalDouble} when this vector or {@code other} has zero length
     */
    public java.util.OptionalDouble tryAngleBetween(Double3 other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        if (Math.useFma()) {
            double _t6 = java.lang.Math.fma(otherZ, this.y, otherY * -this.z);
            double _t7 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _t8 = java.lang.Math.fma(otherZ, this.x, otherX * -this.z);
            double _ct0 = java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryAngleBetween_degenerate_fma(otherX, otherY, otherZ);
            return java.util.OptionalDouble.of(Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))));
        } else {
            double _t6 = ((otherZ) * (this.y) - (otherY * this.z));
            double _t7 = ((otherY) * (this.x) - (otherX * this.y));
            double _t8 = ((otherZ) * (this.x) - (otherX * this.z));
            double _ct0 = ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryAngleBetween_degenerate_mulAdd(otherX, otherY, otherZ);
            return java.util.OptionalDouble.of(Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
        }
    }

    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), unless this vector or ({@code otherX}, {@code otherY}, {@code otherZ}) has
     * zero length.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude;
     * {@code (otherX, otherY, otherZ)} may have components of any finite magnitude.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the angle in radians between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ}), or an empty {@code OptionalDouble} when this vector or
     *        ({@code otherX}, {@code otherY}, {@code otherZ}) has zero length
     */
    public java.util.OptionalDouble tryAngleBetween(double otherX, double otherY, double otherZ) {
        if (Math.useFma()) {
            double _t6 = java.lang.Math.fma(otherZ, this.y, otherY * -this.z);
            double _t7 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
            double _t8 = java.lang.Math.fma(otherZ, this.x, otherX * -this.z);
            double _ct0 = java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryAngleBetween_degenerate_fma(otherX, otherY, otherZ);
            return java.util.OptionalDouble.of(Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))));
        } else {
            double _t6 = ((otherZ) * (this.y) - (otherY * this.z));
            double _t7 = ((otherY) * (this.x) - (otherX * this.y));
            double _t8 = ((otherZ) * (this.x) - (otherX * this.z));
            double _ct0 = ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
            if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryAngleBetween_degenerate_mulAdd(otherX, otherY, otherZ);
            return java.util.OptionalDouble.of(Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
        }
    }

    /**
     * Out-of-range path of {@code tryAngleBetween}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private java.util.OptionalDouble tryAngleBetween_degenerate_fma(double otherX, double otherY, double otherZ) {
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(this.x, this.y, this.z);
        double _t8 = otherZ * _t0;
        double _t9 = this.y * _t1;
        double _t10 = otherY * _t0;
        double _t11 = this.z * _t1;
        double _t12 = this.x * _t1;
        double _t13 = otherX * _t0;
        double _t20 = java.lang.Math.fma(_t8, _t9, _t10 * -_t11);
        double _t21 = java.lang.Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = java.lang.Math.fma(_t8, _t12, _t13 * -_t11);
        double _t23 = unitScale(_t21, _t22, _t20);
        double _t27 = _t20 * _t23;
        double _t28 = _t21 * _t23;
        double _t29 = _t22 * _t23;
        double _ct0 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t13, _t13, _t10 * _t10)) * java.lang.Math.fma(_t11, _t11, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        if (!(_ct0 > 0.0)) return java.util.OptionalDouble.empty();
        return java.util.OptionalDouble.of(Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t23) * Math.min(1.0, _ct0));
    }

    /**
     * Out-of-range path of {@code tryAngleBetween}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private java.util.OptionalDouble tryAngleBetween_degenerate_mulAdd(double otherX, double otherY, double otherZ) {
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
        double _ct0 = ((_t8) * (_t8) + (((_t13) * (_t13) + (_t10 * _t10)))) * ((_t11) * (_t11) + (((_t12) * (_t12) + (_t9 * _t9))));
        if (!(_ct0 > 0.0)) return java.util.OptionalDouble.empty();
        return java.util.OptionalDouble.of(Math.atan2(java.lang.Math.sqrt(((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t23) * Math.min(1.0, _ct0));
    }

    /**
     * Normalize this vector to unit length, unless it has zero length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the normalized vector, or an empty {@code Optional} when this vector has zero length
     */
    public java.util.Optional<Double3> tryNormalize() {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t4 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new Double3(this.x * _t4, this.y * _t4, this.z * _t4));
        } else {
            double _ct0 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t4 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new Double3(this.x * _t4, this.y * _t4, this.z * _t4));
        }
    }

    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length, unless it has zero length.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @return the rescaled vector, or an empty {@code Optional} when this vector has zero length
     */
    public java.util.Optional<Double3> tryNormalizeMul(double length) {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t5 = length * (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new Double3(this.x * _t5, this.y * _t5, this.z * _t5));
        } else {
            double _ct0 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t5 = length * (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new Double3(this.x * _t5, this.y * _t5, this.z * _t5));
        }
    }

    /**
     * Compute the signed angle in radians between this vector and {@code other}, positive when the
     * rotation from this vector to {@code other} is counter-clockwise as seen from the direction of
     * the given normal, unless this vector or {@code other} has zero length.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: {@code normal} must be non-zero; this vector may have components of any finite
     * magnitude; {@code other} may have components of any finite magnitude; {@code normal} may have
     * components of any finite magnitude.
     *
     * @param other the vector to measure the signed angle to
     * @param normal the reference axis that defines the sign of the angle
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise as seen from the
     *        direction of the given normal, or an empty {@code OptionalDouble} when this vector or
     *        {@code other} has zero length
     */
    public java.util.OptionalDouble tryOrientedAngle(Double3 other, Double3 normal) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double normalX = normal.x;
        double normalY = normal.y;
        double normalZ = normal.z;
        if (!Math.useFma()) return tryOrientedAngle_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
        double _t10 = java.lang.Math.fma(otherX, this.z, -(otherZ * this.x));
        double _t11 = java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _ct0 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryOrientedAngle_degenerate_fma(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        return java.lang.Math.fma(_t9, normalZ * _t7, java.lang.Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? java.util.OptionalDouble.of(-_t18) : java.util.OptionalDouble.of(_t18);
    }

    /**
     * Compute the signed angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), positive when the rotation from this vector to ({@code otherX},
     * {@code otherY}, {@code otherZ}) is counter-clockwise as seen from the direction of the given
     * normal, unless this vector or ({@code otherX}, {@code otherY}, {@code otherZ}) has zero
     * length.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must be non-zero; this vector may have
     * components of any finite magnitude; {@code (otherX, otherY, otherZ)} may have components of
     * any finite magnitude; {@code (normalX, normalY, normalZ)} may have components of any finite
     * magnitude.
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
     *        given normal, or an empty {@code OptionalDouble} when this vector or ({@code otherX},
     *        {@code otherY}, {@code otherZ}) has zero length
     */
    public java.util.OptionalDouble tryOrientedAngle(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (!Math.useFma()) return tryOrientedAngle_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = java.lang.Math.fma(otherY, this.x, -(otherX * this.y));
        double _t10 = java.lang.Math.fma(otherX, this.z, -(otherZ * this.x));
        double _t11 = java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _ct0 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryOrientedAngle_degenerate_fma(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        return java.lang.Math.fma(_t9, normalZ * _t7, java.lang.Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? java.util.OptionalDouble.of(-_t18) : java.util.OptionalDouble.of(_t18);
    }

    /** {@code tryOrientedAngle} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private java.util.OptionalDouble tryOrientedAngle_mulAdd(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = ((otherY) * (this.x) - (otherX * this.y));
        double _t10 = ((otherX) * (this.z) - (otherZ * this.x));
        double _t11 = ((otherZ) * (this.y) - (otherY * this.z));
        double _ct0 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryOrientedAngle_degenerate_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))));
        return ((_t9) * (normalZ * _t7) + (((_t10) * (normalY * _t7) + (_t11 * (normalX * _t7))))) < 0.0 ? java.util.OptionalDouble.of(-_t18) : java.util.OptionalDouble.of(_t18);
    }

    /** Private tail of {@code tryOrientedAngle_degenerate}; reached only through it. */
    private double tryOrientedAngle_degenerate_s1c8ffe1d_tail_fma(double normalX, double normalY, double normalZ, double _t40, double _t41, double _t42, double _t50) {
        double _t0 = unitScale(normalX, normalY, normalZ);
        return java.lang.Math.fma(normalZ * _t0, _t40, java.lang.Math.fma(normalX * _t0, _t41, normalY * _t0 * _t42)) < 0.0 ? -_t50 : _t50;
    }

    /** Private tail of {@code tryOrientedAngle_degenerate}; reached only through it. */
    private double tryOrientedAngle_degenerate_s1c8ffe1d_tail_mulAdd(double normalX, double normalY, double normalZ, double _t40, double _t41, double _t42, double _t50) {
        double _t0 = unitScale(normalX, normalY, normalZ);
        return ((normalZ * _t0) * (_t40) + (((normalX * _t0) * (_t41) + (normalY * _t0 * _t42)))) < 0.0 ? -_t50 : _t50;
    }

    /**
     * Out-of-range path of {@code tryOrientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private java.util.OptionalDouble tryOrientedAngle_degenerate_fma(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherY * _t1;
        double _t10 = this.x * _t2;
        double _t11 = otherX * _t1;
        double _t12 = this.y * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = this.z * _t2;
        double _t27 = java.lang.Math.fma(_t9, _t10, -(_t11 * _t12));
        double _t28 = java.lang.Math.fma(_t13, _t12, -(_t9 * _t14));
        double _t29 = java.lang.Math.fma(_t11, _t14, -(_t13 * _t10));
        double _t35 = unitScale(_t28, _t29, _t27);
        double _t40 = _t27 * _t35;
        double _t41 = _t28 * _t35;
        double _t42 = _t29 * _t35;
        double _t50 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t11, _t11, _t9 * _t9)) * java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t10, _t10, _t12 * _t12));
        if (!(_t50 > 0.0)) return java.util.OptionalDouble.empty();
        return java.util.OptionalDouble.of(tryOrientedAngle_degenerate_s1c8ffe1d_tail_fma(normalX, normalY, normalZ, _t40, _t41, _t42, Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t42, _t42, _t41 * _t41))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t35) * Math.min(1.0, _t50)));
    }

    /**
     * Out-of-range path of {@code tryOrientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private java.util.OptionalDouble tryOrientedAngle_degenerate_mulAdd(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(this.x, this.y, this.z);
        double _t9 = otherY * _t1;
        double _t10 = this.x * _t2;
        double _t11 = otherX * _t1;
        double _t12 = this.y * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = this.z * _t2;
        double _t27 = ((_t9) * (_t10) - (_t11 * _t12));
        double _t28 = ((_t13) * (_t12) - (_t9 * _t14));
        double _t29 = ((_t11) * (_t14) - (_t13 * _t10));
        double _t35 = unitScale(_t28, _t29, _t27);
        double _t40 = _t27 * _t35;
        double _t41 = _t28 * _t35;
        double _t42 = _t29 * _t35;
        double _t50 = ((_t13) * (_t13) + (((_t11) * (_t11) + (_t9 * _t9)))) * ((_t14) * (_t14) + (((_t10) * (_t10) + (_t12 * _t12))));
        if (!(_t50 > 0.0)) return java.util.OptionalDouble.empty();
        return java.util.OptionalDouble.of(tryOrientedAngle_degenerate_s1c8ffe1d_tail_mulAdd(normalX, normalY, normalZ, _t40, _t41, _t42, Math.atan2(java.lang.Math.sqrt(((_t40) * (_t40) + (((_t42) * (_t42) + (_t41 * _t41))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t35) * Math.min(1.0, _t50)));
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize((p1 - this) x (p2 - this))} - it points to the side from which the vertices
     * {@code this}, {@code p1}, {@code p2} appear counter-clockwise, unless the triangle is
     * degenerate.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude; {@code p1} may have
     * components of any finite magnitude; {@code p2} may have components of any finite magnitude.
     *
     * @param p1 the second vertex of the triangle (this vector is the first)
     * @param p2 the third vertex of the triangle
     * @return the unit normal, or an empty {@code Optional} for a degenerate triangle
     */
    public java.util.Optional<Double3> tryTriangleNormal(Double3 p1, Double3 p2) {
        double p1X = p1.x;
        double p1Y = p1.y;
        double p1Z = p1.z;
        double p2X = p2.x;
        double p2Y = p2.y;
        double p2Z = p2.z;
        if (!Math.useFma()) return tryTriangleNormal_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = java.lang.Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = java.lang.Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryTriangleNormal_degenerate_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        return java.util.Optional.of(new Double3(_t12 * _t19, _t14 * _t19, _t13 * _t19));
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize(((p1X, p1Y, p1Z) - this) x ((p2X, p2Y, p2Z) - this))} - it points to the
     * side from which the vertices {@code this}, ({@code p1X}, {@code p1Y}, {@code p1Z}),
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) appear counter-clockwise, unless the triangle is
     * degenerate.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: this vector may have components of any finite magnitude; {@code (p1X, p1Y, p1Z)}
     * may have components of any finite magnitude; {@code (p2X, p2Y, p2Z)} may have components of
     * any finite magnitude.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @return the unit normal, or an empty {@code Optional} for a degenerate triangle
     */
    public java.util.Optional<Double3> tryTriangleNormal(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (!Math.useFma()) return tryTriangleNormal_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = java.lang.Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = java.lang.Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryTriangleNormal_degenerate_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        return java.util.Optional.of(new Double3(_t12 * _t19, _t14 * _t19, _t13 * _t19));
    }

    /** {@code tryTriangleNormal} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private java.util.Optional<Double3> tryTriangleNormal_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _t0 = p1Y - this.y;
        double _t1 = p2Z - this.z;
        double _t2 = p1Z - this.z;
        double _t3 = p2Y - this.y;
        double _t4 = p1X - this.x;
        double _t5 = p2X - this.x;
        double _t12 = ((_t0) * (_t1) - (_t2 * _t3));
        double _t13 = ((_t4) * (_t3) - (_t0 * _t5));
        double _t14 = ((_t2) * (_t5) - (_t4 * _t1));
        double _ct0 = ((_t13) * (_t13) + (((_t12) * (_t12) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return tryTriangleNormal_degenerate_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        return java.util.Optional.of(new Double3(_t12 * _t19, _t14 * _t19, _t13 * _t19));
    }

    /** Private tail of {@code tryTriangleNormal_degenerate}; reached only through it. */
    private Double3 tryTriangleNormal_degenerate_s5566d617_tail_fma(double _t42, double _t41, double _t43, double _t38, double _t44, double _t39, double _t40) {
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t64 = java.lang.Math.fma(_t52, _t53, -(_t54 * _t55));
        double _t65 = java.lang.Math.fma(_t54, _t56, -(_t57 * _t53));
        double _t66 = java.lang.Math.fma(_t57, _t55, -(_t52 * _t56));
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _ct0 = java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, _t73 * _t73));
        if (!(_ct0 > 0.0)) return null;
        double _t78 = (1.0 / java.lang.Math.sqrt(_ct0));
        return new Double3(_t78 * _t72, _t78 * _t73, _t78 * _t71);
    }

    /** Private tail of {@code tryTriangleNormal_degenerate}; reached only through it. */
    private Double3 tryTriangleNormal_degenerate_s5566d617_tail_mulAdd(double _t42, double _t41, double _t43, double _t38, double _t44, double _t39, double _t40) {
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t64 = ((_t52) * (_t53) - (_t54 * _t55));
        double _t65 = ((_t54) * (_t56) - (_t57 * _t53));
        double _t66 = ((_t57) * (_t55) - (_t52 * _t56));
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _ct0 = ((_t71) * (_t71) + (((_t72) * (_t72) + (_t73 * _t73))));
        if (!(_ct0 > 0.0)) return null;
        double _t78 = (1.0 / java.lang.Math.sqrt(_ct0));
        return new Double3(_t78 * _t72, _t78 * _t73, _t78 * _t71);
    }

    /**
     * Out-of-range path of {@code tryTriangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private java.util.Optional<Double3> tryTriangleNormal_degenerate_fma(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(this.z), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(this.y))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = this.x * _t19;
        double _t32 = this.y * _t19;
        double _t34 = this.z * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        Double3 _r = tryTriangleNormal_degenerate_s5566d617_tail_fma(p2X * _t19 - _t30, p2Y * _t19 - _t32, p2Z * _t19 - _t34, _t38, unitScale(_t38, _t39, _t40), _t39, _t40);
        return _r != null ? java.util.Optional.of(_r) : java.util.Optional.empty();
    }

    /**
     * Out-of-range path of {@code tryTriangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private java.util.Optional<Double3> tryTriangleNormal_degenerate_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(this.z), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(this.x), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(this.y))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = this.x * _t19;
        double _t32 = this.y * _t19;
        double _t34 = this.z * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        Double3 _r = tryTriangleNormal_degenerate_s5566d617_tail_mulAdd(p2X * _t19 - _t30, p2Y * _t19 - _t32, p2Z * _t19 - _t34, _t38, unitScale(_t38, _t39, _t40), _t39, _t40);
        return _r != null ? java.util.Optional.of(_r) : java.util.Optional.empty();
    }

    /**
     * Compute the unit in the last place (ulp) of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 ulp() {
        return new Double3(Math.ulp(this.x), Math.ulp(this.y), Math.ulp(this.z));
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double4 xyz0() {
        return new Double4(this.x, this.y, this.z, 0.0);
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double4 xyz1() {
        return new Double4(this.x, this.y, this.z, 1.0);
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
    public Double3 preMul(Double3x3 mat) {
        double _mat_m02 = mat.m02();
        double _mat_m00 = mat.m00();
        double _mat_m01 = mat.m01();
        double _mat_m12 = mat.m12();
        double _mat_m10 = mat.m10();
        double _mat_m11 = mat.m11();
        double _mat_m22 = mat.m22();
        double _mat_m20 = mat.m20();
        double _mat_m21 = mat.m21();
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(_mat_m02, this.z, java.lang.Math.fma(_mat_m00, this.x, _mat_m01 * this.y)), java.lang.Math.fma(_mat_m12, this.z, java.lang.Math.fma(_mat_m10, this.x, _mat_m11 * this.y)), java.lang.Math.fma(_mat_m22, this.z, java.lang.Math.fma(_mat_m20, this.x, _mat_m21 * this.y)));
        } else {
            return new Double3(((_mat_m02) * (this.z) + (((_mat_m00) * (this.x) + (_mat_m01 * this.y)))), ((_mat_m12) * (this.z) + (((_mat_m10) * (this.x) + (_mat_m11 * this.y)))), ((_mat_m22) * (this.z) + (((_mat_m20) * (this.x) + (_mat_m21 * this.y)))));
        }
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
    public Double3 preMulDirection(Double3x4 mat) {
        double _mat_m02 = mat.m02();
        double _mat_m00 = mat.m00();
        double _mat_m01 = mat.m01();
        double _mat_m12 = mat.m12();
        double _mat_m10 = mat.m10();
        double _mat_m11 = mat.m11();
        double _mat_m22 = mat.m22();
        double _mat_m20 = mat.m20();
        double _mat_m21 = mat.m21();
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(_mat_m02, this.z, java.lang.Math.fma(_mat_m00, this.x, _mat_m01 * this.y)), java.lang.Math.fma(_mat_m12, this.z, java.lang.Math.fma(_mat_m10, this.x, _mat_m11 * this.y)), java.lang.Math.fma(_mat_m22, this.z, java.lang.Math.fma(_mat_m20, this.x, _mat_m21 * this.y)));
        } else {
            return new Double3(((_mat_m02) * (this.z) + (((_mat_m00) * (this.x) + (_mat_m01 * this.y)))), ((_mat_m12) * (this.z) + (((_mat_m10) * (this.x) + (_mat_m11 * this.y)))), ((_mat_m22) * (this.z) + (((_mat_m20) * (this.x) + (_mat_m21 * this.y)))));
        }
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
    public Double3 preMulDirection(Double4x4 mat) {
        double _mat_m02 = mat.m02();
        double _mat_m00 = mat.m00();
        double _mat_m01 = mat.m01();
        double _mat_m12 = mat.m12();
        double _mat_m10 = mat.m10();
        double _mat_m11 = mat.m11();
        double _mat_m22 = mat.m22();
        double _mat_m20 = mat.m20();
        double _mat_m21 = mat.m21();
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(_mat_m02, this.z, java.lang.Math.fma(_mat_m00, this.x, _mat_m01 * this.y)), java.lang.Math.fma(_mat_m12, this.z, java.lang.Math.fma(_mat_m10, this.x, _mat_m11 * this.y)), java.lang.Math.fma(_mat_m22, this.z, java.lang.Math.fma(_mat_m20, this.x, _mat_m21 * this.y)));
        } else {
            return new Double3(((_mat_m02) * (this.z) + (((_mat_m00) * (this.x) + (_mat_m01 * this.y)))), ((_mat_m12) * (this.z) + (((_mat_m10) * (this.x) + (_mat_m11 * this.y)))), ((_mat_m22) * (this.z) + (((_mat_m20) * (this.x) + (_mat_m21 * this.y)))));
        }
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
    public Double3 preMulPosition(Double3x4 mat) {
        double _mat_m00 = mat.m00();
        double _mat_m01 = mat.m01();
        double _mat_m02 = mat.m02();
        double _mat_m03 = mat.m03();
        double _mat_m10 = mat.m10();
        double _mat_m11 = mat.m11();
        double _mat_m12 = mat.m12();
        double _mat_m13 = mat.m13();
        double _mat_m20 = mat.m20();
        double _mat_m21 = mat.m21();
        double _mat_m22 = mat.m22();
        double _mat_m23 = mat.m23();
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(_mat_m00, this.x, java.lang.Math.fma(_mat_m01, this.y, java.lang.Math.fma(_mat_m02, this.z, _mat_m03))), java.lang.Math.fma(_mat_m10, this.x, java.lang.Math.fma(_mat_m11, this.y, java.lang.Math.fma(_mat_m12, this.z, _mat_m13))), java.lang.Math.fma(_mat_m20, this.x, java.lang.Math.fma(_mat_m21, this.y, java.lang.Math.fma(_mat_m22, this.z, _mat_m23))));
        } else {
            return new Double3(((_mat_m00) * (this.x) + (((_mat_m01) * (this.y) + (((_mat_m02) * (this.z) + (_mat_m03)))))), ((_mat_m10) * (this.x) + (((_mat_m11) * (this.y) + (((_mat_m12) * (this.z) + (_mat_m13)))))), ((_mat_m20) * (this.x) + (((_mat_m21) * (this.y) + (((_mat_m22) * (this.z) + (_mat_m23)))))));
        }
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
    public Double3 preMulPosition(Double4x4 mat) {
        double _mat_m00 = mat.m00();
        double _mat_m01 = mat.m01();
        double _mat_m02 = mat.m02();
        double _mat_m03 = mat.m03();
        double _mat_m10 = mat.m10();
        double _mat_m11 = mat.m11();
        double _mat_m12 = mat.m12();
        double _mat_m13 = mat.m13();
        double _mat_m20 = mat.m20();
        double _mat_m21 = mat.m21();
        double _mat_m22 = mat.m22();
        double _mat_m23 = mat.m23();
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(_mat_m00, this.x, java.lang.Math.fma(_mat_m01, this.y, java.lang.Math.fma(_mat_m02, this.z, _mat_m03))), java.lang.Math.fma(_mat_m10, this.x, java.lang.Math.fma(_mat_m11, this.y, java.lang.Math.fma(_mat_m12, this.z, _mat_m13))), java.lang.Math.fma(_mat_m20, this.x, java.lang.Math.fma(_mat_m21, this.y, java.lang.Math.fma(_mat_m22, this.z, _mat_m23))));
        } else {
            return new Double3(((_mat_m00) * (this.x) + (((_mat_m01) * (this.y) + (((_mat_m02) * (this.z) + (_mat_m03)))))), ((_mat_m10) * (this.x) + (((_mat_m11) * (this.y) + (((_mat_m12) * (this.z) + (_mat_m13)))))), ((_mat_m20) * (this.x) + (((_mat_m21) * (this.y) + (((_mat_m22) * (this.z) + (_mat_m23)))))));
        }
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
    public Double3 preMulProject(Double4x4 mat) {
        if (!Math.useFma()) return preMulProject_mulAdd(mat);
        double _t2_inv = 1.0 / java.lang.Math.fma(mat.m30(), this.x, java.lang.Math.fma(mat.m31(), this.y, java.lang.Math.fma(mat.m32(), this.z, mat.m33())));
        return new Double3(java.lang.Math.fma(mat.m00(), this.x, java.lang.Math.fma(mat.m01(), this.y, java.lang.Math.fma(mat.m02(), this.z, mat.m03()))) * _t2_inv, java.lang.Math.fma(mat.m10(), this.x, java.lang.Math.fma(mat.m11(), this.y, java.lang.Math.fma(mat.m12(), this.z, mat.m13()))) * _t2_inv, java.lang.Math.fma(mat.m20(), this.x, java.lang.Math.fma(mat.m21(), this.y, java.lang.Math.fma(mat.m22(), this.z, mat.m23()))) * _t2_inv);
    }

    /** {@code preMulProject} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 preMulProject_mulAdd(Double4x4 mat) {
        double _t2_inv = 1.0 / ((mat.m30()) * (this.x) + (((mat.m31()) * (this.y) + (((mat.m32()) * (this.z) + (mat.m33()))))));
        return new Double3(((mat.m00()) * (this.x) + (((mat.m01()) * (this.y) + (((mat.m02()) * (this.z) + (mat.m03())))))) * _t2_inv, ((mat.m10()) * (this.x) + (((mat.m11()) * (this.y) + (((mat.m12()) * (this.z) + (mat.m13())))))) * _t2_inv, ((mat.m20()) * (this.x) + (((mat.m21()) * (this.y) + (((mat.m22()) * (this.z) + (mat.m23())))))) * _t2_inv);
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
    public Double3 rotate(DoubleQuat quat) {
        return rotate(quat.x(), quat.y(), quat.z(), quat.w());
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
    public Double3 rotate(double quatX, double quatY, double quatZ, double quatW) {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(quatX, this.y, -(quatY * this.x));
            double _t10 = 2.0 * java.lang.Math.fma(quatZ, this.x, -(quatX * this.z));
            double _t11 = 2.0 * java.lang.Math.fma(quatY, this.z, -(quatZ * this.y));
            return new Double3(java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, this.x))), java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.y))), java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, this.z))));
        } else {
            double _t9 = 2.0 * ((quatX) * (this.y) - (quatY * this.x));
            double _t10 = 2.0 * ((quatZ) * (this.x) - (quatX * this.z));
            double _t11 = 2.0 * ((quatY) * (this.z) - (quatZ * this.y));
            return new Double3(((quatY) * (_t9) + (((((quatW) * (_t11) + (this.x))) - (quatZ) * (_t10)))), ((quatZ) * (_t11) + (((((quatW) * (_t10) + (this.y))) - (quatX) * (_t9)))), ((quatX) * (_t10) + (((((quatW) * (_t9) + (this.z))) - (quatY) * (_t11)))));
        }
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
    public Double3 rotateAround(DoubleQuat quat, Double3 pivot) {
        double quatX = quat.x();
        double quatY = quat.y();
        double quatZ = quat.z();
        double quatW = quat.w();
        double pivotX = pivot.x;
        double pivotY = pivot.y;
        double pivotZ = pivot.z;
        if (!Math.useFma()) return rotateAround_mulAdd(quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        double _t0 = this.y - pivotY;
        double _t1 = this.x - pivotX;
        double _t2 = this.z - pivotZ;
        double _t12 = 2.0 * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        return new Double3(java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, this.x))), java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, this.y))), java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, this.z))));
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
    public Double3 rotateAround(double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (!Math.useFma()) return rotateAround_mulAdd(quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        double _t0 = this.y - pivotY;
        double _t1 = this.x - pivotX;
        double _t2 = this.z - pivotZ;
        double _t12 = 2.0 * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        return new Double3(java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, this.x))), java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, this.y))), java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, this.z))));
    }

    /** {@code rotateAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateAround_mulAdd(double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = this.y - pivotY;
        double _t1 = this.x - pivotX;
        double _t2 = this.z - pivotZ;
        double _t12 = 2.0 * ((quatX) * (_t0) - (quatY * _t1));
        double _t13 = 2.0 * ((quatZ) * (_t1) - (quatX * _t2));
        double _t14 = 2.0 * ((quatY) * (_t2) - (quatZ * _t0));
        return new Double3(((quatY) * (_t12) + (((((quatW) * (_t14) + (this.x))) - (quatZ) * (_t13)))), ((quatZ) * (_t14) + (((((quatW) * (_t13) + (this.y))) - (quatX) * (_t12)))), ((quatX) * (_t13) + (((((quatW) * (_t12) + (this.z))) - (quatY) * (_t14)))));
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
    public Double3 rotateAxis(double angle, Double3 axis) {
        return rotateAxis(angle, axis.x, axis.y, axis.z);
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
    public Double3 rotateAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        if (!Math.useFma()) return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        return new Double3(java.lang.Math.fma(_t3, axisX * _t5, java.lang.Math.fma(this.x, _t1, java.lang.Math.fma(axisY, this.z, -(axisZ * this.y)) * _t0)), java.lang.Math.fma(_t3, axisY * _t5, java.lang.Math.fma(this.y, _t1, java.lang.Math.fma(axisZ, this.x, -(axisX * this.z)) * _t0)), java.lang.Math.fma(_t3, axisZ * _t5, java.lang.Math.fma(this.z, _t1, java.lang.Math.fma(axisX, this.y, -(axisY * this.x)) * _t0)));
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        return new Double3(((_t3) * (axisX * _t5) + (((this.x) * (_t1) + (((axisY) * (this.z) - (axisZ * this.y)) * _t0)))), ((_t3) * (axisY * _t5) + (((this.y) * (_t1) + (((axisZ) * (this.x) - (axisX * this.z)) * _t0)))), ((_t3) * (axisZ * _t5) + (((this.z) * (_t1) + (((axisX) * (this.y) - (axisY * this.x)) * _t0)))));
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
    public Double3 rotateAxisAround(double angle, Double3 axis, Double3 pivot) {
        double axisX = axis.x;
        double axisY = axis.y;
        double axisZ = axis.z;
        double pivotX = pivot.x;
        double pivotY = pivot.y;
        double pivotZ = pivot.z;
        if (!Math.useFma()) return rotateAxisAround_mulAdd(angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = this.x - pivotX;
        double _t3 = this.z - pivotZ;
        double _t4 = this.y - pivotY;
        double _t5 = 1.0 - _t1;
        double _t8 = java.lang.Math.fma(axisZ, _t3, java.lang.Math.fma(axisX, _t2, axisY * _t4));
        return new Double3(java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(java.lang.Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, java.lang.Math.fma(_t5, axisX * _t8, pivotX))), java.lang.Math.fma(_t4, _t1, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, java.lang.Math.fma(_t5, axisY * _t8, pivotY))), java.lang.Math.fma(_t3, _t1, java.lang.Math.fma(java.lang.Math.fma(axisX, _t4, -(axisY * _t2)), _t0, java.lang.Math.fma(_t5, axisZ * _t8, pivotZ))));
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
    public Double3 rotateAxisAround(double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (!Math.useFma()) return rotateAxisAround_mulAdd(angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = this.x - pivotX;
        double _t3 = this.z - pivotZ;
        double _t4 = this.y - pivotY;
        double _t5 = 1.0 - _t1;
        double _t8 = java.lang.Math.fma(axisZ, _t3, java.lang.Math.fma(axisX, _t2, axisY * _t4));
        return new Double3(java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(java.lang.Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, java.lang.Math.fma(_t5, axisX * _t8, pivotX))), java.lang.Math.fma(_t4, _t1, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, java.lang.Math.fma(_t5, axisY * _t8, pivotY))), java.lang.Math.fma(_t3, _t1, java.lang.Math.fma(java.lang.Math.fma(axisX, _t4, -(axisY * _t2)), _t0, java.lang.Math.fma(_t5, axisZ * _t8, pivotZ))));
    }

    /** {@code rotateAxisAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateAxisAround_mulAdd(double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = this.x - pivotX;
        double _t3 = this.z - pivotZ;
        double _t4 = this.y - pivotY;
        double _t5 = 1.0 - _t1;
        double _t8 = ((axisZ) * (_t3) + (((axisX) * (_t2) + (axisY * _t4))));
        return new Double3(((_t2) * (_t1) + (((((axisY) * (_t3) - (axisZ * _t4))) * (_t0) + (((_t5) * (axisX * _t8) + (pivotX)))))), ((_t4) * (_t1) + (((((axisZ) * (_t2) - (axisX * _t3))) * (_t0) + (((_t5) * (axisY * _t8) + (pivotY)))))), ((_t3) * (_t1) + (((((axisX) * (_t4) - (axisY * _t2))) * (_t0) + (((_t5) * (axisZ * _t8) + (pivotZ)))))));
    }

    /**
     * Rotate this vector by the inverse of the given rotation, returning the result as a value.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @return the resulting vector
     */
    public Double3 rotateInverse(DoubleQuat quat) {
        return rotateInverse(quat.x(), quat.y(), quat.z(), quat.w());
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
    public Double3 rotateInverse(double quatX, double quatY, double quatZ, double quatW) {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(quatX, this.z, -(quatZ * this.x));
            double _t10 = 2.0 * java.lang.Math.fma(quatY, this.x, -(quatX * this.y));
            double _t11 = 2.0 * java.lang.Math.fma(quatZ, this.y, -(quatY * this.z));
            return new Double3(java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, this.x))), java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, this.y))), java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, this.z))));
        } else {
            double _t9 = 2.0 * ((quatX) * (this.z) - (quatZ * this.x));
            double _t10 = 2.0 * ((quatY) * (this.x) - (quatX * this.y));
            double _t11 = 2.0 * ((quatZ) * (this.y) - (quatY * this.z));
            return new Double3(((quatZ) * (_t9) + (((((quatW) * (_t11) + (this.x))) - (quatY) * (_t10)))), ((quatX) * (_t10) + (((((quatW) * (_t9) + (this.y))) - (quatZ) * (_t11)))), ((quatY) * (_t11) + (((((quatW) * (_t10) + (this.z))) - (quatX) * (_t9)))));
        }
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
    public Double3 rotateX(double angle) {
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3(this.x, java.lang.Math.fma(this.y, _t1, -(this.z * _t0)), java.lang.Math.fma(this.y, _t0, this.z * _t1));
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3(this.x, ((this.y) * (_t1) - (this.z * _t0)), ((this.y) * (_t0) + (this.z * _t1)));
        }
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
    public Double3 rotateXAround(double angle, Double3 pivot) {
        double pivotY = pivot.y;
        double pivotZ = pivot.z;
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            return new Double3(this.x, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotY)), java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotZ)));
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            return new Double3(this.x, ((_t2) * (_t1) + (((pivotY) - (_t3) * (_t0)))), ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotZ)))));
        }
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
    public Double3 rotateXAround(double angle, double pivotX, double pivotY, double pivotZ) {
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            return new Double3(this.x, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotY)), java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotZ)));
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.y - pivotY;
            double _t3 = this.z - pivotZ;
            return new Double3(this.x, ((_t2) * (_t1) + (((pivotY) - (_t3) * (_t0)))), ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotZ)))));
        }
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
    public Double3 rotateY(double angle) {
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3(java.lang.Math.fma(this.x, _t1, this.z * _t0), this.y, java.lang.Math.fma(this.z, _t1, -(this.x * _t0)));
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3(((this.x) * (_t1) + (this.z * _t0)), this.y, ((this.z) * (_t1) - (this.x * _t0)));
        }
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
    public Double3 rotateYAround(double angle, Double3 pivot) {
        double pivotX = pivot.x;
        double pivotZ = pivot.z;
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            return new Double3(java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotX)), this.y, java.lang.Math.fma(_t3, _t1, java.lang.Math.fma(-_t2, _t0, pivotZ)));
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            return new Double3(((_t2) * (_t1) + (((_t3) * (_t0) + (pivotX)))), this.y, ((_t3) * (_t1) + (((pivotZ) - (_t2) * (_t0)))));
        }
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
    public Double3 rotateYAround(double angle, double pivotX, double pivotY, double pivotZ) {
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            return new Double3(java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotX)), this.y, java.lang.Math.fma(_t3, _t1, java.lang.Math.fma(-_t2, _t0, pivotZ)));
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.z - pivotZ;
            return new Double3(((_t2) * (_t1) + (((_t3) * (_t0) + (pivotX)))), this.y, ((_t3) * (_t1) + (((pivotZ) - (_t2) * (_t0)))));
        }
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
    public Double3 rotateZ(double angle) {
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3(java.lang.Math.fma(this.x, _t1, -(this.y * _t0)), java.lang.Math.fma(this.x, _t0, this.y * _t1), this.z);
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3(((this.x) * (_t1) - (this.y * _t0)), ((this.x) * (_t0) + (this.y * _t1)), this.z);
        }
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
    public Double3 rotateZAround(double angle, Double3 pivot) {
        double pivotX = pivot.x;
        double pivotY = pivot.y;
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            return new Double3(java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotX)), java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotY)), this.z);
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            return new Double3(((_t2) * (_t1) + (((pivotX) - (_t3) * (_t0)))), ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotY)))), this.z);
        }
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
    public Double3 rotateZAround(double angle, double pivotX, double pivotY, double pivotZ) {
        if (Math.useFma()) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            return new Double3(java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(-_t3, _t0, pivotX)), java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotY)), this.z);
        } else {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            double _t2 = this.x - pivotX;
            double _t3 = this.y - pivotY;
            return new Double3(((_t2) * (_t1) + (((pivotX) - (_t3) * (_t0)))), ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotY)))), this.z);
        }
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     */
    public Double3 withX(double x) {
        return new Double3(x, this.y, this.z);
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     */
    public Double3 withY(double y) {
        return new Double3(this.x, y, this.z);
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Double3 withXY(double x, double y) {
        return new Double3(x, y, this.z);
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param z the new value of the {@code z} component
     */
    public Double3 withZ(double z) {
        return new Double3(this.x, this.y, z);
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Double3 withXZ(double x, double z) {
        return new Double3(x, this.y, z);
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Double3 withYZ(double y, double z) {
        return new Double3(this.x, y, z);
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
    public Double3 withXYZ(double x, double y, double z) {
        return new Double3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 xx() {
        return new Double2(x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 xy() {
        return new Double2(x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 xz() {
        return new Double2(x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 yx() {
        return new Double2(y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 yy() {
        return new Double2(y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 yz() {
        return new Double2(y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 zx() {
        return new Double2(z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 zy() {
        return new Double2(z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double2 zz() {
        return new Double2(z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xxx() {
        return new Double3(x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xxy() {
        return new Double3(x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xxz() {
        return new Double3(x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xyx() {
        return new Double3(x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xyy() {
        return new Double3(x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xyz() {
        return new Double3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xzx() {
        return new Double3(x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xzy() {
        return new Double3(x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 xzz() {
        return new Double3(x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yxx() {
        return new Double3(y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yxy() {
        return new Double3(y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yxz() {
        return new Double3(y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yyx() {
        return new Double3(y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yyy() {
        return new Double3(y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yyz() {
        return new Double3(y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yzx() {
        return new Double3(y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yzy() {
        return new Double3(y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 yzz() {
        return new Double3(y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zxx() {
        return new Double3(z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zxy() {
        return new Double3(z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zxz() {
        return new Double3(z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zyx() {
        return new Double3(z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zyy() {
        return new Double3(z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zyz() {
        return new Double3(z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zzx() {
        return new Double3(z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zzy() {
        return new Double3(z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double3 zzz() {
        return new Double3(z, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxxx() {
        return new Double4(x, x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxxy() {
        return new Double4(x, x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxxz() {
        return new Double4(x, x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxyx() {
        return new Double4(x, x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxyy() {
        return new Double4(x, x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxyz() {
        return new Double4(x, x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxzx() {
        return new Double4(x, x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxzy() {
        return new Double4(x, x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xxzz() {
        return new Double4(x, x, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyxx() {
        return new Double4(x, y, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyxy() {
        return new Double4(x, y, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyxz() {
        return new Double4(x, y, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyyx() {
        return new Double4(x, y, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyyy() {
        return new Double4(x, y, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyyz() {
        return new Double4(x, y, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyzx() {
        return new Double4(x, y, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyzy() {
        return new Double4(x, y, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xyzz() {
        return new Double4(x, y, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzxx() {
        return new Double4(x, z, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzxy() {
        return new Double4(x, z, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzxz() {
        return new Double4(x, z, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzyx() {
        return new Double4(x, z, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzyy() {
        return new Double4(x, z, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzyz() {
        return new Double4(x, z, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzzx() {
        return new Double4(x, z, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzzy() {
        return new Double4(x, z, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 xzzz() {
        return new Double4(x, z, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxxx() {
        return new Double4(y, x, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxxy() {
        return new Double4(y, x, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxxz() {
        return new Double4(y, x, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxyx() {
        return new Double4(y, x, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxyy() {
        return new Double4(y, x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxyz() {
        return new Double4(y, x, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxzx() {
        return new Double4(y, x, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxzy() {
        return new Double4(y, x, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yxzz() {
        return new Double4(y, x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyxx() {
        return new Double4(y, y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyxy() {
        return new Double4(y, y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyxz() {
        return new Double4(y, y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyyx() {
        return new Double4(y, y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyyy() {
        return new Double4(y, y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyyz() {
        return new Double4(y, y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyzx() {
        return new Double4(y, y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyzy() {
        return new Double4(y, y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yyzz() {
        return new Double4(y, y, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzxx() {
        return new Double4(y, z, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzxy() {
        return new Double4(y, z, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzxz() {
        return new Double4(y, z, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzyx() {
        return new Double4(y, z, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzyy() {
        return new Double4(y, z, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzyz() {
        return new Double4(y, z, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzzx() {
        return new Double4(y, z, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzzy() {
        return new Double4(y, z, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 yzzz() {
        return new Double4(y, z, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxxx() {
        return new Double4(z, x, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxxy() {
        return new Double4(z, x, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxxz() {
        return new Double4(z, x, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxyx() {
        return new Double4(z, x, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxyy() {
        return new Double4(z, x, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxyz() {
        return new Double4(z, x, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxzx() {
        return new Double4(z, x, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxzy() {
        return new Double4(z, x, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zxzz() {
        return new Double4(z, x, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyxx() {
        return new Double4(z, y, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyxy() {
        return new Double4(z, y, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyxz() {
        return new Double4(z, y, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyyx() {
        return new Double4(z, y, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyyy() {
        return new Double4(z, y, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyyz() {
        return new Double4(z, y, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyzx() {
        return new Double4(z, y, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyzy() {
        return new Double4(z, y, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zyzz() {
        return new Double4(z, y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzxx() {
        return new Double4(z, z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzxy() {
        return new Double4(z, z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzxz() {
        return new Double4(z, z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzyx() {
        return new Double4(z, z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzyy() {
        return new Double4(z, z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzyz() {
        return new Double4(z, z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzzx() {
        return new Double4(z, z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzzy() {
        return new Double4(z, z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value, NaN and the infinities included. */
    public Double4 zzzz() {
        return new Double4(z, z, z, z);
    }

    @Override public String toString() {
        return "Double3(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double3)) return false;
        Double3 o = (Double3) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y)
            && Double.doubleToLongBits(z) == Double.doubleToLongBits(o.z);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y)
            && Double.isFinite(z);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y)
            || Double.isNaN(z);
    }

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a
     * finite one only under an infinite {@code epsilon}; a NaN component never compares equal to
     * anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Double3 other, double epsilon) {
        return java.lang.Math.abs(x - other.x) <= epsilon
            && java.lang.Math.abs(y - other.y) <= epsilon
            && java.lang.Math.abs(z - other.z) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double3SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double3SegOpsUnsafe()
            : new Double3SegOpsMS();
        static final Double3BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double3BbOpsUnsafe()
            : new Double3BbOpsApi();
        static final Double3RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double3RawOpsUnsafe()
            : new Double3RawOpsApi();
    }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        return new Double3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3 loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 24);
        return r;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

    /**
     * Load the elements from the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) {
        dest[offset] = (float) this.x;
        dest[offset + 1] = (float) this.y;
        dest[offset + 2] = (float) this.z;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        return new Double3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3 loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3 r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 12);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double3 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double3} holding the loaded elements
     */
    public static Double3 loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(offset, src);
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

    private abstract static class Double3SegOps {
        public abstract MemorySegment store(Double3 self, long offset, MemorySegment dest);
        public abstract Double3 load(long offset, MemorySegment src);
        public abstract MemorySegment storeFloat(Double3 self, long offset, MemorySegment dest);
        public abstract Double3 loadFloat(long offset, MemorySegment src);
    }

    private static final class Double3SegOpsMS extends Double3SegOps {
        public MemorySegment store(Double3 self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.x);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z);
            return dest;
        }

        public Double3 load(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        public MemorySegment storeFloat(Double3 self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.x);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.y);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.z);
            return dest;
        }

        public Double3 loadFloat(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
            return new Double3(_c0, _c1, _c2);
        }
    }

    private static final class Double3SegOpsUnsafe extends Double3SegOps {
        private static final Double3SegOpsMS MS = new Double3SegOpsMS();

        public MemorySegment store(Double3 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.store(self, offset, dest);
            if (!dest.isNative()) return storeOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            return dest;
        }

        private static MemorySegment storeOnHeap(Double3 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.store(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            return dest;
        }

        public Double3 load(long offset, MemorySegment src) {
            if (!src.isNative()) return loadOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8L);
            double _c2 = U.getDouble(address + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        private static Double3 loadOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.load(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        public MemorySegment storeFloat(Double3 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
            if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            return dest;
        }

        private static MemorySegment storeFloatOnHeap(Double3 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            return dest;
        }

        public Double3 loadFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadFloatOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4L);
            double _c2 = U.getFloat(address + 8L);
            return new Double3(_c0, _c1, _c2);
        }

        private static Double3 loadFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            return new Double3(_c0, _c1, _c2);
        }
    }

    private abstract static class Double3BbOps {
        public abstract DoubleBuffer storeAbsolute(Double3 self, int index, DoubleBuffer buf);
        public abstract Double3 loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeAbsolute(Double3 self, int index, ByteBuffer buf);
        public abstract Double3 loadAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeAbsolute(Double3 self, int index, FloatBuffer buf);
        public abstract Double3 loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeFloatAbsolute(Double3 self, int index, ByteBuffer buf);
        public abstract Double3 loadFloatAbsolute(int index, ByteBuffer buf);
    }

    private static final class Double3BbOpsApi extends Double3BbOps {
        public DoubleBuffer storeAbsolute(Double3 self, int index, DoubleBuffer buf) {
            buf.put(index, self.x);
            buf.put(index + 1, self.y);
            buf.put(index + 2, self.z);
            return buf;
        }

        public Double3 loadAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            return new Double3(_c0, _c1, _c2);
        }

        public ByteBuffer storeAbsolute(Double3 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.x);
            buf.putDouble(index + 8, self.y);
            buf.putDouble(index + 16, self.z);
            return buf;
        }

        public Double3 loadAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            return new Double3(_c0, _c1, _c2);
        }

        public FloatBuffer storeAbsolute(Double3 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.x);
            buf.put(index + 1, (float) self.y);
            buf.put(index + 2, (float) self.z);
            return buf;
        }

        public Double3 loadAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            return new Double3(_c0, _c1, _c2);
        }

        public ByteBuffer storeFloatAbsolute(Double3 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.x);
            buf.putFloat(index + 4, (float) self.y);
            buf.putFloat(index + 8, (float) self.z);
            return buf;
        }

        public Double3 loadFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            return new Double3(_c0, _c1, _c2);
        }
    }

    private static final class Double3BbOpsUnsafe extends Double3BbOps {
        private static final Double3BbOpsApi API = new Double3BbOpsApi();

        public DoubleBuffer storeAbsolute(Double3 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            return buf;
        }

        public Double3 loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8L);
            double _c2 = U.getDouble(address + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        public ByteBuffer storeAbsolute(Double3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(Double3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            return buf;
        }

        public Double3 loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8L);
            double _c2 = U.getDouble(address + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        private static Double3 loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        public FloatBuffer storeAbsolute(Double3 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            return buf;
        }

        public Double3 loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4L);
            double _c2 = U.getFloat(address + 8L);
            return new Double3(_c0, _c1, _c2);
        }

        public ByteBuffer storeFloatAbsolute(Double3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            return buf;
        }

        private static ByteBuffer storeFloatAbsoluteElse(Double3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            return buf;
        }

        public Double3 loadFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4L);
            double _c2 = U.getFloat(address + 8L);
            return new Double3(_c0, _c1, _c2);
        }

        private static Double3 loadFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadFloatAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            return new Double3(_c0, _c1, _c2);
        }
    }

    private abstract static class Double3RawOps {
        public abstract Double3 storeUnsafe(Double3 self, long address);
        public abstract Double3 loadUnsafe(long address);
        public abstract Double3 storeFloatUnsafe(Double3 self, long address);
        public abstract Double3 loadFloatUnsafe(long address);
    }

    private static final class Double3RawOpsApi extends Double3RawOps {
        public Double3 storeUnsafe(Double3 self, long address) {
            self.store(0L, virtualMemory().asSlice(address, 24L));
            return self;
        }

        public Double3 loadUnsafe(long address) {
            return Double3.load(0L, virtualMemory().asSlice(address, 24L));
        }

        public Double3 storeFloatUnsafe(Double3 self, long address) {
            self.storeFloat(0L, virtualMemory().asSlice(address, 12L));
            return self;
        }

        public Double3 loadFloatUnsafe(long address) {
            return Double3.loadFloat(0L, virtualMemory().asSlice(address, 12L));
        }
    }

    private static final class Double3RawOpsUnsafe extends Double3RawOps {
        public Double3 storeUnsafe(Double3 self, long address) {
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            return self;
        }

        public Double3 storeHeap(Double3 self, byte[] bytes, long address) {
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            return self;
        }

        public Double3 loadUnsafe(long address) {
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8L);
            double _c2 = U.getDouble(address + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        public Double3 loadHeap(byte[] bytes, long address) {
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            return new Double3(_c0, _c1, _c2);
        }

        public Double3 storeFloatUnsafe(Double3 self, long address) {
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            return self;
        }

        public Double3 storeFloatHeap(Double3 self, byte[] bytes, long address) {
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            return self;
        }

        public Double3 loadFloatUnsafe(long address) {
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4L);
            double _c2 = U.getFloat(address + 8L);
            return new Double3(_c0, _c1, _c2);
        }

        public Double3 loadFloatHeap(byte[] bytes, long address) {
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            return new Double3(_c0, _c1, _c2);
        }
    }
}
