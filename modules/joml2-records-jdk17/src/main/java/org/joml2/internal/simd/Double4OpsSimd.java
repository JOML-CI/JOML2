// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Double4Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double4Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double4OpsSimd {
    private Double4OpsSimd() {}
    private static final VectorSpecies<Double> SIMD_SPECIES = DoubleVector.SPECIES_256;
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] div(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).div(DoubleVector.broadcast(SIMD_SPECIES, scalar)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] div(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).div(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] c, int cOffset, double b) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, c, cOffset, b);
        return fma_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    public static double[] fma_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] c, int cOffset, double b) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, b), DoubleVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] fma_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] c, int cOffset, double b) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, b)).add(DoubleVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        if (SimdSupport.USE_FMA) return fma_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return fma_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static double[] fma_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.fromArray(SIMD_SPECIES, b, bOffset), DoubleVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] fma_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.fromArray(SIMD_SPECIES, b, bOffset)).add(DoubleVector.fromArray(SIMD_SPECIES, c, cOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, v, vOffset).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double s) {
        DoubleVector.broadcast(SIMD_SPECIES, s).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeZero(double[] dest, int destOffset) {
        DoubleVector.broadcast(SIMD_SPECIES, 0.0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (SimdSupport.USE_FMA) return bezier_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static double[] bezier_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset).fma(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * t * _t3), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset).fma(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * _t0 * _t1), DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * t * _t3)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0 * _t3))).add(DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * _t0 * _t1)).add(DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier2(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        if (SimdSupport.USE_FMA) return bezier2_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static double[] bezier2_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        double _t1 = 1.0 - t;
        DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset).fma(DoubleVector.broadcast(SIMD_SPECIES, t * t), DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset).fma(DoubleVector.broadcast(SIMD_SPECIES, (t + t) * _t1), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier2_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        double _t1 = 1.0 - t;
        DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, t * t)).add(DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, (t + t) * _t1)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier2Tangent(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    public static double[] bezier2Tangent_fma(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        _sv0.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(DoubleVector.broadcast(SIMD_SPECIES, 2.0 * (1.0 - t)), DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier2Tangent_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        _sv0.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(DoubleVector.broadcast(SIMD_SPECIES, 2.0 * (1.0 - t))).add(DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W).sub(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier2Tangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        if (SimdSupport.USE_FMA) return bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    public static double[] bezier2Tangent_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        _sv0.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(DoubleVector.broadcast(SIMD_SPECIES, 2.0 * (1.0 - t)), DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset).sub(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezier2Tangent_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        _sv0.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(DoubleVector.broadcast(SIMD_SPECIES, 2.0 * (1.0 - t))).add(DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset).sub(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, t + t))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezierTangent(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (SimdSupport.USE_FMA) return bezierTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static double[] bezierTangent_fma(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _t1 = 1.0 - t;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).fma(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * t * t), _sv1.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * _t1 * _t1), _sv0.sub(_sv1).mul(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezierTangent_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _t1 = 1.0 - t;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W).sub(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * t * t)).add(_sv1.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * _t1 * _t1)).add(_sv0.sub(_sv1).mul(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezierTangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (SimdSupport.USE_FMA) return bezierTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static double[] bezierTangent_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _t1 = 1.0 - t;
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset).sub(_sv0).fma(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * t * t), _sv1.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).fma(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * _t1 * _t1), _sv0.sub(_sv1).mul(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] bezierTangent_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _t1 = 1.0 - t;
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset).sub(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * t * t)).add(_sv1.sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).mul(DoubleVector.broadcast(SIMD_SPECIES, 3.0 * _t1 * _t1)).add(_sv0.sub(_sv1).mul(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * t * _t1)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRom(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (SimdSupport.USE_FMA) return catmullRom_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static double[] catmullRom_fma(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _t0 = t * t;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(_sv0.fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(DoubleVector.broadcast(SIMD_SPECIES, -5.0).fma(_sv1, _sv0.fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 4.0).fma(_sv2, _sv4.neg()))).fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), DoubleVector.broadcast(SIMD_SPECIES, -3.0).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 3.0).fma(_sv1, _sv4.sub(_sv3))).mul(DoubleVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRom_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _t0 = t * t;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(_sv0.mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(DoubleVector.broadcast(SIMD_SPECIES, -5.0).mul(_sv1).add(_sv0.mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 4.0).mul(_sv2).sub(_sv4))).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(DoubleVector.broadcast(SIMD_SPECIES, -3.0).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 3.0).mul(_sv1).add(_sv4.sub(_sv3))).mul(DoubleVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRom(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (SimdSupport.USE_FMA) return catmullRom_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static double[] catmullRom_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _t0 = t * t;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(_sv0.fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(DoubleVector.broadcast(SIMD_SPECIES, -5.0).fma(_sv1, _sv0.fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 4.0).fma(_sv2, _sv4.neg()))).fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), DoubleVector.broadcast(SIMD_SPECIES, -3.0).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 3.0).fma(_sv1, _sv4.sub(_sv3))).mul(DoubleVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRom_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _t0 = t * t;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(_sv0.mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, t).mul(_sv2.sub(_sv3))).add(DoubleVector.broadcast(SIMD_SPECIES, -5.0).mul(_sv1).add(_sv0.mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 4.0).mul(_sv2).sub(_sv4))).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(DoubleVector.broadcast(SIMD_SPECIES, -3.0).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 3.0).mul(_sv1).add(_sv4.sub(_sv3))).mul(DoubleVector.broadcast(SIMD_SPECIES, t * _t0))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRomTangent(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (SimdSupport.USE_FMA) return catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    public static double[] catmullRomTangent_fma(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _sv5 = DoubleVector.broadcast(SIMD_SPECIES, 3.0);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(DoubleVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -5.0).fma(_sv1, _sv0.fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 4.0).fma(_sv3, _sv4.neg())))), _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, -3.0).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(DoubleVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRomTangent_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p1X).withLane(1, p1Y).withLane(2, p1Z).withLane(3, p1W);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p2X).withLane(1, p2Y).withLane(2, p2Z).withLane(3, p2W);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, p3X).withLane(1, p3Y).withLane(2, p3Z).withLane(3, p3W);
        var _sv5 = DoubleVector.broadcast(SIMD_SPECIES, 3.0);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(DoubleVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -5.0).mul(_sv1).add(_sv0.mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 4.0).mul(_sv3).sub(_sv4))))).add(_sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, -3.0).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(DoubleVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRomTangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (SimdSupport.USE_FMA) return catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    public static double[] catmullRomTangent_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        var _sv5 = DoubleVector.broadcast(SIMD_SPECIES, 3.0);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(DoubleVector.broadcast(SIMD_SPECIES, t).fma(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -5.0).fma(_sv1, _sv0.fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 4.0).fma(_sv3, _sv4.neg())))), _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, -3.0).fma(_sv3, _sv5.fma(_sv1, _sv4.sub(_sv2)))).fma(DoubleVector.broadcast(SIMD_SPECIES, t * t), _sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] catmullRomTangent_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 2.0);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, p1, p1Offset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, p2, p2Offset);
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, p3, p3Offset);
        var _sv5 = DoubleVector.broadcast(SIMD_SPECIES, 3.0);
        DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(DoubleVector.broadcast(SIMD_SPECIES, t).mul(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -5.0).mul(_sv1).add(_sv0.mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 4.0).mul(_sv3).sub(_sv4))))).add(_sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, -3.0).mul(_sv3).add(_sv5.mul(_sv1).add(_sv4.sub(_sv2)))).mul(DoubleVector.broadcast(SIMD_SPECIES, t * t)).add(_sv3.sub(_sv2)))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] hermite(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        if (SimdSupport.USE_FMA) return hermite_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static double[] hermite_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _t0 = t * t;
        double _t2 = t * _t0;
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0))), DoubleVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0, _t0, t)))).add(DoubleVector.fromArray(SIMD_SPECIES, t1, t1Offset).fma(DoubleVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t)), DoubleVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(3.0, _t0, -(_t2 + _t2)))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] hermite_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _t0 = t * t;
        double _t2 = t * _t0;
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0)))).add(DoubleVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(t - 2.0, _t0, t)))).add(DoubleVector.fromArray(SIMD_SPECIES, t1, t1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, t * Math.fma(t, t, -t))).add(DoubleVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(3.0, _t0, -(_t2 + _t2)))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] hermiteTangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        if (SimdSupport.USE_FMA) return hermiteTangent_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    public static double[] hermiteTangent_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _t0 = t * t;
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * Math.fma(t, t, -t)), DoubleVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0))))).add(DoubleVector.fromArray(SIMD_SPECIES, t1, t1Offset).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(3.0, _t0, -(t + t))), DoubleVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * Math.fma(-t, t, t))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] hermiteTangent_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _t0 = t * t;
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * Math.fma(t, t, -t))).add(DoubleVector.fromArray(SIMD_SPECIES, t0, t0Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0))))).add(DoubleVector.fromArray(SIMD_SPECIES, t1, t1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(3.0, _t0, -(t + t)))).add(DoubleVector.fromArray(SIMD_SPECIES, v1, v1Offset).mul(DoubleVector.broadcast(SIMD_SPECIES, 6.0 * Math.fma(-t, t, t))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double[] t, int tOffset) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double[] t, int tOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.fromArray(SIMD_SPECIES, t, tOffset).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double[] t, int tOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.fromArray(SIMD_SPECIES, t, tOffset).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] absolute(double[] dest, int destOffset, double[] src, int srcOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).abs().intoArray(dest, destOffset);
        return dest;
    }

    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double scalar) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    public static double[] addScaled_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double scalar) {
        DoubleVector.broadcast(SIMD_SPECIES, scalar).fma(DoubleVector.fromArray(SIMD_SPECIES, b, bOffset), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] addScaled_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double scalar) {
        DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, b, bOffset)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    public static double[] addScaled_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, b, bOffset).fma(DoubleVector.fromArray(SIMD_SPECIES, c, cOffset), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] addScaled_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, b, bOffset).mul(DoubleVector.fromArray(SIMD_SPECIES, c, cOffset)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] clamp(double[] dest, int destOffset, double[] src, int srcOffset, double min, double max) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).max(DoubleVector.broadcast(SIMD_SPECIES, min)).min(DoubleVector.broadcast(SIMD_SPECIES, max)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] clamp(double[] dest, int destOffset, double[] src, int srcOffset, double[] min, int minOffset, double[] max, int maxOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).max(DoubleVector.fromArray(SIMD_SPECIES, min, minOffset)).min(DoubleVector.fromArray(SIMD_SPECIES, max, maxOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] faceforward(double[] dest, int destOffset, double[] src, int srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] faceforward(double[] dest, int destOffset, double[] src, int srcOffset, double[] I, int IOffset, double[] Nref, int NrefOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(I[IOffset + 3], Nref[NrefOffset + 3], Math.fma(I[IOffset + 2], Nref[NrefOffset + 2], Math.fma(I[IOffset], Nref[NrefOffset], I[IOffset + 1] * Nref[NrefOffset + 1]))) < 0.0 ? 1.0 : -1.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] inverse(double[] dest, int destOffset, double[] src, int srcOffset) {
        DoubleVector.broadcast(SIMD_SPECIES, 1.0).div(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] max(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).max(DoubleVector.broadcast(SIMD_SPECIES, scalar)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] max(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).max(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] min(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).min(DoubleVector.broadcast(SIMD_SPECIES, scalar)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] min(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).min(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] normalize(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        double _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3  !=  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] normalizeMul(double[] dest, int destOffset, double[] src, int srcOffset, double length) {
        double _selfw = src[srcOffset + 3];
        double _selfz = src[srcOffset + 2];
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        (_t3  !=  0.0 ? DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, length * (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] outerProduct(double[] dest, int destOffset, double[] src, int srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, rowX).mul(_sv0).intoArray(dest, destOffset);
        DoubleVector.broadcast(SIMD_SPECIES, rowY).mul(_sv0).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(SIMD_SPECIES, rowZ).mul(_sv0).intoArray(dest, destOffset + 8);
        DoubleVector.broadcast(SIMD_SPECIES, rowW).mul(_sv0).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] outerProduct(double[] dest, int destOffset, double[] src, int srcOffset, double[] row, int rowOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 1]).mul(_sv0);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 2]).mul(_sv0);
        var _c3 = DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 3]).mul(_sv0);
        DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset]).mul(_sv0).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] project(double[] dest, int destOffset, double[] src, int srcOffset, double[] onto, int ontoOffset) {
        double _ontow = onto[ontoOffset + 3];
        double _ontoz = onto[ontoOffset + 2];
        double _ontox = onto[ontoOffset];
        double _ontoy = onto[ontoOffset + 1];
        DoubleVector.fromArray(SIMD_SPECIES, onto, ontoOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_ontow, src[srcOffset + 3], Math.fma(_ontoz, src[srcOffset + 2], Math.fma(_ontox, src[srcOffset], _ontoy * src[srcOffset + 1]))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] projectOnPlane(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return projectOnPlane_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return projectOnPlane_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static double[] projectOnPlane_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, normal, normalOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] projectOnPlane_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1]))))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, normal, normalOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, -(2.0 * Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1]))))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, -(2.0 * Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])))))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] refract(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        if (SimdSupport.USE_FMA) return refract_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    public static double[] refract_fma(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _t4 = Math.fma(normalW, src[srcOffset + 3], Math.fma(normalZ, src[srcOffset + 2], Math.fma(normalX, src[srcOffset], normalY * src[srcOffset + 1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8  >=  0.0 ? DoubleVector.broadcast(SIMD_SPECIES, eta).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), DoubleVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] refract_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _t4 = Math.fma(normalW, src[srcOffset + 3], Math.fma(normalZ, src[srcOffset + 2], Math.fma(normalX, src[srcOffset], normalY * src[srcOffset + 1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8  >=  0.0 ? DoubleVector.broadcast(SIMD_SPECIES, eta).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(DoubleVector.zero(SIMD_SPECIES).withLane(0, normalX).withLane(1, normalY).withLane(2, normalZ).withLane(3, normalW).mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] refract(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset, double eta) {
        if (SimdSupport.USE_FMA) return refract_fma(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    public static double[] refract_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset, double eta) {
        double _t4 = Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8  >=  0.0 ? DoubleVector.broadcast(SIMD_SPECIES, eta).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), DoubleVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] refract_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset, double eta) {
        double _t4 = Math.fma(normal[normalOffset + 3], src[srcOffset + 3], Math.fma(normal[normalOffset + 2], src[srcOffset + 2], Math.fma(normal[normalOffset], src[srcOffset], normal[normalOffset + 1] * src[srcOffset + 1])));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        (_t8  >=  0.0 ? DoubleVector.broadcast(SIMD_SPECIES, eta).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(DoubleVector.fromArray(SIMD_SPECIES, normal, normalOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)))))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] sqrt(double[] dest, int destOffset, double[] src, int srcOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).sqrt().intoArray(dest, destOffset);
        return dest;
    }

    public static double[] preMul(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    public static double[] preMul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset + 12).fma(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset + 3]), DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset + 2]), DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset]), DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset + 1]))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] preMul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset + 12).mul(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset + 3])).add(DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset + 2])).add(DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset])).add(DoubleVector.fromArray(SIMD_SPECIES, mat, matOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, src[srcOffset + 1]))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] add(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, a, aOffset + _i).add(DoubleVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i).add(DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + b[bOffset + _i];
        }
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, a, aOffset + _i).sub(DoubleVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i).sub(DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] - b[bOffset + _i];
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, a, aOffset + _i).mul(DoubleVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i).mul(DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] * b[bOffset + _i];
        }
        return dest;
    }

    public static double[] div(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, a, aOffset + _i).div(DoubleVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i).div(DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] / b[bOffset + _i];
        }
        return dest;
    }

    public static double[] min(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, a, aOffset + _i).min(DoubleVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i).min(DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.min(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    public static double[] max(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, a, aOffset + _i).max(DoubleVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i).max(DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.max(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, src, srcOffset + _i).neg().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).neg().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    public static double[] abs(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, src, srcOffset + _i).abs().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).abs().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.abs(src[srcOffset + _i]);
        }
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, double t, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            SimdMath.fma(DoubleVector.broadcast(_sp, t), DoubleVector.fromArray(_sp, b, bOffset + _i).sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            SimdMath.fma(DoubleVector.broadcast(DoubleVector.SPECIES_256, t), DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i).sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + t * (b[bOffset + _i] - a[aOffset + _i]);
        }
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromArray(_sp, src, srcOffset + _i).mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] * s;
        }
        return dest;
    }

    public static double[] fma(double[] dest, int destOffset, double[] self, int selfOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            SimdMath.fma(DoubleVector.fromArray(_sp, self, selfOffset + _i), DoubleVector.fromArray(_sp, a, aOffset + _i), DoubleVector.fromArray(_sp, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            SimdMath.fma(DoubleVector.fromArray(DoubleVector.SPECIES_256, self, selfOffset + _i), DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i), DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i)).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.fma(self[selfOffset + _i], a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    private static void copyArrArr(double[] dest, int destOffset, double[] src, int srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromArray(_sp, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
    }

    private static void copyArrArr_one(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
        }
    }


    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            for (int _i = 0; _i < count * 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            for (int _i = 0; _i < count * 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                double[] _srcArr = src.array();
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

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 4);
            } else {
                for (int _i = 0; _i < count * 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                double[] _srcArr = src.array();
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
