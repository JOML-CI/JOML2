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
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatPlane} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatPlaneImpl implements FloatPlane {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatPlaneSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatPlaneSegOpsUnsafe()
                        : new FloatPlaneSegOpsMS();
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
        data = new float[4];
    }

    public FloatPlaneImpl(float a, float b, float c, float d) {
        float[] dd = this.data = new float[4];
        dd[0] = a;
        dd[1] = b;
        dd[2] = c;
        dd[3] = d;
    }

    public FloatPlaneImpl(FloatPlaneR src) {
        float[] dd = this.data = new float[4];
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
    @Mutated public FloatPlane set(FloatPlaneR v) {
        float[] dd = this.data;
        float[] vData = ((FloatPlaneImpl) v).data;
        FloatVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
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
    @Mutated public FloatPlane set(float vA, float vB, float vC, float vD) {
        float[] dd = this.data;
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
    public FloatPlane setNormal(Float3R n, @Mutated FloatPlane dest) {
        float[] sd = this.data;
        float[] nData = ((Float3Impl) n).data;
        float[] dd = ((FloatPlaneImpl) dest).data;
        dd[0] = nData[0];
        dd[1] = nData[1];
        dd[2] = nData[2];
        dd[3] = sd[3];
        return dest;
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
        float nY = n.y();
        float nZ = n.z();
        float[] sd = this.data;
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
    public FloatPlane setNormal(float nX, float nY, float nZ, @Mutated FloatPlane dest) {
        float[] sd = this.data;
        float[] dd = ((FloatPlaneImpl) dest).data;
        dd[0] = nX;
        dd[1] = nY;
        dd[2] = nZ;
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoublePlaneImpl) dest).data;
        dd[0] = nX;
        dd[1] = nY;
        dd[2] = nZ;
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoublePlaneImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatPlaneImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).mul(FloatVector.broadcast(COL_SPECIES, (1.0f / (float) java.lang.Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])))))).intoArray(dd, 0);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoublePlaneImpl) dest).data;
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1]))));
        dd[0] = sd[0] * _t3;
        dd[1] = sd[1] * _t3;
        dd[2] = sd[2] * _t3;
        dd[3] = sd[3] * _t3;
        return dest;
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
        float[] sd = this.data;
        return (1.0f / (float) java.lang.Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])))) * java.lang.Math.abs(Math.fma(p.x(), sd[0], Math.fma(p.y(), sd[1], Math.fma(p.z(), sd[2], sd[3]))));
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
        float[] sd = this.data;
        return (1.0f / (float) java.lang.Math.sqrt(Math.fma(sd[2], sd[2], Math.fma(sd[0], sd[0], sd[1] * sd[1])))) * java.lang.Math.abs(Math.fma(pX, sd[0], Math.fma(pY, sd[1], Math.fma(pZ, sd[2], sd[3]))));
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
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }

    public float a() { return data[0]; }
    public float b() { return data[1]; }
    public float c() { return data[2]; }
    public float d() { return data[3]; }

    @Override public String toString() {
        return "FloatPlane(" + a() + ", " + b() + ", " + c() + ", " + d() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatPlaneImpl)) return false;
        FloatPlaneImpl o = (FloatPlaneImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(FloatPlaneR other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.a()) <= epsilon
            && java.lang.Math.abs(data[1] - other.b()) <= epsilon
            && java.lang.Math.abs(data[2] - other.c()) <= epsilon
            && java.lang.Math.abs(data[3] - other.d()) <= epsilon;
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
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        float a = a(), b = b(), c = c(), d = d();
        float invLenSq = 1f / (a * a + b * b + c * c);
        float t = (a * pX + b * pY + c * pZ + d) * invLenSq;
        return dest.set(pX - t * a, pY - t * b, pZ - t * c);
    }

    public boolean containsPoint(float pX, float pY, float pZ, float epsilon) {
        return java.lang.Math.abs(signedDistance(pX, pY, pZ)) <= epsilon;
    }

    public boolean containsPoint(Float3R p, float epsilon) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        return java.lang.Math.abs(signedDistance(pX, pY, pZ)) <= epsilon;
    }

    public boolean intersectsSphere(FloatSphereR sph) {
        return Intersectionf.testPlaneSphere(a(), b(), c(), d(), sph.x(), sph.y(), sph.z(), sph.r());
    }

    public boolean intersectsAABB(FloatAABBR box) {
        return Intersectionf.testAabbPlane(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ(), a(), b(), c(), d());
    }

    public float[] store(@Mutated float[] dest, int offset) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }
    public @Mutated FloatPlane load(float[] src, int offset) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        float[] d = this.data;
        float[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        return buf;
    }
    @Mutated public FloatPlane loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        float[] d = this.data;
        float[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        FloatVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public FloatPlane load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatPlane loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatPlane loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatPlane r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public FloatPlane storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatPlane loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(dest, offset, ByteOrder.nativeOrder());
        return dest;
    }
    public FloatPlane load(long offset, MemorySegment src) {
        float[] d = this.data;
        FloatVector.fromMemorySegment(COL_SPECIES, src, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated FloatPlane load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
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
    @Mutated public FloatPlane load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatPlane loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatPlane loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public FloatPlane loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatPlane loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatPlane loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatPlane r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public FloatPlane storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatPlane loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public FloatPlane loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public FloatPlane loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
}
