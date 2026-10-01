// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2SegOpsUnsafe extends Byte2SegOps {
    private static final Byte2SegOpsMS MS = new Byte2SegOpsMS();

    public MemorySegment store(Byte2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.x());
        U.putByte(address + 1L, self.y());
        return dest;
    }

    private static MemorySegment storeOnHeap(Byte2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putByte(bytes, address, self.x());
        U.putByte(bytes, address + 1L, self.y());
        return dest;
    }

    public Byte2 load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        byte _c0 = U.getByte(address);
        byte _c1 = U.getByte(address + 1L);
        return new Byte2(_c0, _c1);
    }

    private static Byte2 loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        byte _c0 = U.getByte(bytes, address);
        byte _c1 = U.getByte(bytes, address + 1L);
        return new Byte2(_c0, _c1);
    }

    public MemorySegment storeShort(Byte2 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        if (!dest.isNative()) return storeShortOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        return dest;
    }

    private static MemorySegment storeShortOnHeap(Byte2 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeShort(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putShort(bytes, address, self.x());
        U.putShort(bytes, address + 2L, self.y());
        return dest;
    }

    public Byte2 loadShort(long offset, MemorySegment src) {
        if (!src.isNative()) return loadShortOnHeap(offset, src);
        long address = src.address() + offset;
        byte _c0 = (byte) U.getShort(address);
        byte _c1 = (byte) U.getShort(address + 2L);
        return new Byte2(_c0, _c1);
    }

    private static Byte2 loadShortOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadShort(offset, src);
        long address = HeapSegments.offset(src) + offset;
        byte _c0 = (byte) U.getShort(bytes, address);
        byte _c1 = (byte) U.getShort(bytes, address + 2L);
        return new Byte2(_c0, _c1);
    }
}
