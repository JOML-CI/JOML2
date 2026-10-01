// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Immutable axis-aligned bounding box of double-precision {@code double} components.
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
 * @param minX the {@code minX} component
 * @param minY the {@code minY} component
 * @param minZ the {@code minZ} component
 * @param maxX the {@code maxX} component
 * @param maxY the {@code maxY} component
 * @param maxZ the {@code maxZ} component
 */
public record DoubleAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 48;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param minX the {@code minX} component
     * @param minY the {@code minY} component
     * @param minZ the {@code minZ} component
     * @param maxX the {@code maxX} component
     * @param maxY the {@code maxY} component
     * @param maxZ the {@code maxZ} component
     */
    public DoubleAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    /**
     * Create a new instance initialized to empty inverted bounds (so any union starts from the
     * first added geometry).
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public DoubleAABB() {
        this(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    /**
     * Create an axis-aligned bounding box from its minimum and maximum corners.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     */
    public DoubleAABB(Double3 min, Double3 max) {
        this(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /** {@return the {@code minX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double minX() { return minX; }
    /** {@return the {@code minY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double minY() { return minY; }
    /** {@return the {@code minZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double minZ() { return minZ; }
    /** {@return the {@code maxX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double maxX() { return maxX; }
    /** {@return the {@code maxY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double maxY() { return maxY; }
    /** {@return the {@code maxZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double maxZ() { return maxZ; }

    /**
     * Create a new axis-aligned bounding box from its minimum and maximum corners.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB set(Double3 min, Double3 max) {
        return new DoubleAABB(min, max);
    }


    /**
     * Create a new axis-aligned bounding box from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the axis-aligned bounding box to copy
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB set(DoubleAABB v) {
        double minX = v.minX();
        double minY = v.minY();
        double minZ = v.minZ();
        double maxX = v.maxX();
        double maxY = v.maxY();
        double maxZ = v.maxZ();
        return new DoubleAABB(minX, minY, minZ, maxX, maxY, maxZ);
    }


    /**
     * Create a new axis-aligned bounding box from the given values.
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
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB set(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new DoubleAABB(minX, minY, minZ, maxX, maxY, maxZ);
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to {@code max}, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB setMax(Double3 max) {
        double maxX = max.x();
        double maxY = max.y();
        double maxZ = max.z();
        return new DoubleAABB(this.minX, this.minY, this.minZ, maxX, maxY, maxZ);
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to ({@code maxX}, {@code maxY},
     * {@code maxZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB setMax(double maxX, double maxY, double maxZ) {
        return new DoubleAABB(this.minX, this.minY, this.minZ, maxX, maxY, maxZ);
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to {@code min}, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB setMin(Double3 min) {
        double minX = min.x();
        double minY = min.y();
        double minZ = min.z();
        return new DoubleAABB(minX, minY, minZ, this.maxX, this.maxY, this.maxZ);
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to ({@code minX}, {@code minY},
     * {@code minZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB setMin(double minX, double minY, double minZ) {
        return new DoubleAABB(minX, minY, minZ, this.maxX, this.maxY, this.maxZ);
    }


    /**
     * Convert this axis-aligned bounding box to {@code float} precision, returning the result as a
     * new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatAABB} holding the result
     */
    public FloatAABB toFloat() {
        return new FloatAABB((float) (this.minX), (float) (this.minY), (float) (this.minZ), (float) (this.maxX), (float) (this.maxY), (float) (this.maxZ));
    }


    /**
     * Swap the minimum and maximum bounds of this axis-aligned bounding box where necessary so the
     * bounds are valid, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB correctBounds() {
        return new DoubleAABB(java.lang.Math.min(this.minX, this.maxX), java.lang.Math.min(this.minY, this.maxY), java.lang.Math.min(this.minZ, this.maxZ), java.lang.Math.max(this.minX, this.maxX), java.lang.Math.max(this.minY, this.maxY), java.lang.Math.max(this.minZ, this.maxZ));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleAABB transform_s91e96b_tail(double _t11, Double3x4 m, double _t9, double _t10, double _t34, double _t18, double _t28, double _t35, double _t20, double _t29, double _t36, double _t22, double _t30) {
        double _t37 = Math.fma(_t11, java.lang.Math.abs(m.m22()), Math.fma(_t9, java.lang.Math.abs(m.m20()), _t10 * java.lang.Math.abs(m.m21())));
        if (_t34 < 0.0) {
            return new DoubleAABB(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        } else {
            return new DoubleAABB(Math.fma(0.5, _t18, Math.fma(0.5, _t28, Math.fma(-0.5, _t35, m.m03()))), Math.fma(0.5, _t20, Math.fma(0.5, _t29, Math.fma(-0.5, _t36, m.m13()))), Math.fma(0.5, _t22, Math.fma(0.5, _t30, Math.fma(-0.5, _t37, m.m23()))), Math.fma(0.5, _t18, Math.fma(0.5, _t28, Math.fma(0.5, _t35, m.m03()))), Math.fma(0.5, _t20, Math.fma(0.5, _t29, Math.fma(0.5, _t36, m.m13()))), Math.fma(0.5, _t22, Math.fma(0.5, _t30, Math.fma(0.5, _t37, m.m23()))));
        }
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box, returning the result as a value.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB transform(Double3x4 m) {
        double _t9 = this.maxX - this.minX;
        double _t10 = this.maxY - this.minY;
        double _t11 = this.maxZ - this.minZ;
        double _t12 = this.minZ + this.maxZ;
        double _t13 = this.minX + this.maxX;
        double _t14 = this.minY + this.maxY;
        return transform_s91e96b_tail(_t11, m, _t9, _t10, java.lang.Math.min(java.lang.Math.min(0.5 * _t9, 0.5 * _t10), 0.5 * _t11), m.m02() * _t12, Math.fma(m.m00(), _t13, m.m01() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m02()), Math.fma(_t9, java.lang.Math.abs(m.m00()), _t10 * java.lang.Math.abs(m.m01()))), m.m12() * _t12, Math.fma(m.m10(), _t13, m.m11() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m12()), Math.fma(_t9, java.lang.Math.abs(m.m10()), _t10 * java.lang.Math.abs(m.m11()))), m.m22() * _t12, Math.fma(m.m20(), _t13, m.m21() * _t14));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleAABB transform_sa000ec_tail(double _t11, Double4x4 m, double _t9, double _t10, double _t34, double _t18, double _t28, double _t35, double _t20, double _t29, double _t36, double _t22, double _t30) {
        double _t37 = Math.fma(_t11, java.lang.Math.abs(m.m22()), Math.fma(_t9, java.lang.Math.abs(m.m20()), _t10 * java.lang.Math.abs(m.m21())));
        if (_t34 < 0.0) {
            return new DoubleAABB(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        } else {
            return new DoubleAABB(Math.fma(0.5, _t18, Math.fma(0.5, _t28, Math.fma(-0.5, _t35, m.m03()))), Math.fma(0.5, _t20, Math.fma(0.5, _t29, Math.fma(-0.5, _t36, m.m13()))), Math.fma(0.5, _t22, Math.fma(0.5, _t30, Math.fma(-0.5, _t37, m.m23()))), Math.fma(0.5, _t18, Math.fma(0.5, _t28, Math.fma(0.5, _t35, m.m03()))), Math.fma(0.5, _t20, Math.fma(0.5, _t29, Math.fma(0.5, _t36, m.m13()))), Math.fma(0.5, _t22, Math.fma(0.5, _t30, Math.fma(0.5, _t37, m.m23()))));
        }
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box, returning the result as a value.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB transform(Double4x4 m) {
        double _t9 = this.maxX - this.minX;
        double _t10 = this.maxY - this.minY;
        double _t11 = this.maxZ - this.minZ;
        double _t12 = this.minZ + this.maxZ;
        double _t13 = this.minX + this.maxX;
        double _t14 = this.minY + this.maxY;
        return transform_sa000ec_tail(_t11, m, _t9, _t10, java.lang.Math.min(java.lang.Math.min(0.5 * _t9, 0.5 * _t10), 0.5 * _t11), m.m02() * _t12, Math.fma(m.m00(), _t13, m.m01() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m02()), Math.fma(_t9, java.lang.Math.abs(m.m00()), _t10 * java.lang.Math.abs(m.m01()))), m.m12() * _t12, Math.fma(m.m10(), _t13, m.m11() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m12()), Math.fma(_t9, java.lang.Math.abs(m.m10()), _t10 * java.lang.Math.abs(m.m11()))), m.m22() * _t12, Math.fma(m.m20(), _t13, m.m21() * _t14));
    }


    /**
     * Translate this axis-aligned bounding box by {@code delta}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB translate(Double3 delta) {
        double deltaX = delta.x();
        double deltaY = delta.y();
        double deltaZ = delta.z();
        return new DoubleAABB(deltaX + this.minX, deltaY + this.minY, deltaZ + this.minZ, deltaX + this.maxX, deltaY + this.maxY, deltaZ + this.maxZ);
    }


    /**
     * Translate this axis-aligned bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB translate(double deltaX, double deltaY, double deltaZ) {
        return new DoubleAABB(deltaX + this.minX, deltaY + this.minY, deltaZ + this.minZ, deltaX + this.maxX, deltaY + this.maxY, deltaZ + this.maxZ);
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned bounding box to include in the union
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB union(DoubleAABB other) {
        double minX = other.minX();
        double minY = other.minY();
        double minZ = other.minZ();
        double maxX = other.maxX();
        double maxY = other.maxY();
        double maxZ = other.maxZ();
        return new DoubleAABB(java.lang.Math.min(this.minX, minX), java.lang.Math.min(this.minY, minY), java.lang.Math.min(this.minZ, minZ), java.lang.Math.max(this.maxX, maxX), java.lang.Math.max(this.maxY, maxY), java.lang.Math.max(this.maxZ, maxZ));
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and ({@code minX}, {@code minY},
     * {@code minZ}, {@code maxX}, {@code maxY}, {@code maxZ}), returning the result as a value.
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
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new DoubleAABB(java.lang.Math.min(this.minX, minX), java.lang.Math.min(this.minY, minY), java.lang.Math.min(this.minZ, minZ), java.lang.Math.max(this.maxX, maxX), java.lang.Math.max(this.maxY, maxY), java.lang.Math.max(this.maxZ, maxZ));
    }


    /**
     * Grow this axis-aligned bounding box to include the point {@code p}, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB union(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        return new DoubleAABB(java.lang.Math.min(this.minX, pX), java.lang.Math.min(this.minY, pY), java.lang.Math.min(this.minZ, pZ), java.lang.Math.max(this.maxX, pX), java.lang.Math.max(this.maxY, pY), java.lang.Math.max(this.maxZ, pZ));
    }


    /**
     * Grow this axis-aligned bounding box to include the point ({@code pX}, {@code pY},
     * {@code pZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting axis-aligned bounding box
     */
    public DoubleAABB union(double pX, double pY, double pZ) {
        return new DoubleAABB(java.lang.Math.min(this.minX, pX), java.lang.Math.min(this.minY, pY), java.lang.Math.min(this.minZ, pZ), java.lang.Math.max(this.maxX, pX), java.lang.Math.max(this.maxY, pY), java.lang.Math.max(this.maxZ, pZ));
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Double3 closestPointToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        return new Double3(java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX)), java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY)), java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ)));
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
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
     * @return the resulting vector
     */
    public Double3 closestPointToPoint(double pX, double pY, double pZ) {
        return new Double3(java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX)), java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY)), java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ)));
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
    public double distanceSquaredToAABB(DoubleAABB other) {
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(this.minZ - other.maxZ(), other.minZ() - this.maxZ));
        double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(this.minX - other.maxX(), other.minX() - this.maxX));
        double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(this.minY - other.maxY(), other.minY() - this.maxY));
        return Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
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
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
        double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
        double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
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
    public double distanceSquaredToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        double _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        double _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        double _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
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
        double _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        double _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        double _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
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
    public double distanceSquaredToSphere(DoubleSphere sphere) {
        double sphereX = sphere.x();
        double sphereY = sphere.y();
        double sphereZ = sphere.z();
        double _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        double _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphere.r());
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
        double _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        double _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
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
    public double distanceSquaredToSphere(Double3 center, double radius) {
        double sphereX = center.x();
        double sphereY = center.y();
        double sphereZ = center.z();
        double _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        double _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - radius);
        return _t14 * _t14;
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
    public double distanceToAABB(DoubleAABB other) {
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(this.minZ - other.maxZ(), other.minZ() - this.maxZ));
        double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(this.minX - other.maxX(), other.minX() - this.maxX));
        double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(this.minY - other.maxY(), other.minY() - this.maxY));
        return java.lang.Math.sqrt(Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)));
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
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
        double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
        double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
        return java.lang.Math.sqrt(Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)));
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
    public double distanceToPlane(DoublePlane plane) {
        double planeA = plane.a();
        double planeB = plane.b();
        double planeC = plane.c();
        return (1.0 / java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0, Math.fma(-0.5, Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(Math.fma(0.5, Math.fma(planeC, this.minZ + this.maxZ, Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), plane.d()))));
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
        return (1.0 / java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0, Math.fma(-0.5, Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(Math.fma(0.5, Math.fma(planeC, this.minZ + this.maxZ, Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), planeD))));
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
    public double distanceToPlane(Double4 plane) {
        double planeA = plane.x();
        double planeB = plane.y();
        double planeC = plane.z();
        return (1.0 / java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0, Math.fma(-0.5, Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(Math.fma(0.5, Math.fma(planeC, this.minZ + this.maxZ, Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), plane.w()))));
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
    public double distanceToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        double _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        double _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        double _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8)));
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
        double _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        double _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        double _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8)));
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
    public double distanceToSphere(DoubleSphere sphere) {
        double sphereX = sphere.x();
        double sphereY = sphere.y();
        double sphereZ = sphere.z();
        double _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        double _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphere.r());
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
        double _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        double _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
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
    public double distanceToSphere(Double3 center, double radius) {
        double sphereX = center.x();
        double sphereY = center.y();
        double sphereZ = center.z();
        double _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        double _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        double _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        return java.lang.Math.max(0.0, java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - radius);
    }


    /**
     * Get the center of this axis-aligned bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getCenter() {
        return new Double3(0.5 * this.minX + 0.5 * this.maxX, 0.5 * this.minY + 0.5 * this.maxY, 0.5 * this.minZ + 0.5 * this.maxZ);
    }


    /**
     * Get the maximum corner of this axis-aligned bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getMax() {
        return new Double3(this.maxX, this.maxY, this.maxZ);
    }


    /**
     * Get the minimum corner of this axis-aligned bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getMin() {
        return new Double3(this.minX, this.minY, this.minZ);
    }


    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this axis-aligned
     * bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getSize() {
        return new Double3(this.maxX - this.minX, this.maxY - this.minY, this.maxZ - this.minZ);
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

    /**
     * Determine whether this axis-aligned bounding box contains the given point (boundary
     * inclusive). Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @param pZ the z coordinate of the point
     * @return {@code true} if the given point lies inside or on this axis-aligned bounding box,
     *        {@code false} otherwise
     */
    public boolean containsPoint(double pX, double pY, double pZ) {
        return Intersectiond.testPointAabb(pX, pY, pZ, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box contains the given point (boundary
     * inclusive). Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if the given point lies inside or on this axis-aligned bounding box,
     *        {@code false} otherwise
     */
    public boolean containsPoint(Double3 p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    /**
     * Determine whether this axis-aligned bounding box contains the given axis-aligned box
     * (boundary inclusive).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the axis-aligned box to test for containment
     * @return {@code true} if the given box lies entirely inside this axis-aligned bounding box,
     *        boundary included, {@code false} otherwise
     */
    public boolean containsAABB(DoubleAABB o) {
        return minX() <= o.minX() && minY() <= o.minY() && minZ() <= o.minZ() && maxX() >= o.maxX() && maxY() >= o.maxY() && maxZ() >= o.maxZ();
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box.
     * Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the axis-aligned box to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given axis-aligned box
     *        intersect, {@code false} otherwise
     */
    public boolean intersectsAABB(DoubleAABB o) {
        return Intersectiond.testAabbAabb(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), o.minX(), o.minY(), o.minZ(), o.maxX(), o.maxY(), o.maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box swept
     * by the given velocity. Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned box that moves
     * @param vX the x component of the velocity
     * @param vY the y component of the velocity
     * @param vZ the z component of the velocity
     * @return {@code true} if the moving box meets this axis-aligned bounding box at any point of
     *        the step, {@code false} otherwise
     */
    public boolean intersectsSweptAABB(DoubleAABB other, double vX, double vY, double vZ) {
        return Intersectiond.testMovingAabbAabb(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ(), vX, vY, vZ, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box swept
     * by the given velocity. Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned box that moves
     * @param velocity the velocity the given box moves by over one step
     * @return {@code true} if the moving box meets this axis-aligned bounding box at any point of
     *        the step, {@code false} otherwise
     */
    public boolean intersectsSweptAABB(DoubleAABB other, Double3 velocity) {
        return intersectsSweptAABB(other, velocity.x(), velocity.y(), velocity.z());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given sphere. Delegates to
     * the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sph the sphere to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given sphere intersect,
     *        {@code false} otherwise
     */
    public boolean intersectsSphere(DoubleSphere sph) {
        return Intersectiond.testAabbSphere(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), sph.x(), sph.y(), sph.z(), sph.r() * sph.r());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given plane. Delegates to the
     * shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param plane the plane to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given plane intersect,
     *        {@code false} otherwise
     */
    public boolean intersectsPlane(DoublePlane plane) {
        return Intersectiond.testAabbPlane(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), plane.a(), plane.b(), plane.c(), plane.d());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given ray. Delegates to the
     * shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param ray the ray to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given ray intersect,
     *        {@code false} otherwise
     */
    public boolean intersectsRay(DoubleRay ray) {
        return Intersectiond.testRayAabb(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given ray and, if so, the
     * values of <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     * points of intersection. Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param ray the ray to intersect
     * @return a {@link DoubleHit2} whose {@link DoubleHit2#hit() hit()} is {@code true} iff the ray
     *        intersects this axis-aligned bounding box, and whose components then hold the values
     *        of <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     *        points of intersection; {@link DoubleHit2#MISS} otherwise
     */
    public DoubleHit2 intersectRay(DoubleRay ray) {
        return Intersectiond.intersectRayAabb(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * {@return a copy with the {@code minX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code minX} component
     */
    public DoubleAABB withMinX(double v) {
        return new DoubleAABB(v, minY, minZ, maxX, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code minY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code minY} component
     */
    public DoubleAABB withMinY(double v) {
        return new DoubleAABB(minX, v, minZ, maxX, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code minZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code minZ} component
     */
    public DoubleAABB withMinZ(double v) {
        return new DoubleAABB(minX, minY, v, maxX, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code maxX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code maxX} component
     */
    public DoubleAABB withMaxX(double v) {
        return new DoubleAABB(minX, minY, minZ, v, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code maxY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code maxY} component
     */
    public DoubleAABB withMaxY(double v) {
        return new DoubleAABB(minX, minY, minZ, maxX, v, maxZ);
    }

    /**
     * {@return a copy with the {@code maxZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code maxZ} component
     */
    public DoubleAABB withMaxZ(double v) {
        return new DoubleAABB(minX, minY, minZ, maxX, maxY, v);
    }

    @Override public String toString() {
        return "DoubleAABB(" + minX() + ", " + minY() + ", " + minZ() + ", " + maxX() + ", " + maxY() + ", " + maxZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleAABB)) return false;
        DoubleAABB o = (DoubleAABB) obj;
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

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(minX)
            && Double.isFinite(minY)
            && Double.isFinite(minZ)
            && Double.isFinite(maxX)
            && Double.isFinite(maxY)
            && Double.isFinite(maxZ);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(minX)
            || Double.isNaN(minY)
            || Double.isNaN(minZ)
            || Double.isNaN(maxX)
            || Double.isNaN(maxY)
            || Double.isNaN(maxZ);
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
    public boolean equalsEpsilon(DoubleAABB other, double epsilon) {
        return java.lang.Math.abs(minX - other.minX()) <= epsilon
            && java.lang.Math.abs(minY - other.minY()) <= epsilon
            && java.lang.Math.abs(minZ - other.minZ()) <= epsilon
            && java.lang.Math.abs(maxX - other.maxX()) <= epsilon
            && java.lang.Math.abs(maxY - other.maxY()) <= epsilon
            && java.lang.Math.abs(maxZ - other.maxZ()) <= epsilon;
    }

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
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.minZ;
        dest[offset + 3] = this.maxX;
        dest[offset + 4] = this.maxY;
        dest[offset + 5] = this.maxZ;
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB load(double[] src) { return load(src, 0); }

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
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleAABB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleAABB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
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
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleAABB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleAABB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 48);
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
    public DoubleAABB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleAABB} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleAABB loadUnsafe(long address) {
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
        dest[offset] = (float) this.minX;
        dest[offset + 1] = (float) this.minY;
        dest[offset + 2] = (float) this.minZ;
        dest[offset + 3] = (float) this.maxX;
        dest[offset + 4] = (float) this.maxY;
        dest[offset + 5] = (float) this.maxZ;
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB load(float[] src) { return load(src, 0); }

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
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleAABB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleAABB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
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
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     */
    public static DoubleAABB loadFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleAABB loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleAABB r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 24);
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
    public DoubleAABB storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleAABB} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleAABB loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }
}
