// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x4SegOpsUnsafe extends Float3x4SegOps {
    private static final Float3x4SegOpsMS MS = new Float3x4SegOpsMS();
    private static final Float3x4RawOpsUnsafe RAW = new Float3x4RawOpsUnsafe();

    public MemorySegment storeCM(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, self.m01);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, self.m21);
        _U.putFloat(address + 24L, self.m02);
        _U.putFloat(address + 28L, self.m12);
        _U.putFloat(address + 32L, self.m22);
        _U.putFloat(address + 36L, self.m03);
        _U.putFloat(address + 40L, self.m13);
        _U.putFloat(address + 44L, self.m23);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, self.m20);
        _U.putFloat(bytes, address + 12L, self.m01);
        _U.putFloat(bytes, address + 16L, self.m11);
        _U.putFloat(bytes, address + 20L, self.m21);
        _U.putFloat(bytes, address + 24L, self.m02);
        _U.putFloat(bytes, address + 28L, self.m12);
        _U.putFloat(bytes, address + 32L, self.m22);
        _U.putFloat(bytes, address + 36L, self.m03);
        _U.putFloat(bytes, address + 40L, self.m13);
        _U.putFloat(bytes, address + 44L, self.m23);
        return dest;
    }

    public Float3x4 loadCM(Float3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m01 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        self.m02 = _U.getFloat(address + 24L);
        self.m12 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.m03 = _U.getFloat(address + 36L);
        self.m13 = _U.getFloat(address + 40L);
        self.m23 = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadCMOnHeap(Float3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m20 = _U.getFloat(bytes, address + 8L);
        self.m01 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m21 = _U.getFloat(bytes, address + 20L);
        self.m02 = _U.getFloat(bytes, address + 24L);
        self.m12 = _U.getFloat(bytes, address + 28L);
        self.m22 = _U.getFloat(bytes, address + 32L);
        self.m03 = _U.getFloat(bytes, address + 36L);
        self.m13 = _U.getFloat(bytes, address + 40L);
        self.m23 = _U.getFloat(bytes, address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m21);
        _U.putDouble(address + 48L, self.m02);
        _U.putDouble(address + 56L, self.m12);
        _U.putDouble(address + 64L, self.m22);
        _U.putDouble(address + 72L, self.m03);
        _U.putDouble(address + 80L, self.m13);
        _U.putDouble(address + 88L, self.m23);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m20);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m21);
        _U.putDouble(bytes, address + 48L, self.m02);
        _U.putDouble(bytes, address + 56L, self.m12);
        _U.putDouble(bytes, address + 64L, self.m22);
        _U.putDouble(bytes, address + 72L, self.m03);
        _U.putDouble(bytes, address + 80L, self.m13);
        _U.putDouble(bytes, address + 88L, self.m23);
        return dest;
    }

    public Float3x4 loadCMDouble(Float3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8L);
        self.m20 = (float) _U.getDouble(address + 16L);
        self.m01 = (float) _U.getDouble(address + 24L);
        self.m11 = (float) _U.getDouble(address + 32L);
        self.m21 = (float) _U.getDouble(address + 40L);
        self.m02 = (float) _U.getDouble(address + 48L);
        self.m12 = (float) _U.getDouble(address + 56L);
        self.m22 = (float) _U.getDouble(address + 64L);
        self.m03 = (float) _U.getDouble(address + 72L);
        self.m13 = (float) _U.getDouble(address + 80L);
        self.m23 = (float) _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadCMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8L);
        self.m20 = (float) _U.getDouble(bytes, address + 16L);
        self.m01 = (float) _U.getDouble(bytes, address + 24L);
        self.m11 = (float) _U.getDouble(bytes, address + 32L);
        self.m21 = (float) _U.getDouble(bytes, address + 40L);
        self.m02 = (float) _U.getDouble(bytes, address + 48L);
        self.m12 = (float) _U.getDouble(bytes, address + 56L);
        self.m22 = (float) _U.getDouble(bytes, address + 64L);
        self.m03 = (float) _U.getDouble(bytes, address + 72L);
        self.m13 = (float) _U.getDouble(bytes, address + 80L);
        self.m23 = (float) _U.getDouble(bytes, address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Float3x4 loadRM(Float3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
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
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadRMOnHeap(Float3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Float3x4 loadRMDouble(Float3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
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
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadRMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m10);
        _U.putFloat(address + 8, self.m20);
        _U.putFloat(_p1, self.m01);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m21);
        _U.putFloat(_p2, self.m02);
        _U.putFloat(_p2 + 4, self.m12);
        _U.putFloat(_p2 + 8, self.m22);
        _U.putFloat(_p3, self.m03);
        _U.putFloat(_p3 + 4, self.m13);
        _U.putFloat(_p3 + 8, self.m23);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m10);
        _U.putFloat(bytes, address + 8, self.m20);
        _U.putFloat(bytes, _p1, self.m01);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p1 + 8, self.m21);
        _U.putFloat(bytes, _p2, self.m02);
        _U.putFloat(bytes, _p2 + 4, self.m12);
        _U.putFloat(bytes, _p2 + 8, self.m22);
        _U.putFloat(bytes, _p3, self.m03);
        _U.putFloat(bytes, _p3 + 4, self.m13);
        _U.putFloat(bytes, _p3 + 8, self.m23);
        return dest;
    }

    public Float3x4 loadCM(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        self.m02 = _U.getFloat(_p2);
        self.m12 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.m03 = _U.getFloat(_p3);
        self.m13 = _U.getFloat(_p3 + 4);
        self.m23 = _U.getFloat(_p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadCMOnHeap(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m20 = _U.getFloat(bytes, address + 8);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m21 = _U.getFloat(bytes, _p1 + 8);
        self.m02 = _U.getFloat(bytes, _p2);
        self.m12 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.m03 = _U.getFloat(bytes, _p3);
        self.m13 = _U.getFloat(bytes, _p3 + 4);
        self.m23 = _U.getFloat(bytes, _p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        _U.putDouble(_p2, self.m02);
        _U.putDouble(_p2 + 8, self.m12);
        _U.putDouble(_p2 + 16, self.m22);
        _U.putDouble(_p3, self.m03);
        _U.putDouble(_p3 + 8, self.m13);
        _U.putDouble(_p3 + 16, self.m23);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, address + 16, self.m20);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m21);
        _U.putDouble(bytes, _p2, self.m02);
        _U.putDouble(bytes, _p2 + 8, self.m12);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        _U.putDouble(bytes, _p3, self.m03);
        _U.putDouble(bytes, _p3 + 8, self.m13);
        _U.putDouble(bytes, _p3 + 16, self.m23);
        return dest;
    }

    public Float3x4 loadCMDouble(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8);
        self.m20 = (float) _U.getDouble(address + 16);
        self.m01 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m21 = (float) _U.getDouble(_p1 + 16);
        self.m02 = (float) _U.getDouble(_p2);
        self.m12 = (float) _U.getDouble(_p2 + 8);
        self.m22 = (float) _U.getDouble(_p2 + 16);
        self.m03 = (float) _U.getDouble(_p3);
        self.m13 = (float) _U.getDouble(_p3 + 8);
        self.m23 = (float) _U.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadCMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8);
        self.m20 = (float) _U.getDouble(bytes, address + 16);
        self.m01 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m21 = (float) _U.getDouble(bytes, _p1 + 16);
        self.m02 = (float) _U.getDouble(bytes, _p2);
        self.m12 = (float) _U.getDouble(bytes, _p2 + 8);
        self.m22 = (float) _U.getDouble(bytes, _p2 + 16);
        self.m03 = (float) _U.getDouble(bytes, _p3);
        self.m13 = (float) _U.getDouble(bytes, _p3 + 8);
        self.m23 = (float) _U.getDouble(bytes, _p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        return dest;
    }

    public Float3x4 loadRM(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadRMOnHeap(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        return dest;
    }

    public Float3x4 loadRMDouble(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMDoubleOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x4 loadRMDoubleOnHeap(Float3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
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
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM4x4(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m01);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m21);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.m02);
        _U.putFloat(address + 36L, self.m12);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, self.m03);
        _U.putFloat(address + 52L, self.m13);
        _U.putFloat(address + 56L, self.m23);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeCM4x4OnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, self.m20);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, self.m01);
        _U.putFloat(bytes, address + 20L, self.m11);
        _U.putFloat(bytes, address + 24L, self.m21);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, self.m02);
        _U.putFloat(bytes, address + 36L, self.m12);
        _U.putFloat(bytes, address + 40L, self.m22);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, self.m03);
        _U.putFloat(bytes, address + 52L, self.m13);
        _U.putFloat(bytes, address + 56L, self.m23);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return dest;
    }

    public MemorySegment storeCM4x4Double(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4Double(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4DoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m21);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.m02);
        _U.putDouble(address + 72L, self.m12);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, self.m03);
        _U.putDouble(address + 104L, self.m13);
        _U.putDouble(address + 112L, self.m23);
        _U.putDouble(address + 120L, 1.0);
        return dest;
    }

    private static MemorySegment storeCM4x4DoubleOnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4Double(self, offset, dest);
        RAW.storeCM4x4DoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeRM4x4(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeRM4x4OnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM4x4Double(Float3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4Double(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4DoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return dest;
    }

    private static MemorySegment storeRM4x4DoubleOnHeap(Float3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4Double(self, offset, dest);
        RAW.storeRM4x4DoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }
}
