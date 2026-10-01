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
    private static final int PREFERRED_LANES = FloatVector.SPECIES_PREFERRED.length();
    private static final FloatVector UNIT_W = FloatVector.fromArray(SIMD_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 1.0f}, 0);

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).div(FloatVector.broadcast(SIMD_SPECIES, scalar));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).div(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, c, cOffset, b);
        return fma_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    public static float[] fma_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, b), FloatVector.fromArray(SIMD_SPECIES, c, cOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] fma_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, b)).add(FloatVector.fromArray(SIMD_SPECIES, c, cOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return fma_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static float[] fma_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.fromArray(SIMD_SPECIES, b, bOffset), FloatVector.fromArray(SIMD_SPECIES, c, cOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] fma_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.fromArray(SIMD_SPECIES, b, bOffset)).add(FloatVector.fromArray(SIMD_SPECIES, c, cOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, v, vOffset);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float s) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, s);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeZero(float[] dest, int destOffset) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _c0.intoArray(dest, destOffset);
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
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * _t3), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t0 * _t1), FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t1))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * _t3)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t0 * _t1)).add(FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t1))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier2_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static float[] bezier2_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        float _t1 = 1.0f - t;
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, (t + t) * _t1), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t1))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        float _t1 = 1.0f - t;
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, (t + t) * _t1)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t1))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2Tangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    public static float[] bezier2Tangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _c0 = _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t)), FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2Tangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _c0 = _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t))).add(FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2Tangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static float[] bezier2Tangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _c0 = _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t)), FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezier2Tangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _c0 = _sv0.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 2.0f * (1.0f - t))).add(FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, t + t)));
        _c0.intoArray(dest, destOffset);
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
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t), _sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1), _sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezierTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t)).add(_sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1)).add(_sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1))));
        _c0.intoArray(dest, destOffset);
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
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).sub(_sv0).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t), _sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1), _sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] bezierTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t1 = 1.0f - t;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset).sub(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * t * t)).add(_sv1.sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(FloatVector.broadcast(SIMD_SPECIES, 3.0f * _t1 * _t1)).add(_sv0.sub(_sv1).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * t * _t1))));
        _c0.intoArray(dest, destOffset);
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
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv2, _sv4.neg()))).fma(FloatVector.broadcast(SIMD_SPECIES, _t0), FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 3.0f).fma(_sv1, _sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRom_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv2).add(_sv4.neg()))).mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 3.0f).mul(_sv1).add(_sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0)))));
        _c0.intoArray(dest, destOffset);
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
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv2, _sv4.neg()))).fma(FloatVector.broadcast(SIMD_SPECIES, _t0), FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 3.0f).fma(_sv1, _sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRom_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _t0 = t * t;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv0.mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv2).add(_sv4.neg()))).mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 3.0f).mul(_sv1).add(_sv4.sub(_sv3))).mul(FloatVector.broadcast(SIMD_SPECIES, t * _t0)))));
        _c0.intoArray(dest, destOffset);
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
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv3, _sv4.neg())))), _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRomTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv3).add(_sv4.neg()))))).add(_sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2))));
        _c0.intoArray(dest, destOffset);
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
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).fma(_sv1, _sv0.fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 4.0f).fma(_sv3, _sv4.neg())))), _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(FloatVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] catmullRomTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 2.0f);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        var _sv5 = FloatVector.broadcast(SIMD_SPECIES, 3.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(FloatVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -5.0f).mul(_sv1).add(_sv0.mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 4.0f).mul(_sv3).add(_sv4.neg()))))).add(_sv5.mul(FloatVector.broadcast(SIMD_SPECIES, -3.0f).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(FloatVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] hermite(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        if (SimdSupport.USE_FMA) return hermite_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static float[] hermite_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f))), FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0f, _t0, t)))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t)), FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(_t2 + _t2))))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] hermite_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        float _t2 = t * _t0;
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f)))).add(FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0f, _t0, t)))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t))).add(FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(_t2 + _t2))))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] hermiteTangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        if (SimdSupport.USE_FMA) return hermiteTangent_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static float[] hermiteTangent_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(t, t, -t)), FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f))))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(t + t))), FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(-t, t, t)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] hermiteTangent_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _t0 = t * t;
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(t, t, -t))).add(FloatVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f))))).add(FloatVector.fromArray(SIMD_SPECIES, t1, t1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(3.0f, _t0, -(t + t)))).add(FloatVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(FloatVector.broadcast(SIMD_SPECIES, 6.0f * Math.fma(-t, t, t)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, t, tOffset).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, t, tOffset).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] absolute(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).abs();
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, scalar).fma(FloatVector.fromArray(SIMD_SPECIES, b, bOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, b, bOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, b, bOffset).fma(FloatVector.fromArray(SIMD_SPECIES, c, cOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, b, bOffset).mul(FloatVector.fromArray(SIMD_SPECIES, c, cOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float min, float max) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.broadcast(SIMD_SPECIES, min)).min(FloatVector.broadcast(SIMD_SPECIES, max));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float[] min, int minOffset, float[] max, int maxOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.fromArray(SIMD_SPECIES, min, minOffset)).min(FloatVector.fromArray(SIMD_SPECIES, max, maxOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] faceforward(float[] dest, int destOffset, float[] src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = (Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)))  <  0.0f ? _sv0 : _sv0.neg());
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] faceforward(float[] dest, int destOffset, float[] src, int srcOffset, float[] I, int IOffset, float[] Nref, int NrefOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = (Math.fma(I[IOffset + 3], Nref[NrefOffset + 3], Math.fma(I[IOffset + 2], Nref[NrefOffset + 2], Math.fma(I[IOffset + 0], Nref[NrefOffset + 0], I[IOffset + 1] * Nref[NrefOffset + 1])))  <  0.0f ? _sv0 : _sv0.neg());
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] inverse(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 1.0f).div(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.broadcast(SIMD_SPECIES, scalar));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).max(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).min(FloatVector.broadcast(SIMD_SPECIES, scalar));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).min(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t3  !=  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] normalizeMul(float[] dest, int destOffset, float[] src, int srcOffset, float length) {
        float _selfw = src[srcOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        var _c0 = (_t3  !=  0.0f ? FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, length * (1.0f / (float) Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] outerProduct(float[] dest, int destOffset, float[] src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, rowX).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, rowY).mul(_sv0);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, rowZ).mul(_sv0);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, rowW).mul(_sv0);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] outerProduct(float[] dest, int destOffset, float[] src, int srcOffset, float[] row, int rowOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 0]).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 1]).mul(_sv0);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 2]).mul(_sv0);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 3]).mul(_sv0);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] project(float[] dest, int destOffset, float[] src, int srcOffset, float[] onto, int ontoOffset) {
        float _ontow = onto[ontoOffset + 3];
        float _ontoz = onto[ontoOffset + 2];
        float _ontox = onto[ontoOffset + 0];
        float _ontoy = onto[ontoOffset + 1];
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, onto, ontoOffset).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_ontow, src[srcOffset + 3], Math.fma(_ontoz, src[srcOffset + 2], Math.fma(_ontox, src[srcOffset + 0], _ontoy * src[srcOffset + 1]))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] projectOnPlane(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return projectOnPlane_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return projectOnPlane_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] projectOnPlane_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset + 0], src[srcOffset + 0], normal[normalOffset + 1] * src[srcOffset + 1])))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] projectOnPlane_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset + 0], src[srcOffset + 0], normal[normalOffset + 1] * src[srcOffset + 1]))))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).fma(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset + 0], src[srcOffset + 0], normal[normalOffset + 1] * src[srcOffset + 1]))))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -(2.0f * Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset + 0], src[srcOffset + 0], normal[normalOffset + 1] * src[srcOffset + 1])))))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] refract(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (SimdSupport.USE_FMA) return refract_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    public static float[] refract_fma(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _t4 = Math.fma(normalW, src[srcOffset + 3], Math.fma(normalZ, src[srcOffset + 2], Math.fma(normalX, src[srcOffset + 0], normalY * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        var _c0 = (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] refract_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _t4 = Math.fma(normalW, src[srcOffset + 3], Math.fma(normalZ, src[srcOffset + 2], Math.fma(normalX, src[srcOffset + 0], normalY * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        var _c0 = (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] refract(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        if (SimdSupport.USE_FMA) return refract_fma(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    public static float[] refract_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        float _t4 = Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset + 0], src[srcOffset + 0], normal[normalOffset + 1] * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        var _c0 = (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] refract_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        float _t4 = Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset + 0], src[srcOffset + 0], normal[normalOffset + 1] * src[srcOffset + 1])));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        var _c0 = (_t8  >=  0.0f ? FloatVector.broadcast(SIMD_SPECIES, eta).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(FloatVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] sqrt(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sqrt();
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    public static float[] preMul_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 12).fma(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 3]), FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 2]), FloatVector.fromArray(SIMD_SPECIES, mat, matOffset).fma(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 0]), FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 1])))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] preMul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 12).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 3])).add(FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 2])).add(FloatVector.fromArray(SIMD_SPECIES, mat, matOffset).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 0])).add(FloatVector.fromArray(SIMD_SPECIES, mat, matOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, src[srcOffset + 1])))));
        _c0.intoArray(dest, destOffset);
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
            var _v0 = FloatVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(_sp, b, bOffset + _i);
            _v0.add(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            _v0.add(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            _v0.add(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + b[bOffset + _i];
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
            var _v0 = FloatVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(_sp, b, bOffset + _i);
            _v0.sub(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            _v0.sub(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            _v0.sub(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] - b[bOffset + _i];
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
            var _v0 = FloatVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(_sp, b, bOffset + _i);
            _v0.mul(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            _v0.mul(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            _v0.mul(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] * b[bOffset + _i];
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
            var _v0 = FloatVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(_sp, b, bOffset + _i);
            _v0.div(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            _v0.div(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            _v0.div(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] / b[bOffset + _i];
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
            var _v0 = FloatVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(_sp, b, bOffset + _i);
            _v0.min(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            _v0.min(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            _v0.min(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.min(a[aOffset + _i], b[bOffset + _i]);
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
            var _v0 = FloatVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(_sp, b, bOffset + _i);
            _v0.max(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            _v0.max(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            _v0.max(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.max(a[aOffset + _i], b[bOffset + _i]);
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
            var _v0 = FloatVector.fromArray(_sp, src, srcOffset + _i);
            _v0.neg().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i);
            _v0.neg().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i);
            _v0.neg().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
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
            var _v0 = FloatVector.fromArray(_sp, src, srcOffset + _i);
            _v0.abs().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i);
            _v0.abs().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i);
            _v0.abs().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.abs(src[srcOffset + _i]);
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
            var _v1 = FloatVector.fromArray(_sp, b, bOffset + _i);
            SimdMath.fma(FloatVector.broadcast(_sp, t), _v1.sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_256, t), _v1.sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_128, t), _v1.sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + t * (b[bOffset + _i] - a[aOffset + _i]);
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
            var _v0 = FloatVector.fromArray(_sp, src, srcOffset + _i);
            _v0.mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i);
            _v0.mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i);
            _v0.mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] * s;
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
            var _v0 = FloatVector.fromArray(_sp, self, selfOffset + _i);
            var _v1 = FloatVector.fromArray(_sp, a, aOffset + _i);
            var _v2 = FloatVector.fromArray(_sp, b, bOffset + _i);
            SimdMath.fma(_v0, _v1, _v2).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_256, self, selfOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_256, a, aOffset + _i);
            var _v2 = FloatVector.fromArray(FloatVector.SPECIES_256, b, bOffset + _i);
            SimdMath.fma(_v0, _v1, _v2).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromArray(FloatVector.SPECIES_128, self, selfOffset + _i);
            var _v1 = FloatVector.fromArray(FloatVector.SPECIES_128, a, aOffset + _i);
            var _v2 = FloatVector.fromArray(FloatVector.SPECIES_128, b, bOffset + _i);
            SimdMath.fma(_v0, _v1, _v2).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.fma(self[selfOffset + _i], a[aOffset + _i], b[bOffset + _i]);
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

    private static void copyArrArr_one(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
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
            for (int _i = 0; _i < 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
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
            for (int _i = 0; _i < count * 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
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
            for (int _i = 0; _i < count * 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
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
                for (int _i = 0; _i < 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 4; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 4; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
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
                for (int _i = 0; _i < count * 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 4; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 4; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
