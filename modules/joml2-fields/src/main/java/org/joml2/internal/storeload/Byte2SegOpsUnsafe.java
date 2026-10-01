// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2SegOpsUnsafe extends Byte2SegOps {
    private static final Byte2SegOpsMS MS = new Byte2SegOpsMS();

    public MemorySegment store(Byte2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.x);
        U.putByte(address + 1L, self.y);
        return dest;
    }

    private static MemorySegment storeOnHeap(Byte2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putByte(bytes, address, self.x);
        U.putByte(bytes, address + 1L, self.y);
        return dest;
    }

    public Byte2 load(Byte2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        return self;
    }

    private static Byte2 loadOnHeap(Byte2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.x = U.getByte(bytes, address);
        self.y = U.getByte(bytes, address + 1L);
        return self;
    }

    public MemorySegment storeShort(Byte2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        if (!dest.isNative()) return storeShortOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return dest;
    }

    private static MemorySegment storeShortOnHeap(Byte2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeShort(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putShort(bytes, address, self.x);
        U.putShort(bytes, address + 2L, self.y);
        return dest;
    }

    public Byte2 loadShort(Byte2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadShortOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        return self;
    }

    private static Byte2 loadShortOnHeap(Byte2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadShort(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.x = (byte) U.getShort(bytes, address);
        self.y = (byte) U.getShort(bytes, address + 2L);
        return self;
    }
}
