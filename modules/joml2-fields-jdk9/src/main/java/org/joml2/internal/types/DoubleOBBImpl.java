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
 * Generated implementation of {@link DoubleOBB} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleOBBImpl implements DoubleOBB {

    public double cX;
    public double cY;
    public double cZ;
    public double uXx;
    public double uXy;
    public double uXz;
    public double uYx;
    public double uYy;
    public double uYz;
    public double uZx;
    public double uZy;
    public double uZz;
    public double hsX;
    public double hsY;
    public double hsZ;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleOBBBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleOBBBbOpsUnsafe()
                        : new DoubleOBBBbOpsApi();
        static final DoubleOBBRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleOBBRawOpsUnsafe()
                        : new DoubleOBBRawOpsApi();
    }

    public DoubleOBBImpl() {
        uXx = 1;
        uYy = 1;
        uZz = 1;
    }

    public DoubleOBBImpl(double cX, double cY, double cZ, double uXx, double uXy, double uXz, double uYx, double uYy, double uYz, double uZx, double uZy, double uZz, double hsX, double hsY, double hsZ) {
        this.cX = cX;
        this.cY = cY;
        this.cZ = cZ;
        this.uXx = uXx;
        this.uXy = uXy;
        this.uXz = uXz;
        this.uYx = uYx;
        this.uYy = uYy;
        this.uYz = uYz;
        this.uZx = uZx;
        this.uZy = uZy;
        this.uZz = uZz;
        this.hsX = hsX;
        this.hsY = hsY;
        this.hsZ = hsZ;
    }

    public DoubleOBBImpl(DoubleOBBR src) {
        this.cX = src.cX();
        this.cY = src.cY();
        this.cZ = src.cZ();
        this.uXx = src.uXx();
        this.uXy = src.uXy();
        this.uXz = src.uXz();
        this.uYx = src.uYx();
        this.uYy = src.uYy();
        this.uYz = src.uYz();
        this.uZx = src.uZx();
        this.uZy = src.uZy();
        this.uZz = src.uZz();
        this.hsX = src.hsX();
        this.hsY = src.hsY();
        this.hsZ = src.hsZ();
    }


    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return this
     */
    public @Mutated DoubleOBB set(DoubleOBBR v) {
        return set(v.cX(), v.cY(), v.cZ(), v.uXx(), v.uXy(), v.uXz(), v.uYx(), v.uYy(), v.uYz(), v.uZx(), v.uZy(), v.uZz(), v.hsX(), v.hsY(), v.hsZ());
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
        this.cX = vCX;
        this.cY = vCY;
        this.cZ = vCZ;
        this.uXx = vUXx;
        this.uXy = vUXy;
        this.uXz = vUXz;
        this.uYx = vUYx;
        this.uYy = vUYy;
        this.uYz = vUYz;
        this.uZx = vUZx;
        this.uZy = vUZy;
        this.uZz = vUZz;
        this.hsX = vHsX;
        this.hsY = vHsY;
        this.hsZ = vHsZ;
        return this;
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to {@code axisX}, {@code axisY}
     * and {@code axisZ} and store the result in {@code dest}.
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
        return setAxes(axisX.x(), axisX.y(), axisX.z(), axisY.x(), axisY.y(), axisY.z(), axisZ.x(), axisZ.y(), axisZ.z(), dest);
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to ({@code axisXX},
     * {@code axisXY}, {@code axisXZ}), ({@code axisYX}, {@code axisYY}, {@code axisYZ}) and
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}) and store the result in {@code dest}.
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = axisXX;
        d.uXy = axisXY;
        d.uXz = axisXZ;
        d.uYx = axisYX;
        d.uYy = axisYY;
        d.uYz = axisYZ;
        d.uZx = axisZX;
        d.uZy = axisZY;
        d.uZz = axisZZ;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        return setCenter(c.x(), c.y(), c.z(), dest);
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = cX;
        d.cY = cY;
        d.cZ = cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        return setHalfSize(h.x(), h.y(), h.z(), dest);
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = hX;
        d.hsY = hY;
        d.hsZ = hZ;
        return d;
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = 1.0;
        d.uXy = 0.0;
        d.uXz = 0.0;
        d.uYx = 0.0;
        d.uYy = 1.0;
        d.uYz = 0.0;
        d.uZx = 0.0;
        d.uZy = 0.0;
        d.uZz = 1.0;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        return setOrientation(q.x(), q.y(), q.z(), q.w(), dest);
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        d.cX = this.cX;
        d.cY = this.cY;
        d.cZ = this.cZ;
        d.uXx = Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0);
        d.uXy = 2.0 * Math.fma(qX, qY, _t1);
        d.uXz = 2.0 * Math.fma(qX, qZ, -_t2);
        d.uYx = 2.0 * Math.fma(qX, qY, -_t1);
        d.uYy = Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0);
        d.uYz = 2.0 * Math.fma(qX, qW, qY * qZ);
        d.uZx = 2.0 * Math.fma(qX, qZ, _t2);
        d.uZy = 2.0 * Math.fma(qY, qZ, -(qX * qW));
        d.uZz = Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0);
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        FloatOBBImpl d = (FloatOBBImpl) dest;
        d.cX = (float) (this.cX);
        d.cY = (float) (this.cY);
        d.cZ = (float) (this.cZ);
        d.uXx = (float) (this.uXx);
        d.uXy = (float) (this.uXy);
        d.uXz = (float) (this.uXz);
        d.uYx = (float) (this.uYx);
        d.uYy = (float) (this.uYy);
        d.uYz = (float) (this.uYz);
        d.uZx = (float) (this.uZx);
        d.uZy = (float) (this.uZy);
        d.uZz = (float) (this.uZz);
        d.hsX = (float) (this.hsX);
        d.hsY = (float) (this.hsY);
        d.hsZ = (float) (this.hsZ);
        return d;
    }


    /**
     * Set this oriented bounding box to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleOBB makeIdentity() {
        this.cX = 0.0;
        this.cY = 0.0;
        this.cZ = 0.0;
        this.uXx = 1.0;
        this.uXy = 0.0;
        this.uXz = 0.0;
        this.uYx = 0.0;
        this.uYy = 1.0;
        this.uYz = 0.0;
        this.uZx = 0.0;
        this.uZy = 0.0;
        this.uZz = 1.0;
        this.hsX = 0.0;
        this.hsY = 0.0;
        this.hsZ = 0.0;
        return this;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_scba2d91_c0(DoubleOBBImpl _dst, double _r2, double _r18, double _r4, double _r19, double _r0, double _r20, double _r21, double _r10, double _r11, double _r9, double _r22, double _r7, double _r8, double _r6, double _r23, double _t44) {
        _dst.cX = Math.fma(_r2, _r18, Math.fma(_r4, _r19, Math.fma(_r0, _r20, _r21)));
        _dst.cY = Math.fma(_r10, _r18, Math.fma(_r11, _r19, Math.fma(_r9, _r20, _r22)));
        _dst.cZ = Math.fma(_r7, _r18, Math.fma(_r8, _r19, Math.fma(_r6, _r20, _r23)));
        _dst.uXx = _t44;
    }

    /**
     * Private store group 3 of {@code transform}: computes and stores it. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_scba2d91_c3(DoubleOBBImpl _dst, double _r24, double _t24, double _t46, double _t25, double _t44, double _t26, double _t45, double _r25, double _t19, double _t18, double _t20, double _r26, double _t22, double _t21, double _t23, double _t64, double _t62, double _t63, double _t54, double _t55, double _t53) {
        _dst.hsX = Math.fma(_r24, Math.abs(Math.fma(_t24, _t46, Math.fma(_t25, _t44, _t26 * _t45))), Math.fma(_r25, Math.abs(Math.fma(_t19, _t46, Math.fma(_t18, _t44, _t20 * _t45))), _r26 * Math.abs(Math.fma(_t22, _t46, Math.fma(_t21, _t44, _t23 * _t45)))));
        _dst.hsY = Math.fma(_r24, Math.abs(Math.fma(_t24, _t64, Math.fma(_t25, _t62, _t26 * _t63))), Math.fma(_r25, Math.abs(Math.fma(_t19, _t64, Math.fma(_t18, _t62, _t20 * _t63))), _r26 * Math.abs(Math.fma(_t22, _t64, Math.fma(_t21, _t62, _t23 * _t63)))));
        _dst.hsZ = Math.fma(_r24, Math.abs(Math.fma(_t24, _t54, Math.fma(_t25, _t55, _t26 * _t53))), Math.fma(_r25, Math.abs(Math.fma(_t19, _t54, Math.fma(_t18, _t55, _t20 * _t53))), _r26 * Math.abs(Math.fma(_t22, _t54, Math.fma(_t21, _t55, _t23 * _t53)))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_scba2d91_tail(DoubleOBBImpl _dst, Double3x4R m, double _r6, double _r7, double _r8, double _r0, double _r2, double _r4, double _r9, double _r10, double _r11, double _t40, double _t18, double _t20, double _t19, double _t37, double _t52, double _t38, double _t39, double _t22, double _t21, double _t23) {
        double _r15 = this.uZz;
        double _r16 = this.uZx;
        double _r17 = this.uZy;
        double _r18 = this.cX;
        double _r19 = this.cY;
        double _r20 = this.cZ;
        double _r21 = m.m03();
        double _r22 = m.m13();
        double _r23 = m.m23();
        double _r24 = this.hsZ;
        double _r25 = this.hsX;
        double _r26 = this.hsY;
        double _t42 = (1.0 / Math.sqrt(_t40));
        transform_scba2d91_tail2(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, Math.fma(_r6, _r15, Math.fma(_r7, _r16, _r8 * _r17)), Math.fma(_r0, _r15, Math.fma(_r2, _r16, _r4 * _r17)), Math.fma(_r9, _r15, Math.fma(_r10, _r16, _r11 * _r17)), _r25, _t18, _r26, _t22, _t21, _t23);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_scba2d91_tail2(DoubleOBBImpl _dst, double _t20, double _t42, double _t19, double _t37, double _t52, double _t38, double _t39, double _t44, double _r2, double _r18, double _r4, double _r19, double _r0, double _r20, double _r21, double _r10, double _r11, double _r9, double _r22, double _r7, double _r8, double _r6, double _r23, double _r24, double _t24, double _t25, double _t26, double _r25, double _t18, double _r26, double _t22, double _t21, double _t23) {
        double _t45 = _t20 * _t42;
        double _t46 = _t19 * _t42;
        double _t53 = _t37 * _t52;
        double _t54 = _t38 * _t52;
        double _t55 = _t39 * _t52;
        double _t62 = Math.fma(_t46, _t53, -(_t45 * _t54));
        double _t63 = Math.fma(_t44, _t54, -(_t46 * _t55));
        double _t64 = Math.fma(_t45, _t55, -(_t44 * _t53));
        transform_scba2d91_c0(_dst, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _t44);
        _dst.uXy = _t45;
        _dst.uXz = _t46;
        _dst.uYx = _t62;
        _dst.uYy = _t63;
        _dst.uYz = _t64;
        _dst.uZx = _t55;
        _dst.uZy = _t53;
        _dst.uZz = _t54;
        transform_scba2d91_c3(_dst, _r24, _t24, _t46, _t25, _t44, _t26, _t45, _r25, _t19, _t18, _t20, _r26, _t22, _t21, _t23, _t64, _t62, _t63, _t54, _t55, _t53);
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleOBB transform(Double3x4R m, @Mutated DoubleOBB dest) {
        double _r0 = m.m02();
        double _r1 = this.uXz;
        double _r2 = m.m00();
        double _r3 = this.uXx;
        double _r4 = m.m01();
        double _r5 = this.uXy;
        double _r6 = m.m22();
        double _r7 = m.m20();
        double _r8 = m.m21();
        double _r9 = m.m12();
        double _r10 = m.m10();
        double _r11 = m.m11();
        double _r12 = this.uYz;
        double _r13 = this.uYx;
        double _r14 = this.uYy;
        double _t18 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5));
        double _t19 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5));
        double _t20 = Math.fma(_r9, _r1, Math.fma(_r10, _r3, _r11 * _r5));
        double _t21 = Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14));
        double _t22 = Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r8 * _r14));
        double _t23 = Math.fma(_r9, _r12, Math.fma(_r10, _r13, _r11 * _r14));
        return transform_sb93f445e_1(m, dest, (DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t21, _t19, -(_t18 * _t22)), Math.fma(_t18, _t23, -(_t21 * _t20)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_sb93f445e_1(Double3x4R m, DoubleOBB dest, DoubleOBBImpl d, double _r0, double _r2, double _r4, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t37, double _t38) {
        double _t39 = Math.fma(_t20, _t22, -(_t23 * _t19));
        double _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        double _t52 = Math.fma(_t38, _t38, Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_t52 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 5.048709793414476E-29, 2.2250738585072014E-308) && _t52 < Double.POSITIVE_INFINITY)) return transform_degenerate(m, dest);
        transform_scba2d91_tail(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0 / Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private void transform_degenerate_scba2d91_tail(DoubleOBBImpl _dst, Double3x4R m, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19) {
        double _r20 = this.cZ;
        double _r21 = m.m03();
        double _r22 = m.m13();
        double _r23 = m.m23();
        double _r24 = this.hsZ;
        double _r25 = this.hsX;
        double _r26 = this.hsY;
        transform_degenerate_scba2d91_tail2(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5)), Math.fma(_r9, _r1, Math.fma(_r10, _r3, _r11 * _r5)), Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5)), Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r8 * _r14)), Math.fma(_r9, _r12, Math.fma(_r10, _r13, _r11 * _r14)), Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14)), _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26);
    }

    /**
     * Private tail of {@code transform_degenerate}. Shared by 2 identical private paths of
     * {@code transform}; reached only through it.
     */
    private void transform_degenerate_scba2d91_tail2(DoubleOBBImpl _dst, double _r0, double _r15, double _r2, double _r16, double _r4, double _r17, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _t19, double _t20, double _t18, double _t22, double _t23, double _t21, double _r18, double _r19, double _r20, double _r21, double _r22, double _r23, double _r24, double _r25, double _r26) {
        double _t24 = Math.fma(_r0, _r15, Math.fma(_r2, _r16, _r4 * _r17));
        double _t25 = Math.fma(_r6, _r15, Math.fma(_r7, _r16, _r8 * _r17));
        double _t26 = Math.fma(_r9, _r15, Math.fma(_r10, _r16, _r11 * _r17));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        transform_degenerate_scba2d91_tail3(_dst, _t21 * _t28, _t22 * _t28, _t23 * _t28, _t24 * _t29, _t25 * _t29, _t26 * _t29, Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)), _t39, _t40, _t41, _r7, _r18, _r8, _r19, _r6, _r20, _r21, _r10, _r11, _r9, _r22, _r2, _r4, _r0, _r23, _r24, _t24, _t25, _t26, _r25, _t18, _t19, _t20, _r26, _t21, _t22, _t23);
    }

    /**
     * Private tail of {@code transform_degenerate}. Shared by 2 identical private paths of
     * {@code transform}; reached only through it.
     */
    private void transform_degenerate_scba2d91_tail3(DoubleOBBImpl _dst, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t39, double _t40, double _t41, double _r7, double _r18, double _r8, double _r19, double _r6, double _r20, double _r21, double _r10, double _r11, double _r9, double _r22, double _r2, double _r4, double _r0, double _r23, double _r24, double _t24, double _t25, double _t26, double _r25, double _t18, double _t19, double _t20, double _r26, double _t21, double _t22, double _t23) {
        double _t63, _t64, _t65;
        if (_t54 > 0.0) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)) > 0.0) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47)) > 0.0) {
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
        transform_degenerate_scba2d91_tail4(_dst, _t63, _t64, _t65, _t42, _t43, _t44, _t45, _t46, _t47, _t39, _t40, _t41, _r7, _r18, _r8, _r19, _r6, _r20, _r21, _r10, _r11, _r9, _r22, _r2, _r4, _r0, _r23, _r24, _t24, _t25, _t26, _r25, _t18, _t19, _t20, _r26, _t21, _t22, _t23);
    }

    /**
     * Private tail of {@code transform_degenerate}. Shared by 2 identical private paths of
     * {@code transform}; reached only through it.
     */
    private void transform_degenerate_scba2d91_tail4(DoubleOBBImpl _dst, double _t63, double _t64, double _t65, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t39, double _t40, double _t41, double _r7, double _r18, double _r8, double _r19, double _r6, double _r20, double _r21, double _r10, double _r11, double _r9, double _r22, double _r2, double _r4, double _r0, double _r23, double _r24, double _t24, double _t25, double _t26, double _r25, double _t18, double _t19, double _t20, double _r26, double _t21, double _t22, double _t23) {
        double _t69 = (1.0 / Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))));
        double _t70 = _t69 * _t64;
        double _t71 = _t69 * _t65;
        double _t72 = _t69 * _t63;
        double _t83, _t84, _t91;
        if (Math.abs(_t72) < Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        transform_degenerate_scba2d91_tail5(_dst, -Math.fma(_t72, _t42, Math.fma(_t70, _t43, _t71 * _t44)), _t72, _t42, _t70, _t43, _t71, _t44, -Math.fma(_t72, _t45, Math.fma(_t70, _t46, _t71 * _t47)), _t45, _t46, _t47, -Math.fma(_t72, _t39, Math.fma(_t70, _t40, _t71 * _t41)), _t39, _t40, _t41, _t84, _t91, _t83, _r7, _r18, _r8, _r19, _r6, _r20, _r21, _r10, _r11, _r9, _r22, _r2, _r4, _r0, _r23, _r24, _t24, _t25, _t26, _r25, _t18, _t19, _t20, _r26, _t21, _t22, _t23);
    }

    /**
     * Private tail of {@code transform_degenerate}. Shared by 2 identical private paths of
     * {@code transform}; reached only through it.
     */
    private void transform_degenerate_scba2d91_tail5(DoubleOBBImpl _dst, double _t88, double _t72, double _t42, double _t70, double _t43, double _t71, double _t44, double _t89, double _t45, double _t46, double _t47, double _t90, double _t39, double _t40, double _t41, double _t84, double _t91, double _t83, double _r7, double _r18, double _r8, double _r19, double _r6, double _r20, double _r21, double _r10, double _r11, double _r9, double _r22, double _r2, double _r4, double _r0, double _r23, double _r24, double _t24, double _t25, double _t26, double _r25, double _t18, double _t19, double _t20, double _r26, double _t21, double _t22, double _t23) {
        double _t92 = Math.fma(_t88, _t72, _t42);
        double _t93 = Math.fma(_t88, _t70, _t43);
        double _t94 = Math.fma(_t88, _t71, _t44);
        double _t95 = Math.fma(_t89, _t72, _t45);
        double _t96 = Math.fma(_t89, _t70, _t46);
        double _t97 = Math.fma(_t89, _t71, _t47);
        double _t98 = Math.fma(_t90, _t72, _t39);
        double _t99 = Math.fma(_t90, _t70, _t40);
        double _t100 = Math.fma(_t90, _t71, _t41);
        double _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        double _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        transform_degenerate_scba2d91_tail6(_dst, Math.max(_t114, _t115), Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)), _t114, _t115, _t93, _t96, _t99, (1.0 / Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83)))), _t83, _t92, _t95, _t98, _t84, _t94, _t97, _t100, _t91, _t72, _t70, _t71, _r7, _r18, _r8, _r19, _r6, _r20, _r21, _r10, _r11, _r9, _r22, _r2, _r4, _r0, _r23, _r24, _t24, _t25, _t26, _r25, _t18, _t19, _t20, _r26, _t21, _t22, _t23);
    }

    /**
     * Private tail of {@code transform_degenerate}. Shared by 2 identical private paths of
     * {@code transform}; reached only through it.
     */
    private void transform_degenerate_scba2d91_tail6(DoubleOBBImpl _dst, double _t117, double _t116, double _t114, double _t115, double _t93, double _t96, double _t99, double _t110, double _t83, double _t92, double _t95, double _t98, double _t84, double _t94, double _t97, double _t100, double _t91, double _t72, double _t70, double _t71, double _r7, double _r18, double _r8, double _r19, double _r6, double _r20, double _r21, double _r10, double _r11, double _r9, double _r22, double _r2, double _r4, double _r0, double _r23, double _r24, double _t24, double _t25, double _t26, double _r25, double _t18, double _t19, double _t20, double _r26, double _t21, double _t22, double _t23) {
        double _t121 = Math.max(_t117, _t116);
        double _t122 = (1.0 / Math.sqrt(_t121));
        double _t129, _t130, _t131;
        if (_t121 > 5.048709793414476E-29) {
            if (_t117 >= _t116) {
                if (_t114 >= _t115) {
                    _t129 = _t122 * _t93;
                    _t130 = _t122 * _t92;
                    _t131 = _t122 * _t94;
                } else {
                    _t129 = _t122 * _t96;
                    _t130 = _t122 * _t95;
                    _t131 = _t122 * _t97;
                }
            } else {
                _t129 = _t122 * _t99;
                _t130 = _t122 * _t98;
                _t131 = _t122 * _t100;
            }
        } else {
            _t129 = _t110 * _t83;
            _t130 = _t110 * _t84;
            _t131 = _t110 * _t91;
        }
        transform_degenerate_scba2d91_tail7(_dst, _t70, _t131, _t71, _t129, _t130, _t72, _r7, _r18, _r8, _r19, _r6, _r20, _r21, _r10, _r11, _r9, _r22, _r2, _r4, _r0, _r23, Math.fma(_t72, _t129, -(_t70 * _t130)), _r24, _t24, _t25, _t26, _r25, _t18, _t19, _t20, _r26, _t21, _t22, _t23);
    }

    /**
     * Private tail of {@code transform_degenerate}. Shared by 2 identical private paths of
     * {@code transform}; reached only through it.
     */
    private void transform_degenerate_scba2d91_tail7(DoubleOBBImpl _dst, double _t70, double _t131, double _t71, double _t129, double _t130, double _t72, double _r7, double _r18, double _r8, double _r19, double _r6, double _r20, double _r21, double _r10, double _r11, double _r9, double _r22, double _r2, double _r4, double _r0, double _r23, double _t138, double _r24, double _t24, double _t25, double _t26, double _r25, double _t18, double _t19, double _t20, double _r26, double _t21, double _t22, double _t23) {
        double _t139 = Math.fma(_t70, _t131, -(_t71 * _t129));
        double _t140 = Math.fma(_t71, _t130, -(_t72 * _t131));
        transform_scba2d91_c0(_dst, _r7, _r18, _r8, _r19, _r6, _r20, _r21, _r10, _r11, _r9, _r22, _r2, _r4, _r0, _r23, _t70);
        _dst.uXy = _t71;
        _dst.uXz = _t72;
        _dst.uYx = _t129;
        _dst.uYy = _t131;
        _dst.uYz = _t130;
        _dst.uZx = _t140;
        _dst.uZy = _t138;
        _dst.uZz = _t139;
        transform_scba2d91_c3(_dst, _r24, _t24, _t72, _t25, _t70, _t26, _t71, _r25, _t18, _t19, _t20, _r26, _t21, _t22, _t23, _t130, _t129, _t131, _t139, _t140, _t138);
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate(Double3x4R m, @Mutated DoubleOBB dest) {
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        double _r0 = m.m22();
        double _r1 = this.uXz;
        double _r2 = m.m20();
        double _r3 = this.uXx;
        double _r4 = m.m21();
        double _r5 = this.uXy;
        double _r6 = m.m02();
        double _r7 = m.m00();
        double _r8 = m.m01();
        double _r9 = m.m12();
        double _r10 = m.m10();
        double _r11 = m.m11();
        double _r12 = this.uYz;
        double _r13 = this.uYx;
        double _r14 = this.uYy;
        double _r15 = this.uZz;
        double _r16 = this.uZx;
        double _r17 = this.uZy;
        double _r18 = this.cX;
        double _r19 = this.cY;
        transform_degenerate_scba2d91_tail(d, m, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    @Mutated private DoubleOBB transform_degenerate(Double3x4R m) {
        return transform_degenerate(m, Joml.RETURN_NEW ? Joml.doubleOBB() : this);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private void transform_s21a2f5d2_tail(DoubleOBBImpl _dst, Double4x4R m, double _r6, double _r7, double _r8, double _r0, double _r2, double _r4, double _r9, double _r10, double _r11, double _t40, double _t18, double _t20, double _t19, double _t37, double _t52, double _t38, double _t39, double _t22, double _t21, double _t23) {
        double _r15 = this.uZz;
        double _r16 = this.uZx;
        double _r17 = this.uZy;
        double _r18 = this.cX;
        double _r19 = this.cY;
        double _r20 = this.cZ;
        double _r21 = m.m03();
        double _r22 = m.m13();
        double _r23 = m.m23();
        double _r24 = this.hsZ;
        double _r25 = this.hsX;
        double _r26 = this.hsY;
        double _t42 = (1.0 / Math.sqrt(_t40));
        transform_scba2d91_tail2(_dst, _t20, _t42, _t19, _t37, _t52, _t38, _t39, _t18 * _t42, _r2, _r18, _r4, _r19, _r0, _r20, _r21, _r10, _r11, _r9, _r22, _r7, _r8, _r6, _r23, _r24, Math.fma(_r6, _r15, Math.fma(_r7, _r16, _r8 * _r17)), Math.fma(_r0, _r15, Math.fma(_r2, _r16, _r4 * _r17)), Math.fma(_r9, _r15, Math.fma(_r10, _r16, _r11 * _r17)), _r25, _t18, _r26, _t22, _t21, _t23);
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them) and store the
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
        double _r0 = m.m02();
        double _r1 = this.uXz;
        double _r2 = m.m00();
        double _r3 = this.uXx;
        double _r4 = m.m01();
        double _r5 = this.uXy;
        double _r6 = m.m22();
        double _r7 = m.m20();
        double _r8 = m.m21();
        double _r9 = m.m12();
        double _r10 = m.m10();
        double _r11 = m.m11();
        double _r12 = this.uYz;
        double _r13 = this.uYx;
        double _r14 = this.uYy;
        double _t18 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5));
        double _t19 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5));
        double _t20 = Math.fma(_r9, _r1, Math.fma(_r10, _r3, _r11 * _r5));
        double _t21 = Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14));
        double _t22 = Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r8 * _r14));
        double _t23 = Math.fma(_r9, _r12, Math.fma(_r10, _r13, _r11 * _r14));
        return transform_s55de6487_1(m, dest, (DoubleOBBImpl) dest, _r0, _r2, _r4, _r6, _r7, _r8, _r9, _r10, _r11, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t21, _t19, -(_t18 * _t22)), Math.fma(_t18, _t23, -(_t21 * _t20)));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleOBB transform_s55de6487_1(Double4x4R m, DoubleOBB dest, DoubleOBBImpl d, double _r0, double _r2, double _r4, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t37, double _t38) {
        double _t39 = Math.fma(_t20, _t22, -(_t23 * _t19));
        double _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        double _t52 = Math.fma(_t38, _t38, Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_t52 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 5.048709793414476E-29, 2.2250738585072014E-308) && _t52 < Double.POSITIVE_INFINITY)) return transform_degenerate(m, dest);
        transform_s21a2f5d2_tail(d, m, _r6, _r7, _r8, _r0, _r2, _r4, _r9, _r10, _r11, _t40, _t18, _t20, _t19, _t37, (1.0 / Math.sqrt(_t52)), _t38, _t39, _t22, _t21, _t23);
        return d;
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private void transform_degenerate_s21a2f5d2_tail(DoubleOBBImpl _dst, Double4x4R m, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19) {
        double _r20 = this.cZ;
        double _r21 = m.m03();
        double _r22 = m.m13();
        double _r23 = m.m23();
        double _r24 = this.hsZ;
        double _r25 = this.hsX;
        double _r26 = this.hsY;
        transform_degenerate_scba2d91_tail2(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, Math.fma(_r6, _r1, Math.fma(_r7, _r3, _r8 * _r5)), Math.fma(_r9, _r1, Math.fma(_r10, _r3, _r11 * _r5)), Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5)), Math.fma(_r6, _r12, Math.fma(_r7, _r13, _r8 * _r14)), Math.fma(_r9, _r12, Math.fma(_r10, _r13, _r11 * _r14)), Math.fma(_r0, _r12, Math.fma(_r2, _r13, _r4 * _r14)), _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26);
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate(Double4x4R m, @Mutated DoubleOBB dest) {
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        double _r0 = m.m22();
        double _r1 = this.uXz;
        double _r2 = m.m20();
        double _r3 = this.uXx;
        double _r4 = m.m21();
        double _r5 = this.uXy;
        double _r6 = m.m02();
        double _r7 = m.m00();
        double _r8 = m.m01();
        double _r9 = m.m12();
        double _r10 = m.m10();
        double _r11 = m.m11();
        double _r12 = this.uYz;
        double _r13 = this.uYx;
        double _r14 = this.uYy;
        double _r15 = this.uZz;
        double _r16 = this.uZx;
        double _r17 = this.uZy;
        double _r18 = this.cX;
        double _r19 = this.cY;
        transform_degenerate_s21a2f5d2_tail(d, m, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    @Mutated private DoubleOBB transform_degenerate(Double4x4R m) {
        return transform_degenerate(m, Joml.RETURN_NEW ? Joml.doubleOBB() : this);
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
        return translate(delta.x(), delta.y(), delta.z(), dest);
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
        DoubleOBBImpl d = (DoubleOBBImpl) dest;
        d.cX = deltaX + this.cX;
        d.cY = deltaY + this.cY;
        d.cZ = deltaZ + this.cZ;
        d.uXx = this.uXx;
        d.uXy = this.uXy;
        d.uXz = this.uXz;
        d.uYx = this.uYx;
        d.uYy = this.uYy;
        d.uYz = this.uYz;
        d.uZx = this.uZx;
        d.uZy = this.uZy;
        d.uZz = this.uZz;
        d.hsX = this.hsX;
        d.hsY = this.hsY;
        d.hsZ = this.hsZ;
        return d;
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
        return closestPointToPoint(p.x(), p.y(), p.z(), dest);
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
        Double3Impl d = (Double3Impl) dest;
        double _t3 = pZ - this.cZ;
        double _t4 = pX - this.cX;
        double _t5 = pY - this.cY;
        double _t18 = Math.max(-this.hsX, Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        double _t19 = Math.max(-this.hsY, Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        double _t20 = Math.max(-this.hsZ, Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        d.x = Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX)));
        d.y = Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY)));
        d.z = Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ)));
        return d;
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
        return containsPoint(p.x(), p.y(), p.z());
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
        double _t0 = pZ - this.cZ;
        double _t1 = pX - this.cX;
        double _t2 = pY - this.cY;
        if (!(Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) <= this.hsX)) return false;
        if (!(Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) <= this.hsY)) return false;
        return Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) <= this.hsZ;
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
        return distanceSquaredToPoint(p.x(), p.y(), p.z());
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
        double _t0 = pZ - this.cZ;
        double _t1 = pX - this.cX;
        double _t2 = pY - this.cY;
        double _t18 = Math.max(0.0, Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        double _t19 = Math.max(0.0, Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        double _t20 = Math.max(0.0, Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
        return Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
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
        return distanceToPoint(p.x(), p.y(), p.z());
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
        double _t0 = pZ - this.cZ;
        double _t1 = pX - this.cX;
        double _t2 = pY - this.cY;
        double _t18 = Math.max(0.0, Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        double _t19 = Math.max(0.0, Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        double _t20 = Math.max(0.0, Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
        return Math.sqrt(Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.uXx;
        d.y = this.uXy;
        d.z = this.uXz;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.uYx;
        d.y = this.uYy;
        d.z = this.uYz;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.uZx;
        d.y = this.uZy;
        d.z = this.uZz;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.cX;
        d.y = this.cY;
        d.z = this.cZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.hsX;
        d.y = this.hsY;
        d.z = this.hsZ;
        return d;
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
        return intersectsOBB(o.cX(), o.cY(), o.cZ(), o.uXx(), o.uXy(), o.uXz(), o.uYx(), o.uYy(), o.uYz(), o.uZx(), o.uZy(), o.uZz(), o.hsX(), o.hsY(), o.hsZ());
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
        double _t0 = oCZ - this.cZ;
        double _t1 = oCX - this.cX;
        double _t2 = oCY - this.cY;
        double _t54 = Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2));
        double _t24 = Math.fma(oUXz, this.uXz, Math.fma(oUXx, this.uXx, oUXy * this.uXy));
        double _t45 = Math.abs(_t24) + 1.0E-8;
        double _t25 = Math.fma(oUYz, this.uXz, Math.fma(oUYx, this.uXx, oUYy * this.uXy));
        double _t46 = Math.abs(_t25) + 1.0E-8;
        double _t26 = Math.fma(oUZz, this.uXz, Math.fma(oUZx, this.uXx, oUZy * this.uXy));
        double _t47 = Math.abs(_t26) + 1.0E-8;
        if (!(Math.abs(_t54) <= Math.fma(oHsX, _t45, Math.fma(oHsY, _t46, Math.fma(oHsZ, _t47, this.hsX))))) return false;
        return intersectsOBB_sd948180f_1(oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, _t0, _t1, _t2, _t54, _t24, _t45, _t25, _t46, _t26, _t47, Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2)), Math.fma(oUXz, this.uYz, Math.fma(oUXx, this.uYx, oUXy * this.uYy)));
    }

    /** Piece 2 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_1(double oUXx, double oUXy, double oUXz, double oUYx, double oUYy, double oUYz, double oUZx, double oUZy, double oUZz, double oHsX, double oHsY, double oHsZ, double _t0, double _t1, double _t2, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27) {
        double _t48 = Math.abs(_t27) + 1.0E-8;
        double _t28 = Math.fma(oUYz, this.uYz, Math.fma(oUYx, this.uYx, oUYy * this.uYy));
        double _t49 = Math.abs(_t28) + 1.0E-8;
        double _t29 = Math.fma(oUZz, this.uYz, Math.fma(oUZx, this.uYx, oUZy * this.uYy));
        double _t50 = Math.abs(_t29) + 1.0E-8;
        if (!(Math.abs(_t55) <= Math.fma(oHsX, _t48, Math.fma(oHsY, _t49, Math.fma(oHsZ, _t50, this.hsY))))) return false;
        double _t30 = Math.fma(oUXz, this.uZz, Math.fma(oUXx, this.uZx, oUXy * this.uZy));
        double _t31 = Math.fma(oUYz, this.uZz, Math.fma(oUYx, this.uZx, oUYy * this.uZy));
        double _t32 = Math.fma(oUZz, this.uZz, Math.fma(oUZx, this.uZx, oUZy * this.uZy));
        return intersectsOBB_sd948180f_2(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2)), _t30, Math.abs(_t30) + 1.0E-8, _t31, Math.abs(_t31) + 1.0E-8, _t32, Math.abs(_t32) + 1.0E-8);
    }

    /** Piece 3 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_2(double oHsX, double oHsY, double oHsZ, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(Math.abs(_t56) <= Math.fma(oHsX, _t51, Math.fma(oHsY, _t52, Math.fma(oHsZ, _t53, this.hsZ))))) return false;
        if (!(Math.abs(Math.fma(_t30, _t56, Math.fma(_t24, _t54, _t27 * _t55))) <= Math.fma(this.hsX, _t45, Math.fma(this.hsY, _t48, Math.fma(this.hsZ, _t51, oHsX))))) return false;
        if (!(Math.abs(Math.fma(_t31, _t56, Math.fma(_t25, _t54, _t28 * _t55))) <= Math.fma(this.hsX, _t46, Math.fma(this.hsY, _t49, Math.fma(this.hsZ, _t52, oHsY))))) return false;
        if (!(Math.abs(Math.fma(_t32, _t56, Math.fma(_t26, _t54, _t29 * _t55))) <= Math.fma(this.hsX, _t47, Math.fma(this.hsY, _t50, Math.fma(this.hsZ, _t53, oHsZ))))) return false;
        if (!(Math.abs(Math.fma(_t27, _t56, -(_t30 * _t55))) <= Math.fma(oHsY, _t47, oHsZ * _t46) + Math.fma(this.hsY, _t51, this.hsZ * _t48))) return false;
        return intersectsOBB_sd948180f_3(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t56, _t30, _t51, _t31, _t52, _t32, _t53);
    }

    /** Piece 4 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_3(double oHsX, double oHsY, double oHsZ, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t56, double _t30, double _t51, double _t31, double _t52, double _t32, double _t53) {
        if (!(Math.abs(Math.fma(_t28, _t56, -(_t31 * _t55))) <= Math.fma(oHsX, _t47, oHsZ * _t45) + Math.fma(this.hsY, _t52, this.hsZ * _t49))) return false;
        if (!(Math.abs(Math.fma(_t29, _t56, -(_t32 * _t55))) <= Math.fma(oHsX, _t46, oHsY * _t45) + Math.fma(this.hsY, _t53, this.hsZ * _t50))) return false;
        if (!(Math.abs(Math.fma(_t30, _t54, -(_t24 * _t56))) <= Math.fma(oHsY, _t50, oHsZ * _t49) + Math.fma(this.hsX, _t51, this.hsZ * _t45))) return false;
        if (!(Math.abs(Math.fma(_t31, _t54, -(_t25 * _t56))) <= Math.fma(oHsX, _t50, oHsZ * _t48) + Math.fma(this.hsX, _t52, this.hsZ * _t46))) return false;
        if (!(Math.abs(Math.fma(_t32, _t54, -(_t26 * _t56))) <= Math.fma(oHsX, _t49, oHsY * _t48) + Math.fma(this.hsX, _t53, this.hsZ * _t47))) return false;
        return intersectsOBB_sd948180f_4(oHsX, oHsY, oHsZ, _t54, _t24, _t45, _t25, _t46, _t26, _t47, _t55, _t27, _t48, _t28, _t49, _t29, _t50, _t51, _t52, _t53);
    }

    /** Piece 5 of {@code intersectsOBB}, split to fit the inline budget; reached only through it. */
    private boolean intersectsOBB_sd948180f_4(double oHsX, double oHsY, double oHsZ, double _t54, double _t24, double _t45, double _t25, double _t46, double _t26, double _t47, double _t55, double _t27, double _t48, double _t28, double _t49, double _t29, double _t50, double _t51, double _t52, double _t53) {
        if (!(Math.abs(Math.fma(_t24, _t55, -(_t27 * _t54))) <= Math.fma(oHsY, _t53, oHsZ * _t52) + Math.fma(this.hsX, _t48, this.hsY * _t45))) return false;
        if (!(Math.abs(Math.fma(_t25, _t55, -(_t28 * _t54))) <= Math.fma(oHsX, _t53, oHsZ * _t51) + Math.fma(this.hsX, _t49, this.hsY * _t46))) return false;
        return Math.abs(Math.fma(_t26, _t55, -(_t29 * _t54))) <= Math.fma(oHsX, _t52, oHsY * _t51) + Math.fma(this.hsX, _t50, this.hsY * _t47);
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
        if (!(this.hsX >= 0.0)) return false;
        if (!(this.hsY >= 0.0)) return false;
        return this.hsZ >= 0.0;
    }

    public double cX() { return this.cX; }
    public double cY() { return this.cY; }
    public double cZ() { return this.cZ; }
    public double uXx() { return this.uXx; }
    public double uXy() { return this.uXy; }
    public double uXz() { return this.uXz; }
    public double uYx() { return this.uYx; }
    public double uYy() { return this.uYy; }
    public double uYz() { return this.uYz; }
    public double uZx() { return this.uZx; }
    public double uZy() { return this.uZy; }
    public double uZz() { return this.uZz; }
    public double hsX() { return this.hsX; }
    public double hsY() { return this.hsY; }
    public double hsZ() { return this.hsZ; }

    @Override public String toString() {
        return "DoubleOBB(" + cX() + ", " + cY() + ", " + cZ() + ", " + uXx() + ", " + uXy() + ", " + uXz() + ", " + uYx() + ", " + uYy() + ", " + uYz() + ", " + uZx() + ", " + uZy() + ", " + uZz() + ", " + hsX() + ", " + hsY() + ", " + hsZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleOBBImpl)) return false;
        DoubleOBBImpl o = (DoubleOBBImpl) obj;
        return Double.doubleToLongBits(cX) == Double.doubleToLongBits(o.cX)
            && Double.doubleToLongBits(cY) == Double.doubleToLongBits(o.cY)
            && Double.doubleToLongBits(cZ) == Double.doubleToLongBits(o.cZ)
            && Double.doubleToLongBits(uXx) == Double.doubleToLongBits(o.uXx)
            && Double.doubleToLongBits(uXy) == Double.doubleToLongBits(o.uXy)
            && Double.doubleToLongBits(uXz) == Double.doubleToLongBits(o.uXz)
            && Double.doubleToLongBits(uYx) == Double.doubleToLongBits(o.uYx)
            && Double.doubleToLongBits(uYy) == Double.doubleToLongBits(o.uYy)
            && Double.doubleToLongBits(uYz) == Double.doubleToLongBits(o.uYz)
            && Double.doubleToLongBits(uZx) == Double.doubleToLongBits(o.uZx)
            && Double.doubleToLongBits(uZy) == Double.doubleToLongBits(o.uZy)
            && Double.doubleToLongBits(uZz) == Double.doubleToLongBits(o.uZz)
            && Double.doubleToLongBits(hsX) == Double.doubleToLongBits(o.hsX)
            && Double.doubleToLongBits(hsY) == Double.doubleToLongBits(o.hsY)
            && Double.doubleToLongBits(hsZ) == Double.doubleToLongBits(o.hsZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(cX) ^ (Double.doubleToLongBits(cX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(cY) ^ (Double.doubleToLongBits(cY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(cZ) ^ (Double.doubleToLongBits(cZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uXx) ^ (Double.doubleToLongBits(uXx) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uXy) ^ (Double.doubleToLongBits(uXy) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uXz) ^ (Double.doubleToLongBits(uXz) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uYx) ^ (Double.doubleToLongBits(uYx) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uYy) ^ (Double.doubleToLongBits(uYy) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uYz) ^ (Double.doubleToLongBits(uYz) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uZx) ^ (Double.doubleToLongBits(uZx) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uZy) ^ (Double.doubleToLongBits(uZy) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(uZz) ^ (Double.doubleToLongBits(uZz) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(hsX) ^ (Double.doubleToLongBits(hsX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(hsY) ^ (Double.doubleToLongBits(hsY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(hsZ) ^ (Double.doubleToLongBits(hsZ) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(cX)
            && Double.isFinite(cY)
            && Double.isFinite(cZ)
            && Double.isFinite(uXx)
            && Double.isFinite(uXy)
            && Double.isFinite(uXz)
            && Double.isFinite(uYx)
            && Double.isFinite(uYy)
            && Double.isFinite(uYz)
            && Double.isFinite(uZx)
            && Double.isFinite(uZy)
            && Double.isFinite(uZz)
            && Double.isFinite(hsX)
            && Double.isFinite(hsY)
            && Double.isFinite(hsZ);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(cX)
            || Double.isNaN(cY)
            || Double.isNaN(cZ)
            || Double.isNaN(uXx)
            || Double.isNaN(uXy)
            || Double.isNaN(uXz)
            || Double.isNaN(uYx)
            || Double.isNaN(uYy)
            || Double.isNaN(uYz)
            || Double.isNaN(uZx)
            || Double.isNaN(uZy)
            || Double.isNaN(uZz)
            || Double.isNaN(hsX)
            || Double.isNaN(hsY)
            || Double.isNaN(hsZ);
    }

    @Override public boolean equalsEpsilon(DoubleOBBR other, double epsilon) {
        return Math.abs(cX - other.cX()) <= epsilon
            && Math.abs(cY - other.cY()) <= epsilon
            && Math.abs(cZ - other.cZ()) <= epsilon
            && Math.abs(uXx - other.uXx()) <= epsilon
            && Math.abs(uXy - other.uXy()) <= epsilon
            && Math.abs(uXz - other.uXz()) <= epsilon
            && Math.abs(uYx - other.uYx()) <= epsilon
            && Math.abs(uYy - other.uYy()) <= epsilon
            && Math.abs(uYz - other.uYz()) <= epsilon
            && Math.abs(uZx - other.uZx()) <= epsilon
            && Math.abs(uZy - other.uZy()) <= epsilon
            && Math.abs(uZz - other.uZz()) <= epsilon
            && Math.abs(hsX - other.hsX()) <= epsilon
            && Math.abs(hsY - other.hsY()) <= epsilon
            && Math.abs(hsZ - other.hsZ()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.cX;
        dest[offset + 1] = this.cY;
        dest[offset + 2] = this.cZ;
        dest[offset + 3] = this.uXx;
        dest[offset + 4] = this.uXy;
        dest[offset + 5] = this.uXz;
        dest[offset + 6] = this.uYx;
        dest[offset + 7] = this.uYy;
        dest[offset + 8] = this.uYz;
        dest[offset + 9] = this.uZx;
        dest[offset + 10] = this.uZy;
        dest[offset + 11] = this.uZz;
        dest[offset + 12] = this.hsX;
        dest[offset + 13] = this.hsY;
        dest[offset + 14] = this.hsZ;
        return dest;
    }
    public @Mutated DoubleOBB load(double[] src, int offset) {
        this.cX = src[offset + 0];
        this.cY = src[offset + 1];
        this.cZ = src[offset + 2];
        this.uXx = src[offset + 3];
        this.uXy = src[offset + 4];
        this.uXz = src[offset + 5];
        this.uYx = src[offset + 6];
        this.uYy = src[offset + 7];
        this.uYz = src[offset + 8];
        this.uZx = src[offset + 9];
        this.uZy = src[offset + 10];
        this.uZz = src[offset + 11];
        this.hsX = src[offset + 12];
        this.hsY = src[offset + 13];
        this.hsZ = src[offset + 14];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoubleOBB loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleOBB loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleOBB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleOBB loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.cX;
        dest[offset + 1] = (float) this.cY;
        dest[offset + 2] = (float) this.cZ;
        dest[offset + 3] = (float) this.uXx;
        dest[offset + 4] = (float) this.uXy;
        dest[offset + 5] = (float) this.uXz;
        dest[offset + 6] = (float) this.uYx;
        dest[offset + 7] = (float) this.uYy;
        dest[offset + 8] = (float) this.uYz;
        dest[offset + 9] = (float) this.uZx;
        dest[offset + 10] = (float) this.uZy;
        dest[offset + 11] = (float) this.uZz;
        dest[offset + 12] = (float) this.hsX;
        dest[offset + 13] = (float) this.hsY;
        dest[offset + 14] = (float) this.hsZ;
        return dest;
    }
    public @Mutated DoubleOBB load(float[] src, int offset) {
        this.cX = src[offset + 0];
        this.cY = src[offset + 1];
        this.cZ = src[offset + 2];
        this.uXx = src[offset + 3];
        this.uXy = src[offset + 4];
        this.uXz = src[offset + 5];
        this.uYx = src[offset + 6];
        this.uYy = src[offset + 7];
        this.uYz = src[offset + 8];
        this.uZx = src[offset + 9];
        this.uZy = src[offset + 10];
        this.uZz = src[offset + 11];
        this.hsX = src[offset + 12];
        this.hsY = src[offset + 13];
        this.hsZ = src[offset + 14];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoubleOBB loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public DoubleOBB loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleOBB storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleOBB loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
