// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x3RawOpsUnsafe extends Double3x3RawOps {
    public Double3x3 storeCMUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, self.data[3]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[6]);
        _U.putDouble(address + 56L, self.data[7]);
        _U.putDouble(address + 64L, self.data[8]);
        return self;
    }

    public Double3x3 storeCMHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, self.data[2]);
        _U.putDouble(bytes, address + 24L, self.data[3]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[6]);
        _U.putDouble(bytes, address + 56L, self.data[7]);
        _U.putDouble(bytes, address + 64L, self.data[8]);
        return self;
    }

    public Double3x3 loadCMUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8L);
        self.data[2] = _U.getDouble(address + 16L);
        self.data[3] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        self.data[6] = _U.getDouble(address + 48L);
        self.data[7] = _U.getDouble(address + 56L);
        self.data[8] = _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadCMHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8L);
        self.data[2] = _U.getDouble(bytes, address + 16L);
        self.data[3] = _U.getDouble(bytes, address + 24L);
        self.data[4] = _U.getDouble(bytes, address + 32L);
        self.data[5] = _U.getDouble(bytes, address + 40L);
        self.data[6] = _U.getDouble(bytes, address + 48L);
        self.data[7] = _U.getDouble(bytes, address + 56L);
        self.data[8] = _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeCMFloatUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, (float) self.data[2]);
        _U.putFloat(address + 12L, (float) self.data[3]);
        _U.putFloat(address + 16L, (float) self.data[4]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        _U.putFloat(address + 24L, (float) self.data[6]);
        _U.putFloat(address + 28L, (float) self.data[7]);
        _U.putFloat(address + 32L, (float) self.data[8]);
        return self;
    }

    public Double3x3 storeCMFloatHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, (float) self.data[2]);
        _U.putFloat(bytes, address + 12L, (float) self.data[3]);
        _U.putFloat(bytes, address + 16L, (float) self.data[4]);
        _U.putFloat(bytes, address + 20L, (float) self.data[5]);
        _U.putFloat(bytes, address + 24L, (float) self.data[6]);
        _U.putFloat(bytes, address + 28L, (float) self.data[7]);
        _U.putFloat(bytes, address + 32L, (float) self.data[8]);
        return self;
    }

    public Double3x3 loadCMFloatUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4L);
        self.data[2] = _U.getFloat(address + 8L);
        self.data[3] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        self.data[6] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        self.data[8] = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadCMFloatHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4L);
        self.data[2] = _U.getFloat(bytes, address + 8L);
        self.data[3] = _U.getFloat(bytes, address + 12L);
        self.data[4] = _U.getFloat(bytes, address + 16L);
        self.data[5] = _U.getFloat(bytes, address + 20L);
        self.data[6] = _U.getFloat(bytes, address + 24L);
        self.data[7] = _U.getFloat(bytes, address + 28L);
        self.data[8] = _U.getFloat(bytes, address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeRMUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[3]);
        _U.putDouble(address + 16L, self.data[6]);
        _U.putDouble(address + 24L, self.data[1]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[7]);
        _U.putDouble(address + 48L, self.data[2]);
        _U.putDouble(address + 56L, self.data[5]);
        _U.putDouble(address + 64L, self.data[8]);
        return self;
    }

    public Double3x3 storeRMHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[3]);
        _U.putDouble(bytes, address + 16L, self.data[6]);
        _U.putDouble(bytes, address + 24L, self.data[1]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[7]);
        _U.putDouble(bytes, address + 48L, self.data[2]);
        _U.putDouble(bytes, address + 56L, self.data[5]);
        _U.putDouble(bytes, address + 64L, self.data[8]);
        return self;
    }

    public Double3x3 loadRMUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[3] = _U.getDouble(address + 8L);
        self.data[6] = _U.getDouble(address + 16L);
        self.data[1] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[7] = _U.getDouble(address + 40L);
        self.data[2] = _U.getDouble(address + 48L);
        self.data[5] = _U.getDouble(address + 56L);
        self.data[8] = _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadRMHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[3] = _U.getDouble(bytes, address + 8L);
        self.data[6] = _U.getDouble(bytes, address + 16L);
        self.data[1] = _U.getDouble(bytes, address + 24L);
        self.data[4] = _U.getDouble(bytes, address + 32L);
        self.data[7] = _U.getDouble(bytes, address + 40L);
        self.data[2] = _U.getDouble(bytes, address + 48L);
        self.data[5] = _U.getDouble(bytes, address + 56L);
        self.data[8] = _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeRMFloatUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[3]);
        _U.putFloat(address + 8L, (float) self.data[6]);
        _U.putFloat(address + 12L, (float) self.data[1]);
        _U.putFloat(address + 16L, (float) self.data[4]);
        _U.putFloat(address + 20L, (float) self.data[7]);
        _U.putFloat(address + 24L, (float) self.data[2]);
        _U.putFloat(address + 28L, (float) self.data[5]);
        _U.putFloat(address + 32L, (float) self.data[8]);
        return self;
    }

    public Double3x3 storeRMFloatHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[3]);
        _U.putFloat(bytes, address + 8L, (float) self.data[6]);
        _U.putFloat(bytes, address + 12L, (float) self.data[1]);
        _U.putFloat(bytes, address + 16L, (float) self.data[4]);
        _U.putFloat(bytes, address + 20L, (float) self.data[7]);
        _U.putFloat(bytes, address + 24L, (float) self.data[2]);
        _U.putFloat(bytes, address + 28L, (float) self.data[5]);
        _U.putFloat(bytes, address + 32L, (float) self.data[8]);
        return self;
    }

    public Double3x3 loadRMFloatUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[3] = _U.getFloat(address + 4L);
        self.data[6] = _U.getFloat(address + 8L);
        self.data[1] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[7] = _U.getFloat(address + 20L);
        self.data[2] = _U.getFloat(address + 24L);
        self.data[5] = _U.getFloat(address + 28L);
        self.data[8] = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadRMFloatHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[3] = _U.getFloat(bytes, address + 4L);
        self.data[6] = _U.getFloat(bytes, address + 8L);
        self.data[1] = _U.getFloat(bytes, address + 12L);
        self.data[4] = _U.getFloat(bytes, address + 16L);
        self.data[7] = _U.getFloat(bytes, address + 20L);
        self.data[2] = _U.getFloat(bytes, address + 24L);
        self.data[5] = _U.getFloat(bytes, address + 28L);
        self.data[8] = _U.getFloat(bytes, address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeCMUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(_p1, self.data[3]);
        _U.putDouble(_p1 + 8, self.data[4]);
        _U.putDouble(_p1 + 16, self.data[5]);
        _U.putDouble(_p2, self.data[6]);
        _U.putDouble(_p2 + 8, self.data[7]);
        _U.putDouble(_p2 + 16, self.data[8]);
        return self;
    }

    public Double3x3 storeCMHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[1]);
        _U.putDouble(bytes, address + 16, self.data[2]);
        _U.putDouble(bytes, _p1, self.data[3]);
        _U.putDouble(bytes, _p1 + 8, self.data[4]);
        _U.putDouble(bytes, _p1 + 16, self.data[5]);
        _U.putDouble(bytes, _p2, self.data[6]);
        _U.putDouble(bytes, _p2 + 8, self.data[7]);
        _U.putDouble(bytes, _p2 + 16, self.data[8]);
        return self;
    }

    public Double3x3 loadCMUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8);
        self.data[2] = _U.getDouble(address + 16);
        self.data[3] = _U.getDouble(_p1);
        self.data[4] = _U.getDouble(_p1 + 8);
        self.data[5] = _U.getDouble(_p1 + 16);
        self.data[6] = _U.getDouble(_p2);
        self.data[7] = _U.getDouble(_p2 + 8);
        self.data[8] = _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadCMHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8);
        self.data[2] = _U.getDouble(bytes, address + 16);
        self.data[3] = _U.getDouble(bytes, _p1);
        self.data[4] = _U.getDouble(bytes, _p1 + 8);
        self.data[5] = _U.getDouble(bytes, _p1 + 16);
        self.data[6] = _U.getDouble(bytes, _p2);
        self.data[7] = _U.getDouble(bytes, _p2 + 8);
        self.data[8] = _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeCMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[1]);
        _U.putFloat(address + 8, (float) self.data[2]);
        _U.putFloat(_p1, (float) self.data[3]);
        _U.putFloat(_p1 + 4, (float) self.data[4]);
        _U.putFloat(_p1 + 8, (float) self.data[5]);
        _U.putFloat(_p2, (float) self.data[6]);
        _U.putFloat(_p2 + 4, (float) self.data[7]);
        _U.putFloat(_p2 + 8, (float) self.data[8]);
        return self;
    }

    public Double3x3 storeCMFloatHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[1]);
        _U.putFloat(bytes, address + 8, (float) self.data[2]);
        _U.putFloat(bytes, _p1, (float) self.data[3]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[4]);
        _U.putFloat(bytes, _p1 + 8, (float) self.data[5]);
        _U.putFloat(bytes, _p2, (float) self.data[6]);
        _U.putFloat(bytes, _p2 + 4, (float) self.data[7]);
        _U.putFloat(bytes, _p2 + 8, (float) self.data[8]);
        return self;
    }

    public Double3x3 loadCMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(_p1);
        self.data[4] = _U.getFloat(_p1 + 4);
        self.data[5] = _U.getFloat(_p1 + 8);
        self.data[6] = _U.getFloat(_p2);
        self.data[7] = _U.getFloat(_p2 + 4);
        self.data[8] = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadCMFloatHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4);
        self.data[2] = _U.getFloat(bytes, address + 8);
        self.data[3] = _U.getFloat(bytes, _p1);
        self.data[4] = _U.getFloat(bytes, _p1 + 4);
        self.data[5] = _U.getFloat(bytes, _p1 + 8);
        self.data[6] = _U.getFloat(bytes, _p2);
        self.data[7] = _U.getFloat(bytes, _p2 + 4);
        self.data[8] = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeRMUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[3]);
        _U.putDouble(address + 16, self.data[6]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[4]);
        _U.putDouble(_p1 + 16, self.data[7]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[5]);
        _U.putDouble(_p2 + 16, self.data[8]);
        return self;
    }

    public Double3x3 storeRMHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[3]);
        _U.putDouble(bytes, address + 16, self.data[6]);
        _U.putDouble(bytes, _p1, self.data[1]);
        _U.putDouble(bytes, _p1 + 8, self.data[4]);
        _U.putDouble(bytes, _p1 + 16, self.data[7]);
        _U.putDouble(bytes, _p2, self.data[2]);
        _U.putDouble(bytes, _p2 + 8, self.data[5]);
        _U.putDouble(bytes, _p2 + 16, self.data[8]);
        return self;
    }

    public Double3x3 loadRMUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[3] = _U.getDouble(address + 8);
        self.data[6] = _U.getDouble(address + 16);
        self.data[1] = _U.getDouble(_p1);
        self.data[4] = _U.getDouble(_p1 + 8);
        self.data[7] = _U.getDouble(_p1 + 16);
        self.data[2] = _U.getDouble(_p2);
        self.data[5] = _U.getDouble(_p2 + 8);
        self.data[8] = _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadRMHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[3] = _U.getDouble(bytes, address + 8);
        self.data[6] = _U.getDouble(bytes, address + 16);
        self.data[1] = _U.getDouble(bytes, _p1);
        self.data[4] = _U.getDouble(bytes, _p1 + 8);
        self.data[7] = _U.getDouble(bytes, _p1 + 16);
        self.data[2] = _U.getDouble(bytes, _p2);
        self.data[5] = _U.getDouble(bytes, _p2 + 8);
        self.data[8] = _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeRMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[3]);
        _U.putFloat(address + 8, (float) self.data[6]);
        _U.putFloat(_p1, (float) self.data[1]);
        _U.putFloat(_p1 + 4, (float) self.data[4]);
        _U.putFloat(_p1 + 8, (float) self.data[7]);
        _U.putFloat(_p2, (float) self.data[2]);
        _U.putFloat(_p2 + 4, (float) self.data[5]);
        _U.putFloat(_p2 + 8, (float) self.data[8]);
        return self;
    }

    public Double3x3 storeRMFloatHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[3]);
        _U.putFloat(bytes, address + 8, (float) self.data[6]);
        _U.putFloat(bytes, _p1, (float) self.data[1]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[4]);
        _U.putFloat(bytes, _p1 + 8, (float) self.data[7]);
        _U.putFloat(bytes, _p2, (float) self.data[2]);
        _U.putFloat(bytes, _p2 + 4, (float) self.data[5]);
        _U.putFloat(bytes, _p2 + 8, (float) self.data[8]);
        return self;
    }

    public Double3x3 loadRMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[3] = _U.getFloat(address + 4);
        self.data[6] = _U.getFloat(address + 8);
        self.data[1] = _U.getFloat(_p1);
        self.data[4] = _U.getFloat(_p1 + 4);
        self.data[7] = _U.getFloat(_p1 + 8);
        self.data[2] = _U.getFloat(_p2);
        self.data[5] = _U.getFloat(_p2 + 4);
        self.data[8] = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 loadRMFloatHeap(Double3x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[3] = _U.getFloat(bytes, address + 4);
        self.data[6] = _U.getFloat(bytes, address + 8);
        self.data[1] = _U.getFloat(bytes, _p1);
        self.data[4] = _U.getFloat(bytes, _p1 + 4);
        self.data[7] = _U.getFloat(bytes, _p1 + 8);
        self.data[2] = _U.getFloat(bytes, _p2);
        self.data[5] = _U.getFloat(bytes, _p2 + 4);
        self.data[8] = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x3 storeCM4x4Unsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.data[3]);
        _U.putDouble(address + 40L, self.data[4]);
        _U.putDouble(address + 48L, self.data[5]);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.data[6]);
        _U.putDouble(address + 72L, self.data[7]);
        _U.putDouble(address + 80L, self.data[8]);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Double3x3 storeCM4x4Heap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, self.data[2]);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.data[3]);
        _U.putDouble(bytes, address + 40L, self.data[4]);
        _U.putDouble(bytes, address + 48L, self.data[5]);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, self.data[6]);
        _U.putDouble(bytes, address + 72L, self.data[7]);
        _U.putDouble(bytes, address + 80L, self.data[8]);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Double3x3 storeCM4x4FloatUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, (float) self.data[2]);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.data[3]);
        _U.putFloat(address + 20L, (float) self.data[4]);
        _U.putFloat(address + 24L, (float) self.data[5]);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, (float) self.data[6]);
        _U.putFloat(address + 36L, (float) self.data[7]);
        _U.putFloat(address + 40L, (float) self.data[8]);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Double3x3 storeCM4x4FloatHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, (float) self.data[2]);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.data[3]);
        _U.putFloat(bytes, address + 20L, (float) self.data[4]);
        _U.putFloat(bytes, address + 24L, (float) self.data[5]);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, (float) self.data[6]);
        _U.putFloat(bytes, address + 36L, (float) self.data[7]);
        _U.putFloat(bytes, address + 40L, (float) self.data[8]);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }

    public Double3x3 storeRM4x4Unsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[3]);
        _U.putDouble(address + 16L, self.data[6]);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.data[1]);
        _U.putDouble(address + 40L, self.data[4]);
        _U.putDouble(address + 48L, self.data[7]);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.data[2]);
        _U.putDouble(address + 72L, self.data[5]);
        _U.putDouble(address + 80L, self.data[8]);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Double3x3 storeRM4x4Heap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[3]);
        _U.putDouble(bytes, address + 16L, self.data[6]);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.data[1]);
        _U.putDouble(bytes, address + 40L, self.data[4]);
        _U.putDouble(bytes, address + 48L, self.data[7]);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, self.data[2]);
        _U.putDouble(bytes, address + 72L, self.data[5]);
        _U.putDouble(bytes, address + 80L, self.data[8]);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Double3x3 storeRM4x4FloatUnsafe(Double3x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[3]);
        _U.putFloat(address + 8L, (float) self.data[6]);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.data[1]);
        _U.putFloat(address + 20L, (float) self.data[4]);
        _U.putFloat(address + 24L, (float) self.data[7]);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, (float) self.data[2]);
        _U.putFloat(address + 36L, (float) self.data[5]);
        _U.putFloat(address + 40L, (float) self.data[8]);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Double3x3 storeRM4x4FloatHeap(Double3x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[3]);
        _U.putFloat(bytes, address + 8L, (float) self.data[6]);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.data[1]);
        _U.putFloat(bytes, address + 20L, (float) self.data[4]);
        _U.putFloat(bytes, address + 24L, (float) self.data[7]);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, (float) self.data[2]);
        _U.putFloat(bytes, address + 36L, (float) self.data[5]);
        _U.putFloat(bytes, address + 40L, (float) self.data[8]);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }
}
