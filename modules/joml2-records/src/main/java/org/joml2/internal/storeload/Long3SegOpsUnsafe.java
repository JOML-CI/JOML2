// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long3SegOpsUnsafe extends Long3SegOps {
    private static final Long3SegOpsMS MS = new Long3SegOpsMS();

    public MemorySegment store(Long3 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        return dest;
    }

    private static MemorySegment storeOnHeap(Long3 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putLong(bytes, address, self.x());
        U.putLong(bytes, address + 8L, self.y());
        U.putLong(bytes, address + 16L, self.z());
        return dest;
    }

    public Long3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        long _c0 = U.getLong(address);
        long _c1 = U.getLong(address + 8L);
        long _c2 = U.getLong(address + 16L);
        return new Long3(_c0, _c1, _c2);
    }

    private static Long3 loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        long _c0 = U.getLong(bytes, address);
        long _c1 = U.getLong(bytes, address + 8L);
        long _c2 = U.getLong(bytes, address + 16L);
        return new Long3(_c0, _c1, _c2);
    }

    public MemorySegment storeInt(Long3 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        if (!dest.isNative()) return storeIntOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        return dest;
    }

    private static MemorySegment storeIntOnHeap(Long3 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeInt(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putInt(bytes, address, (int) self.x());
        U.putInt(bytes, address + 4L, (int) self.y());
        U.putInt(bytes, address + 8L, (int) self.z());
        return dest;
    }

    public Long3 loadInt(long offset, MemorySegment src) {
        if (!src.isNative()) return loadIntOnHeap(offset, src);
        long address = src.address() + offset;
        long _c0 = U.getInt(address);
        long _c1 = U.getInt(address + 4L);
        long _c2 = U.getInt(address + 8L);
        return new Long3(_c0, _c1, _c2);
    }

    private static Long3 loadIntOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadInt(offset, src);
        long address = HeapSegments.offset(src) + offset;
        long _c0 = U.getInt(bytes, address);
        long _c1 = U.getInt(bytes, address + 4L);
        long _c2 = U.getInt(bytes, address + 8L);
        return new Long3(_c0, _c1, _c2);
    }
}
