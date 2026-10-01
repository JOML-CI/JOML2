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
 * Generated implementation of {@link FloatRay} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatRayImpl implements FloatRay {

    public float oX;
    public float oY;
    public float oZ;
    public float dX;
    public float dY;
    public float dZ;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatRayBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRayBbOpsUnsafe()
                        : new FloatRayBbOpsApi();
        static final FloatRayRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRayRawOpsUnsafe()
                        : new FloatRayRawOpsApi();
    }

    public FloatRayImpl() {
    }

    public FloatRayImpl(float oX, float oY, float oZ, float dX, float dY, float dZ) {
        this.oX = oX;
        this.oY = oY;
        this.oZ = oZ;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    public FloatRayImpl(FloatRayR src) {
        this.oX = src.oX();
        this.oY = src.oY();
        this.oZ = src.oZ();
        this.dX = src.dX();
        this.dY = src.dY();
        this.dZ = src.dZ();
    }


    /**
     * Set this ray to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the ray to copy
     * @return this
     */
    public @Mutated FloatRay set(FloatRayR v) {
        float vOY = v.oY();
        float vOZ = v.oZ();
        float vDX = v.dX();
        float vDY = v.dY();
        float vDZ = v.dZ();
        this.oX = v.oX();
        this.oY = vOY;
        this.oZ = vOZ;
        this.dX = vDX;
        this.dY = vDY;
        this.dZ = vDZ;
        return this;
    }


    /**
     * Set this ray to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vOX the {@code oX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOY the {@code oY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOZ the {@code oZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDX the {@code dX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDY the {@code dY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDZ the {@code dZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @return this
     */
    @Mutated public FloatRay set(float vOX, float vOY, float vOZ, float vDX, float vDY, float vDZ) {
        this.oX = vOX;
        this.oY = vOY;
        this.oZ = vOZ;
        this.dX = vDX;
        this.dY = vDY;
        this.dZ = vDZ;
        return this;
    }


    /**
     * Set the direction of this ray to {@code d} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setDirection(Float3R d, @Mutated FloatRay dest) {
        return setDirection(d.x(), d.y(), d.z(), dest);
    }


    /**
     * Set the direction of this ray to {@code d} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setDirection(Float3R d, @Mutated DoubleRay dest) {
        return setDirection(d.x(), d.y(), d.z(), dest);
    }


    /**
     * Set the direction of this ray to ({@code dX}, {@code dY}, {@code dZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (dX, dY, dZ)} must be non-zero.
     *
     * @param dX the {@code x} component of the vector {@code (dX, dY, dZ)}
     * @param dY the {@code y} component of the vector {@code (dX, dY, dZ)}
     * @param dZ the {@code z} component of the vector {@code (dX, dY, dZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setDirection(float dX, float dY, float dZ, @Mutated FloatRay dest) {
        FloatRayImpl d = (FloatRayImpl) dest;
        d.oX = this.oX;
        d.oY = this.oY;
        d.oZ = this.oZ;
        d.dX = dX;
        d.dY = dY;
        d.dZ = dZ;
        return d;
    }


    /**
     * Set the direction of this ray to ({@code dX}, {@code dY}, {@code dZ}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (dX, dY, dZ)} must be non-zero.
     *
     * @param dX the {@code x} component of the vector {@code (dX, dY, dZ)}
     * @param dY the {@code y} component of the vector {@code (dX, dY, dZ)}
     * @param dZ the {@code z} component of the vector {@code (dX, dY, dZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setDirection(float dX, float dY, float dZ, @Mutated DoubleRay dest) {
        DoubleRayImpl d = (DoubleRayImpl) dest;
        d.oX = this.oX;
        d.oY = this.oY;
        d.oZ = this.oZ;
        d.dX = dX;
        d.dY = dY;
        d.dZ = dZ;
        return d;
    }


    /**
     * Set the origin of this ray to {@code o} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setOrigin(Float3R o, @Mutated FloatRay dest) {
        float oY = o.y();
        float oZ = o.z();
        FloatRayImpl d = (FloatRayImpl) dest;
        d.oX = o.x();
        d.oY = oY;
        d.oZ = oZ;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        return d;
    }


    /**
     * Set the origin of this ray to {@code o} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setOrigin(Float3R o, @Mutated DoubleRay dest) {
        float oY = o.y();
        float oZ = o.z();
        DoubleRayImpl d = (DoubleRayImpl) dest;
        d.oX = o.x();
        d.oY = oY;
        d.oZ = oZ;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        return d;
    }


    /**
     * Set the origin of this ray to ({@code oX}, {@code oY}, {@code oZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oX the {@code x} component of the vector {@code (oX, oY, oZ)}
     * @param oY the {@code y} component of the vector {@code (oX, oY, oZ)}
     * @param oZ the {@code z} component of the vector {@code (oX, oY, oZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setOrigin(float oX, float oY, float oZ, @Mutated FloatRay dest) {
        FloatRayImpl d = (FloatRayImpl) dest;
        d.oX = oX;
        d.oY = oY;
        d.oZ = oZ;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        return d;
    }


    /**
     * Set the origin of this ray to ({@code oX}, {@code oY}, {@code oZ}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oX the {@code x} component of the vector {@code (oX, oY, oZ)}
     * @param oY the {@code y} component of the vector {@code (oX, oY, oZ)}
     * @param oZ the {@code z} component of the vector {@code (oX, oY, oZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setOrigin(float oX, float oY, float oZ, @Mutated DoubleRay dest) {
        DoubleRayImpl d = (DoubleRayImpl) dest;
        d.oX = oX;
        d.oY = oY;
        d.oZ = oZ;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        return d;
    }


    /**
     * Convert this ray to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay toDouble(@Mutated DoubleRay dest) {
        DoubleRayImpl d = (DoubleRayImpl) dest;
        d.oX = this.oX;
        d.oY = this.oY;
        d.oZ = this.oZ;
        d.dX = this.dX;
        d.dY = this.dY;
        d.dZ = this.dZ;
        return d;
    }


    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay transform(Float3x4R m, @Mutated FloatRay dest) {
        FloatRayImpl d = (FloatRayImpl) dest;
        float _rd0 = this.oX;
        float _rd1 = this.oY;
        float _rd2 = this.dX;
        float _rd3 = this.dY;
        d.oX = Math.fma(m.m02(), this.oZ, Math.fma(m.m00(), _rd0, Math.fma(m.m01(), _rd1, m.m03())));
        d.oY = Math.fma(m.m12(), this.oZ, Math.fma(m.m10(), _rd0, Math.fma(m.m11(), _rd1, m.m13())));
        d.oZ = Math.fma(m.m22(), this.oZ, Math.fma(m.m20(), _rd0, Math.fma(m.m21(), _rd1, m.m23())));
        d.dX = Math.fma(m.m02(), this.dZ, Math.fma(m.m00(), _rd2, m.m01() * _rd3));
        d.dY = Math.fma(m.m12(), this.dZ, Math.fma(m.m10(), _rd2, m.m11() * _rd3));
        d.dZ = Math.fma(m.m22(), this.dZ, Math.fma(m.m20(), _rd2, m.m21() * _rd3));
        return d;
    }


    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
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
    public DoubleRay transform(Float3x4R m, @Mutated DoubleRay dest) {
        DoubleRayImpl d = (DoubleRayImpl) dest;
        d.oX = Math.fma(m.m02(), this.oZ, Math.fma(m.m00(), this.oX, Math.fma(m.m01(), this.oY, m.m03())));
        d.oY = Math.fma(m.m12(), this.oZ, Math.fma(m.m10(), this.oX, Math.fma(m.m11(), this.oY, m.m13())));
        d.oZ = Math.fma(m.m22(), this.oZ, Math.fma(m.m20(), this.oX, Math.fma(m.m21(), this.oY, m.m23())));
        d.dX = Math.fma(m.m02(), this.dZ, Math.fma(m.m00(), this.dX, m.m01() * this.dY));
        d.dY = Math.fma(m.m12(), this.dZ, Math.fma(m.m10(), this.dX, m.m11() * this.dY));
        d.dZ = Math.fma(m.m22(), this.dZ, Math.fma(m.m20(), this.dX, m.m21() * this.dY));
        return d;
    }


    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
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
    public FloatRay transform(Float4x4R m, @Mutated FloatRay dest) {
        FloatRayImpl d = (FloatRayImpl) dest;
        float _rd0 = this.oX;
        float _rd1 = this.oY;
        float _rd2 = this.dX;
        float _rd3 = this.dY;
        d.oX = Math.fma(m.m02(), this.oZ, Math.fma(m.m00(), _rd0, Math.fma(m.m01(), _rd1, m.m03())));
        d.oY = Math.fma(m.m12(), this.oZ, Math.fma(m.m10(), _rd0, Math.fma(m.m11(), _rd1, m.m13())));
        d.oZ = Math.fma(m.m22(), this.oZ, Math.fma(m.m20(), _rd0, Math.fma(m.m21(), _rd1, m.m23())));
        d.dX = Math.fma(m.m02(), this.dZ, Math.fma(m.m00(), _rd2, m.m01() * _rd3));
        d.dY = Math.fma(m.m12(), this.dZ, Math.fma(m.m10(), _rd2, m.m11() * _rd3));
        d.dZ = Math.fma(m.m22(), this.dZ, Math.fma(m.m20(), _rd2, m.m21() * _rd3));
        return d;
    }


    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
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
    public DoubleRay transform(Float4x4R m, @Mutated DoubleRay dest) {
        DoubleRayImpl d = (DoubleRayImpl) dest;
        d.oX = Math.fma(m.m02(), this.oZ, Math.fma(m.m00(), this.oX, Math.fma(m.m01(), this.oY, m.m03())));
        d.oY = Math.fma(m.m12(), this.oZ, Math.fma(m.m10(), this.oX, Math.fma(m.m11(), this.oY, m.m13())));
        d.oZ = Math.fma(m.m22(), this.oZ, Math.fma(m.m20(), this.oX, Math.fma(m.m21(), this.oY, m.m23())));
        d.dX = Math.fma(m.m02(), this.dZ, Math.fma(m.m00(), this.dX, m.m01() * this.dY));
        d.dY = Math.fma(m.m12(), this.dZ, Math.fma(m.m10(), this.dX, m.m11() * this.dY));
        d.dZ = Math.fma(m.m22(), this.dZ, Math.fma(m.m20(), this.dX, m.m21() * this.dY));
        return d;
    }


    /**
     * Compute the point on this ray at the parameter value {@code t} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the distance along the ray, as a multiple of the ray direction
     * @param dest will hold the result
     * @return dest
     */
    public Float3 at(float t, @Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = java.lang.Math.fma(t, this.dX, this.oX);
            d.y = java.lang.Math.fma(t, this.dY, this.oY);
            d.z = java.lang.Math.fma(t, this.dZ, this.oZ);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = ((t) * (this.dX) + (this.oX));
            d.y = ((t) * (this.dY) + (this.oY));
            d.z = ((t) * (this.dZ) + (this.oZ));
            return d;
        }
    }


    /**
     * Compute the point on this ray at the parameter value {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the distance along the ray, as a multiple of the ray direction
     * @param dest will hold the result
     * @return dest
     */
    public Double3 at(float t, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(t, this.dX, this.oX);
            d.y = java.lang.Math.fma(t, this.dY, this.oY);
            d.z = java.lang.Math.fma(t, this.dZ, this.oZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((t) * (this.dX) + (this.oX));
            d.y = ((t) * (this.dY) + (this.oY));
            d.z = ((t) * (this.dZ) + (this.oZ));
            return d;
        }
    }


    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            d.x = java.lang.Math.fma(this.dX, _t10, this.oX);
            d.y = java.lang.Math.fma(this.dY, _t10, this.oY);
            d.z = java.lang.Math.fma(this.dZ, _t10, this.oZ);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            d.x = ((this.dX) * (_t10) + (this.oX));
            d.y = ((this.dY) * (_t10) + (this.oY));
            d.z = ((this.dZ) * (_t10) + (this.oZ));
            return d;
        }
    }


    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Float3R p, @Mutated Double3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            d.x = java.lang.Math.fma(this.dX, _t10, this.oX);
            d.y = java.lang.Math.fma(this.dY, _t10, this.oY);
            d.z = java.lang.Math.fma(this.dZ, _t10, this.oZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            d.x = ((this.dX) * (_t10) + (this.oX));
            d.y = ((this.dY) * (_t10) + (this.oY));
            d.z = ((this.dZ) * (_t10) + (this.oZ));
            return d;
        }
    }


    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
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
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            d.x = java.lang.Math.fma(this.dX, _t10, this.oX);
            d.y = java.lang.Math.fma(this.dY, _t10, this.oY);
            d.z = java.lang.Math.fma(this.dZ, _t10, this.oZ);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            d.x = ((this.dX) * (_t10) + (this.oX));
            d.y = ((this.dY) * (_t10) + (this.oY));
            d.z = ((this.dZ) * (_t10) + (this.oZ));
            return d;
        }
    }


    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            d.x = java.lang.Math.fma(this.dX, _t10, this.oX);
            d.y = java.lang.Math.fma(this.dY, _t10, this.oY);
            d.z = java.lang.Math.fma(this.dZ, _t10, this.oZ);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            float _t10 = java.lang.Math.max(0.0f, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            d.x = ((this.dX) * (_t10) + (this.oX));
            d.y = ((this.dY) * (_t10) + (this.oY));
            d.z = ((this.dZ) * (_t10) + (this.oZ));
            return d;
        }
    }


    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this ray and the given point, i.e. the squared distance
     *        from the point to the closest point on the ray (the ray starts at its origin and
     *        extends only along its direction). The direction need not be of unit length but must
     *        not be zero
     */
    public float distanceSquaredToPoint(Float3R p) {
        return distanceSquaredToPoint(p.x(), p.y(), p.z());
    }


    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
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
    public float distanceSquaredToPoint(float pX, float pY, float pZ) {
        if (Math.useFma()) {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = java.lang.Math.max(0.0f, java.lang.Math.fma(this.dZ, _t3, java.lang.Math.fma(this.dX, _t4, this.dY * _t5)) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            float _t14 = java.lang.Math.fma(-this.dZ, _t13, _t3);
            float _t15 = java.lang.Math.fma(-this.dX, _t13, _t4);
            float _t16 = java.lang.Math.fma(-this.dY, _t13, _t5);
            return java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16));
        } else {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = java.lang.Math.max(0.0f, ((this.dZ) * (_t3) + (((this.dX) * (_t4) + (this.dY * _t5)))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            float _t14 = ((-this.dZ) * (_t13) + (_t3));
            float _t15 = ((-this.dX) * (_t13) + (_t4));
            float _t16 = ((-this.dY) * (_t13) + (_t5));
            return ((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))));
        }
    }


    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the distance between this ray and the given point, i.e. the distance from the point
     *        to the closest point on the ray (the ray starts at its origin and extends only along
     *        its direction). The direction need not be of unit length but must not be zero
     */
    public float distanceToPoint(Float3R p) {
        return distanceToPoint(p.x(), p.y(), p.z());
    }


    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
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
    public float distanceToPoint(float pX, float pY, float pZ) {
        if (Math.useFma()) {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = java.lang.Math.max(0.0f, java.lang.Math.fma(this.dZ, _t3, java.lang.Math.fma(this.dX, _t4, this.dY * _t5)) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            float _t14 = java.lang.Math.fma(-this.dZ, _t13, _t3);
            float _t15 = java.lang.Math.fma(-this.dX, _t13, _t4);
            float _t16 = java.lang.Math.fma(-this.dY, _t13, _t5);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        } else {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = java.lang.Math.max(0.0f, ((this.dZ) * (_t3) + (((this.dX) * (_t4) + (this.dY * _t5)))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            float _t14 = ((-this.dZ) * (_t13) + (_t3));
            float _t15 = ((-this.dX) * (_t13) + (_t4));
            float _t16 = ((-this.dY) * (_t13) + (_t5));
            return (float) java.lang.Math.sqrt(((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16)))));
        }
    }


    /**
     * Get the direction of this ray and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getDirection(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.dX;
        d.y = this.dY;
        d.z = this.dZ;
        return d;
    }


    /**
     * Get the direction of this ray and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getDirection(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.dX;
        d.y = this.dY;
        d.z = this.dZ;
        return d;
    }


    /**
     * Get the origin of this ray and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getOrigin(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.oX;
        d.y = this.oY;
        d.z = this.oZ;
        return d;
    }


    /**
     * Get the origin of this ray and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getOrigin(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.oX;
        d.y = this.oY;
        d.z = this.oZ;
        return d;
    }

    public float oX() { return this.oX; }
    public float oY() { return this.oY; }
    public float oZ() { return this.oZ; }
    public float dX() { return this.dX; }
    public float dY() { return this.dY; }
    public float dZ() { return this.dZ; }

    @Override public String toString() {
        return "FloatRay(" + oX() + ", " + oY() + ", " + oZ() + ", " + dX() + ", " + dY() + ", " + dZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRayImpl)) return false;
        FloatRayImpl o = (FloatRayImpl) obj;
        return Float.floatToIntBits(oX) == Float.floatToIntBits(o.oX)
            && Float.floatToIntBits(oY) == Float.floatToIntBits(o.oY)
            && Float.floatToIntBits(oZ) == Float.floatToIntBits(o.oZ)
            && Float.floatToIntBits(dX) == Float.floatToIntBits(o.dX)
            && Float.floatToIntBits(dY) == Float.floatToIntBits(o.dY)
            && Float.floatToIntBits(dZ) == Float.floatToIntBits(o.dZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(oX);
        h = 31 * h + Float.floatToIntBits(oY);
        h = 31 * h + Float.floatToIntBits(oZ);
        h = 31 * h + Float.floatToIntBits(dX);
        h = 31 * h + Float.floatToIntBits(dY);
        h = 31 * h + Float.floatToIntBits(dZ);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(oX)
            && Float.isFinite(oY)
            && Float.isFinite(oZ)
            && Float.isFinite(dX)
            && Float.isFinite(dY)
            && Float.isFinite(dZ);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(oX)
            || Float.isNaN(oY)
            || Float.isNaN(oZ)
            || Float.isNaN(dX)
            || Float.isNaN(dY)
            || Float.isNaN(dZ);
    }

    @Override public boolean equalsEpsilon(FloatRayR other, float epsilon) {
        return java.lang.Math.abs(oX - other.oX()) <= epsilon
            && java.lang.Math.abs(oY - other.oY()) <= epsilon
            && java.lang.Math.abs(oZ - other.oZ()) <= epsilon
            && java.lang.Math.abs(dX - other.dX()) <= epsilon
            && java.lang.Math.abs(dY - other.dY()) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.oX;
        dest[offset + 1] = this.oY;
        dest[offset + 2] = this.oZ;
        dest[offset + 3] = this.dX;
        dest[offset + 4] = this.dY;
        dest[offset + 5] = this.dZ;
        return dest;
    }
    public @Mutated FloatRay load(float[] src, int offset) {
        this.oX = src[offset];
        this.oY = src[offset + 1];
        this.oZ = src[offset + 2];
        this.dX = src[offset + 3];
        this.dY = src[offset + 4];
        this.dZ = src[offset + 5];
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
    @Mutated public FloatRay load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatRay loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatRay loadRelative(FloatBuffer buf) {
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
    public FloatRay load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatRay loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatRay loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRay r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public FloatRay storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatRay loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.oX;
        dest[offset + 1] = this.oY;
        dest[offset + 2] = this.oZ;
        dest[offset + 3] = this.dX;
        dest[offset + 4] = this.dY;
        dest[offset + 5] = this.dZ;
        return dest;
    }
    public @Mutated FloatRay load(double[] src, int offset) {
        this.oX = (float) src[offset];
        this.oY = (float) src[offset + 1];
        this.oZ = (float) src[offset + 2];
        this.dX = (float) src[offset + 3];
        this.dY = (float) src[offset + 4];
        this.dZ = (float) src[offset + 5];
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
    @Mutated public FloatRay load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatRay loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatRay loadRelative(DoubleBuffer buf) {
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
    public FloatRay loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatRay loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatRay loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRay r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public FloatRay storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatRay loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
}
