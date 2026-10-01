// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectSegOpsUnsafe extends IntRectSegOps {
    private static final IntRectSegOpsMS MS = new IntRectSegOpsMS();

    public MemorySegment store(IntRect self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.minX());
        U.putInt(address + 4L, self.minY());
        U.putInt(address + 8L, self.maxX());
        U.putInt(address + 12L, self.maxY());
        return dest;
    }

    private static MemorySegment storeOnHeap(IntRect self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putInt(bytes, address, self.minX());
        U.putInt(bytes, address + 4L, self.minY());
        U.putInt(bytes, address + 8L, self.maxX());
        U.putInt(bytes, address + 12L, self.maxY());
        return dest;
    }

    public IntRect load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        int _c0 = U.getInt(address);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        int _c3 = U.getInt(address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    private static IntRect loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        int _c0 = U.getInt(bytes, address);
        int _c1 = U.getInt(bytes, address + 4L);
        int _c2 = U.getInt(bytes, address + 8L);
        int _c3 = U.getInt(bytes, address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeLong(IntRect self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        if (!dest.isNative()) return storeLongOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.minX());
        U.putLong(address + 8L, self.minY());
        U.putLong(address + 16L, self.maxX());
        U.putLong(address + 24L, self.maxY());
        return dest;
    }

    private static MemorySegment storeLongOnHeap(IntRect self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeLong(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.minX());
        U.putLong(bytes, address + 8L, self.minY());
        U.putLong(bytes, address + 16L, self.maxX());
        U.putLong(bytes, address + 24L, self.maxY());
        return dest;
    }

    public IntRect loadLong(long offset, MemorySegment src) {
        if (!src.isNative()) return loadLongOnHeap(offset, src);
        long address = src.address() + offset;
        int _c0 = (int) U.getLong(address);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        int _c3 = (int) U.getLong(address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    private static IntRect loadLongOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadLong(offset, src);
        long address = HeapSegments.offset(src) + offset;
        int _c0 = (int) U.getLong(bytes, address);
        int _c1 = (int) U.getLong(bytes, address + 8L);
        int _c2 = (int) U.getLong(bytes, address + 16L);
        int _c3 = (int) U.getLong(bytes, address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
}
