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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double4} backed by a {@code double[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Double4Impl implements Double4 {

    public double[] data;

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
        data = new double[4];
        data[3] = 1;
    }

    public Double4Impl(double x, double y, double z, double w) {
        double[] dd = this.data = new double[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = w;
    }

    public Double4Impl(Double4R src) {
        double[] dd = this.data = new double[4];
        dd[0] = src.x();
        dd[1] = src.y();
        dd[2] = src.z();
        dd[3] = src.w();
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
        if (COL_NARROW) return add_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, otherData, 0).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
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
    public Double4 div(double scalar, @Mutated Double4 dest) {
        if (COL_NARROW) return div_narrow(scalar, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).div(DoubleVector.broadcast(COL_SPECIES, scalar)).intoArray(dd, 0);
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
    public Double4 div(Double4R other, @Mutated Double4 dest) {
        if (COL_NARROW) return div_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).div(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] / otherX;
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
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
    public Double4 fma(double b, Double4R c, @Mutated Double4 dest) {
        if (COL_NARROW) return fma_narrow(b, c, dest);
        if (SimdMath.USE_FMA) return fma_fma(b, c, dest);
        return fma_mulAdd(b, c, dest);
    }

    private Double4 fma_fma(double b, Double4R c, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] cData = ((Double4Impl) c).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, b), DoubleVector.fromArray(COL_SPECIES, cData, 0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 fma_mulAdd(double b, Double4R c, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] cData = ((Double4Impl) c).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, b)).add(DoubleVector.fromArray(COL_SPECIES, cData, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.fma(sd[0], b, cX);
        dd[1] = Math.fma(sd[1], b, cY);
        dd[2] = Math.fma(sd[2], b, cZ);
        dd[3] = Math.fma(sd[3], b, cW);
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
    public Double4 fma(Double4R b, Double4R c, @Mutated Double4 dest) {
        if (COL_NARROW) return fma_narrow(b, c, dest);
        if (SimdMath.USE_FMA) return fma_fma(b, c, dest);
        return fma_mulAdd(b, c, dest);
    }

    private Double4 fma_fma(Double4R b, Double4R c, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] bData = ((Double4Impl) b).data;
        double[] cData = ((Double4Impl) c).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.fromArray(COL_SPECIES, bData, 0), DoubleVector.fromArray(COL_SPECIES, cData, 0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 fma_mulAdd(Double4R b, Double4R c, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] bData = ((Double4Impl) b).data;
        double[] cData = ((Double4Impl) c).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.fromArray(COL_SPECIES, bData, 0)).add(DoubleVector.fromArray(COL_SPECIES, cData, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.fma(sd[0], bX, cX);
        dd[1] = Math.fma(sd[1], bY, cY);
        dd[2] = Math.fma(sd[2], bZ, cZ);
        dd[3] = Math.fma(sd[3], bW, cW);
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
    public Double4 mul(double scalar, @Mutated Double4 dest) {
        if (COL_NARROW) return mul_narrow(scalar, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, scalar).mul(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
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
    public Double4 mul(Double4R other, @Mutated Double4 dest) {
        if (COL_NARROW) return mul_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, otherData, 0).mul(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        dd[3] = otherW * sd[3];
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
    public Double4 negate(@Mutated Double4 dest) {
        if (COL_NARROW) return negate_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).neg().intoArray(dd, 0);
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
    public Double4 sub(Double4R other, @Mutated Double4 dest) {
        if (COL_NARROW) return sub_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).sub(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
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
        if (COL_NARROW) return makeUniformDirection_narrow(u, v, w);
        double[] dd = this.data;
        double _t1 = v * 6.283185307179586;
        double _t3 = w * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t6 = Math.sin(_t3);
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.cosFromSin(_t4, _t1)).withLane(1, _t4).withLane(2, Math.cosFromSin(_t6, _t3)).withLane(3, _t6).mul(DoubleVector.broadcast(COL_SPECIES, java.lang.Math.sqrt(1.0 - u)).blend(DoubleVector.broadcast(COL_SPECIES, java.lang.Math.sqrt(u)), MASK_0)).intoArray(dd, 0);
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
    @Mutated public Double4 set(Double4R v) {
        if (COL_NARROW) return set_narrow(v);
        double[] dd = this.data;
        double[] vData = ((Double4Impl) v).data;
        DoubleVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
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
    @Mutated public Double4 set(double vX, double vY, double vZ, double vW) {
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
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
        if (COL_NARROW) return set_narrow(s, dest);
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, s).intoArray(dd, 0);
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
    public Float4 toFloat(@Mutated Float4 dest) {
        double[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
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
    public Byte4 toByte(@Mutated Byte4 dest) {
        double[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
        dd[3] = (byte) (sd[3]);
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
    public Byte4 toByte(RoundingMode roundingMode, @Mutated Byte4 dest) {
        double[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toByte(dest); }
            case FLOOR -> {
                dd[0] = (byte) Math.floor(sd[0]);
                dd[1] = (byte) Math.floor(sd[1]);
                dd[2] = (byte) Math.floor(sd[2]);
                dd[3] = (byte) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (byte) Math.ceil(sd[0]);
                dd[1] = (byte) Math.ceil(sd[1]);
                dd[2] = (byte) Math.ceil(sd[2]);
                dd[3] = (byte) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
                dd[1] = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
                dd[2] = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[2])));
                dd[3] = (byte) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[3])));
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (byte) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (byte) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
                dd[2] = (byte) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
                dd[3] = (byte) (java.lang.Math.abs(sd[3] - Math.rint(sd[3])) == 0.5 ? sd[3] + Math.copySign(0.5, sd[3]) : Math.rint(sd[3]));
            }
            case HALF_EVEN -> {
                dd[0] = (byte) Math.rint(sd[0]);
                dd[1] = (byte) Math.rint(sd[1]);
                dd[2] = (byte) Math.rint(sd[2]);
                dd[3] = (byte) Math.rint(sd[3]);
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
        double[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
        dd[3] = (short) (sd[3]);
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
    public Short4 toShort(RoundingMode roundingMode, @Mutated Short4 dest) {
        double[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toShort(dest); }
            case FLOOR -> {
                dd[0] = (short) Math.floor(sd[0]);
                dd[1] = (short) Math.floor(sd[1]);
                dd[2] = (short) Math.floor(sd[2]);
                dd[3] = (short) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (short) Math.ceil(sd[0]);
                dd[1] = (short) Math.ceil(sd[1]);
                dd[2] = (short) Math.ceil(sd[2]);
                dd[3] = (short) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
                dd[1] = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
                dd[2] = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[2])));
                dd[3] = (short) (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[3])));
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (short) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (short) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
                dd[2] = (short) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
                dd[3] = (short) (java.lang.Math.abs(sd[3] - Math.rint(sd[3])) == 0.5 ? sd[3] + Math.copySign(0.5, sd[3]) : Math.rint(sd[3]));
            }
            case HALF_EVEN -> {
                dd[0] = (short) Math.rint(sd[0]);
                dd[1] = (short) Math.rint(sd[1]);
                dd[2] = (short) Math.rint(sd[2]);
                dd[3] = (short) Math.rint(sd[3]);
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
        double[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
        dd[2] = (int) (sd[2]);
        dd[3] = (int) (sd[3]);
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
    public Int4 toInt(RoundingMode roundingMode, @Mutated Int4 dest) {
        double[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toInt(dest); }
            case FLOOR -> {
                dd[0] = (int) Math.floor(sd[0]);
                dd[1] = (int) Math.floor(sd[1]);
                dd[2] = (int) Math.floor(sd[2]);
                dd[3] = (int) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (int) Math.ceil(sd[0]);
                dd[1] = (int) Math.ceil(sd[1]);
                dd[2] = (int) Math.ceil(sd[2]);
                dd[3] = (int) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
                dd[1] = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
                dd[2] = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[2])));
                dd[3] = (int) java.lang.Math.max(Integer.MIN_VALUE, java.lang.Math.min(Integer.MAX_VALUE, Math.round(sd[3])));
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (int) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (int) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
                dd[2] = (int) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
                dd[3] = (int) (java.lang.Math.abs(sd[3] - Math.rint(sd[3])) == 0.5 ? sd[3] + Math.copySign(0.5, sd[3]) : Math.rint(sd[3]));
            }
            case HALF_EVEN -> {
                dd[0] = (int) Math.rint(sd[0]);
                dd[1] = (int) Math.rint(sd[1]);
                dd[2] = (int) Math.rint(sd[2]);
                dd[3] = (int) Math.rint(sd[3]);
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
        double[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = (long) (sd[0]);
        dd[1] = (long) (sd[1]);
        dd[2] = (long) (sd[2]);
        dd[3] = (long) (sd[3]);
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
    public Long4 toLong(RoundingMode roundingMode, @Mutated Long4 dest) {
        double[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toLong(dest); }
            case FLOOR -> {
                dd[0] = (long) Math.floor(sd[0]);
                dd[1] = (long) Math.floor(sd[1]);
                dd[2] = (long) Math.floor(sd[2]);
                dd[3] = (long) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (long) Math.ceil(sd[0]);
                dd[1] = (long) Math.ceil(sd[1]);
                dd[2] = (long) Math.ceil(sd[2]);
                dd[3] = (long) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = Math.round(sd[0]);
                dd[1] = Math.round(sd[1]);
                dd[2] = Math.round(sd[2]);
                dd[3] = Math.round(sd[3]);
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (long) (java.lang.Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (long) (java.lang.Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
                dd[2] = (long) (java.lang.Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
                dd[3] = (long) (java.lang.Math.abs(sd[3] - Math.rint(sd[3])) == 0.5 ? sd[3] + Math.copySign(0.5, sd[3]) : Math.rint(sd[3]));
            }
            case HALF_EVEN -> {
                dd[0] = (long) Math.rint(sd[0]);
                dd[1] = (long) Math.rint(sd[1]);
                dd[2] = (long) Math.rint(sd[2]);
                dd[3] = (long) Math.rint(sd[3]);
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
        if (COL_NARROW) return makeZero_narrow();
        double[] dd = this.data;
        DoubleVector.broadcast(COL_SPECIES, 0.0).intoArray(dd, 0);
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
        if (COL_NARROW) return bezier_narrow(p1, p2, p3, t, dest);
        if (SimdMath.USE_FMA) return bezier_fma(p1, p2, p3, t, dest);
        return bezier_mulAdd(p1, p2, p3, t, dest);
    }

    private Double4 bezier_fma(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        DoubleVector.fromArray(COL_SPECIES, p1Data, 0).fma(DoubleVector.broadcast(COL_SPECIES, 3.0 * t * _t3), DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t0 * _t3))).add(DoubleVector.fromArray(COL_SPECIES, p2Data, 0).fma(DoubleVector.broadcast(COL_SPECIES, 3.0 * _t0 * _t1), DoubleVector.fromArray(COL_SPECIES, p3Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, t * _t1)))).intoArray(dd, 0);
        return dest;
    }

    private Double4 bezier_mulAdd(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        DoubleVector.fromArray(COL_SPECIES, p1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, 3.0 * t * _t3)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t0 * _t3))).add(DoubleVector.fromArray(COL_SPECIES, p2Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, 3.0 * _t0 * _t1)).add(DoubleVector.fromArray(COL_SPECIES, p3Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, t * _t1)))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dd[0] = Math.fma(p1X, _t7, sd[0] * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        dd[1] = Math.fma(p1Y, _t7, sd[1] * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = Math.fma(p1Z, _t7, sd[2] * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        dd[3] = Math.fma(p1W, _t7, sd[3] * _t8) + Math.fma(p2W, _t6, p3W * _t2);
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
    public Double4 bezier2(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return bezier2_narrow(p1, p2, t, dest);
        if (SimdMath.USE_FMA) return bezier2_fma(p1, p2, t, dest);
        return bezier2_mulAdd(p1, p2, t, dest);
    }

    private Double4 bezier2_fma(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t1 = 1.0 - t;
        DoubleVector.fromArray(COL_SPECIES, p2Data, 0).fma(DoubleVector.broadcast(COL_SPECIES, t * t), DoubleVector.fromArray(COL_SPECIES, p1Data, 0).fma(DoubleVector.broadcast(COL_SPECIES, (t + t) * _t1), DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t1 * _t1)))).intoArray(dd, 0);
        return dest;
    }

    private Double4 bezier2_mulAdd(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t1 = 1.0 - t;
        DoubleVector.fromArray(COL_SPECIES, p2Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, t * t)).add(DoubleVector.fromArray(COL_SPECIES, p1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, (t + t) * _t1)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, _t1 * _t1)))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dd[0] = Math.fma(p2X, _t0, Math.fma(p1X, _t3, sd[0] * _t4));
        dd[1] = Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, sd[1] * _t4));
        dd[2] = Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, sd[2] * _t4));
        dd[3] = Math.fma(p2W, _t0, Math.fma(p1W, _t3, sd[3] * _t4));
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
    public Double4 bezier2Tangent(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return bezier2Tangent_narrow(p1, p2, t, dest);
        if (SimdMath.USE_FMA) return bezier2Tangent_fma(p1, p2, t, dest);
        return bezier2Tangent_mulAdd(p1, p2, t, dest);
    }

    private Double4 bezier2Tangent_fma(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        _sv0.sub(DoubleVector.fromArray(COL_SPECIES, sd, 0)).fma(DoubleVector.broadcast(COL_SPECIES, 2.0 * (1.0 - t)), DoubleVector.fromArray(COL_SPECIES, p2Data, 0).sub(_sv0).mul(DoubleVector.broadcast(COL_SPECIES, t + t))).intoArray(dd, 0);
        return dest;
    }

    private Double4 bezier2Tangent_mulAdd(Double4R p1, Double4R p2, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        _sv0.sub(DoubleVector.fromArray(COL_SPECIES, sd, 0)).mul(DoubleVector.broadcast(COL_SPECIES, 2.0 * (1.0 - t))).add(DoubleVector.fromArray(COL_SPECIES, p2Data, 0).sub(_sv0).mul(DoubleVector.broadcast(COL_SPECIES, t + t))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dd[0] = Math.fma(p1X - sd[0], _t2, (p2X - p1X) * _t1);
        dd[1] = Math.fma(p1Y - sd[1], _t2, (p2Y - p1Y) * _t1);
        dd[2] = Math.fma(p1Z - sd[2], _t2, (p2Z - p1Z) * _t1);
        dd[3] = Math.fma(p1W - sd[3], _t2, (p2W - p1W) * _t1);
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
    public Double4 bezierTangent(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return bezierTangent_narrow(p1, p2, p3, t, dest);
        if (SimdMath.USE_FMA) return bezierTangent_fma(p1, p2, p3, t, dest);
        return bezierTangent_mulAdd(p1, p2, p3, t, dest);
    }

    private Double4 bezierTangent_fma(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t1 = 1.0 - t;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, p2Data, 0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        DoubleVector.fromArray(COL_SPECIES, p3Data, 0).sub(_sv0).fma(DoubleVector.broadcast(COL_SPECIES, 3.0 * t * t), _sv1.sub(DoubleVector.fromArray(COL_SPECIES, sd, 0)).fma(DoubleVector.broadcast(COL_SPECIES, 3.0 * _t1 * _t1), _sv0.sub(_sv1).mul(DoubleVector.broadcast(COL_SPECIES, 6.0 * t * _t1)))).intoArray(dd, 0);
        return dest;
    }

    private Double4 bezierTangent_mulAdd(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t1 = 1.0 - t;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, p2Data, 0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        DoubleVector.fromArray(COL_SPECIES, p3Data, 0).sub(_sv0).mul(DoubleVector.broadcast(COL_SPECIES, 3.0 * t * t)).add(_sv1.sub(DoubleVector.fromArray(COL_SPECIES, sd, 0)).mul(DoubleVector.broadcast(COL_SPECIES, 3.0 * _t1 * _t1)).add(_sv0.sub(_sv1).mul(DoubleVector.broadcast(COL_SPECIES, 6.0 * t * _t1)))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dd[0] = Math.fma(p3X - p2X, _t2, Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        dd[3] = Math.fma(p3W - p2W, _t2, Math.fma(p1W - sd[3], _t6, (p2W - p1W) * _t5));
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
    public Double4 catmullRom(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return catmullRom_narrow(p1, p2, p3, t, dest);
        if (SimdMath.USE_FMA) return catmullRom_fma(p1, p2, p3, t, dest);
        return catmullRom_mulAdd(p1, p2, p3, t, dest);
    }

    private Double4 catmullRom_fma(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, 2.0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, p2Data, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv4 = DoubleVector.fromArray(COL_SPECIES, p3Data, 0);
        DoubleVector.broadcast(COL_SPECIES, 0.5).mul(_sv0.fma(_sv1, DoubleVector.broadcast(COL_SPECIES, t).mul(_sv2.sub(_sv3))).add(DoubleVector.broadcast(COL_SPECIES, -5.0).fma(_sv1, _sv0.fma(_sv3, DoubleVector.broadcast(COL_SPECIES, 4.0).fma(_sv2, _sv4.neg()))).fma(DoubleVector.broadcast(COL_SPECIES, _t0), DoubleVector.broadcast(COL_SPECIES, -3.0).fma(_sv2, DoubleVector.broadcast(COL_SPECIES, 3.0).fma(_sv1, _sv4.sub(_sv3))).mul(DoubleVector.broadcast(COL_SPECIES, t * _t0))))).intoArray(dd, 0);
        return dest;
    }

    private Double4 catmullRom_mulAdd(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        catmullRom_s5821807f_tail(dd, t * t, t, sd, p1Data, p2Data, p3Data);
        return dest;
    }

    /** Private vector tail of {@code catmullRom_s5821807f}: loads, computes and stores every column; reached only through it. */
    private static void catmullRom_s5821807f_tail(double[] dd, double _t0, double t, double[] sd, double[] p1Data, double[] p2Data, double[] p3Data) {
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, p2Data, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, p3Data, 0);
        var _sv4 = DoubleVector.broadcast(COL_SPECIES, 2.0);
        DoubleVector.broadcast(COL_SPECIES, 0.5).mul(_sv4.mul(_sv0).add(DoubleVector.broadcast(COL_SPECIES, t).mul(_sv1.sub(_sv2))).add(DoubleVector.broadcast(COL_SPECIES, -5.0).mul(_sv0).add(_sv4.mul(_sv2).add(DoubleVector.broadcast(COL_SPECIES, 4.0).mul(_sv1).sub(_sv3))).mul(DoubleVector.broadcast(COL_SPECIES, _t0)).add(DoubleVector.broadcast(COL_SPECIES, -3.0).mul(_sv1).add(DoubleVector.broadcast(COL_SPECIES, 3.0).mul(_sv0).add(_sv3.sub(_sv2))).mul(DoubleVector.broadcast(COL_SPECIES, t * _t0))))).intoArray(dd, 0);
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t1 = t * _t0;
        dd[0] = 0.5 * (Math.fma(2.0, p1X, t * (p2X - sd[0])) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, sd[0], Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - sd[0])) * _t1));
        dd[1] = 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - sd[1])) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, sd[1], Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - sd[1])) * _t1));
        dd[2] = 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - sd[2])) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, sd[2], Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - sd[2])) * _t1));
        return catmullRom_scde09e05_1(p1W, p2W, p3W, t, dest, sd, dd, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom}, split to fit the inline budget; reached only through it. */
    private Double4 catmullRom_scde09e05_1(double p1W, double p2W, double p3W, double t, Double4 dest, double[] sd, double[] dd, double _t0, double _t1) {
        dd[3] = 0.5 * (Math.fma(2.0, p1W, t * (p2W - sd[3])) + Math.fma(Math.fma(-5.0, p1W, Math.fma(2.0, sd[3], Math.fma(4.0, p2W, -p3W))), _t0, Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - sd[3])) * _t1));
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
    public Double4 catmullRomTangent(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return catmullRomTangent_narrow(p1, p2, p3, t, dest);
        if (SimdMath.USE_FMA) return catmullRomTangent_fma(p1, p2, p3, t, dest);
        return catmullRomTangent_mulAdd(p1, p2, p3, t, dest);
    }

    private Double4 catmullRomTangent_fma(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, 2.0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, p2Data, 0);
        var _sv4 = DoubleVector.fromArray(COL_SPECIES, p3Data, 0);
        var _sv5 = DoubleVector.broadcast(COL_SPECIES, 3.0);
        DoubleVector.broadcast(COL_SPECIES, 0.5).mul(DoubleVector.broadcast(COL_SPECIES, t).fma(_sv0.mul(DoubleVector.broadcast(COL_SPECIES, -5.0).fma(_sv1, _sv0.fma(_sv2, DoubleVector.broadcast(COL_SPECIES, 4.0).fma(_sv3, _sv4.neg())))), _sv5.mul(DoubleVector.broadcast(COL_SPECIES, -3.0).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(DoubleVector.broadcast(COL_SPECIES, t * t), _sv3.sub(_sv2)))).intoArray(dd, 0);
        return dest;
    }

    private Double4 catmullRomTangent_mulAdd(Double4R p1, Double4R p2, Double4R p3, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] p1Data = ((Double4Impl) p1).data;
        double[] p2Data = ((Double4Impl) p2).data;
        double[] p3Data = ((Double4Impl) p3).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, 2.0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, p1Data, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, p2Data, 0);
        var _sv4 = DoubleVector.fromArray(COL_SPECIES, p3Data, 0);
        var _sv5 = DoubleVector.broadcast(COL_SPECIES, 3.0);
        DoubleVector.broadcast(COL_SPECIES, 0.5).mul(DoubleVector.broadcast(COL_SPECIES, t).mul(_sv0.mul(DoubleVector.broadcast(COL_SPECIES, -5.0).mul(_sv1).add(_sv0.mul(_sv2).add(DoubleVector.broadcast(COL_SPECIES, 4.0).mul(_sv3).sub(_sv4))))).add(_sv5.mul(DoubleVector.broadcast(COL_SPECIES, -3.0).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(DoubleVector.broadcast(COL_SPECIES, t * t)).add(_sv3.sub(_sv2)))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        dd[0] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, sd[0], Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - sd[0])), _t0, p2X - sd[0]));
        dd[1] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, sd[1], Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - sd[1])), _t0, p2Y - sd[1]));
        dd[2] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, sd[2], Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - sd[2])), _t0, p2Z - sd[2]));
        return catmullRomTangent_s66d1d242_1(p1W, p2W, p3W, t, dest, sd, dd, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent}, split to fit the inline budget; reached only through it. */
    private Double4 catmullRomTangent_s66d1d242_1(double p1W, double p2W, double p3W, double t, Double4 dest, double[] sd, double[] dd, double _t0) {
        dd[3] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1W, Math.fma(2.0, sd[3], Math.fma(4.0, p2W, -p3W))), Math.fma(3.0 * Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - sd[3])), _t0, p2W - sd[3]));
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
    public Double4 hermite(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return hermite_narrow(t0, v1, t1, t, dest);
        if (SimdMath.USE_FMA) return hermite_fma(t0, v1, t1, t, dest);
        return hermite_mulAdd(t0, v1, t1, t, dest);
    }

    private Double4 hermite_fma(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] t0Data = ((Double4Impl) t0).data;
        double[] v1Data = ((Double4Impl) v1).data;
        double[] t1Data = ((Double4Impl) t1).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t2 = t * _t0;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0))), DoubleVector.fromArray(COL_SPECIES, t0Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(t - 2.0, _t0, t)))).add(DoubleVector.fromArray(COL_SPECIES, t1Data, 0).fma(DoubleVector.broadcast(COL_SPECIES, t * Math.fma(t, t, -t)), DoubleVector.fromArray(COL_SPECIES, v1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(3.0, _t0, -(_t2 + _t2)))))).intoArray(dd, 0);
        return dest;
    }

    private Double4 hermite_mulAdd(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] t0Data = ((Double4Impl) t0).data;
        double[] v1Data = ((Double4Impl) v1).data;
        double[] t1Data = ((Double4Impl) t1).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t2 = t * _t0;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0)))).add(DoubleVector.fromArray(COL_SPECIES, t0Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(t - 2.0, _t0, t)))).add(DoubleVector.fromArray(COL_SPECIES, t1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, t * Math.fma(t, t, -t))).add(DoubleVector.fromArray(COL_SPECIES, v1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(3.0, _t0, -(_t2 + _t2)))))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dd[0] = Math.fma(sd[0], _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        dd[1] = Math.fma(sd[1], _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        dd[2] = Math.fma(sd[2], _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        dd[3] = Math.fma(sd[3], _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9);
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
    public Double4 hermiteTangent(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return hermiteTangent_narrow(t0, v1, t1, t, dest);
        if (SimdMath.USE_FMA) return hermiteTangent_fma(t0, v1, t1, t, dest);
        return hermiteTangent_mulAdd(t0, v1, t1, t, dest);
    }

    private Double4 hermiteTangent_fma(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] t0Data = ((Double4Impl) t0).data;
        double[] v1Data = ((Double4Impl) v1).data;
        double[] t1Data = ((Double4Impl) t1).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, 6.0 * Math.fma(t, t, -t)), DoubleVector.fromArray(COL_SPECIES, t0Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0))))).add(DoubleVector.fromArray(COL_SPECIES, t1Data, 0).fma(DoubleVector.broadcast(COL_SPECIES, Math.fma(3.0, _t0, -(t + t))), DoubleVector.fromArray(COL_SPECIES, v1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, 6.0 * Math.fma(-t, t, t))))).intoArray(dd, 0);
        return dest;
    }

    private Double4 hermiteTangent_mulAdd(Double4R t0, Double4R v1, Double4R t1, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] t0Data = ((Double4Impl) t0).data;
        double[] v1Data = ((Double4Impl) v1).data;
        double[] t1Data = ((Double4Impl) t1).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, 6.0 * Math.fma(t, t, -t))).add(DoubleVector.fromArray(COL_SPECIES, t0Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0))))).add(DoubleVector.fromArray(COL_SPECIES, t1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(3.0, _t0, -(t + t)))).add(DoubleVector.fromArray(COL_SPECIES, v1Data, 0).mul(DoubleVector.broadcast(COL_SPECIES, 6.0 * Math.fma(-t, t, t))))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dd[0] = Math.fma(sd[0], _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        dd[1] = Math.fma(sd[1], _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        dd[2] = Math.fma(sd[2], _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        dd[3] = Math.fma(sd[3], _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7);
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
    public Double4 lerp(Double4R other, double t, @Mutated Double4 dest) {
        if (COL_NARROW) return lerp_narrow(other, t, dest);
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private Double4 lerp_fma(Double4R other, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, t).fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0), _sv0).intoArray(dd, 0);
        return dest;
    }

    private Double4 lerp_mulAdd(Double4R other, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, t).mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0)).add(_sv0).intoArray(dd, 0);
        return dest;
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
        if (COL_NARROW) return lerp_narrow(otherX, otherY, otherZ, otherW, t, dest);
        if (SimdMath.USE_FMA) return lerp_fma(otherX, otherY, otherZ, otherW, t, dest);
        return lerp_mulAdd(otherX, otherY, otherZ, otherW, t, dest);
    }

    private Double4 lerp_fma(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, t).fma(DoubleVector.zero(COL_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoArray(dd, 0);
        return dest;
    }

    private Double4 lerp_mulAdd(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, t).mul(DoubleVector.zero(COL_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoArray(dd, 0);
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
    public Double4 lerp(Double4R other, Double4R t, @Mutated Double4 dest) {
        if (COL_NARROW) return lerp_narrow(other, t, dest);
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private Double4 lerp_fma(Double4R other, Double4R t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] tData = ((Double4Impl) t).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.fromArray(COL_SPECIES, tData, 0).fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0), _sv0).intoArray(dd, 0);
        return dest;
    }

    private Double4 lerp_mulAdd(Double4R other, Double4R t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] tData = ((Double4Impl) t).data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.fromArray(COL_SPECIES, tData, 0).mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0)).add(_sv0).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.fma(tX, otherX - sd[0], sd[0]);
        dd[1] = Math.fma(tY, otherY - sd[1], sd[1]);
        dd[2] = Math.fma(tZ, otherZ - sd[2], sd[2]);
        dd[3] = Math.fma(tW, otherW - sd[3], sd[3]);
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
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t9 = Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return slerp_degenerate(other, t, dest);
        double _t10 = Math.fma(otherData[3], otherData[3], Math.fma(otherData[2], otherData[2], Math.fma(otherData[0], otherData[0], otherData[1] * otherData[1])));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return slerp_degenerate(other, t, dest);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = sd[0] * _t11;
        double _t19 = sd[3] * _t11;
        double _t21 = sd[2] * _t11;
        double _t24 = sd[1] * _t11;
        return slerp_s5e1ea5dc_1(other, t, dest, otherData, dd, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), Math.fma(otherData[3] * _t14, _t19, Math.fma(otherData[2] * _t14, _t21, Math.fma(otherData[0] * _t14, _t16, otherData[1] * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_s5e1ea5dc_1(Double4R other, double t, Double4 dest, double[] otherData, double[] dd, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31) {
        double _t40 = Math.fma(otherData[3], _t14, -(_t31 * _t19));
        double _t41 = Math.fma(otherData[2], _t14, -(_t31 * _t21));
        double _t42 = Math.fma(otherData[0], _t14, -(_t31 * _t16));
        double _t43 = Math.fma(otherData[1], _t14, -(_t31 * _t24));
        double _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = Math.fma(_t48, _t19, _t40);
        double _t50 = Math.fma(_t48, _t21, _t41);
        double _t51 = Math.fma(_t48, _t16, _t42);
        double _t52 = Math.fma(_t48, _t24, _t43);
        double _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return slerp_degenerate(other, t, dest);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _sp0 = _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        double _t66 = _t28 * Math.cos(_t61);
        dd[0] = Math.fma(_t16, _t66, _sp0 * _t51);
        dd[1] = Math.fma(_t24, _t66, _sp0 * _t52);
        dd[2] = Math.fma(_t21, _t66, _sp0 * _t50);
        dd[3] = Math.fma(_t19, _t66, _sp0 * _t49);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t9 = Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t, dest);
        double _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t, dest);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = sd[0] * _t11;
        double _t19 = sd[3] * _t11;
        double _t21 = sd[2] * _t11;
        double _t24 = sd[1] * _t11;
        double _t31 = Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        return slerp_s604033a3_1(otherX, otherY, otherZ, otherW, t, dest, dd, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), _t31, Math.fma(otherW, _t14, -(_t31 * _t19)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private Double4 slerp_s604033a3_1(double otherX, double otherY, double otherZ, double otherW, double t, Double4 dest, double[] dd, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31, double _t40) {
        double _t41 = Math.fma(otherZ, _t14, -(_t31 * _t21));
        double _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        double _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = Math.fma(_t48, _t19, _t40);
        double _t50 = Math.fma(_t48, _t21, _t41);
        double _t51 = Math.fma(_t48, _t16, _t42);
        double _t52 = Math.fma(_t48, _t24, _t43);
        double _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return slerp_degenerate(otherX, otherY, otherZ, otherW, t, dest);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _sp0 = _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        double _t66 = _t28 * Math.cos(_t61);
        dd[0] = Math.fma(_t16, _t66, _sp0 * _t51);
        dd[1] = Math.fma(_t24, _t66, _sp0 * _t52);
        dd[2] = Math.fma(_t21, _t66, _sp0 * _t50);
        dd[3] = Math.fma(_t19, _t66, _sp0 * _t49);
        return dest;
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate(Double4R other, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t7 = unitScale(otherData[2], otherData[3], java.lang.Math.max(java.lang.Math.abs(otherData[0]), java.lang.Math.abs(otherData[1])));
        double _t8 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        double _t17 = otherData[3] * _t7;
        double _t18 = otherData[2] * _t7;
        double _t19 = otherData[0] * _t7;
        double _t20 = otherData[1] * _t7;
        double _t21 = sd[3] * _t8;
        double _t22 = sd[2] * _t8;
        double _t23 = sd[0] * _t8;
        double _t24 = sd[1] * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = Math.fma(_t80, _t43, _t72);
        double _t82 = Math.fma(_t80, _t45, _t73);
        double _t83 = Math.fma(_t80, _t47, _t74);
        double _t84 = Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t114 = _t60 * Math.sin(_t110);
        double _t115 = _t60 * Math.cos(_t110);
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dd[0] = Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv;
                    dd[1] = Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv;
                    dd[2] = Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv;
                    dd[3] = Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv;
                } else {
                    dd[0] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                    dd[1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                    dd[2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                    dd[3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
                }
            } else {
                dd[0] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                dd[1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                dd[2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                dd[3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
            }
        } else {
            dd[0] = Math.fma(t, otherData[0] - sd[0], sd[0]);
            dd[1] = Math.fma(t, otherData[1] - sd[1], sd[1]);
            dd[2] = Math.fma(t, otherData[2] - sd[2], sd[2]);
            dd[3] = Math.fma(t, otherData[3] - sd[3], sd[3]);
        }
        return dest;
    }


    /**
     * Degenerate-input path of {@code slerp}: its methods leave here when a vector is zero, the two
     * are parallel or opposite, or a squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private Double4 slerp_degenerate(double otherX, double otherY, double otherZ, double otherW, double t, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = sd[3] * _t8;
        double _t22 = sd[2] * _t8;
        double _t23 = sd[0] * _t8;
        double _t24 = sd[1] * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = Math.fma(_t80, _t43, _t72);
        double _t82 = Math.fma(_t80, _t45, _t73);
        double _t83 = Math.fma(_t80, _t47, _t74);
        double _t84 = Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t114 = _t60 * Math.sin(_t110);
        double _t115 = _t60 * Math.cos(_t110);
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dd[0] = Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv;
                    dd[1] = Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv;
                    dd[2] = Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv;
                    dd[3] = Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv;
                } else {
                    dd[0] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                    dd[1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                    dd[2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                    dd[3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
                }
            } else {
                dd[0] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                dd[1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                dd[2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                dd[3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
            }
        } else {
            dd[0] = Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = Math.fma(t, otherW - sd[3], sd[3]);
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
    public Double4 absolute(@Mutated Double4 dest) {
        if (COL_NARROW) return absolute_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).abs().intoArray(dd, 0);
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
    public Double4 acos(@Mutated Double4 dest) {
        if (COL_NARROW) return acos_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.acos(sd[0])).withLane(1, Math.acos(sd[1])).withLane(2, Math.acos(sd[2])).withLane(3, Math.acos(sd[3])).intoArray(dd, 0);
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
    public Double4 addScaled(Double4R b, double scalar, @Mutated Double4 dest) {
        if (COL_NARROW) return addScaled_narrow(b, scalar, dest);
        if (SimdMath.USE_FMA) return addScaled_fma(b, scalar, dest);
        return addScaled_mulAdd(b, scalar, dest);
    }

    private Double4 addScaled_fma(Double4R b, double scalar, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] bData = ((Double4Impl) b).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, scalar).fma(DoubleVector.fromArray(COL_SPECIES, bData, 0), DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 addScaled_mulAdd(Double4R b, double scalar, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] bData = ((Double4Impl) b).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, scalar).mul(DoubleVector.fromArray(COL_SPECIES, bData, 0)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.fma(scalar, bX, sd[0]);
        dd[1] = Math.fma(scalar, bY, sd[1]);
        dd[2] = Math.fma(scalar, bZ, sd[2]);
        dd[3] = Math.fma(scalar, bW, sd[3]);
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
    public Double4 addScaled(Double4R b, Double4R c, @Mutated Double4 dest) {
        if (COL_NARROW) return addScaled_narrow(b, c, dest);
        if (SimdMath.USE_FMA) return addScaled_fma(b, c, dest);
        return addScaled_mulAdd(b, c, dest);
    }

    private Double4 addScaled_fma(Double4R b, Double4R c, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] bData = ((Double4Impl) b).data;
        double[] cData = ((Double4Impl) c).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, bData, 0).fma(DoubleVector.fromArray(COL_SPECIES, cData, 0), DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 addScaled_mulAdd(Double4R b, Double4R c, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] bData = ((Double4Impl) b).data;
        double[] cData = ((Double4Impl) c).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, bData, 0).mul(DoubleVector.fromArray(COL_SPECIES, cData, 0)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.fma(bX, cX, sd[0]);
        dd[1] = Math.fma(bY, cY, sd[1]);
        dd[2] = Math.fma(bZ, cZ, sd[2]);
        dd[3] = Math.fma(bW, cW, sd[3]);
        return dest;
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
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double[] sd = this.data;
        double _t12 = Math.fma(otherW, sd[2], -(otherZ * sd[3]));
        double _t13 = Math.fma(otherW, sd[1], -(otherY * sd[3]));
        double _t14 = Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        double _t15 = Math.fma(otherW, sd[0], -(otherX * sd[3]));
        double _t16 = Math.fma(otherY, sd[0], -(otherX * sd[1]));
        double _t17 = Math.fma(otherZ, sd[0], -(otherX * sd[2]));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherW, sd[3], Math.fma(otherZ, sd[2], Math.fma(otherX, sd[0], otherY * sd[1]))));
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
        double[] sd = this.data;
        double _t12 = Math.fma(otherW, sd[2], -(otherZ * sd[3]));
        double _t13 = Math.fma(otherW, sd[1], -(otherY * sd[3]));
        double _t14 = Math.fma(otherZ, sd[1], -(otherY * sd[2]));
        double _t15 = Math.fma(otherW, sd[0], -(otherX * sd[3]));
        double _t16 = Math.fma(otherY, sd[0], -(otherX * sd[1]));
        double _t17 = Math.fma(otherZ, sd[0], -(otherX * sd[2]));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return angleBetween_degenerate(otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherW, sd[3], Math.fma(otherZ, sd[2], Math.fma(otherX, sd[0], otherY * sd[1]))));
    }


    /**
     * Out-of-range path of {@code angleBetween}: its methods leave here when the cross product they
     * form (its squared length, beyond 2D) is zero, NaN or outside the normal floating-point range;
     * reached only through them.
     */
    private double angleBetween_degenerate(double otherX, double otherY, double otherZ, double otherW) {
        double[] sd = this.data;
        double _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(sd[2], sd[3], java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1])));
        double _t16 = otherW * _t6;
        double _t17 = sd[2] * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = sd[3] * _t7;
        double _t20 = otherY * _t6;
        double _t21 = sd[0] * _t7;
        double _t22 = otherX * _t6;
        double _t23 = sd[1] * _t7;
        double _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        double _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        double _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        double _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        double _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        double _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        double _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        return angleBetween_degenerate_s3a55def3_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t51, _t36 * _t51, _t41 * _t51, _t40 * _t51, _t39 * _t51, _t37 * _t51, _t38 * _t51);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private double angleBetween_degenerate_s3a55def3_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t51, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63) {
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
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
        if (COL_NARROW) return asin_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.asin(sd[0])).withLane(1, Math.asin(sd[1])).withLane(2, Math.asin(sd[2])).withLane(3, Math.asin(sd[3])).intoArray(dd, 0);
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
    public Double4 atan(@Mutated Double4 dest) {
        if (COL_NARROW) return atan_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.atan(sd[0])).withLane(1, Math.atan(sd[1])).withLane(2, Math.atan(sd[2])).withLane(3, Math.atan(sd[3])).intoArray(dd, 0);
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
    public Double4 atan2(double x, @Mutated Double4 dest) {
        if (COL_NARROW) return atan2_narrow(x, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.atan2(sd[0], x)).withLane(1, Math.atan2(sd[1], x)).withLane(2, Math.atan2(sd[2], x)).withLane(3, Math.atan2(sd[3], x)).intoArray(dd, 0);
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
    public Double4 atan2(Double4R x, @Mutated Double4 dest) {
        if (COL_NARROW) return atan2_narrow(x, dest);
        double[] sd = this.data;
        double[] xData = ((Double4Impl) x).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.atan2(sd[0], xData[0])).withLane(1, Math.atan2(sd[1], xData[1])).withLane(2, Math.atan2(sd[2], xData[2])).withLane(3, Math.atan2(sd[3], xData[3])).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], xX);
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
        dd[3] = Math.atan2(sd[3], xW);
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
    public Double4 cbrt(@Mutated Double4 dest) {
        if (COL_NARROW) return cbrt_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.cbrt(sd[0])).withLane(1, Math.cbrt(sd[1])).withLane(2, Math.cbrt(sd[2])).withLane(3, Math.cbrt(sd[3])).intoArray(dd, 0);
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
    public Double4 ceil(@Mutated Double4 dest) {
        if (COL_NARROW) return ceil_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.ceil(sd[0])).withLane(1, Math.ceil(sd[1])).withLane(2, Math.ceil(sd[2])).withLane(3, Math.ceil(sd[3])).intoArray(dd, 0);
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
    public Double4 clamp(double min, double max, @Mutated Double4 dest) {
        if (COL_NARROW) return clamp_narrow(min, max, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).max(DoubleVector.broadcast(COL_SPECIES, min)).min(DoubleVector.broadcast(COL_SPECIES, max)).intoArray(dd, 0);
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
    public Double4 clamp(Double4R min, Double4R max, @Mutated Double4 dest) {
        if (COL_NARROW) return clamp_narrow(min, max, dest);
        double[] sd = this.data;
        double[] minData = ((Double4Impl) min).data;
        double[] maxData = ((Double4Impl) max).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).max(DoubleVector.fromArray(COL_SPECIES, minData, 0)).min(DoubleVector.fromArray(COL_SPECIES, maxData, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], minW), maxW);
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
        return sd[3] + (sd[2] + (sd[0] + sd[1]));
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
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(sd[0], sd[1]), sd[2]), sd[3]);
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
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(sd[0], sd[1]), sd[2]), sd[3]);
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
        return sd[3] * sd[2] * sd[0] * sd[1];
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
        if (COL_NARROW) return copySign_narrow(sign, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.copySign(sd[0], sign)).withLane(1, Math.copySign(sd[1], sign)).withLane(2, Math.copySign(sd[2], sign)).withLane(3, Math.copySign(sd[3], sign)).intoArray(dd, 0);
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
    public Double4 copySign(Double4R sign, @Mutated Double4 dest) {
        if (COL_NARROW) return copySign_narrow(sign, dest);
        double[] sd = this.data;
        double[] signData = ((Double4Impl) sign).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.copySign(sd[0], signData[0])).withLane(1, Math.copySign(sd[1], signData[1])).withLane(2, Math.copySign(sd[2], signData[2])).withLane(3, Math.copySign(sd[3], signData[3])).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], signX);
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
        dd[3] = Math.copySign(sd[3], signW);
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
    public Double4 cos(@Mutated Double4 dest) {
        if (COL_NARROW) return cos_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.cos(sd[0])).withLane(1, Math.cos(sd[1])).withLane(2, Math.cos(sd[2])).withLane(3, Math.cos(sd[3])).intoArray(dd, 0);
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
    public Double4 cosh(@Mutated Double4 dest) {
        if (COL_NARROW) return cosh_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.cosh(sd[0])).withLane(1, Math.cosh(sd[1])).withLane(2, Math.cosh(sd[2])).withLane(3, Math.cosh(sd[3])).intoArray(dd, 0);
        return dest;
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
        if (COL_NARROW) return cross_narrow(v, w, dest);
        if (SimdMath.USE_FMA) return cross_fma(v, w, dest);
        return cross_mulAdd(v, w, dest);
    }

    private Double4 cross_fma(Double4R v, Double4R w, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] vData = ((Double4Impl) v).data;
        double[] wData = ((Double4Impl) w).data;
        double[] dd = ((Double4Impl) dest).data;
        double _r0 = vData[1];
        double _r1 = wData[2];
        double _r2 = vData[2];
        double _r3 = wData[1];
        double _r4 = wData[3];
        double _r5 = vData[3];
        double _r6 = vData[0];
        double _r7 = wData[0];
        double _r8 = sd[3];
        double _r9 = sd[2];
        double _r10 = sd[1];
        double _r11 = sd[0];
        cross_s644be985_tail(dd, _r8, _r9, _r10, _r11, Math.fma(_r0, _r1, -(_r2 * _r3)), Math.fma(_r2, _r4, -(_r5 * _r1)), Math.fma(_r0, _r4, -(_r5 * _r3)), Math.fma(_r6, _r1, -(_r2 * _r7)), Math.fma(_r6, _r4, -(_r5 * _r7)), Math.fma(_r6, _r3, -(_r0 * _r7)));
        return dest;
    }

    private Double4 cross_mulAdd(Double4R v, Double4R w, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] vData = ((Double4Impl) v).data;
        double[] wData = ((Double4Impl) w).data;
        double[] dd = ((Double4Impl) dest).data;
        double _r0 = vData[1];
        double _r1 = wData[2];
        double _r2 = vData[2];
        double _r3 = wData[1];
        double _r4 = wData[3];
        double _r5 = vData[3];
        double _r6 = vData[0];
        double _r7 = wData[0];
        double _r8 = sd[3];
        double _r9 = sd[2];
        double _r10 = sd[1];
        double _r11 = sd[0];
        cross_s2a649552_tail(dd, _r8, _r9, _r10, _r11, Math.fma(_r0, _r1, -(_r2 * _r3)), Math.fma(_r2, _r4, -(_r5 * _r1)), Math.fma(_r0, _r4, -(_r5 * _r3)), Math.fma(_r6, _r1, -(_r2 * _r7)), Math.fma(_r6, _r4, -(_r5 * _r7)), Math.fma(_r6, _r3, -(_r0 * _r7)));
        return dest;
    }

    /**
     * Private vector tail of {@code cross_s644be985}: loads, computes and stores every column.
     * Shared by 2 identical private paths of {@code cross}; reached only through it.
     */
    private static void cross_s644be985_tail(double[] dd, double _r8, double _r9, double _r10, double _r11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, _r8);
        _sv0.blend(_sv0.withLane(3, _r9).neg(), MASK_1).fma(DoubleVector.zero(COL_SPECIES).withLane(0, _t12).withLane(1, _t15).withLane(2, _t17).withLane(3, _t17), DoubleVector.zero(COL_SPECIES).withLane(0, _r10 * _t13 - _r9 * _t14).withLane(1, _r9 * _t16 - _r11 * _t13).withLane(2, _r11 * _t14 - _r10 * _t16).withLane(3, _r10 * _t15 - _r11 * _t12)).intoArray(dd, 0);
    }

    /**
     * Private vector tail of {@code cross_s2a649552}: loads, computes and stores every column.
     * Shared by 2 identical private paths of {@code cross}; reached only through it.
     */
    private static void cross_s2a649552_tail(double[] dd, double _r8, double _r9, double _r10, double _r11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, _r8);
        _sv0.blend(_sv0.withLane(3, _r9).neg(), MASK_1).mul(DoubleVector.zero(COL_SPECIES).withLane(0, _t12).withLane(1, _t15).withLane(2, _t17).withLane(3, _t17)).add(DoubleVector.zero(COL_SPECIES).withLane(0, _r10 * _t13 - _r9 * _t14).withLane(1, _r9 * _t16 - _r11 * _t13).withLane(2, _r11 * _t14 - _r10 * _t16).withLane(3, _r10 * _t15 - _r11 * _t12)).intoArray(dd, 0);
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
        if (COL_NARROW) return cross_narrow(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
        if (SimdMath.USE_FMA) return cross_fma(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
        return cross_mulAdd(vX, vY, vZ, vW, wX, wY, wZ, wW, dest);
    }

    private Double4 cross_fma(double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _r0 = sd[3];
        double _r1 = sd[2];
        double _r2 = sd[1];
        double _r3 = sd[0];
        cross_s644be985_tail(dd, _r0, _r1, _r2, _r3, Math.fma(vY, wZ, -(vZ * wY)), Math.fma(vZ, wW, -(vW * wZ)), Math.fma(vY, wW, -(vW * wY)), Math.fma(vX, wZ, -(vZ * wX)), Math.fma(vX, wW, -(vW * wX)), Math.fma(vX, wY, -(vY * wX)));
        return dest;
    }

    private Double4 cross_mulAdd(double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _r0 = sd[3];
        double _r1 = sd[2];
        double _r2 = sd[1];
        double _r3 = sd[0];
        cross_s2a649552_tail(dd, _r0, _r1, _r2, _r3, Math.fma(vY, wZ, -(vZ * wY)), Math.fma(vZ, wW, -(vW * wZ)), Math.fma(vY, wW, -(vW * wY)), Math.fma(vX, wZ, -(vZ * wX)), Math.fma(vX, wW, -(vW * wX)), Math.fma(vX, wY, -(vY * wX)));
        return dest;
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
        if (COL_NARROW) return degrees_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.toDegrees(sd[0])).withLane(1, Math.toDegrees(sd[1])).withLane(2, Math.toDegrees(sd[2])).withLane(3, Math.toDegrees(sd[3])).intoArray(dd, 0);
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
    public double distance(Double4R other) {
        double[] sd = this.data;
        double _t0 = sd[3] - other.w();
        double _t1 = sd[2] - other.z();
        double _t2 = sd[0] - other.x();
        double _t3 = sd[1] - other.y();
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
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
        double[] sd = this.data;
        double _t0 = sd[3] - otherW;
        double _t1 = sd[2] - otherZ;
        double _t2 = sd[0] - otherX;
        double _t3 = sd[1] - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
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
        double[] sd = this.data;
        double _t0 = sd[3] - other.w();
        double _t1 = sd[2] - other.z();
        double _t2 = sd[0] - other.x();
        double _t3 = sd[1] - other.y();
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
    public double distanceSquared(double otherX, double otherY, double otherZ, double otherW) {
        double[] sd = this.data;
        double _t0 = sd[3] - otherW;
        double _t1 = sd[2] - otherZ;
        double _t2 = sd[0] - otherX;
        double _t3 = sd[1] - otherY;
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
        double[] sd = this.data;
        return Math.fma(other.w(), sd[3], Math.fma(other.z(), sd[2], Math.fma(other.x(), sd[0], other.y() * sd[1])));
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
        double[] sd = this.data;
        return Math.fma(otherW, sd[3], Math.fma(otherZ, sd[2], Math.fma(otherX, sd[0], otherY * sd[1])));
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
        if (COL_NARROW) return exp_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.exp(sd[0])).withLane(1, Math.exp(sd[1])).withLane(2, Math.exp(sd[2])).withLane(3, Math.exp(sd[3])).intoArray(dd, 0);
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
    public Double4 exp2(@Mutated Double4 dest) {
        if (COL_NARROW) return exp2_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.pow(2.0, sd[0])).withLane(1, Math.pow(2.0, sd[1])).withLane(2, Math.pow(2.0, sd[2])).withLane(3, Math.pow(2.0, sd[3])).intoArray(dd, 0);
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
    public Double4 expm1(@Mutated Double4 dest) {
        if (COL_NARROW) return expm1_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.expm1(sd[0])).withLane(1, Math.expm1(sd[1])).withLane(2, Math.expm1(sd[2])).withLane(3, Math.expm1(sd[3])).intoArray(dd, 0);
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
    public Double4 faceforward(Double4R I, Double4R Nref, @Mutated Double4 dest) {
        if (COL_NARROW) return faceforward_narrow(I, Nref, dest);
        double[] sd = this.data;
        double[] IData = ((Double4Impl) I).data;
        double[] NrefData = ((Double4Impl) Nref).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(IData[3], NrefData[3], Math.fma(IData[2], NrefData[2], Math.fma(IData[0], NrefData[0], IData[1] * NrefData[1]))) < 0.0 ? 1.0 : -1.0)).intoArray(dd, 0);
        return dest;
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
        if (COL_NARROW) return faceforward_narrow(IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0)).intoArray(dd, 0);
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
    public Double4 floor(@Mutated Double4 dest) {
        if (COL_NARROW) return floor_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.floor(sd[0])).withLane(1, Math.floor(sd[1])).withLane(2, Math.floor(sd[2])).withLane(3, Math.floor(sd[3])).intoArray(dd, 0);
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
    public Double4 fract(@Mutated Double4 dest) {
        if (COL_NARROW) return fract_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        _sv0.sub(DoubleVector.zero(COL_SPECIES).withLane(0, Math.floor(sd[0])).withLane(1, Math.floor(sd[1])).withLane(2, Math.floor(sd[2])).withLane(3, Math.floor(sd[3]))).min(DoubleVector.broadcast(COL_SPECIES, 0.9999999999999999)).intoArray(dd, 0);
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
    public Double4 hypot(double y, @Mutated Double4 dest) {
        if (COL_NARROW) return hypot_narrow(y, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.hypot(sd[0], y)).withLane(1, Math.hypot(sd[1], y)).withLane(2, Math.hypot(sd[2], y)).withLane(3, Math.hypot(sd[3], y)).intoArray(dd, 0);
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
    public Double4 hypot(Double4R y, @Mutated Double4 dest) {
        if (COL_NARROW) return hypot_narrow(y, dest);
        double[] sd = this.data;
        double[] yData = ((Double4Impl) y).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.hypot(sd[0], yData[0])).withLane(1, Math.hypot(sd[1], yData[1])).withLane(2, Math.hypot(sd[2], yData[2])).withLane(3, Math.hypot(sd[3], yData[3])).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], yX);
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
        dd[3] = Math.hypot(sd[3], yW);
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
    public Double4 inverse(@Mutated Double4 dest) {
        if (COL_NARROW) return inverse_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.broadcast(COL_SPECIES, 1.0).div(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
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
    public Double4 inverseSqrt(@Mutated Double4 dest) {
        if (COL_NARROW) return inverseSqrt_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, (1.0 / java.lang.Math.sqrt(sd[0]))).withLane(1, (1.0 / java.lang.Math.sqrt(sd[1]))).withLane(2, (1.0 / java.lang.Math.sqrt(sd[2]))).withLane(3, (1.0 / java.lang.Math.sqrt(sd[3]))).intoArray(dd, 0);
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
        return java.lang.Math.sqrt(Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]))));
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
        return Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
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
        if (COL_NARROW) return log_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.log(sd[0])).withLane(1, Math.log(sd[1])).withLane(2, Math.log(sd[2])).withLane(3, Math.log(sd[3])).intoArray(dd, 0);
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
    public Double4 log10(@Mutated Double4 dest) {
        if (COL_NARROW) return log10_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.log10(sd[0])).withLane(1, Math.log10(sd[1])).withLane(2, Math.log10(sd[2])).withLane(3, Math.log10(sd[3])).intoArray(dd, 0);
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
    public Double4 log1p(@Mutated Double4 dest) {
        if (COL_NARROW) return log1p_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.log1p(sd[0])).withLane(1, Math.log1p(sd[1])).withLane(2, Math.log1p(sd[2])).withLane(3, Math.log1p(sd[3])).intoArray(dd, 0);
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
    public Double4 log2(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log2(sd[0]);
        dd[1] = Math.log2(sd[1]);
        dd[2] = Math.log2(sd[2]);
        dd[3] = Math.log2(sd[3]);
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
    public double manhattanDistance(Double4R other) {
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - other.x()) + java.lang.Math.abs(sd[1] - other.y()) + java.lang.Math.abs(sd[2] - other.z()) + java.lang.Math.abs(sd[3] - other.w());
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
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherX) + java.lang.Math.abs(sd[1] - otherY) + java.lang.Math.abs(sd[2] - otherZ) + java.lang.Math.abs(sd[3] - otherW);
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
        return java.lang.Math.abs(sd[0]) + java.lang.Math.abs(sd[1]) + java.lang.Math.abs(sd[2]) + java.lang.Math.abs(sd[3]);
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
        if (COL_NARROW) return max_narrow(scalar, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).max(DoubleVector.broadcast(COL_SPECIES, scalar)).intoArray(dd, 0);
        return dest;
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
        if (COL_NARROW) return max_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).max(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        dd[3] = java.lang.Math.max(sd[3], otherW);
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
    public Double4 min(double scalar, @Mutated Double4 dest) {
        if (COL_NARROW) return min_narrow(scalar, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).min(DoubleVector.broadcast(COL_SPECIES, scalar)).intoArray(dd, 0);
        return dest;
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
        if (COL_NARROW) return min_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double4Impl) other).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).min(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        dd[3] = java.lang.Math.min(sd[3], otherW);
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
    public Double4 mod(double y, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = flooredMod(sd[0], y);
        dd[1] = flooredMod(sd[1], y);
        dd[2] = flooredMod(sd[2], y);
        dd[3] = flooredMod(sd[3], y);
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
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mod(Double4R y, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] yData = ((Double4Impl) y).data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = flooredMod(sd[0], yData[0]);
        dd[1] = flooredMod(sd[1], yData[1]);
        dd[2] = flooredMod(sd[2], yData[2]);
        dd[3] = flooredMod(sd[3], yData[3]);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = flooredMod(sd[0], yX);
        dd[1] = flooredMod(sd[1], yY);
        dd[2] = flooredMod(sd[2], yZ);
        dd[3] = flooredMod(sd[3], yW);
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
    public Double4 nextDown(@Mutated Double4 dest) {
        if (COL_NARROW) return nextDown_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.nextDown(sd[0])).withLane(1, Math.nextDown(sd[1])).withLane(2, Math.nextDown(sd[2])).withLane(3, Math.nextDown(sd[3])).intoArray(dd, 0);
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
    public Double4 nextUp(@Mutated Double4 dest) {
        if (COL_NARROW) return nextUp_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.nextUp(sd[0])).withLane(1, Math.nextUp(sd[1])).withLane(2, Math.nextUp(sd[2])).withLane(3, Math.nextUp(sd[3])).intoArray(dd, 0);
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
    public Double4 normalize(@Mutated Double4 dest) {
        if (COL_NARROW) return normalize_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        double _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3 != 0.0 ? _sv0.mul(DoubleVector.broadcast(COL_SPECIES, (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(COL_SPECIES, 0.0)).intoArray(dd, 0);
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
    public Double4 normalizeMul(double length, @Mutated Double4 dest) {
        if (COL_NARROW) return normalizeMul_narrow(length, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t3 = Math.fma(sd[3], sd[3], Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])));
        (_t3 != 0.0 ? DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, length * (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(COL_SPECIES, 0.0)).intoArray(dd, 0);
        return dest;
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
        if (COL_NARROW) return outerProduct_narrow(row, dest);
        double rowX = row.x();
        double rowY = row.y();
        double rowZ = row.z();
        double rowW = row.w();
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, rowX).mul(_sv0).intoArray(dd, 0);
        DoubleVector.broadcast(COL_SPECIES, rowY).mul(_sv0).intoArray(dd, 4);
        DoubleVector.broadcast(COL_SPECIES, rowZ).mul(_sv0).intoArray(dd, 8);
        DoubleVector.broadcast(COL_SPECIES, rowW).mul(_sv0).intoArray(dd, 12);
        ((Double4x4Impl) dest).properties = 0;
        return dest;
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
        if (COL_NARROW) return outerProduct_narrow(rowX, rowY, rowZ, rowW, dest);
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        DoubleVector.broadcast(COL_SPECIES, rowX).mul(_sv0).intoArray(dd, 0);
        DoubleVector.broadcast(COL_SPECIES, rowY).mul(_sv0).intoArray(dd, 4);
        DoubleVector.broadcast(COL_SPECIES, rowZ).mul(_sv0).intoArray(dd, 8);
        DoubleVector.broadcast(COL_SPECIES, rowW).mul(_sv0).intoArray(dd, 12);
        ((Double4x4Impl) dest).properties = 0;
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
    public Double4 pow(double exponent, @Mutated Double4 dest) {
        if (COL_NARROW) return pow_narrow(exponent, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.pow(sd[0], exponent)).withLane(1, Math.pow(sd[1], exponent)).withLane(2, Math.pow(sd[2], exponent)).withLane(3, Math.pow(sd[3], exponent)).intoArray(dd, 0);
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
    public Double4 pow(Double4R exponent, @Mutated Double4 dest) {
        if (COL_NARROW) return pow_narrow(exponent, dest);
        double[] sd = this.data;
        double[] exponentData = ((Double4Impl) exponent).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.pow(sd[0], exponentData[0])).withLane(1, Math.pow(sd[1], exponentData[1])).withLane(2, Math.pow(sd[2], exponentData[2])).withLane(3, Math.pow(sd[3], exponentData[3])).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponentX);
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
        dd[3] = Math.pow(sd[3], exponentW);
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
    public Double4 project(Double4R onto, @Mutated Double4 dest) {
        if (COL_NARROW) return project_narrow(onto, dest);
        double[] sd = this.data;
        double[] ontoData = ((Double4Impl) onto).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, ontoData, 0).mul(DoubleVector.broadcast(COL_SPECIES, Math.fma(ontoData[3], sd[3], Math.fma(ontoData[2], sd[2], Math.fma(ontoData[0], sd[0], ontoData[1] * sd[1]))) / Math.fma(ontoData[3], ontoData[3], Math.fma(ontoData[2], ontoData[2], Math.fma(ontoData[0], ontoData[0], ontoData[1] * ontoData[1]))))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t9 = Math.fma(ontoW, sd[3], Math.fma(ontoZ, sd[2], Math.fma(ontoX, sd[0], ontoY * sd[1]))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dd[0] = ontoX * _t9;
        dd[1] = ontoY * _t9;
        dd[2] = ontoZ * _t9;
        dd[3] = ontoW * _t9;
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
    public Double4 projectOnPlane(Double4R normal, @Mutated Double4 dest) {
        if (COL_NARROW) return projectOnPlane_narrow(normal, dest);
        if (SimdMath.USE_FMA) return projectOnPlane_fma(normal, dest);
        return projectOnPlane_mulAdd(normal, dest);
    }

    private Double4 projectOnPlane_fma(Double4R normal, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] normalData = ((Double4Impl) normal).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, normalData, 0).fma(DoubleVector.broadcast(COL_SPECIES, -Math.fma(normalData[3], sd[3], Math.fma(normalData[2], sd[2], Math.fma(normalData[0], sd[0], normalData[1] * sd[1])))), DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 projectOnPlane_mulAdd(Double4R normal, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] normalData = ((Double4Impl) normal).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, normalData, 0).mul(DoubleVector.broadcast(COL_SPECIES, -Math.fma(normalData[3], sd[3], Math.fma(normalData[2], sd[2], Math.fma(normalData[0], sd[0], normalData[1] * sd[1]))))).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t3 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        dd[0] = Math.fma(-normalX, _t3, sd[0]);
        dd[1] = Math.fma(-normalY, _t3, sd[1]);
        dd[2] = Math.fma(-normalZ, _t3, sd[2]);
        dd[3] = Math.fma(-normalW, _t3, sd[3]);
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
    public Double4 radians(@Mutated Double4 dest) {
        if (COL_NARROW) return radians_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.toRadians(sd[0])).withLane(1, Math.toRadians(sd[1])).withLane(2, Math.toRadians(sd[2])).withLane(3, Math.toRadians(sd[3])).intoArray(dd, 0);
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
    public Double4 reflect(Double4R normal, @Mutated Double4 dest) {
        if (COL_NARROW) return reflect_narrow(normal, dest);
        if (SimdMath.USE_FMA) return reflect_fma(normal, dest);
        return reflect_mulAdd(normal, dest);
    }

    private Double4 reflect_fma(Double4R normal, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] normalData = ((Double4Impl) normal).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, normalData, 0).fma(DoubleVector.broadcast(COL_SPECIES, -(2.0 * Math.fma(normalData[3], sd[3], Math.fma(normalData[2], sd[2], Math.fma(normalData[0], sd[0], normalData[1] * sd[1]))))), DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 reflect_mulAdd(Double4R normal, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] normalData = ((Double4Impl) normal).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, normalData, 0).mul(DoubleVector.broadcast(COL_SPECIES, -(2.0 * Math.fma(normalData[3], sd[3], Math.fma(normalData[2], sd[2], Math.fma(normalData[0], sd[0], normalData[1] * sd[1])))))).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t4 = 2.0 * Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        dd[0] = Math.fma(-normalX, _t4, sd[0]);
        dd[1] = Math.fma(-normalY, _t4, sd[1]);
        dd[2] = Math.fma(-normalZ, _t4, sd[2]);
        dd[3] = Math.fma(-normalW, _t4, sd[3]);
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
    public Double4 refract(Double4R normal, double eta, @Mutated Double4 dest) {
        if (COL_NARROW) return refract_narrow(normal, eta, dest);
        if (SimdMath.USE_FMA) return refract_fma(normal, eta, dest);
        return refract_mulAdd(normal, eta, dest);
    }

    private Double4 refract_fma(Double4R normal, double eta, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] normalData = ((Double4Impl) normal).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t4 = Math.fma(normalData[3], sd[3], Math.fma(normalData[2], sd[2], Math.fma(normalData[0], sd[0], normalData[1] * sd[1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8 >= 0.0 ? DoubleVector.broadcast(COL_SPECIES, eta).fma(DoubleVector.fromArray(COL_SPECIES, sd, 0), DoubleVector.fromArray(COL_SPECIES, normalData, 0).mul(DoubleVector.broadcast(COL_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(COL_SPECIES, 0.0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 refract_mulAdd(Double4R normal, double eta, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] normalData = ((Double4Impl) normal).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t4 = Math.fma(normalData[3], sd[3], Math.fma(normalData[2], sd[2], Math.fma(normalData[0], sd[0], normalData[1] * sd[1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8 >= 0.0 ? DoubleVector.broadcast(COL_SPECIES, eta).mul(DoubleVector.fromArray(COL_SPECIES, sd, 0)).add(DoubleVector.fromArray(COL_SPECIES, normalData, 0).mul(DoubleVector.broadcast(COL_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(COL_SPECIES, 0.0)).intoArray(dd, 0);
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
        if (COL_NARROW) return refract_narrow(normalX, normalY, normalZ, normalW, eta, dest);
        if (SimdMath.USE_FMA) return refract_fma(normalX, normalY, normalZ, normalW, eta, dest);
        return refract_mulAdd(normalX, normalY, normalZ, normalW, eta, dest);
    }

    private Double4 refract_fma(double normalX, double normalY, double normalZ, double normalW, double eta, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8 >= 0.0 ? DoubleVector.broadcast(COL_SPECIES, eta).fma(DoubleVector.fromArray(COL_SPECIES, sd, 0), DoubleVector.zero(COL_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(DoubleVector.broadcast(COL_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(COL_SPECIES, 0.0)).intoArray(dd, 0);
        return dest;
    }

    private Double4 refract_mulAdd(double normalX, double normalY, double normalZ, double normalW, double eta, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8 >= 0.0 ? DoubleVector.broadcast(COL_SPECIES, eta).mul(DoubleVector.fromArray(COL_SPECIES, sd, 0)).add(DoubleVector.zero(COL_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(DoubleVector.broadcast(COL_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(COL_SPECIES, 0.0)).intoArray(dd, 0);
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
    public Double4 round(@Mutated Double4 dest) {
        if (COL_NARROW) return round_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.rint(sd[0])).withLane(1, Math.rint(sd[1])).withLane(2, Math.rint(sd[2])).withLane(3, Math.rint(sd[3])).intoArray(dd, 0);
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
    public Double4 sign(@Mutated Double4 dest) {
        if (COL_NARROW) return sign_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.signum(sd[0])).withLane(1, Math.signum(sd[1])).withLane(2, Math.signum(sd[2])).withLane(3, Math.signum(sd[3])).intoArray(dd, 0);
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
    public Double4 sin(@Mutated Double4 dest) {
        if (COL_NARROW) return sin_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.sin(sd[0])).withLane(1, Math.sin(sd[1])).withLane(2, Math.sin(sd[2])).withLane(3, Math.sin(sd[3])).intoArray(dd, 0);
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
    public Double4 sinh(@Mutated Double4 dest) {
        if (COL_NARROW) return sinh_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.sinh(sd[0])).withLane(1, Math.sinh(sd[1])).withLane(2, Math.sinh(sd[2])).withLane(3, Math.sinh(sd[3])).intoArray(dd, 0);
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
    public Double4 smoothstep(double edge0, double edge1, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[0] - edge0) * _t0_inv));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[1] - edge0) * _t0_inv));
        double _t15 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[2] - edge0) * _t0_inv));
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[3] - edge0) * _t0_inv));
        dd[0] = Math.fma(-2.0, _t13, 3.0) * _t13 * _t13;
        dd[1] = Math.fma(-2.0, _t14, 3.0) * _t14 * _t14;
        dd[2] = Math.fma(-2.0, _t15, 3.0) * _t15 * _t15;
        dd[3] = Math.fma(-2.0, _t16, 3.0) * _t16 * _t16;
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
    public Double4 smoothstep(Double4R edge0, Double4R edge1, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] edge0Data = ((Double4Impl) edge0).data;
        double[] edge1Data = ((Double4Impl) edge1).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[0] - edge0Data[0]) / (edge1Data[0] - edge0Data[0])));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[1] - edge0Data[1]) / (edge1Data[1] - edge0Data[1])));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[2] - edge0Data[2]) / (edge1Data[2] - edge0Data[2])));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[3] - edge0Data[3]) / (edge1Data[3] - edge0Data[3])));
        dd[0] = Math.fma(-2.0, _t16, 3.0) * _t16 * _t16;
        dd[1] = Math.fma(-2.0, _t17, 3.0) * _t17 * _t17;
        dd[2] = Math.fma(-2.0, _t18, 3.0) * _t18 * _t18;
        dd[3] = Math.fma(-2.0, _t19, 3.0) * _t19 * _t19;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[0] - edge0X) / (edge1X - edge0X)));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[1] - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[2] - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (sd[3] - edge0W) / (edge1W - edge0W)));
        dd[0] = Math.fma(-2.0, _t16, 3.0) * _t16 * _t16;
        dd[1] = Math.fma(-2.0, _t17, 3.0) * _t17 * _t17;
        dd[2] = Math.fma(-2.0, _t18, 3.0) * _t18 * _t18;
        dd[3] = Math.fma(-2.0, _t19, 3.0) * _t19 * _t19;
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
    public Double4 sqrt(@Mutated Double4 dest) {
        if (COL_NARROW) return sqrt_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).sqrt().intoArray(dd, 0);
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
    public Double4 step(double edge, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] < edge ? 0.0 : 1.0;
        dd[1] = sd[1] < edge ? 0.0 : 1.0;
        dd[2] = sd[2] < edge ? 0.0 : 1.0;
        dd[3] = sd[3] < edge ? 0.0 : 1.0;
        return dest;
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
        double[] sd = this.data;
        double[] edgeData = ((Double4Impl) edge).data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] < edgeData[0] ? 0.0 : 1.0;
        dd[1] = sd[1] < edgeData[1] ? 0.0 : 1.0;
        dd[2] = sd[2] < edgeData[2] ? 0.0 : 1.0;
        dd[3] = sd[3] < edgeData[3] ? 0.0 : 1.0;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] < edgeX ? 0.0 : 1.0;
        dd[1] = sd[1] < edgeY ? 0.0 : 1.0;
        dd[2] = sd[2] < edgeZ ? 0.0 : 1.0;
        dd[3] = sd[3] < edgeW ? 0.0 : 1.0;
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
    public Double4 tan(@Mutated Double4 dest) {
        if (COL_NARROW) return tan_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.tan(sd[0])).withLane(1, Math.tan(sd[1])).withLane(2, Math.tan(sd[2])).withLane(3, Math.tan(sd[3])).intoArray(dd, 0);
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
    public Double4 tanh(@Mutated Double4 dest) {
        if (COL_NARROW) return tanh_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.tanh(sd[0])).withLane(1, Math.tanh(sd[1])).withLane(2, Math.tanh(sd[2])).withLane(3, Math.tanh(sd[3])).intoArray(dd, 0);
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
    public Double4 trunc(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] >= 0.0 ? Math.floor(sd[0]) : Math.ceil(sd[0]);
        dd[1] = sd[1] >= 0.0 ? Math.floor(sd[1]) : Math.ceil(sd[1]);
        dd[2] = sd[2] >= 0.0 ? Math.floor(sd[2]) : Math.ceil(sd[2]);
        dd[3] = sd[3] >= 0.0 ? Math.floor(sd[3]) : Math.ceil(sd[3]);
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
    public Double4 ulp(@Mutated Double4 dest) {
        if (COL_NARROW) return ulp_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, Math.ulp(sd[0])).withLane(1, Math.ulp(sd[1])).withLane(2, Math.ulp(sd[2])).withLane(3, Math.ulp(sd[3])).intoArray(dd, 0);
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
    public Double4 preMul(Double4x4R mat, @Mutated Double4 dest) {
        if (COL_NARROW) return preMul_narrow(mat, dest);
        if (SimdMath.USE_FMA) return preMul_fma(mat, dest);
        return preMul_mulAdd(mat, dest);
    }

    private Double4 preMul_fma(Double4x4R mat, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] matData = ((Double4x4Impl) mat).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, matData, 12).fma(DoubleVector.broadcast(COL_SPECIES, sd[3]), DoubleVector.fromArray(COL_SPECIES, matData, 8).fma(DoubleVector.broadcast(COL_SPECIES, sd[2]), DoubleVector.fromArray(COL_SPECIES, matData, 0).fma(DoubleVector.broadcast(COL_SPECIES, sd[0]), DoubleVector.fromArray(COL_SPECIES, matData, 4).mul(DoubleVector.broadcast(COL_SPECIES, sd[1]))))).intoArray(dd, 0);
        return dest;
    }

    private Double4 preMul_mulAdd(Double4x4R mat, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] matData = ((Double4x4Impl) mat).data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, matData, 12).mul(DoubleVector.broadcast(COL_SPECIES, sd[3])).add(DoubleVector.fromArray(COL_SPECIES, matData, 8).mul(DoubleVector.broadcast(COL_SPECIES, sd[2])).add(DoubleVector.fromArray(COL_SPECIES, matData, 0).mul(DoubleVector.broadcast(COL_SPECIES, sd[0])).add(DoubleVector.fromArray(COL_SPECIES, matData, 4).mul(DoubleVector.broadcast(COL_SPECIES, sd[1]))))).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] quatData = ((DoubleQuatImpl) quat).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t9 = 2.0 * Math.fma(quatData[0], sd[1], -(quatData[1] * sd[0]));
        double _t10 = 2.0 * Math.fma(quatData[2], sd[0], -(quatData[0] * sd[2]));
        double _t11 = 2.0 * Math.fma(quatData[1], sd[2], -(quatData[2] * sd[1]));
        dd[0] = Math.fma(quatData[1], _t9, Math.fma(-quatData[2], _t10, Math.fma(quatData[3], _t11, sd[0])));
        dd[1] = Math.fma(quatData[2], _t11, Math.fma(-quatData[0], _t9, Math.fma(quatData[3], _t10, sd[1])));
        dd[2] = Math.fma(quatData[0], _t10, Math.fma(-quatData[1], _t11, Math.fma(quatData[3], _t9, sd[2])));
        dd[3] = sd[3];
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t9 = 2.0 * Math.fma(quatX, sd[1], -(quatY * sd[0]));
        double _t10 = 2.0 * Math.fma(quatZ, sd[0], -(quatX * sd[2]));
        double _t11 = 2.0 * Math.fma(quatY, sd[2], -(quatZ * sd[1]));
        dd[0] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[1])));
        dd[2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, sd[2])));
        dd[3] = sd[3];
        return dest;
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
        if (axis.y() == 0 && axis.z() == 0 && java.lang.Math.abs(axis.x()) == 1) return rotateX(axis.x() * angle, dest);
        if (axis.x() == 0 && axis.z() == 0 && java.lang.Math.abs(axis.y()) == 1) return rotateY(axis.y() * angle, dest);
        if (axis.x() == 0 && axis.y() == 0 && java.lang.Math.abs(axis.z()) == 1) return rotateZ(axis.z() * angle, dest);
        double[] sd = this.data;
        double[] axisData = ((Double3Impl) axis).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisData[2], sd[2], Math.fma(axisData[0], sd[0], axisData[1] * sd[1]));
        return rotateAxis_s8b8e78d7_1(dest, sd, axisData, ((Double4Impl) dest).data, _t0, _t1, _t3, _t5, Math.fma(_t3, axisData[0] * _t5, Math.fma(sd[0], _t1, Math.fma(axisData[1], sd[2], -(axisData[2] * sd[1])) * _t0)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private Double4 rotateAxis_s8b8e78d7_1(Double4 dest, double[] sd, double[] axisData, double[] dd, double _t0, double _t1, double _t3, double _t5, double _buf0) {
        double _buf1 = Math.fma(_t3, axisData[1] * _t5, Math.fma(sd[1], _t1, Math.fma(axisData[2], sd[0], -(axisData[0] * sd[2])) * _t0));
        dd[2] = Math.fma(_t3, axisData[2] * _t5, Math.fma(sd[2], _t1, Math.fma(axisData[0], sd[1], -(axisData[1] * sd[0])) * _t0));
        dd[3] = sd[3];
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, sd[2], Math.fma(axisX, sd[0], axisY * sd[1]));
        double _buf0 = Math.fma(_t3, axisX * _t5, Math.fma(sd[0], _t1, Math.fma(axisY, sd[2], -(axisZ * sd[1])) * _t0));
        double _buf1 = Math.fma(_t3, axisY * _t5, Math.fma(sd[1], _t1, Math.fma(axisZ, sd[0], -(axisX * sd[2])) * _t0));
        dd[2] = Math.fma(_t3, axisZ * _t5, Math.fma(sd[2], _t1, Math.fma(axisX, sd[1], -(axisY * sd[0])) * _t0));
        dd[3] = sd[3];
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        double[] sd = this.data;
        double[] quatData = ((DoubleQuatImpl) quat).data;
        double[] dd = ((Double4Impl) dest).data;
        double _t9 = 2.0 * Math.fma(quatData[0], sd[2], -(quatData[2] * sd[0]));
        double _t10 = 2.0 * Math.fma(quatData[1], sd[0], -(quatData[0] * sd[1]));
        double _t11 = 2.0 * Math.fma(quatData[2], sd[1], -(quatData[1] * sd[2]));
        dd[0] = Math.fma(quatData[2], _t9, Math.fma(-quatData[1], _t10, Math.fma(quatData[3], _t11, sd[0])));
        dd[1] = Math.fma(quatData[0], _t10, Math.fma(-quatData[2], _t11, Math.fma(quatData[3], _t9, sd[1])));
        dd[2] = Math.fma(quatData[1], _t11, Math.fma(-quatData[0], _t9, Math.fma(quatData[3], _t10, sd[2])));
        dd[3] = sd[3];
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t9 = 2.0 * Math.fma(quatX, sd[2], -(quatZ * sd[0]));
        double _t10 = 2.0 * Math.fma(quatY, sd[0], -(quatX * sd[1]));
        double _t11 = 2.0 * Math.fma(quatZ, sd[1], -(quatY * sd[2]));
        dd[0] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, sd[0])));
        dd[1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, sd[1])));
        dd[2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, sd[2])));
        dd[3] = sd[3];
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = sd[0];
        double _buf0 = Math.fma(sd[1], _t1, -(sd[2] * _t0));
        dd[2] = Math.fma(sd[1], _t0, sd[2] * _t1);
        dd[3] = sd[3];
        dd[1] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, sd[2] * _t0);
        dd[1] = sd[1];
        dd[2] = Math.fma(sd[2], _t1, -(sd[0] * _t0));
        dd[3] = sd[3];
        dd[0] = _buf0;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[0] = _buf0;
        return dest;
    }

    public double x() { return data[0]; }
    public double y() { return data[1]; }
    public double z() { return data[2]; }
    public double w() { return data[3]; }

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

    public Double2 xw(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[3];
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

    public Double2 yw(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[3];
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

    public Double2 zw(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Double2 wx(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        return dest;
    }

    public Double2 wy(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        return dest;
    }

    public Double2 wz(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        return dest;
    }

    public Double2 ww(@Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        double _v0 = this.data[3];
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

    public Double3 xxw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
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

    public Double3 xyw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
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

    public Double3 xzw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 xwx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 xwy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 xwz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 xww(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
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

    public Double3 yxw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
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

    public Double3 yyw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
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

    public Double3 yzw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 ywx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 ywy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 ywz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 yww(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
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

    public Double3 zxw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
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

    public Double3 zyw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
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

    public Double3 zzw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 zwx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 zwy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 zwz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 zww(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 wxx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 wxy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 wxz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 wxw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 wyx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 wyy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 wyz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 wyw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 wzx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 wzy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Double3 wzz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Double3 wzw(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Double3 wwx(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 wwy(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 wwz(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Double3 www(@Mutated Double3 dest) {
        double[] dd = ((Double3Impl) dest).data;
        double _v0 = this.data[3];
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

    public Double4 xxxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
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

    public Double4 xxyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        double _v2 = sd[3];
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

    public Double4 xxzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xxwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xxwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xxwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xxww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
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

    public Double4 xyxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        double _v2 = sd[3];
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

    public Double4 xyyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
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

    public Double4 xyzw(@Mutated Double4 dest) {
        if (COL_NARROW) return xyzw_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        return dest;
    }

    public Double4 xywx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xywy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xywz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
        double _v3 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 xyww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
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

    public Double4 xzxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
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

    public Double4 xzyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        double _v3 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
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

    public Double4 xzzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xzwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xzwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        double _v3 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 xzwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xzww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xwxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xwxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xwxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xwxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xwyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xwyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xwyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        double _v3 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 xwyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xwzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xwzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        double _v3 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 xwzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xwzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 xwwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[0];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 xwwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xwwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 xwww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
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

    public Double4 yxxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
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

    public Double4 yxyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        double _v2 = sd[3];
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

    public Double4 yxzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        double _v3 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 yxwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yxwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yxwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
        double _v3 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 yxww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
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

    public Double4 yyxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[0];
        double _v2 = sd[3];
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

    public Double4 yyyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
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

    public Double4 yyzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yywx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yywy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yywz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yyww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
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

    public Double4 yzxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        double _v3 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
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

    public Double4 yzyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
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

    public Double4 yzzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 yzwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        double _v3 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 yzwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 yzwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 yzww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[3];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 ywxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 ywxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 ywxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        double _v3 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 ywxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 ywyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 ywyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 ywyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 ywyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 ywzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        double _v3 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 ywzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 ywzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 ywzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 ywwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 ywwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[1];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 ywwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 ywww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
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

    public Double4 zxxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
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

    public Double4 zxyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        double _v3 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
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

    public Double4 zxzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zxwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zxwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
        double _v3 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 zxwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zxww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
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

    public Double4 zyxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        double _v3 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
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

    public Double4 zyyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
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

    public Double4 zyzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zywx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
        double _v3 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 zywy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zywz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zyww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
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

    public Double4 zzxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[0];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
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

    public Double4 zzyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[1];
        double _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
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

    public Double4 zzzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zzwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zzwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zzwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zzww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zwxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zwxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        double _v3 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 zwxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zwxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zwyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        double _v3 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 zwyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zwyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zwyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zwzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zwzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zwzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zwzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 zwwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zwwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        double _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 zwwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[2];
        double _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 zwww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[3];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wxxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wxxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wxxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wxxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wxyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wxyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wxyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[1];
        double _v3 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 wxyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wxzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wxzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        double _v3 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 wxzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wxzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wxwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wxwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wxwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wxww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wyxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wyxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wyxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        double _v3 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 wyxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wyyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wyyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wyyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wyyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wyzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        double _v3 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 wyzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wyzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wyzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wywx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wywy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wywz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wyww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wzxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wzxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        double _v3 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 wzxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wzxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wzyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        double _v3 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Double4 wzyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wzyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wzyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wzzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wzzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wzzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[2];
        dd[0] = sd[3];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wzzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wzwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wzwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wzwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wzww(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wwxx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wwxy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wwxz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wwxw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wwyx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wwyy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wwyz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        double _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wwyw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wwzx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        double _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wwzy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        double _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Double4 wwzz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wwzw(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Double4 wwwx(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wwwy(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wwwz(@Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = sd[3];
        double _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Double4 wwww(@Mutated Double4 dest) {
        double[] dd = ((Double4Impl) dest).data;
        double _v0 = this.data[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Double4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double4Impl)) return false;
        Double4Impl o = (Double4Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(Double4R other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.x()) <= epsilon
            && java.lang.Math.abs(data[1] - other.y()) <= epsilon
            && java.lang.Math.abs(data[2] - other.z()) <= epsilon
            && java.lang.Math.abs(data[3] - other.w()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        if (COL_NARROW) return store_narrow(dest, offset);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }
    public @Mutated Double4 load(double[] src, int offset) {
        if (COL_NARROW) return load_narrow(src, offset);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        if (COL_NARROW) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        if (!buf.hasArray()) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        double[] d = this.data;
        double[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        return buf;
    }
    @Mutated public Double4 loadAbsolute(int index, DoubleBuffer buf) {
        if (COL_NARROW) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        if (!buf.hasArray()) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        double[] d = this.data;
        double[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        DoubleVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public Double4 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Double4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Double4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public Double4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Double4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        if (COL_NARROW) return store_narrow(offset, dest);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(dest, offset, ByteOrder.nativeOrder());
        return dest;
    }
    public Double4 load(long offset, MemorySegment src) {
        if (COL_NARROW) return load_narrow(offset, src);
        double[] d = this.data;
        DoubleVector.fromMemorySegment(COL_SPECIES, src, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        return dest;
    }
    public @Mutated Double4 load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
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
    @Mutated public Double4 load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double4 loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Double4 loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public Double4 loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public Double4 loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public Double4 loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4 r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public Double4 storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public Double4 loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    @Mutated public Double4 loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }
    public Double4 loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final VectorMask<Double> MASK_0 = VectorMask.fromValues(COL_SPECIES, false, false, true, true);
    private static final VectorMask<Double> MASK_1 = VectorMask.fromValues(COL_SPECIES, false, true, false, true);

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

    /** The column species is wider than this machine's vector unit: take the scalar twins. */
    private static final boolean COL_NARROW = COL_SPECIES.length() > DoubleVector.SPECIES_PREFERRED.length();

    private Double4 add_narrow(Double4R other, Double4 dest) {
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
    }

    private Double4 div_narrow(double scalar, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] / scalar;
        dd[1] = sd[1] / scalar;
        dd[2] = sd[2] / scalar;
        dd[3] = sd[3] / scalar;
        return dest;
    }

    private Double4 div_narrow(Double4R other, Double4 dest) {
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] / other.x();
        dd[1] = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        return dest;
    }

    private Double4 mul_narrow(double scalar, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        return dest;
    }

    private Double4 mul_narrow(Double4R other, Double4 dest) {
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        dd[3] = otherW * sd[3];
        return dest;
    }

    private Double4 negate_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        return dest;
    }

    private Double4 sub_narrow(Double4R other, Double4 dest) {
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
    }

    private Double4 makeUniformDirection_narrow(double u, double v, double w) {
        double[] dd = this.data;
        double _t0 = java.lang.Math.sqrt(u);
        double _t1 = v * 6.283185307179586;
        double _t3 = w * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u);
        double _t6 = Math.sin(_t3);
        dd[0] = Math.cosFromSin(_t4, _t1) * _t5;
        dd[1] = _t4 * _t5;
        dd[2] = Math.cosFromSin(_t6, _t3) * _t0;
        dd[3] = _t6 * _t0;
        return this;
    }

    private Double4 set_narrow(Double4R v) {
        double vY = v.y();
        double vZ = v.z();
        double vW = v.w();
        double[] dd = this.data;
        dd[0] = v.x();
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
        return this;
    }

    private Double4 set_narrow(double s, Double4 dest) {
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        dd[3] = s;
        return dest;
    }

    private Double4 makeZero_narrow() {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        return this;
    }

    private Double4 absolute_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
        dd[3] = java.lang.Math.abs(sd[3]);
        return dest;
    }

    private Double4 acos_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.acos(sd[0]);
        dd[1] = Math.acos(sd[1]);
        dd[2] = Math.acos(sd[2]);
        dd[3] = Math.acos(sd[3]);
        return dest;
    }

    private Double4 asin_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.asin(sd[0]);
        dd[1] = Math.asin(sd[1]);
        dd[2] = Math.asin(sd[2]);
        dd[3] = Math.asin(sd[3]);
        return dest;
    }

    private Double4 atan_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan(sd[0]);
        dd[1] = Math.atan(sd[1]);
        dd[2] = Math.atan(sd[2]);
        dd[3] = Math.atan(sd[3]);
        return dest;
    }

    private Double4 atan2_narrow(double x, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], x);
        dd[1] = Math.atan2(sd[1], x);
        dd[2] = Math.atan2(sd[2], x);
        dd[3] = Math.atan2(sd[3], x);
        return dest;
    }

    private Double4 atan2_narrow(Double4R x, Double4 dest) {
        double xY = x.y();
        double xZ = x.z();
        double xW = x.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.atan2(sd[0], x.x());
        dd[1] = Math.atan2(sd[1], xY);
        dd[2] = Math.atan2(sd[2], xZ);
        dd[3] = Math.atan2(sd[3], xW);
        return dest;
    }

    private Double4 cbrt_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.cbrt(sd[0]);
        dd[1] = Math.cbrt(sd[1]);
        dd[2] = Math.cbrt(sd[2]);
        dd[3] = Math.cbrt(sd[3]);
        return dest;
    }

    private Double4 ceil_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.ceil(sd[0]);
        dd[1] = Math.ceil(sd[1]);
        dd[2] = Math.ceil(sd[2]);
        dd[3] = Math.ceil(sd[3]);
        return dest;
    }

    private Double4 clamp_narrow(double min, double max, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], min), max);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], min), max);
        return dest;
    }

    private Double4 clamp_narrow(Double4R min, Double4R max, Double4 dest) {
        double minY = min.y();
        double minZ = min.z();
        double minW = min.w();
        double maxY = max.y();
        double maxZ = max.z();
        double maxW = max.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        dd[3] = java.lang.Math.min(java.lang.Math.max(sd[3], minW), maxW);
        return dest;
    }

    private Double4 copySign_narrow(double sign, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], sign);
        dd[1] = Math.copySign(sd[1], sign);
        dd[2] = Math.copySign(sd[2], sign);
        dd[3] = Math.copySign(sd[3], sign);
        return dest;
    }

    private Double4 copySign_narrow(Double4R sign, Double4 dest) {
        double signY = sign.y();
        double signZ = sign.z();
        double signW = sign.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.copySign(sd[0], sign.x());
        dd[1] = Math.copySign(sd[1], signY);
        dd[2] = Math.copySign(sd[2], signZ);
        dd[3] = Math.copySign(sd[3], signW);
        return dest;
    }

    private Double4 cos_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.cos(sd[0]);
        dd[1] = Math.cos(sd[1]);
        dd[2] = Math.cos(sd[2]);
        dd[3] = Math.cos(sd[3]);
        return dest;
    }

    private Double4 cosh_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.cosh(sd[0]);
        dd[1] = Math.cosh(sd[1]);
        dd[2] = Math.cosh(sd[2]);
        dd[3] = Math.cosh(sd[3]);
        return dest;
    }

    private Double4 degrees_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.toDegrees(sd[0]);
        dd[1] = Math.toDegrees(sd[1]);
        dd[2] = Math.toDegrees(sd[2]);
        dd[3] = Math.toDegrees(sd[3]);
        return dest;
    }

    private Double4 exp_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.exp(sd[0]);
        dd[1] = Math.exp(sd[1]);
        dd[2] = Math.exp(sd[2]);
        dd[3] = Math.exp(sd[3]);
        return dest;
    }

    private Double4 exp2_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(2.0, sd[0]);
        dd[1] = Math.pow(2.0, sd[1]);
        dd[2] = Math.pow(2.0, sd[2]);
        dd[3] = Math.pow(2.0, sd[3]);
        return dest;
    }

    private Double4 expm1_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.expm1(sd[0]);
        dd[1] = Math.expm1(sd[1]);
        dd[2] = Math.expm1(sd[2]);
        dd[3] = Math.expm1(sd[3]);
        return dest;
    }

    private Double4 faceforward_narrow(Double4R I, Double4R Nref, Double4 dest) {
        double IX = I.x();
        double IY = I.y();
        double IZ = I.z();
        double IW = I.w();
        double NrefX = Nref.x();
        double NrefY = Nref.y();
        double NrefZ = Nref.z();
        double NrefW = Nref.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        }
    }

    private Double4 faceforward_narrow(double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW, Double4 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0 ? 1.0 : -1.0;
            dd[0] = sd[0] * _t4;
            dd[1] = sd[1] * _t4;
            dd[2] = sd[2] * _t4;
            dd[3] = sd[3] * _t4;
            return dest;
        }
    }

    private Double4 floor_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.floor(sd[0]);
        dd[1] = Math.floor(sd[1]);
        dd[2] = Math.floor(sd[2]);
        dd[3] = Math.floor(sd[3]);
        return dest;
    }

    private Double4 fract_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0] - Math.floor(sd[0]), 0.9999999999999999);
        dd[1] = java.lang.Math.min(sd[1] - Math.floor(sd[1]), 0.9999999999999999);
        dd[2] = java.lang.Math.min(sd[2] - Math.floor(sd[2]), 0.9999999999999999);
        dd[3] = java.lang.Math.min(sd[3] - Math.floor(sd[3]), 0.9999999999999999);
        return dest;
    }

    private Double4 hypot_narrow(double y, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], y);
        dd[1] = Math.hypot(sd[1], y);
        dd[2] = Math.hypot(sd[2], y);
        dd[3] = Math.hypot(sd[3], y);
        return dest;
    }

    private Double4 hypot_narrow(Double4R y, Double4 dest) {
        double yY = y.y();
        double yZ = y.z();
        double yW = y.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.hypot(sd[0], y.x());
        dd[1] = Math.hypot(sd[1], yY);
        dd[2] = Math.hypot(sd[2], yZ);
        dd[3] = Math.hypot(sd[3], yW);
        return dest;
    }

    private Double4 inverse_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = 1.0 / sd[0];
        dd[1] = 1.0 / sd[1];
        dd[2] = 1.0 / sd[2];
        dd[3] = 1.0 / sd[3];
        return dest;
    }

    private Double4 inverseSqrt_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = (1.0 / java.lang.Math.sqrt(sd[0]));
        dd[1] = (1.0 / java.lang.Math.sqrt(sd[1]));
        dd[2] = (1.0 / java.lang.Math.sqrt(sd[2]));
        dd[3] = (1.0 / java.lang.Math.sqrt(sd[3]));
        return dest;
    }

    private Double4 log_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log(sd[0]);
        dd[1] = Math.log(sd[1]);
        dd[2] = Math.log(sd[2]);
        dd[3] = Math.log(sd[3]);
        return dest;
    }

    private Double4 log10_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log10(sd[0]);
        dd[1] = Math.log10(sd[1]);
        dd[2] = Math.log10(sd[2]);
        dd[3] = Math.log10(sd[3]);
        return dest;
    }

    private Double4 log1p_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.log1p(sd[0]);
        dd[1] = Math.log1p(sd[1]);
        dd[2] = Math.log1p(sd[2]);
        dd[3] = Math.log1p(sd[3]);
        return dest;
    }

    private Double4 max_narrow(double scalar, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        dd[2] = java.lang.Math.max(sd[2], scalar);
        dd[3] = java.lang.Math.max(sd[3], scalar);
        return dest;
    }

    private Double4 max_narrow(Double4R other, Double4 dest) {
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        dd[3] = java.lang.Math.max(sd[3], otherW);
        return dest;
    }

    private Double4 min_narrow(double scalar, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        dd[2] = java.lang.Math.min(sd[2], scalar);
        dd[3] = java.lang.Math.min(sd[3], scalar);
        return dest;
    }

    private Double4 min_narrow(Double4R other, Double4 dest) {
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        dd[3] = java.lang.Math.min(sd[3], otherW);
        return dest;
    }

    private Double4 nextDown_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.nextDown(sd[0]);
        dd[1] = Math.nextDown(sd[1]);
        dd[2] = Math.nextDown(sd[2]);
        dd[3] = Math.nextDown(sd[3]);
        return dest;
    }

    private Double4 nextUp_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.nextUp(sd[0]);
        dd[1] = Math.nextUp(sd[1]);
        dd[2] = Math.nextUp(sd[2]);
        dd[3] = Math.nextUp(sd[3]);
        return dest;
    }

    private Double4 normalize_narrow(Double4 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t3 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t3 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dd[0] = sd[0] * _t4;
                dd[1] = sd[1] * _t4;
                dd[2] = sd[2] * _t4;
                dd[3] = sd[3] * _t4;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
            return dest;
        }
    }

    private Double4 normalizeMul_narrow(double length, Double4 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t3 = java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])));
            double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dd[0] = sd[0] * _t5;
                dd[1] = sd[1] * _t5;
                dd[2] = sd[2] * _t5;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t3 = ((sd[3]) * (sd[3]) + (((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dd[0] = sd[0] * _t5;
                dd[1] = sd[1] * _t5;
                dd[2] = sd[2] * _t5;
                dd[3] = sd[3] * _t5;
            } else {
                dd[0] = 0.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 0.0;
            }
            return dest;
        }
    }

    private Double4x4 outerProduct_narrow(Double4R row, Double4x4 dest) {
        double rowX = row.x();
        double rowY = row.y();
        double rowZ = row.z();
        double rowW = row.w();
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = rowX * _rd0;
        dd[1] = rowX * _rd1;
        dd[2] = rowX * _rd2;
        dd[3] = rowX * _rd3;
        dd[4] = rowY * _rd0;
        dd[5] = rowY * _rd1;
        dd[6] = rowY * _rd2;
        dd[7] = rowY * _rd3;
        dd[8] = rowZ * _rd0;
        dd[9] = rowZ * _rd1;
        dd[10] = rowZ * _rd2;
        dd[11] = rowZ * _rd3;
        dd[12] = rowW * _rd0;
        dd[13] = rowW * _rd1;
        dd[14] = rowW * _rd2;
        dd[15] = rowW * _rd3;
        ((Double4x4Impl) dest).properties = 0;
        return dest;
    }

    private Double4x4 outerProduct_narrow(double rowX, double rowY, double rowZ, double rowW, Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = rowX * _rd0;
        dd[1] = rowX * _rd1;
        dd[2] = rowX * _rd2;
        dd[3] = rowX * _rd3;
        dd[4] = rowY * _rd0;
        dd[5] = rowY * _rd1;
        dd[6] = rowY * _rd2;
        dd[7] = rowY * _rd3;
        dd[8] = rowZ * _rd0;
        dd[9] = rowZ * _rd1;
        dd[10] = rowZ * _rd2;
        dd[11] = rowZ * _rd3;
        dd[12] = rowW * _rd0;
        dd[13] = rowW * _rd1;
        dd[14] = rowW * _rd2;
        dd[15] = rowW * _rd3;
        ((Double4x4Impl) dest).properties = 0;
        return dest;
    }

    private Double4 pow_narrow(double exponent, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponent);
        dd[1] = Math.pow(sd[1], exponent);
        dd[2] = Math.pow(sd[2], exponent);
        dd[3] = Math.pow(sd[3], exponent);
        return dest;
    }

    private Double4 pow_narrow(Double4R exponent, Double4 dest) {
        double exponentY = exponent.y();
        double exponentZ = exponent.z();
        double exponentW = exponent.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.pow(sd[0], exponent.x());
        dd[1] = Math.pow(sd[1], exponentY);
        dd[2] = Math.pow(sd[2], exponentZ);
        dd[3] = Math.pow(sd[3], exponentW);
        return dest;
    }

    private Double4 project_narrow(Double4R onto, Double4 dest) {
        double ontoX = onto.x();
        double ontoY = onto.y();
        double ontoZ = onto.z();
        double ontoW = onto.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t9 = java.lang.Math.fma(ontoW, sd[3], java.lang.Math.fma(ontoZ, sd[2], java.lang.Math.fma(ontoX, sd[0], ontoY * sd[1]))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t9 = ((ontoW) * (sd[3]) + (((ontoZ) * (sd[2]) + (((ontoX) * (sd[0]) + (ontoY * sd[1])))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            dd[0] = ontoX * _t9;
            dd[1] = ontoY * _t9;
            dd[2] = ontoZ * _t9;
            dd[3] = ontoW * _t9;
            return dest;
        }
    }

    private Double4 radians_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.toRadians(sd[0]);
        dd[1] = Math.toRadians(sd[1]);
        dd[2] = Math.toRadians(sd[2]);
        dd[3] = Math.toRadians(sd[3]);
        return dest;
    }

    private Double4 round_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.rint(sd[0]);
        dd[1] = Math.rint(sd[1]);
        dd[2] = Math.rint(sd[2]);
        dd[3] = Math.rint(sd[3]);
        return dest;
    }

    private Double4 sign_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        dd[3] = Math.signum(sd[3]);
        return dest;
    }

    private Double4 sin_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.sin(sd[0]);
        dd[1] = Math.sin(sd[1]);
        dd[2] = Math.sin(sd[2]);
        dd[3] = Math.sin(sd[3]);
        return dest;
    }

    private Double4 sinh_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.sinh(sd[0]);
        dd[1] = Math.sinh(sd[1]);
        dd[2] = Math.sinh(sd[2]);
        dd[3] = Math.sinh(sd[3]);
        return dest;
    }

    private Double4 sqrt_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = java.lang.Math.sqrt(sd[0]);
        dd[1] = java.lang.Math.sqrt(sd[1]);
        dd[2] = java.lang.Math.sqrt(sd[2]);
        dd[3] = java.lang.Math.sqrt(sd[3]);
        return dest;
    }

    private Double4 tan_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.tan(sd[0]);
        dd[1] = Math.tan(sd[1]);
        dd[2] = Math.tan(sd[2]);
        dd[3] = Math.tan(sd[3]);
        return dest;
    }

    private Double4 tanh_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.tanh(sd[0]);
        dd[1] = Math.tanh(sd[1]);
        dd[2] = Math.tanh(sd[2]);
        dd[3] = Math.tanh(sd[3]);
        return dest;
    }

    private Double4 ulp_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.ulp(sd[0]);
        dd[1] = Math.ulp(sd[1]);
        dd[2] = Math.ulp(sd[2]);
        dd[3] = Math.ulp(sd[3]);
        return dest;
    }

    private Double4 xyzw_narrow(Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _v1 = sd[1];
        double _v2 = sd[2];
        double _v3 = sd[3];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    private double[] store_narrow(double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    private Double4 load_narrow(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }

    private MemorySegment store_narrow(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    private Double4 load_narrow(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    private Double4 fma_narrow(double b, Double4R c, Double4 dest) {
        double cX = c.x();
        double cY = c.y();
        double cZ = c.z();
        double cW = c.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], b, cX);
            dd[1] = java.lang.Math.fma(sd[1], b, cY);
            dd[2] = java.lang.Math.fma(sd[2], b, cZ);
            dd[3] = java.lang.Math.fma(sd[3], b, cW);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (b) + (cX));
            dd[1] = ((sd[1]) * (b) + (cY));
            dd[2] = ((sd[2]) * (b) + (cZ));
            dd[3] = ((sd[3]) * (b) + (cW));
            return dest;
        }
    }

    private Double4 fma_narrow(Double4R b, Double4R c, Double4 dest) {
        double bX = b.x();
        double bY = b.y();
        double bZ = b.z();
        double bW = b.w();
        double cX = c.x();
        double cY = c.y();
        double cZ = c.z();
        double cW = c.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], bX, cX);
            dd[1] = java.lang.Math.fma(sd[1], bY, cY);
            dd[2] = java.lang.Math.fma(sd[2], bZ, cZ);
            dd[3] = java.lang.Math.fma(sd[3], bW, cW);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (bX) + (cX));
            dd[1] = ((sd[1]) * (bY) + (cY));
            dd[2] = ((sd[2]) * (bZ) + (cZ));
            dd[3] = ((sd[3]) * (bW) + (cW));
            return dest;
        }
    }

    private Double4 bezier_narrow(Double4R p1, Double4R p2, Double4R p3, double t, Double4 dest) {
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p1W = p1.w();
        double p2Y = p2.y();
        double p2Z = p2.z();
        double p2W = p2.w();
        double p3Y = p3.y();
        double p3Z = p3.z();
        double p3W = p3.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dd[0] = Math.fma(p1.x(), _t7, sd[0] * _t8) + Math.fma(p2.x(), _t6, p3.x() * _t2);
        dd[1] = Math.fma(p1Y, _t7, sd[1] * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dd[2] = Math.fma(p1Z, _t7, sd[2] * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        dd[3] = Math.fma(p1W, _t7, sd[3] * _t8) + Math.fma(p2W, _t6, p3W * _t2);
        return dest;
    }

    private Double4 bezier2_narrow(Double4R p1, Double4R p2, double t, Double4 dest) {
        return bezier2(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), t, dest);
    }

    private Double4 bezier2Tangent_narrow(Double4R p1, Double4R p2, double t, Double4 dest) {
        return bezier2Tangent(p1.x(), p1.y(), p1.z(), p1.w(), p2.x(), p2.y(), p2.z(), p2.w(), t, dest);
    }

    private Double4 bezierTangent_narrow(Double4R p1, Double4R p2, Double4R p3, double t, Double4 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p1W = p1.w();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p2Z = p2.z();
        double p2W = p2.w();
        double p3Y = p3.y();
        double p3Z = p3.z();
        double p3W = p3.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dd[0] = Math.fma(p3.x() - p2X, _t2, Math.fma(p1X - sd[0], _t6, (p2X - p1X) * _t5));
        dd[1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - sd[1], _t6, (p2Y - p1Y) * _t5));
        dd[2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - sd[2], _t6, (p2Z - p1Z) * _t5));
        dd[3] = Math.fma(p3W - p2W, _t2, Math.fma(p1W - sd[3], _t6, (p2W - p1W) * _t5));
        return dest;
    }

    private Double4 catmullRom_narrow(Double4R p1, Double4R p2, Double4R p3, double t, Double4 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p1W = p1.w();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p2Z = p2.z();
        double p2W = p2.w();
        double p3X = p3.x();
        double p3Y = p3.y();
        double p3Z = p3.z();
        double p3W = p3.w();
        if (Math.useFma()) return catmullRom_fma_narrow(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRom_mulAdd_narrow(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }

    private Double4 catmullRomTangent_narrow(Double4R p1, Double4R p2, Double4R p3, double t, Double4 dest) {
        double p1X = p1.x();
        double p1Y = p1.y();
        double p1Z = p1.z();
        double p1W = p1.w();
        double p2X = p2.x();
        double p2Y = p2.y();
        double p2Z = p2.z();
        double p2W = p2.w();
        double p3X = p3.x();
        double p3Y = p3.y();
        double p3Z = p3.z();
        double p3W = p3.w();
        if (Math.useFma()) return catmullRomTangent_fma_narrow(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
        return catmullRomTangent_mulAdd_narrow(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, dest);
    }

    private Double4 hermite_narrow(Double4R t0, Double4R v1, Double4R t1, double t, Double4 dest) {
        return hermite(t0.x(), t0.y(), t0.z(), t0.w(), v1.x(), v1.y(), v1.z(), v1.w(), t1.x(), t1.y(), t1.z(), t1.w(), t, dest);
    }

    private Double4 hermiteTangent_narrow(Double4R t0, Double4R v1, Double4R t1, double t, Double4 dest) {
        return hermiteTangent(t0.x(), t0.y(), t0.z(), t0.w(), v1.x(), v1.y(), v1.z(), v1.w(), t1.x(), t1.y(), t1.z(), t1.w(), t, dest);
    }

    private Double4 lerp_narrow(Double4R other, double t, Double4 dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }

    private Double4 lerp_narrow(double otherX, double otherY, double otherZ, double otherW, double t, Double4 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherW - sd[3], sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((t) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }

    private Double4 lerp_narrow(Double4R other, Double4R t, Double4 dest) {
        double otherX = other.x();
        double otherY = other.y();
        double otherZ = other.z();
        double otherW = other.w();
        double tX = t.x();
        double tY = t.y();
        double tZ = t.z();
        double tW = t.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(tX, otherX - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(tY, otherY - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(tZ, otherZ - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(tW, otherW - sd[3], sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((tX) * (otherX - sd[0]) + (sd[0]));
            dd[1] = ((tY) * (otherY - sd[1]) + (sd[1]));
            dd[2] = ((tZ) * (otherZ - sd[2]) + (sd[2]));
            dd[3] = ((tW) * (otherW - sd[3]) + (sd[3]));
            return dest;
        }
    }

    private Double4 addScaled_narrow(Double4R b, double scalar, Double4 dest) {
        double bX = b.x();
        double bY = b.y();
        double bZ = b.z();
        double bW = b.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(scalar, bX, sd[0]);
            dd[1] = java.lang.Math.fma(scalar, bY, sd[1]);
            dd[2] = java.lang.Math.fma(scalar, bZ, sd[2]);
            dd[3] = java.lang.Math.fma(scalar, bW, sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((scalar) * (bX) + (sd[0]));
            dd[1] = ((scalar) * (bY) + (sd[1]));
            dd[2] = ((scalar) * (bZ) + (sd[2]));
            dd[3] = ((scalar) * (bW) + (sd[3]));
            return dest;
        }
    }

    private Double4 addScaled_narrow(Double4R b, Double4R c, Double4 dest) {
        double bX = b.x();
        double bY = b.y();
        double bZ = b.z();
        double bW = b.w();
        double cX = c.x();
        double cY = c.y();
        double cZ = c.z();
        double cW = c.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(bX, cX, sd[0]);
            dd[1] = java.lang.Math.fma(bY, cY, sd[1]);
            dd[2] = java.lang.Math.fma(bZ, cZ, sd[2]);
            dd[3] = java.lang.Math.fma(bW, cW, sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((bX) * (cX) + (sd[0]));
            dd[1] = ((bY) * (cY) + (sd[1]));
            dd[2] = ((bZ) * (cZ) + (sd[2]));
            dd[3] = ((bW) * (cW) + (sd[3]));
            return dest;
        }
    }

    private Double4 cross_narrow(double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t12 = Math.fma(vY, wZ, -(vZ * wY));
        double _t13 = Math.fma(vZ, wW, -(vW * wZ));
        double _t14 = Math.fma(vY, wW, -(vW * wY));
        double _t15 = Math.fma(vX, wZ, -(vZ * wX));
        double _t16 = Math.fma(vX, wW, -(vW * wX));
        double _t17 = Math.fma(vX, wY, -(vY * wX));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(_rd3, _t12, Math.fma(_rd1, _t13, -(_rd2 * _t14)));
        dd[1] = Math.fma(-_rd3, _t15, Math.fma(_rd2, _t16, -(_rd0 * _t13)));
        dd[2] = Math.fma(_rd3, _t17, Math.fma(_rd0, _t14, -(_rd1 * _t16)));
        dd[3] = Math.fma(-_rd2, _t17, Math.fma(_rd1, _t15, -(_rd0 * _t12)));
        return dest;
    }

    private Double4 projectOnPlane_narrow(Double4R normal, Double4 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        double normalZ = normal.z();
        double normalW = normal.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t3 = java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t3, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t3, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t3, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t3, sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t3 = ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t3) + (sd[0]));
            dd[1] = ((-normalY) * (_t3) + (sd[1]));
            dd[2] = ((-normalZ) * (_t3) + (sd[2]));
            dd[3] = ((-normalW) * (_t3) + (sd[3]));
            return dest;
        }
    }

    private Double4 reflect_narrow(Double4R normal, Double4 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        double normalZ = normal.z();
        double normalW = normal.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t4 = 2.0 * java.lang.Math.fma(normalW, sd[3], java.lang.Math.fma(normalZ, sd[2], java.lang.Math.fma(normalX, sd[0], normalY * sd[1])));
            dd[0] = java.lang.Math.fma(-normalX, _t4, sd[0]);
            dd[1] = java.lang.Math.fma(-normalY, _t4, sd[1]);
            dd[2] = java.lang.Math.fma(-normalZ, _t4, sd[2]);
            dd[3] = java.lang.Math.fma(-normalW, _t4, sd[3]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            double _t4 = 2.0 * ((normalW) * (sd[3]) + (((normalZ) * (sd[2]) + (((normalX) * (sd[0]) + (normalY * sd[1]))))));
            dd[0] = ((-normalX) * (_t4) + (sd[0]));
            dd[1] = ((-normalY) * (_t4) + (sd[1]));
            dd[2] = ((-normalZ) * (_t4) + (sd[2]));
            dd[3] = ((-normalW) * (_t4) + (sd[3]));
            return dest;
        }
    }

    private Double4 refract_narrow(Double4R normal, double eta, Double4 dest) {
        double normalX = normal.x();
        double normalY = normal.y();
        double normalZ = normal.z();
        double normalW = normal.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dd[0] = Math.fma(eta, sd[0], -(normalX * _t11));
            dd[1] = Math.fma(eta, sd[1], -(normalY * _t11));
            dd[2] = Math.fma(eta, sd[2], -(normalZ * _t11));
            dd[3] = Math.fma(eta, sd[3], -(normalW * _t11));
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    private Double4 refract_narrow(double normalX, double normalY, double normalZ, double normalW, double eta, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t4 = Math.fma(normalW, sd[3], Math.fma(normalZ, sd[2], Math.fma(normalX, sd[0], normalY * sd[1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dd[0] = Math.fma(eta, sd[0], -(normalX * _t11));
            dd[1] = Math.fma(eta, sd[1], -(normalY * _t11));
            dd[2] = Math.fma(eta, sd[2], -(normalZ * _t11));
            dd[3] = Math.fma(eta, sd[3], -(normalW * _t11));
        } else {
            dd[0] = 0.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 0.0;
        }
        return dest;
    }

    private Double4 preMul_narrow(Double4x4R mat, Double4 dest) {
        double[] sd = this.data;
        double[] matData = ((Double4x4Impl) mat).data;
        double[] dd = ((Double4Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(matData[12], _rd3, Math.fma(matData[8], _rd2, Math.fma(matData[0], _rd0, matData[4] * _rd1)));
        dd[1] = Math.fma(matData[13], _rd3, Math.fma(matData[9], _rd2, Math.fma(matData[1], _rd0, matData[5] * _rd1)));
        dd[2] = Math.fma(matData[14], _rd3, Math.fma(matData[10], _rd2, Math.fma(matData[2], _rd0, matData[6] * _rd1)));
        dd[3] = Math.fma(matData[15], _rd3, Math.fma(matData[11], _rd2, Math.fma(matData[3], _rd0, matData[7] * _rd1)));
        return dest;
    }

    private Double4 cross_narrow(Double4R v, Double4R w, Double4 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double vW = v.w();
        double wX = w.x();
        double wY = w.y();
        double wZ = w.z();
        double wW = w.w();
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t12 = Math.fma(vY, wZ, -(vZ * wY));
        double _t13 = Math.fma(vZ, wW, -(vW * wZ));
        double _t14 = Math.fma(vY, wW, -(vW * wY));
        double _t15 = Math.fma(vX, wZ, -(vZ * wX));
        double _t16 = Math.fma(vX, wW, -(vW * wX));
        double _t17 = Math.fma(vX, wY, -(vY * wX));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(_rd3, _t12, Math.fma(_rd1, _t13, -(_rd2 * _t14)));
        dd[1] = Math.fma(-_rd3, _t15, Math.fma(_rd2, _t16, -(_rd0 * _t13)));
        dd[2] = Math.fma(_rd3, _t17, Math.fma(_rd0, _t14, -(_rd1 * _t16)));
        dd[3] = Math.fma(-_rd2, _t17, Math.fma(_rd1, _t15, -(_rd0 * _t12)));
        return dest;
    }

    private Double4 catmullRom_fma_narrow(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t1 = t * _t0;
        dd[0] = 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - sd[0])) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, sd[0], java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - sd[0])) * _t1));
        dd[1] = 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - sd[1])) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, sd[1], java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - sd[1])) * _t1));
        dd[2] = 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - sd[2])) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, sd[2], java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - sd[2])) * _t1));
        return catmullRom_scde09e05_1_fma_narrow(p1W, p2W, p3W, t, dest, sd, dd, _t0, _t1);
    }

    private Double4 catmullRom_mulAdd_narrow(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        double _t1 = t * _t0;
        dd[0] = 0.5 * (((2.0) * (p1X) + (t * (p2X - sd[0]))) + ((((-5.0) * (p1X) + (((2.0) * (sd[0]) + (((4.0) * (p2X) - (p3X))))))) * (_t0) + (((-3.0) * (p2X) + (((3.0) * (p1X) + (p3X - sd[0])))) * _t1)));
        dd[1] = 0.5 * (((2.0) * (p1Y) + (t * (p2Y - sd[1]))) + ((((-5.0) * (p1Y) + (((2.0) * (sd[1]) + (((4.0) * (p2Y) - (p3Y))))))) * (_t0) + (((-3.0) * (p2Y) + (((3.0) * (p1Y) + (p3Y - sd[1])))) * _t1)));
        dd[2] = 0.5 * (((2.0) * (p1Z) + (t * (p2Z - sd[2]))) + ((((-5.0) * (p1Z) + (((2.0) * (sd[2]) + (((4.0) * (p2Z) - (p3Z))))))) * (_t0) + (((-3.0) * (p2Z) + (((3.0) * (p1Z) + (p3Z - sd[2])))) * _t1)));
        return catmullRom_scde09e05_1_mulAdd_narrow(p1W, p2W, p3W, t, dest, sd, dd, _t0, _t1);
    }

    private Double4 catmullRomTangent_fma_narrow(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        dd[0] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, sd[0], java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - sd[0])), _t0, p2X - sd[0]));
        dd[1] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, sd[1], java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - sd[1])), _t0, p2Y - sd[1]));
        dd[2] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, sd[2], java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - sd[2])), _t0, p2Z - sd[2]));
        return catmullRomTangent_s66d1d242_1_fma_narrow(p1W, p2W, p3W, t, dest, sd, dd, _t0);
    }

    private Double4 catmullRomTangent_mulAdd_narrow(double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t, Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _t0 = t * t;
        dd[0] = 0.5 * ((t) * (2.0 * ((-5.0) * (p1X) + (((2.0) * (sd[0]) + (((4.0) * (p2X) - (p3X))))))) + (((3.0 * ((-3.0) * (p2X) + (((3.0) * (p1X) + (p3X - sd[0]))))) * (_t0) + (p2X - sd[0]))));
        dd[1] = 0.5 * ((t) * (2.0 * ((-5.0) * (p1Y) + (((2.0) * (sd[1]) + (((4.0) * (p2Y) - (p3Y))))))) + (((3.0 * ((-3.0) * (p2Y) + (((3.0) * (p1Y) + (p3Y - sd[1]))))) * (_t0) + (p2Y - sd[1]))));
        dd[2] = 0.5 * ((t) * (2.0 * ((-5.0) * (p1Z) + (((2.0) * (sd[2]) + (((4.0) * (p2Z) - (p3Z))))))) + (((3.0 * ((-3.0) * (p2Z) + (((3.0) * (p1Z) + (p3Z - sd[2]))))) * (_t0) + (p2Z - sd[2]))));
        return catmullRomTangent_s66d1d242_1_mulAdd_narrow(p1W, p2W, p3W, t, dest, sd, dd, _t0);
    }

    private Double4 catmullRom_scde09e05_1_fma_narrow(double p1W, double p2W, double p3W, double t, Double4 dest, double[] sd, double[] dd, double _t0, double _t1) {
        dd[3] = 0.5 * (java.lang.Math.fma(2.0, p1W, t * (p2W - sd[3])) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, sd[3], java.lang.Math.fma(4.0, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - sd[3])) * _t1));
        return dest;
    }

    private Double4 catmullRom_scde09e05_1_mulAdd_narrow(double p1W, double p2W, double p3W, double t, Double4 dest, double[] sd, double[] dd, double _t0, double _t1) {
        dd[3] = 0.5 * (((2.0) * (p1W) + (t * (p2W - sd[3]))) + ((((-5.0) * (p1W) + (((2.0) * (sd[3]) + (((4.0) * (p2W) - (p3W))))))) * (_t0) + (((-3.0) * (p2W) + (((3.0) * (p1W) + (p3W - sd[3])))) * _t1)));
        return dest;
    }

    private Double4 catmullRomTangent_s66d1d242_1_fma_narrow(double p1W, double p2W, double p3W, double t, Double4 dest, double[] sd, double[] dd, double _t0) {
        dd[3] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, sd[3], java.lang.Math.fma(4.0, p2W, -p3W))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - sd[3])), _t0, p2W - sd[3]));
        return dest;
    }

    private Double4 catmullRomTangent_s66d1d242_1_mulAdd_narrow(double p1W, double p2W, double p3W, double t, Double4 dest, double[] sd, double[] dd, double _t0) {
        dd[3] = 0.5 * ((t) * (2.0 * ((-5.0) * (p1W) + (((2.0) * (sd[3]) + (((4.0) * (p2W) - (p3W))))))) + (((3.0 * ((-3.0) * (p2W) + (((3.0) * (p1W) + (p3W - sd[3]))))) * (_t0) + (p2W - sd[3]))));
        return dest;
    }
}
