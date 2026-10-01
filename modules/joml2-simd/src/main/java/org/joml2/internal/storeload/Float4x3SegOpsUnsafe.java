// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x3SegOpsUnsafe extends Float4x3SegOps {
    private static final Float4x3SegOpsMS MS = new Float4x3SegOpsMS();

    public MemorySegment storeCMDouble(Float4x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Float4x3 loadCMDouble(Float4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8L);
        self.data[2] = (float) _U.getDouble(address + 16L);
        self.data[3] = (float) _U.getDouble(address + 24L);
        self.data[4] = (float) _U.getDouble(address + 32L);
        self.data[5] = (float) _U.getDouble(address + 40L);
        self.data[6] = (float) _U.getDouble(address + 48L);
        self.data[7] = (float) _U.getDouble(address + 56L);
        self.data[8] = (float) _U.getDouble(address + 64L);
        self.data[9] = (float) _U.getDouble(address + 72L);
        self.data[10] = (float) _U.getDouble(address + 80L);
        self.data[11] = (float) _U.getDouble(address + 88L);
        return self;
    }

    private static Float4x3 loadCMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8L);
        self.data[2] = (float) _U.getDouble(bytes, address + 16L);
        self.data[3] = (float) _U.getDouble(bytes, address + 24L);
        self.data[4] = (float) _U.getDouble(bytes, address + 32L);
        self.data[5] = (float) _U.getDouble(bytes, address + 40L);
        self.data[6] = (float) _U.getDouble(bytes, address + 48L);
        self.data[7] = (float) _U.getDouble(bytes, address + 56L);
        self.data[8] = (float) _U.getDouble(bytes, address + 64L);
        self.data[9] = (float) _U.getDouble(bytes, address + 72L);
        self.data[10] = (float) _U.getDouble(bytes, address + 80L);
        self.data[11] = (float) _U.getDouble(bytes, address + 88L);
        return self;
    }

    public MemorySegment storeRM(Float4x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[4]);
        _U.putFloat(address + 8L, self.data[8]);
        _U.putFloat(address + 12L, self.data[1]);
        _U.putFloat(address + 16L, self.data[5]);
        _U.putFloat(address + 20L, self.data[9]);
        _U.putFloat(address + 24L, self.data[2]);
        _U.putFloat(address + 28L, self.data[6]);
        _U.putFloat(address + 32L, self.data[10]);
        _U.putFloat(address + 36L, self.data[3]);
        _U.putFloat(address + 40L, self.data[7]);
        _U.putFloat(address + 44L, self.data[11]);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float4x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[4]);
        _U.putFloat(bytes, address + 8L, self.data[8]);
        _U.putFloat(bytes, address + 12L, self.data[1]);
        _U.putFloat(bytes, address + 16L, self.data[5]);
        _U.putFloat(bytes, address + 20L, self.data[9]);
        _U.putFloat(bytes, address + 24L, self.data[2]);
        _U.putFloat(bytes, address + 28L, self.data[6]);
        _U.putFloat(bytes, address + 32L, self.data[10]);
        _U.putFloat(bytes, address + 36L, self.data[3]);
        _U.putFloat(bytes, address + 40L, self.data[7]);
        _U.putFloat(bytes, address + 44L, self.data[11]);
        return dest;
    }

    public Float4x3 loadRM(Float4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
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
        return self;
    }

    private static Float4x3 loadRMOnHeap(Float4x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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
        return self;
    }

    public MemorySegment storeRMDouble(Float4x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Float4x3 loadRMDouble(Float4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8L);
        self.data[8] = (float) _U.getDouble(address + 16L);
        self.data[1] = (float) _U.getDouble(address + 24L);
        self.data[5] = (float) _U.getDouble(address + 32L);
        self.data[9] = (float) _U.getDouble(address + 40L);
        self.data[2] = (float) _U.getDouble(address + 48L);
        self.data[6] = (float) _U.getDouble(address + 56L);
        self.data[10] = (float) _U.getDouble(address + 64L);
        self.data[3] = (float) _U.getDouble(address + 72L);
        self.data[7] = (float) _U.getDouble(address + 80L);
        self.data[11] = (float) _U.getDouble(address + 88L);
        return self;
    }

    private static Float4x3 loadRMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[4] = (float) _U.getDouble(bytes, address + 8L);
        self.data[8] = (float) _U.getDouble(bytes, address + 16L);
        self.data[1] = (float) _U.getDouble(bytes, address + 24L);
        self.data[5] = (float) _U.getDouble(bytes, address + 32L);
        self.data[9] = (float) _U.getDouble(bytes, address + 40L);
        self.data[2] = (float) _U.getDouble(bytes, address + 48L);
        self.data[6] = (float) _U.getDouble(bytes, address + 56L);
        self.data[10] = (float) _U.getDouble(bytes, address + 64L);
        self.data[3] = (float) _U.getDouble(bytes, address + 72L);
        self.data[7] = (float) _U.getDouble(bytes, address + 80L);
        self.data[11] = (float) _U.getDouble(bytes, address + 88L);
        return self;
    }

    public MemorySegment storeCM(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[1]);
        _U.putFloat(address + 8, self.data[2]);
        _U.putFloat(address + 12, self.data[3]);
        _U.putFloat(_p1, self.data[4]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p1 + 8, self.data[6]);
        _U.putFloat(_p1 + 12, self.data[7]);
        _U.putFloat(_p2, self.data[8]);
        _U.putFloat(_p2 + 4, self.data[9]);
        _U.putFloat(_p2 + 8, self.data[10]);
        _U.putFloat(_p2 + 12, self.data[11]);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[1]);
        _U.putFloat(bytes, address + 8, self.data[2]);
        _U.putFloat(bytes, address + 12, self.data[3]);
        _U.putFloat(bytes, _p1, self.data[4]);
        _U.putFloat(bytes, _p1 + 4, self.data[5]);
        _U.putFloat(bytes, _p1 + 8, self.data[6]);
        _U.putFloat(bytes, _p1 + 12, self.data[7]);
        _U.putFloat(bytes, _p2, self.data[8]);
        _U.putFloat(bytes, _p2 + 4, self.data[9]);
        _U.putFloat(bytes, _p2 + 8, self.data[10]);
        _U.putFloat(bytes, _p2 + 12, self.data[11]);
        return dest;
    }

    public Float4x3 loadCM(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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
        return self;
    }

    private static Float4x3 loadCMOnHeap(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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
        return self;
    }

    public MemorySegment storeCMDouble(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Float4x3 loadCMDouble(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8);
        self.data[2] = (float) _U.getDouble(address + 16);
        self.data[3] = (float) _U.getDouble(address + 24);
        self.data[4] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[6] = (float) _U.getDouble(_p1 + 16);
        self.data[7] = (float) _U.getDouble(_p1 + 24);
        self.data[8] = (float) _U.getDouble(_p2);
        self.data[9] = (float) _U.getDouble(_p2 + 8);
        self.data[10] = (float) _U.getDouble(_p2 + 16);
        self.data[11] = (float) _U.getDouble(_p2 + 24);
        return self;
    }

    private static Float4x3 loadCMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8);
        self.data[2] = (float) _U.getDouble(bytes, address + 16);
        self.data[3] = (float) _U.getDouble(bytes, address + 24);
        self.data[4] = (float) _U.getDouble(bytes, _p1);
        self.data[5] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[6] = (float) _U.getDouble(bytes, _p1 + 16);
        self.data[7] = (float) _U.getDouble(bytes, _p1 + 24);
        self.data[8] = (float) _U.getDouble(bytes, _p2);
        self.data[9] = (float) _U.getDouble(bytes, _p2 + 8);
        self.data[10] = (float) _U.getDouble(bytes, _p2 + 16);
        self.data[11] = (float) _U.getDouble(bytes, _p2 + 24);
        return self;
    }

    public MemorySegment storeRM(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[4]);
        _U.putFloat(address + 8, self.data[8]);
        _U.putFloat(_p1, self.data[1]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p1 + 8, self.data[9]);
        _U.putFloat(_p2, self.data[2]);
        _U.putFloat(_p2 + 4, self.data[6]);
        _U.putFloat(_p2 + 8, self.data[10]);
        _U.putFloat(_p3, self.data[3]);
        _U.putFloat(_p3 + 4, self.data[7]);
        _U.putFloat(_p3 + 8, self.data[11]);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[4]);
        _U.putFloat(bytes, address + 8, self.data[8]);
        _U.putFloat(bytes, _p1, self.data[1]);
        _U.putFloat(bytes, _p1 + 4, self.data[5]);
        _U.putFloat(bytes, _p1 + 8, self.data[9]);
        _U.putFloat(bytes, _p2, self.data[2]);
        _U.putFloat(bytes, _p2 + 4, self.data[6]);
        _U.putFloat(bytes, _p2 + 8, self.data[10]);
        _U.putFloat(bytes, _p3, self.data[3]);
        _U.putFloat(bytes, _p3 + 4, self.data[7]);
        _U.putFloat(bytes, _p3 + 8, self.data[11]);
        return dest;
    }

    public Float4x3 loadRM(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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
        return self;
    }

    private static Float4x3 loadRMOnHeap(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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
        return self;
    }

    public MemorySegment storeRMDouble(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Float4x3 loadRMDouble(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMDoubleOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8);
        self.data[8] = (float) _U.getDouble(address + 16);
        self.data[1] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[9] = (float) _U.getDouble(_p1 + 16);
        self.data[2] = (float) _U.getDouble(_p2);
        self.data[6] = (float) _U.getDouble(_p2 + 8);
        self.data[10] = (float) _U.getDouble(_p2 + 16);
        self.data[3] = (float) _U.getDouble(_p3);
        self.data[7] = (float) _U.getDouble(_p3 + 8);
        self.data[11] = (float) _U.getDouble(_p3 + 16);
        return self;
    }

    private static Float4x3 loadRMDoubleOnHeap(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[4] = (float) _U.getDouble(bytes, address + 8);
        self.data[8] = (float) _U.getDouble(bytes, address + 16);
        self.data[1] = (float) _U.getDouble(bytes, _p1);
        self.data[5] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[9] = (float) _U.getDouble(bytes, _p1 + 16);
        self.data[2] = (float) _U.getDouble(bytes, _p2);
        self.data[6] = (float) _U.getDouble(bytes, _p2 + 8);
        self.data[10] = (float) _U.getDouble(bytes, _p2 + 16);
        self.data[3] = (float) _U.getDouble(bytes, _p3);
        self.data[7] = (float) _U.getDouble(bytes, _p3 + 8);
        self.data[11] = (float) _U.getDouble(bytes, _p3 + 16);
        return self;
    }
}
