// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x2RawOpsUnsafe extends Float3x2RawOps {
    public Float3x2 storeCMUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, self.m01);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, self.m21);
        return self;
    }

    public Float3x2 storeCMHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, self.m20);
        _U.putFloat(bytes, address + 12L, self.m01);
        _U.putFloat(bytes, address + 16L, self.m11);
        _U.putFloat(bytes, address + 20L, self.m21);
        return self;
    }

    public Float3x2 loadCMUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m01 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        return self;
    }

    public Float3x2 loadCMHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m20 = _U.getFloat(bytes, address + 8L);
        self.m01 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m21 = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m21);
        return self;
    }

    public Float3x2 storeCMDoubleHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m20);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m21);
        return self;
    }

    public Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8L);
        self.m20 = (float) _U.getDouble(address + 16L);
        self.m01 = (float) _U.getDouble(address + 24L);
        self.m11 = (float) _U.getDouble(address + 32L);
        self.m21 = (float) _U.getDouble(address + 40L);
        return self;
    }

    public Float3x2 loadCMDoubleHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8L);
        self.m20 = (float) _U.getDouble(bytes, address + 16L);
        self.m01 = (float) _U.getDouble(bytes, address + 24L);
        self.m11 = (float) _U.getDouble(bytes, address + 32L);
        self.m21 = (float) _U.getDouble(bytes, address + 40L);
        return self;
    }

    public Float3x2 storeRMUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m10);
        _U.putFloat(address + 12L, self.m11);
        _U.putFloat(address + 16L, self.m20);
        _U.putFloat(address + 20L, self.m21);
        return self;
    }

    public Float3x2 storeRMHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m01);
        _U.putFloat(bytes, address + 8L, self.m10);
        _U.putFloat(bytes, address + 12L, self.m11);
        _U.putFloat(bytes, address + 16L, self.m20);
        _U.putFloat(bytes, address + 20L, self.m21);
        return self;
    }

    public Float3x2 loadRMUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m10 = _U.getFloat(address + 8L);
        self.m11 = _U.getFloat(address + 12L);
        self.m20 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        return self;
    }

    public Float3x2 loadRMHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m10 = _U.getFloat(bytes, address + 8L);
        self.m11 = _U.getFloat(bytes, address + 12L);
        self.m20 = _U.getFloat(bytes, address + 16L);
        self.m21 = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m10);
        _U.putDouble(address + 24L, self.m11);
        _U.putDouble(address + 32L, self.m20);
        _U.putDouble(address + 40L, self.m21);
        return self;
    }

    public Float3x2 storeRMDoubleHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m10);
        _U.putDouble(bytes, address + 24L, self.m11);
        _U.putDouble(bytes, address + 32L, self.m20);
        _U.putDouble(bytes, address + 40L, self.m21);
        return self;
    }

    public Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8L);
        self.m10 = (float) _U.getDouble(address + 16L);
        self.m11 = (float) _U.getDouble(address + 24L);
        self.m20 = (float) _U.getDouble(address + 32L);
        self.m21 = (float) _U.getDouble(address + 40L);
        return self;
    }

    public Float3x2 loadRMDoubleHeap(Float3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8L);
        self.m10 = (float) _U.getDouble(bytes, address + 16L);
        self.m11 = (float) _U.getDouble(bytes, address + 24L);
        self.m20 = (float) _U.getDouble(bytes, address + 32L);
        self.m21 = (float) _U.getDouble(bytes, address + 40L);
        return self;
    }

    public Float3x2 storeCMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m10);
        _U.putFloat(address + 8, self.m20);
        _U.putFloat(_p1, self.m01);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m21);
        return self;
    }

    public Float3x2 storeCMHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m10);
        _U.putFloat(bytes, address + 8, self.m20);
        _U.putFloat(bytes, _p1, self.m01);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p1 + 8, self.m21);
        return self;
    }

    public Float3x2 loadCMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        return self;
    }

    public Float3x2 loadCMHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m20 = _U.getFloat(bytes, address + 8);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m21 = _U.getFloat(bytes, _p1 + 8);
        return self;
    }

    public Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        return self;
    }

    public Float3x2 storeCMDoubleHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, address + 16, self.m20);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m21);
        return self;
    }

    public Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8);
        self.m20 = (float) _U.getDouble(address + 16);
        self.m01 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m21 = (float) _U.getDouble(_p1 + 16);
        return self;
    }

    public Float3x2 loadCMDoubleHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8);
        self.m20 = (float) _U.getDouble(bytes, address + 16);
        self.m01 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m21 = (float) _U.getDouble(bytes, _p1 + 16);
        return self;
    }

    public Float3x2 storeRMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m01);
        _U.putFloat(_p1, self.m10);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p2, self.m20);
        _U.putFloat(_p2 + 4, self.m21);
        return self;
    }

    public Float3x2 storeRMHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m01);
        _U.putFloat(bytes, _p1, self.m10);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p2, self.m20);
        _U.putFloat(bytes, _p2 + 4, self.m21);
        return self;
    }

    public Float3x2 loadRMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        return self;
    }

    public Float3x2 loadRMHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m20 = _U.getFloat(bytes, _p2);
        self.m21 = _U.getFloat(bytes, _p2 + 4);
        return self;
    }

    public Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        return self;
    }

    public Float3x2 storeRMDoubleHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p2, self.m20);
        _U.putDouble(bytes, _p2 + 8, self.m21);
        return self;
    }

    public Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8);
        self.m10 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m20 = (float) _U.getDouble(_p2);
        self.m21 = (float) _U.getDouble(_p2 + 8);
        return self;
    }

    public Float3x2 loadRMDoubleHeap(Float3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8);
        self.m10 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m20 = (float) _U.getDouble(bytes, _p2);
        self.m21 = (float) _U.getDouble(bytes, _p2 + 8);
        return self;
    }
}
