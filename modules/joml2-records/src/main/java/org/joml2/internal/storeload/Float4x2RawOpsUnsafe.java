// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x2RawOpsUnsafe extends Float4x2RawOps {
    public Float4x2 storeCMUnsafe(Float4x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, self.m20());
        _U.putFloat(address + 12L, self.m30());
        _U.putFloat(address + 16L, self.m01());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, self.m21());
        _U.putFloat(address + 28L, self.m31());
        return self;
    }

    public Float4x2 storeCMHeap(Float4x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m10());
        _U.putFloat(bytes, address + 8L, self.m20());
        _U.putFloat(bytes, address + 12L, self.m30());
        _U.putFloat(bytes, address + 16L, self.m01());
        _U.putFloat(bytes, address + 20L, self.m11());
        _U.putFloat(bytes, address + 24L, self.m21());
        _U.putFloat(bytes, address + 28L, self.m31());
        return self;
    }

    public Float4x2 loadCMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c2 = _U.getFloat(address + 4L);
        float _c4 = _U.getFloat(address + 8L);
        float _c6 = _U.getFloat(address + 12L);
        float _c1 = _U.getFloat(address + 16L);
        float _c3 = _U.getFloat(address + 20L);
        float _c5 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadCMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c2 = _U.getFloat(bytes, address + 4L);
        float _c4 = _U.getFloat(bytes, address + 8L);
        float _c6 = _U.getFloat(bytes, address + 12L);
        float _c1 = _U.getFloat(bytes, address + 16L);
        float _c3 = _U.getFloat(bytes, address + 20L);
        float _c5 = _U.getFloat(bytes, address + 24L);
        float _c7 = _U.getFloat(bytes, address + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 storeCMDoubleUnsafe(Float4x2 self, long address) {
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

    public Float4x2 storeCMDoubleHeap(Float4x2 self, byte[] bytes, long address) {
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

    public Float4x2 loadCMDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c2 = (float) _U.getDouble(address + 8L);
        float _c4 = (float) _U.getDouble(address + 16L);
        float _c6 = (float) _U.getDouble(address + 24L);
        float _c1 = (float) _U.getDouble(address + 32L);
        float _c3 = (float) _U.getDouble(address + 40L);
        float _c5 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadCMDoubleHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c2 = (float) _U.getDouble(bytes, address + 8L);
        float _c4 = (float) _U.getDouble(bytes, address + 16L);
        float _c6 = (float) _U.getDouble(bytes, address + 24L);
        float _c1 = (float) _U.getDouble(bytes, address + 32L);
        float _c3 = (float) _U.getDouble(bytes, address + 40L);
        float _c5 = (float) _U.getDouble(bytes, address + 48L);
        float _c7 = (float) _U.getDouble(bytes, address + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 storeRMUnsafe(Float4x2 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m10());
        _U.putFloat(address + 12L, self.m11());
        _U.putFloat(address + 16L, self.m20());
        _U.putFloat(address + 20L, self.m21());
        _U.putFloat(address + 24L, self.m30());
        _U.putFloat(address + 28L, self.m31());
        return self;
    }

    public Float4x2 storeRMHeap(Float4x2 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m01());
        _U.putFloat(bytes, address + 8L, self.m10());
        _U.putFloat(bytes, address + 12L, self.m11());
        _U.putFloat(bytes, address + 16L, self.m20());
        _U.putFloat(bytes, address + 20L, self.m21());
        _U.putFloat(bytes, address + 24L, self.m30());
        _U.putFloat(bytes, address + 28L, self.m31());
        return self;
    }

    public Float4x2 loadRMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c6 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadRMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c6 = _U.getFloat(bytes, address + 24L);
        float _c7 = _U.getFloat(bytes, address + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 storeRMDoubleUnsafe(Float4x2 self, long address) {
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

    public Float4x2 storeRMDoubleHeap(Float4x2 self, byte[] bytes, long address) {
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

    public Float4x2 loadRMDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c6 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadRMDoubleHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        float _c6 = (float) _U.getDouble(bytes, address + 48L);
        float _c7 = (float) _U.getDouble(bytes, address + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 storeCMUnsafe(Float4x2 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m10());
        _U.putFloat(address + 8, self.m20());
        _U.putFloat(address + 12, self.m30());
        _U.putFloat(_p1, self.m01());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m21());
        _U.putFloat(_p1 + 12, self.m31());
        return self;
    }

    public Float4x2 storeCMHeap(Float4x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4, self.m10());
        _U.putFloat(bytes, address + 8, self.m20());
        _U.putFloat(bytes, address + 12, self.m30());
        _U.putFloat(bytes, _p1, self.m01());
        _U.putFloat(bytes, _p1 + 4, self.m11());
        _U.putFloat(bytes, _p1 + 8, self.m21());
        _U.putFloat(bytes, _p1 + 12, self.m31());
        return self;
    }

    public Float4x2 loadCMUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c2 = _U.getFloat(address + 4);
        float _c4 = _U.getFloat(address + 8);
        float _c6 = _U.getFloat(address + 12);
        float _c1 = _U.getFloat(_p1);
        float _c3 = _U.getFloat(_p1 + 4);
        float _c5 = _U.getFloat(_p1 + 8);
        float _c7 = _U.getFloat(_p1 + 12);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadCMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c2 = _U.getFloat(bytes, address + 4);
        float _c4 = _U.getFloat(bytes, address + 8);
        float _c6 = _U.getFloat(bytes, address + 12);
        float _c1 = _U.getFloat(bytes, _p1);
        float _c3 = _U.getFloat(bytes, _p1 + 4);
        float _c5 = _U.getFloat(bytes, _p1 + 8);
        float _c7 = _U.getFloat(bytes, _p1 + 12);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 storeCMDoubleUnsafe(Float4x2 self, long address, int stride) {
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

    public Float4x2 storeCMDoubleHeap(Float4x2 self, byte[] bytes, long address, int stride) {
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

    public Float4x2 loadCMDoubleUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c2 = (float) _U.getDouble(address + 8);
        float _c4 = (float) _U.getDouble(address + 16);
        float _c6 = (float) _U.getDouble(address + 24);
        float _c1 = (float) _U.getDouble(_p1);
        float _c3 = (float) _U.getDouble(_p1 + 8);
        float _c5 = (float) _U.getDouble(_p1 + 16);
        float _c7 = (float) _U.getDouble(_p1 + 24);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadCMDoubleHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c2 = (float) _U.getDouble(bytes, address + 8);
        float _c4 = (float) _U.getDouble(bytes, address + 16);
        float _c6 = (float) _U.getDouble(bytes, address + 24);
        float _c1 = (float) _U.getDouble(bytes, _p1);
        float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
        float _c7 = (float) _U.getDouble(bytes, _p1 + 24);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 storeRMUnsafe(Float4x2 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m01());
        _U.putFloat(_p1, self.m10());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p2, self.m20());
        _U.putFloat(_p2 + 4, self.m21());
        _U.putFloat(_p3, self.m30());
        _U.putFloat(_p3 + 4, self.m31());
        return self;
    }

    public Float4x2 storeRMHeap(Float4x2 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4, self.m01());
        _U.putFloat(bytes, _p1, self.m10());
        _U.putFloat(bytes, _p1 + 4, self.m11());
        _U.putFloat(bytes, _p2, self.m20());
        _U.putFloat(bytes, _p2 + 4, self.m21());
        _U.putFloat(bytes, _p3, self.m30());
        _U.putFloat(bytes, _p3 + 4, self.m31());
        return self;
    }

    public Float4x2 loadRMUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4);
        float _c2 = _U.getFloat(_p1);
        float _c3 = _U.getFloat(_p1 + 4);
        float _c4 = _U.getFloat(_p2);
        float _c5 = _U.getFloat(_p2 + 4);
        float _c6 = _U.getFloat(_p3);
        float _c7 = _U.getFloat(_p3 + 4);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadRMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4);
        float _c2 = _U.getFloat(bytes, _p1);
        float _c3 = _U.getFloat(bytes, _p1 + 4);
        float _c4 = _U.getFloat(bytes, _p2);
        float _c5 = _U.getFloat(bytes, _p2 + 4);
        float _c6 = _U.getFloat(bytes, _p3);
        float _c7 = _U.getFloat(bytes, _p3 + 4);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 storeRMDoubleUnsafe(Float4x2 self, long address, int stride) {
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

    public Float4x2 storeRMDoubleHeap(Float4x2 self, byte[] bytes, long address, int stride) {
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

    public Float4x2 loadRMDoubleUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8);
        float _c2 = (float) _U.getDouble(_p1);
        float _c3 = (float) _U.getDouble(_p1 + 8);
        float _c4 = (float) _U.getDouble(_p2);
        float _c5 = (float) _U.getDouble(_p2 + 8);
        float _c6 = (float) _U.getDouble(_p3);
        float _c7 = (float) _U.getDouble(_p3 + 8);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public Float4x2 loadRMDoubleHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8);
        float _c2 = (float) _U.getDouble(bytes, _p1);
        float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c4 = (float) _U.getDouble(bytes, _p2);
        float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
        float _c6 = (float) _U.getDouble(bytes, _p3);
        float _c7 = (float) _U.getDouble(bytes, _p3 + 8);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
