// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoublePlane} backed by a {@code double[]} array, with Vector
 * API SIMD kernels where profitable.
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
    @Mutated public DoublePlane set(DoublePlaneR v) {
        double[] dd = this.data;
        double[] vData = ((DoublePlaneImpl) v).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, vData, 0);
        _col0.intoArray(dd, 0);
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
        double[] sd = this.data;
        double[] nData = ((Double3Impl) n).data;
        double[] dd = ((DoublePlaneImpl) dest).data;
        dd[0] = nData[0];
        dd[1] = nData[1];
        dd[2] = nData[2];
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
        double[] sd = this.data;
        double[] dd = ((DoublePlaneImpl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, (1.0 / Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]))))));
        _col0.intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        return (1.0 / Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])))) * Math.abs(Math.fma(pX, sd[0], Math.fma(pY, sd[1], Math.fma(pZ, sd[2], sd[3]))));
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
        return Math.abs(data[0] - other.a()) <= epsilon
            && Math.abs(data[1] - other.b()) <= epsilon
            && Math.abs(data[2] - other.c()) <= epsilon
            && Math.abs(data[3] - other.d()) <= epsilon;
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
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }
    public @Mutated DoublePlane load(double[] src, int offset) {
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        } else if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) {
            return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, baseOff, ByteOrder.nativeOrder());
        }
        return buf;
    }
    @Mutated public DoublePlane loadAbsolute(int index, DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            DoubleVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
        } else if (buf.order() != ByteOrder.nativeOrder()) {
            return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, baseOff, ByteOrder.nativeOrder()).intoArray(d, 0);
        }
        return this;
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, index, ByteOrder.nativeOrder());
        return buf;
    }
    public DoublePlane loadAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder()) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, index, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }
    public DoublePlane storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoublePlane loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        double[] d = this.data;
        MemorySegment seg = dest;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, offset, ByteOrder.nativeOrder());
        return dest;
    }
    public DoublePlane load(long offset, MemorySegment src) {
        double[] d = this.data;
        MemorySegment seg = src;
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        return dest;
    }
    public @Mutated DoublePlane load(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
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

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;

}
