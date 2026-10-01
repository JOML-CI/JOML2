// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4RawOpsUnsafe extends Float4x4RawOps {
    public Float4x4 storeCMUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, self.m30);
        _U.putFloat(address + 16L, self.m01);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m21);
        _U.putFloat(address + 28L, self.m31);
        _U.putFloat(address + 32L, self.m02);
        _U.putFloat(address + 36L, self.m12);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, self.m32);
        _U.putFloat(address + 48L, self.m03);
        _U.putFloat(address + 52L, self.m13);
        _U.putFloat(address + 56L, self.m23);
        _U.putFloat(address + 60L, self.m33);
        return self;
    }

    public Float4x4 storeCMHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, self.m20);
        _U.putFloat(bytes, address + 12L, self.m30);
        _U.putFloat(bytes, address + 16L, self.m01);
        _U.putFloat(bytes, address + 20L, self.m11);
        _U.putFloat(bytes, address + 24L, self.m21);
        _U.putFloat(bytes, address + 28L, self.m31);
        _U.putFloat(bytes, address + 32L, self.m02);
        _U.putFloat(bytes, address + 36L, self.m12);
        _U.putFloat(bytes, address + 40L, self.m22);
        _U.putFloat(bytes, address + 44L, self.m32);
        _U.putFloat(bytes, address + 48L, self.m03);
        _U.putFloat(bytes, address + 52L, self.m13);
        _U.putFloat(bytes, address + 56L, self.m23);
        _U.putFloat(bytes, address + 60L, self.m33);
        return self;
    }

    public Float4x4 loadCMUnsafe(Float4x4Impl self, long address) {
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
        self.m03 = _U.getFloat(address + 48L);
        self.m13 = _U.getFloat(address + 52L);
        self.m23 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadCMHeap(Float4x4Impl self, byte[] bytes, long address) {
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
        self.m03 = _U.getFloat(bytes, address + 48L);
        self.m13 = _U.getFloat(bytes, address + 52L);
        self.m23 = _U.getFloat(bytes, address + 56L);
        self.m33 = _U.getFloat(bytes, address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeCMDoubleUnsafe(Float4x4Impl self, long address) {
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
        _U.putDouble(address + 96L, self.m03);
        _U.putDouble(address + 104L, self.m13);
        _U.putDouble(address + 112L, self.m23);
        _U.putDouble(address + 120L, self.m33);
        return self;
    }

    public Float4x4 storeCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
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
        _U.putDouble(bytes, address + 96L, self.m03);
        _U.putDouble(bytes, address + 104L, self.m13);
        _U.putDouble(bytes, address + 112L, self.m23);
        _U.putDouble(bytes, address + 120L, self.m33);
        return self;
    }

    public Float4x4 loadCMDoubleUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8L);
        self.m20 = (float) _U.getDouble(address + 16L);
        self.m30 = (float) _U.getDouble(address + 24L);
        self.m01 = (float) _U.getDouble(address + 32L);
        self.m11 = (float) _U.getDouble(address + 40L);
        self.m21 = (float) _U.getDouble(address + 48L);
        self.m31 = (float) _U.getDouble(address + 56L);
        self.m02 = (float) _U.getDouble(address + 64L);
        self.m12 = (float) _U.getDouble(address + 72L);
        self.m22 = (float) _U.getDouble(address + 80L);
        self.m32 = (float) _U.getDouble(address + 88L);
        self.m03 = (float) _U.getDouble(address + 96L);
        self.m13 = (float) _U.getDouble(address + 104L);
        self.m23 = (float) _U.getDouble(address + 112L);
        self.m33 = (float) _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8L);
        self.m20 = (float) _U.getDouble(bytes, address + 16L);
        self.m30 = (float) _U.getDouble(bytes, address + 24L);
        self.m01 = (float) _U.getDouble(bytes, address + 32L);
        self.m11 = (float) _U.getDouble(bytes, address + 40L);
        self.m21 = (float) _U.getDouble(bytes, address + 48L);
        self.m31 = (float) _U.getDouble(bytes, address + 56L);
        self.m02 = (float) _U.getDouble(bytes, address + 64L);
        self.m12 = (float) _U.getDouble(bytes, address + 72L);
        self.m22 = (float) _U.getDouble(bytes, address + 80L);
        self.m32 = (float) _U.getDouble(bytes, address + 88L);
        self.m03 = (float) _U.getDouble(bytes, address + 96L);
        self.m13 = (float) _U.getDouble(bytes, address + 104L);
        self.m23 = (float) _U.getDouble(bytes, address + 112L);
        self.m33 = (float) _U.getDouble(bytes, address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeRMUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, self.m03);
        _U.putFloat(address + 16L, self.m10);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m12);
        _U.putFloat(address + 28L, self.m13);
        _U.putFloat(address + 32L, self.m20);
        _U.putFloat(address + 36L, self.m21);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, self.m23);
        _U.putFloat(address + 48L, self.m30);
        _U.putFloat(address + 52L, self.m31);
        _U.putFloat(address + 56L, self.m32);
        _U.putFloat(address + 60L, self.m33);
        return self;
    }

    public Float4x4 storeRMHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m01);
        _U.putFloat(bytes, address + 8L, self.m02);
        _U.putFloat(bytes, address + 12L, self.m03);
        _U.putFloat(bytes, address + 16L, self.m10);
        _U.putFloat(bytes, address + 20L, self.m11);
        _U.putFloat(bytes, address + 24L, self.m12);
        _U.putFloat(bytes, address + 28L, self.m13);
        _U.putFloat(bytes, address + 32L, self.m20);
        _U.putFloat(bytes, address + 36L, self.m21);
        _U.putFloat(bytes, address + 40L, self.m22);
        _U.putFloat(bytes, address + 44L, self.m23);
        _U.putFloat(bytes, address + 48L, self.m30);
        _U.putFloat(bytes, address + 52L, self.m31);
        _U.putFloat(bytes, address + 56L, self.m32);
        _U.putFloat(bytes, address + 60L, self.m33);
        return self;
    }

    public Float4x4 loadRMUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m03 = _U.getFloat(address + 12L);
        self.m10 = _U.getFloat(address + 16L);
        self.m11 = _U.getFloat(address + 20L);
        self.m12 = _U.getFloat(address + 24L);
        self.m13 = _U.getFloat(address + 28L);
        self.m20 = _U.getFloat(address + 32L);
        self.m21 = _U.getFloat(address + 36L);
        self.m22 = _U.getFloat(address + 40L);
        self.m23 = _U.getFloat(address + 44L);
        self.m30 = _U.getFloat(address + 48L);
        self.m31 = _U.getFloat(address + 52L);
        self.m32 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadRMHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m02 = _U.getFloat(bytes, address + 8L);
        self.m03 = _U.getFloat(bytes, address + 12L);
        self.m10 = _U.getFloat(bytes, address + 16L);
        self.m11 = _U.getFloat(bytes, address + 20L);
        self.m12 = _U.getFloat(bytes, address + 24L);
        self.m13 = _U.getFloat(bytes, address + 28L);
        self.m20 = _U.getFloat(bytes, address + 32L);
        self.m21 = _U.getFloat(bytes, address + 36L);
        self.m22 = _U.getFloat(bytes, address + 40L);
        self.m23 = _U.getFloat(bytes, address + 44L);
        self.m30 = _U.getFloat(bytes, address + 48L);
        self.m31 = _U.getFloat(bytes, address + 52L);
        self.m32 = _U.getFloat(bytes, address + 56L);
        self.m33 = _U.getFloat(bytes, address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeRMDoubleUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m03);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m12);
        _U.putDouble(address + 56L, self.m13);
        _U.putDouble(address + 64L, self.m20);
        _U.putDouble(address + 72L, self.m21);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, self.m23);
        _U.putDouble(address + 96L, self.m30);
        _U.putDouble(address + 104L, self.m31);
        _U.putDouble(address + 112L, self.m32);
        _U.putDouble(address + 120L, self.m33);
        return self;
    }

    public Float4x4 storeRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, self.m03);
        _U.putDouble(bytes, address + 32L, self.m10);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, self.m12);
        _U.putDouble(bytes, address + 56L, self.m13);
        _U.putDouble(bytes, address + 64L, self.m20);
        _U.putDouble(bytes, address + 72L, self.m21);
        _U.putDouble(bytes, address + 80L, self.m22);
        _U.putDouble(bytes, address + 88L, self.m23);
        _U.putDouble(bytes, address + 96L, self.m30);
        _U.putDouble(bytes, address + 104L, self.m31);
        _U.putDouble(bytes, address + 112L, self.m32);
        _U.putDouble(bytes, address + 120L, self.m33);
        return self;
    }

    public Float4x4 loadRMDoubleUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8L);
        self.m02 = (float) _U.getDouble(address + 16L);
        self.m03 = (float) _U.getDouble(address + 24L);
        self.m10 = (float) _U.getDouble(address + 32L);
        self.m11 = (float) _U.getDouble(address + 40L);
        self.m12 = (float) _U.getDouble(address + 48L);
        self.m13 = (float) _U.getDouble(address + 56L);
        self.m20 = (float) _U.getDouble(address + 64L);
        self.m21 = (float) _U.getDouble(address + 72L);
        self.m22 = (float) _U.getDouble(address + 80L);
        self.m23 = (float) _U.getDouble(address + 88L);
        self.m30 = (float) _U.getDouble(address + 96L);
        self.m31 = (float) _U.getDouble(address + 104L);
        self.m32 = (float) _U.getDouble(address + 112L);
        self.m33 = (float) _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8L);
        self.m02 = (float) _U.getDouble(bytes, address + 16L);
        self.m03 = (float) _U.getDouble(bytes, address + 24L);
        self.m10 = (float) _U.getDouble(bytes, address + 32L);
        self.m11 = (float) _U.getDouble(bytes, address + 40L);
        self.m12 = (float) _U.getDouble(bytes, address + 48L);
        self.m13 = (float) _U.getDouble(bytes, address + 56L);
        self.m20 = (float) _U.getDouble(bytes, address + 64L);
        self.m21 = (float) _U.getDouble(bytes, address + 72L);
        self.m22 = (float) _U.getDouble(bytes, address + 80L);
        self.m23 = (float) _U.getDouble(bytes, address + 88L);
        self.m30 = (float) _U.getDouble(bytes, address + 96L);
        self.m31 = (float) _U.getDouble(bytes, address + 104L);
        self.m32 = (float) _U.getDouble(bytes, address + 112L);
        self.m33 = (float) _U.getDouble(bytes, address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeCMUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m10);
        _U.putFloat(address + 8, self.m20);
        _U.putFloat(address + 12, self.m30);
        _U.putFloat(_p1, self.m01);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m21);
        _U.putFloat(_p1 + 12, self.m31);
        _U.putFloat(_p2, self.m02);
        _U.putFloat(_p2 + 4, self.m12);
        _U.putFloat(_p2 + 8, self.m22);
        _U.putFloat(_p2 + 12, self.m32);
        _U.putFloat(_p3, self.m03);
        _U.putFloat(_p3 + 4, self.m13);
        _U.putFloat(_p3 + 8, self.m23);
        _U.putFloat(_p3 + 12, self.m33);
        return self;
    }

    public Float4x4 storeCMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m10);
        _U.putFloat(bytes, address + 8, self.m20);
        _U.putFloat(bytes, address + 12, self.m30);
        _U.putFloat(bytes, _p1, self.m01);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p1 + 8, self.m21);
        _U.putFloat(bytes, _p1 + 12, self.m31);
        _U.putFloat(bytes, _p2, self.m02);
        _U.putFloat(bytes, _p2 + 4, self.m12);
        _U.putFloat(bytes, _p2 + 8, self.m22);
        _U.putFloat(bytes, _p2 + 12, self.m32);
        _U.putFloat(bytes, _p3, self.m03);
        _U.putFloat(bytes, _p3 + 4, self.m13);
        _U.putFloat(bytes, _p3 + 8, self.m23);
        _U.putFloat(bytes, _p3 + 12, self.m33);
        return self;
    }

    public Float4x4 loadCMUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        self.m03 = _U.getFloat(_p3);
        self.m13 = _U.getFloat(_p3 + 4);
        self.m23 = _U.getFloat(_p3 + 8);
        self.m33 = _U.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadCMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        self.m03 = _U.getFloat(bytes, _p3);
        self.m13 = _U.getFloat(bytes, _p3 + 4);
        self.m23 = _U.getFloat(bytes, _p3 + 8);
        self.m33 = _U.getFloat(bytes, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeCMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        _U.putDouble(_p3, self.m03);
        _U.putDouble(_p3 + 8, self.m13);
        _U.putDouble(_p3 + 16, self.m23);
        _U.putDouble(_p3 + 24, self.m33);
        return self;
    }

    public Float4x4 storeCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        _U.putDouble(bytes, _p3, self.m03);
        _U.putDouble(bytes, _p3 + 8, self.m13);
        _U.putDouble(bytes, _p3 + 16, self.m23);
        _U.putDouble(bytes, _p3 + 24, self.m33);
        return self;
    }

    public Float4x4 loadCMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8);
        self.m20 = (float) _U.getDouble(address + 16);
        self.m30 = (float) _U.getDouble(address + 24);
        self.m01 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m21 = (float) _U.getDouble(_p1 + 16);
        self.m31 = (float) _U.getDouble(_p1 + 24);
        self.m02 = (float) _U.getDouble(_p2);
        self.m12 = (float) _U.getDouble(_p2 + 8);
        self.m22 = (float) _U.getDouble(_p2 + 16);
        self.m32 = (float) _U.getDouble(_p2 + 24);
        self.m03 = (float) _U.getDouble(_p3);
        self.m13 = (float) _U.getDouble(_p3 + 8);
        self.m23 = (float) _U.getDouble(_p3 + 16);
        self.m33 = (float) _U.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8);
        self.m20 = (float) _U.getDouble(bytes, address + 16);
        self.m30 = (float) _U.getDouble(bytes, address + 24);
        self.m01 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m21 = (float) _U.getDouble(bytes, _p1 + 16);
        self.m31 = (float) _U.getDouble(bytes, _p1 + 24);
        self.m02 = (float) _U.getDouble(bytes, _p2);
        self.m12 = (float) _U.getDouble(bytes, _p2 + 8);
        self.m22 = (float) _U.getDouble(bytes, _p2 + 16);
        self.m32 = (float) _U.getDouble(bytes, _p2 + 24);
        self.m03 = (float) _U.getDouble(bytes, _p3);
        self.m13 = (float) _U.getDouble(bytes, _p3 + 8);
        self.m23 = (float) _U.getDouble(bytes, _p3 + 16);
        self.m33 = (float) _U.getDouble(bytes, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeRMUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m01);
        _U.putFloat(address + 8, self.m02);
        _U.putFloat(address + 12, self.m03);
        _U.putFloat(_p1, self.m10);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m12);
        _U.putFloat(_p1 + 12, self.m13);
        _U.putFloat(_p2, self.m20);
        _U.putFloat(_p2 + 4, self.m21);
        _U.putFloat(_p2 + 8, self.m22);
        _U.putFloat(_p2 + 12, self.m23);
        _U.putFloat(_p3, self.m30);
        _U.putFloat(_p3 + 4, self.m31);
        _U.putFloat(_p3 + 8, self.m32);
        _U.putFloat(_p3 + 12, self.m33);
        return self;
    }

    public Float4x4 storeRMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m01);
        _U.putFloat(bytes, address + 8, self.m02);
        _U.putFloat(bytes, address + 12, self.m03);
        _U.putFloat(bytes, _p1, self.m10);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p1 + 8, self.m12);
        _U.putFloat(bytes, _p1 + 12, self.m13);
        _U.putFloat(bytes, _p2, self.m20);
        _U.putFloat(bytes, _p2 + 4, self.m21);
        _U.putFloat(bytes, _p2 + 8, self.m22);
        _U.putFloat(bytes, _p2 + 12, self.m23);
        _U.putFloat(bytes, _p3, self.m30);
        _U.putFloat(bytes, _p3 + 4, self.m31);
        _U.putFloat(bytes, _p3 + 8, self.m32);
        _U.putFloat(bytes, _p3 + 12, self.m33);
        return self;
    }

    public Float4x4 loadRMUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m02 = _U.getFloat(address + 8);
        self.m03 = _U.getFloat(address + 12);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m12 = _U.getFloat(_p1 + 8);
        self.m13 = _U.getFloat(_p1 + 12);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.m23 = _U.getFloat(_p2 + 12);
        self.m30 = _U.getFloat(_p3);
        self.m31 = _U.getFloat(_p3 + 4);
        self.m32 = _U.getFloat(_p3 + 8);
        self.m33 = _U.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadRMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m02 = _U.getFloat(bytes, address + 8);
        self.m03 = _U.getFloat(bytes, address + 12);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m12 = _U.getFloat(bytes, _p1 + 8);
        self.m13 = _U.getFloat(bytes, _p1 + 12);
        self.m20 = _U.getFloat(bytes, _p2);
        self.m21 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.m23 = _U.getFloat(bytes, _p2 + 12);
        self.m30 = _U.getFloat(bytes, _p3);
        self.m31 = _U.getFloat(bytes, _p3 + 4);
        self.m32 = _U.getFloat(bytes, _p3 + 8);
        self.m33 = _U.getFloat(bytes, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeRMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(address + 16, self.m02);
        _U.putDouble(address + 24, self.m03);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m12);
        _U.putDouble(_p1 + 24, self.m13);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        _U.putDouble(_p2 + 16, self.m22);
        _U.putDouble(_p2 + 24, self.m23);
        _U.putDouble(_p3, self.m30);
        _U.putDouble(_p3 + 8, self.m31);
        _U.putDouble(_p3 + 16, self.m32);
        _U.putDouble(_p3 + 24, self.m33);
        return self;
    }

    public Float4x4 storeRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, address + 16, self.m02);
        _U.putDouble(bytes, address + 24, self.m03);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m12);
        _U.putDouble(bytes, _p1 + 24, self.m13);
        _U.putDouble(bytes, _p2, self.m20);
        _U.putDouble(bytes, _p2 + 8, self.m21);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        _U.putDouble(bytes, _p2 + 24, self.m23);
        _U.putDouble(bytes, _p3, self.m30);
        _U.putDouble(bytes, _p3 + 8, self.m31);
        _U.putDouble(bytes, _p3 + 16, self.m32);
        _U.putDouble(bytes, _p3 + 24, self.m33);
        return self;
    }

    public Float4x4 loadRMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8);
        self.m02 = (float) _U.getDouble(address + 16);
        self.m03 = (float) _U.getDouble(address + 24);
        self.m10 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m12 = (float) _U.getDouble(_p1 + 16);
        self.m13 = (float) _U.getDouble(_p1 + 24);
        self.m20 = (float) _U.getDouble(_p2);
        self.m21 = (float) _U.getDouble(_p2 + 8);
        self.m22 = (float) _U.getDouble(_p2 + 16);
        self.m23 = (float) _U.getDouble(_p2 + 24);
        self.m30 = (float) _U.getDouble(_p3);
        self.m31 = (float) _U.getDouble(_p3 + 8);
        self.m32 = (float) _U.getDouble(_p3 + 16);
        self.m33 = (float) _U.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8);
        self.m02 = (float) _U.getDouble(bytes, address + 16);
        self.m03 = (float) _U.getDouble(bytes, address + 24);
        self.m10 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m12 = (float) _U.getDouble(bytes, _p1 + 16);
        self.m13 = (float) _U.getDouble(bytes, _p1 + 24);
        self.m20 = (float) _U.getDouble(bytes, _p2);
        self.m21 = (float) _U.getDouble(bytes, _p2 + 8);
        self.m22 = (float) _U.getDouble(bytes, _p2 + 16);
        self.m23 = (float) _U.getDouble(bytes, _p2 + 24);
        self.m30 = (float) _U.getDouble(bytes, _p3);
        self.m31 = (float) _U.getDouble(bytes, _p3 + 8);
        self.m32 = (float) _U.getDouble(bytes, _p3 + 16);
        self.m33 = (float) _U.getDouble(bytes, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
}
