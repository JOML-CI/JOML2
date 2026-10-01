// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRectSegOpsUnsafe extends DoubleRectSegOps {
    private static final DoubleRectSegOpsMS MS = new DoubleRectSegOpsMS();

    public MemorySegment store(DoubleRect self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.maxX());
        U.putDouble(address + 24L, self.maxY());
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleRect self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.minX());
        U.putDouble(bytes, address + 8L, self.minY());
        U.putDouble(bytes, address + 16L, self.maxX());
        U.putDouble(bytes, address + 24L, self.maxY());
        return dest;
    }

    public DoubleRect load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }

    private static DoubleRect loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8L);
        double _c2 = U.getDouble(bytes, address + 16L);
        double _c3 = U.getDouble(bytes, address + 24L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeFloat(DoubleRect self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.minX());
        U.putFloat(address + 4L, (float) self.minY());
        U.putFloat(address + 8L, (float) self.maxX());
        U.putFloat(address + 12L, (float) self.maxY());
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleRect self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, (float) self.minX());
        U.putFloat(bytes, address + 4L, (float) self.minY());
        U.putFloat(bytes, address + 8L, (float) self.maxX());
        U.putFloat(bytes, address + 12L, (float) self.maxY());
        return dest;
    }

    public DoubleRect loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }

    private static DoubleRect loadFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(offset, src);
        long address = HeapSegments.offset(src) + offset;
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4L);
        double _c2 = U.getFloat(bytes, address + 8L);
        double _c3 = U.getFloat(bytes, address + 12L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
}
