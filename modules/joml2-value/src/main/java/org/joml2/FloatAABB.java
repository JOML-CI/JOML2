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
 * Immutable axis-aligned bounding box of single-precision {@code float} components, declared as a
 * value record.
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
 * @param minX the {@code minX} component
 * @param minY the {@code minY} component
 * @param minZ the {@code minZ} component
 * @param maxX the {@code maxX} component
 * @param maxY the {@code maxY} component
 * @param maxZ the {@code maxZ} component
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record FloatAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 24;

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
    public FloatAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
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
    public FloatAABB() {
        this(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
    }

    /**
     * Create an axis-aligned bounding box from its minimum and maximum corners.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     */
    public FloatAABB(Float3 min, Float3 max) {
        this(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /** {@return the {@code minX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float minX() { return minX; }
    /** {@return the {@code minY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float minY() { return minY; }
    /** {@return the {@code minZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float minZ() { return minZ; }
    /** {@return the {@code maxX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float maxX() { return maxX; }
    /** {@return the {@code maxY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float maxY() { return maxY; }
    /** {@return the {@code maxZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float maxZ() { return maxZ; }

    /**
     * Create a new axis-aligned bounding box from its minimum and maximum corners.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the resulting axis-aligned bounding box
     */
    public FloatAABB set(Float3 min, Float3 max) {
        return new FloatAABB(min, max);
    }


    /**
     * Create a new axis-aligned bounding box from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the axis-aligned bounding box to copy
     * @return the resulting axis-aligned bounding box
     */
    public FloatAABB set(FloatAABB v) {
        float minX = v.minX();
        float minY = v.minY();
        float minZ = v.minZ();
        float maxX = v.maxX();
        float maxY = v.maxY();
        float maxZ = v.maxZ();
        return new FloatAABB(minX, minY, minZ, maxX, maxY, maxZ);
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
    public FloatAABB set(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new FloatAABB(minX, minY, minZ, maxX, maxY, maxZ);
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
    public FloatAABB setMax(Float3 max) {
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        return new FloatAABB(this.minX, this.minY, this.minZ, maxX, maxY, maxZ);
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
    public FloatAABB setMax(float maxX, float maxY, float maxZ) {
        return new FloatAABB(this.minX, this.minY, this.minZ, maxX, maxY, maxZ);
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
    public FloatAABB setMin(Float3 min) {
        float minX = min.x();
        float minY = min.y();
        float minZ = min.z();
        return new FloatAABB(minX, minY, minZ, this.maxX, this.maxY, this.maxZ);
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
    public FloatAABB setMin(float minX, float minY, float minZ) {
        return new FloatAABB(minX, minY, minZ, this.maxX, this.maxY, this.maxZ);
    }


    /**
     * Convert this axis-aligned bounding box to {@code double} precision, returning the result as a
     * new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleAABB} holding the result
     */
    public DoubleAABB toDouble() {
        return new DoubleAABB(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }


    /**
     * Swap the minimum and maximum bounds of this axis-aligned bounding box where necessary so the
     * bounds are valid, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting axis-aligned bounding box
     */
    public FloatAABB correctBounds() {
        return new FloatAABB(java.lang.Math.min(this.minX, this.maxX), java.lang.Math.min(this.minY, this.maxY), java.lang.Math.min(this.minZ, this.maxZ), java.lang.Math.max(this.minX, this.maxX), java.lang.Math.max(this.minY, this.maxY), java.lang.Math.max(this.minZ, this.maxZ));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatAABB transform_s37cad600_tail(float _t11, Float3x4 m, float _t9, float _t10, float _t34, float _t18, float _t28, float _t35, float _t20, float _t29, float _t36, float _t22, float _t30) {
        float _t37 = Math.fma(_t11, java.lang.Math.abs(m.m22()), Math.fma(_t9, java.lang.Math.abs(m.m20()), _t10 * java.lang.Math.abs(m.m21())));
        if (_t34 < 0.0f) {
            return new FloatAABB(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        } else {
            return new FloatAABB(Math.fma(0.5f, _t18, Math.fma(0.5f, _t28, Math.fma(-0.5f, _t35, m.m03()))), Math.fma(0.5f, _t20, Math.fma(0.5f, _t29, Math.fma(-0.5f, _t36, m.m13()))), Math.fma(0.5f, _t22, Math.fma(0.5f, _t30, Math.fma(-0.5f, _t37, m.m23()))), Math.fma(0.5f, _t18, Math.fma(0.5f, _t28, Math.fma(0.5f, _t35, m.m03()))), Math.fma(0.5f, _t20, Math.fma(0.5f, _t29, Math.fma(0.5f, _t36, m.m13()))), Math.fma(0.5f, _t22, Math.fma(0.5f, _t30, Math.fma(0.5f, _t37, m.m23()))));
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
    public FloatAABB transform(Float3x4 m) {
        float _t9 = this.maxX - this.minX;
        float _t10 = this.maxY - this.minY;
        float _t11 = this.maxZ - this.minZ;
        float _t12 = this.minZ + this.maxZ;
        float _t13 = this.minX + this.maxX;
        float _t14 = this.minY + this.maxY;
        return transform_s37cad600_tail(_t11, m, _t9, _t10, java.lang.Math.min(java.lang.Math.min(0.5f * _t9, 0.5f * _t10), 0.5f * _t11), m.m02() * _t12, Math.fma(m.m00(), _t13, m.m01() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m02()), Math.fma(_t9, java.lang.Math.abs(m.m00()), _t10 * java.lang.Math.abs(m.m01()))), m.m12() * _t12, Math.fma(m.m10(), _t13, m.m11() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m12()), Math.fma(_t9, java.lang.Math.abs(m.m10()), _t10 * java.lang.Math.abs(m.m11()))), m.m22() * _t12, Math.fma(m.m20(), _t13, m.m21() * _t14));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatAABB transform_s37d8ed81_tail(float _t11, Float4x4 m, float _t9, float _t10, float _t34, float _t18, float _t28, float _t35, float _t20, float _t29, float _t36, float _t22, float _t30) {
        float _t37 = Math.fma(_t11, java.lang.Math.abs(m.m22()), Math.fma(_t9, java.lang.Math.abs(m.m20()), _t10 * java.lang.Math.abs(m.m21())));
        if (_t34 < 0.0f) {
            return new FloatAABB(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        } else {
            return new FloatAABB(Math.fma(0.5f, _t18, Math.fma(0.5f, _t28, Math.fma(-0.5f, _t35, m.m03()))), Math.fma(0.5f, _t20, Math.fma(0.5f, _t29, Math.fma(-0.5f, _t36, m.m13()))), Math.fma(0.5f, _t22, Math.fma(0.5f, _t30, Math.fma(-0.5f, _t37, m.m23()))), Math.fma(0.5f, _t18, Math.fma(0.5f, _t28, Math.fma(0.5f, _t35, m.m03()))), Math.fma(0.5f, _t20, Math.fma(0.5f, _t29, Math.fma(0.5f, _t36, m.m13()))), Math.fma(0.5f, _t22, Math.fma(0.5f, _t30, Math.fma(0.5f, _t37, m.m23()))));
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
    public FloatAABB transform(Float4x4 m) {
        float _t9 = this.maxX - this.minX;
        float _t10 = this.maxY - this.minY;
        float _t11 = this.maxZ - this.minZ;
        float _t12 = this.minZ + this.maxZ;
        float _t13 = this.minX + this.maxX;
        float _t14 = this.minY + this.maxY;
        return transform_s37d8ed81_tail(_t11, m, _t9, _t10, java.lang.Math.min(java.lang.Math.min(0.5f * _t9, 0.5f * _t10), 0.5f * _t11), m.m02() * _t12, Math.fma(m.m00(), _t13, m.m01() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m02()), Math.fma(_t9, java.lang.Math.abs(m.m00()), _t10 * java.lang.Math.abs(m.m01()))), m.m12() * _t12, Math.fma(m.m10(), _t13, m.m11() * _t14), Math.fma(_t11, java.lang.Math.abs(m.m12()), Math.fma(_t9, java.lang.Math.abs(m.m10()), _t10 * java.lang.Math.abs(m.m11()))), m.m22() * _t12, Math.fma(m.m20(), _t13, m.m21() * _t14));
    }


    /**
     * Translate this axis-aligned bounding box by {@code delta}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @return the resulting axis-aligned bounding box
     */
    public FloatAABB translate(Float3 delta) {
        float deltaX = delta.x();
        float deltaY = delta.y();
        float deltaZ = delta.z();
        return new FloatAABB(deltaX + this.minX, deltaY + this.minY, deltaZ + this.minZ, deltaX + this.maxX, deltaY + this.maxY, deltaZ + this.maxZ);
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
    public FloatAABB translate(float deltaX, float deltaY, float deltaZ) {
        return new FloatAABB(deltaX + this.minX, deltaY + this.minY, deltaZ + this.minZ, deltaX + this.maxX, deltaY + this.maxY, deltaZ + this.maxZ);
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
    public FloatAABB union(FloatAABB other) {
        float minX = other.minX();
        float minY = other.minY();
        float minZ = other.minZ();
        float maxX = other.maxX();
        float maxY = other.maxY();
        float maxZ = other.maxZ();
        return new FloatAABB(java.lang.Math.min(this.minX, minX), java.lang.Math.min(this.minY, minY), java.lang.Math.min(this.minZ, minZ), java.lang.Math.max(this.maxX, maxX), java.lang.Math.max(this.maxY, maxY), java.lang.Math.max(this.maxZ, maxZ));
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
    public FloatAABB union(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new FloatAABB(java.lang.Math.min(this.minX, minX), java.lang.Math.min(this.minY, minY), java.lang.Math.min(this.minZ, minZ), java.lang.Math.max(this.maxX, maxX), java.lang.Math.max(this.maxY, maxY), java.lang.Math.max(this.maxZ, maxZ));
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
    public FloatAABB union(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        return new FloatAABB(java.lang.Math.min(this.minX, pX), java.lang.Math.min(this.minY, pY), java.lang.Math.min(this.minZ, pZ), java.lang.Math.max(this.maxX, pX), java.lang.Math.max(this.maxY, pY), java.lang.Math.max(this.maxZ, pZ));
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
    public FloatAABB union(float pX, float pY, float pZ) {
        return new FloatAABB(java.lang.Math.min(this.minX, pX), java.lang.Math.min(this.minY, pY), java.lang.Math.min(this.minZ, pZ), java.lang.Math.max(this.maxX, pX), java.lang.Math.max(this.maxY, pY), java.lang.Math.max(this.maxZ, pZ));
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Float3 closestPointToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        return new Float3(java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX)), java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY)), java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ)));
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
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
    public Float3 closestPointToPoint(float pX, float pY, float pZ) {
        return new Float3(java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX)), java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY)), java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ)));
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given box, i.e.
     *        the squared length of the shortest vector between any two points of the two boxes;
     *        zero when they overlap or touch
     */
    public float distanceSquaredToAABB(FloatAABB other) {
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - other.maxZ(), other.minZ() - this.maxZ));
        float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - other.maxX(), other.minX() - this.maxX));
        float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - other.maxY(), other.minY() - this.maxY));
        return Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
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
    public float distanceSquaredToAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
        float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
        float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
        return Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given point, i.e.
     *        the squared length of the difference between the point and its per-axis clamp into the
     *        box's bounds; zero for a point inside or on the box
     */
    public float distanceSquaredToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
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
    public float distanceSquaredToPoint(float pX, float pY, float pZ) {
        float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public float distanceSquaredToSphere(FloatSphere sphere) {
        float sphereX = sphere.x();
        float sphereY = sphere.y();
        float sphereZ = sphere.z();
        float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphere.r());
        return _t14 * _t14;
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
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
    public float distanceSquaredToSphere(float sphereX, float sphereY, float sphereZ, float sphereR) {
        float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
        return _t14 * _t14;
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public float distanceSquaredToSphere(Float3 center, float radius) {
        float sphereX = center.x();
        float sphereY = center.y();
        float sphereZ = center.z();
        float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - radius);
        return _t14 * _t14;
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given box, i.e. the
     *        length of the shortest vector between any two points of the two boxes; zero when they
     *        overlap or touch
     */
    public float distanceToAABB(FloatAABB other) {
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - other.maxZ(), other.minZ() - this.maxZ));
        float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - other.maxX(), other.minX() - this.maxX));
        float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - other.maxY(), other.minY() - this.maxY));
        return (float) java.lang.Math.sqrt(Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
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
    public float distanceToAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
        float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
        float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
        return (float) java.lang.Math.sqrt(Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public float distanceToPlane(FloatPlane plane) {
        float planeA = plane.a();
        float planeB = plane.b();
        float planeC = plane.c();
        return (1.0f / (float) java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0f, Math.fma(-0.5f, Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(Math.fma(0.5f, Math.fma(planeC, this.minZ + this.maxZ, Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), plane.d()))));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code (planeA, planeB, planeC, planeD)} must be non-zero.
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
    public float distanceToPlane(float planeA, float planeB, float planeC, float planeD) {
        return (1.0f / (float) java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0f, Math.fma(-0.5f, Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(Math.fma(0.5f, Math.fma(planeC, this.minZ + this.maxZ, Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), planeD))));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public float distanceToPlane(Float4 plane) {
        float planeA = plane.x();
        float planeB = plane.y();
        float planeC = plane.z();
        return (1.0f / (float) java.lang.Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0f, Math.fma(-0.5f, Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(Math.fma(0.5f, Math.fma(planeC, this.minZ + this.maxZ, Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), plane.w()))));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given point, i.e. the
     *        length of the difference between the point and its per-axis clamp into the box's
     *        bounds; zero for a point inside or on the box
     */
    public float distanceToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8)));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
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
    public float distanceToPoint(float pX, float pY, float pZ) {
        float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8)));
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public float distanceToSphere(FloatSphere sphere) {
        float sphereX = sphere.x();
        float sphereY = sphere.y();
        float sphereZ = sphere.z();
        float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphere.r());
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
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
    public float distanceToSphere(float sphereX, float sphereY, float sphereZ, float sphereR) {
        float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public float distanceToSphere(Float3 center, float radius) {
        float sphereX = center.x();
        float sphereY = center.y();
        float sphereZ = center.z();
        float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
        float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
        float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
        return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - radius);
    }


    /**
     * Get the center of this axis-aligned bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getCenter() {
        return new Float3(0.5f * this.minX + 0.5f * this.maxX, 0.5f * this.minY + 0.5f * this.maxY, 0.5f * this.minZ + 0.5f * this.maxZ);
    }


    /**
     * Get the maximum corner of this axis-aligned bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getMax() {
        return new Float3(this.maxX, this.maxY, this.maxZ);
    }


    /**
     * Get the minimum corner of this axis-aligned bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getMin() {
        return new Float3(this.minX, this.minY, this.minZ);
    }


    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this axis-aligned
     * bounding box, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getSize() {
        return new Float3(this.maxX - this.minX, this.maxY - this.minY, this.maxZ - this.minZ);
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
     * inclusive). Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @param pZ the z coordinate of the point
     * @return {@code true} if the given point lies inside or on this axis-aligned bounding box,
     *        {@code false} otherwise
     */
    public boolean containsPoint(float pX, float pY, float pZ) {
        return Intersectionf.testPointAabb(pX, pY, pZ, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box contains the given point (boundary
     * inclusive). Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if the given point lies inside or on this axis-aligned bounding box,
     *        {@code false} otherwise
     */
    public boolean containsPoint(Float3 p) {
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
    public boolean containsAABB(FloatAABB o) {
        return minX() <= o.minX() && minY() <= o.minY() && minZ() <= o.minZ() && maxX() >= o.maxX() && maxY() >= o.maxY() && maxZ() >= o.maxZ();
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box.
     * Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the axis-aligned box to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given axis-aligned box
     *        intersect, {@code false} otherwise
     */
    public boolean intersectsAABB(FloatAABB o) {
        return Intersectionf.testAabbAabb(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), o.minX(), o.minY(), o.minZ(), o.maxX(), o.maxY(), o.maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box swept
     * by the given velocity. Delegates to the shared {@code Intersectionf} kernels.
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
    public boolean intersectsSweptAABB(FloatAABB other, float vX, float vY, float vZ) {
        return Intersectionf.testMovingAabbAabb(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ(), vX, vY, vZ, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box swept
     * by the given velocity. Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned box that moves
     * @param velocity the velocity the given box moves by over one step
     * @return {@code true} if the moving box meets this axis-aligned bounding box at any point of
     *        the step, {@code false} otherwise
     */
    public boolean intersectsSweptAABB(FloatAABB other, Float3 velocity) {
        return intersectsSweptAABB(other, velocity.x(), velocity.y(), velocity.z());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given sphere. Delegates to
     * the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sph the sphere to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given sphere intersect,
     *        {@code false} otherwise
     */
    public boolean intersectsSphere(FloatSphere sph) {
        return Intersectionf.testAabbSphere(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), sph.x(), sph.y(), sph.z(), sph.r() * sph.r());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given plane. Delegates to the
     * shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param plane the plane to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given plane intersect,
     *        {@code false} otherwise
     */
    public boolean intersectsPlane(FloatPlane plane) {
        return Intersectionf.testAabbPlane(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), plane.a(), plane.b(), plane.c(), plane.d());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given ray. Delegates to the
     * shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param ray the ray to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given ray intersect,
     *        {@code false} otherwise
     */
    public boolean intersectsRay(FloatRay ray) {
        return Intersectionf.testRayAabb(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * Determine whether this axis-aligned bounding box intersects the given ray and, if so, the
     * values of <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     * points of intersection. Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param ray the ray to intersect
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is {@code true} iff the ray
     *        intersects this axis-aligned bounding box, and whose components then hold the values
     *        of <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     *        points of intersection; {@link FloatHit2#MISS} otherwise
     */
    public FloatHit2 intersectRay(FloatRay ray) {
        return Intersectionf.intersectRayAabb(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    /**
     * {@return a copy with the {@code minX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code minX} component
     */
    public FloatAABB withMinX(float v) {
        return new FloatAABB(v, minY, minZ, maxX, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code minY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code minY} component
     */
    public FloatAABB withMinY(float v) {
        return new FloatAABB(minX, v, minZ, maxX, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code minZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code minZ} component
     */
    public FloatAABB withMinZ(float v) {
        return new FloatAABB(minX, minY, v, maxX, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code maxX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code maxX} component
     */
    public FloatAABB withMaxX(float v) {
        return new FloatAABB(minX, minY, minZ, v, maxY, maxZ);
    }

    /**
     * {@return a copy with the {@code maxY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code maxY} component
     */
    public FloatAABB withMaxY(float v) {
        return new FloatAABB(minX, minY, minZ, maxX, v, maxZ);
    }

    /**
     * {@return a copy with the {@code maxZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code maxZ} component
     */
    public FloatAABB withMaxZ(float v) {
        return new FloatAABB(minX, minY, minZ, maxX, maxY, v);
    }

    @Override public String toString() {
        return "FloatAABB(" + minX() + ", " + minY() + ", " + minZ() + ", " + maxX() + ", " + maxY() + ", " + maxZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatAABB)) return false;
        FloatAABB o = (FloatAABB) obj;
        return Float.floatToIntBits(minX) == Float.floatToIntBits(o.minX)
            && Float.floatToIntBits(minY) == Float.floatToIntBits(o.minY)
            && Float.floatToIntBits(minZ) == Float.floatToIntBits(o.minZ)
            && Float.floatToIntBits(maxX) == Float.floatToIntBits(o.maxX)
            && Float.floatToIntBits(maxY) == Float.floatToIntBits(o.maxY)
            && Float.floatToIntBits(maxZ) == Float.floatToIntBits(o.maxZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(minX);
        h = 31 * h + Float.floatToIntBits(minY);
        h = 31 * h + Float.floatToIntBits(minZ);
        h = 31 * h + Float.floatToIntBits(maxX);
        h = 31 * h + Float.floatToIntBits(maxY);
        h = 31 * h + Float.floatToIntBits(maxZ);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(minX)
            && Float.isFinite(minY)
            && Float.isFinite(minZ)
            && Float.isFinite(maxX)
            && Float.isFinite(maxY)
            && Float.isFinite(maxZ);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(minX)
            || Float.isNaN(minY)
            || Float.isNaN(minZ)
            || Float.isNaN(maxX)
            || Float.isNaN(maxY)
            || Float.isNaN(maxZ);
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
    public boolean equalsEpsilon(FloatAABB other, float epsilon) {
        return java.lang.Math.abs(minX - other.minX()) <= epsilon
            && java.lang.Math.abs(minY - other.minY()) <= epsilon
            && java.lang.Math.abs(minZ - other.minZ()) <= epsilon
            && java.lang.Math.abs(maxX - other.maxX()) <= epsilon
            && java.lang.Math.abs(maxY - other.maxY()) <= epsilon
            && java.lang.Math.abs(maxZ - other.maxZ()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatAABBSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatAABBSegOpsUnsafe()
                        : new FloatAABBSegOpsMS();
        static final FloatAABBBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatAABBBbOpsUnsafe()
                        : new FloatAABBBbOpsApi();
        static final FloatAABBRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatAABBRawOpsUnsafe()
                        : new FloatAABBRawOpsApi();
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
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(float[] src) { return load(src, 0); }

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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(FloatBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatAABB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatAABB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(ByteBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatAABB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatAABB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 24);
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
    public FloatAABB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadUnsafe(long address) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(long offset, MemorySegment src) {
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
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.minZ;
        dest[offset + 3] = this.maxX;
        dest[offset + 4] = this.maxY;
        dest[offset + 5] = this.maxZ;
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(double[] src) { return load(src, 0); }

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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB load(DoubleBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatAABB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatAABB r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadDouble(ByteBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatAABB loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatAABB r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 48);
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
    public FloatAABB storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadDoubleUnsafe(long address) {
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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

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
     * @return a new {@code FloatAABB} holding the loaded elements
     */
    public static FloatAABB loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(offset, src);
    }
}
