// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x2RawOpsUnsafe extends Double4x2RawOps {
    public Double4x2 storeCMUnsafe(Double4x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, self.m20());
        _U.putDouble(address + 24L, self.m30());
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, self.m21());
        _U.putDouble(address + 56L, self.m31());
        return self;
    }

    public Double4x2 storeCMHeap(Double4x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, self.m20());
        _U.putDouble(bytes, address + 24L, self.m30());
        _U.putDouble(bytes, address + 32L, self.m01());
        _U.putDouble(bytes, address + 40L, self.m11());
        _U.putDouble(bytes, address + 48L, self.m21());
        _U.putDouble(bytes, address + 56L, self.m31());
        return self;
    }

    public Double4x2 loadCMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c2 = _U.getDouble(address + 8L);
        double _c4 = _U.getDouble(address + 16L);
        double _c6 = _U.getDouble(address + 24L);
        double _c1 = _U.getDouble(address + 32L);
        double _c3 = _U.getDouble(address + 40L);
        double _c5 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadCMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c2 = _U.getDouble(bytes, address + 8L);
        double _c4 = _U.getDouble(bytes, address + 16L);
        double _c6 = _U.getDouble(bytes, address + 24L);
        double _c1 = _U.getDouble(bytes, address + 32L);
        double _c3 = _U.getDouble(bytes, address + 40L);
        double _c5 = _U.getDouble(bytes, address + 48L);
        double _c7 = _U.getDouble(bytes, address + 56L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m10());
        _U.putFloat(address + 8L, (float) self.m20());
        _U.putFloat(address + 12L, (float) self.m30());
        _U.putFloat(address + 16L, (float) self.m01());
        _U.putFloat(address + 20L, (float) self.m11());
        _U.putFloat(address + 24L, (float) self.m21());
        _U.putFloat(address + 28L, (float) self.m31());
        return self;
    }

    public Double4x2 storeCMFloatHeap(Double4x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m10());
        _U.putFloat(bytes, address + 8L, (float) self.m20());
        _U.putFloat(bytes, address + 12L, (float) self.m30());
        _U.putFloat(bytes, address + 16L, (float) self.m01());
        _U.putFloat(bytes, address + 20L, (float) self.m11());
        _U.putFloat(bytes, address + 24L, (float) self.m21());
        _U.putFloat(bytes, address + 28L, (float) self.m31());
        return self;
    }

    public Double4x2 loadCMFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c2 = _U.getFloat(address + 4L);
        double _c4 = _U.getFloat(address + 8L);
        double _c6 = _U.getFloat(address + 12L);
        double _c1 = _U.getFloat(address + 16L);
        double _c3 = _U.getFloat(address + 20L);
        double _c5 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadCMFloatHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c2 = _U.getFloat(bytes, address + 4L);
        double _c4 = _U.getFloat(bytes, address + 8L);
        double _c6 = _U.getFloat(bytes, address + 12L);
        double _c1 = _U.getFloat(bytes, address + 16L);
        double _c3 = _U.getFloat(bytes, address + 20L);
        double _c5 = _U.getFloat(bytes, address + 24L);
        double _c7 = _U.getFloat(bytes, address + 28L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 storeRMUnsafe(Double4x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m10());
        _U.putDouble(address + 24L, self.m11());
        _U.putDouble(address + 32L, self.m20());
        _U.putDouble(address + 40L, self.m21());
        _U.putDouble(address + 48L, self.m30());
        _U.putDouble(address + 56L, self.m31());
        return self;
    }

    public Double4x2 storeRMHeap(Double4x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, self.m10());
        _U.putDouble(bytes, address + 24L, self.m11());
        _U.putDouble(bytes, address + 32L, self.m20());
        _U.putDouble(bytes, address + 40L, self.m21());
        _U.putDouble(bytes, address + 48L, self.m30());
        _U.putDouble(bytes, address + 56L, self.m31());
        return self;
    }

    public Double4x2 loadRMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadRMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        double _c6 = _U.getDouble(bytes, address + 48L);
        double _c7 = _U.getDouble(bytes, address + 56L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m01());
        _U.putFloat(address + 8L, (float) self.m10());
        _U.putFloat(address + 12L, (float) self.m11());
        _U.putFloat(address + 16L, (float) self.m20());
        _U.putFloat(address + 20L, (float) self.m21());
        _U.putFloat(address + 24L, (float) self.m30());
        _U.putFloat(address + 28L, (float) self.m31());
        return self;
    }

    public Double4x2 storeRMFloatHeap(Double4x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m01());
        _U.putFloat(bytes, address + 8L, (float) self.m10());
        _U.putFloat(bytes, address + 12L, (float) self.m11());
        _U.putFloat(bytes, address + 16L, (float) self.m20());
        _U.putFloat(bytes, address + 20L, (float) self.m21());
        _U.putFloat(bytes, address + 24L, (float) self.m30());
        _U.putFloat(bytes, address + 28L, (float) self.m31());
        return self;
    }

    public Double4x2 loadRMFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadRMFloatHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        double _c6 = _U.getFloat(bytes, address + 24L);
        double _c7 = _U.getFloat(bytes, address + 28L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 storeCMUnsafe(Double4x2 self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(address + 16, self.m20());
        _U.putDouble(address + 24, self.m30());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m21());
        _U.putDouble(_p1 + 24, self.m31());
        return self;
    }

    public Double4x2 storeCMHeap(Double4x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m10());
        _U.putDouble(bytes, address + 16, self.m20());
        _U.putDouble(bytes, address + 24, self.m30());
        _U.putDouble(bytes, _p1, self.m01());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p1 + 16, self.m21());
        _U.putDouble(bytes, _p1 + 24, self.m31());
        return self;
    }

    public Double4x2 loadCMUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c2 = _U.getDouble(address + 8);
        double _c4 = _U.getDouble(address + 16);
        double _c6 = _U.getDouble(address + 24);
        double _c1 = _U.getDouble(_p1);
        double _c3 = _U.getDouble(_p1 + 8);
        double _c5 = _U.getDouble(_p1 + 16);
        double _c7 = _U.getDouble(_p1 + 24);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadCMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c2 = _U.getDouble(bytes, address + 8);
        double _c4 = _U.getDouble(bytes, address + 16);
        double _c6 = _U.getDouble(bytes, address + 24);
        double _c1 = _U.getDouble(bytes, _p1);
        double _c3 = _U.getDouble(bytes, _p1 + 8);
        double _c5 = _U.getDouble(bytes, _p1 + 16);
        double _c7 = _U.getDouble(bytes, _p1 + 24);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m10());
        _U.putFloat(address + 8, (float) self.m20());
        _U.putFloat(address + 12, (float) self.m30());
        _U.putFloat(_p1, (float) self.m01());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p1 + 8, (float) self.m21());
        _U.putFloat(_p1 + 12, (float) self.m31());
        return self;
    }

    public Double4x2 storeCMFloatHeap(Double4x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m10());
        _U.putFloat(bytes, address + 8, (float) self.m20());
        _U.putFloat(bytes, address + 12, (float) self.m30());
        _U.putFloat(bytes, _p1, (float) self.m01());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p1 + 8, (float) self.m21());
        _U.putFloat(bytes, _p1 + 12, (float) self.m31());
        return self;
    }

    public Double4x2 loadCMFloatUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c2 = _U.getFloat(address + 4);
        double _c4 = _U.getFloat(address + 8);
        double _c6 = _U.getFloat(address + 12);
        double _c1 = _U.getFloat(_p1);
        double _c3 = _U.getFloat(_p1 + 4);
        double _c5 = _U.getFloat(_p1 + 8);
        double _c7 = _U.getFloat(_p1 + 12);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadCMFloatHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c2 = _U.getFloat(bytes, address + 4);
        double _c4 = _U.getFloat(bytes, address + 8);
        double _c6 = _U.getFloat(bytes, address + 12);
        double _c1 = _U.getFloat(bytes, _p1);
        double _c3 = _U.getFloat(bytes, _p1 + 4);
        double _c5 = _U.getFloat(bytes, _p1 + 8);
        double _c7 = _U.getFloat(bytes, _p1 + 12);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 storeRMUnsafe(Double4x2 self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p2, self.m20());
        _U.putDouble(_p2 + 8, self.m21());
        _U.putDouble(_p3, self.m30());
        _U.putDouble(_p3 + 8, self.m31());
        return self;
    }

    public Double4x2 storeRMHeap(Double4x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m01());
        _U.putDouble(bytes, _p1, self.m10());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p2, self.m20());
        _U.putDouble(bytes, _p2 + 8, self.m21());
        _U.putDouble(bytes, _p3, self.m30());
        _U.putDouble(bytes, _p3 + 8, self.m31());
        return self;
    }

    public Double4x2 loadRMUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8);
        double _c2 = _U.getDouble(_p1);
        double _c3 = _U.getDouble(_p1 + 8);
        double _c4 = _U.getDouble(_p2);
        double _c5 = _U.getDouble(_p2 + 8);
        double _c6 = _U.getDouble(_p3);
        double _c7 = _U.getDouble(_p3 + 8);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadRMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8);
        double _c2 = _U.getDouble(bytes, _p1);
        double _c3 = _U.getDouble(bytes, _p1 + 8);
        double _c4 = _U.getDouble(bytes, _p2);
        double _c5 = _U.getDouble(bytes, _p2 + 8);
        double _c6 = _U.getDouble(bytes, _p3);
        double _c7 = _U.getDouble(bytes, _p3 + 8);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m01());
        _U.putFloat(_p1, (float) self.m10());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p2, (float) self.m20());
        _U.putFloat(_p2 + 4, (float) self.m21());
        _U.putFloat(_p3, (float) self.m30());
        _U.putFloat(_p3 + 4, (float) self.m31());
        return self;
    }

    public Double4x2 storeRMFloatHeap(Double4x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m01());
        _U.putFloat(bytes, _p1, (float) self.m10());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p2, (float) self.m20());
        _U.putFloat(bytes, _p2 + 4, (float) self.m21());
        _U.putFloat(bytes, _p3, (float) self.m30());
        _U.putFloat(bytes, _p3 + 4, (float) self.m31());
        return self;
    }

    public Double4x2 loadRMFloatUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4);
        double _c2 = _U.getFloat(_p1);
        double _c3 = _U.getFloat(_p1 + 4);
        double _c4 = _U.getFloat(_p2);
        double _c5 = _U.getFloat(_p2 + 4);
        double _c6 = _U.getFloat(_p3);
        double _c7 = _U.getFloat(_p3 + 4);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Double4x2 loadRMFloatHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4);
        double _c2 = _U.getFloat(bytes, _p1);
        double _c3 = _U.getFloat(bytes, _p1 + 4);
        double _c4 = _U.getFloat(bytes, _p2);
        double _c5 = _U.getFloat(bytes, _p2 + 4);
        double _c6 = _U.getFloat(bytes, _p3);
        double _c7 = _U.getFloat(bytes, _p3 + 4);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
