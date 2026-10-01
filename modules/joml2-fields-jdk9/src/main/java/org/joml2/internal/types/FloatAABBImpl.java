// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatAABB} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatAABBImpl implements FloatAABB {

    public float minX;
    public float minY;
    public float minZ;
    public float maxX;
    public float maxY;
    public float maxZ;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatAABBBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatAABBBbOpsUnsafe()
                        : new FloatAABBBbOpsApi();
        static final FloatAABBRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatAABBRawOpsUnsafe()
                        : new FloatAABBRawOpsApi();
    }

    public FloatAABBImpl() {
        minX = Float.POSITIVE_INFINITY;
        minY = Float.POSITIVE_INFINITY;
        minZ = Float.POSITIVE_INFINITY;
        maxX = Float.NEGATIVE_INFINITY;
        maxY = Float.NEGATIVE_INFINITY;
        maxZ = Float.NEGATIVE_INFINITY;
    }

    public FloatAABBImpl(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public FloatAABBImpl(FloatAABBR src) {
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
    public @Mutated FloatAABB set(FloatAABBR v) {
        float minY = v.minY();
        float minZ = v.minZ();
        float maxX = v.maxX();
        float maxY = v.maxY();
        float maxZ = v.maxZ();
        this.minX = v.minX();
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        return this;
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
    @Mutated public FloatAABB set(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
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
    public FloatAABB setMax(Float3R max, @Mutated FloatAABB dest) {
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.minZ = this.minZ;
        d.maxX = maxX;
        d.maxY = maxY;
        d.maxZ = maxZ;
        return d;
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMax(Float3R max, @Mutated DoubleAABB dest) {
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
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
    public FloatAABB setMax(float maxX, float maxY, float maxZ, @Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.minZ = this.minZ;
        d.maxX = maxX;
        d.maxY = maxY;
        d.maxZ = maxZ;
        return d;
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to ({@code maxX}, {@code maxY},
     * {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMax(float maxX, float maxY, float maxZ, @Mutated DoubleAABB dest) {
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
    public FloatAABB setMin(Float3R min, @Mutated FloatAABB dest) {
        float minY = min.y();
        float minZ = min.z();
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = min.x();
        d.minY = minY;
        d.minZ = minZ;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        d.maxZ = this.maxZ;
        return d;
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to {@code min} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMin(Float3R min, @Mutated DoubleAABB dest) {
        float minY = min.y();
        float minZ = min.z();
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = min.x();
        d.minY = minY;
        d.minZ = minZ;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        d.maxZ = this.maxZ;
        return d;
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
    public FloatAABB setMin(float minX, float minY, float minZ, @Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = minX;
        d.minY = minY;
        d.minZ = minZ;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        d.maxZ = this.maxZ;
        return d;
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to ({@code minX}, {@code minY},
     * {@code minZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMin(float minX, float minY, float minZ, @Mutated DoubleAABB dest) {
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
     * Convert this axis-aligned bounding box to {@code double} precision and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB toDouble(@Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.minZ = this.minZ;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        d.maxZ = this.maxZ;
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
    public FloatAABB correctBounds(@Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        float _rd0 = this.minX;
        float _rd1 = this.minY;
        float _rd2 = this.minZ;
        d.minX = java.lang.Math.min(_rd0, this.maxX);
        d.minY = java.lang.Math.min(_rd1, this.maxY);
        d.minZ = java.lang.Math.min(_rd2, this.maxZ);
        d.maxX = java.lang.Math.max(_rd0, this.maxX);
        d.maxY = java.lang.Math.max(_rd1, this.maxY);
        d.maxZ = java.lang.Math.max(_rd2, this.maxZ);
        return d;
    }


    /**
     * Swap the minimum and maximum bounds of this axis-aligned bounding box where necessary so the
     * bounds are valid and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB correctBounds(@Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, this.maxX);
        d.minY = java.lang.Math.min(this.minY, this.maxY);
        d.minZ = java.lang.Math.min(this.minZ, this.maxZ);
        d.maxX = java.lang.Math.max(this.minX, this.maxX);
        d.maxY = java.lang.Math.max(this.minY, this.maxY);
        d.maxZ = java.lang.Math.max(this.minZ, this.maxZ);
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s15123f48_c0_fma(FloatAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.minX = _t34 < 0.0f ? Float.POSITIVE_INFINITY : java.lang.Math.fma(0.5f, _t18, java.lang.Math.fma(0.5f, _t28, java.lang.Math.fma(-0.5f, _t35, _r15)));
        _dst.minY = _t34 < 0.0f ? Float.POSITIVE_INFINITY : java.lang.Math.fma(0.5f, _t20, java.lang.Math.fma(0.5f, _t29, java.lang.Math.fma(-0.5f, _t36, _r16)));
        _dst.minZ = _t34 < 0.0f ? Float.POSITIVE_INFINITY : java.lang.Math.fma(0.5f, _t22, java.lang.Math.fma(0.5f, _t30, java.lang.Math.fma(-0.5f, _t37, _r17)));
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s15123f48_c0_mulAdd(FloatAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.minX = _t34 < 0.0f ? Float.POSITIVE_INFINITY : ((0.5f) * (_t18) + (((0.5f) * (_t28) + (((-0.5f) * (_t35) + (_r15))))));
        _dst.minY = _t34 < 0.0f ? Float.POSITIVE_INFINITY : ((0.5f) * (_t20) + (((0.5f) * (_t29) + (((-0.5f) * (_t36) + (_r16))))));
        _dst.minZ = _t34 < 0.0f ? Float.POSITIVE_INFINITY : ((0.5f) * (_t22) + (((0.5f) * (_t30) + (((-0.5f) * (_t37) + (_r17))))));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s15123f48_c1_fma(FloatAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.maxX = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : java.lang.Math.fma(0.5f, _t18, java.lang.Math.fma(0.5f, _t28, java.lang.Math.fma(0.5f, _t35, _r15)));
        _dst.maxY = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : java.lang.Math.fma(0.5f, _t20, java.lang.Math.fma(0.5f, _t29, java.lang.Math.fma(0.5f, _t36, _r16)));
        _dst.maxZ = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : java.lang.Math.fma(0.5f, _t22, java.lang.Math.fma(0.5f, _t30, java.lang.Math.fma(0.5f, _t37, _r17)));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s15123f48_c1_mulAdd(FloatAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.maxX = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : ((0.5f) * (_t18) + (((0.5f) * (_t28) + (((0.5f) * (_t35) + (_r15))))));
        _dst.maxY = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : ((0.5f) * (_t20) + (((0.5f) * (_t29) + (((0.5f) * (_t36) + (_r16))))));
        _dst.maxZ = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : ((0.5f) * (_t22) + (((0.5f) * (_t30) + (((0.5f) * (_t37) + (_r17))))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s15123f48_tail_fma(FloatAABBImpl _dst, float _r3, float _r2, float _r6, float _t12, float _r7, float _r8, float _r9, float _t13, float _r10, float _r11, float _r12, float _r13, float _r14, float _t9, float _t10, float _t11, float _r15, float _r16, float _r17) {
        float _t14 = _r3 + _r2;
        float _t18 = _r6 * _t12;
        float _t20 = _r7 * _t12;
        float _t22 = _r8 * _t12;
        float _t28 = java.lang.Math.fma(_r9, _t13, _r10 * _t14);
        float _t29 = java.lang.Math.fma(_r11, _t13, _r12 * _t14);
        float _t30 = java.lang.Math.fma(_r13, _t13, _r14 * _t14);
        float _t34 = java.lang.Math.min(java.lang.Math.min(0.5f * _t9, 0.5f * _t10), 0.5f * _t11);
        float _t35 = java.lang.Math.fma(_t11, java.lang.Math.abs(_r6), java.lang.Math.fma(_t9, java.lang.Math.abs(_r9), _t10 * java.lang.Math.abs(_r10)));
        float _t36 = java.lang.Math.fma(_t11, java.lang.Math.abs(_r7), java.lang.Math.fma(_t9, java.lang.Math.abs(_r11), _t10 * java.lang.Math.abs(_r12)));
        float _t37 = java.lang.Math.fma(_t11, java.lang.Math.abs(_r8), java.lang.Math.fma(_t9, java.lang.Math.abs(_r13), _t10 * java.lang.Math.abs(_r14)));
        transform_s15123f48_c0_fma(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
        transform_s15123f48_c1_fma(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s15123f48_tail_mulAdd(FloatAABBImpl _dst, float _r3, float _r2, float _r6, float _t12, float _r7, float _r8, float _r9, float _t13, float _r10, float _r11, float _r12, float _r13, float _r14, float _t9, float _t10, float _t11, float _r15, float _r16, float _r17) {
        float _t14 = _r3 + _r2;
        float _t18 = _r6 * _t12;
        float _t20 = _r7 * _t12;
        float _t22 = _r8 * _t12;
        float _t28 = ((_r9) * (_t13) + (_r10 * _t14));
        float _t29 = ((_r11) * (_t13) + (_r12 * _t14));
        float _t30 = ((_r13) * (_t13) + (_r14 * _t14));
        float _t34 = java.lang.Math.min(java.lang.Math.min(0.5f * _t9, 0.5f * _t10), 0.5f * _t11);
        float _t35 = ((_t11) * (java.lang.Math.abs(_r6)) + (((_t9) * (java.lang.Math.abs(_r9)) + (_t10 * java.lang.Math.abs(_r10)))));
        float _t36 = ((_t11) * (java.lang.Math.abs(_r7)) + (((_t9) * (java.lang.Math.abs(_r11)) + (_t10 * java.lang.Math.abs(_r12)))));
        float _t37 = ((_t11) * (java.lang.Math.abs(_r8)) + (((_t9) * (java.lang.Math.abs(_r13)) + (_t10 * java.lang.Math.abs(_r14)))));
        transform_s15123f48_c0_mulAdd(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
        transform_s15123f48_c1_mulAdd(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
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
    public FloatAABB transform(Float3x4R m, @Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        float _r0 = this.maxX;
        float _r1 = this.minX;
        float _r2 = this.maxY;
        float _r3 = this.minY;
        float _r4 = this.maxZ;
        float _r5 = this.minZ;
        float _r6 = m.m02();
        float _r7 = m.m12();
        float _r8 = m.m22();
        float _r9 = m.m00();
        float _r10 = m.m01();
        float _r11 = m.m10();
        float _r12 = m.m11();
        float _r13 = m.m20();
        float _r14 = m.m21();
        float _r15 = m.m03();
        float _r16 = m.m13();
        float _r17 = m.m23();
        if (Math.useFma()) transform_s15123f48_tail_fma(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17); else transform_s15123f48_tail_mulAdd(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17);
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s1f6f022f_c0_fma(DoubleAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.minX = _t34 < 0.0f ? Float.POSITIVE_INFINITY : java.lang.Math.fma(0.5f, _t18, java.lang.Math.fma(0.5f, _t28, java.lang.Math.fma(-0.5f, _t35, _r15)));
        _dst.minY = _t34 < 0.0f ? Float.POSITIVE_INFINITY : java.lang.Math.fma(0.5f, _t20, java.lang.Math.fma(0.5f, _t29, java.lang.Math.fma(-0.5f, _t36, _r16)));
        _dst.minZ = _t34 < 0.0f ? Float.POSITIVE_INFINITY : java.lang.Math.fma(0.5f, _t22, java.lang.Math.fma(0.5f, _t30, java.lang.Math.fma(-0.5f, _t37, _r17)));
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s1f6f022f_c0_mulAdd(DoubleAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.minX = _t34 < 0.0f ? Float.POSITIVE_INFINITY : ((0.5f) * (_t18) + (((0.5f) * (_t28) + (((-0.5f) * (_t35) + (_r15))))));
        _dst.minY = _t34 < 0.0f ? Float.POSITIVE_INFINITY : ((0.5f) * (_t20) + (((0.5f) * (_t29) + (((-0.5f) * (_t36) + (_r16))))));
        _dst.minZ = _t34 < 0.0f ? Float.POSITIVE_INFINITY : ((0.5f) * (_t22) + (((0.5f) * (_t30) + (((-0.5f) * (_t37) + (_r17))))));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s1f6f022f_c1_fma(DoubleAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.maxX = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : java.lang.Math.fma(0.5f, _t18, java.lang.Math.fma(0.5f, _t28, java.lang.Math.fma(0.5f, _t35, _r15)));
        _dst.maxY = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : java.lang.Math.fma(0.5f, _t20, java.lang.Math.fma(0.5f, _t29, java.lang.Math.fma(0.5f, _t36, _r16)));
        _dst.maxZ = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : java.lang.Math.fma(0.5f, _t22, java.lang.Math.fma(0.5f, _t30, java.lang.Math.fma(0.5f, _t37, _r17)));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s1f6f022f_c1_mulAdd(DoubleAABBImpl _dst, float _t34, float _t18, float _t28, float _t35, float _r15, float _t20, float _t29, float _t36, float _r16, float _t22, float _t30, float _t37, float _r17) {
        _dst.maxX = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : ((0.5f) * (_t18) + (((0.5f) * (_t28) + (((0.5f) * (_t35) + (_r15))))));
        _dst.maxY = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : ((0.5f) * (_t20) + (((0.5f) * (_t29) + (((0.5f) * (_t36) + (_r16))))));
        _dst.maxZ = _t34 < 0.0f ? Float.NEGATIVE_INFINITY : ((0.5f) * (_t22) + (((0.5f) * (_t30) + (((0.5f) * (_t37) + (_r17))))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s1f6f022f_tail_fma(DoubleAABBImpl _dst, float _r3, float _r2, float _r6, float _t12, float _r7, float _r8, float _r9, float _t13, float _r10, float _r11, float _r12, float _r13, float _r14, float _t9, float _t10, float _t11, float _r15, float _r16, float _r17) {
        float _t14 = _r3 + _r2;
        float _t18 = _r6 * _t12;
        float _t20 = _r7 * _t12;
        float _t22 = _r8 * _t12;
        float _t28 = java.lang.Math.fma(_r9, _t13, _r10 * _t14);
        float _t29 = java.lang.Math.fma(_r11, _t13, _r12 * _t14);
        float _t30 = java.lang.Math.fma(_r13, _t13, _r14 * _t14);
        float _t34 = java.lang.Math.min(java.lang.Math.min(0.5f * _t9, 0.5f * _t10), 0.5f * _t11);
        float _t35 = java.lang.Math.fma(_t11, java.lang.Math.abs(_r6), java.lang.Math.fma(_t9, java.lang.Math.abs(_r9), _t10 * java.lang.Math.abs(_r10)));
        float _t36 = java.lang.Math.fma(_t11, java.lang.Math.abs(_r7), java.lang.Math.fma(_t9, java.lang.Math.abs(_r11), _t10 * java.lang.Math.abs(_r12)));
        float _t37 = java.lang.Math.fma(_t11, java.lang.Math.abs(_r8), java.lang.Math.fma(_t9, java.lang.Math.abs(_r13), _t10 * java.lang.Math.abs(_r14)));
        transform_s1f6f022f_c0_fma(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
        transform_s1f6f022f_c1_fma(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s1f6f022f_tail_mulAdd(DoubleAABBImpl _dst, float _r3, float _r2, float _r6, float _t12, float _r7, float _r8, float _r9, float _t13, float _r10, float _r11, float _r12, float _r13, float _r14, float _t9, float _t10, float _t11, float _r15, float _r16, float _r17) {
        float _t14 = _r3 + _r2;
        float _t18 = _r6 * _t12;
        float _t20 = _r7 * _t12;
        float _t22 = _r8 * _t12;
        float _t28 = ((_r9) * (_t13) + (_r10 * _t14));
        float _t29 = ((_r11) * (_t13) + (_r12 * _t14));
        float _t30 = ((_r13) * (_t13) + (_r14 * _t14));
        float _t34 = java.lang.Math.min(java.lang.Math.min(0.5f * _t9, 0.5f * _t10), 0.5f * _t11);
        float _t35 = ((_t11) * (java.lang.Math.abs(_r6)) + (((_t9) * (java.lang.Math.abs(_r9)) + (_t10 * java.lang.Math.abs(_r10)))));
        float _t36 = ((_t11) * (java.lang.Math.abs(_r7)) + (((_t9) * (java.lang.Math.abs(_r11)) + (_t10 * java.lang.Math.abs(_r12)))));
        float _t37 = ((_t11) * (java.lang.Math.abs(_r8)) + (((_t9) * (java.lang.Math.abs(_r13)) + (_t10 * java.lang.Math.abs(_r14)))));
        transform_s1f6f022f_c0_mulAdd(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
        transform_s1f6f022f_c1_mulAdd(_dst, _t34, _t18, _t28, _t35, _r15, _t20, _t29, _t36, _r16, _t22, _t30, _t37, _r17);
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB transform(Float3x4R m, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        float _r0 = this.maxX;
        float _r1 = this.minX;
        float _r2 = this.maxY;
        float _r3 = this.minY;
        float _r4 = this.maxZ;
        float _r5 = this.minZ;
        float _r6 = m.m02();
        float _r7 = m.m12();
        float _r8 = m.m22();
        float _r9 = m.m00();
        float _r10 = m.m01();
        float _r11 = m.m10();
        float _r12 = m.m11();
        float _r13 = m.m20();
        float _r14 = m.m21();
        float _r15 = m.m03();
        float _r16 = m.m13();
        float _r17 = m.m23();
        if (Math.useFma()) transform_s1f6f022f_tail_fma(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17); else transform_s1f6f022f_tail_mulAdd(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17);
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
    public FloatAABB transform(Float4x4R m, @Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        float _r0 = this.maxX;
        float _r1 = this.minX;
        float _r2 = this.maxY;
        float _r3 = this.minY;
        float _r4 = this.maxZ;
        float _r5 = this.minZ;
        float _r6 = m.m02();
        float _r7 = m.m12();
        float _r8 = m.m22();
        float _r9 = m.m00();
        float _r10 = m.m01();
        float _r11 = m.m10();
        float _r12 = m.m11();
        float _r13 = m.m20();
        float _r14 = m.m21();
        float _r15 = m.m03();
        float _r16 = m.m13();
        float _r17 = m.m23();
        if (Math.useFma()) transform_s15123f48_tail_fma(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17); else transform_s15123f48_tail_mulAdd(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17);
        return d;
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB transform(Float4x4R m, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        float _r0 = this.maxX;
        float _r1 = this.minX;
        float _r2 = this.maxY;
        float _r3 = this.minY;
        float _r4 = this.maxZ;
        float _r5 = this.minZ;
        float _r6 = m.m02();
        float _r7 = m.m12();
        float _r8 = m.m22();
        float _r9 = m.m00();
        float _r10 = m.m01();
        float _r11 = m.m10();
        float _r12 = m.m11();
        float _r13 = m.m20();
        float _r14 = m.m21();
        float _r15 = m.m03();
        float _r16 = m.m13();
        float _r17 = m.m23();
        if (Math.useFma()) transform_s1f6f022f_tail_fma(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17); else transform_s1f6f022f_tail_mulAdd(d, _r3, _r2, _r6, _r5 + _r4, _r7, _r8, _r9, _r1 + _r0, _r10, _r11, _r12, _r13, _r14, _r0 - _r1, _r2 - _r3, _r4 - _r5, _r15, _r16, _r17);
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
    public FloatAABB translate(Float3R delta, @Mutated FloatAABB dest) {
        float deltaX = delta.x();
        float deltaY = delta.y();
        float deltaZ = delta.z();
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.minZ = deltaZ + this.minZ;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        d.maxZ = deltaZ + this.maxZ;
        return d;
    }


    /**
     * Translate this axis-aligned bounding box by {@code delta} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB translate(Float3R delta, @Mutated DoubleAABB dest) {
        float deltaX = delta.x();
        float deltaY = delta.y();
        float deltaZ = delta.z();
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
    public FloatAABB translate(float deltaX, float deltaY, float deltaZ, @Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.minZ = deltaZ + this.minZ;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        d.maxZ = deltaZ + this.maxZ;
        return d;
    }


    /**
     * Translate this axis-aligned bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB translate(float deltaX, float deltaY, float deltaZ, @Mutated DoubleAABB dest) {
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
    public FloatAABB union(FloatAABBR other, @Mutated FloatAABB dest) {
        float minY = other.minY();
        float minZ = other.minZ();
        float maxX = other.maxX();
        float maxY = other.maxY();
        float maxZ = other.maxZ();
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, other.minX());
        d.minY = java.lang.Math.min(this.minY, minY);
        d.minZ = java.lang.Math.min(this.minZ, minZ);
        d.maxX = java.lang.Math.max(this.maxX, maxX);
        d.maxY = java.lang.Math.max(this.maxY, maxY);
        d.maxZ = java.lang.Math.max(this.maxZ, maxZ);
        return d;
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned bounding box to include in the union
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(FloatAABBR other, @Mutated DoubleAABB dest) {
        float minY = other.minY();
        float minZ = other.minZ();
        float maxX = other.maxX();
        float maxY = other.maxY();
        float maxZ = other.maxZ();
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, other.minX());
        d.minY = java.lang.Math.min(this.minY, minY);
        d.minZ = java.lang.Math.min(this.minZ, minZ);
        d.maxX = java.lang.Math.max(this.maxX, maxX);
        d.maxY = java.lang.Math.max(this.maxY, maxY);
        d.maxZ = java.lang.Math.max(this.maxZ, maxZ);
        return d;
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
    public FloatAABB union(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, @Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, minX);
        d.minY = java.lang.Math.min(this.minY, minY);
        d.minZ = java.lang.Math.min(this.minZ, minZ);
        d.maxX = java.lang.Math.max(this.maxX, maxX);
        d.maxY = java.lang.Math.max(this.maxY, maxY);
        d.maxZ = java.lang.Math.max(this.maxZ, maxZ);
        return d;
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and ({@code minX}, {@code minY},
     * {@code minZ}, {@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleAABB union(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, minX);
        d.minY = java.lang.Math.min(this.minY, minY);
        d.minZ = java.lang.Math.min(this.minZ, minZ);
        d.maxX = java.lang.Math.max(this.maxX, maxX);
        d.maxY = java.lang.Math.max(this.maxY, maxY);
        d.maxZ = java.lang.Math.max(this.maxZ, maxZ);
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
    public FloatAABB union(Float3R p, @Mutated FloatAABB dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.minZ = java.lang.Math.min(this.minZ, pZ);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        d.maxZ = java.lang.Math.max(this.maxZ, pZ);
        return d;
    }


    /**
     * Grow this axis-aligned bounding box to include the point {@code p} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(Float3R p, @Mutated DoubleAABB dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.minZ = java.lang.Math.min(this.minZ, pZ);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        d.maxZ = java.lang.Math.max(this.maxZ, pZ);
        return d;
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
    public FloatAABB union(float pX, float pY, float pZ, @Mutated FloatAABB dest) {
        FloatAABBImpl d = (FloatAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.minZ = java.lang.Math.min(this.minZ, pZ);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        d.maxZ = java.lang.Math.max(this.maxZ, pZ);
        return d;
    }


    /**
     * Grow this axis-aligned bounding box to include the point ({@code pX}, {@code pY}, {@code pZ})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(float pX, float pY, float pZ, @Mutated DoubleAABB dest) {
        DoubleAABBImpl d = (DoubleAABBImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.minZ = java.lang.Math.min(this.minZ, pZ);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        d.maxZ = java.lang.Math.max(this.maxZ, pZ);
        return d;
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        float pY = p.y();
        float pZ = p.z();
        Float3Impl d = (Float3Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(p.x(), this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        d.z = java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        return d;
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Float3R p, @Mutated Double3 dest) {
        float pY = p.y();
        float pZ = p.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(p.x(), this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        d.z = java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        return d;
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        d.z = java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        return d;
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        d.z = java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
        return d;
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
    public float distanceSquaredToAABB(FloatAABBR other) {
        float minX = other.minX();
        float minY = other.minY();
        float minZ = other.minZ();
        float maxX = other.maxX();
        float maxY = other.maxY();
        float maxZ = other.maxZ();
        if (Math.useFma()) {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        } else {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        }
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
        if (Math.useFma()) {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        } else {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        }
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
    public float distanceSquaredToPoint(Float3R p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
        } else {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
        }
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
        if (Math.useFma()) {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
        } else {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
        }
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
    public float distanceSquaredToSphere(FloatSphereR sphere) {
        float sphereX = sphere.x();
        float sphereY = sphere.y();
        float sphereZ = sphere.z();
        float sphereR = sphere.r();
        if (Math.useFma()) {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
            return _t14 * _t14;
        } else {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
            return _t14 * _t14;
        }
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
        if (Math.useFma()) {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
            return _t14 * _t14;
        } else {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
            return _t14 * _t14;
        }
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
    public float distanceSquaredToSphere(Float3R center, float radius) {
        float sphereX = center.x();
        float sphereY = center.y();
        float sphereZ = center.z();
        if (Math.useFma()) {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - radius);
            return _t14 * _t14;
        } else {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - radius);
            return _t14 * _t14;
        }
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
    public float distanceToAABB(FloatAABBR other) {
        float minX = other.minX();
        float minY = other.minY();
        float minZ = other.minZ();
        float maxX = other.maxX();
        float maxY = other.maxY();
        float maxZ = other.maxZ();
        if (Math.useFma()) {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11)));
        } else {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return (float) java.lang.Math.sqrt(((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))));
        }
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
        if (Math.useFma()) {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11)));
        } else {
            float _t9 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minZ - maxZ, minZ - this.maxZ));
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - maxX, minX - this.maxX));
            float _t11 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - maxY, minY - this.maxY));
            return (float) java.lang.Math.sqrt(((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))));
        }
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
    public float distanceToPlane(FloatPlaneR plane) {
        float planeA = plane.a();
        float planeB = plane.b();
        float planeC = plane.c();
        float planeD = plane.d();
        if (Math.useFma()) {
            return (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0f, java.lang.Math.fma(-0.5f, java.lang.Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), java.lang.Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(java.lang.Math.fma(0.5f, java.lang.Math.fma(planeC, this.minZ + this.maxZ, java.lang.Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), planeD))));
        } else {
            return (1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB)))))) * java.lang.Math.max(0.0f, ((-0.5f) * (((this.maxZ - this.minZ) * (java.lang.Math.abs(planeC)) + (((this.maxX - this.minX) * (java.lang.Math.abs(planeA)) + ((this.maxY - this.minY) * java.lang.Math.abs(planeB)))))) + (java.lang.Math.abs(((0.5f) * (((planeC) * (this.minZ + this.maxZ) + (((planeA) * (this.minX + this.maxX) + (planeB * (this.minY + this.maxY)))))) + (planeD))))));
        }
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
        if (Math.useFma()) {
            return (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0f, java.lang.Math.fma(-0.5f, java.lang.Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), java.lang.Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(java.lang.Math.fma(0.5f, java.lang.Math.fma(planeC, this.minZ + this.maxZ, java.lang.Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), planeD))));
        } else {
            return (1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB)))))) * java.lang.Math.max(0.0f, ((-0.5f) * (((this.maxZ - this.minZ) * (java.lang.Math.abs(planeC)) + (((this.maxX - this.minX) * (java.lang.Math.abs(planeA)) + ((this.maxY - this.minY) * java.lang.Math.abs(planeB)))))) + (java.lang.Math.abs(((0.5f) * (((planeC) * (this.minZ + this.maxZ) + (((planeA) * (this.minX + this.maxX) + (planeB * (this.minY + this.maxY)))))) + (planeD))))));
        }
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
    public float distanceToPlane(Float4R plane) {
        float planeA = plane.x();
        float planeB = plane.y();
        float planeC = plane.z();
        float planeD = plane.w();
        if (Math.useFma()) {
            return (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0f, java.lang.Math.fma(-0.5f, java.lang.Math.fma(this.maxZ - this.minZ, java.lang.Math.abs(planeC), java.lang.Math.fma(this.maxX - this.minX, java.lang.Math.abs(planeA), (this.maxY - this.minY) * java.lang.Math.abs(planeB))), java.lang.Math.abs(java.lang.Math.fma(0.5f, java.lang.Math.fma(planeC, this.minZ + this.maxZ, java.lang.Math.fma(planeA, this.minX + this.maxX, planeB * (this.minY + this.maxY))), planeD))));
        } else {
            return (1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB)))))) * java.lang.Math.max(0.0f, ((-0.5f) * (((this.maxZ - this.minZ) * (java.lang.Math.abs(planeC)) + (((this.maxX - this.minX) * (java.lang.Math.abs(planeA)) + ((this.maxY - this.minY) * java.lang.Math.abs(planeB)))))) + (java.lang.Math.abs(((0.5f) * (((planeC) * (this.minZ + this.maxZ) + (((planeA) * (this.minX + this.maxX) + (planeB * (this.minY + this.maxY)))))) + (planeD))))));
        }
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
    public float distanceToPoint(Float3R p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        } else {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8)))));
        }
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
        if (Math.useFma()) {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        } else {
            float _t6 = pZ - java.lang.Math.max(this.minZ, java.lang.Math.min(pZ, this.maxZ));
            float _t7 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t8 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8)))));
        }
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
    public float distanceToSphere(FloatSphereR sphere) {
        float sphereX = sphere.x();
        float sphereY = sphere.y();
        float sphereZ = sphere.z();
        float sphereR = sphere.r();
        if (Math.useFma()) {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
        } else {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
        }
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
        if (Math.useFma()) {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
        } else {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
        }
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
    public float distanceToSphere(Float3R center, float radius) {
        float sphereX = center.x();
        float sphereY = center.y();
        float sphereZ = center.z();
        if (Math.useFma()) {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - radius);
        } else {
            float _t6 = sphereZ - java.lang.Math.max(this.minZ, java.lang.Math.min(sphereZ, this.maxZ));
            float _t7 = sphereX - java.lang.Math.max(this.minX, java.lang.Math.min(sphereX, this.maxX));
            float _t8 = sphereY - java.lang.Math.max(this.minY, java.lang.Math.min(sphereY, this.maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - radius);
        }
    }


    /**
     * Get the center of this axis-aligned bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getCenter(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = 0.5f * this.minX + 0.5f * this.maxX;
        d.y = 0.5f * this.minY + 0.5f * this.maxY;
        d.z = 0.5f * this.minZ + 0.5f * this.maxZ;
        return d;
    }


    /**
     * Get the center of this axis-aligned bounding box and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getCenter(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = 0.5f * this.minX + 0.5f * this.maxX;
        d.y = 0.5f * this.minY + 0.5f * this.maxY;
        d.z = 0.5f * this.minZ + 0.5f * this.maxZ;
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
    public Float3 getMax(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.maxX;
        d.y = this.maxY;
        d.z = this.maxZ;
        return d;
    }


    /**
     * Get the maximum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Float3 getMin(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.minX;
        d.y = this.minY;
        d.z = this.minZ;
        return d;
    }


    /**
     * Get the minimum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Float3 getSize(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.maxX - this.minX;
        d.y = this.maxY - this.minY;
        d.z = this.maxZ - this.minZ;
        return d;
    }


    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this axis-aligned
     * bounding box and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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

    public float minX() { return this.minX; }
    public float minY() { return this.minY; }
    public float minZ() { return this.minZ; }
    public float maxX() { return this.maxX; }
    public float maxY() { return this.maxY; }
    public float maxZ() { return this.maxZ; }

    @Override public String toString() {
        return "FloatAABB(" + minX() + ", " + minY() + ", " + minZ() + ", " + maxX() + ", " + maxY() + ", " + maxZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatAABBImpl)) return false;
        FloatAABBImpl o = (FloatAABBImpl) obj;
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

    @Override public boolean isFinite() {
        return Float.isFinite(minX)
            && Float.isFinite(minY)
            && Float.isFinite(minZ)
            && Float.isFinite(maxX)
            && Float.isFinite(maxY)
            && Float.isFinite(maxZ);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(minX)
            || Float.isNaN(minY)
            || Float.isNaN(minZ)
            || Float.isNaN(maxX)
            || Float.isNaN(maxY)
            || Float.isNaN(maxZ);
    }

    @Override public boolean equalsEpsilon(FloatAABBR other, float epsilon) {
        return java.lang.Math.abs(minX - other.minX()) <= epsilon
            && java.lang.Math.abs(minY - other.minY()) <= epsilon
            && java.lang.Math.abs(minZ - other.minZ()) <= epsilon
            && java.lang.Math.abs(maxX - other.maxX()) <= epsilon
            && java.lang.Math.abs(maxY - other.maxY()) <= epsilon
            && java.lang.Math.abs(maxZ - other.maxZ()) <= epsilon;
    }

    public boolean containsPoint(float x, float y, float z) {
        return Intersectionf.testPointAabb(x, y, z, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    public boolean containsPoint(Float3R p) {
        return Intersectionf.testPointAabb(p, this);
    }

    public boolean containsAABB(FloatAABBR o) {
        return Intersectionf.testAabbAabbContains(this, o);
    }

    public boolean intersectsAABB(FloatAABBR o) {
        return Intersectionf.testAabbAabb(this, o);
    }

    public boolean intersectsSweptAABB(FloatAABBR other, float vX, float vY, float vZ) {
        return Intersectionf.testMovingAabbAabb(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ(), vX, vY, vZ, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    public boolean intersectsSweptAABB(FloatAABBR other, Float3R velocity) {
        return intersectsSweptAABB(other, velocity.x(), velocity.y(), velocity.z());
    }

    public boolean intersectsSphere(FloatSphereR sph) {
        return Intersectionf.testAabbSphere(this, sph);
    }

    public boolean intersectsPlane(FloatPlaneR plane) {
        return Intersectionf.testAabbPlane(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), plane.a(), plane.b(), plane.c(), plane.d());
    }

    public boolean intersectsRay(FloatRayR r) {
        return Intersectionf.testRayAabb(r, this);
    }

    public boolean intersectRay(FloatRayR r, @Mutated Float2 dest) {
        return Intersectionf.intersectRayAabb(r, this, dest);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.minZ;
        dest[offset + 3] = this.maxX;
        dest[offset + 4] = this.maxY;
        dest[offset + 5] = this.maxZ;
        return dest;
    }
    public @Mutated FloatAABB load(float[] src, int offset) {
        this.minX = src[offset];
        this.minY = src[offset + 1];
        this.minZ = src[offset + 2];
        this.maxX = src[offset + 3];
        this.maxY = src[offset + 4];
        this.maxZ = src[offset + 5];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public FloatAABB load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatAABB loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatAABB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public FloatAABB load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatAABB loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatAABB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatAABB r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public FloatAABB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatAABB loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.minZ;
        dest[offset + 3] = this.maxX;
        dest[offset + 4] = this.maxY;
        dest[offset + 5] = this.maxZ;
        return dest;
    }
    public @Mutated FloatAABB load(double[] src, int offset) {
        this.minX = (float) src[offset];
        this.minY = (float) src[offset + 1];
        this.minZ = (float) src[offset + 2];
        this.maxX = (float) src[offset + 3];
        this.maxY = (float) src[offset + 4];
        this.maxZ = (float) src[offset + 5];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public FloatAABB load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatAABB loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatAABB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public FloatAABB loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatAABB loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatAABB loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatAABB r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public FloatAABB storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatAABB loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
}
