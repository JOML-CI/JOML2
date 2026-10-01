// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Immutable oriented bounding box of double-precision {@code double} components.
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
 * @param cX the {@code cX} component
 * @param cY the {@code cY} component
 * @param cZ the {@code cZ} component
 * @param uXx the {@code uXx} component
 * @param uXy the {@code uXy} component
 * @param uXz the {@code uXz} component
 * @param uYx the {@code uYx} component
 * @param uYy the {@code uYy} component
 * @param uYz the {@code uYz} component
 * @param uZx the {@code uZx} component
 * @param uZy the {@code uZy} component
 * @param uZz the {@code uZz} component
 * @param hsX the {@code hsX} component
 * @param hsY the {@code hsY} component
 * @param hsZ the {@code hsZ} component
 */
public record DoubleOBB(double cX, double cY, double cZ, double uXx, double uXy, double uXz, double uYx, double uYy, double uYz, double uZx, double uZy, double uZz, double hsX, double hsY, double hsZ) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 120;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param cX the {@code cX} component
     * @param cY the {@code cY} component
     * @param cZ the {@code cZ} component
     * @param uXx the {@code uXx} component
     * @param uXy the {@code uXy} component
     * @param uXz the {@code uXz} component
     * @param uYx the {@code uYx} component
     * @param uYy the {@code uYy} component
     * @param uYz the {@code uYz} component
     * @param uZx the {@code uZx} component
     * @param uZy the {@code uZy} component
     * @param uZz the {@code uZz} component
     * @param hsX the {@code hsX} component
     * @param hsY the {@code hsY} component
     * @param hsZ the {@code hsZ} component
     */
    public DoubleOBB(double cX, double cY, double cZ, double uXx, double uXy, double uXz, double uYx, double uYy, double uYz, double uZx, double uZy, double uZz, double hsX, double hsY, double hsZ) {
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

    /**
     * Create a new instance initialized to a degenerate box with identity orientation and zero
     * center and extents.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public DoubleOBB() {
        this(0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0);
    }

    /**
     * Create an oriented bounding box from its center, its three local axes and its half-size.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the box
     * @param axisX the local x axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisY the local y axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisZ the local z axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param halfSize the half extent of the box along each local axis
     */
    public DoubleOBB(Double3 center, Double3 axisX, Double3 axisY, Double3 axisZ, Double3 halfSize) {
        this(center.x(), center.y(), center.z(), axisX.x(), axisX.y(), axisX.z(), axisY.x(), axisY.y(), axisY.z(), axisZ.x(), axisZ.y(), axisZ.z(), halfSize.x(), halfSize.y(), halfSize.z());
    }

    /** {@return the {@code cX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double cX() { return cX; }
    /** {@return the {@code cY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double cY() { return cY; }
    /** {@return the {@code cZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double cZ() { return cZ; }
    /** {@return the {@code uXx} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uXx() { return uXx; }
    /** {@return the {@code uXy} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uXy() { return uXy; }
    /** {@return the {@code uXz} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uXz() { return uXz; }
    /** {@return the {@code uYx} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uYx() { return uYx; }
    /** {@return the {@code uYy} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uYy() { return uYy; }
    /** {@return the {@code uYz} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uYz() { return uYz; }
    /** {@return the {@code uZx} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uZx() { return uZx; }
    /** {@return the {@code uZy} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uZy() { return uZy; }
    /** {@return the {@code uZz} component} <p>Valid input: any value, NaN and the infinities included. */
    public double uZz() { return uZz; }
    /** {@return the {@code hsX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double hsX() { return hsX; }
    /** {@return the {@code hsY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double hsY() { return hsY; }
    /** {@return the {@code hsZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double hsZ() { return hsZ; }

    /**
     * Create a new oriented bounding box from its center, its three local axes and its half-size.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the box
     * @param axisX the local x axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisY the local y axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisZ the local z axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param halfSize the half extent of the box along each local axis
     * @return the resulting oriented bounding box
     */
    public DoubleOBB set(Double3 center, Double3 axisX, Double3 axisY, Double3 axisZ, Double3 halfSize) {
        return new DoubleOBB(center, axisX, axisY, axisZ, halfSize);
    }


    /**
     * Create a new oriented bounding box from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return the resulting oriented bounding box
     */
    public DoubleOBB set(DoubleOBB v) {
        return set(v.cX(), v.cY(), v.cZ(), v.uXx(), v.uXy(), v.uXz(), v.uYx(), v.uYy(), v.uYz(), v.uZx(), v.uZy(), v.uZz(), v.hsX(), v.hsY(), v.hsZ());
    }


    /**
     * Create a new oriented bounding box from the given values.
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
     * @return the resulting oriented bounding box
     */
    public DoubleOBB set(double vCX, double vCY, double vCZ, double vUXx, double vUXy, double vUXz, double vUYx, double vUYy, double vUYz, double vUZx, double vUZy, double vUZz, double vHsX, double vHsY, double vHsZ) {
        return new DoubleOBB(vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ);
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to {@code axisX}, {@code axisY}
     * and {@code axisZ}, returning the result as a value.
     * <p>
     * Valid input: {@code axisX}, {@code axisY} and {@code axisZ} must be orthonormal.
     *
     * @param axisX the new local X axis
     * @param axisY the new local Y axis
     * @param axisZ the new local Z axis
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setAxes(Double3 axisX, Double3 axisY, Double3 axisZ) {
        return setAxes(axisX.x(), axisX.y(), axisX.z(), axisY.x(), axisY.y(), axisY.z(), axisZ.x(), axisZ.y(), axisZ.z());
    }


    /**
     * Set the local coordinate axes of this oriented bounding box to ({@code axisXX},
     * {@code axisXY}, {@code axisXZ}), ({@code axisYX}, {@code axisYY}, {@code axisYZ}) and
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}), returning the result as a value.
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
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setAxes(double axisXX, double axisXY, double axisXZ, double axisYX, double axisYY, double axisYZ, double axisZX, double axisZY, double axisZZ) {
        return new DoubleOBB(this.cX, this.cY, this.cZ, axisXX, axisXY, axisXZ, axisYX, axisYY, axisYZ, axisZX, axisZY, axisZZ, this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Set the center of this oriented bounding box to {@code c}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setCenter(Double3 c) {
        return setCenter(c.x(), c.y(), c.z());
    }


    /**
     * Set the center of this oriented bounding box to ({@code cX}, {@code cY}, {@code cZ}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setCenter(double cX, double cY, double cZ) {
        return new DoubleOBB(cX, cY, cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Set the half extents of this oriented bounding box to {@code h}, returning the result as a
     * value.
     * <p>
     * Valid input: each component of {@code h} must not be negative.
     *
     * @param h the new half extents
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setHalfSize(Double3 h) {
        return setHalfSize(h.x(), h.y(), h.z());
    }


    /**
     * Set the half extents of this oriented bounding box to ({@code hX}, {@code hY}, {@code hZ}),
     * returning the result as a value.
     * <p>
     * Valid input: each component of {@code (hX, hY, hZ)} must not be negative.
     *
     * @param hX the {@code x} component of the vector {@code (hX, hY, hZ)}
     * @param hY the {@code y} component of the vector {@code (hX, hY, hZ)}
     * @param hZ the {@code z} component of the vector {@code (hX, hY, hZ)}
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setHalfSize(double hX, double hY, double hZ) {
        return new DoubleOBB(this.cX, this.cY, this.cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, hX, hY, hZ);
    }


    /**
     * Reset the orientation of this oriented bounding box to identity, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setIdentityOrientation() {
        return new DoubleOBB(this.cX, this.cY, this.cZ, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Set the orientation of this oriented bounding box to {@code q}, returning the result as a
     * value.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the new orientation
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setOrientation(DoubleQuat q) {
        return setOrientation(q.x(), q.y(), q.z(), q.w());
    }


    /**
     * Set the orientation of this oriented bounding box to ({@code qX}, {@code qY}, {@code qZ},
     * {@code qW}), returning the result as a value.
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @return the resulting oriented bounding box
     */
    public DoubleOBB setOrientation(double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        return new DoubleOBB(this.cX, this.cY, this.cZ, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0), 2.0 * Math.fma(qX, qY, _t1), 2.0 * Math.fma(qX, qZ, -_t2), 2.0 * Math.fma(qX, qY, -_t1), Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0), 2.0 * Math.fma(qX, qW, qY * qZ), 2.0 * Math.fma(qX, qZ, _t2), 2.0 * Math.fma(qY, qZ, -(qX * qW)), Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0), this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Convert this oriented bounding box to {@code float} precision, returning the result as a new
     * instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatOBB} holding the result
     */
    public FloatOBB toFloat() {
        return new FloatOBB((float) (this.cX), (float) (this.cY), (float) (this.cZ), (float) (this.uXx), (float) (this.uXy), (float) (this.uXz), (float) (this.uYx), (float) (this.uYy), (float) (this.uYz), (float) (this.uZx), (float) (this.uZy), (float) (this.uZz), (float) (this.hsX), (float) (this.hsY), (float) (this.hsZ));
    }


    /**
     * Create an identity oriented bounding box.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting oriented bounding box
     */
    public static DoubleOBB makeIdentity() {
        return new DoubleOBB(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleOBB transform_s91e96b_tail(double _t19, double _t18, double _t20, double _t38, double _t37, double _t39, double _t22, double _t21, double _t23, Double3x4 m) {
        double _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        double _ct0 = Math.fma(_t38, _t38, Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_ct0 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return null;
        double _t52 = (1.0 / Math.sqrt(_ct0));
        double _t42 = (1.0 / Math.sqrt(_t40));
        return transform_s91e96b_tail2(_t38, _t52, _t39, _t19 * _t42, _t37 * _t52, _t20 * _t42, _t18 * _t42, m, Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _t19, _t18, _t20, _t22, _t21, _t23);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleOBB transform_s91e96b_tail2(double _t38, double _t52, double _t39, double _t46, double _t53, double _t45, double _t44, Double3x4 m, double _t24, double _t25, double _t26, double _t19, double _t18, double _t20, double _t22, double _t21, double _t23) {
        double _t54 = _t38 * _t52;
        double _t55 = _t39 * _t52;
        double _t62 = Math.fma(_t46, _t53, -(_t45 * _t54));
        double _t63 = Math.fma(_t44, _t54, -(_t46 * _t55));
        double _t64 = Math.fma(_t45, _t55, -(_t44 * _t53));
        double _sfx0 = Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03())));
        double _sfx1 = Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13())));
        double _sfx2 = Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23())));
        double _sfx3 = _t44;
        double _sfx4 = _t45;
        double _sfx5 = _t46;
        double _sfx6 = _t62;
        double _sfx7 = _t63;
        double _sfx8 = _t64;
        double _sfx9 = _t55;
        double _sfx10 = _t53;
        double _sfx11 = _t54;
        return transform_s91e96b_tail3(_t24, _t46, _t25, _t44, _t26, _t45, _t19, _t18, _t20, _t22, _t21, _t23, _t64, _t62, _t63, _t54, _t55, _t53, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9, _sfx10, _sfx11);
    }

    /**
     * Private tail of {@code transform}. Shared by 4 identical private paths of {@code transform};
     * reached only through it.
     */
    private DoubleOBB transform_s91e96b_tail3(double _t24, double _t46, double _t25, double _t44, double _t26, double _t45, double _t19, double _t18, double _t20, double _t22, double _t21, double _t23, double _t64, double _t62, double _t63, double _t54, double _t55, double _t53, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4, double _sfx5, double _sfx6, double _sfx7, double _sfx8, double _sfx9, double _sfx10, double _sfx11) {
        double _sfx12 = Math.fma(this.hsZ, Math.abs(Math.fma(_t24, _t46, Math.fma(_t25, _t44, _t26 * _t45))), Math.fma(this.hsX, Math.abs(Math.fma(_t19, _t46, Math.fma(_t18, _t44, _t20 * _t45))), this.hsY * Math.abs(Math.fma(_t22, _t46, Math.fma(_t21, _t44, _t23 * _t45)))));
        double _sfx13 = Math.fma(this.hsZ, Math.abs(Math.fma(_t24, _t64, Math.fma(_t25, _t62, _t26 * _t63))), Math.fma(this.hsX, Math.abs(Math.fma(_t19, _t64, Math.fma(_t18, _t62, _t20 * _t63))), this.hsY * Math.abs(Math.fma(_t22, _t64, Math.fma(_t21, _t62, _t23 * _t63)))));
        double _sfx14 = Math.fma(this.hsZ, Math.abs(Math.fma(_t24, _t54, Math.fma(_t25, _t55, _t26 * _t53))), Math.fma(this.hsX, Math.abs(Math.fma(_t19, _t54, Math.fma(_t18, _t55, _t20 * _t53))), this.hsY * Math.abs(Math.fma(_t22, _t54, Math.fma(_t21, _t55, _t23 * _t53)))));
        return new DoubleOBB(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9, _sfx10, _sfx11, _sfx12, _sfx13, _sfx14);
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), returning the
     * result as a value.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @return the resulting oriented bounding box
     */
    public DoubleOBB transform(Double3x4 m) {
        double _t18 = Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy));
        double _t19 = Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy));
        double _t20 = Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy));
        double _t21 = Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy));
        double _t22 = Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy));
        double _t23 = Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy));
        DoubleOBB _r = transform_s91e96b_tail(_t19, _t18, _t20, Math.fma(_t18, _t23, -(_t21 * _t20)), Math.fma(_t21, _t19, -(_t18 * _t22)), Math.fma(_t20, _t22, -(_t23 * _t19)), _t22, _t21, _t23, m);
        return _r != null ? _r : transform_degenerate(m);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_s91e96b_tail(Double3x4 m, double _t19, double _t20, double _t18, double _t22, double _t23, double _t21, double _t24) {
        double _t25 = Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy));
        double _t26 = Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        return transform_degenerate_s91e96b_tail2(_t24 * _t29, _t25 * _t29, _t26 * _t29, Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)), _t39, Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)), _t42, _t40, _t43, _t41, _t44, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_s91e96b_tail2(double _t45, double _t46, double _t47, double _t54, double _t39, double _t55, double _t42, double _t40, double _t43, double _t41, double _t44, Double3x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
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
        double _t69 = (1.0 / Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))));
        double _t70 = _t69 * _t64;
        double _t72 = _t69 * _t63;
        return transform_degenerate_s91e96b_tail3(Math.abs(_t72), Math.abs(_t70), _t69 * _t65, _t72, _t42, _t70, _t43, _t44, _t45, _t46, _t47, _t39, _t40, _t41, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_s91e96b_tail3(double _t73, double _t74, double _t71, double _t72, double _t42, double _t70, double _t43, double _t44, double _t45, double _t46, double _t47, double _t39, double _t40, double _t41, Double3x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t83, _t84, _t91;
        if (_t73 < _t74) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -Math.fma(_t72, _t42, Math.fma(_t70, _t43, _t71 * _t44));
        double _t89 = -Math.fma(_t72, _t45, Math.fma(_t70, _t46, _t71 * _t47));
        double _t90 = -Math.fma(_t72, _t39, Math.fma(_t70, _t40, _t71 * _t41));
        return transform_degenerate_s91e96b_tail4(_t84, _t91, _t83, Math.fma(_t88, _t72, _t42), Math.fma(_t88, _t70, _t43), Math.fma(_t88, _t71, _t44), Math.fma(_t89, _t72, _t45), Math.fma(_t89, _t70, _t46), Math.fma(_t89, _t71, _t47), Math.fma(_t90, _t72, _t39), Math.fma(_t90, _t70, _t40), Math.fma(_t90, _t71, _t41), _t72, _t70, _t71, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_s91e96b_tail4(double _t84, double _t91, double _t83, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t72, double _t70, double _t71, Double3x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        double _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        double _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        double _t117 = Math.max(_t114, _t115);
        double _t121 = Math.max(_t117, _t116);
        return transform_degenerate_s91e96b_tail5(_t121, (1.0 / Math.sqrt(_t121)), _t117, _t116, _t114, _t115, _t93, _t96, _t99, (1.0 / Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83)))), _t83, _t92, _t95, _t98, _t84, _t94, _t97, _t100, _t91, _t72, _t70, _t71, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_s91e96b_tail5(double _t121, double _t122, double _t117, double _t116, double _t114, double _t115, double _t93, double _t96, double _t99, double _t110, double _t83, double _t92, double _t95, double _t98, double _t84, double _t94, double _t97, double _t100, double _t91, double _t72, double _t70, double _t71, Double3x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
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
        return transform_degenerate_s91e96b_tail6(m, _t70, _t71, _t72, _t129, _t131, _t130, Math.fma(_t71, _t130, -(_t72 * _t131)), Math.fma(_t72, _t129, -(_t70 * _t130)), Math.fma(_t70, _t131, -(_t71 * _t129)), _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_s91e96b_tail6(Double3x4 m, double _t70, double _t71, double _t72, double _t129, double _t131, double _t130, double _t140, double _t138, double _t139, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _sfx0 = Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03())));
        double _sfx1 = Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13())));
        double _sfx2 = Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23())));
        double _sfx3 = _t70;
        double _sfx4 = _t71;
        double _sfx5 = _t72;
        double _sfx6 = _t129;
        double _sfx7 = _t131;
        double _sfx8 = _t130;
        double _sfx9 = _t140;
        double _sfx10 = _t138;
        double _sfx11 = _t139;
        return transform_s91e96b_tail3(_t24, _t72, _t25, _t70, _t26, _t71, _t18, _t19, _t20, _t21, _t22, _t23, _t130, _t129, _t131, _t139, _t140, _t138, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9, _sfx10, _sfx11);
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate(Double3x4 m) {
        return transform_degenerate_s91e96b_tail(m, Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy)), Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy)), Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy)), Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy)), Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy)), Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleOBB transform_sa000ec_tail(double _t19, double _t18, double _t20, double _t38, double _t37, double _t39, double _t22, double _t21, double _t23, Double4x4 m) {
        double _t40 = Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20));
        double _ct0 = Math.fma(_t38, _t38, Math.fma(_t37, _t37, _t39 * _t39));
        if (!(_ct0 > Math.fma(Math.fma(_t22, _t22, Math.fma(_t21, _t21, _t23 * _t23)), _t40 * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return null;
        double _t52 = (1.0 / Math.sqrt(_ct0));
        double _t42 = (1.0 / Math.sqrt(_t40));
        return transform_sa000ec_tail2(_t38, _t52, _t39, _t19 * _t42, _t37 * _t52, _t20 * _t42, _t18 * _t42, m, Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _t19, _t18, _t20, _t22, _t21, _t23);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleOBB transform_sa000ec_tail2(double _t38, double _t52, double _t39, double _t46, double _t53, double _t45, double _t44, Double4x4 m, double _t24, double _t25, double _t26, double _t19, double _t18, double _t20, double _t22, double _t21, double _t23) {
        double _t54 = _t38 * _t52;
        double _t55 = _t39 * _t52;
        double _t62 = Math.fma(_t46, _t53, -(_t45 * _t54));
        double _t63 = Math.fma(_t44, _t54, -(_t46 * _t55));
        double _t64 = Math.fma(_t45, _t55, -(_t44 * _t53));
        double _sfx0 = Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03())));
        double _sfx1 = Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13())));
        double _sfx2 = Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23())));
        double _sfx3 = _t44;
        double _sfx4 = _t45;
        double _sfx5 = _t46;
        double _sfx6 = _t62;
        double _sfx7 = _t63;
        double _sfx8 = _t64;
        double _sfx9 = _t55;
        double _sfx10 = _t53;
        double _sfx11 = _t54;
        return transform_s91e96b_tail3(_t24, _t46, _t25, _t44, _t26, _t45, _t19, _t18, _t20, _t22, _t21, _t23, _t64, _t62, _t63, _t54, _t55, _t53, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9, _sfx10, _sfx11);
    }


    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), returning the
     * result as a value.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @return the resulting oriented bounding box
     */
    public DoubleOBB transform(Double4x4 m) {
        double _t18 = Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy));
        double _t19 = Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy));
        double _t20 = Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy));
        double _t21 = Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy));
        double _t22 = Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy));
        double _t23 = Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy));
        DoubleOBB _r = transform_sa000ec_tail(_t19, _t18, _t20, Math.fma(_t18, _t23, -(_t21 * _t20)), Math.fma(_t21, _t19, -(_t18 * _t22)), Math.fma(_t20, _t22, -(_t23 * _t19)), _t22, _t21, _t23, m);
        return _r != null ? _r : transform_degenerate(m);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_sa000ec_tail(Double4x4 m, double _t19, double _t20, double _t18, double _t22, double _t23, double _t21, double _t24) {
        double _t25 = Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy));
        double _t26 = Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy));
        double _t27 = unitScale(_t19, _t20, _t18);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t25, _t26, _t24);
        double _t39 = _t18 * _t27;
        double _t40 = _t19 * _t27;
        double _t41 = _t20 * _t27;
        double _t42 = _t21 * _t28;
        double _t43 = _t22 * _t28;
        double _t44 = _t23 * _t28;
        return transform_degenerate_sa000ec_tail2(_t24 * _t29, _t25 * _t29, _t26 * _t29, Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)), _t39, Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)), _t42, _t40, _t43, _t41, _t44, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_sa000ec_tail2(double _t45, double _t46, double _t47, double _t54, double _t39, double _t55, double _t42, double _t40, double _t43, double _t41, double _t44, Double4x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
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
        double _t69 = (1.0 / Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))));
        double _t70 = _t69 * _t64;
        double _t72 = _t69 * _t63;
        return transform_degenerate_sa000ec_tail3(Math.abs(_t72), Math.abs(_t70), _t69 * _t65, _t72, _t42, _t70, _t43, _t44, _t45, _t46, _t47, _t39, _t40, _t41, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_sa000ec_tail3(double _t73, double _t74, double _t71, double _t72, double _t42, double _t70, double _t43, double _t44, double _t45, double _t46, double _t47, double _t39, double _t40, double _t41, Double4x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t83, _t84, _t91;
        if (_t73 < _t74) {
            _t83 = _t71;
            _t84 = 0.0;
            _t91 = -_t70;
        } else {
            _t83 = 0.0;
            _t84 = -_t71;
            _t91 = _t72;
        }
        double _t88 = -Math.fma(_t72, _t42, Math.fma(_t70, _t43, _t71 * _t44));
        double _t89 = -Math.fma(_t72, _t45, Math.fma(_t70, _t46, _t71 * _t47));
        double _t90 = -Math.fma(_t72, _t39, Math.fma(_t70, _t40, _t71 * _t41));
        return transform_degenerate_sa000ec_tail4(_t84, _t91, _t83, Math.fma(_t88, _t72, _t42), Math.fma(_t88, _t70, _t43), Math.fma(_t88, _t71, _t44), Math.fma(_t89, _t72, _t45), Math.fma(_t89, _t70, _t46), Math.fma(_t89, _t71, _t47), Math.fma(_t90, _t72, _t39), Math.fma(_t90, _t70, _t40), Math.fma(_t90, _t71, _t41), _t72, _t70, _t71, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_sa000ec_tail4(double _t84, double _t91, double _t83, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t72, double _t70, double _t71, Double4x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        double _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        double _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        double _t117 = Math.max(_t114, _t115);
        double _t121 = Math.max(_t117, _t116);
        return transform_degenerate_sa000ec_tail5(_t121, (1.0 / Math.sqrt(_t121)), _t117, _t116, _t114, _t115, _t93, _t96, _t99, (1.0 / Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83)))), _t83, _t92, _t95, _t98, _t84, _t94, _t97, _t100, _t91, _t72, _t70, _t71, m, _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_sa000ec_tail5(double _t121, double _t122, double _t117, double _t116, double _t114, double _t115, double _t93, double _t96, double _t99, double _t110, double _t83, double _t92, double _t95, double _t98, double _t84, double _t94, double _t97, double _t100, double _t91, double _t72, double _t70, double _t71, Double4x4 m, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
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
        return transform_degenerate_sa000ec_tail6(m, _t70, _t71, _t72, _t129, _t131, _t130, Math.fma(_t71, _t130, -(_t72 * _t131)), Math.fma(_t72, _t129, -(_t70 * _t130)), Math.fma(_t70, _t131, -(_t71 * _t129)), _t24, _t25, _t26, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Private tail of {@code transform_degenerate}; reached only through it. */
    private DoubleOBB transform_degenerate_sa000ec_tail6(Double4x4 m, double _t70, double _t71, double _t72, double _t129, double _t131, double _t130, double _t140, double _t138, double _t139, double _t24, double _t25, double _t26, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _sfx0 = Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03())));
        double _sfx1 = Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13())));
        double _sfx2 = Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23())));
        double _sfx3 = _t70;
        double _sfx4 = _t71;
        double _sfx5 = _t72;
        double _sfx6 = _t129;
        double _sfx7 = _t131;
        double _sfx8 = _t130;
        double _sfx9 = _t140;
        double _sfx10 = _t138;
        double _sfx11 = _t139;
        return transform_s91e96b_tail3(_t24, _t72, _t25, _t70, _t26, _t71, _t18, _t19, _t20, _t21, _t22, _t23, _t130, _t129, _t131, _t139, _t140, _t138, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9, _sfx10, _sfx11);
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private DoubleOBB transform_degenerate(Double4x4 m) {
        return transform_degenerate_sa000ec_tail(m, Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy)), Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy)), Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy)), Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy)), Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy)), Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)));
    }


    /**
     * Translate this oriented bounding box by {@code delta}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @return the resulting oriented bounding box
     */
    public DoubleOBB translate(Double3 delta) {
        return translate(delta.x(), delta.y(), delta.z());
    }


    /**
     * Translate this oriented bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @return the resulting oriented bounding box
     */
    public DoubleOBB translate(double deltaX, double deltaY, double deltaZ) {
        return new DoubleOBB(deltaX + this.cX, deltaY + this.cY, deltaZ + this.cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * axes of this oriented bounding box must be orthonormal.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Double3 closestPointToPoint(Double3 p) {
        return closestPointToPoint(p.x(), p.y(), p.z());
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
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
     * @return the resulting vector
     */
    public Double3 closestPointToPoint(double pX, double pY, double pZ) {
        double _t3 = pZ - this.cZ;
        double _t4 = pX - this.cX;
        double _t5 = pY - this.cY;
        double _t18 = Math.max(-this.hsX, Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        double _t19 = Math.max(-this.hsY, Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        double _t20 = Math.max(-this.hsZ, Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        return new Double3(Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX))), Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY))), Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ))));
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
    public boolean containsPoint(Double3 p) {
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
    public double distanceSquaredToPoint(Double3 p) {
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
    public double distanceToPoint(Double3 p) {
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
     * Get the local {@code X} axis of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getAxisX() {
        return new Double3(this.uXx, this.uXy, this.uXz);
    }


    /**
     * Get the local {@code Y} axis of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getAxisY() {
        return new Double3(this.uYx, this.uYy, this.uYz);
    }


    /**
     * Get the local {@code Z} axis of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getAxisZ() {
        return new Double3(this.uZx, this.uZy, this.uZz);
    }


    /**
     * Get the center of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getCenter() {
        return new Double3(this.cX, this.cY, this.cZ);
    }


    /**
     * Get the half extents of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getHalfSize() {
        return new Double3(this.hsX, this.hsY, this.hsZ);
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
    public boolean intersectsOBB(DoubleOBB o) {
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
        double _t55 = Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2));
        double _t27 = Math.fma(oUXz, this.uYz, Math.fma(oUXx, this.uYx, oUXy * this.uYy));
        double _t48 = Math.abs(_t27) + 1.0E-8;
        double _t28 = Math.fma(oUYz, this.uYz, Math.fma(oUYx, this.uYx, oUYy * this.uYy));
        double _t49 = Math.abs(_t28) + 1.0E-8;
        double _t29 = Math.fma(oUZz, this.uYz, Math.fma(oUZx, this.uYx, oUZy * this.uYy));
        double _t50 = Math.abs(_t29) + 1.0E-8;
        if (!(Math.abs(_t55) <= Math.fma(oHsX, _t48, Math.fma(oHsY, _t49, Math.fma(oHsZ, _t50, this.hsY))))) return false;
        double _t56 = Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2));
        double _t30 = Math.fma(oUXz, this.uZz, Math.fma(oUXx, this.uZx, oUXy * this.uZy));
        double _t51 = Math.abs(_t30) + 1.0E-8;
        double _t31 = Math.fma(oUYz, this.uZz, Math.fma(oUYx, this.uZx, oUYy * this.uZy));
        double _t52 = Math.abs(_t31) + 1.0E-8;
        double _t32 = Math.fma(oUZz, this.uZz, Math.fma(oUZx, this.uZx, oUZy * this.uZy));
        double _t53 = Math.abs(_t32) + 1.0E-8;
        if (!(Math.abs(_t56) <= Math.fma(oHsX, _t51, Math.fma(oHsY, _t52, Math.fma(oHsZ, _t53, this.hsZ))))) return false;
        if (!(Math.abs(Math.fma(_t30, _t56, Math.fma(_t24, _t54, _t27 * _t55))) <= Math.fma(this.hsX, _t45, Math.fma(this.hsY, _t48, Math.fma(this.hsZ, _t51, oHsX))))) return false;
        if (!(Math.abs(Math.fma(_t31, _t56, Math.fma(_t25, _t54, _t28 * _t55))) <= Math.fma(this.hsX, _t46, Math.fma(this.hsY, _t49, Math.fma(this.hsZ, _t52, oHsY))))) return false;
        if (!(Math.abs(Math.fma(_t32, _t56, Math.fma(_t26, _t54, _t29 * _t55))) <= Math.fma(this.hsX, _t47, Math.fma(this.hsY, _t50, Math.fma(this.hsZ, _t53, oHsZ))))) return false;
        if (!(Math.abs(Math.fma(_t27, _t56, -(_t30 * _t55))) <= Math.fma(oHsY, _t47, oHsZ * _t46) + Math.fma(this.hsY, _t51, this.hsZ * _t48))) return false;
        if (!(Math.abs(Math.fma(_t28, _t56, -(_t31 * _t55))) <= Math.fma(oHsX, _t47, oHsZ * _t45) + Math.fma(this.hsY, _t52, this.hsZ * _t49))) return false;
        if (!(Math.abs(Math.fma(_t29, _t56, -(_t32 * _t55))) <= Math.fma(oHsX, _t46, oHsY * _t45) + Math.fma(this.hsY, _t53, this.hsZ * _t50))) return false;
        if (!(Math.abs(Math.fma(_t30, _t54, -(_t24 * _t56))) <= Math.fma(oHsY, _t50, oHsZ * _t49) + Math.fma(this.hsX, _t51, this.hsZ * _t45))) return false;
        if (!(Math.abs(Math.fma(_t31, _t54, -(_t25 * _t56))) <= Math.fma(oHsX, _t50, oHsZ * _t48) + Math.fma(this.hsX, _t52, this.hsZ * _t46))) return false;
        if (!(Math.abs(Math.fma(_t32, _t54, -(_t26 * _t56))) <= Math.fma(oHsX, _t49, oHsY * _t48) + Math.fma(this.hsX, _t53, this.hsZ * _t47))) return false;
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

    /**
     * {@return a copy with the {@code cX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code cX} component
     */
    public DoubleOBB withCX(double v) {
        return new DoubleOBB(v, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code cY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code cY} component
     */
    public DoubleOBB withCY(double v) {
        return new DoubleOBB(cX, v, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code cZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code cZ} component
     */
    public DoubleOBB withCZ(double v) {
        return new DoubleOBB(cX, cY, v, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uXx} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uXx} component
     */
    public DoubleOBB withUXx(double v) {
        return new DoubleOBB(cX, cY, cZ, v, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uXy} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uXy} component
     */
    public DoubleOBB withUXy(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, v, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uXz} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uXz} component
     */
    public DoubleOBB withUXz(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, v, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uYx} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uYx} component
     */
    public DoubleOBB withUYx(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, v, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uYy} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uYy} component
     */
    public DoubleOBB withUYy(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, v, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uYz} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uYz} component
     */
    public DoubleOBB withUYz(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, v, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uZx} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uZx} component
     */
    public DoubleOBB withUZx(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, v, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uZy} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uZy} component
     */
    public DoubleOBB withUZy(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, v, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uZz} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uZz} component
     */
    public DoubleOBB withUZz(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, v, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code hsX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code hsX} component
     */
    public DoubleOBB withHsX(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, v, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code hsY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code hsY} component
     */
    public DoubleOBB withHsY(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, v, hsZ);
    }

    /**
     * {@return a copy with the {@code hsZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code hsZ} component
     */
    public DoubleOBB withHsZ(double v) {
        return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, v);
    }

    @Override public String toString() {
        return "DoubleOBB(" + cX() + ", " + cY() + ", " + cZ() + ", " + uXx() + ", " + uXy() + ", " + uXz() + ", " + uYx() + ", " + uYy() + ", " + uYz() + ", " + uZx() + ", " + uZy() + ", " + uZz() + ", " + hsX() + ", " + hsY() + ", " + hsZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleOBB)) return false;
        DoubleOBB o = (DoubleOBB) obj;
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

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
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

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
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
    public boolean equalsEpsilon(DoubleOBB other, double epsilon) {
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB load(double[] src, int offset) {
        double _c0 = src[offset + 0];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        double _c9 = src[offset + 9];
        double _c10 = src[offset + 10];
        double _c11 = src[offset + 11];
        double _c12 = src[offset + 12];
        double _c13 = src[offset + 13];
        double _c14 = src[offset + 14];
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB load(double[] src) { return load(src, 0); }

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
        return storeAbsolute(buf.position(), buf);
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
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 15);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB load(DoubleBuffer buf) {
        return loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleOBB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleOBB r = loadAbsolute(pos, buf);
        buf.position(pos + 15);
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
        return storeAbsolute(buf.position(), buf);
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
        if (buf.remaining() < 120) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 120);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB load(ByteBuffer buf) {
        return loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleOBB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleOBB r = loadAbsolute(pos, buf);
        buf.position(pos + 120);
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
    public DoubleOBB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleOBB} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleOBB loadUnsafe(long address) {
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB load(float[] src, int offset) {
        double _c0 = src[offset + 0];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        double _c9 = src[offset + 9];
        double _c10 = src[offset + 10];
        double _c11 = src[offset + 11];
        double _c12 = src[offset + 12];
        double _c13 = src[offset + 13];
        double _c14 = src[offset + 14];
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB load(float[] src) { return load(src, 0); }

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
        return storeAbsolute(buf.position(), buf);
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
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 15);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB load(FloatBuffer buf) {
        return loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleOBB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleOBB r = loadAbsolute(pos, buf);
        buf.position(pos + 15);
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
        return storeFloatAbsolute(buf.position(), buf);
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
        if (buf.remaining() < 60) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeFloatAbsolute(pos, buf);
        buf.position(pos + 60);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB loadFloat(ByteBuffer buf) {
        return loadFloatAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     */
    public static DoubleOBB loadFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleOBB loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleOBB r = loadFloatAbsolute(pos, buf);
        buf.position(pos + 60);
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
    public DoubleOBB storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleOBB} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleOBB loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
