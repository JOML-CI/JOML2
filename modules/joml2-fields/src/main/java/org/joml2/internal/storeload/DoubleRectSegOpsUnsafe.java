// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRectSegOpsUnsafe extends DoubleRectSegOps {
    private static final DoubleRectSegOpsMS MS = new DoubleRectSegOpsMS();

    public MemorySegment store(DoubleRectImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleRectImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.minX);
        U.putDouble(bytes, address + 8L, self.minY);
        U.putDouble(bytes, address + 16L, self.maxX);
        U.putDouble(bytes, address + 24L, self.maxY);
        return dest;
    }

    public DoubleRect load(DoubleRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getDouble(address);
        self.minY = U.getDouble(address + 8L);
        self.maxX = U.getDouble(address + 16L);
        self.maxY = U.getDouble(address + 24L);
        return self;
    }

    private static DoubleRect loadOnHeap(DoubleRectImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.minX = U.getDouble(bytes, address);
        self.minY = U.getDouble(bytes, address + 8L);
        self.maxX = U.getDouble(bytes, address + 16L);
        self.maxY = U.getDouble(bytes, address + 24L);
        return self;
    }

    public MemorySegment storeFloat(DoubleRectImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.maxX);
        U.putFloat(address + 12L, (float) self.maxY);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleRectImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, (float) self.minX);
        U.putFloat(bytes, address + 4L, (float) self.minY);
        U.putFloat(bytes, address + 8L, (float) self.maxX);
        U.putFloat(bytes, address + 12L, (float) self.maxY);
        return dest;
    }

    public DoubleRect loadFloat(DoubleRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }

    private static DoubleRect loadFloatOnHeap(DoubleRectImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.minX = U.getFloat(bytes, address);
        self.minY = U.getFloat(bytes, address + 4L);
        self.maxX = U.getFloat(bytes, address + 8L);
        self.maxY = U.getFloat(bytes, address + 12L);
        return self;
    }
}
