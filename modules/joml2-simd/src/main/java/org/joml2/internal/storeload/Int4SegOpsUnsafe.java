// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int4SegOpsUnsafe extends Int4SegOps {
    private static final Int4SegOpsMS MS = new Int4SegOpsMS();

    public MemorySegment storeLong(Int4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        if (!dest.isNative()) return storeLongOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        U.putLong(address + 24L, self.data[3]);
        return dest;
    }

    private static MemorySegment storeLongOnHeap(Int4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeLong(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.data[0]);
        U.putLong(bytes, address + 8L, self.data[1]);
        U.putLong(bytes, address + 16L, self.data[2]);
        U.putLong(bytes, address + 24L, self.data[3]);
        return dest;
    }

    public Int4 loadLong(Int4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadLongOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (int) U.getLong(address);
        self.data[1] = (int) U.getLong(address + 8L);
        self.data[2] = (int) U.getLong(address + 16L);
        self.data[3] = (int) U.getLong(address + 24L);
        return self;
    }

    private static Int4 loadLongOnHeap(Int4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadLong(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = (int) U.getLong(bytes, address);
        self.data[1] = (int) U.getLong(bytes, address + 8L);
        self.data[2] = (int) U.getLong(bytes, address + 16L);
        self.data[3] = (int) U.getLong(bytes, address + 24L);
        return self;
    }
}
