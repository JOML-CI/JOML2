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
 * Generated implementation of {@link DoubleOBB} backed by a {@code double[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleOBBImpl implements DoubleOBB {
    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleOBBSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleOBBSegOpsUnsafe()
            : new DoubleOBBSegOpsMS();
        static final DoubleOBBBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleOBBBbOpsUnsafe()
            : new DoubleOBBBbOpsApi();
        static final DoubleOBBRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleOBBRawOpsUnsafe()
            : new DoubleOBBRawOpsApi();
    }

    public DoubleOBBImpl() {
        data = new double[15];
        data[3] = 1;
        data[7] = 1;
        data[11] = 1;
    }

    public DoubleOBBImpl(double cX, double cY, double cZ, double uXx, double uXy, double uXz, double uYx, double uYy, double uYz, double uZx, double uZy, double uZz, double hsX, double hsY, double hsZ) {
        double[] dd = this.data = new double[15];
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = uXx;
        dd[4] = uXy;
        dd[5] = uXz;
        dd[6] = uYx;
        dd[7] = uYy;
        dd[8] = uYz;
        dd[9] = uZx;
        dd[10] = uZy;
        dd[11] = uZz;
        dd[12] = hsX;
        dd[13] = hsY;
        dd[14] = hsZ;
    }

    public DoubleOBBImpl(DoubleOBBR src) {
        double[] srcData = ((DoubleOBBImpl) src).data;
        double[] dd = this.data = new double[15];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
        dd[3] = srcData[3];
        dd[4] = srcData[4];
        dd[5] = srcData[5];
        dd[6] = srcData[6];
        dd[7] = srcData[7];
        dd[8] = srcData[8];
        dd[9] = srcData[9];
        dd[10] = srcData[10];
        dd[11] = srcData[11];
        dd[12] = srcData[12];
        dd[13] = srcData[13];
        dd[14] = srcData[14];
    }

    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return this
     */
    @Mutated public DoubleOBB set(DoubleOBBR v) {
        double[] vData = ((DoubleOBBImpl) v).data;
        double vCY = vData[1];
        double vCZ = vData[2];
        double vUXx = vData[3];
        double vUXy = vData[4];
        double vUXz = vData[5];
        double vUYx = vData[6];
        double vUYy = vData[7];
        double vUYz = vData[8];
        double vUZx = vData[9];
        double vUZy = vData[10];
        double vUZz = vData[11];
        double vHsX = vData[12];
        double vHsY = vData[13];
        double vHsZ = vData[14];
        double[] dd = this.data;
        dd[0] = vData[0];
        dd[1] = vCY;
        dd[2] = vCZ;
        dd[3] = vUXx;
        dd[4] = vUXy;
        dd[5] = vUXz;
        dd[6] = vUYx;
        dd[7] = vUYy;
        dd[8] = vUYz;
        dd[9] = vUZx;
        dd[10] = vUZy;
        dd[11] = vUZz;
        dd[12] = vHsX;
        dd[13] = vHsY;
        dd[14] = vHsZ;
        return this;
    }

    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vCX the {@code cX} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vCY the {@code cY} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vCZ the {@code cZ} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUXx the {@code uXx} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUXy the {@code uXy} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUXz the {@code uXz} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUYx the {@code uYx} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUYy the {@code uYy} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUYz the {@code uYz} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUZx the {@code uZx} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUZy the {@code uZy} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vUZz the {@code uZz} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vHsX the {@code hsX} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vHsY the {@code hsY} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @param vHsZ the {@code hsZ} component of the oriented bounding box
     *        {@code (vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ)}
     * @return this
     */
    @Mutated public DoubleOBB set(double vCX, double vCY, double vCZ, double vUXx, double vUXy, double vUXz, double vUYx, double vUYy, double vUYz, double vUZx, double vUZy, double vUZz, double vHsX, double vHsY, double vHsZ) {
        double[] dd = this.data;
        dd[0] = vCX;
        dd[1] = vCY;
        dd[2] = vCZ;
        dd[3] = vUXx;
        dd[4] = vUXy;
        dd[5] = vUXz;
        dd[6] = vUYx;
        dd[7] = vUYy;
        dd[8] = vUYz;
        dd[9] = vUZx;
        dd[10] = vUZy;
        dd[11] = vUZz;
        dd[12] = vHsX;
        dd[13] = vHsY;
        dd[14] = vHsZ;
        return this;
    }

    /**
     * Set the local coordinate axes of this oriented bounding box to {@code axisX}, {@code axisY}
     * and {@code axisZ}, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code axisX}, {@code axisY} and {@code axisZ} must be orthonormal.
     *
     * @param axisX the new local X axis
     * @param axisY the new local Y axis
     * @param axisZ the new local Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setAxes(Double3R axisX, Double3R axisY, Double3R axisZ, @Mutated DoubleOBB dest) {
        double[] axisXData = ((Double3Impl) axisX).data;
        double[] axisYData = ((Double3Impl) axisY).data;
        double[] axisZData = ((Double3Impl) axisZ).data;
        double axisXX = axisXData[0];
        double axisXY = axisXData[1];
        double axisXZ = axisXData[2];
        double axisYX = axisYData[0];
        double axisYY = axisYData[1];
        double axisYZ = axisYData[2];
        double axisZX = axisZData[0];
        double axisZY = axisZData[1];
        double axisZZ = axisZData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = axisXX;
        dd[4] = axisXY;
        dd[5] = axisXZ;
        dd[6] = axisYX;
        dd[7] = axisYY;
        dd[8] = axisYZ;
        dd[9] = axisZX;
        dd[10] = axisZY;
        dd[11] = axisZZ;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    /**
     * Set the local coordinate axes of this oriented bounding box to ({@code axisXX},
     * {@code axisXY}, {@code axisXZ}), ({@code axisYX}, {@code axisYY}, {@code axisYZ}) and
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}), and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (axisXX, axisXY, axisXZ)}, {@code (axisYX, axisYY, axisYZ)} and
     * {@code (axisZX, axisZY, axisZZ)} must be orthonormal.
     *
     * @param axisXX the {@code x} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXY the {@code y} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXZ the {@code z} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisYX the {@code x} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYY the {@code y} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYZ the {@code z} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisZX the {@code x} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZY the {@code y} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZZ the {@code z} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setAxes(double axisXX, double axisXY, double axisXZ, double axisYX, double axisYY, double axisYZ, double axisZX, double axisZY, double axisZZ, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = axisXX;
        dd[4] = axisXY;
        dd[5] = axisXZ;
        dd[6] = axisYX;
        dd[7] = axisYY;
        dd[8] = axisYZ;
        dd[9] = axisZX;
        dd[10] = axisZY;
        dd[11] = axisZZ;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    /**
     * Set the center of this oriented bounding box to {@code c} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setCenter(Double3R c, @Mutated DoubleOBB dest) {
        double[] cData = ((Double3Impl) c).data;
        if (COL_NARROW) return setCenter_narrow(c, dest);
        double cY = cData[1];
        double cZ = cData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = cData[0];
        dd[1] = cY;
        dd[2] = cZ;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        DoubleVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        DoubleVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }

    /**
     * Set the center of this oriented bounding box to ({@code cX}, {@code cY}, {@code cZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setCenter(double cX, double cY, double cZ, @Mutated DoubleOBB dest) {
        if (COL_NARROW) return setCenter_narrow(cX, cY, cZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        DoubleVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        DoubleVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }

    /**
     * Set the half extents of this oriented bounding box to {@code h} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of {@code h} must not be negative.
     *
     * @param h the new half extents
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setHalfSize(Double3R h, @Mutated DoubleOBB dest) {
        double[] hData = ((Double3Impl) h).data;
        if (COL_NARROW) return setHalfSize_narrow(h, dest);
        double hX = hData[0];
        double hY = hData[1];
        double hZ = hData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        DoubleVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        DoubleVector.fromArray(COL_SPECIES, sd, 8).intoArray(dd, 8);
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }

    /**
     * Set the half extents of this oriented bounding box to ({@code hX}, {@code hY}, {@code hZ})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (hX, hY, hZ)} must not be negative.
     *
     * @param hX the {@code x} component of the vector {@code (hX, hY, hZ)}
     * @param hY the {@code y} component of the vector {@code (hX, hY, hZ)}
     * @param hZ the {@code z} component of the vector {@code (hX, hY, hZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setHalfSize(double hX, double hY, double hZ, @Mutated DoubleOBB dest) {
        if (COL_NARROW) return setHalfSize_narrow(hX, hY, hZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        DoubleVector.fromArray(COL_SPECIES, sd, 4).intoArray(dd, 4);
        DoubleVector.fromArray(COL_SPECIES, sd, 8).intoArray(dd, 8);
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }

    /**
     * Reset the orientation of this oriented bounding box to identity and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setIdentityOrientation(@Mutated DoubleOBB dest) {
        if (COL_NARROW) return setIdentityOrientation_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        VEC_0.intoArray(dd, 3);
        VEC_0.intoArray(dd, 7);
        dd[11] = 1.0;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    /**
     * Set the orientation of this oriented bounding box to {@code q} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the new orientation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setOrientation(DoubleQuatR q, @Mutated DoubleOBB dest) {
        double[] qData = ((DoubleQuatImpl) q).data;
        return setOrientation(qData[0], qData[1], qData[2], qData[3], dest);
    }

    /**
     * Set the orientation of this oriented bounding box to ({@code qX}, {@code qY}, {@code qZ},
     * {@code qW}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB setOrientation(double qX, double qY, double qZ, double qW, @Mutated DoubleOBB dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return setOrientation_mulAdd(qX, qY, qZ, qW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(-2.0, java.lang.Math.fma(qY, qY, _t0), 1.0);
        dd[4] = 2.0 * java.lang.Math.fma(qX, qY, _t1);
        dd[5] = 2.0 * java.lang.Math.fma(qX, qZ, -_t2);
        dd[6] = 2.0 * java.lang.Math.fma(qX, qY, -_t1);
        dd[7] = java.lang.Math.fma(-2.0, java.lang.Math.fma(qX, qX, _t0), 1.0);
        dd[8] = 2.0 * java.lang.Math.fma(qX, qW, qY * qZ);
        dd[9] = 2.0 * java.lang.Math.fma(qX, qZ, _t2);
        dd[10] = 2.0 * java.lang.Math.fma(qY, qZ, -(qX * qW));
        dd[11] = java.lang.Math.fma(-2.0, java.lang.Math.fma(qX, qX, qY * qY), 1.0);
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    /** {@code setOrientation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB setOrientation_mulAdd(double qX, double qY, double qZ, double qW, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((1.0) - (2.0) * (((qY) * (qY) + (_t0))));
        dd[4] = 2.0 * ((qX) * (qY) + (_t1));
        dd[5] = 2.0 * ((qX) * (qZ) - (_t2));
        dd[6] = 2.0 * ((qX) * (qY) - (_t1));
        dd[7] = ((1.0) - (2.0) * (((qX) * (qX) + (_t0))));
        dd[8] = 2.0 * ((qX) * (qW) + (qY * qZ));
        dd[9] = 2.0 * ((qX) * (qZ) + (_t2));
        dd[10] = 2.0 * ((qY) * (qZ) - (qX * qW));
        dd[11] = ((1.0) - (2.0) * (((qX) * (qX) + (qY * qY))));
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    /**
     * Convert this oriented bounding box to {@code float} precision and store the result in
     * {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatOBB toFloat(@Mutated FloatOBB dest) {
        double[] sd = this.data;
        float[] dd = ((FloatOBBImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        dd[7] = (float) (sd[7]);
        dd[8] = (float) (sd[8]);
        dd[9] = (float) (sd[9]);
        dd[10] = (float) (sd[10]);
        dd[11] = (float) (sd[11]);
        dd[12] = (float) (sd[12]);
        dd[13] = (float) (sd[13]);
        dd[14] = (float) (sd[14]);
        return dest;
    }

    /**
     * Set this oriented bounding box to zero center and half-size and identity axes (a point box at
     * the origin).
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleOBB makeIdentity() {
        double[] dd = this.data;
        System.arraycopy(DATA_1, 0, dd, 0, 15);
        return this;
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Double3x4R m, @Mutated DoubleOBB dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _ld0 = mData[2];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[1];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[8];
        double _ld8 = mData[9];
        double _ld9 = mData[6];
        double _ld10 = mData[4];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_sb93f445e_5_fma(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)), java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Double3x4R m, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _ld0 = mData[2];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[1];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[8];
        double _ld8 = mData[9];
        double _ld9 = mData[6];
        double _ld10 = mData[4];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_sb93f445e_5_mulAdd(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))), ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14)))));
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private double transform_sb93f445e_1_fma(double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t37 = java.lang.Math.fma(_t21, _t19, -(_t18 * _t22));
        double _t38 = java.lang.Math.fma(_t18, _t23, -(_t21 * _t20));
        double _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        return java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39));
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private double transform_sb93f445e_1_mulAdd(double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t37 = ((_t21) * (_t19) - (_t18 * _t22));
        double _t38 = ((_t18) * (_t23) - (_t21 * _t20));
        double _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        return ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_sb93f445e_2_fma(double[] mData, double _ld6, double _ld7, double _ld8, double _ld18, double _ld19, double _ld20, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40, double _ct0) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _t52 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        double _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        double _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[2] = java.lang.Math.fma(_ld7, _ld18, java.lang.Math.fma(_ld8, _ld19, java.lang.Math.fma(_ld6, _ld20, mData[11])));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (java.lang.Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (java.lang.Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (java.lang.Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_sb93f445e_2_mulAdd(double[] mData, double _ld6, double _ld7, double _ld8, double _ld18, double _ld19, double _ld20, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40, double _ct0) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _t52 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        double _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        double _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (mData[11]))))));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (((_t46) * (_t53) - (_t45 * _t54)));
        dd[7] = (((_t44) * (_t54) - (_t46 * _t55)));
        dd[8] = (((_t45) * (_t55) - (_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_sb93f445e_3_fma(double _ld0, double _ld2, double _ld4, double _ld9, double _ld10, double _ld11, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _buf0 = java.lang.Math.fma(_ld2, _ld18, java.lang.Math.fma(_ld4, _ld19, java.lang.Math.fma(_ld0, _ld20, _ld21)));
        double _buf1 = java.lang.Math.fma(_ld10, _ld18, java.lang.Math.fma(_ld11, _ld19, java.lang.Math.fma(_ld9, _ld20, _ld22)));
        double _buf2 = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t46, java.lang.Math.fma(_t25, _t44, _t26 * _t45))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t46, java.lang.Math.fma(_t18, _t44, _t20 * _t45))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t21, _t44, _t23 * _t45)))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[12] = _buf2;
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_sb93f445e_3_mulAdd(double _ld0, double _ld2, double _ld4, double _ld9, double _ld10, double _ld11, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _buf0 = ((_ld2) * (_ld18) + (((_ld4) * (_ld19) + (((_ld0) * (_ld20) + (_ld21))))));
        double _buf1 = ((_ld10) * (_ld18) + (((_ld11) * (_ld19) + (((_ld9) * (_ld20) + (_ld22))))));
        double _buf2 = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t46) + (((_t25) * (_t44) + (_t26 * _t45)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t46) + (((_t18) * (_t44) + (_t20 * _t45)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t46) + (((_t21) * (_t44) + (_t23 * _t45)))))))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[12] = _buf2;
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_sb93f445e_4_fma(DoubleOBB dest, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40, double _ct0) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _t52 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        double _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        double _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        double _t62 = java.lang.Math.fma(_t46, _t53, -(_t45 * _t54));
        double _t63 = java.lang.Math.fma(_t44, _t54, -(_t46 * _t55));
        double _t64 = java.lang.Math.fma(_t45, _t55, -(_t44 * _t53));
        double _buf3 = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t64, java.lang.Math.fma(_t25, _t62, _t26 * _t63))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t64, java.lang.Math.fma(_t18, _t62, _t20 * _t63))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t64, java.lang.Math.fma(_t21, _t62, _t23 * _t63)))));
        dd[13] = _buf3;
        return dest;
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_sb93f445e_4_mulAdd(DoubleOBB dest, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40, double _ct0) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _t52 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        double _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        double _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        double _t62 = ((_t46) * (_t53) - (_t45 * _t54));
        double _t63 = ((_t44) * (_t54) - (_t46 * _t55));
        double _t64 = ((_t45) * (_t55) - (_t44 * _t53));
        double _buf3 = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t64) + (((_t25) * (_t62) + (_t26 * _t63)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t64) + (((_t18) * (_t62) + (_t20 * _t63)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t64) + (((_t21) * (_t62) + (_t23 * _t63)))))))));
        dd[13] = _buf3;
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_sb93f445e_5_fma(Double3x4R m, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        double _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        double _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        double _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        double _ct0 = transform_sb93f445e_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_sb93f445e_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_sb93f445e_5_mulAdd(Double3x4R m, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        double _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        double _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        double _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        double _ct0 = transform_sb93f445e_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_sb93f445e_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_mulAdd(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Double3x4R m, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        return transform_degenerate_s115c3475_1_fma(dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4])), java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4])), java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[8], sd[6], mData[9] * sd[7])), java.lang.Math.fma(mData[2], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[1] * sd[7])), java.lang.Math.fma(mData[6], sd[8], java.lang.Math.fma(mData[4], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[8], sd[9], mData[9] * sd[10])), java.lang.Math.fma(mData[2], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Double3x4R m, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        return transform_degenerate_s115c3475_1_mulAdd(dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4])))), ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4])))), ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[8]) * (sd[6]) + (mData[9] * sd[7])))), ((mData[2]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[1] * sd[7])))), ((mData[6]) * (sd[8]) + (((mData[4]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[8]) * (sd[9]) + (mData[9] * sd[10])))), ((mData[2]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[1] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s115c3475_1_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = java.lang.Math.fma(mData[6], sd[11], java.lang.Math.fma(mData[4], sd[9], mData[5] * sd[10]));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_s115c3475_2_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s115c3475_1_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = ((mData[6]) * (sd[11]) + (((mData[4]) * (sd[9]) + (mData[5] * sd[10]))));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_s115c3475_2_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s115c3475_2_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s115c3475_3_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s115c3475_2_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s115c3475_3_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s115c3475_3_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        double _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        double _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        double _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        double _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        double _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        double _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        double _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        double _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        double _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s115c3475_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 5.048709793414476E-29);
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s115c3475_3_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = ((_t88) * (_t71) + (_t44));
        double _t95 = ((_t89) * (_t72) + (_t45));
        double _t96 = ((_t89) * (_t70) + (_t46));
        double _t97 = ((_t89) * (_t71) + (_t47));
        double _t98 = ((_t90) * (_t72) + (_t39));
        double _t99 = ((_t90) * (_t70) + (_t40));
        double _t100 = ((_t90) * (_t71) + (_t41));
        double _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        double _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        double _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s115c3475_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 5.048709793414476E-29);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s115c3475_4_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t110, double _t114, double _t115, double _t116, double _t117, double _t122, double _t123, double _t125) {
        double _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        double _buf0 = java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[1], sd[1], java.lang.Math.fma(mData[2], sd[2], mData[3])));
        double _buf1 = java.lang.Math.fma(mData[4], sd[0], java.lang.Math.fma(mData[5], sd[1], java.lang.Math.fma(mData[6], sd[2], mData[7])));
        dd[2] = java.lang.Math.fma(mData[8], sd[0], java.lang.Math.fma(mData[9], sd[1], java.lang.Math.fma(mData[10], sd[2], mData[11])));
        dd[3] = _t70;
        return transform_degenerate_s115c3475_5_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, java.lang.Math.fma(_t72, _t132, -(_t70 * _t133)), java.lang.Math.fma(_t70, _t134, -(_t71 * _t132)), java.lang.Math.fma(_t71, _t133, -(_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s115c3475_4_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t110, double _t114, double _t115, double _t116, double _t117, double _t122, double _t123, double _t125) {
        double _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        double _buf0 = ((mData[0]) * (sd[0]) + (((mData[1]) * (sd[1]) + (((mData[2]) * (sd[2]) + (mData[3]))))));
        double _buf1 = ((mData[4]) * (sd[0]) + (((mData[5]) * (sd[1]) + (((mData[6]) * (sd[2]) + (mData[7]))))));
        dd[2] = ((mData[8]) * (sd[0]) + (((mData[9]) * (sd[1]) + (((mData[10]) * (sd[2]) + (mData[11]))))));
        dd[3] = _t70;
        return transform_degenerate_s115c3475_5_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, ((_t72) * (_t132) - (_t70 * _t133)), ((_t70) * (_t134) - (_t71 * _t132)), ((_t71) * (_t133) - (_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s115c3475_5_fma(DoubleOBB dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t132, double _t133, double _t134, double _t141, double _t142, double _t143, double _buf0, double _buf1) {
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s115c3475_6_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t72, java.lang.Math.fma(_t25, _t70, _t26 * _t71))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t72, java.lang.Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t72, java.lang.Math.fma(_t22, _t70, _t23 * _t71))))), java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t133, java.lang.Math.fma(_t25, _t132, _t26 * _t134))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t133, java.lang.Math.fma(_t19, _t132, _t20 * _t134))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t133, java.lang.Math.fma(_t22, _t132, _t23 * _t134))))));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s115c3475_5_mulAdd(DoubleOBB dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t132, double _t133, double _t134, double _t141, double _t142, double _t143, double _buf0, double _buf1) {
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s115c3475_6_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t72) + (((_t25) * (_t70) + (_t26 * _t71)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t72) + (((_t19) * (_t70) + (_t20 * _t71)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t72) + (((_t22) * (_t70) + (_t23 * _t71))))))))), ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t133) + (((_t25) * (_t132) + (_t26 * _t134)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t133) + (((_t19) * (_t132) + (_t20 * _t134)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t133) + (((_t22) * (_t132) + (_t23 * _t134))))))))));
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s115c3475_6_fma(DoubleOBB dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t141, double _t142, double _t143, double _buf0, double _buf1, double _buf2, double _buf3) {
        dd[14] = java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t142, java.lang.Math.fma(_t25, _t143, _t26 * _t141))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t142, java.lang.Math.fma(_t19, _t143, _t20 * _t141))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t142, java.lang.Math.fma(_t22, _t143, _t23 * _t141)))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[12] = _buf2;
        dd[13] = _buf3;
        return dest;
    }

    /**
     * Piece 7 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_s115c3475_6_mulAdd(DoubleOBB dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t141, double _t142, double _t143, double _buf0, double _buf1, double _buf2, double _buf3) {
        dd[14] = ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t142) + (((_t25) * (_t143) + (_t26 * _t141)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t142) + (((_t19) * (_t143) + (_t20 * _t141)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t142) + (((_t22) * (_t143) + (_t23 * _t141)))))))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[12] = _buf2;
        dd[13] = _buf3;
        return dest;
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Double4x4R m, @Mutated DoubleOBB dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _ld0 = mData[8];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[4];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[2];
        double _ld8 = mData[6];
        double _ld9 = mData[9];
        double _ld10 = mData[1];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_s55de6487_5_fma(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)), java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Double4x4R m, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _ld0 = mData[8];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[4];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[2];
        double _ld8 = mData[6];
        double _ld9 = mData[9];
        double _ld10 = mData[1];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_s55de6487_5_mulAdd(m, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))), ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s55de6487_2_fma(double[] mData, double _ld6, double _ld7, double _ld8, double _ld18, double _ld19, double _ld20, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40, double _ct0) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _t52 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t53 = (java.lang.Math.fma(_t21, _t19, -(_t18 * _t22))) * _t52;
        double _t54 = (java.lang.Math.fma(_t18, _t23, -(_t21 * _t20))) * _t52;
        double _t55 = (java.lang.Math.fma(_t20, _t22, -(_t23 * _t19))) * _t52;
        dd[2] = java.lang.Math.fma(_ld7, _ld18, java.lang.Math.fma(_ld8, _ld19, java.lang.Math.fma(_ld6, _ld20, mData[14])));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (java.lang.Math.fma(_t46, _t53, -(_t45 * _t54)));
        dd[7] = (java.lang.Math.fma(_t44, _t54, -(_t46 * _t55)));
        dd[8] = (java.lang.Math.fma(_t45, _t55, -(_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = java.lang.Math.fma(_ld24, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(_ld25, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), _ld26 * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private void transform_s55de6487_2_mulAdd(double[] mData, double _ld6, double _ld7, double _ld8, double _ld18, double _ld19, double _ld20, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40, double _ct0) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t40));
        double _t44 = _t18 * _t42;
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _t52 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t53 = (((_t21) * (_t19) - (_t18 * _t22))) * _t52;
        double _t54 = (((_t18) * (_t23) - (_t21 * _t20))) * _t52;
        double _t55 = (((_t20) * (_t22) - (_t23 * _t19))) * _t52;
        dd[2] = ((_ld7) * (_ld18) + (((_ld8) * (_ld19) + (((_ld6) * (_ld20) + (mData[14]))))));
        dd[3] = _t44;
        dd[4] = _t45;
        dd[5] = _t46;
        dd[6] = (((_t46) * (_t53) - (_t45 * _t54)));
        dd[7] = (((_t44) * (_t54) - (_t46 * _t55)));
        dd[8] = (((_t45) * (_t55) - (_t44 * _t53)));
        dd[9] = _t55;
        dd[10] = _t53;
        dd[11] = _t54;
        dd[14] = ((_ld24) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((_ld25) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (_ld26 * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s55de6487_5_fma(Double4x4R m, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        double _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        double _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        double _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        double _ct0 = transform_sb93f445e_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_fma(m, dest);
        transform_s55de6487_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s55de6487_5_mulAdd(Double4x4R m, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        double _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        double _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        double _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        double _ct0 = transform_sb93f445e_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (_t40 * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, dest);
        transform_s55de6487_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_mulAdd(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Double4x4R m, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        return transform_degenerate_sc004a930_1_fma(dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4])), java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4])), java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[2], sd[6], mData[6] * sd[7])), java.lang.Math.fma(mData[8], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[4] * sd[7])), java.lang.Math.fma(mData[9], sd[8], java.lang.Math.fma(mData[1], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[2], sd[9], mData[6] * sd[10])), java.lang.Math.fma(mData[8], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Double4x4R m, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        return transform_degenerate_sc004a930_1_mulAdd(dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4])))), ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4])))), ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[2]) * (sd[6]) + (mData[6] * sd[7])))), ((mData[8]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[4] * sd[7])))), ((mData[9]) * (sd[8]) + (((mData[1]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[2]) * (sd[9]) + (mData[6] * sd[10])))), ((mData[8]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[4] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sc004a930_1_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = java.lang.Math.fma(mData[9], sd[11], java.lang.Math.fma(mData[1], sd[9], mData[5] * sd[10]));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_sc004a930_2_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sc004a930_1_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = ((mData[9]) * (sd[11]) + (((mData[1]) * (sd[9]) + (mData[5] * sd[10]))));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_sc004a930_2_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sc004a930_2_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_sc004a930_3_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sc004a930_2_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_sc004a930_3_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sc004a930_3_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        double _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        double _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        double _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        double _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        double _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        double _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        double _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        double _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        double _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sc004a930_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 5.048709793414476E-29);
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_sc004a930_3_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = ((_t88) * (_t71) + (_t44));
        double _t95 = ((_t89) * (_t72) + (_t45));
        double _t96 = ((_t89) * (_t70) + (_t46));
        double _t97 = ((_t89) * (_t71) + (_t47));
        double _t98 = ((_t90) * (_t72) + (_t39));
        double _t99 = ((_t90) * (_t70) + (_t40));
        double _t100 = ((_t90) * (_t71) + (_t41));
        double _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        double _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        double _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sc004a930_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54) * 5.048709793414476E-29);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_sc004a930_4_fma(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t110, double _t114, double _t115, double _t116, double _t117, double _t122, double _t123, double _t125) {
        double _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        double _buf0 = java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[4], sd[1], java.lang.Math.fma(mData[8], sd[2], mData[12])));
        double _buf1 = java.lang.Math.fma(mData[1], sd[0], java.lang.Math.fma(mData[5], sd[1], java.lang.Math.fma(mData[9], sd[2], mData[13])));
        dd[2] = java.lang.Math.fma(mData[2], sd[0], java.lang.Math.fma(mData[6], sd[1], java.lang.Math.fma(mData[10], sd[2], mData[14])));
        return transform_degenerate_sc004a930_5_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, java.lang.Math.fma(_t72, _t132, -(_t70 * _t133)), java.lang.Math.fma(_t70, _t134, -(_t71 * _t132)), java.lang.Math.fma(_t71, _t133, -(_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_sc004a930_4_mulAdd(DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t110, double _t114, double _t115, double _t116, double _t117, double _t122, double _t123, double _t125) {
        double _t132, _t133, _t134;
        if (_t122 > _t125) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t132 = _t123 * _t93;
                    _t133 = _t123 * _t92;
                    _t134 = _t123 * _t94;
                } else {
                    _t132 = _t123 * _t96;
                    _t133 = _t123 * _t95;
                    _t134 = _t123 * _t97;
                }
            } else {
                _t132 = _t123 * _t99;
                _t133 = _t123 * _t98;
                _t134 = _t123 * _t100;
            }
        } else {
            _t132 = _t110 * _t83;
            _t133 = _t110 * _t84;
            _t134 = _t110 * _t91;
        }
        double _buf0 = ((mData[0]) * (sd[0]) + (((mData[4]) * (sd[1]) + (((mData[8]) * (sd[2]) + (mData[12]))))));
        double _buf1 = ((mData[1]) * (sd[0]) + (((mData[5]) * (sd[1]) + (((mData[9]) * (sd[2]) + (mData[13]))))));
        dd[2] = ((mData[2]) * (sd[0]) + (((mData[6]) * (sd[1]) + (((mData[10]) * (sd[2]) + (mData[14]))))));
        return transform_degenerate_sc004a930_5_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t132, _t133, _t134, ((_t72) * (_t132) - (_t70 * _t133)), ((_t70) * (_t134) - (_t71 * _t132)), ((_t71) * (_t133) - (_t72 * _t134)), _buf0, _buf1);
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_sc004a930_5_fma(DoubleOBB dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t132, double _t133, double _t134, double _t141, double _t142, double _t143, double _buf0, double _buf1) {
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s115c3475_6_fma(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t72, java.lang.Math.fma(_t25, _t70, _t26 * _t71))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t72, java.lang.Math.fma(_t19, _t70, _t20 * _t71))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t72, java.lang.Math.fma(_t22, _t70, _t23 * _t71))))), java.lang.Math.fma(sd[14], java.lang.Math.abs(java.lang.Math.fma(_t24, _t133, java.lang.Math.fma(_t25, _t132, _t26 * _t134))), java.lang.Math.fma(sd[12], java.lang.Math.abs(java.lang.Math.fma(_t18, _t133, java.lang.Math.fma(_t19, _t132, _t20 * _t134))), sd[13] * java.lang.Math.abs(java.lang.Math.fma(_t21, _t133, java.lang.Math.fma(_t22, _t132, _t23 * _t134))))));
    }

    /**
     * Piece 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code transform}; reached only through it.
     */
    private DoubleOBB transform_degenerate_sc004a930_5_mulAdd(DoubleOBB dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t70, double _t71, double _t72, double _t132, double _t133, double _t134, double _t141, double _t142, double _t143, double _buf0, double _buf1) {
        dd[3] = _t70;
        dd[4] = _t71;
        dd[5] = _t72;
        dd[6] = _t132;
        dd[7] = _t134;
        dd[8] = _t133;
        dd[9] = _t143;
        dd[10] = _t141;
        dd[11] = _t142;
        return transform_degenerate_s115c3475_6_mulAdd(dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t141, _t142, _t143, _buf0, _buf1, ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t72) + (((_t25) * (_t70) + (_t26 * _t71)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t72) + (((_t19) * (_t70) + (_t20 * _t71)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t72) + (((_t22) * (_t70) + (_t23 * _t71))))))))), ((sd[14]) * (java.lang.Math.abs(((_t24) * (_t133) + (((_t25) * (_t132) + (_t26 * _t134)))))) + (((sd[12]) * (java.lang.Math.abs(((_t18) * (_t133) + (((_t19) * (_t132) + (_t20 * _t134)))))) + (sd[13] * java.lang.Math.abs(((_t21) * (_t133) + (((_t22) * (_t132) + (_t23 * _t134))))))))));
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-94}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Double3x4R m, double epsilon, @Mutated DoubleOBB dest) {
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(m, epsilon, dest);
        return transform_mulAdd(m, epsilon, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_fma(Double3x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _ld0 = mData[2];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[1];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[8];
        double _ld8 = mData[9];
        double _ld9 = mData[6];
        double _ld10 = mData[4];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_s843cfab7_5_fma(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Double3x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _ld0 = mData[2];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[1];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[8];
        double _ld8 = mData[9];
        double _ld9 = mData[6];
        double _ld10 = mData[4];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_s843cfab7_5_mulAdd(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[3], mData[7], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s843cfab7_5_fma(Double3x4R m, double epsilon, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14));
        double _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        double _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        double _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        double _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        double _ct0 = transform_sb93f445e_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), epsilon * _t40, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon, dest);
        transform_sb93f445e_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s843cfab7_5_mulAdd(Double3x4R m, double epsilon, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14))));
        double _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        double _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        double _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        double _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        double _ct0 = transform_sb93f445e_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (epsilon * _t40) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon, dest);
        transform_sb93f445e_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_mulAdd(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Double3x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        return transform_degenerate_s53924188_1_fma(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4])), java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4])), java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[8], sd[6], mData[9] * sd[7])), java.lang.Math.fma(mData[2], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[1] * sd[7])), java.lang.Math.fma(mData[6], sd[8], java.lang.Math.fma(mData[4], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[8], sd[9], mData[9] * sd[10])), java.lang.Math.fma(mData[2], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[1] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Double3x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        return transform_degenerate_s53924188_1_mulAdd(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4])))), ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4])))), ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[8]) * (sd[6]) + (mData[9] * sd[7])))), ((mData[2]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[1] * sd[7])))), ((mData[6]) * (sd[8]) + (((mData[4]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[8]) * (sd[9]) + (mData[9] * sd[10])))), ((mData[2]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[1] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s53924188_1_fma(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = java.lang.Math.fma(mData[6], sd[11], java.lang.Math.fma(mData[4], sd[9], mData[5] * sd[10]));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_s53924188_2_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s53924188_1_mulAdd(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = ((mData[6]) * (sd[11]) + (((mData[4]) * (sd[9]) + (mData[5] * sd[10]))));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_s53924188_2_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s53924188_2_fma(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s53924188_3_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s53924188_2_mulAdd(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s53924188_3_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s53924188_3_fma(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        double _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        double _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        double _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        double _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        double _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        double _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        double _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        double _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        double _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s115c3475_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s53924188_3_mulAdd(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = ((_t88) * (_t71) + (_t44));
        double _t95 = ((_t89) * (_t72) + (_t45));
        double _t96 = ((_t89) * (_t70) + (_t46));
        double _t97 = ((_t89) * (_t71) + (_t47));
        double _t98 = ((_t90) * (_t72) + (_t39));
        double _t99 = ((_t90) * (_t70) + (_t40));
        double _t100 = ((_t90) * (_t71) + (_t41));
        double _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        double _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        double _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_s115c3475_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), and store the
     * result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-94}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Double4x4R m, double epsilon, @Mutated DoubleOBB dest) {
        if (org.joml2.internal.FmaSwitch.ON) return transform_fma(m, epsilon, dest);
        return transform_mulAdd(m, epsilon, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_fma(Double4x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _ld0 = mData[8];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[4];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[2];
        double _ld8 = mData[6];
        double _ld9 = mData[9];
        double _ld10 = mData[1];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_s9594a246_5_fma(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, java.lang.Math.fma(_ld0, _ld1, java.lang.Math.fma(_ld2, _ld3, _ld4 * _ld5)), java.lang.Math.fma(_ld6, _ld1, java.lang.Math.fma(_ld7, _ld3, _ld8 * _ld5)), java.lang.Math.fma(_ld9, _ld1, java.lang.Math.fma(_ld10, _ld3, _ld11 * _ld5)), java.lang.Math.fma(_ld0, _ld12, java.lang.Math.fma(_ld2, _ld13, _ld4 * _ld14)), java.lang.Math.fma(_ld6, _ld12, java.lang.Math.fma(_ld7, _ld13, _ld8 * _ld14)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleOBB transform_mulAdd(Double4x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _ld0 = mData[8];
        double _ld1 = sd[5];
        double _ld2 = mData[0];
        double _ld3 = sd[3];
        double _ld4 = mData[4];
        double _ld5 = sd[4];
        double _ld6 = mData[10];
        double _ld7 = mData[2];
        double _ld8 = mData[6];
        double _ld9 = mData[9];
        double _ld10 = mData[1];
        double _ld11 = mData[5];
        double _ld12 = sd[8];
        double _ld13 = sd[6];
        double _ld14 = sd[7];
        return transform_s9594a246_5_mulAdd(m, epsilon, dest, mData, _ld0, _ld2, _ld4, _ld6, _ld7, _ld8, _ld9, _ld10, _ld11, _ld12, _ld13, _ld14, sd[11], sd[9], sd[10], sd[0], sd[1], sd[2], mData[12], mData[13], sd[14], sd[12], sd[13], ((DoubleOBBImpl) dest).data, ((_ld0) * (_ld1) + (((_ld2) * (_ld3) + (_ld4 * _ld5)))), ((_ld6) * (_ld1) + (((_ld7) * (_ld3) + (_ld8 * _ld5)))), ((_ld9) * (_ld1) + (((_ld10) * (_ld3) + (_ld11 * _ld5)))), ((_ld0) * (_ld12) + (((_ld2) * (_ld13) + (_ld4 * _ld14)))), ((_ld6) * (_ld12) + (((_ld7) * (_ld13) + (_ld8 * _ld14)))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s9594a246_5_fma(Double4x4R m, double epsilon, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = java.lang.Math.fma(_ld9, _ld12, java.lang.Math.fma(_ld10, _ld13, _ld11 * _ld14));
        double _t24 = java.lang.Math.fma(_ld6, _ld15, java.lang.Math.fma(_ld7, _ld16, _ld8 * _ld17));
        double _t25 = java.lang.Math.fma(_ld0, _ld15, java.lang.Math.fma(_ld2, _ld16, _ld4 * _ld17));
        double _t26 = java.lang.Math.fma(_ld9, _ld15, java.lang.Math.fma(_ld10, _ld16, _ld11 * _ld17));
        double _t40 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        double _ct0 = transform_sb93f445e_1_fma(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > java.lang.Math.fma(java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t21, _t21, _t23 * _t23)), epsilon * _t40, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon, dest);
        transform_s55de6487_2_fma(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_fma(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_fma(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s9594a246_5_mulAdd(Double4x4R m, double epsilon, DoubleOBB dest, double[] mData, double _ld0, double _ld2, double _ld4, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _ld15, double _ld16, double _ld17, double _ld18, double _ld19, double _ld20, double _ld21, double _ld22, double _ld24, double _ld25, double _ld26, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = ((_ld9) * (_ld12) + (((_ld10) * (_ld13) + (_ld11 * _ld14))));
        double _t24 = ((_ld6) * (_ld15) + (((_ld7) * (_ld16) + (_ld8 * _ld17))));
        double _t25 = ((_ld0) * (_ld15) + (((_ld2) * (_ld16) + (_ld4 * _ld17))));
        double _t26 = ((_ld9) * (_ld15) + (((_ld10) * (_ld16) + (_ld11 * _ld17))));
        double _t40 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        double _ct0 = transform_sb93f445e_1_mulAdd(_t18, _t19, _t20, _t21, _t22, _t23);
        if (!(_ct0 > ((((_t22) * (_t22) + (((_t21) * (_t21) + (_t23 * _t23))))) * (epsilon * _t40) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon, dest);
        transform_s55de6487_2_mulAdd(mData, _ld6, _ld7, _ld8, _ld18, _ld19, _ld20, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
        transform_sb93f445e_3_mulAdd(_ld0, _ld2, _ld4, _ld9, _ld10, _ld11, _ld18, _ld19, _ld20, _ld21, _ld22, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40);
        return transform_sb93f445e_4_mulAdd(dest, _ld24, _ld25, _ld26, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40, _ct0);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_fma(Double4x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        return transform_degenerate_s8cdefe3d_1_fma(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4])), java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4])), java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4])), java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[2], sd[6], mData[6] * sd[7])), java.lang.Math.fma(mData[8], sd[8], java.lang.Math.fma(mData[0], sd[6], mData[4] * sd[7])), java.lang.Math.fma(mData[9], sd[8], java.lang.Math.fma(mData[1], sd[6], mData[5] * sd[7])), java.lang.Math.fma(mData[10], sd[11], java.lang.Math.fma(mData[2], sd[9], mData[6] * sd[10])), java.lang.Math.fma(mData[8], sd[11], java.lang.Math.fma(mData[0], sd[9], mData[4] * sd[10])));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate_mulAdd(Double4x4R m, double epsilon, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        return transform_degenerate_s8cdefe3d_1_mulAdd(epsilon, dest, sd, mData, ((DoubleOBBImpl) dest).data, ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4])))), ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4])))), ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4])))), ((mData[10]) * (sd[8]) + (((mData[2]) * (sd[6]) + (mData[6] * sd[7])))), ((mData[8]) * (sd[8]) + (((mData[0]) * (sd[6]) + (mData[4] * sd[7])))), ((mData[9]) * (sd[8]) + (((mData[1]) * (sd[6]) + (mData[5] * sd[7])))), ((mData[10]) * (sd[11]) + (((mData[2]) * (sd[9]) + (mData[6] * sd[10])))), ((mData[8]) * (sd[11]) + (((mData[0]) * (sd[9]) + (mData[4] * sd[10])))));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s8cdefe3d_1_fma(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = java.lang.Math.fma(mData[9], sd[11], java.lang.Math.fma(mData[1], sd[9], mData[5] * sd[10]));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_s8cdefe3d_2_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41)), java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44)), java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47)));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s8cdefe3d_1_mulAdd(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = ((mData[9]) * (sd[11]) + (((mData[1]) * (sd[9]) + (mData[5] * sd[10]))));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        double _t45 = _t24 * _t29;
        double _t46 = _t25 * _t29;
        double _t47 = _t26 * _t29;
        return transform_degenerate_s8cdefe3d_2_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41)))), ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44)))), ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47)))));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s8cdefe3d_2_fma(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -java.lang.Math.fma(_t72, _t42, java.lang.Math.fma(_t70, _t43, _t71 * _t44));
        return transform_degenerate_s8cdefe3d_3_fma(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -java.lang.Math.fma(_t72, _t45, java.lang.Math.fma(_t70, _t46, _t71 * _t47)), -java.lang.Math.fma(_t72, _t39, java.lang.Math.fma(_t70, _t40, _t71 * _t41)), java.lang.Math.fma(_t88, _t72, _t42), java.lang.Math.fma(_t88, _t70, _t43));
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s8cdefe3d_2_mulAdd(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0;
                    _t64 = 1.0;
                    _t65 = 0.0;
                }
            }
        }
        double _t69 = (1.0 / java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -((_t72) * (_t42) + (((_t70) * (_t43) + (_t71 * _t44))));
        return transform_degenerate_s8cdefe3d_3_mulAdd(epsilon, dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t39, _t40, _t41, _t44, _t45, _t46, _t47, _t54, _t55, _t56, _t70, _t71, _t72, _t83, _t84, _t91, _t88, -((_t72) * (_t45) + (((_t70) * (_t46) + (_t71 * _t47)))), -((_t72) * (_t39) + (((_t70) * (_t40) + (_t71 * _t41)))), ((_t88) * (_t72) + (_t42)), ((_t88) * (_t70) + (_t43)));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s8cdefe3d_3_fma(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = java.lang.Math.fma(_t88, _t71, _t44);
        double _t95 = java.lang.Math.fma(_t89, _t72, _t45);
        double _t96 = java.lang.Math.fma(_t89, _t70, _t46);
        double _t97 = java.lang.Math.fma(_t89, _t71, _t47);
        double _t98 = java.lang.Math.fma(_t90, _t72, _t39);
        double _t99 = java.lang.Math.fma(_t90, _t70, _t40);
        double _t100 = java.lang.Math.fma(_t90, _t71, _t41);
        double _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        double _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        double _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sc004a930_4_fma(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83)))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /** Piece 4 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_degenerate_s8cdefe3d_3_mulAdd(double epsilon, DoubleOBB dest, double[] sd, double[] mData, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t39, double _t40, double _t41, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _t56, double _t70, double _t71, double _t72, double _t83, double _t84, double _t91, double _t88, double _t89, double _t90, double _t92, double _t93) {
        double _t94 = ((_t88) * (_t71) + (_t44));
        double _t95 = ((_t89) * (_t72) + (_t45));
        double _t96 = ((_t89) * (_t70) + (_t46));
        double _t97 = ((_t89) * (_t71) + (_t47));
        double _t98 = ((_t90) * (_t72) + (_t39));
        double _t99 = ((_t90) * (_t70) + (_t40));
        double _t100 = ((_t90) * (_t71) + (_t41));
        double _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        double _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        double _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        double _t117 = Math.max(_t114, _t115);
        double _t122 = Math.max(_t117, _t116);
        return transform_degenerate_sc004a930_4_mulAdd(dest, sd, mData, dd, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t70, _t71, _t72, _t83, _t84, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, (1.0 / java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83)))))), _t114, _t115, _t116, _t117, _t122, (1.0 / java.lang.Math.sqrt(_t122)), epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _t55 : _t56 : _t54));
    }

    /**
     * Translate this oriented bounding box by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB translate(Double3R delta, @Mutated DoubleOBB dest) {
        double[] deltaData = ((Double3Impl) delta).data;
        if (COL_NARROW) return translate_narrow(delta, dest);
        double deltaY = deltaData[1];
        double deltaZ = deltaData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = deltaData[0] + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        DoubleVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        DoubleVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }

    /**
     * Translate this oriented bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB translate(double deltaX, double deltaY, double deltaZ, @Mutated DoubleOBB dest) {
        if (COL_NARROW) return translate_narrow(deltaX, deltaY, deltaZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        DoubleVector.fromArray(COL_SPECIES, sd, 7).intoArray(dd, 7);
        DoubleVector.fromArray(COL_SPECIES, sd, 11).intoArray(dd, 11);
        return dest;
    }

    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * axes of this oriented bounding box must be orthonormal.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Double3R p, @Mutated Double3 dest) {
        double[] pData = ((Double3Impl) p).data;
        double pX = pData[0];
        double pY = pData[1];
        double pZ = pData[2];
        if (org.joml2.internal.FmaSwitch.ON) return closestPointToPoint_fma(pX, pY, pZ, dest);
        return closestPointToPoint_mulAdd(pX, pY, pZ, dest);
    }

    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * axes of this oriented bounding box must be orthonormal.
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
        if (org.joml2.internal.FmaSwitch.ON) return closestPointToPoint_fma(pX, pY, pZ, dest);
        return closestPointToPoint_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code closestPointToPoint} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 closestPointToPoint_fma(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t3 = pZ - sd[2];
        double _t4 = pX - sd[0];
        double _t5 = pY - sd[1];
        double _t18 = Math.max(-sd[12], Math.min(java.lang.Math.fma(sd[5], _t3, java.lang.Math.fma(sd[3], _t4, sd[4] * _t5)), sd[12]));
        double _t19 = Math.max(-sd[13], Math.min(java.lang.Math.fma(sd[8], _t3, java.lang.Math.fma(sd[6], _t4, sd[7] * _t5)), sd[13]));
        double _t20 = Math.max(-sd[14], Math.min(java.lang.Math.fma(sd[11], _t3, java.lang.Math.fma(sd[9], _t4, sd[10] * _t5)), sd[14]));
        dd[0] = java.lang.Math.fma(sd[3], _t18, java.lang.Math.fma(sd[6], _t19, java.lang.Math.fma(sd[9], _t20, sd[0])));
        dd[1] = java.lang.Math.fma(sd[4], _t18, java.lang.Math.fma(sd[7], _t19, java.lang.Math.fma(sd[10], _t20, sd[1])));
        dd[2] = java.lang.Math.fma(sd[5], _t18, java.lang.Math.fma(sd[8], _t19, java.lang.Math.fma(sd[11], _t20, sd[2])));
        return dest;
    }

    /** {@code closestPointToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 closestPointToPoint_mulAdd(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t3 = pZ - sd[2];
        double _t4 = pX - sd[0];
        double _t5 = pY - sd[1];
        double _t18 = Math.max(-sd[12], Math.min(((sd[5]) * (_t3) + (((sd[3]) * (_t4) + (sd[4] * _t5)))), sd[12]));
        double _t19 = Math.max(-sd[13], Math.min(((sd[8]) * (_t3) + (((sd[6]) * (_t4) + (sd[7] * _t5)))), sd[13]));
        double _t20 = Math.max(-sd[14], Math.min(((sd[11]) * (_t3) + (((sd[9]) * (_t4) + (sd[10] * _t5)))), sd[14]));
        dd[0] = ((sd[3]) * (_t18) + (((sd[6]) * (_t19) + (((sd[9]) * (_t20) + (sd[0]))))));
        dd[1] = ((sd[4]) * (_t18) + (((sd[7]) * (_t19) + (((sd[10]) * (_t20) + (sd[1]))))));
        dd[2] = ((sd[5]) * (_t18) + (((sd[8]) * (_t19) + (((sd[11]) * (_t20) + (sd[2]))))));
        return dest;
    }

    /**
     * Determine whether this oriented bounding box contains the given point (boundary inclusive).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative.
     *
     * @param p the point to test
     * @return {@code true} if this oriented bounding box contains the given point (boundary
     *        inclusive), {@code false} otherwise
     */
    public boolean containsPoint(Double3R p) {
        double[] pData = ((Double3Impl) p).data;
        double pX = pData[0];
        double pY = pData[1];
        double pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return containsPoint_mulAdd(pX, pY, pZ);
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) <= sd[13])) return false;
        return java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) <= sd[14];
    }

    /**
     * Determine whether this oriented bounding box contains the given point (boundary inclusive).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return {@code true} if this oriented bounding box contains the given point (boundary
     *        inclusive), {@code false} otherwise
     */
    public boolean containsPoint(double pX, double pY, double pZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return containsPoint_mulAdd(pX, pY, pZ);
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) <= sd[13])) return false;
        return java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) <= sd[14];
    }

    /** {@code containsPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean containsPoint_mulAdd(double pX, double pY, double pZ) {
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        if (!(java.lang.Math.abs(((sd[5]) * (_t0) + (((sd[3]) * (_t1) + (sd[4] * _t2))))) <= sd[12])) return false;
        if (!(java.lang.Math.abs(((sd[8]) * (_t0) + (((sd[6]) * (_t1) + (sd[7] * _t2))))) <= sd[13])) return false;
        return java.lang.Math.abs(((sd[11]) * (_t0) + (((sd[9]) * (_t1) + (sd[10] * _t2))))) <= sd[14];
    }

    /**
     * Compute the squared distance between this oriented bounding box and the given point,
     * evaluated in the box's local frame; zero for a point inside or on the box. Assumes the box's
     * axes are orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * axes of this oriented bounding box must be orthonormal.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this oriented bounding box and the given point,
     *        evaluated in the box's local frame; zero for a point inside or on the box. Assumes the
     *        box's axes are orthonormal
     */
    public double distanceSquaredToPoint(Double3R p) {
        double[] pData = ((Double3Impl) p).data;
        double pX = pData[0];
        double pY = pData[1];
        double pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return distanceSquaredToPoint_mulAdd(pX, pY, pZ);
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t18 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        double _t19 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        double _t20 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
    }

    /**
     * Compute the squared distance between this oriented bounding box and the given point,
     * evaluated in the box's local frame; zero for a point inside or on the box. Assumes the box's
     * axes are orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * axes of this oriented bounding box must be orthonormal.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this oriented bounding box and the given point,
     *        evaluated in the box's local frame; zero for a point inside or on the box. Assumes the
     *        box's axes are orthonormal
     */
    public double distanceSquaredToPoint(double pX, double pY, double pZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return distanceSquaredToPoint_mulAdd(pX, pY, pZ);
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t18 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        double _t19 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        double _t20 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
    }

    /** {@code distanceSquaredToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double distanceSquaredToPoint_mulAdd(double pX, double pY, double pZ) {
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t18 = Math.max(0.0, java.lang.Math.abs(((sd[11]) * (_t0) + (((sd[9]) * (_t1) + (sd[10] * _t2))))) - sd[14]);
        double _t19 = Math.max(0.0, java.lang.Math.abs(((sd[5]) * (_t0) + (((sd[3]) * (_t1) + (sd[4] * _t2))))) - sd[12]);
        double _t20 = Math.max(0.0, java.lang.Math.abs(((sd[8]) * (_t0) + (((sd[6]) * (_t1) + (sd[7] * _t2))))) - sd[13]);
        return ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
    }

    /**
     * Compute the distance between this oriented bounding box and the given point, evaluated in the
     * box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     * orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * axes of this oriented bounding box must be orthonormal.
     *
     * @param p the point to measure the distance to
     * @return the distance between this oriented bounding box and the given point, evaluated in the
     *        box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     *        orthonormal
     */
    public double distanceToPoint(Double3R p) {
        double[] pData = ((Double3Impl) p).data;
        double pX = pData[0];
        double pY = pData[1];
        double pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return distanceToPoint_mulAdd(pX, pY, pZ);
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t18 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        double _t19 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        double _t20 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return java.lang.Math.sqrt(java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
    }

    /**
     * Compute the distance between this oriented bounding box and the given point, evaluated in the
     * box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     * orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * axes of this oriented bounding box must be orthonormal.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this oriented bounding box and the given point, evaluated in the
     *        box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     *        orthonormal
     */
    public double distanceToPoint(double pX, double pY, double pZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return distanceToPoint_mulAdd(pX, pY, pZ);
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t18 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[11], _t0, java.lang.Math.fma(sd[9], _t1, sd[10] * _t2))) - sd[14]);
        double _t19 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[5], _t0, java.lang.Math.fma(sd[3], _t1, sd[4] * _t2))) - sd[12]);
        double _t20 = Math.max(0.0, java.lang.Math.abs(java.lang.Math.fma(sd[8], _t0, java.lang.Math.fma(sd[6], _t1, sd[7] * _t2))) - sd[13]);
        return java.lang.Math.sqrt(java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
    }

    /** {@code distanceToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double distanceToPoint_mulAdd(double pX, double pY, double pZ) {
        double[] sd = this.data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t18 = Math.max(0.0, java.lang.Math.abs(((sd[11]) * (_t0) + (((sd[9]) * (_t1) + (sd[10] * _t2))))) - sd[14]);
        double _t19 = Math.max(0.0, java.lang.Math.abs(((sd[5]) * (_t0) + (((sd[3]) * (_t1) + (sd[4] * _t2))))) - sd[12]);
        double _t20 = Math.max(0.0, java.lang.Math.abs(((sd[8]) * (_t0) + (((sd[6]) * (_t1) + (sd[7] * _t2))))) - sd[13]);
        return java.lang.Math.sqrt(((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20)))));
    }

    /**
     * Get the local {@code X} axis of this oriented bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getAxisX(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }

    /**
     * Get the local {@code Y} axis of this oriented bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getAxisY(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = sd[8];
        return dest;
    }

    /**
     * Get the local {@code Z} axis of this oriented bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getAxisZ(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[9];
        dd[1] = sd[10];
        dd[2] = sd[11];
        return dest;
    }

    /**
     * Get the center of this oriented bounding box and store the result in {@code dest}.
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
     * Get the half extents of this oriented bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getHalfSize(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[12];
        dd[1] = sd[13];
        dd[2] = sd[14];
        return dest;
    }

    /**
     * Determine whether this oriented bounding box intersects {@code o}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative; the axes of {@code o} must
     * be orthonormal; each component of the half sizes of {@code o} must not be negative.
     *
     * @param o the oriented bounding box to test for intersection
     * @return {@code true} if this oriented bounding box intersects {@code o}, {@code false}
     *        otherwise
     */
    public boolean intersectsOBB(DoubleOBBR o) {
        double[] oData = ((DoubleOBBImpl) o).data;
        return intersectsOBB(oData[0], oData[1], oData[2], oData[3], oData[4], oData[5], oData[6], oData[7], oData[8], oData[9], oData[10], oData[11], oData[12], oData[13], oData[14]);
    }

    /**
     * Determine whether this oriented bounding box intersects ({@code oCX}, {@code oCY},
     * {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     * {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     * {@code oHsZ}).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative; the axes of
     * {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * must be orthonormal; each component of {@code (oHsX, oHsY, oHsZ)} must not be negative.
     *
     * @param oCX the {@code cX} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oCY the {@code cY} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oCZ the {@code cZ} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXx the {@code uXx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXy the {@code uXy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXz the {@code uXz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYx the {@code uYx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYy the {@code uYy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYz the {@code uYz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZx the {@code uZx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZy the {@code uZy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZz the {@code uZz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsX the {@code hsX} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsY the {@code hsY} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsZ the {@code hsZ} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @return {@code true} if this oriented bounding box intersects ({@code oCX}, {@code oCY},
     *        {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     *        {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     *        {@code oHsZ}), {@code false} otherwise
     */
    public boolean intersectsOBB(double oCX, double oCY, double oCZ, double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
        double[] sd = this.data;
        double _ld3 = sd[5];
        double _ld4 = sd[3];
        double _ld5 = sd[4];
        double _t0 = oCZ - sd[2];
        double _t1 = oCX - sd[0];
        double _t2 = oCY - sd[1];
        double _t24 = java.lang.Math.fma(oUXz, _ld3, java.lang.Math.fma(oUXx, _ld4, oUXy * _ld5));
        double _t25 = java.lang.Math.fma(oUYz, _ld3, java.lang.Math.fma(oUYx, _ld4, oUYy * _ld5));
        double _t26 = java.lang.Math.fma(oUZz, _ld3, java.lang.Math.fma(oUZx, _ld4, oUZy * _ld5));
        return intersectsOBB_sd948180f_2_fma(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, sd[12], sd[8], sd[6], sd[7], sd[13], sd[11], sd[9], sd[10], sd[14], _t0, _t1, _t2, java.lang.Math.fma(_ld3, _t0, java.lang.Math.fma(_ld4, _t1, _ld5 * _t2)), _t24, java.lang.Math.abs(_t24) + 1.0E-8, _t25, java.lang.Math.abs(_t25) + 1.0E-8, _t26, java.lang.Math.abs(_t26) + 1.0E-8);
    }

    /** {@code intersectsOBB} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_mulAdd(double oCX, double oCY, double oCZ, double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ) {
        double[] sd = this.data;
        double _ld3 = sd[5];
        double _ld4 = sd[3];
        double _ld5 = sd[4];
        double _t0 = oCZ - sd[2];
        double _t1 = oCX - sd[0];
        double _t2 = oCY - sd[1];
        double _t24 = ((oUXz) * (_ld3) + (((oUXx) * (_ld4) + (oUXy * _ld5))));
        double _t25 = ((oUYz) * (_ld3) + (((oUYx) * (_ld4) + (oUYy * _ld5))));
        double _t26 = ((oUZz) * (_ld3) + (((oUZx) * (_ld4) + (oUZy * _ld5))));
        return intersectsOBB_sd948180f_2_mulAdd(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, sd[12], sd[8], sd[6], sd[7], sd[13], sd[11], sd[9], sd[10], sd[14], _t0, _t1, _t2, ((_ld3) * (_t0) + (((_ld4) * (_t1) + (_ld5 * _t2)))), _t24, java.lang.Math.abs(_t24) + 1.0E-8, _t25, java.lang.Math.abs(_t25) + 1.0E-8, _t26, java.lang.Math.abs(_t26) + 1.0E-8);
    }

    /** Part 1 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_1_fma(double oHsX, double oHsY, double oHsZ, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t25, double _t26, double _t55, double _t27, double _t28, double _t29, double _t56, double _t30, double _t31, double _t32) {
        double _t47 = java.lang.Math.abs(_t26) + 1.0E-8;
        double _t48 = java.lang.Math.abs(_t27) + 1.0E-8;
        double _t49 = java.lang.Math.abs(_t28) + 1.0E-8;
        double _t51 = java.lang.Math.abs(_t30) + 1.0E-8;
        double _t52 = java.lang.Math.abs(_t31) + 1.0E-8;
        double _t53 = java.lang.Math.abs(_t32) + 1.0E-8;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t54, -(_t26 * _t56))) <= java.lang.Math.fma(oHsX, _t49, oHsY * _t48) + java.lang.Math.fma(_ld6, _t53, _ld14 * _t47))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t24, _t55, -(_t27 * _t54))) <= java.lang.Math.fma(oHsY, _t53, oHsZ * _t52) + java.lang.Math.fma(_ld6, _t48, _ld10 * (java.lang.Math.abs(_t24) + 1.0E-8)))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t25, _t55, -(_t28 * _t54))) <= java.lang.Math.fma(oHsX, _t53, oHsZ * _t51) + java.lang.Math.fma(_ld6, _t49, _ld10 * (java.lang.Math.abs(_t25) + 1.0E-8)))) return false;
        return java.lang.Math.abs(java.lang.Math.fma(_t26, _t55, -(_t29 * _t54))) <= java.lang.Math.fma(oHsX, _t52, oHsY * _t51) + java.lang.Math.fma(_ld6, (java.lang.Math.abs(_t29) + 1.0E-8), _ld10 * _t47);
    }

    /** Part 1 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_1_mulAdd(double oHsX, double oHsY, double oHsZ, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t25, double _t26, double _t55, double _t27, double _t28, double _t29, double _t56, double _t30, double _t31, double _t32) {
        double _t47 = java.lang.Math.abs(_t26) + 1.0E-8;
        double _t48 = java.lang.Math.abs(_t27) + 1.0E-8;
        double _t49 = java.lang.Math.abs(_t28) + 1.0E-8;
        double _t51 = java.lang.Math.abs(_t30) + 1.0E-8;
        double _t52 = java.lang.Math.abs(_t31) + 1.0E-8;
        double _t53 = java.lang.Math.abs(_t32) + 1.0E-8;
        if (!(java.lang.Math.abs(((_t32) * (_t54) - (_t26 * _t56))) <= ((oHsX) * (_t49) + (oHsY * _t48)) + ((_ld6) * (_t53) + (_ld14 * _t47)))) return false;
        if (!(java.lang.Math.abs(((_t24) * (_t55) - (_t27 * _t54))) <= ((oHsY) * (_t53) + (oHsZ * _t52)) + ((_ld6) * (_t48) + (_ld10 * (java.lang.Math.abs(_t24) + 1.0E-8))))) return false;
        if (!(java.lang.Math.abs(((_t25) * (_t55) - (_t28 * _t54))) <= ((oHsX) * (_t53) + (oHsZ * _t51)) + ((_ld6) * (_t49) + (_ld10 * (java.lang.Math.abs(_t25) + 1.0E-8))))) return false;
        return java.lang.Math.abs(((_t26) * (_t55) - (_t29 * _t54))) <= ((oHsX) * (_t52) + (oHsY * _t51)) + ((_ld6) * ((java.lang.Math.abs(_t29) + 1.0E-8)) + (_ld10 * _t47));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_2_fma(double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _t0, double _t1, double _t2, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47) {
        if (!(java.lang.Math.abs(_t54) <= java.lang.Math.fma(oHsX, _t45, java.lang.Math.fma(oHsY, _t46, java.lang.Math.fma(oHsZ, _t47, _ld6))))) return false;
        double _t55 = java.lang.Math.fma(_ld7, _t0, java.lang.Math.fma(_ld8, _t1, _ld9 * _t2));
        double _t27 = java.lang.Math.fma(oUXz, _ld7, java.lang.Math.fma(oUXx, _ld8, oUXy * _ld9));
        double _t48 = java.lang.Math.abs(_t27) + 1.0E-8;
        double _t28 = java.lang.Math.fma(oUYz, _ld7, java.lang.Math.fma(oUYx, _ld8, oUYy * _ld9));
        double _t49 = java.lang.Math.abs(_t28) + 1.0E-8;
        double _t29 = java.lang.Math.fma(oUZz, _ld7, java.lang.Math.fma(oUZx, _ld8, oUZy * _ld9));
        double _t50 = java.lang.Math.abs(_t29) + 1.0E-8;
        if (!(java.lang.Math.abs(_t55) <= java.lang.Math.fma(oHsX, _t48, java.lang.Math.fma(oHsY, _t49, java.lang.Math.fma(oHsZ, _t50, _ld10))))) return false;
        double _t30 = java.lang.Math.fma(oUXz, _ld11, java.lang.Math.fma(oUXx, _ld12, oUXy * _ld13));
        return intersectsOBB_sd948180f_3_fma(oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _ld6, _ld10, _ld11, _ld12, _ld13, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, java.lang.Math.fma(_ld11, _t0, java.lang.Math.fma(_ld12, _t1, _ld13 * _t2)), _t30, java.lang.Math.abs(_t30) + 1.0E-8, java.lang.Math.fma(oUYz, _ld11, java.lang.Math.fma(oUYx, _ld12, oUYy * _ld13)));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_2_mulAdd(double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _t0, double _t1, double _t2, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47) {
        if (!(java.lang.Math.abs(_t54) <= ((oHsX) * (_t45) + (((oHsY) * (_t46) + (((oHsZ) * (_t47) + (_ld6)))))))) return false;
        double _t55 = ((_ld7) * (_t0) + (((_ld8) * (_t1) + (_ld9 * _t2))));
        double _t27 = ((oUXz) * (_ld7) + (((oUXx) * (_ld8) + (oUXy * _ld9))));
        double _t48 = java.lang.Math.abs(_t27) + 1.0E-8;
        double _t28 = ((oUYz) * (_ld7) + (((oUYx) * (_ld8) + (oUYy * _ld9))));
        double _t49 = java.lang.Math.abs(_t28) + 1.0E-8;
        double _t29 = ((oUZz) * (_ld7) + (((oUZx) * (_ld8) + (oUZy * _ld9))));
        double _t50 = java.lang.Math.abs(_t29) + 1.0E-8;
        if (!(java.lang.Math.abs(_t55) <= ((oHsX) * (_t48) + (((oHsY) * (_t49) + (((oHsZ) * (_t50) + (_ld10)))))))) return false;
        double _t30 = ((oUXz) * (_ld11) + (((oUXx) * (_ld12) + (oUXy * _ld13))));
        return intersectsOBB_sd948180f_3_mulAdd(oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _ld6, _ld10, _ld11, _ld12, _ld13, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, ((_ld11) * (_t0) + (((_ld12) * (_t1) + (_ld13 * _t2)))), _t30, java.lang.Math.abs(_t30) + 1.0E-8, ((oUYz) * (_ld11) + (((oUYx) * (_ld12) + (oUYy * _ld13)))));
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_3_fma(double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double _ld6, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31) {
        double _t52 = java.lang.Math.abs(_t31) + 1.0E-8;
        double _t32 = java.lang.Math.fma(oUZz, _ld11, java.lang.Math.fma(oUZx, _ld12, oUZy * _ld13));
        double _t53 = java.lang.Math.abs(_t32) + 1.0E-8;
        if (!(java.lang.Math.abs(_t56) <= java.lang.Math.fma(oHsX, _t51, java.lang.Math.fma(oHsY, _t52, java.lang.Math.fma(oHsZ, _t53, _ld14))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t56, java.lang.Math.fma(_t24, _t54, _t27 * _t55))) <= java.lang.Math.fma(_ld6, _t45, java.lang.Math.fma(_ld10, _t48, java.lang.Math.fma(_ld14, _t51, oHsX))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t56, java.lang.Math.fma(_t25, _t54, _t28 * _t55))) <= java.lang.Math.fma(_ld6, _t46, java.lang.Math.fma(_ld10, _t49, java.lang.Math.fma(_ld14, _t52, oHsY))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t56, java.lang.Math.fma(_t26, _t54, _t29 * _t55))) <= java.lang.Math.fma(_ld6, _t47, java.lang.Math.fma(_ld10, _t50, java.lang.Math.fma(_ld14, _t53, oHsZ))))) return false;
        return intersectsOBB_sd948180f_4_fma(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_3_mulAdd(double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double _ld6, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31) {
        double _t52 = java.lang.Math.abs(_t31) + 1.0E-8;
        double _t32 = ((oUZz) * (_ld11) + (((oUZx) * (_ld12) + (oUZy * _ld13))));
        double _t53 = java.lang.Math.abs(_t32) + 1.0E-8;
        if (!(java.lang.Math.abs(_t56) <= ((oHsX) * (_t51) + (((oHsY) * (_t52) + (((oHsZ) * (_t53) + (_ld14)))))))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t56) + (((_t24) * (_t54) + (_t27 * _t55))))) <= ((_ld6) * (_t45) + (((_ld10) * (_t48) + (((_ld14) * (_t51) + (oHsX)))))))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t56) + (((_t25) * (_t54) + (_t28 * _t55))))) <= ((_ld6) * (_t46) + (((_ld10) * (_t49) + (((_ld14) * (_t52) + (oHsY)))))))) return false;
        if (!(java.lang.Math.abs(((_t32) * (_t56) + (((_t26) * (_t54) + (_t29 * _t55))))) <= ((_ld6) * (_t47) + (((_ld10) * (_t50) + (((_ld14) * (_t53) + (oHsZ)))))))) return false;
        return intersectsOBB_sd948180f_4_mulAdd(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_4_fma(double oHsX, double oHsY, double oHsZ, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t27, _t56, -(_t30 * _t55))) <= java.lang.Math.fma(oHsY, _t47, oHsZ * _t46) + java.lang.Math.fma(_ld10, _t51, _ld14 * _t48))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t28, _t56, -(_t31 * _t55))) <= java.lang.Math.fma(oHsX, _t47, oHsZ * _t45) + java.lang.Math.fma(_ld10, _t52, _ld14 * _t49))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t29, _t56, -(_t32 * _t55))) <= java.lang.Math.fma(oHsX, _t46, oHsY * _t45) + java.lang.Math.fma(_ld10, _t53, _ld14 * _t50))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t54, -(_t24 * _t56))) <= java.lang.Math.fma(oHsY, _t50, oHsZ * _t49) + java.lang.Math.fma(_ld6, _t51, _ld14 * _t45))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t54, -(_t25 * _t56))) <= java.lang.Math.fma(oHsX, _t50, oHsZ * _t48) + java.lang.Math.fma(_ld6, _t52, _ld14 * _t46))) return false;
        return intersectsOBB_sd948180f_1_fma(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t25, _t26, _t55, _t27, _t28, _t29, _t56, _t30, _t31, _t32);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_4_mulAdd(double oHsX, double oHsY, double oHsZ, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(java.lang.Math.abs(((_t27) * (_t56) - (_t30 * _t55))) <= ((oHsY) * (_t47) + (oHsZ * _t46)) + ((_ld10) * (_t51) + (_ld14 * _t48)))) return false;
        if (!(java.lang.Math.abs(((_t28) * (_t56) - (_t31 * _t55))) <= ((oHsX) * (_t47) + (oHsZ * _t45)) + ((_ld10) * (_t52) + (_ld14 * _t49)))) return false;
        if (!(java.lang.Math.abs(((_t29) * (_t56) - (_t32 * _t55))) <= ((oHsX) * (_t46) + (oHsY * _t45)) + ((_ld10) * (_t53) + (_ld14 * _t50)))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t54) - (_t24 * _t56))) <= ((oHsY) * (_t50) + (oHsZ * _t49)) + ((_ld6) * (_t51) + (_ld14 * _t45)))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t54) - (_t25 * _t56))) <= ((oHsX) * (_t50) + (oHsZ * _t48)) + ((_ld6) * (_t52) + (_ld14 * _t46)))) return false;
        return intersectsOBB_sd948180f_1_mulAdd(oHsX, oHsY, oHsZ, _ld6, _ld10, _ld14, _t54, _t24, _t25, _t26, _t55, _t27, _t28, _t29, _t56, _t30, _t31, _t32);
    }

    /**
     * Determine whether this oriented bounding box intersects {@code o}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative; the axes of {@code o} must
     * be orthonormal; each component of the half sizes of {@code o} must not be negative;
     * {@code epsilon} must not be negative.
     *
     * @param o the oriented bounding box to test for intersection
     * @param epsilon the amount added to each absolute term of the relative rotation, so that
     *        (near) parallel edges cannot degenerate the cross-product axes; a larger value makes
     *        the test more conservative; the overload without it uses {@code 1e-8}
     * @return {@code true} if this oriented bounding box intersects {@code o}, {@code false}
     *        otherwise
     */
    public boolean intersectsOBB(DoubleOBBR o, double epsilon) {
        double[] oData = ((DoubleOBBImpl) o).data;
        double oCX = oData[0];
        double oCY = oData[1];
        double oCZ = oData[2];
        double oUXx = oData[3];
        double oUXy = oData[4];
        double oUXz = oData[5];
        double oUYx = oData[6];
        double oUYy = oData[7];
        double oUYz = oData[8];
        double oUZx = oData[9];
        double oUZy = oData[10];
        double oUZz = oData[11];
        double oHsX = oData[12];
        double oHsY = oData[13];
        double oHsZ = oData[14];
        if (org.joml2.internal.FmaSwitch.ON) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
    }

    /**
     * Determine whether this oriented bounding box intersects ({@code oCX}, {@code oCY},
     * {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     * {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     * {@code oHsZ}).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; each component of
     * the half sizes of this oriented bounding box must not be negative; the axes of
     * {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * must be orthonormal; each component of {@code (oHsX, oHsY, oHsZ)} must not be negative;
     * {@code epsilon} must not be negative.
     *
     * @param oCX the {@code cX} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oCY the {@code cY} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oCZ the {@code cZ} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXx the {@code uXx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXy the {@code uXy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUXz the {@code uXz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYx the {@code uYx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYy the {@code uYy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUYz the {@code uYz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZx the {@code uZx} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZy the {@code uZy} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oUZz the {@code uZz} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsX the {@code hsX} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsY the {@code hsY} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param oHsZ the {@code hsZ} component of the oriented bounding box
     *        {@code (oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ)}
     * @param epsilon the amount added to each absolute term of the relative rotation, so that
     *        (near) parallel edges cannot degenerate the cross-product axes; a larger value makes
     *        the test more conservative; the overload without it uses {@code 1e-8}
     * @return {@code true} if this oriented bounding box intersects ({@code oCX}, {@code oCY},
     *        {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     *        {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     *        {@code oHsZ}), {@code false} otherwise
     */
    public boolean intersectsOBB(double oCX, double oCY, double oCZ, double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
    }

    /** {@code intersectsOBB} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_fma(double oCX, double oCY, double oCZ, double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double epsilon) {
        double[] sd = this.data;
        double _ld3 = sd[5];
        double _ld4 = sd[3];
        double _ld5 = sd[4];
        double _ld6 = sd[12];
        double _t0 = oCZ - sd[2];
        double _t1 = oCX - sd[0];
        double _t2 = oCY - sd[1];
        double _t54 = java.lang.Math.fma(_ld3, _t0, java.lang.Math.fma(_ld4, _t1, _ld5 * _t2));
        double _t24 = java.lang.Math.fma(oUXz, _ld3, java.lang.Math.fma(oUXx, _ld4, oUXy * _ld5));
        double _t45 = epsilon + java.lang.Math.abs(_t24);
        double _t25 = java.lang.Math.fma(oUYz, _ld3, java.lang.Math.fma(oUYx, _ld4, oUYy * _ld5));
        double _t46 = epsilon + java.lang.Math.abs(_t25);
        double _t26 = java.lang.Math.fma(oUZz, _ld3, java.lang.Math.fma(oUZx, _ld4, oUZy * _ld5));
        double _t47 = epsilon + java.lang.Math.abs(_t26);
        if (!(java.lang.Math.abs(_t54) <= java.lang.Math.fma(oHsX, _t45, java.lang.Math.fma(oHsY, _t46, java.lang.Math.fma(oHsZ, _t47, _ld6))))) return false;
        return intersectsOBB_s1b0dc82c_2_fma(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon, _ld6, sd[8], sd[6], sd[7], sd[13], sd[11], sd[9], sd[10], sd[14], _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47);
    }

    /** {@code intersectsOBB} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_mulAdd(double oCX, double oCY, double oCZ, double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double epsilon) {
        double[] sd = this.data;
        double _ld3 = sd[5];
        double _ld4 = sd[3];
        double _ld5 = sd[4];
        double _ld6 = sd[12];
        double _t0 = oCZ - sd[2];
        double _t1 = oCX - sd[0];
        double _t2 = oCY - sd[1];
        double _t54 = ((_ld3) * (_t0) + (((_ld4) * (_t1) + (_ld5 * _t2))));
        double _t24 = ((oUXz) * (_ld3) + (((oUXx) * (_ld4) + (oUXy * _ld5))));
        double _t45 = epsilon + java.lang.Math.abs(_t24);
        double _t25 = ((oUYz) * (_ld3) + (((oUYx) * (_ld4) + (oUYy * _ld5))));
        double _t46 = epsilon + java.lang.Math.abs(_t25);
        double _t26 = ((oUZz) * (_ld3) + (((oUZx) * (_ld4) + (oUZy * _ld5))));
        double _t47 = epsilon + java.lang.Math.abs(_t26);
        if (!(java.lang.Math.abs(_t54) <= ((oHsX) * (_t45) + (((oHsY) * (_t46) + (((oHsZ) * (_t47) + (_ld6)))))))) return false;
        return intersectsOBB_s1b0dc82c_2_mulAdd(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon, _ld6, sd[8], sd[6], sd[7], sd[13], sd[11], sd[9], sd[10], sd[14], _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47);
    }

    /** Part 1 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_1_fma(double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t25, double _t26, double _t55, double _t27, double _t28, double _t29, double _t56, double _t30, double _t31, double _t32) {
        double _t47 = epsilon + java.lang.Math.abs(_t26);
        double _t48 = epsilon + java.lang.Math.abs(_t27);
        double _t49 = epsilon + java.lang.Math.abs(_t28);
        double _t51 = epsilon + java.lang.Math.abs(_t30);
        double _t52 = epsilon + java.lang.Math.abs(_t31);
        double _t53 = epsilon + java.lang.Math.abs(_t32);
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t54, -(_t26 * _t56))) <= java.lang.Math.fma(oHsX, _t49, oHsY * _t48) + java.lang.Math.fma(_ld6, _t53, _ld14 * _t47))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t24, _t55, -(_t27 * _t54))) <= java.lang.Math.fma(oHsY, _t53, oHsZ * _t52) + java.lang.Math.fma(_ld6, _t48, _ld10 * (epsilon + java.lang.Math.abs(_t24))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t25, _t55, -(_t28 * _t54))) <= java.lang.Math.fma(oHsX, _t53, oHsZ * _t51) + java.lang.Math.fma(_ld6, _t49, _ld10 * (epsilon + java.lang.Math.abs(_t25))))) return false;
        return java.lang.Math.abs(java.lang.Math.fma(_t26, _t55, -(_t29 * _t54))) <= java.lang.Math.fma(oHsX, _t52, oHsY * _t51) + java.lang.Math.fma(_ld6, (epsilon + java.lang.Math.abs(_t29)), _ld10 * _t47);
    }

    /** Part 1 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_1_mulAdd(double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t25, double _t26, double _t55, double _t27, double _t28, double _t29, double _t56, double _t30, double _t31, double _t32) {
        double _t47 = epsilon + java.lang.Math.abs(_t26);
        double _t48 = epsilon + java.lang.Math.abs(_t27);
        double _t49 = epsilon + java.lang.Math.abs(_t28);
        double _t51 = epsilon + java.lang.Math.abs(_t30);
        double _t52 = epsilon + java.lang.Math.abs(_t31);
        double _t53 = epsilon + java.lang.Math.abs(_t32);
        if (!(java.lang.Math.abs(((_t32) * (_t54) - (_t26 * _t56))) <= ((oHsX) * (_t49) + (oHsY * _t48)) + ((_ld6) * (_t53) + (_ld14 * _t47)))) return false;
        if (!(java.lang.Math.abs(((_t24) * (_t55) - (_t27 * _t54))) <= ((oHsY) * (_t53) + (oHsZ * _t52)) + ((_ld6) * (_t48) + (_ld10 * (epsilon + java.lang.Math.abs(_t24)))))) return false;
        if (!(java.lang.Math.abs(((_t25) * (_t55) - (_t28 * _t54))) <= ((oHsX) * (_t53) + (oHsZ * _t51)) + ((_ld6) * (_t49) + (_ld10 * (epsilon + java.lang.Math.abs(_t25)))))) return false;
        return java.lang.Math.abs(((_t26) * (_t55) - (_t29 * _t54))) <= ((oHsX) * (_t52) + (oHsY * _t51)) + ((_ld6) * ((epsilon + java.lang.Math.abs(_t29))) + (_ld10 * _t47));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_2_fma(double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _t0, double _t1, double _t2, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47) {
        double _t55 = java.lang.Math.fma(_ld7, _t0, java.lang.Math.fma(_ld8, _t1, _ld9 * _t2));
        double _t27 = java.lang.Math.fma(oUXz, _ld7, java.lang.Math.fma(oUXx, _ld8, oUXy * _ld9));
        double _t48 = epsilon + java.lang.Math.abs(_t27);
        double _t28 = java.lang.Math.fma(oUYz, _ld7, java.lang.Math.fma(oUYx, _ld8, oUYy * _ld9));
        double _t49 = epsilon + java.lang.Math.abs(_t28);
        double _t29 = java.lang.Math.fma(oUZz, _ld7, java.lang.Math.fma(oUZx, _ld8, oUZy * _ld9));
        double _t50 = epsilon + java.lang.Math.abs(_t29);
        if (!(java.lang.Math.abs(_t55) <= java.lang.Math.fma(oHsX, _t48, java.lang.Math.fma(oHsY, _t49, java.lang.Math.fma(oHsZ, _t50, _ld10))))) return false;
        double _t30 = java.lang.Math.fma(oUXz, _ld11, java.lang.Math.fma(oUXx, _ld12, oUXy * _ld13));
        double _t31 = java.lang.Math.fma(oUYz, _ld11, java.lang.Math.fma(oUYx, _ld12, oUYy * _ld13));
        double _t32 = java.lang.Math.fma(oUZz, _ld11, java.lang.Math.fma(oUZx, _ld12, oUZy * _ld13));
        return intersectsOBB_s1b0dc82c_3_fma(oHsX, oHsY, oHsZ, epsilon, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, java.lang.Math.fma(_ld11, _t0, java.lang.Math.fma(_ld12, _t1, _ld13 * _t2)), _t30, epsilon + java.lang.Math.abs(_t30), _t31, epsilon + java.lang.Math.abs(_t31), _t32, epsilon + java.lang.Math.abs(_t32));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_2_mulAdd(double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld7, double _ld8, double _ld9, double _ld10, double _ld11, double _ld12, double _ld13, double _ld14, double _t0, double _t1, double _t2, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47) {
        double _t55 = ((_ld7) * (_t0) + (((_ld8) * (_t1) + (_ld9 * _t2))));
        double _t27 = ((oUXz) * (_ld7) + (((oUXx) * (_ld8) + (oUXy * _ld9))));
        double _t48 = epsilon + java.lang.Math.abs(_t27);
        double _t28 = ((oUYz) * (_ld7) + (((oUYx) * (_ld8) + (oUYy * _ld9))));
        double _t49 = epsilon + java.lang.Math.abs(_t28);
        double _t29 = ((oUZz) * (_ld7) + (((oUZx) * (_ld8) + (oUZy * _ld9))));
        double _t50 = epsilon + java.lang.Math.abs(_t29);
        if (!(java.lang.Math.abs(_t55) <= ((oHsX) * (_t48) + (((oHsY) * (_t49) + (((oHsZ) * (_t50) + (_ld10)))))))) return false;
        double _t30 = ((oUXz) * (_ld11) + (((oUXx) * (_ld12) + (oUXy * _ld13))));
        double _t31 = ((oUYz) * (_ld11) + (((oUYx) * (_ld12) + (oUYy * _ld13))));
        double _t32 = ((oUZz) * (_ld11) + (((oUZx) * (_ld12) + (oUZy * _ld13))));
        return intersectsOBB_s1b0dc82c_3_mulAdd(oHsX, oHsY, oHsZ, epsilon, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, ((_ld11) * (_t0) + (((_ld12) * (_t1) + (_ld13 * _t2)))), _t30, epsilon + java.lang.Math.abs(_t30), _t31, epsilon + java.lang.Math.abs(_t31), _t32, epsilon + java.lang.Math.abs(_t32));
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_3_fma(double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(java.lang.Math.abs(_t56) <= java.lang.Math.fma(oHsX, _t51, java.lang.Math.fma(oHsY, _t52, java.lang.Math.fma(oHsZ, _t53, _ld14))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t56, java.lang.Math.fma(_t24, _t54, _t27 * _t55))) <= java.lang.Math.fma(_ld6, _t45, java.lang.Math.fma(_ld10, _t48, java.lang.Math.fma(_ld14, _t51, oHsX))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t56, java.lang.Math.fma(_t25, _t54, _t28 * _t55))) <= java.lang.Math.fma(_ld6, _t46, java.lang.Math.fma(_ld10, _t49, java.lang.Math.fma(_ld14, _t52, oHsY))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t32, _t56, java.lang.Math.fma(_t26, _t54, _t29 * _t55))) <= java.lang.Math.fma(_ld6, _t47, java.lang.Math.fma(_ld10, _t50, java.lang.Math.fma(_ld14, _t53, oHsZ))))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t27, _t56, -(_t30 * _t55))) <= java.lang.Math.fma(oHsY, _t47, oHsZ * _t46) + java.lang.Math.fma(_ld10, _t51, _ld14 * _t48))) return false;
        return intersectsOBB_s1b0dc82c_4_fma(oHsX, oHsY, oHsZ, epsilon, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_3_mulAdd(double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(java.lang.Math.abs(_t56) <= ((oHsX) * (_t51) + (((oHsY) * (_t52) + (((oHsZ) * (_t53) + (_ld14)))))))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t56) + (((_t24) * (_t54) + (_t27 * _t55))))) <= ((_ld6) * (_t45) + (((_ld10) * (_t48) + (((_ld14) * (_t51) + (oHsX)))))))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t56) + (((_t25) * (_t54) + (_t28 * _t55))))) <= ((_ld6) * (_t46) + (((_ld10) * (_t49) + (((_ld14) * (_t52) + (oHsY)))))))) return false;
        if (!(java.lang.Math.abs(((_t32) * (_t56) + (((_t26) * (_t54) + (_t29 * _t55))))) <= ((_ld6) * (_t47) + (((_ld10) * (_t50) + (((_ld14) * (_t53) + (oHsZ)))))))) return false;
        if (!(java.lang.Math.abs(((_t27) * (_t56) - (_t30 * _t55))) <= ((oHsY) * (_t47) + (oHsZ * _t46)) + ((_ld10) * (_t51) + (_ld14 * _t48)))) return false;
        return intersectsOBB_s1b0dc82c_4_mulAdd(oHsX, oHsY, oHsZ, epsilon, _ld6, _ld10, _ld14, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_4_fma(double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t28, _t56, -(_t31 * _t55))) <= java.lang.Math.fma(oHsX, _t47, oHsZ * _t45) + java.lang.Math.fma(_ld10, _t52, _ld14 * _t49))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t29, _t56, -(_t32 * _t55))) <= java.lang.Math.fma(oHsX, _t46, oHsY * _t45) + java.lang.Math.fma(_ld10, _t53, _ld14 * _t50))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t30, _t54, -(_t24 * _t56))) <= java.lang.Math.fma(oHsY, _t50, oHsZ * _t49) + java.lang.Math.fma(_ld6, _t51, _ld14 * _t45))) return false;
        if (!(java.lang.Math.abs(java.lang.Math.fma(_t31, _t54, -(_t25 * _t56))) <= java.lang.Math.fma(oHsX, _t50, oHsZ * _t48) + java.lang.Math.fma(_ld6, _t52, _ld14 * _t46))) return false;
        return intersectsOBB_s1b0dc82c_1_fma(oHsX, oHsY, oHsZ, epsilon, _ld6, _ld10, _ld14, _t54, _t24, _t25, _t26, _t55, _t27, _t28, _t29, _t56, _t30, _t31, _t32);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_s1b0dc82c_4_mulAdd(double oHsX, double oHsY, double oHsZ, double epsilon, double _ld6, double _ld10, double _ld14, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(java.lang.Math.abs(((_t28) * (_t56) - (_t31 * _t55))) <= ((oHsX) * (_t47) + (oHsZ * _t45)) + ((_ld10) * (_t52) + (_ld14 * _t49)))) return false;
        if (!(java.lang.Math.abs(((_t29) * (_t56) - (_t32 * _t55))) <= ((oHsX) * (_t46) + (oHsY * _t45)) + ((_ld10) * (_t53) + (_ld14 * _t50)))) return false;
        if (!(java.lang.Math.abs(((_t30) * (_t54) - (_t24 * _t56))) <= ((oHsY) * (_t50) + (oHsZ * _t49)) + ((_ld6) * (_t51) + (_ld14 * _t45)))) return false;
        if (!(java.lang.Math.abs(((_t31) * (_t54) - (_t25 * _t56))) <= ((oHsX) * (_t50) + (oHsZ * _t48)) + ((_ld6) * (_t52) + (_ld14 * _t46)))) return false;
        return intersectsOBB_s1b0dc82c_1_mulAdd(oHsX, oHsY, oHsZ, epsilon, _ld6, _ld10, _ld14, _t54, _t24, _t25, _t26, _t55, _t27, _t28, _t29, _t56, _t30, _t31, _t32);
    }

    /**
     * Determine whether this oriented bounding box is valid, i.e. none of its half extents is
     * negative.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this oriented bounding box is valid, i.e. none of its half extents is
     *        negative, {@code false} otherwise
     */
    public boolean isValid() {
        double[] sd = this.data;
        if (!(sd[12] >= 0.0)) return false;
        if (!(sd[13] >= 0.0)) return false;
        return sd[14] >= 0.0;
    }

    public double cX() { return data[0]; }
    public double cY() { return data[1]; }
    public double cZ() { return data[2]; }
    public double uXx() { return data[3]; }
    public double uXy() { return data[4]; }
    public double uXz() { return data[5]; }
    public double uYx() { return data[6]; }
    public double uYy() { return data[7]; }
    public double uYz() { return data[8]; }
    public double uZx() { return data[9]; }
    public double uZy() { return data[10]; }
    public double uZz() { return data[11]; }
    public double hsX() { return data[12]; }
    public double hsY() { return data[13]; }
    public double hsZ() { return data[14]; }

    @Override public String toString() {
        return "DoubleOBB(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ", " + this.data[3] + ", " + this.data[4] + ", " + this.data[5] + ", " + this.data[6] + ", " + this.data[7] + ", " + this.data[8] + ", " + this.data[9] + ", " + this.data[10] + ", " + this.data[11] + ", " + this.data[12] + ", " + this.data[13] + ", " + this.data[14] + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleOBBImpl)) return false;
        DoubleOBBImpl o = (DoubleOBBImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3])
            && Double.isFinite(data[4])
            && Double.isFinite(data[5])
            && Double.isFinite(data[6])
            && Double.isFinite(data[7])
            && Double.isFinite(data[8])
            && Double.isFinite(data[9])
            && Double.isFinite(data[10])
            && Double.isFinite(data[11])
            && Double.isFinite(data[12])
            && Double.isFinite(data[13])
            && Double.isFinite(data[14]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6])
            || Double.isNaN(data[7])
            || Double.isNaN(data[8])
            || Double.isNaN(data[9])
            || Double.isNaN(data[10])
            || Double.isNaN(data[11])
            || Double.isNaN(data[12])
            || Double.isNaN(data[13])
            || Double.isNaN(data[14]);
    }

    @Override public boolean equalsEpsilon(DoubleOBBR other, double epsilon) {
        double[] otherData = ((DoubleOBBImpl) other).data;
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon
            && java.lang.Math.abs(sd[6] - otherData[6]) <= epsilon
            && java.lang.Math.abs(sd[7] - otherData[7]) <= epsilon
            && java.lang.Math.abs(sd[8] - otherData[8]) <= epsilon
            && java.lang.Math.abs(sd[9] - otherData[9]) <= epsilon
            && java.lang.Math.abs(sd[10] - otherData[10]) <= epsilon
            && java.lang.Math.abs(sd[11] - otherData[11]) <= epsilon
            && java.lang.Math.abs(sd[12] - otherData[12]) <= epsilon
            && java.lang.Math.abs(sd[13] - otherData[13]) <= epsilon
            && java.lang.Math.abs(sd[14] - otherData[14]) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        dest[offset + 9] = this.data[9];
        dest[offset + 10] = this.data[10];
        dest[offset + 11] = this.data[11];
        dest[offset + 12] = this.data[12];
        dest[offset + 13] = this.data[13];
        dest[offset + 14] = this.data[14];
        return dest;
    }

    @Mutated public DoubleOBB load(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.data[9] = src[offset + 9];
        this.data[10] = src[offset + 10];
        this.data[11] = src[offset + 11];
        this.data[12] = src[offset + 12];
        this.data[13] = src[offset + 13];
        this.data[14] = src[offset + 14];
        return this;
    }

    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return buf;
    }

    @Mutated public DoubleOBB load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleOBB loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public DoubleOBB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return this;
    }

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 120);
        return buf;
    }

    @Mutated public DoubleOBB load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleOBB loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public DoubleOBB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 120);
        return this;
    }

    public DoubleOBB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public DoubleOBB loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    @Mutated public DoubleOBB load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }

    @Mutated public DoubleOBB load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        dest[offset + 6] = (float) this.data[6];
        dest[offset + 7] = (float) this.data[7];
        dest[offset + 8] = (float) this.data[8];
        dest[offset + 9] = (float) this.data[9];
        dest[offset + 10] = (float) this.data[10];
        dest[offset + 11] = (float) this.data[11];
        dest[offset + 12] = (float) this.data[12];
        dest[offset + 13] = (float) this.data[13];
        dest[offset + 14] = (float) this.data[14];
        return dest;
    }

    @Mutated public DoubleOBB load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.data[9] = src[offset + 9];
        this.data[10] = src[offset + 10];
        this.data[11] = src[offset + 11];
        this.data[12] = src[offset + 12];
        this.data[13] = src[offset + 13];
        this.data[14] = src[offset + 14];
        return this;
    }

    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return buf;
    }

    @Mutated public DoubleOBB load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleOBB loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public DoubleOBB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 15);
        return this;
    }

    public ByteBuffer storeFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 60);
        return buf;
    }

    @Mutated public DoubleOBB loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleOBB loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }

    @Mutated public DoubleOBB loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 60);
        return this;
    }

    public DoubleOBB storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    @Mutated public DoubleOBB loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }

    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    public MemorySegment storeFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    @Mutated public DoubleOBB loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }

    @Mutated public DoubleOBB loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final DoubleVector VEC_0 = DoubleVector.fromArray(COL_SPECIES, new double[] {1.0, 0.0, 0.0, 0.0}, 0);
    private static final double[] DATA_1 = new double[] {0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};

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

    /** The column species is wider than this machine's vector unit: take the scalar twins. */
    private static final boolean COL_NARROW = COL_SPECIES.length() > DoubleVector.SPECIES_PREFERRED.length();

    private DoubleOBB setCenter_narrow(Double3R c, @Mutated DoubleOBB dest) {
        double[] cData = ((Double3Impl) c).data;
        double cY = cData[1];
        double cZ = cData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = cData[0];
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    private DoubleOBB setCenter_narrow(double cX, double cY, double cZ, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    private DoubleOBB setHalfSize_narrow(Double3R h, @Mutated DoubleOBB dest) {
        double[] hData = ((Double3Impl) h).data;
        double hX = hData[0];
        double hY = hData[1];
        double hZ = hData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }

    private DoubleOBB setHalfSize_narrow(double hX, double hY, double hZ, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = hX;
        dd[13] = hY;
        dd[14] = hZ;
        return dest;
    }

    private DoubleOBB setIdentityOrientation_narrow(@Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 1.0;
        dd[8] = 0.0;
        dd[9] = 0.0;
        dd[10] = 0.0;
        dd[11] = 1.0;
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    private DoubleOBB translate_narrow(Double3R delta, @Mutated DoubleOBB dest) {
        double[] deltaData = ((Double3Impl) delta).data;
        double deltaY = deltaData[1];
        double deltaZ = deltaData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = deltaData[0] + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }

    private DoubleOBB translate_narrow(double deltaX, double deltaY, double deltaZ, @Mutated DoubleOBB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleOBBImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        dd[12] = sd[12];
        dd[13] = sd[13];
        dd[14] = sd[14];
        return dest;
    }
}
