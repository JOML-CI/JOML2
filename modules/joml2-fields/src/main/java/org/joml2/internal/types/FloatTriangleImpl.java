// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatTriangle} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatTriangleImpl implements FloatTriangle {

    public float v0X;
    public float v0Y;
    public float v0Z;
    public float v1X;
    public float v1Y;
    public float v1Z;
    public float v2X;
    public float v2Y;
    public float v2Z;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatTriangleSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTriangleSegOpsUnsafe()
                        : new FloatTriangleSegOpsMS();
        static final FloatTriangleBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTriangleBbOpsUnsafe()
                        : new FloatTriangleBbOpsApi();
        static final FloatTriangleRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTriangleRawOpsUnsafe()
                        : new FloatTriangleRawOpsApi();
    }

    public FloatTriangleImpl() {
    }

    public FloatTriangleImpl(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) {
        this.v0X = v0X;
        this.v0Y = v0Y;
        this.v0Z = v0Z;
        this.v1X = v1X;
        this.v1Y = v1Y;
        this.v1Z = v1Z;
        this.v2X = v2X;
        this.v2Y = v2Y;
        this.v2Z = v2Z;
    }

    public FloatTriangleImpl(FloatTriangleR src) {
        this.v0X = src.v0X();
        this.v0Y = src.v0Y();
        this.v0Z = src.v0Z();
        this.v1X = src.v1X();
        this.v1Y = src.v1Y();
        this.v1Z = src.v1Z();
        this.v2X = src.v2X();
        this.v2Y = src.v2Y();
        this.v2Z = src.v2Z();
    }


    /**
     * Set this triangle to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the triangle to copy
     * @return this
     */
    public @Mutated FloatTriangle set(FloatTriangleR v) {
        float vV0Y = v.v0Y();
        float vV0Z = v.v0Z();
        float vV1X = v.v1X();
        float vV1Y = v.v1Y();
        float vV1Z = v.v1Z();
        float vV2X = v.v2X();
        float vV2Y = v.v2Y();
        float vV2Z = v.v2Z();
        this.v0X = v.v0X();
        this.v0Y = vV0Y;
        this.v0Z = vV0Z;
        this.v1X = vV1X;
        this.v1Y = vV1Y;
        this.v1Z = vV1Z;
        this.v2X = vV2X;
        this.v2Y = vV2Y;
        this.v2Z = vV2Z;
        return this;
    }


    /**
     * Set this triangle to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vV0X the {@code v0X} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV0Y the {@code v0Y} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV0Z the {@code v0Z} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV1X the {@code v1X} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV1Y the {@code v1Y} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV1Z the {@code v1Z} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV2X the {@code v2X} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV2Y the {@code v2Y} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV2Z the {@code v2Z} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @return this
     */
    @Mutated public FloatTriangle set(float vV0X, float vV0Y, float vV0Z, float vV1X, float vV1Y, float vV1Z, float vV2X, float vV2Y, float vV2Z) {
        this.v0X = vV0X;
        this.v0Y = vV0Y;
        this.v0Z = vV0Z;
        this.v1X = vV1X;
        this.v1Y = vV1Y;
        this.v1Z = vV1Z;
        this.v2X = vV2X;
        this.v2Y = vV2Y;
        this.v2Z = vV2Z;
        return this;
    }


    /**
     * Convert this triangle to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTriangle toDouble(@Mutated DoubleTriangle dest) {
        DoubleTriangleImpl d = (DoubleTriangleImpl) dest;
        d.v0X = this.v0X;
        d.v0Y = this.v0Y;
        d.v0Z = this.v0Z;
        d.v1X = this.v1X;
        d.v1Y = this.v1Y;
        d.v1Z = this.v1Z;
        d.v2X = this.v2X;
        d.v2Y = this.v2Y;
        d.v2Z = this.v2Z;
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s62bbd380_c0_fma(FloatTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v0X = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6)));
        _dst.v0Y = java.lang.Math.fma(_r7, _r1, java.lang.Math.fma(_r8, _r3, java.lang.Math.fma(_r9, _r5, _r10)));
        _dst.v0Z = java.lang.Math.fma(_r11, _r1, java.lang.Math.fma(_r12, _r3, java.lang.Math.fma(_r13, _r5, _r14)));
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s62bbd380_c0_mulAdd(FloatTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v0X = ((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6))))));
        _dst.v0Y = ((_r7) * (_r1) + (((_r8) * (_r3) + (((_r9) * (_r5) + (_r10))))));
        _dst.v0Z = ((_r11) * (_r1) + (((_r12) * (_r3) + (((_r13) * (_r5) + (_r14))))));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s62bbd380_c1_fma(FloatTriangleImpl _dst, float _r0, float _r15, float _r2, float _r16, float _r4, float _r17, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v1X = java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, java.lang.Math.fma(_r4, _r17, _r6)));
        _dst.v1Y = java.lang.Math.fma(_r7, _r15, java.lang.Math.fma(_r8, _r16, java.lang.Math.fma(_r9, _r17, _r10)));
        _dst.v1Z = java.lang.Math.fma(_r11, _r15, java.lang.Math.fma(_r12, _r16, java.lang.Math.fma(_r13, _r17, _r14)));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s62bbd380_c1_mulAdd(FloatTriangleImpl _dst, float _r0, float _r15, float _r2, float _r16, float _r4, float _r17, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v1X = ((_r0) * (_r15) + (((_r2) * (_r16) + (((_r4) * (_r17) + (_r6))))));
        _dst.v1Y = ((_r7) * (_r15) + (((_r8) * (_r16) + (((_r9) * (_r17) + (_r10))))));
        _dst.v1Z = ((_r11) * (_r15) + (((_r12) * (_r16) + (((_r13) * (_r17) + (_r14))))));
    }

    /**
     * Private store group 2 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s62bbd380_c2_fma(FloatTriangleImpl _dst, float _r0, float _r18, float _r2, float _r19, float _r4, float _r20, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v2X = java.lang.Math.fma(_r0, _r18, java.lang.Math.fma(_r2, _r19, java.lang.Math.fma(_r4, _r20, _r6)));
        _dst.v2Y = java.lang.Math.fma(_r7, _r18, java.lang.Math.fma(_r8, _r19, java.lang.Math.fma(_r9, _r20, _r10)));
        _dst.v2Z = java.lang.Math.fma(_r11, _r18, java.lang.Math.fma(_r12, _r19, java.lang.Math.fma(_r13, _r20, _r14)));
    }

    /**
     * Private store group 2 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s62bbd380_c2_mulAdd(FloatTriangleImpl _dst, float _r0, float _r18, float _r2, float _r19, float _r4, float _r20, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v2X = ((_r0) * (_r18) + (((_r2) * (_r19) + (((_r4) * (_r20) + (_r6))))));
        _dst.v2Y = ((_r7) * (_r18) + (((_r8) * (_r19) + (((_r9) * (_r20) + (_r10))))));
        _dst.v2Z = ((_r11) * (_r18) + (((_r12) * (_r19) + (((_r13) * (_r20) + (_r14))))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s62bbd380_tail_fma(FloatTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.v2Y;
        transform_s62bbd380_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s62bbd380_c1_fma(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s62bbd380_c2_fma(_dst, _r0, _r18, _r2, _r19, _r4, _r20, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s62bbd380_tail_mulAdd(FloatTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.v2Y;
        transform_s62bbd380_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s62bbd380_c1_mulAdd(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s62bbd380_c2_mulAdd(_dst, _r0, _r18, _r2, _r19, _r4, _r20, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatTriangle transform(Float3x4R m, @Mutated FloatTriangle dest) {
        FloatTriangleImpl d = (FloatTriangleImpl) dest;
        float _r0 = m.m02();
        float _r1 = this.v0Z;
        float _r2 = m.m00();
        float _r3 = this.v0X;
        float _r4 = m.m01();
        float _r5 = this.v0Y;
        float _r6 = m.m03();
        float _r7 = m.m12();
        float _r8 = m.m10();
        float _r9 = m.m11();
        float _r10 = m.m13();
        float _r11 = m.m22();
        float _r12 = m.m20();
        float _r13 = m.m21();
        float _r14 = m.m23();
        float _r15 = this.v1Z;
        float _r16 = this.v1X;
        float _r17 = this.v1Y;
        float _r18 = this.v2Z;
        float _r19 = this.v2X;
        if (Math.useFma()) transform_s62bbd380_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else transform_s62bbd380_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s199ecae7_c0_fma(DoubleTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v0X = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6)));
        _dst.v0Y = java.lang.Math.fma(_r7, _r1, java.lang.Math.fma(_r8, _r3, java.lang.Math.fma(_r9, _r5, _r10)));
        _dst.v0Z = java.lang.Math.fma(_r11, _r1, java.lang.Math.fma(_r12, _r3, java.lang.Math.fma(_r13, _r5, _r14)));
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s199ecae7_c0_mulAdd(DoubleTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v0X = ((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6))))));
        _dst.v0Y = ((_r7) * (_r1) + (((_r8) * (_r3) + (((_r9) * (_r5) + (_r10))))));
        _dst.v0Z = ((_r11) * (_r1) + (((_r12) * (_r3) + (((_r13) * (_r5) + (_r14))))));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s199ecae7_c1_fma(DoubleTriangleImpl _dst, float _r0, float _r15, float _r2, float _r16, float _r4, float _r17, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v1X = java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, java.lang.Math.fma(_r4, _r17, _r6)));
        _dst.v1Y = java.lang.Math.fma(_r7, _r15, java.lang.Math.fma(_r8, _r16, java.lang.Math.fma(_r9, _r17, _r10)));
        _dst.v1Z = java.lang.Math.fma(_r11, _r15, java.lang.Math.fma(_r12, _r16, java.lang.Math.fma(_r13, _r17, _r14)));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s199ecae7_c1_mulAdd(DoubleTriangleImpl _dst, float _r0, float _r15, float _r2, float _r16, float _r4, float _r17, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v1X = ((_r0) * (_r15) + (((_r2) * (_r16) + (((_r4) * (_r17) + (_r6))))));
        _dst.v1Y = ((_r7) * (_r15) + (((_r8) * (_r16) + (((_r9) * (_r17) + (_r10))))));
        _dst.v1Z = ((_r11) * (_r15) + (((_r12) * (_r16) + (((_r13) * (_r17) + (_r14))))));
    }

    /**
     * Private store group 2 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s199ecae7_c2_fma(DoubleTriangleImpl _dst, float _r0, float _r18, float _r2, float _r19, float _r4, float _r20, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v2X = java.lang.Math.fma(_r0, _r18, java.lang.Math.fma(_r2, _r19, java.lang.Math.fma(_r4, _r20, _r6)));
        _dst.v2Y = java.lang.Math.fma(_r7, _r18, java.lang.Math.fma(_r8, _r19, java.lang.Math.fma(_r9, _r20, _r10)));
        _dst.v2Z = java.lang.Math.fma(_r11, _r18, java.lang.Math.fma(_r12, _r19, java.lang.Math.fma(_r13, _r20, _r14)));
    }

    /**
     * Private store group 2 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s199ecae7_c2_mulAdd(DoubleTriangleImpl _dst, float _r0, float _r18, float _r2, float _r19, float _r4, float _r20, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14) {
        _dst.v2X = ((_r0) * (_r18) + (((_r2) * (_r19) + (((_r4) * (_r20) + (_r6))))));
        _dst.v2Y = ((_r7) * (_r18) + (((_r8) * (_r19) + (((_r9) * (_r20) + (_r10))))));
        _dst.v2Z = ((_r11) * (_r18) + (((_r12) * (_r19) + (((_r13) * (_r20) + (_r14))))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s199ecae7_tail_fma(DoubleTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.v2Y;
        transform_s199ecae7_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s199ecae7_c1_fma(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s199ecae7_c2_fma(_dst, _r0, _r18, _r2, _r19, _r4, _r20, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s199ecae7_tail_mulAdd(DoubleTriangleImpl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.v2Y;
        transform_s199ecae7_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s199ecae7_c1_mulAdd(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s199ecae7_c2_mulAdd(_dst, _r0, _r18, _r2, _r19, _r4, _r20, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTriangle transform(Float3x4R m, @Mutated DoubleTriangle dest) {
        DoubleTriangleImpl d = (DoubleTriangleImpl) dest;
        float _r0 = m.m02();
        float _r1 = this.v0Z;
        float _r2 = m.m00();
        float _r3 = this.v0X;
        float _r4 = m.m01();
        float _r5 = this.v0Y;
        float _r6 = m.m03();
        float _r7 = m.m12();
        float _r8 = m.m10();
        float _r9 = m.m11();
        float _r10 = m.m13();
        float _r11 = m.m22();
        float _r12 = m.m20();
        float _r13 = m.m21();
        float _r14 = m.m23();
        float _r15 = this.v1Z;
        float _r16 = this.v1X;
        float _r17 = this.v1Y;
        float _r18 = this.v2Z;
        float _r19 = this.v2X;
        if (Math.useFma()) transform_s199ecae7_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else transform_s199ecae7_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatTriangle transform(Float4x4R m, @Mutated FloatTriangle dest) {
        FloatTriangleImpl d = (FloatTriangleImpl) dest;
        float _r0 = m.m02();
        float _r1 = this.v0Z;
        float _r2 = m.m00();
        float _r3 = this.v0X;
        float _r4 = m.m01();
        float _r5 = this.v0Y;
        float _r6 = m.m03();
        float _r7 = m.m12();
        float _r8 = m.m10();
        float _r9 = m.m11();
        float _r10 = m.m13();
        float _r11 = m.m22();
        float _r12 = m.m20();
        float _r13 = m.m21();
        float _r14 = m.m23();
        float _r15 = this.v1Z;
        float _r16 = this.v1X;
        float _r17 = this.v1Y;
        float _r18 = this.v2Z;
        float _r19 = this.v2X;
        if (Math.useFma()) transform_s62bbd380_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else transform_s62bbd380_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTriangle transform(Float4x4R m, @Mutated DoubleTriangle dest) {
        DoubleTriangleImpl d = (DoubleTriangleImpl) dest;
        float _r0 = m.m02();
        float _r1 = this.v0Z;
        float _r2 = m.m00();
        float _r3 = this.v0X;
        float _r4 = m.m01();
        float _r5 = this.v0Y;
        float _r6 = m.m03();
        float _r7 = m.m12();
        float _r8 = m.m10();
        float _r9 = m.m11();
        float _r10 = m.m13();
        float _r11 = m.m22();
        float _r12 = m.m20();
        float _r13 = m.m21();
        float _r14 = m.m23();
        float _r15 = this.v1Z;
        float _r16 = this.v1X;
        float _r17 = this.v1Y;
        float _r18 = this.v2Z;
        float _r19 = this.v2X;
        if (Math.useFma()) transform_s199ecae7_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else transform_s199ecae7_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }


    /**
     * Compute the area of this triangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this triangle
     */
    public float area() {
        if (Math.useFma()) {
            float _t0 = this.v1X - this.v0X;
            float _t1 = this.v2Y - this.v0Y;
            float _t2 = this.v1Y - this.v0Y;
            float _t3 = this.v2X - this.v0X;
            float _t4 = this.v2Z - this.v0Z;
            float _t5 = this.v1Z - this.v0Z;
            float _t12 = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            float _t13 = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            float _t14 = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return 0.5f * (float) java.lang.Math.sqrt(java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14)));
        } else {
            float _t0 = this.v1X - this.v0X;
            float _t1 = this.v2Y - this.v0Y;
            float _t2 = this.v1Y - this.v0Y;
            float _t3 = this.v2X - this.v0X;
            float _t4 = this.v2Z - this.v0Z;
            float _t5 = this.v1Z - this.v0Z;
            float _t12 = ((_t0) * (_t1) - (_t2 * _t3));
            float _t13 = ((_t2) * (_t4) - (_t5 * _t1));
            float _t14 = ((_t5) * (_t3) - (_t0 * _t4));
            return 0.5f * (float) java.lang.Math.sqrt(((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14)))));
        }
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param p the point whose barycentric coordinates to compute
     * @param dest will hold the result
     * @return dest
     */
    public Float3 barycentric(Float3R p, @Mutated Float3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param p the point whose barycentric coordinates to compute
     * @param dest will hold the result
     * @return dest
     */
    public Double3 barycentric(Float3R p, @Mutated Double3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 barycentric(float pX, float pY, float pZ, @Mutated Float3 dest) {
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code barycentric} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 barycentric_fma(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float _t0 = pX - this.v0X;
        float _t1 = this.v2Y - this.v0Y;
        float _t2 = pY - this.v0Y;
        float _t3 = this.v2X - this.v0X;
        float _t4 = this.v1X - this.v0X;
        float _t5 = this.v1Y - this.v0Y;
        float _t6 = this.v2Z - this.v0Z;
        float _t7 = pZ - this.v0Z;
        float _t8 = this.v1Z - this.v0Z;
        float _t28 = java.lang.Math.fma(_t4, _t1, -(_t5 * _t3));
        float _t30 = java.lang.Math.fma(_t5, _t6, -(_t8 * _t1));
        float _t32 = java.lang.Math.fma(_t8, _t3, -(_t4 * _t6));
        float _t46 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _ct0 = java.lang.Math.fma(java.lang.Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, java.lang.Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32));
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        return barycentric_s2df94b79_1_fma(pX, pY, pZ, dest, (Float3Impl) dest, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, _ct0);
    }

    /** {@code barycentric} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 barycentric_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float _t0 = pX - this.v0X;
        float _t1 = this.v2Y - this.v0Y;
        float _t2 = pY - this.v0Y;
        float _t3 = this.v2X - this.v0X;
        float _t4 = this.v1X - this.v0X;
        float _t5 = this.v1Y - this.v0Y;
        float _t6 = this.v2Z - this.v0Z;
        float _t7 = pZ - this.v0Z;
        float _t8 = this.v1Z - this.v0Z;
        float _t28 = ((_t4) * (_t1) - (_t5 * _t3));
        float _t30 = ((_t5) * (_t6) - (_t8 * _t1));
        float _t32 = ((_t8) * (_t3) - (_t4 * _t6));
        float _t46 = ((_t28) * (_t28) + (((_t30) * (_t30) + (_t32 * _t32))));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _ct0 = ((((_t0) * (_t1) - (_t2 * _t3))) * (_t28) + (((((_t2) * (_t6) - (_t7 * _t1))) * (_t30) + (((_t7) * (_t3) - (_t0 * _t6)) * _t32))));
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        return barycentric_s2df94b79_1_mulAdd(pX, pY, pZ, dest, (Float3Impl) dest, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, _ct0);
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_s2df94b79_1_fma(float pX, float pY, float pZ, Float3 dest, Float3Impl d, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = java.lang.Math.fma(java.lang.Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, java.lang.Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        d.x = 1.0f - _t48 - _t49;
        d.y = _t48;
        d.z = _t49;
        return d;
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_s2df94b79_1_mulAdd(float pX, float pY, float pZ, Float3 dest, Float3Impl d, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = ((((_t2) * (_t4) - (_t0 * _t5))) * (_t28) + (((((_t0) * (_t8) - (_t7 * _t4))) * (_t32) + (((_t7) * (_t5) - (_t2 * _t8)) * _t30))));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        d.x = 1.0f - _t48 - _t49;
        d.y = _t48;
        d.z = _t49;
        return d;
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 barycentric(float pX, float pY, float pZ, @Mutated Double3 dest) {
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code barycentric} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 barycentric_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float _t0 = pX - this.v0X;
        float _t1 = this.v2Y - this.v0Y;
        float _t2 = pY - this.v0Y;
        float _t3 = this.v2X - this.v0X;
        float _t4 = this.v1X - this.v0X;
        float _t5 = this.v1Y - this.v0Y;
        float _t6 = this.v2Z - this.v0Z;
        float _t7 = pZ - this.v0Z;
        float _t8 = this.v1Z - this.v0Z;
        float _t28 = java.lang.Math.fma(_t4, _t1, -(_t5 * _t3));
        float _t30 = java.lang.Math.fma(_t5, _t6, -(_t8 * _t1));
        float _t32 = java.lang.Math.fma(_t8, _t3, -(_t4 * _t6));
        float _t46 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _ct0 = java.lang.Math.fma(java.lang.Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, java.lang.Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32));
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        return barycentric_s65efdde6_1_fma(pX, pY, pZ, dest, (Double3Impl) dest, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, _ct0);
    }

    /** {@code barycentric} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 barycentric_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float _t0 = pX - this.v0X;
        float _t1 = this.v2Y - this.v0Y;
        float _t2 = pY - this.v0Y;
        float _t3 = this.v2X - this.v0X;
        float _t4 = this.v1X - this.v0X;
        float _t5 = this.v1Y - this.v0Y;
        float _t6 = this.v2Z - this.v0Z;
        float _t7 = pZ - this.v0Z;
        float _t8 = this.v1Z - this.v0Z;
        float _t28 = ((_t4) * (_t1) - (_t5 * _t3));
        float _t30 = ((_t5) * (_t6) - (_t8 * _t1));
        float _t32 = ((_t8) * (_t3) - (_t4 * _t6));
        float _t46 = ((_t28) * (_t28) + (((_t30) * (_t30) + (_t32 * _t32))));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _ct0 = ((((_t0) * (_t1) - (_t2 * _t3))) * (_t28) + (((((_t2) * (_t6) - (_t7 * _t1))) * (_t30) + (((_t7) * (_t3) - (_t0 * _t6)) * _t32))));
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        return barycentric_s65efdde6_1_mulAdd(pX, pY, pZ, dest, (Double3Impl) dest, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, _ct0);
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_s65efdde6_1_fma(float pX, float pY, float pZ, Double3 dest, Double3Impl d, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = java.lang.Math.fma(java.lang.Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, java.lang.Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        d.x = 1.0f - _t48 - _t49;
        d.y = _t48;
        d.z = _t49;
        return d;
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_s65efdde6_1_mulAdd(float pX, float pY, float pZ, Double3 dest, Double3Impl d, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = ((((_t2) * (_t4) - (_t0 * _t5))) * (_t28) + (((((_t0) * (_t8) - (_t7 * _t4))) * (_t32) + (((_t7) * (_t5) - (_t2 * _t8)) * _t30))));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        d.x = 1.0f - _t48 - _t49;
        d.y = _t48;
        d.z = _t49;
        return d;
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Float3 barycentric_degenerate_fma(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float _r0 = this.v0X;
        float _r1 = this.v0Y;
        float _r2 = this.v0Z;
        float _r3 = this.v1X;
        float _r4 = this.v1Y;
        float _r5 = this.v1Z;
        float _r6 = this.v2X;
        float _r7 = this.v2Y;
        float _r8 = this.v2Z;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(_r1))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r4), java.lang.Math.abs(_r5))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r6), java.lang.Math.abs(_r7)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r8), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = _r0 * _t28;
        float _t44 = _r1 * _t28;
        float _t46 = _r2 * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_s7fdf5c50_1_fma((Float3Impl) dest, _r7 * _t28 - _t44, _r3 * _t28 - _t42, _r4 * _t28 - _t44, _r5 * _t28 - _t46, _r6 * _t28 - _t42, _r8 * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Float3 barycentric_degenerate_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float _r0 = this.v0X;
        float _r1 = this.v0Y;
        float _r2 = this.v0Z;
        float _r3 = this.v1X;
        float _r4 = this.v1Y;
        float _r5 = this.v1Z;
        float _r6 = this.v2X;
        float _r7 = this.v2Y;
        float _r8 = this.v2Z;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(_r1))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r4), java.lang.Math.abs(_r5))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r6), java.lang.Math.abs(_r7)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r8), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = _r0 * _t28;
        float _t44 = _r1 * _t28;
        float _t46 = _r2 * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_s7fdf5c50_1_mulAdd((Float3Impl) dest, _r7 * _t28 - _t44, _r3 * _t28 - _t42, _r4 * _t28 - _t44, _r5 * _t28 - _t46, _r6 * _t28 - _t42, _r8 * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_degenerate_s7fdf5c50_1_fma(Float3Impl d, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = java.lang.Math.fma(_t87, _t85, -(_t88 * _t86));
        float _t118 = java.lang.Math.fma(_t88, _t89, -(_t90 * _t85));
        float _t119 = java.lang.Math.fma(_t90, _t86, -(_t87 * _t89));
        float _sp0 = _t78 / _t71 / java.lang.Math.fma(_t117, _t117, java.lang.Math.fma(_t118, _t118, _t119 * _t119));
        float _t131 = java.lang.Math.fma(java.lang.Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, java.lang.Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0;
        float _t132 = java.lang.Math.fma(java.lang.Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, java.lang.Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        d.x = 1.0f - _t131 - _t132;
        d.y = _t131;
        d.z = _t132;
        return d;
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_degenerate_s7fdf5c50_1_mulAdd(Float3Impl d, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = ((_t87) * (_t85) - (_t88 * _t86));
        float _t118 = ((_t88) * (_t89) - (_t90 * _t85));
        float _t119 = ((_t90) * (_t86) - (_t87 * _t89));
        float _sp0 = _t78 / _t71 / ((_t117) * (_t117) + (((_t118) * (_t118) + (_t119 * _t119))));
        float _t131 = ((((_t75) * (_t85) - (_t76 * _t86))) * (_t117) + (((((_t76) * (_t89) - (_t77 * _t85))) * (_t118) + (((_t77) * (_t86) - (_t75 * _t89)) * _t119)))) * _sp0;
        float _t132 = ((((_t76) * (_t87) - (_t75 * _t88))) * (_t117) + (((((_t75) * (_t90) - (_t77 * _t87))) * (_t119) + (((_t77) * (_t88) - (_t76 * _t90)) * _t118)))) * _sp0;
        d.x = 1.0f - _t131 - _t132;
        d.y = _t131;
        d.z = _t132;
        return d;
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float _r0 = this.v0X;
        float _r1 = this.v0Y;
        float _r2 = this.v0Z;
        float _r3 = this.v1X;
        float _r4 = this.v1Y;
        float _r5 = this.v1Z;
        float _r6 = this.v2X;
        float _r7 = this.v2Y;
        float _r8 = this.v2Z;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(_r1))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r4), java.lang.Math.abs(_r5))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r6), java.lang.Math.abs(_r7)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r8), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = _r0 * _t28;
        float _t44 = _r1 * _t28;
        float _t46 = _r2 * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_sf3374b25_1_fma((Double3Impl) dest, _r7 * _t28 - _t44, _r3 * _t28 - _t42, _r4 * _t28 - _t44, _r5 * _t28 - _t46, _r6 * _t28 - _t42, _r8 * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float _r0 = this.v0X;
        float _r1 = this.v0Y;
        float _r2 = this.v0Z;
        float _r3 = this.v1X;
        float _r4 = this.v1Y;
        float _r5 = this.v1Z;
        float _r6 = this.v2X;
        float _r7 = this.v2Y;
        float _r8 = this.v2Z;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(_r1))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r4), java.lang.Math.abs(_r5))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r6), java.lang.Math.abs(_r7)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r8), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = _r0 * _t28;
        float _t44 = _r1 * _t28;
        float _t46 = _r2 * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_sf3374b25_1_mulAdd((Double3Impl) dest, _r7 * _t28 - _t44, _r3 * _t28 - _t42, _r4 * _t28 - _t44, _r5 * _t28 - _t46, _r6 * _t28 - _t42, _r8 * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_sf3374b25_1_fma(Double3Impl d, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = java.lang.Math.fma(_t87, _t85, -(_t88 * _t86));
        float _t118 = java.lang.Math.fma(_t88, _t89, -(_t90 * _t85));
        float _t119 = java.lang.Math.fma(_t90, _t86, -(_t87 * _t89));
        float _sp0 = _t78 / _t71 / java.lang.Math.fma(_t117, _t117, java.lang.Math.fma(_t118, _t118, _t119 * _t119));
        float _t131 = java.lang.Math.fma(java.lang.Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, java.lang.Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0;
        float _t132 = java.lang.Math.fma(java.lang.Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, java.lang.Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        d.x = 1.0f - _t131 - _t132;
        d.y = _t131;
        d.z = _t132;
        return d;
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_sf3374b25_1_mulAdd(Double3Impl d, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = ((_t87) * (_t85) - (_t88 * _t86));
        float _t118 = ((_t88) * (_t89) - (_t90 * _t85));
        float _t119 = ((_t90) * (_t86) - (_t87 * _t89));
        float _sp0 = _t78 / _t71 / ((_t117) * (_t117) + (((_t118) * (_t118) + (_t119 * _t119))));
        float _t131 = ((((_t75) * (_t85) - (_t76 * _t86))) * (_t117) + (((((_t76) * (_t89) - (_t77 * _t85))) * (_t118) + (((_t77) * (_t86) - (_t75 * _t89)) * _t119)))) * _sp0;
        float _t132 = ((((_t76) * (_t87) - (_t75 * _t88))) * (_t117) + (((((_t75) * (_t90) - (_t77 * _t87))) * (_t119) + (((_t77) * (_t88) - (_t76 * _t90)) * _t118)))) * _sp0;
        d.x = 1.0f - _t131 - _t132;
        d.y = _t131;
        d.z = _t132;
        return d;
    }


    /**
     * Get the centroid (the average of the vertices) of this triangle and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getCentroid(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = 0.33333334f * this.v0X + 0.33333334f * this.v1X + 0.33333334f * this.v2X;
        d.y = 0.33333334f * this.v0Y + 0.33333334f * this.v1Y + 0.33333334f * this.v2Y;
        d.z = 0.33333334f * this.v0Z + 0.33333334f * this.v1Z + 0.33333334f * this.v2Z;
        return d;
    }


    /**
     * Get the centroid (the average of the vertices) of this triangle and store the result in
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
    public Double3 getCentroid(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = 0.33333334f * this.v0X + 0.33333334f * this.v1X + 0.33333334f * this.v2X;
        d.y = 0.33333334f * this.v0Y + 0.33333334f * this.v1Y + 0.33333334f * this.v2Y;
        d.z = 0.33333334f * this.v0Z + 0.33333334f * this.v1Z + 0.33333334f * this.v2Z;
        return d;
    }


    /**
     * Get the normal of this triangle, i.e. the cross product {@code (v1 - v0) x (v2 - v0)} (not
     * unit length: its length is twice the triangle's area) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getNormal(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            float _t0 = this.v1Y - this.v0Y;
            float _t1 = this.v2Z - this.v0Z;
            float _t2 = this.v1Z - this.v0Z;
            float _t3 = this.v2Y - this.v0Y;
            float _t4 = this.v2X - this.v0X;
            float _t5 = this.v1X - this.v0X;
            d.x = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            d.y = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            d.z = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            float _t0 = this.v1Y - this.v0Y;
            float _t1 = this.v2Z - this.v0Z;
            float _t2 = this.v1Z - this.v0Z;
            float _t3 = this.v2Y - this.v0Y;
            float _t4 = this.v2X - this.v0X;
            float _t5 = this.v1X - this.v0X;
            d.x = ((_t0) * (_t1) - (_t2 * _t3));
            d.y = ((_t2) * (_t4) - (_t5 * _t1));
            d.z = ((_t5) * (_t3) - (_t0 * _t4));
            return d;
        }
    }


    /**
     * Get the normal of this triangle, i.e. the cross product {@code (v1 - v0) x (v2 - v0)} (not
     * unit length: its length is twice the triangle's area) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getNormal(@Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            float _t0 = this.v1Y - this.v0Y;
            float _t1 = this.v2Z - this.v0Z;
            float _t2 = this.v1Z - this.v0Z;
            float _t3 = this.v2Y - this.v0Y;
            float _t4 = this.v2X - this.v0X;
            float _t5 = this.v1X - this.v0X;
            d.x = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            d.y = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            d.z = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            float _t0 = this.v1Y - this.v0Y;
            float _t1 = this.v2Z - this.v0Z;
            float _t2 = this.v1Z - this.v0Z;
            float _t3 = this.v2Y - this.v0Y;
            float _t4 = this.v2X - this.v0X;
            float _t5 = this.v1X - this.v0X;
            d.x = ((_t0) * (_t1) - (_t2 * _t3));
            d.y = ((_t2) * (_t4) - (_t5 * _t1));
            d.z = ((_t5) * (_t3) - (_t0 * _t4));
            return d;
        }
    }


    /**
     * Get the first vertex of this triangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getV0(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.v0X;
        d.y = this.v0Y;
        d.z = this.v0Z;
        return d;
    }


    /**
     * Get the first vertex of this triangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getV0(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.v0X;
        d.y = this.v0Y;
        d.z = this.v0Z;
        return d;
    }


    /**
     * Get the second vertex of this triangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getV1(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.v1X;
        d.y = this.v1Y;
        d.z = this.v1Z;
        return d;
    }


    /**
     * Get the second vertex of this triangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getV1(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.v1X;
        d.y = this.v1Y;
        d.z = this.v1Z;
        return d;
    }


    /**
     * Get the third vertex of this triangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getV2(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.v2X;
        d.y = this.v2Y;
        d.z = this.v2Z;
        return d;
    }


    /**
     * Get the third vertex of this triangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getV2(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.v2X;
        d.y = this.v2Y;
        d.z = this.v2Z;
        return d;
    }

    public float v0X() { return this.v0X; }
    public float v0Y() { return this.v0Y; }
    public float v0Z() { return this.v0Z; }
    public float v1X() { return this.v1X; }
    public float v1Y() { return this.v1Y; }
    public float v1Z() { return this.v1Z; }
    public float v2X() { return this.v2X; }
    public float v2Y() { return this.v2Y; }
    public float v2Z() { return this.v2Z; }

    @Override public String toString() {
        return "FloatTriangle(" + v0X() + ", " + v0Y() + ", " + v0Z() + ", " + v1X() + ", " + v1Y() + ", " + v1Z() + ", " + v2X() + ", " + v2Y() + ", " + v2Z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatTriangleImpl)) return false;
        FloatTriangleImpl o = (FloatTriangleImpl) obj;
        return Float.floatToIntBits(v0X) == Float.floatToIntBits(o.v0X)
            && Float.floatToIntBits(v0Y) == Float.floatToIntBits(o.v0Y)
            && Float.floatToIntBits(v0Z) == Float.floatToIntBits(o.v0Z)
            && Float.floatToIntBits(v1X) == Float.floatToIntBits(o.v1X)
            && Float.floatToIntBits(v1Y) == Float.floatToIntBits(o.v1Y)
            && Float.floatToIntBits(v1Z) == Float.floatToIntBits(o.v1Z)
            && Float.floatToIntBits(v2X) == Float.floatToIntBits(o.v2X)
            && Float.floatToIntBits(v2Y) == Float.floatToIntBits(o.v2Y)
            && Float.floatToIntBits(v2Z) == Float.floatToIntBits(o.v2Z);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(v0X);
        h = 31 * h + Float.floatToIntBits(v0Y);
        h = 31 * h + Float.floatToIntBits(v0Z);
        h = 31 * h + Float.floatToIntBits(v1X);
        h = 31 * h + Float.floatToIntBits(v1Y);
        h = 31 * h + Float.floatToIntBits(v1Z);
        h = 31 * h + Float.floatToIntBits(v2X);
        h = 31 * h + Float.floatToIntBits(v2Y);
        h = 31 * h + Float.floatToIntBits(v2Z);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(v0X)
            && Float.isFinite(v0Y)
            && Float.isFinite(v0Z)
            && Float.isFinite(v1X)
            && Float.isFinite(v1Y)
            && Float.isFinite(v1Z)
            && Float.isFinite(v2X)
            && Float.isFinite(v2Y)
            && Float.isFinite(v2Z);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(v0X)
            || Float.isNaN(v0Y)
            || Float.isNaN(v0Z)
            || Float.isNaN(v1X)
            || Float.isNaN(v1Y)
            || Float.isNaN(v1Z)
            || Float.isNaN(v2X)
            || Float.isNaN(v2Y)
            || Float.isNaN(v2Z);
    }

    @Override public boolean equalsEpsilon(FloatTriangleR other, float epsilon) {
        return java.lang.Math.abs(v0X - other.v0X()) <= epsilon
            && java.lang.Math.abs(v0Y - other.v0Y()) <= epsilon
            && java.lang.Math.abs(v0Z - other.v0Z()) <= epsilon
            && java.lang.Math.abs(v1X - other.v1X()) <= epsilon
            && java.lang.Math.abs(v1Y - other.v1Y()) <= epsilon
            && java.lang.Math.abs(v1Z - other.v1Z()) <= epsilon
            && java.lang.Math.abs(v2X - other.v2X()) <= epsilon
            && java.lang.Math.abs(v2Y - other.v2Y()) <= epsilon
            && java.lang.Math.abs(v2Z - other.v2Z()) <= epsilon;
    }

    public boolean containsPoint(float pX, float pY, float pZ) {
        return Intersectionf.testPointInTriangle(pX, pY, pZ, v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z());
    }

    public boolean containsPoint(Float3R p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    public boolean intersectsRay(FloatRayR ray, float epsilon) {
        return Intersectionf.testRayTriangle(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    public boolean intersectsRayFront(FloatRayR ray, float epsilon) {
        return Intersectionf.testRayTriangleFront(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    public Float3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Float3 dest) {
        Intersectionf.findClosestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), pX, pY, pZ, dest);
        return dest;
    }

    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        Intersectionf.findClosestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), pX, pY, pZ, dest);
        return dest;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.v0X;
        dest[offset + 1] = this.v0Y;
        dest[offset + 2] = this.v0Z;
        dest[offset + 3] = this.v1X;
        dest[offset + 4] = this.v1Y;
        dest[offset + 5] = this.v1Z;
        dest[offset + 6] = this.v2X;
        dest[offset + 7] = this.v2Y;
        dest[offset + 8] = this.v2Z;
        return dest;
    }
    public @Mutated FloatTriangle load(float[] src, int offset) {
        this.v0X = src[offset];
        this.v0Y = src[offset + 1];
        this.v0Z = src[offset + 2];
        this.v1X = src[offset + 3];
        this.v1Y = src[offset + 4];
        this.v1Z = src[offset + 5];
        this.v2X = src[offset + 6];
        this.v2Y = src[offset + 7];
        this.v2Z = src[offset + 8];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }
    @Mutated public FloatTriangle load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatTriangle loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatTriangle loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }
    public FloatTriangle load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatTriangle loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatTriangle loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return r;
    }
    public FloatTriangle storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatTriangle loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public FloatTriangle load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public FloatTriangle load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.v0X;
        dest[offset + 1] = this.v0Y;
        dest[offset + 2] = this.v0Z;
        dest[offset + 3] = this.v1X;
        dest[offset + 4] = this.v1Y;
        dest[offset + 5] = this.v1Z;
        dest[offset + 6] = this.v2X;
        dest[offset + 7] = this.v2Y;
        dest[offset + 8] = this.v2Z;
        return dest;
    }
    public @Mutated FloatTriangle load(double[] src, int offset) {
        this.v0X = (float) src[offset];
        this.v0Y = (float) src[offset + 1];
        this.v0Z = (float) src[offset + 2];
        this.v1X = (float) src[offset + 3];
        this.v1Y = (float) src[offset + 4];
        this.v1Z = (float) src[offset + 5];
        this.v2X = (float) src[offset + 6];
        this.v2Y = (float) src[offset + 7];
        this.v2Z = (float) src[offset + 8];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }
    @Mutated public FloatTriangle load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatTriangle loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatTriangle loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }
    public FloatTriangle loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatTriangle loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatTriangle loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return r;
    }
    public FloatTriangle storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatTriangle loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public FloatTriangle loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public FloatTriangle loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
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
