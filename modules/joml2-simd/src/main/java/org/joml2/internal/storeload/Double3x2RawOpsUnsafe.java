// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x2RawOpsUnsafe extends Double3x2RawOps {
    public Double3x2 storeCMUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, self.data[3]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[5]);
        return self;
    }

    public Double3x2 storeCMHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, self.data[2]);
        _U.putDouble(bytes, address + 24L, self.data[3]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        return self;
    }

    public Double3x2 loadCMUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8L);
        self.data[2] = _U.getDouble(address + 16L);
        self.data[3] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        return self;
    }

    public Double3x2 loadCMHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8L);
        self.data[2] = _U.getDouble(bytes, address + 16L);
        self.data[3] = _U.getDouble(bytes, address + 24L);
        self.data[4] = _U.getDouble(bytes, address + 32L);
        self.data[5] = _U.getDouble(bytes, address + 40L);
        return self;
    }

    public Double3x2 storeCMFloatUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, (float) self.data[2]);
        _U.putFloat(address + 12L, (float) self.data[3]);
        _U.putFloat(address + 16L, (float) self.data[4]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        return self;
    }

    public Double3x2 storeCMFloatHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, (float) self.data[2]);
        _U.putFloat(bytes, address + 12L, (float) self.data[3]);
        _U.putFloat(bytes, address + 16L, (float) self.data[4]);
        _U.putFloat(bytes, address + 20L, (float) self.data[5]);
        return self;
    }

    public Double3x2 loadCMFloatUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4L);
        self.data[2] = _U.getFloat(address + 8L);
        self.data[3] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        return self;
    }

    public Double3x2 loadCMFloatHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4L);
        self.data[2] = _U.getFloat(bytes, address + 8L);
        self.data[3] = _U.getFloat(bytes, address + 12L);
        self.data[4] = _U.getFloat(bytes, address + 16L);
        self.data[5] = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public Double3x2 storeRMUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[3]);
        _U.putDouble(address + 16L, self.data[1]);
        _U.putDouble(address + 24L, self.data[4]);
        _U.putDouble(address + 32L, self.data[2]);
        _U.putDouble(address + 40L, self.data[5]);
        return self;
    }

    public Double3x2 storeRMHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[3]);
        _U.putDouble(bytes, address + 16L, self.data[1]);
        _U.putDouble(bytes, address + 24L, self.data[4]);
        _U.putDouble(bytes, address + 32L, self.data[2]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        return self;
    }

    public Double3x2 loadRMUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[3] = _U.getDouble(address + 8L);
        self.data[1] = _U.getDouble(address + 16L);
        self.data[4] = _U.getDouble(address + 24L);
        self.data[2] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        return self;
    }

    public Double3x2 loadRMHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[3] = _U.getDouble(bytes, address + 8L);
        self.data[1] = _U.getDouble(bytes, address + 16L);
        self.data[4] = _U.getDouble(bytes, address + 24L);
        self.data[2] = _U.getDouble(bytes, address + 32L);
        self.data[5] = _U.getDouble(bytes, address + 40L);
        return self;
    }

    public Double3x2 storeRMFloatUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[3]);
        _U.putFloat(address + 8L, (float) self.data[1]);
        _U.putFloat(address + 12L, (float) self.data[4]);
        _U.putFloat(address + 16L, (float) self.data[2]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        return self;
    }

    public Double3x2 storeRMFloatHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[3]);
        _U.putFloat(bytes, address + 8L, (float) self.data[1]);
        _U.putFloat(bytes, address + 12L, (float) self.data[4]);
        _U.putFloat(bytes, address + 16L, (float) self.data[2]);
        _U.putFloat(bytes, address + 20L, (float) self.data[5]);
        return self;
    }

    public Double3x2 loadRMFloatUnsafe(Double3x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[3] = _U.getFloat(address + 4L);
        self.data[1] = _U.getFloat(address + 8L);
        self.data[4] = _U.getFloat(address + 12L);
        self.data[2] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        return self;
    }

    public Double3x2 loadRMFloatHeap(Double3x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[3] = _U.getFloat(bytes, address + 4L);
        self.data[1] = _U.getFloat(bytes, address + 8L);
        self.data[4] = _U.getFloat(bytes, address + 12L);
        self.data[2] = _U.getFloat(bytes, address + 16L);
        self.data[5] = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public Double3x2 storeCMUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(_p1, self.data[3]);
        _U.putDouble(_p1 + 8, self.data[4]);
        _U.putDouble(_p1 + 16, self.data[5]);
        return self;
    }

    public Double3x2 storeCMHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[1]);
        _U.putDouble(bytes, address + 16, self.data[2]);
        _U.putDouble(bytes, _p1, self.data[3]);
        _U.putDouble(bytes, _p1 + 8, self.data[4]);
        _U.putDouble(bytes, _p1 + 16, self.data[5]);
        return self;
    }

    public Double3x2 loadCMUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8);
        self.data[2] = _U.getDouble(address + 16);
        self.data[3] = _U.getDouble(_p1);
        self.data[4] = _U.getDouble(_p1 + 8);
        self.data[5] = _U.getDouble(_p1 + 16);
        return self;
    }

    public Double3x2 loadCMHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8);
        self.data[2] = _U.getDouble(bytes, address + 16);
        self.data[3] = _U.getDouble(bytes, _p1);
        self.data[4] = _U.getDouble(bytes, _p1 + 8);
        self.data[5] = _U.getDouble(bytes, _p1 + 16);
        return self;
    }

    public Double3x2 storeCMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[1]);
        _U.putFloat(address + 8, (float) self.data[2]);
        _U.putFloat(_p1, (float) self.data[3]);
        _U.putFloat(_p1 + 4, (float) self.data[4]);
        _U.putFloat(_p1 + 8, (float) self.data[5]);
        return self;
    }

    public Double3x2 storeCMFloatHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[1]);
        _U.putFloat(bytes, address + 8, (float) self.data[2]);
        _U.putFloat(bytes, _p1, (float) self.data[3]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[4]);
        _U.putFloat(bytes, _p1 + 8, (float) self.data[5]);
        return self;
    }

    public Double3x2 loadCMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(_p1);
        self.data[4] = _U.getFloat(_p1 + 4);
        self.data[5] = _U.getFloat(_p1 + 8);
        return self;
    }

    public Double3x2 loadCMFloatHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4);
        self.data[2] = _U.getFloat(bytes, address + 8);
        self.data[3] = _U.getFloat(bytes, _p1);
        self.data[4] = _U.getFloat(bytes, _p1 + 4);
        self.data[5] = _U.getFloat(bytes, _p1 + 8);
        return self;
    }

    public Double3x2 storeRMUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[3]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[4]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[5]);
        return self;
    }

    public Double3x2 storeRMHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[3]);
        _U.putDouble(bytes, _p1, self.data[1]);
        _U.putDouble(bytes, _p1 + 8, self.data[4]);
        _U.putDouble(bytes, _p2, self.data[2]);
        _U.putDouble(bytes, _p2 + 8, self.data[5]);
        return self;
    }

    public Double3x2 loadRMUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[3] = _U.getDouble(address + 8);
        self.data[1] = _U.getDouble(_p1);
        self.data[4] = _U.getDouble(_p1 + 8);
        self.data[2] = _U.getDouble(_p2);
        self.data[5] = _U.getDouble(_p2 + 8);
        return self;
    }

    public Double3x2 loadRMHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[3] = _U.getDouble(bytes, address + 8);
        self.data[1] = _U.getDouble(bytes, _p1);
        self.data[4] = _U.getDouble(bytes, _p1 + 8);
        self.data[2] = _U.getDouble(bytes, _p2);
        self.data[5] = _U.getDouble(bytes, _p2 + 8);
        return self;
    }

    public Double3x2 storeRMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[3]);
        _U.putFloat(_p1, (float) self.data[1]);
        _U.putFloat(_p1 + 4, (float) self.data[4]);
        _U.putFloat(_p2, (float) self.data[2]);
        _U.putFloat(_p2 + 4, (float) self.data[5]);
        return self;
    }

    public Double3x2 storeRMFloatHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[3]);
        _U.putFloat(bytes, _p1, (float) self.data[1]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[4]);
        _U.putFloat(bytes, _p2, (float) self.data[2]);
        _U.putFloat(bytes, _p2 + 4, (float) self.data[5]);
        return self;
    }

    public Double3x2 loadRMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[3] = _U.getFloat(address + 4);
        self.data[1] = _U.getFloat(_p1);
        self.data[4] = _U.getFloat(_p1 + 4);
        self.data[2] = _U.getFloat(_p2);
        self.data[5] = _U.getFloat(_p2 + 4);
        return self;
    }

    public Double3x2 loadRMFloatHeap(Double3x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[3] = _U.getFloat(bytes, address + 4);
        self.data[1] = _U.getFloat(bytes, _p1);
        self.data[4] = _U.getFloat(bytes, _p1 + 4);
        self.data[2] = _U.getFloat(bytes, _p2);
        self.data[5] = _U.getFloat(bytes, _p2 + 4);
        return self;
    }
}
