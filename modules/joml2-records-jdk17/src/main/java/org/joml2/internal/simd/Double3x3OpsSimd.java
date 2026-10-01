// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Double3x3Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double3x3Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double3x3OpsSimd {
    private Double3x3OpsSimd() {}
    private static final VectorSpecies<Double> SIMD_SPECIES = DoubleVector.SPECIES_256;
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        double _v22 = v[vOffset + 8];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, v, vOffset);
        var _vcp1 = DoubleVector.fromArray(SIMD_SPECIES, v, vOffset + 4);
        _vcp0.intoArray(dest, destOffset);
        _vcp1.intoArray(dest, destOffset + 4);
        dest[destOffset + 8] = _v22;
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double tX, double tY) {
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self22 = src[srcOffset + 8];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = tX;
        dest[destOffset + 7] = tY;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double[] t, int tOffset) {
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self22 = src[srcOffset + 8];
        double _tx = t[tOffset];
        double _ty = t[tOffset + 1];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = _tx;
        dest[destOffset + 7] = _ty;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self00, vX, Math.fma(_self01, vY, _self02));
        dest[destOffset + 7] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        dest[destOffset + 8] = Math.fma(_self20, vX, Math.fma(_self21, vY, _self22));
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02));
        dest[destOffset + 7] = Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12));
        dest[destOffset + 8] = Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22));
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
            dest[destOffset + 8] = src[srcOffset + 8];
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            dest[destOffset + 8] = src[srcOffset + 8];
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 6).intoArray(dest, destOffset + 6);
            dest[destOffset + 8] = src[srcOffset + 8];
        }
    }


    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 9; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 9);
        } else {
            for (int _i = 0; _i < count * 9; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 9; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 9);
        } else {
            for (int _i = 0; _i < count * 9; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 9; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 9; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 9; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 9);
            } else {
                for (int _i = 0; _i < count * 9; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 9; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 9; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
