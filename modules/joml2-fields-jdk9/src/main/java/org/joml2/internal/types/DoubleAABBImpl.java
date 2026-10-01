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
 * Generated implementation of {@link DoubleAABB} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleAABBImpl implements DoubleAABB {

    public double minX;
    public double minY;
    public double minZ;
    public double maxX;
    public double maxY;
    public double maxZ;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleAABBBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleAABBBbOpsUnsafe()
                        : new DoubleAABBBbOpsApi();
        static final DoubleAABBRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleAABBRawOpsUnsafe()
                        : new DoubleAABBRawOpsApi();
    }

    public DoubleAABBImpl() {
        minX = Double.POSITIVE_INFINITY;
        minY = Double.POSITIVE_INFINITY;
        minZ = Double.POSITIVE_INFINITY;
        maxX = Double.NEGATIVE_INFINITY;
        maxY = Double.NEGATIVE_INFINITY;
        maxZ = Double.NEGATIVE_INFINITY;
    }

    public DoubleAABBImpl(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public DoubleAABBImpl(DoubleAABBR src) {
        this.minX = src.minX();
        this.minY = src.minY();
        this.minZ = src.minZ();
        this.maxX = src.maxX();
        this.maxY = src.maxY();
        this.maxZ = src.maxZ();
    }


    /**
     * Set this axis-aligned bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the axis-aligned bounding box to copy
     * @return this
     */
    public @Mutated DoubleAABB set(DoubleAABBR v) {
        return set(v.minX(), v.minY(), v.minZ(), v.maxX(), v.maxY(), v.maxZ());
    }


    /**
     * Set this axis-aligned bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minY the {@code minY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minZ the {@code minZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxX the {@code maxX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxY the {@code maxY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxZ the {@code maxZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @return this
     */
    @Mutated public DoubleAABB set(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        return this;
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMax(Double3R max, @Mutated DoubleAABB dest) {
        return setMax(max.x(), max.y(), max.z(), dest);
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to ({@code maxX}, {@code maxY},
     * {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMax(double maxX, double maxY, double maxZ, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.minZ = this.minZ;
        d.maxX = maxX;
        d.maxY = maxY;
        d.maxZ = maxZ;
        return d;
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to {@code min} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMin(Double3R min, @Mutated DoubleAABB dest) {
        return setMin(min.x(), min.y(), min.z(), dest);
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to ({@code minX}, {@code minY},
     * {@code minZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMin(double minX, double minY, double minZ, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = minX;
        d.minY = minY;
        d.minZ = minZ;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        d.maxZ = this.maxZ;
        return d;
    }


    /**
     * Convert this axis-aligned bounding box to {@code float} precision and store the result in
     * {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatAABB toFloat(@Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = (float) (this.minX);
        d.minY = (float) (this.minY);
        d.minZ = (float) (this.minZ);
        d.maxX = (float) (this.maxX);
        d.maxY = (float) (this.maxY);
        d.maxZ = (float) (this.maxZ);
        return d;
    }


    /**
     * Swap the minimum and maximum bounds of this axis-aligned bounding box where necessary so the
     * bounds are valid and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB correctBounds(@Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        double _buf0 = Math.min(this.minX, this.maxX);
        double _buf1 = Math.min(this.minY, this.maxY);
        double _buf2 = Math.min(this.minZ, this.maxZ);
        d.maxX = Math.max(this.minX, this.maxX);
        d.maxY = Math.max(this.minY, this.maxY);
        d.maxZ = Math.max(this.minZ, this.maxZ);
        d.minX = _buf0;
        d.minY = _buf1;
        d.minZ = _buf2;
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s3d3223e4_c0(DoubleAABBImpl _dst, double _t34, double _t18, double _t28, double _t35, double _r15, double _t20, double _t29, double _t36, double _r16, double _t22, double _t30, double _t37, double _r17) {
        _dst.minX = _t34 < 0.0 ? Double.POSITIVE_INFINITY : Math.fma(0.5, _t18, Math.fma(0.5, _t28, Math.fma(-0.5, _t35, _r15)));
        _dst.minY = _t34 < 0.0 ? Double.POSITIVE_INFINITY : Math.fma(0.5, _t20, Math.fma(0.5, _t29, Math.fma(-0.5, _t36, _r16)));
        _dst.minZ = _t34 < 0.0 ? Double.POSITIVE_INFINITY : Math.fma(0.5, _t22, Math.fma(0.5, _t30, Math.fma(-0.5, _t37, _r17)));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s3d3223e4_c1(DoubleAABBImpl _dst, double _t34, double _t18, double _t28, double _t35, double _r15, double _t20, double _t29, double _t36, double _r16, double _t22, double _t30, double _t37, double _r17) {
        _dst.maxX = _t34 < 0.0 ? Double.NEGATIVE_INFINITY : Math.fma(0.5, _t18, Math.fma(0.5, _t28, Math.fma(0.5, _t35, _r15)));
        _dst.maxY = _t34 < 0.0 ? Double.NEGATIVE_INFINITY : Math.fma(0.5, _t20, Math.fma(0.5, _t29, Math.fma(0.5, _t36, _r16)));
        _dst.maxZ = _t34 < 0.0 ? Double.NEGATIVE_INFINITY : Math.fma(0.5, _t22, Math.fma(0.5, _t30, Math.fma(0.5, _t37, _r17)));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s3d3223e4_tail(DoubleAABBImpl _dst, double _r3, double _r2, double _r6, double _t12, double _r7, double _r8, double _r9, double _t13, double _r10, double _r11, double _r12, double _r13, double _r14, double _t9, double _t10, double _t11, double _r15, double _r16, double _r17) {
        double _t14 = _r3 + _r2;
        double _t18 = _r6 * _t12;
        double _t20 = _r7 * _t12;
        double _t22 = _r8 * _t12;
        double _t28 = Math.fma(_r9, _t13, _r10 * _t14);
        double _t29 = Math.fma(_r11, _t13, _r12 * _t14);
        double _t30 = Math.fma(_r13, _t13, _r14 * _t14);
        double _t34 = Math.min(Math.min(0.5 * _t9, 0.5 * _t10), 0.5 * _t11);
        double _t35 = Math.fma(_t11, Math.abs(_r6), Math.fma(_t9, Math.abs(_r9), _t10 * Math.abs(_r10)));
        double _t36 = Math.fma(_t11, Math.abs(_r7), Math.fma(_t9, Math.abs(_r11), _t10 * Math.abs(_r12)));
        double _t37 = Math.fma(_t11, Math.abs(_r8), Math.fma(_t9, Math.abs(_r13), _t10 * Math.abs(_r14)));
        transform_s3d3223e4_c0(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
        transform_s3d3223e4_c1(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB transform(Double3x4R m, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        double _r0 = this.maxX;
        double _r1 = this.minX;
        double _r2 = this.maxY;
        double _r3 = this.minY;
        double _r4 = this.maxZ;
        double _r5 = this.minZ;
        double _r6 = m.m02();
        double _r7 = m.m12();
        double _r8 = m.m22();
        double _r9 = m.m00();
        double _r10 = m.m01();
        double _r11 = m.m10();
        double _r12 = m.m11();
        double _r13 = m.m20();
        double _r14 = m.m21();
        double _r15 = m.m03();
        double _r16 = m.m13();
        double _r17 = m.m23();
        transform_s3d3223e4_tail(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17);
        return d;
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB transform(Double4x4R m, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        double _r0 = this.maxX;
        double _r1 = this.minX;
        double _r2 = this.maxY;
        double _r3 = this.minY;
        double _r4 = this.maxZ;
        double _r5 = this.minZ;
        double _r6 = m.m02();
        double _r7 = m.m12();
        double _r8 = m.m22();
        double _r9 = m.m00();
        double _r10 = m.m01();
        double _r11 = m.m10();
        double _r12 = m.m11();
        double _r13 = m.m20();
        double _r14 = m.m21();
        double _r15 = m.m03();
        double _r16 = m.m13();
        double _r17 = m.m23();
        transform_s3d3223e4_tail(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17);
        return d;
    }


    /**
     * Translate this axis-aligned bounding box by {@code delta} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB translate(Double3R delta, @Mutated DoubleAABB dest) {
        return translate(delta.x(), delta.y(), delta.z(), dest);
    }


    /**
     * Translate this axis-aligned bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB translate(double deltaX, double deltaY, double deltaZ, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.minZ = deltaZ + this.minZ;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        d.maxZ = deltaZ + this.maxZ;
        return d;
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned bounding box to include in the union
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(DoubleAABBR other, @Mutated DoubleAABB dest) {
        return union(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ(), dest);
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and ({@code minX}, {@code minY},
     * {@code minZ}, {@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minY the {@code minY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minZ the {@code minZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxX the {@code maxX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxY the {@code maxY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxZ the {@code maxZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = Math.min(this.minX, minX);
        d.minY = Math.min(this.minY, minY);
        d.minZ = Math.min(this.minZ, minZ);
        d.maxX = Math.max(this.maxX, maxX);
        d.maxY = Math.max(this.maxY, maxY);
        d.maxZ = Math.max(this.maxZ, maxZ);
        return d;
    }


    /**
     * Grow this axis-aligned bounding box to include the point {@code p} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(Double3R p, @Mutated DoubleAABB dest) {
        return union(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Grow this axis-aligned bounding box to include the point ({@code pX}, {@code pY}, {@code pZ})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(double pX, double pY, double pZ, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = Math.min(this.minX, pX);
        d.minY = Math.min(this.minY, pY);
        d.minZ = Math.min(this.minZ, pZ);
        d.maxX = Math.max(this.maxX, pX);
        d.maxY = Math.max(this.maxY, pY);
        d.maxZ = Math.max(this.maxZ, pZ);
        return d;
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Double3R p, @Mutated Double3 dest) {
        return closestPointToPoint(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
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
        d.x = Math.max(this.minX, Math.min(pX, this.maxX));
        d.y = Math.max(this.minY, Math.min(pY, this.maxY));
        d.z = Math.max(this.minZ, Math.min(pZ, this.maxZ));
        return d;
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given box, i.e.
     *        the squared length of the shortest vector between any two points of the two boxes;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(DoubleAABBR other) {
        return distanceSquaredToAABB(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ());
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; {@code (minX, minY, minZ)} must not exceed
     * {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given box, i.e.
     *        the squared length of the shortest vector between any two points of the two boxes;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _t9 = Math.max(0.0, Math.max(this.minZ - maxZ, minZ - this.maxZ));
        double _t10 = Math.max(0.0, Math.max(this.minX - maxX, minX - this.maxX));
        double _t11 = Math.max(0.0, Math.max(this.minY - maxY, minY - this.maxY));
        return Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given point, i.e.
     *        the squared length of the difference between the point and its per-axis clamp into the
     *        box's bounds; zero for a point inside or on the box
     */
    public double distanceSquaredToPoint(Double3R p) {
        return distanceSquaredToPoint(p.x(), p.y(), p.z());
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this axis-aligned bounding box and the given point, i.e.
     *        the squared length of the difference between the point and its per-axis clamp into the
     *        box's bounds; zero for a point inside or on the box
     */
    public double distanceSquaredToPoint(double pX, double pY, double pZ) {
        double _t6 = pZ - Math.max(this.minZ, Math.min(pZ, this.maxZ));
        double _t7 = pX - Math.max(this.minX, Math.min(pX, this.maxX));
        double _t8 = pY - Math.max(this.minY, Math.min(pY, this.maxY));
        return Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public double distanceSquaredToSphere(DoubleSphereR sphere) {
        return distanceSquaredToSphere(sphere.x(), sphere.y(), sphere.z(), sphere.r());
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphereX the {@code x} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereY the {@code y} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereZ the {@code z} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereR the {@code r} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public double distanceSquaredToSphere(double sphereX, double sphereY, double sphereZ, double sphereR) {
        double _t6 = sphereZ - Math.max(this.minZ, Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - Math.max(this.minX, Math.min(sphereX, this.maxX));
        double _t8 = sphereY - Math.max(this.minY, Math.min(sphereY, this.maxY));
        double _t14 = Math.max(0.0, Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
        return _t14 * _t14;
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public double distanceSquaredToSphere(Double3R center, double radius) {
        return distanceSquaredToSphere(center.x(), center.y(), center.z(), radius);
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given box, i.e. the
     *        length of the shortest vector between any two points of the two boxes; zero when they
     *        overlap or touch
     */
    public double distanceToAABB(DoubleAABBR other) {
        return distanceToAABB(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ());
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; {@code (minX, minY, minZ)} must not exceed
     * {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given box, i.e. the
     *        length of the shortest vector between any two points of the two boxes; zero when they
     *        overlap or touch
     */
    public double distanceToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _t9 = Math.max(0.0, Math.max(this.minZ - maxZ, minZ - this.maxZ));
        double _t10 = Math.max(0.0, Math.max(this.minX - maxX, minX - this.maxX));
        double _t11 = Math.max(0.0, Math.max(this.minY - maxY, minY - this.maxY));
        return Math.sqrt(Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public double distanceToPlane(DoublePlaneR plane) {
        return distanceToPlane(plane.a(), plane.b(), plane.c(), plane.d());
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
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
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public double distanceToPlane(double planeA, double planeB, double planeC, double planeD) {
        return (1.0 / Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))) * Math.max(0.0, Math.fma(-0.5, Math.fma(this.maxZ - this.minZ, Math.abs(planeC), Math.fma(this.maxX - this.minX, Math.abs(planeA), (this.maxY - this.minY) * Math.abs(planeB))), Math.abs(Math.fma(0.5, Math.fma(planeC, this.minZ + this.maxZ, Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), planeD))));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public double distanceToPlane(Double4R plane) {
        return distanceToPlane(plane.x(), plane.y(), plane.z(), plane.w());
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given point, i.e. the
     *        length of the difference between the point and its per-axis clamp into the box's
     *        bounds; zero for a point inside or on the box
     */
    public double distanceToPoint(Double3R p) {
        return distanceToPoint(p.x(), p.y(), p.z());
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this axis-aligned bounding box and the given point, i.e. the
     *        length of the difference between the point and its per-axis clamp into the box's
     *        bounds; zero for a point inside or on the box
     */
    public double distanceToPoint(double pX, double pY, double pZ) {
        double _t6 = pZ - Math.max(this.minZ, Math.min(pZ, this.maxZ));
        double _t7 = pX - Math.max(this.minX, Math.min(pX, this.maxX));
        double _t8 = pY - Math.max(this.minY, Math.min(pY, this.maxY));
        return Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8)));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public double distanceToSphere(DoubleSphereR sphere) {
        return distanceToSphere(sphere.x(), sphere.y(), sphere.z(), sphere.r());
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphereX the {@code x} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereY the {@code y} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereZ the {@code z} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereR the {@code r} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public double distanceToSphere(double sphereX, double sphereY, double sphereZ, double sphereR) {
        double _t6 = sphereZ - Math.max(this.minZ, Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - Math.max(this.minX, Math.min(sphereX, this.maxX));
        double _t8 = sphereY - Math.max(this.minY, Math.min(sphereY, this.maxY));
        return Math.max(0.0, Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public double distanceToSphere(Double3R center, double radius) {
        return distanceToSphere(center.x(), center.y(), center.z(), radius);
    }


    /**
     * Get the center of this axis-aligned bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getCenter(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = 0.5 * this.minX + 0.5 * this.maxX;
        d.y = 0.5 * this.minY + 0.5 * this.maxY;
        d.z = 0.5 * this.minZ + 0.5 * this.maxZ;
        return d;
    }


    /**
     * Get the maximum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getMax(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.maxX;
        d.y = this.maxY;
        d.z = this.maxZ;
        return d;
    }


    /**
     * Get the minimum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getMin(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.minX;
        d.y = this.minY;
        d.z = this.minZ;
        return d;
    }


    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this axis-aligned
     * bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getSize(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.maxX - this.minX;
        d.y = this.maxY - this.minY;
        d.z = this.maxZ - this.minZ;
        return d;
    }


    /**
     * Determine whether this axis-aligned bounding box is valid, i.e. no minimum bound exceeds its
     * maximum.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this axis-aligned bounding box is valid, i.e. no minimum bound
     *        exceeds its maximum, {@code false} otherwise
     */
    public boolean isValid() {
        if (!(this.minX <= this.maxX)) return false;
        if (!(this.minY <= this.maxY)) return false;
        return this.minZ <= this.maxZ;
    }

    public double minX() { return this.minX; }
    public double minY() { return this.minY; }
    public double minZ() { return this.minZ; }
    public double maxX() { return this.maxX; }
    public double maxY() { return this.maxY; }
    public double maxZ() { return this.maxZ; }

    @Override public String toString() {
        return "DoubleAABB(" + minX() + ", " + minY() + ", " + minZ() + ", " + maxX() + ", " + maxY() + ", " + maxZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleAABBImpl)) return false;
        DoubleAABBImpl o = (DoubleAABBImpl) obj;
        return Double.doubleToLongBits(minX) == Double.doubleToLongBits(o.minX)
            && Double.doubleToLongBits(minY) == Double.doubleToLongBits(o.minY)
            && Double.doubleToLongBits(minZ) == Double.doubleToLongBits(o.minZ)
            && Double.doubleToLongBits(maxX) == Double.doubleToLongBits(o.maxX)
            && Double.doubleToLongBits(maxY) == Double.doubleToLongBits(o.maxY)
            && Double.doubleToLongBits(maxZ) == Double.doubleToLongBits(o.maxZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(minX) ^ (Double.doubleToLongBits(minX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(minY) ^ (Double.doubleToLongBits(minY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(minZ) ^ (Double.doubleToLongBits(minZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(maxX) ^ (Double.doubleToLongBits(maxX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(maxY) ^ (Double.doubleToLongBits(maxY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(maxZ) ^ (Double.doubleToLongBits(maxZ) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(minX)
            && Double.isFinite(minY)
            && Double.isFinite(minZ)
            && Double.isFinite(maxX)
            && Double.isFinite(maxY)
            && Double.isFinite(maxZ);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(minX)
            || Double.isNaN(minY)
            || Double.isNaN(minZ)
            || Double.isNaN(maxX)
            || Double.isNaN(maxY)
            || Double.isNaN(maxZ);
    }

    @Override public boolean equalsEpsilon(DoubleAABBR other, double epsilon) {
        return Math.abs(minX - other.minX()) <= epsilon
            && Math.abs(minY - other.minY()) <= epsilon
            && Math.abs(minZ - other.minZ()) <= epsilon
            && Math.abs(maxX - other.maxX()) <= epsilon
            && Math.abs(maxY - other.maxY()) <= epsilon
            && Math.abs(maxZ - other.maxZ()) <= epsilon;
    }

    public boolean containsPoint(double x, double y, double z) {
        return Intersectiond.testPointAabb(x, y, z, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    public boolean containsPoint(Double3R p) {
        return Intersectiond.testPointAabb(p, this);
    }

    public boolean containsAABB(DoubleAABBR o) {
        return Intersectiond.testAabbAabbContains(this, o);
    }

    public boolean intersectsAABB(DoubleAABBR o) {
        return Intersectiond.testAabbAabb(this, o);
    }

    public boolean intersectsSweptAABB(DoubleAABBR other, double vX, double vY, double vZ) {
        return Intersectiond.testMovingAabbAabb(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ(), vX, vY, vZ, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    public boolean intersectsSweptAABB(DoubleAABBR other, Double3R velocity) {
        return intersectsSweptAABB(other, velocity.x(), velocity.y(), velocity.z());
    }

    public boolean intersectsSphere(DoubleSphereR sph) {
        return Intersectiond.testAabbSphere(this, sph);
    }

    public boolean intersectsPlane(DoublePlaneR plane) {
        return Intersectiond.testAabbPlane(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), plane.a(), plane.b(), plane.c(), plane.d());
    }

    public boolean intersectsRay(DoubleRayR r) {
        return Intersectiond.testRayAabb(r, this);
    }

    public boolean intersectRay(DoubleRayR r, @Mutated Double2 dest) {
        return Intersectiond.intersectRayAabb(r, this, dest);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.minZ;
        dest[offset + 3] = this.maxX;
        dest[offset + 4] = this.maxY;
        dest[offset + 5] = this.maxZ;
        return dest;
    }
    public @Mutated DoubleAABB load(double[] src, int offset) {
        this.minX = src[offset + 0];
        this.minY = src[offset + 1];
        this.minZ = src[offset + 2];
        this.maxX = src[offset + 3];
        this.maxY = src[offset + 4];
        this.maxZ = src[offset + 5];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoubleAABB loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleAABB loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleAABB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleAABB loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.minX;
        dest[offset + 1] = (float) this.minY;
        dest[offset + 2] = (float) this.minZ;
        dest[offset + 3] = (float) this.maxX;
        dest[offset + 4] = (float) this.maxY;
        dest[offset + 5] = (float) this.maxZ;
        return dest;
    }
    public @Mutated DoubleAABB load(float[] src, int offset) {
        this.minX = src[offset + 0];
        this.minY = src[offset + 1];
        this.minZ = src[offset + 2];
        this.maxX = src[offset + 3];
        this.maxY = src[offset + 4];
        this.maxZ = src[offset + 5];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoubleAABB loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public DoubleAABB loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleAABB storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleAABB loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }

}
