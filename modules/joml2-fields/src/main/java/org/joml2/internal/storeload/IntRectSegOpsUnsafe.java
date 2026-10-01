// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectSegOpsUnsafe extends IntRectSegOps {
    private static final IntRectSegOpsMS MS = new IntRectSegOpsMS();

    public MemorySegment store(IntRectImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.minX);
        U.putInt(address + 4L, self.minY);
        U.putInt(address + 8L, self.maxX);
        U.putInt(address + 12L, self.maxY);
        return dest;
    }

    private static MemorySegment storeOnHeap(IntRectImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putInt(bytes, address, self.minX);
        U.putInt(bytes, address + 4L, self.minY);
        U.putInt(bytes, address + 8L, self.maxX);
        U.putInt(bytes, address + 12L, self.maxY);
        return dest;
    }

    public IntRect load(IntRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getInt(address);
        self.minY = U.getInt(address + 4L);
        self.maxX = U.getInt(address + 8L);
        self.maxY = U.getInt(address + 12L);
        return self;
    }

    private static IntRect loadOnHeap(IntRectImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.minX = U.getInt(bytes, address);
        self.minY = U.getInt(bytes, address + 4L);
        self.maxX = U.getInt(bytes, address + 8L);
        self.maxY = U.getInt(bytes, address + 12L);
        return self;
    }

    public MemorySegment storeLong(IntRectImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        if (!dest.isNative()) return storeLongOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.minX);
        U.putLong(address + 8L, self.minY);
        U.putLong(address + 16L, self.maxX);
        U.putLong(address + 24L, self.maxY);
        return dest;
    }

    private static MemorySegment storeLongOnHeap(IntRectImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeLong(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.minX);
        U.putLong(bytes, address + 8L, self.minY);
        U.putLong(bytes, address + 16L, self.maxX);
        U.putLong(bytes, address + 24L, self.maxY);
        return dest;
    }

    public IntRect loadLong(IntRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadLongOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.minX = (int) U.getLong(address);
        self.minY = (int) U.getLong(address + 8L);
        self.maxX = (int) U.getLong(address + 16L);
        self.maxY = (int) U.getLong(address + 24L);
        return self;
    }

    private static IntRect loadLongOnHeap(IntRectImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadLong(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.minX = (int) U.getLong(bytes, address);
        self.minY = (int) U.getLong(bytes, address + 8L);
        self.maxX = (int) U.getLong(bytes, address + 16L);
        self.maxY = (int) U.getLong(bytes, address + 24L);
        return self;
    }
}
