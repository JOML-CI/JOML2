// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Double2Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double2Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double2OpsSimd {
    private Double2OpsSimd() {}

    public static double[] add(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).add(DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).add(DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L) + b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L));
        }
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).sub(DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).sub(DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L) - b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L));
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L) * b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L));
        }
        return dest;
    }

    public static double[] div(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).div(DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).div(DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L) / b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L));
        }
        return dest;
    }

    public static double[] min(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).min(DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).min(DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, java.lang.Math.min(a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L), b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L)));
        }
        return dest;
    }

    public static double[] max(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).max(DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).max(DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, java.lang.Math.max(a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L), b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L)));
        }
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        }
        return dest;
    }

    public static double[] abs(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment abs(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).abs().intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).abs().intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L)));
        }
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, double t, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, double t, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        lerp_vbc2e5dc2(dest, destOffset, a, aOffset, b, bOffset, t, count * 2);
        return dest;
    }

    private static void lerp_vbc2e5dc2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, double t, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(DoubleVector.broadcast(_sp, t), DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).sub(_v0), _v0).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(DoubleVector.broadcast(DoubleVector.SPECIES_256, t), DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).sub(_v0), _v0).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L) + t * (b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L) - a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L)));
        }
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).mul(s).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).mul(s).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L) * s);
        }
        return dest;
    }

    public static double[] fma(double[] dest, int destOffset, double[] self, int selfOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment self, long selfOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        fma_v7754bb17(dest, destOffset, self, selfOffset, a, aOffset, b, bOffset, count * 2);
        return dest;
    }

    private static void fma_v7754bb17(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment self, long selfOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            SimdMath.fma(DoubleVector.fromMemorySegment(_sp, self, selfOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()), DoubleVector.fromMemorySegment(_sp, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()), DoubleVector.fromMemorySegment(_sp, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            SimdMath.fma(DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, self, selfOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()), DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()), DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + _i * 8L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, Math.fma(self.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, selfOffset + _i * 8L), a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + _i * 8L), b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + _i * 8L)));
        }
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

    private static void copyArrSeg(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L);
    }

    private static void copySegArr(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromArray(_sp, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src[srcOffset + _i]);
    }

    private static void copySegSeg(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
    }

    private static void copyArrArr_one(double[] dest, int destOffset, double[] src, int srcOffset) {
        DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
    }

    private static void copyArrSeg_one(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }


    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 2);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset * 8L);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && srcOffset <= src.limit() - (count > 1073741823 ? -1 : count * 2)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 2);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 2);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 2);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 2);
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr_one(_destSeg, destOffset * 8L, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && destOffset <= dest.limit() - (count > 1073741823 ? -1 : count * 2)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 2);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, destOffset * 8L, src, srcOffset, count * 2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 8L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr_one(_destSeg, destOffset * 8L, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg_one(_destSeg, destOffset * 8L, _srcSeg, srcOffset * 8L);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && destOffset <= dest.limit() - (count > 1073741823 ? -1 : count * 2)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && srcOffset <= src.limit() - (count > 1073741823 ? -1 : count * 2)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 2);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 8L, count * 2);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && srcOffset <= src.limit() - (count > 1073741823 ? -1 : count * 2)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 8L, _srcArr, _srcOff, count * 2);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset * 8L, count * 2);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 8L, _srcSeg, srcOffset);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && destOffset <= dest.limit() - (count > 1073741823 ? -1 : count * 2)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 2);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset, count * 2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 8L, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && destOffset <= dest.limit() - (count > 1073741823 ? -1 : count * 2)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 2);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, src, srcOffset, count * 2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr_one(_destSeg, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 2);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(_destSeg, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset, _srcSeg, srcOffset * 8L);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && srcOffset <= src.limit() - (count > 1073741823 ? -1 : count * 2)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 2);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 8L, count * 2);
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
        copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset, count * 2);
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
        copySegSeg(_destSeg, destOffset, src, srcOffset, count * 2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(dest, destOffset, _srcSeg, srcOffset * 8L);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && srcOffset <= src.limit() - (count > 1073741823 ? -1 : count * 2)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 2);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 2);
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
        copySegSeg(dest, destOffset, _srcSeg, srcOffset, count * 2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copySegSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copySegSeg(dest, destOffset, src, srcOffset, count * 2);
        return dest;
    }
}
