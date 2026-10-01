// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2x2RawOpsUnsafe extends Double2x2RawOps {
    public Double2x2 storeCMUnsafe(Double2x2Impl self, long address) {
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        return self;
    }

    public Double2x2 storeCMHeap(Double2x2Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[1]);
        U.putDouble(bytes, address + 16L, self.data[2]);
        U.putDouble(bytes, address + 24L, self.data[3]);
        return self;
    }

    public Double2x2 loadCMUnsafe(Double2x2Impl self, long address) {
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadCMHeap(Double2x2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getDouble(bytes, address);
        self.data[1] = U.getDouble(bytes, address + 8L);
        self.data[2] = U.getDouble(bytes, address + 16L);
        self.data[3] = U.getDouble(bytes, address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeCMFloatUnsafe(Double2x2Impl self, long address) {
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        return self;
    }

    public Double2x2 storeCMFloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.data[0]);
        U.putFloat(bytes, address + 4L, (float) self.data[1]);
        U.putFloat(bytes, address + 8L, (float) self.data[2]);
        U.putFloat(bytes, address + 12L, (float) self.data[3]);
        return self;
    }

    public Double2x2 loadCMFloatUnsafe(Double2x2Impl self, long address) {
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadCMFloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4L);
        self.data[2] = U.getFloat(bytes, address + 8L);
        self.data[3] = U.getFloat(bytes, address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeRMUnsafe(Double2x2Impl self, long address) {
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[3]);
        return self;
    }

    public Double2x2 storeRMHeap(Double2x2Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[2]);
        U.putDouble(bytes, address + 16L, self.data[1]);
        U.putDouble(bytes, address + 24L, self.data[3]);
        return self;
    }

    public Double2x2 loadRMUnsafe(Double2x2Impl self, long address) {
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8L);
        self.data[1] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadRMHeap(Double2x2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getDouble(bytes, address);
        self.data[2] = U.getDouble(bytes, address + 8L);
        self.data[1] = U.getDouble(bytes, address + 16L);
        self.data[3] = U.getDouble(bytes, address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeRMFloatUnsafe(Double2x2Impl self, long address) {
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, (float) self.data[1]);
        U.putFloat(address + 12L, (float) self.data[3]);
        return self;
    }

    public Double2x2 storeRMFloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.data[0]);
        U.putFloat(bytes, address + 4L, (float) self.data[2]);
        U.putFloat(bytes, address + 8L, (float) self.data[1]);
        U.putFloat(bytes, address + 12L, (float) self.data[3]);
        return self;
    }

    public Double2x2 loadRMFloatUnsafe(Double2x2Impl self, long address) {
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadRMFloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getFloat(bytes, address);
        self.data[2] = U.getFloat(bytes, address + 4L);
        self.data[1] = U.getFloat(bytes, address + 8L);
        self.data[3] = U.getFloat(bytes, address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeCMUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(_p1, self.data[2]);
        U.putDouble(_p1 + 8, self.data[3]);
        return self;
    }

    public Double2x2 storeCMHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8, self.data[1]);
        U.putDouble(bytes, _p1, self.data[2]);
        U.putDouble(bytes, _p1 + 8, self.data[3]);
        return self;
    }

    public Double2x2 loadCMUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8);
        self.data[2] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadCMHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(bytes, address);
        self.data[1] = U.getDouble(bytes, address + 8);
        self.data[2] = U.getDouble(bytes, _p1);
        self.data[3] = U.getDouble(bytes, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeCMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[1]);
        U.putFloat(_p1, (float) self.data[2]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        return self;
    }

    public Double2x2 storeCMFloatHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, (float) self.data[0]);
        U.putFloat(bytes, address + 4, (float) self.data[1]);
        U.putFloat(bytes, _p1, (float) self.data[2]);
        U.putFloat(bytes, _p1 + 4, (float) self.data[3]);
        return self;
    }

    public Double2x2 loadCMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadCMFloatHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4);
        self.data[2] = U.getFloat(bytes, _p1);
        self.data[3] = U.getFloat(bytes, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeRMUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[2]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[3]);
        return self;
    }

    public Double2x2 storeRMHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8, self.data[2]);
        U.putDouble(bytes, _p1, self.data[1]);
        U.putDouble(bytes, _p1 + 8, self.data[3]);
        return self;
    }

    public Double2x2 loadRMUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8);
        self.data[1] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadRMHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(bytes, address);
        self.data[2] = U.getDouble(bytes, address + 8);
        self.data[1] = U.getDouble(bytes, _p1);
        self.data[3] = U.getDouble(bytes, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeRMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[2]);
        U.putFloat(_p1, (float) self.data[1]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        return self;
    }

    public Double2x2 storeRMFloatHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, (float) self.data[0]);
        U.putFloat(bytes, address + 4, (float) self.data[2]);
        U.putFloat(bytes, _p1, (float) self.data[1]);
        U.putFloat(bytes, _p1 + 4, (float) self.data[3]);
        return self;
    }

    public Double2x2 loadRMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 loadRMFloatHeap(Double2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(bytes, address);
        self.data[2] = U.getFloat(bytes, address + 4);
        self.data[1] = U.getFloat(bytes, _p1);
        self.data[3] = U.getFloat(bytes, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x2 storeCM3x3Unsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.data[2]);
        _U.putDouble(address + 32L, self.data[3]);
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeCM3x3Heap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.data[2]);
        _U.putDouble(bytes, address + 32L, self.data[3]);
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeCM3x3FloatUnsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.data[2]);
        _U.putFloat(address + 16L, (float) self.data[3]);
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeCM3x3FloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.data[2]);
        _U.putFloat(bytes, address + 16L, (float) self.data[3]);
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeRM3x3Unsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[2]);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.data[1]);
        _U.putDouble(address + 32L, self.data[3]);
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeRM3x3Heap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[2]);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.data[1]);
        _U.putDouble(bytes, address + 32L, self.data[3]);
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Double2x2 storeRM3x3FloatUnsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[2]);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.data[1]);
        _U.putFloat(address + 16L, (float) self.data[3]);
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeRM3x3FloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[2]);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.data[1]);
        _U.putFloat(bytes, address + 16L, (float) self.data[3]);
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Double2x2 storeCM4x4Unsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.data[2]);
        _U.putDouble(address + 40L, self.data[3]);
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

    public Double2x2 storeCM4x4Heap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.data[2]);
        _U.putDouble(bytes, address + 40L, self.data[3]);
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

    public Double2x2 storeCM4x4FloatUnsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.data[2]);
        _U.putFloat(address + 20L, (float) self.data[3]);
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

    public Double2x2 storeCM4x4FloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.data[2]);
        _U.putFloat(bytes, address + 20L, (float) self.data[3]);
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

    public Double2x2 storeRM4x4Unsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[2]);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.data[1]);
        _U.putDouble(address + 40L, self.data[3]);
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

    public Double2x2 storeRM4x4Heap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[2]);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.data[1]);
        _U.putDouble(bytes, address + 40L, self.data[3]);
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

    public Double2x2 storeRM4x4FloatUnsafe(Double2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[2]);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.data[1]);
        _U.putFloat(address + 20L, (float) self.data[3]);
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

    public Double2x2 storeRM4x4FloatHeap(Double2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[2]);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.data[1]);
        _U.putFloat(bytes, address + 20L, (float) self.data[3]);
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
