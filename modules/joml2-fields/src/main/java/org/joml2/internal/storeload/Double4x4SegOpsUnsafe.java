// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4SegOpsUnsafe extends Double4x4SegOps {
    private static final Double4x4SegOpsMS MS = new Double4x4SegOpsMS();
    private static final Double4x4RawOpsUnsafe RAW = new Double4x4RawOpsUnsafe();

    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest) {
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
        _U.putDouble(address + 96L, self.m03);
        _U.putDouble(address + 104L, self.m13);
        _U.putDouble(address + 112L, self.m23);
        _U.putDouble(address + 120L, self.m33);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src) {
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
        self.m03 = _U.getDouble(address + 96L);
        self.m13 = _U.getDouble(address + 104L);
        self.m23 = _U.getDouble(address + 112L);
        self.m33 = _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double4x4 loadCMOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        return RAW.loadCMHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        RAW.storeCMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src) {
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
        self.m03 = _U.getFloat(address + 48L);
        self.m13 = _U.getFloat(address + 52L);
        self.m23 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double4x4 loadCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src);
        return RAW.loadCMFloatHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
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
        _U.putDouble(address + 96L, self.m30);
        _U.putDouble(address + 104L, self.m31);
        _U.putDouble(address + 112L, self.m32);
        _U.putDouble(address + 120L, self.m33);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8L);
        self.m02 = _U.getDouble(address + 16L);
        self.m03 = _U.getDouble(address + 24L);
        self.m10 = _U.getDouble(address + 32L);
        self.m11 = _U.getDouble(address + 40L);
        self.m12 = _U.getDouble(address + 48L);
        self.m13 = _U.getDouble(address + 56L);
        self.m20 = _U.getDouble(address + 64L);
        self.m21 = _U.getDouble(address + 72L);
        self.m22 = _U.getDouble(address + 80L);
        self.m23 = _U.getDouble(address + 88L);
        self.m30 = _U.getDouble(address + 96L);
        self.m31 = _U.getDouble(address + 104L);
        self.m32 = _U.getDouble(address + 112L);
        self.m33 = _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double4x4 loadRMOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        return RAW.loadRMHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        RAW.storeRMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src);
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
        self.m30 = _U.getFloat(address + 48L);
        self.m31 = _U.getFloat(address + 52L);
        self.m32 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double4x4 loadRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src);
        return RAW.loadRMFloatHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }

    private static Double4x4 loadCMOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        RAW.storeCMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src, stride);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset, stride);
    }

    private static Double4x4 loadCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src, stride);
        return RAW.loadCMFloatHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }

    private static Double4x4 loadRMOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        RAW.storeRMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src, stride);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset, stride);
    }

    private static Double4x4 loadRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src, stride);
        return RAW.loadRMFloatHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }
}
