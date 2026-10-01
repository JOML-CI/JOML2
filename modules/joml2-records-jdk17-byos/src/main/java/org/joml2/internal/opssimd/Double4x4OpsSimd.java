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
 * Vector-API isolation cell for {@link Double4x4Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double4x4Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double4x4OpsSimd {
    private Double4x4OpsSimd() {}
    private static final VectorSpecies<Double> SIMD_SPECIES = DoubleVector.SPECIES_256;
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).neg().intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, v, vOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double tX, double tY, double tZ) {
        double _self33 = src[srcOffset + 15];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _vcp1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _vcp2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _vcp0.intoArray(dest, destOffset);
        _vcp1.intoArray(dest, destOffset + 4);
        _vcp2.intoArray(dest, destOffset + 8);
        dest[destOffset + 12] = tX;
        dest[destOffset + 13] = tY;
        dest[destOffset + 14] = tZ;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double[] t, int tOffset) {
        double _tx = t[tOffset];
        double _ty = t[tOffset + 1];
        double _tz = t[tOffset + 2];
        double _self33 = src[srcOffset + 15];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _vcp1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _vcp2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _vcp0.intoArray(dest, destOffset);
        _vcp1.intoArray(dest, destOffset + 4);
        _vcp2.intoArray(dest, destOffset + 8);
        dest[destOffset + 12] = _tx;
        dest[destOffset + 13] = _ty;
        dest[destOffset + 14] = _tz;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _li = 0; _li < 4; _li++) {
            int _lo1 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)), DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _li = 0; _li < 4; _li++) {
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 3]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 1]).mul(_sv3)))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 3]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, right[_lo0 + 1]).mul(_sv3)))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mulMat2x2(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat2x2_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x2_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat2x2_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat2x2_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat2x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat2x3_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x3_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat2x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).fma(_sv1, DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat2x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).mul(_sv1).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat3x3_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat3x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 8]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 6]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 8]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 6]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat3x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat3x4_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x4_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat3x4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        mulMat3x4_fma_sb641010c_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_fma_sb641010c_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.broadcast(_SIMD_SPECIES, _h9).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, _h10).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, _h11).fma(_sv0, DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        DoubleVector.broadcast(_SIMD_SPECIES, _h0).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h1).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, _h2).mul(_sv2))).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _h3).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h4).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, _h5).mul(_sv2))).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, _h6).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, _h7).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, _h8).mul(_sv2))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] mulMat3x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        mulMat3x4_mulAdd_s2aee6a03_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_mulAdd_s2aee6a03_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.broadcast(_SIMD_SPECIES, _h9).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, _h10).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, _h11).mul(_sv0).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        DoubleVector.broadcast(_SIMD_SPECIES, _h0).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h1).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, _h2).mul(_sv2))).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _h3).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h4).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, _h5).mul(_sv2))).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, _h6).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, _h7).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, _h8).mul(_sv2))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] preMul(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3])).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat3x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _other02 = other[otherOffset + 6];
        double _other12 = other[otherOffset + 7];
        double _other22 = other[otherOffset + 8];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]), DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _other02 = other[otherOffset + 6];
        double _other12 = other[otherOffset + 7];
        double _other22 = other[otherOffset + 8];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1])).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMulMat3x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat3x4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat3x4_fma_s962320cd_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_fma_s962320cd_v(double[] dest, int destOffset, double[] src, int srcOffset, double _other03, double _other13, double _other23, double _other02, double _other12, double _other22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv3.fma(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]), DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preMulMat3x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat3x4_mulAdd_s8b673d70_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_mulAdd_s8b673d70_v(double[] dest, int destOffset, double[] src, int srcOffset, double _other03, double _other13, double _other23, double _other02, double _other12, double _other22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3])).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1])).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static double[] addScaled_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] addScaled_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] makeOuterProduct(double[] dest, int destOffset, double colX, double colY, double colZ, double colW, double rowX, double rowY, double rowZ, double rowW) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, colX).withLane(1, colY).withLane(2, colZ).withLane(3, colW);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowX)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowY)).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowZ)).intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowW)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] makeOuterProduct(double[] dest, int destOffset, double[] col, int colOffset, double[] row, int rowOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, col, colOffset);
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 1]));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 2]));
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 3]));
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset])).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] arcball(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double centerX, double centerY, double centerZ, double angleX, double angleY) {
        if (SimdSupport.USE_FMA) return arcball_fma(dest, destOffset, src, srcOffset, radius, centerX, centerY, centerZ, angleX, angleY);
        return arcball_mulAdd(dest, destOffset, src, srcOffset, radius, centerX, centerY, centerZ, angleX, angleY);
    }

    public static double[] arcball_fma(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double centerX, double centerY, double centerZ, double angleX, double angleY) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleX);
        arcball_fma_se525cb58_v(dest, destOffset, src, srcOffset, radius, centerX, centerZ, _t0, _t1, _t2, _t3, -centerZ, -centerY, _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    private static void arcball_fma_se525cb58_v(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double centerX, double centerZ, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t4, _t0, -(centerX * _t2))), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(centerZ, _t9, java.lang.Math.fma(_t5, _t1, -(centerX * _t7)))), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(centerX, _t6, java.lang.Math.fma(_t5, _t3, java.lang.Math.fma(_t4, _t8, -radius)))), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -_t6), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t2), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t7)))).intoArray(dest, destOffset);
        _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t1), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t8), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t0), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t9)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] arcball_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double centerX, double centerY, double centerZ, double angleX, double angleY) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleX);
        arcball_mulAdd_s8516c9eb_v(dest, destOffset, src, srcOffset, radius, centerX, centerZ, _t0, _t1, _t2, _t3, -centerZ, -centerY, _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    private static void arcball_mulAdd_s8516c9eb_v(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double centerX, double centerZ, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t4) * (_t0) - (centerX * _t2)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((centerZ) * (_t9) + (((_t5) * (_t1) - (centerX * _t7)))))).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((centerX) * (_t6) + (((_t5) * (_t3) + (((_t4) * (_t8) - (radius)))))))).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t6)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t7)))).intoArray(dest, destOffset);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t8)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t9)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] arcball(double[] dest, int destOffset, double[] src, int srcOffset, double[] center, int centerOffset, double radius, double angleX, double angleY) {
        if (SimdSupport.USE_FMA) return arcball_fma(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
        return arcball_mulAdd(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
    }

    public static double[] arcball_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] center, int centerOffset, double radius, double angleX, double angleY) {
        double _centerz = center[centerOffset + 2];
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleX);
        arcball_fma_se525cb58_v(dest, destOffset, src, srcOffset, radius, center[centerOffset], _centerz, _t0, _t1, _t2, _t3, -_centerz, -center[centerOffset + 1], _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    public static double[] arcball_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] center, int centerOffset, double radius, double angleX, double angleY) {
        double _centerz = center[centerOffset + 2];
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleX);
        arcball_mulAdd_s8516c9eb_v(dest, destOffset, src, srcOffset, radius, center[centerOffset], _centerz, _t0, _t1, _t2, _t3, -_centerz, -center[centerOffset + 1], _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    public static double[] axonometricDimetric(double[] dest, int destOffset, double[] src, int srcOffset, double alpha) {
        if (SimdSupport.USE_FMA) return axonometricDimetric_fma(dest, destOffset, src, srcOffset, alpha);
        return axonometricDimetric_mulAdd(dest, destOffset, src, srcOffset, alpha);
    }

    public static double[] axonometricDimetric_fma(double[] dest, int destOffset, double[] src, int srcOffset, double alpha) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _t0 = Math.cosWithSin(alpha);
        double _t1 = 1.4142135623730951;
        double _t2 = Math.sinWithCos(alpha);
        double _t9 = 0.5 * _t0 * _t1;
        double _t10 = 0.5 * _t2 * _t1;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, 0.5);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self00 * _t1).withLane(1, _self10 * _t1).withLane(2, _self20 * _t1).withLane(3, _self30 * _t1);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -_t9), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t10), _sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t0), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t9), _sv2.fma(_sv3, _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t10)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] axonometricDimetric_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double alpha) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _t0 = Math.cosWithSin(alpha);
        double _t1 = 1.4142135623730951;
        double _t2 = Math.sinWithCos(alpha);
        double _t9 = 0.5 * _t0 * _t1;
        double _t10 = 0.5 * _t2 * _t1;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, 0.5);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _self00 * _t1).withLane(1, _self10 * _t1).withLane(2, _self20 * _t1).withLane(3, _self30 * _t1);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t9)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t10)).add(_sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t9)).add(_sv2.mul(_sv3).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t10)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] axonometricIsometric(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return axonometricIsometric_fma(dest, destOffset, src, srcOffset);
        return axonometricIsometric_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code axonometricIsometric} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] axonometricIsometric_fma(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        axonometricIsometric_fma_s7d5f6bc_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32);
        axonometricIsometric_fma_s7d5f6bc_2(dest, destOffset, _self20, _self30, _self21, _self31, _self22, _self32);
        _vcp0.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** Part 1 of {@code axonometricIsometric_fma}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_fma_s7d5f6bc_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32) {
        double _t0 = 1.7320508075688772;
        double _t1 = 1.4142135623730951;
        double _t2 = 2.449489742783178;
        double _sp0 = 0.16666666666666666 * _t2;
        double _t3 = _self02 * _t0;
        double _t4 = _self00 * _t1;
        double _t6 = _self12 * _t0;
        double _t7 = _self10 * _t1;
        double _t9 = _self22 * _t0;
        double _t12 = _self32 * _t0;
        double _t15 = _sp0 * _self01;
        double _t16 = _sp0 * _self11;
        dest[destOffset] = java.lang.Math.fma(-0.3333333333333333, _t3, java.lang.Math.fma(0.5, _t4, _t15));
        dest[destOffset + 1] = java.lang.Math.fma(-0.3333333333333333, _t6, java.lang.Math.fma(0.5, _t7, _t16));
        dest[destOffset + 2] = java.lang.Math.fma(-0.3333333333333333, _t9, java.lang.Math.fma(0.5, (_self20 * _t1), (_sp0 * _self21)));
        dest[destOffset + 3] = java.lang.Math.fma(-0.3333333333333333, _t12, java.lang.Math.fma(0.5, (_self30 * _t1), (_sp0 * _self31)));
        dest[destOffset + 4] = 0.3333333333333333 * java.lang.Math.fma(_self01, _t2, _t3);
        dest[destOffset + 5] = 0.3333333333333333 * java.lang.Math.fma(_self11, _t2, _t6);
        dest[destOffset + 6] = 0.3333333333333333 * java.lang.Math.fma(_self21, _t2, _t9);
        dest[destOffset + 7] = 0.3333333333333333 * java.lang.Math.fma(_self31, _t2, _t12);
        dest[destOffset + 8] = java.lang.Math.fma(0.3333333333333333, _t3, java.lang.Math.fma(0.5, _t4, -_t15));
        dest[destOffset + 9] = java.lang.Math.fma(0.3333333333333333, _t6, java.lang.Math.fma(0.5, _t7, -_t16));
    }

    /** Part 2 of {@code axonometricIsometric_fma}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_fma_s7d5f6bc_2(double[] dest, int destOffset, double _self20, double _self30, double _self21, double _self31, double _self22, double _self32) {
        double _t0 = 1.7320508075688772;
        double _t1 = 1.4142135623730951;
        double _sp0 = 0.16666666666666666 * (2.449489742783178);
        dest[destOffset + 10] = java.lang.Math.fma(0.3333333333333333, (_self22 * _t0), java.lang.Math.fma(0.5, (_self20 * _t1), -(_sp0 * _self21)));
        dest[destOffset + 11] = java.lang.Math.fma(0.3333333333333333, (_self32 * _t0), java.lang.Math.fma(0.5, (_self30 * _t1), -(_sp0 * _self31)));
    }

    /** {@code axonometricIsometric} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] axonometricIsometric_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        axonometricIsometric_mulAdd_sc10e497f_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12);
        axonometricIsometric_mulAdd_sc10e497f_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32);
        _vcp0.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** Part 1 of {@code axonometricIsometric_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_mulAdd_sc10e497f_1(double[] dest, int destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12) {
        double _t0 = 1.7320508075688772;
        double _t1 = 1.4142135623730951;
        double _sp0 = 0.16666666666666666 * (2.449489742783178);
        dest[destOffset] = ((((0.5) * ((_self00 * _t1)) + ((_sp0 * _self01)))) - (0.3333333333333333) * ((_self02 * _t0)));
        dest[destOffset + 1] = ((((0.5) * ((_self10 * _t1)) + ((_sp0 * _self11)))) - (0.3333333333333333) * ((_self12 * _t0)));
    }

    /** Part 2 of {@code axonometricIsometric_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_mulAdd_sc10e497f_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32) {
        double _t0 = 1.7320508075688772;
        double _t1 = 1.4142135623730951;
        double _t2 = 2.449489742783178;
        double _sp0 = 0.16666666666666666 * _t2;
        double _t3 = _self02 * _t0;
        double _t6 = _self12 * _t0;
        double _t9 = _self22 * _t0;
        double _t10 = _self20 * _t1;
        double _t12 = _self32 * _t0;
        double _t13 = _self30 * _t1;
        double _t17 = _sp0 * _self21;
        double _t18 = _sp0 * _self31;
        dest[destOffset + 2] = ((((0.5) * (_t10) + (_t17))) - (0.3333333333333333) * (_t9));
        dest[destOffset + 3] = ((((0.5) * (_t13) + (_t18))) - (0.3333333333333333) * (_t12));
        dest[destOffset + 4] = 0.3333333333333333 * ((_self01) * (_t2) + (_t3));
        dest[destOffset + 5] = 0.3333333333333333 * ((_self11) * (_t2) + (_t6));
        dest[destOffset + 6] = 0.3333333333333333 * ((_self21) * (_t2) + (_t9));
        dest[destOffset + 7] = 0.3333333333333333 * ((_self31) * (_t2) + (_t12));
        dest[destOffset + 8] = ((0.3333333333333333) * (_t3) + (((0.5) * ((_self00 * _t1)) - ((_sp0 * _self01)))));
        dest[destOffset + 9] = ((0.3333333333333333) * (_t6) + (((0.5) * ((_self10 * _t1)) - ((_sp0 * _self11)))));
        dest[destOffset + 10] = ((0.3333333333333333) * (_t9) + (((0.5) * (_t10) - (_t17))));
        dest[destOffset + 11] = ((0.3333333333333333) * (_t12) + (((0.5) * (_t13) - (_t18))));
    }

    public static double[] axonometricTrimetric(double[] dest, int destOffset, double[] src, int srcOffset, double alphaX, double alphaY) {
        if (SimdSupport.USE_FMA) return axonometricTrimetric_fma(dest, destOffset, src, srcOffset, alphaX, alphaY);
        return axonometricTrimetric_mulAdd(dest, destOffset, src, srcOffset, alphaX, alphaY);
    }

    public static double[] axonometricTrimetric_fma(double[] dest, int destOffset, double[] src, int srcOffset, double alphaX, double alphaY) {
        double _t0 = Math.sinWithCos(alphaY);
        double _t1 = Math.cosWithSin(alphaX);
        double _t2 = Math.cosWithSin(alphaY);
        double _t3 = Math.sinWithCos(alphaX);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t2), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3 * _t0)))).intoArray(dest, destOffset);
        _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t1), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t1 * _t2), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t0), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t3 * _t2))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] axonometricTrimetric_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double alphaX, double alphaY) {
        double _t0 = Math.sinWithCos(alphaY);
        double _t1 = Math.cosWithSin(alphaX);
        double _t2 = Math.cosWithSin(alphaY);
        double _t3 = Math.sinWithCos(alphaX);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3 * _t0)))).intoArray(dest, destOffset);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1 * _t2)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t3 * _t2))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        composeTRSMul_fma_s8387ac18_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, java.lang.Math.fma(rotationX, rotationZ, _t3) * _t0, java.lang.Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ), java.lang.Math.fma(-java.lang.Math.fma(rotationY, rotationY, _t4), _t1, scaleX), java.lang.Math.fma(rotationX, rotationY, _t5) * _t1, java.lang.Math.fma(rotationX, rotationZ, -_t3) * _t1, java.lang.Math.fma(rotationX, rotationY, -_t5) * _t2, java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, _t4), _t2, scaleY), java.lang.Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2);
        return dest;
    }

    private static void composeTRSMul_fma_s8387ac18_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = mOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0 + 3]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0 + 1]).fma(_sv3, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, m[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_s3467d619_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, ((rotationX) * (rotationZ) + (_t3)) * _t0, ((rotationY) * (rotationZ) - (rotationX * rotationW)) * _t0, ((scaleZ) - ((rotationX) * (rotationX) + (rotationY * rotationY)) * (_t0)), ((scaleX) - ((rotationY) * (rotationY) + (_t4)) * (_t1)), ((rotationX) * (rotationY) + (_t5)) * _t1, ((rotationX) * (rotationZ) - (_t3)) * _t1, ((rotationX) * (rotationY) - (_t5)) * _t2, ((scaleY) - ((rotationX) * (rotationX) + (_t4)) * (_t2)), ((rotationX) * (rotationW) + (rotationY * rotationZ)) * _t2);
        return dest;
    }

    private static void composeTRSMul_mulAdd_s3467d619_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = mOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0 + 3]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, m[_lo0 + 1]).mul(_sv3).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, m[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
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
        composeTRSMul_fma_s8387ac18_v(dest, destOffset, m, mOffset, translation[translationOffset], translation[translationOffset + 1], translation[translationOffset + 2], java.lang.Math.fma(_rotationx, _rotationz, _t3) * _t0, java.lang.Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), java.lang.Math.fma(-java.lang.Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), java.lang.Math.fma(_rotationx, _rotationy, _t5) * _t1, java.lang.Math.fma(_rotationx, _rotationz, -_t3) * _t1, java.lang.Math.fma(_rotationx, _rotationy, -_t5) * _t2, java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), java.lang.Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2);
        return dest;
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
        composeTRSMul_mulAdd_s3467d619_v(dest, destOffset, m, mOffset, translation[translationOffset], translation[translationOffset + 1], translation[translationOffset + 2], ((_rotationx) * (_rotationz) + (_t3)) * _t0, ((_rotationy) * (_rotationz) - (_rotationx * _rotationw)) * _t0, ((_scalez) - ((_rotationx) * (_rotationx) + (_rotationy * _rotationy)) * (_t0)), ((_scalex) - ((_rotationy) * (_rotationy) + (_t4)) * (_t1)), ((_rotationx) * (_rotationy) + (_t5)) * _t1, ((_rotationx) * (_rotationz) - (_t3)) * _t1, ((_rotationx) * (_rotationy) - (_t5)) * _t2, ((_scaley) - ((_rotationx) * (_rotationx) + (_t4)) * (_t2)), ((_rotationx) * (_rotationw) + (_rotationy * _rotationz)) * _t2);
        return dest;
    }

    public static double[] frustum_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _t12, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zFar;
                _w0 = 1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zFar;
                _w0 = -1.0;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zNear;
                _w0 = 1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zNear;
                _w0 = -((zFar + zNear) * _t3_inv);
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_lh_fma_sb9a6b0ad_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_fma_sb9a6b0ad_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0_inv, double _t1_inv, double _t12, double _w0, double _w1) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _t12, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zFar;
                _w0 = 1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zFar;
                _w0 = -1.0;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zNear;
                _w0 = 1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zNear;
                _w0 = -((zFar + zNear) * _t3_inv);
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_lh_mulAdd_s7fe1ff0e_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_mulAdd_s7fe1ff0e_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0_inv, double _t1_inv, double _t12, double _w0, double _w1) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _t12, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zFar;
                _w0 = -1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zFar;
                _w0 = 1.0;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zNear;
                _w0 = -1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zNear;
                _w0 = (zFar + zNear) * _t3_inv;
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_rh_fma_s4cd499ab_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_fma_s4cd499ab_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0_inv, double _t1_inv, double _t12, double _w0, double _w1) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * _t0_inv), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, (bottom + top) * _t1_inv))).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _t12, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zFar;
                _w0 = -1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zFar;
                _w0 = 1.0;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t12 = 2.0 * zNear;
                _w0 = -1.0;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0 * zNear;
                _w0 = (zFar + zNear) * _t3_inv;
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_rh_mulAdd_sba103a7c_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_mulAdd_sba103a7c_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0_inv, double _t1_inv, double _t12, double _w0, double _w1) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * _t0_inv)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, (bottom + top) * _t1_inv))).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t2_inv = 1.0 / (zNear - zFar);
        double _t9, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t9 = 2.0 * zFar;
                _w0 = 1.0;
                _w1 = -zNear;
            } else {
                _t9 = 2.0 * zFar;
                _w0 = 0.0;
                _w1 = zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t9 = 2.0 * zNear;
                _w0 = 1.0;
                _w1 = -zNear;
            } else {
                _t9 = 2.0 * zNear;
                _w0 = -(zFar * _t2_inv);
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_no_lh_fma_sb9a6b0ad_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t9, _w0, _w1);
        return dest;
    }

    public static double[] frustum_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t2_inv = 1.0 / (zNear - zFar);
        double _t9, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t9 = 2.0 * zFar;
                _w0 = 1.0;
                _w1 = -zNear;
            } else {
                _t9 = 2.0 * zFar;
                _w0 = 0.0;
                _w1 = zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t9 = 2.0 * zNear;
                _w0 = 1.0;
                _w1 = -zNear;
            } else {
                _t9 = 2.0 * zNear;
                _w0 = -(zFar * _t2_inv);
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_no_lh_mulAdd_s7fe1ff0e_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t9, _w0, _w1);
        return dest;
    }

    public static double[] frustum_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t2_inv = 1.0 / (zNear - zFar);
        double _t8, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t8 = 2.0 * zFar;
                _w0 = -1.0;
                _w1 = -zNear;
            } else {
                _t8 = 2.0 * zFar;
                _w0 = 0.0;
                _w1 = zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t8 = 2.0 * zNear;
                _w0 = -1.0;
                _w1 = -zNear;
            } else {
                _t8 = 2.0 * zNear;
                _w0 = zFar * _t2_inv;
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_no_rh_fma_s4cd499ab_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t8, _w0, _w1);
        return dest;
    }

    public static double[] frustum_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t2_inv = 1.0 / (zNear - zFar);
        double _t8, _w0, _w1;
        if (zNear == Double.POSITIVE_INFINITY) {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t8 = 2.0 * zFar;
                _w0 = -1.0;
                _w1 = -zNear;
            } else {
                _t8 = 2.0 * zFar;
                _w0 = 0.0;
                _w1 = zFar;
            }
        } else {
            if (zFar == Double.POSITIVE_INFINITY) {
                _t8 = 2.0 * zNear;
                _w0 = -1.0;
                _w1 = -zNear;
            } else {
                _t8 = 2.0 * zNear;
                _w0 = zFar * _t2_inv;
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_no_rh_mulAdd_sba103a7c_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0 / (right - left), 1.0 / (top - bottom), _t8, _w0, _w1);
        return dest;
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t11), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t37), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t38, _t13, -(_t39 * _t12)))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t13), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t39), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t12), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t38), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t37)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t38) * (_t13) - (_t39 * _t12)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t13)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t39)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t37) * (_t12) - (_t38 * _t11)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t38)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t39) * (_t11) - (_t37 * _t13)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -java.lang.Math.fma(upZ, _t16, java.lang.Math.fma(upX, _t15, upY * _t17));
        double _t22 = java.lang.Math.fma(_t21, _t17, upY);
        double _t23 = java.lang.Math.fma(_t21, _t15, upX);
        double _t24 = java.lang.Math.fma(_t21, _t16, upZ);
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        double _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        double _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        return lookAlong_rh_fma_s3419d583_1(dest, destOffset, src, srcOffset, _t15, _t16, _t17, _t41, _t42, _t43);
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_fma_s3419d583_1(double[] dest, int destOffset, double[] src, int srcOffset, double _t15, double _t16, double _t17, double _t41, double _t42, double _t43) {
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    private static void lookAlong_rh_fma_s60e1a003_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self02, double _self12, double _self22, double _self32, double _t15, double _t16, double _t17, double _t41, double _t42, double _t43) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t15), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t41), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t42, _t16, -(_t43 * _t17)))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t17), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t42), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t43, _t15, -(_t41 * _t16)))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t43), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t41, _t17, -(_t42 * _t15)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -((upZ) * (_t16) + (((upX) * (_t15) + (upY * _t17))));
        double _t22 = ((_t21) * (_t17) + (upY));
        double _t23 = ((_t21) * (_t15) + (upX));
        double _t24 = ((_t21) * (_t16) + (upZ));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        double _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        double _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        double _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    private static void lookAlong_rh_mulAdd_s61c041b4_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self02, double _self12, double _self22, double _self32, double _t15, double _t16, double _t17, double _t41, double _t42, double _t43) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t15)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t41)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t42) * (_t16) - (_t43 * _t17)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t17)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t42)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t43) * (_t15) - (_t41 * _t16)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t43)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t41) * (_t17) - (_t42 * _t15)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        double _t8 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -java.lang.Math.fma(_upz, _t16, java.lang.Math.fma(_upx, _t15, _upy * _t17));
        double _t22 = java.lang.Math.fma(_t21, _t17, _upy);
        double _t23 = java.lang.Math.fma(_t21, _t15, _upx);
        double _t24 = java.lang.Math.fma(_t21, _t16, _upz);
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        double _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        double _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        return lookAlong_rh_fma_s997ee389_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0 / java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_fma_s997ee389_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t15, double _t16, double _t17, double _t31, double _t32, double _t33, double _t36, double _t37) {
        double _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t8 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -((_upz) * (_t16) + (((_upx) * (_t15) + (_upy * _t17))));
        double _t22 = ((_t21) * (_t17) + (_upy));
        double _t23 = ((_t21) * (_t15) + (_upx));
        double _t24 = ((_t21) * (_t16) + (_upz));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        double _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        double _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        double _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        return lookAlong_rh_mulAdd_sebffd028_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0 / java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_mulAdd_sebffd028_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t15, double _t16, double _t17, double _t31, double _t32, double _t33, double _t36, double _t37) {
        double _t41, _t42, _t43;
        if (_t36 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
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
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -java.lang.Math.fma(upZ, _t16, java.lang.Math.fma(upX, _t15, upY * _t17));
        double _t22 = java.lang.Math.fma(_t21, _t17, upY);
        double _t23 = java.lang.Math.fma(_t21, _t15, upX);
        double _t24 = java.lang.Math.fma(_t21, _t16, upZ);
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        double _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        double _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        return lookAlong_rh_fma_sc1c87650_1(dest, destOffset, src, srcOffset, _t15, _t16, _t17, _t41, _t42, _t43);
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_fma_sc1c87650_1(double[] dest, int destOffset, double[] src, int srcOffset, double _t15, double _t16, double _t17, double _t41, double _t42, double _t43) {
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -((upZ) * (_t16) + (((upX) * (_t15) + (upY * _t17))));
        double _t22 = ((_t21) * (_t17) + (upY));
        double _t23 = ((_t21) * (_t15) + (upX));
        double _t24 = ((_t21) * (_t16) + (upZ));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        double _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        double _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        double _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
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
        double _t8 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -java.lang.Math.fma(_upz, _t16, java.lang.Math.fma(_upx, _t15, _upy * _t17));
        double _t22 = java.lang.Math.fma(_t21, _t17, _upy);
        double _t23 = java.lang.Math.fma(_t21, _t15, _upx);
        double _t24 = java.lang.Math.fma(_t21, _t16, _upz);
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        double _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        double _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        return lookAlong_rh_fma_s5252a35a_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0 / java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_fma_s5252a35a_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t15, double _t16, double _t17, double _t31, double _t32, double _t33, double _t36, double _t37) {
        double _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        lookAlong_rh_fma_s60e1a003_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t8 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -((_upz) * (_t16) + (((_upx) * (_t15) + (_upy * _t17))));
        double _t22 = ((_t21) * (_t17) + (_upy));
        double _t23 = ((_t21) * (_t15) + (_upx));
        double _t24 = ((_t21) * (_t16) + (_upz));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        double _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        double _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        double _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        return lookAlong_rh_mulAdd_sf68fd67b_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0 / java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_mulAdd_sf68fd67b_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _upz, double _upx, double _upy, double _t15, double _t16, double _t17, double _t31, double _t32, double _t33, double _t36, double _t37) {
        double _t41, _t42, _t43;
        if (_t36 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        lookAlong_rh_mulAdd_s61c041b4_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
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
        lookAt_lh_fma_s8c9f864_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
        return dest;
    }

    private static void lookAt_lh_fma_s8c9f864_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(eyeZ, _t44, java.lang.Math.fma(eyeX, _t43, eyeY * _t45))), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55))), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16))), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t14), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t43), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t54)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t45), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t55)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t15), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t44), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t56)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        lookAt_lh_mulAdd_se73a766f_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), -((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))), -((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))));
        return dest;
    }

    private static void lookAt_lh_mulAdd_se73a766f_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t14, double _t15, double _t16, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _h0, double _h1, double _h2) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t14)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t43)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t54)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t45)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t55)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t15)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t44)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t56)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -java.lang.Math.fma(upZ, _t19, java.lang.Math.fma(upX, _t18, upY * _t20));
        double _t28 = java.lang.Math.fma(_t27, _t20, upY);
        double _t29 = java.lang.Math.fma(_t27, _t18, upX);
        double _t30 = java.lang.Math.fma(_t27, _t19, upZ);
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = java.lang.Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39));
        return lookAt_rh_fma_sa88f0056_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0 / java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_sa88f0056_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39, double _t42, double _t43) {
        double _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_se2b0a512_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(eyeZ, _t49, java.lang.Math.fma(eyeX, _t47, eyeY * _t48)), -java.lang.Math.fma(eyeZ, _t60, java.lang.Math.fma(eyeX, _t58, eyeY * _t59)), java.lang.Math.fma(eyeZ, _t19, java.lang.Math.fma(eyeX, _t18, eyeY * _t20)));
        return dest;
    }

    private static void lookAt_rh_fma_se2b0a512_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t19, double _t20, double _t47, double _t48, double _t49, double _t58, double _t59, double _t60, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2).withLane(3, _h3);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h5), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t18), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t47), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t58)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t20), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t48), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t59)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t19), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t49), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t60)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -((upZ) * (_t19) + (((upX) * (_t18) + (upY * _t20))));
        double _t28 = ((_t27) * (_t20) + (upY));
        double _t29 = ((_t27) * (_t18) + (upX));
        double _t30 = ((_t27) * (_t19) + (upZ));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        double _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        double _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        double _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        return lookAt_rh_mulAdd_s2af21ec9_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t18, _t19, _t20, _t47, _t48, _t49, ((_t48) * (_t19) - (_t49 * _t20)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s2af21ec9_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t18, double _t19, double _t20, double _t47, double _t48, double _t49, double _t58) {
        double _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        double _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_sbf311395_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((eyeZ) * (_t49) + (((eyeX) * (_t47) + (eyeY * _t48)))), -((eyeZ) * (_t60) + (((eyeX) * (_t58) + (eyeY * _t59)))), ((eyeZ) * (_t19) + (((eyeX) * (_t18) + (eyeY * _t20)))));
        return dest;
    }

    private static void lookAt_rh_mulAdd_sbf311395_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t19, double _t20, double _t47, double _t48, double _t49, double _t58, double _t59, double _t60, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2).withLane(3, _h3);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, _h6)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t18)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t47)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t58)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t20)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t48)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t59)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t19)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t49)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t60)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        lookAt_lh_fma_s2eeb267e_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
        return dest;
    }

    private static void lookAt_lh_fma_s2eeb267e_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(_eyez, _t44, java.lang.Math.fma(_eyex, _t43, _eyey * _t45))), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55))), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16))), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t14), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t43), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t54)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t45), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t55)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t15), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t44), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t56)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        return lookAt_lh_mulAdd_s72af92c5_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_mulAdd_s72af92c5_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
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
        lookAt_lh_mulAdd_se73a766f_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), -((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))), -((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))));
        return dest;
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
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -java.lang.Math.fma(_upz, _t19, java.lang.Math.fma(_upx, _t18, _upy * _t20));
        double _t28 = java.lang.Math.fma(_t27, _t20, _upy);
        double _t29 = java.lang.Math.fma(_t27, _t18, _upx);
        double _t30 = java.lang.Math.fma(_t27, _t19, _upz);
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = java.lang.Math.fma(_t30, _t20, -(_t28 * _t19));
        return lookAt_rh_fma_sda93f074_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, _t37, _t38, _t39, java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_sda93f074_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39, double _t42) {
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_se2b0a512_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(_eyez, _t49, java.lang.Math.fma(_eyex, _t47, _eyey * _t48)), -java.lang.Math.fma(_eyez, _t60, java.lang.Math.fma(_eyex, _t58, _eyey * _t59)), java.lang.Math.fma(_eyez, _t19, java.lang.Math.fma(_eyex, _t18, _eyey * _t20)));
        return dest;
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -((_upz) * (_t19) + (((_upx) * (_t18) + (_upy * _t20))));
        double _t28 = ((_t27) * (_t20) + (_upy));
        double _t29 = ((_t27) * (_t18) + (_upx));
        double _t30 = ((_t27) * (_t19) + (_upz));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        double _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        double _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        double _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        return lookAt_rh_mulAdd_s6dacaa93_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0 / java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s6dacaa93_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39, double _t42, double _t43) {
        double _t47, _t48, _t49;
        if (_t42 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = ((_t48) * (_t19) - (_t49 * _t20));
        double _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        double _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_sbf311395_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((_eyez) * (_t49) + (((_eyex) * (_t47) + (_eyey * _t48)))), -((_eyez) * (_t60) + (((_eyex) * (_t58) + (_eyey * _t59)))), ((_eyez) * (_t19) + (((_eyex) * (_t18) + (_eyey * _t20)))));
        return dest;
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
        lookAt_lh_fma_s8c9f864_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
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
        lookAt_lh_mulAdd_se73a766f_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), -((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))), -((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))));
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -java.lang.Math.fma(upZ, _t19, java.lang.Math.fma(upX, _t18, upY * _t20));
        double _t28 = java.lang.Math.fma(_t27, _t20, upY);
        double _t29 = java.lang.Math.fma(_t27, _t18, upX);
        double _t30 = java.lang.Math.fma(_t27, _t19, upZ);
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = java.lang.Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39));
        return lookAt_rh_fma_s3b36c8b9_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0 / java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_s3b36c8b9_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double epsilon, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39, double _t42, double _t43) {
        double _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_se2b0a512_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(eyeZ, _t49, java.lang.Math.fma(eyeX, _t47, eyeY * _t48)), -java.lang.Math.fma(eyeZ, _t60, java.lang.Math.fma(eyeX, _t58, eyeY * _t59)), java.lang.Math.fma(eyeZ, _t19, java.lang.Math.fma(eyeX, _t18, eyeY * _t20)));
        return dest;
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -((upZ) * (_t19) + (((upX) * (_t18) + (upY * _t20))));
        double _t28 = ((_t27) * (_t20) + (upY));
        double _t29 = ((_t27) * (_t18) + (upX));
        double _t30 = ((_t27) * (_t19) + (upZ));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        double _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        double _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        double _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        return lookAt_rh_mulAdd_s1d9b4966_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t18, _t19, _t20, _t47, _t48, _t49, ((_t48) * (_t19) - (_t49 * _t20)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s1d9b4966_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t18, double _t19, double _t20, double _t47, double _t48, double _t49, double _t58) {
        double _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        double _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_sbf311395_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((eyeZ) * (_t49) + (((eyeX) * (_t47) + (eyeY * _t48)))), -((eyeZ) * (_t60) + (((eyeX) * (_t58) + (eyeY * _t59)))), ((eyeZ) * (_t19) + (((eyeX) * (_t18) + (eyeY * _t20)))));
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
        lookAt_lh_fma_s2eeb267e_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
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
        lookAt_lh_mulAdd_se73a766f_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), -((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))), -((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))));
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
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -java.lang.Math.fma(_upz, _t19, java.lang.Math.fma(_upx, _t18, _upy * _t20));
        double _t28 = java.lang.Math.fma(_t27, _t20, _upy);
        double _t29 = java.lang.Math.fma(_t27, _t18, _upx);
        double _t30 = java.lang.Math.fma(_t27, _t19, _upz);
        double _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = java.lang.Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = java.lang.Math.fma(_t30, _t20, -(_t28 * _t19));
        return lookAt_rh_fma_s260134d7_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, _t37, _t38, _t39, java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_s260134d7_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39, double _t42) {
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_se2b0a512_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(_eyez, _t49, java.lang.Math.fma(_eyex, _t47, _eyey * _t48)), -java.lang.Math.fma(_eyez, _t60, java.lang.Math.fma(_eyex, _t58, _eyey * _t59)), java.lang.Math.fma(_eyez, _t19, java.lang.Math.fma(_eyex, _t18, _eyey * _t20)));
        return dest;
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -((_upz) * (_t19) + (((_upx) * (_t18) + (_upy * _t20))));
        double _t28 = ((_t27) * (_t20) + (_upy));
        double _t29 = ((_t27) * (_t18) + (_upx));
        double _t30 = ((_t27) * (_t19) + (_upz));
        double _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        double _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        double _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        double _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        return lookAt_rh_mulAdd_s685c7200_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0 / java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s685c7200_1(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39, double _t42, double _t43) {
        double _t47, _t48, _t49;
        if (_t42 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = ((_t48) * (_t19) - (_t49 * _t20));
        double _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        double _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_sbf311395_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((_eyez) * (_t49) + (((_eyex) * (_t47) + (_eyey * _t48)))), -((_eyez) * (_t60) + (((_eyex) * (_t58) + (_eyey * _t59)))), ((_eyez) * (_t19) + (((_eyex) * (_t18) + (_eyey * _t20)))));
        return dest;
    }

    public static double[] mapXYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mapXYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueCabinet(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return obliqueCabinet_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueCabinet_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] obliqueCabinet_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(0.5 * Math.cosWithSin(angle))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(0.5 * Math.sinWithCos(angle))), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueCabinet_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(0.5 * Math.cosWithSin(angle)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(0.5 * Math.sinWithCos(angle)))).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueCavalier(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return obliqueCavalier_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueCavalier_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] obliqueCavalier_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -Math.cosWithSin(angle)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -Math.sinWithCos(angle)), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueCavalier_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -Math.cosWithSin(angle))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -Math.sinWithCos(angle))).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueMilitary(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return obliqueMilitary_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueMilitary_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] obliqueMilitary_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle)), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).neg()));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv1.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueMilitary_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle))).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv1.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliquePlanometric(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return obliquePlanometric_fma(dest, destOffset, src, srcOffset, angle);
        return obliquePlanometric_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] obliquePlanometric_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle));
        var _c1 = _sv1.add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t0), _sv1.mul(_sv2)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.fma(_sv2, _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliquePlanometric_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle));
        var _c1 = _sv1.add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv1.mul(_sv2)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.mul(_sv2).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_lh_fma(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        return obliqueZ_no_lh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
    }

    /** {@code obliqueZ_no_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t13 = planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t14 = _self23 * _t13;
        double _t18_inv = 1.0 / (java.lang.Math.fma(planeW, 1.0 - src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_no_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t13 = planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t14 = _self23 * _t13;
        double _t18_inv = 1.0 / (((planeW) * (1.0 - src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_rh_fma(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        return obliqueZ_no_rh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
    }

    /** {@code obliqueZ_no_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t13 = planeX * (src[srcOffset + 8] + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - planeZ;
        double _t14 = _self23 * _t13;
        double _t18_inv = 1.0 / (java.lang.Math.fma(planeW, 1.0 + src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_no_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t13 = planeX * (src[srcOffset + 8] + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - planeZ;
        double _t14 = _self23 * _t13;
        double _t18_inv = 1.0 / (((planeW) * (1.0 + src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_lh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        double _self23 = src[srcOffset + 14];
        double _t12 = planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (java.lang.Math.fma(planeW, 1.0 - src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t12 = planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (((planeW) * (1.0 - src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_rh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        double _self23 = src[srcOffset + 14];
        double _t12 = planeX * (src[srcOffset + 8] + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - planeZ;
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (java.lang.Math.fma(planeW, 1.0 + src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t12 = planeX * (src[srcOffset + 8] + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - planeZ;
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (((planeW) * (1.0 + src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_lh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_no_lh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_no_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self23 = src[srcOffset + 14];
        double _t13 = _planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t14 = _self23 * _t13;
        obliqueZ_no_lh_fma_v97764eb9(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0 / (java.lang.Math.fma(_planew, 1.0 - src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14)));
        return dest;
    }

    private static void obliqueZ_no_lh_fma_v97764eb9(double[] dest, int destOffset, double[] src, int srcOffset, double _planex, double _planey, double _planez, double _planew, double _t0, double _t18_inv) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    /** {@code obliqueZ_no_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self23 = src[srcOffset + 14];
        double _t13 = _planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t14 = _self23 * _t13;
        obliqueZ_no_lh_mulAdd_vc0a0c36a(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0 / (((_planew) * (1.0 - src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14))));
        return dest;
    }

    private static void obliqueZ_no_lh_mulAdd_vc0a0c36a(double[] dest, int destOffset, double[] src, int srcOffset, double _planex, double _planey, double _planez, double _planew, double _t0, double _t18_inv) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] obliqueZ_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_rh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_no_rh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_no_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self23 = src[srcOffset + 14];
        double _t13 = _planex * (src[srcOffset + 8] + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - _planez;
        double _t14 = _self23 * _t13;
        obliqueZ_no_rh_fma_vb33ab893(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0 / (java.lang.Math.fma(_planew, 1.0 + src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14)));
        return dest;
    }

    private static void obliqueZ_no_rh_fma_vb33ab893(double[] dest, int destOffset, double[] src, int srcOffset, double _planex, double _planey, double _planez, double _planew, double _t0, double _t18_inv) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    /** {@code obliqueZ_no_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self23 = src[srcOffset + 14];
        double _t13 = _planex * (src[srcOffset + 8] + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - _planez;
        double _t14 = _self23 * _t13;
        obliqueZ_no_rh_mulAdd_vde7f7134(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0 / (((_planew) * (1.0 + src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14))));
        return dest;
    }

    private static void obliqueZ_no_rh_mulAdd_vde7f7134(double[] dest, int destOffset, double[] src, int srcOffset, double _planex, double _planey, double _planez, double _planew, double _t0, double _t18_inv) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] obliqueZ_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_lh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_zo_lh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_zo_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _self23 = src[srcOffset + 14];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _t12 = _planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (java.lang.Math.fma(_planew, 1.0 - src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _self23 = src[srcOffset + 14];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _t12 = _planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5]);
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (((_planew) * (1.0 - src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_rh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_zo_rh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_zo_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _self23 = src[srcOffset + 14];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _t12 = _planex * (src[srcOffset + 8] + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - _planez;
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (java.lang.Math.fma(_planew, 1.0 + src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] obliqueZ_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _self23 = src[srcOffset + 14];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _t12 = _planex * (src[srcOffset + 8] + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - _planez;
        double _t13 = _self23 * _t12;
        double _t17_inv = 1.0 / (((_planew) * (1.0 + src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return ortho_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] ortho_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2_inv + _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2_inv + _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return ortho_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] ortho_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -2.0 * _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -2.0 * _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] ortho_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] ortho_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        if (SimdSupport.USE_FMA) return ortho2D_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static double[] ortho2D_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        if (SimdSupport.USE_FMA) return ortho2D_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static double[] ortho2D_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static double[] ortho2D_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, 0.5);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv2.fma(_sv3, _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(_SIMD_SPECIES, 0.5);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv2.mul(_sv3).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static double[] ortho2D_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.5).fma(_sv2, _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, -0.5).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.broadcast(_SIMD_SPECIES, 0.5).mul(_sv2).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, -0.5).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspective_no_lh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_no_lh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static double[] perspective_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t2_inv = 1.0 / (near - far);
        double _t6 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t2_inv);
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t2_inv = 1.0 / (near - far);
        double _t6 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t2_inv);
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspective_no_rh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_no_rh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static double[] perspective_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t2_inv = 1.0 / (near - far);
        double _t6 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t2_inv;
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg());
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t2_inv = 1.0 / (near - far);
        double _t6 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t2_inv;
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspective_zo_lh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_zo_lh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static double[] perspective_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t1_inv = 1.0 / (near - far);
        double _t3 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = -(far * _t1_inv);
                _w1 = far * near * _t1_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t1_inv = 1.0 / (near - far);
        double _t3 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = -(far * _t1_inv);
                _w1 = far * near * _t1_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspective_zo_rh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_zo_rh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static double[] perspective_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t1_inv = 1.0 / (near - far);
        double _t3 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = far * _t1_inv;
                _w1 = far * near * _t1_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg());
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspective_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t1_inv = 1.0 / (near - far);
        double _t3 = Math.tan(0.5 * fovy);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = far * _t1_inv;
                _w1 = far * near * _t1_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_no_lh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_no_lh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static double[] perspectiveFovRange_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
        double _t8_inv = 1.0 / _t8;
        double _t11_inv = 1.0 / (aspect * _t8);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t3_inv);
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t8_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
        double _t8_inv = 1.0 / _t8;
        double _t11_inv = 1.0 / (aspect * _t8);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t3_inv);
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t8_inv)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_no_rh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_no_rh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static double[] perspectiveFovRange_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
        double _t8_inv = 1.0 / _t8;
        double _t11_inv = 1.0 / (aspect * _t8);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t3_inv;
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t8_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg()));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
        double _t8_inv = 1.0 / _t8;
        double _t11_inv = 1.0 / (aspect * _t8);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t3_inv;
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t8_inv)).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_zo_lh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_zo_lh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static double[] perspectiveFovRange_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t4 = _t0 - _t1;
        double _t4_inv = 1.0 / _t4;
        double _t8_inv = 1.0 / (aspect * _t4);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = -(far * _t2_inv);
                _w1 = far * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t4_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t4 = _t0 - _t1;
        double _t4_inv = 1.0 / _t4;
        double _t8_inv = 1.0 / (aspect * _t4);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = -(far * _t2_inv);
                _w1 = far * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t4_inv)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_zo_rh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_zo_rh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static double[] perspectiveFovRange_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t4 = _t0 - _t1;
        double _t4_inv = 1.0 / _t4;
        double _t7_inv = 1.0 / (aspect * _t4);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = far * _t2_inv;
                _w1 = far * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t4_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg()));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t4 = _t0 - _t1;
        double _t4_inv = 1.0 / _t4;
        double _t7_inv = 1.0 / (aspect * _t4);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = far * _t2_inv;
                _w1 = far * near * _t2_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t4_inv)).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFrustumSlice_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _t0_inv = 1.0 / (near - far);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t0_inv);
                _w1 = (far + far) * near * _t0_inv;
            }
        }
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFrustumSlice_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _t0_inv = 1.0 / (near - far);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t0_inv;
                _w1 = (far + far) * near * _t0_inv;
            }
        }
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFrustumSlice_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _t0_inv = 1.0 / (near - far);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = -(far * _t0_inv);
                _w1 = far * near * _t0_inv;
            }
        }
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFrustumSlice_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _t0_inv = 1.0 / (near - far);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = far * _t0_inv;
                _w1 = far * near * _t0_inv;
            }
        }
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveOffCenterFov_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_no_lh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_no_lh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static double[] perspectiveOffCenterFov_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t5_inv = 1.0 / (near - far);
        double _t10_inv = 1.0 / (_t0 - _t1);
        double _t11_inv = 1.0 / (_t2 - _t3);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t5_inv);
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_lh_fma_s8b45defb_v(dest, destOffset, src, srcOffset, _w0, _w1, _t10_inv + _t10_inv, _t11_inv + _t11_inv, -(_t3 + _t2) * _t11_inv, -(_t1 + _t0) * _t10_inv);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_lh_fma_s8b45defb_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t5_inv = 1.0 / (near - far);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t5_inv);
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_lh_mulAdd_sd28419b4_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, 1.0 / (_t0 - _t1), 1.0 / (_t2 - _t3), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_lh_mulAdd_sd28419b4_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t10_inv, double _t11_inv, double _w0, double _w1) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t3 + _t2) * _t11_inv)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t1 + _t0) * _t10_inv)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t10_inv + _t10_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_no_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_no_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static double[] perspectiveOffCenterFov_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t5_inv = 1.0 / (near - far);
        double _t10_inv = 1.0 / (_t0 - _t1);
        double _t11_inv = 1.0 / (_t2 - _t3);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t5_inv;
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_rh_fma_s1abe499d_v(dest, destOffset, src, srcOffset, _w0, _w1, _t10_inv + _t10_inv, _t11_inv + _t11_inv, (_t1 + _t0) * _t10_inv, (_t3 + _t2) * _t11_inv);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_fma_s1abe499d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h3))).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t5_inv = 1.0 / (near - far);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -(near + near);
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t5_inv;
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_rh_mulAdd_sae5ccc5e_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, 1.0 / (_t0 - _t1), 1.0 / (_t2 - _t3), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_mulAdd_sae5ccc5e_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t10_inv, double _t11_inv, double _w0, double _w1) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (_t1 + _t0) * _t10_inv)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, (_t3 + _t2) * _t11_inv))).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t10_inv + _t10_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_lh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_lh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static double[] perspectiveOffCenterFov_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t4_inv = 1.0 / (near - far);
        double _t6_inv = 1.0 / (_t0 - _t1);
        double _t7_inv = 1.0 / (_t2 - _t3);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = -(far * _t4_inv);
                _w1 = far * near * _t4_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(_t3 + _t2) * _t7_inv), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, -(_t1 + _t0) * _t6_inv), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t6_inv + _t6_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveOffCenterFov_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t4_inv = 1.0 / (near - far);
        double _t6_inv = 1.0 / (_t0 - _t1);
        double _t7_inv = 1.0 / (_t2 - _t3);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = -(far * _t4_inv);
                _w1 = far * near * _t4_inv;
            }
        }
        perspectiveOffCenterFov_zo_lh_mulAdd_sc4dd1958_v(dest, destOffset, src, srcOffset, _w0, _w1, _t6_inv + _t6_inv, _t7_inv + _t7_inv, -(_t3 + _t2) * _t7_inv, -(_t1 + _t0) * _t6_inv);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_lh_mulAdd_sc4dd1958_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h3)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static double[] perspectiveOffCenterFov_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t4_inv = 1.0 / (near - far);
        double _t6_inv = 1.0 / (_t0 - _t1);
        double _t7_inv = 1.0 / (_t2 - _t3);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = far * _t4_inv;
                _w1 = far * near * _t4_inv;
            }
        }
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, (_t1 + _t0) * _t6_inv), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, (_t3 + _t2) * _t7_inv))).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t6_inv + _t6_inv)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveOffCenterFov_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t4_inv = 1.0 / (near - far);
        double _t6_inv = 1.0 / (_t0 - _t1);
        double _t7_inv = 1.0 / (_t2 - _t3);
        double _w0, _w1;
        if (far == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = far;
            } else {
                _w0 = far * _t4_inv;
                _w1 = far * near * _t4_inv;
            }
        }
        perspectiveOffCenterFov_zo_rh_mulAdd_sf26674fa_v(dest, destOffset, src, srcOffset, _w0, _w1, _t6_inv + _t6_inv, _t7_inv + _t7_inv, (_t1 + _t0) * _t6_inv, (_t3 + _t2) * _t7_inv);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_rh_mulAdd_sf26674fa_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h3))).sub(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static double[] pickMatrix(double[] dest, int destOffset, double[] src, int srcOffset, double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH) {
        if (SimdSupport.USE_FMA) return pickMatrix_fma(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
        return pickMatrix_mulAdd(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
    }

    public static double[] pickMatrix_fma(double[] dest, int destOffset, double[] src, int srcOffset, double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH) {
        double _rcp0 = 1.0 / deltaX;
        double _rcp1 = 1.0 / deltaY;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, centerX - vpX, vpW) * _rcp0), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, centerY - vpY, vpH) * _rcp1))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, vpW * _rcp0)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, vpH * _rcp1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] pickMatrix_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH) {
        double _rcp0 = 1.0 / deltaX;
        double _rcp1 = 1.0 / deltaY;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((vpW) - (2.0) * (centerX - vpX)) * _rcp0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((vpH) - (2.0) * (centerY - vpY)) * _rcp1))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, vpW * _rcp0)).intoArray(dest, destOffset);
        _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, vpH * _rcp1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static double[] preRotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -pivotZ;
        double _t1 = -rotY;
        double _t3 = -rotX;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t7;
        double _t10 = rotW * _t7;
        double _t11 = rotW * _t5;
        double _t16 = java.lang.Math.fma(-rotZ, _t7, 1.0);
        double _t18 = java.lang.Math.fma(rotZ, _t5, _t8);
        double _t21 = java.lang.Math.fma(rotY, _t5, _t10);
        double _t22 = java.lang.Math.fma(rotZ, _t6, _t11);
        double _t24 = java.lang.Math.fma(rotY, _t5, -_t10);
        double _t25 = java.lang.Math.fma(rotZ, _t6, -_t11);
        double _t26 = java.lang.Math.fma(rotZ, _t5, -_t8);
        preRotateAround_fma_s5306fd9d_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, java.lang.Math.fma(_t0, _t18, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t6, _t9), -(pivotY * _t24))), java.lang.Math.fma(_t0, _t25, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t5, _t9), -(pivotX * _t21))), java.lang.Math.fma(-pivotY, _t22, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t26))), java.lang.Math.fma(_t3, _t5, java.lang.Math.fma(_t1, _t6, 1.0)), java.lang.Math.fma(_t1, _t6, _t16), java.lang.Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_fma_s5306fd9d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t21, double _t22, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv3, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -pivotZ;
        double _t1 = -rotY;
        double _t3 = -rotX;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t7;
        double _t10 = rotW * _t7;
        double _t11 = rotW * _t5;
        double _t16 = ((1.0) - (rotZ) * (_t7));
        double _t18 = ((rotZ) * (_t5) + (_t8));
        double _t21 = ((rotY) * (_t5) + (_t10));
        double _t22 = ((rotZ) * (_t6) + (_t11));
        double _t24 = ((rotY) * (_t5) - (_t10));
        double _t25 = ((rotZ) * (_t6) - (_t11));
        double _t26 = ((rotZ) * (_t5) - (_t8));
        preRotateAround_mulAdd_s3b7753b6_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, ((_t0) * (_t18) + (((pivotX) * (((rotY) * (_t6) + (_t9))) - (pivotY * _t24)))), ((_t0) * (_t25) + (((pivotY) * (((rotX) * (_t5) + (_t9))) - (pivotX * _t21)))), ((((pivotZ) * (((rotX) * (_t5) + (rotY * _t6))) - (pivotX * _t26))) - (pivotY) * (_t22)), ((_t3) * (_t5) + (((_t1) * (_t6) + (1.0)))), ((_t1) * (_t6) + (_t16)), ((_t3) * (_t5) + (_t16)));
        return dest;
    }

    private static void preRotateAround_mulAdd_s3b7753b6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t21, double _t22, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv3).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
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
        double _t1 = -_roty;
        double _t3 = -_rotx;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t7;
        double _t11 = _rotw * _t5;
        double _t16 = java.lang.Math.fma(-_rotz, _t7, 1.0);
        double _t22 = java.lang.Math.fma(_rotz, _t6, _t11);
        double _t26 = java.lang.Math.fma(_rotz, _t5, -_t8);
        preRotateAround_fma_sbdfa813d_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t5, _t6, _rotz * _t7, java.lang.Math.fma(_rotz, _t5, _t8), java.lang.Math.fma(_roty, _t5, _t10), _t22, java.lang.Math.fma(_roty, _t5, -_t10), java.lang.Math.fma(_rotz, _t6, -_t11), _t26, java.lang.Math.fma(-_pivoty, _t22, java.lang.Math.fma(_pivotz, java.lang.Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t26))), java.lang.Math.fma(_t3, _t5, java.lang.Math.fma(_t1, _t6, 1.0)), java.lang.Math.fma(_t1, _t6, _t16), java.lang.Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_fma_sbdfa813d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _rotx, double _t0, double _t5, double _t6, double _t9, double _t18, double _t21, double _t22, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t18, java.lang.Math.fma(_pivotx, java.lang.Math.fma(_roty, _t6, _t9), -(_pivoty * _t24)))).withLane(1, java.lang.Math.fma(_t0, _t25, java.lang.Math.fma(_pivoty, java.lang.Math.fma(_rotx, _t5, _t9), -(_pivotx * _t21)))).withLane(2, _h0);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h1);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h2).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h3).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv3, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t1 = -_roty;
        double _t3 = -_rotx;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t7;
        double _t11 = _rotw * _t5;
        double _t16 = ((1.0) - (_rotz) * (_t7));
        double _t22 = ((_rotz) * (_t6) + (_t11));
        double _t26 = ((_rotz) * (_t5) - (_t8));
        preRotateAround_mulAdd_sd915c628_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t5, _t6, _rotz * _t7, ((_rotz) * (_t5) + (_t8)), ((_roty) * (_t5) + (_t10)), _t22, ((_roty) * (_t5) - (_t10)), ((_rotz) * (_t6) - (_t11)), _t26, ((((_pivotz) * (((_rotx) * (_t5) + (_roty * _t6))) - (_pivotx * _t26))) - (_pivoty) * (_t22)), ((_t3) * (_t5) + (((_t1) * (_t6) + (1.0)))), ((_t1) * (_t6) + (_t16)), ((_t3) * (_t5) + (_t16)));
        return dest;
    }

    private static void preRotateAround_mulAdd_sd915c628_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _rotx, double _t0, double _t5, double _t6, double _t9, double _t18, double _t21, double _t22, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t18) + (((_pivotx) * (((_roty) * (_t6) + (_t9))) - (_pivoty * _t24))))).withLane(1, ((_t0) * (_t25) + (((_pivoty) * (((_rotx) * (_t5) + (_t9))) - (_pivotx * _t21))))).withLane(2, _h0);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h1);
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _h2).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h3).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv3).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] preRotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t1 = Math.cosWithSin(angle);
        preRotateAxis_fma_s995bbb1c_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, Math.sinWithCos(angle), _t1, 1.0 - _t1, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void preRotateAxis_fma_s995bbb1c_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t3, double _t5, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(axisY, _t0, _t2 * _t3)).withLane(1, java.lang.Math.fma(_t2, _t7, -(axisX * _t0))).withLane(2, java.lang.Math.fma(_t2, axisZ * axisZ, _t1));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, axisX * axisX, _t1)).withLane(1, java.lang.Math.fma(axisZ, _t0, _t2 * _t5)).withLane(2, java.lang.Math.fma(_t2, _t3, -(axisY * _t0)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _t5, -(axisZ * _t0))).withLane(1, java.lang.Math.fma(_t2, axisY * axisY, _t1)).withLane(2, java.lang.Math.fma(axisX, _t0, _t2 * _t7));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t1 = Math.cosWithSin(angle);
        preRotateAxis_mulAdd_sbf6928a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, Math.sinWithCos(angle), _t1, 1.0 - _t1, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void preRotateAxis_mulAdd_sbf6928a1_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t3, double _t5, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((axisY) * (_t0) + (_t2 * _t3))).withLane(1, ((_t2) * (_t7) - (axisX * _t0))).withLane(2, ((_t2) * (axisZ * axisZ) + (_t1)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (axisX * axisX) + (_t1))).withLane(1, ((axisZ) * (_t0) + (_t2 * _t5))).withLane(2, ((_t2) * (_t3) - (axisY * _t0)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_t5) - (axisZ * _t0))).withLane(1, ((_t2) * (axisY * axisY) + (_t1))).withLane(2, ((axisX) * (_t0) + (_t2 * _t7)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_axisy, _t0, _t2 * _t3)).withLane(1, java.lang.Math.fma(_t2, _t7, -(_axisx * _t0))).withLane(2, java.lang.Math.fma(_t2, _axisz * _axisz, _t1));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _axisx * _axisx, _t1)).withLane(1, java.lang.Math.fma(_axisz, _t0, _t2 * _t5)).withLane(2, java.lang.Math.fma(_t2, _t3, -(_axisy * _t0)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _t5, -(_axisz * _t0))).withLane(1, java.lang.Math.fma(_t2, _axisy * _axisy, _t1)).withLane(2, java.lang.Math.fma(_axisx, _t0, _t2 * _t7));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_axisy) * (_t0) + (_t2 * _t3))).withLane(1, ((_t2) * (_t7) - (_axisx * _t0))).withLane(2, ((_t2) * (_axisz * _axisz) + (_t1)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_axisx * _axisx) + (_t1))).withLane(1, ((_axisz) * (_t0) + (_t2 * _t5))).withLane(2, ((_t2) * (_t3) - (_axisy * _t0)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_t5) - (_axisz * _t0))).withLane(1, ((_t2) * (_axisy * _axisy) + (_t1))).withLane(2, ((_axisx) * (_t0) + (_t2 * _t7)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static double[] preRotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        preRotateQuat_fma_s872a8523_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, java.lang.Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_fma_s872a8523_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(qZ, _t3, _t6)).withLane(1, java.lang.Math.fma(qZ, _t4, -_t8)).withLane(2, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t4, _t12)).withLane(1, java.lang.Math.fma(qY, _t3, _t7)).withLane(2, java.lang.Math.fma(qZ, _t3, -_t6));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(qY, _t3, -_t7)).withLane(1, java.lang.Math.fma(_t2, _t3, _t12)).withLane(2, java.lang.Math.fma(qZ, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((qZ) * (_t3) + (_t6))).withLane(1, ((qZ) * (_t4) - (_t8))).withLane(2, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t4) + (_t12))).withLane(1, ((qY) * (_t3) + (_t7))).withLane(2, ((qZ) * (_t3) - (_t6)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((qY) * (_t3) - (_t7))).withLane(1, ((_t2) * (_t3) + (_t12))).withLane(2, ((qZ) * (_t4) + (_t8)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_qz, _t3, _t6)).withLane(1, java.lang.Math.fma(_qz, _t4, -_t8)).withLane(2, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t4, _t12)).withLane(1, java.lang.Math.fma(_qy, _t3, _t7)).withLane(2, java.lang.Math.fma(_qz, _t3, -_t6));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_qy, _t3, -_t7)).withLane(1, java.lang.Math.fma(_t2, _t3, _t12)).withLane(2, java.lang.Math.fma(_qz, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_qz) * (_t3) + (_t6))).withLane(1, ((_qz) * (_t4) - (_t8))).withLane(2, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))));
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t4) + (_t12))).withLane(1, ((_qy) * (_t3) + (_t7))).withLane(2, ((_qz) * (_t3) - (_t6)));
        var _sv2 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, ((_qy) * (_t3) - (_t7))).withLane(1, ((_t2) * (_t3) + (_t12))).withLane(2, ((_qz) * (_t4) + (_t8)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static double[] preScale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] preScale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, pivotX * _t0).withLane(1, pivotY * _t0).withLane(2, pivotZ * _t0);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).fma(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0), DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, pivotX * _t0).withLane(1, pivotY * _t0).withLane(2, pivotZ * _t0);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).fma(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0), DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, pivotX * (1.0 - sX)).withLane(1, pivotY * (1.0 - sY)).withLane(2, pivotZ * (1.0 - sZ));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0), DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, pivotX * (1.0 - sX)).withLane(1, pivotY * (1.0 - sY)).withLane(2, pivotZ * (1.0 - sZ));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
        }
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
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0 - _sx)).withLane(1, _pivoty * (1.0 - _sy)).withLane(2, _pivotz * (1.0 - _sz));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0), DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0 - _sx)).withLane(1, _pivoty * (1.0 - _sy)).withLane(2, _pivotz * (1.0 - _sz));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) return preTranslate_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return preTranslate_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static double[] preTranslate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preTranslate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preTranslate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preTranslate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] preTranslate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preTranslate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        reflect_fma_sdd385ec2_v(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], (normalX + normalX) * normalZ, (normalY + normalY) * normalZ);
        return dest;
    }

    private static void reflect_fma_sdd385ec2_v(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double _self02, double _self12, double _self22, double _self32, double _t10, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, normalZ * normalZ, 1.0)), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -_t12), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t10), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, normalX * normalX, 1.0)), _sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t12), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, normalY * normalY, 1.0)), _sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        reflect_mulAdd_sdd26ac6b_v(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], (normalX + normalX) * normalZ, (normalY + normalY) * normalZ);
        return dest;
    }

    private static void reflect_mulAdd_sdd26ac6b_v(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double _self02, double _self12, double _self22, double _self32, double _t10, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, ((1.0) - (2.0) * (normalZ * normalZ)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t12)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t10)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((1.0) - (2.0) * (normalX * normalX)))).add(_sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((1.0) - (2.0) * (normalY * normalY)))).add(_sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        reflect_fma_s54924c8a_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_fma_s54924c8a_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self02, double _self12, double _self22, double _self32, double _normalx, double _normaly, double _normalz, double _t10, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, _normalz * _normalz, 1.0)), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, -_t12), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t10), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, _normalx * _normalx, 1.0)), _sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t12), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, _normaly * _normaly, 1.0)), _sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        reflect_mulAdd_sd09fdfb9_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_mulAdd_sd09fdfb9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self02, double _self12, double _self22, double _self32, double _normalx, double _normaly, double _normalz, double _t10, double _t12) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(_SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, ((1.0) - (2.0) * (_normalz * _normalz)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t12)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t10)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((1.0) - (2.0) * (_normalx * _normalx)))).add(_sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((1.0) - (2.0) * (_normaly * _normaly)))).add(_sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static double[] rotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t16 = java.lang.Math.fma(-rotZ, _t7, 1.0);
        double _t18 = java.lang.Math.fma(rotY, _t5, _t9);
        double _t19 = java.lang.Math.fma(rotZ, _t6, _t10);
        double _t20 = java.lang.Math.fma(rotZ, _t5, _t8);
        double _t24 = java.lang.Math.fma(rotZ, _t5, -_t8);
        double _t25 = java.lang.Math.fma(rotY, _t5, -_t9);
        double _t26 = java.lang.Math.fma(rotZ, _t6, -_t10);
        rotateAround_fma_s9107c306_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, java.lang.Math.fma(_t0, _t6, _t16), java.lang.Math.fma(_t2, _t5, _t16), java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0)), java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), java.lang.Math.fma(-pivotY, _t19, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s9107c306_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h5), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t24), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t18)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t19), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t25), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t20), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t26)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t16 = ((1.0) - (rotZ) * (_t7));
        double _t18 = ((rotY) * (_t5) + (_t9));
        double _t19 = ((rotZ) * (_t6) + (_t10));
        double _t20 = ((rotZ) * (_t5) + (_t8));
        double _t24 = ((rotZ) * (_t5) - (_t8));
        double _t25 = ((rotY) * (_t5) - (_t9));
        double _t26 = ((rotZ) * (_t6) - (_t10));
        rotateAround_mulAdd_s6f860be3_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, ((_t0) * (_t6) + (_t16)), ((_t2) * (_t5) + (_t16)), ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0)))), ((_t3) * (_t20) + (((pivotX) * (((rotY) * (_t6) + (_t11))) - (pivotY * _t25)))), ((_t3) * (_t26) + (((pivotY) * (((rotX) * (_t5) + (_t11))) - (pivotX * _t18)))), ((((pivotZ) * (((rotX) * (_t5) + (rotY * _t6))) - (pivotX * _t24))) - (pivotY) * (_t19)));
        return dest;
    }

    private static void rotateAround_mulAdd_s6f860be3_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h3)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t24)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t18)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t19)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t25)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t20)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t26)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t16 = java.lang.Math.fma(-_rotz, _t7, 1.0);
        double _t19 = java.lang.Math.fma(_rotz, _t6, _t10);
        double _t24 = java.lang.Math.fma(_rotz, _t5, -_t8);
        rotateAround_fma_s3319d5f8_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t5, _t6, _rotz * _t7, java.lang.Math.fma(_roty, _t5, _t9), _t19, java.lang.Math.fma(_rotz, _t5, _t8), _t24, java.lang.Math.fma(_roty, _t5, -_t9), java.lang.Math.fma(_rotz, _t6, -_t10), java.lang.Math.fma(_t0, _t6, _t16), java.lang.Math.fma(_t2, _t5, _t16), java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0)), java.lang.Math.fma(-_pivoty, _t19, java.lang.Math.fma(_pivotz, java.lang.Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s3319d5f8_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _rotx, double _t3, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(_pivotx, java.lang.Math.fma(_roty, _t6, _t11), -(_pivoty * _t25)))), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(_pivoty, java.lang.Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)))), _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t24), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t18)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t19), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t25), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h1)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t20), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t26)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _roty = rot[rotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        return rotateAround_mulAdd_s6b6a071b_1(dest, destOffset, src, srcOffset, _roty, _rotx, _pivotz, pivot[pivotOffset], pivot[pivotOffset + 1], -_roty, -_rotx, -_pivotz, _t5, _t6, _rotz * _t7, ((1.0) - (_rotz) * (_t7)), ((_roty) * (_t5) + (_t9)), ((_rotz) * (_t6) + (_t10)), ((_rotz) * (_t5) + (_t8)), ((_rotz) * (_t5) - (_t8)), ((_roty) * (_t5) - (_t9)), ((_rotz) * (_t6) - (_t10)));
    }

    /** Piece 2 of {@code rotateAround_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAround_mulAdd_s6b6a071b_1(double[] dest, int destOffset, double[] src, int srcOffset, double _roty, double _rotx, double _pivotz, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26) {
        rotateAround_mulAdd_s6f860be3_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, ((_t0) * (_t6) + (_t16)), ((_t2) * (_t5) + (_t16)), ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0)))), ((_t3) * (_t20) + (((_pivotx) * (((_roty) * (_t6) + (_t11))) - (_pivoty * _t25)))), ((_t3) * (_t26) + (((_pivoty) * (((_rotx) * (_t5) + (_t11))) - (_pivotx * _t18)))), ((((_pivotz) * (((_rotx) * (_t5) + (_roty * _t6))) - (_pivotx * _t24))) - (_pivoty) * (_t19)));
        return dest;
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, -(axisY * _t1))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, axisX * axisX, _t0)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(axisZ, _t1, _t2 * _t6))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(axisX, _t1, _t2 * _t7)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t6, -(axisZ * _t1))), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, axisY * axisY, _t0))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, axisZ * axisZ, _t0)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(axisY, _t1, _t2 * _t3)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t7, -(axisX * _t1)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        rotateAxis_mulAdd_s461c3d92_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, Math.sinWithCos(angle), 1.0 - _t0, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void rotateAxis_mulAdd_s461c3d92_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t3, double _t6, double _t7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (axisY * _t1)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisX * axisX) + (_t0)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((axisZ) * (_t1) + (_t2 * _t6)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((axisX) * (_t1) + (_t2 * _t7)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t6) - (axisZ * _t1)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisY * axisY) + (_t0)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisZ * axisZ) + (_t0)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((axisY) * (_t1) + (_t2 * _t3)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t7) - (axisX * _t1)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, -(_axisy * _t1))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisx * _axisx, _t0)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisz, _t1, _t2 * _t6))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisx, _t1, _t2 * _t7)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t6, -(_axisz * _t1))), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisy * _axisy, _t0))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisz * _axisz, _t0)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisy, _t1, _t2 * _t3)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t7, -(_axisx * _t1)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (_axisy * _t1)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisx * _axisx) + (_t0)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_axisz) * (_t1) + (_t2 * _t6)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_axisx) * (_t1) + (_t2 * _t7)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t6) - (_axisz * _t1)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisy * _axisy) + (_t0)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisz * _axisz) + (_t0)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_axisy) * (_t1) + (_t2 * _t3)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t7) - (_axisx * _t1)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t3, -_t6)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qY, _t3, _t7))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t4, _t8)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qY, _t3, -_t7)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t3, _t6)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t4, -_t8))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t3) - (_t6)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t4) + (_t12)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qY) * (_t3) + (_t7)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t4) + (_t8)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qY) * (_t3) - (_t7)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (_t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t3) + (_t6)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t4) - (_t8)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t3, -_t6)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qy, _t3, _t7))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t4, _t8)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qy, _t3, -_t7)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t3, _t6)), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t4, -_t8))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t3) - (_t6)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t4) + (_t12)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qy) * (_t3) + (_t7)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t4) + (_t8)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qy) * (_t3) - (_t7)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (_t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0)))))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t3) + (_t6)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t4) - (_t8)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t37), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t38), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t39)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13))), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t37, _t13, -(_t39 * _t11))), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t11), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t12), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t13)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t37)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t38)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t39)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t39) * (_t12) - (_t38 * _t13)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t37) * (_t13) - (_t39 * _t11)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t38) * (_t11) - (_t37 * _t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t11)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t13)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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

    public static double[] rotateX(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateX_fma(dest, destOffset, src, srcOffset, angle);
        return rotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] rotateX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset + 4);
        _sv2.fma(_sv1, _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset + 4);
        _sv2.mul(_sv1).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateXYZ(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (SimdSupport.USE_FMA) return rotateXYZ_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    public static double[] rotateXYZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.cosWithSin(angleX);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleY);
        double _t6 = _t2 * _t3;
        double _t8 = _t0 * _t2;
        rotateXYZ_fma_sa4cfd303_v(dest, destOffset, src, srcOffset, _t2, java.lang.Math.fma(_t0, _t1, -(_t6 * _t4)), _t5 * _t4, java.lang.Math.fma(_t8, _t4, _t1 * _t3), java.lang.Math.fma(_t6, _t1, _t0 * _t4), java.lang.Math.fma(_t3, _t4, -(_t8 * _t1)), -(_t1 * _t5), _t3 * _t5, -(_t0 * _t5));
        return dest;
    }

    private static void rotateXYZ_fma_sa4cfd303_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t2, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t2), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h7)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateXYZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.cosWithSin(angleX);
        rotateXYZ_mulAdd_s46fc8de6_v(dest, destOffset, src, srcOffset, _t0, Math.sinWithCos(angleZ), _t2, _t3, Math.cosWithSin(angleZ), Math.cosWithSin(angleY), _t2 * _t3, _t0 * _t2);
        return dest;
    }

    private static void rotateXYZ_mulAdd_s46fc8de6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t1) - (_t6 * _t4)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t5 * _t4)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t4) + (_t1 * _t3)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t1) + (_t0 * _t4)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t3) * (_t4) - (_t8 * _t1)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t1 * _t5))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3 * _t5)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 * _t5))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateXZY(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (SimdSupport.USE_FMA) return rotateXZY_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    public static double[] rotateXZY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.cosWithSin(angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        rotateXZY_fma_se755b3f7_v(dest, destOffset, src, srcOffset, java.lang.Math.fma(_t6, _t2, -(_t3 * _t4)), _t2 * _t5, java.lang.Math.fma(_t9, _t2, _t0 * _t3), _t0 * _t5, _t4 * _t5, -_t1, java.lang.Math.fma(_t6, _t3, _t4 * _t2), _t3 * _t5, java.lang.Math.fma(_t9, _t3, -(_t0 * _t2)));
        return dest;
    }

    private static void rotateXZY_fma_se755b3f7_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h7), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateXZY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t4 = Math.cosWithSin(angleX);
        rotateXZY_mulAdd_s155e0d8a_v(dest, destOffset, src, srcOffset, _t0, _t1, Math.cosWithSin(angleY), Math.sinWithCos(angleY), _t4, Math.cosWithSin(angleZ), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateXZY_mulAdd_s155e0d8a_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t9) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t2) - (_t3 * _t4)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2 * _t5)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t2) + (_t0 * _t3)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t0 * _t5)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4 * _t5)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t3) + (_t4 * _t2)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3 * _t5)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t3) - (_t0 * _t2)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateY(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateY_fma(dest, destOffset, src, srcOffset, angle);
        return rotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] rotateY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t1), _sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        double _t8 = _t0 * _t3;
        rotateYXZ_fma_s75ba388b_v(dest, destOffset, src, srcOffset, java.lang.Math.fma(_t6, _t2, -(_t3 * _t4)), java.lang.Math.fma(_t8, _t2, _t1 * _t4), _t2 * _t5, java.lang.Math.fma(_t6, _t4, _t3 * _t2), java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)), _t5 * _t4, _t5 * _t1, _t3 * _t5, -_t0);
        return dest;
    }

    private static void rotateYXZ_fma_s75ba388b_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h4), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h7), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateYXZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleY);
        rotateYXZ_mulAdd_sc6df9532_v(dest, destOffset, src, srcOffset, _t0, _t1, Math.sinWithCos(angleZ), _t3, Math.cosWithSin(angleZ), Math.cosWithSin(angleX), _t0 * _t1, _t0 * _t3);
        return dest;
    }

    private static void rotateYXZ_mulAdd_sc6df9532_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t2) - (_t3 * _t4)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t2) + (_t1 * _t4)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2 * _t5)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t4) + (_t3 * _t2)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t4) - (_t2 * _t1)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t5 * _t4)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t5 * _t1)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3 * _t5)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t0)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateYZX(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (SimdSupport.USE_FMA) return rotateYZX_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    public static double[] rotateYZX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _t8 = _t0 * _t3;
        double _t10 = _t3 * _t2;
        rotateYZX_fma_sab9536f_v(dest, destOffset, src, srcOffset, _t3, -(_t0 * _t1), _t2 * _t1, java.lang.Math.fma(_t8, _t4, _t5 * _t2), java.lang.Math.fma(_t5, _t0, -(_t10 * _t4)), _t4 * _t1, java.lang.Math.fma(_t4, _t2, -(_t8 * _t5)), java.lang.Math.fma(_t10, _t5, _t0 * _t4), -(_t5 * _t1));
        return dest;
    }

    private static void rotateYZX_fma_sab9536f_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t3, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h2), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h5), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h7)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateYZX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleZ);
        rotateYZX_mulAdd_sc72113f2_v(dest, destOffset, src, srcOffset, _t0, Math.cosWithSin(angleZ), _t2, _t3, Math.cosWithSin(angleX), Math.sinWithCos(angleX), _t0 * _t3, _t3 * _t2);
        return dest;
    }

    private static void rotateYZX_mulAdd_sc72113f2_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t8, double _t10) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2 * _t1)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t4) + (_t5 * _t2)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t5) * (_t0) - (_t10 * _t4)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4 * _t1)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t4) * (_t2) - (_t8 * _t5)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t10) * (_t5) + (_t0 * _t4)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t5 * _t1))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateZ(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return rotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] rotateZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset);
        _sv2.fma(_sv1, _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t1 = Math.sinWithCos(angle);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset);
        _sv2.mul(_sv1).add(_sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateZXY(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (SimdSupport.USE_FMA) return rotateZXY_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    public static double[] rotateZXY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.cosWithSin(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.sinWithCos(angleZ);
        double _t7 = _t4 * _t5;
        double _t8 = _t4 * _t3;
        rotateZXY_fma_sdc57f80b_v(dest, destOffset, src, srcOffset, _t4, -(_t0 * _t1), java.lang.Math.fma(_t2, _t3, -(_t7 * _t0)), java.lang.Math.fma(_t8, _t0, _t5 * _t2), _t1 * _t3, -(_t5 * _t1), _t1 * _t2, java.lang.Math.fma(_t7, _t2, _t0 * _t3), java.lang.Math.fma(_t0, _t5, -(_t8 * _t2)));
        return dest;
    }

    private static void rotateZXY_fma_sdc57f80b_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t4, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _t4), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h3), _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h4)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h5), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _h6), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _h7)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateZXY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t3 = Math.cosWithSin(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.sinWithCos(angleZ);
        rotateZXY_mulAdd_sce414c02_v(dest, destOffset, src, srcOffset, Math.sinWithCos(angleY), Math.cosWithSin(angleX), Math.cosWithSin(angleY), _t3, _t4, _t5, _t4 * _t5, _t4 * _t3);
        return dest;
    }

    private static void rotateZXY_mulAdd_sce414c02_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t7, double _t8) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (_t7 * _t0)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t0) + (_t5 * _t2)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1 * _t3)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_t5 * _t1))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1 * _t2)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t7) * (_t2) + (_t0 * _t3)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t5) - (_t8 * _t2)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateZYX(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (SimdSupport.USE_FMA) return rotateZYX_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    public static double[] rotateZYX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.cosWithSin(angleZ);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _t9 = _t0 * _t2;
        double _t11 = _t0 * _t3;
        rotateYXZ_fma_s75ba388b_v(dest, destOffset, src, srcOffset, -_t0, _t1 * _t2, _t3 * _t1, _t4 * _t1, java.lang.Math.fma(_t9, _t4, -(_t3 * _t5)), java.lang.Math.fma(_t11, _t4, _t5 * _t2), _t5 * _t1, java.lang.Math.fma(_t9, _t5, _t4 * _t3), java.lang.Math.fma(_t11, _t5, -(_t4 * _t2)));
        return dest;
    }

    public static double[] rotateZYX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t2 = Math.cosWithSin(angleZ);
        double _t3 = Math.sinWithCos(angleZ);
        rotateZYX_mulAdd_se9739456_v(dest, destOffset, src, srcOffset, _t0, Math.cosWithSin(angleY), _t2, _t3, Math.sinWithCos(angleX), Math.cosWithSin(angleX), _t0 * _t2, _t0 * _t3);
        return dest;
    }

    private static void rotateZYX_mulAdd_se9739456_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t9, double _t11) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, -_t0)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t1 * _t2)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t3 * _t1)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t4 * _t1)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t4) - (_t3 * _t5)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t11) * (_t4) + (_t5 * _t2)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _t5 * _t1)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t5) + (_t4 * _t3)))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_t11) * (_t5) - (_t4 * _t2)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, vY));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, vZ));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _c1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 2]));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset])).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivotX * _t0), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivotY * _t0), _sv3.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivotZ * _t0), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivotX * _t0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivotY * _t0)).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivotZ * _t0)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * _t0), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * _t0), _sv3.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * _t0), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * _t0)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * _t0)).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * _t0)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivotX * (1.0 - sX)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivotY * (1.0 - sY)), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivotZ * (1.0 - sZ)), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        DoubleVector.broadcast(_SIMD_SPECIES, sX).mul(_sv0).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, sY).mul(_sv1).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, sZ).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivotX * (1.0 - sX))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivotY * (1.0 - sY))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivotZ * (1.0 - sZ))).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        DoubleVector.broadcast(_SIMD_SPECIES, sX).mul(_sv0).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, sY).mul(_sv1).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, sZ).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * (1.0 - _sx)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0 - _sy)), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0 - _sz)), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        DoubleVector.broadcast(_SIMD_SPECIES, _sx).mul(_sv0).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _sy).mul(_sv1).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, _sz).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * (1.0 - _sx))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0 - _sy))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0 - _sz))).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        DoubleVector.broadcast(_SIMD_SPECIES, _sx).mul(_sv0).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, _sy).mul(_sv1).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, _sz).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shadow(double[] dest, int destOffset, double[] src, int srcOffset, double lightX, double lightY, double lightZ, double lightW, double planeX, double planeY, double planeZ, double planeW) {
        if (SimdSupport.USE_FMA) return shadow_fma(dest, destOffset, src, srcOffset, lightX, lightY, lightZ, lightW, planeX, planeY, planeZ, planeW);
        return shadow_mulAdd(dest, destOffset, src, srcOffset, lightX, lightY, lightZ, lightW, planeX, planeY, planeZ, planeW);
    }

    public static double[] shadow_fma(double[] dest, int destOffset, double[] src, int srcOffset, double lightX, double lightY, double lightZ, double lightW, double planeX, double planeY, double planeZ, double planeW) {
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _t14 = lightZ * planeZ;
        double _t28 = java.lang.Math.fma(lightX, planeX, lightY * planeY);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightW * planeZ), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightW, planeW, _t28)), _sv4.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightY * planeZ), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightZ, planeZ, _t28)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightZ * planeW), _sv4.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightY * planeW), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightX * planeW))))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightW * planeX), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightZ * planeX), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightW, planeW, java.lang.Math.fma(lightY, planeY, _t14))), _sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightY * planeX)))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightW * planeY), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, lightZ * planeY), _sv3.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightW, planeW, java.lang.Math.fma(lightX, planeX, _t14))), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightX * planeY)))))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shadow_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double lightX, double lightY, double lightZ, double lightW, double planeX, double planeY, double planeZ, double planeW) {
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _t14 = lightZ * planeZ;
        double _t28 = ((lightX) * (planeX) + (lightY * planeY));
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightW * planeZ)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, ((lightW) * (planeW) + (_t28)))).add(_sv4.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightY * planeZ)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(_SIMD_SPECIES, ((lightZ) * (planeZ) + (_t28)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightZ * planeW)).add(_sv4.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightY * planeW)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightX * planeW))))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightW * planeX)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightZ * planeX)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((lightW) * (planeW) + (((lightY) * (planeY) + (_t14)))))).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightY * planeX)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightW * planeY)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, lightZ * planeY)).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((lightW) * (planeW) + (((lightX) * (planeX) + (_t14)))))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(lightX * planeY)))))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shadow(double[] dest, int destOffset, double[] src, int srcOffset, double[] light, int lightOffset, double[] plane, int planeOffset) {
        if (SimdSupport.USE_FMA) return shadow_fma(dest, destOffset, src, srcOffset, light, lightOffset, plane, planeOffset);
        return shadow_mulAdd(dest, destOffset, src, srcOffset, light, lightOffset, plane, planeOffset);
    }

    public static double[] shadow_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] light, int lightOffset, double[] plane, int planeOffset) {
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _lightw = light[lightOffset + 3];
        double _planex = plane[planeOffset];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _lightz = light[lightOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _lighty = light[lightOffset + 1];
        double _planey = plane[planeOffset + 1];
        double _lightx = light[lightOffset];
        double _planez = plane[planeOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _t14 = _lightz * _planez;
        double _t28 = java.lang.Math.fma(_lightx, _planex, _lighty * _planey);
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lightw * _planez), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightw, _planew, _t28)), _sv4.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lighty * _planez), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightz, _planez, _t28)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lightz * _planew), _sv4.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lighty * _planew), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lightx * _planew))))));
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lightw * _planex), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lightz * _planex), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightw, _planew, java.lang.Math.fma(_lighty, _planey, _t14))), _sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lighty * _planex)))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lightw * _planey), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, _lightz * _planey), _sv3.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightw, _planew, java.lang.Math.fma(_lightx, _planex, _t14))), _sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lightx * _planey)))))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shadow_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] light, int lightOffset, double[] plane, int planeOffset) {
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _lightw = light[lightOffset + 3];
        double _planex = plane[planeOffset];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _lightz = light[lightOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _lighty = light[lightOffset + 1];
        double _planey = plane[planeOffset + 1];
        double _lightx = light[lightOffset];
        double _planez = plane[planeOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _t14 = _lightz * _planez;
        double _t28 = ((_lightx) * (_planex) + (_lighty * _planey));
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lightw * _planez)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_lightw) * (_planew) + (_t28)))).add(_sv4.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lighty * _planez)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_lightz) * (_planez) + (_t28)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lightz * _planew)).add(_sv4.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lighty * _planew)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lightx * _planew))))));
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lightw * _planex)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lightz * _planex)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_lightw) * (_planew) + (((_lighty) * (_planey) + (_t14)))))).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lighty * _planex)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lightw * _planey)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, _lightz * _planey)).add(_sv3.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((_lightw) * (_planew) + (((_lightx) * (_planex) + (_t14)))))).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, -(_lightx * _planey)))))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shear(double[] dest, int destOffset, double[] src, int srcOffset, double xy, double xz, double yx, double yz, double zx, double zy) {
        if (SimdSupport.USE_FMA) return shear_fma(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
        return shear_mulAdd(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
    }

    public static double[] shear_fma(double[] dest, int destOffset, double[] src, int srcOffset, double xy, double xz, double yx, double yz, double zx, double zy) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.broadcast(_SIMD_SPECIES, yx).fma(_sv0, DoubleVector.broadcast(_SIMD_SPECIES, zx).fma(_sv1, _sv2)).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, xy).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, zy).fma(_sv1, _sv0)).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, xz).fma(_sv2, DoubleVector.broadcast(_SIMD_SPECIES, yz).fma(_sv0, _sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shear_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double xy, double xz, double yx, double yz, double zx, double zy) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        DoubleVector.broadcast(_SIMD_SPECIES, yx).mul(_sv0).add(DoubleVector.broadcast(_SIMD_SPECIES, zx).mul(_sv1).add(_sv2)).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, xy).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, zy).mul(_sv1).add(_sv0)).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(_SIMD_SPECIES, xz).mul(_sv2).add(DoubleVector.broadcast(_SIMD_SPECIES, yz).mul(_sv0).add(_sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] tile(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double w, double h) {
        if (SimdSupport.USE_FMA) return tile_fma(dest, destOffset, src, srcOffset, x, y, w, h);
        return tile_mulAdd(dest, destOffset, src, srcOffset, x, y, w, h);
    }

    public static double[] tile_fma(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double w, double h) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, x, w - 1.0)), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0, y, h - 1.0)), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.broadcast(_SIMD_SPECIES, w).mul(_sv0).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, h).mul(_sv1).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] tile_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double w, double h) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((w - 1.0) - (2.0) * (x)))).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, ((h - 1.0) - (2.0) * (y)))).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        DoubleVector.broadcast(_SIMD_SPECIES, w).mul(_sv0).intoArray(dest, destOffset);
        DoubleVector.broadcast(_SIMD_SPECIES, h).mul(_sv1).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return translate_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static double[] translate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, vX), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, vY), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, vZ), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, vX)).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, vY)).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, vZ)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return translate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] translate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset]), _sv1.fma(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]), _sv2.fma(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 2]), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset])).add(_sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 1])).add(_sv2.mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 2])).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulVec4(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ, double vW) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
    }

    public static double[] mulVec4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ, double vW) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(_SIMD_SPECIES, vW), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(_SIMD_SPECIES, vZ), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(_SIMD_SPECIES, vX), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, vY))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mulVec4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ, double vW) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(_SIMD_SPECIES, vW)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, vZ)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, vX)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, vY))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mulVec4(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] mulVec4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 3]), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 2]), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset]), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mulVec4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 3])).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 2])).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset])).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]))))).intoArray(dest, destOffset);
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
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        } else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 12).intoArray(dest, destOffset + 12);
        } else {
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 6).intoArray(dest, destOffset + 6);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 8).intoArray(dest, destOffset + 8);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 10).intoArray(dest, destOffset + 10);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 12).intoArray(dest, destOffset + 12);
            DoubleVector.fromArray(_SPECIES_128, src, srcOffset + 14).intoArray(dest, destOffset + 14);
        }
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 16);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 16) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 16; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && srcOffset <= src.limit() - (count > 134217727 ? -1 : count * 16)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 16);
        } else {
            for (int _i = 0; _i < count * 16; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 16; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && destOffset <= dest.limit() - (count > 134217727 ? -1 : count * 16)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 16);
        } else {
            for (int _i = 0; _i < count * 16; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 16) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 16; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 16) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 16; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 16; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && destOffset <= dest.limit() - (count > 134217727 ? -1 : count * 16)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && srcOffset <= src.limit() - (count > 134217727 ? -1 : count * 16)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 16);
            } else {
                for (int _i = 0; _i < count * 16; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && srcOffset <= src.limit() - (count > 134217727 ? -1 : count * 16)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 16; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 16; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
