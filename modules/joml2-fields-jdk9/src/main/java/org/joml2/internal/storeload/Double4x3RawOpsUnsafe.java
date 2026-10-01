// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x3RawOpsUnsafe extends Double4x3RawOps {
    public Double4x3 storeCMUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m30);
        _U.putDouble(address + 32L, self.m01);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m21);
        _U.putDouble(address + 56L, self.m31);
        _U.putDouble(address + 64L, self.m02);
        _U.putDouble(address + 72L, self.m12);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, self.m32);
        return self;
    }

    public Double4x3 storeCMHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m20);
        _U.putDouble(bytes, address + 24L, self.m30);
        _U.putDouble(bytes, address + 32L, self.m01);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, self.m21);
        _U.putDouble(bytes, address + 56L, self.m31);
        _U.putDouble(bytes, address + 64L, self.m02);
        _U.putDouble(bytes, address + 72L, self.m12);
        _U.putDouble(bytes, address + 80L, self.m22);
        _U.putDouble(bytes, address + 88L, self.m32);
        return self;
    }

    public Double4x3 loadCMUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8L);
        self.m20 = _U.getDouble(address + 16L);
        self.m30 = _U.getDouble(address + 24L);
        self.m01 = _U.getDouble(address + 32L);
        self.m11 = _U.getDouble(address + 40L);
        self.m21 = _U.getDouble(address + 48L);
        self.m31 = _U.getDouble(address + 56L);
        self.m02 = _U.getDouble(address + 64L);
        self.m12 = _U.getDouble(address + 72L);
        self.m22 = _U.getDouble(address + 80L);
        self.m32 = _U.getDouble(address + 88L);
        return self;
    }

    public Double4x3 loadCMHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8L);
        self.m20 = _U.getDouble(bytes, address + 16L);
        self.m30 = _U.getDouble(bytes, address + 24L);
        self.m01 = _U.getDouble(bytes, address + 32L);
        self.m11 = _U.getDouble(bytes, address + 40L);
        self.m21 = _U.getDouble(bytes, address + 48L);
        self.m31 = _U.getDouble(bytes, address + 56L);
        self.m02 = _U.getDouble(bytes, address + 64L);
        self.m12 = _U.getDouble(bytes, address + 72L);
        self.m22 = _U.getDouble(bytes, address + 80L);
        self.m32 = _U.getDouble(bytes, address + 88L);
        return self;
    }

    public Double4x3 storeCMFloatUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, (float) self.m20);
        _U.putFloat(address + 12L, (float) self.m30);
        _U.putFloat(address + 16L, (float) self.m01);
        _U.putFloat(address + 20L, (float) self.m11);
        _U.putFloat(address + 24L, (float) self.m21);
        _U.putFloat(address + 28L, (float) self.m31);
        _U.putFloat(address + 32L, (float) self.m02);
        _U.putFloat(address + 36L, (float) self.m12);
        _U.putFloat(address + 40L, (float) self.m22);
        _U.putFloat(address + 44L, (float) self.m32);
        return self;
    }

    public Double4x3 storeCMFloatHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, (float) self.m20);
        _U.putFloat(bytes, address + 12L, (float) self.m30);
        _U.putFloat(bytes, address + 16L, (float) self.m01);
        _U.putFloat(bytes, address + 20L, (float) self.m11);
        _U.putFloat(bytes, address + 24L, (float) self.m21);
        _U.putFloat(bytes, address + 28L, (float) self.m31);
        _U.putFloat(bytes, address + 32L, (float) self.m02);
        _U.putFloat(bytes, address + 36L, (float) self.m12);
        _U.putFloat(bytes, address + 40L, (float) self.m22);
        _U.putFloat(bytes, address + 44L, (float) self.m32);
        return self;
    }

    public Double4x3 loadCMFloatUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m30 = _U.getFloat(address + 12L);
        self.m01 = _U.getFloat(address + 16L);
        self.m11 = _U.getFloat(address + 20L);
        self.m21 = _U.getFloat(address + 24L);
        self.m31 = _U.getFloat(address + 28L);
        self.m02 = _U.getFloat(address + 32L);
        self.m12 = _U.getFloat(address + 36L);
        self.m22 = _U.getFloat(address + 40L);
        self.m32 = _U.getFloat(address + 44L);
        return self;
    }

    public Double4x3 loadCMFloatHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m20 = _U.getFloat(bytes, address + 8L);
        self.m30 = _U.getFloat(bytes, address + 12L);
        self.m01 = _U.getFloat(bytes, address + 16L);
        self.m11 = _U.getFloat(bytes, address + 20L);
        self.m21 = _U.getFloat(bytes, address + 24L);
        self.m31 = _U.getFloat(bytes, address + 28L);
        self.m02 = _U.getFloat(bytes, address + 32L);
        self.m12 = _U.getFloat(bytes, address + 36L);
        self.m22 = _U.getFloat(bytes, address + 40L);
        self.m32 = _U.getFloat(bytes, address + 44L);
        return self;
    }

    public Double4x3 storeRMUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m12);
        _U.putDouble(address + 48L, self.m20);
        _U.putDouble(address + 56L, self.m21);
        _U.putDouble(address + 64L, self.m22);
        _U.putDouble(address + 72L, self.m30);
        _U.putDouble(address + 80L, self.m31);
        _U.putDouble(address + 88L, self.m32);
        return self;
    }

    public Double4x3 storeRMHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, self.m10);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m12);
        _U.putDouble(bytes, address + 48L, self.m20);
        _U.putDouble(bytes, address + 56L, self.m21);
        _U.putDouble(bytes, address + 64L, self.m22);
        _U.putDouble(bytes, address + 72L, self.m30);
        _U.putDouble(bytes, address + 80L, self.m31);
        _U.putDouble(bytes, address + 88L, self.m32);
        return self;
    }

    public Double4x3 loadRMUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8L);
        self.m02 = _U.getDouble(address + 16L);
        self.m10 = _U.getDouble(address + 24L);
        self.m11 = _U.getDouble(address + 32L);
        self.m12 = _U.getDouble(address + 40L);
        self.m20 = _U.getDouble(address + 48L);
        self.m21 = _U.getDouble(address + 56L);
        self.m22 = _U.getDouble(address + 64L);
        self.m30 = _U.getDouble(address + 72L);
        self.m31 = _U.getDouble(address + 80L);
        self.m32 = _U.getDouble(address + 88L);
        return self;
    }

    public Double4x3 loadRMHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8L);
        self.m02 = _U.getDouble(bytes, address + 16L);
        self.m10 = _U.getDouble(bytes, address + 24L);
        self.m11 = _U.getDouble(bytes, address + 32L);
        self.m12 = _U.getDouble(bytes, address + 40L);
        self.m20 = _U.getDouble(bytes, address + 48L);
        self.m21 = _U.getDouble(bytes, address + 56L);
        self.m22 = _U.getDouble(bytes, address + 64L);
        self.m30 = _U.getDouble(bytes, address + 72L);
        self.m31 = _U.getDouble(bytes, address + 80L);
        self.m32 = _U.getDouble(bytes, address + 88L);
        return self;
    }

    public Double4x3 storeRMFloatUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, (float) self.m02);
        _U.putFloat(address + 12L, (float) self.m10);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, (float) self.m12);
        _U.putFloat(address + 24L, (float) self.m20);
        _U.putFloat(address + 28L, (float) self.m21);
        _U.putFloat(address + 32L, (float) self.m22);
        _U.putFloat(address + 36L, (float) self.m30);
        _U.putFloat(address + 40L, (float) self.m31);
        _U.putFloat(address + 44L, (float) self.m32);
        return self;
    }

    public Double4x3 storeRMFloatHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, (float) self.m02);
        _U.putFloat(bytes, address + 12L, (float) self.m10);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, (float) self.m12);
        _U.putFloat(bytes, address + 24L, (float) self.m20);
        _U.putFloat(bytes, address + 28L, (float) self.m21);
        _U.putFloat(bytes, address + 32L, (float) self.m22);
        _U.putFloat(bytes, address + 36L, (float) self.m30);
        _U.putFloat(bytes, address + 40L, (float) self.m31);
        _U.putFloat(bytes, address + 44L, (float) self.m32);
        return self;
    }

    public Double4x3 loadRMFloatUnsafe(Double4x3Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m10 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m12 = _U.getFloat(address + 20L);
        self.m20 = _U.getFloat(address + 24L);
        self.m21 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.m30 = _U.getFloat(address + 36L);
        self.m31 = _U.getFloat(address + 40L);
        self.m32 = _U.getFloat(address + 44L);
        return self;
    }

    public Double4x3 loadRMFloatHeap(Double4x3Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m02 = _U.getFloat(bytes, address + 8L);
        self.m10 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m12 = _U.getFloat(bytes, address + 20L);
        self.m20 = _U.getFloat(bytes, address + 24L);
        self.m21 = _U.getFloat(bytes, address + 28L);
        self.m22 = _U.getFloat(bytes, address + 32L);
        self.m30 = _U.getFloat(bytes, address + 36L);
        self.m31 = _U.getFloat(bytes, address + 40L);
        self.m32 = _U.getFloat(bytes, address + 44L);
        return self;
    }

    public Double4x3 storeCMUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(address + 24, self.m30);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        _U.putDouble(_p1 + 24, self.m31);
        _U.putDouble(_p2, self.m02);
        _U.putDouble(_p2 + 8, self.m12);
        _U.putDouble(_p2 + 16, self.m22);
        _U.putDouble(_p2 + 24, self.m32);
        return self;
    }

    public Double4x3 storeCMHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, address + 16, self.m20);
        _U.putDouble(bytes, address + 24, self.m30);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m21);
        _U.putDouble(bytes, _p1 + 24, self.m31);
        _U.putDouble(bytes, _p2, self.m02);
        _U.putDouble(bytes, _p2 + 8, self.m12);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        _U.putDouble(bytes, _p2 + 24, self.m32);
        return self;
    }

    public Double4x3 loadCMUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8);
        self.m20 = _U.getDouble(address + 16);
        self.m30 = _U.getDouble(address + 24);
        self.m01 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m21 = _U.getDouble(_p1 + 16);
        self.m31 = _U.getDouble(_p1 + 24);
        self.m02 = _U.getDouble(_p2);
        self.m12 = _U.getDouble(_p2 + 8);
        self.m22 = _U.getDouble(_p2 + 16);
        self.m32 = _U.getDouble(_p2 + 24);
        return self;
    }

    public Double4x3 loadCMHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8);
        self.m20 = _U.getDouble(bytes, address + 16);
        self.m30 = _U.getDouble(bytes, address + 24);
        self.m01 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m21 = _U.getDouble(bytes, _p1 + 16);
        self.m31 = _U.getDouble(bytes, _p1 + 24);
        self.m02 = _U.getDouble(bytes, _p2);
        self.m12 = _U.getDouble(bytes, _p2 + 8);
        self.m22 = _U.getDouble(bytes, _p2 + 16);
        self.m32 = _U.getDouble(bytes, _p2 + 24);
        return self;
    }

    public Double4x3 storeCMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m10);
        _U.putFloat(address + 8, (float) self.m20);
        _U.putFloat(address + 12, (float) self.m30);
        _U.putFloat(_p1, (float) self.m01);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p1 + 8, (float) self.m21);
        _U.putFloat(_p1 + 12, (float) self.m31);
        _U.putFloat(_p2, (float) self.m02);
        _U.putFloat(_p2 + 4, (float) self.m12);
        _U.putFloat(_p2 + 8, (float) self.m22);
        _U.putFloat(_p2 + 12, (float) self.m32);
        return self;
    }

    public Double4x3 storeCMFloatHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m10);
        _U.putFloat(bytes, address + 8, (float) self.m20);
        _U.putFloat(bytes, address + 12, (float) self.m30);
        _U.putFloat(bytes, _p1, (float) self.m01);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p1 + 8, (float) self.m21);
        _U.putFloat(bytes, _p1 + 12, (float) self.m31);
        _U.putFloat(bytes, _p2, (float) self.m02);
        _U.putFloat(bytes, _p2 + 4, (float) self.m12);
        _U.putFloat(bytes, _p2 + 8, (float) self.m22);
        _U.putFloat(bytes, _p2 + 12, (float) self.m32);
        return self;
    }

    public Double4x3 loadCMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m30 = _U.getFloat(address + 12);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        self.m31 = _U.getFloat(_p1 + 12);
        self.m02 = _U.getFloat(_p2);
        self.m12 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.m32 = _U.getFloat(_p2 + 12);
        return self;
    }

    public Double4x3 loadCMFloatHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m20 = _U.getFloat(bytes, address + 8);
        self.m30 = _U.getFloat(bytes, address + 12);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m21 = _U.getFloat(bytes, _p1 + 8);
        self.m31 = _U.getFloat(bytes, _p1 + 12);
        self.m02 = _U.getFloat(bytes, _p2);
        self.m12 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.m32 = _U.getFloat(bytes, _p2 + 12);
        return self;
    }

    public Double4x3 storeRMUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(address + 16, self.m02);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m12);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        _U.putDouble(_p2 + 16, self.m22);
        _U.putDouble(_p3, self.m30);
        _U.putDouble(_p3 + 8, self.m31);
        _U.putDouble(_p3 + 16, self.m32);
        return self;
    }

    public Double4x3 storeRMHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, address + 16, self.m02);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m12);
        _U.putDouble(bytes, _p2, self.m20);
        _U.putDouble(bytes, _p2 + 8, self.m21);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        _U.putDouble(bytes, _p3, self.m30);
        _U.putDouble(bytes, _p3 + 8, self.m31);
        _U.putDouble(bytes, _p3 + 16, self.m32);
        return self;
    }

    public Double4x3 loadRMUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8);
        self.m02 = _U.getDouble(address + 16);
        self.m10 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m12 = _U.getDouble(_p1 + 16);
        self.m20 = _U.getDouble(_p2);
        self.m21 = _U.getDouble(_p2 + 8);
        self.m22 = _U.getDouble(_p2 + 16);
        self.m30 = _U.getDouble(_p3);
        self.m31 = _U.getDouble(_p3 + 8);
        self.m32 = _U.getDouble(_p3 + 16);
        return self;
    }

    public Double4x3 loadRMHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8);
        self.m02 = _U.getDouble(bytes, address + 16);
        self.m10 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m12 = _U.getDouble(bytes, _p1 + 16);
        self.m20 = _U.getDouble(bytes, _p2);
        self.m21 = _U.getDouble(bytes, _p2 + 8);
        self.m22 = _U.getDouble(bytes, _p2 + 16);
        self.m30 = _U.getDouble(bytes, _p3);
        self.m31 = _U.getDouble(bytes, _p3 + 8);
        self.m32 = _U.getDouble(bytes, _p3 + 16);
        return self;
    }

    public Double4x3 storeRMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m01);
        _U.putFloat(address + 8, (float) self.m02);
        _U.putFloat(_p1, (float) self.m10);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p1 + 8, (float) self.m12);
        _U.putFloat(_p2, (float) self.m20);
        _U.putFloat(_p2 + 4, (float) self.m21);
        _U.putFloat(_p2 + 8, (float) self.m22);
        _U.putFloat(_p3, (float) self.m30);
        _U.putFloat(_p3 + 4, (float) self.m31);
        _U.putFloat(_p3 + 8, (float) self.m32);
        return self;
    }

    public Double4x3 storeRMFloatHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m01);
        _U.putFloat(bytes, address + 8, (float) self.m02);
        _U.putFloat(bytes, _p1, (float) self.m10);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p1 + 8, (float) self.m12);
        _U.putFloat(bytes, _p2, (float) self.m20);
        _U.putFloat(bytes, _p2 + 4, (float) self.m21);
        _U.putFloat(bytes, _p2 + 8, (float) self.m22);
        _U.putFloat(bytes, _p3, (float) self.m30);
        _U.putFloat(bytes, _p3 + 4, (float) self.m31);
        _U.putFloat(bytes, _p3 + 8, (float) self.m32);
        return self;
    }

    public Double4x3 loadRMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m02 = _U.getFloat(address + 8);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m12 = _U.getFloat(_p1 + 8);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.m30 = _U.getFloat(_p3);
        self.m31 = _U.getFloat(_p3 + 4);
        self.m32 = _U.getFloat(_p3 + 8);
        return self;
    }

    public Double4x3 loadRMFloatHeap(Double4x3Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m02 = _U.getFloat(bytes, address + 8);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m12 = _U.getFloat(bytes, _p1 + 8);
        self.m20 = _U.getFloat(bytes, _p2);
        self.m21 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.m30 = _U.getFloat(bytes, _p3);
        self.m31 = _U.getFloat(bytes, _p3 + 4);
        self.m32 = _U.getFloat(bytes, _p3 + 8);
        return self;
    }
}
