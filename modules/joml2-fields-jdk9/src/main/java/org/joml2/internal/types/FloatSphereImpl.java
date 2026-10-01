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
 * Generated implementation of {@link FloatSphere} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatSphereImpl implements FloatSphere {
    public float x;
    public float y;
    public float z;
    public float r;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatSphereBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatSphereBbOpsUnsafe()
            : new FloatSphereBbOpsApi();
        static final FloatSphereRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatSphereRawOpsUnsafe()
            : new FloatSphereRawOpsApi();
    }

    public FloatSphereImpl() {}

    public FloatSphereImpl(float x, float y, float z, float r) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
    }

    public FloatSphereImpl(FloatSphereR src) {
        FloatSphereImpl srcImpl = (FloatSphereImpl) src;
        this.x = srcImpl.x;
        this.y = srcImpl.y;
        this.z = srcImpl.z;
        this.r = srcImpl.r;
    }

    /**
     * Set this sphere to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the sphere to copy
     * @return this
     */
    @Mutated public FloatSphere set(FloatSphereR v) {
        FloatSphereImpl vImpl = (FloatSphereImpl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        float vZ = vImpl.z;
        float vR = vImpl.r;
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.r = vR;
        return this;
    }

    /**
     * Set this sphere to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vY the {@code y} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vZ the {@code z} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vR the {@code r} component of the sphere {@code (vX, vY, vZ, vR)}
     * @return this
     */
    @Mutated public FloatSphere set(float vX, float vY, float vZ, float vR) {
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.r = vR;
        return this;
    }

    /**
     * Set the center of this sphere to {@code c} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere setCenter(Float3R c, @Mutated FloatSphere dest) {
        Float3Impl cImpl = (Float3Impl) c;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        FloatSphereImpl d = (FloatSphereImpl) dest;
        d.x = cX;
        d.y = cY;
        d.z = cZ;
        d.r = this.r;
        return d;
    }

    /**
     * Set the center of this sphere to {@code c} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setCenter(Float3R c, @Mutated DoubleSphere dest) {
        Float3Impl cImpl = (Float3Impl) c;
        float cX = cImpl.x;
        float cY = cImpl.y;
        float cZ = cImpl.z;
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        d.x = cX;
        d.y = cY;
        d.z = cZ;
        d.r = this.r;
        return d;
    }

    /**
     * Set the center of this sphere to ({@code cX}, {@code cY}, {@code cZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere setCenter(float cX, float cY, float cZ, @Mutated FloatSphere dest) {
        FloatSphereImpl d = (FloatSphereImpl) dest;
        d.x = cX;
        d.y = cY;
        d.z = cZ;
        d.r = this.r;
        return d;
    }

    /**
     * Set the center of this sphere to ({@code cX}, {@code cY}, {@code cZ}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setCenter(float cX, float cY, float cZ, @Mutated DoubleSphere dest) {
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        d.x = cX;
        d.y = cY;
        d.z = cZ;
        d.r = this.r;
        return d;
    }

    /**
     * Set the radius of this sphere to {@code radius} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param radius the radius
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere setRadius(float radius, @Mutated FloatSphere dest) {
        FloatSphereImpl d = (FloatSphereImpl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.r = radius;
        return d;
    }

    /**
     * Set the radius of this sphere to {@code radius} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param radius the radius
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setRadius(float radius, @Mutated DoubleSphere dest) {
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.r = radius;
        return d;
    }

    /**
     * Convert this sphere to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere toDouble(@Mutated DoubleSphere dest) {
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.r = this.r;
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s10a2025b_c0_fma(FloatSphereImpl _dst, float _r7, float _r9, float _r2, float _r10, float _r3, float _r11, float _r12, float _r8, float _r4, float _r5, float _r13, float _r6, float _r0, float _r1, float _r14, float _r15, float _t9, float _t10, float _t11) {
        _dst.x = java.lang.Math.fma(_r7, _r9, java.lang.Math.fma(_r2, _r10, java.lang.Math.fma(_r3, _r11, _r12)));
        _dst.y = java.lang.Math.fma(_r8, _r9, java.lang.Math.fma(_r4, _r10, java.lang.Math.fma(_r5, _r11, _r13)));
        _dst.z = java.lang.Math.fma(_r6, _r9, java.lang.Math.fma(_r0, _r10, java.lang.Math.fma(_r1, _r11, _r14)));
        _dst.r = _r15 * (float) java.lang.Math.sqrt(Math.max(Math.max(java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r4, _r4, java.lang.Math.fma(_r0, _r0, _t9 + _t10))), java.lang.Math.fma(_r3, _r3, java.lang.Math.fma(_r5, _r5, java.lang.Math.fma(_r1, _r1, _t9 + _t11)))), java.lang.Math.fma(_r7, _r7, java.lang.Math.fma(_r8, _r8, java.lang.Math.fma(_r6, _r6, _t10 + _t11)))));
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s10a2025b_c0_mulAdd(FloatSphereImpl _dst, float _r7, float _r9, float _r2, float _r10, float _r3, float _r11, float _r12, float _r8, float _r4, float _r5, float _r13, float _r6, float _r0, float _r1, float _r14, float _r15, float _t9, float _t10, float _t11) {
        _dst.x = ((_r7) * (_r9) + (((_r2) * (_r10) + (((_r3) * (_r11) + (_r12))))));
        _dst.y = ((_r8) * (_r9) + (((_r4) * (_r10) + (((_r5) * (_r11) + (_r13))))));
        _dst.z = ((_r6) * (_r9) + (((_r0) * (_r10) + (((_r1) * (_r11) + (_r14))))));
        _dst.r = _r15 * (float) java.lang.Math.sqrt(Math.max(Math.max(((_r2) * (_r2) + (((_r4) * (_r4) + (((_r0) * (_r0) + (_t9 + _t10)))))), ((_r3) * (_r3) + (((_r5) * (_r5) + (((_r1) * (_r1) + (_t9 + _t11))))))), ((_r7) * (_r7) + (((_r8) * (_r8) + (((_r6) * (_r6) + (_t10 + _t11))))))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s10a2025b_tail_fma(FloatSphereImpl _dst, float _r1, float _r6, float _r3, float _r7, float _r5, float _r8, float _r9, float _r2, float _r10, float _r11, float _r12, float _r4, float _r13, float _r0, float _r14, float _r15, float _t9, float _t10) {
        transform_s10a2025b_c0_fma(_dst, _r7, _r9, _r2, _r10, _r3, _r11, _r12, _r8, _r4, _r5, _r13, _r6, _r0, _r1, _r14, _r15, _t9, _t10, java.lang.Math.abs(java.lang.Math.fma(_r1, _r6, java.lang.Math.fma(_r3, _r7, _r5 * _r8))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s10a2025b_tail_mulAdd(FloatSphereImpl _dst, float _r1, float _r6, float _r3, float _r7, float _r5, float _r8, float _r9, float _r2, float _r10, float _r11, float _r12, float _r4, float _r13, float _r0, float _r14, float _r15, float _t9, float _t10) {
        transform_s10a2025b_c0_mulAdd(_dst, _r7, _r9, _r2, _r10, _r3, _r11, _r12, _r8, _r4, _r5, _r13, _r6, _r0, _r1, _r14, _r15, _t9, _t10, java.lang.Math.abs(((_r1) * (_r6) + (((_r3) * (_r7) + (_r5 * _r8))))));
    }

    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere, and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact when the matrix maps the coordinate axes to perpendicular
     * directions (a rotation applied after an axis scale, {@code M = R * S}), and at most about
     * 1.17 times too large for a scale applied after the rotation or a shear. (The largest column
     * length would be no bound once the scale is applied after the rotation.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere transform(Float3x4R m, @Mutated FloatSphere dest) {
        Float3x4Impl mImpl = (Float3x4Impl) m;
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        FloatSphereImpl d = (FloatSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s10a2025b_tail_fma(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5))), java.lang.Math.abs(java.lang.Math.fma(_r0, _r6, java.lang.Math.fma(_r2, _r7, _r4 * _r8))));
        return d;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatSphere transform_mulAdd(Float3x4R m, @Mutated FloatSphere dest) {
        Float3x4Impl mImpl = (Float3x4Impl) m;
        FloatSphereImpl d = (FloatSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s10a2025b_tail_mulAdd(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))))), java.lang.Math.abs(((_r0) * (_r6) + (((_r2) * (_r7) + (_r4 * _r8))))));
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s76d9a782_c0_fma(DoubleSphereImpl _dst, float _r7, float _r9, float _r2, float _r10, float _r3, float _r11, float _r12, float _r8, float _r4, float _r5, float _r13, float _r6, float _r0, float _r1, float _r14, float _r15, float _t9, float _t10, float _t11) {
        _dst.x = java.lang.Math.fma(_r7, _r9, java.lang.Math.fma(_r2, _r10, java.lang.Math.fma(_r3, _r11, _r12)));
        _dst.y = java.lang.Math.fma(_r8, _r9, java.lang.Math.fma(_r4, _r10, java.lang.Math.fma(_r5, _r11, _r13)));
        _dst.z = java.lang.Math.fma(_r6, _r9, java.lang.Math.fma(_r0, _r10, java.lang.Math.fma(_r1, _r11, _r14)));
        _dst.r = _r15 * (float) java.lang.Math.sqrt(Math.max(Math.max(java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r4, _r4, java.lang.Math.fma(_r0, _r0, _t9 + _t10))), java.lang.Math.fma(_r3, _r3, java.lang.Math.fma(_r5, _r5, java.lang.Math.fma(_r1, _r1, _t9 + _t11)))), java.lang.Math.fma(_r7, _r7, java.lang.Math.fma(_r8, _r8, java.lang.Math.fma(_r6, _r6, _t10 + _t11)))));
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s76d9a782_c0_mulAdd(DoubleSphereImpl _dst, float _r7, float _r9, float _r2, float _r10, float _r3, float _r11, float _r12, float _r8, float _r4, float _r5, float _r13, float _r6, float _r0, float _r1, float _r14, float _r15, float _t9, float _t10, float _t11) {
        _dst.x = ((_r7) * (_r9) + (((_r2) * (_r10) + (((_r3) * (_r11) + (_r12))))));
        _dst.y = ((_r8) * (_r9) + (((_r4) * (_r10) + (((_r5) * (_r11) + (_r13))))));
        _dst.z = ((_r6) * (_r9) + (((_r0) * (_r10) + (((_r1) * (_r11) + (_r14))))));
        _dst.r = _r15 * (float) java.lang.Math.sqrt(Math.max(Math.max(((_r2) * (_r2) + (((_r4) * (_r4) + (((_r0) * (_r0) + (_t9 + _t10)))))), ((_r3) * (_r3) + (((_r5) * (_r5) + (((_r1) * (_r1) + (_t9 + _t11))))))), ((_r7) * (_r7) + (((_r8) * (_r8) + (((_r6) * (_r6) + (_t10 + _t11))))))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s76d9a782_tail_fma(DoubleSphereImpl _dst, float _r1, float _r6, float _r3, float _r7, float _r5, float _r8, float _r9, float _r2, float _r10, float _r11, float _r12, float _r4, float _r13, float _r0, float _r14, float _r15, float _t9, float _t10) {
        transform_s76d9a782_c0_fma(_dst, _r7, _r9, _r2, _r10, _r3, _r11, _r12, _r8, _r4, _r5, _r13, _r6, _r0, _r1, _r14, _r15, _t9, _t10, java.lang.Math.abs(java.lang.Math.fma(_r1, _r6, java.lang.Math.fma(_r3, _r7, _r5 * _r8))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s76d9a782_tail_mulAdd(DoubleSphereImpl _dst, float _r1, float _r6, float _r3, float _r7, float _r5, float _r8, float _r9, float _r2, float _r10, float _r11, float _r12, float _r4, float _r13, float _r0, float _r14, float _r15, float _t9, float _t10) {
        transform_s76d9a782_c0_mulAdd(_dst, _r7, _r9, _r2, _r10, _r3, _r11, _r12, _r8, _r4, _r5, _r13, _r6, _r0, _r1, _r14, _r15, _t9, _t10, java.lang.Math.abs(((_r1) * (_r6) + (((_r3) * (_r7) + (_r5 * _r8))))));
    }

    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere, and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact when the matrix maps the coordinate axes to perpendicular
     * directions (a rotation applied after an axis scale, {@code M = R * S}), and at most about
     * 1.17 times too large for a scale applied after the rotation or a shear. (The largest column
     * length would be no bound once the scale is applied after the rotation.)
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
    public DoubleSphere transform(Float3x4R m, @Mutated DoubleSphere dest) {
        Float3x4Impl mImpl = (Float3x4Impl) m;
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s76d9a782_tail_fma(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5))), java.lang.Math.abs(java.lang.Math.fma(_r0, _r6, java.lang.Math.fma(_r2, _r7, _r4 * _r8))));
        return d;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleSphere transform_mulAdd(Float3x4R m, @Mutated DoubleSphere dest) {
        Float3x4Impl mImpl = (Float3x4Impl) m;
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s76d9a782_tail_mulAdd(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))))), java.lang.Math.abs(((_r0) * (_r6) + (((_r2) * (_r7) + (_r4 * _r8))))));
        return d;
    }

    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere, and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact when the matrix maps the coordinate axes to perpendicular
     * directions (a rotation applied after an axis scale, {@code M = R * S}), and at most about
     * 1.17 times too large for a scale applied after the rotation or a shear. (The largest column
     * length would be no bound once the scale is applied after the rotation.)
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
    public FloatSphere transform(Float4x4R m, @Mutated FloatSphere dest) {
        Float4x4Impl mImpl = (Float4x4Impl) m;
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        FloatSphereImpl d = (FloatSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s10a2025b_tail_fma(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5))), java.lang.Math.abs(java.lang.Math.fma(_r0, _r6, java.lang.Math.fma(_r2, _r7, _r4 * _r8))));
        return d;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatSphere transform_mulAdd(Float4x4R m, @Mutated FloatSphere dest) {
        Float4x4Impl mImpl = (Float4x4Impl) m;
        FloatSphereImpl d = (FloatSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s10a2025b_tail_mulAdd(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))))), java.lang.Math.abs(((_r0) * (_r6) + (((_r2) * (_r7) + (_r4 * _r8))))));
        return d;
    }

    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere, and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact when the matrix maps the coordinate axes to perpendicular
     * directions (a rotation applied after an axis scale, {@code M = R * S}), and at most about
     * 1.17 times too large for a scale applied after the rotation or a shear. (The largest column
     * length would be no bound once the scale is applied after the rotation.)
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
    public DoubleSphere transform(Float4x4R m, @Mutated DoubleSphere dest) {
        Float4x4Impl mImpl = (Float4x4Impl) m;
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s76d9a782_tail_fma(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5))), java.lang.Math.abs(java.lang.Math.fma(_r0, _r6, java.lang.Math.fma(_r2, _r7, _r4 * _r8))));
        return d;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleSphere transform_mulAdd(Float4x4R m, @Mutated DoubleSphere dest) {
        Float4x4Impl mImpl = (Float4x4Impl) m;
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        float _r0 = mImpl.m20;
        float _r1 = mImpl.m21;
        float _r2 = mImpl.m00;
        float _r3 = mImpl.m01;
        float _r4 = mImpl.m10;
        float _r5 = mImpl.m11;
        float _r6 = mImpl.m22;
        float _r7 = mImpl.m02;
        float _r8 = mImpl.m12;
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = mImpl.m03;
        float _r13 = mImpl.m13;
        float _r14 = mImpl.m23;
        float _r15 = this.r;
        transform_s76d9a782_tail_mulAdd(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, java.lang.Math.abs(((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))))), java.lang.Math.abs(((_r0) * (_r6) + (((_r2) * (_r7) + (_r4 * _r8))))));
        return d;
    }

    /**
     * Translate this sphere by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere translate(Float3R delta, @Mutated FloatSphere dest) {
        Float3Impl deltaImpl = (Float3Impl) delta;
        float deltaX = deltaImpl.x;
        float deltaY = deltaImpl.y;
        float deltaZ = deltaImpl.z;
        FloatSphereImpl d = (FloatSphereImpl) dest;
        d.x = deltaX + this.x;
        d.y = deltaY + this.y;
        d.z = deltaZ + this.z;
        d.r = this.r;
        return d;
    }

    /**
     * Translate this sphere by {@code delta} and store the result in {@code dest}.
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
    public DoubleSphere translate(Float3R delta, @Mutated DoubleSphere dest) {
        Float3Impl deltaImpl = (Float3Impl) delta;
        float deltaX = deltaImpl.x;
        float deltaY = deltaImpl.y;
        float deltaZ = deltaImpl.z;
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        d.x = deltaX + this.x;
        d.y = deltaY + this.y;
        d.z = deltaZ + this.z;
        d.r = this.r;
        return d;
    }

    /**
     * Translate this sphere by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere translate(float deltaX, float deltaY, float deltaZ, @Mutated FloatSphere dest) {
        FloatSphereImpl d = (FloatSphereImpl) dest;
        d.x = deltaX + this.x;
        d.y = deltaY + this.y;
        d.z = deltaZ + this.z;
        d.r = this.r;
        return d;
    }

    /**
     * Translate this sphere by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and store the
     * result in {@code dest}.
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
    public DoubleSphere translate(float deltaX, float deltaY, float deltaZ, @Mutated DoubleSphere dest) {
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        d.x = deltaX + this.x;
        d.y = deltaY + this.y;
        d.z = deltaZ + this.z;
        d.r = this.r;
        return d;
    }

    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        Float3Impl pImpl = (Float3Impl) p;
        return closestPointToPoint(pImpl.x, pImpl.y, pImpl.z, dest);
    }

    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Float3R p, @Mutated Double3 dest) {
        Float3Impl pImpl = (Float3Impl) p;
        float pX = pImpl.x;
        float pY = pImpl.y;
        float pZ = pImpl.z;
        if (!org.joml2.internal.FmaSwitch.ON) return closestPointToPoint_mulAdd(pX, pY, pZ, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        float _t6 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
        if (_t6 <= this.r * this.r) {
            d.x = pX;
            d.y = pY;
            d.z = pZ;
        } else {
            d.x = java.lang.Math.fma(_t1, _t8, this.x);
            d.y = java.lang.Math.fma(_t2, _t8, this.y);
            d.z = java.lang.Math.fma(_t0, _t8, this.z);
        }
        return d;
    }

    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Float3Impl d = (Float3Impl) dest;
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t6 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
            float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                d.x = pX;
                d.y = pY;
                d.z = pZ;
            } else {
                d.x = java.lang.Math.fma(_t1, _t8, this.x);
                d.y = java.lang.Math.fma(_t2, _t8, this.y);
                d.z = java.lang.Math.fma(_t0, _t8, this.z);
            }
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t6 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
            float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                d.x = pX;
                d.y = pY;
                d.z = pZ;
            } else {
                d.x = ((_t1) * (_t8) + (this.x));
                d.y = ((_t2) * (_t8) + (this.y));
                d.z = ((_t0) * (_t8) + (this.z));
            }
            return d;
        }
    }

    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return closestPointToPoint_mulAdd(pX, pY, pZ, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        float _t6 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
        if (_t6 <= this.r * this.r) {
            d.x = pX;
            d.y = pY;
            d.z = pZ;
        } else {
            d.x = java.lang.Math.fma(_t1, _t8, this.x);
            d.y = java.lang.Math.fma(_t2, _t8, this.y);
            d.z = java.lang.Math.fma(_t0, _t8, this.z);
        }
        return d;
    }

    /** {@code closestPointToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 closestPointToPoint_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        float _t6 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
        if (_t6 <= this.r * this.r) {
            d.x = pX;
            d.y = pY;
            d.z = pZ;
        } else {
            d.x = ((_t1) * (_t8) + (this.x));
            d.y = ((_t2) * (_t8) + (this.y));
            d.z = ((_t0) * (_t8) + (this.z));
        }
        return d;
    }

    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public float distanceSquaredToAABB(FloatAABBR aabb) {
        FloatAABBImpl aabbImpl = (FloatAABBImpl) aabb;
        float minX = aabbImpl.minX;
        float minY = aabbImpl.minY;
        float minZ = aabbImpl.minZ;
        float maxX = aabbImpl.maxX;
        float maxY = aabbImpl.maxY;
        float maxZ = aabbImpl.maxZ;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
            return _t14 * _t14;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18};
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public float distanceSquaredToAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
            return _t14 * _t14;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; {@code min}
     * must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public float distanceSquaredToAABB(Float3R min, Float3R max) {
        Float3Impl minImpl = (Float3Impl) min;
        Float3Impl maxImpl = (Float3Impl) max;
        float minX = minImpl.x;
        float minY = minImpl.y;
        float minZ = minImpl.z;
        float maxX = maxImpl.x;
        float maxY = maxImpl.y;
        float maxZ = maxImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
            return _t14 * _t14;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this sphere and the given point, i.e. the square of the
     *        distance from the point to the center minus the radius, clamped at zero; zero for a
     *        point inside or on the sphere
     */
    public float distanceSquaredToPoint(Float3R p) {
        Float3Impl pImpl = (Float3Impl) p;
        float pX = pImpl.x;
        float pY = pImpl.y;
        float pZ = pImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
            return _t8 * _t8;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
            return _t8 * _t8;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this sphere and the given point, i.e. the square of the
     *        distance from the point to the center minus the radius, clamped at zero; zero for a
     *        point inside or on the sphere
     */
    public float distanceSquaredToPoint(float pX, float pY, float pZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
            return _t8 * _t8;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
            return _t8 * _t8;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the sphere to measure the distance to
     * @return the squared distance between this sphere and the given sphere, i.e. the square of the
     *        distance between the centers minus both radii, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceSquaredToSphere(FloatSphereR other) {
        FloatSphereImpl otherImpl = (FloatSphereImpl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherR = otherImpl.r;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
            return _t9 * _t9;
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
            return _t9 * _t9;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param otherX the {@code x} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherY the {@code y} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherZ the {@code z} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherR the {@code r} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @return the squared distance between this sphere and the given sphere, i.e. the square of the
     *        distance between the centers minus both radii, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceSquaredToSphere(float otherX, float otherY, float otherZ, float otherR) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
            return _t9 * _t9;
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
            return _t9 * _t9;
        }
    }

    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceToAABB(FloatAABBR aabb) {
        FloatAABBImpl aabbImpl = (FloatAABBImpl) aabb;
        float minX = aabbImpl.minX;
        float minY = aabbImpl.minY;
        float minZ = aabbImpl.minZ;
        float maxX = aabbImpl.maxX;
        float maxY = aabbImpl.maxY;
        float maxZ = aabbImpl.maxZ;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18};
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceToAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; {@code min}
     * must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceToAABB(Float3R min, Float3R max) {
        Float3Impl minImpl = (Float3Impl) min;
        Float3Impl maxImpl = (Float3Impl) max;
        float minX = minImpl.x;
        float minY = minImpl.y;
        float minZ = minImpl.z;
        float maxX = maxImpl.x;
        float maxY = maxImpl.y;
        float maxZ = maxImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public float distanceToPlane(FloatPlaneR plane) {
        FloatPlaneImpl planeImpl = (FloatPlaneImpl) plane;
        float planeA = planeImpl.a;
        float planeB = planeImpl.b;
        float planeC = planeImpl.c;
        float planeD = planeImpl.d;
        if (org.joml2.internal.FmaSwitch.ON) {
            return Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
        }
    }

    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
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
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public float distanceToPlane(float planeA, float planeB, float planeC, float planeD) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
        }
    }

    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public float distanceToPlane(Float4R plane) {
        Float4Impl planeImpl = (Float4Impl) plane;
        float planeA = planeImpl.x;
        float planeB = planeImpl.y;
        float planeC = planeImpl.z;
        float planeD = planeImpl.w;
        if (org.joml2.internal.FmaSwitch.ON) {
            return Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
        }
    }

    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to measure the distance to
     * @return the distance between this sphere and the given point, i.e. the distance from the
     *        point to the center minus the radius, clamped at zero; zero for a point inside or on
     *        the sphere
     */
    public float distanceToPoint(Float3R p) {
        Float3Impl pImpl = (Float3Impl) p;
        float pX = pImpl.x;
        float pY = pImpl.y;
        float pZ = pImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this sphere and the given point, i.e. the distance from the
     *        point to the center minus the radius, clamped at zero; zero for a point inside or on
     *        the sphere
     */
    public float distanceToPoint(float pX, float pY, float pZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the sphere to measure the distance to
     * @return the distance between this sphere and the given sphere, i.e. the distance between the
     *        centers minus both radii, clamped at zero; zero when they overlap or touch
     */
    public float distanceToSphere(FloatSphereR other) {
        FloatSphereImpl otherImpl = (FloatSphereImpl) other;
        float otherX = otherImpl.x;
        float otherY = otherImpl.y;
        float otherZ = otherImpl.z;
        float otherR = otherImpl.r;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
        }
    }

    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param otherX the {@code x} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherY the {@code y} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherZ the {@code z} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherR the {@code r} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @return the distance between this sphere and the given sphere, i.e. the distance between the
     *        centers minus both radii, clamped at zero; zero when they overlap or touch
     */
    public float distanceToSphere(float otherX, float otherY, float otherZ, float otherR) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
        }
    }

    /**
     * Get the center of this sphere and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getCenter(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        return d;
    }

    /**
     * Get the center of this sphere and store the result in {@code dest}.
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
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        return d;
    }

    /**
     * Determine whether this sphere is valid, i.e. its radius is not negative.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this sphere is valid, i.e. its radius is not negative, {@code false}
     *        otherwise
     */
    public boolean isValid() {
        return this.r >= 0.0f;
    }

    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to measure the distance to
     * @return the signed distance between the given point and the surface of this sphere, i.e. the
     *        distance from the point to the center minus the radius: positive outside, zero on the
     *        surface and negative inside
     */
    public float signedDistanceToPoint(Float3R p) {
        Float3Impl pImpl = (Float3Impl) p;
        float pX = pImpl.x;
        float pY = pImpl.y;
        float pZ = pImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r;
        }
    }

    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the signed distance between the given point and the surface of this sphere, i.e. the
     *        distance from the point to the center minus the radius: positive outside, zero on the
     *        surface and negative inside
     */
    public float signedDistanceToPoint(float pX, float pY, float pZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r;
        }
    }

    public float x() { return this.x; }
    public float y() { return this.y; }
    public float z() { return this.z; }
    public float r() { return this.r; }

    @Override public String toString() {
        return "FloatSphere(" + this.x + ", " + this.y + ", " + this.z + ", " + this.r + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatSphereImpl)) return false;
        FloatSphereImpl o = (FloatSphereImpl) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(o.x)
            && Float.floatToIntBits(y) == Float.floatToIntBits(o.y)
            && Float.floatToIntBits(z) == Float.floatToIntBits(o.z)
            && Float.floatToIntBits(r) == Float.floatToIntBits(o.r);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(x);
        h = 31 * h + Float.floatToIntBits(y);
        h = 31 * h + Float.floatToIntBits(z);
        h = 31 * h + Float.floatToIntBits(r);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(x)
            && Float.isFinite(y)
            && Float.isFinite(z)
            && Float.isFinite(r);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(x)
            || Float.isNaN(y)
            || Float.isNaN(z)
            || Float.isNaN(r);
    }

    @Override public boolean equalsEpsilon(FloatSphereR other, float epsilon) {
        FloatSphereImpl otherImpl = (FloatSphereImpl) other;
        return java.lang.Math.abs(x - otherImpl.x) <= epsilon
            && java.lang.Math.abs(y - otherImpl.y) <= epsilon
            && java.lang.Math.abs(z - otherImpl.z) <= epsilon
            && java.lang.Math.abs(r - otherImpl.r) <= epsilon;
    }

    public boolean containsPoint(float pX, float pY, float pZ) {
        return Intersectionf.testPointSphere(pX, pY, pZ, this.x, this.y, this.z, this.r * this.r);
    }

    public boolean containsPoint(Float3R p) {
        return Intersectionf.testPointSphere(p, this);
    }

    public boolean intersectsSphere(FloatSphereR o) {
        FloatSphereImpl oImpl = (FloatSphereImpl) o;
        return Intersectionf.testSphereSphere(this.x, this.y, this.z, this.r * this.r, oImpl.x, oImpl.y, oImpl.z, oImpl.r * oImpl.r);
    }

    public boolean intersectsAABB(FloatAABBR aabb) {
        return Intersectionf.testAabbSphere(aabb, this);
    }

    public boolean intersectsPlane(FloatPlaneR plane) {
        FloatPlaneImpl planeImpl = (FloatPlaneImpl) plane;
        return Intersectionf.testPlaneSphere(planeImpl.a, planeImpl.b, planeImpl.c, planeImpl.d, this.x, this.y, this.z, this.r);
    }

    public boolean intersectsRay(FloatRayR ray) {
        return Intersectionf.testRaySphere(ray, this);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.r;
        return dest;
    }

    @Mutated public FloatSphere load(float[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.r = src[offset + 3];
        return this;
    }

    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public FloatSphere load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatSphere loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatSphere loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public FloatSphere load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatSphere loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatSphere loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public FloatSphere storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public FloatSphere loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.r;
        return dest;
    }

    @Mutated public FloatSphere load(double[] src, int offset) {
        this.x = (float) src[offset];
        this.y = (float) src[offset + 1];
        this.z = (float) src[offset + 2];
        this.r = (float) src[offset + 3];
        return this;
    }

    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public FloatSphere load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatSphere loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatSphere loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public FloatSphere loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }

    @Mutated public FloatSphere loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public FloatSphere storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    @Mutated public FloatSphere loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
}
