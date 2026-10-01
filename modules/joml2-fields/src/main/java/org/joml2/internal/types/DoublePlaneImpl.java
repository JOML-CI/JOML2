// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoublePlane} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoublePlaneImpl implements DoublePlane {

    public double a;
    public double b;
    public double c;
    public double d;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoublePlaneSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoublePlaneSegOpsUnsafe()
                        : new DoublePlaneSegOpsMS();
        static final DoublePlaneBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoublePlaneBbOpsUnsafe()
                        : new DoublePlaneBbOpsApi();
        static final DoublePlaneRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoublePlaneRawOpsUnsafe()
                        : new DoublePlaneRawOpsApi();
    }

    public DoublePlaneImpl() {
    }

    public DoublePlaneImpl(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public DoublePlaneImpl(DoublePlaneR src) {
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
    public @Mutated DoublePlane set(DoublePlaneR v) {
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
    @Mutated public DoublePlane set(double vA, double vB, double vC, double vD) {
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
    public DoublePlane setNormal(Double3R n, @Mutated DoublePlane dest) {
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
    public DoublePlane setNormal(double nX, double nY, double nZ, @Mutated DoublePlane dest) {
        DoublePlaneImpl d = (DoublePlaneImpl) dest;
        d.a = nX;
        d.b = nY;
        d.c = nZ;
        d.d = this.d;
        return d;
    }


    /**
     * Convert this plane to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatPlane toFloat(@Mutated FloatPlane dest) {
        FloatPlaneImpl d = (FloatPlaneImpl) dest;
        d.a = (float) (this.a);
        d.b = (float) (this.b);
        d.c = (float) (this.c);
        d.d = (float) (this.d);
        return d;
    }


    /**
     * Normalize this plane, scaling {@code (a, b, c, d)} so that the normal {@code (a, b, c)} has
     * unit length and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of this plane must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoublePlane normalize(@Mutated DoublePlane dest) {
        DoublePlaneImpl d = (DoublePlaneImpl) dest;
        double _t3 = (1.0 / Math.sqrt(Math.fma(this.c, this.c, Math.fma(this.a, this.a, this.b * this.b))));
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of this plane must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the (unsigned) distance between this plane and the given point. The plane's normal
     *        need not be of unit length: the result is divided by that normal's length
     */
    public double distanceToPoint(Double3R p) {
        return distanceToPoint(p.x(), p.y(), p.z());
    }


    /**
     * Compute the (unsigned) distance between this plane and the given point. The plane's normal
     * need not be of unit length: the result is divided by that normal's length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of this plane must be non-zero.
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
    public double distanceToPoint(double pX, double pY, double pZ) {
        return (1.0 / Math.sqrt(Math.fma(this.c, this.c, Math.fma(this.a, this.a, this.b * this.b)))) * Math.abs(Math.fma(pX, this.a, Math.fma(pY, this.b, Math.fma(pZ, this.c, this.d))));
    }


    /**
     * Get the normal of this plane and store the result in {@code dest}.
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

    public double a() { return this.a; }
    public double b() { return this.b; }
    public double c() { return this.c; }
    public double d() { return this.d; }

    @Override public String toString() {
        return "DoublePlane(" + a() + ", " + b() + ", " + c() + ", " + d() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoublePlaneImpl)) return false;
        DoublePlaneImpl o = (DoublePlaneImpl) obj;
        return Double.doubleToLongBits(a) == Double.doubleToLongBits(o.a)
            && Double.doubleToLongBits(b) == Double.doubleToLongBits(o.b)
            && Double.doubleToLongBits(c) == Double.doubleToLongBits(o.c)
            && Double.doubleToLongBits(d) == Double.doubleToLongBits(o.d);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(a) ^ (Double.doubleToLongBits(a) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(b) ^ (Double.doubleToLongBits(b) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(c) ^ (Double.doubleToLongBits(c) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(d) ^ (Double.doubleToLongBits(d) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(a)
            && Double.isFinite(b)
            && Double.isFinite(c)
            && Double.isFinite(d);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(a)
            || Double.isNaN(b)
            || Double.isNaN(c)
            || Double.isNaN(d);
    }

    @Override public boolean equalsEpsilon(DoublePlaneR other, double epsilon) {
        return Math.abs(a - other.a()) <= epsilon
            && Math.abs(b - other.b()) <= epsilon
            && Math.abs(c - other.c()) <= epsilon
            && Math.abs(d - other.d()) <= epsilon;
    }

    public double signedDistance(double pX, double pY, double pZ) {
        return Intersectiond.distancePointPlane(pX, pY, pZ, a(), b(), c(), d());
    }

    public double signedDistance(Double3R p) {
        return signedDistance(p.x(), p.y(), p.z());
    }

    public Double3 projectPoint(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double a = a(), b = b(), c = c(), d = d();
        double invLenSq = 1d / (a * a + b * b + c * c);
        double t = (a * pX + b * pY + c * pZ + d) * invLenSq;
        return dest.set(pX - t * a, pY - t * b, pZ - t * c);
    }

    public Double3 projectPoint(Double3R p, @Mutated Double3 dest) {
        return projectPoint(p.x(), p.y(), p.z(), dest);
    }

    public boolean containsPoint(double pX, double pY, double pZ, double epsilon) {
        return Math.abs(signedDistance(pX, pY, pZ)) <= epsilon;
    }

    public boolean containsPoint(Double3R p, double epsilon) {
        return containsPoint(p.x(), p.y(), p.z(), epsilon);
    }

    public boolean intersectsSphere(DoubleSphereR sph) {
        return Intersectiond.testPlaneSphere(a(), b(), c(), d(), sph.x(), sph.y(), sph.z(), sph.r());
    }

    public boolean intersectsAABB(DoubleAABBR box) {
        return Intersectiond.testAabbPlane(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ(), a(), b(), c(), d());
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.a;
        dest[offset + 1] = this.b;
        dest[offset + 2] = this.c;
        dest[offset + 3] = this.d;
        return dest;
    }
    public @Mutated DoublePlane load(double[] src, int offset) {
        this.a = src[offset + 0];
        this.b = src[offset + 1];
        this.c = src[offset + 2];
        this.d = src[offset + 3];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoublePlane loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoublePlane loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoublePlane storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoublePlane loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public DoublePlane load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.a;
        dest[offset + 1] = (float) this.b;
        dest[offset + 2] = (float) this.c;
        dest[offset + 3] = (float) this.d;
        return dest;
    }
    public @Mutated DoublePlane load(float[] src, int offset) {
        this.a = src[offset + 0];
        this.b = src[offset + 1];
        this.c = src[offset + 2];
        this.d = src[offset + 3];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoublePlane loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public DoublePlane loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoublePlane storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoublePlane loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    public DoublePlane loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

}
