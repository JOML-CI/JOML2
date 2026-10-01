// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2x4RawOpsUnsafe extends Float2x4RawOps {
    public Float2x4 storeCMUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m01);
        _U.putFloat(address + 12L, self.m11);
        _U.putFloat(address + 16L, self.m02);
        _U.putFloat(address + 20L, self.m12);
        _U.putFloat(address + 24L, self.m03);
        _U.putFloat(address + 28L, self.m13);
        return self;
    }

    public Float2x4 storeCMHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, self.m01);
        _U.putFloat(bytes, address + 12L, self.m11);
        _U.putFloat(bytes, address + 16L, self.m02);
        _U.putFloat(bytes, address + 20L, self.m12);
        _U.putFloat(bytes, address + 24L, self.m03);
        _U.putFloat(bytes, address + 28L, self.m13);
        return self;
    }

    public Float2x4 loadCMUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m01 = _U.getFloat(address + 8L);
        self.m11 = _U.getFloat(address + 12L);
        self.m02 = _U.getFloat(address + 16L);
        self.m12 = _U.getFloat(address + 20L);
        self.m03 = _U.getFloat(address + 24L);
        self.m13 = _U.getFloat(address + 28L);
        return self;
    }

    public Float2x4 loadCMHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m01 = _U.getFloat(bytes, address + 8L);
        self.m11 = _U.getFloat(bytes, address + 12L);
        self.m02 = _U.getFloat(bytes, address + 16L);
        self.m12 = _U.getFloat(bytes, address + 20L);
        self.m03 = _U.getFloat(bytes, address + 24L);
        self.m13 = _U.getFloat(bytes, address + 28L);
        return self;
    }

    public Float2x4 storeCMDoubleUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m01);
        _U.putDouble(address + 24L, self.m11);
        _U.putDouble(address + 32L, self.m02);
        _U.putDouble(address + 40L, self.m12);
        _U.putDouble(address + 48L, self.m03);
        _U.putDouble(address + 56L, self.m13);
        return self;
    }

    public Float2x4 storeCMDoubleHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m01);
        _U.putDouble(bytes, address + 24L, self.m11);
        _U.putDouble(bytes, address + 32L, self.m02);
        _U.putDouble(bytes, address + 40L, self.m12);
        _U.putDouble(bytes, address + 48L, self.m03);
        _U.putDouble(bytes, address + 56L, self.m13);
        return self;
    }

    public Float2x4 loadCMDoubleUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8L);
        self.m01 = (float) _U.getDouble(address + 16L);
        self.m11 = (float) _U.getDouble(address + 24L);
        self.m02 = (float) _U.getDouble(address + 32L);
        self.m12 = (float) _U.getDouble(address + 40L);
        self.m03 = (float) _U.getDouble(address + 48L);
        self.m13 = (float) _U.getDouble(address + 56L);
        return self;
    }

    public Float2x4 loadCMDoubleHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8L);
        self.m01 = (float) _U.getDouble(bytes, address + 16L);
        self.m11 = (float) _U.getDouble(bytes, address + 24L);
        self.m02 = (float) _U.getDouble(bytes, address + 32L);
        self.m12 = (float) _U.getDouble(bytes, address + 40L);
        self.m03 = (float) _U.getDouble(bytes, address + 48L);
        self.m13 = (float) _U.getDouble(bytes, address + 56L);
        return self;
    }

    public Float2x4 storeRMUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, self.m03);
        _U.putFloat(address + 16L, self.m10);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m12);
        _U.putFloat(address + 28L, self.m13);
        return self;
    }

    public Float2x4 storeRMHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m01);
        _U.putFloat(bytes, address + 8L, self.m02);
        _U.putFloat(bytes, address + 12L, self.m03);
        _U.putFloat(bytes, address + 16L, self.m10);
        _U.putFloat(bytes, address + 20L, self.m11);
        _U.putFloat(bytes, address + 24L, self.m12);
        _U.putFloat(bytes, address + 28L, self.m13);
        return self;
    }

    public Float2x4 loadRMUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m03 = _U.getFloat(address + 12L);
        self.m10 = _U.getFloat(address + 16L);
        self.m11 = _U.getFloat(address + 20L);
        self.m12 = _U.getFloat(address + 24L);
        self.m13 = _U.getFloat(address + 28L);
        return self;
    }

    public Float2x4 loadRMHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m02 = _U.getFloat(bytes, address + 8L);
        self.m03 = _U.getFloat(bytes, address + 12L);
        self.m10 = _U.getFloat(bytes, address + 16L);
        self.m11 = _U.getFloat(bytes, address + 20L);
        self.m12 = _U.getFloat(bytes, address + 24L);
        self.m13 = _U.getFloat(bytes, address + 28L);
        return self;
    }

    public Float2x4 storeRMDoubleUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m03);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m12);
        _U.putDouble(address + 56L, self.m13);
        return self;
    }

    public Float2x4 storeRMDoubleHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, self.m03);
        _U.putDouble(bytes, address + 32L, self.m10);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, self.m12);
        _U.putDouble(bytes, address + 56L, self.m13);
        return self;
    }

    public Float2x4 loadRMDoubleUnsafe(Float2x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8L);
        self.m02 = (float) _U.getDouble(address + 16L);
        self.m03 = (float) _U.getDouble(address + 24L);
        self.m10 = (float) _U.getDouble(address + 32L);
        self.m11 = (float) _U.getDouble(address + 40L);
        self.m12 = (float) _U.getDouble(address + 48L);
        self.m13 = (float) _U.getDouble(address + 56L);
        return self;
    }

    public Float2x4 loadRMDoubleHeap(Float2x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8L);
        self.m02 = (float) _U.getDouble(bytes, address + 16L);
        self.m03 = (float) _U.getDouble(bytes, address + 24L);
        self.m10 = (float) _U.getDouble(bytes, address + 32L);
        self.m11 = (float) _U.getDouble(bytes, address + 40L);
        self.m12 = (float) _U.getDouble(bytes, address + 48L);
        self.m13 = (float) _U.getDouble(bytes, address + 56L);
        return self;
    }

    public Float2x4 storeCMUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m10);
        _U.putFloat(_p1, self.m01);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p2, self.m02);
        _U.putFloat(_p2 + 4, self.m12);
        _U.putFloat(_p3, self.m03);
        _U.putFloat(_p3 + 4, self.m13);
        return self;
    }

    public Float2x4 storeCMHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m10);
        _U.putFloat(bytes, _p1, self.m01);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p2, self.m02);
        _U.putFloat(bytes, _p2 + 4, self.m12);
        _U.putFloat(bytes, _p3, self.m03);
        _U.putFloat(bytes, _p3 + 4, self.m13);
        return self;
    }

    public Float2x4 loadCMUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m02 = _U.getFloat(_p2);
        self.m12 = _U.getFloat(_p2 + 4);
        self.m03 = _U.getFloat(_p3);
        self.m13 = _U.getFloat(_p3 + 4);
        return self;
    }

    public Float2x4 loadCMHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m02 = _U.getFloat(bytes, _p2);
        self.m12 = _U.getFloat(bytes, _p2 + 4);
        self.m03 = _U.getFloat(bytes, _p3);
        self.m13 = _U.getFloat(bytes, _p3 + 4);
        return self;
    }

    public Float2x4 storeCMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p2, self.m02);
        _U.putDouble(_p2 + 8, self.m12);
        _U.putDouble(_p3, self.m03);
        _U.putDouble(_p3 + 8, self.m13);
        return self;
    }

    public Float2x4 storeCMDoubleHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p2, self.m02);
        _U.putDouble(bytes, _p2 + 8, self.m12);
        _U.putDouble(bytes, _p3, self.m03);
        _U.putDouble(bytes, _p3 + 8, self.m13);
        return self;
    }

    public Float2x4 loadCMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8);
        self.m01 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m02 = (float) _U.getDouble(_p2);
        self.m12 = (float) _U.getDouble(_p2 + 8);
        self.m03 = (float) _U.getDouble(_p3);
        self.m13 = (float) _U.getDouble(_p3 + 8);
        return self;
    }

    public Float2x4 loadCMDoubleHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8);
        self.m01 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m02 = (float) _U.getDouble(bytes, _p2);
        self.m12 = (float) _U.getDouble(bytes, _p2 + 8);
        self.m03 = (float) _U.getDouble(bytes, _p3);
        self.m13 = (float) _U.getDouble(bytes, _p3 + 8);
        return self;
    }

    public Float2x4 storeRMUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m01);
        _U.putFloat(address + 8, self.m02);
        _U.putFloat(address + 12, self.m03);
        _U.putFloat(_p1, self.m10);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m12);
        _U.putFloat(_p1 + 12, self.m13);
        return self;
    }

    public Float2x4 storeRMHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m01);
        _U.putFloat(bytes, address + 8, self.m02);
        _U.putFloat(bytes, address + 12, self.m03);
        _U.putFloat(bytes, _p1, self.m10);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p1 + 8, self.m12);
        _U.putFloat(bytes, _p1 + 12, self.m13);
        return self;
    }

    public Float2x4 loadRMUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m02 = _U.getFloat(address + 8);
        self.m03 = _U.getFloat(address + 12);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m12 = _U.getFloat(_p1 + 8);
        self.m13 = _U.getFloat(_p1 + 12);
        return self;
    }

    public Float2x4 loadRMHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m02 = _U.getFloat(bytes, address + 8);
        self.m03 = _U.getFloat(bytes, address + 12);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m12 = _U.getFloat(bytes, _p1 + 8);
        self.m13 = _U.getFloat(bytes, _p1 + 12);
        return self;
    }

    public Float2x4 storeRMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(address + 16, self.m02);
        _U.putDouble(address + 24, self.m03);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m12);
        _U.putDouble(_p1 + 24, self.m13);
        return self;
    }

    public Float2x4 storeRMDoubleHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, address + 16, self.m02);
        _U.putDouble(bytes, address + 24, self.m03);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m12);
        _U.putDouble(bytes, _p1 + 24, self.m13);
        return self;
    }

    public Float2x4 loadRMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8);
        self.m02 = (float) _U.getDouble(address + 16);
        self.m03 = (float) _U.getDouble(address + 24);
        self.m10 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m12 = (float) _U.getDouble(_p1 + 16);
        self.m13 = (float) _U.getDouble(_p1 + 24);
        return self;
    }

    public Float2x4 loadRMDoubleHeap(Float2x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8);
        self.m02 = (float) _U.getDouble(bytes, address + 16);
        self.m03 = (float) _U.getDouble(bytes, address + 24);
        self.m10 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m12 = (float) _U.getDouble(bytes, _p1 + 16);
        self.m13 = (float) _U.getDouble(bytes, _p1 + 24);
        return self;
    }
}
