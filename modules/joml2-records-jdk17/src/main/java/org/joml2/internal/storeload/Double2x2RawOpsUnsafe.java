// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2x2RawOpsUnsafe extends Double2x2RawOps {
    public Double2x2 storeCMUnsafe(Double2x2 self, long address) {
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m01());
        U.putDouble(address + 24L, self.m11());
        return self;
    }

    public Double2x2 storeCMHeap(Double2x2 self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8L, self.m10());
        U.putDouble(bytes, address + 16L, self.m01());
        U.putDouble(bytes, address + 24L, self.m11());
        return self;
    }

    public Double2x2 loadCMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c2 = _U.getDouble(address + 8L);
        double _c1 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadCMHeap(byte[] bytes, long address) {
        double _c0 = U.getDouble(bytes, address);
        double _c2 = U.getDouble(bytes, address + 8L);
        double _c1 = U.getDouble(bytes, address + 16L);
        double _c3 = U.getDouble(bytes, address + 24L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address) {
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m10());
        U.putFloat(address + 8L, (float) self.m01());
        U.putFloat(address + 12L, (float) self.m11());
        return self;
    }

    public Double2x2 storeCMFloatHeap(Double2x2 self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.m00());
        U.putFloat(bytes, address + 4L, (float) self.m10());
        U.putFloat(bytes, address + 8L, (float) self.m01());
        U.putFloat(bytes, address + 12L, (float) self.m11());
        return self;
    }

    public Double2x2 loadCMFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c2 = _U.getFloat(address + 4L);
        double _c1 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadCMFloatHeap(byte[] bytes, long address) {
        double _c0 = U.getFloat(bytes, address);
        double _c2 = U.getFloat(bytes, address + 4L);
        double _c1 = U.getFloat(bytes, address + 8L);
        double _c3 = U.getFloat(bytes, address + 12L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeRMUnsafe(Double2x2 self, long address) {
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m10());
        U.putDouble(address + 24L, self.m11());
        return self;
    }

    public Double2x2 storeRMHeap(Double2x2 self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8L, self.m01());
        U.putDouble(bytes, address + 16L, self.m10());
        U.putDouble(bytes, address + 24L, self.m11());
        return self;
    }

    public Double2x2 loadRMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadRMHeap(byte[] bytes, long address) {
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8L);
        double _c2 = U.getDouble(bytes, address + 16L);
        double _c3 = U.getDouble(bytes, address + 24L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address) {
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m01());
        U.putFloat(address + 8L, (float) self.m10());
        U.putFloat(address + 12L, (float) self.m11());
        return self;
    }

    public Double2x2 storeRMFloatHeap(Double2x2 self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.m00());
        U.putFloat(bytes, address + 4L, (float) self.m01());
        U.putFloat(bytes, address + 8L, (float) self.m10());
        U.putFloat(bytes, address + 12L, (float) self.m11());
        return self;
    }

    public Double2x2 loadRMFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadRMFloatHeap(byte[] bytes, long address) {
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4L);
        double _c2 = U.getFloat(bytes, address + 8L);
        double _c3 = U.getFloat(bytes, address + 12L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeCMUnsafe(Double2x2 self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        return self;
    }

    public Double2x2 storeCMHeap(Double2x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8, self.m10());
        U.putDouble(bytes, _p1, self.m01());
        U.putDouble(bytes, _p1 + 8, self.m11());
        return self;
    }

    public Double2x2 loadCMUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        double _c0 = U.getDouble(address);
        double _c2 = U.getDouble(address + 8);
        double _c1 = U.getDouble(_p1);
        double _c3 = U.getDouble(_p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadCMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        double _c0 = U.getDouble(bytes, address);
        double _c2 = U.getDouble(bytes, address + 8);
        double _c1 = U.getDouble(bytes, _p1);
        double _c3 = U.getDouble(bytes, _p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m10());
        U.putFloat(_p1, (float) self.m01());
        U.putFloat(_p1 + 4, (float) self.m11());
        return self;
    }

    public Double2x2 storeCMFloatHeap(Double2x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, (float) self.m00());
        U.putFloat(bytes, address + 4, (float) self.m10());
        U.putFloat(bytes, _p1, (float) self.m01());
        U.putFloat(bytes, _p1 + 4, (float) self.m11());
        return self;
    }

    public Double2x2 loadCMFloatUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        double _c0 = U.getFloat(address);
        double _c2 = U.getFloat(address + 4);
        double _c1 = U.getFloat(_p1);
        double _c3 = U.getFloat(_p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadCMFloatHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        double _c0 = U.getFloat(bytes, address);
        double _c2 = U.getFloat(bytes, address + 4);
        double _c1 = U.getFloat(bytes, _p1);
        double _c3 = U.getFloat(bytes, _p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeRMUnsafe(Double2x2 self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        return self;
    }

    public Double2x2 storeRMHeap(Double2x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8, self.m01());
        U.putDouble(bytes, _p1, self.m10());
        U.putDouble(bytes, _p1 + 8, self.m11());
        return self;
    }

    public Double2x2 loadRMUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8);
        double _c2 = U.getDouble(_p1);
        double _c3 = U.getDouble(_p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadRMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8);
        double _c2 = U.getDouble(bytes, _p1);
        double _c3 = U.getDouble(bytes, _p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m01());
        U.putFloat(_p1, (float) self.m10());
        U.putFloat(_p1 + 4, (float) self.m11());
        return self;
    }

    public Double2x2 storeRMFloatHeap(Double2x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, (float) self.m00());
        U.putFloat(bytes, address + 4, (float) self.m01());
        U.putFloat(bytes, _p1, (float) self.m10());
        U.putFloat(bytes, _p1 + 4, (float) self.m11());
        return self;
    }

    public Double2x2 loadRMFloatUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4);
        double _c2 = U.getFloat(_p1);
        double _c3 = U.getFloat(_p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 loadRMFloatHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4);
        double _c2 = U.getFloat(bytes, _p1);
        double _c3 = U.getFloat(bytes, _p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    public Double2x2 storeCM3x3Unsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m01());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeCM3x3Heap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m01());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeCM3x3FloatUnsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.m01());
        _U.putFloat(address + 16L, (float) self.m11());
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeCM3x3FloatHeap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m10());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.m01());
        _U.putFloat(bytes, address + 16L, (float) self.m11());
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeRM3x3Unsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeRM3x3Heap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m10());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeRM3x3FloatUnsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m01());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.m10());
        _U.putFloat(address + 16L, (float) self.m11());
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeRM3x3FloatHeap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m01());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.m10());
        _U.putFloat(bytes, address + 16L, (float) self.m11());
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeCM4x4Unsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Double2x2 storeCM4x4Heap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m01());
        _U.putDouble(bytes, address + 40L, self.m11());
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 0.0);
        _U.putDouble(bytes, address + 72L, 0.0);
        _U.putDouble(bytes, address + 80L, 1.0);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Double2x2 storeCM4x4FloatUnsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.m01());
        _U.putFloat(address + 20L, (float) self.m11());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Double2x2 storeCM4x4FloatHeap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m10());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.m01());
        _U.putFloat(bytes, address + 20L, (float) self.m11());
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 0.0f);
        _U.putFloat(bytes, address + 36L, 0.0f);
        _U.putFloat(bytes, address + 40L, 1.0f);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }

    public Double2x2 storeRM4x4Unsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m10());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Double2x2 storeRM4x4Heap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m10());
        _U.putDouble(bytes, address + 40L, self.m11());
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 0.0);
        _U.putDouble(bytes, address + 72L, 0.0);
        _U.putDouble(bytes, address + 80L, 1.0);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Double2x2 storeRM4x4FloatUnsafe(Double2x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m01());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.m10());
        _U.putFloat(address + 20L, (float) self.m11());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Double2x2 storeRM4x4FloatHeap(Double2x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m01());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.m10());
        _U.putFloat(bytes, address + 20L, (float) self.m11());
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 0.0f);
        _U.putFloat(bytes, address + 36L, 0.0f);
        _U.putFloat(bytes, address + 40L, 1.0f);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }
}
