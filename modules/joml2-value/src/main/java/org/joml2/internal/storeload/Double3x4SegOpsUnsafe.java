// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x4SegOpsUnsafe extends Double3x4SegOps {
    private static final Double3x4SegOpsMS MS = new Double3x4SegOpsMS();
    private static final Double3x4RawOpsUnsafe RAW = new Double3x4RawOpsUnsafe();

    public MemorySegment storeCM(Double3x4 self, long offset, MemorySegment dest) {
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
        _U.putDouble(address + 48L, self.m02());
        _U.putDouble(address + 56L, self.m12());
        _U.putDouble(address + 64L, self.m22());
        _U.putDouble(address + 72L, self.m03());
        _U.putDouble(address + 80L, self.m13());
        _U.putDouble(address + 88L, self.m23());
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double3x4 self, long offset, MemorySegment dest) {
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
        _U.putDouble(bytes, address + 48L, self.m02());
        _U.putDouble(bytes, address + 56L, self.m12());
        _U.putDouble(bytes, address + 64L, self.m22());
        _U.putDouble(bytes, address + 72L, self.m03());
        _U.putDouble(bytes, address + 80L, self.m13());
        _U.putDouble(bytes, address + 88L, self.m23());
        return dest;
    }

    public Double3x4 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c4 = _U.getDouble(address + 8L);
        double _c8 = _U.getDouble(address + 16L);
        double _c1 = _U.getDouble(address + 24L);
        double _c5 = _U.getDouble(address + 32L);
        double _c9 = _U.getDouble(address + 40L);
        double _c2 = _U.getDouble(address + 48L);
        double _c6 = _U.getDouble(address + 56L);
        double _c10 = _U.getDouble(address + 64L);
        double _c3 = _U.getDouble(address + 72L);
        double _c7 = _U.getDouble(address + 80L);
        double _c11 = _U.getDouble(address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadCMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c4 = _U.getDouble(bytes, address + 8L);
        double _c8 = _U.getDouble(bytes, address + 16L);
        double _c1 = _U.getDouble(bytes, address + 24L);
        double _c5 = _U.getDouble(bytes, address + 32L);
        double _c9 = _U.getDouble(bytes, address + 40L);
        double _c2 = _U.getDouble(bytes, address + 48L);
        double _c6 = _U.getDouble(bytes, address + 56L);
        double _c10 = _U.getDouble(bytes, address + 64L);
        double _c3 = _U.getDouble(bytes, address + 72L);
        double _c7 = _U.getDouble(bytes, address + 80L);
        double _c11 = _U.getDouble(bytes, address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeCMFloat(Double3x4 self, long offset, MemorySegment dest) {
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
        _U.putFloat(address + 24L, (float) self.m02());
        _U.putFloat(address + 28L, (float) self.m12());
        _U.putFloat(address + 32L, (float) self.m22());
        _U.putFloat(address + 36L, (float) self.m03());
        _U.putFloat(address + 40L, (float) self.m13());
        _U.putFloat(address + 44L, (float) self.m23());
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x4 self, long offset, MemorySegment dest) {
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
        _U.putFloat(bytes, address + 24L, (float) self.m02());
        _U.putFloat(bytes, address + 28L, (float) self.m12());
        _U.putFloat(bytes, address + 32L, (float) self.m22());
        _U.putFloat(bytes, address + 36L, (float) self.m03());
        _U.putFloat(bytes, address + 40L, (float) self.m13());
        _U.putFloat(bytes, address + 44L, (float) self.m23());
        return dest;
    }

    public Double3x4 loadCMFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c4 = _U.getFloat(address + 4L);
        double _c8 = _U.getFloat(address + 8L);
        double _c1 = _U.getFloat(address + 12L);
        double _c5 = _U.getFloat(address + 16L);
        double _c9 = _U.getFloat(address + 20L);
        double _c2 = _U.getFloat(address + 24L);
        double _c6 = _U.getFloat(address + 28L);
        double _c10 = _U.getFloat(address + 32L);
        double _c3 = _U.getFloat(address + 36L);
        double _c7 = _U.getFloat(address + 40L);
        double _c11 = _U.getFloat(address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadCMFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c4 = _U.getFloat(bytes, address + 4L);
        double _c8 = _U.getFloat(bytes, address + 8L);
        double _c1 = _U.getFloat(bytes, address + 12L);
        double _c5 = _U.getFloat(bytes, address + 16L);
        double _c9 = _U.getFloat(bytes, address + 20L);
        double _c2 = _U.getFloat(bytes, address + 24L);
        double _c6 = _U.getFloat(bytes, address + 28L);
        double _c10 = _U.getFloat(bytes, address + 32L);
        double _c3 = _U.getFloat(bytes, address + 36L);
        double _c7 = _U.getFloat(bytes, address + 40L);
        double _c11 = _U.getFloat(bytes, address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeRM(Double3x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m03());
        _U.putDouble(address + 32L, self.m10());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, self.m12());
        _U.putDouble(address + 56L, self.m13());
        _U.putDouble(address + 64L, self.m20());
        _U.putDouble(address + 72L, self.m21());
        _U.putDouble(address + 80L, self.m22());
        _U.putDouble(address + 88L, self.m23());
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double3x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, self.m02());
        _U.putDouble(bytes, address + 24L, self.m03());
        _U.putDouble(bytes, address + 32L, self.m10());
        _U.putDouble(bytes, address + 40L, self.m11());
        _U.putDouble(bytes, address + 48L, self.m12());
        _U.putDouble(bytes, address + 56L, self.m13());
        _U.putDouble(bytes, address + 64L, self.m20());
        _U.putDouble(bytes, address + 72L, self.m21());
        _U.putDouble(bytes, address + 80L, self.m22());
        _U.putDouble(bytes, address + 88L, self.m23());
        return dest;
    }

    public Double3x4 loadRM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        double _c8 = _U.getDouble(address + 64L);
        double _c9 = _U.getDouble(address + 72L);
        double _c10 = _U.getDouble(address + 80L);
        double _c11 = _U.getDouble(address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadRMOnHeap(long offset, MemorySegment src) {
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
        double _c6 = _U.getDouble(bytes, address + 48L);
        double _c7 = _U.getDouble(bytes, address + 56L);
        double _c8 = _U.getDouble(bytes, address + 64L);
        double _c9 = _U.getDouble(bytes, address + 72L);
        double _c10 = _U.getDouble(bytes, address + 80L);
        double _c11 = _U.getDouble(bytes, address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeRMFloat(Double3x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m01());
        _U.putFloat(address + 8L, (float) self.m02());
        _U.putFloat(address + 12L, (float) self.m03());
        _U.putFloat(address + 16L, (float) self.m10());
        _U.putFloat(address + 20L, (float) self.m11());
        _U.putFloat(address + 24L, (float) self.m12());
        _U.putFloat(address + 28L, (float) self.m13());
        _U.putFloat(address + 32L, (float) self.m20());
        _U.putFloat(address + 36L, (float) self.m21());
        _U.putFloat(address + 40L, (float) self.m22());
        _U.putFloat(address + 44L, (float) self.m23());
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m01());
        _U.putFloat(bytes, address + 8L, (float) self.m02());
        _U.putFloat(bytes, address + 12L, (float) self.m03());
        _U.putFloat(bytes, address + 16L, (float) self.m10());
        _U.putFloat(bytes, address + 20L, (float) self.m11());
        _U.putFloat(bytes, address + 24L, (float) self.m12());
        _U.putFloat(bytes, address + 28L, (float) self.m13());
        _U.putFloat(bytes, address + 32L, (float) self.m20());
        _U.putFloat(bytes, address + 36L, (float) self.m21());
        _U.putFloat(bytes, address + 40L, (float) self.m22());
        _U.putFloat(bytes, address + 44L, (float) self.m23());
        return dest;
    }

    public Double3x4 loadRMFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        double _c8 = _U.getFloat(address + 32L);
        double _c9 = _U.getFloat(address + 36L);
        double _c10 = _U.getFloat(address + 40L);
        double _c11 = _U.getFloat(address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadRMFloatOnHeap(long offset, MemorySegment src) {
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
        double _c6 = _U.getFloat(bytes, address + 24L);
        double _c7 = _U.getFloat(bytes, address + 28L);
        double _c8 = _U.getFloat(bytes, address + 32L);
        double _c9 = _U.getFloat(bytes, address + 36L);
        double _c10 = _U.getFloat(bytes, address + 40L);
        double _c11 = _U.getFloat(bytes, address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeCM(Double3x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(address + 16, self.m20());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m21());
        _U.putDouble(_p2, self.m02());
        _U.putDouble(_p2 + 8, self.m12());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p3, self.m03());
        _U.putDouble(_p3 + 8, self.m13());
        _U.putDouble(_p3 + 16, self.m23());
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double3x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m10());
        _U.putDouble(bytes, address + 16, self.m20());
        _U.putDouble(bytes, _p1, self.m01());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p1 + 16, self.m21());
        _U.putDouble(bytes, _p2, self.m02());
        _U.putDouble(bytes, _p2 + 8, self.m12());
        _U.putDouble(bytes, _p2 + 16, self.m22());
        _U.putDouble(bytes, _p3, self.m03());
        _U.putDouble(bytes, _p3 + 8, self.m13());
        _U.putDouble(bytes, _p3 + 16, self.m23());
        return dest;
    }

    public Double3x4 loadCM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c4 = _U.getDouble(address + 8);
        double _c8 = _U.getDouble(address + 16);
        double _c1 = _U.getDouble(_p1);
        double _c5 = _U.getDouble(_p1 + 8);
        double _c9 = _U.getDouble(_p1 + 16);
        double _c2 = _U.getDouble(_p2);
        double _c6 = _U.getDouble(_p2 + 8);
        double _c10 = _U.getDouble(_p2 + 16);
        double _c3 = _U.getDouble(_p3);
        double _c7 = _U.getDouble(_p3 + 8);
        double _c11 = _U.getDouble(_p3 + 16);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadCMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c4 = _U.getDouble(bytes, address + 8);
        double _c8 = _U.getDouble(bytes, address + 16);
        double _c1 = _U.getDouble(bytes, _p1);
        double _c5 = _U.getDouble(bytes, _p1 + 8);
        double _c9 = _U.getDouble(bytes, _p1 + 16);
        double _c2 = _U.getDouble(bytes, _p2);
        double _c6 = _U.getDouble(bytes, _p2 + 8);
        double _c10 = _U.getDouble(bytes, _p2 + 16);
        double _c3 = _U.getDouble(bytes, _p3);
        double _c7 = _U.getDouble(bytes, _p3 + 8);
        double _c11 = _U.getDouble(bytes, _p3 + 16);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeCMFloat(Double3x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m10());
        _U.putFloat(address + 8, (float) self.m20());
        _U.putFloat(_p1, (float) self.m01());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p1 + 8, (float) self.m21());
        _U.putFloat(_p2, (float) self.m02());
        _U.putFloat(_p2 + 4, (float) self.m12());
        _U.putFloat(_p2 + 8, (float) self.m22());
        _U.putFloat(_p3, (float) self.m03());
        _U.putFloat(_p3 + 4, (float) self.m13());
        _U.putFloat(_p3 + 8, (float) self.m23());
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m10());
        _U.putFloat(bytes, address + 8, (float) self.m20());
        _U.putFloat(bytes, _p1, (float) self.m01());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p1 + 8, (float) self.m21());
        _U.putFloat(bytes, _p2, (float) self.m02());
        _U.putFloat(bytes, _p2 + 4, (float) self.m12());
        _U.putFloat(bytes, _p2 + 8, (float) self.m22());
        _U.putFloat(bytes, _p3, (float) self.m03());
        _U.putFloat(bytes, _p3 + 4, (float) self.m13());
        _U.putFloat(bytes, _p3 + 8, (float) self.m23());
        return dest;
    }

    public Double3x4 loadCMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c4 = _U.getFloat(address + 4);
        double _c8 = _U.getFloat(address + 8);
        double _c1 = _U.getFloat(_p1);
        double _c5 = _U.getFloat(_p1 + 4);
        double _c9 = _U.getFloat(_p1 + 8);
        double _c2 = _U.getFloat(_p2);
        double _c6 = _U.getFloat(_p2 + 4);
        double _c10 = _U.getFloat(_p2 + 8);
        double _c3 = _U.getFloat(_p3);
        double _c7 = _U.getFloat(_p3 + 4);
        double _c11 = _U.getFloat(_p3 + 8);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadCMFloatOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c4 = _U.getFloat(bytes, address + 4);
        double _c8 = _U.getFloat(bytes, address + 8);
        double _c1 = _U.getFloat(bytes, _p1);
        double _c5 = _U.getFloat(bytes, _p1 + 4);
        double _c9 = _U.getFloat(bytes, _p1 + 8);
        double _c2 = _U.getFloat(bytes, _p2);
        double _c6 = _U.getFloat(bytes, _p2 + 4);
        double _c10 = _U.getFloat(bytes, _p2 + 8);
        double _c3 = _U.getFloat(bytes, _p3);
        double _c7 = _U.getFloat(bytes, _p3 + 4);
        double _c11 = _U.getFloat(bytes, _p3 + 8);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeRM(Double3x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(address + 16, self.m02());
        _U.putDouble(address + 24, self.m03());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m12());
        _U.putDouble(_p1 + 24, self.m13());
        _U.putDouble(_p2, self.m20());
        _U.putDouble(_p2 + 8, self.m21());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p2 + 24, self.m23());
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double3x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m01());
        _U.putDouble(bytes, address + 16, self.m02());
        _U.putDouble(bytes, address + 24, self.m03());
        _U.putDouble(bytes, _p1, self.m10());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p1 + 16, self.m12());
        _U.putDouble(bytes, _p1 + 24, self.m13());
        _U.putDouble(bytes, _p2, self.m20());
        _U.putDouble(bytes, _p2 + 8, self.m21());
        _U.putDouble(bytes, _p2 + 16, self.m22());
        _U.putDouble(bytes, _p2 + 24, self.m23());
        return dest;
    }

    public Double3x4 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8);
        double _c2 = _U.getDouble(address + 16);
        double _c3 = _U.getDouble(address + 24);
        double _c4 = _U.getDouble(_p1);
        double _c5 = _U.getDouble(_p1 + 8);
        double _c6 = _U.getDouble(_p1 + 16);
        double _c7 = _U.getDouble(_p1 + 24);
        double _c8 = _U.getDouble(_p2);
        double _c9 = _U.getDouble(_p2 + 8);
        double _c10 = _U.getDouble(_p2 + 16);
        double _c11 = _U.getDouble(_p2 + 24);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadRMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8);
        double _c2 = _U.getDouble(bytes, address + 16);
        double _c3 = _U.getDouble(bytes, address + 24);
        double _c4 = _U.getDouble(bytes, _p1);
        double _c5 = _U.getDouble(bytes, _p1 + 8);
        double _c6 = _U.getDouble(bytes, _p1 + 16);
        double _c7 = _U.getDouble(bytes, _p1 + 24);
        double _c8 = _U.getDouble(bytes, _p2);
        double _c9 = _U.getDouble(bytes, _p2 + 8);
        double _c10 = _U.getDouble(bytes, _p2 + 16);
        double _c11 = _U.getDouble(bytes, _p2 + 24);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeRMFloat(Double3x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m01());
        _U.putFloat(address + 8, (float) self.m02());
        _U.putFloat(address + 12, (float) self.m03());
        _U.putFloat(_p1, (float) self.m10());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p1 + 8, (float) self.m12());
        _U.putFloat(_p1 + 12, (float) self.m13());
        _U.putFloat(_p2, (float) self.m20());
        _U.putFloat(_p2 + 4, (float) self.m21());
        _U.putFloat(_p2 + 8, (float) self.m22());
        _U.putFloat(_p2 + 12, (float) self.m23());
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m01());
        _U.putFloat(bytes, address + 8, (float) self.m02());
        _U.putFloat(bytes, address + 12, (float) self.m03());
        _U.putFloat(bytes, _p1, (float) self.m10());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p1 + 8, (float) self.m12());
        _U.putFloat(bytes, _p1 + 12, (float) self.m13());
        _U.putFloat(bytes, _p2, (float) self.m20());
        _U.putFloat(bytes, _p2 + 4, (float) self.m21());
        _U.putFloat(bytes, _p2 + 8, (float) self.m22());
        _U.putFloat(bytes, _p2 + 12, (float) self.m23());
        return dest;
    }

    public Double3x4 loadRMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4);
        double _c2 = _U.getFloat(address + 8);
        double _c3 = _U.getFloat(address + 12);
        double _c4 = _U.getFloat(_p1);
        double _c5 = _U.getFloat(_p1 + 4);
        double _c6 = _U.getFloat(_p1 + 8);
        double _c7 = _U.getFloat(_p1 + 12);
        double _c8 = _U.getFloat(_p2);
        double _c9 = _U.getFloat(_p2 + 4);
        double _c10 = _U.getFloat(_p2 + 8);
        double _c11 = _U.getFloat(_p2 + 12);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Double3x4 loadRMFloatOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4);
        double _c2 = _U.getFloat(bytes, address + 8);
        double _c3 = _U.getFloat(bytes, address + 12);
        double _c4 = _U.getFloat(bytes, _p1);
        double _c5 = _U.getFloat(bytes, _p1 + 4);
        double _c6 = _U.getFloat(bytes, _p1 + 8);
        double _c7 = _U.getFloat(bytes, _p1 + 12);
        double _c8 = _U.getFloat(bytes, _p2);
        double _c9 = _U.getFloat(bytes, _p2 + 4);
        double _c10 = _U.getFloat(bytes, _p2 + 8);
        double _c11 = _U.getFloat(bytes, _p2 + 12);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public MemorySegment storeCM4x4(Double3x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
        RAW.storeCM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCM4x4OnHeap(Double3x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4(self, offset, dest);
        RAW.storeCM4x4Heap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeCM4x4Float(Double3x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4FloatOnHeap(self, offset, dest);
        RAW.storeCM4x4FloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCM4x4FloatOnHeap(Double3x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4Float(self, offset, dest);
        RAW.storeCM4x4FloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeRM4x4(Double3x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
        RAW.storeRM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRM4x4OnHeap(Double3x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4(self, offset, dest);
        RAW.storeRM4x4Heap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeRM4x4Float(Double3x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4FloatOnHeap(self, offset, dest);
        RAW.storeRM4x4FloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRM4x4FloatOnHeap(Double3x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4Float(self, offset, dest);
        RAW.storeRM4x4FloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }
}
