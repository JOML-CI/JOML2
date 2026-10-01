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
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] add(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.add(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.add(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L) + b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L));
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

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.sub(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.sub(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L) - b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L));
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

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L) * b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L));
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

    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.div(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.div(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L) / b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L));
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

    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.min(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.min(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, Math.min(a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L), b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L)));
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

    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.max(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.max(_v1).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, Math.max(a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L), b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L)));
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

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.neg().intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.neg().intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (long) _i * 8L));
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

    public static java.lang.foreign.MemorySegment abs(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.abs().intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.abs().intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (long) _i * 8L)));
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

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, double t, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(DoubleVector.broadcast(_sp, t), _v1.sub(_v0), _v0).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(DoubleVector.broadcast(DoubleVector.SPECIES_256, t), _v1.sub(_v0), _v0).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L) + t * (b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L) - a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L)));
        }
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
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

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(s).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(s).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (long) _i * 8L) * s);
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

    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment self, long selfOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 1073741823) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 2;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = DoubleVector.fromMemorySegment(_sp, self, selfOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v2 = DoubleVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(_v0, _v1, _v2).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, self, selfOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v1 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, a, aOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            var _v2 = DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, b, bOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(_v0, _v1, _v2).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, Math.fma(self.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, selfOffset + (long) _i * 8L), a.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, aOffset + (long) _i * 8L), b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + (long) _i * 8L)));
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

    private static void copyArrSeg(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (long) _i * 8L);
    }

    private static void copySegArr(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromArray(_sp, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, src[srcOffset + _i]);
    }

    private static void copySegSeg(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (long) _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (long) _i * 8L));
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
            copyArrSeg_one(dest, destOffset, _srcSeg, (long) srcOffset * 8);
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
            copyArrSeg(dest, destOffset, _srcSeg, (long) srcOffset * 8, count * 2);
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
            copySegArr_one(_destSeg, (long) destOffset * 8, src, srcOffset);
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
            copySegArr(_destSeg, (long) destOffset * 8, src, srcOffset, count * 2);
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
                copyArrSeg_one(_destArr, _destOff, _srcSeg, (long) srcOffset * 8);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr_one(_destSeg, (long) destOffset * 8, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg_one(_destSeg, (long) destOffset * 8, _srcSeg, (long) srcOffset * 8);
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
                copyArrSeg(_destArr, _destOff, _srcSeg, (long) srcOffset * 8, count * 2);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 1073741823 ? -1 : count * 2) >= 0 && srcOffset <= src.limit() - (count > 1073741823 ? -1 : count * 2)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, (long) destOffset * 8, _srcArr, _srcOff, count * 2);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, (long) destOffset * 8, _srcSeg, (long) srcOffset * 8, count * 2);
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
            copySegSeg_one(_destSeg, (long) destOffset * 8, _srcSeg, srcOffset);
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
            copySegSeg(_destSeg, (long) destOffset * 8, _srcSeg, srcOffset, count * 2);
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
            copySegSeg_one(_destSeg, (long) destOffset * 8, src, srcOffset);
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
            copySegSeg(_destSeg, (long) destOffset * 8, src, srcOffset, count * 2);
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
            copySegSeg_one(_destSeg, destOffset, _srcSeg, (long) srcOffset * 8);
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
            copySegSeg(_destSeg, destOffset, _srcSeg, (long) srcOffset * 8, count * 2);
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
            copySegSeg_one(dest, destOffset, _srcSeg, (long) srcOffset * 8);
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
            copySegSeg(dest, destOffset, _srcSeg, (long) srcOffset * 8, count * 2);
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
