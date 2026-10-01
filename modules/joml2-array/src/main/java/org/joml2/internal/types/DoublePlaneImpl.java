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
 * Generated implementation of {@link DoublePlane} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoublePlaneImpl implements DoublePlane {

    public double[] data;

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
        data = new double[4];
    }

    public DoublePlaneImpl(double a, double b, double c, double d) {
        double[] dd = this.data = new double[4];
        dd[0] = a;
        dd[1] = b;
        dd[2] = c;
        dd[3] = d;
    }

    public DoublePlaneImpl(DoublePlaneR src) {
        double[] dd = this.data = new double[4];
        dd[0] = src.a();
        dd[1] = src.b();
        dd[2] = src.c();
        dd[3] = src.d();
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
        double vB = v.b();
        double vC = v.c();
        double vD = v.d();
        double[] dd = this.data;
        dd[0] = v.a();
        dd[1] = vB;
        dd[2] = vC;
        dd[3] = vD;
        return this;
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
        double[] dd = this.data;
        dd[0] = vA;
        dd[1] = vB;
        dd[2] = vC;
        dd[3] = vD;
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
        double nY = n.y();
        double nZ = n.z();
        double[] sd = this.data;
        double[] dd = ((DoublePlaneImpl) dest).data;
        dd[0] = n.x();
        dd[1] = nY;
        dd[2] = nZ;
        dd[3] = sd[3];
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoublePlaneImpl) dest).data;
        dd[0] = nX;
        dd[1] = nY;
        dd[2] = nZ;
        dd[3] = sd[3];
        return dest;
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
        double[] sd = this.data;
        float[] dd = ((FloatPlaneImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        return dest;
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
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((DoublePlaneImpl) dest).data;
            double _t3 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]))));
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
            dd[3] = sd[3] * _t3;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((DoublePlaneImpl) dest).data;
            double _t3 = (1.0 / java.lang.Math.sqrt(((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1]))))));
            dd[0] = sd[0] * _t3;
            dd[1] = sd[1] * _t3;
            dd[2] = sd[2] * _t3;
            dd[3] = sd[3] * _t3;
            return dest;
        }
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
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])))) * java.lang.Math.abs(java.lang.Math.fma(pX, sd[0], java.lang.Math.fma(pY, sd[1], java.lang.Math.fma(pZ, sd[2], sd[3]))));
        } else {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1])))))) * java.lang.Math.abs(((pX) * (sd[0]) + (((pY) * (sd[1]) + (((pZ) * (sd[2]) + (sd[3])))))));
        }
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
        if (Math.useFma()) {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1])))) * java.lang.Math.abs(java.lang.Math.fma(pX, sd[0], java.lang.Math.fma(pY, sd[1], java.lang.Math.fma(pZ, sd[2], sd[3]))));
        } else {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(((sd[2]) * (sd[2]) + (((sd[0]) * (sd[0]) + (sd[1] * sd[1])))))) * java.lang.Math.abs(((pX) * (sd[0]) + (((pY) * (sd[1]) + (((pZ) * (sd[2]) + (sd[3])))))));
        }
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }

    public double a() { return data[0]; }
    public double b() { return data[1]; }
    public double c() { return data[2]; }
    public double d() { return data[3]; }

    @Override public String toString() {
        return "DoublePlane(" + a() + ", " + b() + ", " + c() + ", " + d() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoublePlaneImpl)) return false;
        DoublePlaneImpl o = (DoublePlaneImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(DoublePlaneR other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.a()) <= epsilon
            && java.lang.Math.abs(data[1] - other.b()) <= epsilon
            && java.lang.Math.abs(data[2] - other.c()) <= epsilon
            && java.lang.Math.abs(data[3] - other.d()) <= epsilon;
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
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        double a = a(), b = b(), c = c(), d = d();
        double invLenSq = 1d / (a * a + b * b + c * c);
        double t = (a * pX + b * pY + c * pZ + d) * invLenSq;
        return dest.set(pX - t * a, pY - t * b, pZ - t * c);
    }

    public boolean containsPoint(double pX, double pY, double pZ, double epsilon) {
        return java.lang.Math.abs(signedDistance(pX, pY, pZ)) <= epsilon;
    }

    public boolean containsPoint(Double3R p, double epsilon) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        return java.lang.Math.abs(signedDistance(pX, pY, pZ)) <= epsilon;
    }

    public boolean intersectsSphere(DoubleSphereR sph) {
        return Intersectiond.testPlaneSphere(a(), b(), c(), d(), sph.x(), sph.y(), sph.z(), sph.r());
    }

    public boolean intersectsAABB(DoubleAABBR box) {
        return Intersectiond.testAabbPlane(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ(), a(), b(), c(), d());
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated DoublePlane load(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
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
    @Mutated public DoublePlane load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoublePlane loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoublePlane loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public DoublePlane load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoublePlane loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoublePlane loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoublePlane r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public DoublePlane storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoublePlane loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public DoublePlane load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public DoublePlane load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        return dest;
    }
    public @Mutated DoublePlane load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
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
    @Mutated public DoublePlane load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoublePlane loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoublePlane loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public DoublePlane loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoublePlane loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoublePlane loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoublePlane r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public DoublePlane storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoublePlane loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    @Mutated public DoublePlane loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }
    public DoublePlane loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }
}
