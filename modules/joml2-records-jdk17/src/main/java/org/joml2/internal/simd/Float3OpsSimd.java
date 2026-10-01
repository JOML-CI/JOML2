// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Float3Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Float3Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Float3OpsSimd {
    private Float3OpsSimd() {}
    private static final int PREFERRED_LANES = FloatVector.SPECIES_PREFERRED.length();

    public static float[] add(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
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
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = src[srcOffset + 1];
        dest[destOffset + 2] = src[srcOffset + 2];
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 3);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 3; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 3);
        } else {
            for (int _i = 0; _i < count * 3; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 3; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 3);
        } else {
            for (int _i = 0; _i < count * 3; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 3; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 3; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 3; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 3);
            } else {
                for (int _i = 0; _i < count * 3; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 3; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 3; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
