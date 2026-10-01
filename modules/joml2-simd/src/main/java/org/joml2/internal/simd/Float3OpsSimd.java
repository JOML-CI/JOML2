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

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.add(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.add(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.add(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L) + b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L));
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

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.sub(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.sub(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.sub(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L) - b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L));
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

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L) * b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L));
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

    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.div(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.div(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.div(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L) / b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L));
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

    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.min(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.min(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.min(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, Math.min(a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L)));
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

    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.max(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.max(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.max(_v1).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, Math.max(a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L)));
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

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.neg().intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.neg().intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.neg().intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L));
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

    public static java.lang.foreign.MemorySegment abs(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.abs().intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.abs().intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.abs().intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L)));
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

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, float t, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(FloatVector.broadcast(_sp, t), _v1.sub(_v0), _v0).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_256, t), _v1.sub(_v0), _v0).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(FloatVector.broadcast(FloatVector.SPECIES_128, t), _v1.sub(_v0), _v0).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L) + t * (b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L) - a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L)));
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

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(s).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(s).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            _v0.mul(s).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L) * s);
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

    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment self, long selfOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length()) {
            var _v0 = FloatVector.fromMemorySegment(_sp, self, selfOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(_sp, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v2 = FloatVector.fromMemorySegment(_sp, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(_v0, _v1, _v2).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 8; _i += 8) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, self, selfOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v2 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(_v0, _v1, _v2).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i <= n - 4; _i += 4) {
            var _v0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, self, selfOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v1 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, a, aOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            var _v2 = FloatVector.fromMemorySegment(FloatVector.SPECIES_128, b, bOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
            SimdMath.fma(_v0, _v1, _v2).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        }
        for (; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, Math.fma(self.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, selfOffset + (long) _i * 4L), a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + (long) _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + (long) _i * 4L)));
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

    private static void copyArrSeg(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L);
    }

    private static void copySegArr(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromArray(_sp, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src[srcOffset + _i]);
    }

    private static void copySegSeg(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L));
    }

    private static void copyArrArr_one(float[] dest, int destOffset, float[] src, int srcOffset) {
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = src[srcOffset + 1];
        dest[destOffset + 2] = src[srcOffset + 2];
    }

    private static void copyArrSeg_one(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        dest[destOffset] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        dest[destOffset + 1] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest[destOffset + 2] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src[srcOffset]);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, src[srcOffset + 1]);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, src[srcOffset + 2]);
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L));
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
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(dest, destOffset, _srcSeg, (long) srcOffset * 4);
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
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, (long) srcOffset * 4, count * 3);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 3);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 3);
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr_one(_destSeg, (long) destOffset * 4, src, srcOffset);
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
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, (long) destOffset * 4, src, srcOffset, count * 3);
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
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, (long) srcOffset * 4);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr_one(_destSeg, (long) destOffset * 4, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg_one(_destSeg, (long) destOffset * 4, _srcSeg, (long) srcOffset * 4);
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
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, (long) srcOffset * 4, count * 3);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, (long) destOffset * 4, _srcArr, _srcOff, count * 3);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, (long) destOffset * 4, _srcSeg, (long) srcOffset * 4, count * 3);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, (long) destOffset * 4, _srcSeg, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 3);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, (long) destOffset * 4, _srcSeg, srcOffset, count * 3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg_one(_destSeg, (long) destOffset * 4, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 3);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, (long) destOffset * 4, src, srcOffset, count * 3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr_one(_destSeg, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 3);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(_destSeg, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset, _srcSeg, (long) srcOffset * 4);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 3);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, (long) srcOffset * 4, count * 3);
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
        copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset, count * 3);
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
        copySegSeg(_destSeg, destOffset, src, srcOffset, count * 3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(dest, destOffset, _srcSeg, (long) srcOffset * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 3);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, (long) srcOffset * 4, count * 3);
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
        copySegSeg(dest, destOffset, _srcSeg, srcOffset, count * 3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copySegSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copySegSeg(dest, destOffset, src, srcOffset, count * 3);
        return dest;
    }
}
