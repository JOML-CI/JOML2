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
 * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a finite
 * one only under an infinite {@code epsilon}; a NaN component never compares equal to anything.
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
     * Valid input: the method reads no input.
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

    /**
     * Create an array of {@code length} elements, each the instance with all components 0.
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
    public static FloatOBB[] newArray(int length) {
        return (FloatOBB[]) org.joml2.internal.value.FlatArrays.newArray(FloatOBB.class, length, new FloatOBB(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
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
    public static FloatOBB[] newArray(int length, FloatOBB init) {
        return (FloatOBB[]) org.joml2.internal.value.FlatArrays.newArray(FloatOBB.class, length, init);
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
     * Return a oriented bounding box with the values of {@code v}: that oriented bounding box
     * itself (an immutable value needs no copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return the resulting oriented bounding box
     */
    public FloatOBB set(FloatOBB v) {
        float vCX = v.cX;
        float vCY = v.cY;
        float vCZ = v.cZ;
        float vUXx = v.uXx;
        float vUXy = v.uXy;
        float vUXz = v.uXz;
        float vUYx = v.uYx;
        float vUYy = v.uYy;
        float vUYz = v.uYz;
        float vUZx = v.uZx;
        float vUZy = v.uZy;
        float vUZz = v.uZz;
        float vHsX = v.hsX;
        float vHsY = v.hsY;
        float vHsZ = v.hsZ;
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
    public FloatOBB setOrientation(float qX, float qY, float qZ, float qW) {
        if (Math.useFma()) {
            float _t0 = qZ * qZ;
            float _t1 = qZ * qW;
            float _t2 = qY * qW;
            return new FloatOBB(this.cX, this.cY, this.cZ, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qY, qY, _t0), 1.0f), 2.0f * java.lang.Math.fma(qX, qY, _t1), 2.0f * java.lang.Math.fma(qX, qZ, -_t2), 2.0f * java.lang.Math.fma(qX, qY, -_t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, _t0), 1.0f), 2.0f * java.lang.Math.fma(qX, qW, qY * qZ), 2.0f * java.lang.Math.fma(qX, qZ, _t2), 2.0f * java.lang.Math.fma(qY, qZ, -(qX * qW)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, qY * qY), 1.0f), this.hsX, this.hsY, this.hsZ);
        } else {
            float _t0 = qZ * qZ;
            float _t1 = qZ * qW;
            float _t2 = qY * qW;
            return new FloatOBB(this.cX, this.cY, this.cZ, ((1.0f) - (2.0f) * (((qY) * (qY) + (_t0)))), 2.0f * ((qX) * (qY) + (_t1)), 2.0f * ((qX) * (qZ) - (_t2)), 2.0f * ((qX) * (qY) - (_t1)), ((1.0f) - (2.0f) * (((qX) * (qX) + (_t0)))), 2.0f * ((qX) * (qW) + (qY * qZ)), 2.0f * ((qX) * (qZ) + (_t2)), 2.0f * ((qY) * (qZ) - (qX * qW)), ((1.0f) - (2.0f) * (((qX) * (qX) + (qY * qY)))), this.hsX, this.hsY, this.hsZ);
        }
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
     * Create an oriented bounding box with zero center and half-size and identity axes (a point box
     * at the origin).
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
        if (Math.useFma()) return transform_fma(m);
        return transform_mulAdd(m);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float3x4 m) {
        float[] _bundle0 = transform_sf6171e49_1_fma(m);
        float[] _bundle1 = transform_sf6171e49_2_fma(m, _bundle0);
        if (!(_bundle1[8] > java.lang.Math.fma(java.lang.Math.fma(_bundle1[2], _bundle1[2], java.lang.Math.fma(_bundle1[1], _bundle1[1], _bundle1[3] * _bundle1[3])), _bundle1[7] * 1.4551915E-11f, 1.1754944E-38f) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m);
        float[] _bundle2 = transform_sf6171e49_3_fma(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_sf6171e49_4_fma(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_fma(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float3x4 m) {
        float[] _bundle0 = transform_sf6171e49_1_mulAdd(m);
        float[] _bundle1 = transform_sf6171e49_2_mulAdd(m, _bundle0);
        if (!(_bundle1[8] > ((((_bundle1[2]) * (_bundle1[2]) + (((_bundle1[1]) * (_bundle1[1]) + (_bundle1[3] * _bundle1[3]))))) * (_bundle1[7] * 1.4551915E-11f) + (1.1754944E-38f)) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m);
        float[] _bundle2 = transform_sf6171e49_3_mulAdd(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_sf6171e49_4_mulAdd(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_mulAdd(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_1_fma(Float3x4 m) {
        return new float[] {java.lang.Math.fma(m.m02(), this.uXz, java.lang.Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), java.lang.Math.fma(m.m22(), this.uXz, java.lang.Math.fma(m.m20(), this.uXx, m.m21() * this.uXy))};
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_1_mulAdd(Float3x4 m) {
        return new float[] {((m.m02()) * (this.uXz) + (((m.m00()) * (this.uXx) + (m.m01() * this.uXy)))), ((m.m22()) * (this.uXz) + (((m.m20()) * (this.uXx) + (m.m21() * this.uXy))))};
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_2_fma(Float3x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t20 = java.lang.Math.fma(_m_m12, this.uXz, java.lang.Math.fma(_m_m10, this.uXx, _m_m11 * this.uXy));
        float _t21 = java.lang.Math.fma(m.m02(), this.uYz, java.lang.Math.fma(m.m00(), this.uYx, m.m01() * this.uYy));
        float _t22 = java.lang.Math.fma(m.m22(), this.uYz, java.lang.Math.fma(m.m20(), this.uYx, m.m21() * this.uYy));
        float _t23 = java.lang.Math.fma(_m_m12, this.uYz, java.lang.Math.fma(_m_m10, this.uYx, _m_m11 * this.uYy));
        float _t37 = java.lang.Math.fma(_t21, _t19, -(_t18 * _t22));
        float _t38 = java.lang.Math.fma(_t18, _t23, -(_t21 * _t20));
        float _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        return new float[] {_t20, _t21, _t22, _t23, _t37, _t38, _t39, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20)), java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39))};
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_2_mulAdd(Float3x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t20 = ((_m_m12) * (this.uXz) + (((_m_m10) * (this.uXx) + (_m_m11 * this.uXy))));
        float _t21 = ((m.m02()) * (this.uYz) + (((m.m00()) * (this.uYx) + (m.m01() * this.uYy))));
        float _t22 = ((m.m22()) * (this.uYz) + (((m.m20()) * (this.uYx) + (m.m21() * this.uYy))));
        float _t23 = ((_m_m12) * (this.uYz) + (((_m_m10) * (this.uYx) + (_m_m11 * this.uYy))));
        float _t37 = ((_t21) * (_t19) - (_t18 * _t22));
        float _t38 = ((_t18) * (_t23) - (_t21 * _t20));
        float _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        return new float[] {_t20, _t21, _t22, _t23, _t37, _t38, _t39, ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20)))), ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))))};
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_3_fma(Float3x4 m, float[] _bundle0, float[] _bundle1) {
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[8]));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[7]));
        float _t45 = _bundle1[0] * _t42;
        float _t46 = _bundle0[1] * _t42;
        float _t53 = _bundle1[4] * _t52;
        float _t54 = _bundle1[5] * _t52;
        return new float[] {java.lang.Math.fma(m.m22(), this.uZz, java.lang.Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), java.lang.Math.fma(m.m02(), this.uZz, java.lang.Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), java.lang.Math.fma(m.m12(), this.uZz, java.lang.Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _bundle0[0] * _t42, _t45, _t46, _t53, _t54, _bundle1[6] * _t52, java.lang.Math.fma(_t46, _t53, -(_t45 * _t54))};
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_3_mulAdd(Float3x4 m, float[] _bundle0, float[] _bundle1) {
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[8]));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[7]));
        float _t45 = _bundle1[0] * _t42;
        float _t46 = _bundle0[1] * _t42;
        float _t53 = _bundle1[4] * _t52;
        float _t54 = _bundle1[5] * _t52;
        return new float[] {((m.m22()) * (this.uZz) + (((m.m20()) * (this.uZx) + (m.m21() * this.uZy)))), ((m.m02()) * (this.uZz) + (((m.m00()) * (this.uZx) + (m.m01() * this.uZy)))), ((m.m12()) * (this.uZz) + (((m.m10()) * (this.uZx) + (m.m11() * this.uZy)))), _bundle0[0] * _t42, _t45, _t46, _t53, _t54, _bundle1[6] * _t52, ((_t46) * (_t53) - (_t45 * _t54))};
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_4_fma(Float3x4 m, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62) {
        float _t63 = java.lang.Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = java.lang.Math.fma(_t45, _t55, -(_t44 * _t53));
        float _sfx0 = java.lang.Math.fma(m.m00(), this.cX, java.lang.Math.fma(m.m01(), this.cY, java.lang.Math.fma(m.m02(), this.cZ, m.m03())));
        float _sfx1 = java.lang.Math.fma(m.m10(), this.cX, java.lang.Math.fma(m.m11(), this.cY, java.lang.Math.fma(m.m12(), this.cZ, m.m13())));
        float _sfx2 = java.lang.Math.fma(m.m20(), this.cX, java.lang.Math.fma(m.m21(), this.cY, java.lang.Math.fma(m.m22(), this.cZ, m.m23())));
        return new float[] {_t63, _t64, _sfx0, _sfx1, _sfx2, _t44, _t45, _t46, _t62, _t63, _t64, _t55, _t53, _t54};
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_4_mulAdd(Float3x4 m, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62) {
        float _t63 = ((_t44) * (_t54) - (_t46 * _t55));
        float _t64 = ((_t45) * (_t55) - (_t44 * _t53));
        return new float[] {_t63, _t64, ((m.m00()) * (this.cX) + (((m.m01()) * (this.cY) + (((m.m02()) * (this.cZ) + (m.m03())))))), ((m.m10()) * (this.cX) + (((m.m11()) * (this.cY) + (((m.m12()) * (this.cZ) + (m.m13())))))), ((m.m20()) * (this.cX) + (((m.m21()) * (this.cY) + (((m.m22()) * (this.cZ) + (m.m23())))))), _t44, _t45, _t46, _t62, _t63, _t64, _t55, _t53, _t54};
    }

    /**
     * Part 5 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_5_fma(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62, float _t63, float _t64) {
        float _sfx12 = java.lang.Math.fma(this.hsZ, java.lang.Math.abs(java.lang.Math.fma(_t24, _t46, java.lang.Math.fma(_t25, _t44, _t26 * _t45))), java.lang.Math.fma(this.hsX, java.lang.Math.abs(java.lang.Math.fma(_t19, _t46, java.lang.Math.fma(_t18, _t44, _t20 * _t45))), this.hsY * java.lang.Math.abs(java.lang.Math.fma(_t22, _t46, java.lang.Math.fma(_t21, _t44, _t23 * _t45)))));
        float _sfx13 = java.lang.Math.fma(this.hsZ, java.lang.Math.abs(java.lang.Math.fma(_t24, _t64, java.lang.Math.fma(_t25, _t62, _t26 * _t63))), java.lang.Math.fma(this.hsX, java.lang.Math.abs(java.lang.Math.fma(_t19, _t64, java.lang.Math.fma(_t18, _t62, _t20 * _t63))), this.hsY * java.lang.Math.abs(java.lang.Math.fma(_t22, _t64, java.lang.Math.fma(_t21, _t62, _t23 * _t63)))));
        float _sfx14 = java.lang.Math.fma(this.hsZ, java.lang.Math.abs(java.lang.Math.fma(_t24, _t54, java.lang.Math.fma(_t25, _t55, _t26 * _t53))), java.lang.Math.fma(this.hsX, java.lang.Math.abs(java.lang.Math.fma(_t19, _t54, java.lang.Math.fma(_t18, _t55, _t20 * _t53))), this.hsY * java.lang.Math.abs(java.lang.Math.fma(_t22, _t54, java.lang.Math.fma(_t21, _t55, _t23 * _t53)))));
        return new float[] {_sfx12, _sfx13, _sfx14};
    }

    /**
     * Part 5 of {@code transform}, split to fit the inline budget. Shared by 4 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_sf6171e49_5_mulAdd(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62, float _t63, float _t64) {
        float _sfx12 = ((this.hsZ) * (java.lang.Math.abs(((_t24) * (_t46) + (((_t25) * (_t44) + (_t26 * _t45)))))) + (((this.hsX) * (java.lang.Math.abs(((_t19) * (_t46) + (((_t18) * (_t44) + (_t20 * _t45)))))) + (this.hsY * java.lang.Math.abs(((_t22) * (_t46) + (((_t21) * (_t44) + (_t23 * _t45)))))))));
        float _sfx13 = ((this.hsZ) * (java.lang.Math.abs(((_t24) * (_t64) + (((_t25) * (_t62) + (_t26 * _t63)))))) + (((this.hsX) * (java.lang.Math.abs(((_t19) * (_t64) + (((_t18) * (_t62) + (_t20 * _t63)))))) + (this.hsY * java.lang.Math.abs(((_t22) * (_t64) + (((_t21) * (_t62) + (_t23 * _t63)))))))));
        float _sfx14 = ((this.hsZ) * (java.lang.Math.abs(((_t24) * (_t54) + (((_t25) * (_t55) + (_t26 * _t53)))))) + (((this.hsX) * (java.lang.Math.abs(((_t19) * (_t54) + (((_t18) * (_t55) + (_t20 * _t53)))))) + (this.hsY * java.lang.Math.abs(((_t22) * (_t54) + (((_t21) * (_t55) + (_t23 * _t53)))))))));
        return new float[] {_sfx12, _sfx13, _sfx14};
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float3x4 m) {
        float[] _bundle0 = transform_degenerate_s66aa5e50_1_fma(m);
        float[] _bundle1 = transform_degenerate_s66aa5e50_2_fma(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_fma(_bundle1);
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
        float _t88 = -java.lang.Math.fma(_t72, _bundle1[8], java.lang.Math.fma(_t70, _bundle1[9], _t71 * _bundle1[10]));
        float _t89 = -java.lang.Math.fma(_t72, _bundle2[0], java.lang.Math.fma(_t70, _bundle2[1], _t71 * _bundle2[2]));
        float _t90 = -java.lang.Math.fma(_t72, _bundle1[5], java.lang.Math.fma(_t70, _bundle1[6], _t71 * _bundle1[7]));
        return transform_degenerate_s66aa5e50_7_fma(m, _bundle0, _bundle1, _bundle2, _t83, _t84, _t91, java.lang.Math.fma(_t88, _t72, _bundle1[8]), java.lang.Math.fma(_t88, _t70, _bundle1[9]), java.lang.Math.fma(_t88, _t71, _bundle1[10]), java.lang.Math.fma(_t89, _t72, _bundle2[0]), java.lang.Math.fma(_t89, _t70, _bundle2[1]), java.lang.Math.fma(_t89, _t71, _bundle2[2]), java.lang.Math.fma(_t90, _t72, _bundle1[5]), java.lang.Math.fma(_t90, _t70, _bundle1[6]), java.lang.Math.fma(_t90, _t71, _bundle1[7]));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float3x4 m) {
        float[] _bundle0 = transform_degenerate_s66aa5e50_1_mulAdd(m);
        float[] _bundle1 = transform_degenerate_s66aa5e50_2_mulAdd(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_mulAdd(_bundle1);
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
        float _t88 = -((_t72) * (_bundle1[8]) + (((_t70) * (_bundle1[9]) + (_t71 * _bundle1[10]))));
        float _t89 = -((_t72) * (_bundle2[0]) + (((_t70) * (_bundle2[1]) + (_t71 * _bundle2[2]))));
        float _t90 = -((_t72) * (_bundle1[5]) + (((_t70) * (_bundle1[6]) + (_t71 * _bundle1[7]))));
        return transform_degenerate_s66aa5e50_7_mulAdd(m, _bundle0, _bundle1, _bundle2, _t83, _t84, _t91, ((_t88) * (_t72) + (_bundle1[8])), ((_t88) * (_t70) + (_bundle1[9])), ((_t88) * (_t71) + (_bundle1[10])), ((_t89) * (_t72) + (_bundle2[0])), ((_t89) * (_t70) + (_bundle2[1])), ((_t89) * (_t71) + (_bundle2[2])), ((_t90) * (_t72) + (_bundle1[5])), ((_t90) * (_t70) + (_bundle1[6])), ((_t90) * (_t71) + (_bundle1[7])));
    }

    /**
     * Part 1 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_1_fma(Float3x4 m) {
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new float[] {java.lang.Math.fma(_m_m22, this.uXz, java.lang.Math.fma(_m_m20, this.uXx, _m_m21 * this.uXy)), java.lang.Math.fma(m.m02(), this.uXz, java.lang.Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), java.lang.Math.fma(m.m12(), this.uXz, java.lang.Math.fma(m.m10(), this.uXx, m.m11() * this.uXy)), java.lang.Math.fma(_m_m22, this.uYz, java.lang.Math.fma(_m_m20, this.uYx, _m_m21 * this.uYy))};
    }

    /**
     * Part 1 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_1_mulAdd(Float3x4 m) {
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new float[] {((_m_m22) * (this.uXz) + (((_m_m20) * (this.uXx) + (_m_m21 * this.uXy)))), ((m.m02()) * (this.uXz) + (((m.m00()) * (this.uXx) + (m.m01() * this.uXy)))), ((m.m12()) * (this.uXz) + (((m.m10()) * (this.uXx) + (m.m11() * this.uXy)))), ((_m_m22) * (this.uYz) + (((_m_m20) * (this.uYx) + (_m_m21 * this.uYy))))};
    }

    /**
     * Part 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_2_fma(Float3x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = _bundle0[2];
        float _t21 = _bundle0[3];
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _t22 = java.lang.Math.fma(_m_m02, this.uYz, java.lang.Math.fma(_m_m00, this.uYx, _m_m01 * this.uYy));
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t23 = java.lang.Math.fma(_m_m12, this.uYz, java.lang.Math.fma(_m_m10, this.uYx, _m_m11 * this.uYy));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        return new float[] {_t22, _t23, java.lang.Math.fma(m.m22(), this.uZz, java.lang.Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), java.lang.Math.fma(_m_m02, this.uZz, java.lang.Math.fma(_m_m00, this.uZx, _m_m01 * this.uZy)), java.lang.Math.fma(_m_m12, this.uZz, java.lang.Math.fma(_m_m10, this.uZx, _m_m11 * this.uZy)), _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28};
    }

    /**
     * Part 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_2_mulAdd(Float3x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = _bundle0[2];
        float _t21 = _bundle0[3];
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _t22 = ((_m_m02) * (this.uYz) + (((_m_m00) * (this.uYx) + (_m_m01 * this.uYy))));
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t23 = ((_m_m12) * (this.uYz) + (((_m_m10) * (this.uYx) + (_m_m11 * this.uYy))));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        return new float[] {_t22, _t23, ((m.m22()) * (this.uZz) + (((m.m20()) * (this.uZx) + (m.m21() * this.uZy)))), ((_m_m02) * (this.uZz) + (((_m_m00) * (this.uZx) + (_m_m01 * this.uZy)))), ((_m_m12) * (this.uZz) + (((_m_m10) * (this.uZx) + (_m_m11 * this.uZy)))), _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28};
    }

    /**
     * Part 3 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_3_fma(float[] _bundle1) {
        float _t24 = _bundle1[2];
        float _t25 = _bundle1[3];
        float _t26 = _bundle1[4];
        float _t39 = _bundle1[5];
        float _t40 = _bundle1[6];
        float _t41 = _bundle1[7];
        float _t42 = _bundle1[8];
        float _t43 = _bundle1[9];
        float _t44 = _bundle1[10];
        float _t29 = unitScale(_t25, _t26, _t24);
        float _t45 = _t24 * _t29;
        float _t46 = _t25 * _t29;
        float _t47 = _t26 * _t29;
        float _t54 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t40, _t40, _t41 * _t41));
        float _t55 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, _t44 * _t44));
        float _t56 = java.lang.Math.fma(_t45, _t45, java.lang.Math.fma(_t46, _t46, _t47 * _t47));
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        return new float[] {_t45, _t46, _t47, _t54, _t55, _t56, _t63, _t64, _t65, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t63, _t63, java.lang.Math.fma(_t64, _t64, _t65 * _t65))))};
    }

    /**
     * Part 3 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_3_mulAdd(float[] _bundle1) {
        float _t24 = _bundle1[2];
        float _t25 = _bundle1[3];
        float _t26 = _bundle1[4];
        float _t39 = _bundle1[5];
        float _t40 = _bundle1[6];
        float _t41 = _bundle1[7];
        float _t42 = _bundle1[8];
        float _t43 = _bundle1[9];
        float _t44 = _bundle1[10];
        float _t29 = unitScale(_t25, _t26, _t24);
        float _t45 = _t24 * _t29;
        float _t46 = _t25 * _t29;
        float _t47 = _t26 * _t29;
        float _t54 = ((_t39) * (_t39) + (((_t40) * (_t40) + (_t41 * _t41))));
        float _t55 = ((_t42) * (_t42) + (((_t43) * (_t43) + (_t44 * _t44))));
        float _t56 = ((_t45) * (_t45) + (((_t46) * (_t46) + (_t47 * _t47))));
        float _t63, _t64, _t65;
        if (_t54 > 0.0f) {
            _t63 = _t39;
            _t64 = _t40;
            _t65 = _t41;
        } else {
            if (_t55 > 0.0f) {
                _t63 = _t42;
                _t64 = _t43;
                _t65 = _t44;
            } else {
                if (_t56 > 0.0f) {
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
        return new float[] {_t45, _t46, _t47, _t54, _t55, _t56, _t63, _t64, _t65, (1.0f / (float) java.lang.Math.sqrt(((_t63) * (_t63) + (((_t64) * (_t64) + (_t65 * _t65))))))};
    }

    /**
     * Part 4 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_4_fma(float[] _bundle2, float _t132, float _t133, float _t134) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {java.lang.Math.fma(_t72, _t132, -(_t70 * _t133)), java.lang.Math.fma(_t70, _t134, -(_t71 * _t132)), java.lang.Math.fma(_t71, _t133, -(_t72 * _t134))};
    }

    /**
     * Part 4 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_4_mulAdd(float[] _bundle2, float _t132, float _t133, float _t134) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {((_t72) * (_t132) - (_t70 * _t133)), ((_t70) * (_t134) - (_t71 * _t132)), ((_t71) * (_t133) - (_t72 * _t134))};
    }

    /**
     * Part 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_5_fma(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {java.lang.Math.fma(m.m00(), this.cX, java.lang.Math.fma(m.m01(), this.cY, java.lang.Math.fma(m.m02(), this.cZ, m.m03()))), java.lang.Math.fma(m.m10(), this.cX, java.lang.Math.fma(m.m11(), this.cY, java.lang.Math.fma(m.m12(), this.cZ, m.m13()))), java.lang.Math.fma(m.m20(), this.cX, java.lang.Math.fma(m.m21(), this.cY, java.lang.Math.fma(m.m22(), this.cZ, m.m23()))), _t70, _t71, _t72, java.lang.Math.fma(this.hsZ, java.lang.Math.abs(java.lang.Math.fma(_bundle1[2], _t72, java.lang.Math.fma(_bundle1[3], _t70, _bundle1[4] * _t71))), java.lang.Math.fma(this.hsX, java.lang.Math.abs(java.lang.Math.fma(_bundle0[0], _t72, java.lang.Math.fma(_bundle0[1], _t70, _bundle0[2] * _t71))), this.hsY * java.lang.Math.abs(java.lang.Math.fma(_bundle0[3], _t72, java.lang.Math.fma(_bundle1[0], _t70, _bundle1[1] * _t71)))))};
    }

    /**
     * Part 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s66aa5e50_5_mulAdd(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {((m.m00()) * (this.cX) + (((m.m01()) * (this.cY) + (((m.m02()) * (this.cZ) + (m.m03())))))), ((m.m10()) * (this.cX) + (((m.m11()) * (this.cY) + (((m.m12()) * (this.cZ) + (m.m13())))))), ((m.m20()) * (this.cX) + (((m.m21()) * (this.cY) + (((m.m22()) * (this.cZ) + (m.m23())))))), _t70, _t71, _t72, ((this.hsZ) * (java.lang.Math.abs(((_bundle1[2]) * (_t72) + (((_bundle1[3]) * (_t70) + (_bundle1[4] * _t71)))))) + (((this.hsX) * (java.lang.Math.abs(((_bundle0[0]) * (_t72) + (((_bundle0[1]) * (_t70) + (_bundle0[2] * _t71)))))) + (this.hsY * java.lang.Math.abs(((_bundle0[3]) * (_t72) + (((_bundle1[0]) * (_t70) + (_bundle1[1] * _t71)))))))))};
    }

    /**
     * Part 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s66aa5e50_6_fma(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t133, float _t132, float _t134, float _t141, float _t142, float _t143, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4, float _sfx5, float _sfx12) {
        float _sfx13 = java.lang.Math.fma(this.hsZ, java.lang.Math.abs(java.lang.Math.fma(_t24, _t133, java.lang.Math.fma(_t25, _t132, _t26 * _t134))), java.lang.Math.fma(this.hsX, java.lang.Math.abs(java.lang.Math.fma(_t18, _t133, java.lang.Math.fma(_t19, _t132, _t20 * _t134))), this.hsY * java.lang.Math.abs(java.lang.Math.fma(_t21, _t133, java.lang.Math.fma(_t22, _t132, _t23 * _t134)))));
        float _sfx14 = java.lang.Math.fma(this.hsZ, java.lang.Math.abs(java.lang.Math.fma(_t24, _t142, java.lang.Math.fma(_t25, _t143, _t26 * _t141))), java.lang.Math.fma(this.hsX, java.lang.Math.abs(java.lang.Math.fma(_t18, _t142, java.lang.Math.fma(_t19, _t143, _t20 * _t141))), this.hsY * java.lang.Math.abs(java.lang.Math.fma(_t21, _t142, java.lang.Math.fma(_t22, _t143, _t23 * _t141)))));
        float _el6 = (_t132);
        float _el7 = (_t134);
        float _el8 = (_t133);
        float _el9 = (_t143);
        float _el10 = (_t141);
        float _el11 = (_t142);
        return new FloatOBB((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), _el6, _el7, _el8, _el9, _el10, _el11, (_sfx12), (_sfx13), (_sfx14));
    }

    /**
     * Part 6 of {@code transform_degenerate}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code transform}; reached only through it.
     */
    private FloatOBB transform_degenerate_s66aa5e50_6_mulAdd(float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t133, float _t132, float _t134, float _t141, float _t142, float _t143, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4, float _sfx5, float _sfx12) {
        float _sfx13 = ((this.hsZ) * (java.lang.Math.abs(((_t24) * (_t133) + (((_t25) * (_t132) + (_t26 * _t134)))))) + (((this.hsX) * (java.lang.Math.abs(((_t18) * (_t133) + (((_t19) * (_t132) + (_t20 * _t134)))))) + (this.hsY * java.lang.Math.abs(((_t21) * (_t133) + (((_t22) * (_t132) + (_t23 * _t134)))))))));
        float _sfx14 = ((this.hsZ) * (java.lang.Math.abs(((_t24) * (_t142) + (((_t25) * (_t143) + (_t26 * _t141)))))) + (((this.hsX) * (java.lang.Math.abs(((_t18) * (_t142) + (((_t19) * (_t143) + (_t20 * _t141)))))) + (this.hsY * java.lang.Math.abs(((_t21) * (_t142) + (((_t22) * (_t143) + (_t23 * _t141)))))))));
        float _el6 = (_t132);
        float _el7 = (_t134);
        float _el8 = (_t133);
        float _el9 = (_t143);
        float _el10 = (_t141);
        float _el11 = (_t142);
        return new FloatOBB((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), _el6, _el7, _el8, _el9, _el10, _el11, (_sfx12), (_sfx13), (_sfx14));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s66aa5e50_7_fma(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100) {
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83))));
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > ((_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]) * 1.4551915E-11f)) {
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
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_fma(_bundle2, _t132, _t133, _t134);
        return transform_degenerate_s66aa5e50_8_fma(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134, _bundle3);
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s66aa5e50_7_mulAdd(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100) {
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83))))));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > ((_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]) * 1.4551915E-11f)) {
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
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_mulAdd(_bundle2, _t132, _t133, _t134);
        return transform_degenerate_s66aa5e50_8_mulAdd(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134, _bundle3);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s66aa5e50_8_fma(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134, float[] _bundle3) {
        float[] _bundle4 = transform_degenerate_s66aa5e50_5_fma(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_fma(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s66aa5e50_8_mulAdd(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134, float[] _bundle3) {
        float[] _bundle4 = transform_degenerate_s66aa5e50_5_mulAdd(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_mulAdd(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
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
        if (Math.useFma()) return transform_fma(m);
        return transform_mulAdd(m);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float4x4 m) {
        float[] _bundle0 = transform_s2253001c_1_fma(m);
        float[] _bundle1 = transform_s2253001c_2_fma(m, _bundle0);
        if (!(_bundle1[8] > java.lang.Math.fma(java.lang.Math.fma(_bundle1[2], _bundle1[2], java.lang.Math.fma(_bundle1[1], _bundle1[1], _bundle1[3] * _bundle1[3])), _bundle1[7] * 1.4551915E-11f, 1.1754944E-38f) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m);
        float[] _bundle2 = transform_s2253001c_3_fma(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_s2253001c_4_fma(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_fma(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float4x4 m) {
        float[] _bundle0 = transform_s2253001c_1_mulAdd(m);
        float[] _bundle1 = transform_s2253001c_2_mulAdd(m, _bundle0);
        if (!(_bundle1[8] > ((((_bundle1[2]) * (_bundle1[2]) + (((_bundle1[1]) * (_bundle1[1]) + (_bundle1[3] * _bundle1[3]))))) * (_bundle1[7] * 1.4551915E-11f) + (1.1754944E-38f)) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m);
        float[] _bundle2 = transform_s2253001c_3_mulAdd(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_s2253001c_4_mulAdd(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_mulAdd(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_1_fma(Float4x4 m) {
        return new float[] {java.lang.Math.fma(m.m02(), this.uXz, java.lang.Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), java.lang.Math.fma(m.m22(), this.uXz, java.lang.Math.fma(m.m20(), this.uXx, m.m21() * this.uXy))};
    }

    /**
     * Part 1 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_1_mulAdd(Float4x4 m) {
        return new float[] {((m.m02()) * (this.uXz) + (((m.m00()) * (this.uXx) + (m.m01() * this.uXy)))), ((m.m22()) * (this.uXz) + (((m.m20()) * (this.uXx) + (m.m21() * this.uXy))))};
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_2_fma(Float4x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t20 = java.lang.Math.fma(_m_m12, this.uXz, java.lang.Math.fma(_m_m10, this.uXx, _m_m11 * this.uXy));
        float _t21 = java.lang.Math.fma(m.m02(), this.uYz, java.lang.Math.fma(m.m00(), this.uYx, m.m01() * this.uYy));
        float _t22 = java.lang.Math.fma(m.m22(), this.uYz, java.lang.Math.fma(m.m20(), this.uYx, m.m21() * this.uYy));
        float _t23 = java.lang.Math.fma(_m_m12, this.uYz, java.lang.Math.fma(_m_m10, this.uYx, _m_m11 * this.uYy));
        float _t37 = java.lang.Math.fma(_t21, _t19, -(_t18 * _t22));
        float _t38 = java.lang.Math.fma(_t18, _t23, -(_t21 * _t20));
        float _t39 = java.lang.Math.fma(_t20, _t22, -(_t23 * _t19));
        return new float[] {_t20, _t21, _t22, _t23, _t37, _t38, _t39, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20)), java.lang.Math.fma(_t38, _t38, java.lang.Math.fma(_t37, _t37, _t39 * _t39))};
    }

    /**
     * Part 2 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_2_mulAdd(Float4x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t20 = ((_m_m12) * (this.uXz) + (((_m_m10) * (this.uXx) + (_m_m11 * this.uXy))));
        float _t21 = ((m.m02()) * (this.uYz) + (((m.m00()) * (this.uYx) + (m.m01() * this.uYy))));
        float _t22 = ((m.m22()) * (this.uYz) + (((m.m20()) * (this.uYx) + (m.m21() * this.uYy))));
        float _t23 = ((_m_m12) * (this.uYz) + (((_m_m10) * (this.uYx) + (_m_m11 * this.uYy))));
        float _t37 = ((_t21) * (_t19) - (_t18 * _t22));
        float _t38 = ((_t18) * (_t23) - (_t21 * _t20));
        float _t39 = ((_t20) * (_t22) - (_t23 * _t19));
        return new float[] {_t20, _t21, _t22, _t23, _t37, _t38, _t39, ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20)))), ((_t38) * (_t38) + (((_t37) * (_t37) + (_t39 * _t39))))};
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_3_fma(Float4x4 m, float[] _bundle0, float[] _bundle1) {
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[8]));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[7]));
        float _t45 = _bundle1[0] * _t42;
        float _t46 = _bundle0[1] * _t42;
        float _t53 = _bundle1[4] * _t52;
        float _t54 = _bundle1[5] * _t52;
        return new float[] {java.lang.Math.fma(m.m22(), this.uZz, java.lang.Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), java.lang.Math.fma(m.m02(), this.uZz, java.lang.Math.fma(m.m00(), this.uZx, m.m01() * this.uZy)), java.lang.Math.fma(m.m12(), this.uZz, java.lang.Math.fma(m.m10(), this.uZx, m.m11() * this.uZy)), _bundle0[0] * _t42, _t45, _t46, _t53, _t54, _bundle1[6] * _t52, java.lang.Math.fma(_t46, _t53, -(_t45 * _t54))};
    }

    /**
     * Part 3 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_3_mulAdd(Float4x4 m, float[] _bundle0, float[] _bundle1) {
        float _t52 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[8]));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_bundle1[7]));
        float _t45 = _bundle1[0] * _t42;
        float _t46 = _bundle0[1] * _t42;
        float _t53 = _bundle1[4] * _t52;
        float _t54 = _bundle1[5] * _t52;
        return new float[] {((m.m22()) * (this.uZz) + (((m.m20()) * (this.uZx) + (m.m21() * this.uZy)))), ((m.m02()) * (this.uZz) + (((m.m00()) * (this.uZx) + (m.m01() * this.uZy)))), ((m.m12()) * (this.uZz) + (((m.m10()) * (this.uZx) + (m.m11() * this.uZy)))), _bundle0[0] * _t42, _t45, _t46, _t53, _t54, _bundle1[6] * _t52, ((_t46) * (_t53) - (_t45 * _t54))};
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_4_fma(Float4x4 m, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62) {
        float _t63 = java.lang.Math.fma(_t44, _t54, -(_t46 * _t55));
        float _t64 = java.lang.Math.fma(_t45, _t55, -(_t44 * _t53));
        float _sfx0 = java.lang.Math.fma(m.m00(), this.cX, java.lang.Math.fma(m.m01(), this.cY, java.lang.Math.fma(m.m02(), this.cZ, m.m03())));
        float _sfx1 = java.lang.Math.fma(m.m10(), this.cX, java.lang.Math.fma(m.m11(), this.cY, java.lang.Math.fma(m.m12(), this.cZ, m.m13())));
        float _sfx2 = java.lang.Math.fma(m.m20(), this.cX, java.lang.Math.fma(m.m21(), this.cY, java.lang.Math.fma(m.m22(), this.cZ, m.m23())));
        return new float[] {_t63, _t64, _sfx0, _sfx1, _sfx2, _t44, _t45, _t46, _t62, _t63, _t64, _t55, _t53, _t54};
    }

    /**
     * Part 4 of {@code transform}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code transform}; reached only through it.
     */
    private float[] transform_s2253001c_4_mulAdd(Float4x4 m, float _t44, float _t45, float _t46, float _t53, float _t54, float _t55, float _t62) {
        float _t63 = ((_t44) * (_t54) - (_t46 * _t55));
        float _t64 = ((_t45) * (_t55) - (_t44 * _t53));
        return new float[] {_t63, _t64, ((m.m00()) * (this.cX) + (((m.m01()) * (this.cY) + (((m.m02()) * (this.cZ) + (m.m03())))))), ((m.m10()) * (this.cX) + (((m.m11()) * (this.cY) + (((m.m12()) * (this.cZ) + (m.m13())))))), ((m.m20()) * (this.cX) + (((m.m21()) * (this.cY) + (((m.m22()) * (this.cZ) + (m.m23())))))), _t44, _t45, _t46, _t62, _t63, _t64, _t55, _t53, _t54};
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float4x4 m) {
        float[] _bundle0 = transform_degenerate_s26673445_1_fma(m);
        float[] _bundle1 = transform_degenerate_s26673445_2_fma(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_fma(_bundle1);
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
        float _t88 = -java.lang.Math.fma(_t72, _bundle1[8], java.lang.Math.fma(_t70, _bundle1[9], _t71 * _bundle1[10]));
        float _t89 = -java.lang.Math.fma(_t72, _bundle2[0], java.lang.Math.fma(_t70, _bundle2[1], _t71 * _bundle2[2]));
        float _t90 = -java.lang.Math.fma(_t72, _bundle1[5], java.lang.Math.fma(_t70, _bundle1[6], _t71 * _bundle1[7]));
        return transform_degenerate_s26673445_7_fma(m, _bundle0, _bundle1, _bundle2, _t83, _t84, _t91, java.lang.Math.fma(_t88, _t72, _bundle1[8]), java.lang.Math.fma(_t88, _t70, _bundle1[9]), java.lang.Math.fma(_t88, _t71, _bundle1[10]), java.lang.Math.fma(_t89, _t72, _bundle2[0]), java.lang.Math.fma(_t89, _t70, _bundle2[1]), java.lang.Math.fma(_t89, _t71, _bundle2[2]), java.lang.Math.fma(_t90, _t72, _bundle1[5]), java.lang.Math.fma(_t90, _t70, _bundle1[6]), java.lang.Math.fma(_t90, _t71, _bundle1[7]));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float4x4 m) {
        float[] _bundle0 = transform_degenerate_s26673445_1_mulAdd(m);
        float[] _bundle1 = transform_degenerate_s26673445_2_mulAdd(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_mulAdd(_bundle1);
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
        float _t88 = -((_t72) * (_bundle1[8]) + (((_t70) * (_bundle1[9]) + (_t71 * _bundle1[10]))));
        float _t89 = -((_t72) * (_bundle2[0]) + (((_t70) * (_bundle2[1]) + (_t71 * _bundle2[2]))));
        float _t90 = -((_t72) * (_bundle1[5]) + (((_t70) * (_bundle1[6]) + (_t71 * _bundle1[7]))));
        return transform_degenerate_s26673445_7_mulAdd(m, _bundle0, _bundle1, _bundle2, _t83, _t84, _t91, ((_t88) * (_t72) + (_bundle1[8])), ((_t88) * (_t70) + (_bundle1[9])), ((_t88) * (_t71) + (_bundle1[10])), ((_t89) * (_t72) + (_bundle2[0])), ((_t89) * (_t70) + (_bundle2[1])), ((_t89) * (_t71) + (_bundle2[2])), ((_t90) * (_t72) + (_bundle1[5])), ((_t90) * (_t70) + (_bundle1[6])), ((_t90) * (_t71) + (_bundle1[7])));
    }

    /**
     * Part 1 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s26673445_1_fma(Float4x4 m) {
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new float[] {java.lang.Math.fma(_m_m22, this.uXz, java.lang.Math.fma(_m_m20, this.uXx, _m_m21 * this.uXy)), java.lang.Math.fma(m.m02(), this.uXz, java.lang.Math.fma(m.m00(), this.uXx, m.m01() * this.uXy)), java.lang.Math.fma(m.m12(), this.uXz, java.lang.Math.fma(m.m10(), this.uXx, m.m11() * this.uXy)), java.lang.Math.fma(_m_m22, this.uYz, java.lang.Math.fma(_m_m20, this.uYx, _m_m21 * this.uYy))};
    }

    /**
     * Part 1 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s26673445_1_mulAdd(Float4x4 m) {
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new float[] {((_m_m22) * (this.uXz) + (((_m_m20) * (this.uXx) + (_m_m21 * this.uXy)))), ((m.m02()) * (this.uXz) + (((m.m00()) * (this.uXx) + (m.m01() * this.uXy)))), ((m.m12()) * (this.uXz) + (((m.m10()) * (this.uXx) + (m.m11() * this.uXy)))), ((_m_m22) * (this.uYz) + (((_m_m20) * (this.uYx) + (_m_m21 * this.uYy))))};
    }

    /**
     * Part 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s26673445_2_fma(Float4x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = _bundle0[2];
        float _t21 = _bundle0[3];
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _t22 = java.lang.Math.fma(_m_m02, this.uYz, java.lang.Math.fma(_m_m00, this.uYx, _m_m01 * this.uYy));
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t23 = java.lang.Math.fma(_m_m12, this.uYz, java.lang.Math.fma(_m_m10, this.uYx, _m_m11 * this.uYy));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        return new float[] {_t22, _t23, java.lang.Math.fma(m.m22(), this.uZz, java.lang.Math.fma(m.m20(), this.uZx, m.m21() * this.uZy)), java.lang.Math.fma(_m_m02, this.uZz, java.lang.Math.fma(_m_m00, this.uZx, _m_m01 * this.uZy)), java.lang.Math.fma(_m_m12, this.uZz, java.lang.Math.fma(_m_m10, this.uZx, _m_m11 * this.uZy)), _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28};
    }

    /**
     * Part 2 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s26673445_2_mulAdd(Float4x4 m, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t19 = _bundle0[1];
        float _t20 = _bundle0[2];
        float _t21 = _bundle0[3];
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _t22 = ((_m_m02) * (this.uYz) + (((_m_m00) * (this.uYx) + (_m_m01 * this.uYy))));
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _t23 = ((_m_m12) * (this.uYz) + (((_m_m10) * (this.uYx) + (_m_m11 * this.uYy))));
        float _t27 = unitScale(_t19, _t20, _t18);
        float _t28 = unitScale(_t22, _t23, _t21);
        return new float[] {_t22, _t23, ((m.m22()) * (this.uZz) + (((m.m20()) * (this.uZx) + (m.m21() * this.uZy)))), ((_m_m02) * (this.uZz) + (((_m_m00) * (this.uZx) + (_m_m01 * this.uZy)))), ((_m_m12) * (this.uZz) + (((_m_m10) * (this.uZx) + (_m_m11 * this.uZy)))), _t18 * _t27, _t19 * _t27, _t20 * _t27, _t21 * _t28, _t22 * _t28, _t23 * _t28};
    }

    /**
     * Part 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s26673445_5_fma(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {java.lang.Math.fma(m.m00(), this.cX, java.lang.Math.fma(m.m01(), this.cY, java.lang.Math.fma(m.m02(), this.cZ, m.m03()))), java.lang.Math.fma(m.m10(), this.cX, java.lang.Math.fma(m.m11(), this.cY, java.lang.Math.fma(m.m12(), this.cZ, m.m13()))), java.lang.Math.fma(m.m20(), this.cX, java.lang.Math.fma(m.m21(), this.cY, java.lang.Math.fma(m.m22(), this.cZ, m.m23()))), _t70, _t71, _t72, java.lang.Math.fma(this.hsZ, java.lang.Math.abs(java.lang.Math.fma(_bundle1[2], _t72, java.lang.Math.fma(_bundle1[3], _t70, _bundle1[4] * _t71))), java.lang.Math.fma(this.hsX, java.lang.Math.abs(java.lang.Math.fma(_bundle0[0], _t72, java.lang.Math.fma(_bundle0[1], _t70, _bundle0[2] * _t71))), this.hsY * java.lang.Math.abs(java.lang.Math.fma(_bundle0[3], _t72, java.lang.Math.fma(_bundle1[0], _t70, _bundle1[1] * _t71)))))};
    }

    /**
     * Part 5 of {@code transform_degenerate}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private float[] transform_degenerate_s26673445_5_mulAdd(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2) {
        float _t69 = _bundle2[9];
        float _t70 = _t69 * _bundle2[7];
        float _t71 = _t69 * _bundle2[8];
        float _t72 = _t69 * _bundle2[6];
        return new float[] {((m.m00()) * (this.cX) + (((m.m01()) * (this.cY) + (((m.m02()) * (this.cZ) + (m.m03())))))), ((m.m10()) * (this.cX) + (((m.m11()) * (this.cY) + (((m.m12()) * (this.cZ) + (m.m13())))))), ((m.m20()) * (this.cX) + (((m.m21()) * (this.cY) + (((m.m22()) * (this.cZ) + (m.m23())))))), _t70, _t71, _t72, ((this.hsZ) * (java.lang.Math.abs(((_bundle1[2]) * (_t72) + (((_bundle1[3]) * (_t70) + (_bundle1[4] * _t71)))))) + (((this.hsX) * (java.lang.Math.abs(((_bundle0[0]) * (_t72) + (((_bundle0[1]) * (_t70) + (_bundle0[2] * _t71)))))) + (this.hsY * java.lang.Math.abs(((_bundle0[3]) * (_t72) + (((_bundle1[0]) * (_t70) + (_bundle1[1] * _t71)))))))))};
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s26673445_7_fma(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100) {
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83))));
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > ((_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]) * 1.4551915E-11f)) {
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
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_fma(_bundle2, _t132, _t133, _t134);
        return transform_degenerate_s26673445_8_fma(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134, _bundle3);
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s26673445_7_mulAdd(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t83, float _t84, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100) {
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83))))));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > ((_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]) * 1.4551915E-11f)) {
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
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_mulAdd(_bundle2, _t132, _t133, _t134);
        return transform_degenerate_s26673445_8_mulAdd(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134, _bundle3);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s26673445_8_fma(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134, float[] _bundle3) {
        float[] _bundle4 = transform_degenerate_s26673445_5_fma(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_fma(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s26673445_8_mulAdd(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134, float[] _bundle3) {
        float[] _bundle4 = transform_degenerate_s26673445_5_mulAdd(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_mulAdd(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
    }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them), returning the
     * result as a value.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-36}
     * @return the resulting oriented bounding box
     */
    public FloatOBB transform(Float3x4 m, float epsilon) {
        if (Math.useFma()) return transform_fma(m, epsilon);
        return transform_mulAdd(m, epsilon);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float3x4 m, float epsilon) {
        float[] _bundle0 = transform_sf6171e49_1_fma(m);
        float[] _bundle1 = transform_sf6171e49_2_fma(m, _bundle0);
        if (!(_bundle1[8] > java.lang.Math.fma(java.lang.Math.fma(_bundle1[2], _bundle1[2], java.lang.Math.fma(_bundle1[1], _bundle1[1], _bundle1[3] * _bundle1[3])), epsilon * _bundle1[7], 1.1754944E-38f) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon);
        float[] _bundle2 = transform_sf6171e49_3_fma(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_sf6171e49_4_fma(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_fma(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float3x4 m, float epsilon) {
        float[] _bundle0 = transform_sf6171e49_1_mulAdd(m);
        float[] _bundle1 = transform_sf6171e49_2_mulAdd(m, _bundle0);
        if (!(_bundle1[8] > ((((_bundle1[2]) * (_bundle1[2]) + (((_bundle1[1]) * (_bundle1[1]) + (_bundle1[3] * _bundle1[3]))))) * (epsilon * _bundle1[7]) + (1.1754944E-38f)) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon);
        float[] _bundle2 = transform_sf6171e49_3_mulAdd(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_sf6171e49_4_mulAdd(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_mulAdd(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float3x4 m, float epsilon) {
        float[] _bundle0 = transform_degenerate_s66aa5e50_1_fma(m);
        float[] _bundle1 = transform_degenerate_s66aa5e50_2_fma(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_fma(_bundle1);
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
        float _t88 = -java.lang.Math.fma(_t72, _bundle1[8], java.lang.Math.fma(_t70, _bundle1[9], _t71 * _bundle1[10]));
        float _t89 = -java.lang.Math.fma(_t72, _bundle2[0], java.lang.Math.fma(_t70, _bundle2[1], _t71 * _bundle2[2]));
        float _t90 = -java.lang.Math.fma(_t72, _bundle1[5], java.lang.Math.fma(_t70, _bundle1[6], _t71 * _bundle1[7]));
        return transform_degenerate_s832e2e24_7_fma(m, epsilon, _bundle0, _bundle1, _bundle2, _t71, _t83, _t84, _t91, _t90, java.lang.Math.fma(_t88, _t72, _bundle1[8]), java.lang.Math.fma(_t88, _t70, _bundle1[9]), java.lang.Math.fma(_t88, _t71, _bundle1[10]), java.lang.Math.fma(_t89, _t72, _bundle2[0]), java.lang.Math.fma(_t89, _t70, _bundle2[1]), java.lang.Math.fma(_t89, _t71, _bundle2[2]), java.lang.Math.fma(_t90, _t72, _bundle1[5]), java.lang.Math.fma(_t90, _t70, _bundle1[6]));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float3x4 m, float epsilon) {
        float[] _bundle0 = transform_degenerate_s66aa5e50_1_mulAdd(m);
        float[] _bundle1 = transform_degenerate_s66aa5e50_2_mulAdd(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_mulAdd(_bundle1);
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
        float _t88 = -((_t72) * (_bundle1[8]) + (((_t70) * (_bundle1[9]) + (_t71 * _bundle1[10]))));
        float _t89 = -((_t72) * (_bundle2[0]) + (((_t70) * (_bundle2[1]) + (_t71 * _bundle2[2]))));
        float _t90 = -((_t72) * (_bundle1[5]) + (((_t70) * (_bundle1[6]) + (_t71 * _bundle1[7]))));
        return transform_degenerate_s832e2e24_7_mulAdd(m, epsilon, _bundle0, _bundle1, _bundle2, _t71, _t83, _t84, _t91, _t90, ((_t88) * (_t72) + (_bundle1[8])), ((_t88) * (_t70) + (_bundle1[9])), ((_t88) * (_t71) + (_bundle1[10])), ((_t89) * (_t72) + (_bundle2[0])), ((_t89) * (_t70) + (_bundle2[1])), ((_t89) * (_t71) + (_bundle2[2])), ((_t90) * (_t72) + (_bundle1[5])), ((_t90) * (_t70) + (_bundle1[6])));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s832e2e24_7_fma(Float3x4 m, float epsilon, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t71, float _t83, float _t84, float _t91, float _t90, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99) {
        float _t100 = java.lang.Math.fma(_t90, _t71, _bundle1[7]);
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83))));
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > (epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]))) {
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
        return transform_degenerate_s832e2e24_8_fma(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134);
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s832e2e24_7_mulAdd(Float3x4 m, float epsilon, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t71, float _t83, float _t84, float _t91, float _t90, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99) {
        float _t100 = ((_t90) * (_t71) + (_bundle1[7]));
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83))))));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > (epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]))) {
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
        return transform_degenerate_s832e2e24_8_mulAdd(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s832e2e24_8_fma(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134) {
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_fma(_bundle2, _t132, _t133, _t134);
        float[] _bundle4 = transform_degenerate_s66aa5e50_5_fma(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_fma(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s832e2e24_8_mulAdd(Float3x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134) {
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_mulAdd(_bundle2, _t132, _t133, _t134);
        float[] _bundle4 = transform_degenerate_s66aa5e50_5_mulAdd(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_mulAdd(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
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
     * Valid input: the axes of this oriented bounding box must be orthonormal; {@code epsilon} must
     * not be negative.
     *
     * @param m the transformation matrix to apply
     * @param epsilon the squared sine of the angle at or below which two transformed axes count as
     *        parallel; the overload without it uses {@code 2^-36}
     * @return the resulting oriented bounding box
     */
    public FloatOBB transform(Float4x4 m, float epsilon) {
        if (Math.useFma()) return transform_fma(m, epsilon);
        return transform_mulAdd(m, epsilon);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_fma(Float4x4 m, float epsilon) {
        float[] _bundle0 = transform_s2253001c_1_fma(m);
        float[] _bundle1 = transform_s2253001c_2_fma(m, _bundle0);
        if (!(_bundle1[8] > java.lang.Math.fma(java.lang.Math.fma(_bundle1[2], _bundle1[2], java.lang.Math.fma(_bundle1[1], _bundle1[1], _bundle1[3] * _bundle1[3])), epsilon * _bundle1[7], 1.1754944E-38f) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_fma(m, epsilon);
        float[] _bundle2 = transform_s2253001c_3_fma(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_s2253001c_4_fma(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_fma(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatOBB transform_mulAdd(Float4x4 m, float epsilon) {
        float[] _bundle0 = transform_s2253001c_1_mulAdd(m);
        float[] _bundle1 = transform_s2253001c_2_mulAdd(m, _bundle0);
        if (!(_bundle1[8] > ((((_bundle1[2]) * (_bundle1[2]) + (((_bundle1[1]) * (_bundle1[1]) + (_bundle1[3] * _bundle1[3]))))) * (epsilon * _bundle1[7]) + (1.1754944E-38f)) && _bundle1[8] < Float.POSITIVE_INFINITY)) return transform_degenerate_mulAdd(m, epsilon);
        float[] _bundle2 = transform_s2253001c_3_mulAdd(m, _bundle0, _bundle1);
        float[] _bundle3 = transform_s2253001c_4_mulAdd(m, _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9]);
        float[] _bundle4 = transform_sf6171e49_5_mulAdd(_bundle0[0], _bundle0[1], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], _bundle2[9], _bundle3[0], _bundle3[1]);
        return new FloatOBB(_bundle3[2], _bundle3[3], _bundle3[4], _bundle3[5], _bundle3[6], _bundle3[7], _bundle3[8], _bundle3[9], _bundle3[10], _bundle3[11], _bundle3[12], _bundle3[13], _bundle4[0], _bundle4[1], _bundle4[2]);
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_fma(Float4x4 m, float epsilon) {
        float[] _bundle0 = transform_degenerate_s26673445_1_fma(m);
        float[] _bundle1 = transform_degenerate_s26673445_2_fma(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_fma(_bundle1);
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
        float _t88 = -java.lang.Math.fma(_t72, _bundle1[8], java.lang.Math.fma(_t70, _bundle1[9], _t71 * _bundle1[10]));
        float _t89 = -java.lang.Math.fma(_t72, _bundle2[0], java.lang.Math.fma(_t70, _bundle2[1], _t71 * _bundle2[2]));
        float _t90 = -java.lang.Math.fma(_t72, _bundle1[5], java.lang.Math.fma(_t70, _bundle1[6], _t71 * _bundle1[7]));
        return transform_degenerate_s9fd4b74b_7_fma(m, epsilon, _bundle0, _bundle1, _bundle2, _t71, _t83, _t84, _t91, _t90, java.lang.Math.fma(_t88, _t72, _bundle1[8]), java.lang.Math.fma(_t88, _t70, _bundle1[9]), java.lang.Math.fma(_t88, _t71, _bundle1[10]), java.lang.Math.fma(_t89, _t72, _bundle2[0]), java.lang.Math.fma(_t89, _t70, _bundle2[1]), java.lang.Math.fma(_t89, _t71, _bundle2[2]), java.lang.Math.fma(_t90, _t72, _bundle1[5]), java.lang.Math.fma(_t90, _t70, _bundle1[6]));
    }

    /**
     * Degenerate-input path of {@code transform}: its methods leave here when the transformed axes
     * are zero, parallel, or of a length outside the normal floating-point range (or NaN); reached
     * only through them.
     */
    private FloatOBB transform_degenerate_mulAdd(Float4x4 m, float epsilon) {
        float[] _bundle0 = transform_degenerate_s26673445_1_mulAdd(m);
        float[] _bundle1 = transform_degenerate_s26673445_2_mulAdd(m, _bundle0);
        float[] _bundle2 = transform_degenerate_s66aa5e50_3_mulAdd(_bundle1);
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
        float _t88 = -((_t72) * (_bundle1[8]) + (((_t70) * (_bundle1[9]) + (_t71 * _bundle1[10]))));
        float _t89 = -((_t72) * (_bundle2[0]) + (((_t70) * (_bundle2[1]) + (_t71 * _bundle2[2]))));
        float _t90 = -((_t72) * (_bundle1[5]) + (((_t70) * (_bundle1[6]) + (_t71 * _bundle1[7]))));
        return transform_degenerate_s9fd4b74b_7_mulAdd(m, epsilon, _bundle0, _bundle1, _bundle2, _t71, _t83, _t84, _t91, _t90, ((_t88) * (_t72) + (_bundle1[8])), ((_t88) * (_t70) + (_bundle1[9])), ((_t88) * (_t71) + (_bundle1[10])), ((_t89) * (_t72) + (_bundle2[0])), ((_t89) * (_t70) + (_bundle2[1])), ((_t89) * (_t71) + (_bundle2[2])), ((_t90) * (_t72) + (_bundle1[5])), ((_t90) * (_t70) + (_bundle1[6])));
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s9fd4b74b_7_fma(Float4x4 m, float epsilon, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t71, float _t83, float _t84, float _t91, float _t90, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99) {
        float _t100 = java.lang.Math.fma(_t90, _t71, _bundle1[7]);
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t84, _t84, java.lang.Math.fma(_t91, _t91, _t83 * _t83))));
        float _t114 = java.lang.Math.fma(_t92, _t92, java.lang.Math.fma(_t93, _t93, _t94 * _t94));
        float _t115 = java.lang.Math.fma(_t95, _t95, java.lang.Math.fma(_t96, _t96, _t97 * _t97));
        float _t116 = java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > (epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]))) {
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
        return transform_degenerate_s9fd4b74b_8_fma(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134);
    }

    /** Piece 2 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s9fd4b74b_7_mulAdd(Float4x4 m, float epsilon, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t71, float _t83, float _t84, float _t91, float _t90, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99) {
        float _t100 = ((_t90) * (_t71) + (_bundle1[7]));
        float _t110 = (1.0f / (float) java.lang.Math.sqrt(((_t84) * (_t84) + (((_t91) * (_t91) + (_t83 * _t83))))));
        float _t114 = ((_t92) * (_t92) + (((_t93) * (_t93) + (_t94 * _t94))));
        float _t115 = ((_t95) * (_t95) + (((_t96) * (_t96) + (_t97 * _t97))));
        float _t116 = ((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))));
        float _t117 = Math.max(_t114, _t115);
        float _t122 = Math.max(_t117, _t116);
        float _t123 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t132, _t133, _t134;
        if (_t122 > (epsilon * (_t117 >= _t116 ? _t114 >= _t115 ? _bundle2[4] : _bundle2[5] : _bundle2[3]))) {
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
        return transform_degenerate_s9fd4b74b_8_mulAdd(m, _bundle0, _bundle1, _bundle2, _t132, _t133, _t134);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s9fd4b74b_8_fma(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134) {
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_fma(_bundle2, _t132, _t133, _t134);
        float[] _bundle4 = transform_degenerate_s26673445_5_fma(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_fma(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
    }

    /** Piece 3 of {@code transform_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatOBB transform_degenerate_s9fd4b74b_8_mulAdd(Float4x4 m, float[] _bundle0, float[] _bundle1, float[] _bundle2, float _t132, float _t133, float _t134) {
        float[] _bundle3 = transform_degenerate_s66aa5e50_4_mulAdd(_bundle2, _t132, _t133, _t134);
        float[] _bundle4 = transform_degenerate_s26673445_5_mulAdd(m, _bundle0, _bundle1, _bundle2);
        return transform_degenerate_s66aa5e50_6_mulAdd(_bundle0[0], _bundle0[1], _bundle0[2], _bundle0[3], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _t133, _t132, _t134, _bundle3[0], _bundle3[1], _bundle3[2], _bundle4[0], _bundle4[1], _bundle4[2], _bundle4[3], _bundle4[4], _bundle4[5], _bundle4[6]);
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
        float _t18 = Math.max(-this.hsX, Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = Math.max(-this.hsY, Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = Math.max(-this.hsZ, Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
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
        float _t18 = Math.max(-this.hsX, Math.min(Math.fma(this.uXz, _t3, Math.fma(this.uXx, _t4, this.uXy * _t5)), this.hsX));
        float _t19 = Math.max(-this.hsY, Math.min(Math.fma(this.uYz, _t3, Math.fma(this.uYx, _t4, this.uYy * _t5)), this.hsY));
        float _t20 = Math.max(-this.hsZ, Math.min(Math.fma(this.uZz, _t3, Math.fma(this.uZx, _t4, this.uZy * _t5)), this.hsZ));
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
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uXz, _t0, java.lang.Math.fma(this.uXx, _t1, this.uXy * _t2))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uYz, _t0, java.lang.Math.fma(this.uYx, _t1, this.uYy * _t2))) <= this.hsY)) return false;
            return java.lang.Math.abs(java.lang.Math.fma(this.uZz, _t0, java.lang.Math.fma(this.uZx, _t1, this.uZy * _t2))) <= this.hsZ;
        } else {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(((this.uXz) * (_t0) + (((this.uXx) * (_t1) + (this.uXy * _t2))))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(((this.uYz) * (_t0) + (((this.uYx) * (_t1) + (this.uYy * _t2))))) <= this.hsY)) return false;
            return java.lang.Math.abs(((this.uZz) * (_t0) + (((this.uZx) * (_t1) + (this.uZy * _t2))))) <= this.hsZ;
        }
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
        if (Math.useFma()) {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uXz, _t0, java.lang.Math.fma(this.uXx, _t1, this.uXy * _t2))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(java.lang.Math.fma(this.uYz, _t0, java.lang.Math.fma(this.uYx, _t1, this.uYy * _t2))) <= this.hsY)) return false;
            return java.lang.Math.abs(java.lang.Math.fma(this.uZz, _t0, java.lang.Math.fma(this.uZx, _t1, this.uZy * _t2))) <= this.hsZ;
        } else {
            float _t0 = pZ - this.cZ;
            float _t1 = pX - this.cX;
            float _t2 = pY - this.cY;
            if (!(java.lang.Math.abs(((this.uXz) * (_t0) + (((this.uXx) * (_t1) + (this.uXy * _t2))))) <= this.hsX)) return false;
            if (!(java.lang.Math.abs(((this.uYz) * (_t0) + (((this.uYx) * (_t1) + (this.uYy * _t2))))) <= this.hsY)) return false;
            return java.lang.Math.abs(((this.uZz) * (_t0) + (((this.uZx) * (_t1) + (this.uZy * _t2))))) <= this.hsZ;
        }
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
        float _t18 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        float _t18 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        float _t18 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        float _t18 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uZz, _t0, Math.fma(this.uZx, _t1, this.uZy * _t2))) - this.hsZ);
        float _t19 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uXz, _t0, Math.fma(this.uXx, _t1, this.uXy * _t2))) - this.hsX);
        float _t20 = Math.max(0.0f, java.lang.Math.abs(Math.fma(this.uYz, _t0, Math.fma(this.uYx, _t1, this.uYy * _t2))) - this.hsY);
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
        float oCX = o.cX;
        float oCY = o.cY;
        float oCZ = o.cZ;
        float oUXx = o.uXx;
        float oUXy = o.uXy;
        float oUXz = o.uXz;
        float oUYx = o.uYx;
        float oUYy = o.uYy;
        float oUYz = o.uYz;
        float oUZx = o.uZx;
        float oUZy = o.uZy;
        float oUZz = o.uZz;
        float oHsX = o.hsX;
        float oHsY = o.hsY;
        float oHsZ = o.hsZ;
        if (Math.useFma()) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s2f6d5554_tail_fma(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t45, float _t25, float _t46, float _t26, float _t47, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float _t0, float _t1, float _t2) {
        float _t27 = java.lang.Math.fma(oUXz, this.uYz, java.lang.Math.fma(oUXx, this.uYx, oUXy * this.uYy));
        float _t28 = java.lang.Math.fma(oUYz, this.uYz, java.lang.Math.fma(oUYx, this.uYx, oUYy * this.uYy));
        float _t29 = java.lang.Math.fma(oUZz, this.uYz, java.lang.Math.fma(oUZx, this.uYx, oUZy * this.uYy));
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        float _t55 = java.lang.Math.fma(this.uYz, _t0, java.lang.Math.fma(this.uYx, _t1, this.uYy * _t2));
        return (java.lang.Math.abs(_t55) <= java.lang.Math.fma(oHsX, _t48, java.lang.Math.fma(oHsY, _t49, java.lang.Math.fma(oHsZ, _t50, this.hsY)))) && (intersectsOBB_s2f6d5554_tail2_fma(oHsX, oHsY, oHsZ, _t24, _t54, _t27, _t55, _t45, _t48, _t25, _t28, _t46, _t49, _t26, _t29, _t47, _t50, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, _t0, _t1, _t2));
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s2f6d5554_tail_mulAdd(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t45, float _t25, float _t46, float _t26, float _t47, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float _t0, float _t1, float _t2) {
        float _t27 = ((oUXz) * (this.uYz) + (((oUXx) * (this.uYx) + (oUXy * this.uYy))));
        float _t28 = ((oUYz) * (this.uYz) + (((oUYx) * (this.uYx) + (oUYy * this.uYy))));
        float _t29 = ((oUZz) * (this.uYz) + (((oUZx) * (this.uYx) + (oUZy * this.uYy))));
        float _t48 = java.lang.Math.abs(_t27) + 1.0E-5f;
        float _t49 = java.lang.Math.abs(_t28) + 1.0E-5f;
        float _t50 = java.lang.Math.abs(_t29) + 1.0E-5f;
        float _t55 = ((this.uYz) * (_t0) + (((this.uYx) * (_t1) + (this.uYy * _t2))));
        return (java.lang.Math.abs(_t55) <= ((oHsX) * (_t48) + (((oHsY) * (_t49) + (((oHsZ) * (_t50) + (this.hsY))))))) && (intersectsOBB_s2f6d5554_tail2_mulAdd(oHsX, oHsY, oHsZ, _t24, _t54, _t27, _t55, _t45, _t48, _t25, _t28, _t46, _t49, _t26, _t29, _t47, _t50, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, _t0, _t1, _t2));
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s2f6d5554_tail2_fma(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t27, float _t55, float _t45, float _t48, float _t25, float _t28, float _t46, float _t49, float _t26, float _t29, float _t47, float _t50, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float _t0, float _t1, float _t2) {
        float _t30 = java.lang.Math.fma(oUXz, this.uZz, java.lang.Math.fma(oUXx, this.uZx, oUXy * this.uZy));
        float _t31 = java.lang.Math.fma(oUYz, this.uZz, java.lang.Math.fma(oUYx, this.uZx, oUYy * this.uZy));
        float _t32 = java.lang.Math.fma(oUZz, this.uZz, java.lang.Math.fma(oUZx, this.uZx, oUZy * this.uZy));
        float _t51 = java.lang.Math.abs(_t30) + 1.0E-5f;
        float _t52 = java.lang.Math.abs(_t31) + 1.0E-5f;
        float _t53 = java.lang.Math.abs(_t32) + 1.0E-5f;
        float _t56 = java.lang.Math.fma(this.uZz, _t0, java.lang.Math.fma(this.uZx, _t1, this.uZy * _t2));
        return (java.lang.Math.abs(_t56) <= java.lang.Math.fma(oHsX, _t51, java.lang.Math.fma(oHsY, _t52, java.lang.Math.fma(oHsZ, _t53, this.hsZ)))) && ((java.lang.Math.abs(java.lang.Math.fma(_t30, _t56, java.lang.Math.fma(_t24, _t54, _t27 * _t55))) <= java.lang.Math.fma(this.hsX, _t45, java.lang.Math.fma(this.hsY, _t48, java.lang.Math.fma(this.hsZ, _t51, oHsX)))) && (intersectsOBB_s2f6d5554_tail3_fma(_t31, _t56, _t25, _t54, _t28, _t55, _t46, _t49, _t52, oHsY, _t32, _t26, _t29, _t47, _t50, _t53, oHsZ, _t27, _t30, _t51, _t48, oHsX, _t45, _t24)));
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s2f6d5554_tail2_mulAdd(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t27, float _t55, float _t45, float _t48, float _t25, float _t28, float _t46, float _t49, float _t26, float _t29, float _t47, float _t50, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float _t0, float _t1, float _t2) {
        float _t30 = ((oUXz) * (this.uZz) + (((oUXx) * (this.uZx) + (oUXy * this.uZy))));
        float _t31 = ((oUYz) * (this.uZz) + (((oUYx) * (this.uZx) + (oUYy * this.uZy))));
        float _t32 = ((oUZz) * (this.uZz) + (((oUZx) * (this.uZx) + (oUZy * this.uZy))));
        float _t51 = java.lang.Math.abs(_t30) + 1.0E-5f;
        float _t52 = java.lang.Math.abs(_t31) + 1.0E-5f;
        float _t53 = java.lang.Math.abs(_t32) + 1.0E-5f;
        float _t56 = ((this.uZz) * (_t0) + (((this.uZx) * (_t1) + (this.uZy * _t2))));
        return (java.lang.Math.abs(_t56) <= ((oHsX) * (_t51) + (((oHsY) * (_t52) + (((oHsZ) * (_t53) + (this.hsZ))))))) && ((java.lang.Math.abs(((_t30) * (_t56) + (((_t24) * (_t54) + (_t27 * _t55))))) <= ((this.hsX) * (_t45) + (((this.hsY) * (_t48) + (((this.hsZ) * (_t51) + (oHsX))))))) && (intersectsOBB_s2f6d5554_tail3_mulAdd(_t31, _t56, _t25, _t54, _t28, _t55, _t46, _t49, _t52, oHsY, _t32, _t26, _t29, _t47, _t50, _t53, oHsZ, _t27, _t30, _t51, _t48, oHsX, _t45, _t24)));
    }

    /**
     * Private tail of {@code intersectsOBB}. Shared by 2 identical private paths of
     * {@code intersectsOBB}; reached only through it.
     */
    private boolean intersectsOBB_s2f6d5554_tail3_fma(float _t31, float _t56, float _t25, float _t54, float _t28, float _t55, float _t46, float _t49, float _t52, float oHsY, float _t32, float _t26, float _t29, float _t47, float _t50, float _t53, float oHsZ, float _t27, float _t30, float _t51, float _t48, float oHsX, float _t45, float _t24) {
        return (java.lang.Math.abs(java.lang.Math.fma(_t31, _t56, java.lang.Math.fma(_t25, _t54, _t28 * _t55))) <= java.lang.Math.fma(this.hsX, _t46, java.lang.Math.fma(this.hsY, _t49, java.lang.Math.fma(this.hsZ, _t52, oHsY)))) && ((java.lang.Math.abs(java.lang.Math.fma(_t32, _t56, java.lang.Math.fma(_t26, _t54, _t29 * _t55))) <= java.lang.Math.fma(this.hsX, _t47, java.lang.Math.fma(this.hsY, _t50, java.lang.Math.fma(this.hsZ, _t53, oHsZ)))) && ((java.lang.Math.abs(java.lang.Math.fma(_t27, _t56, -(_t30 * _t55))) <= java.lang.Math.fma(oHsY, _t47, oHsZ * _t46) + java.lang.Math.fma(this.hsY, _t51, this.hsZ * _t48)) && ((java.lang.Math.abs(java.lang.Math.fma(_t28, _t56, -(_t31 * _t55))) <= java.lang.Math.fma(oHsX, _t47, oHsZ * _t45) + java.lang.Math.fma(this.hsY, _t52, this.hsZ * _t49)) && (intersectsOBB_s2f6d5554_tail4_fma(_t29, _t56, _t32, _t55, oHsX, _t46, oHsY, _t45, _t53, _t50, _t30, _t54, _t24, oHsZ, _t49, _t51, _t31, _t25, _t48, _t52, _t26, _t47, _t27, _t28)))));
    }

    /**
     * Private tail of {@code intersectsOBB}. Shared by 2 identical private paths of
     * {@code intersectsOBB}; reached only through it.
     */
    private boolean intersectsOBB_s2f6d5554_tail3_mulAdd(float _t31, float _t56, float _t25, float _t54, float _t28, float _t55, float _t46, float _t49, float _t52, float oHsY, float _t32, float _t26, float _t29, float _t47, float _t50, float _t53, float oHsZ, float _t27, float _t30, float _t51, float _t48, float oHsX, float _t45, float _t24) {
        return (java.lang.Math.abs(((_t31) * (_t56) + (((_t25) * (_t54) + (_t28 * _t55))))) <= ((this.hsX) * (_t46) + (((this.hsY) * (_t49) + (((this.hsZ) * (_t52) + (oHsY))))))) && ((java.lang.Math.abs(((_t32) * (_t56) + (((_t26) * (_t54) + (_t29 * _t55))))) <= ((this.hsX) * (_t47) + (((this.hsY) * (_t50) + (((this.hsZ) * (_t53) + (oHsZ))))))) && ((java.lang.Math.abs(((_t27) * (_t56) - (_t30 * _t55))) <= ((oHsY) * (_t47) + (oHsZ * _t46)) + ((this.hsY) * (_t51) + (this.hsZ * _t48))) && ((java.lang.Math.abs(((_t28) * (_t56) - (_t31 * _t55))) <= ((oHsX) * (_t47) + (oHsZ * _t45)) + ((this.hsY) * (_t52) + (this.hsZ * _t49))) && (intersectsOBB_s2f6d5554_tail4_mulAdd(_t29, _t56, _t32, _t55, oHsX, _t46, oHsY, _t45, _t53, _t50, _t30, _t54, _t24, oHsZ, _t49, _t51, _t31, _t25, _t48, _t52, _t26, _t47, _t27, _t28)))));
    }

    /**
     * Private tail of {@code intersectsOBB}. Shared by 2 identical private paths of
     * {@code intersectsOBB}; reached only through it.
     */
    private boolean intersectsOBB_s2f6d5554_tail4_fma(float _t29, float _t56, float _t32, float _t55, float oHsX, float _t46, float oHsY, float _t45, float _t53, float _t50, float _t30, float _t54, float _t24, float oHsZ, float _t49, float _t51, float _t31, float _t25, float _t48, float _t52, float _t26, float _t47, float _t27, float _t28) {
        return (java.lang.Math.abs(java.lang.Math.fma(_t29, _t56, -(_t32 * _t55))) <= java.lang.Math.fma(oHsX, _t46, oHsY * _t45) + java.lang.Math.fma(this.hsY, _t53, this.hsZ * _t50)) && ((java.lang.Math.abs(java.lang.Math.fma(_t30, _t54, -(_t24 * _t56))) <= java.lang.Math.fma(oHsY, _t50, oHsZ * _t49) + java.lang.Math.fma(this.hsX, _t51, this.hsZ * _t45)) && ((java.lang.Math.abs(java.lang.Math.fma(_t31, _t54, -(_t25 * _t56))) <= java.lang.Math.fma(oHsX, _t50, oHsZ * _t48) + java.lang.Math.fma(this.hsX, _t52, this.hsZ * _t46)) && ((java.lang.Math.abs(java.lang.Math.fma(_t32, _t54, -(_t26 * _t56))) <= java.lang.Math.fma(oHsX, _t49, oHsY * _t48) + java.lang.Math.fma(this.hsX, _t53, this.hsZ * _t47)) && (intersectsOBB_s2f6d5554_tail5_fma(_t24, _t55, _t27, _t54, oHsY, _t53, oHsZ, _t52, _t48, _t45, _t25, _t28, oHsX, _t51, _t49, _t46, _t26, _t29, _t50, _t47)))));
    }

    /**
     * Private tail of {@code intersectsOBB}. Shared by 2 identical private paths of
     * {@code intersectsOBB}; reached only through it.
     */
    private boolean intersectsOBB_s2f6d5554_tail4_mulAdd(float _t29, float _t56, float _t32, float _t55, float oHsX, float _t46, float oHsY, float _t45, float _t53, float _t50, float _t30, float _t54, float _t24, float oHsZ, float _t49, float _t51, float _t31, float _t25, float _t48, float _t52, float _t26, float _t47, float _t27, float _t28) {
        return (java.lang.Math.abs(((_t29) * (_t56) - (_t32 * _t55))) <= ((oHsX) * (_t46) + (oHsY * _t45)) + ((this.hsY) * (_t53) + (this.hsZ * _t50))) && ((java.lang.Math.abs(((_t30) * (_t54) - (_t24 * _t56))) <= ((oHsY) * (_t50) + (oHsZ * _t49)) + ((this.hsX) * (_t51) + (this.hsZ * _t45))) && ((java.lang.Math.abs(((_t31) * (_t54) - (_t25 * _t56))) <= ((oHsX) * (_t50) + (oHsZ * _t48)) + ((this.hsX) * (_t52) + (this.hsZ * _t46))) && ((java.lang.Math.abs(((_t32) * (_t54) - (_t26 * _t56))) <= ((oHsX) * (_t49) + (oHsY * _t48)) + ((this.hsX) * (_t53) + (this.hsZ * _t47))) && (intersectsOBB_s2f6d5554_tail5_mulAdd(_t24, _t55, _t27, _t54, oHsY, _t53, oHsZ, _t52, _t48, _t45, _t25, _t28, oHsX, _t51, _t49, _t46, _t26, _t29, _t50, _t47)))));
    }

    /**
     * Private tail of {@code intersectsOBB}. Shared by 2 identical private paths of
     * {@code intersectsOBB}; reached only through it.
     */
    private boolean intersectsOBB_s2f6d5554_tail5_fma(float _t24, float _t55, float _t27, float _t54, float oHsY, float _t53, float oHsZ, float _t52, float _t48, float _t45, float _t25, float _t28, float oHsX, float _t51, float _t49, float _t46, float _t26, float _t29, float _t50, float _t47) {
        return (java.lang.Math.abs(java.lang.Math.fma(_t24, _t55, -(_t27 * _t54))) <= java.lang.Math.fma(oHsY, _t53, oHsZ * _t52) + java.lang.Math.fma(this.hsX, _t48, this.hsY * _t45)) && ((java.lang.Math.abs(java.lang.Math.fma(_t25, _t55, -(_t28 * _t54))) <= java.lang.Math.fma(oHsX, _t53, oHsZ * _t51) + java.lang.Math.fma(this.hsX, _t49, this.hsY * _t46)) && (java.lang.Math.abs(java.lang.Math.fma(_t26, _t55, -(_t29 * _t54))) <= java.lang.Math.fma(oHsX, _t52, oHsY * _t51) + java.lang.Math.fma(this.hsX, _t50, this.hsY * _t47)));
    }

    /**
     * Private tail of {@code intersectsOBB}. Shared by 2 identical private paths of
     * {@code intersectsOBB}; reached only through it.
     */
    private boolean intersectsOBB_s2f6d5554_tail5_mulAdd(float _t24, float _t55, float _t27, float _t54, float oHsY, float _t53, float oHsZ, float _t52, float _t48, float _t45, float _t25, float _t28, float oHsX, float _t51, float _t49, float _t46, float _t26, float _t29, float _t50, float _t47) {
        return (java.lang.Math.abs(((_t24) * (_t55) - (_t27 * _t54))) <= ((oHsY) * (_t53) + (oHsZ * _t52)) + ((this.hsX) * (_t48) + (this.hsY * _t45))) && ((java.lang.Math.abs(((_t25) * (_t55) - (_t28 * _t54))) <= ((oHsX) * (_t53) + (oHsZ * _t51)) + ((this.hsX) * (_t49) + (this.hsY * _t46))) && (java.lang.Math.abs(((_t26) * (_t55) - (_t29 * _t54))) <= ((oHsX) * (_t52) + (oHsY * _t51)) + ((this.hsX) * (_t50) + (this.hsY * _t47))));
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
        if (Math.useFma()) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ);
    }

    /** {@code intersectsOBB} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_fma(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float _t0 = oCZ - this.cZ;
        float _t1 = oCX - this.cX;
        float _t2 = oCY - this.cY;
        float _t24 = java.lang.Math.fma(oUXz, this.uXz, java.lang.Math.fma(oUXx, this.uXx, oUXy * this.uXy));
        float _t25 = java.lang.Math.fma(oUYz, this.uXz, java.lang.Math.fma(oUYx, this.uXx, oUYy * this.uXy));
        float _t26 = java.lang.Math.fma(oUZz, this.uXz, java.lang.Math.fma(oUZx, this.uXx, oUZy * this.uXy));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        float _t54 = java.lang.Math.fma(this.uXz, _t0, java.lang.Math.fma(this.uXx, _t1, this.uXy * _t2));
        return (java.lang.Math.abs(_t54) <= java.lang.Math.fma(oHsX, _t45, java.lang.Math.fma(oHsY, _t46, java.lang.Math.fma(oHsZ, _t47, this.hsX)))) && (intersectsOBB_s2f6d5554_tail_fma(oHsX, oHsY, oHsZ, _t24, _t54, _t45, _t25, _t46, _t26, _t47, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, _t0, _t1, _t2));
    }

    /** {@code intersectsOBB} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_mulAdd(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ) {
        float _t0 = oCZ - this.cZ;
        float _t1 = oCX - this.cX;
        float _t2 = oCY - this.cY;
        float _t24 = ((oUXz) * (this.uXz) + (((oUXx) * (this.uXx) + (oUXy * this.uXy))));
        float _t25 = ((oUYz) * (this.uXz) + (((oUYx) * (this.uXx) + (oUYy * this.uXy))));
        float _t26 = ((oUZz) * (this.uXz) + (((oUZx) * (this.uXx) + (oUZy * this.uXy))));
        float _t45 = java.lang.Math.abs(_t24) + 1.0E-5f;
        float _t46 = java.lang.Math.abs(_t25) + 1.0E-5f;
        float _t47 = java.lang.Math.abs(_t26) + 1.0E-5f;
        float _t54 = ((this.uXz) * (_t0) + (((this.uXx) * (_t1) + (this.uXy * _t2))));
        return (java.lang.Math.abs(_t54) <= ((oHsX) * (_t45) + (((oHsY) * (_t46) + (((oHsZ) * (_t47) + (this.hsX))))))) && (intersectsOBB_s2f6d5554_tail_mulAdd(oHsX, oHsY, oHsZ, _t24, _t54, _t45, _t25, _t46, _t26, _t47, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, _t0, _t1, _t2));
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
     *        the test more conservative; the overload without it uses {@code 1e-5}
     * @return {@code true} if this oriented bounding box intersects {@code o}, {@code false}
     *        otherwise
     */
    public boolean intersectsOBB(FloatOBB o, float epsilon) {
        float oCX = o.cX;
        float oCY = o.cY;
        float oCZ = o.cZ;
        float oUXx = o.uXx;
        float oUXy = o.uXy;
        float oUXz = o.uXz;
        float oUYx = o.uYx;
        float oUYy = o.uYy;
        float oUYz = o.uYz;
        float oUZx = o.uZx;
        float oUZy = o.uZy;
        float oUZz = o.uZz;
        float oHsX = o.hsX;
        float oHsY = o.hsY;
        float oHsZ = o.hsZ;
        if (Math.useFma()) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s3eb004de_tail_fma(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t45, float _t25, float _t46, float _t26, float _t47, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float epsilon, float _t0, float _t1, float _t2) {
        float _t27 = java.lang.Math.fma(oUXz, this.uYz, java.lang.Math.fma(oUXx, this.uYx, oUXy * this.uYy));
        float _t28 = java.lang.Math.fma(oUYz, this.uYz, java.lang.Math.fma(oUYx, this.uYx, oUYy * this.uYy));
        float _t29 = java.lang.Math.fma(oUZz, this.uYz, java.lang.Math.fma(oUZx, this.uYx, oUZy * this.uYy));
        float _t48 = epsilon + java.lang.Math.abs(_t27);
        float _t49 = epsilon + java.lang.Math.abs(_t28);
        float _t50 = epsilon + java.lang.Math.abs(_t29);
        float _t55 = java.lang.Math.fma(this.uYz, _t0, java.lang.Math.fma(this.uYx, _t1, this.uYy * _t2));
        return (java.lang.Math.abs(_t55) <= java.lang.Math.fma(oHsX, _t48, java.lang.Math.fma(oHsY, _t49, java.lang.Math.fma(oHsZ, _t50, this.hsY)))) && (intersectsOBB_s3eb004de_tail2_fma(oHsX, oHsY, oHsZ, _t24, _t54, _t27, _t55, _t45, _t48, _t25, _t28, _t46, _t49, _t26, _t29, _t47, _t50, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, epsilon, _t0, _t1, _t2));
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s3eb004de_tail_mulAdd(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t45, float _t25, float _t46, float _t26, float _t47, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float epsilon, float _t0, float _t1, float _t2) {
        float _t27 = ((oUXz) * (this.uYz) + (((oUXx) * (this.uYx) + (oUXy * this.uYy))));
        float _t28 = ((oUYz) * (this.uYz) + (((oUYx) * (this.uYx) + (oUYy * this.uYy))));
        float _t29 = ((oUZz) * (this.uYz) + (((oUZx) * (this.uYx) + (oUZy * this.uYy))));
        float _t48 = epsilon + java.lang.Math.abs(_t27);
        float _t49 = epsilon + java.lang.Math.abs(_t28);
        float _t50 = epsilon + java.lang.Math.abs(_t29);
        float _t55 = ((this.uYz) * (_t0) + (((this.uYx) * (_t1) + (this.uYy * _t2))));
        return (java.lang.Math.abs(_t55) <= ((oHsX) * (_t48) + (((oHsY) * (_t49) + (((oHsZ) * (_t50) + (this.hsY))))))) && (intersectsOBB_s3eb004de_tail2_mulAdd(oHsX, oHsY, oHsZ, _t24, _t54, _t27, _t55, _t45, _t48, _t25, _t28, _t46, _t49, _t26, _t29, _t47, _t50, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, epsilon, _t0, _t1, _t2));
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s3eb004de_tail2_fma(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t27, float _t55, float _t45, float _t48, float _t25, float _t28, float _t46, float _t49, float _t26, float _t29, float _t47, float _t50, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float epsilon, float _t0, float _t1, float _t2) {
        float _t30 = java.lang.Math.fma(oUXz, this.uZz, java.lang.Math.fma(oUXx, this.uZx, oUXy * this.uZy));
        float _t31 = java.lang.Math.fma(oUYz, this.uZz, java.lang.Math.fma(oUYx, this.uZx, oUYy * this.uZy));
        float _t32 = java.lang.Math.fma(oUZz, this.uZz, java.lang.Math.fma(oUZx, this.uZx, oUZy * this.uZy));
        float _t51 = epsilon + java.lang.Math.abs(_t30);
        float _t52 = epsilon + java.lang.Math.abs(_t31);
        float _t53 = epsilon + java.lang.Math.abs(_t32);
        float _t56 = java.lang.Math.fma(this.uZz, _t0, java.lang.Math.fma(this.uZx, _t1, this.uZy * _t2));
        return (java.lang.Math.abs(_t56) <= java.lang.Math.fma(oHsX, _t51, java.lang.Math.fma(oHsY, _t52, java.lang.Math.fma(oHsZ, _t53, this.hsZ)))) && ((java.lang.Math.abs(java.lang.Math.fma(_t30, _t56, java.lang.Math.fma(_t24, _t54, _t27 * _t55))) <= java.lang.Math.fma(this.hsX, _t45, java.lang.Math.fma(this.hsY, _t48, java.lang.Math.fma(this.hsZ, _t51, oHsX)))) && (intersectsOBB_s2f6d5554_tail3_fma(_t31, _t56, _t25, _t54, _t28, _t55, _t46, _t49, _t52, oHsY, _t32, _t26, _t29, _t47, _t50, _t53, oHsZ, _t27, _t30, _t51, _t48, oHsX, _t45, _t24)));
    }

    /** Private tail of {@code intersectsOBB}; reached only through it. */
    private boolean intersectsOBB_s3eb004de_tail2_mulAdd(float oHsX, float oHsY, float oHsZ, float _t24, float _t54, float _t27, float _t55, float _t45, float _t48, float _t25, float _t28, float _t46, float _t49, float _t26, float _t29, float _t47, float _t50, float oUXz, float oUXx, float oUXy, float oUYz, float oUYx, float oUYy, float oUZz, float oUZx, float oUZy, float epsilon, float _t0, float _t1, float _t2) {
        float _t30 = ((oUXz) * (this.uZz) + (((oUXx) * (this.uZx) + (oUXy * this.uZy))));
        float _t31 = ((oUYz) * (this.uZz) + (((oUYx) * (this.uZx) + (oUYy * this.uZy))));
        float _t32 = ((oUZz) * (this.uZz) + (((oUZx) * (this.uZx) + (oUZy * this.uZy))));
        float _t51 = epsilon + java.lang.Math.abs(_t30);
        float _t52 = epsilon + java.lang.Math.abs(_t31);
        float _t53 = epsilon + java.lang.Math.abs(_t32);
        float _t56 = ((this.uZz) * (_t0) + (((this.uZx) * (_t1) + (this.uZy * _t2))));
        return (java.lang.Math.abs(_t56) <= ((oHsX) * (_t51) + (((oHsY) * (_t52) + (((oHsZ) * (_t53) + (this.hsZ))))))) && ((java.lang.Math.abs(((_t30) * (_t56) + (((_t24) * (_t54) + (_t27 * _t55))))) <= ((this.hsX) * (_t45) + (((this.hsY) * (_t48) + (((this.hsZ) * (_t51) + (oHsX))))))) && (intersectsOBB_s2f6d5554_tail3_mulAdd(_t31, _t56, _t25, _t54, _t28, _t55, _t46, _t49, _t52, oHsY, _t32, _t26, _t29, _t47, _t50, _t53, oHsZ, _t27, _t30, _t51, _t48, oHsX, _t45, _t24)));
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
     *        the test more conservative; the overload without it uses {@code 1e-5}
     * @return {@code true} if this oriented bounding box intersects ({@code oCX}, {@code oCY},
     *        {@code oCZ}, {@code oUXx}, {@code oUXy}, {@code oUXz}, {@code oUYx}, {@code oUYy},
     *        {@code oUYz}, {@code oUZx}, {@code oUZy}, {@code oUZz}, {@code oHsX}, {@code oHsY},
     *        {@code oHsZ}), {@code false} otherwise
     */
    public boolean intersectsOBB(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon) {
        if (Math.useFma()) return intersectsOBB_fma(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
        return intersectsOBB_mulAdd(oCX, oCY, oCZ, oUXx, oUXy, oUXz, oUYx, oUYy, oUYz, oUZx, oUZy, oUZz, oHsX, oHsY, oHsZ, epsilon);
    }

    /** {@code intersectsOBB} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_fma(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon) {
        float _t0 = oCZ - this.cZ;
        float _t1 = oCX - this.cX;
        float _t2 = oCY - this.cY;
        float _t24 = java.lang.Math.fma(oUXz, this.uXz, java.lang.Math.fma(oUXx, this.uXx, oUXy * this.uXy));
        float _t25 = java.lang.Math.fma(oUYz, this.uXz, java.lang.Math.fma(oUYx, this.uXx, oUYy * this.uXy));
        float _t26 = java.lang.Math.fma(oUZz, this.uXz, java.lang.Math.fma(oUZx, this.uXx, oUZy * this.uXy));
        float _t45 = epsilon + java.lang.Math.abs(_t24);
        float _t46 = epsilon + java.lang.Math.abs(_t25);
        float _t47 = epsilon + java.lang.Math.abs(_t26);
        float _t54 = java.lang.Math.fma(this.uXz, _t0, java.lang.Math.fma(this.uXx, _t1, this.uXy * _t2));
        return (java.lang.Math.abs(_t54) <= java.lang.Math.fma(oHsX, _t45, java.lang.Math.fma(oHsY, _t46, java.lang.Math.fma(oHsZ, _t47, this.hsX)))) && (intersectsOBB_s3eb004de_tail_fma(oHsX, oHsY, oHsZ, _t24, _t54, _t45, _t25, _t46, _t26, _t47, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, epsilon, _t0, _t1, _t2));
    }

    /** {@code intersectsOBB} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean intersectsOBB_mulAdd(float oCX, float oCY, float oCZ, float oUXx, float oUXy, float oUXz, float oUYx, float oUYy, float oUYz, float oUZx, float oUZy, float oUZz, float oHsX, float oHsY, float oHsZ, float epsilon) {
        float _t0 = oCZ - this.cZ;
        float _t1 = oCX - this.cX;
        float _t2 = oCY - this.cY;
        float _t24 = ((oUXz) * (this.uXz) + (((oUXx) * (this.uXx) + (oUXy * this.uXy))));
        float _t25 = ((oUYz) * (this.uXz) + (((oUYx) * (this.uXx) + (oUYy * this.uXy))));
        float _t26 = ((oUZz) * (this.uXz) + (((oUZx) * (this.uXx) + (oUZy * this.uXy))));
        float _t45 = epsilon + java.lang.Math.abs(_t24);
        float _t46 = epsilon + java.lang.Math.abs(_t25);
        float _t47 = epsilon + java.lang.Math.abs(_t26);
        float _t54 = ((this.uXz) * (_t0) + (((this.uXx) * (_t1) + (this.uXy * _t2))));
        return (java.lang.Math.abs(_t54) <= ((oHsX) * (_t45) + (((oHsY) * (_t46) + (((oHsZ) * (_t47) + (this.hsX))))))) && (intersectsOBB_s3eb004de_tail_mulAdd(oHsX, oHsY, oHsZ, _t24, _t54, _t45, _t25, _t46, _t26, _t47, oUXz, oUXx, oUXy, oUYz, oUYx, oUYy, oUZz, oUZx, oUZy, epsilon, _t0, _t1, _t2));
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
    public boolean equalsEpsilon(FloatOBB other, float epsilon) {
        return java.lang.Math.abs(cX - other.cX) <= epsilon
            && java.lang.Math.abs(cY - other.cY) <= epsilon
            && java.lang.Math.abs(cZ - other.cZ) <= epsilon
            && java.lang.Math.abs(uXx - other.uXx) <= epsilon
            && java.lang.Math.abs(uXy - other.uXy) <= epsilon
            && java.lang.Math.abs(uXz - other.uXz) <= epsilon
            && java.lang.Math.abs(uYx - other.uYx) <= epsilon
            && java.lang.Math.abs(uYy - other.uYy) <= epsilon
            && java.lang.Math.abs(uYz - other.uYz) <= epsilon
            && java.lang.Math.abs(uZx - other.uZx) <= epsilon
            && java.lang.Math.abs(uZy - other.uZy) <= epsilon
            && java.lang.Math.abs(uZz - other.uZz) <= epsilon
            && java.lang.Math.abs(hsX - other.hsX) <= epsilon
            && java.lang.Math.abs(hsY - other.hsY) <= epsilon
            && java.lang.Math.abs(hsZ - other.hsZ) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatOBBSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatOBBSegOpsUnsafe()
            : new FloatOBBSegOpsMS();
        static final FloatOBBBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatOBBBbOpsUnsafe()
            : new FloatOBBBbOpsApi();
        static final FloatOBBRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatOBBRawOpsUnsafe()
            : new FloatOBBRawOpsApi();
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
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
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
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(float[] src) { return load(src, 0); }

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
    public FloatBuffer store(FloatBuffer buf) {
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
    public FloatBuffer storeAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(FloatBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(ByteBuffer buf) {
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadAbsolute(int index, ByteBuffer buf) {
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
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
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
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }

    /**
     * Store the elements into the given array, converting each element to {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
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
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer store(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer storeAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
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
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
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
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
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
    public MemorySegment storeDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double},
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
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code FloatOBB} holding the loaded elements
     */
    public static FloatOBB loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double},
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
