// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int2SegOpsUnsafe extends Int2SegOps {
    private static final Int2SegOpsMS MS = new Int2SegOpsMS();

    public MemorySegment store(Int2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.x());
        U.putInt(address + 4L, self.y());
        return dest;
    }

    private static MemorySegment storeOnHeap(Int2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putInt(bytes, address, self.x());
        U.putInt(bytes, address + 4L, self.y());
        return dest;
    }

    public Int2 load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        int _c0 = U.getInt(address);
        int _c1 = U.getInt(address + 4L);
        return new Int2(_c0, _c1);
    }

    private static Int2 loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        int _c0 = U.getInt(bytes, address);
        int _c1 = U.getInt(bytes, address + 4L);
        return new Int2(_c0, _c1);
    }

    public MemorySegment storeLong(Int2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        if (!dest.isNative()) return storeLongOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        return dest;
    }

    private static MemorySegment storeLongOnHeap(Int2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeLong(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.x());
        U.putLong(bytes, address + 8L, self.y());
        return dest;
    }

    public Int2 loadLong(long offset, MemorySegment src) {
        if (!src.isNative()) return loadLongOnHeap(offset, src);
        long address = src.address() + offset;
        int _c0 = (int) U.getLong(address);
        int _c1 = (int) U.getLong(address + 8L);
        return new Int2(_c0, _c1);
    }

    private static Int2 loadLongOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadLong(offset, src);
        long address = HeapSegments.offset(src) + offset;
        int _c0 = (int) U.getLong(bytes, address);
        int _c1 = (int) U.getLong(bytes, address + 8L);
        return new Int2(_c0, _c1);
    }
}
