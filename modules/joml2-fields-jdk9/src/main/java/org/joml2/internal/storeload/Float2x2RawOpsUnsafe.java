// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2x2RawOpsUnsafe extends Float2x2RawOps {
    public Float2x2 storeCMUnsafe(Float2x2Impl self, long address) {
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m01);
        U.putFloat(address + 12L, self.m11);
        return self;
    }

    public Float2x2 storeCMHeap(Float2x2Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.m00);
        U.putFloat(bytes, address + 4L, self.m10);
        U.putFloat(bytes, address + 8L, self.m01);
        U.putFloat(bytes, address + 12L, self.m11);
        return self;
    }

    public Float2x2 loadCMUnsafe(Float2x2Impl self, long address) {
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m01 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadCMHeap(Float2x2Impl self, byte[] bytes, long address) {
        self.m00 = U.getFloat(bytes, address);
        self.m10 = U.getFloat(bytes, address + 4L);
        self.m01 = U.getFloat(bytes, address + 8L);
        self.m11 = U.getFloat(bytes, address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeCMDoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m01);
        U.putDouble(address + 24L, self.m11);
        return self;
    }

    public Float2x2 storeCMDoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8L, self.m10);
        U.putDouble(bytes, address + 16L, self.m01);
        U.putDouble(bytes, address + 24L, self.m11);
        return self;
    }

    public Float2x2 loadCMDoubleUnsafe(Float2x2Impl self, long address) {
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8L);
        self.m01 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadCMDoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
        self.m00 = (float) U.getDouble(bytes, address);
        self.m10 = (float) U.getDouble(bytes, address + 8L);
        self.m01 = (float) U.getDouble(bytes, address + 16L);
        self.m11 = (float) U.getDouble(bytes, address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeRMUnsafe(Float2x2Impl self, long address) {
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m10);
        U.putFloat(address + 12L, self.m11);
        return self;
    }

    public Float2x2 storeRMHeap(Float2x2Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.m00);
        U.putFloat(bytes, address + 4L, self.m01);
        U.putFloat(bytes, address + 8L, self.m10);
        U.putFloat(bytes, address + 12L, self.m11);
        return self;
    }

    public Float2x2 loadRMUnsafe(Float2x2Impl self, long address) {
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m10 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadRMHeap(Float2x2Impl self, byte[] bytes, long address) {
        self.m00 = U.getFloat(bytes, address);
        self.m01 = U.getFloat(bytes, address + 4L);
        self.m10 = U.getFloat(bytes, address + 8L);
        self.m11 = U.getFloat(bytes, address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeRMDoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m10);
        U.putDouble(address + 24L, self.m11);
        return self;
    }

    public Float2x2 storeRMDoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8L, self.m01);
        U.putDouble(bytes, address + 16L, self.m10);
        U.putDouble(bytes, address + 24L, self.m11);
        return self;
    }

    public Float2x2 loadRMDoubleUnsafe(Float2x2Impl self, long address) {
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8L);
        self.m10 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadRMDoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
        self.m00 = (float) U.getDouble(bytes, address);
        self.m01 = (float) U.getDouble(bytes, address + 8L);
        self.m10 = (float) U.getDouble(bytes, address + 16L);
        self.m11 = (float) U.getDouble(bytes, address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeCMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m10);
        U.putFloat(_p1, self.m01);
        U.putFloat(_p1 + 4, self.m11);
        return self;
    }

    public Float2x2 storeCMHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, self.m00);
        U.putFloat(bytes, address + 4, self.m10);
        U.putFloat(bytes, _p1, self.m01);
        U.putFloat(bytes, _p1 + 4, self.m11);
        return self;
    }

    public Float2x2 loadCMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadCMHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(bytes, address);
        self.m10 = U.getFloat(bytes, address + 4);
        self.m01 = U.getFloat(bytes, _p1);
        self.m11 = U.getFloat(bytes, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeCMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        return self;
    }

    public Float2x2 storeCMDoubleHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8, self.m10);
        U.putDouble(bytes, _p1, self.m01);
        U.putDouble(bytes, _p1 + 8, self.m11);
        return self;
    }

    public Float2x2 loadCMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8);
        self.m01 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadCMDoubleHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(bytes, address);
        self.m10 = (float) U.getDouble(bytes, address + 8);
        self.m01 = (float) U.getDouble(bytes, _p1);
        self.m11 = (float) U.getDouble(bytes, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeRMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m01);
        U.putFloat(_p1, self.m10);
        U.putFloat(_p1 + 4, self.m11);
        return self;
    }

    public Float2x2 storeRMHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, self.m00);
        U.putFloat(bytes, address + 4, self.m01);
        U.putFloat(bytes, _p1, self.m10);
        U.putFloat(bytes, _p1 + 4, self.m11);
        return self;
    }

    public Float2x2 loadRMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadRMHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(bytes, address);
        self.m01 = U.getFloat(bytes, address + 4);
        self.m10 = U.getFloat(bytes, _p1);
        self.m11 = U.getFloat(bytes, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeRMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        return self;
    }

    public Float2x2 storeRMDoubleHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8, self.m01);
        U.putDouble(bytes, _p1, self.m10);
        U.putDouble(bytes, _p1 + 8, self.m11);
        return self;
    }

    public Float2x2 loadRMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8);
        self.m10 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 loadRMDoubleHeap(Float2x2Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(bytes, address);
        self.m01 = (float) U.getDouble(bytes, address + 8);
        self.m10 = (float) U.getDouble(bytes, _p1);
        self.m11 = (float) U.getDouble(bytes, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Float2x2 storeCM3x3Unsafe(Float2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m01);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Float2x2 storeCM3x3Heap(Float2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, self.m01);
        _U.putFloat(bytes, address + 16L, self.m11);
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Float2x2 storeCM3x3DoubleUnsafe(Float2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Float2x2 storeCM3x3DoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Float2x2 storeRM3x3Unsafe(Float2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m10);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return self;
    }

    public Float2x2 storeRM3x3Heap(Float2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m01);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, self.m10);
        _U.putFloat(bytes, address + 16L, self.m11);
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return self;
    }

    public Float2x2 storeRM3x3DoubleUnsafe(Float2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return self;
    }

    public Float2x2 storeRM3x3DoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m10);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return self;
    }

    public Float2x2 storeCM4x4Unsafe(Float2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m01);
        _U.putFloat(address + 20L, self.m11);
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

    public Float2x2 storeCM4x4Heap(Float2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, self.m01);
        _U.putFloat(bytes, address + 20L, self.m11);
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

    public Float2x2 storeCM4x4DoubleUnsafe(Float2x2Impl self, long address) {
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
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Float2x2 storeCM4x4DoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
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
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Float2x2 storeRM4x4Unsafe(Float2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m10);
        _U.putFloat(address + 20L, self.m11);
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

    public Float2x2 storeRM4x4Heap(Float2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m01);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, self.m10);
        _U.putFloat(bytes, address + 20L, self.m11);
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

    public Float2x2 storeRM4x4DoubleUnsafe(Float2x2Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
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

    public Float2x2 storeRM4x4DoubleHeap(Float2x2Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m10);
        _U.putDouble(bytes, address + 40L, self.m11);
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
}
