// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRectSegOpsUnsafe extends FloatRectSegOps {
    private static final FloatRectSegOpsMS MS = new FloatRectSegOpsMS();

    public MemorySegment store(FloatRectImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.minX);
        U.putFloat(address + 4L, self.minY);
        U.putFloat(address + 8L, self.maxX);
        U.putFloat(address + 12L, self.maxY);
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatRectImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, self.minX);
        U.putFloat(bytes, address + 4L, self.minY);
        U.putFloat(bytes, address + 8L, self.maxX);
        U.putFloat(bytes, address + 12L, self.maxY);
        return dest;
    }

    public FloatRect load(FloatRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }

    private static FloatRect loadOnHeap(FloatRectImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.minX = U.getFloat(bytes, address);
        self.minY = U.getFloat(bytes, address + 4L);
        self.maxX = U.getFloat(bytes, address + 8L);
        self.maxY = U.getFloat(bytes, address + 12L);
        return self;
    }

    public MemorySegment storeDouble(FloatRectImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(FloatRectImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.minX);
        U.putDouble(bytes, address + 8L, self.minY);
        U.putDouble(bytes, address + 16L, self.maxX);
        U.putDouble(bytes, address + 24L, self.maxY);
        return dest;
    }

    public FloatRect loadDouble(FloatRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.minX = (float) U.getDouble(address);
        self.minY = (float) U.getDouble(address + 8L);
        self.maxX = (float) U.getDouble(address + 16L);
        self.maxY = (float) U.getDouble(address + 24L);
        return self;
    }

    private static FloatRect loadDoubleOnHeap(FloatRectImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.minX = (float) U.getDouble(bytes, address);
        self.minY = (float) U.getDouble(bytes, address + 8L);
        self.maxX = (float) U.getDouble(bytes, address + 16L);
        self.maxY = (float) U.getDouble(bytes, address + 24L);
        return self;
    }
}
