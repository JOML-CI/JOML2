// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

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
    private static final DoubleVector UNIT_W = DoubleVector.fromArray(SIMD_SPECIES, new double[]{0.0, 0.0, 0.0, 1.0}, 0);

    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).add(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).neg();
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).sub(DoubleVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, v, (vOffset + _li * 4));
            _c.intoArray(dest, destOffset + _li * 4);
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
        double _self33 = src[srcOffset + 15];
        double _tx = t[tOffset + 0];
        double _ty = t[tOffset + 1];
        double _tz = t[tOffset + 2];
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
            var _c = DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))), DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)))).add(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mul_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 3]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 0]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 1]).mul(_sv3))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 3]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 0]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 1]).mul(_sv3))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mulMat2x2(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat2x2_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x2_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat2x2_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat2x2_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).fma(_sv1, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat2x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).mul(_sv1).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2)));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 8]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 6]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2)));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 8]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 6]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
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
        mulMat3x4_fma_sb641010c_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset + 0], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_fma_sb641010c_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, _h0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h1).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2)));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _h3).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h4).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _h6).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h7).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2)));
        var _c3 = DoubleVector.broadcast(SIMD_SPECIES, _h9).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, _h10).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, _h11).fma(_sv0, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] mulMat3x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        mulMat3x4_mulAdd_s2aee6a03_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset + 0], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_mulAdd_s2aee6a03_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, _h0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h1).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2)));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _h3).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h4).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _h6).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h7).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2)));
        var _c3 = DoubleVector.broadcast(SIMD_SPECIES, _h9).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, _h10).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, _h11).mul(_sv0).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] preMul(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3])).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])))));
            _c.intoArray(dest, destOffset + _li * 4);
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
        double _other00 = other[otherOffset + 0];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _other02 = other[otherOffset + 6];
        double _other12 = other[otherOffset + 7];
        double _other22 = other[otherOffset + 8];
        double _other00 = other[otherOffset + 0];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMulMat3x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat3x4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat3x4_fma_s962320cd_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset + 0], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_fma_s962320cd_v(double[] dest, int destOffset, double[] src, int srcOffset, double _other03, double _other13, double _other23, double _other02, double _other12, double _other22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preMulMat3x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat3x4_mulAdd_s8b673d70_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset + 0], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_mulAdd_s8b673d70_v(double[] dest, int destOffset, double[] src, int srcOffset, double _other03, double _other13, double _other23, double _other02, double _other12, double _other22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3])).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static double[] addScaled_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, weight).fma(DoubleVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)), DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] addScaled_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, weight).mul(DoubleVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4))).add(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] makeOuterProduct(double[] dest, int destOffset, double colX, double colY, double colZ, double colW, double rowX, double rowY, double rowZ, double rowW) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, colX).withLane(1, colY).withLane(2, colZ).withLane(3, colW);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowX));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowY));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowZ));
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, rowW));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] makeOuterProduct(double[] dest, int destOffset, double[] col, int colOffset, double[] row, int rowOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, col, colOffset);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 0]));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 1]));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 2]));
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, row[rowOffset + 3]));
        _c0.intoArray(dest, destOffset);
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
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t4 = Math.cosFromSin(_t1, angleX);
        double _t5 = Math.cosFromSin(_t0, angleY);
        arcball_fma_se525cb58_v(dest, destOffset, src, srcOffset, radius, centerX, centerZ, _t0, _t1, -centerZ, -centerY, _t4, _t5, _t1 * _t0, _t0 * _t4, _t1 * _t5, _t4 * _t5);
        return dest;
    }

    private static void arcball_fma_se525cb58_v(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double centerX, double centerZ, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -_t7), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t5), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t4), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t12), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t8))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(centerX * _t5))), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(centerZ, _t8, Math.fma(_t3, _t4, -(centerX * _t6)))), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(centerX, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius)))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] arcball_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double centerX, double centerY, double centerZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = -centerZ;
        double _t3 = -centerY;
        double _t4 = Math.cosFromSin(_t1, angleX);
        double _t5 = Math.cosFromSin(_t0, angleY);
        double _t6 = _t1 * _t0;
        double _t7 = _t0 * _t4;
        double _t8 = _t1 * _t5;
        double _t12 = _t4 * _t5;
        arcball_mulAdd_s8516c9eb_v(dest, destOffset, src, srcOffset, _t0, _t1, _t4, _t5, _t6, _t12, -_t7, -_t8, Math.fma(_t2, _t0, -(centerX * _t5)), Math.fma(centerZ, _t8, Math.fma(_t3, _t4, -(centerX * _t6))), Math.fma(centerX, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius))));
        return dest;
    }

    private static void arcball_mulAdd_s8516c9eb_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t4, double _t5, double _t6, double _t12, double _h0, double _h1, double _h2, double _h3, double _h4) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t4)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t12)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] arcball(double[] dest, int destOffset, double[] src, int srcOffset, double[] center, int centerOffset, double radius, double angleX, double angleY) {
        if (SimdSupport.USE_FMA) return arcball_fma(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
        return arcball_mulAdd(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
    }

    public static double[] arcball_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] center, int centerOffset, double radius, double angleX, double angleY) {
        double _centerz = center[centerOffset + 2];
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t4 = Math.cosFromSin(_t1, angleX);
        double _t5 = Math.cosFromSin(_t0, angleY);
        arcball_fma_sc3f821b4_v(dest, destOffset, src, srcOffset, radius, center[centerOffset + 0], _centerz, _t0, _t1, -_centerz, -center[centerOffset + 1], _t4, _t5, _t1 * _t0, _t0 * _t4, _t1 * _t5, _t4 * _t5);
        return dest;
    }

    private static void arcball_fma_sc3f821b4_v(double[] dest, int destOffset, double[] src, int srcOffset, double radius, double _centerx, double _centerz, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -_t7), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t5), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t4), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t12), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t8))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(_centerx * _t5))), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_centerz, _t8, Math.fma(_t3, _t4, -(_centerx * _t6)))), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_centerx, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius)))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] arcball_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] center, int centerOffset, double radius, double angleX, double angleY) {
        double _centerz = center[centerOffset + 2];
        double _centerx = center[centerOffset + 0];
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = -_centerz;
        double _t3 = -center[centerOffset + 1];
        double _t4 = Math.cosFromSin(_t1, angleX);
        double _t5 = Math.cosFromSin(_t0, angleY);
        double _t6 = _t1 * _t0;
        double _t7 = _t0 * _t4;
        double _t8 = _t1 * _t5;
        double _t12 = _t4 * _t5;
        arcball_mulAdd_s21d64c9_v(dest, destOffset, src, srcOffset, _t0, _t1, _t4, _t5, _t6, _t12, -_t7, -_t8, Math.fma(_t2, _t0, -(_centerx * _t5)), Math.fma(_centerz, _t8, Math.fma(_t3, _t4, -(_centerx * _t6))), Math.fma(_centerx, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius))));
        return dest;
    }

    private static void arcball_mulAdd_s21d64c9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t4, double _t5, double _t6, double _t12, double _h0, double _h1, double _h2, double _h3, double _h4) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t4)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t12)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] axonometricDimetric(double[] dest, int destOffset, double[] src, int srcOffset, double alpha) {
        if (SimdSupport.USE_FMA) return axonometricDimetric_fma(dest, destOffset, src, srcOffset, alpha);
        return axonometricDimetric_mulAdd(dest, destOffset, src, srcOffset, alpha);
    }

    public static double[] axonometricDimetric_fma(double[] dest, int destOffset, double[] src, int srcOffset, double alpha) {
        double _t0 = Math.sin(alpha);
        double _t1 = Math.sqrt(2.0);
        double _t2 = Math.cosFromSin(_t0, alpha);
        double _t9 = 0.5 * _t0 * _t1;
        double _t10 = 0.5 * _t2 * _t1;
        axonometricDimetric_fma_s500ae7f3_v(dest, destOffset, src, srcOffset, _t0, _t2, _t9, _t10, -_t10, src[srcOffset + 0] * _t1, src[srcOffset + 1] * _t1, src[srcOffset + 2] * _t1, src[srcOffset + 3] * _t1, -_t9);
        return dest;
    }

    private static void axonometricDimetric_fma_s500ae7f3_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t2, double _t9, double _t10, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.5);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h1).withLane(1, _h2).withLane(2, _h3).withLane(3, _h4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t9), _sv2.mul(_sv3)));
        var _c1 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t2), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t10), _sv2.fma(_sv3, _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] axonometricDimetric_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double alpha) {
        double _t0 = Math.sin(alpha);
        double _t1 = Math.sqrt(2.0);
        double _t2 = Math.cosFromSin(_t0, alpha);
        axonometricDimetric_mulAdd_s77bb65cc_v(dest, destOffset, src, srcOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t0, _t1, _t2, 0.5 * _t0 * _t1, 0.5 * _t2 * _t1);
        return dest;
    }

    private static void axonometricDimetric_mulAdd_s77bb65cc_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self00, double _self10, double _self20, double _self30, double _t0, double _t1, double _t2, double _t9, double _t10) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.5);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self00 * _t1).withLane(1, _self10 * _t1).withLane(2, _self20 * _t1).withLane(3, _self30 * _t1);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t10)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t9)).add(_sv2.mul(_sv3)));
        var _c1 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t10)).add(_sv2.mul(_sv3).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t9))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] axonometricIsometric(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t0 = Math.sqrt(3.0);
        double _t1 = Math.sqrt(2.0);
        double _t2 = Math.sqrt(6.0);
        double _sp0 = 0.16666666666666666 * _t2;
        double _t3 = _self02 * _t0;
        double _t4 = _self00 * _t1;
        double _t6 = _self12 * _t0;
        double _t7 = _self10 * _t1;
        double _t9 = _self22 * _t0;
        double _t10 = _self20 * _t1;
        double _t12 = _self32 * _t0;
        double _t13 = _self30 * _t1;
        double _t15 = _sp0 * _self01;
        double _t16 = _sp0 * _self11;
        double _t17 = _sp0 * _self21;
        double _t18 = _sp0 * _self31;
        dest[destOffset + 0] = Math.fma(-0.3333333333333333, _t3, Math.fma(0.5, _t4, _t15));
        dest[destOffset + 1] = Math.fma(-0.3333333333333333, _t6, Math.fma(0.5, _t7, _t16));
        dest[destOffset + 2] = Math.fma(-0.3333333333333333, _t9, Math.fma(0.5, _t10, _t17));
        dest[destOffset + 3] = Math.fma(-0.3333333333333333, _t12, Math.fma(0.5, _t13, _t18));
        dest[destOffset + 4] = 0.3333333333333333 * Math.fma(_self01, _t2, _t3);
        dest[destOffset + 5] = 0.3333333333333333 * Math.fma(_self11, _t2, _t6);
        dest[destOffset + 6] = 0.3333333333333333 * Math.fma(_self21, _t2, _t9);
        dest[destOffset + 7] = 0.3333333333333333 * Math.fma(_self31, _t2, _t12);
        dest[destOffset + 8] = Math.fma(0.3333333333333333, _t3, Math.fma(0.5, _t4, -_t15));
        dest[destOffset + 9] = Math.fma(0.3333333333333333, _t6, Math.fma(0.5, _t7, -_t16));
        dest[destOffset + 10] = Math.fma(0.3333333333333333, _t9, Math.fma(0.5, _t10, -_t17));
        dest[destOffset + 11] = Math.fma(0.3333333333333333, _t12, Math.fma(0.5, _t13, -_t18));
        _vcp0.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] axonometricTrimetric(double[] dest, int destOffset, double[] src, int srcOffset, double alphaX, double alphaY) {
        if (SimdSupport.USE_FMA) return axonometricTrimetric_fma(dest, destOffset, src, srcOffset, alphaX, alphaY);
        return axonometricTrimetric_mulAdd(dest, destOffset, src, srcOffset, alphaX, alphaY);
    }

    public static double[] axonometricTrimetric_fma(double[] dest, int destOffset, double[] src, int srcOffset, double alphaX, double alphaY) {
        double _t0 = Math.sin(alphaY);
        double _t1 = Math.sin(alphaX);
        double _t2 = Math.cosFromSin(_t1, alphaX);
        double _t3 = Math.cosFromSin(_t0, alphaY);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t2)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t3), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t0))));
        var _c1 = _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t2), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t3), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 * _t3)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] axonometricTrimetric_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double alphaX, double alphaY) {
        double _t0 = Math.sin(alphaY);
        double _t1 = Math.sin(alphaX);
        double _t2 = Math.cosFromSin(_t1, alphaX);
        double _t3 = Math.cosFromSin(_t0, alphaY);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t2))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t0))));
        var _c1 = _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 * _t3)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
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
        composeTRSMul_fma_s8387ac18_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ), Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2);
        return dest;
    }

    private static void composeTRSMul_fma_s8387ac18_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_s3467d619_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ), Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2);
        return dest;
    }

    private static void composeTRSMul_mulAdd_s3467d619_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] composeTRSMul(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_mulAdd(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static double[] composeTRSMul_fma(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _scalez = scale[scaleOffset + 2];
        double _scalex = scale[scaleOffset + 0];
        double _scaley = scale[scaleOffset + 1];
        double _rotationy = rotation[rotationOffset + 1];
        double _rotationw = rotation[rotationOffset + 3];
        double _rotationz = rotation[rotationOffset + 2];
        double _rotationx = rotation[rotationOffset + 0];
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        composeTRSMul_fma_s712b2612_v(dest, destOffset, m, mOffset, translation[translationOffset + 0], translation[translationOffset + 1], translation[translationOffset + 2], Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2);
        return dest;
    }

    private static void composeTRSMul_fma_s712b2612_v(double[] dest, int destOffset, double[] m, int mOffset, double _translationx, double _translationy, double _translationz, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _translationx).withLane(1, _translationy).withLane(2, _translationz);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _scalez = scale[scaleOffset + 2];
        double _scalex = scale[scaleOffset + 0];
        double _scaley = scale[scaleOffset + 1];
        double _rotationy = rotation[rotationOffset + 1];
        double _rotationw = rotation[rotationOffset + 3];
        double _rotationz = rotation[rotationOffset + 2];
        double _rotationx = rotation[rotationOffset + 0];
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        composeTRSMul_mulAdd_s13f9a605_v(dest, destOffset, m, mOffset, translation[translationOffset + 0], translation[translationOffset + 1], translation[translationOffset + 2], Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2);
        return dest;
    }

    private static void composeTRSMul_mulAdd_s13f9a605_v(double[] dest, int destOffset, double[] m, int mOffset, double _translationx, double _translationy, double _translationz, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _translationx).withLane(1, _translationy).withLane(2, _translationz);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] frustum_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0 = zNear + zNear;
        double _t4_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -_t0;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = zFar + zFar;
            } else {
                _w0 = -((zFar + zNear) * _t4_inv);
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_lh_fma_sb9a6b0ad_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_fma_sb9a6b0ad_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv4, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(left + right))).fma(_sv2, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0 = zNear + zNear;
        double _t4_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -_t0;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = -1.0;
                _w1 = zFar + zFar;
            } else {
                _w0 = -((zFar + zNear) * _t4_inv);
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_lh_mulAdd_s7fe1ff0e_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_mulAdd_s7fe1ff0e_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv4).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv2).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0 = zNear + zNear;
        double _t4_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -_t0;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = zFar + zFar;
            } else {
                _w0 = (zFar + zNear) * _t4_inv;
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_rh_fma_s4cd499ab_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_fma_s4cd499ab_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).fma(_sv2, _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, bottom + top)).mul(_sv4)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0 = zNear + zNear;
        double _t4_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -_t0;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = 1.0;
                _w1 = zFar + zFar;
            } else {
                _w0 = (zFar + zNear) * _t4_inv;
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_rh_mulAdd_sba103a7c_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_mulAdd_sba103a7c_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double _t0, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(_sv2).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, bottom + top)).mul(_sv4)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = zFar;
            } else {
                _w0 = -(zFar * _t3_inv);
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_lh_fma_sacd514d1_v(dest, destOffset, src, srcOffset, _w0, _w1, zNear + zNear, 1.0 / (right - left), 1.0 / (top - bottom), -(bottom + top), -(left + right));
        return dest;
    }

    private static void frustum_zo_lh_fma_sacd514d1_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2, double _h3, double _h4) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _h0);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, _h2);
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).fma(_sv4, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).fma(_sv2, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = 1.0;
            _w1 = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = zFar;
            } else {
                _w0 = -(zFar * _t3_inv);
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_lh_mulAdd_s453f3992_v(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, _w0, _w1);
        return dest;
    }

    private static void frustum_zo_lh_mulAdd_s453f3992_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, zNear + zNear);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv4).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv2).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] frustum_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = zFar;
            } else {
                _w0 = zFar * _t3_inv;
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_rh_fma_scb994c07_v(dest, destOffset, src, srcOffset, _w0, _w1, zNear + zNear, 1.0 / (right - left), 1.0 / (top - bottom), left + right, bottom + top);
        return dest;
    }

    private static void frustum_zo_rh_fma_scb994c07_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2, double _h3, double _h4) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _h0);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, _h2);
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).fma(_sv2, _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).mul(_sv4)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] frustum_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t3_inv = 1.0 / (zNear - zFar);
        double _w0, _w1;
        if (zFar == Double.POSITIVE_INFINITY) {
            _w0 = -1.0;
            _w1 = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _w0 = 0.0;
                _w1 = zFar;
            } else {
                _w0 = zFar * _t3_inv;
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_rh_mulAdd_sadf0488_v(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, _w0, _w1);
        return dest;
    }

    private static void frustum_zo_rh_mulAdd_sadf0488_v(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, zNear + zNear);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(_sv2).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, bottom + top)).mul(_sv4)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] lookAlong(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static double[] lookAlong_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_fma_sd42ad6ea_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_fma_sd42ad6ea_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t37), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t38), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t11), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t12), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] lookAlong_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_mulAdd_s7aa6e3f_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_mulAdd_s7aa6e3f_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t37)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t38)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t11)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] lookAlong(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static double[] lookAlong_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset + 0];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t37), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t38), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t11), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t12), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAlong_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset + 0];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t37)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t38)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t11)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t14), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t43), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t45), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t15), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t44), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45))), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55))), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t14)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t43)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t45)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t15)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t44)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)))).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / Math.sqrt(_t13));
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
        double _t27 = -Math.fma(upZ, _t19, Math.fma(upX, _t18, upY * _t20));
        double _t28 = Math.fma(_t27, _t20, upY);
        double _t29 = Math.fma(_t27, _t18, upX);
        double _t30 = Math.fma(_t27, _t19, upZ);
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t18), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t47), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t48), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t49), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t49, Math.fma(eyeX, _t47, eyeY * _t48))), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t60, Math.fma(eyeX, _t58, eyeY * _t59))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(eyeZ, _t19, Math.fma(eyeX, _t18, eyeY * _t20))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / Math.sqrt(_t13));
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
        double _t27 = -Math.fma(upZ, _t19, Math.fma(upX, _t18, upY * _t20));
        double _t28 = Math.fma(_t27, _t20, upY);
        double _t29 = Math.fma(_t27, _t18, upX);
        double _t30 = Math.fma(_t27, _t19, upZ);
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t47)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t48)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t49)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t49, Math.fma(eyeX, _t47, eyeY * _t48)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t60, Math.fma(eyeX, _t58, eyeY * _t59)))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(eyeZ, _t19, Math.fma(eyeX, _t18, eyeY * _t20)))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset + 0] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t14), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t43), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t45), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t15), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t44), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45))), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55))), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset + 0] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t14)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t43)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t45)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t15)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t44)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)))).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset + 0] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / Math.sqrt(_t13));
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
        double _t27 = -Math.fma(_upz, _t19, Math.fma(_upx, _t18, _upy * _t20));
        double _t28 = Math.fma(_t27, _t20, _upy);
        double _t29 = Math.fma(_t27, _t18, _upx);
        double _t30 = Math.fma(_t27, _t19, _upz);
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t18), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t47), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t48), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t49), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t49, Math.fma(_eyex, _t47, _eyey * _t48))), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t60, Math.fma(_eyex, _t58, _eyey * _t59))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_eyez, _t19, Math.fma(_eyex, _t18, _eyey * _t20))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset + 0] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / Math.sqrt(_t13));
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
        double _t27 = -Math.fma(_upz, _t19, Math.fma(_upx, _t18, _upy * _t20));
        double _t28 = Math.fma(_t27, _t20, _upy);
        double _t29 = Math.fma(_t27, _t18, _upx);
        double _t30 = Math.fma(_t27, _t19, _upz);
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        double _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        double _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t47)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t48)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t49)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t49, Math.fma(_eyex, _t47, _eyey * _t48)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t60, Math.fma(_eyex, _t58, _eyey * _t59)))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_eyez, _t19, Math.fma(_eyex, _t18, _eyey * _t20)))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mapXYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapXnZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapYnZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapZnYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnXnZnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnXnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnYnZnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnXnY(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mapnZnYnX(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
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
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -(0.5 * Math.cosFromSin(_t0, angle))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, -(0.5 * _t0)), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueCabinet_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(0.5 * Math.cosFromSin(_t0, angle)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(0.5 * _t0))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueCavalier(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return obliqueCavalier_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueCavalier_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] obliqueCavalier_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -Math.cosFromSin(_t0, angle)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, -_t0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueCavalier_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -Math.cosFromSin(_t0, angle))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueMilitary(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return obliqueMilitary_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueMilitary_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] obliqueMilitary_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg()));
        var _c2 = _sv1;
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueMilitary_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg()));
        var _c2 = _sv1;
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliquePlanometric(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return obliquePlanometric_fma(dest, destOffset, src, srcOffset, angle);
        return obliquePlanometric_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] obliquePlanometric_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _t0);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t1), _sv1.mul(_sv2));
        var _c1 = _sv1.add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c2 = _sv0.fma(_sv2, _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t1)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliquePlanometric_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _t0);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)).add(_sv1.mul(_sv2));
        var _c1 = _sv1.add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c2 = _sv0.mul(_sv2).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t1)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t15_inv = 1.0 / Math.fma(planeW, 1.0 - src[srcOffset + 10], _self23 * (planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset + 0] + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t15_inv = 1.0 / Math.fma(planeW, 1.0 + src[srcOffset + 10], _self23 * (planeX * (src[srcOffset + 8] + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 0] + planeY * (src[srcOffset + 9] + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - planeZ));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t14_inv = 1.0 / Math.fma(planeW, 1.0 - src[srcOffset + 10], _self23 * (planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset + 0] + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t14_inv);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t14_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t14_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self23 = src[srcOffset + 14];
        double _t14_inv = 1.0 / Math.fma(planeW, 1.0 + src[srcOffset + 10], _self23 * (planeX * (src[srcOffset + 8] + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 0] + planeY * (src[srcOffset + 9] + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - planeZ));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t14_inv);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t14_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t14_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset + 0];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t15_inv = 1.0 / Math.fma(_planew, 1.0 - src[srcOffset + 10], _self23 * (_planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset + 0] + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset + 0];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self23 = src[srcOffset + 14];
        double _t0 = _self23 + _self23;
        double _t15_inv = 1.0 / Math.fma(_planew, 1.0 + src[srcOffset + 10], _self23 * (_planex * (src[srcOffset + 8] + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 0] + _planey * (src[srcOffset + 9] + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - _planez));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset + 0];
        double _self23 = src[srcOffset + 14];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _t14_inv = 1.0 / Math.fma(_planew, 1.0 - src[srcOffset + 10], _self23 * (_planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - src[srcOffset + 8]) / src[srcOffset + 0] + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t14_inv);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t14_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t14_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] obliqueZ_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset + 0];
        double _self23 = src[srcOffset + 14];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _t14_inv = 1.0 / Math.fma(_planew, 1.0 + src[srcOffset + 10], _self23 * (_planex * (src[srcOffset + 8] + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 0] + _planey * (src[srcOffset + 9] + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / src[srcOffset + 5] - _planez));
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t14_inv);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t14_inv);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t14_inv);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.add(_sv3).mul(_sv4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).fma(_sv4, _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.add(_sv3).mul(_sv4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).mul(_sv4).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return ortho_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] ortho_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, -2.0).mul(_sv3).mul(_sv4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).fma(_sv4, _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, -2.0).mul(_sv3).mul(_sv4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).mul(_sv4).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] ortho_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(_sv4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(DoubleVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).fma(_sv4, _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(_sv4);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(DoubleVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).mul(_sv4).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static double[] ortho_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t2_inv = 1.0 / (zFar - zNear);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t2_inv));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(DoubleVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).fma(DoubleVector.broadcast(SIMD_SPECIES, _t2_inv), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t2_inv = 1.0 / (zFar - zNear);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t2_inv));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(DoubleVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).mul(DoubleVector.broadcast(SIMD_SPECIES, _t2_inv)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        if (SimdSupport.USE_FMA) return ortho2D_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static double[] ortho2D_no_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, left + right)).mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static double[] ortho2D_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 0.5);
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(_sv5);
        var _c3 = _sv4.fma(_sv5, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv3, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(left + right))).fma(_sv1, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv4 = DoubleVector.broadcast(SIMD_SPECIES, 0.5);
        var _sv5 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(_sv5);
        var _c3 = _sv4.mul(_sv5).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv3).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv1).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static double[] ortho2D_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, -0.5).mul(_sv4);
        var _c3 = DoubleVector.broadcast(SIMD_SPECIES, 0.5).fma(_sv4, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv3, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(left + right))).fma(_sv1, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] ortho2D_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (right - left));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (top - bottom));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, -0.5).mul(_sv4);
        var _c3 = DoubleVector.broadcast(SIMD_SPECIES, 0.5).mul(_sv4).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv3).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv1).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t6)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t6)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t6)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t6)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t6));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t3)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t3)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t3)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t3)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t3));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        perspectiveFovRange_no_lh_fma_s449fb40d_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0 / (aspect * _t8), 1.0 / _t8, -(_t0 + _t1));
        return dest;
    }

    private static void perspectiveFovRange_no_lh_fma_s449fb40d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).fma(_sv2, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveFovRange_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
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
        perspectiveFovRange_no_lh_mulAdd_s784bc098_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0 / (aspect * _t8), 1.0 / _t8, -(_t0 + _t1));
        return dest;
    }

    private static void perspectiveFovRange_no_lh_mulAdd_s784bc098_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).mul(_sv2).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        perspectiveFovRange_no_rh_fma_sb0806957_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0 / (aspect * _t8), 1.0 / _t8, _t0 + _t1);
        return dest;
    }

    private static void perspectiveFovRange_no_rh_fma_sb0806957_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).fma(_sv2, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveFovRange_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
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
        perspectiveFovRange_no_rh_mulAdd_s64ee229a_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0 / (aspect * _t8), 1.0 / _t8, _t0 + _t1);
        return dest;
    }

    private static void perspectiveFovRange_no_rh_mulAdd_s64ee229a_v(double[] dest, int destOffset, double[] src, int srcOffset, double _w0, double _w1, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).mul(_sv2).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 + _t1))).fma(_sv2, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t4 = _t0 - _t1;
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 + _t1))).mul(_sv2).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0 + _t1)).fma(_sv2, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] perspectiveFovRange_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t4 = _t0 - _t1;
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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0 + _t1)).mul(_sv2).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
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
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
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
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
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
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
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
        perspectiveOffCenterFov_no_lh_fma_s8b45defb_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_lh_fma_s8b45defb_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).fma(_sv3, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).fma(_sv1, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_no_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
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
        perspectiveOffCenterFov_no_lh_mulAdd_sd28419b4_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_lh_mulAdd_sd28419b4_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).mul(_sv3).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).mul(_sv1).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_no_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_no_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static double[] perspectiveOffCenterFov_no_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
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
        perspectiveOffCenterFov_no_rh_fma_s1abe499d_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_fma_s1abe499d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 + _t0)).fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_no_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
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
        perspectiveOffCenterFov_no_rh_mulAdd_sae5ccc5e_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_mulAdd_sae5ccc5e_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 + _t0)).mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_lh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_lh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static double[] perspectiveOffCenterFov_zo_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t4_inv = 1.0 / (near - far);
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
        perspectiveOffCenterFov_zo_lh_fma_s60df863f_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_lh_fma_s60df863f_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).fma(_sv3, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).fma(_sv1, DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_zo_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t4_inv = 1.0 / (near - far);
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
        perspectiveOffCenterFov_zo_lh_mulAdd_sc4dd1958_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_lh_mulAdd_sc4dd1958_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).mul(_sv3).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).mul(_sv1).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static double[] perspectiveOffCenterFov_zo_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t4_inv = 1.0 / (near - far);
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
        perspectiveOffCenterFov_zo_rh_fma_s8d1aecf9_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_rh_fma_s8d1aecf9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 + _t0)).fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] perspectiveOffCenterFov_zo_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t4_inv = 1.0 / (near - far);
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
        perspectiveOffCenterFov_zo_rh_mulAdd_sf26674fa_v(dest, destOffset, src, srcOffset, Math.tan(angleRight), Math.tan(angleLeft), Math.tan(angleUp), Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_rh_mulAdd_sf26674fa_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _w0, double _w1) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t0 - _t1));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 1.0 / (_t2 - _t3));
        var _sv4 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 + _t0)).mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] pickMatrix(double[] dest, int destOffset, double[] src, int srcOffset, double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH) {
        if (SimdSupport.USE_FMA) return pickMatrix_fma(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
        return pickMatrix_mulAdd(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
    }

    public static double[] pickMatrix_fma(double[] dest, int destOffset, double[] src, int srcOffset, double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH) {
        double _rcp0 = 1.0 / deltaX;
        double _rcp1 = 1.0 / deltaY;
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _rcp0);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, _rcp1);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, vpW).mul(_sv0).mul(_sv1);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, vpH).mul(_sv2).mul(_sv3);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, centerX - vpX, vpW))).fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, centerY - vpY, vpH))).mul(_sv3)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] pickMatrix_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH) {
        double _rcp0 = 1.0 / deltaX;
        double _rcp1 = 1.0 / deltaY;
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _rcp0);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, _rcp1);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, vpW).mul(_sv0).mul(_sv1);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, vpH).mul(_sv2).mul(_sv3);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, centerX - vpX, vpW))).mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, centerY - vpY, vpH))).mul(_sv3)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
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
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotZ, _t5, _t8);
        double _t21 = Math.fma(rotY, _t5, _t10);
        double _t22 = Math.fma(rotZ, _t6, _t11);
        double _t24 = Math.fma(rotY, _t5, -_t10);
        double _t25 = Math.fma(rotZ, _t6, -_t11);
        double _t26 = Math.fma(rotZ, _t5, -_t8);
        preRotateAround_fma_s5306fd9d_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, Math.fma(_t0, _t18, Math.fma(pivotX, Math.fma(rotY, _t6, _t9), -(pivotY * _t24))), Math.fma(_t0, _t25, Math.fma(pivotY, Math.fma(rotX, _t5, _t9), -(pivotX * _t21))), Math.fma(-pivotY, _t22, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t26))), Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0)), Math.fma(_t1, _t6, _t16), Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_fma_s5306fd9d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t21, double _t22, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
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
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotZ, _t5, _t8);
        double _t21 = Math.fma(rotY, _t5, _t10);
        double _t22 = Math.fma(rotZ, _t6, _t11);
        double _t24 = Math.fma(rotY, _t5, -_t10);
        double _t25 = Math.fma(rotZ, _t6, -_t11);
        double _t26 = Math.fma(rotZ, _t5, -_t8);
        preRotateAround_mulAdd_s3b7753b6_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, Math.fma(_t0, _t18, Math.fma(pivotX, Math.fma(rotY, _t6, _t9), -(pivotY * _t24))), Math.fma(_t0, _t25, Math.fma(pivotY, Math.fma(rotX, _t5, _t9), -(pivotX * _t21))), Math.fma(-pivotY, _t22, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t26))), Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0)), Math.fma(_t1, _t6, _t16), Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_mulAdd_s3b7753b6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t21, double _t22, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static double[] preRotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset + 0];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset + 0];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t0 = -_pivotz;
        double _t1 = -_roty;
        double _t3 = -_rotx;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotz * _t7;
        double _t10 = _rotw * _t7;
        double _t11 = _rotw * _t5;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_rotz, _t5, _t8);
        double _t21 = Math.fma(_roty, _t5, _t10);
        double _t22 = Math.fma(_rotz, _t6, _t11);
        double _t24 = Math.fma(_roty, _t5, -_t10);
        double _t25 = Math.fma(_rotz, _t6, -_t11);
        double _t26 = Math.fma(_rotz, _t5, -_t8);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t18, Math.fma(_pivotx, Math.fma(_roty, _t6, _t9), -(_pivoty * _t24)))).withLane(1, Math.fma(_t0, _t25, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t9), -(_pivotx * _t21)))).withLane(2, Math.fma(-_pivoty, _t22, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t26))));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t1, _t6, _t16)).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, Math.fma(_t3, _t5, _t16)).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preRotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset + 0];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset + 0];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t0 = -_pivotz;
        double _t1 = -_roty;
        double _t3 = -_rotx;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotz * _t7;
        double _t10 = _rotw * _t7;
        double _t11 = _rotw * _t5;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_rotz, _t5, _t8);
        double _t21 = Math.fma(_roty, _t5, _t10);
        double _t22 = Math.fma(_rotz, _t6, _t11);
        double _t24 = Math.fma(_roty, _t5, -_t10);
        double _t25 = Math.fma(_rotz, _t6, -_t11);
        double _t26 = Math.fma(_rotz, _t5, -_t8);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t18, Math.fma(_pivotx, Math.fma(_roty, _t6, _t9), -(_pivoty * _t24)))).withLane(1, Math.fma(_t0, _t25, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t9), -(_pivotx * _t21)))).withLane(2, Math.fma(-_pivoty, _t22, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t26))));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t1, _t6, _t16)).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, Math.fma(_t3, _t5, _t16)).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] preRotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_s995bbb1c_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_s995bbb1c_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisY, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(axisX * _t0))).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(axisZ, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(axisY * _t0)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(axisZ * _t0))).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(axisX, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_sbf6928a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_sbf6928a1_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisY, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(axisX * _t0))).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(axisZ, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(axisY * _t0)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(axisZ * _t0))).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(axisX, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static double[] preRotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset + 0];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_s703295d2_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_s703295d2_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisy, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(_axisx * _t0))).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_axisz, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(_axisy * _t0)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(_axisz * _t0))).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_axisx, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset + 0];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_sb318cd01_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_sb318cd01_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisy, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(_axisx * _t0))).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_axisz, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(_axisy * _t0)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(_axisz * _t0))).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_axisx, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
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
        preRotateQuat_fma_s872a8523_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_fma_s872a8523_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, _t6)).withLane(1, Math.fma(qZ, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, _t7)).withLane(2, Math.fma(qZ, _t3, -_t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        preRotateQuat_mulAdd_s51543db2_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s51543db2_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, _t6)).withLane(1, Math.fma(qZ, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, _t7)).withLane(2, Math.fma(qZ, _t3, -_t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static double[] preRotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset + 0];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_fma_s78c6ffd6_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_fma_s78c6ffd6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, _t6)).withLane(1, Math.fma(_qz, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, _t7)).withLane(2, Math.fma(_qz, _t3, -_t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preRotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset + 0];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_mulAdd_sa065509_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_mulAdd_sa065509_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, _t6)).withLane(1, Math.fma(_qz, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, _t7)).withLane(2, Math.fma(_qz, _t3, -_t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static double[] preScale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] preScale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
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
            var _c = DoubleVector.broadcast(SIMD_SPECIES, s).fma(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, pivotX * _t0).withLane(1, pivotY * _t0).withLane(2, pivotZ * _t0);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, s).fma(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
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
            var _c = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, pivotX * (1.0 - sX)).withLane(1, pivotY * (1.0 - sY)).withLane(2, pivotZ * (1.0 - sZ));
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset + 0];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0 - _sx)).withLane(1, _pivoty * (1.0 - _sy)).withLane(2, _pivotz * (1.0 - _sz));
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset + 0];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0 - _sx)).withLane(1, _pivoty * (1.0 - _sy)).withLane(2, _pivotz * (1.0 - _sz));
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
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
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preTranslate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preTranslate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preTranslate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] preTranslate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preTranslate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = DoubleVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(DoubleVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
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
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t10), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, normalX * normalX, 1.0)), _sv2.mul(_sv3)));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t12), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, normalY * normalY, 1.0)), _sv1.mul(_sv3)));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, normalZ * normalZ, 1.0)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, -_t12), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        reflect_mulAdd_sdd26ac6b_v(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], (normalX + normalX) * normalZ, (normalY + normalY) * normalZ);
        return dest;
    }

    private static void reflect_mulAdd_sdd26ac6b_v(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double _self02, double _self12, double _self22, double _self32, double _t10, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t10)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, normalX * normalX, 1.0))).add(_sv2.mul(_sv3)));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, normalY * normalY, 1.0))).add(_sv1.mul(_sv3)));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, normalZ * normalZ, 1.0))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t12)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _normalx = normal[normalOffset + 0];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        reflect_fma_s54924c8a_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_fma_s54924c8a_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self02, double _self12, double _self22, double _self32, double _normalx, double _normaly, double _normalz, double _t10, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t10), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, _normalx * _normalx, 1.0)), _sv2.mul(_sv3)));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t12), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, _normaly * _normaly, 1.0)), _sv1.mul(_sv3)));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, _normalz * _normalz, 1.0)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, -_t12), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _normalx = normal[normalOffset + 0];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        reflect_mulAdd_sd09fdfb9_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_mulAdd_sd09fdfb9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self02, double _self12, double _self22, double _self32, double _normalx, double _normaly, double _normalz, double _t10, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t10)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, _normalx * _normalx, 1.0))).add(_sv2.mul(_sv3)));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, _normaly * _normaly, 1.0))).add(_sv1.mul(_sv3)));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, _normalz * _normalz, 1.0))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t12)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
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
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t20 = Math.fma(rotZ, _t5, _t8);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t25 = Math.fma(rotY, _t5, -_t9);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_fma_s9107c306_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, Math.fma(_t0, _t6, _t16), Math.fma(_t2, _t5, _t16), Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)), Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s9107c306_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t24), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t25), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h2), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t20), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h3), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h5), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
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
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t20 = Math.fma(rotZ, _t5, _t8);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t25 = Math.fma(rotY, _t5, -_t9);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_mulAdd_s6f860be3_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, Math.fma(_t0, _t6, _t16), Math.fma(_t2, _t5, _t16), Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)), Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_mulAdd_s6f860be3_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t24)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t25)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t20)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static double[] rotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset + 0];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset + 0];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t24), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t6, _t16)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t25), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, _t16)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t20), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25)))), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)))), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)))), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset + 0];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset + 0];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t24)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t6, _t16))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t25)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, _t16)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t20)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))))).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] rotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_s118d95b9_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_fma_s118d95b9_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t5)))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t5, -(axisZ * _t0))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        rotateAxis_mulAdd_s461c3d92_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, axisX * axisX, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_s461c3d92_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static double[] rotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset + 0];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_s84fc82b9_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_fma_s84fc82b9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t5)))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t5, -(_axisz * _t0))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisx = axis[axisOffset + 0];
        double _axisz = axis[axisOffset + 2];
        double _axisy = axis[axisOffset + 1];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        rotateAxis_mulAdd_sabf16874_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(_axisx * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_sabf16874_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
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
        rotateQuat_fma_sff33a8b2_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_sff33a8b2_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7)))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        rotateQuat_mulAdd_sa23574f1_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_mulAdd_sa23574f1_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7)))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static double[] rotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset + 0];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_fma_s768c7609_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_s768c7609_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset + 0];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_mulAdd_s9e0cc6e0_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_mulAdd_s9e0cc6e0_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateX(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateX_fma(dest, destOffset, src, srcOffset, angle);
        return rotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] rotateX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv2.fma(_sv1, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv2.mul(_sv1).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateXYZ(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (SimdSupport.USE_FMA) return rotateXYZ_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    public static double[] rotateXYZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t0, angleX);
        rotateXYZ_fma_sa4cfd303_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, Math.cosFromSin(_t1, angleZ), Math.cosFromSin(_t2, angleY), _t0 * _t2, _t2 * _t3);
        return dest;
    }

    private static void rotateXYZ_fma_sa4cfd303_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t7) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t1, -(_t7 * _t4))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t4), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t1 * _t3)))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t7, _t1, _t0 * _t4)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, -(_t6 * _t1))), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 * _t5)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t5), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t2), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t5)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateXYZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t0, angleX);
        rotateXYZ_mulAdd_s46fc8de6_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, Math.cosFromSin(_t1, angleZ), Math.cosFromSin(_t2, angleY), _t0 * _t2, _t2 * _t3);
        return dest;
    }

    private static void rotateXYZ_mulAdd_s46fc8de6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t7) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t1, -(_t7 * _t4)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t4)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t1 * _t3)))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t7, _t1, _t0 * _t4))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, -(_t6 * _t1)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t1 * _t5)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t5)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t5)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateXZY(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (SimdSupport.USE_FMA) return rotateXZY_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    public static double[] rotateXZY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        rotateXZY_fma_se755b3f7_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t2, angleY), _t4, Math.cosFromSin(_t1, angleZ), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateXZY_fma_se755b3f7_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t9) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t3, -(_t2 * _t4))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t5), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t3, _t0 * _t2)))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0 * _t5), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t4 * _t5), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t1))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t4 * _t3)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t5), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, -(_t0 * _t3))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateXZY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        rotateXZY_mulAdd_s155e0d8a_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t2, angleY), _t4, Math.cosFromSin(_t1, angleZ), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateXZY_mulAdd_s155e0d8a_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t9) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t3, -(_t2 * _t4)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t5)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t3, _t0 * _t2)))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0 * _t5)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t4 * _t5)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t1))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t4 * _t3))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t5)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, -(_t0 * _t3))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateY(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateY_fma(dest, destOffset, src, srcOffset, angle);
        return rotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] rotateY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateYXZ(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (SimdSupport.USE_FMA) return rotateYXZ_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static double[] rotateYXZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        rotateYXZ_fma_s75ba388b_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, Math.cosFromSin(_t2, angleZ), Math.cosFromSin(_t0, angleX), _t0 * _t1, _t0 * _t3);
        return dest;
    }

    private static void rotateYXZ_fma_s75ba388b_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t2, -(_t1 * _t4))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t3 * _t4)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t5))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t4, _t1 * _t2)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, -(_t2 * _t3))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t4))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t3), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t5), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateYXZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        rotateYXZ_mulAdd_sc6df9532_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, Math.cosFromSin(_t2, angleZ), Math.cosFromSin(_t0, angleX), _t0 * _t1, _t0 * _t3);
        return dest;
    }

    private static void rotateYXZ_mulAdd_sc6df9532_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t2, -(_t1 * _t4)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t3 * _t4))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t5))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t4, _t1 * _t2))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, -(_t2 * _t3)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t4))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t5)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateYZX(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (SimdSupport.USE_FMA) return rotateYZX_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    public static double[] rotateYZX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        rotateYZX_fma_sab9536f_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t1, angleZ), _t4, Math.cosFromSin(_t2, angleX), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateYZX_fma_sab9536f_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t9) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t3)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t4 * _t3), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, _t2 * _t4)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(_t9 * _t5))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t3))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t5, _t4, -(_t6 * _t2))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, _t0 * _t5)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateYZX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        rotateYZX_mulAdd_sc72113f2_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t1, angleZ), _t4, Math.cosFromSin(_t2, angleX), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateYZX_mulAdd_sc72113f2_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t9) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t3))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t4 * _t3)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, _t2 * _t4))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(_t9 * _t5)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t3))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t5, _t4, -(_t6 * _t2)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, _t0 * _t5))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateZ(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return rotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] rotateZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)));
        var _c1 = _sv2.fma(_sv1, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)));
        var _c1 = _sv2.mul(_sv1).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] rotateZXY(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (SimdSupport.USE_FMA) return rotateZXY_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    public static double[] rotateZXY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        rotateZXY_fma_sdc57f80b_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t1, angleX), Math.cosFromSin(_t0, angleY), _t5, _t1 * _t2, _t1 * _t5);
        return dest;
    }

    private static void rotateZXY_fma_sdc57f80b_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t3)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t4, _t5, -(_t6 * _t0))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t0, _t2 * _t4)))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t1), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t5), _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t4), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t0 * _t5)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t2, -(_t8 * _t4))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateZXY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        rotateZXY_mulAdd_sce414c02_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t1, angleX), Math.cosFromSin(_t0, angleY), _t5, _t1 * _t2, _t1 * _t5);
        return dest;
    }

    private static void rotateZXY_mulAdd_sce414c02_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t0 * _t3))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t4, _t5, -(_t6 * _t0)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t0, _t2 * _t4)))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t5)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t4)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t0 * _t5))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t2, -(_t8 * _t4))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateZYX(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (SimdSupport.USE_FMA) return rotateZYX_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    public static double[] rotateZYX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        rotateZYX_fma_s770ed58b_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t0, angleY), _t4, Math.cosFromSin(_t2, angleX), _t0 * _t1, _t0 * _t4);
        return dest;
    }

    private static void rotateZYX_fma_s770ed58b_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, -_t0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t4), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t3))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t3), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t2, -(_t1 * _t5))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t5 * _t4)))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t3), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t5, _t2 * _t1)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, -(_t2 * _t4))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] rotateZYX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        rotateZYX_mulAdd_se9739456_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, Math.cosFromSin(_t0, angleY), _t4, Math.cosFromSin(_t2, angleX), _t0 * _t1, _t0 * _t4);
        return dest;
    }

    private static void rotateZYX_mulAdd_se9739456_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t3 * _t4)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t1 * _t3))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * _t3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t2, -(_t1 * _t5)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t5 * _t4)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t5 * _t3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t5, _t2 * _t1))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, -(_t2 * _t4))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, vX));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, vY));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, vZ));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 0]));
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 2]));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _c0 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset));
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
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
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, pivotX * _t0), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, pivotY * _t0), _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, pivotZ * _t0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, pivotX * _t0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, pivotY * _t0)).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, pivotZ * _t0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * _t0), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * _t0), _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * _t0), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * _t0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * _t0)).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * _t0)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, sX).mul(_sv0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, sY).mul(_sv1);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, sZ).mul(_sv2);
        var _c3 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, pivotX * (1.0 - sX)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, pivotY * (1.0 - sY)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, pivotZ * (1.0 - sZ)), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, sX).mul(_sv0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, sY).mul(_sv1);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, sZ).mul(_sv2);
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, pivotX * (1.0 - sX))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, pivotY * (1.0 - sY))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, pivotZ * (1.0 - sZ))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset + 0];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, _sx).mul(_sv0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).mul(_sv1);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).mul(_sv2);
        var _c3 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * (1.0 - _sx)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0 - _sy)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0 - _sz)), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset + 0];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, _sx).mul(_sv0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).mul(_sv1);
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).mul(_sv2);
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * (1.0 - _sx))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0 - _sy))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0 - _sz))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
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
        double _t28 = Math.fma(lightX, planeX, lightY * planeY);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, lightW * planeX), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, lightZ * planeX), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightY, planeY, _t14))), _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightY * planeX))))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, lightW * planeY), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, lightZ * planeY), _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightX, planeX, _t14))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightX * planeY))))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, lightW * planeZ), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, _t28)), _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, lightY * planeZ), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightZ, planeZ, _t28)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, lightZ * planeW), _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, lightY * planeW), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightX * planeW))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
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
        double _t28 = Math.fma(lightX, planeX, lightY * planeY);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, lightW * planeX)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, lightZ * planeX)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightY, planeY, _t14)))).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightY * planeX))))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, lightW * planeY)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, lightZ * planeY)).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightX, planeX, _t14)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightX * planeY))))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, lightW * planeZ)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, _t28))).add(_sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, lightY * planeZ)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(lightZ, planeZ, _t28))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, lightZ * planeW)).add(_sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, lightY * planeW)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(lightX * planeW))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
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
        double _planex = plane[planeOffset + 0];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _lightz = light[lightOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _lighty = light[lightOffset + 1];
        double _planey = plane[planeOffset + 1];
        double _lightx = light[lightOffset + 0];
        double _planez = plane[planeOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _t14 = _lightz * _planez;
        double _t28 = Math.fma(_lightx, _planex, _lighty * _planey);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _lightw * _planex), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _lightz * _planex), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lighty, _planey, _t14))), _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lighty * _planex))))));
        var _c1 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _lightw * _planey), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _lightz * _planey), _sv3.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lightx, _planex, _t14))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lightx * _planey))))));
        var _c2 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _lightw * _planez), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, _t28)), _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, _lighty * _planez), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightz, _planez, _t28)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _lightz * _planew), _sv4.fma(DoubleVector.broadcast(SIMD_SPECIES, _lighty * _planew), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lightx * _planew))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
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
        double _planex = plane[planeOffset + 0];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _lightz = light[lightOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _lighty = light[lightOffset + 1];
        double _planey = plane[planeOffset + 1];
        double _lightx = light[lightOffset + 0];
        double _planez = plane[planeOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _t14 = _lightz * _planez;
        double _t28 = Math.fma(_lightx, _planex, _lighty * _planey);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = DoubleVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _lightw * _planex)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _lightz * _planex)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lighty, _planey, _t14)))).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lighty * _planex))))));
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _lightw * _planey)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _lightz * _planey)).add(_sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lightx, _planex, _t14)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lightx * _planey))))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _lightw * _planez)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, _t28))).add(_sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _lighty * _planez)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_lightz, _planez, _t28))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _lightz * _planew)).add(_sv4.mul(DoubleVector.broadcast(SIMD_SPECIES, _lighty * _planew)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -(_lightx * _planew))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shear(double[] dest, int destOffset, double[] src, int srcOffset, double xy, double xz, double yx, double yz, double zx, double zy) {
        if (SimdSupport.USE_FMA) return shear_fma(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
        return shear_mulAdd(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
    }

    public static double[] shear_fma(double[] dest, int destOffset, double[] src, int srcOffset, double xy, double xz, double yx, double yz, double zx, double zy) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, yx).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, zx).fma(_sv1, _sv2));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, xy).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, zy).fma(_sv1, _sv0));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, xz).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, yz).fma(_sv0, _sv1));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] shear_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double xy, double xz, double yx, double yz, double zx, double zy) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, yx).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, zx).mul(_sv1).add(_sv2));
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, xy).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, zy).mul(_sv1).add(_sv0));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, xz).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, yz).mul(_sv0).add(_sv1));
        var _c3 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] tile(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double w, double h) {
        if (SimdSupport.USE_FMA) return tile_fma(dest, destOffset, src, srcOffset, x, y, w, h);
        return tile_mulAdd(dest, destOffset, src, srcOffset, x, y, w, h);
    }

    public static double[] tile_fma(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double w, double h) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, w).mul(_sv0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, h).mul(_sv1);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, x, w - 1.0)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, y, h - 1.0)), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] tile_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double w, double h) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = DoubleVector.broadcast(SIMD_SPECIES, w).mul(_sv0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, h).mul(_sv1);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, x, w - 1.0))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(-2.0, y, h - 1.0))).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return translate_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static double[] translate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, vX), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, vY), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, vZ), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, vX)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, vY)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, vZ)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return translate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] translate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 0]), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 1]), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 2]), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] translate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 0])).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 1])).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 2])).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static double[] mulVec4(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ, double vW) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
    }

    public static double[] mulVec4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ, double vW) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(SIMD_SPECIES, vW), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, vZ), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, vX), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, vY)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mulVec4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ, double vW) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(SIMD_SPECIES, vW)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, vZ)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, vX)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, vY)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mulVec4(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] mulVec4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 3]), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 2]), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 0]), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 1])))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mulVec4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _c0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 3])).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 2])).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 0])).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 1])))));
        _c0.intoArray(dest, destOffset);
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
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 12).intoArray(dest, destOffset + 12);
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 6).intoArray(dest, destOffset + 6);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 8).intoArray(dest, destOffset + 8);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 10).intoArray(dest, destOffset + 10);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 12).intoArray(dest, destOffset + 12);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 14).intoArray(dest, destOffset + 14);
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
