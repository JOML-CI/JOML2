// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2SegOpsUnsafe extends Long2SegOps {
    private static final Long2SegOpsMS MS = new Long2SegOpsMS();

    public MemorySegment store(Long2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        return dest;
    }

    private static MemorySegment storeOnHeap(Long2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.data[0]);
        U.putLong(bytes, address + 8L, self.data[1]);
        return dest;
    }

    public Long2 load(Long2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getLong(address);
        self.data[1] = U.getLong(address + 8L);
        return self;
    }

    private static Long2 loadOnHeap(Long2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getLong(bytes, address);
        self.data[1] = U.getLong(bytes, address + 8L);
        return self;
    }

    public MemorySegment storeInt(Long2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        if (!dest.isNative()) return storeIntOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.data[0]);
        U.putInt(address + 4L, (int) self.data[1]);
        return dest;
    }

    private static MemorySegment storeIntOnHeap(Long2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeInt(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putInt(bytes, address, (int) self.data[0]);
        U.putInt(bytes, address + 4L, (int) self.data[1]);
        return dest;
    }

    public Long2 loadInt(Long2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadIntOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        return self;
    }

    private static Long2 loadIntOnHeap(Long2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadInt(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getInt(bytes, address);
        self.data[1] = U.getInt(bytes, address + 4L);
        return self;
    }
}
