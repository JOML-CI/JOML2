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
 * Immutable oriented bounding box of single-precision {@code float} components, declared as a value
 * record.
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
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record FloatOBB(float cX, float cY, float cZ, float uXx, float uXy, float uXz, float uYx, float uYy, float uYz, float uZx, float uZy, float uZz, float hsX, float hsY, float hsZ) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 60;

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
    public FloatOBB(float cX, float cY, float cZ, float uXx, float uXy, float uXz, float uYx, float uYy, float uYz, float uZx, float uZy, float uZz, float hsX, float hsY, float hsZ) {
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
    public FloatOBB() {
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
    public FloatOBB(Float3 center, Float3 axisX, Float3 axisY, Float3 axisZ, Float3 halfSize) {
        this(center.x(), center.y(), center.z(), axisX.x(), axisX.y(), axisX.z(), axisY.x(), axisY.y(), axisY.z(), axisZ.x(), axisZ.y(), axisZ.z(), halfSize.x(), halfSize.y(), halfSize.z());
    }

    /** {@return the {@code cX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float cX() { return cX; }
    /** {@return the {@code cY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float cY() { return cY; }
    /** {@return the {@code cZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float cZ() { return cZ; }
    /** {@return the {@code uXx} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uXx() { return uXx; }
    /** {@return the {@code uXy} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uXy() { return uXy; }
    /** {@return the {@code uXz} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uXz() { return uXz; }
    /** {@return the {@code uYx} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uYx() { return uYx; }
    /** {@return the {@code uYy} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uYy() { return uYy; }
    /** {@return the {@code uYz} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uYz() { return uYz; }
    /** {@return the {@code uZx} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uZx() { return uZx; }
    /** {@return the {@code uZy} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uZy() { return uZy; }
    /** {@return the {@code uZz} component} <p>Valid input: any value, NaN and the infinities included. */
    public float uZz() { return uZz; }
    /** {@return the {@code hsX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float hsX() { return hsX; }
    /** {@return the {@code hsY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float hsY() { return hsY; }
    /** {@return the {@code hsZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float hsZ() { return hsZ; }

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
    public FloatOBB set(Float3 center, Float3 axisX, Float3 axisY, Float3 axisZ, Float3 halfSize) {
        return new FloatOBB(center, axisX, axisY, axisZ, halfSize);
    }


    /**
     * Create a new oriented bounding box from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return the resulting oriented bounding box
     */
    public FloatOBB set(FloatOBB v) {
        float vCX = v.cX();
        float vCY = v.cY();
        float vCZ = v.cZ();
        float vUXx = v.uXx();
        float vUXy = v.uXy();
        float vUXz = v.uXz();
        float vUYx = v.uYx();
        float vUYy = v.uYy();
        float vUYz = v.uYz();
        float vUZx = v.uZx();
        float vUZy = v.uZy();
        float vUZz = v.uZz();
        float vHsX = v.hsX();
        float vHsY = v.hsY();
        float vHsZ = v.hsZ();
        return new FloatOBB(vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ);
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
    public FloatOBB set(float vCX, float vCY, float vCZ, float vUXx, float vUXy, float vUXz, float vUYx, float vUYy, float vUYz, float vUZx, float vUZy, float vUZz, float vHsX, float vHsY, float vHsZ) {
        return new FloatOBB(vCX, vCY, vCZ, vUXx, vUXy, vUXz, vUYx, vUYy, vUYz, vUZx, vUZy, vUZz, vHsX, vHsY, vHsZ);
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
    public FloatOBB setAxes(Float3 axisX, Float3 axisY, Float3 axisZ) {
        float axisXX = axisX.x();
        float axisXY = axisX.y();
        float axisXZ = axisX.z();
        float axisYX = axisY.x();
        float axisYY = axisY.y();
        float axisYZ = axisY.z();
        float axisZX = axisZ.x();
        float axisZY = axisZ.y();
        float axisZZ = axisZ.z();
        return new FloatOBB(this.cX, this.cY, this.cZ, axisXX, axisXY, axisXZ, axisYX, axisYY, axisYZ, axisZX, axisZY, axisZZ, this.hsX, this.hsY, this.hsZ);
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
    public FloatOBB setAxes(float axisXX, float axisXY, float axisXZ, float axisYX, float axisYY, float axisYZ, float axisZX, float axisZY, float axisZZ) {
        return new FloatOBB(this.cX, this.cY, this.cZ, axisXX, axisXY, axisXZ, axisYX, axisYY, axisYZ, axisZX, axisZY, axisZZ, this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Set the center of this oriented bounding box to {@code c}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @return the resulting oriented bounding box
     */
    public FloatOBB setCenter(Float3 c) {
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        return new FloatOBB(cX, cY, cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, this.hsX, this.hsY, this.hsZ);
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
    public FloatOBB setCenter(float cX, float cY, float cZ) {
        return new FloatOBB(cX, cY, cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, this.hsX, this.hsY, this.hsZ);
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
    public FloatOBB setHalfSize(Float3 h) {
        float hX = h.x();
        float hY = h.y();
        float hZ = h.z();
        return new FloatOBB(this.cX, this.cY, this.cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, hX, hY, hZ);
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
    public FloatOBB setHalfSize(float hX, float hY, float hZ) {
        return new FloatOBB(this.cX, this.cY, this.cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, hX, hY, hZ);
    }


    /**
     * Reset the orientation of this oriented bounding box to identity, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting oriented bounding box
     */
    public FloatOBB setIdentityOrientation() {
        return new FloatOBB(this.cX, this.cY, this.cZ, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, this.hsX, this.hsY, this.hsZ);
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
    public FloatOBB setOrientation(FloatQuat q) {
        float qX = q.x();
        float qY = q.y();
        float qZ = q.z();
        float qW = q.w();
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        return new FloatOBB(this.cX, this.cY, this.cZ, Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f), 2.0f * Math.fma(qX, qY, _t1), 2.0f * Math.fma(qX, qZ, -_t2), 2.0f * Math.fma(qX, qY, -_t1), Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f), 2.0f * Math.fma(qX, qW, qY * qZ), 2.0f * Math.fma(qX, qZ, _t2), 2.0f * Math.fma(qY, qZ, -(qX * qW)), Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f), this.hsX, this.hsY, this.hsZ);
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
    public FloatOBB setOrientation(float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        return new FloatOBB(this.cX, this.cY, this.cZ, Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f), 2.0f * Math.fma(qX, qY, _t1), 2.0f * Math.fma(qX, qZ, -_t2), 2.0f * Math.fma(qX, qY, -_t1), Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f), 2.0f * Math.fma(qX, qW, qY * qZ), 2.0f * Math.fma(qX, qZ, _t2), 2.0f * Math.fma(qY, qZ, -(qX * qW)), Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f), this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Convert this oriented bounding box to {@code double} precision, returning the result as a new
     * instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleOBB} holding the result
     */
    public DoubleOBB toDouble() {
        return new DoubleOBB(this.cX, this.cY, this.cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Create an identity oriented bounding box.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting oriented bounding box
     */
    public static FloatOBB makeIdentity() {
        return new FloatOBB(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
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
    public FloatOBB transform(Float3x4 m) {
        float[] _bundle0 = transform_sf6171e49_1(m);
        float[] _bundle1 = transform_sf6171e49_2(m, _bundle0);
        if (!(_bundle1[8] > Math.fma(Math.fma(_bundle1[2], _bundle1[2], Math.fma(_bundle1[1], _bundle1[1], _bundle1[3] * _bundle1[3])), _bundle1[7] * 1.4551915E-11f, 1.1754944E-38f) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate(m);
        float[] _bundle2 = transform_sf6171e49_3(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_sf6171e49_4(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /** Part 1 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_sf6171e49_1(Float3x4 m) {
        return new float[] {Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy))};
    }

    /** Part 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_sf6171e49_2(Float3x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy));
        float _t21 = Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy));
        float _t22 = Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy));
        float _t23 = Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy));
        float _t37 = Math.fma(_t21, _t19, -(_t18 * _t22));
        float _t38 = Math.fma(_t18, _t23, -(_t21 * _t20));
        float _t39 = Math.fma(_t20, _t22, -(_t23 * _t19));
        return new float[] {_t20, _t21, _t22, _t23, _t37, _t38, _t39, Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20)), Math.fma(_t38, _t38, Math.fma(_t37, _t37, _t39 * _t39))};
    }

    /** Part 3 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_sf6171e49_3(Float3x4 m, float[] _bundle0, float[] _bundle1) {
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[8]));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[7]));
        float _t45 = _bundle1[0] * _t42;
        float _t46 = _bundle0[1] * _t42;
        float _t53 = _bundle1[4] * _t52;
        float _t54 = _bundle1[5] * _t52;
        return new float[] {Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _bundle0[0] * _t42, _t45, _t46, _t53, _t54, _bundle1[6] * _t52, Math.fma(_t46, _t53, -(_t45 * _t54))};
    }

    /** Part 4 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_sf6171e49_4(Float3x4 m, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62) {
        float _t63 = Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = Math.fma(_t45, _t55, -(_t44 * _t53));
        float _sfx0 = Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03())));
        float _sfx1 = Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13())));
        float _sfx2 = Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23())));
        return new float[] {_t63, _t64, _sfx0, _sfx1, _sfx2, _t44, _t45, _t46, _t62, _t63, _t64, _t55, _t53, _t54};
    }

    /**
     * Part 5 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_5(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62, float _t63, float _t64) {
        float _sfx12 = Math.fma(this.hsZ, java.lang.Math.abs(Math.fma(_t24, _t46, Math.fma(_t25, _t44, _t26 * _t45))), Math.fma(this.hsX, java.lang.Math.abs(Math.fma(_t19, _t46, Math.fma(_t18, _t44, _t20 * _t45))), this.hsY * java.lang.Math.abs(Math.fma(_t22, _t46, Math.fma(_t21, _t44, _t23 * _t45)))));
        float _sfx13 = Math.fma(this.hsZ, java.lang.Math.abs(Math.fma(_t24, _t64, Math.fma(_t25, _t62, _t26 * _t63))), Math.fma(this.hsX, java.lang.Math.abs(Math.fma(_t19, _t64, Math.fma(_t18, _t62, _t20 * _t63))), this.hsY * java.lang.Math.abs(Math.fma(_t22, _t64, Math.fma(_t21, _t62, _t23 * _t63)))));
        float _sfx14 = Math.fma(this.hsZ, java.lang.Math.abs(Math.fma(_t24, _t54, Math.fma(_t25, _t55, _t26 * _t53))), Math.fma(this.hsX, java.lang.Math.abs(Math.fma(_t19, _t54, Math.fma(_t18, _t55, _t20 * _t53))), this.hsY * java.lang.Math.abs(Math.fma(_t22, _t54, Math.fma(_t21, _t55, _t23 * _t53)))));
        return new float[] {_sfx12, _sfx13, _sfx14};
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate(Float3x4 m) {
        float[] _bundle0 = transform_degenerate_s66aa5e50_1(m);
        float[] _bundle1 = transform_degenerate_s66aa5e50_2(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3(_bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8]);
        float _t70 = _bundle2[9] * _bundle2[7];
        float _t71 = _bundle2[9] * _bundle2[8];
        float _t72 = _bundle2[9] * _bundle2[6];
        float _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0f;
            _t91 = -_t70;
        } else {
            _t83 = 0.0f;
            _t84 = -_t71;
            _t91 = _t72;
        }
        float _t88 = -Math.fma(_t72, _bundle2[0], Math.fma(_t70, _bundle2[1], _t71 * _bundle2[2]));
        float _t89 = -Math.fma(_t72, _bundle2[3], Math.fma(_t70, _bundle2[4], _t71 * _bundle2[5]));
        return transform_degenerate_s66aa5e50_7(m, _bundle0, _bundle1, _bundle2, _t70, _t71, _t72, _t83, _t84, _t91, -Math.fma(_t72, _bundle1[6], Math.fma(_t70, _bundle1[7], _t71 * _bundle1[8])), Math.fma(_t88, _t72, _bundle2[0]), Math.fma(_t88, _t70, _bundle2[1]), Math.fma(_t88, _t71, _bundle2[2]), Math.fma(_t89, _t72, _bundle2[3]), Math.fma(_t89, _t70, _bundle2[4]), Math.fma(_t89, _t71, _bundle2[5]));
    }

    /** Part 1 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] transform_degenerate_s66aa5e50_1(Float3x4 m) {
        return new float[] {Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy)), Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy))};
    }

    /** Part 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] transform_degenerate_s66aa5e50_2(Float3x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = _bundle0[2];
        float _t27 = unitScale(_t19, _t20, _t18);
        return new float[] {Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy)), Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy)), Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy)), Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _t18 * _t27, _t19 * _t27, _t20 * _t27};
    }

    /**
     * Part 3 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_3(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t39, float _t40, float _t41) {
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t25, _t26, _t24);
        float _t42 = _t21 * _t28;
        float _t43 = _t22 * _t28;
        float _t44 = _t23 * _t28;
        float _t45 = _t24 * _t29;
        float _t46 = _t25 * _t29;
        float _t47 = _t26 * _t29;
        float _t63, _t64, _t65;
        if (Math.fma(_t39, _t39, Math.fma(_t40, _t40, _t41 * _t41)) > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44)) > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47)) > 0.0f) {
                    _t63 = _t45;
                    _t64 = _t46;
                    _t65 = _t47;
                } else {
                    _t63 = 0.0f;
                    _t64 = 1.0f;
                    _t65 = 0.0f;
                }
            }
        }
        return new float[] {_t42, _t43, _t44, _t45, _t46, _t47, _t63, _t64, _t65, (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t63, _t63, Math.fma(_t64, _t64, _t65 * _t65))))};
    }

    /**
     * Part 4 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_4(float[] _bundle2, float _t129, float _t130, float _t131) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {Math.fma(_t72, _t129, -(_t70 * _t130)), Math.fma(_t70, _t131, -(_t71 * _t129)), Math.fma(_t71, _t130, -(_t72 * _t131))};
    }

    /** Part 5 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] transform_degenerate_s66aa5e50_5(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03()))), Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13()))), Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23()))), _t70, _t71, _t72, Math.fma(this.hsZ, java.lang.Math.abs(Math.fma(_bundle1[3], _t72, Math.fma(_bundle1[4], _t70, _bundle1[5] * _t71))), Math.fma(this.hsX, java.lang.Math.abs(Math.fma(_bundle0[0], _t72, Math.fma(_bundle0[1], _t70, _bundle0[2] * _t71))), this.hsY * java.lang.Math.abs(Math.fma(_bundle1[0], _t72, Math.fma(_bundle1[1], _t70, _bundle1[2] * _t71)))))};
    }

    /**
     * Part 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s66aa5e50_6(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t130, float _t129, float _t131, float _t138, float _t139, float _t140, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4, float _sfx5, float _sfx12) {
        float _sfx13 = Math.fma(this.hsZ, java.lang.Math.abs(Math.fma(_t24, _t130, Math.fma(_t25, _t129, _t26 * _t131))), Math.fma(this.hsX, java.lang.Math.abs(Math.fma(_t18, _t130, Math.fma(_t19, _t129, _t20 * _t131))), this.hsY * java.lang.Math.abs(Math.fma(_t21, _t130, Math.fma(_t22, _t129, _t23 * _t131)))));
        float _sfx14 = Math.fma(this.hsZ, java.lang.Math.abs(Math.fma(_t24, _t139, Math.fma(_t25, _t140, _t26 * _t138))), Math.fma(this.hsX, java.lang.Math.abs(Math.fma(_t18, _t139, Math.fma(_t19, _t140, _t20 * _t138))), this.hsY * java.lang.Math.abs(Math.fma(_t21, _t139, Math.fma(_t22, _t140, _t23 * _t138)))));
        float _el6 = (_t129);
        float _el7 = (_t131);
        float _el8 = (_t130);
        float _el9 = (_t140);
        float _el10 = (_t138);
        float _el11 = (_t139);
        return new FloatOBB((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), _el6, _el7, _el8, _el9, _el10, _el11, (_sfx12), (_sfx13), (_sfx14));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s66aa5e50_7(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t90, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97) {
        float _t98 = Math.fma(_t90, _t72, _bundle1[6]);
        float _t99 = Math.fma(_t90, _t70, _bundle1[7]);
        float _t100 = Math.fma(_t90, _t71, _bundle1[8]);
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83))));
        float _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = java.lang.Math.max(_t114, _t115);
        float _t121 = java.lang.Math.max(_t117, _t116);
        float _t122 = (1.0f / (float) java.lang.Math.sqrt(_t121));
        float _t129, _t130, _t131;
        if (_t121 > 1.4551915E-11f) {
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
        float[] _bundle3 = transform_degenerate_s66aa5e50_4(_bundle2, _t129, _t130, _t131);
        return transform_degenerate_s66aa5e50_8(m, _bundle0, _bundle1, _bundle2, _t129, _t130, _t131, _bundle3);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s66aa5e50_8(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t129, float _t130, float _t131, float[] _bundle3) {
        float[] _bundle4 = transform_degenerate_s66aa5e50_5(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6(_bundle0[0], _bundle0[1], _bundle0[2], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _t130, _t129, _t131, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
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
    public FloatOBB transform(Float4x4 m) {
        float[] _bundle0 = transform_s2253001c_1(m);
        float[] _bundle1 = transform_s2253001c_2(m, _bundle0);
        if (!(_bundle1[8] > Math.fma(Math.fma(_bundle1[2], _bundle1[2], Math.fma(_bundle1[1], _bundle1[1], _bundle1[3] * _bundle1[3])), _bundle1[7] * 1.4551915E-11f, 1.1754944E-38f) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate(m);
        float[] _bundle2 = transform_s2253001c_3(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_s2253001c_4(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /** Part 1 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_s2253001c_1(Float4x4 m) {
        return new float[] {Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy))};
    }

    /** Part 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_s2253001c_2(Float4x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy));
        float _t21 = Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy));
        float _t22 = Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy));
        float _t23 = Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy));
        float _t37 = Math.fma(_t21, _t19, -(_t18 * _t22));
        float _t38 = Math.fma(_t18, _t23, -(_t21 * _t20));
        float _t39 = Math.fma(_t20, _t22, -(_t23 * _t19));
        return new float[] {_t20, _t21, _t22, _t23, _t37, _t38, _t39, Math.fma(_t19, _t19, Math.fma(_t18, _t18, _t20 * _t20)), Math.fma(_t38, _t38, Math.fma(_t37, _t37, _t39 * _t39))};
    }

    /** Part 3 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_s2253001c_3(Float4x4 m, float[] _bundle0, float[] _bundle1) {
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[8]));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[7]));
        float _t45 = _bundle1[0] * _t42;
        float _t46 = _bundle0[1] * _t42;
        float _t53 = _bundle1[4] * _t52;
        float _t54 = _bundle1[5] * _t52;
        return new float[] {Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _bundle0[0] * _t42, _t45, _t46, _t53, _t54, _bundle1[6] * _t52, Math.fma(_t46, _t53, -(_t45 * _t54))};
    }

    /** Part 4 of {@code transform}, split to fit the inline budget; reached only through it. */
    private float[] transform_s2253001c_4(Float4x4 m, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62) {
        float _t63 = Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = Math.fma(_t45, _t55, -(_t44 * _t53));
        float _sfx0 = Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03())));
        float _sfx1 = Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13())));
        float _sfx2 = Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23())));
        return new float[] {_t63, _t64, _sfx0, _sfx1, _sfx2, _t44, _t45, _t46, _t62, _t63, _t64, _t55, _t53, _t54};
    }


    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate(Float4x4 m) {
        float[] _bundle0 = transform_degenerate_s26673445_1(m);
        float[] _bundle1 = transform_degenerate_s26673445_2(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3(_bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8]);
        float _t70 = _bundle2[9] * _bundle2[7];
        float _t71 = _bundle2[9] * _bundle2[8];
        float _t72 = _bundle2[9] * _bundle2[6];
        float _t83, _t84, _t91;
        if (java.lang.Math.abs(_t72) < java.lang.Math.abs(_t70)) {
            _t83 = _t71;
            _t84 = 0.0f;
            _t91 = -_t70;
        } else {
            _t83 = 0.0f;
            _t84 = -_t71;
            _t91 = _t72;
        }
        float _t88 = -Math.fma(_t72, _bundle2[0], Math.fma(_t70, _bundle2[1], _t71 * _bundle2[2]));
        float _t89 = -Math.fma(_t72, _bundle2[3], Math.fma(_t70, _bundle2[4], _t71 * _bundle2[5]));
        return transform_degenerate_s26673445_7(m, _bundle0, _bundle1, _bundle2, _t70, _t71, _t72, _t83, _t84, _t91, -Math.fma(_t72, _bundle1[6], Math.fma(_t70, _bundle1[7], _t71 * _bundle1[8])), Math.fma(_t88, _t72, _bundle2[0]), Math.fma(_t88, _t70, _bundle2[1]), Math.fma(_t88, _t71, _bundle2[2]), Math.fma(_t89, _t72, _bundle2[3]), Math.fma(_t89, _t70, _bundle2[4]), Math.fma(_t89, _t71, _bundle2[5]));
    }

    /** Part 1 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] transform_degenerate_s26673445_1(Float4x4 m) {
        return new float[] {Math.fma(m.m22(), this.uXz, Math.fma(m.m20(), this.uXx, m.m21() * this.uXy)), Math.fma(m.m02(), this.uXz, Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), Math.fma(m.m12(), this.uXz, Math.fma(m.m10(), this.uXx, m.m11() * this.uXy))};
    }

    /** Part 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] transform_degenerate_s26673445_2(Float4x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = _bundle0[2];
        float _t27 = unitScale(_t19, _t20, _t18);
        return new float[] {Math.fma(m.m22(), this.uYz, Math.fma(m.m20(), this.uYx, m.m21() * this.uYy)), Math.fma(m.m02(), this.uYz, Math.fma(m.m00(), this.uYx, m.m01() * this.uYy)), Math.fma(m.m12(), this.uYz, Math.fma(m.m10(), this.uYx, m.m11() * this.uYy)), Math.fma(m.m22(), this.uZz, Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), Math.fma(m.m02(), this.uZz, Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), Math.fma(m.m12(), this.uZz, Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _t18 * _t27, _t19 * _t27, _t20 * _t27};
    }

    /** Part 5 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] transform_degenerate_s26673445_5(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {Math.fma(m.m00(), this.cX, Math.fma(m.m01(), this.cY, Math.fma(m.m02(), this.cZ, m.m03()))), Math.fma(m.m10(), this.cX, Math.fma(m.m11(), this.cY, Math.fma(m.m12(), this.cZ, m.m13()))), Math.fma(m.m20(), this.cX, Math.fma(m.m21(), this.cY, Math.fma(m.m22(), this.cZ, m.m23()))), _t70, _t71, _t72, Math.fma(this.hsZ, java.lang.Math.abs(Math.fma(_bundle1[3], _t72, Math.fma(_bundle1[4], _t70, _bundle1[5] * _t71))), Math.fma(this.hsX, java.lang.Math.abs(Math.fma(_bundle0[0], _t72, Math.fma(_bundle0[1], _t70, _bundle0[2] * _t71))), this.hsY * java.lang.Math.abs(Math.fma(_bundle1[0], _t72, Math.fma(_bundle1[1], _t70, _bundle1[2] * _t71)))))};
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s26673445_7(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t70, float _t71, float _t72, float _t83, float _t84, float _t91, float _t90, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97) {
        float _t98 = Math.fma(_t90, _t72, _bundle1[6]);
        float _t99 = Math.fma(_t90, _t70, _bundle1[7]);
        float _t100 = Math.fma(_t90, _t71, _bundle1[8]);
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t84, _t84, Math.fma(_t91, _t91, _t83 * _t83))));
        float _t114 = Math.fma(_t92, _t92, Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = Math.fma(_t95, _t95, Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = java.lang.Math.max(_t114, _t115);
        float _t121 = java.lang.Math.max(_t117, _t116);
        float _t122 = (1.0f / (float) java.lang.Math.sqrt(_t121));
        float _t129, _t130, _t131;
        if (_t121 > 1.4551915E-11f) {
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
        float[] _bundle3 = transform_degenerate_s66aa5e50_4(_bundle2, _t129, _t130, _t131);
        return transform_degenerate_s26673445_8(m, _bundle0, _bundle1, _bundle2, _t129, _t130, _t131, _bundle3);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s26673445_8(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t129, float _t130, float _t131, float[] _bundle3) {
        float[] _bundle4 = transform_degenerate_s26673445_5(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6(_bundle0[0], _bundle0[1], _bundle0[2], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _t130, _t129, _t131, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
    }


    /**
     * Translate this oriented bounding box by {@code delta}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @return the resulting oriented bounding box
     */
    public FloatOBB translate(Float3 delta) {
        float deltaX = delta.x();
        float deltaY = delta.y();
        float deltaZ = delta.z();
        return new FloatOBB(deltaX + this.cX, deltaY + this.cY, deltaZ + this.cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, this.hsX, this.hsY, this.hsZ);
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
    public FloatOBB translate(float deltaX, float deltaY, float deltaZ) {
        return new FloatOBB(deltaX + this.cX, deltaY + this.cY, deltaZ + this.cZ, this.uXx, this.uXy, this.uXz, this.uYx, this.uYy, this.uYz, this.uZx, this.uZy, this.uZz, this.hsX, this.hsY, this.hsZ);
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Float3 closestPointToPoint(Float3 p) {
        float _t3 = p.z() - this.cZ;
        float _t4 = p.x() - this.cX;
        float _t5 = p.y() - this.cY;
        float _t18 = java.lang.Math.max(-this.hsX, java.lang.Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = java.lang.Math.max(-this.hsY, java.lang.Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = java.lang.Math.max(-this.hsZ, java.lang.Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        return new Float3(Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX))), Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY))), Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ))));
    }


    /**
     * Compute the point of this oriented bounding box closest to the given point, i.e. the point
     * expressed in the box's local frame, clamped per axis to the box's half extents and mapped
     * back to world space. For a point inside or on the box, the result is the point itself (up to
     * rounding). Assumes the box's axes are orthonormal.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @return the resulting vector
     */
    public Float3 closestPointToPoint(float pX, float pY, float pZ) {
        float _t3 = pZ - this.cZ;
        float _t4 = pX - this.cX;
        float _t5 = pY - this.cY;
        float _t18 = java.lang.Math.max(-this.hsX, java.lang.Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = java.lang.Math.max(-this.hsY, java.lang.Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = java.lang.Math.max(-this.hsZ, java.lang.Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
        return new Float3(Math.fma(this.uXx, _t18, Math.fma(this.uYx, _t19, Math.fma(this.uZx, _t20, this.cX))), Math.fma(this.uXy, _t18, Math.fma(this.uYy, _t19, Math.fma(this.uZy, _t20, this.cY))), Math.fma(this.uXz, _t18, Math.fma(this.uYz, _t19, Math.fma(this.uZz, _t20, this.cZ))));
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
    public boolean containsPoint(Float3 p) {
        float _t0 = p.z() - this.cZ;
        float _t1 = p.x() - this.cX;
        float _t2 = p.y() - this.cY;
        if (!(java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) <= this.hsX)) return false;
        if (!(java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) <= this.hsY)) return false;
        return java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) <= this.hsZ;
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
    public boolean containsPoint(float pX, float pY, float pZ) {
        float _t0 = pZ - this.cZ;
        float _t1 = pX - this.cX;
        float _t2 = pY - this.cY;
        if (!(java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) <= this.hsX)) return false;
        if (!(java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) <= this.hsY)) return false;
        return java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) <= this.hsZ;
    }


    /**
     * Compute the squared distance between this oriented bounding box and the given point,
     * evaluated in the box's local frame; zero for a point inside or on the box. Assumes the box's
     * axes are orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this oriented bounding box and the given point,
     *        evaluated in the box's local frame; zero for a point inside or on the box. Assumes the
     *        box's axes are orthonormal
     */
    public float distanceSquaredToPoint(Float3 p) {
        float _t0 = p.z() - this.cZ;
        float _t1 = p.x() - this.cX;
        float _t2 = p.y() - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
        return Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
    }


    /**
     * Compute the squared distance between this oriented bounding box and the given point,
     * evaluated in the box's local frame; zero for a point inside or on the box. Assumes the box's
     * axes are orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
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
    public float distanceSquaredToPoint(float pX, float pY, float pZ) {
        float _t0 = pZ - this.cZ;
        float _t1 = pX - this.cX;
        float _t2 = pY - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
        return Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
    }


    /**
     * Compute the distance between this oriented bounding box and the given point, evaluated in the
     * box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     * orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
     *
     * @param p the point to measure the distance to
     * @return the distance between this oriented bounding box and the given point, evaluated in the
     *        box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     *        orthonormal
     */
    public float distanceToPoint(Float3 p) {
        float _t0 = p.z() - this.cZ;
        float _t1 = p.x() - this.cX;
        float _t2 = p.y() - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
        return (float) java.lang.Math.sqrt(Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
    }


    /**
     * Compute the distance between this oriented bounding box and the given point, evaluated in the
     * box's local frame; zero for a point inside or on the box. Assumes the box's axes are
     * orthonormal.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the axes of
     * this oriented bounding box must be orthonormal.
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
    public float distanceToPoint(float pX, float pY, float pZ) {
        float _t0 = pZ - this.cZ;
        float _t1 = pX - this.cX;
        float _t2 = pY - this.cY;
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = java.lang.Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
        return (float) java.lang.Math.sqrt(Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
    }


    /**
     * Get the local {@code X} axis of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getAxisX() {
        return new Float3(this.uXx, this.uXy, this.uXz);
    }


    /**
     * Get the local {@code Y} axis of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getAxisY() {
        return new Float3(this.uYx, this.uYy, this.uYz);
    }


    /**
     * Get the local {@code Z} axis of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getAxisZ() {
        return new Float3(this.uZx, this.uZy, this.uZz);
    }


    /**
     * Get the center of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getCenter() {
        return new Float3(this.cX, this.cY, this.cZ);
    }


    /**
     * Get the half extents of this oriented bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getHalfSize() {
        return new Float3(this.hsX, this.hsY, this.hsZ);
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
    public boolean intersectsOBB(FloatOBB o) {
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
    public boolean intersectsOBB(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float _t0 = oCZ - this.cZ;
        float _t1 = oCX - this.cX;
        float _t2 = oCY - this.cY;
        float _t54 = Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2));
        float _t24 = Math.fma(oUXz, this.uXz, Math.fma(oUXx, this.uXx, oUXy * this.uXy));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t25 = Math.fma(oUYz, this.uXz, Math.fma(oUYx, this.uXx, oUYy * this.uXy));
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t26 = Math.fma(oUZz, this.uXz, Math.fma(oUZx, this.uXx, oUZy * this.uXy));
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t54) <= Math.fma(oHsX, _t45, Math.fma(oHsY, _t46, Math.fma(oHsZ, _t47, this.hsX))))) return false;
        float _t55 = Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2));
        float _t27 = Math.fma(oUXz, this.uYz, Math.fma(oUXx, this.uYx, oUXy * this.uYy));
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t28 = Math.fma(oUYz, this.uYz, Math.fma(oUYx, this.uYx, oUYy * this.uYy));
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t29 = Math.fma(oUZz, this.uYz, Math.fma(oUZx, this.uYx, oUZy * this.uYy));
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t55) <= Math.fma(oHsX, _t48, Math.fma(oHsY, _t49, Math.fma(oHsZ, _t50, this.hsY))))) return false;
        float _t56 = Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2));
        float _t30 = Math.fma(oUXz, this.uZz, Math.fma(oUXx, this.uZx, oUXy * this.uZy));
        float _t51 = java.lang.Math.abs(_t30) + 1.0E-5f;
        float _t31 = Math.fma(oUYz, this.uZz, Math.fma(oUYx, this.uZx, oUYy * this.uZy));
        float _t52 = java.lang.Math.abs(_t31) + 1.0E-5f;
        float _t32 = Math.fma(oUZz, this.uZz, Math.fma(oUZx, this.uZx, oUZy * this.uZy));
        float _t53 = java.lang.Math.abs(_t32) + 1.0E-5f;
        if (!(java.lang.Math.abs(_t56) <= Math.fma(oHsX, _t51, Math.fma(oHsY, _t52, Math.fma(oHsZ, _t53, this.hsZ))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t30, _t56, Math.fma(_t24, _t54, _t27 * _t55))) <= Math.fma(this.hsX, _t45, Math.fma(this.hsY, _t48, Math.fma(this.hsZ, _t51, oHsX))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t31, _t56, Math.fma(_t25, _t54, _t28 * _t55))) <= Math.fma(this.hsX, _t46, Math.fma(this.hsY, _t49, Math.fma(this.hsZ, _t52, oHsY))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t32, _t56, Math.fma(_t26, _t54, _t29 * _t55))) <= Math.fma(this.hsX, _t47, Math.fma(this.hsY, _t50, Math.fma(this.hsZ, _t53, oHsZ))))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t27, _t56, -(_t30 * _t55))) <= Math.fma(oHsY, _t47, oHsZ * _t46) + Math.fma(this.hsY, _t51, this.hsZ * _t48))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t28, _t56, -(_t31 * _t55))) <= Math.fma(oHsX, _t47, oHsZ * _t45) + Math.fma(this.hsY, _t52, this.hsZ * _t49))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t29, _t56, -(_t32 * _t55))) <= Math.fma(oHsX, _t46, oHsY * _t45) + Math.fma(this.hsY, _t53, this.hsZ * _t50))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t30, _t54, -(_t24 * _t56))) <= Math.fma(oHsY, _t50, oHsZ * _t49) + Math.fma(this.hsX, _t51, this.hsZ * _t45))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t31, _t54, -(_t25 * _t56))) <= Math.fma(oHsX, _t50, oHsZ * _t48) + Math.fma(this.hsX, _t52, this.hsZ * _t46))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t32, _t54, -(_t26 * _t56))) <= Math.fma(oHsX, _t49, oHsY * _t48) + Math.fma(this.hsX, _t53, this.hsZ * _t47))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t24, _t55, -(_t27 * _t54))) <= Math.fma(oHsY, _t53, oHsZ * _t52) + Math.fma(this.hsX, _t48, this.hsY * _t45))) return false;
        if (!(java.lang.Math.abs(Math.fma(_t25, _t55, -(_t28 * _t54))) <= Math.fma(oHsX, _t53, oHsZ * _t51) + Math.fma(this.hsX, _t49, this.hsY * _t46))) return false;
        return java.lang.Math.abs(Math.fma(_t26, _t55, -(_t29 * _t54))) <= Math.fma(oHsX, _t52, oHsY * _t51) + Math.fma(this.hsX, _t50, this.hsY * _t47);
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
        if (!(this.hsX >= 0.0f)) return false;
        if (!(this.hsY >= 0.0f)) return false;
        return this.hsZ >= 0.0f;
    }

    /**
     * {@return a copy with the {@code cX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code cX} component
     */
    public FloatOBB withCX(float v) {
        return new FloatOBB(v, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code cY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code cY} component
     */
    public FloatOBB withCY(float v) {
        return new FloatOBB(cX, v, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code cZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code cZ} component
     */
    public FloatOBB withCZ(float v) {
        return new FloatOBB(cX, cY, v, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uXx} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uXx} component
     */
    public FloatOBB withUXx(float v) {
        return new FloatOBB(cX, cY, cZ, v, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uXy} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uXy} component
     */
    public FloatOBB withUXy(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, v, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uXz} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uXz} component
     */
    public FloatOBB withUXz(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, v, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uYx} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uYx} component
     */
    public FloatOBB withUYx(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, v, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uYy} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uYy} component
     */
    public FloatOBB withUYy(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, v, uYz, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uYz} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uYz} component
     */
    public FloatOBB withUYz(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, v, uZx, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uZx} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uZx} component
     */
    public FloatOBB withUZx(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, v, uZy, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uZy} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uZy} component
     */
    public FloatOBB withUZy(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, v, uZz, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code uZz} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code uZz} component
     */
    public FloatOBB withUZz(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, v, hsX, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code hsX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code hsX} component
     */
    public FloatOBB withHsX(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, v, hsY, hsZ);
    }

    /**
     * {@return a copy with the {@code hsY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code hsY} component
     */
    public FloatOBB withHsY(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, v, hsZ);
    }

    /**
     * {@return a copy with the {@code hsZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code hsZ} component
     */
    public FloatOBB withHsZ(float v) {
        return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, v);
    }

    @Override public String toString() {
        return "FloatOBB(" + cX() + ", " + cY() + ", " + cZ() + ", " + uXx() + ", " + uXy() + ", " + uXz() + ", " + uYx() + ", " + uYy() + ", " + uYz() + ", " + uZx() + ", " + uZy() + ", " + uZz() + ", " + hsX() + ", " + hsY() + ", " + hsZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatOBB)) return false;
        FloatOBB o = (FloatOBB) obj;
        return Float.floatToIntBits(cX) == Float.floatToIntBits(o.cX)
            && Float.floatToIntBits(cY) == Float.floatToIntBits(o.cY)
            && Float.floatToIntBits(cZ) == Float.floatToIntBits(o.cZ)
            && Float.floatToIntBits(uXx) == Float.floatToIntBits(o.uXx)
            && Float.floatToIntBits(uXy) == Float.floatToIntBits(o.uXy)
            && Float.floatToIntBits(uXz) == Float.floatToIntBits(o.uXz)
            && Float.floatToIntBits(uYx) == Float.floatToIntBits(o.uYx)
            && Float.floatToIntBits(uYy) == Float.floatToIntBits(o.uYy)
            && Float.floatToIntBits(uYz) == Float.floatToIntBits(o.uYz)
            && Float.floatToIntBits(uZx) == Float.floatToIntBits(o.uZx)
            && Float.floatToIntBits(uZy) == Float.floatToIntBits(o.uZy)
            && Float.floatToIntBits(uZz) == Float.floatToIntBits(o.uZz)
            && Float.floatToIntBits(hsX) == Float.floatToIntBits(o.hsX)
            && Float.floatToIntBits(hsY) == Float.floatToIntBits(o.hsY)
            && Float.floatToIntBits(hsZ) == Float.floatToIntBits(o.hsZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(cX);
        h = 31 * h + Float.floatToIntBits(cY);
        h = 31 * h + Float.floatToIntBits(cZ);
        h = 31 * h + Float.floatToIntBits(uXx);
        h = 31 * h + Float.floatToIntBits(uXy);
        h = 31 * h + Float.floatToIntBits(uXz);
        h = 31 * h + Float.floatToIntBits(uYx);
        h = 31 * h + Float.floatToIntBits(uYy);
        h = 31 * h + Float.floatToIntBits(uYz);
        h = 31 * h + Float.floatToIntBits(uZx);
        h = 31 * h + Float.floatToIntBits(uZy);
        h = 31 * h + Float.floatToIntBits(uZz);
        h = 31 * h + Float.floatToIntBits(hsX);
        h = 31 * h + Float.floatToIntBits(hsY);
        h = 31 * h + Float.floatToIntBits(hsZ);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(cX)
            && Float.isFinite(cY)
            && Float.isFinite(cZ)
            && Float.isFinite(uXx)
            && Float.isFinite(uXy)
            && Float.isFinite(uXz)
            && Float.isFinite(uYx)
            && Float.isFinite(uYy)
            && Float.isFinite(uYz)
            && Float.isFinite(uZx)
            && Float.isFinite(uZy)
            && Float.isFinite(uZz)
            && Float.isFinite(hsX)
            && Float.isFinite(hsY)
            && Float.isFinite(hsZ);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(cX)
            || Float.isNaN(cY)
            || Float.isNaN(cZ)
            || Float.isNaN(uXx)
            || Float.isNaN(uXy)
            || Float.isNaN(uXz)
            || Float.isNaN(uYx)
            || Float.isNaN(uYy)
            || Float.isNaN(uYz)
            || Float.isNaN(uZx)
            || Float.isNaN(uZy)
            || Float.isNaN(uZz)
            || Float.isNaN(hsX)
            || Float.isNaN(hsY)
            || Float.isNaN(hsZ);
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
    public boolean equalsEpsilon(FloatOBB other, float epsilon) {
        return java.lang.Math.abs(cX - other.cX()) <= epsilon
            && java.lang.Math.abs(cY - other.cY()) <= epsilon
            && java.lang.Math.abs(cZ - other.cZ()) <= epsilon
            && java.lang.Math.abs(uXx - other.uXx()) <= epsilon
            && java.lang.Math.abs(uXy - other.uXy()) <= epsilon
            && java.lang.Math.abs(uXz - other.uXz()) <= epsilon
            && java.lang.Math.abs(uYx - other.uYx()) <= epsilon
            && java.lang.Math.abs(uYy - other.uYy()) <= epsilon
            && java.lang.Math.abs(uYz - other.uYz()) <= epsilon
            && java.lang.Math.abs(uZx - other.uZx()) <= epsilon
            && java.lang.Math.abs(uZy - other.uZy()) <= epsilon
            && java.lang.Math.abs(uZz - other.uZz()) <= epsilon
            && java.lang.Math.abs(hsX - other.hsX()) <= epsilon
            && java.lang.Math.abs(hsY - other.hsY()) <= epsilon
            && java.lang.Math.abs(hsZ - other.hsZ()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatOBBSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatOBBSegOpsUnsafe()
                        : new FloatOBBSegOpsMS();
        static final FloatOBBBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatOBBBbOpsUnsafe()
                        : new FloatOBBBbOpsApi();
        static final FloatOBBRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatOBBRawOpsUnsafe()
                        : new FloatOBBRawOpsApi();
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
        dest[offset] = this.cX;
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
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        float _c6 = src[offset + 6];
        float _c7 = src[offset + 7];
        float _c8 = src[offset + 8];
        float _c9 = src[offset + 9];
        float _c10 = src[offset + 10];
        float _c11 = src[offset + 11];
        float _c12 = src[offset + 12];
        float _c13 = src[offset + 13];
        float _c14 = src[offset + 14];
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(float[] src) { return load(src, 0); }

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
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(FloatBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatOBB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatOBB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
        if (buf.remaining() < 60) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 60);
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(ByteBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatOBB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 60) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatOBB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 60);
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
    public FloatOBB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadUnsafe(long address) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(long offset, MemorySegment src) {
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
        dest[offset] = this.cX;
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        float _c6 = (float) src[offset + 6];
        float _c7 = (float) src[offset + 7];
        float _c8 = (float) src[offset + 8];
        float _c9 = (float) src[offset + 9];
        float _c10 = (float) src[offset + 10];
        float _c11 = (float) src[offset + 11];
        float _c12 = (float) src[offset + 12];
        float _c13 = (float) src[offset + 13];
        float _c14 = (float) src[offset + 14];
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(double[] src) { return load(src, 0); }

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
        if (buf.remaining() < 15) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 15);
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(DoubleBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatOBB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatOBB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 15);
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
        if (buf.remaining() < 120) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 120);
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDouble(ByteBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatOBB loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 120) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatOBB r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 120);
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
    public FloatOBB storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDoubleUnsafe(long address) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDouble(long offset, MemorySegment src) {
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
}
