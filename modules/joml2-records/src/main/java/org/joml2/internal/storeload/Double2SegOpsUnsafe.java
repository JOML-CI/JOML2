// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2SegOpsUnsafe extends Double2SegOps {
    private static final Double2SegOpsMS MS = new Double2SegOpsMS();

    public MemorySegment store(Double2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        return dest;
    }

    private static MemorySegment storeOnHeap(Double2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.x());
        U.putDouble(bytes, address + 8L, self.y());
        return dest;
    }

    public Double2 load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        return new Double2(_c0, _c1);
    }

    private static Double2 loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8L);
        return new Double2(_c0, _c1);
    }

    public MemorySegment storeFloat(Double2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.x());
        U.putFloat(address + 4L, (float) self.y());
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(Double2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, (float) self.x());
        U.putFloat(bytes, address + 4L, (float) self.y());
        return dest;
    }

    public Double2 loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        return new Double2(_c0, _c1);
    }

    private static Double2 loadFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(offset, src);
        long address = HeapSegments.offset(src) + offset;
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4L);
        return new Double2(_c0, _c1);
    }
}
