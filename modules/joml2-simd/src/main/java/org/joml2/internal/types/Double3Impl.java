// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import org.joml2.internal.unsafe.HeapSegments;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double3} backed by a {@code double[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Double3Impl implements Double3 {
    public double[] data;

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

    public Double3Impl() {
        data = new double[3];
    }

    public Double3Impl(double x, double y, double z) {
        double[] dd = this.data = new double[3];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
    }

    public Double3Impl(Double3R src) {
        double[] srcData = ((Double3Impl) src).data;
        double[] dd = this.data = new double[3];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
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
        double[] otherData = ((Double3Impl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
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
    public Double3 div(double scalar, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] / scalar;
        dd[1] = sd[1] / scalar;
        dd[2] = sd[2] / scalar;
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
    public Double3 div(Double3R other, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] / otherData[0];
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] / otherX;
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        return dest;
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
    public Double3 fma(double b, Double3R c, @Mutated Double3 dest) {
        double[] cData = ((Double3Impl) c).data;
        double cX = cData[0];
        double cY = cData[1];
        double cZ = cData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], b, cX);
            dd[1] = java.lang.Math.fma(sd[1], b, cY);
            dd[2] = java.lang.Math.fma(sd[2], b, cZ);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((sd[0]) * (b) + (cX));
            dd[1] = ((sd[1]) * (b) + (cY));
            dd[2] = ((sd[2]) * (b) + (cZ));
            return dest;
        }
    }

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY},
     * {@code cZ}), i.e. compute {@code this * b + (cX, cY, cZ)} per component, and store the result
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], b, cX);
            dd[1] = java.lang.Math.fma(sd[1], b, cY);
            dd[2] = java.lang.Math.fma(sd[2], b, cZ);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((sd[0]) * (b) + (cX));
            dd[1] = ((sd[1]) * (b) + (cY));
            dd[2] = ((sd[2]) * (b) + (cZ));
            return dest;
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
    public Double3 fma(Double3R b, Double3R c, @Mutated Double3 dest) {
        double[] bData = ((Double3Impl) b).data;
        double[] cData = ((Double3Impl) c).data;
        double bX = bData[0];
        double bY = bData[1];
        double bZ = bData[2];
        double cX = cData[0];
        double cY = cData[1];
        double cZ = cData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], bX, cX);
            dd[1] = java.lang.Math.fma(sd[1], bY, cY);
            dd[2] = java.lang.Math.fma(sd[2], bZ, cZ);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((sd[0]) * (bX) + (cX));
            dd[1] = ((sd[1]) * (bY) + (cY));
            dd[2] = ((sd[2]) * (bZ) + (cZ));
            return dest;
        }
    }

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}) and add
     * ({@code cX}, {@code cY}, {@code cZ}), i.e. compute {@code this * (bX, bY, bZ) + (cX, cY, cZ)}
     * per component, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], bX, cX);
            dd[1] = java.lang.Math.fma(sd[1], bY, cY);
            dd[2] = java.lang.Math.fma(sd[2], bZ, cZ);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((sd[0]) * (bX) + (cX));
            dd[1] = ((sd[1]) * (bY) + (cY));
            dd[2] = ((sd[2]) * (bZ) + (cZ));
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
    public Double3 mul(double scalar, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
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
    public Double3 mul(Double3R other, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = otherData[0] * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
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
    public Double3 negate(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
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
    public Double3 sub(Double3R other, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] dd = this.data;
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            dd[0] = _t4 * Math.cosWithSin(_t1);
            dd[1] = _t4 * Math.sinWithCos(_t1);
            dd[2] = java.lang.Math.fma(2.0, u, -1.0);
            return this;
        } else {
            double[] dd = this.data;
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            dd[0] = _t4 * Math.cosWithSin(_t1);
            dd[1] = _t4 * Math.sinWithCos(_t1);
            dd[2] = ((2.0) * (u) - (1.0));
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
    @Mutated public Double3 set(Double3R v) {
        double[] vData = ((Double3Impl) v).data;
        double vY = vData[1];
        double vZ = vData[2];
        double[] dd = this.data;
        dd[0] = vData[0];
        dd[1] = vY;
        dd[2] = vZ;
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
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
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
    public Double3 set(double s, @Mutated Double3 dest) {
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Float3 toFloat(@Mutated Float3 dest) {
        double[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        return dest;
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
    public Byte3 toByte(@Mutated Byte3 dest) {
        double[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
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
    public Byte3 toByte(RoundingMode roundingMode, @Mutated Byte3 dest) {
        switch (roundingMode) {
            case TRUNCATE -> { return toByte(dest); }
            case FLOOR -> {
                {
                    double[] sd = this.data;
                    byte[] dd = ((Byte3Impl) dest).data;
                    dd[0] = (byte) Math.floor(sd[0]);
                    dd[1] = (byte) Math.floor(sd[1]);
                    dd[2] = (byte) Math.floor(sd[2]);
                    return dest;
                }
            }
            case CEILING -> {
                {
                    double[] sd = this.data;
                    byte[] dd = ((Byte3Impl) dest).data;
                    dd[0] = (byte) Math.ceil(sd[0]);
                    dd[1] = (byte) Math.ceil(sd[1]);
                    dd[2] = (byte) Math.ceil(sd[2]);
                    return dest;
                }
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> { return toByte_halfTowardPositiveInfinity(dest); }
            case HALF_AWAY_FROM_ZERO -> { return toByte_halfAwayFromZero(dest); }
        }
        {
            double[] sd = this.data;
            byte[] dd = ((Byte3Impl) dest).data;
            dd[0] = (byte) Math.rint(sd[0]);
            dd[1] = (byte) Math.rint(sd[1]);
            dd[2] = (byte) Math.rint(sd[2]);
            return dest;
        }
    }

    /** {@code toByte(RoundingMode, dest)} under {@code HALF_TOWARD_POSITIVE_INFINITY}; reached only through it. */
    private Byte3 toByte_halfTowardPositiveInfinity(@Mutated Byte3 dest) {
        double[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
        dd[1] = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
        dd[2] = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[2])));
        return dest;
    }

    /** {@code toByte(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Byte3 toByte_halfAwayFromZero(@Mutated Byte3 dest) {
        double[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
        dd[1] = (byte) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
        dd[2] = (byte) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
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
    public Short3 toShort(@Mutated Short3 dest) {
        double[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
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
    public Short3 toShort(RoundingMode roundingMode, @Mutated Short3 dest) {
        switch (roundingMode) {
            case TRUNCATE -> { return toShort(dest); }
            case FLOOR -> {
                {
                    double[] sd = this.data;
                    short[] dd = ((Short3Impl) dest).data;
                    dd[0] = (short) Math.floor(sd[0]);
                    dd[1] = (short) Math.floor(sd[1]);
                    dd[2] = (short) Math.floor(sd[2]);
                    return dest;
                }
            }
            case CEILING -> {
                {
                    double[] sd = this.data;
                    short[] dd = ((Short3Impl) dest).data;
                    dd[0] = (short) Math.ceil(sd[0]);
                    dd[1] = (short) Math.ceil(sd[1]);
                    dd[2] = (short) Math.ceil(sd[2]);
                    return dest;
                }
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> { return toShort_halfTowardPositiveInfinity(dest); }
            case HALF_AWAY_FROM_ZERO -> { return toShort_halfAwayFromZero(dest); }
        }
        {
            double[] sd = this.data;
            short[] dd = ((Short3Impl) dest).data;
            dd[0] = (short) Math.rint(sd[0]);
            dd[1] = (short) Math.rint(sd[1]);
            dd[2] = (short) Math.rint(sd[2]);
            return dest;
        }
    }

    /** {@code toShort(RoundingMode, dest)} under {@code HALF_TOWARD_POSITIVE_INFINITY}; reached only through it. */
    private Short3 toShort_halfTowardPositiveInfinity(@Mutated Short3 dest) {
        double[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
        dd[1] = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
        dd[2] = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[2])));
        return dest;
    }

    /** {@code toShort(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Short3 toShort_halfAwayFromZero(@Mutated Short3 dest) {
        double[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
        dd[1] = (short) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
        dd[2] = (short) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
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
    public Int3 toInt(@Mutated Int3 dest) {
        double[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
        dd[2] = (int) (sd[2]);
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
    public Int3 toInt(RoundingMode roundingMode, @Mutated Int3 dest) {
        switch (roundingMode) {
            case TRUNCATE -> { return toInt(dest); }
            case FLOOR -> {
                {
                    double[] sd = this.data;
                    int[] dd = ((Int3Impl) dest).data;
                    dd[0] = (int) Math.floor(sd[0]);
                    dd[1] = (int) Math.floor(sd[1]);
                    dd[2] = (int) Math.floor(sd[2]);
                    return dest;
                }
            }
            case CEILING -> {
                {
                    double[] sd = this.data;
                    int[] dd = ((Int3Impl) dest).data;
                    dd[0] = (int) Math.ceil(sd[0]);
                    dd[1] = (int) Math.ceil(sd[1]);
                    dd[2] = (int) Math.ceil(sd[2]);
                    return dest;
                }
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> { return toInt_halfTowardPositiveInfinity(dest); }
            case HALF_AWAY_FROM_ZERO -> { return toInt_halfAwayFromZero(dest); }
        }
        {
            double[] sd = this.data;
            int[] dd = ((Int3Impl) dest).data;
            dd[0] = (int) Math.rint(sd[0]);
            dd[1] = (int) Math.rint(sd[1]);
            dd[2] = (int) Math.rint(sd[2]);
            return dest;
        }
    }

    /** {@code toInt(RoundingMode, dest)} under {@code HALF_TOWARD_POSITIVE_INFINITY}; reached only through it. */
    private Int3 toInt_halfTowardPositiveInfinity(@Mutated Int3 dest) {
        double[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
        dd[1] = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
        dd[2] = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[2])));
        return dest;
    }

    /** {@code toInt(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Int3 toInt_halfAwayFromZero(@Mutated Int3 dest) {
        double[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = (int) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
        dd[1] = (int) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
        dd[2] = (int) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
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
    public Long3 toLong(@Mutated Long3 dest) {
        double[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = (long) (sd[0]);
        dd[1] = (long) (sd[1]);
        dd[2] = (long) (sd[2]);
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
    public Long3 toLong(RoundingMode roundingMode, @Mutated Long3 dest) {
        switch (roundingMode) {
            case TRUNCATE -> { return toLong(dest); }
            case FLOOR -> {
                {
                    double[] sd = this.data;
                    long[] dd = ((Long3Impl) dest).data;
                    dd[0] = (long) Math.floor(sd[0]);
                    dd[1] = (long) Math.floor(sd[1]);
                    dd[2] = (long) Math.floor(sd[2]);
                    return dest;
                }
            }
            case CEILING -> {
                {
                    double[] sd = this.data;
                    long[] dd = ((Long3Impl) dest).data;
                    dd[0] = (long) Math.ceil(sd[0]);
                    dd[1] = (long) Math.ceil(sd[1]);
                    dd[2] = (long) Math.ceil(sd[2]);
                    return dest;
                }
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                {
                    double[] sd = this.data;
                    long[] dd = ((Long3Impl) dest).data;
                    dd[0] = Math.round(sd[0]);
                    dd[1] = Math.round(sd[1]);
                    dd[2] = Math.round(sd[2]);
                    return dest;
                }
            }
            case HALF_AWAY_FROM_ZERO -> { return toLong_halfAwayFromZero(dest); }
        }
        {
            double[] sd = this.data;
            long[] dd = ((Long3Impl) dest).data;
            dd[0] = (long) Math.rint(sd[0]);
            dd[1] = (long) Math.rint(sd[1]);
            dd[2] = (long) Math.rint(sd[2]);
            return dest;
        }
    }

    /** {@code toLong(RoundingMode, dest)} under {@code HALF_AWAY_FROM_ZERO}; reached only through it. */
    private Long3 toLong_halfAwayFromZero(@Mutated Long3 dest) {
        double[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = (long) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
        dd[1] = (long) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
        dd[2] = (long) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
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
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
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
    public Double3 bezier(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        double[] p1Data = ((Double3Impl) p1).data;
        double[] p2Data = ((Double3Impl) p2).data;
        double[] p3Data = ((Double3Impl) p3).data;
        double p1X = p1Data[0];
        double p1Y = p1Data[1];
        double p1Z = p1Data[2];
        double p2X = p2Data[0];
        double p2Y = p2Data[1];
        double p2Z = p2Data[2];
        double p3X = p3Data[0];
        double p3Y = p3Data[1];
        double p3Z = p3Data[2];
        if (!org.joml2.internal.FmaSwitch.ON) return bezier_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dd[0] = java.lang.Math.fma(p1X, _t7, sd[0] * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2);
        dd[1] = java.lang.Math.fma(p1Y, _t7, sd[1] * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = java.lang.Math.fma(p1Z, _t7, sd[2] * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2);
        return dest;
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z})
     * and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}), and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return bezier_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dd[0] = java.lang.Math.fma(p1X, _t7, sd[0] * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2);
        dd[1] = java.lang.Math.fma(p1Y, _t7, sd[1] * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = java.lang.Math.fma(p1Z, _t7, sd[2] * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2);
        return dest;
    }

    /** {@code bezier} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 bezier_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dd[0] = ((p1X) * (_t7) + (sd[0] * _t8)) + ((p2X) * (_t6) + (p3X * _t2));
        dd[1] = ((p1Y) * (_t7) + (sd[1] * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2));
        dd[2] = ((p1Z) * (_t7) + (sd[2] * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2));
        return dest;
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
    public Double3 bezier2(Double3R p1, Double3R p2, double t, @Mutated Double3 dest) {
        double[] p1Data = ((Double3Impl) p1).data;
        double[] p2Data = ((Double3Impl) p2).data;
        double p1X = p1Data[0];
        double p1Y = p1Data[1];
        double p1Z = p1Data[2];
        double p2X = p2Data[0];
        double p2Y = p2Data[1];
        double p2Z = p2Data[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            dd[0] = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, sd[0] * _t4));
            dd[1] = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, sd[1] * _t4));
            dd[2] = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, sd[2] * _t4));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            dd[0] = ((p2X) * (_t0) + (((p1X) * (_t3) + (sd[0] * _t4))));
            dd[1] = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (sd[1] * _t4))));
            dd[2] = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (sd[2] * _t4))));
            return dest;
        }
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            dd[0] = java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, sd[0] * _t4));
            dd[1] = java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, sd[1] * _t4));
            dd[2] = java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, sd[2] * _t4));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            dd[0] = ((p2X) * (_t0) + (((p1X) * (_t3) + (sd[0] * _t4))));
            dd[1] = ((p2Y) * (_t0) + (((p1Y) * (_t3) + (sd[1] * _t4))));
            dd[2] = ((p2Z) * (_t0) + (((p1Z) * (_t3) + (sd[2] * _t4))));
            return dest;
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
    public Double3 bezier2Tangent(Double3R p1, Double3R p2, double t, @Mutated Double3 dest) {
        double[] p1Data = ((Double3Impl) p1).data;
        double[] p2Data = ((Double3Impl) p2).data;
        double p1X = p1Data[0];
        double p1Y = p1Data[1];
        double p1Z = p1Data[2];
        double p2X = p2Data[0];
        double p2Y = p2Data[1];
        double p2Z = p2Data[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dd[0] = java.lang.Math.fma(p1X - sd[0], _t2, (p2X - p1X) * _t1);
            dd[1] = java.lang.Math.fma(p1Y - sd[1], _t2, (p2Y - p1Y) * _t1);
            dd[2] = java.lang.Math.fma(p1Z - sd[2], _t2, (p2Z - p1Z) * _t1);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dd[0] = ((p1X - sd[0]) * (_t2) + ((p2X - p1X) * _t1));
            dd[1] = ((p1Y - sd[1]) * (_t2) + ((p2Y - p1Y) * _t1));
            dd[2] = ((p1Z - sd[2]) * (_t2) + ((p2Z - p1Z) * _t1));
            return dest;
        }
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y}, {@code p2Z}),
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dd[0] = java.lang.Math.fma(p1X - sd[0], _t2, (p2X - p1X) * _t1);
            dd[1] = java.lang.Math.fma(p1Y - sd[1], _t2, (p2Y - p1Y) * _t1);
            dd[2] = java.lang.Math.fma(p1Z - sd[2], _t2, (p2Z - p1Z) * _t1);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dd[0] = ((p1X - sd[0]) * (_t2) + ((p2X - p1X) * _t1));
            dd[1] = ((p1Y - sd[1]) * (_t2) + ((p2Y - p1Y) * _t1));
            dd[2] = ((p1Z - sd[2]) * (_t2) + ((p2Z - p1Z) * _t1));
            return dest;
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
    public Double3 bezierTangent(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        double[] p1Data = ((Double3Impl) p1).data;
        double[] p2Data = ((Double3Impl) p2).data;
        double[] p3Data = ((Double3Impl) p3).data;
        double p1X = p1Data[0];
        double p1Y = p1Data[1];
        double p1Z = p1Data[2];
        double p2X = p2Data[0];
        double p2Y = p2Data[1];
        double p2Z = p2Data[2];
        double p3X = p3Data[0];
        double p3Y = p3Data[1];
        double p3Z = p3Data[2];
        if (!org.joml2.internal.FmaSwitch.ON) return bezierTangent_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dd[0] = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z}) and ends at
     * ({@code p3X}, {@code p3Y}, {@code p3Z}), and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return bezierTangent_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dd[0] = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        return dest;
    }

    /** {@code bezierTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 bezierTangent_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dd[0] = ((p3X - p2X) * (_t2) + (((p1X - sd[0]) * (_t6) + ((p2X - p1X) * _t5))));
        dd[1] = ((p3Y - p2Y) * (_t2) + (((p1Y - sd[1]) * (_t6) + ((p2Y - p1Y) * _t5))));
        dd[2] = ((p3Z - p2Z) * (_t2) + (((p1Z - sd[2]) * (_t6) + ((p2Z - p1Z) * _t5))));
        return dest;
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
    public Double3 catmullRom(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        double[] p1Data = ((Double3Impl) p1).data;
        double[] p2Data = ((Double3Impl) p2).data;
        double[] p3Data = ((Double3Impl) p3).data;
        double p1X = p1Data[0];
        double p1Y = p1Data[1];
        double p1Z = p1Data[2];
        double p2X = p2Data[0];
        double p2Y = p2Data[1];
        double p2Z = p2Data[2];
        double p3X = p3Data[0];
        double p3Y = p3Data[1];
        double p3Z = p3Data[2];
        if (org.joml2.internal.FmaSwitch.ON) return catmullRom_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
        return catmullRom_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z})
     * to ({@code p2X}, {@code p2Y}, {@code p2Z}), with this vector as the control point before the
     * segment and ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it, and store
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
        if (org.joml2.internal.FmaSwitch.ON) return catmullRom_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
        return catmullRom_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
    }

    /** {@code catmullRom} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 catmullRom_fma(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t1 = t * _t0;
        dd[0] = 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - sd[0])) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, sd[0], java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - sd[0])) * _t1));
        dd[1] = 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - sd[1])) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, sd[1], java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - sd[1])) * _t1));
        dd[2] = 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - sd[2])) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, sd[2], java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - sd[2])) * _t1));
        return dest;
    }

    /** {@code catmullRom} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 catmullRom_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t1 = t * _t0;
        dd[0] = 0.5 * (((2.0) * (p1X) + (t * (p2X - sd[0]))) + ((((((2.0) * (sd[0]) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - sd[0]))) - (3.0) * (p2X)) * _t1)));
        dd[1] = 0.5 * (((2.0) * (p1Y) + (t * (p2Y - sd[1]))) + ((((((2.0) * (sd[1]) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - sd[1]))) - (3.0) * (p2Y)) * _t1)));
        dd[2] = 0.5 * (((2.0) * (p1Z) + (t * (p2Z - sd[2]))) + ((((((2.0) * (sd[2]) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - sd[2]))) - (3.0) * (p2Z)) * _t1)));
        return dest;
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
    public Double3 catmullRomTangent(Double3R p1, Double3R p2, Double3R p3, double t, @Mutated Double3 dest) {
        double[] p1Data = ((Double3Impl) p1).data;
        double[] p2Data = ((Double3Impl) p2).data;
        double[] p3Data = ((Double3Impl) p3).data;
        return catmullRomTangent(p1Data[0], p1Data[1], p1Data[2], p2Data[0], p2Data[1], p2Data[2], p3Data[0], p3Data[1], p3Data[2], t, dest);
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}) to ({@code p2X},
     * {@code p2Y}, {@code p2Z}), with this vector as the control point before the segment and
     * ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it, and store the result
     * in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return catmullRomTangent_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        dd[0] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, sd[0], java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - sd[0])), _t0, p2X - sd[0]));
        dd[1] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, sd[1], java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - sd[1])), _t0, p2Y - sd[1]));
        dd[2] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, sd[2], java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - sd[2])), _t0, p2Z - sd[2]));
        return dest;
    }

    /** {@code catmullRomTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 catmullRomTangent_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        dd[0] = 0.5 * ((t) * (2.0 * ((((2.0) * (sd[0]) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - sd[0]))) - (3.0) * (p2X))) * (_t0) + (p2X - sd[0]))));
        dd[1] = 0.5 * ((t) * (2.0 * ((((2.0) * (sd[1]) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - sd[1]))) - (3.0) * (p2Y))) * (_t0) + (p2Y - sd[1]))));
        dd[2] = 0.5 * ((t) * (2.0 * ((((2.0) * (sd[2]) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - sd[2]))) - (3.0) * (p2Z))) * (_t0) + (p2Z - sd[2]))));
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
    public Double3 hermite(Double3R t0, Double3R v1, Double3R t1, double t, @Mutated Double3 dest) {
        double[] t0Data = ((Double3Impl) t0).data;
        double[] v1Data = ((Double3Impl) v1).data;
        double[] t1Data = ((Double3Impl) t1).data;
        double t0X = t0Data[0];
        double t0Y = t0Data[1];
        double t0Z = t0Data[2];
        double v1X = v1Data[0];
        double v1Y = v1Data[1];
        double v1Z = v1Data[2];
        double t1X = t1Data[0];
        double t1Y = t1Data[1];
        double t1Z = t1Data[2];
        if (!org.joml2.internal.FmaSwitch.ON) return hermite_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        dd[0] = java.lang.Math.fma(sd[0], _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9);
        dd[1] = java.lang.Math.fma(sd[1], _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9);
        dd[2] = java.lang.Math.fma(sd[2], _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9);
        return dest;
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
        if (!org.joml2.internal.FmaSwitch.ON) return hermite_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        dd[0] = java.lang.Math.fma(sd[0], _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9);
        dd[1] = java.lang.Math.fma(sd[1], _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9);
        dd[2] = java.lang.Math.fma(sd[2], _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9);
        return dest;
    }

    /** {@code hermite} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 hermite_mulAdd(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        dd[0] = ((sd[0]) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9));
        dd[1] = ((sd[1]) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9));
        dd[2] = ((sd[2]) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9));
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
    public Double3 hermiteTangent(Double3R t0, Double3R v1, Double3R t1, double t, @Mutated Double3 dest) {
        double[] t0Data = ((Double3Impl) t0).data;
        double[] v1Data = ((Double3Impl) v1).data;
        double[] t1Data = ((Double3Impl) t1).data;
        double t0X = t0Data[0];
        double t0Y = t0Data[1];
        double t0Z = t0Data[2];
        double v1X = v1Data[0];
        double v1Y = v1Data[1];
        double v1Z = v1Data[2];
        double t1X = t1Data[0];
        double t1Y = t1Data[1];
        double t1Z = t1Data[2];
        if (!org.joml2.internal.FmaSwitch.ON) return hermiteTangent_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        dd[0] = java.lang.Math.fma(sd[0], _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7);
        dd[1] = java.lang.Math.fma(sd[1], _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7);
        dd[2] = java.lang.Math.fma(sd[2], _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7);
        return dest;
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
        if (!org.joml2.internal.FmaSwitch.ON) return hermiteTangent_mulAdd(t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        dd[0] = java.lang.Math.fma(sd[0], _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7);
        dd[1] = java.lang.Math.fma(sd[1], _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7);
        dd[2] = java.lang.Math.fma(sd[2], _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7);
        return dest;
    }

    /** {@code hermiteTangent} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 hermiteTangent_mulAdd(double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        dd[0] = ((sd[0]) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7));
        dd[1] = ((sd[1]) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7));
        dd[2] = ((sd[2]) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7));
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
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 lerp(Double3R other, double t, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            return dest;
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
     * than the other one (1e17 towards 1 ends at 0).
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
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
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 lerp(Double3R other, Double3R t, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double[] tData = ((Double3Impl) t).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double tX = tData[0];
        double tY = tData[1];
        double tZ = tData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(tX, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(tY, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(tZ, otherZ - sd[2], sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((tX) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((tY) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((tZ) * (otherZ - sd[2]) + (sd[2]));
            return dest;
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 lerp(double otherX, double otherY, double otherZ, double tX, double tY, double tZ, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(tX, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(tY, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(tZ, otherZ - sd[2], sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((tX) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((tY) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((tZ) * (otherZ - sd[2]) + (sd[2]));
            return dest;
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
        double[] otherData = ((Double3Impl) other).data;
        return slerp(otherData[0], otherData[1], otherData[2], t, dest);
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) using the interpolation factor {@code t}: the direction turns at a constant
     * rate along the shorter arc between the two directions, and the length changes linearly
     * between the two lengths, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return slerp_mulAdd(otherX, otherY, otherZ, t, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t7 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, dest);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, dest);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = sd[0] * _t9;
        double _t17 = sd[2] * _t9;
        double _t20 = sd[1] * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_s960f8c7a_1_fma(otherX, otherY, otherZ, t, dest, dd, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 slerp_mulAdd(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t7 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, dest);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, dest);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = sd[0] * _t9;
        double _t17 = sd[2] * _t9;
        double _t20 = sd[1] * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_s960f8c7a_1_mulAdd(otherX, otherY, otherZ, t, dest, dd, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_s960f8c7a_1_fma(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] dd, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, dest);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dd[0] = java.lang.Math.fma(_t14, _t53, _sp0 * _t41);
        dd[1] = java.lang.Math.fma(_t20, _t53, _sp0 * _t42);
        dd[2] = java.lang.Math.fma(_t17, _t53, _sp0 * _t40);
        return dest;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_s960f8c7a_1_mulAdd(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] dd, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, dest);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dd[0] = ((_t14) * (_t53) + (_sp0 * _t41));
        dd[1] = ((_t20) * (_t53) + (_sp0 * _t42));
        dd[2] = ((_t17) * (_t53) + (_sp0 * _t40));
        return dest;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_fma(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = sd[2] * _t2;
        double _t13 = sd[0] * _t2;
        double _t14 = sd[1] * _t2;
        double _t15 = Math.min(_t2, _t1);
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
        return slerp_degenerate_s4977c373_1_fma(otherX, otherY, otherZ, t, dest, sd, ((Double3Impl) dest).data, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_mulAdd(double otherX, double otherY, double otherZ, double t, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = sd[2] * _t2;
        double _t13 = sd[0] * _t2;
        double _t14 = sd[1] * _t2;
        double _t15 = Math.min(_t2, _t1);
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
        return slerp_degenerate_s4977c373_1_mulAdd(otherX, otherY, otherZ, t, dest, sd, ((Double3Impl) dest).data, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_1_fma(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52) {
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
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_s4977c373_2_fma(otherX, otherY, otherZ, t, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t95, _t48 * Math.sinWithCos(_t95));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_1_mulAdd(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52) {
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
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_s4977c373_2_mulAdd(otherX, otherY, otherZ, t, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t95, _t48 * Math.sinWithCos(_t95));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_2_fma(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t95, double _t98) {
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s4977c373_3_fma(otherX, otherY, otherZ, t, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_2_mulAdd(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t95, double _t98) {
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s4977c373_3_mulAdd(otherX, otherY, otherZ, t, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_3_fma(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dd[0] = java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv;
                    dd[1] = java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv;
                    dd[2] = java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv;
                } else {
                    dd[0] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                    dd[1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                    dd[2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
                }
            } else {
                dd[0] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                dd[1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                dd[2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
            }
        } else {
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
        }
        return dest;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s4977c373_3_mulAdd(double otherX, double otherY, double otherZ, double t, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 5.048709793414476E-29) {
                    dd[0] = ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv;
                    dd[1] = ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv;
                    dd[2] = ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv;
                } else {
                    dd[0] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                    dd[1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                    dd[2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
                }
            } else {
                dd[0] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                dd[1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                dd[2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
            }
        } else {
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
        }
        return dest;
    }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths, and store the result in
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
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param epsilon the squared sine of the angle between the two vectors at or below which
     *        vectors more than a right angle apart count as opposite, spanning no plane (the
     *        direction then turns through a fixed perpendicular of this vector); the overload
     *        without it uses {@code 2^-94}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 slerp(Double3R other, double t, double epsilon, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        return slerp(otherData[0], otherData[1], otherData[2], t, epsilon, dest);
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) using the interpolation factor {@code t}: the direction turns at a constant
     * rate along the shorter arc between the two directions, and the length changes linearly
     * between the two lengths, and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 slerp(double otherX, double otherY, double otherZ, double t, double epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return slerp_mulAdd(otherX, otherY, otherZ, t, epsilon, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t7 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon, dest);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon, dest);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = sd[0] * _t9;
        double _t17 = sd[2] * _t9;
        double _t20 = sd[1] * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_s51be3057_1_fma(otherX, otherY, otherZ, t, epsilon, dest, dd, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 slerp_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t7 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, epsilon, dest);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, epsilon, dest);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = sd[0] * _t9;
        double _t17 = sd[2] * _t9;
        double _t20 = sd[1] * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_s51be3057_1_mulAdd(otherX, otherY, otherZ, t, epsilon, dest, dd, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_s51be3057_1_fma(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] dd, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return slerp_degenerate_fma(otherX, otherY, otherZ, t, epsilon, dest);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dd[0] = java.lang.Math.fma(_t14, _t53, _sp0 * _t41);
        dd[1] = java.lang.Math.fma(_t20, _t53, _sp0 * _t42);
        dd[2] = java.lang.Math.fma(_t17, _t53, _sp0 * _t40);
        return dest;
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_s51be3057_1_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] dd, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return slerp_degenerate_mulAdd(otherX, otherY, otherZ, t, epsilon, dest);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dd[0] = ((_t14) * (_t53) + (_sp0 * _t41));
        dd[1] = ((_t20) * (_t53) + (_sp0 * _t42));
        dd[2] = ((_t17) * (_t53) + (_sp0 * _t40));
        return dest;
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_fma(double otherX, double otherY, double otherZ, double t, double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = sd[2] * _t2;
        double _t13 = sd[0] * _t2;
        double _t14 = sd[1] * _t2;
        double _t15 = Math.min(_t2, _t1);
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
        return slerp_degenerate_s6e2b3c4e_1_fma(otherX, otherY, otherZ, t, epsilon, dest, sd, ((Double3Impl) dest).data, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double3 slerp_degenerate_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = sd[2] * _t2;
        double _t13 = sd[0] * _t2;
        double _t14 = sd[1] * _t2;
        double _t15 = Math.min(_t2, _t1);
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
        return slerp_degenerate_s6e2b3c4e_1_mulAdd(otherX, otherY, otherZ, t, epsilon, dest, sd, ((Double3Impl) dest).data, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s6e2b3c4e_1_fma(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52) {
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
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_s6e2b3c4e_2_fma(otherX, otherY, otherZ, t, epsilon, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t95, _t48 * Math.sinWithCos(_t95));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s6e2b3c4e_1_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] sd, double[] dd, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52) {
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
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_s6e2b3c4e_2_mulAdd(otherX, otherY, otherZ, t, epsilon, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t95, _t48 * Math.sinWithCos(_t95));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s6e2b3c4e_2_fma(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t95, double _t98) {
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s6e2b3c4e_3_fma(otherX, otherY, otherZ, t, epsilon, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s6e2b3c4e_2_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t95, double _t98) {
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s6e2b3c4e_3_mulAdd(otherX, otherY, otherZ, t, epsilon, dest, sd, dd, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s6e2b3c4e_3_fma(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dd[0] = java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv;
                    dd[1] = java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv;
                    dd[2] = java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv;
                } else {
                    dd[0] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                    dd[1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                    dd[2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
                }
            } else {
                dd[0] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                dd[1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                dd[2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
            }
        } else {
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
        }
        return dest;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 slerp_degenerate_s6e2b3c4e_3_mulAdd(double otherX, double otherY, double otherZ, double t, double epsilon, Double3 dest, double[] sd, double[] dd, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dd[0] = ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv;
                    dd[1] = ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv;
                    dd[2] = ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv;
                } else {
                    dd[0] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                    dd[1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                    dd[2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
                }
            } else {
                dd[0] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                dd[1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                dd[2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
            }
        } else {
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
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
    public Double3 absolute(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
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
    public Double3 acos(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.acos(sd[0]);
        dd[1] = Math.acos(sd[1]);
        dd[2] = Math.acos(sd[2]);
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
    public Double3 addScaled(Double3R b, double scalar, @Mutated Double3 dest) {
        double[] bData = ((Double3Impl) b).data;
        double bX = bData[0];
        double bY = bData[1];
        double bZ = bData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(scalar, bX, sd[0]);
            dd[1] = java.lang.Math.fma(scalar, bY, sd[1]);
            dd[2] = java.lang.Math.fma(scalar, bZ, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((scalar) * (bX) + (sd[0]));
            dd[1] = ((scalar) * (bY) + (sd[1]));
            dd[2] = ((scalar) * (bZ) + (sd[2]));
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(scalar, bX, sd[0]);
            dd[1] = java.lang.Math.fma(scalar, bY, sd[1]);
            dd[2] = java.lang.Math.fma(scalar, bZ, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((scalar) * (bX) + (sd[0]));
            dd[1] = ((scalar) * (bY) + (sd[1]));
            dd[2] = ((scalar) * (bZ) + (sd[2]));
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
    public Double3 addScaled(Double3R b, Double3R c, @Mutated Double3 dest) {
        double[] bData = ((Double3Impl) b).data;
        double[] cData = ((Double3Impl) c).data;
        double bX = bData[0];
        double bY = bData[1];
        double bZ = bData[2];
        double cX = cData[0];
        double cY = cData[1];
        double cZ = cData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(bX, cX, sd[0]);
            dd[1] = java.lang.Math.fma(bY, cY, sd[1]);
            dd[2] = java.lang.Math.fma(bZ, cZ, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((bX) * (cX) + (sd[0]));
            dd[1] = ((bY) * (cY) + (sd[1]));
            dd[2] = ((bZ) * (cZ) + (sd[2]));
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(bX, cX, sd[0]);
            dd[1] = java.lang.Math.fma(bY, cY, sd[1]);
            dd[2] = java.lang.Math.fma(bZ, cZ, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((bX) * (cX) + (sd[0]));
            dd[1] = ((bY) * (cY) + (sd[1]));
            dd[2] = ((bZ) * (cZ) + (sd[2]));
            return dest;
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
    public double angleBetween(Double3R other) {
        double[] otherData = ((Double3Impl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return angleBetween_mulAdd(otherX, otherY, otherZ);
        double[] sd = this.data;
        double _t3 = otherY * -sd[2];
        double _t4 = -(otherX * sd[1]);
        double _t5 = otherX * -sd[2];
        double _t12 = java.lang.Math.fma(otherZ, sd[1], _t3) - java.lang.Math.fma(otherY, sd[2], _t3);
        double _t13 = java.lang.Math.fma(otherY, sd[0], _t4) - java.lang.Math.fma(otherX, sd[1], _t4);
        double _t14 = java.lang.Math.fma(otherZ, sd[0], _t5) - java.lang.Math.fma(otherX, sd[2], _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_fma(otherX, otherY, otherZ);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1])));
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
        if (!org.joml2.internal.FmaSwitch.ON) return angleBetween_mulAdd(otherX, otherY, otherZ);
        double[] sd = this.data;
        double _t3 = otherY * -sd[2];
        double _t4 = -(otherX * sd[1]);
        double _t5 = otherX * -sd[2];
        double _t12 = java.lang.Math.fma(otherZ, sd[1], _t3) - java.lang.Math.fma(otherY, sd[2], _t3);
        double _t13 = java.lang.Math.fma(otherY, sd[0], _t4) - java.lang.Math.fma(otherX, sd[1], _t4);
        double _t14 = java.lang.Math.fma(otherZ, sd[0], _t5) - java.lang.Math.fma(otherX, sd[2], _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_fma(otherX, otherY, otherZ);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1])));
    }

    /** {@code angleBetween} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double angleBetween_mulAdd(double otherX, double otherY, double otherZ) {
        double[] sd = this.data;
        double _t3 = otherY * -sd[2];
        double _t4 = -(otherX * sd[1]);
        double _t5 = otherX * -sd[2];
        double _t12 = ((otherZ) * (sd[1]) + (_t3)) - ((otherY) * (sd[2]) + (_t3));
        double _t13 = ((otherY) * (sd[0]) + (_t4)) - ((otherX) * (sd[1]) + (_t4));
        double _t14 = ((otherZ) * (sd[0]) + (_t5)) - ((otherX) * (sd[2]) + (_t5));
        double _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate_mulAdd(otherX, otherY, otherZ);
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1])))));
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate_fma(double otherX, double otherY, double otherZ) {
        double[] sd = this.data;
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(sd[0], sd[1], sd[2]);
        double _t8 = otherZ * _t0;
        double _t9 = sd[1] * _t1;
        double _t10 = otherY * _t0;
        double _t11 = sd[2] * _t1;
        double _t12 = sd[0] * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        double _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        double _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate_mulAdd(double otherX, double otherY, double otherZ) {
        double[] sd = this.data;
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(sd[0], sd[1], sd[2]);
        double _t8 = otherZ * _t0;
        double _t9 = sd[1] * _t1;
        double _t10 = otherY * _t0;
        double _t11 = sd[2] * _t1;
        double _t12 = sd[0] * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        double _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        double _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.asin(sd[0]);
        dd[1] = Math.asin(sd[1]);
        dd[2] = Math.asin(sd[2]);
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
    public Double3 atan(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.atan(sd[0]);
        dd[1] = Math.atan(sd[1]);
        dd[2] = Math.atan(sd[2]);
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
    public Double3 atan2(double x, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.atan2(sd[0], x);
        dd[1] = Math.atan2(sd[1], x);
        dd[2] = Math.atan2(sd[2], x);
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
    public Double3 atan2(Double3R x, @Mutated Double3 dest) {
        double[] xData = ((Double3Impl) x).data;
        double xY = xData[1];
        double xZ = xData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.atan2(sd[0], xData[0]);
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.atan2(sd[0], xX);
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
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
    public Double3 cbrt(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.cbrt(sd[0]);
        dd[1] = Math.cbrt(sd[1]);
        dd[2] = Math.cbrt(sd[2]);
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
    public Double3 ceil(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.ceil(sd[0]);
        dd[1] = Math.ceil(sd[1]);
        dd[2] = Math.ceil(sd[2]);
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
    public Double3 clamp(double min, double max, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], min), max);
        dd[1] = Math.min(Math.max(sd[1], min), max);
        dd[2] = Math.min(Math.max(sd[2], min), max);
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
    public Double3 clamp(Double3R min, Double3R max, @Mutated Double3 dest) {
        double[] minData = ((Double3Impl) min).data;
        double[] maxData = ((Double3Impl) max).data;
        double minY = minData[1];
        double minZ = minData[2];
        double maxY = maxData[1];
        double maxZ = maxData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minData[0]), maxData[0]);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        return dest;
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
        double[] lineStartData = ((Double3Impl) lineStart).data;
        double[] lineEndData = ((Double3Impl) lineEnd).data;
        double lineStartX = lineStartData[0];
        double lineStartY = lineStartData[1];
        double lineStartZ = lineStartData[2];
        double lineEndX = lineEndData[0];
        double lineEndY = lineEndData[1];
        double lineEndZ = lineEndData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return closestPointOnLine_mulAdd(lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, sd[2] - lineStartZ, java.lang.Math.fma(_t1, sd[0] - lineStartX, _t2 * (sd[1] - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            dd[0] = java.lang.Math.fma(_t1, _t14, lineStartX);
            dd[1] = java.lang.Math.fma(_t2, _t14, lineStartY);
            dd[2] = java.lang.Math.fma(_t0, _t14, lineStartZ);
        } else {
            dd[0] = lineStartX;
            dd[1] = lineStartY;
            dd[2] = lineStartZ;
        }
        return dest;
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
        if (!org.joml2.internal.FmaSwitch.ON) return closestPointOnLine_mulAdd(lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, sd[2] - lineStartZ, java.lang.Math.fma(_t1, sd[0] - lineStartX, _t2 * (sd[1] - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            dd[0] = java.lang.Math.fma(_t1, _t14, lineStartX);
            dd[1] = java.lang.Math.fma(_t2, _t14, lineStartY);
            dd[2] = java.lang.Math.fma(_t0, _t14, lineStartZ);
        } else {
            dd[0] = lineStartX;
            dd[1] = lineStartY;
            dd[2] = lineStartZ;
        }
        return dest;
    }

    /** {@code closestPointOnLine} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 closestPointOnLine_mulAdd(double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (sd[2] - lineStartZ) + (((_t1) * (sd[0] - lineStartX) + (_t2 * (sd[1] - lineStartY))))) / _t10));
        if (_t10 > 0.0) {
            dd[0] = ((_t1) * (_t14) + (lineStartX));
            dd[1] = ((_t2) * (_t14) + (lineStartY));
            dd[2] = ((_t0) * (_t14) + (lineStartZ));
        } else {
            dd[0] = lineStartX;
            dd[1] = lineStartY;
            dd[2] = lineStartZ;
        }
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
        return sd[2] + (sd[0] + sd[1]);
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
        return Math.max(Math.max(sd[0], sd[1]), sd[2]);
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
        return Math.min(Math.min(sd[0], sd[1]), sd[2]);
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
        return sd[2] * sd[0] * sd[1];
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.copySign(sd[0], sign);
        dd[1] = Math.copySign(sd[1], sign);
        dd[2] = Math.copySign(sd[2], sign);
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
    public Double3 copySign(Double3R sign, @Mutated Double3 dest) {
        double[] signData = ((Double3Impl) sign).data;
        double signY = signData[1];
        double signZ = signData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.copySign(sd[0], signData[0]);
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.copySign(sd[0], signX);
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
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
    public Double3 cos(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.cos(sd[0]);
        dd[1] = Math.cos(sd[1]);
        dd[2] = Math.cos(sd[2]);
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
    public Double3 cosh(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.cosh(sd[0]);
        dd[1] = Math.cosh(sd[1]);
        dd[2] = Math.cosh(sd[2]);
        return dest;
    }

    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}), and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double3 cross(Double3R other, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = -(otherY * sd[2]);
            double _t4 = -(otherZ * sd[0]);
            double _t5 = -(otherX * sd[1]);
            double _buf0 = java.lang.Math.fma(otherZ, sd[1], _t3) - java.lang.Math.fma(otherY, sd[2], _t3);
            double _buf1 = java.lang.Math.fma(otherX, sd[2], _t4) - java.lang.Math.fma(otherZ, sd[0], _t4);
            dd[2] = java.lang.Math.fma(otherY, sd[0], _t5) - java.lang.Math.fma(otherX, sd[1], _t5);
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = -(otherY * sd[2]);
            double _t4 = -(otherZ * sd[0]);
            double _t5 = -(otherX * sd[1]);
            double _buf0 = ((otherZ) * (sd[1]) + (_t3)) - ((otherY) * (sd[2]) + (_t3));
            double _buf1 = ((otherX) * (sd[2]) + (_t4)) - ((otherZ) * (sd[0]) + (_t4));
            dd[2] = ((otherY) * (sd[0]) + (_t5)) - ((otherX) * (sd[1]) + (_t5));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        }
    }

    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}), and store the
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
    public Double3 cross(double otherX, double otherY, double otherZ, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = -(otherY * sd[2]);
            double _t4 = -(otherZ * sd[0]);
            double _t5 = -(otherX * sd[1]);
            double _buf0 = java.lang.Math.fma(otherZ, sd[1], _t3) - java.lang.Math.fma(otherY, sd[2], _t3);
            double _buf1 = java.lang.Math.fma(otherX, sd[2], _t4) - java.lang.Math.fma(otherZ, sd[0], _t4);
            dd[2] = java.lang.Math.fma(otherY, sd[0], _t5) - java.lang.Math.fma(otherX, sd[1], _t5);
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = -(otherY * sd[2]);
            double _t4 = -(otherZ * sd[0]);
            double _t5 = -(otherX * sd[1]);
            double _buf0 = ((otherZ) * (sd[1]) + (_t3)) - ((otherY) * (sd[2]) + (_t3));
            double _buf1 = ((otherX) * (sd[2]) + (_t4)) - ((otherZ) * (sd[0]) + (_t4));
            dd[2] = ((otherY) * (sd[0]) + (_t5)) - ((otherX) * (sd[1]) + (_t5));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.toDegrees(sd[0]);
        dd[1] = Math.toDegrees(sd[1]);
        dd[2] = Math.toDegrees(sd[2]);
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
    public double distance(Double3R other) {
        double[] otherData = ((Double3Impl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
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
        double[] otherData = ((Double3Impl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double[] sd = this.data;
            double _t0 = sd[2] - otherZ;
            double _t1 = sd[0] - otherX;
            double _t2 = sd[1] - otherY;
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
        double[] otherData = ((Double3Impl) other).data;
        double otherX = otherData[0];
        double otherY = otherData[1];
        double otherZ = otherData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1]));
        } else {
            double[] sd = this.data;
            return ((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1]))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1]));
        } else {
            double[] sd = this.data;
            return ((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1]))));
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.exp(sd[0]);
        dd[1] = Math.exp(sd[1]);
        dd[2] = Math.exp(sd[2]);
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
    public Double3 exp2(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.pow(2.0, sd[0]);
        dd[1] = Math.pow(2.0, sd[1]);
        dd[2] = Math.pow(2.0, sd[2]);
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
    public Double3 expm1(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.expm1(sd[0]);
        dd[1] = Math.expm1(sd[1]);
        dd[2] = Math.expm1(sd[2]);
        return dest;
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
    public Double3 faceforward(Double3R I, Double3R Nref, @Mutated Double3 dest) {
        double[] IData = ((Double3Impl) I).data;
        double[] NrefData = ((Double3Impl) Nref).data;
        double IX = IData[0];
        double IY = IData[1];
        double IZ = IData[2];
        double NrefX = NrefData[0];
        double NrefY = NrefData[1];
        double NrefZ = NrefData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
            return dest;
        }
    }

    /**
     * Orient this vector against the incident direction ({@code IX}, {@code IY}, {@code IZ}) as
     * judged by the reference vector ({@code NrefX}, {@code NrefY}, {@code NrefZ}) (kept when
     * {@code dot((NrefX, NrefY, NrefZ), (IX, IY, IZ))} is negative, negated otherwise) and store
     * the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
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
    public Double3 floor(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.floor(sd[0]);
        dd[1] = Math.floor(sd[1]);
        dd[2] = Math.floor(sd[2]);
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
    public Double3 fract(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(sd[0] - Math.floor(sd[0]), 0.9999999999999999);
        dd[1] = Math.min(sd[1] - Math.floor(sd[1]), 0.9999999999999999);
        dd[2] = Math.min(sd[2] - Math.floor(sd[2]), 0.9999999999999999);
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
    public Double3 hypot(double y, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.hypot(sd[0], y);
        dd[1] = Math.hypot(sd[1], y);
        dd[2] = Math.hypot(sd[2], y);
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
    public Double3 hypot(Double3R y, @Mutated Double3 dest) {
        double[] yData = ((Double3Impl) y).data;
        double yY = yData[1];
        double yZ = yData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.hypot(sd[0], yData[0]);
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.hypot(sd[0], yX);
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
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
    public Double3 inverse(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = 1.0 / sd[0];
        dd[1] = 1.0 / sd[1];
        dd[2] = 1.0 / sd[2];
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
    public Double3 inverseSqrt(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = (1.0 / java.lang.Math.sqrt(sd[0]));
        dd[1] = (1.0 / java.lang.Math.sqrt(sd[1]));
        dd[2] = (1.0 / java.lang.Math.sqrt(sd[2]));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.sqrt(java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        } else {
            double[] sd = this.data;
            return java.lang.Math.sqrt(((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1])))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
        } else {
            double[] sd = this.data;
            return ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.log(sd[0]);
        dd[1] = Math.log(sd[1]);
        dd[2] = Math.log(sd[2]);
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
    public Double3 log10(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.log10(sd[0]);
        dd[1] = Math.log10(sd[1]);
        dd[2] = Math.log10(sd[2]);
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
    public Double3 log1p(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.log1p(sd[0]);
        dd[1] = Math.log1p(sd[1]);
        dd[2] = Math.log1p(sd[2]);
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
    public Double3 log2(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.log2(sd[0]);
        dd[1] = Math.log2(sd[1]);
        dd[2] = Math.log2(sd[2]);
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
    public double manhattanDistance(Double3R other) {
        double[] otherData = ((Double3Impl) other).data;
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) + java.lang.Math.abs(sd[1] - otherData[1]) + java.lang.Math.abs(sd[2] - otherData[2]);
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
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherX) + java.lang.Math.abs(sd[1] - otherY) + java.lang.Math.abs(sd[2] - otherZ);
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
        return java.lang.Math.abs(sd[0]) + java.lang.Math.abs(sd[1]) + java.lang.Math.abs(sd[2]);
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
    public Double3 max(double scalar, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.max(sd[0], scalar);
        dd[1] = Math.max(sd[1], scalar);
        dd[2] = Math.max(sd[2], scalar);
        return dest;
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
    public Double3 max(Double3R other, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.max(sd[0], otherData[0]);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
        return dest;
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
        return dest;
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
    public Double3 min(double scalar, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(sd[0], scalar);
        dd[1] = Math.min(sd[1], scalar);
        dd[2] = Math.min(sd[2], scalar);
        return dest;
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
    public Double3 min(Double3R other, @Mutated Double3 dest) {
        double[] otherData = ((Double3Impl) other).data;
        double otherY = otherData[1];
        double otherZ = otherData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(sd[0], otherData[0]);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
        return dest;
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
        return dest;
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
    public Double3 mod(double y, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = flooredMod(sd[0], y);
        dd[1] = flooredMod(sd[1], y);
        dd[2] = flooredMod(sd[2], y);
        return dest;
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
    public Double3 mod(Double3R y, @Mutated Double3 dest) {
        double[] yData = ((Double3Impl) y).data;
        double yX = yData[0];
        double yY = yData[1];
        double yZ = yData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
        dd[2] = flooredMod(sd[2], yZ);
        return dest;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}) (for a component {@code a} and its divisor {@code b}: {@code a % b}, plus
     * {@code b} when that remainder is non-zero and its sign differs from {@code b}'s - exactly
     * Kotlin's {@code mod}) and store the result in {@code dest}.
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
        dd[2] = flooredMod(sd[2], yZ);
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
    public Double3 nextDown(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.nextDown(sd[0]);
        dd[1] = Math.nextDown(sd[1]);
        dd[2] = Math.nextDown(sd[2]);
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
    public Double3 nextUp(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.nextUp(sd[0]);
        dd[1] = Math.nextUp(sd[1]);
        dd[2] = Math.nextUp(sd[2]);
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
    public Double3 normalize(@Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dd[0] = sd[0] * _t3;
                dd[1] = sd[1] * _t3;
                dd[2] = sd[2] * _t3;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dd[0] = sd[0] * _t3;
                dd[1] = sd[1] * _t3;
                dd[2] = sd[2] * _t3;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            return dest;
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
    public Double3 normalizeMul(double length, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = ((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
            }
            return dest;
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
    public double orientedAngle(Double3R other, Double3R normal) {
        double[] otherData = ((Double3Impl) other).data;
        double[] normalData = ((Double3Impl) normal).data;
        return orientedAngle(otherData[0], otherData[1], otherData[2], normalData[0], normalData[1], normalData[2]);
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
        if (!org.joml2.internal.FmaSwitch.ON) return orientedAngle_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double[] sd = this.data;
        double _t4 = -(otherX * sd[1]);
        double _t5 = -(otherZ * sd[0]);
        double _t6 = -(otherY * sd[2]);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = java.lang.Math.fma(otherY, sd[0], _t4) - java.lang.Math.fma(otherX, sd[1], _t4);
        double _t17 = java.lang.Math.fma(otherX, sd[2], _t5) - java.lang.Math.fma(otherZ, sd[0], _t5);
        double _t18 = java.lang.Math.fma(otherZ, sd[1], _t6) - java.lang.Math.fma(otherY, sd[2], _t6);
        double _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_fma(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, sd[2], java.lang.Math.fma(otherX, sd[0], otherY * sd[1])));
        return java.lang.Math.fma(_t16, normalZ * _t7, java.lang.Math.fma(_t17, normalY * _t7, _t18 * (normalX * _t7))) < 0.0 ? -_t24 : _t24;
    }

    /** {@code orientedAngle} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double orientedAngle_mulAdd(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double[] sd = this.data;
        double _t4 = -(otherX * sd[1]);
        double _t5 = -(otherZ * sd[0]);
        double _t6 = -(otherY * sd[2]);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = ((otherY) * (sd[0]) + (_t4)) - ((otherX) * (sd[1]) + (_t4));
        double _t17 = ((otherX) * (sd[2]) + (_t5)) - ((otherZ) * (sd[0]) + (_t5));
        double _t18 = ((otherZ) * (sd[1]) + (_t6)) - ((otherY) * (sd[2]) + (_t6));
        double _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return orientedAngle_degenerate_mulAdd(otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (sd[2]) + (((otherX) * (sd[0]) + (otherY * sd[1])))));
        return ((_t16) * (normalZ * _t7) + (((_t17) * (normalY * _t7) + (_t18 * (normalX * _t7))))) < 0.0 ? -_t24 : _t24;
    }

    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate_fma(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double[] sd = this.data;
        double _t0 = unitScale(normalX, normalY, normalZ);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t9 = otherY * _t1;
        double _t10 = sd[0] * _t2;
        double _t11 = otherX * _t1;
        double _t12 = sd[1] * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = sd[2] * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        double _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        double _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        double _t33 = unitScale(_t31, _t32, _t30);
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(normalZ * _t0, _t39, java.lang.Math.fma(normalX * _t0, _t40, normalY * _t0 * _t41)) < 0.0 ? -_t46 : _t46;
    }

    /**
     * Out-of-range path of {@code orientedAngle}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private double orientedAngle_degenerate_mulAdd(double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double[] sd = this.data;
        double _t0 = unitScale(normalX, normalY, normalZ);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(sd[0], sd[1], sd[2]);
        double _t9 = otherY * _t1;
        double _t10 = sd[0] * _t2;
        double _t11 = otherX * _t1;
        double _t12 = sd[1] * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = sd[2] * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        double _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        double _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        double _t33 = unitScale(_t31, _t32, _t30);
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((normalZ * _t0) * (_t39) + (((normalX * _t0) * (_t40) + (normalY * _t0 * _t41)))) < 0.0 ? -_t46 : _t46;
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
        double[] rowData = ((Double3Impl) row).data;
        double rowX = rowData[0];
        double rowY = rowData[1];
        double rowZ = rowData[2];
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rowX * sd[0];
        dd[1] = rowX * sd[1];
        dd[2] = rowX * sd[2];
        dd[3] = rowY * sd[0];
        dd[4] = rowY * sd[1];
        dd[5] = rowY * sd[2];
        dd[6] = rowZ * sd[0];
        dd[7] = rowZ * sd[1];
        dd[8] = rowZ * sd[2];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = rowX * sd[0];
        dd[1] = rowX * sd[1];
        dd[2] = rowX * sd[2];
        dd[3] = rowY * sd[0];
        dd[4] = rowY * sd[1];
        dd[5] = rowY * sd[2];
        dd[6] = rowZ * sd[0];
        dd[7] = rowZ * sd[1];
        dd[8] = rowZ * sd[2];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        if (java.lang.Math.abs(sd[2]) < java.lang.Math.abs(sd[0])) {
            double _buf0 = sd[1];
            dd[1] = -sd[0];
            dd[2] = 0.0;
            dd[0] = _buf0;
        } else {
            dd[0] = 0.0;
            double _buf0 = sd[2];
            dd[2] = -sd[1];
            dd[1] = _buf0;
        }
        return dest;
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
    public Double3 pow(double exponent, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponent);
        dd[1] = Math.pow(sd[1], exponent);
        dd[2] = Math.pow(sd[2], exponent);
        return dest;
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
    public Double3 pow(Double3R exponent, @Mutated Double3 dest) {
        double[] exponentData = ((Double3Impl) exponent).data;
        double exponentY = exponentData[1];
        double exponentZ = exponentData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponentData[0]);
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
        return dest;
    }

    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 pow(double exponentX, double exponentY, double exponentZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponentX);
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
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
    public Double3 project(Double3R onto, @Mutated Double3 dest) {
        double[] ontoData = ((Double3Impl) onto).data;
        double ontoX = ontoData[0];
        double ontoY = ontoData[1];
        double ontoZ = ontoData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t7 = java.lang.Math.fma(ontoZ, sd[2], java.lang.Math.fma(ontoX, sd[0], ontoY * sd[1])) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            dd[0] = ontoX * _t7;
            dd[1] = ontoY * _t7;
            dd[2] = ontoZ * _t7;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t7 = ((ontoZ) * (sd[2]) + (((ontoX) * (sd[0]) + (ontoY * sd[1])))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            dd[0] = ontoX * _t7;
            dd[1] = ontoY * _t7;
            dd[2] = ontoZ * _t7;
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t7 = java.lang.Math.fma(ontoZ, sd[2], java.lang.Math.fma(ontoX, sd[0], ontoY * sd[1])) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            dd[0] = ontoX * _t7;
            dd[1] = ontoY * _t7;
            dd[2] = ontoZ * _t7;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t7 = ((ontoZ) * (sd[2]) + (((ontoX) * (sd[0]) + (ontoY * sd[1])))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            dd[0] = ontoX * _t7;
            dd[1] = ontoY * _t7;
            dd[2] = ontoZ * _t7;
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
    public Double3 projectOnPlane(Double3R normal, @Mutated Double3 dest) {
        double[] normalData = ((Double3Impl) normal).data;
        double normalX = normalData[0];
        double normalY = normalData[1];
        double normalZ = normalData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1]));
            dd[0] = java.lang.Math.fma(-normalX, _t2, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t2, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t2, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = ((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))));
            dd[0] = ((sd[0]) - (normalX) * (_t2));
            dd[1] = ((sd[1]) - (normalY) * (_t2));
            dd[2] = ((sd[2]) - (normalZ) * (_t2));
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1]));
            dd[0] = java.lang.Math.fma(-normalX, _t2, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t2, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t2, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t2 = ((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))));
            dd[0] = ((sd[0]) - (normalX) * (_t2));
            dd[1] = ((sd[1]) - (normalY) * (_t2));
            dd[2] = ((sd[2]) - (normalZ) * (_t2));
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
    public Double3 radians(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.toRadians(sd[0]);
        dd[1] = Math.toRadians(sd[1]);
        dd[2] = Math.toRadians(sd[2]);
        return dest;
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
    public Double3 reflect(Double3R normal, @Mutated Double3 dest) {
        double[] normalData = ((Double3Impl) normal).data;
        double normalX = normalData[0];
        double normalY = normalData[1];
        double normalZ = normalData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1]));
            dd[0] = java.lang.Math.fma(-normalX, _t3, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t3, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t3, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = 2.0 * ((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))));
            dd[0] = ((sd[0]) - (normalX) * (_t3));
            dd[1] = ((sd[1]) - (normalY) * (_t3));
            dd[2] = ((sd[2]) - (normalZ) * (_t3));
            return dest;
        }
    }

    /**
     * Reflect this vector off the plane with the given normal (its component along the normal is
     * negated) and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1]));
            dd[0] = java.lang.Math.fma(-normalX, _t3, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t3, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t3, sd[2]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t3 = 2.0 * ((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))));
            dd[0] = ((sd[0]) - (normalX) * (_t3));
            dd[1] = ((sd[1]) - (normalY) * (_t3));
            dd[2] = ((sd[2]) - (normalZ) * (_t3));
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
    public Double3 refract(Double3R normal, double eta, @Mutated Double3 dest) {
        double[] normalData = ((Double3Impl) normal).data;
        double normalX = normalData[0];
        double normalY = normalData[1];
        double normalZ = normalData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return refract_mulAdd(normalX, normalY, normalZ, eta, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t3 = java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1]));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dd[0] = java.lang.Math.fma(eta, sd[0], -(normalX * _t10));
            dd[1] = java.lang.Math.fma(eta, sd[1], -(normalY * _t10));
            dd[2] = java.lang.Math.fma(eta, sd[2], -(normalZ * _t10));
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 refract(double normalX, double normalY, double normalZ, double eta, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return refract_mulAdd(normalX, normalY, normalZ, eta, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t3 = java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1]));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dd[0] = java.lang.Math.fma(eta, sd[0], -(normalX * _t10));
            dd[1] = java.lang.Math.fma(eta, sd[1], -(normalY * _t10));
            dd[2] = java.lang.Math.fma(eta, sd[2], -(normalZ * _t10));
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** {@code refract} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 refract_mulAdd(double normalX, double normalY, double normalZ, double eta, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t3 = ((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))));
        double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
        double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
        if (_t7 >= 0.0) {
            dd[0] = ((eta) * (sd[0]) - (normalX * _t10));
            dd[1] = ((eta) * (sd[1]) - (normalY * _t10));
            dd[2] = ((eta) * (sd[2]) - (normalZ * _t10));
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
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
    public Double3 round(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.rint(sd[0]);
        dd[1] = Math.rint(sd[1]);
        dd[2] = Math.rint(sd[2]);
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
    public Double3 sign(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
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
    public Double3 sin(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.sin(sd[0]);
        dd[1] = Math.sin(sd[1]);
        dd[2] = Math.sin(sd[2]);
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
    public Double3 sinh(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.sinh(sd[0]);
        dd[1] = Math.sinh(sd[1]);
        dd[2] = Math.sinh(sd[2]);
        return dest;
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
    public Double3 smoothstep(double edge0, double edge1, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t10 = Math.max(0.0, Math.min(1.0, (sd[0] - edge0) * _t0_inv));
            double _t11 = Math.max(0.0, Math.min(1.0, (sd[1] - edge0) * _t0_inv));
            double _t12 = Math.max(0.0, Math.min(1.0, (sd[2] - edge0) * _t0_inv));
            dd[0] = java.lang.Math.fma(-2.0, _t10, 3.0) * _t10 * _t10;
            dd[1] = java.lang.Math.fma(-2.0, _t11, 3.0) * _t11 * _t11;
            dd[2] = java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0_inv = 1.0 / (edge1 - edge0);
            double _t10 = Math.max(0.0, Math.min(1.0, (sd[0] - edge0) * _t0_inv));
            double _t11 = Math.max(0.0, Math.min(1.0, (sd[1] - edge0) * _t0_inv));
            double _t12 = Math.max(0.0, Math.min(1.0, (sd[2] - edge0) * _t0_inv));
            dd[0] = ((3.0) - (2.0) * (_t10)) * _t10 * _t10;
            dd[1] = ((3.0) - (2.0) * (_t11)) * _t11 * _t11;
            dd[2] = ((3.0) - (2.0) * (_t12)) * _t12 * _t12;
            return dest;
        }
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
    public Double3 smoothstep(Double3R edge0, Double3R edge1, @Mutated Double3 dest) {
        double[] edge0Data = ((Double3Impl) edge0).data;
        double[] edge1Data = ((Double3Impl) edge1).data;
        return smoothstep(edge0Data[0], edge0Data[1], edge0Data[2], edge1Data[0], edge1Data[1], edge1Data[2], dest);
    }

    /**
     * Compute the smooth Hermite step of each component of this vector, ramping from 0 at
     * ({@code edge0X}, {@code edge0Y}, {@code edge0Z}) to 1 at ({@code edge1X}, {@code edge1Y},
     * {@code edge1Z}): 0 at or beyond ({@code edge0X}, {@code edge0Y}, {@code edge0Z}) on the side
     * away from ({@code edge1X}, {@code edge1Y}, {@code edge1Z}), 1 at or beyond ({@code edge1X},
     * {@code edge1Y}, {@code edge1Z}) (a falling step when ({@code edge0X}, {@code edge0Y},
     * {@code edge0Z}) is the greater), and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t12 = Math.max(0.0, Math.min(1.0, (sd[0] - edge0X) / (edge1X - edge0X)));
            double _t13 = Math.max(0.0, Math.min(1.0, (sd[1] - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = Math.max(0.0, Math.min(1.0, (sd[2] - edge0Z) / (edge1Z - edge0Z)));
            dd[0] = java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12;
            dd[1] = java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13;
            dd[2] = java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t12 = Math.max(0.0, Math.min(1.0, (sd[0] - edge0X) / (edge1X - edge0X)));
            double _t13 = Math.max(0.0, Math.min(1.0, (sd[1] - edge0Y) / (edge1Y - edge0Y)));
            double _t14 = Math.max(0.0, Math.min(1.0, (sd[2] - edge0Z) / (edge1Z - edge0Z)));
            dd[0] = ((3.0) - (2.0) * (_t12)) * _t12 * _t12;
            dd[1] = ((3.0) - (2.0) * (_t13)) * _t13 * _t13;
            dd[2] = ((3.0) - (2.0) * (_t14)) * _t14 * _t14;
            return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.sqrt(sd[0]);
        dd[1] = java.lang.Math.sqrt(sd[1]);
        dd[2] = java.lang.Math.sqrt(sd[2]);
        return dest;
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
    public Double3 step(double edge, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] < edge ? 0.0 : 1.0;
        dd[1] = sd[1] < edge ? 0.0 : 1.0;
        dd[2] = sd[2] < edge ? 0.0 : 1.0;
        return dest;
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
    public Double3 step(Double3R edge, @Mutated Double3 dest) {
        double[] edgeData = ((Double3Impl) edge).data;
        double edgeY = edgeData[1];
        double edgeZ = edgeData[2];
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] < edgeData[0] ? 0.0 : 1.0;
        dd[1] = sd[1] < edgeY ? 0.0 : 1.0;
        dd[2] = sd[2] < edgeZ ? 0.0 : 1.0;
        return dest;
    }

    /**
     * Compute, for each component of this vector, {@code 0} when it is smaller than the
     * corresponding component of the given edge and {@code 1} otherwise, and store the result in
     * {@code dest}.
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] < edgeX ? 0.0 : 1.0;
        dd[1] = sd[1] < edgeY ? 0.0 : 1.0;
        dd[2] = sd[2] < edgeZ ? 0.0 : 1.0;
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
    public Double3 tan(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.tan(sd[0]);
        dd[1] = Math.tan(sd[1]);
        dd[2] = Math.tan(sd[2]);
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
    public Double3 tanh(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.tanh(sd[0]);
        dd[1] = Math.tanh(sd[1]);
        dd[2] = Math.tanh(sd[2]);
        return dest;
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize((p1 - this) x (p2 - this))} - it points to the side from which the vertices
     * {@code this}, {@code p1}, {@code p2} appear counter-clockwise (a degenerate triangle yields
     * the zero vector), and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 triangleNormal(Double3R p1, Double3R p2, @Mutated Double3 dest) {
        double[] p1Data = ((Double3Impl) p1).data;
        double[] p2Data = ((Double3Impl) p2).data;
        return triangleNormal(p1Data[0], p1Data[1], p1Data[2], p2Data[0], p2Data[1], p2Data[2], dest);
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize(((p1X, p1Y, p1Z) - this) x ((p2X, p2Y, p2Z) - this))} - it points to the
     * side from which the vertices {@code this}, ({@code p1X}, {@code p1Y}, {@code p1Z}),
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) appear counter-clockwise (a degenerate triangle
     * yields the zero vector), and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 triangleNormal(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return triangleNormal_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = p1Y - sd[1];
        double _t1 = p2Z - sd[2];
        double _t2 = p1Z - sd[2];
        double _t3 = p2Y - sd[1];
        double _t4 = p1X - sd[0];
        double _t5 = p2X - sd[0];
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        double _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        double _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        double _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return triangleNormal_degenerate_fma(p1X, p1Y, p1Z, p2X, p2Y, p2Z, dest);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dd[0] = _t18 * _t25;
        dd[1] = _t20 * _t25;
        dd[2] = _t19 * _t25;
        return dest;
    }

    /** {@code triangleNormal} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 triangleNormal_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = p1Y - sd[1];
        double _t1 = p2Z - sd[2];
        double _t2 = p1Z - sd[2];
        double _t3 = p2Y - sd[1];
        double _t4 = p1X - sd[0];
        double _t5 = p2X - sd[0];
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        double _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        double _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        double _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return triangleNormal_degenerate_mulAdd(p1X, p1Y, p1Z, p2X, p2Y, p2Z, dest);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dd[0] = _t18 * _t25;
        dd[1] = _t20 * _t25;
        dd[2] = _t19 * _t25;
        return dest;
    }

    /**
     * Out-of-range path of {@code triangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 triangleNormal_degenerate_fma(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(sd[2]), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(sd[1]))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = sd[0] * _t19;
        double _t32 = sd[1] * _t19;
        double _t34 = sd[2] * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        return triangleNormal_degenerate_s17a4970e_1_fma(dest, ((Double3Impl) dest).data, _t52, _t53, _t54, _t55, _t56, _t57, -(_t57 * _t53), -(_t52 * _t56), java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61));
    }

    /**
     * Out-of-range path of {@code triangleNormal}: its methods leave here when the cross product
     * they form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 triangleNormal_degenerate_mulAdd(double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, @Mutated Double3 dest) {
        double[] sd = this.data;
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(sd[2]), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(sd[1]))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = sd[0] * _t19;
        double _t32 = sd[1] * _t19;
        double _t34 = sd[2] * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        return triangleNormal_degenerate_s17a4970e_1_mulAdd(dest, ((Double3Impl) dest).data, _t52, _t53, _t54, _t55, _t56, _t57, -(_t57 * _t53), -(_t52 * _t56), ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 triangleNormal_degenerate_s17a4970e_1_fma(Double3 dest, double[] dd, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t62, double _t63, double _t70) {
        double _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        double _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            dd[0] = _t83 * _t78;
            dd[1] = _t83 * _t79;
            dd[2] = _t83 * _t77;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
        return dest;
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 triangleNormal_degenerate_s17a4970e_1_mulAdd(Double3 dest, double[] dd, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t62, double _t63, double _t70) {
        double _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        double _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            dd[0] = _t83 * _t78;
            dd[1] = _t83 * _t79;
            dd[2] = _t83 * _t77;
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
        }
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
    public Double3 trunc(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] >= 0.0 ? Math.floor(sd[0]) : Math.ceil(sd[0]);
        dd[1] = sd[1] >= 0.0 ? Math.floor(sd[1]) : Math.ceil(sd[1]);
        dd[2] = sd[2] >= 0.0 ? Math.floor(sd[2]) : Math.ceil(sd[2]);
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
    public Double3 ulp(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.ulp(sd[0]);
        dd[1] = Math.ulp(sd[1]);
        dd[2] = Math.ulp(sd[2]);
        return dest;
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0}, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz0(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 0.0;
        return dest;
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1}, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz1(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1.0;
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
    public Double3 preMul(Double3x3R mat, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] matData = ((Double3x3Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = java.lang.Math.fma(matData[6], sd[2], java.lang.Math.fma(matData[0], sd[0], matData[3] * sd[1]));
            double _buf1 = java.lang.Math.fma(matData[7], sd[2], java.lang.Math.fma(matData[1], sd[0], matData[4] * sd[1]));
            dd[2] = java.lang.Math.fma(matData[8], sd[2], java.lang.Math.fma(matData[2], sd[0], matData[5] * sd[1]));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        } else {
            double[] sd = this.data;
            double[] matData = ((Double3x3Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = ((matData[6]) * (sd[2]) + (((matData[0]) * (sd[0]) + (matData[3] * sd[1]))));
            double _buf1 = ((matData[7]) * (sd[2]) + (((matData[1]) * (sd[0]) + (matData[4] * sd[1]))));
            dd[2] = ((matData[8]) * (sd[2]) + (((matData[2]) * (sd[0]) + (matData[5] * sd[1]))));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        }
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulDirection(Double3x4R mat, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] matData = ((Double3x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = java.lang.Math.fma(matData[2], sd[2], java.lang.Math.fma(matData[0], sd[0], matData[1] * sd[1]));
            double _buf1 = java.lang.Math.fma(matData[6], sd[2], java.lang.Math.fma(matData[4], sd[0], matData[5] * sd[1]));
            dd[2] = java.lang.Math.fma(matData[10], sd[2], java.lang.Math.fma(matData[8], sd[0], matData[9] * sd[1]));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        } else {
            double[] sd = this.data;
            double[] matData = ((Double3x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = ((matData[2]) * (sd[2]) + (((matData[0]) * (sd[0]) + (matData[1] * sd[1]))));
            double _buf1 = ((matData[6]) * (sd[2]) + (((matData[4]) * (sd[0]) + (matData[5] * sd[1]))));
            dd[2] = ((matData[10]) * (sd[2]) + (((matData[8]) * (sd[0]) + (matData[9] * sd[1]))));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        }
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulDirection(Double4x4R mat, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] matData = ((Double4x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = java.lang.Math.fma(matData[8], sd[2], java.lang.Math.fma(matData[0], sd[0], matData[4] * sd[1]));
            double _buf1 = java.lang.Math.fma(matData[9], sd[2], java.lang.Math.fma(matData[1], sd[0], matData[5] * sd[1]));
            dd[2] = java.lang.Math.fma(matData[10], sd[2], java.lang.Math.fma(matData[2], sd[0], matData[6] * sd[1]));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        } else {
            double[] sd = this.data;
            double[] matData = ((Double4x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = ((matData[8]) * (sd[2]) + (((matData[0]) * (sd[0]) + (matData[4] * sd[1]))));
            double _buf1 = ((matData[9]) * (sd[2]) + (((matData[1]) * (sd[0]) + (matData[5] * sd[1]))));
            dd[2] = ((matData[10]) * (sd[2]) + (((matData[2]) * (sd[0]) + (matData[6] * sd[1]))));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        }
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulPosition(Double3x4R mat, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] matData = ((Double3x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = java.lang.Math.fma(matData[0], sd[0], java.lang.Math.fma(matData[1], sd[1], java.lang.Math.fma(matData[2], sd[2], matData[3])));
            double _buf1 = java.lang.Math.fma(matData[4], sd[0], java.lang.Math.fma(matData[5], sd[1], java.lang.Math.fma(matData[6], sd[2], matData[7])));
            dd[2] = java.lang.Math.fma(matData[8], sd[0], java.lang.Math.fma(matData[9], sd[1], java.lang.Math.fma(matData[10], sd[2], matData[11])));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        } else {
            double[] sd = this.data;
            double[] matData = ((Double3x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = ((matData[0]) * (sd[0]) + (((matData[1]) * (sd[1]) + (((matData[2]) * (sd[2]) + (matData[3]))))));
            double _buf1 = ((matData[4]) * (sd[0]) + (((matData[5]) * (sd[1]) + (((matData[6]) * (sd[2]) + (matData[7]))))));
            dd[2] = ((matData[8]) * (sd[0]) + (((matData[9]) * (sd[1]) + (((matData[10]) * (sd[2]) + (matData[11]))))));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        }
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulPosition(Double4x4R mat, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] matData = ((Double4x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = java.lang.Math.fma(matData[0], sd[0], java.lang.Math.fma(matData[4], sd[1], java.lang.Math.fma(matData[8], sd[2], matData[12])));
            double _buf1 = java.lang.Math.fma(matData[1], sd[0], java.lang.Math.fma(matData[5], sd[1], java.lang.Math.fma(matData[9], sd[2], matData[13])));
            dd[2] = java.lang.Math.fma(matData[2], sd[0], java.lang.Math.fma(matData[6], sd[1], java.lang.Math.fma(matData[10], sd[2], matData[14])));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        } else {
            double[] sd = this.data;
            double[] matData = ((Double4x4Impl) mat).data;
            double[] dd = ((Double3Impl) dest).data;
            double _buf0 = ((matData[0]) * (sd[0]) + (((matData[4]) * (sd[1]) + (((matData[8]) * (sd[2]) + (matData[12]))))));
            double _buf1 = ((matData[1]) * (sd[0]) + (((matData[5]) * (sd[1]) + (((matData[9]) * (sd[2]) + (matData[13]))))));
            dd[2] = ((matData[2]) * (sd[0]) + (((matData[6]) * (sd[1]) + (((matData[10]) * (sd[2]) + (matData[14]))))));
            dd[0] = _buf0;
            dd[1] = _buf1;
            return dest;
        }
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1},
     * then perform a perspective divide - i.e. compute {@code r = mat * (this, 1)} and return
     * {@code r.xyz / r.w}, and store the result in {@code dest}.
     * <p>
     * Valid input: this vector must not be mapped to {@code w = 0} by {@code mat}.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 preMulProject(Double4x4R mat, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMulProject_mulAdd(mat, dest);
        double[] sd = this.data;
        double[] matData = ((Double4x4Impl) mat).data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2_inv = 1.0 / java.lang.Math.fma(matData[3], sd[0], java.lang.Math.fma(matData[7], sd[1], java.lang.Math.fma(matData[11], sd[2], matData[15])));
        double _buf0 = java.lang.Math.fma(matData[0], sd[0], java.lang.Math.fma(matData[4], sd[1], java.lang.Math.fma(matData[8], sd[2], matData[12]))) * _t2_inv;
        double _buf1 = java.lang.Math.fma(matData[1], sd[0], java.lang.Math.fma(matData[5], sd[1], java.lang.Math.fma(matData[9], sd[2], matData[13]))) * _t2_inv;
        dd[2] = java.lang.Math.fma(matData[2], sd[0], java.lang.Math.fma(matData[6], sd[1], java.lang.Math.fma(matData[10], sd[2], matData[14]))) * _t2_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }

    /** {@code preMulProject} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 preMulProject_mulAdd(Double4x4R mat, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] matData = ((Double4x4Impl) mat).data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2_inv = 1.0 / ((matData[3]) * (sd[0]) + (((matData[7]) * (sd[1]) + (((matData[11]) * (sd[2]) + (matData[15]))))));
        double _buf0 = ((matData[0]) * (sd[0]) + (((matData[4]) * (sd[1]) + (((matData[8]) * (sd[2]) + (matData[12])))))) * _t2_inv;
        double _buf1 = ((matData[1]) * (sd[0]) + (((matData[5]) * (sd[1]) + (((matData[9]) * (sd[2]) + (matData[13])))))) * _t2_inv;
        dd[2] = ((matData[2]) * (sd[0]) + (((matData[6]) * (sd[1]) + (((matData[10]) * (sd[2]) + (matData[14])))))) * _t2_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }

    /**
     * Rotate this vector by the quaternion {@code quat}, i.e. compute {@code q * this * q^-1}, and
     * store the result in {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotate(DoubleQuatR quat, @Mutated Double3 dest) {
        double[] quatData = ((DoubleQuatImpl) quat).data;
        double quatX = quatData[0];
        double quatY = quatData[1];
        double quatZ = quatData[2];
        double quatW = quatData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(quatX, quatY, quatZ, quatW, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(quatX, sd[1], -(quatY * sd[0]));
        double _t10 = 2.0 * java.lang.Math.fma(quatZ, sd[0], -(quatX * sd[2]));
        double _t11 = 2.0 * java.lang.Math.fma(quatY, sd[2], -(quatZ * sd[1]));
        dd[0] = java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, sd[0])));
        dd[1] = java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, sd[1])));
        dd[2] = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, sd[2])));
        return dest;
    }

    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}), i.e. compute {@code q * this * q^-1}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(quatX, quatY, quatZ, quatW, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(quatX, sd[1], -(quatY * sd[0]));
        double _t10 = 2.0 * java.lang.Math.fma(quatZ, sd[0], -(quatX * sd[2]));
        double _t11 = 2.0 * java.lang.Math.fma(quatY, sd[2], -(quatZ * sd[1]));
        dd[0] = java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, sd[0])));
        dd[1] = java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, sd[1])));
        dd[2] = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, sd[2])));
        return dest;
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotate_mulAdd(double quatX, double quatY, double quatZ, double quatW, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((quatX) * (sd[1]) - (quatY * sd[0]));
        double _t10 = 2.0 * ((quatZ) * (sd[0]) - (quatX * sd[2]));
        double _t11 = 2.0 * ((quatY) * (sd[2]) - (quatZ * sd[1]));
        dd[0] = ((quatY) * (_t9) + (((((quatW) * (_t11) + (sd[0]))) - (quatZ) * (_t10))));
        dd[1] = ((quatZ) * (_t11) + (((((quatW) * (_t10) + (sd[1]))) - (quatX) * (_t9))));
        dd[2] = ((quatX) * (_t10) + (((((quatW) * (_t9) + (sd[2]))) - (quatY) * (_t11))));
        return dest;
    }

    /**
     * Rotate this vector by the quaternion {@code quat} about the point {@code pivot}, i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p}, and store the result in
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
        double[] quatData = ((DoubleQuatImpl) quat).data;
        double[] pivotData = ((Double3Impl) pivot).data;
        double quatX = quatData[0];
        double quatY = quatData[1];
        double quatZ = quatData[2];
        double quatW = quatData[3];
        double pivotX = pivotData[0];
        double pivotY = pivotData[1];
        double pivotZ = pivotData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAround_mulAdd(quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[1] - pivotY;
        double _t1 = sd[0] - pivotX;
        double _t2 = sd[2] - pivotZ;
        double _t12 = 2.0 * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        dd[0] = java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, sd[0])));
        dd[1] = java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, sd[1])));
        dd[2] = java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, sd[2])));
        return dest;
    }

    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}) about the point ({@code pivotX}, {@code pivotY}, {@code pivotZ}), i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p}, and store the result in
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAround_mulAdd(quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[1] - pivotY;
        double _t1 = sd[0] - pivotX;
        double _t2 = sd[2] - pivotZ;
        double _t12 = 2.0 * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        dd[0] = java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, sd[0])));
        dd[1] = java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, sd[1])));
        dd[2] = java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, sd[2])));
        return dest;
    }

    /** {@code rotateAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateAround_mulAdd(double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[1] - pivotY;
        double _t1 = sd[0] - pivotX;
        double _t2 = sd[2] - pivotZ;
        double _t12 = 2.0 * ((quatX) * (_t0) - (quatY * _t1));
        double _t13 = 2.0 * ((quatZ) * (_t1) - (quatX * _t2));
        double _t14 = 2.0 * ((quatY) * (_t2) - (quatZ * _t0));
        dd[0] = ((quatY) * (_t12) + (((((quatW) * (_t14) + (sd[0]))) - (quatZ) * (_t13))));
        dd[1] = ((quatZ) * (_t14) + (((((quatW) * (_t13) + (sd[1]))) - (quatX) * (_t12))));
        dd[2] = ((quatX) * (_t13) + (((((quatW) * (_t12) + (sd[2]))) - (quatY) * (_t14))));
        return dest;
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
        double[] axisData = ((Double3Impl) axis).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateAxis_fma(double angle, double axisX, double axisY, double axisZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(axisZ, sd[2], java.lang.Math.fma(axisX, sd[0], axisY * sd[1]));
        double _buf0 = java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(sd[0], _t0, java.lang.Math.fma(axisY, sd[2], -(axisZ * sd[1])) * _t1));
        double _buf1 = java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(sd[1], _t0, java.lang.Math.fma(axisZ, sd[0], -(axisX * sd[2])) * _t1));
        dd[2] = java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(sd[2], _t0, java.lang.Math.fma(axisX, sd[1], -(axisY * sd[0])) * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((axisZ) * (sd[2]) + (((axisX) * (sd[0]) + (axisY * sd[1]))));
        double _buf0 = ((_t2) * (axisX * _t5) + (((sd[0]) * (_t0) + (((axisY) * (sd[2]) - (axisZ * sd[1])) * _t1))));
        double _buf1 = ((_t2) * (axisY * _t5) + (((sd[1]) * (_t0) + (((axisZ) * (sd[0]) - (axisX * sd[2])) * _t1))));
        dd[2] = ((_t2) * (axisZ * _t5) + (((sd[2]) * (_t0) + (((axisX) * (sd[1]) - (axisY * sd[0])) * _t1))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        double[] axisData = ((Double3Impl) axis).data;
        double[] pivotData = ((Double3Impl) pivot).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        double pivotX = pivotData[0];
        double pivotY = pivotData[1];
        double pivotZ = pivotData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAxisAround_mulAdd(angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = sd[0] - pivotX;
        double _t4 = sd[2] - pivotZ;
        double _t5 = sd[1] - pivotY;
        double _t8 = java.lang.Math.fma(axisZ, _t4, java.lang.Math.fma(axisX, _t3, axisY * _t5));
        dd[0] = java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(axisY, _t4, -(axisZ * _t5)), _t1, java.lang.Math.fma(_t2, axisX * _t8, pivotX)));
        dd[1] = java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t3, -(axisX * _t4)), _t1, java.lang.Math.fma(_t2, axisY * _t8, pivotY)));
        dd[2] = java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(axisX, _t5, -(axisY * _t3)), _t1, java.lang.Math.fma(_t2, axisZ * _t8, pivotZ)));
        return dest;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAxisAround_mulAdd(angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = sd[0] - pivotX;
        double _t4 = sd[2] - pivotZ;
        double _t5 = sd[1] - pivotY;
        double _t8 = java.lang.Math.fma(axisZ, _t4, java.lang.Math.fma(axisX, _t3, axisY * _t5));
        dd[0] = java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(axisY, _t4, -(axisZ * _t5)), _t1, java.lang.Math.fma(_t2, axisX * _t8, pivotX)));
        dd[1] = java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t3, -(axisX * _t4)), _t1, java.lang.Math.fma(_t2, axisY * _t8, pivotY)));
        dd[2] = java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(axisX, _t5, -(axisY * _t3)), _t1, java.lang.Math.fma(_t2, axisZ * _t8, pivotZ)));
        return dest;
    }

    /** {@code rotateAxisAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateAxisAround_mulAdd(double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = sd[0] - pivotX;
        double _t4 = sd[2] - pivotZ;
        double _t5 = sd[1] - pivotY;
        double _t8 = ((axisZ) * (_t4) + (((axisX) * (_t3) + (axisY * _t5))));
        dd[0] = ((_t3) * (_t0) + (((((axisY) * (_t4) - (axisZ * _t5))) * (_t1) + (((_t2) * (axisX * _t8) + (pivotX))))));
        dd[1] = ((_t5) * (_t0) + (((((axisZ) * (_t3) - (axisX * _t4))) * (_t1) + (((_t2) * (axisY * _t8) + (pivotY))))));
        dd[2] = ((_t4) * (_t0) + (((((axisX) * (_t5) - (axisY * _t3))) * (_t1) + (((_t2) * (axisZ * _t8) + (pivotZ))))));
        return dest;
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
        double[] quatData = ((DoubleQuatImpl) quat).data;
        double quatX = quatData[0];
        double quatY = quatData[1];
        double quatZ = quatData[2];
        double quatW = quatData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return rotateInverse_mulAdd(quatX, quatY, quatZ, quatW, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(quatX, sd[2], -(quatZ * sd[0]));
        double _t10 = 2.0 * java.lang.Math.fma(quatY, sd[0], -(quatX * sd[1]));
        double _t11 = 2.0 * java.lang.Math.fma(quatZ, sd[1], -(quatY * sd[2]));
        dd[0] = java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, sd[0])));
        dd[1] = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, sd[1])));
        dd[2] = java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, sd[2])));
        return dest;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateInverse_mulAdd(quatX, quatY, quatZ, quatW, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(quatX, sd[2], -(quatZ * sd[0]));
        double _t10 = 2.0 * java.lang.Math.fma(quatY, sd[0], -(quatX * sd[1]));
        double _t11 = 2.0 * java.lang.Math.fma(quatZ, sd[1], -(quatY * sd[2]));
        dd[0] = java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, sd[0])));
        dd[1] = java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, sd[1])));
        dd[2] = java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, sd[2])));
        return dest;
    }

    /** {@code rotateInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 rotateInverse_mulAdd(double quatX, double quatY, double quatZ, double quatW, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((quatX) * (sd[2]) - (quatZ * sd[0]));
        double _t10 = 2.0 * ((quatY) * (sd[0]) - (quatX * sd[1]));
        double _t11 = 2.0 * ((quatZ) * (sd[1]) - (quatY * sd[2]));
        dd[0] = ((quatZ) * (_t9) + (((((quatW) * (_t11) + (sd[0]))) - (quatY) * (_t10))));
        dd[1] = ((quatX) * (_t10) + (((((quatW) * (_t9) + (sd[1]))) - (quatZ) * (_t11))));
        dd[2] = ((quatY) * (_t11) + (((((quatW) * (_t10) + (sd[2]))) - (quatX) * (_t9))));
        return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dd[0] = sd[0];
            double _buf0 = java.lang.Math.fma(sd[1], _t0, -(sd[2] * _t1));
            dd[2] = java.lang.Math.fma(sd[1], _t1, sd[2] * _t0);
            dd[1] = _buf0;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dd[0] = sd[0];
            double _buf0 = ((sd[1]) * (_t0) - (sd[2] * _t1));
            dd[2] = ((sd[1]) * (_t1) + (sd[2] * _t0));
            dd[1] = _buf0;
            return dest;
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
        double[] pivotData = ((Double3Impl) pivot).data;
        double pivotX = pivotData[0];
        double pivotY = pivotData[1];
        double pivotZ = pivotData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[1] - pivotY;
            double _t3 = sd[2] - pivotZ;
            dd[0] = sd[0];
            dd[1] = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotY));
            dd[2] = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotZ));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[1] - pivotY;
            double _t3 = sd[2] - pivotZ;
            dd[0] = sd[0];
            dd[1] = ((_t2) * (_t0) + (((pivotY) - (_t3) * (_t1))));
            dd[2] = ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotZ))));
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[1] - pivotY;
            double _t3 = sd[2] - pivotZ;
            dd[0] = sd[0];
            dd[1] = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotY));
            dd[2] = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotZ));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[1] - pivotY;
            double _t3 = sd[2] - pivotZ;
            dd[0] = sd[0];
            dd[1] = ((_t2) * (_t0) + (((pivotY) - (_t3) * (_t1))));
            dd[2] = ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotZ))));
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
            dd[1] = sd[1];
            dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
            dd[0] = _buf0;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
            dd[1] = sd[1];
            dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
            dd[0] = _buf0;
            return dest;
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
        double[] pivotData = ((Double3Impl) pivot).data;
        double pivotX = pivotData[0];
        double pivotY = pivotData[1];
        double pivotZ = pivotData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[2] - pivotZ;
            dd[0] = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotX));
            dd[1] = sd[1];
            dd[2] = java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, pivotZ));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[2] - pivotZ;
            dd[0] = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotX))));
            dd[1] = sd[1];
            dd[2] = ((_t3) * (_t0) + (((pivotZ) - (_t2) * (_t1))));
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[2] - pivotZ;
            dd[0] = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotX));
            dd[1] = sd[1];
            dd[2] = java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, pivotZ));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[2] - pivotZ;
            dd[0] = ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotX))));
            dd[1] = sd[1];
            dd[2] = ((_t3) * (_t0) + (((pivotZ) - (_t2) * (_t1))));
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _buf0 = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
            dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
            dd[2] = sd[2];
            dd[0] = _buf0;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _buf0 = ((sd[0]) * (_t0) - (sd[1] * _t1));
            dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
            dd[2] = sd[2];
            dd[0] = _buf0;
            return dest;
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
        double[] pivotData = ((Double3Impl) pivot).data;
        double pivotX = pivotData[0];
        double pivotY = pivotData[1];
        double pivotZ = pivotData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[1] - pivotY;
            dd[0] = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX));
            dd[1] = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY));
            dd[2] = sd[2];
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[1] - pivotY;
            dd[0] = ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1))));
            dd[1] = ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY))));
            dd[2] = sd[2];
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[1] - pivotY;
            dd[0] = java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX));
            dd[1] = java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY));
            dd[2] = sd[2];
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = sd[0] - pivotX;
            double _t3 = sd[1] - pivotY;
            dd[0] = ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1))));
            dd[1] = ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY))));
            dd[2] = sd[2];
            return dest;
        }
    }

    public double x() { return data[0]; }
    public double y() { return data[1]; }
    public double z() { return data[2]; }

    public Double2 xx(@Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Double2 xy(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Double2 xz(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Double2 yx(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Double2 yy(@Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Double2 yz(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Double2 zx(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Double2 zy(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Double2 zz(@Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Double3 xxx(@Mutated Double3 dest) {
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = this.data[0];
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

    public Double3 xxz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
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
        double _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 xyz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 xzx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 xzy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 xzz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 yxx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[1];
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

    public Double3 yxz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
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
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Double3 yyz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 yzx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 yzy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 yzz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 zxx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 zxy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 zxz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 zyx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 zyy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 zyz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 zzx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 zzy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 zzz(@Mutated Double3 dest) {
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Double4 xxxx(@Mutated Double4 dest) {
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = this.data[0];
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

    public Double4 xxxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
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

    public Double4 xxyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xxzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xxzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xxzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
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

    public Double4 xyxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
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
        double _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xyyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xyzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xyzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xyzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xzxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xzxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xzxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xzyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xzyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xzyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xzzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xzzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xzzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yxxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[1];
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

    public Double4 yxxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
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

    public Double4 yxyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yxzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yxzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yxzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
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

    public Double4 yyxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
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
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yyyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yyzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yyzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yyzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yzxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yzxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yzxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yzyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yzyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yzyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yzzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yzzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yzzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zxxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zxxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zxxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zxyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zxyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zxyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zxzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zxzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zxzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zyxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zyxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zyxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zyyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zyyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zyyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zyzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zyzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zyzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zzxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zzxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zzxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zzyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zzyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zzyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zzzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zzzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zzzz(@Mutated Double4 dest) {
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Double3(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double3Impl)) return false;
        Double3Impl o = (Double3Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2]);
    }

    @Override public boolean equalsEpsilon(Double3R other, double epsilon) {
        double[] otherData = ((Double3Impl) other).data;
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        return dest;
    }

    @Mutated public Double3 load(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
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

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    @Mutated public Double3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public Double3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return this;
    }

    public Double3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public Double3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    @Mutated public Double3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }

    @Mutated public Double3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        return dest;
    }

    @Mutated public Double3 load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
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

    public ByteBuffer storeFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }

    @Mutated public Double3 loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double3 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }

    @Mutated public Double3 loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return this;
    }

    public Double3 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    @Mutated public Double3 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }

    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    public MemorySegment storeFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    @Mutated public Double3 loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }

    @Mutated public Double3 loadFloat(long offset, MemorySegment src) {
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
