// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Float4Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Float4Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Float4OpsSimd {
    private Float4OpsSimd() {}
    private static final VectorSpecies<Float> SIMD_SPECIES = FloatVector.SPECIES_128;

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).div(FloatVector.broadcast(SIMD_SPECIES, scalar)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).div(FloatVector.broadcast(SIMD_SPECIES, scalar)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).div(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).div(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, c, cOffset, b);
        return fma_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    public static float[] fma_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, b), FloatVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] fma_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, b)).add(FloatVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, c, cOffset, b);
        return fma_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    public static java.lang.foreign.MemorySegment fma_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, b), FloatVector.fromMemorySegment(SIMD_SPECIES, c, cOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, b)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, c, cOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return fma_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static float[] fma_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.fromArray(SIMD_SPECIES, b, bOffset), FloatVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] fma_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.fromArray(SIMD_SPECIES, b, bOffset)).add(FloatVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return fma_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static java.lang.foreign.MemorySegment fma_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, b, bOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, c, cOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, b, bOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, c, cOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        FloatVector.fromArray(SIMD_SPECIES, v, vOffset).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float s) {
        FloatVector.broadcast(SIMD_SPECIES, s).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        FloatVector.broadcast(SIMD_SPECIES, s).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] makeZero(float[] dest, int destOffset) {
        FloatVector.broadcast(SIMD_SPECIES, 0.0f).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero(java.lang.foreign.MemorySegment dest, long destOffset) {
        FloatVector.broadcast(SIMD_SPECIES, 0.0f).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] bezier(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static float[] bezier_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * _t3), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t0 * _t1), FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * _t3)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t0 * _t1)).add(FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static java.lang.foreign.MemorySegment bezier_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * _t3), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t0 * _t1), FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * _t3)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t0 * _t1)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] bezier2(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier2_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static float[] bezier2_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        float _t1 = 1.0f - t;
        FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, (t + t) * _t1), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        float _t1 = 1.0f - t;
        FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, (t + t) * _t1)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier2_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static java.lang.foreign.MemorySegment bezier2_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        float _t1 = 1.0f - t;
        FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, (t + t) * _t1), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        float _t1 = 1.0f - t;
        FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, (t + t) * _t1)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] bezier2Tangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    public static float[] bezier2Tangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t)), FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2Tangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t))).add(FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        _sv0.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).fma(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t)), FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        _sv0.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).mul(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t))).add(FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] bezier2Tangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static float[] bezier2Tangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t)), FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2Tangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t))).add(FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        _sv0.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).fma(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t)), FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder()).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        _sv0.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).mul(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder()).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] bezierTangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.USE_FMA) return bezierTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static float[] bezierTangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t), _sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1), _sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezierTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t)).add(_sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1)).add(_sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.USE_FMA) return bezierTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static java.lang.foreign.MemorySegment bezierTangent_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t), _sv1.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1), _sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t)).add(_sv1.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1)).add(_sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] bezierTangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.USE_FMA) return bezierTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static float[] bezierTangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).sub(_sv0).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t), _sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1), _sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezierTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t)).add(_sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1)).add(_sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.USE_FMA) return bezierTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static java.lang.foreign.MemorySegment bezierTangent_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder()).sub(_sv0).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t), _sv1.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1), _sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder()).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t)).add(_sv1.sub(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1)).add(_sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] catmullRom(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.USE_FMA) return catmullRom_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static float[] catmullRom_fma(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv2, _sv4.neg()))).fma(FloatVector.broadcast(SIMD_SPECIES, _t0), FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 3.0f).fma(_sv1, _sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRom_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv2).sub(_sv4))).mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 3.0f).mul(_sv1).add(_sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.USE_FMA) return catmullRom_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static java.lang.foreign.MemorySegment catmullRom_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv3 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv2, _sv4.neg()))).fma(FloatVector.broadcast(SIMD_SPECIES, _t0), FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 3.0f).fma(_sv1, _sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv3 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv2).sub(_sv4))).mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 3.0f).mul(_sv1).add(_sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] catmullRom(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.USE_FMA) return catmullRom_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static float[] catmullRom_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv2, _sv4.neg()))).fma(FloatVector.broadcast(SIMD_SPECIES, _t0), FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 3.0f).fma(_sv1, _sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRom_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv2).sub(_sv4))).mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 3.0f).mul(_sv1).add(_sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.USE_FMA) return catmullRom_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static java.lang.foreign.MemorySegment catmullRom_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv4 = FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv2, _sv4.neg()))).fma(FloatVector.broadcast(SIMD_SPECIES, _t0), FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 3.0f).fma(_sv1, _sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv4 = FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv2).sub(_sv4))).mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 3.0f).mul(_sv1).add(_sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] catmullRomTangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.USE_FMA) return catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static float[] catmullRomTangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv3, _sv4.neg())))), _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRomTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv3).sub(_sv4))))).add(_sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.USE_FMA) return catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv3, _sv4.neg())))), _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv3).sub(_sv4))))).add(_sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] catmullRomTangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.USE_FMA) return catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static float[] catmullRomTangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv3, _sv4.neg())))), _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRomTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv3).sub(_sv4))))).add(_sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.USE_FMA) return catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder());
        var _sv4 = FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder());
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv3, _sv4.neg())))), _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, p1, p1Offset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.fromMemorySegment(SIMD_SPECIES, p2, p2Offset, java.nio.ByteOrder.nativeOrder());
        var _sv4 = FloatVector.fromMemorySegment(SIMD_SPECIES, p3, p3Offset, java.nio.ByteOrder.nativeOrder());
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv3).sub(_sv4))))).add(_sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] hermite(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        if (SimdSupport.USE_FMA) return hermite_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static float[] hermite_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f))), FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0f, _t0, t)))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t)), FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(_t2 + _t2)))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] hermite_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f)))).add(FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0f, _t0, t)))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t))).add(FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(_t2 + _t2)))))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        if (SimdSupport.USE_FMA) return hermite_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static java.lang.foreign.MemorySegment hermite_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f))), FloatVector.fromMemorySegment(SIMD_SPECIES, t0, t0Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0f, _t0, t)))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, t1, t1Offset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t)), FloatVector.fromMemorySegment(SIMD_SPECIES, v1, v1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(_t2 + _t2)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f)))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, t0, t0Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0f, _t0, t)))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, t1, t1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, v1, v1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(_t2 + _t2)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] hermiteTangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        if (SimdSupport.USE_FMA) return hermiteTangent_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static float[] hermiteTangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(t, t, -t)), FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f))))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(t + t))), FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(-t, t, t))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] hermiteTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(t, t, -t))).add(FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f))))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(t + t)))).add(FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(-t, t, t))))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        if (SimdSupport.USE_FMA) return hermiteTangent_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _t0 = t * t;
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(t, t, -t)), FloatVector.fromMemorySegment(SIMD_SPECIES, t0, t0Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f))))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, t1, t1Offset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(t + t))), FloatVector.fromMemorySegment(SIMD_SPECIES, v1, v1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(-t, t, t))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _t0 = t * t;
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(t, t, -t))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, t0, t0Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f))))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, t1, t1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(t + t)))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, v1, v1Offset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(-t, t, t))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0), _sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0)).add(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.fromArray(SIMD_SPECIES, t, tOffset).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.fromArray(SIMD_SPECIES, t, tOffset).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromMemorySegment(SIMD_SPECIES, t, tOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0), _sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromMemorySegment(SIMD_SPECIES, t, tOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0)).add(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] absolute(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).abs().intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).abs().intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).fma(FloatVector.fromArray(SIMD_SPECIES, b, bOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, b, bOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    public static java.lang.foreign.MemorySegment addScaled_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, b, bOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, b, bOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        FloatVector.fromArray(SIMD_SPECIES, b, bOffset).fma(FloatVector.fromArray(SIMD_SPECIES, c, cOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        FloatVector.fromArray(SIMD_SPECIES, b, bOffset).mul(FloatVector.fromArray(SIMD_SPECIES, c, cOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static java.lang.foreign.MemorySegment addScaled_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, b, bOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, c, cOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, b, bOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, c, cOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float min, float max) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.broadcast(SIMD_SPECIES, min)).min(FloatVector.broadcast(SIMD_SPECIES, max)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).max(FloatVector.broadcast(SIMD_SPECIES, min)).min(FloatVector.broadcast(SIMD_SPECIES, max)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float[] min, int minOffset, float[] max, int maxOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.fromArray(SIMD_SPECIES, min, minOffset)).min(FloatVector.fromArray(SIMD_SPECIES, max, maxOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).max(FloatVector.fromMemorySegment(SIMD_SPECIES, min, minOffset, java.nio.ByteOrder.nativeOrder())).min(FloatVector.fromMemorySegment(SIMD_SPECIES, max, maxOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] faceforward(float[] dest, int destOffset, float[] src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] faceforward(float[] dest, int destOffset, float[] src, int srcOffset, float[] I, int IOffset, float[] Nref, int NrefOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(I[IOffset + 3], Nref[NrefOffset + 3], Math.fma(I[IOffset + 2], Nref[NrefOffset + 2], Math.fma(I[IOffset], Nref[NrefOffset], I[IOffset + 1] * Nref[NrefOffset + 1]))) < 0.0f ? 1.0f : -1.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && I.isNative() && Nref.isNative()) return faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        long _IBase = I.address() + IOffset;
        long _NrefBase = Nref.address() + NrefOffset;
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(UnsafeOpsHolder.U.getFloat(_IBase + 12L), UnsafeOpsHolder.U.getFloat(_NrefBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_IBase + 8L), UnsafeOpsHolder.U.getFloat(_NrefBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_IBase), UnsafeOpsHolder.U.getFloat(_NrefBase), UnsafeOpsHolder.U.getFloat(_IBase + 4L) * UnsafeOpsHolder.U.getFloat(_NrefBase + 4L)))) < 0.0f ? 1.0f : -1.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 12L), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 12L), Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 8L), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 8L), Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset), I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 4L)))) < 0.0f ? 1.0f : -1.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] inverse(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.broadcast(SIMD_SPECIES, 1.0f).div(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.broadcast(SIMD_SPECIES, 1.0f).div(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.broadcast(SIMD_SPECIES, scalar)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).max(FloatVector.broadcast(SIMD_SPECIES, scalar)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).max(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).min(FloatVector.broadcast(SIMD_SPECIES, scalar)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).min(FloatVector.broadcast(SIMD_SPECIES, scalar)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).min(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).min(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3  !=  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3  !=  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] normalizeMul(float[] dest, int destOffset, float[] src, int srcOffset, float length) {
        float _selfw = src[srcOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        (_t3  !=  0.0f ? FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, length * (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        long _srcBase = src.address() + srcOffset;
        float _selfw = UnsafeOpsHolder.U.getFloat(_srcBase + 12L);
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        (_t3  !=  0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, length * (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        (_t3  !=  0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, length * (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] outerProduct(float[] dest, int destOffset, float[] src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, rowX).mul(_sv0).intoArray(dest, destOffset);
        FloatVector.broadcast(SIMD_SPECIES, rowY).mul(_sv0).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(SIMD_SPECIES, rowZ).mul(_sv0).intoArray(dest, destOffset + 8);
        FloatVector.broadcast(SIMD_SPECIES, rowW).mul(_sv0).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, rowX).mul(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, rowY).mul(_sv0).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, rowZ).mul(_sv0).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, rowW).mul(_sv0).intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] outerProduct(float[] dest, int destOffset, float[] src, int srcOffset, float[] row, int rowOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 1]).mul(_sv0);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 2]).mul(_sv0);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 3]).mul(_sv0);
        FloatVector.broadcast(SIMD_SPECIES, row[rowOffset]).mul(_sv0).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && row.isNative()) return outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _rowBase = row.address() + rowOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_rowBase + 4L)).mul(_sv0);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_rowBase + 8L)).mul(_sv0);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_rowBase + 12L)).mul(_sv0);
        FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_rowBase)).mul(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 4L)).mul(_sv0);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 8L)).mul(_sv0);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 12L)).mul(_sv0);
        FloatVector.broadcast(SIMD_SPECIES, row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset)).mul(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] project(float[] dest, int destOffset, float[] src, int srcOffset, float[] onto, int ontoOffset) {
        float _ontow = onto[ontoOffset + 3];
        float _ontoz = onto[ontoOffset + 2];
        float _ontox = onto[ontoOffset];
        float _ontoy = onto[ontoOffset + 1];
        FloatVector.fromArray(SIMD_SPECIES, onto, ontoOffset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_ontow, src[srcOffset + 3], Math.fma(_ontoz, src[srcOffset + 2], Math.fma(_ontox, src[srcOffset], _ontoy * src[srcOffset + 1]))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy))))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && onto.isNative()) return project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        long _srcBase = src.address() + srcOffset;
        long _ontoBase = onto.address() + ontoOffset;
        float _ontow = UnsafeOpsHolder.U.getFloat(_ontoBase + 12L);
        float _ontoz = UnsafeOpsHolder.U.getFloat(_ontoBase + 8L);
        float _ontox = UnsafeOpsHolder.U.getFloat(_ontoBase);
        float _ontoy = UnsafeOpsHolder.U.getFloat(_ontoBase + 4L);
        FloatVector.fromMemorySegment(SIMD_SPECIES, onto, ontoOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_ontow, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(_ontoz, UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(_ontox, UnsafeOpsHolder.U.getFloat(_srcBase), _ontoy * UnsafeOpsHolder.U.getFloat(_srcBase + 4L)))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        float _ontow = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 12L);
        float _ontoz = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 8L);
        float _ontox = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        FloatVector.fromMemorySegment(SIMD_SPECIES, onto, ontoOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_ontow, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(_ontoz, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(_ontox, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _ontoy * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] projectOnPlane(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return projectOnPlane_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return projectOnPlane_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] projectOnPlane_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] projectOnPlane_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1]))))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return projectOnPlane_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
            return projectOnPlane_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return projectOnPlane_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return projectOnPlane_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return projectOnPlane_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return projectOnPlane_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_normalBase + 4L) * UnsafeOpsHolder.U.getFloat(_srcBase + 4L))))), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))))), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return projectOnPlane_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return projectOnPlane_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_normalBase + 4L) * UnsafeOpsHolder.U.getFloat(_srcBase + 4L)))))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).fma(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1]))))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])))))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
            return reflect_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment reflect_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment reflect_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_normalBase + 4L) * UnsafeOpsHolder.U.getFloat(_srcBase + 4L)))))), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))))), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_normalBase + 4L) * UnsafeOpsHolder.U.getFloat(_srcBase + 4L))))))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))))))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] refract(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (SimdSupport.USE_FMA) return refract_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    public static float[] refract_fma(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _t4 = Math.fma(normalW, src[srcOffset + 3], Math.fma(normalZ, src[srcOffset + 2], Math.fma(normalX, src[srcOffset], normalY * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] refract_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _t4 = Math.fma(normalW, src[srcOffset + 3], Math.fma(normalZ, src[srcOffset + 2], Math.fma(normalX, src[srcOffset], normalY * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return refract_fma_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
            return refract_fma_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return refract_mulAdd_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_mulAdd_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    public static java.lang.foreign.MemorySegment refract_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return refract_fma_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_fma_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    public static java.lang.foreign.MemorySegment refract_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        long _srcBase = src.address() + srcOffset;
        float _t4 = Math.fma(normalW, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(normalZ, UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(normalX, UnsafeOpsHolder.U.getFloat(_srcBase), normalY * UnsafeOpsHolder.U.getFloat(_srcBase + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _t4 = Math.fma(normalW, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normalZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normalX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normalY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return refract_mulAdd_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_mulAdd_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    public static java.lang.foreign.MemorySegment refract_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        long _srcBase = src.address() + srcOffset;
        float _t4 = Math.fma(normalW, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(normalZ, UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(normalX, UnsafeOpsHolder.U.getFloat(_srcBase), normalY * UnsafeOpsHolder.U.getFloat(_srcBase + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _t4 = Math.fma(normalW, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normalZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normalX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normalY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] refract(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        if (SimdSupport.USE_FMA) return refract_fma(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    public static float[] refract_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        float _t4 = Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] refract_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        float _t4 = Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return refract_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
            return refract_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return refract_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    public static java.lang.foreign.MemorySegment refract_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return refract_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    public static java.lang.foreign.MemorySegment refract_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        float _t4 = Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_normalBase + 4L) * UnsafeOpsHolder.U.getFloat(_srcBase + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        float _t4 = Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return refract_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    public static java.lang.foreign.MemorySegment refract_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        float _t4 = Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_normalBase), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_normalBase + 4L) * UnsafeOpsHolder.U.getFloat(_srcBase + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        float _t4 = Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, normal, normalOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] sqrt(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sqrt().intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).sqrt().intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    public static float[] preMul_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 12).fma(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 3]), FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 2]), FloatVector.fromArray(SIMD_SPECIES, mat, matOffset).fma(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset]), FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 1]))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] preMul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 12).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 3])).add(FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 2])).add(FloatVector.fromArray(SIMD_SPECIES, mat, matOffset).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset])).add(FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 1]))))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMul_fma_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
            return preMul_fma_api(dest, destOffset, src, srcOffset, mat, matOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMul_mulAdd_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_mulAdd_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    public static java.lang.foreign.MemorySegment preMul_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMul_fma_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_fma_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    public static java.lang.foreign.MemorySegment preMul_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _srcBase = src.address() + srcOffset;
        FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 48L, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 12L)), FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 32L, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 8L)), FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase)), FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 4L)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 48L, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)), FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 32L, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L)), FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)), FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMul_mulAdd_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_mulAdd_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _srcBase = src.address() + srcOffset;
        FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 48L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 12L))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 8L))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 4L)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 48L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, mat, matOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] add(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, a, aOffset + _i).add(FloatVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i).add(FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i).add(FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + b[bOffset + _i];
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) + b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, a, aOffset + _i).sub(FloatVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i).sub(FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i).sub(FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] - b[bOffset + _i];
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) - b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, a, aOffset + _i).mul(FloatVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i).mul(FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i).mul(FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] * b[bOffset + _i];
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) * b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    public static float[] div(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, a, aOffset + _i).div(FloatVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i).div(FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i).div(FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] / b[bOffset + _i];
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).div(FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).div(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).div(FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) / b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    public static float[] min(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, a, aOffset + _i).min(FloatVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i).min(FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i).min(FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.min(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).min(FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).min(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).min(FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.min(a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L)));
        }
        return dest;
    }

    public static float[] max(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, a, aOffset + _i).max(FloatVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i).max(FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i).max(FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.max(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).max(FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).max(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).max(FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.max(a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L)));
        }
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, src, srcOffset + _i).neg().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).neg().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).neg().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        }
        return dest;
    }

    public static float[] abs(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, src, srcOffset + _i).abs().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).abs().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).abs().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.abs(src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment abs(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).abs().intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).abs().intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).abs().intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L)));
        }
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, float t, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromArray(_sp, a, aOffset + _i);
            SimdMath.fma(FloatVector.broadcast(_sp, t), FloatVector.fromArray(_sp, b, bOffset + _i).sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_256, t), FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i).sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_128, t), FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i).sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + t * (b[bOffset + _i] - a[aOffset + _i]);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, float t, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(FloatVector.broadcast(_sp, t), FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).sub(_v0), _v0).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_256, t), FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).sub(_v0), _v0).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_128, t), FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).sub(_v0), _v0).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) + t * (b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L) - a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L)));
        }
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromArray(_sp, src, srcOffset + _i).mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] * s;
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            FloatVector.fromMemorySegment(_sp, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).mul(s).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).mul(s).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).mul(s).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L) * s);
        }
        return dest;
    }

    public static float[] fma(float[] dest, int destOffset, float[] self, int selfOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            SimdMath.fma(FloatVector.fromArray(_sp, self, selfOffset + _i), FloatVector.fromArray(_sp, a, aOffset + _i), FloatVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            SimdMath.fma(FloatVector.fromArray(FloatVector.SPECIES_256, self, selfOffset + _i), FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i), FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            SimdMath.fma(FloatVector.fromArray(FloatVector.SPECIES_128, self, selfOffset + _i), FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i), FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.fma(self[selfOffset + _i], a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment self, long selfOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            SimdMath.fma(FloatVector.fromMemorySegment(_sp, self, selfOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(_sp, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(_sp, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            SimdMath.fma(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, self, selfOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            SimdMath.fma(FloatVector.fromMemorySegment(FloatVector.SPECIES_128, self, selfOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + _i * 4L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, Math.fma(self.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, selfOffset + _i * 4L), a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L)));
        }
        return dest;
    }

    private static void copyArrArr(float[] dest, int destOffset, float[] src, int srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromArray(_sp, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
    }

    private static void copyArrSeg(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L);
    }

    private static void copySegArr(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromArray(_sp, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src[srcOffset + _i]);
    }

    private static void copySegSeg(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
    }

    private static void copyArrArr_one(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
    }

    private static void copyArrSeg_one(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset * 4L);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 4);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 4);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr_one(_destSeg, destOffset * 4L, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, destOffset * 4L, src, srcOffset, count * 4);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 4L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr_one(_destSeg, destOffset * 4L, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg_one(_destSeg, destOffset * 4L, _srcSeg, srcOffset * 4L);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 4L, count * 4);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 4L, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset * 4L, count * 4);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 4L, _srcSeg, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset, count * 4);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 4L, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 4L, src, srcOffset, count * 4);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr_one(_destSeg, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(_destSeg, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset, _srcSeg, srcOffset * 4L);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 4L, count * 4);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg_one(_destSeg, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset, count * 4);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegSeg_one(_destSeg, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegSeg(_destSeg, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(dest, destOffset, _srcSeg, srcOffset * 4L);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg_one(dest, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg(dest, destOffset, _srcSeg, srcOffset, count * 4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copySegSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copySegSeg(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }
}
