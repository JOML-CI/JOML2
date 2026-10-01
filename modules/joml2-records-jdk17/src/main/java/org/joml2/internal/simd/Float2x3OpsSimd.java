// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Float2x3Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Float2x3Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Float2x3OpsSimd {
    private Float2x3OpsSimd() {}
    private static final VectorSpecies<Float> SIMD_SPECIES = FloatVector.SPECIES_128;

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        float _v02 = v[vOffset + 4];
        float _v12 = v[vOffset + 5];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, v, vOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _v02;
        dest[destOffset + 5] = _v12;
        return dest;
    }

    public static float[] setMat2x2(float[] dest, int destOffset, float[] m, int mOffset) {
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY) {
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = tX;
        dest[destOffset + 5] = tY;
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        float _tx = t[tOffset];
        float _ty = t[tOffset + 1];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _tx;
        dest[destOffset + 5] = _ty;
        return dest;
    }

    public static float[] to2x2(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self02 + vX;
        dest[destOffset + 5] = _self12 + vY;
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self02 + _vx;
        dest[destOffset + 5] = _self12 + _vy;
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = Math.fma(_self00, vX, Math.fma(_self01, vY, _self02));
        dest[destOffset + 5] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12));
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
        dest[destOffset + 4] = src[srcOffset + 4];
        dest[destOffset + 5] = src[srcOffset + 5];
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 6);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 6; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 357913941 ? -1 : count * 6) >= 0 && srcOffset <= src.limit() - (count > 357913941 ? -1 : count * 6)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 6);
        } else {
            for (int _i = 0; _i < count * 6; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 6; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 357913941 ? -1 : count * 6) >= 0 && destOffset <= dest.limit() - (count > 357913941 ? -1 : count * 6)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 6);
        } else {
            for (int _i = 0; _i < count * 6; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 6; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 6; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 6; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 357913941 ? -1 : count * 6) >= 0 && destOffset <= dest.limit() - (count > 357913941 ? -1 : count * 6)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 357913941 ? -1 : count * 6) >= 0 && srcOffset <= src.limit() - (count > 357913941 ? -1 : count * 6)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 6);
            } else {
                for (int _i = 0; _i < count * 6; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 357913941 ? -1 : count * 6) >= 0 && srcOffset <= src.limit() - (count > 357913941 ? -1 : count * 6)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 6; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 6; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
