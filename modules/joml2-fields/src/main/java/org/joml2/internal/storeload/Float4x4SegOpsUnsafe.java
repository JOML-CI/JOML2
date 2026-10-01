// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4SegOpsUnsafe extends Float4x4SegOps {
    private static final Float4x4SegOpsMS MS = new Float4x4SegOpsMS();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
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
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
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

    private static Float4x4 loadCMOnHeap(Float4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        return RAW.loadCMHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest);
        RAW.storeCMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
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

    private static Float4x4 loadCMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src);
        return RAW.loadCMDoubleHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest) {
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
        _U.putFloat(address + 48L, self.m30);
        _U.putFloat(address + 52L, self.m31);
        _U.putFloat(address + 56L, self.m32);
        _U.putFloat(address + 60L, self.m33);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src) {
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
        self.m30 = _U.getFloat(address + 48L);
        self.m31 = _U.getFloat(address + 52L);
        self.m32 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadRMOnHeap(Float4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        return RAW.loadRMHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest);
        RAW.storeRMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src) {
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
        self.m30 = (float) _U.getDouble(address + 96L);
        self.m31 = (float) _U.getDouble(address + 104L);
        self.m32 = (float) _U.getDouble(address + 112L);
        self.m33 = (float) _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadRMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src);
        return RAW.loadRMDoubleHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }

    private static Float4x4 loadCMOnHeap(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
        RAW.storeCMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src, stride);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset, stride);
    }

    private static Float4x4 loadCMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src, stride);
        return RAW.loadCMDoubleHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }

    private static Float4x4 loadRMOnHeap(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
        RAW.storeRMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMDoubleOnHeap(self, offset, src, stride);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset, stride);
    }

    private static Float4x4 loadRMDoubleOnHeap(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src, stride);
        return RAW.loadRMDoubleHeap(self, bytes, HeapSegments.offset(src) + offset, stride);
    }
}
