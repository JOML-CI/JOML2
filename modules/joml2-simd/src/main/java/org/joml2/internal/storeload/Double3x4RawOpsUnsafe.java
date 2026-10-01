// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x4RawOpsUnsafe extends Double3x4RawOps {
    public Double3x4 storeCMUnsafe(Double3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[8]);
        _U.putDouble(address + 24L, self.data[1]);
        _U.putDouble(address + 32L, self.data[5]);
        _U.putDouble(address + 40L, self.data[9]);
        _U.putDouble(address + 48L, self.data[2]);
        _U.putDouble(address + 56L, self.data[6]);
        _U.putDouble(address + 64L, self.data[10]);
        _U.putDouble(address + 72L, self.data[3]);
        _U.putDouble(address + 80L, self.data[7]);
        _U.putDouble(address + 88L, self.data[11]);
        return self;
    }

    public Double3x4 storeCMHeap(Double3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[8]);
        _U.putDouble(bytes, address + 24L, self.data[1]);
        _U.putDouble(bytes, address + 32L, self.data[5]);
        _U.putDouble(bytes, address + 40L, self.data[9]);
        _U.putDouble(bytes, address + 48L, self.data[2]);
        _U.putDouble(bytes, address + 56L, self.data[6]);
        _U.putDouble(bytes, address + 64L, self.data[10]);
        _U.putDouble(bytes, address + 72L, self.data[3]);
        _U.putDouble(bytes, address + 80L, self.data[7]);
        _U.putDouble(bytes, address + 88L, self.data[11]);
        return self;
    }

    public Double3x4 loadCMUnsafe(Double3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8L);
        self.data[8] = _U.getDouble(address + 16L);
        self.data[1] = _U.getDouble(address + 24L);
        self.data[5] = _U.getDouble(address + 32L);
        self.data[9] = _U.getDouble(address + 40L);
        self.data[2] = _U.getDouble(address + 48L);
        self.data[6] = _U.getDouble(address + 56L);
        self.data[10] = _U.getDouble(address + 64L);
        self.data[3] = _U.getDouble(address + 72L);
        self.data[7] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadCMHeap(Double3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[4] = _U.getDouble(bytes, address + 8L);
        self.data[8] = _U.getDouble(bytes, address + 16L);
        self.data[1] = _U.getDouble(bytes, address + 24L);
        self.data[5] = _U.getDouble(bytes, address + 32L);
        self.data[9] = _U.getDouble(bytes, address + 40L);
        self.data[2] = _U.getDouble(bytes, address + 48L);
        self.data[6] = _U.getDouble(bytes, address + 56L);
        self.data[10] = _U.getDouble(bytes, address + 64L);
        self.data[3] = _U.getDouble(bytes, address + 72L);
        self.data[7] = _U.getDouble(bytes, address + 80L);
        self.data[11] = _U.getDouble(bytes, address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[4]);
        _U.putFloat(address + 8L, (float) self.data[8]);
        _U.putFloat(address + 12L, (float) self.data[1]);
        _U.putFloat(address + 16L, (float) self.data[5]);
        _U.putFloat(address + 20L, (float) self.data[9]);
        _U.putFloat(address + 24L, (float) self.data[2]);
        _U.putFloat(address + 28L, (float) self.data[6]);
        _U.putFloat(address + 32L, (float) self.data[10]);
        _U.putFloat(address + 36L, (float) self.data[3]);
        _U.putFloat(address + 40L, (float) self.data[7]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        return self;
    }

    public Double3x4 storeCMFloatHeap(Double3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[4]);
        _U.putFloat(bytes, address + 8L, (float) self.data[8]);
        _U.putFloat(bytes, address + 12L, (float) self.data[1]);
        _U.putFloat(bytes, address + 16L, (float) self.data[5]);
        _U.putFloat(bytes, address + 20L, (float) self.data[9]);
        _U.putFloat(bytes, address + 24L, (float) self.data[2]);
        _U.putFloat(bytes, address + 28L, (float) self.data[6]);
        _U.putFloat(bytes, address + 32L, (float) self.data[10]);
        _U.putFloat(bytes, address + 36L, (float) self.data[3]);
        _U.putFloat(bytes, address + 40L, (float) self.data[7]);
        _U.putFloat(bytes, address + 44L, (float) self.data[11]);
        return self;
    }

    public Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[8] = _U.getFloat(address + 8L);
        self.data[1] = _U.getFloat(address + 12L);
        self.data[5] = _U.getFloat(address + 16L);
        self.data[9] = _U.getFloat(address + 20L);
        self.data[2] = _U.getFloat(address + 24L);
        self.data[6] = _U.getFloat(address + 28L);
        self.data[10] = _U.getFloat(address + 32L);
        self.data[3] = _U.getFloat(address + 36L);
        self.data[7] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadCMFloatHeap(Double3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4L);
        self.data[8] = _U.getFloat(bytes, address + 8L);
        self.data[1] = _U.getFloat(bytes, address + 12L);
        self.data[5] = _U.getFloat(bytes, address + 16L);
        self.data[9] = _U.getFloat(bytes, address + 20L);
        self.data[2] = _U.getFloat(bytes, address + 24L);
        self.data[6] = _U.getFloat(bytes, address + 28L);
        self.data[10] = _U.getFloat(bytes, address + 32L);
        self.data[3] = _U.getFloat(bytes, address + 36L);
        self.data[7] = _U.getFloat(bytes, address + 40L);
        self.data[11] = _U.getFloat(bytes, address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeRMUnsafe(Double3x4Impl self, long address) {
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
        _U.putDouble(address + 72L, self.data[9]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, self.data[11]);
        return self;
    }

    public Double3x4 storeRMHeap(Double3x4Impl self, byte[] bytes, long address) {
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
        _U.putDouble(bytes, address + 72L, self.data[9]);
        _U.putDouble(bytes, address + 80L, self.data[10]);
        _U.putDouble(bytes, address + 88L, self.data[11]);
        return self;
    }

    public Double3x4 loadRMUnsafe(Double3x4Impl self, long address) {
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
        self.data[9] = _U.getDouble(address + 72L);
        self.data[10] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadRMHeap(Double3x4Impl self, byte[] bytes, long address) {
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
        self.data[9] = _U.getDouble(bytes, address + 72L);
        self.data[10] = _U.getDouble(bytes, address + 80L);
        self.data[11] = _U.getDouble(bytes, address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address) {
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
        _U.putFloat(address + 36L, (float) self.data[9]);
        _U.putFloat(address + 40L, (float) self.data[10]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        return self;
    }

    public Double3x4 storeRMFloatHeap(Double3x4Impl self, byte[] bytes, long address) {
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
        _U.putFloat(bytes, address + 36L, (float) self.data[9]);
        _U.putFloat(bytes, address + 40L, (float) self.data[10]);
        _U.putFloat(bytes, address + 44L, (float) self.data[11]);
        return self;
    }

    public Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address) {
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
        self.data[9] = _U.getFloat(address + 36L);
        self.data[10] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadRMFloatHeap(Double3x4Impl self, byte[] bytes, long address) {
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
        self.data[9] = _U.getFloat(bytes, address + 36L);
        self.data[10] = _U.getFloat(bytes, address + 40L);
        self.data[11] = _U.getFloat(bytes, address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeCMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[4]);
        _U.putDouble(address + 16, self.data[8]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[9]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[6]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p3, self.data[3]);
        _U.putDouble(_p3 + 8, self.data[7]);
        _U.putDouble(_p3 + 16, self.data[11]);
        return self;
    }

    public Double3x4 storeCMHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[4]);
        _U.putDouble(bytes, address + 16, self.data[8]);
        _U.putDouble(bytes, _p1, self.data[1]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p1 + 16, self.data[9]);
        _U.putDouble(bytes, _p2, self.data[2]);
        _U.putDouble(bytes, _p2 + 8, self.data[6]);
        _U.putDouble(bytes, _p2 + 16, self.data[10]);
        _U.putDouble(bytes, _p3, self.data[3]);
        _U.putDouble(bytes, _p3 + 8, self.data[7]);
        _U.putDouble(bytes, _p3 + 16, self.data[11]);
        return self;
    }

    public Double3x4 loadCMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8);
        self.data[8] = _U.getDouble(address + 16);
        self.data[1] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[9] = _U.getDouble(_p1 + 16);
        self.data[2] = _U.getDouble(_p2);
        self.data[6] = _U.getDouble(_p2 + 8);
        self.data[10] = _U.getDouble(_p2 + 16);
        self.data[3] = _U.getDouble(_p3);
        self.data[7] = _U.getDouble(_p3 + 8);
        self.data[11] = _U.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadCMHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[4] = _U.getDouble(bytes, address + 8);
        self.data[8] = _U.getDouble(bytes, address + 16);
        self.data[1] = _U.getDouble(bytes, _p1);
        self.data[5] = _U.getDouble(bytes, _p1 + 8);
        self.data[9] = _U.getDouble(bytes, _p1 + 16);
        self.data[2] = _U.getDouble(bytes, _p2);
        self.data[6] = _U.getDouble(bytes, _p2 + 8);
        self.data[10] = _U.getDouble(bytes, _p2 + 16);
        self.data[3] = _U.getDouble(bytes, _p3);
        self.data[7] = _U.getDouble(bytes, _p3 + 8);
        self.data[11] = _U.getDouble(bytes, _p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[4]);
        _U.putFloat(address + 8, (float) self.data[8]);
        _U.putFloat(_p1, (float) self.data[1]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p1 + 8, (float) self.data[9]);
        _U.putFloat(_p2, (float) self.data[2]);
        _U.putFloat(_p2 + 4, (float) self.data[6]);
        _U.putFloat(_p2 + 8, (float) self.data[10]);
        _U.putFloat(_p3, (float) self.data[3]);
        _U.putFloat(_p3 + 4, (float) self.data[7]);
        _U.putFloat(_p3 + 8, (float) self.data[11]);
        return self;
    }

    public Double3x4 storeCMFloatHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[4]);
        _U.putFloat(bytes, address + 8, (float) self.data[8]);
        _U.putFloat(bytes, _p1, (float) self.data[1]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[5]);
        _U.putFloat(bytes, _p1 + 8, (float) self.data[9]);
        _U.putFloat(bytes, _p2, (float) self.data[2]);
        _U.putFloat(bytes, _p2 + 4, (float) self.data[6]);
        _U.putFloat(bytes, _p2 + 8, (float) self.data[10]);
        _U.putFloat(bytes, _p3, (float) self.data[3]);
        _U.putFloat(bytes, _p3 + 4, (float) self.data[7]);
        _U.putFloat(bytes, _p3 + 8, (float) self.data[11]);
        return self;
    }

    public Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4);
        self.data[8] = _U.getFloat(address + 8);
        self.data[1] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[9] = _U.getFloat(_p1 + 8);
        self.data[2] = _U.getFloat(_p2);
        self.data[6] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[3] = _U.getFloat(_p3);
        self.data[7] = _U.getFloat(_p3 + 4);
        self.data[11] = _U.getFloat(_p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadCMFloatHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4);
        self.data[8] = _U.getFloat(bytes, address + 8);
        self.data[1] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[9] = _U.getFloat(bytes, _p1 + 8);
        self.data[2] = _U.getFloat(bytes, _p2);
        self.data[6] = _U.getFloat(bytes, _p2 + 4);
        self.data[10] = _U.getFloat(bytes, _p2 + 8);
        self.data[3] = _U.getFloat(bytes, _p3);
        self.data[7] = _U.getFloat(bytes, _p3 + 4);
        self.data[11] = _U.getFloat(bytes, _p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeRMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(address + 24, self.data[3]);
        _U.putDouble(_p1, self.data[4]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[6]);
        _U.putDouble(_p1 + 24, self.data[7]);
        _U.putDouble(_p2, self.data[8]);
        _U.putDouble(_p2 + 8, self.data[9]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p2 + 24, self.data[11]);
        return self;
    }

    public Double3x4 storeRMHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[1]);
        _U.putDouble(bytes, address + 16, self.data[2]);
        _U.putDouble(bytes, address + 24, self.data[3]);
        _U.putDouble(bytes, _p1, self.data[4]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p1 + 16, self.data[6]);
        _U.putDouble(bytes, _p1 + 24, self.data[7]);
        _U.putDouble(bytes, _p2, self.data[8]);
        _U.putDouble(bytes, _p2 + 8, self.data[9]);
        _U.putDouble(bytes, _p2 + 16, self.data[10]);
        _U.putDouble(bytes, _p2 + 24, self.data[11]);
        return self;
    }

    public Double3x4 loadRMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8);
        self.data[2] = _U.getDouble(address + 16);
        self.data[3] = _U.getDouble(address + 24);
        self.data[4] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[6] = _U.getDouble(_p1 + 16);
        self.data[7] = _U.getDouble(_p1 + 24);
        self.data[8] = _U.getDouble(_p2);
        self.data[9] = _U.getDouble(_p2 + 8);
        self.data[10] = _U.getDouble(_p2 + 16);
        self.data[11] = _U.getDouble(_p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadRMHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8);
        self.data[2] = _U.getDouble(bytes, address + 16);
        self.data[3] = _U.getDouble(bytes, address + 24);
        self.data[4] = _U.getDouble(bytes, _p1);
        self.data[5] = _U.getDouble(bytes, _p1 + 8);
        self.data[6] = _U.getDouble(bytes, _p1 + 16);
        self.data[7] = _U.getDouble(bytes, _p1 + 24);
        self.data[8] = _U.getDouble(bytes, _p2);
        self.data[9] = _U.getDouble(bytes, _p2 + 8);
        self.data[10] = _U.getDouble(bytes, _p2 + 16);
        self.data[11] = _U.getDouble(bytes, _p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[1]);
        _U.putFloat(address + 8, (float) self.data[2]);
        _U.putFloat(address + 12, (float) self.data[3]);
        _U.putFloat(_p1, (float) self.data[4]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p1 + 8, (float) self.data[6]);
        _U.putFloat(_p1 + 12, (float) self.data[7]);
        _U.putFloat(_p2, (float) self.data[8]);
        _U.putFloat(_p2 + 4, (float) self.data[9]);
        _U.putFloat(_p2 + 8, (float) self.data[10]);
        _U.putFloat(_p2 + 12, (float) self.data[11]);
        return self;
    }

    public Double3x4 storeRMFloatHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4, (float) self.data[1]);
        _U.putFloat(bytes, address + 8, (float) self.data[2]);
        _U.putFloat(bytes, address + 12, (float) self.data[3]);
        _U.putFloat(bytes, _p1, (float) self.data[4]);
        _U.putFloat(bytes, _p1 + 4, (float) self.data[5]);
        _U.putFloat(bytes, _p1 + 8, (float) self.data[6]);
        _U.putFloat(bytes, _p1 + 12, (float) self.data[7]);
        _U.putFloat(bytes, _p2, (float) self.data[8]);
        _U.putFloat(bytes, _p2 + 4, (float) self.data[9]);
        _U.putFloat(bytes, _p2 + 8, (float) self.data[10]);
        _U.putFloat(bytes, _p2 + 12, (float) self.data[11]);
        return self;
    }

    public Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(address + 12);
        self.data[4] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[6] = _U.getFloat(_p1 + 8);
        self.data[7] = _U.getFloat(_p1 + 12);
        self.data[8] = _U.getFloat(_p2);
        self.data[9] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[11] = _U.getFloat(_p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 loadRMFloatHeap(Double3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4);
        self.data[2] = _U.getFloat(bytes, address + 8);
        self.data[3] = _U.getFloat(bytes, address + 12);
        self.data[4] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[6] = _U.getFloat(bytes, _p1 + 8);
        self.data[7] = _U.getFloat(bytes, _p1 + 12);
        self.data[8] = _U.getFloat(bytes, _p2);
        self.data[9] = _U.getFloat(bytes, _p2 + 4);
        self.data[10] = _U.getFloat(bytes, _p2 + 8);
        self.data[11] = _U.getFloat(bytes, _p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Double3x4 storeCM4x4Unsafe(Double3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[8]);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.data[1]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[9]);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.data[2]);
        _U.putDouble(address + 72L, self.data[6]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, self.data[3]);
        _U.putDouble(address + 104L, self.data[7]);
        _U.putDouble(address + 112L, self.data[11]);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Double3x4 storeCM4x4Heap(Double3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[8]);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.data[1]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[9]);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, self.data[2]);
        _U.putDouble(bytes, address + 72L, self.data[6]);
        _U.putDouble(bytes, address + 80L, self.data[10]);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, self.data[3]);
        _U.putDouble(bytes, address + 104L, self.data[7]);
        _U.putDouble(bytes, address + 112L, self.data[11]);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Double3x4 storeCM4x4FloatUnsafe(Double3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[4]);
        _U.putFloat(address + 8L, (float) self.data[8]);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.data[1]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        _U.putFloat(address + 24L, (float) self.data[9]);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, (float) self.data[2]);
        _U.putFloat(address + 36L, (float) self.data[6]);
        _U.putFloat(address + 40L, (float) self.data[10]);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, (float) self.data[3]);
        _U.putFloat(address + 52L, (float) self.data[7]);
        _U.putFloat(address + 56L, (float) self.data[11]);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Double3x4 storeCM4x4FloatHeap(Double3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[4]);
        _U.putFloat(bytes, address + 8L, (float) self.data[8]);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.data[1]);
        _U.putFloat(bytes, address + 20L, (float) self.data[5]);
        _U.putFloat(bytes, address + 24L, (float) self.data[9]);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, (float) self.data[2]);
        _U.putFloat(bytes, address + 36L, (float) self.data[6]);
        _U.putFloat(bytes, address + 40L, (float) self.data[10]);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, (float) self.data[3]);
        _U.putFloat(bytes, address + 52L, (float) self.data[7]);
        _U.putFloat(bytes, address + 56L, (float) self.data[11]);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }

    public Double3x4 storeRM4x4Unsafe(Double3x4Impl self, long address) {
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
        _U.putDouble(address + 72L, self.data[9]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, self.data[11]);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Double3x4 storeRM4x4Heap(Double3x4Impl self, byte[] bytes, long address) {
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
        _U.putDouble(bytes, address + 72L, self.data[9]);
        _U.putDouble(bytes, address + 80L, self.data[10]);
        _U.putDouble(bytes, address + 88L, self.data[11]);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Double3x4 storeRM4x4FloatUnsafe(Double3x4Impl self, long address) {
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
        _U.putFloat(address + 36L, (float) self.data[9]);
        _U.putFloat(address + 40L, (float) self.data[10]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Double3x4 storeRM4x4FloatHeap(Double3x4Impl self, byte[] bytes, long address) {
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
        _U.putFloat(bytes, address + 36L, (float) self.data[9]);
        _U.putFloat(bytes, address + 40L, (float) self.data[10]);
        _U.putFloat(bytes, address + 44L, (float) self.data[11]);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }
}
