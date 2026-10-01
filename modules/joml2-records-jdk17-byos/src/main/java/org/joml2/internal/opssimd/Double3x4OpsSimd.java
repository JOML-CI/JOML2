// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.opssimd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Vector-API isolation cell for {@link Double3x4Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double3x4Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double3x4OpsSimd {
    private Double3x4OpsSimd() {}
    private static final VectorSpecies<Double> SIMD_SPECIES = DoubleVector.SPECIES_256;
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] transpose(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).neg().intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, v, vOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double tX, double tY, double tZ) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, tY);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, tZ);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, tX).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double[] t, int tOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, t[tOffset + 1]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, t[tOffset + 2]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, t[tOffset]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo1 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)), DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo1 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, src, _lo1))).add(DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mul_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, right, rightOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, right, rightOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, right, rightOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, right, rightOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, right, rightOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, right, rightOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1])).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mulMat2x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right00 = right[rightOffset];
        double _right01 = right[rightOffset + 2];
        double _right10 = right[rightOffset + 1];
        double _right11 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 5];
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, _right00).withLane(1, _right01).mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0])).withLane(3, src[_lo0 + 3]).add(DoubleVector.broadcast(SIMD_SPECIES, _right10).withLane(1, _right11).mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1])).withLane(3, _right02 * src[_lo0] + _right12 * src[_lo0 + 1])).withLane(2, src[_lo0 + 2]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat3x3_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat3x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right20 = right[rightOffset + 2];
        double _right21 = right[rightOffset + 5];
        double _right22 = right[rightOffset + 8];
        double _right00 = right[rightOffset];
        double _right01 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 6];
        double _right10 = right[rightOffset + 1];
        double _right11 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 7];
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]), DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right20 = right[rightOffset + 2];
        double _right21 = right[rightOffset + 5];
        double _right22 = right[rightOffset + 8];
        double _right00 = right[rightOffset];
        double _right01 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 6];
        double _right10 = right[rightOffset + 1];
        double _right11 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 7];
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1])).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mulMat4x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat4x4_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat4x4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        mulMat4x4_fma_sb7ed2b29_v(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_fma_sb7ed2b29_v(double[] dest, int destOffset, double[] right, int rightOffset, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 3]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 1]).fma(_sv3, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, right[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] mulMat4x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        mulMat4x4_mulAdd_s526e04ec_v(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_mulAdd_s526e04ec_v(double[] dest, int destOffset, double[] right, int rightOffset, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 3]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 1]).mul(_sv3).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, right[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preMul(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = otherOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, other[_lo0]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[_lo0 + 1]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, other[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, other[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = otherOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, other[_lo0]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[_lo0 + 1]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, other[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, other[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMulMat2x2(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat2x2_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat2x2_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat2x2_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat2x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat2x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat2x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 3]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 4]))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat2x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 4]))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat3x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 7]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 1]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 8]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 6]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 7]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 1]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 8]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 6]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat4x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat4x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat4x4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat4x4_fma_s1657eee8_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_fma_s1657eee8_v(double[] dest, int destOffset, double[] other, int otherOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h9), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h10), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h11), DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h7), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] preMulMat4x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat4x4_mulAdd_sdb6cce97_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_mulAdd_sdb6cce97_v(double[] dest, int destOffset, double[] other, int otherOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 4);
        var _c3 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h9)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h10)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h11)).add(DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h3)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h6)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h7)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static double[] addScaled_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] addScaled_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] composeTRSMul(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return composeTRSMul_mulAdd(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
    }

    public static double[] composeTRSMul_fma(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_fma_s8387ac18_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, java.lang.Math.fma(rotationX, rotationZ, _t3) * _t0, java.lang.Math.fma(rotationX, rotationY, -_t5) * _t2, java.lang.Math.fma(-java.lang.Math.fma(rotationY, rotationY, _t4), _t1, scaleX), java.lang.Math.fma(rotationX, rotationY, _t5) * _t1, java.lang.Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, _t4), _t2, scaleY), java.lang.Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, java.lang.Math.fma(rotationX, rotationZ, -_t3) * _t1, java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_fma_s8387ac18_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv3.withLane(3, translationX)))).intoArray(dest, destOffset);
        _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h5), _sv3.withLane(3, translationY)))).intoArray(dest, destOffset + 4);
        _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h7), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h8), _sv3.withLane(3, translationZ)))).intoArray(dest, destOffset + 8);
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_s3467d619_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, ((rotationX) * (rotationZ) + (_t3)) * _t0, ((rotationX) * (rotationY) - (_t5)) * _t2, ((scaleX) - ((rotationY) * (rotationY) + (_t4)) * (_t1)), ((rotationX) * (rotationY) + (_t5)) * _t1, ((rotationY) * (rotationZ) - (rotationX * rotationW)) * _t0, ((scaleY) - ((rotationX) * (rotationX) + (_t4)) * (_t2)), ((rotationX) * (rotationW) + (rotationY * rotationZ)) * _t2, ((rotationX) * (rotationZ) - (_t3)) * _t1, ((scaleZ) - ((rotationX) * (rotationX) + (rotationY * rotationY)) * (_t0)));
        return dest;
    }

    private static void composeTRSMul_mulAdd_s3467d619_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv3.withLane(3, translationX)))).intoArray(dest, destOffset);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h3)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)).add(_sv3.withLane(3, translationY)))).intoArray(dest, destOffset + 4);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h6)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h7)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h8)).add(_sv3.withLane(3, translationZ)))).intoArray(dest, destOffset + 8);
    }

    public static double[] composeTRSMul(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_mulAdd(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static double[] composeTRSMul_fma(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _scalez = scale[scaleOffset + 2];
        double _scalex = scale[scaleOffset];
        double _scaley = scale[scaleOffset + 1];
        double _rotationy = rotation[rotationOffset + 1];
        double _rotationw = rotation[rotationOffset + 3];
        double _rotationz = rotation[rotationOffset + 2];
        double _rotationx = rotation[rotationOffset];
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        composeTRSMul_fma_s712b2612_v(dest, destOffset, m, mOffset, java.lang.Math.fma(_rotationx, _rotationz, _t3) * _t0, java.lang.Math.fma(_rotationx, _rotationy, -_t5) * _t2, java.lang.Math.fma(-java.lang.Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation[translationOffset], java.lang.Math.fma(_rotationx, _rotationy, _t5) * _t1, java.lang.Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation[translationOffset + 1], java.lang.Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, java.lang.Math.fma(_rotationx, _rotationz, -_t3) * _t1, java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_fma_s712b2612_v(double[] dest, int destOffset, double[] m, int mOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoArray(dest, destOffset);
        _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h5), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv3.withLane(3, _h7)))).intoArray(dest, destOffset + 4);
        _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h8), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h9), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h10), _sv3.withLane(3, _h11)))).intoArray(dest, destOffset + 8);
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _scalez = scale[scaleOffset + 2];
        double _scalex = scale[scaleOffset];
        double _scaley = scale[scaleOffset + 1];
        double _rotationy = rotation[rotationOffset + 1];
        double _rotationw = rotation[rotationOffset + 3];
        double _rotationz = rotation[rotationOffset + 2];
        double _rotationx = rotation[rotationOffset];
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        composeTRSMul_mulAdd_s13f9a605_v(dest, destOffset, m, mOffset, ((_rotationx) * (_rotationz) + (_t3)) * _t0, ((_rotationx) * (_rotationy) - (_t5)) * _t2, ((_scalex) - ((_rotationy) * (_rotationy) + (_t4)) * (_t1)), translation[translationOffset], ((_rotationx) * (_rotationy) + (_t5)) * _t1, ((_rotationy) * (_rotationz) - (_rotationx * _rotationw)) * _t0, ((_scaley) - ((_rotationx) * (_rotationx) + (_t4)) * (_t2)), translation[translationOffset + 1], ((_rotationx) * (_rotationw) + (_rotationy * _rotationz)) * _t2, ((_rotationx) * (_rotationz) - (_t3)) * _t1, ((_scalez) - ((_rotationx) * (_rotationx) + (_rotationy * _rotationy)) * (_t0)), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_mulAdd_s13f9a605_v(double[] dest, int destOffset, double[] m, int mOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoArray(dest, destOffset);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h6)).add(_sv3.withLane(3, _h7)))).intoArray(dest, destOffset + 4);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h8)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h9)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h10)).add(_sv3.withLane(3, _h11)))).intoArray(dest, destOffset + 8);
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_fma_saafc6159_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_lh_fma_saafc6159_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t11).withLane(1, _t13).withLane(2, _t12);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t37).withLane(1, _t39).withLane(2, _t38);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t38, _t13, -(_t39 * _t12))).withLane(1, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11))).withLane(2, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        double _t18 = ((_t17) * (_t11) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t12) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_mulAdd_sd4b10f6a_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_lh_mulAdd_sd4b10f6a_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t11).withLane(1, _t13).withLane(2, _t12);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t37).withLane(1, _t39).withLane(2, _t38);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t38) * (_t13) - (_t39 * _t12))).withLane(1, ((_t37) * (_t12) - (_t38 * _t11))).withLane(2, ((_t39) * (_t11) - (_t37 * _t13)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, upY);
        double _t22 = java.lang.Math.fma(_t20, _t14, upX);
        double _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    private static void lookAlong_rh_fma_s60e1a003_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t40).withLane(1, _t41).withLane(2, _t42);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t41, _t15, -(_t42 * _t16))).withLane(1, java.lang.Math.fma(_t42, _t14, -(_t40 * _t15))).withLane(2, java.lang.Math.fma(_t40, _t16, -(_t41 * _t14)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, -src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t21 = ((_t20) * (_t16) + (upY));
        double _t22 = ((_t20) * (_t14) + (upX));
        double _t23 = ((_t20) * (_t15) + (upZ));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    private static void lookAlong_rh_mulAdd_s61c041b4_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t40).withLane(1, _t41).withLane(2, _t42);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t41) * (_t15) - (_t42 * _t16))).withLane(1, ((_t42) * (_t14) - (_t40 * _t15))).withLane(2, ((_t40) * (_t16) - (_t41 * _t14)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, -src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_lh_fma_s6fa90897_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_fma_s6fa90897_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_fma_saafc6159_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t11) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t12) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return lookAlong_lh_mulAdd_s5359dc5e_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_mulAdd_s5359dc5e_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_mulAdd_sd4b10f6a_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_fma_s997ee389_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_fma_s997ee389_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        double _t21 = ((_t20) * (_t16) + (_upy));
        double _t22 = ((_t20) * (_t14) + (_upx));
        double _t23 = ((_t20) * (_t15) + (_upz));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_mulAdd_sebffd028_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_mulAdd_sebffd028_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_fma_saafc6159_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        double _t18 = ((_t17) * (_t11) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t12) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_mulAdd_sd4b10f6a_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, upY);
        double _t22 = java.lang.Math.fma(_t20, _t14, upX);
        double _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t21 = ((_t20) * (_t16) + (upY));
        double _t22 = ((_t20) * (_t14) + (upX));
        double _t23 = ((_t20) * (_t15) + (upZ));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_lh_fma_sa0716c04_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_fma_sa0716c04_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_fma_saafc6159_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t11) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t12) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return lookAlong_lh_mulAdd_s276c6fe5_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_mulAdd_s276c6fe5_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_lh_mulAdd_sd4b10f6a_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_fma_s5252a35a_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_fma_s5252a35a_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        double _t21 = ((_t20) * (_t16) + (_upy));
        double _t22 = ((_t20) * (_t14) + (_upx));
        double _t23 = ((_t20) * (_t15) + (_upz));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_mulAdd_sf68fd67b_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_mulAdd_sf68fd67b_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t24 = java.lang.Math.fma(_t23, _t14, upX);
        double _t25 = java.lang.Math.fma(_t23, _t16, upY);
        double _t26 = java.lang.Math.fma(_t23, _t15, upZ);
        double _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_s84b6f51c_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_fma_s84b6f51c_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_s8c9f864_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_s8c9f864_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, java.lang.Math.fma(eyeZ, _t44, java.lang.Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t24 = ((_t23) * (_t14) + (upX));
        double _t25 = ((_t23) * (_t16) + (upY));
        double _t26 = ((_t23) * (_t15) + (upZ));
        double _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        double _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        double _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        double _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        return lookAt_lh_mulAdd_sd8f2a58f_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45, ((_t44) * (_t16) - (_t45 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_mulAdd_sd8f2a58f_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t43, double _t44, double _t45, double _t54) {
        double _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        double _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_se73a766f_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, ((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))), _t43, _t44, _t45, _t54, _t55, _t56, ((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))));
        return dest;
    }

    private static void lookAt_lh_mulAdd_se73a766f_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, ((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -java.lang.Math.fma(upZ, _t18, java.lang.Math.fma(upX, _t17, upY * _t19));
        double _t27 = java.lang.Math.fma(_t26, _t19, upY);
        double _t28 = java.lang.Math.fma(_t26, _t17, upX);
        double _t29 = java.lang.Math.fma(_t26, _t18, upZ);
        double _t36 = java.lang.Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_fma_sa88f0056_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_sa88f0056_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_se2b0a512_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(eyeZ, _t48, java.lang.Math.fma(eyeX, _t46, eyeY * _t47)), java.lang.Math.fma(eyeZ, _t59, java.lang.Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_se2b0a512_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, java.lang.Math.fma(eyeZ, _t18, java.lang.Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, -src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -((upZ) * (_t18) + (((upX) * (_t17) + (upY * _t19))));
        double _t27 = ((_t26) * (_t19) + (upY));
        double _t28 = ((_t26) * (_t17) + (upX));
        double _t29 = ((_t26) * (_t18) + (upZ));
        double _t36 = ((_t27) * (_t17) - (_t28 * _t19));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t17));
        double _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        double _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        return lookAt_rh_mulAdd_s2af21ec9_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, ((_t47) * (_t18) - (_t48 * _t19)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s2af21ec9_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57) {
        double _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        double _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        lookAt_rh_mulAdd_sbf311395_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, ((eyeZ) * (_t48) + (((eyeX) * (_t46) + (eyeY * _t47)))), ((eyeZ) * (_t59) + (((eyeX) * (_t57) + (eyeY * _t58)))));
        return dest;
    }

    private static void lookAt_rh_mulAdd_sbf311395_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, ((eyeZ) * (_t18) + (((eyeX) * (_t17) + (eyeY * _t19)))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, -src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = java.lang.Math.fma(_t23, _t14, _upx);
        double _t25 = java.lang.Math.fma(_t23, _t16, _upy);
        double _t26 = java.lang.Math.fma(_t23, _t15, _upz);
        double _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_fma_s5874f362_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_fma_s5874f362_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38) {
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_s2eeb267e_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_s2eeb267e_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, java.lang.Math.fma(_eyez, _t44, java.lang.Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        double _t24 = ((_t23) * (_t14) + (_upx));
        double _t25 = ((_t23) * (_t16) + (_upy));
        double _t26 = ((_t23) * (_t15) + (_upz));
        double _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        double _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        double _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        double _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        return lookAt_lh_mulAdd_s10d0f719_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_mulAdd_s10d0f719_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        double _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        double _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_s72af92c5_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, ((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))), _t43, _t44, _t45, _t54, _t55, _t56, ((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))));
        return dest;
    }

    private static void lookAt_lh_mulAdd_s72af92c5_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, ((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t3 = center[centerOffset + 2] - _eyez;
        double _t4 = center[centerOffset] - _eyex;
        double _t5 = center[centerOffset + 1] - _eyey;
        double _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -java.lang.Math.fma(_upz, _t18, java.lang.Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = java.lang.Math.fma(_t26, _t19, _upy);
        double _t28 = java.lang.Math.fma(_t26, _t17, _upx);
        double _t29 = java.lang.Math.fma(_t26, _t18, _upz);
        double _t36 = java.lang.Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        return lookAt_rh_fma_sc0e4bc8c_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_sc0e4bc8c_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_sda93f074_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(_eyez, _t48, java.lang.Math.fma(_eyex, _t46, _eyey * _t47)), java.lang.Math.fma(_eyez, _t59, java.lang.Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_sda93f074_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, java.lang.Math.fma(_eyez, _t18, java.lang.Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, -src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t3 = center[centerOffset + 2] - _eyez;
        double _t4 = center[centerOffset] - _eyex;
        double _t5 = center[centerOffset + 1] - _eyey;
        double _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -((_upz) * (_t18) + (((_upx) * (_t17) + (_upy * _t19))));
        double _t27 = ((_t26) * (_t19) + (_upy));
        double _t28 = ((_t26) * (_t17) + (_upx));
        double _t29 = ((_t26) * (_t18) + (_upz));
        double _t36 = ((_t27) * (_t17) - (_t28 * _t19));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t17));
        double _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        double _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        return lookAt_rh_mulAdd_scb89fcf3_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_scb89fcf3_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = ((_t47) * (_t18) - (_t48 * _t19));
        double _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        double _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        lookAt_rh_mulAdd_s6dacaa93_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, ((_eyez) * (_t48) + (((_eyex) * (_t46) + (_eyey * _t47)))), ((_eyez) * (_t59) + (((_eyex) * (_t57) + (_eyey * _t58)))));
        return dest;
    }

    private static void lookAt_rh_mulAdd_s6dacaa93_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, ((_eyez) * (_t18) + (((_eyex) * (_t17) + (_eyey * _t19)))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, -src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    public static double[] lookAt_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t24 = java.lang.Math.fma(_t23, _t14, upX);
        double _t25 = java.lang.Math.fma(_t23, _t16, upY);
        double _t26 = java.lang.Math.fma(_t23, _t15, upZ);
        double _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_s6cad2567_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_fma_s6cad2567_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double epsilon, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_s8c9f864_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t24 = ((_t23) * (_t14) + (upX));
        double _t25 = ((_t23) * (_t16) + (upY));
        double _t26 = ((_t23) * (_t15) + (upZ));
        double _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        double _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        double _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        double _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        return lookAt_lh_mulAdd_sd076068c_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45, ((_t44) * (_t16) - (_t45 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_mulAdd_sd076068c_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t43, double _t44, double _t45, double _t54) {
        double _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        double _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_se73a766f_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, ((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))), _t43, _t44, _t45, _t54, _t55, _t56, ((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))));
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -java.lang.Math.fma(upZ, _t18, java.lang.Math.fma(upX, _t17, upY * _t19));
        double _t27 = java.lang.Math.fma(_t26, _t19, upY);
        double _t28 = java.lang.Math.fma(_t26, _t17, upX);
        double _t29 = java.lang.Math.fma(_t26, _t18, upZ);
        double _t36 = java.lang.Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_fma_s3b36c8b9_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_s3b36c8b9_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double epsilon, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_se2b0a512_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(eyeZ, _t48, java.lang.Math.fma(eyeX, _t46, eyeY * _t47)), java.lang.Math.fma(eyeZ, _t59, java.lang.Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -((upZ) * (_t18) + (((upX) * (_t17) + (upY * _t19))));
        double _t27 = ((_t26) * (_t19) + (upY));
        double _t28 = ((_t26) * (_t17) + (upX));
        double _t29 = ((_t26) * (_t18) + (upZ));
        double _t36 = ((_t27) * (_t17) - (_t28 * _t19));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t17));
        double _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        double _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        return lookAt_rh_mulAdd_s1d9b4966_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, ((_t47) * (_t18) - (_t48 * _t19)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s1d9b4966_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57) {
        double _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        double _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        lookAt_rh_mulAdd_sbf311395_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, ((eyeZ) * (_t48) + (((eyeX) * (_t46) + (eyeY * _t47)))), ((eyeZ) * (_t59) + (((eyeX) * (_t57) + (eyeY * _t58)))));
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    public static double[] lookAt_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = java.lang.Math.fma(_t23, _t14, _upx);
        double _t25 = java.lang.Math.fma(_t23, _t16, _upy);
        double _t26 = java.lang.Math.fma(_t23, _t15, _upz);
        double _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_fma_sb9956405_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_fma_sb9956405_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38) {
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_s2eeb267e_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        double _t24 = ((_t23) * (_t14) + (_upx));
        double _t25 = ((_t23) * (_t16) + (_upy));
        double _t26 = ((_t23) * (_t15) + (_upz));
        double _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        double _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        double _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        double _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        return lookAt_lh_mulAdd_sc510c2b6_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_mulAdd_sc510c2b6_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        double _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        double _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_s72af92c5_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, ((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))), _t43, _t44, _t45, _t54, _t55, _t56, ((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))));
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t3 = center[centerOffset + 2] - _eyez;
        double _t4 = center[centerOffset] - _eyex;
        double _t5 = center[centerOffset + 1] - _eyey;
        double _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -java.lang.Math.fma(_upz, _t18, java.lang.Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = java.lang.Math.fma(_t26, _t19, _upy);
        double _t28 = java.lang.Math.fma(_t26, _t17, _upx);
        double _t29 = java.lang.Math.fma(_t26, _t18, _upz);
        double _t36 = java.lang.Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        return lookAt_rh_fma_s260134d7_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_s260134d7_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_sda93f074_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(_eyez, _t48, java.lang.Math.fma(_eyex, _t46, _eyey * _t47)), java.lang.Math.fma(_eyez, _t59, java.lang.Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t3 = center[centerOffset + 2] - _eyez;
        double _t4 = center[centerOffset] - _eyex;
        double _t5 = center[centerOffset + 1] - _eyey;
        double _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -((_upz) * (_t18) + (((_upx) * (_t17) + (_upy * _t19))));
        double _t27 = ((_t26) * (_t19) + (_upy));
        double _t28 = ((_t26) * (_t17) + (_upx));
        double _t29 = ((_t26) * (_t18) + (_upz));
        double _t36 = ((_t27) * (_t17) - (_t28 * _t19));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t17));
        double _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        double _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        return lookAt_rh_mulAdd_s685c7200_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s685c7200_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = ((_t47) * (_t18) - (_t48 * _t19));
        double _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        double _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        lookAt_rh_mulAdd_s6dacaa93_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, ((_eyez) * (_t48) + (((_eyex) * (_t46) + (_eyey * _t47)))), ((_eyez) * (_t59) + (((_eyex) * (_t57) + (_eyey * _t58)))));
        return dest;
    }

    public static double[] mapXYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mapXYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).withLane(2, -src[_lo0 + 2]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mapXnYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).withLane(1, -src[_lo0 + 1]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static double[] preRotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -pivotZ;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t6;
        double _t10 = rotW * _t4;
        double _t14 = java.lang.Math.fma(-rotZ, _t6, 1.0);
        double _t16 = java.lang.Math.fma(rotZ, _t4, _t7);
        double _t17 = java.lang.Math.fma(rotY, _t4, _t8);
        double _t18 = java.lang.Math.fma(rotZ, _t5, _t10);
        double _t19 = java.lang.Math.fma(rotY, _t4, -_t8);
        double _t20 = java.lang.Math.fma(rotZ, _t5, -_t10);
        double _t21 = java.lang.Math.fma(rotZ, _t4, -_t7);
        preRotateAround_fma_s5306fd9d_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, java.lang.Math.fma(_t0, _t5, _t14), java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), java.lang.Math.fma(_t3, _t4, _t14), java.lang.Math.fma(_t2, _t20, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), java.lang.Math.fma(_t3, _t4, java.lang.Math.fma(_t0, _t5, 1.0)), java.lang.Math.fma(-pivotY, _t18, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_fma_s5306fd9d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t19), _sv3.withLane(3, _h1)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t20), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t17), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t21), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t18), _sv3.withLane(3, _h5)))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -pivotZ;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t6;
        double _t10 = rotW * _t4;
        double _t14 = ((1.0) - (rotZ) * (_t6));
        double _t16 = ((rotZ) * (_t4) + (_t7));
        double _t17 = ((rotY) * (_t4) + (_t8));
        double _t18 = ((rotZ) * (_t5) + (_t10));
        double _t19 = ((rotY) * (_t4) - (_t8));
        double _t20 = ((rotZ) * (_t5) - (_t10));
        double _t21 = ((rotZ) * (_t4) - (_t7));
        preRotateAround_mulAdd_s3b7753b6_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, ((_t0) * (_t5) + (_t14)), ((_t2) * (_t16) + (((pivotX) * (((rotY) * (_t5) + (_t9))) - (pivotY * _t19)))), ((_t3) * (_t4) + (_t14)), ((_t2) * (_t20) + (((pivotY) * (((rotX) * (_t4) + (_t9))) - (pivotX * _t17)))), ((_t3) * (_t4) + (((_t0) * (_t5) + (1.0)))), ((((pivotZ) * (((rotX) * (_t4) + (rotY * _t5))) - (pivotX * _t21))) - (pivotY) * (_t18)));
        return dest;
    }

    private static void preRotateAround_mulAdd_s3b7753b6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t19)).add(_sv3.withLane(3, _h1)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t20)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t17)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t21)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t18)).add(_sv3.withLane(3, _h5)))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static double[] preRotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t0 = -_roty;
        double _t3 = -_rotx;
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        double _t14 = java.lang.Math.fma(-_rotz, _t6, 1.0);
        double _t18 = java.lang.Math.fma(_rotz, _t5, _t10);
        double _t21 = java.lang.Math.fma(_rotz, _t4, -_t7);
        preRotateAround_fma_sbdfa813d_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t4, _t5, _rotz * _t6, java.lang.Math.fma(_rotz, _t4, _t7), java.lang.Math.fma(_roty, _t4, _t8), _t18, java.lang.Math.fma(_roty, _t4, -_t8), java.lang.Math.fma(_rotz, _t5, -_t10), _t21, java.lang.Math.fma(_t0, _t5, _t14), java.lang.Math.fma(_t3, _t4, _t14), java.lang.Math.fma(_t3, _t4, java.lang.Math.fma(_t0, _t5, 1.0)), java.lang.Math.fma(-_pivoty, _t18, java.lang.Math.fma(_pivotz, java.lang.Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    private static void preRotateAround_fma_sbdfa813d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _rotx, double _t2, double _t4, double _t5, double _t9, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t19), _sv3.withLane(3, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_pivotx, java.lang.Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t20), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t17), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv3.withLane(3, java.lang.Math.fma(_t2, _t20, java.lang.Math.fma(_pivoty, java.lang.Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t21), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t18), _sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t0 = -_roty;
        double _t3 = -_rotx;
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        double _t14 = ((1.0) - (_rotz) * (_t6));
        double _t18 = ((_rotz) * (_t5) + (_t10));
        double _t21 = ((_rotz) * (_t4) - (_t7));
        preRotateAround_mulAdd_sd915c628_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t4, _t5, _rotz * _t6, ((_rotz) * (_t4) + (_t7)), ((_roty) * (_t4) + (_t8)), _t18, ((_roty) * (_t4) - (_t8)), ((_rotz) * (_t5) - (_t10)), _t21, ((_t0) * (_t5) + (_t14)), ((_t3) * (_t4) + (_t14)), ((_t3) * (_t4) + (((_t0) * (_t5) + (1.0)))), ((((_pivotz) * (((_rotx) * (_t4) + (_roty * _t5))) - (_pivotx * _t21))) - (_pivoty) * (_t18)));
        return dest;
    }

    private static void preRotateAround_mulAdd_sd915c628_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _rotx, double _t2, double _t4, double _t5, double _t9, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t19)).add(_sv3.withLane(3, ((_t2) * (_t16) + (((_pivotx) * (((_roty) * (_t5) + (_t9))) - (_pivoty * _t19)))))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t20)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t17)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).add(_sv3.withLane(3, ((_t2) * (_t20) + (((_pivoty) * (((_rotx) * (_t4) + (_t9))) - (_pivotx * _t17)))))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t21)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t18)).add(_sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] preRotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _t2 = 1.0 - _t1;
        double _t3 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t7 = axisY * axisZ;
        preRotateAxis_fma_s995bbb1c_v(dest, destOffset, src, srcOffset, java.lang.Math.fma(axisY, _t0, _t2 * _t3), java.lang.Math.fma(_t2, axisX * axisX, _t1), java.lang.Math.fma(_t2, _t5, -(axisZ * _t0)), java.lang.Math.fma(_t2, _t7, -(axisX * _t0)), java.lang.Math.fma(axisZ, _t0, _t2 * _t5), java.lang.Math.fma(_t2, axisY * axisY, _t1), java.lang.Math.fma(_t2, axisZ * axisZ, _t1), java.lang.Math.fma(_t2, _t3, -(axisY * _t0)), java.lang.Math.fma(axisX, _t0, _t2 * _t7));
        return dest;
    }

    private static void preRotateAxis_fma_s995bbb1c_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h7), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t1 = Math.cosWithSin(angle);
        preRotateAxis_mulAdd_sbf6928a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, Math.sinWithCos(angle), _t1, 1.0 - _t1, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void preRotateAxis_mulAdd_sbf6928a1_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t3, double _t5, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((axisY) * (_t0) + (_t2 * _t3)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisX * axisX) + (_t1)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t5) - (axisZ * _t0)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t7) - (axisX * _t0)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((axisZ) * (_t0) + (_t2 * _t5)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisY * axisY) + (_t1)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisZ * axisZ) + (_t1)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (axisY * _t0)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((axisX) * (_t0) + (_t2 * _t7)))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static double[] preRotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t1 = Math.cosWithSin(angle);
        preRotateAxis_fma_s703295d2_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, Math.sinWithCos(angle), _t1, 1.0 - _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void preRotateAxis_fma_s703295d2_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t3, double _t5, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisy, _t0, _t2 * _t3)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisx * _axisx, _t1)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t5, -(_axisz * _t0)))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t7, -(_axisx * _t0))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisz, _t0, _t2 * _t5)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisy * _axisy, _t1))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisz * _axisz, _t1)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, -(_axisy * _t0))), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisx, _t0, _t2 * _t7))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t1 = Math.cosWithSin(angle);
        preRotateAxis_mulAdd_sb318cd01_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, Math.sinWithCos(angle), _t1, 1.0 - _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void preRotateAxis_mulAdd_sb318cd01_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t3, double _t5, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_axisy) * (_t0) + (_t2 * _t3)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisx * _axisx) + (_t1)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t5) - (_axisz * _t0)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t7) - (_axisx * _t0)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_axisz) * (_t0) + (_t2 * _t5)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisy * _axisy) + (_t1)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisz * _axisz) + (_t1)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (_axisy * _t0)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_axisx) * (_t0) + (_t2 * _t7)))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static double[] preRotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = java.lang.Math.fma(-qZ, _t5, 1.0);
        preRotateAxis_fma_s995bbb1c_v(dest, destOffset, src, srcOffset, java.lang.Math.fma(qZ, _t3, _t6), java.lang.Math.fma(_t0, _t4, _t12), java.lang.Math.fma(qY, _t3, -_t7), java.lang.Math.fma(qZ, _t4, -_t8), java.lang.Math.fma(qY, _t3, _t7), java.lang.Math.fma(_t2, _t3, _t12), java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0)), java.lang.Math.fma(qZ, _t3, -_t6), java.lang.Math.fma(qZ, _t4, _t8));
        return dest;
    }

    public static double[] preRotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        preRotateQuat_mulAdd_s51543db2_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, ((1.0) - (qZ) * (_t5)));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s51543db2_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t3) + (_t6)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t4) + (_t12)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qY) * (_t3) - (_t7)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t4) - (_t8)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qY) * (_t3) + (_t7)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (_t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t3) - (_t6)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t4) + (_t8)))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static double[] preRotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_fma_s78c6ffd6_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, java.lang.Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_fma_s78c6ffd6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t3, _t6)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qy, _t3, -_t7))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t4, -_t8)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qy, _t3, _t7)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t3, -_t6)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t4, _t8))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_mulAdd_sa065509_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, ((1.0) - (_qz) * (_t5)));
        return dest;
    }

    private static void preRotateQuat_mulAdd_sa065509_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t3) + (_t6)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t4) + (_t12)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qy) * (_t3) - (_t7)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t4) - (_t8)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qy) * (_t3) + (_t7)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (_t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t3) - (_t6)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t4) + (_t8)))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateX(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateX_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] preRotateX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t1), _sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateY(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateY_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] preRotateY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv2.fma(_sv1, _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv2.mul(_sv1).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateZ(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] preRotateZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t1), _sv2.mul(_sv1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, vY));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, vZ));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 2]));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset])).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivotZ * _t0));
        _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivotX * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivotZ * _t0));
        _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivotX * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivot[pivotOffset] * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivot[pivotOffset] * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, sY).fma(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivotY * (1.0 - sY)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, sZ).fma(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivotZ * (1.0 - sZ)));
        DoubleVector.broadcast(_SIMD_SPECIES, sX).fma(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivotX * (1.0 - sX))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, sY).mul(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivotY * (1.0 - sY)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, sZ).mul(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivotZ * (1.0 - sZ)));
        DoubleVector.broadcast(_SIMD_SPECIES, sX).mul(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivotX * (1.0 - sX))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, _sy).fma(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivot[pivotOffset + 1] * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, _sz).fma(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivot[pivotOffset + 2] * (1.0 - _sz)));
        DoubleVector.broadcast(_SIMD_SPECIES, _sx).fma(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivot[pivotOffset] * (1.0 - _sx))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, _sy).mul(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivot[pivotOffset + 1] * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, _sz).mul(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivot[pivotOffset + 2] * (1.0 - _sz)));
        DoubleVector.broadcast(_SIMD_SPECIES, _sx).mul(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivot[pivotOffset] * (1.0 - _sx))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, vY));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, vZ));
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, v[vOffset + 1]));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, v[vOffset + 2]));
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, v[vOffset])).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _self22 = src[srcOffset + 10];
        double _self02 = src[srcOffset + 2];
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self12 = src[srcOffset + 6];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _t2 = -_self22;
        double _t9 = (normalX + normalX) * normalZ;
        double _t10 = (normalX + normalX) * normalY;
        double _t11 = (normalY + normalY) * normalZ;
        double _t12 = java.lang.Math.fma(-2.0, normalX * normalX, 1.0);
        double _t13 = java.lang.Math.fma(-2.0, normalY * normalY, 1.0);
        double _t14 = java.lang.Math.fma(-2.0, normalZ * normalZ, 1.0);
        reflect_fma_sdd385ec2_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src[srcOffset + 11]);
        return dest;
    }

    private static void reflect_fma_sdd385ec2_v(double[] dest, int destOffset, double _t9, double _t11, double _t14, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18, double _h19) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        DoubleVector.broadcast(_SIMD_SPECIES, _h0).withLane(2, _h1).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h2).withLane(1, _h3).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _h8).withLane(2, _h9).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h10).withLane(1, _h11).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoArray(dest, destOffset + 4);
        DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoArray(dest, destOffset + 8);
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _self22 = src[srcOffset + 10];
        double _self02 = src[srcOffset + 2];
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self12 = src[srcOffset + 6];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _t2 = -_self22;
        double _t9 = (normalX + normalX) * normalZ;
        double _t10 = (normalX + normalX) * normalY;
        double _t11 = (normalY + normalY) * normalZ;
        double _t12 = ((1.0) - (2.0) * (normalX * normalX));
        double _t13 = ((1.0) - (2.0) * (normalY * normalY));
        double _t14 = ((1.0) - (2.0) * (normalZ * normalZ));
        reflect_mulAdd_sdd26ac6b_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src[srcOffset + 11]);
        return dest;
    }

    private static void reflect_mulAdd_sdd26ac6b_v(double[] dest, int destOffset, double _t9, double _t11, double _t14, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18, double _h19) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        DoubleVector.broadcast(_SIMD_SPECIES, _h0).withLane(2, _h1).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h2).withLane(1, _h3).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _h8).withLane(2, _h9).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h10).withLane(1, _h11).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoArray(dest, destOffset + 4);
        DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoArray(dest, destOffset + 8);
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _self22 = src[srcOffset + 10];
        double _self21 = src[srcOffset + 9];
        double _self20 = src[srcOffset + 8];
        double _normalx = normal[normalOffset];
        double _normalz = normal[normalOffset + 2];
        double _normaly = normal[normalOffset + 1];
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _t2 = -_self22;
        double _t9 = (_normalx + _normalx) * _normalz;
        double _t10 = (_normalx + _normalx) * _normaly;
        double _t11 = (_normaly + _normaly) * _normalz;
        double _t12 = java.lang.Math.fma(-2.0, _normalx * _normalx, 1.0);
        double _t13 = java.lang.Math.fma(-2.0, _normaly * _normaly, 1.0);
        reflect_fma_s54924c8a_v(dest, destOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], _self22, _self21, _self20, _t9, _t11, java.lang.Math.fma(-2.0, _normalz * _normalz, 1.0), _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10));
        return dest;
    }

    private static void reflect_fma_s54924c8a_v(double[] dest, int destOffset, double _h5, double _h11, double _h14, double _self02, double _self12, double _self22, double _self21, double _self20, double _t9, double _t11, double _t14, double _h0, double _h1, double _h2, double _h3, double _h4, double _h6, double _h7, double _h8, double _h9, double _h10, double _h12, double _h13) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        DoubleVector.broadcast(_SIMD_SPECIES, -_self02).withLane(2, _self02).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h0).withLane(1, _h1).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h2).withLane(1, _h3)).withLane(2, _h4)).withLane(3, _h5).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, -_self12).withLane(2, _self12).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h6).withLane(1, _h7).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h8).withLane(1, _h9)).withLane(2, _h10)).withLane(3, _h11).intoArray(dest, destOffset + 4);
        DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h12).withLane(1, _h13).withLane(2, _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9)).withLane(3, _h14).intoArray(dest, destOffset + 8);
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _self22 = src[srcOffset + 10];
        double _self21 = src[srcOffset + 9];
        double _self20 = src[srcOffset + 8];
        double _normalx = normal[normalOffset];
        double _normalz = normal[normalOffset + 2];
        double _normaly = normal[normalOffset + 1];
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _t2 = -_self22;
        double _t9 = (_normalx + _normalx) * _normalz;
        double _t10 = (_normalx + _normalx) * _normaly;
        double _t11 = (_normaly + _normaly) * _normalz;
        double _t12 = ((1.0) - (2.0) * (_normalx * _normalx));
        double _t13 = ((1.0) - (2.0) * (_normaly * _normaly));
        reflect_mulAdd_sd09fdfb9_v(dest, destOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], _self22, _self21, _self20, _t9, _t11, ((1.0) - (2.0) * (_normalz * _normalz)), _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10));
        return dest;
    }

    private static void reflect_mulAdd_sd09fdfb9_v(double[] dest, int destOffset, double _h5, double _h11, double _h14, double _self02, double _self12, double _self22, double _self21, double _self20, double _t9, double _t11, double _t14, double _h0, double _h1, double _h2, double _h3, double _h4, double _h6, double _h7, double _h8, double _h9, double _h10, double _h12, double _h13) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        DoubleVector.broadcast(_SIMD_SPECIES, -_self02).withLane(2, _self02).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h0).withLane(1, _h1).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h2).withLane(1, _h3)).withLane(2, _h4)).withLane(3, _h5).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, -_self12).withLane(2, _self12).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h6).withLane(1, _h7).sub(DoubleVector.broadcast(_SIMD_SPECIES, _h8).withLane(1, _h9)).withLane(2, _h10)).withLane(3, _h11).intoArray(dest, destOffset + 4);
        DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h12).withLane(1, _h13).withLane(2, _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9)).withLane(3, _h14).intoArray(dest, destOffset + 8);
    }

    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static double[] rotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t18 = java.lang.Math.fma(rotY, _t5, _t9);
        double _t19 = java.lang.Math.fma(rotZ, _t6, _t10);
        double _t24 = java.lang.Math.fma(rotZ, _t5, -_t8);
        double _t26 = java.lang.Math.fma(rotZ, _t6, -_t10);
        rotateAround_fma_s9107c306_v(dest, destOffset, src, srcOffset, rotY, pivotX, pivotY, -rotY, -rotX, _t3, _t5, _t6, _t11, java.lang.Math.fma(-rotZ, _t7, 1.0), _t18, _t19, java.lang.Math.fma(rotZ, _t5, _t8), _t24, java.lang.Math.fma(rotY, _t5, -_t9), _t26, java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), java.lang.Math.fma(-pivotY, _t19, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s9107c306_v(double[] dest, int destOffset, double[] src, int srcOffset, double rotY, double pivotX, double pivotY, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, java.lang.Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t18 = ((rotY) * (_t5) + (_t9));
        double _t19 = ((rotZ) * (_t6) + (_t10));
        double _t24 = ((rotZ) * (_t5) - (_t8));
        double _t26 = ((rotZ) * (_t6) - (_t10));
        rotateAround_mulAdd_s6f860be3_v(dest, destOffset, src, srcOffset, rotY, pivotX, pivotY, -rotY, -rotX, _t3, _t5, _t6, _t11, ((1.0) - (rotZ) * (_t7)), _t18, _t19, ((rotZ) * (_t5) + (_t8)), _t24, ((rotY) * (_t5) - (_t9)), _t26, ((_t3) * (_t26) + (((pivotY) * (((rotX) * (_t5) + (_t11))) - (pivotX * _t18)))), ((((pivotZ) * (((rotX) * (_t5) + (rotY * _t6))) - (pivotX * _t24))) - (pivotY) * (_t19)));
        return dest;
    }

    private static void rotateAround_mulAdd_s6f860be3_v(double[] dest, int destOffset, double[] src, int srcOffset, double rotY, double pivotX, double pivotY, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t6) + (_t16))).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, ((_t2) * (_t5) + (_t16))).withLane(2, _t26).withLane(3, ((_t3) * (_t20) + (((pivotX) * (((rotY) * (_t6) + (_t11))) - (pivotY * _t25)))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0)))));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static double[] rotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t18 = java.lang.Math.fma(_roty, _t5, _t9);
        double _t19 = java.lang.Math.fma(_rotz, _t6, _t10);
        double _t24 = java.lang.Math.fma(_rotz, _t5, -_t8);
        double _t26 = java.lang.Math.fma(_rotz, _t6, -_t10);
        rotateAround_fma_s3319d5f8_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, -_roty, -_rotx, _t3, _t5, _t6, _t11, java.lang.Math.fma(-_rotz, _t7, 1.0), _t18, _t19, java.lang.Math.fma(_rotz, _t5, _t8), _t24, java.lang.Math.fma(_roty, _t5, -_t9), _t26, java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(_pivoty, java.lang.Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), java.lang.Math.fma(-_pivoty, _t19, java.lang.Math.fma(_pivotz, java.lang.Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s3319d5f8_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, java.lang.Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(_pivotx, java.lang.Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t18 = ((_roty) * (_t5) + (_t9));
        double _t19 = ((_rotz) * (_t6) + (_t10));
        double _t24 = ((_rotz) * (_t5) - (_t8));
        double _t26 = ((_rotz) * (_t6) - (_t10));
        rotateAround_mulAdd_s6b6a071b_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, -_roty, -_rotx, _t3, _t5, _t6, _t11, ((1.0) - (_rotz) * (_t7)), _t18, _t19, ((_rotz) * (_t5) + (_t8)), _t24, ((_roty) * (_t5) - (_t9)), _t26, ((_t3) * (_t26) + (((_pivoty) * (((_rotx) * (_t5) + (_t11))) - (_pivotx * _t18)))), ((((_pivotz) * (((_rotx) * (_t5) + (_roty * _t6))) - (_pivotx * _t24))) - (_pivoty) * (_t19)));
        return dest;
    }

    private static void rotateAround_mulAdd_s6b6a071b_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t6) + (_t16))).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, ((_t2) * (_t5) + (_t16))).withLane(2, _t26).withLane(3, ((_t3) * (_t20) + (((_pivotx) * (((_roty) * (_t6) + (_t11))) - (_pivoty * _t25)))));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0)))));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] rotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        rotateAxis_fma_s118d95b9_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, Math.sinWithCos(angle), 1.0 - _t0, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void rotateAxis_fma_s118d95b9_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t3, double _t6, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _t3, -(axisY * _t1))).withLane(1, java.lang.Math.fma(axisX, _t1, _t2 * _t7)).withLane(2, java.lang.Math.fma(_t2, axisZ * axisZ, _t0));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, axisX * axisX, _t0)).withLane(1, java.lang.Math.fma(_t2, _t6, -(axisZ * _t1))).withLane(2, java.lang.Math.fma(axisY, _t1, _t2 * _t3));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(axisZ, _t1, _t2 * _t6)).withLane(1, java.lang.Math.fma(_t2, axisY * axisY, _t0)).withLane(2, java.lang.Math.fma(_t2, _t7, -(axisX * _t1)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        rotateAxis_mulAdd_s461c3d92_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, Math.sinWithCos(angle), 1.0 - _t0, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void rotateAxis_mulAdd_s461c3d92_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t3, double _t6, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_t3) - (axisY * _t1))).withLane(1, ((axisX) * (_t1) + (_t2 * _t7))).withLane(2, ((_t2) * (axisZ * axisZ) + (_t0)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (axisX * axisX) + (_t0))).withLane(1, ((_t2) * (_t6) - (axisZ * _t1))).withLane(2, ((axisY) * (_t1) + (_t2 * _t3)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((axisZ) * (_t1) + (_t2 * _t6))).withLane(1, ((_t2) * (axisY * axisY) + (_t0))).withLane(2, ((_t2) * (_t7) - (axisX * _t1)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static double[] rotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.cosWithSin(angle);
        rotateAxis_fma_s84fc82b9_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, Math.sinWithCos(angle), 1.0 - _t0, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void rotateAxis_fma_s84fc82b9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t3, double _t6, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _t3, -(_axisy * _t1))).withLane(1, java.lang.Math.fma(_axisx, _t1, _t2 * _t7)).withLane(2, java.lang.Math.fma(_t2, _axisz * _axisz, _t0));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _axisx * _axisx, _t0)).withLane(1, java.lang.Math.fma(_t2, _t6, -(_axisz * _t1))).withLane(2, java.lang.Math.fma(_axisy, _t1, _t2 * _t3));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_axisz, _t1, _t2 * _t6)).withLane(1, java.lang.Math.fma(_t2, _axisy * _axisy, _t0)).withLane(2, java.lang.Math.fma(_t2, _t7, -(_axisx * _t1)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.cosWithSin(angle);
        rotateAxis_mulAdd_sabf16874_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, Math.sinWithCos(angle), 1.0 - _t0, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void rotateAxis_mulAdd_sabf16874_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t3, double _t6, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_t3) - (_axisy * _t1))).withLane(1, ((_axisx) * (_t1) + (_t2 * _t7))).withLane(2, ((_t2) * (_axisz * _axisz) + (_t0)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_axisx * _axisx) + (_t0))).withLane(1, ((_t2) * (_t6) - (_axisz * _t1))).withLane(2, ((_axisy) * (_t1) + (_t2 * _t3)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_axisz) * (_t1) + (_t2 * _t6))).withLane(1, ((_t2) * (_axisy * _axisy) + (_t0))).withLane(2, ((_t2) * (_t7) - (_axisx * _t1)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static double[] rotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        rotateQuat_fma_sff33a8b2_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, java.lang.Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_sff33a8b2_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(qZ, _t3, -_t6)).withLane(1, java.lang.Math.fma(qZ, _t4, _t8)).withLane(2, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t4, _t12)).withLane(1, java.lang.Math.fma(qY, _t3, -_t7)).withLane(2, java.lang.Math.fma(qZ, _t3, _t6));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(qY, _t3, _t7)).withLane(1, java.lang.Math.fma(_t2, _t3, _t12)).withLane(2, java.lang.Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        rotateQuat_mulAdd_sa23574f1_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, ((1.0) - (qZ) * (_t5)));
        return dest;
    }

    private static void rotateQuat_mulAdd_sa23574f1_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((qZ) * (_t3) - (_t6))).withLane(1, ((qZ) * (_t4) + (_t8))).withLane(2, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t4) + (_t12))).withLane(1, ((qY) * (_t3) - (_t7))).withLane(2, ((qZ) * (_t3) + (_t6)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((qY) * (_t3) + (_t7))).withLane(1, ((_t2) * (_t3) + (_t12))).withLane(2, ((qZ) * (_t4) - (_t8)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static double[] rotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_fma_s768c7609_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, java.lang.Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_s768c7609_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_qz, _t3, -_t6)).withLane(1, java.lang.Math.fma(_qz, _t4, _t8)).withLane(2, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t4, _t12)).withLane(1, java.lang.Math.fma(_qy, _t3, -_t7)).withLane(2, java.lang.Math.fma(_qz, _t3, _t6));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_qy, _t3, _t7)).withLane(1, java.lang.Math.fma(_t2, _t3, _t12)).withLane(2, java.lang.Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_mulAdd_s9e0cc6e0_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, ((1.0) - (_qz) * (_t5)));
        return dest;
    }

    private static void rotateQuat_mulAdd_s9e0cc6e0_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_qz) * (_t3) - (_t6))).withLane(1, ((_qz) * (_t4) + (_t8))).withLane(2, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t4) + (_t12))).withLane(1, ((_qy) * (_t3) - (_t7))).withLane(2, ((_qz) * (_t3) + (_t6)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_qy) * (_t3) + (_t7))).withLane(1, ((_t2) * (_t3) + (_t12))).withLane(2, ((_qz) * (_t4) - (_t8)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateTowards(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static double[] rotateTowards_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_fma_scb757c8f_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void rotateTowards_fma_scb757c8f_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t37).withLane(1, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t38).withLane(1, java.lang.Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t39).withLane(1, java.lang.Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateTowards_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_mulAdd_s24e3c210_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void rotateTowards_mulAdd_s24e3c210_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t37).withLane(1, ((_t39) * (_t12) - (_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t38).withLane(1, ((_t37) * (_t13) - (_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t39).withLane(1, ((_t38) * (_t11) - (_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateTowards(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static double[] rotateTowards_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return rotateTowards_fma_s7fd96f7d_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_fma}, split to fit the inline budget; reached only through it. */
    private static double[] rotateTowards_fma_s7fd96f7d_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_fma_scb757c8f_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] rotateTowards_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return rotateTowards_mulAdd_sd37ec24c_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] rotateTowards_mulAdd_sd37ec24c_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_mulAdd_s24e3c210_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] rotateTowards(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static double[] rotateTowards_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_fma_scb757c8f_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] rotateTowards_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_mulAdd_s24e3c210_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] rotateTowards(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static double[] rotateTowards_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return rotateTowards_fma_se544b0fe_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_fma}, split to fit the inline budget; reached only through it. */
    private static double[] rotateTowards_fma_se544b0fe_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_fma_scb757c8f_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] rotateTowards_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return rotateTowards_mulAdd_s27aa7e7f_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] rotateTowards_mulAdd_s27aa7e7f_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        rotateTowards_mulAdd_s24e3c210_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] rotateYXZ(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (SimdSupport.USE_FMA) return rotateYXZ_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static double[] rotateYXZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t2 * _t5;
        double _t13 = _t5 * _t4;
        double _t14 = _t5 * _t1;
        double _t19 = java.lang.Math.fma(_t6, _t4, _t3 * _t2);
        double _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        rotateYXZ_fma_s75ba388b_v(dest, destOffset, _t1, _t2, _t4, _t0 * _t3, src[srcOffset], _t3 * _t5, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 11], _self01 * _t10, _self01 * _t13, _self02 * _t20, _self02 * _t19, _self02 * _t14 - _self01 * _t0, _self11 * _t10, _self11 * _t13, _self12 * _t20, _self12 * _t19, _self12 * _t14 - _self11 * _t0, _self21 * _t10, _self21 * _t13, _self22 * _t20, _self22 * _t19, _self22 * _t14 - _self21 * _t0);
        return dest;
    }

    private static void rotateYXZ_fma_s75ba388b_v(double[] dest, int destOffset, double _t1, double _t2, double _t4, double _t8, double _h0, double _h1, double _h7, double _h8, double _h14, double _h15, double _h21, double _h2, double _h3, double _h4, double _h5, double _h6, double _h9, double _h10, double _h11, double _h12, double _h13, double _h16, double _h17, double _h18, double _h19, double _h20) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t8, _t2, _t1 * _t4)).withLane(1, java.lang.Math.fma(_t8, _t4, -(_t2 * _t1))).withLane(2, _h1);
        DoubleVector.broadcast(_SIMD_SPECIES, _h0).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h2).withLane(1, _h3).add(DoubleVector.broadcast(_SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _h8).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h9).withLane(1, _h10).add(DoubleVector.broadcast(_SIMD_SPECIES, _h11).withLane(1, _h12)).withLane(2, _h13)).withLane(3, _h14).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, _h15).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h16).withLane(1, _h17).add(DoubleVector.broadcast(_SIMD_SPECIES, _h18).withLane(1, _h19)).withLane(2, _h20)).withLane(3, _h21).intoArray(dest, destOffset + 8);
    }

    public static double[] rotateYXZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t2 * _t5;
        double _t13 = _t5 * _t4;
        double _t14 = _t5 * _t1;
        double _t19 = ((_t6) * (_t4) + (_t3 * _t2));
        double _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        rotateYXZ_mulAdd_sc6df9532_v(dest, destOffset, _t1, _t2, _t4, _t0 * _t3, src[srcOffset], _t3 * _t5, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 11], _self01 * _t10, _self01 * _t13, _self02 * _t20, _self02 * _t19, _self02 * _t14 - _self01 * _t0, _self11 * _t10, _self11 * _t13, _self12 * _t20, _self12 * _t19, _self12 * _t14 - _self11 * _t0, _self21 * _t10, _self21 * _t13, _self22 * _t20, _self22 * _t19, _self22 * _t14 - _self21 * _t0);
        return dest;
    }

    private static void rotateYXZ_mulAdd_sc6df9532_v(double[] dest, int destOffset, double _t1, double _t2, double _t4, double _t8, double _h0, double _h1, double _h7, double _h8, double _h14, double _h15, double _h21, double _h2, double _h3, double _h4, double _h5, double _h6, double _h9, double _h10, double _h11, double _h12, double _h13, double _h16, double _h17, double _h18, double _h19, double _h20) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t8) * (_t2) + (_t1 * _t4))).withLane(1, ((_t8) * (_t4) - (_t2 * _t1))).withLane(2, _h1);
        DoubleVector.broadcast(_SIMD_SPECIES, _h0).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h2).withLane(1, _h3).add(DoubleVector.broadcast(_SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _h8).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h9).withLane(1, _h10).add(DoubleVector.broadcast(_SIMD_SPECIES, _h11).withLane(1, _h12)).withLane(2, _h13)).withLane(3, _h14).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, _h15).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h16).withLane(1, _h17).add(DoubleVector.broadcast(_SIMD_SPECIES, _h18).withLane(1, _h19)).withLane(2, _h20)).withLane(3, _h21).intoArray(dest, destOffset + 8);
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static double[] scale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return scale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] scale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = 1.0 - s;
            double _t1 = pivotX * _t0;
            double _t2 = pivotY * _t0;
            double _t3 = pivotZ * _t0;
            for (int _li = 0; _li < 3; _li++) {
                int _lo0 = srcOffset + _li * 4;
                DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, java.lang.Math.fma(src[_lo0], _t1, java.lang.Math.fma(src[_lo0 + 1], _t2, java.lang.Math.fma(src[_lo0 + 2], _t3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
            }
            return dest;
        } else {
            double _t0 = 1.0 - s;
            double _t1 = pivotX * _t0;
            double _t2 = pivotY * _t0;
            double _t3 = pivotZ * _t0;
            for (int _li = 0; _li < 3; _li++) {
                int _lo0 = srcOffset + _li * 4;
                DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, ((src[_lo0]) * (_t1) + (((src[_lo0 + 1]) * (_t2) + (((src[_lo0 + 2]) * (_t3) + (src[_lo0 + 3]))))))).intoArray(dest, destOffset + _li * 4);
            }
            return dest;
        }
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = 1.0 - s;
            double _t1 = pivot[pivotOffset] * _t0;
            double _t2 = pivot[pivotOffset + 1] * _t0;
            double _t3 = pivot[pivotOffset + 2] * _t0;
            for (int _li = 0; _li < 3; _li++) {
                int _lo0 = srcOffset + _li * 4;
                DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, java.lang.Math.fma(src[_lo0], _t1, java.lang.Math.fma(src[_lo0 + 1], _t2, java.lang.Math.fma(src[_lo0 + 2], _t3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
            }
            return dest;
        } else {
            double _t0 = 1.0 - s;
            double _t1 = pivot[pivotOffset] * _t0;
            double _t2 = pivot[pivotOffset + 1] * _t0;
            double _t3 = pivot[pivotOffset + 2] * _t0;
            for (int _li = 0; _li < 3; _li++) {
                int _lo0 = srcOffset + _li * 4;
                DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, ((src[_lo0]) * (_t1) + (((src[_lo0 + 1]) * (_t2) + (((src[_lo0 + 2]) * (_t3) + (src[_lo0 + 3]))))))).intoArray(dest, destOffset + _li * 4);
            }
            return dest;
        }
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, java.lang.Math.fma(src[_lo0], _t3, java.lang.Math.fma(src[_lo0 + 1], _t4, java.lang.Math.fma(src[_lo0 + 2], _t5, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, ((src[_lo0]) * (_t3) + (((src[_lo0 + 1]) * (_t4) + (((src[_lo0 + 2]) * (_t5) + (src[_lo0 + 3])))))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _t3 = pivot[pivotOffset] * (1.0 - _sx);
        double _t4 = pivot[pivotOffset + 1] * (1.0 - _sy);
        double _t5 = pivot[pivotOffset + 2] * (1.0 - _sz);
        scaleAround_fma_s8282f5fd_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, java.lang.Math.fma(src[srcOffset], _t3, java.lang.Math.fma(src[srcOffset + 1], _t4, java.lang.Math.fma(src[srcOffset + 2], _t5, src[srcOffset + 3]))), java.lang.Math.fma(src[srcOffset + 4], _t3, java.lang.Math.fma(src[srcOffset + 5], _t4, java.lang.Math.fma(src[srcOffset + 6], _t5, src[srcOffset + 7]))), java.lang.Math.fma(src[srcOffset + 8], _t3, java.lang.Math.fma(src[srcOffset + 9], _t4, java.lang.Math.fma(src[srcOffset + 10], _t5, src[srcOffset + 11]))));
        return dest;
    }

    private static void scaleAround_fma_s8282f5fd_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, _h4));
        var _c2 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, _h5));
        _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, _h3)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _t3 = pivot[pivotOffset] * (1.0 - _sx);
        double _t4 = pivot[pivotOffset + 1] * (1.0 - _sy);
        double _t5 = pivot[pivotOffset + 2] * (1.0 - _sz);
        scaleAround_mulAdd_s8f6259fc_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, ((src[srcOffset]) * (_t3) + (((src[srcOffset + 1]) * (_t4) + (((src[srcOffset + 2]) * (_t5) + (src[srcOffset + 3])))))), ((src[srcOffset + 4]) * (_t3) + (((src[srcOffset + 5]) * (_t4) + (((src[srcOffset + 6]) * (_t5) + (src[srcOffset + 7])))))), ((src[srcOffset + 8]) * (_t3) + (((src[srcOffset + 9]) * (_t4) + (((src[srcOffset + 10]) * (_t5) + (src[srcOffset + 11])))))));
        return dest;
    }

    private static void scaleAround_mulAdd_s8f6259fc_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, _h4));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, _h5));
        _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, _h3)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0] * vX + (src[_lo0 + 1] * vY + src[_lo0 + 2] * vZ))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0] * _vx + (src[_lo0 + 1] * _vy + src[_lo0 + 2] * _vz))).intoArray(dest, destOffset + _li * 4);
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
        jdk.incubator.vector.VectorSpecies<Double> _SPECIES_128 = DoubleVector.SPECIES_128;
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        } else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        } else {
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 6).intoArray(dest, destOffset + 6);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 8).intoArray(dest, destOffset + 8);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 10).intoArray(dest, destOffset + 10);
        }
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 12; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 12);
        } else {
            for (int _i = 0; _i < count * 12; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 12; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 12);
        } else {
            for (int _i = 0; _i < count * 12; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 12; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 12; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 12; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 12);
            } else {
                for (int _i = 0; _i < count * 12; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 12; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 12; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
