// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int2SegOpsUnsafe extends Int2SegOps {
    private static final Int2SegOpsMS MS = new Int2SegOpsMS();

    public MemorySegment store(Int2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.x);
        U.putInt(address + 4L, self.y);
        return dest;
    }

    private static MemorySegment storeOnHeap(Int2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putInt(bytes, address, self.x);
        U.putInt(bytes, address + 4L, self.y);
        return dest;
    }

    public Int2 load(Int2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        return self;
    }

    private static Int2 loadOnHeap(Int2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.x = U.getInt(bytes, address);
        self.y = U.getInt(bytes, address + 4L);
        return self;
    }

    public MemorySegment storeLong(Int2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        if (!dest.isNative()) return storeLongOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        return dest;
    }

    private static MemorySegment storeLongOnHeap(Int2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeLong(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.x);
        U.putLong(bytes, address + 8L, self.y);
        return dest;
    }

    public Int2 loadLong(Int2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadLongOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.x = (int) U.getLong(address);
        self.y = (int) U.getLong(address + 8L);
        return self;
    }

    private static Int2 loadLongOnHeap(Int2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadLong(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.x = (int) U.getLong(bytes, address);
        self.y = (int) U.getLong(bytes, address + 8L);
        return self;
    }
}
