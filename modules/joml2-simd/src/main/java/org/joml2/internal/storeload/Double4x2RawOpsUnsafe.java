// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x2RawOpsUnsafe extends Double4x2RawOps {
    public Double4x2 storeCMUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, self.data[3]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[6]);
        _U.putDouble(address + 56L, self.data[7]);
        return self;
    }

    public Double4x2 storeCMHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, self.data[2]);
        _U.putDouble(bytes, address + 24L, self.data[3]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[6]);
        _U.putDouble(bytes, address + 56L, self.data[7]);
        return self;
    }

    public Double4x2 loadCMUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8L);
        self.data[2] = _U.getDouble(address + 16L);
        self.data[3] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        self.data[6] = _U.getDouble(address + 48L);
        self.data[7] = _U.getDouble(address + 56L);
        return self;
    }

    public Double4x2 loadCMHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8L);
        self.data[2] = _U.getDouble(bytes, address + 16L);
        self.data[3] = _U.getDouble(bytes, address + 24L);
        self.data[4] = _U.getDouble(bytes, address + 32L);
        self.data[5] = _U.getDouble(bytes, address + 40L);
        self.data[6] = _U.getDouble(bytes, address + 48L);
        self.data[7] = _U.getDouble(bytes, address + 56L);
        return self;
    }

    public Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, (float) self.data[2]);
        _U.putFloat(address + 12L, (float) self.data[3]);
        _U.putFloat(address + 16L, (float) self.data[4]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        _U.putFloat(address + 24L, (float) self.data[6]);
        _U.putFloat(address + 28L, (float) self.data[7]);
        return self;
    }

    public Double4x2 storeCMFloatHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, (float) self.data[2]);
        _U.putFloat(bytes, address + 12L, (float) self.data[3]);
        _U.putFloat(bytes, address + 16L, (float) self.data[4]);
        _U.putFloat(bytes, address + 20L, (float) self.data[5]);
        _U.putFloat(bytes, address + 24L, (float) self.data[6]);
        _U.putFloat(bytes, address + 28L, (float) self.data[7]);
        return self;
    }

    public Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4L);
        self.data[2] = _U.getFloat(address + 8L);
        self.data[3] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        self.data[6] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        return self;
    }

    public Double4x2 loadCMFloatHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4L);
        self.data[2] = _U.getFloat(bytes, address + 8L);
        self.data[3] = _U.getFloat(bytes, address + 12L);
        self.data[4] = _U.getFloat(bytes, address + 16L);
        self.data[5] = _U.getFloat(bytes, address + 20L);
        self.data[6] = _U.getFloat(bytes, address + 24L);
        self.data[7] = _U.getFloat(bytes, address + 28L);
        return self;
    }

    public Double4x2 storeRMUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[1]);
        _U.putDouble(address + 24L, self.data[5]);
        _U.putDouble(address + 32L, self.data[2]);
        _U.putDouble(address + 40L, self.data[6]);
        _U.putDouble(address + 48L, self.data[3]);
        _U.putDouble(address + 56L, self.data[7]);
        return self;
    }

    public Double4x2 storeRMHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[1]);
        _U.putDouble(bytes, address + 24L, self.data[5]);
        _U.putDouble(bytes, address + 32L, self.data[2]);
        _U.putDouble(bytes, address + 40L, self.data[6]);
        _U.putDouble(bytes, address + 48L, self.data[3]);
        _U.putDouble(bytes, address + 56L, self.data[7]);
        return self;
    }

    public Double4x2 loadRMUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8L);
        self.data[1] = _U.getDouble(address + 16L);
        self.data[5] = _U.getDouble(address + 24L);
        self.data[2] = _U.getDouble(address + 32L);
        self.data[6] = _U.getDouble(address + 40L);
        self.data[3] = _U.getDouble(address + 48L);
        self.data[7] = _U.getDouble(address + 56L);
        return self;
    }

    public Double4x2 loadRMHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[4] = _U.getDouble(bytes, address + 8L);
        self.data[1] = _U.getDouble(bytes, address + 16L);
        self.data[5] = _U.getDouble(bytes, address + 24L);
        self.data[2] = _U.getDouble(bytes, address + 32L);
        self.data[6] = _U.getDouble(bytes, address + 40L);
        self.data[3] = _U.getDouble(bytes, address + 48L);
        self.data[7] = _U.getDouble(bytes, address + 56L);
        return self;
    }

    public Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[4]);
        _U.putFloat(address + 8L, (float) self.data[1]);
        _U.putFloat(address + 12L, (float) self.data[5]);
        _U.putFloat(address + 16L, (float) self.data[2]);
        _U.putFloat(address + 20L, (float) self.data[6]);
        _U.putFloat(address + 24L, (float) self.data[3]);
        _U.putFloat(address + 28L, (float) self.data[7]);
        return self;
    }

    public Double4x2 storeRMFloatHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[4]);
        _U.putFloat(bytes, address + 8L, (float) self.data[1]);
        _U.putFloat(bytes, address + 12L, (float) self.data[5]);
        _U.putFloat(bytes, address + 16L, (float) self.data[2]);
        _U.putFloat(bytes, address + 20L, (float) self.data[6]);
        _U.putFloat(bytes, address + 24L, (float) self.data[3]);
        _U.putFloat(bytes, address + 28L, (float) self.data[7]);
        return self;
    }

    public Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[1] = _U.getFloat(address + 8L);
        self.data[5] = _U.getFloat(address + 12L);
        self.data[2] = _U.getFloat(address + 16L);
        self.data[6] = _U.getFloat(address + 20L);
        self.data[3] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        return self;
    }

    public Double4x2 loadRMFloatHeap(Double4x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4L);
        self.data[1] = _U.getFloat(bytes, address + 8L);
        self.data[5] = _U.getFloat(bytes, address + 12L);
        self.data[2] = _U.getFloat(bytes, address + 16L);
        self.data[6] = _U.getFloat(bytes, address + 20L);
        self.data[3] = _U.getFloat(bytes, address + 24L);
        self.data[7] = _U.getFloat(bytes, address + 28L);
        return self;
    }

    public Double4x2 storeCMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(address + 24, self.data[3]);
        _U.putDouble(_p1, self.data[4]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[6]);
        _U.putDouble(_p1 + 24, self.data[7]);
        return self;
    }

    public Double4x2 storeCMHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[1]);
        _U.putDouble(bytes, address + 16, self.data[2]);
        _U.putDouble(bytes, address + 24, self.data[3]);
        _U.putDouble(bytes, _p1, self.data[4]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p1 + 16, self.data[6]);
        _U.putDouble(bytes, _p1 + 24, self.data[7]);
        return self;
    }

    public Double4x2 loadCMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8);
        self.data[2] = _U.getDouble(address + 16);
        self.data[3] = _U.getDouble(address + 24);
        self.data[4] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[6] = _U.getDouble(_p1 + 16);
        self.data[7] = _U.getDouble(_p1 + 24);
        return self;
    }

    public Double4x2 loadCMHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8);
        self.data[2] = _U.getDouble(bytes, address + 16);
        self.data[3] = _U.getDouble(bytes, address + 24);
        self.data[4] = _U.getDouble(bytes, _p1);
        self.data[5] = _U.getDouble(bytes, _p1 + 8);
        self.data[6] = _U.getDouble(bytes, _p1 + 16);
        self.data[7] = _U.getDouble(bytes, _p1 + 24);
        return self;
    }

    public Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[1]);
        _U.putFloat(address + 8, (float) self.data[2]);
        _U.putFloat(address + 12, (float) self.data[3]);
        _U.putFloat(_p1, (float) self.data[4]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p1 + 8, (float) self.data[6]);
        _U.putFloat(_p1 + 12, (float) self.data[7]);
        return self;
    }

    public Double4x2 storeCMFloatHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[1]);
        _U.putFloat(bytes, address + 8, (float) self.data[2]);
        _U.putFloat(bytes, address + 12, (float) self.data[3]);
        _U.putFloat(bytes, _p1, (float) self.data[4]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[5]);
        _U.putFloat(bytes, _p1 + 8, (float) self.data[6]);
        _U.putFloat(bytes, _p1 + 12, (float) self.data[7]);
        return self;
    }

    public Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(address + 12);
        self.data[4] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[6] = _U.getFloat(_p1 + 8);
        self.data[7] = _U.getFloat(_p1 + 12);
        return self;
    }

    public Double4x2 loadCMFloatHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4);
        self.data[2] = _U.getFloat(bytes, address + 8);
        self.data[3] = _U.getFloat(bytes, address + 12);
        self.data[4] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[6] = _U.getFloat(bytes, _p1 + 8);
        self.data[7] = _U.getFloat(bytes, _p1 + 12);
        return self;
    }

    public Double4x2 storeRMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[4]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[6]);
        _U.putDouble(_p3, self.data[3]);
        _U.putDouble(_p3 + 8, self.data[7]);
        return self;
    }

    public Double4x2 storeRMHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[4]);
        _U.putDouble(bytes, _p1, self.data[1]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p2, self.data[2]);
        _U.putDouble(bytes, _p2 + 8, self.data[6]);
        _U.putDouble(bytes, _p3, self.data[3]);
        _U.putDouble(bytes, _p3 + 8, self.data[7]);
        return self;
    }

    public Double4x2 loadRMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8);
        self.data[1] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[2] = _U.getDouble(_p2);
        self.data[6] = _U.getDouble(_p2 + 8);
        self.data[3] = _U.getDouble(_p3);
        self.data[7] = _U.getDouble(_p3 + 8);
        return self;
    }

    public Double4x2 loadRMHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[4] = _U.getDouble(bytes, address + 8);
        self.data[1] = _U.getDouble(bytes, _p1);
        self.data[5] = _U.getDouble(bytes, _p1 + 8);
        self.data[2] = _U.getDouble(bytes, _p2);
        self.data[6] = _U.getDouble(bytes, _p2 + 8);
        self.data[3] = _U.getDouble(bytes, _p3);
        self.data[7] = _U.getDouble(bytes, _p3 + 8);
        return self;
    }

    public Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[4]);
        _U.putFloat(_p1, (float) self.data[1]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p2, (float) self.data[2]);
        _U.putFloat(_p2 + 4, (float) self.data[6]);
        _U.putFloat(_p3, (float) self.data[3]);
        _U.putFloat(_p3 + 4, (float) self.data[7]);
        return self;
    }

    public Double4x2 storeRMFloatHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[4]);
        _U.putFloat(bytes, _p1, (float) self.data[1]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[5]);
        _U.putFloat(bytes, _p2, (float) self.data[2]);
        _U.putFloat(bytes, _p2 + 4, (float) self.data[6]);
        _U.putFloat(bytes, _p3, (float) self.data[3]);
        _U.putFloat(bytes, _p3 + 4, (float) self.data[7]);
        return self;
    }

    public Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4);
        self.data[1] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[2] = _U.getFloat(_p2);
        self.data[6] = _U.getFloat(_p2 + 4);
        self.data[3] = _U.getFloat(_p3);
        self.data[7] = _U.getFloat(_p3 + 4);
        return self;
    }

    public Double4x2 loadRMFloatHeap(Double4x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4);
        self.data[1] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[2] = _U.getFloat(bytes, _p2);
        self.data[6] = _U.getFloat(bytes, _p2 + 4);
        self.data[3] = _U.getFloat(bytes, _p3);
        self.data[7] = _U.getFloat(bytes, _p3 + 4);
        return self;
    }
}
