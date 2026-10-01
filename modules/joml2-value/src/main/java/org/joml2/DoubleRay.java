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
 * Immutable ray of double-precision {@code double} components, declared as a value record.
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
 * @param oX the {@code oX} component
 * @param oY the {@code oY} component
 * @param oZ the {@code oZ} component
 * @param dX the {@code dX} component
 * @param dY the {@code dY} component
 * @param dZ the {@code dZ} component
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record DoubleRay(double oX, double oY, double oZ, double dX, double dY, double dZ) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 48;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param oX the {@code oX} component
     * @param oY the {@code oY} component
     * @param oZ the {@code oZ} component
     * @param dX the {@code dX} component
     * @param dY the {@code dY} component
     * @param dZ the {@code dZ} component
     */
    public DoubleRay(double oX, double oY, double oZ, double dX, double dY, double dZ) {
        this.oX = oX;
        this.oY = oY;
        this.oZ = oZ;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: the method reads no input.
     */
    public DoubleRay() {
        this(0, 0, 0, 0, 0, 0);
    }

    /**
     * Create a ray from its origin and direction.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     */
    public DoubleRay(Double3 origin, Double3 direction) {
        this(origin.x(), origin.y(), origin.z(), direction.x(), direction.y(), direction.z());
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
    public static DoubleRay[] newArray(int length) {
        return (DoubleRay[]) org.joml2.internal.value.FlatArrays.newArray(DoubleRay.class, length, new DoubleRay(0, 0, 0, 0, 0, 0));
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
    public static DoubleRay[] newArray(int length, DoubleRay init) {
        return (DoubleRay[]) org.joml2.internal.value.FlatArrays.newArray(DoubleRay.class, length, init);
    }

    /** {@return the {@code oX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double oX() { return oX; }

    /** {@return the {@code oY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double oY() { return oY; }

    /** {@return the {@code oZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double oZ() { return oZ; }

    /** {@return the {@code dX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double dX() { return dX; }

    /** {@return the {@code dY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double dY() { return dY; }

    /** {@return the {@code dZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double dZ() { return dZ; }

    /**
     * Create a new ray from its origin and direction.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     * @return the resulting ray
     */
    public DoubleRay set(Double3 origin, Double3 direction) {
        return new DoubleRay(origin, direction);
    }

    /**
     * Return a ray with the values of {@code v}: that ray itself (an immutable value needs no
     * copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the ray to copy
     * @return the resulting ray
     */
    public DoubleRay set(DoubleRay v) {
        double vOX = v.oX;
        double vOY = v.oY;
        double vOZ = v.oZ;
        double vDX = v.dX;
        double vDY = v.dY;
        double vDZ = v.dZ;
        return new DoubleRay(vOX, vOY, vOZ, vDX, vDY, vDZ);
    }

    /**
     * Create a new ray from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vOX the {@code oX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOY the {@code oY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOZ the {@code oZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDX the {@code dX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDY the {@code dY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDZ the {@code dZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @return the resulting ray
     */
    public DoubleRay set(double vOX, double vOY, double vOZ, double vDX, double vDY, double vDZ) {
        return new DoubleRay(vOX, vOY, vOZ, vDX, vDY, vDZ);
    }

    /**
     * Set the direction of this ray to {@code d}, returning the result as a value.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @return the resulting ray
     */
    public DoubleRay setDirection(Double3 d) {
        double dX = d.x();
        double dY = d.y();
        double dZ = d.z();
        return new DoubleRay(this.oX, this.oY, this.oZ, dX, dY, dZ);
    }

    /**
     * Set the direction of this ray to ({@code dX}, {@code dY}, {@code dZ}), returning the result
     * as a value.
     * <p>
     * Valid input: {@code (dX, dY, dZ)} must be non-zero.
     *
     * @param dX the {@code x} component of the vector {@code (dX, dY, dZ)}
     * @param dY the {@code y} component of the vector {@code (dX, dY, dZ)}
     * @param dZ the {@code z} component of the vector {@code (dX, dY, dZ)}
     * @return the resulting ray
     */
    public DoubleRay setDirection(double dX, double dY, double dZ) {
        return new DoubleRay(this.oX, this.oY, this.oZ, dX, dY, dZ);
    }

    /**
     * Set the origin of this ray to {@code o}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @return the resulting ray
     */
    public DoubleRay setOrigin(Double3 o) {
        double oX = o.x();
        double oY = o.y();
        double oZ = o.z();
        return new DoubleRay(oX, oY, oZ, this.dX, this.dY, this.dZ);
    }

    /**
     * Set the origin of this ray to ({@code oX}, {@code oY}, {@code oZ}), returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oX the {@code x} component of the vector {@code (oX, oY, oZ)}
     * @param oY the {@code y} component of the vector {@code (oX, oY, oZ)}
     * @param oZ the {@code z} component of the vector {@code (oX, oY, oZ)}
     * @return the resulting ray
     */
    public DoubleRay setOrigin(double oX, double oY, double oZ) {
        return new DoubleRay(oX, oY, oZ, this.dX, this.dY, this.dZ);
    }

    /**
     * Convert this ray to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatRay} holding the result
     */
    public FloatRay toFloat() {
        return new FloatRay((float) (this.oX), (float) (this.oY), (float) (this.oZ), (float) (this.dX), (float) (this.dY), (float) (this.dZ));
    }

    /**
     * Transform this ray by {@code m}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting ray
     */
    public DoubleRay transform(Double3x4 m) {
        if (!Math.useFma()) return transform_mulAdd(m);
        double _m_m02 = m.m02();
        double _m_m00 = m.m00();
        double _m_m01 = m.m01();
        double _m_m12 = m.m12();
        double _m_m10 = m.m10();
        double _m_m11 = m.m11();
        double _m_m22 = m.m22();
        double _m_m20 = m.m20();
        double _m_m21 = m.m21();
        return new DoubleRay(java.lang.Math.fma(_m_m02, this.oZ, java.lang.Math.fma(_m_m00, this.oX, java.lang.Math.fma(_m_m01, this.oY, m.m03()))), java.lang.Math.fma(_m_m12, this.oZ, java.lang.Math.fma(_m_m10, this.oX, java.lang.Math.fma(_m_m11, this.oY, m.m13()))), java.lang.Math.fma(_m_m22, this.oZ, java.lang.Math.fma(_m_m20, this.oX, java.lang.Math.fma(_m_m21, this.oY, m.m23()))), java.lang.Math.fma(_m_m02, this.dZ, java.lang.Math.fma(_m_m00, this.dX, _m_m01 * this.dY)), java.lang.Math.fma(_m_m12, this.dZ, java.lang.Math.fma(_m_m10, this.dX, _m_m11 * this.dY)), java.lang.Math.fma(_m_m22, this.dZ, java.lang.Math.fma(_m_m20, this.dX, _m_m21 * this.dY)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRay transform_mulAdd(Double3x4 m) {
        double _m_m02 = m.m02();
        double _m_m00 = m.m00();
        double _m_m01 = m.m01();
        double _m_m12 = m.m12();
        double _m_m10 = m.m10();
        double _m_m11 = m.m11();
        double _m_m22 = m.m22();
        double _m_m20 = m.m20();
        double _m_m21 = m.m21();
        return new DoubleRay(((_m_m02) * (this.oZ) + (((_m_m00) * (this.oX) + (((_m_m01) * (this.oY) + (m.m03())))))), ((_m_m12) * (this.oZ) + (((_m_m10) * (this.oX) + (((_m_m11) * (this.oY) + (m.m13())))))), ((_m_m22) * (this.oZ) + (((_m_m20) * (this.oX) + (((_m_m21) * (this.oY) + (m.m23())))))), ((_m_m02) * (this.dZ) + (((_m_m00) * (this.dX) + (_m_m01 * this.dY)))), ((_m_m12) * (this.dZ) + (((_m_m10) * (this.dX) + (_m_m11 * this.dY)))), ((_m_m22) * (this.dZ) + (((_m_m20) * (this.dX) + (_m_m21 * this.dY)))));
    }

    /**
     * Transform this ray by {@code m}, returning the result as a value.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting ray
     */
    public DoubleRay transform(Double4x4 m) {
        if (!Math.useFma()) return transform_mulAdd(m);
        double _m_m02 = m.m02();
        double _m_m00 = m.m00();
        double _m_m01 = m.m01();
        double _m_m12 = m.m12();
        double _m_m10 = m.m10();
        double _m_m11 = m.m11();
        double _m_m22 = m.m22();
        double _m_m20 = m.m20();
        double _m_m21 = m.m21();
        return new DoubleRay(java.lang.Math.fma(_m_m02, this.oZ, java.lang.Math.fma(_m_m00, this.oX, java.lang.Math.fma(_m_m01, this.oY, m.m03()))), java.lang.Math.fma(_m_m12, this.oZ, java.lang.Math.fma(_m_m10, this.oX, java.lang.Math.fma(_m_m11, this.oY, m.m13()))), java.lang.Math.fma(_m_m22, this.oZ, java.lang.Math.fma(_m_m20, this.oX, java.lang.Math.fma(_m_m21, this.oY, m.m23()))), java.lang.Math.fma(_m_m02, this.dZ, java.lang.Math.fma(_m_m00, this.dX, _m_m01 * this.dY)), java.lang.Math.fma(_m_m12, this.dZ, java.lang.Math.fma(_m_m10, this.dX, _m_m11 * this.dY)), java.lang.Math.fma(_m_m22, this.dZ, java.lang.Math.fma(_m_m20, this.dX, _m_m21 * this.dY)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRay transform_mulAdd(Double4x4 m) {
        double _m_m02 = m.m02();
        double _m_m00 = m.m00();
        double _m_m01 = m.m01();
        double _m_m12 = m.m12();
        double _m_m10 = m.m10();
        double _m_m11 = m.m11();
        double _m_m22 = m.m22();
        double _m_m20 = m.m20();
        double _m_m21 = m.m21();
        return new DoubleRay(((_m_m02) * (this.oZ) + (((_m_m00) * (this.oX) + (((_m_m01) * (this.oY) + (m.m03())))))), ((_m_m12) * (this.oZ) + (((_m_m10) * (this.oX) + (((_m_m11) * (this.oY) + (m.m13())))))), ((_m_m22) * (this.oZ) + (((_m_m20) * (this.oX) + (((_m_m21) * (this.oY) + (m.m23())))))), ((_m_m02) * (this.dZ) + (((_m_m00) * (this.dX) + (_m_m01 * this.dY)))), ((_m_m12) * (this.dZ) + (((_m_m10) * (this.dX) + (_m_m11 * this.dY)))), ((_m_m22) * (this.dZ) + (((_m_m20) * (this.dX) + (_m_m21 * this.dY)))));
    }

    /**
     * Compute the point on this ray at the parameter value {@code t}, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the ray parameter: the point is {@code origin + t * direction}
     * @return the resulting vector
     */
    public Double3 at(double t) {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(t, this.dX, this.oX), java.lang.Math.fma(t, this.dY, this.oY), java.lang.Math.fma(t, this.dZ, this.oZ));
        } else {
            return new Double3(((t) * (this.dX) + (this.oX)), ((t) * (this.dY) + (this.oY)), ((t) * (this.dZ) + (this.oZ)));
        }
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Double3 closestPointToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double _t10 = Math.max(0.0, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            return new Double3(java.lang.Math.fma(this.dX, _t10, this.oX), java.lang.Math.fma(this.dY, _t10, this.oY), java.lang.Math.fma(this.dZ, _t10, this.oZ));
        } else {
            double _t10 = Math.max(0.0, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            return new Double3(((this.dX) * (_t10) + (this.oX)), ((this.dY) * (_t10) + (this.oY)), ((this.dZ) * (_t10) + (this.oZ)));
        }
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * direction of this ray must be non-zero.
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
        if (Math.useFma()) {
            double _t10 = Math.max(0.0, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            return new Double3(java.lang.Math.fma(this.dX, _t10, this.oX), java.lang.Math.fma(this.dY, _t10, this.oY), java.lang.Math.fma(this.dZ, _t10, this.oZ));
        } else {
            double _t10 = Math.max(0.0, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            return new Double3(((this.dX) * (_t10) + (this.oX)), ((this.dY) * (_t10) + (this.oY)), ((this.dZ) * (_t10) + (this.oZ)));
        }
    }

    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this ray and the given point, i.e. the squared distance
     *        from the point to the closest point on the ray (the ray starts at its origin and
     *        extends only along its direction). The direction need not be of unit length but must
     *        not be zero
     */
    public double distanceSquaredToPoint(Double3 p) {
        return distanceSquaredToPoint(p.x(), p.y(), p.z());
    }

    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * direction of this ray must be non-zero.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this ray and the given point, i.e. the squared distance
     *        from the point to the closest point on the ray (the ray starts at its origin and
     *        extends only along its direction). The direction need not be of unit length but must
     *        not be zero
     */
    public double distanceSquaredToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double _t3 = pZ - this.oZ;
            double _t4 = pX - this.oX;
            double _t5 = pY - this.oY;
            double _t13 = Math.max(0.0, java.lang.Math.fma(this.dZ, _t3, java.lang.Math.fma(this.dX, _t4, this.dY * _t5)) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            double _t14 = java.lang.Math.fma(-this.dZ, _t13, _t3);
            double _t15 = java.lang.Math.fma(-this.dX, _t13, _t4);
            double _t16 = java.lang.Math.fma(-this.dY, _t13, _t5);
            return java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16));
        } else {
            double _t3 = pZ - this.oZ;
            double _t4 = pX - this.oX;
            double _t5 = pY - this.oY;
            double _t13 = Math.max(0.0, ((this.dZ) * (_t3) + (((this.dX) * (_t4) + (this.dY * _t5)))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            double _t14 = ((_t3) - (this.dZ) * (_t13));
            double _t15 = ((_t4) - (this.dX) * (_t13));
            double _t16 = ((_t5) - (this.dY) * (_t13));
            return ((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))));
        }
    }

    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the distance between this ray and the given point, i.e. the distance from the point
     *        to the closest point on the ray (the ray starts at its origin and extends only along
     *        its direction). The direction need not be of unit length but must not be zero
     */
    public double distanceToPoint(Double3 p) {
        return distanceToPoint(p.x(), p.y(), p.z());
    }

    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * direction of this ray must be non-zero.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this ray and the given point, i.e. the distance from the point
     *        to the closest point on the ray (the ray starts at its origin and extends only along
     *        its direction). The direction need not be of unit length but must not be zero
     */
    public double distanceToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double _t3 = pZ - this.oZ;
            double _t4 = pX - this.oX;
            double _t5 = pY - this.oY;
            double _t13 = Math.max(0.0, java.lang.Math.fma(this.dZ, _t3, java.lang.Math.fma(this.dX, _t4, this.dY * _t5)) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            double _t14 = java.lang.Math.fma(-this.dZ, _t13, _t3);
            double _t15 = java.lang.Math.fma(-this.dX, _t13, _t4);
            double _t16 = java.lang.Math.fma(-this.dY, _t13, _t5);
            return java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        } else {
            double _t3 = pZ - this.oZ;
            double _t4 = pX - this.oX;
            double _t5 = pY - this.oY;
            double _t13 = Math.max(0.0, ((this.dZ) * (_t3) + (((this.dX) * (_t4) + (this.dY * _t5)))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            double _t14 = ((_t3) - (this.dZ) * (_t13));
            double _t15 = ((_t4) - (this.dX) * (_t13));
            double _t16 = ((_t5) - (this.dY) * (_t13));
            return java.lang.Math.sqrt(((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16)))));
        }
    }

    /**
     * Get the direction of this ray, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getDirection() {
        return new Double3(this.dX, this.dY, this.dZ);
    }

    /**
     * Get the origin of this ray, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getOrigin() {
        return new Double3(this.oX, this.oY, this.oZ);
    }

    /**
     * {@return a copy with the {@code oX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code oX} component
     */
    public DoubleRay withOX(double v) {
        return new DoubleRay(v, oY, oZ, dX, dY, dZ);
    }

    /**
     * {@return a copy with the {@code oY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code oY} component
     */
    public DoubleRay withOY(double v) {
        return new DoubleRay(oX, v, oZ, dX, dY, dZ);
    }

    /**
     * {@return a copy with the {@code oZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code oZ} component
     */
    public DoubleRay withOZ(double v) {
        return new DoubleRay(oX, oY, v, dX, dY, dZ);
    }

    /**
     * {@return a copy with the {@code dX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dX} component
     */
    public DoubleRay withDX(double v) {
        return new DoubleRay(oX, oY, oZ, v, dY, dZ);
    }

    /**
     * {@return a copy with the {@code dY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dY} component
     */
    public DoubleRay withDY(double v) {
        return new DoubleRay(oX, oY, oZ, dX, v, dZ);
    }

    /**
     * {@return a copy with the {@code dZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dZ} component
     */
    public DoubleRay withDZ(double v) {
        return new DoubleRay(oX, oY, oZ, dX, dY, v);
    }

    @Override public String toString() {
        return "DoubleRay(" + this.oX + ", " + this.oY + ", " + this.oZ + ", " + this.dX + ", " + this.dY + ", " + this.dZ + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleRay)) return false;
        DoubleRay o = (DoubleRay) obj;
        return Double.doubleToLongBits(oX) == Double.doubleToLongBits(o.oX)
            && Double.doubleToLongBits(oY) == Double.doubleToLongBits(o.oY)
            && Double.doubleToLongBits(oZ) == Double.doubleToLongBits(o.oZ)
            && Double.doubleToLongBits(dX) == Double.doubleToLongBits(o.dX)
            && Double.doubleToLongBits(dY) == Double.doubleToLongBits(o.dY)
            && Double.doubleToLongBits(dZ) == Double.doubleToLongBits(o.dZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(oX) ^ (Double.doubleToLongBits(oX) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(oY) ^ (Double.doubleToLongBits(oY) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(oZ) ^ (Double.doubleToLongBits(oZ) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(dX) ^ (Double.doubleToLongBits(dX) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(dY) ^ (Double.doubleToLongBits(dY) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(dZ) ^ (Double.doubleToLongBits(dZ) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(oX)
            && Double.isFinite(oY)
            && Double.isFinite(oZ)
            && Double.isFinite(dX)
            && Double.isFinite(dY)
            && Double.isFinite(dZ);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(oX)
            || Double.isNaN(oY)
            || Double.isNaN(oZ)
            || Double.isNaN(dX)
            || Double.isNaN(dY)
            || Double.isNaN(dZ);
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
    public boolean equalsEpsilon(DoubleRay other, double epsilon) {
        return java.lang.Math.abs(oX - other.oX) <= epsilon
            && java.lang.Math.abs(oY - other.oY) <= epsilon
            && java.lang.Math.abs(oZ - other.oZ) <= epsilon
            && java.lang.Math.abs(dX - other.dX) <= epsilon
            && java.lang.Math.abs(dY - other.dY) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleRaySegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleRaySegOpsUnsafe()
            : new DoubleRaySegOpsMS();
        static final DoubleRayBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleRayBbOpsUnsafe()
            : new DoubleRayBbOpsApi();
        static final DoubleRayRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleRayRawOpsUnsafe()
            : new DoubleRayRawOpsApi();
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
        dest[offset] = this.oX;
        dest[offset + 1] = this.oY;
        dest[offset + 2] = this.oZ;
        dest[offset + 3] = this.dX;
        dest[offset + 4] = this.dY;
        dest[offset + 5] = this.dZ;
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(double[] src) { return load(src, 0); }

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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(DoubleBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRay loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRay r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 48);
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(ByteBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRay loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRay r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 48);
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
    public DoubleRay storeUnsafe(long address) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadUnsafe(long address) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(long offset, MemorySegment src) {
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
        dest[offset] = (float) this.oX;
        dest[offset + 1] = (float) this.oY;
        dest[offset + 2] = (float) this.oZ;
        dest[offset + 3] = (float) this.dX;
        dest[offset + 4] = (float) this.dY;
        dest[offset + 5] = (float) this.dZ;
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(float[] src) { return load(src, 0); }

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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay load(FloatBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRay loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRay r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
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
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadFloat(ByteBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRay loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRay r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 24);
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
    public DoubleRay storeFloatUnsafe(long address) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadFloatUnsafe(long address) {
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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(0L, src); }

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
     * @return a new {@code DoubleRay} holding the loaded elements
     */
    public static DoubleRay loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(offset, src);
    }

    private abstract static class DoubleRaySegOps {
        public abstract MemorySegment store(DoubleRay self, long offset, MemorySegment dest);
        public abstract DoubleRay load(long offset, MemorySegment src);
        public abstract MemorySegment storeFloat(DoubleRay self, long offset, MemorySegment dest);
        public abstract DoubleRay loadFloat(long offset, MemorySegment src);
    }

    private static final class DoubleRaySegOpsMS extends DoubleRaySegOps {
        public MemorySegment store(DoubleRay self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.oX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.oY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.oZ);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.dX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dZ);
            return dest;
        }

        public DoubleRay load(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeFloat(DoubleRay self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.oX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.oY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.oZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.dX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.dY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.dZ);
            return dest;
        }

        public DoubleRay loadFloat(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private static final class DoubleRaySegOpsUnsafe extends DoubleRaySegOps {
        private static final DoubleRaySegOpsMS MS = new DoubleRaySegOpsMS();

        public MemorySegment store(DoubleRay self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.store(self, offset, dest);
            if (!dest.isNative()) return storeOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return dest;
        }

        private static MemorySegment storeOnHeap(DoubleRay self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.store(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.oX);
            _U.putDouble(bytes, address + 8L, self.oY);
            _U.putDouble(bytes, address + 16L, self.oZ);
            _U.putDouble(bytes, address + 24L, self.dX);
            _U.putDouble(bytes, address + 32L, self.dY);
            _U.putDouble(bytes, address + 40L, self.dZ);
            return dest;
        }

        public DoubleRay load(long offset, MemorySegment src) {
            if (!src.isNative()) return loadOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static DoubleRay loadOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.load(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeFloat(DoubleRay self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
            if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.oX);
            _U.putFloat(address + 4L, (float) self.oY);
            _U.putFloat(address + 8L, (float) self.oZ);
            _U.putFloat(address + 12L, (float) self.dX);
            _U.putFloat(address + 16L, (float) self.dY);
            _U.putFloat(address + 20L, (float) self.dZ);
            return dest;
        }

        private static MemorySegment storeFloatOnHeap(DoubleRay self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.oX);
            _U.putFloat(bytes, address + 4L, (float) self.oY);
            _U.putFloat(bytes, address + 8L, (float) self.oZ);
            _U.putFloat(bytes, address + 12L, (float) self.dX);
            _U.putFloat(bytes, address + 16L, (float) self.dY);
            _U.putFloat(bytes, address + 20L, (float) self.dZ);
            return dest;
        }

        public DoubleRay loadFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadFloatOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static DoubleRay loadFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private abstract static class DoubleRayBbOps {
        public abstract DoubleBuffer storeAbsolute(DoubleRay self, int index, DoubleBuffer buf);
        public abstract DoubleRay loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeAbsolute(DoubleRay self, int index, ByteBuffer buf);
        public abstract DoubleRay loadAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeAbsolute(DoubleRay self, int index, FloatBuffer buf);
        public abstract DoubleRay loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeFloatAbsolute(DoubleRay self, int index, ByteBuffer buf);
        public abstract DoubleRay loadFloatAbsolute(int index, ByteBuffer buf);
    }

    private static final class DoubleRayBbOpsApi extends DoubleRayBbOps {
        public DoubleBuffer storeAbsolute(DoubleRay self, int index, DoubleBuffer buf) {
            buf.put(index, self.oX);
            buf.put(index + 1, self.oY);
            buf.put(index + 2, self.oZ);
            buf.put(index + 3, self.dX);
            buf.put(index + 4, self.dY);
            buf.put(index + 5, self.dZ);
            return buf;
        }

        public DoubleRay loadAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeAbsolute(DoubleRay self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.oX);
            buf.putDouble(index + 8, self.oY);
            buf.putDouble(index + 16, self.oZ);
            buf.putDouble(index + 24, self.dX);
            buf.putDouble(index + 32, self.dY);
            buf.putDouble(index + 40, self.dZ);
            return buf;
        }

        public DoubleRay loadAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            double _c4 = buf.getDouble(index + 32);
            double _c5 = buf.getDouble(index + 40);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeAbsolute(DoubleRay self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.oX);
            buf.put(index + 1, (float) self.oY);
            buf.put(index + 2, (float) self.oZ);
            buf.put(index + 3, (float) self.dX);
            buf.put(index + 4, (float) self.dY);
            buf.put(index + 5, (float) self.dZ);
            return buf;
        }

        public DoubleRay loadAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeFloatAbsolute(DoubleRay self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.oX);
            buf.putFloat(index + 4, (float) self.oY);
            buf.putFloat(index + 8, (float) self.oZ);
            buf.putFloat(index + 12, (float) self.dX);
            buf.putFloat(index + 16, (float) self.dY);
            buf.putFloat(index + 20, (float) self.dZ);
            return buf;
        }

        public DoubleRay loadFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            double _c4 = buf.getFloat(index + 16);
            double _c5 = buf.getFloat(index + 20);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private static final class DoubleRayBbOpsUnsafe extends DoubleRayBbOps {
        private static final DoubleRayBbOpsApi API = new DoubleRayBbOpsApi();

        public DoubleBuffer storeAbsolute(DoubleRay self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return buf;
        }

        public DoubleRay loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeAbsolute(DoubleRay self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(DoubleRay self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.oX);
            _U.putDouble(bytes, address + 8L, self.oY);
            _U.putDouble(bytes, address + 16L, self.oZ);
            _U.putDouble(bytes, address + 24L, self.dX);
            _U.putDouble(bytes, address + 32L, self.dY);
            _U.putDouble(bytes, address + 40L, self.dZ);
            return buf;
        }

        public DoubleRay loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static DoubleRay loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeAbsolute(DoubleRay self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.oX);
            _U.putFloat(address + 4L, (float) self.oY);
            _U.putFloat(address + 8L, (float) self.oZ);
            _U.putFloat(address + 12L, (float) self.dX);
            _U.putFloat(address + 16L, (float) self.dY);
            _U.putFloat(address + 20L, (float) self.dZ);
            return buf;
        }

        public DoubleRay loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeFloatAbsolute(DoubleRay self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.oX);
            _U.putFloat(address + 4L, (float) self.oY);
            _U.putFloat(address + 8L, (float) self.oZ);
            _U.putFloat(address + 12L, (float) self.dX);
            _U.putFloat(address + 16L, (float) self.dY);
            _U.putFloat(address + 20L, (float) self.dZ);
            return buf;
        }

        private static ByteBuffer storeFloatAbsoluteElse(DoubleRay self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.oX);
            _U.putFloat(bytes, address + 4L, (float) self.oY);
            _U.putFloat(bytes, address + 8L, (float) self.oZ);
            _U.putFloat(bytes, address + 12L, (float) self.dX);
            _U.putFloat(bytes, address + 16L, (float) self.dY);
            _U.putFloat(bytes, address + 20L, (float) self.dZ);
            return buf;
        }

        public DoubleRay loadFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static DoubleRay loadFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private abstract static class DoubleRayRawOps {
        public abstract DoubleRay storeUnsafe(DoubleRay self, long address);
        public abstract DoubleRay loadUnsafe(long address);
        public abstract DoubleRay storeFloatUnsafe(DoubleRay self, long address);
        public abstract DoubleRay loadFloatUnsafe(long address);
    }

    private static final class DoubleRayRawOpsApi extends DoubleRayRawOps {
        public DoubleRay storeUnsafe(DoubleRay self, long address) {
            self.store(0L, virtualMemory().asSlice(address, 48L));
            return self;
        }

        public DoubleRay loadUnsafe(long address) {
            return DoubleRay.load(0L, virtualMemory().asSlice(address, 48L));
        }

        public DoubleRay storeFloatUnsafe(DoubleRay self, long address) {
            self.storeFloat(0L, virtualMemory().asSlice(address, 24L));
            return self;
        }

        public DoubleRay loadFloatUnsafe(long address) {
            return DoubleRay.loadFloat(0L, virtualMemory().asSlice(address, 24L));
        }
    }

    private static final class DoubleRayRawOpsUnsafe extends DoubleRayRawOps {
        public DoubleRay storeUnsafe(DoubleRay self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return self;
        }

        public DoubleRay storeHeap(DoubleRay self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.oX);
            _U.putDouble(bytes, address + 8L, self.oY);
            _U.putDouble(bytes, address + 16L, self.oZ);
            _U.putDouble(bytes, address + 24L, self.dX);
            _U.putDouble(bytes, address + 32L, self.dY);
            _U.putDouble(bytes, address + 40L, self.dZ);
            return self;
        }

        public DoubleRay loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleRay loadHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleRay storeFloatUnsafe(DoubleRay self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.oX);
            _U.putFloat(address + 4L, (float) self.oY);
            _U.putFloat(address + 8L, (float) self.oZ);
            _U.putFloat(address + 12L, (float) self.dX);
            _U.putFloat(address + 16L, (float) self.dY);
            _U.putFloat(address + 20L, (float) self.dZ);
            return self;
        }

        public DoubleRay storeFloatHeap(DoubleRay self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.oX);
            _U.putFloat(bytes, address + 4L, (float) self.oY);
            _U.putFloat(bytes, address + 8L, (float) self.oZ);
            _U.putFloat(bytes, address + 12L, (float) self.dX);
            _U.putFloat(bytes, address + 16L, (float) self.dY);
            _U.putFloat(bytes, address + 20L, (float) self.dZ);
            return self;
        }

        public DoubleRay loadFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleRay loadFloatHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }
}
