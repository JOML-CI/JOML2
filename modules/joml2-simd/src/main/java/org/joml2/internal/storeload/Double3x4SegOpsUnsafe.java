// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x4SegOpsUnsafe extends Double3x4SegOps {
    private static final Double3x4SegOpsMS MS = new Double3x4SegOpsMS();
    private static final Double3x4RawOpsUnsafe RAW = new Double3x4RawOpsUnsafe();

    public MemorySegment storeCM(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
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

    private static MemorySegment storeCMOnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
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

    public Double3x4 loadCM(Double3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Double3x4 loadCMOnHeap(Double3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeCMFloat(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double3x4 loadCMFloat(Double3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src);
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
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadCMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src);
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
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
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

    private static MemorySegment storeRMOnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
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

    public Double3x4 loadRM(Double3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Double3x4 loadRMOnHeap(Double3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeRMFloat(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double3x4 loadRMFloat(Double3x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Double3x4 loadRMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeCM(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
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

    private static MemorySegment storeCMOnHeap(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
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

    public Double3x4 loadCM(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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

    private static Double3x4 loadCMOnHeap(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeCMFloat(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double3x4 loadCMFloat(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src, stride);
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
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadCMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src, stride);
        return RAW.loadCMFloatHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRM(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
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

    private static MemorySegment storeRMOnHeap(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
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

    public Double3x4 loadRM(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
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

    private static Double3x4 loadRMOnHeap(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
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

    public MemorySegment storeRMFloat(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
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
        return dest;
    }

    public Double3x4 loadRMFloat(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src, stride);
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
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadRMFloatOnHeap(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src, stride);
        return RAW.loadRMFloatHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeCM4x4(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
        RAW.storeCM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCM4x4OnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4(self, offset, dest);
        RAW.storeCM4x4Heap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeCM4x4Float(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4FloatOnHeap(self, offset, dest);
        RAW.storeCM4x4FloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCM4x4FloatOnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4Float(self, offset, dest);
        RAW.storeCM4x4FloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeRM4x4(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
        RAW.storeRM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRM4x4OnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4(self, offset, dest);
        RAW.storeRM4x4Heap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeRM4x4Float(Double3x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4FloatOnHeap(self, offset, dest);
        RAW.storeRM4x4FloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRM4x4FloatOnHeap(Double3x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4Float(self, offset, dest);
        RAW.storeRM4x4FloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }
}
