// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4SegOpsUnsafe extends Double4x4SegOps {
    private static final Double4x4SegOpsMS MS = new Double4x4SegOpsMS();
    private static final Double4x4RawOpsUnsafe RAW = new Double4x4RawOpsUnsafe();

    public MemorySegment storeCM(Double4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c4 = _U.getDouble(address + 8L);
        double _c8 = _U.getDouble(address + 16L);
        double _c12 = _U.getDouble(address + 24L);
        double _c1 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c9 = _U.getDouble(address + 48L);
        double _c13 = _U.getDouble(address + 56L);
        double _c2 = _U.getDouble(address + 64L);
        double _c6 = _U.getDouble(address + 72L);
        double _c10 = _U.getDouble(address + 80L);
        double _c14 = _U.getDouble(address + 88L);
        double _c3 = _U.getDouble(address + 96L);
        double _c7 = _U.getDouble(address + 104L);
        double _c11 = _U.getDouble(address + 112L);
        double _c15 = _U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Double4x4 loadCMOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c4 = _U.getDouble(bytes, address + 8L);
        double _c8 = _U.getDouble(bytes, address + 16L);
        double _c12 = _U.getDouble(bytes, address + 24L);
        double _c1 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        double _c9 = _U.getDouble(bytes, address + 48L);
        double _c13 = _U.getDouble(bytes, address + 56L);
        double _c2 = _U.getDouble(bytes, address + 64L);
        double _c6 = _U.getDouble(bytes, address + 72L);
        double _c10 = _U.getDouble(bytes, address + 80L);
        double _c14 = _U.getDouble(bytes, address + 88L);
        double _c3 = _U.getDouble(bytes, address + 96L);
        double _c7 = _U.getDouble(bytes, address + 104L);
        double _c11 = _U.getDouble(bytes, address + 112L);
        double _c15 = _U.getDouble(bytes, address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public MemorySegment storeCMFloat(Double4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        RAW.storeCMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadCMFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c4 = _U.getFloat(address + 4L);
        double _c8 = _U.getFloat(address + 8L);
        double _c12 = _U.getFloat(address + 12L);
        double _c1 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c9 = _U.getFloat(address + 24L);
        double _c13 = _U.getFloat(address + 28L);
        double _c2 = _U.getFloat(address + 32L);
        double _c6 = _U.getFloat(address + 36L);
        double _c10 = _U.getFloat(address + 40L);
        double _c14 = _U.getFloat(address + 44L);
        double _c3 = _U.getFloat(address + 48L);
        double _c7 = _U.getFloat(address + 52L);
        double _c11 = _U.getFloat(address + 56L);
        double _c15 = _U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Double4x4 loadCMFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(offset, src);
        return RAW.loadCMFloatHeap(bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeRM(Double4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadRM(long offset, MemorySegment src) {
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
        double _c12 = _U.getDouble(address + 96L);
        double _c13 = _U.getDouble(address + 104L);
        double _c14 = _U.getDouble(address + 112L);
        double _c15 = _U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Double4x4 loadRMOnHeap(long offset, MemorySegment src) {
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
        double _c12 = _U.getDouble(bytes, address + 96L);
        double _c13 = _U.getDouble(bytes, address + 104L);
        double _c14 = _U.getDouble(bytes, address + 112L);
        double _c15 = _U.getDouble(bytes, address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public MemorySegment storeRMFloat(Double4x4 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x4 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        RAW.storeRMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadRMFloat(long offset, MemorySegment src) {
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
        double _c12 = _U.getFloat(address + 48L);
        double _c13 = _U.getFloat(address + 52L);
        double _c14 = _U.getFloat(address + 56L);
        double _c15 = _U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Double4x4 loadRMFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(offset, src);
        return RAW.loadRMFloatHeap(bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeCM(Double4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadCM(long offset, MemorySegment src, int stride) {
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
        double _c12 = _U.getDouble(address + 24);
        double _c1 = _U.getDouble(_p1);
        double _c5 = _U.getDouble(_p1 + 8);
        double _c9 = _U.getDouble(_p1 + 16);
        double _c13 = _U.getDouble(_p1 + 24);
        double _c2 = _U.getDouble(_p2);
        double _c6 = _U.getDouble(_p2 + 8);
        double _c10 = _U.getDouble(_p2 + 16);
        double _c14 = _U.getDouble(_p2 + 24);
        double _c3 = _U.getDouble(_p3);
        double _c7 = _U.getDouble(_p3 + 8);
        double _c11 = _U.getDouble(_p3 + 16);
        double _c15 = _U.getDouble(_p3 + 24);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Double4x4 loadCMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(offset, src, stride);
        return RAW.loadCMHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeCMFloat(Double4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        RAW.storeCMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadCMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(offset, src, stride);
        return RAW.loadCMFloatUnsafe(src.address() + offset, stride);
    }

    private static Double4x4 loadCMFloatOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(offset, src, stride);
        return RAW.loadCMFloatHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRM(Double4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        double _c12 = _U.getDouble(_p3);
        double _c13 = _U.getDouble(_p3 + 8);
        double _c14 = _U.getDouble(_p3 + 16);
        double _c15 = _U.getDouble(_p3 + 24);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Double4x4 loadRMOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(offset, src, stride);
        return RAW.loadRMHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }

    public MemorySegment storeRMFloat(Double4x4 self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x4 self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        RAW.storeRMFloatHeap(self, bytes, HeapSegments.offset(dest) + offset, stride);
        return dest;
    }

    public Double4x4 loadRMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(offset, src, stride);
        return RAW.loadRMFloatUnsafe(src.address() + offset, stride);
    }

    private static Double4x4 loadRMFloatOnHeap(long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(offset, src, stride);
        return RAW.loadRMFloatHeap(bytes, HeapSegments.offset(src) + offset, stride);
    }
}
