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
 * Generated implementation of {@link FloatPlane} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatPlaneImpl implements FloatPlane {

    public float a;
    public float b;
    public float c;
    public float d;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatPlaneBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatPlaneBbOpsUnsafe()
                        : new FloatPlaneBbOpsApi();
        static final FloatPlaneRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatPlaneRawOpsUnsafe()
                        : new FloatPlaneRawOpsApi();
    }

    public FloatPlaneImpl() {
    }

    public FloatPlaneImpl(float a, float b, float c, float d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public FloatPlaneImpl(FloatPlaneR src) {
        this.a = src.a();
        this.b = src.b();
        this.c = src.c();
        this.d = src.d();
    }


    /**
     * Set this plane to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the plane to copy
     * @return this
     */
    public @Mutated FloatPlane set(FloatPlaneR v) {
        return set(v.a(), v.b(), v.c(), v.d());
    }


    /**
     * Set this plane to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vA the {@code a} component of the plane {@code (vA, vB, vC, vD)}
     * @param vB the {@code b} component of the plane {@code (vA, vB, vC, vD)}
     * @param vC the {@code c} component of the plane {@code (vA, vB, vC, vD)}
     * @param vD the {@code d} component of the plane {@code (vA, vB, vC, vD)}
     * @return this
     */
    @Mutated public FloatPlane set(float vA, float vB, float vC, float vD) {
        this.a = vA;
        this.b = vB;
        this.c = vC;
        this.d = vD;
        return this;
    }


    /**
     * Set the normal of this plane to {@code n} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code n} must be non-zero.
     *
     * @param n the new normal
     * @param dest will hold the result
     * @return dest
     */
    public FloatPlane setNormal(Float3R n, @Mutated FloatPlane dest) {
        return setNormal(n.x(), n.y(), n.z(), dest);
    }


    /**
     * Set the normal of this plane to {@code n} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code n} must be non-zero.
     *
     * @param n the new normal
     * @param dest will hold the result
     * @return dest
     */
    public DoublePlane setNormal(Float3R n, @Mutated DoublePlane dest) {
        return setNormal(n.x(), n.y(), n.z(), dest);
    }


    /**
     * Set the normal of this plane to ({@code nX}, {@code nY}, {@code nZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (nX, nY, nZ)} must be non-zero.
     *
     * @param nX the {@code x} component of the vector {@code (nX, nY, nZ)}
     * @param nY the {@code y} component of the vector {@code (nX, nY, nZ)}
     * @param nZ the {@code z} component of the vector {@code (nX, nY, nZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatPlane setNormal(float nX, float nY, float nZ, @Mutated FloatPlane dest) {
        FloatPlaneImpl d = (FloatPlaneImpl) dest;
        d.a = nX;
        d.b = nY;
        d.c = nZ;
        d.d = this.d;
        return d;
    }


    /**
     * Set the normal of this plane to ({@code nX}, {@code nY}, {@code nZ}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (nX, nY, nZ)} must be non-zero.
     *
     * @param nX the {@code x} component of the vector {@code (nX, nY, nZ)}
     * @param nY the {@code y} component of the vector {@code (nX, nY, nZ)}
     * @param nZ the {@code z} component of the vector {@code (nX, nY, nZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoublePlane setNormal(float nX, float nY, float nZ, @Mutated DoublePlane dest) {
        DoublePlaneImpl d = (DoublePlaneImpl) dest;
        d.a = nX;
        d.b = nY;
        d.c = nZ;
        d.d = this.d;
        return d;
    }


    /**
     * Convert this plane to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoublePlane toDouble(@Mutated DoublePlane dest) {
        DoublePlaneImpl d = (DoublePlaneImpl) dest;
        d.a = this.a;
        d.b = this.b;
        d.c = this.c;
        d.d = this.d;
        return d;
    }


    /**
     * Normalize this plane, scaling {@code (a, b, c, d)} so that the normal {@code (a, b, c)} has
     * unit length and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of this plane must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatPlane normalize(@Mutated FloatPlane dest) {
        FloatPlaneImpl d = (FloatPlaneImpl) dest;
        float _t3 = (1.0f / (float) Math.sqrt(Math.fma(this.c, this.c, Math.fma(this.a, this.a, this.b * this.b))));
        d.a = this.a * _t3;
        d.b = this.b * _t3;
        d.c = this.c * _t3;
        d.d = this.d * _t3;
        return d;
    }


    /**
     * Normalize this plane, scaling {@code (a, b, c, d)} so that the normal {@code (a, b, c)} has
     * unit length and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of this plane must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoublePlane normalize(@Mutated DoublePlane dest) {
        DoublePlaneImpl d = (DoublePlaneImpl) dest;
        float _t3 = (1.0f / (float) Math.sqrt(Math.fma(this.c, this.c, Math.fma(this.a, this.a, this.b * this.b))));
        d.a = this.a * _t3;
        d.b = this.b * _t3;
        d.c = this.c * _t3;
        d.d = this.d * _t3;
        return d;
    }


    /**
     * Compute the (unsigned) distance between this plane and the given point. The plane's normal
     * need not be of unit length: the result is divided by that normal's length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of this plane must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the (unsigned) distance between this plane and the given point. The plane's normal
     *        need not be of unit length: the result is divided by that normal's length
     */
    public float distanceToPoint(Float3R p) {
        return distanceToPoint(p.x(), p.y(), p.z());
    }


    /**
     * Compute the (unsigned) distance between this plane and the given point. The plane's normal
     * need not be of unit length: the result is divided by that normal's length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of this plane must be non-zero.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the (unsigned) distance between this plane and the given point. The plane's normal
     *        need not be of unit length: the result is divided by that normal's length
     */
    public float distanceToPoint(float pX, float pY, float pZ) {
        return (1.0f / (float) Math.sqrt(Math.fma(this.c, this.c, Math.fma(this.a, this.a, this.b * this.b)))) * Math.abs(Math.fma(pX, this.a, Math.fma(pY, this.b, Math.fma(pZ, this.c, this.d))));
    }


    /**
     * Get the normal of this plane and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getNormal(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.a;
        d.y = this.b;
        d.z = this.c;
        return d;
    }


    /**
     * Get the normal of this plane and store the result in {@code dest}.
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.a;
        d.y = this.b;
        d.z = this.c;
        return d;
    }

    public float a() { return this.a; }
    public float b() { return this.b; }
    public float c() { return this.c; }
    public float d() { return this.d; }

    @Override public String toString() {
        return "FloatPlane(" + a() + ", " + b() + ", " + c() + ", " + d() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatPlaneImpl)) return false;
        FloatPlaneImpl o = (FloatPlaneImpl) obj;
        return Float.floatToIntBits(a) == Float.floatToIntBits(o.a)
            && Float.floatToIntBits(b) == Float.floatToIntBits(o.b)
            && Float.floatToIntBits(c) == Float.floatToIntBits(o.c)
            && Float.floatToIntBits(d) == Float.floatToIntBits(o.d);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(a);
        h = 31 * h + Float.floatToIntBits(b);
        h = 31 * h + Float.floatToIntBits(c);
        h = 31 * h + Float.floatToIntBits(d);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(a)
            && Float.isFinite(b)
            && Float.isFinite(c)
            && Float.isFinite(d);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(a)
            || Float.isNaN(b)
            || Float.isNaN(c)
            || Float.isNaN(d);
    }

    @Override public boolean equalsEpsilon(FloatPlaneR other, float epsilon) {
        return Math.abs(a - other.a()) <= epsilon
            && Math.abs(b - other.b()) <= epsilon
            && Math.abs(c - other.c()) <= epsilon
            && Math.abs(d - other.d()) <= epsilon;
    }

    public float signedDistance(float pX, float pY, float pZ) {
        return Intersectionf.distancePointPlane(pX, pY, pZ, a(), b(), c(), d());
    }

    public float signedDistance(Float3R p) {
        return signedDistance(p.x(), p.y(), p.z());
    }

    public Float3 projectPoint(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float a = a(), b = b(), c = c(), d = d();
        float invLenSq = 1f / (a * a + b * b + c * c);
        float t = (a * pX + b * pY + c * pZ + d) * invLenSq;
        return dest.set(pX - t * a, pY - t * b, pZ - t * c);
    }

    public Float3 projectPoint(Float3R p, @Mutated Float3 dest) {
        return projectPoint(p.x(), p.y(), p.z(), dest);
    }

    public boolean containsPoint(float pX, float pY, float pZ, float epsilon) {
        return Math.abs(signedDistance(pX, pY, pZ)) <= epsilon;
    }

    public boolean containsPoint(Float3R p, float epsilon) {
        return containsPoint(p.x(), p.y(), p.z(), epsilon);
    }

    public boolean intersectsSphere(FloatSphereR sph) {
        return Intersectionf.testPlaneSphere(a(), b(), c(), d(), sph.x(), sph.y(), sph.z(), sph.r());
    }

    public boolean intersectsAABB(FloatAABBR box) {
        return Intersectionf.testAabbPlane(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ(), a(), b(), c(), d());
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.a;
        dest[offset + 1] = this.b;
        dest[offset + 2] = this.c;
        dest[offset + 3] = this.d;
        return dest;
    }
    public @Mutated FloatPlane load(float[] src, int offset) {
        this.a = src[offset + 0];
        this.b = src[offset + 1];
        this.c = src[offset + 2];
        this.d = src[offset + 3];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatPlane loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatPlane loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatPlane storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatPlane loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.a;
        dest[offset + 1] = this.b;
        dest[offset + 2] = this.c;
        dest[offset + 3] = this.d;
        return dest;
    }
    public @Mutated FloatPlane load(double[] src, int offset) {
        this.a = (float) src[offset + 0];
        this.b = (float) src[offset + 1];
        this.c = (float) src[offset + 2];
        this.d = (float) src[offset + 3];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatPlane loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public FloatPlane loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatPlane storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatPlane loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }

}
