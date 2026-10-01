// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x2SegOpsUnsafe extends Double3x2SegOps {
    private static final Double3x2SegOpsMS MS = new Double3x2SegOpsMS();

    public MemorySegment storeCM(Double3x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, self.m20());
        _U.putDouble(address + 24L, self.m01());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, self.m21());
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double3x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, self.m20());
        _U.putDouble(bytes, address + 24L, self.m01());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, self.m21());
        return dest;
    }

    public Double3x2 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c2 = _U.getDouble(address + 8L);
        double _c4 = _U.getDouble(address + 16L);
        double _c1 = _U.getDouble(address + 24L);
        double _c3 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadCMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c2 = _U.getDouble(bytes, address + 8L);
        double _c4 = _U.getDouble(bytes, address + 16L);
        double _c1 = _U.getDouble(bytes, address + 24L);
        double _c3 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeCMFloat(Double3x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m10());
        _U.putFloat(address + 8L, (float) self.m20());
        _U.putFloat(address + 12L, (float) self.m01());
        _U.putFloat(address + 16L, (float) self.m11());
        _U.putFloat(address + 20L, (float) self.m21());
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m10());
        _U.putFloat(bytes, address + 8L, (float) self.m20());
        _U.putFloat(bytes, address + 12L, (float) self.m01());
        _U.putFloat(bytes, address + 16L, (float) self.m11());
        _U.putFloat(bytes, address + 20L, (float) self.m21());
        return dest;
    }

    public Double3x2 loadCMFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c2 = _U.getFloat(address + 4L);
        double _c4 = _U.getFloat(address + 8L);
        double _c1 = _U.getFloat(address + 12L);
        double _c3 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadCMFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c2 = _U.getFloat(bytes, address + 4L);
        double _c4 = _U.getFloat(bytes, address + 8L);
        double _c1 = _U.getFloat(bytes, address + 12L);
        double _c3 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeRM(Double3x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m10());
        _U.putDouble(address + 24L, self.m11());
        _U.putDouble(address + 32L, self.m20());
        _U.putDouble(address + 40L, self.m21());
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double3x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, self.m10());
        _U.putDouble(bytes, address + 24L, self.m11());
        _U.putDouble(bytes, address + 32L, self.m20());
        _U.putDouble(bytes, address + 40L, self.m21());
        return dest;
    }

    public Double3x2 loadRM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadRMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeRMFloat(Double3x2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m01());
        _U.putFloat(address + 8L, (float) self.m10());
        _U.putFloat(address + 12L, (float) self.m11());
        _U.putFloat(address + 16L, (float) self.m20());
        _U.putFloat(address + 20L, (float) self.m21());
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m01());
        _U.putFloat(bytes, address + 8L, (float) self.m10());
        _U.putFloat(bytes, address + 12L, (float) self.m11());
        _U.putFloat(bytes, address + 16L, (float) self.m20());
        _U.putFloat(bytes, address + 20L, (float) self.m21());
        return dest;
    }

    public Double3x2 loadRMFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadRMFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeCM(Double3x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(address + 16, self.m20());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m21());
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double3x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m10());
        _U.putDouble(bytes, address + 16, self.m20());
        _U.putDouble(bytes, _p1, self.m01());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p1 + 16, self.m21());
        return dest;
    }

    public Double3x2 loadCM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c2 = _U.getDouble(address + 8);
        double _c4 = _U.getDouble(address + 16);
        double _c1 = _U.getDouble(_p1);
        double _c3 = _U.getDouble(_p1 + 8);
        double _c5 = _U.getDouble(_p1 + 16);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadCMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c2 = _U.getDouble(bytes, address + 8);
        double _c4 = _U.getDouble(bytes, address + 16);
        double _c1 = _U.getDouble(bytes, _p1);
        double _c3 = _U.getDouble(bytes, _p1 + 8);
        double _c5 = _U.getDouble(bytes, _p1 + 16);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeCMFloat(Double3x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m10());
        _U.putFloat(address + 8, (float) self.m20());
        _U.putFloat(_p1, (float) self.m01());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p1 + 8, (float) self.m21());
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m10());
        _U.putFloat(bytes, address + 8, (float) self.m20());
        _U.putFloat(bytes, _p1, (float) self.m01());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p1 + 8, (float) self.m21());
        return dest;
    }

    public Double3x2 loadCMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c2 = _U.getFloat(address + 4);
        double _c4 = _U.getFloat(address + 8);
        double _c1 = _U.getFloat(_p1);
        double _c3 = _U.getFloat(_p1 + 4);
        double _c5 = _U.getFloat(_p1 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadCMFloatOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c2 = _U.getFloat(bytes, address + 4);
        double _c4 = _U.getFloat(bytes, address + 8);
        double _c1 = _U.getFloat(bytes, _p1);
        double _c3 = _U.getFloat(bytes, _p1 + 4);
        double _c5 = _U.getFloat(bytes, _p1 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeRM(Double3x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p2, self.m20());
        _U.putDouble(_p2 + 8, self.m21());
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double3x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m01());
        _U.putDouble(bytes, _p1, self.m10());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p2, self.m20());
        _U.putDouble(bytes, _p2 + 8, self.m21());
        return dest;
    }

    public Double3x2 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8);
        double _c2 = _U.getDouble(_p1);
        double _c3 = _U.getDouble(_p1 + 8);
        double _c4 = _U.getDouble(_p2);
        double _c5 = _U.getDouble(_p2 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadRMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8);
        double _c2 = _U.getDouble(bytes, _p1);
        double _c3 = _U.getDouble(bytes, _p1 + 8);
        double _c4 = _U.getDouble(bytes, _p2);
        double _c5 = _U.getDouble(bytes, _p2 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeRMFloat(Double3x2 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m01());
        _U.putFloat(_p1, (float) self.m10());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p2, (float) self.m20());
        _U.putFloat(_p2 + 4, (float) self.m21());
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x2 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m01());
        _U.putFloat(bytes, _p1, (float) self.m10());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p2, (float) self.m20());
        _U.putFloat(bytes, _p2 + 4, (float) self.m21());
        return dest;
    }

    public Double3x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4);
        double _c2 = _U.getFloat(_p1);
        double _c3 = _U.getFloat(_p1 + 4);
        double _c4 = _U.getFloat(_p2);
        double _c5 = _U.getFloat(_p2 + 4);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Double3x2 loadRMFloatOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4);
        double _c2 = _U.getFloat(bytes, _p1);
        double _c3 = _U.getFloat(bytes, _p1 + 4);
        double _c4 = _U.getFloat(bytes, _p2);
        double _c5 = _U.getFloat(bytes, _p2 + 4);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
