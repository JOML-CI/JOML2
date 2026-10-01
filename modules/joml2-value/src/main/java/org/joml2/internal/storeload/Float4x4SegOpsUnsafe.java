// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4SegOpsUnsafe extends Float4x4SegOps {
    private static final Float4x4SegOpsMS MS = new Float4x4SegOpsMS();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public MemorySegment storeCM(Float4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c4 = _U.getFloat(address + 4L);
        float _c8 = _U.getFloat(address + 8L);
        float _c12 = _U.getFloat(address + 12L);
        float _c1 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c9 = _U.getFloat(address + 24L);
        float _c13 = _U.getFloat(address + 28L);
        float _c2 = _U.getFloat(address + 32L);
        float _c6 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c14 = _U.getFloat(address + 44L);
        float _c3 = _U.getFloat(address + 48L);
        float _c7 = _U.getFloat(address + 52L);
        float _c11 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadCMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c4 = _U.getFloat(bytes, address + 4L);
        float _c8 = _U.getFloat(bytes, address + 8L);
        float _c12 = _U.getFloat(bytes, address + 12L);
        float _c1 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c9 = _U.getFloat(bytes, address + 24L);
        float _c13 = _U.getFloat(bytes, address + 28L);
        float _c2 = _U.getFloat(bytes, address + 32L);
        float _c6 = _U.getFloat(bytes, address + 36L);
        float _c10 = _U.getFloat(bytes, address + 40L);
        float _c14 = _U.getFloat(bytes, address + 44L);
        float _c3 = _U.getFloat(bytes, address + 48L);
        float _c7 = _U.getFloat(bytes, address + 52L);
        float _c11 = _U.getFloat(bytes, address + 56L);
        float _c15 = _U.getFloat(bytes, address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public MemorySegment storeCMDouble(Float4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest);
        RAW.storeCMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadCMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c4 = (float) _U.getDouble(address + 8L);
        float _c8 = (float) _U.getDouble(address + 16L);
        float _c12 = (float) _U.getDouble(address + 24L);
        float _c1 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c9 = (float) _U.getDouble(address + 48L);
        float _c13 = (float) _U.getDouble(address + 56L);
        float _c2 = (float) _U.getDouble(address + 64L);
        float _c6 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c14 = (float) _U.getDouble(address + 88L);
        float _c3 = (float) _U.getDouble(address + 96L);
        float _c7 = (float) _U.getDouble(address + 104L);
        float _c11 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadCMDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(offset, src);
        return RAW.loadCMDoubleHeap(bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeRM(Float4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadRM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c6 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        float _c8 = _U.getFloat(address + 32L);
        float _c9 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c11 = _U.getFloat(address + 44L);
        float _c12 = _U.getFloat(address + 48L);
        float _c13 = _U.getFloat(address + 52L);
        float _c14 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadRMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c6 = _U.getFloat(bytes, address + 24L);
        float _c7 = _U.getFloat(bytes, address + 28L);
        float _c8 = _U.getFloat(bytes, address + 32L);
        float _c9 = _U.getFloat(bytes, address + 36L);
        float _c10 = _U.getFloat(bytes, address + 40L);
        float _c11 = _U.getFloat(bytes, address + 44L);
        float _c12 = _U.getFloat(bytes, address + 48L);
        float _c13 = _U.getFloat(bytes, address + 52L);
        float _c14 = _U.getFloat(bytes, address + 56L);
        float _c15 = _U.getFloat(bytes, address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public MemorySegment storeRMDouble(Float4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest);
        RAW.storeRMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Float4x4 loadRMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c6 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        float _c8 = (float) _U.getDouble(address + 64L);
        float _c9 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c11 = (float) _U.getDouble(address + 88L);
        float _c12 = (float) _U.getDouble(address + 96L);
        float _c13 = (float) _U.getDouble(address + 104L);
        float _c14 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadRMDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(offset, src);
        return RAW.loadRMDoubleHeap(bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeCM(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadCM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c4 = _U.getFloat(address + 4);
        float _c8 = _U.getFloat(address + 8);
        float _c12 = _U.getFloat(address + 12);
        float _c1 = _U.getFloat(_p1);
        float _c5 = _U.getFloat(_p1 + 4);
        float _c9 = _U.getFloat(_p1 + 8);
        float _c13 = _U.getFloat(_p1 + 12);
        float _c2 = _U.getFloat(_p2);
        float _c6 = _U.getFloat(_p2 + 4);
        float _c10 = _U.getFloat(_p2 + 8);
        float _c14 = _U.getFloat(_p2 + 12);
        float _c3 = _U.getFloat(_p3);
        float _c7 = _U.getFloat(_p3 + 4);
        float _c11 = _U.getFloat(_p3 + 8);
        float _c15 = _U.getFloat(_p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadCMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src, stride);
        return RAW.loadCMHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeCMDouble(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
        RAW.storeCMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadCMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMDoubleOnHeap(offset, src, stride);
        return RAW.loadCMDoubleUnsafe(src.address() + offset, stride);
    }

    private static Float4x4 loadCMDoubleOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(offset, src, stride);
        return RAW.loadCMDoubleHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRM(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4);
        float _c2 = _U.getFloat(address + 8);
        float _c3 = _U.getFloat(address + 12);
        float _c4 = _U.getFloat(_p1);
        float _c5 = _U.getFloat(_p1 + 4);
        float _c6 = _U.getFloat(_p1 + 8);
        float _c7 = _U.getFloat(_p1 + 12);
        float _c8 = _U.getFloat(_p2);
        float _c9 = _U.getFloat(_p2 + 4);
        float _c10 = _U.getFloat(_p2 + 8);
        float _c11 = _U.getFloat(_p2 + 12);
        float _c12 = _U.getFloat(_p3);
        float _c13 = _U.getFloat(_p3 + 4);
        float _c14 = _U.getFloat(_p3 + 8);
        float _c15 = _U.getFloat(_p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadRMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src, stride);
        return RAW.loadRMHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRMDouble(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
        RAW.storeRMDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Float4x4 loadRMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMDoubleOnHeap(offset, src, stride);
        return RAW.loadRMDoubleUnsafe(src.address() + offset, stride);
    }

    private static Float4x4 loadRMDoubleOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(offset, src, stride);
        return RAW.loadRMDoubleHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }
}
