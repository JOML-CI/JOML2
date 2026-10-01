// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int3SegOpsUnsafe extends Int3SegOps {
    private static final Int3SegOpsMS MS = new Int3SegOpsMS();

    public MemorySegment store(Int3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.data[0]);
        U.putInt(address + 4L, self.data[1]);
        U.putInt(address + 8L, self.data[2]);
        return dest;
    }

    private static MemorySegment storeOnHeap(Int3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putInt(bytes, address, self.data[0]);
        U.putInt(bytes, address + 4L, self.data[1]);
        U.putInt(bytes, address + 8L, self.data[2]);
        return dest;
    }

    public Int3 load(Int3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        self.data[2] = U.getInt(address + 8L);
        return self;
    }

    private static Int3 loadOnHeap(Int3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getInt(bytes, address);
        self.data[1] = U.getInt(bytes, address + 4L);
        self.data[2] = U.getInt(bytes, address + 8L);
        return self;
    }

    public MemorySegment storeLong(Int3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        if (!dest.isNative()) return storeLongOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        return dest;
    }

    private static MemorySegment storeLongOnHeap(Int3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeLong(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.data[0]);
        U.putLong(bytes, address + 8L, self.data[1]);
        U.putLong(bytes, address + 16L, self.data[2]);
        return dest;
    }

    public Int3 loadLong(Int3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadLongOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (int) U.getLong(address);
        self.data[1] = (int) U.getLong(address + 8L);
        self.data[2] = (int) U.getLong(address + 16L);
        return self;
    }

    private static Int3 loadLongOnHeap(Int3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadLong(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = (int) U.getLong(bytes, address);
        self.data[1] = (int) U.getLong(bytes, address + 8L);
        self.data[2] = (int) U.getLong(bytes, address + 16L);
        return self;
    }
}
