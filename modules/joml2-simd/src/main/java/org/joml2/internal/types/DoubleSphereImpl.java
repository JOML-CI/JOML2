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
 * Generated implementation of {@link DoubleSphere} backed by a {@code double[]} array, with Vector
 * API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleSphereImpl implements DoubleSphere {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleSphereSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleSphereSegOpsUnsafe()
                        : new DoubleSphereSegOpsMS();
        static final DoubleSphereBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleSphereBbOpsUnsafe()
                        : new DoubleSphereBbOpsApi();
        static final DoubleSphereRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleSphereRawOpsUnsafe()
                        : new DoubleSphereRawOpsApi();
    }

    public DoubleSphereImpl() {
        data = new double[4];
    }

    public DoubleSphereImpl(double x, double y, double z, double r) {
        double[] dd = this.data = new double[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = r;
    }

    public DoubleSphereImpl(DoubleSphereR src) {
        double[] dd = this.data = new double[4];
        dd[0] = src.x();
        dd[1] = src.y();
        dd[2] = src.z();
        dd[3] = src.r();
    }


    /**
     * Set this sphere to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the sphere to copy
     * @return this
     */
    @Mutated public DoubleSphere set(DoubleSphereR v) {
        if (COL_NARROW) return set_narrow(v);
        double[] dd = this.data;
        double[] vData = ((DoubleSphereImpl) v).data;
        DoubleVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
        return this;
    }


    /**
     * Set this sphere to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vY the {@code y} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vZ the {@code z} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vR the {@code r} component of the sphere {@code (vX, vY, vZ, vR)}
     * @return this
     */
    @Mutated public DoubleSphere set(double vX, double vY, double vZ, double vR) {
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vR;
        return this;
    }


    /**
     * Set the center of this sphere to {@code c} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setCenter(Double3R c, @Mutated DoubleSphere dest) {
        double[] sd = this.data;
        double[] cData = ((Double3Impl) c).data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = cData[0];
        dd[1] = cData[1];
        dd[2] = cData[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set the center of this sphere to ({@code cX}, {@code cY}, {@code cZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setCenter(double cX, double cY, double cZ, @Mutated DoubleSphere dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set the radius of this sphere to {@code radius} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param radius the radius
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setRadius(double radius, @Mutated DoubleSphere dest) {
        if (COL_NARROW) return setRadius_narrow(radius, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).withLane(3, radius).intoArray(dd, 0);
        return dest;
    }


    /**
     * Convert this sphere to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere toFloat(@Mutated FloatSphere dest) {
        double[] sd = this.data;
        float[] dd = ((FloatSphereImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        return dest;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere transform(Double3x4R m, @Mutated DoubleSphere dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        return transform_sc33799c_1(dest, sd, mData, ((DoubleSphereImpl) dest).data, java.lang.Math.abs(Math.fma(mData[8], mData[9], Math.fma(mData[0], mData[1], mData[4] * mData[5]))), java.lang.Math.abs(Math.fma(mData[8], mData[10], Math.fma(mData[0], mData[2], mData[4] * mData[6]))), java.lang.Math.abs(Math.fma(mData[9], mData[10], Math.fma(mData[1], mData[2], mData[5] * mData[6]))), Math.fma(mData[2], sd[2], Math.fma(mData[0], sd[0], Math.fma(mData[1], sd[1], mData[3]))), Math.fma(mData[6], sd[2], Math.fma(mData[4], sd[0], Math.fma(mData[5], sd[1], mData[7]))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleSphere transform_sc33799c_1(DoubleSphere dest, double[] sd, double[] mData, double[] dd, double _t9, double _t10, double _t11, double _buf0, double _buf1) {
        dd[2] = Math.fma(mData[10], sd[2], Math.fma(mData[8], sd[0], Math.fma(mData[9], sd[1], mData[11])));
        dd[3] = sd[3] * java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(Math.fma(mData[0], mData[0], Math.fma(mData[4], mData[4], Math.fma(mData[8], mData[8], _t9 + _t10))), Math.fma(mData[1], mData[1], Math.fma(mData[5], mData[5], Math.fma(mData[9], mData[9], _t9 + _t11)))), Math.fma(mData[2], mData[2], Math.fma(mData[6], mData[6], Math.fma(mData[10], mData[10], _t10 + _t11)))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere transform(Double4x4R m, @Mutated DoubleSphere dest) {
        if (COL_NARROW) return transform_narrow(m, dest);
        if (SimdMath.USE_FMA) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    private DoubleSphere transform_fma(Double4x4R m, @Mutated DoubleSphere dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        double _r0 = mData[2];
        double _r1 = mData[6];
        double _r2 = mData[0];
        double _r3 = mData[4];
        double _r4 = mData[1];
        double _r5 = mData[5];
        double _r6 = mData[10];
        double _r7 = mData[8];
        double _r8 = mData[9];
        double _r9 = sd[2];
        double _r10 = sd[0];
        double _r11 = sd[1];
        double _r12 = sd[3];
        transform_s10286e8d_tail(dd, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, java.lang.Math.abs(Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5))), java.lang.Math.abs(Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r4 * _r8))), java.lang.Math.abs(Math.fma(_r1, _r6, Math.fma(_r3, _r7, _r5 * _r8))), mData);
        return dest;
    }

    private DoubleSphere transform_mulAdd(Double4x4R m, @Mutated DoubleSphere dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        double _r0 = mData[2];
        double _r1 = mData[6];
        double _r2 = mData[0];
        double _r3 = mData[4];
        double _r4 = mData[1];
        double _r5 = mData[5];
        double _r6 = mData[10];
        double _r7 = mData[8];
        double _r8 = mData[9];
        double _r9 = sd[2];
        double _r10 = sd[0];
        double _r11 = sd[1];
        double _r12 = sd[3];
        transform_s5d81934a_tail(dd, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, java.lang.Math.abs(Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5))), java.lang.Math.abs(Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r4 * _r8))), java.lang.Math.abs(Math.fma(_r1, _r6, Math.fma(_r3, _r7, _r5 * _r8))), mData);
        return dest;
    }

    /** Private vector tail of {@code transform_s10286e8d}: loads, computes and stores every column; reached only through it. */
    private static void transform_s10286e8d_tail(double[] dd, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _t9, double _t10, double _t11, double[] mData) {
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, mData, 8);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, mData, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, mData, 4);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, mData, 12);
        _sv0.fma(DoubleVector.broadcast(COL_SPECIES, _r9), _sv1.fma(DoubleVector.broadcast(COL_SPECIES, _r10), _sv2.fma(DoubleVector.broadcast(COL_SPECIES, _r11), _sv3))).withLane(3, _r12 * java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(Math.fma(_r2, _r2, Math.fma(_r4, _r4, Math.fma(_r0, _r0, _t9 + _t10))), Math.fma(_r3, _r3, Math.fma(_r5, _r5, Math.fma(_r1, _r1, _t9 + _t11)))), Math.fma(_r7, _r7, Math.fma(_r8, _r8, Math.fma(_r6, _r6, _t10 + _t11)))))).intoArray(dd, 0);
    }

    /** Private vector tail of {@code transform_s5d81934a}: loads, computes and stores every column; reached only through it. */
    private static void transform_s5d81934a_tail(double[] dd, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _t9, double _t10, double _t11, double[] mData) {
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, mData, 8);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, mData, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, mData, 4);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, mData, 12);
        _sv0.mul(DoubleVector.broadcast(COL_SPECIES, _r9)).add(_sv1.mul(DoubleVector.broadcast(COL_SPECIES, _r10)).add(_sv2.mul(DoubleVector.broadcast(COL_SPECIES, _r11)).add(_sv3))).withLane(3, _r12 * java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(Math.fma(_r2, _r2, Math.fma(_r4, _r4, Math.fma(_r0, _r0, _t9 + _t10))), Math.fma(_r3, _r3, Math.fma(_r5, _r5, Math.fma(_r1, _r1, _t9 + _t11)))), Math.fma(_r7, _r7, Math.fma(_r8, _r8, Math.fma(_r6, _r6, _t10 + _t11)))))).intoArray(dd, 0);
    }


    /**
     * Translate this sphere by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere translate(Double3R delta, @Mutated DoubleSphere dest) {
        if (COL_NARROW) return translate_narrow(delta, dest);
        double[] sd = this.data;
        double[] deltaData = ((Double3Impl) delta).data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, deltaData[0]).withLane(1, deltaData[1]).withLane(2, deltaData[2]).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).withLane(3, sd[3]).intoArray(dd, 0);
        return dest;
    }


    /**
     * Translate this sphere by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere translate(double deltaX, double deltaY, double deltaZ, @Mutated DoubleSphere dest) {
        if (COL_NARROW) return translate_narrow(deltaX, deltaY, deltaZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        DoubleVector.zero(COL_SPECIES).withLane(0, deltaX).withLane(1, deltaY).withLane(2, deltaZ).add(DoubleVector.fromArray(COL_SPECIES, sd, 0)).withLane(3, sd[3]).intoArray(dd, 0);
        return dest;
    }


    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Double3R p, @Mutated Double3 dest) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t8 = sd[3] * (1.0 / java.lang.Math.sqrt(_t6));
        if (_t6 <= sd[3] * sd[3]) {
            dd[0] = pX;
            dd[1] = pY;
            dd[2] = pZ;
        } else {
            dd[0] = Math.fma(_t1, _t8, sd[0]);
            dd[1] = Math.fma(_t2, _t8, sd[1]);
            dd[2] = Math.fma(_t0, _t8, sd[2]);
        }
        return dest;
    }


    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t8 = sd[3] * (1.0 / java.lang.Math.sqrt(_t6));
        if (_t6 <= sd[3] * sd[3]) {
            dd[0] = pX;
            dd[1] = pY;
            dd[2] = pZ;
        } else {
            dd[0] = Math.fma(_t1, _t8, sd[0]);
            dd[1] = Math.fma(_t2, _t8, sd[1]);
            dd[2] = Math.fma(_t0, _t8, sd[2]);
        }
        return dest;
    }


    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any
     * component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(DoubleAABBR aabb) {
        double[] sd = this.data;
        double _t6 = sd[2] - java.lang.Math.max(aabb.minZ(), java.lang.Math.min(sd[2], aabb.maxZ()));
        double _t7 = sd[0] - java.lang.Math.max(aabb.minX(), java.lang.Math.min(sd[0], aabb.maxX()));
        double _t8 = sd[1] - java.lang.Math.max(aabb.minY(), java.lang.Math.min(sd[1], aabb.maxY()));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
        return _t14 * _t14;
    }


    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double[] sd = this.data;
        double _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
        double _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
        double _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
        return _t14 * _t14;
    }


    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
     * {@code min} must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(Double3R min, Double3R max) {
        double[] sd = this.data;
        double _t6 = sd[2] - java.lang.Math.max(min.z(), java.lang.Math.min(sd[2], max.z()));
        double _t7 = sd[0] - java.lang.Math.max(min.x(), java.lang.Math.min(sd[0], max.x()));
        double _t8 = sd[1] - java.lang.Math.max(min.y(), java.lang.Math.min(sd[1], max.y()));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
        return _t14 * _t14;
    }


    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this sphere and the given point, i.e. the square of the
     *        distance from the point to the center minus the radius, clamped at zero; zero for a
     *        point inside or on the sphere
     */
    public double distanceSquaredToPoint(Double3R p) {
        double[] sd = this.data;
        double _t0 = p.z() - sd[2];
        double _t1 = p.x() - sd[0];
        double _t2 = p.y() - sd[1];
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
        return _t8 * _t8;
    }


    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this sphere and the given point, i.e. the square of the
     *        distance from the point to the center minus the radius, clamped at zero; zero for a
     *        point inside or on the sphere
     */
    public double distanceSquaredToPoint(double pX, double pY, double pZ) {
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
        return _t8 * _t8;
    }


    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the sphere to measure the distance to
     * @return the squared distance between this sphere and the given sphere, i.e. the square of the
     *        distance between the centers minus both radii, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceSquaredToSphere(DoubleSphereR other) {
        double[] sd = this.data;
        double _t0 = other.z() - sd[2];
        double _t1 = other.x() - sd[0];
        double _t2 = other.y() - sd[1];
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - other.r());
        return _t9 * _t9;
    }


    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param otherX the {@code x} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherY the {@code y} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherZ the {@code z} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherR the {@code r} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @return the squared distance between this sphere and the given sphere, i.e. the square of the
     *        distance between the centers minus both radii, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceSquaredToSphere(double otherX, double otherY, double otherZ, double otherR) {
        double[] sd = this.data;
        double _t0 = otherZ - sd[2];
        double _t1 = otherX - sd[0];
        double _t2 = otherY - sd[1];
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - otherR);
        return _t9 * _t9;
    }


    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any
     * component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceToAABB(DoubleAABBR aabb) {
        double[] sd = this.data;
        double _t6 = sd[2] - java.lang.Math.max(aabb.minZ(), java.lang.Math.min(sd[2], aabb.maxZ()));
        double _t7 = sd[0] - java.lang.Math.max(aabb.minX(), java.lang.Math.min(sd[0], aabb.maxX()));
        double _t8 = sd[1] - java.lang.Math.max(aabb.minY(), java.lang.Math.min(sd[1], aabb.maxY()));
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
    }


    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double[] sd = this.data;
        double _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
        double _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
        double _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
    }


    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
     * {@code min} must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceToAABB(Double3R min, Double3R max) {
        double[] sd = this.data;
        double _t6 = sd[2] - java.lang.Math.max(min.z(), java.lang.Math.min(sd[2], max.z()));
        double _t7 = sd[0] - java.lang.Math.max(min.x(), java.lang.Math.min(sd[0], max.x()));
        double _t8 = sd[1] - java.lang.Math.max(min.y(), java.lang.Math.min(sd[1], max.y()));
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
    }


    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public double distanceToPlane(DoublePlaneR plane) {
        double planeA = plane.a();
        double planeB = plane.b();
        double planeC = plane.c();
        double[] sd = this.data;
        return java.lang.Math.max(0.0, Math.fma((1.0 / java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(Math.fma(planeA, sd[0], Math.fma(planeB, sd[1], Math.fma(planeC, sd[2], plane.d())))), -sd[3]));
    }


    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code (planeA, planeB, planeC, planeD)} must be non-zero.
     *
     * @param planeA the {@code a} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeB the {@code b} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeC the {@code c} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeD the {@code d} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public double distanceToPlane(double planeA, double planeB, double planeC, double planeD) {
        double[] sd = this.data;
        return java.lang.Math.max(0.0, Math.fma((1.0 / java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(Math.fma(planeA, sd[0], Math.fma(planeB, sd[1], Math.fma(planeC, sd[2], planeD)))), -sd[3]));
    }


    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public double distanceToPlane(Double4R plane) {
        double planeA = plane.x();
        double planeB = plane.y();
        double planeC = plane.z();
        double[] sd = this.data;
        return java.lang.Math.max(0.0, Math.fma((1.0 / java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(Math.fma(planeA, sd[0], Math.fma(planeB, sd[1], Math.fma(planeC, sd[2], plane.w())))), -sd[3]));
    }


    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to measure the distance to
     * @return the distance between this sphere and the given point, i.e. the distance from the
     *        point to the center minus the radius, clamped at zero; zero for a point inside or on
     *        the sphere
     */
    public double distanceToPoint(Double3R p) {
        double[] sd = this.data;
        double _t0 = p.z() - sd[2];
        double _t1 = p.x() - sd[0];
        double _t2 = p.y() - sd[1];
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
    }


    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this sphere and the given point, i.e. the distance from the
     *        point to the center minus the radius, clamped at zero; zero for a point inside or on
     *        the sphere
     */
    public double distanceToPoint(double pX, double pY, double pZ) {
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
    }


    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the sphere to measure the distance to
     * @return the distance between this sphere and the given sphere, i.e. the distance between the
     *        centers minus both radii, clamped at zero; zero when they overlap or touch
     */
    public double distanceToSphere(DoubleSphereR other) {
        double[] sd = this.data;
        double _t0 = other.z() - sd[2];
        double _t1 = other.x() - sd[0];
        double _t2 = other.y() - sd[1];
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - other.r());
    }


    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param otherX the {@code x} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherY the {@code y} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherZ the {@code z} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherR the {@code r} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @return the distance between this sphere and the given sphere, i.e. the distance between the
     *        centers minus both radii, clamped at zero; zero when they overlap or touch
     */
    public double distanceToSphere(double otherX, double otherY, double otherZ, double otherR) {
        double[] sd = this.data;
        double _t0 = otherZ - sd[2];
        double _t1 = otherX - sd[0];
        double _t2 = otherY - sd[1];
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - otherR);
    }


    /**
     * Get the center of this sphere and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getCenter(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Determine whether this sphere is valid, i.e. its radius is not negative.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this sphere is valid, i.e. its radius is not negative, {@code false}
     *        otherwise
     */
    public boolean isValid() {
        return this.data[3] >= 0.0;
    }


    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to measure the distance to
     * @return the signed distance between the given point and the surface of this sphere, i.e. the
     *        distance from the point to the center minus the radius: positive outside, zero on the
     *        surface and negative inside
     */
    public double signedDistanceToPoint(Double3R p) {
        double[] sd = this.data;
        double _t0 = p.z() - sd[2];
        double _t1 = p.x() - sd[0];
        double _t2 = p.y() - sd[1];
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3];
    }


    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the signed distance between the given point and the surface of this sphere, i.e. the
     *        distance from the point to the center minus the radius: positive outside, zero on the
     *        surface and negative inside
     */
    public double signedDistanceToPoint(double pX, double pY, double pZ) {
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - sd[3];
    }

    public double x() { return data[0]; }
    public double y() { return data[1]; }
    public double z() { return data[2]; }
    public double r() { return data[3]; }

    @Override public String toString() {
        return "DoubleSphere(" + x() + ", " + y() + ", " + z() + ", " + r() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleSphereImpl)) return false;
        DoubleSphereImpl o = (DoubleSphereImpl) obj;
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

    @Override public boolean equalsEpsilon(DoubleSphereR other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.x()) <= epsilon
            && java.lang.Math.abs(data[1] - other.y()) <= epsilon
            && java.lang.Math.abs(data[2] - other.z()) <= epsilon
            && java.lang.Math.abs(data[3] - other.r()) <= epsilon;
    }

    public boolean containsPoint(double px, double py, double pz) {
        return Intersectiond.testPointSphere(px, py, pz, x(), y(), z(), r() * r());
    }

    public boolean containsPoint(Double3R p) {
        return Intersectiond.testPointSphere(p, this);
    }

    public boolean intersectsSphere(DoubleSphereR o) {
        return Intersectiond.testSphereSphere(x(), y(), z(), r() * r(), o.x(), o.y(), o.z(), o.r() * o.r());
    }

    public boolean intersectsAABB(DoubleAABBR aabb) {
        return Intersectiond.testAabbSphere(aabb, this);
    }

    public boolean intersectsPlane(DoublePlaneR plane) {
        return Intersectiond.testPlaneSphere(plane.a(), plane.b(), plane.c(), plane.d(), x(), y(), z(), r());
    }

    public boolean intersectsRay(DoubleRayR ray) {
        return Intersectiond.testRaySphere(ray, this);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        if (COL_NARROW) return store_narrow(dest, offset);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }
    public @Mutated DoubleSphere load(double[] src, int offset) {
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
    @Mutated public DoubleSphere loadAbsolute(int index, DoubleBuffer buf) {
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
    public DoubleSphere load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoubleSphere loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleSphere loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleSphere r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public DoubleSphere storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleSphere loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        if (COL_NARROW) return store_narrow(offset, dest);
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(dest, offset, ByteOrder.nativeOrder());
        return dest;
    }
    public DoubleSphere load(long offset, MemorySegment src) {
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
    public @Mutated DoubleSphere load(float[] src, int offset) {
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
    @Mutated public DoubleSphere load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleSphere loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleSphere loadRelative(FloatBuffer buf) {
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
    public DoubleSphere loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoubleSphere loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleSphere loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleSphere r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public DoubleSphere storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleSphere loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    @Mutated public DoubleSphere loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }
    public DoubleSphere loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;

    /** The column species is wider than this machine's vector unit: take the scalar twins. */
    private static final boolean COL_NARROW = COL_SPECIES.length() > DoubleVector.SPECIES_PREFERRED.length();

    private DoubleSphere set_narrow(DoubleSphereR v) {
        double vY = v.y();
        double vZ = v.z();
        double vR = v.r();
        double[] dd = this.data;
        dd[0] = v.x();
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vR;
        return this;
    }

    private DoubleSphere setRadius_narrow(double radius, DoubleSphere dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = radius;
        return dest;
    }

    private DoubleSphere translate_narrow(Double3R delta, DoubleSphere dest) {
        double deltaY = delta.y();
        double deltaZ = delta.z();
        double[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = delta.x() + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        return dest;
    }

    private DoubleSphere translate_narrow(double deltaX, double deltaY, double deltaZ, DoubleSphere dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        return dest;
    }

    private double[] store_narrow(double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    private DoubleSphere load_narrow(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }

    private MemorySegment store_narrow(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    private DoubleSphere load_narrow(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    private DoubleSphere transform_narrow(Double4x4R m, DoubleSphere dest) {
        if (Math.useFma()) return transform_fma_narrow(m, dest);
        return transform_mulAdd_narrow(m, dest);
    }

    private DoubleSphere transform_fma_narrow(Double4x4R m, DoubleSphere dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        return transform_s23f602bf_1_fma_narrow(dest, sd, mData, ((DoubleSphereImpl) dest).data, java.lang.Math.abs(java.lang.Math.fma(mData[2], mData[6], java.lang.Math.fma(mData[0], mData[4], mData[1] * mData[5]))), java.lang.Math.abs(java.lang.Math.fma(mData[2], mData[10], java.lang.Math.fma(mData[0], mData[8], mData[1] * mData[9]))), java.lang.Math.abs(java.lang.Math.fma(mData[6], mData[10], java.lang.Math.fma(mData[4], mData[8], mData[5] * mData[9]))), mData[0], mData[1], mData[2], mData[4], mData[5], mData[6], mData[8], mData[9], mData[10], sd[0], sd[1], sd[2]);
    }

    private DoubleSphere transform_mulAdd_narrow(Double4x4R m, DoubleSphere dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        return transform_s23f602bf_1_mulAdd_narrow(dest, sd, mData, ((DoubleSphereImpl) dest).data, java.lang.Math.abs(((mData[2]) * (mData[6]) + (((mData[0]) * (mData[4]) + (mData[1] * mData[5]))))), java.lang.Math.abs(((mData[2]) * (mData[10]) + (((mData[0]) * (mData[8]) + (mData[1] * mData[9]))))), java.lang.Math.abs(((mData[6]) * (mData[10]) + (((mData[4]) * (mData[8]) + (mData[5] * mData[9]))))), mData[0], mData[1], mData[2], mData[4], mData[5], mData[6], mData[8], mData[9], mData[10], sd[0], sd[1], sd[2]);
    }

    private DoubleSphere transform_s23f602bf_1_fma_narrow(DoubleSphere dest, double[] sd, double[] mData, double[] dd, double _t9, double _t10, double _t11, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7, double _rd8, double _rd9, double _rd10, double _rd11) {
        dd[0] = java.lang.Math.fma(_rd6, _rd11, java.lang.Math.fma(_rd0, _rd9, java.lang.Math.fma(_rd3, _rd10, mData[12])));
        dd[1] = java.lang.Math.fma(_rd7, _rd11, java.lang.Math.fma(_rd1, _rd9, java.lang.Math.fma(_rd4, _rd10, mData[13])));
        dd[2] = java.lang.Math.fma(_rd8, _rd11, java.lang.Math.fma(_rd2, _rd9, java.lang.Math.fma(_rd5, _rd10, mData[14])));
        dd[3] = sd[3] * java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(java.lang.Math.fma(_rd0, _rd0, java.lang.Math.fma(_rd1, _rd1, java.lang.Math.fma(_rd2, _rd2, _t9 + _t10))), java.lang.Math.fma(_rd3, _rd3, java.lang.Math.fma(_rd4, _rd4, java.lang.Math.fma(_rd5, _rd5, _t9 + _t11)))), java.lang.Math.fma(_rd6, _rd6, java.lang.Math.fma(_rd7, _rd7, java.lang.Math.fma(_rd8, _rd8, _t10 + _t11)))));
        return dest;
    }

    private DoubleSphere transform_s23f602bf_1_mulAdd_narrow(DoubleSphere dest, double[] sd, double[] mData, double[] dd, double _t9, double _t10, double _t11, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7, double _rd8, double _rd9, double _rd10, double _rd11) {
        dd[0] = ((_rd6) * (_rd11) + (((_rd0) * (_rd9) + (((_rd3) * (_rd10) + (mData[12]))))));
        dd[1] = ((_rd7) * (_rd11) + (((_rd1) * (_rd9) + (((_rd4) * (_rd10) + (mData[13]))))));
        dd[2] = ((_rd8) * (_rd11) + (((_rd2) * (_rd9) + (((_rd5) * (_rd10) + (mData[14]))))));
        dd[3] = sd[3] * java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(((_rd0) * (_rd0) + (((_rd1) * (_rd1) + (((_rd2) * (_rd2) + (_t9 + _t10)))))), ((_rd3) * (_rd3) + (((_rd4) * (_rd4) + (((_rd5) * (_rd5) + (_t9 + _t11))))))), ((_rd6) * (_rd6) + (((_rd7) * (_rd7) + (((_rd8) * (_rd8) + (_t10 + _t11))))))));
        return dest;
    }
}
