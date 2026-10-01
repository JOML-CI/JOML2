// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2x2SegOpsUnsafe extends Float2x2SegOps {
    private static final Float2x2SegOpsMS MS = new Float2x2SegOpsMS();

    public MemorySegment storeCM(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m01());
        U.putFloat(address + 12L, self.m11());
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, self.m00());
        U.putFloat(bytes, address + 4L, self.m10());
        U.putFloat(bytes, address + 8L, self.m01());
        U.putFloat(bytes, address + 12L, self.m11());
        return dest;
    }

    public Float2x2 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c2 = U.getFloat(address + 4L);
        float _c1 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadCMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = U.getFloat(bytes, address);
        float _c2 = U.getFloat(bytes, address + 4L);
        float _c1 = U.getFloat(bytes, address + 8L);
        float _c3 = U.getFloat(bytes, address + 12L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeCMDouble(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m01());
        U.putDouble(address + 24L, self.m11());
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8L, self.m10());
        U.putDouble(bytes, address + 16L, self.m01());
        U.putDouble(bytes, address + 24L, self.m11());
        return dest;
    }

    public Float2x2 loadCMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c2 = (float) U.getDouble(address + 8L);
        float _c1 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadCMDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = (float) U.getDouble(bytes, address);
        float _c2 = (float) U.getDouble(bytes, address + 8L);
        float _c1 = (float) U.getDouble(bytes, address + 16L);
        float _c3 = (float) U.getDouble(bytes, address + 24L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeRM(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m10());
        U.putFloat(address + 12L, self.m11());
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, self.m00());
        U.putFloat(bytes, address + 4L, self.m01());
        U.putFloat(bytes, address + 8L, self.m10());
        U.putFloat(bytes, address + 12L, self.m11());
        return dest;
    }

    public Float2x2 loadRM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadRMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = U.getFloat(bytes, address);
        float _c1 = U.getFloat(bytes, address + 4L);
        float _c2 = U.getFloat(bytes, address + 8L);
        float _c3 = U.getFloat(bytes, address + 12L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeRMDouble(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m10());
        U.putDouble(address + 24L, self.m11());
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8L, self.m01());
        U.putDouble(bytes, address + 16L, self.m10());
        U.putDouble(bytes, address + 24L, self.m11());
        return dest;
    }

    public Float2x2 loadRMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadRMDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = (float) U.getDouble(bytes, address);
        float _c1 = (float) U.getDouble(bytes, address + 8L);
        float _c2 = (float) U.getDouble(bytes, address + 16L);
        float _c3 = (float) U.getDouble(bytes, address + 24L);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeCM(Float2x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m10());
        U.putFloat(_p1, self.m01());
        U.putFloat(_p1 + 4, self.m11());
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float2x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, self.m00());
        U.putFloat(bytes, address + 4, self.m10());
        U.putFloat(bytes, _p1, self.m01());
        U.putFloat(bytes, _p1 + 4, self.m11());
        return dest;
    }

    public Float2x2 loadCM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        float _c0 = U.getFloat(address);
        float _c2 = U.getFloat(address + 4);
        float _c1 = U.getFloat(_p1);
        float _c3 = U.getFloat(_p1 + 4);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadCMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        float _c0 = U.getFloat(bytes, address);
        float _c2 = U.getFloat(bytes, address + 4);
        float _c1 = U.getFloat(bytes, _p1);
        float _c3 = U.getFloat(bytes, _p1 + 4);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeCMDouble(Float2x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float2x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8, self.m10());
        U.putDouble(bytes, _p1, self.m01());
        U.putDouble(bytes, _p1 + 8, self.m11());
        return dest;
    }

    public Float2x2 loadCMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMDoubleOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c2 = (float) U.getDouble(address + 8);
        float _c1 = (float) U.getDouble(_p1);
        float _c3 = (float) U.getDouble(_p1 + 8);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadCMDoubleOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        float _c0 = (float) U.getDouble(bytes, address);
        float _c2 = (float) U.getDouble(bytes, address + 8);
        float _c1 = (float) U.getDouble(bytes, _p1);
        float _c3 = (float) U.getDouble(bytes, _p1 + 8);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeRM(Float2x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m01());
        U.putFloat(_p1, self.m10());
        U.putFloat(_p1 + 4, self.m11());
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float2x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, self.m00());
        U.putFloat(bytes, address + 4, self.m01());
        U.putFloat(bytes, _p1, self.m10());
        U.putFloat(bytes, _p1 + 4, self.m11());
        return dest;
    }

    public Float2x2 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4);
        float _c2 = U.getFloat(_p1);
        float _c3 = U.getFloat(_p1 + 4);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadRMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        float _c0 = U.getFloat(bytes, address);
        float _c1 = U.getFloat(bytes, address + 4);
        float _c2 = U.getFloat(bytes, _p1);
        float _c3 = U.getFloat(bytes, _p1 + 4);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeRMDouble(Float2x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float2x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00());
        U.putDouble(bytes, address + 8, self.m01());
        U.putDouble(bytes, _p1, self.m10());
        U.putDouble(bytes, _p1 + 8, self.m11());
        return dest;
    }

    public Float2x2 loadRMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMDoubleOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8);
        float _c2 = (float) U.getDouble(_p1);
        float _c3 = (float) U.getDouble(_p1 + 8);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    private static Float2x2 loadRMDoubleOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        float _c0 = (float) U.getDouble(bytes, address);
        float _c1 = (float) U.getDouble(bytes, address + 8);
        float _c2 = (float) U.getDouble(bytes, _p1);
        float _c3 = (float) U.getDouble(bytes, _p1 + 8);
        return new Float2x2(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeCM3x3(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM3x3(self, offset, dest);
        if (!dest.isNative()) return storeCM3x3OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m01());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return dest;
    }

    private static MemorySegment storeCM3x3OnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM3x3(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m10());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, self.m01());
        _U.putFloat(bytes, address + 16L, self.m11());
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return dest;
    }

    public MemorySegment storeCM3x3Double(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM3x3Double(self, offset, dest);
        if (!dest.isNative()) return storeCM3x3DoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m01());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return dest;
    }

    private static MemorySegment storeCM3x3DoubleOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM3x3Double(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m01());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return dest;
    }

    public MemorySegment storeRM3x3(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM3x3(self, offset, dest);
        if (!dest.isNative()) return storeRM3x3OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m10());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return dest;
    }

    private static MemorySegment storeRM3x3OnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM3x3(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m01());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, self.m10());
        _U.putFloat(bytes, address + 16L, self.m11());
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM3x3Double(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM3x3Double(self, offset, dest);
        if (!dest.isNative()) return storeRM3x3DoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return dest;
    }

    private static MemorySegment storeRM3x3DoubleOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM3x3Double(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m10());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return dest;
    }

    public MemorySegment storeCM4x4(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m01());
        _U.putFloat(address + 20L, self.m11());
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
        return dest;
    }

    private static MemorySegment storeCM4x4OnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m10());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, self.m01());
        _U.putFloat(bytes, address + 20L, self.m11());
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
        return dest;
    }

    public MemorySegment storeCM4x4Double(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4Double(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4DoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
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
        return dest;
    }

    private static MemorySegment storeCM4x4DoubleOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4Double(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m01());
        _U.putDouble(bytes, address + 40L, self.m11());
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
        return dest;
    }

    public MemorySegment storeRM4x4(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m10());
        _U.putFloat(address + 20L, self.m11());
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
        return dest;
    }

    private static MemorySegment storeRM4x4OnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m01());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, self.m10());
        _U.putFloat(bytes, address + 20L, self.m11());
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
        return dest;
    }

    public MemorySegment storeRM4x4Double(Float2x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4Double(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4DoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m10());
        _U.putDouble(address + 40L, self.m11());
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
        return dest;
    }

    private static MemorySegment storeRM4x4DoubleOnHeap(Float2x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4Double(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m10());
        _U.putDouble(bytes, address + 40L, self.m11());
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
        return dest;
    }
}
