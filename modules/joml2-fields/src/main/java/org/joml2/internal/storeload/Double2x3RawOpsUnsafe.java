// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2x3RawOpsUnsafe extends Double2x3RawOps {
    public Double2x3 storeCMUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m01);
        _U.putDouble(address + 24L, self.m11);
        _U.putDouble(address + 32L, self.m02);
        _U.putDouble(address + 40L, self.m12);
        return self;
    }

    public Double2x3 storeCMHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m01);
        _U.putDouble(bytes, address + 24L, self.m11);
        _U.putDouble(bytes, address + 32L, self.m02);
        _U.putDouble(bytes, address + 40L, self.m12);
        return self;
    }

    public Double2x3 loadCMUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8L);
        self.m01 = _U.getDouble(address + 16L);
        self.m11 = _U.getDouble(address + 24L);
        self.m02 = _U.getDouble(address + 32L);
        self.m12 = _U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadCMHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8L);
        self.m01 = _U.getDouble(bytes, address + 16L);
        self.m11 = _U.getDouble(bytes, address + 24L);
        self.m02 = _U.getDouble(bytes, address + 32L);
        self.m12 = _U.getDouble(bytes, address + 40L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, (float) self.m01);
        _U.putFloat(address + 12L, (float) self.m11);
        _U.putFloat(address + 16L, (float) self.m02);
        _U.putFloat(address + 20L, (float) self.m12);
        return self;
    }

    public Double2x3 storeCMFloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, (float) self.m01);
        _U.putFloat(bytes, address + 12L, (float) self.m11);
        _U.putFloat(bytes, address + 16L, (float) self.m02);
        _U.putFloat(bytes, address + 20L, (float) self.m12);
        return self;
    }

    public Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m01 = _U.getFloat(address + 8L);
        self.m11 = _U.getFloat(address + 12L);
        self.m02 = _U.getFloat(address + 16L);
        self.m12 = _U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadCMFloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m01 = _U.getFloat(bytes, address + 8L);
        self.m11 = _U.getFloat(bytes, address + 12L);
        self.m02 = _U.getFloat(bytes, address + 16L);
        self.m12 = _U.getFloat(bytes, address + 20L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeRMUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m12);
        return self;
    }

    public Double2x3 storeRMHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, self.m10);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m12);
        return self;
    }

    public Double2x3 loadRMUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8L);
        self.m02 = _U.getDouble(address + 16L);
        self.m10 = _U.getDouble(address + 24L);
        self.m11 = _U.getDouble(address + 32L);
        self.m12 = _U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadRMHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8L);
        self.m02 = _U.getDouble(bytes, address + 16L);
        self.m10 = _U.getDouble(bytes, address + 24L);
        self.m11 = _U.getDouble(bytes, address + 32L);
        self.m12 = _U.getDouble(bytes, address + 40L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, (float) self.m02);
        _U.putFloat(address + 12L, (float) self.m10);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, (float) self.m12);
        return self;
    }

    public Double2x3 storeRMFloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, (float) self.m02);
        _U.putFloat(bytes, address + 12L, (float) self.m10);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, (float) self.m12);
        return self;
    }

    public Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m10 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m12 = _U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadRMFloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m02 = _U.getFloat(bytes, address + 8L);
        self.m10 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m12 = _U.getFloat(bytes, address + 20L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeCMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p2, self.m02);
        _U.putDouble(_p2 + 8, self.m12);
        return self;
    }

    public Double2x3 storeCMHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p2, self.m02);
        _U.putDouble(bytes, _p2 + 8, self.m12);
        return self;
    }

    public Double2x3 loadCMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8);
        self.m01 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m02 = _U.getDouble(_p2);
        self.m12 = _U.getDouble(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadCMHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8);
        self.m01 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m02 = _U.getDouble(bytes, _p2);
        self.m12 = _U.getDouble(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m10);
        _U.putFloat(_p1, (float) self.m01);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p2, (float) self.m02);
        _U.putFloat(_p2 + 4, (float) self.m12);
        return self;
    }

    public Double2x3 storeCMFloatHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m10);
        _U.putFloat(bytes, _p1, (float) self.m01);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p2, (float) self.m02);
        _U.putFloat(bytes, _p2 + 4, (float) self.m12);
        return self;
    }

    public Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m02 = _U.getFloat(_p2);
        self.m12 = _U.getFloat(_p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadCMFloatHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m02 = _U.getFloat(bytes, _p2);
        self.m12 = _U.getFloat(bytes, _p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeRMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(address + 16, self.m02);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m12);
        return self;
    }

    public Double2x3 storeRMHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, address + 16, self.m02);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m12);
        return self;
    }

    public Double2x3 loadRMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8);
        self.m02 = _U.getDouble(address + 16);
        self.m10 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m12 = _U.getDouble(_p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadRMHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8);
        self.m02 = _U.getDouble(bytes, address + 16);
        self.m10 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m12 = _U.getDouble(bytes, _p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m01);
        _U.putFloat(address + 8, (float) self.m02);
        _U.putFloat(_p1, (float) self.m10);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p1 + 8, (float) self.m12);
        return self;
    }

    public Double2x3 storeRMFloatHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m01);
        _U.putFloat(bytes, address + 8, (float) self.m02);
        _U.putFloat(bytes, _p1, (float) self.m10);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p1 + 8, (float) self.m12);
        return self;
    }

    public Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m02 = _U.getFloat(address + 8);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m12 = _U.getFloat(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 loadRMFloatHeap(Double2x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m02 = _U.getFloat(bytes, address + 8);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m12 = _U.getFloat(bytes, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double2x3 storeCM3x3Unsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, self.m02);
        _U.putDouble(address + 56L, self.m12);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Double2x3 storeCM3x3Heap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, self.m02);
        _U.putDouble(bytes, address + 56L, self.m12);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Double2x3 storeCM3x3FloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.m01);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, (float) self.m02);
        _U.putFloat(address + 28L, (float) self.m12);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Double2x3 storeCM3x3FloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.m01);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, (float) self.m02);
        _U.putFloat(bytes, address + 28L, (float) self.m12);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Double2x3 storeRM3x3Unsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m12);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Double2x3 storeRM3x3Heap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, self.m10);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m12);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Double2x3 storeRM3x3FloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, (float) self.m02);
        _U.putFloat(address + 12L, (float) self.m10);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, (float) self.m12);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Double2x3 storeRM3x3FloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, (float) self.m02);
        _U.putFloat(bytes, address + 12L, (float) self.m10);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, (float) self.m12);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Double2x3 storeCM4x4Unsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, self.m02);
        _U.putDouble(address + 104L, self.m12);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Double2x3 storeCM4x4Heap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m01);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 0.0);
        _U.putDouble(bytes, address + 72L, 0.0);
        _U.putDouble(bytes, address + 80L, 1.0);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, self.m02);
        _U.putDouble(bytes, address + 104L, self.m12);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Double2x3 storeCM4x4FloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.m01);
        _U.putFloat(address + 20L, (float) self.m11);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, (float) self.m02);
        _U.putFloat(address + 52L, (float) self.m12);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Double2x3 storeCM4x4FloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.m01);
        _U.putFloat(bytes, address + 20L, (float) self.m11);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 0.0f);
        _U.putFloat(bytes, address + 36L, 0.0f);
        _U.putFloat(bytes, address + 40L, 1.0f);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, (float) self.m02);
        _U.putFloat(bytes, address + 52L, (float) self.m12);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }

    public Double2x3 storeRM4x4Unsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m02);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, self.m12);
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

    public Double2x3 storeRM4x4Heap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m02);
        _U.putDouble(bytes, address + 32L, self.m10);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, self.m12);
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

    public Double2x3 storeRM4x4FloatUnsafe(Double2x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.m02);
        _U.putFloat(address + 16L, (float) self.m10);
        _U.putFloat(address + 20L, (float) self.m11);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, (float) self.m12);
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

    public Double2x3 storeRM4x4FloatHeap(Double2x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.m02);
        _U.putFloat(bytes, address + 16L, (float) self.m10);
        _U.putFloat(bytes, address + 20L, (float) self.m11);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, (float) self.m12);
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
