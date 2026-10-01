// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2SegOpsUnsafe extends Double2SegOps {
    private static final Double2SegOpsMS MS = new Double2SegOpsMS();

    public MemorySegment store(Double2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        return dest;
    }

    private static MemorySegment storeOnHeap(Double2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[1]);
        return dest;
    }

    public Double2 load(Double2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        return self;
    }

    private static Double2 loadOnHeap(Double2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getDouble(bytes, address);
        self.data[1] = U.getDouble(bytes, address + 8L);
        return self;
    }

    public MemorySegment storeFloat(Double2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(Double2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, (float) self.data[0]);
        U.putFloat(bytes, address + 4L, (float) self.data[1]);
        return dest;
    }

    public Double2 loadFloat(Double2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        return self;
    }

    private static Double2 loadFloatOnHeap(Double2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4L);
        return self;
    }
}
