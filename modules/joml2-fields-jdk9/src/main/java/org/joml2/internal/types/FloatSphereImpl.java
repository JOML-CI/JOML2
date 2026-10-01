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
        static final FloatSphereBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatSphereBbOpsUnsafe()
                        : new FloatSphereBbOpsApi();
        static final FloatSphereRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatSphereRawOpsUnsafe()
                        : new FloatSphereRawOpsApi();
    }

    public FloatSphereImpl() {
    }

    public FloatSphereImpl(float x, float y, float z, float r) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
    }

    public FloatSphereImpl(FloatSphereR src) {
        this.x = src.x();
        this.y = src.y();
        this.z = src.z();
        this.r = src.r();
    }


    /**
     * Set this sphere to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the sphere to copy
     * @return this
     */
    public @Mutated FloatSphere set(FloatSphereR v) {
        return set(v.x(), v.y(), v.z(), v.r());
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
        return setCenter(c.x(), c.y(), c.z(), dest);
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
        return setCenter(c.x(), c.y(), c.z(), dest);
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
    private void transform_s10a2025b_c0(FloatSphereImpl _dst, float _r7, float _r9, float _r2, float _r10, float _r3, float _r11, float _r12, float _r8, float _r4, float _r5, float _r13, float _r6, float _r0, float _r1, float _r14, float _r15, float _t9, float _t10, float _t11) {
        _dst.x = Math.fma(_r7, _r9, Math.fma(_r2, _r10, Math.fma(_r3, _r11, _r12)));
        _dst.y = Math.fma(_r8, _r9, Math.fma(_r4, _r10, Math.fma(_r5, _r11, _r13)));
        _dst.z = Math.fma(_r6, _r9, Math.fma(_r0, _r10, Math.fma(_r1, _r11, _r14)));
        _dst.r = _r15 * (float) Math.sqrt(Math.max(Math.max(Math.fma(_r2, _r2, Math.fma(_r4, _r4, Math.fma(_r0, _r0, _t9 + _t10))), Math.fma(_r3, _r3, Math.fma(_r5, _r5, Math.fma(_r1, _r1, _t9 + _t11)))), Math.fma(_r7, _r7, Math.fma(_r8, _r8, Math.fma(_r6, _r6, _t10 + _t11)))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s10a2025b_tail(FloatSphereImpl _dst, float _r1, float _r6, float _r3, float _r7, float _r5, float _r8, float _r9, float _r2, float _r10, float _r11, float _r12, float _r4, float _r13, float _r0, float _r14, float _r15, float _t9, float _t10) {
        transform_s10a2025b_c0(_dst, _r7, _r9, _r2, _r10, _r3, _r11, _r12, _r8, _r4, _r5, _r13, _r6, _r0, _r1, _r14, _r15, _t9, _t10, Math.abs(Math.fma(_r1, _r6, Math.fma(_r3, _r7, _r5 * _r8))));
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere transform(Float3x4R m, @Mutated FloatSphere dest) {
        FloatSphereImpl d = (FloatSphereImpl) dest;
        float _r0 = m.m20();
        float _r1 = m.m21();
        float _r2 = m.m00();
        float _r3 = m.m01();
        float _r4 = m.m10();
        float _r5 = m.m11();
        float _r6 = m.m22();
        float _r7 = m.m02();
        float _r8 = m.m12();
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = m.m03();
        float _r13 = m.m13();
        float _r14 = m.m23();
        float _r15 = this.r;
        transform_s10a2025b_tail(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, Math.abs(Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5))), Math.abs(Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r4 * _r8))));
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s76d9a782_c0(DoubleSphereImpl _dst, float _r7, float _r9, float _r2, float _r10, float _r3, float _r11, float _r12, float _r8, float _r4, float _r5, float _r13, float _r6, float _r0, float _r1, float _r14, float _r15, float _t9, float _t10, float _t11) {
        _dst.x = Math.fma(_r7, _r9, Math.fma(_r2, _r10, Math.fma(_r3, _r11, _r12)));
        _dst.y = Math.fma(_r8, _r9, Math.fma(_r4, _r10, Math.fma(_r5, _r11, _r13)));
        _dst.z = Math.fma(_r6, _r9, Math.fma(_r0, _r10, Math.fma(_r1, _r11, _r14)));
        _dst.r = _r15 * (float) Math.sqrt(Math.max(Math.max(Math.fma(_r2, _r2, Math.fma(_r4, _r4, Math.fma(_r0, _r0, _t9 + _t10))), Math.fma(_r3, _r3, Math.fma(_r5, _r5, Math.fma(_r1, _r1, _t9 + _t11)))), Math.fma(_r7, _r7, Math.fma(_r8, _r8, Math.fma(_r6, _r6, _t10 + _t11)))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s76d9a782_tail(DoubleSphereImpl _dst, float _r1, float _r6, float _r3, float _r7, float _r5, float _r8, float _r9, float _r2, float _r10, float _r11, float _r12, float _r4, float _r13, float _r0, float _r14, float _r15, float _t9, float _t10) {
        transform_s76d9a782_c0(_dst, _r7, _r9, _r2, _r10, _r3, _r11, _r12, _r8, _r4, _r5, _r13, _r6, _r0, _r1, _r14, _r15, _t9, _t10, Math.abs(Math.fma(_r1, _r6, Math.fma(_r3, _r7, _r5 * _r8))));
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
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
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        float _r0 = m.m20();
        float _r1 = m.m21();
        float _r2 = m.m00();
        float _r3 = m.m01();
        float _r4 = m.m10();
        float _r5 = m.m11();
        float _r6 = m.m22();
        float _r7 = m.m02();
        float _r8 = m.m12();
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = m.m03();
        float _r13 = m.m13();
        float _r14 = m.m23();
        float _r15 = this.r;
        transform_s76d9a782_tail(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, Math.abs(Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5))), Math.abs(Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r4 * _r8))));
        return d;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
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
        FloatSphereImpl d = (FloatSphereImpl) dest;
        float _r0 = m.m20();
        float _r1 = m.m21();
        float _r2 = m.m00();
        float _r3 = m.m01();
        float _r4 = m.m10();
        float _r5 = m.m11();
        float _r6 = m.m22();
        float _r7 = m.m02();
        float _r8 = m.m12();
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = m.m03();
        float _r13 = m.m13();
        float _r14 = m.m23();
        float _r15 = this.r;
        transform_s10a2025b_tail(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, Math.abs(Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5))), Math.abs(Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r4 * _r8))));
        return d;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
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
        DoubleSphereImpl d = (DoubleSphereImpl) dest;
        float _r0 = m.m20();
        float _r1 = m.m21();
        float _r2 = m.m00();
        float _r3 = m.m01();
        float _r4 = m.m10();
        float _r5 = m.m11();
        float _r6 = m.m22();
        float _r7 = m.m02();
        float _r8 = m.m12();
        float _r9 = this.z;
        float _r10 = this.x;
        float _r11 = this.y;
        float _r12 = m.m03();
        float _r13 = m.m13();
        float _r14 = m.m23();
        float _r15 = this.r;
        transform_s76d9a782_tail(d, _r1, _r6, _r3, _r7, _r5, _r8, _r9, _r2, _r10, _r11, _r12, _r4, _r13, _r0, _r14, _r15, Math.abs(Math.fma(_r0, _r1, Math.fma(_r2, _r3, _r4 * _r5))), Math.abs(Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r4 * _r8))));
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
        return translate(delta.x(), delta.y(), delta.z(), dest);
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
        return translate(delta.x(), delta.y(), delta.z(), dest);
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
        return closestPointToPoint(p.x(), p.y(), p.z(), dest);
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
        return closestPointToPoint(p.x(), p.y(), p.z(), dest);
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        float _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = this.r * (1.0f / (float) Math.sqrt(_t6));
        if (_t6 <= this.r * this.r) {
            d.x = pX;
            d.y = pY;
            d.z = pZ;
        } else {
            d.x = Math.fma(_t1, _t8, this.x);
            d.y = Math.fma(_t2, _t8, this.y);
            d.z = Math.fma(_t0, _t8, this.z);
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        float _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = this.r * (1.0f / (float) Math.sqrt(_t6));
        if (_t6 <= this.r * this.r) {
            d.x = pX;
            d.y = pY;
            d.z = pZ;
        } else {
            d.x = Math.fma(_t1, _t8, this.x);
            d.y = Math.fma(_t2, _t8, this.y);
            d.z = Math.fma(_t0, _t8, this.z);
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
        return distanceSquaredToAABB(aabb.minX(), aabb.minY(), aabb.minZ(), aabb.maxX(), aabb.maxY(), aabb.maxZ());
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
        float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
        float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
        float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
        float _t14 = Math.max(0.0f, (float) Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        return _t14 * _t14;
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
        return distanceSquaredToAABB(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
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
        return distanceSquaredToPoint(p.x(), p.y(), p.z());
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
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        float _t8 = Math.max(0.0f, (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
        return _t8 * _t8;
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
        return distanceSquaredToSphere(other.x(), other.y(), other.z(), other.r());
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
        float _t0 = otherZ - this.z;
        float _t1 = otherX - this.x;
        float _t2 = otherY - this.y;
        float _t9 = Math.max(0.0f, (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
        return _t9 * _t9;
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
        return distanceToAABB(aabb.minX(), aabb.minY(), aabb.minZ(), aabb.maxX(), aabb.maxY(), aabb.maxZ());
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
        float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
        float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
        float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
        return Math.max(0.0f, (float) Math.sqrt(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
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
        return distanceToAABB(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
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
        return distanceToPlane(plane.a(), plane.b(), plane.c(), plane.d());
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
        return Math.max(0.0f, Math.fma((1.0f / (float) Math.sqrt(Math.fma(planeC, planeC, Math.fma(planeA, planeA, planeB * planeB)))), Math.abs(Math.fma(planeA, this.x, Math.fma(planeB, this.y, Math.fma(planeC, this.z, planeD)))), -this.r));
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
        return distanceToPlane(plane.x(), plane.y(), plane.z(), plane.w());
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
        return distanceToPoint(p.x(), p.y(), p.z());
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
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        return Math.max(0.0f, (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
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
        return distanceToSphere(other.x(), other.y(), other.z(), other.r());
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
        float _t0 = otherZ - this.z;
        float _t1 = otherX - this.x;
        float _t2 = otherY - this.y;
        return Math.max(0.0f, (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
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
        return signedDistanceToPoint(p.x(), p.y(), p.z());
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
        float _t0 = pZ - this.z;
        float _t1 = pX - this.x;
        float _t2 = pY - this.y;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2))) - this.r;
    }

    public float x() { return this.x; }
    public float y() { return this.y; }
    public float z() { return this.z; }
    public float r() { return this.r; }

    @Override public String toString() {
        return "FloatSphere(" + x() + ", " + y() + ", " + z() + ", " + r() + ")";
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
        return Math.abs(x - other.x()) <= epsilon
            && Math.abs(y - other.y()) <= epsilon
            && Math.abs(z - other.z()) <= epsilon
            && Math.abs(r - other.r()) <= epsilon;
    }

    public boolean containsPoint(float px, float py, float pz) {
        return Intersectionf.testPointSphere(px, py, pz, x(), y(), z(), r() * r());
    }

    public boolean containsPoint(Float3R p) {
        return Intersectionf.testPointSphere(p, this);
    }

    public boolean intersectsSphere(FloatSphereR o) {
        return Intersectionf.testSphereSphere(x(), y(), z(), r() * r(), o.x(), o.y(), o.z(), o.r() * o.r());
    }

    public boolean intersectsAABB(FloatAABBR aabb) {
        return Intersectionf.testAabbSphere(aabb, this);
    }

    public boolean intersectsPlane(FloatPlaneR plane) {
        return Intersectionf.testPlaneSphere(plane.a(), plane.b(), plane.c(), plane.d(), x(), y(), z(), r());
    }

    public boolean intersectsRay(FloatRayR ray) {
        return Intersectionf.testRaySphere(ray, this);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.r;
        return dest;
    }
    public @Mutated FloatSphere load(float[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.r = src[offset + 3];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatSphere loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatSphere loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatSphere storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatSphere loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.r;
        return dest;
    }
    public @Mutated FloatSphere load(double[] src, int offset) {
        this.x = (float) src[offset + 0];
        this.y = (float) src[offset + 1];
        this.z = (float) src[offset + 2];
        this.r = (float) src[offset + 3];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatSphere loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatSphere storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatSphere loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }

}
