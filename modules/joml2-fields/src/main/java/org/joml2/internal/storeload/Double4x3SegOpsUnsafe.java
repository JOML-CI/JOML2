// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x3SegOpsUnsafe extends Double4x3SegOps {
    private static final Double4x3SegOpsMS MS = new Double4x3SegOpsMS();

    public MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Double4x3 loadCMOnHeap(Double4x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Double4x3 loadCMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Double4x3 loadRMOnHeap(Double4x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Double4x3 loadRMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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

    private static Double4x3 loadCMOnHeap(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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

    private static Double4x3 loadCMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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

    private static Double4x3 loadRMOnHeap(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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

    private static Double4x3 loadRMFloatOnHeap(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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
