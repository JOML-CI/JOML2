// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Double3Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double3Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double3OpsSimd {
    private Double3OpsSimd() {}
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] add(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            _v0.add(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            _v0.add(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + b[bOffset + _i];
        }
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            _v0.sub(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            _v0.sub(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] - b[bOffset + _i];
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            _v0.mul(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            _v0.mul(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] * b[bOffset + _i];
        }
        return dest;
    }

    public static double[] div(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            _v0.div(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            _v0.div(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] / b[bOffset + _i];
        }
        return dest;
    }

    public static double[] min(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            _v0.min(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            _v0.min(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.min(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    public static double[] max(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            _v0.max(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            _v0.max(_v1).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.max(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, src, srcOffset + _i);
            _v0.neg().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i);
            _v0.neg().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    public static double[] abs(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, src, srcOffset + _i);
            _v0.abs().intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i);
            _v0.abs().intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = Math.abs(src[srcOffset + _i]);
        }
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, double t, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            SimdMath.fma(DoubleVector.broadcast(_sp, t), _v1.sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            SimdMath.fma(DoubleVector.broadcast(DoubleVector.SPECIES_256, t), _v1.sub(_v0), _v0).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + t * (b[bOffset + _i] - a[aOffset + _i]);
        }
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, src, srcOffset + _i);
            _v0.mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i);
            _v0.mul(s).intoArray(dest, destOffset + _i);
        }
        for (; _i < n; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] * s;
        }
        return dest;
    }

    public static double[] fma(double[] dest, int destOffset, double[] self, int selfOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromArray(_sp, self, selfOffset + _i);
            var _v1 = DoubleVector.fromArray(_sp, a, aOffset + _i);
            var _v2 = DoubleVector.fromArray(_sp, b, bOffset + _i);
            SimdMath.fma(_v0, _v1, _v2).intoArray(dest, destOffset + _i);
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromArray(DoubleVector.SPECIES_256, self, selfOffset + _i);
            var _v1 = DoubleVector.fromArray(DoubleVector.SPECIES_256, a, aOffset + _i);
            var _v2 = DoubleVector.fromArray(DoubleVector.SPECIES_256, b, bOffset + _i);
            SimdMath.fma(_v0, _v1, _v2).intoArray(dest, destOffset + _i);
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
        DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
        dest[destOffset + 2] = src[srcOffset + 2];
    }


    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 3);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 3; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 3);
        } else {
            for (int _i = 0; _i < count * 3; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 3; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 3);
        } else {
            for (int _i = 0; _i < count * 3; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 3; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
                double[] _srcArr = src.array();
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

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 3);
            } else {
                for (int _i = 0; _i < count * 3; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
                double[] _srcArr = src.array();
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
