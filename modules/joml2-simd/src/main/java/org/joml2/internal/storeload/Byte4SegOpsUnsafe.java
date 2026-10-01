// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte4SegOpsUnsafe extends Byte4SegOps {
    private static final Byte4SegOpsMS MS = new Byte4SegOpsMS();

    public MemorySegment store(Byte4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.data[0]);
        U.putByte(address + 1L, self.data[1]);
        U.putByte(address + 2L, self.data[2]);
        U.putByte(address + 3L, self.data[3]);
        return dest;
    }

    private static MemorySegment storeOnHeap(Byte4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putByte(bytes, address, self.data[0]);
        U.putByte(bytes, address + 1L, self.data[1]);
        U.putByte(bytes, address + 2L, self.data[2]);
        U.putByte(bytes, address + 3L, self.data[3]);
        return dest;
    }

    public Byte4 load(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        self.data[3] = U.getByte(address + 3L);
        return self;
    }

    private static Byte4 loadOnHeap(Byte4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getByte(bytes, address);
        self.data[1] = U.getByte(bytes, address + 1L);
        self.data[2] = U.getByte(bytes, address + 2L);
        self.data[3] = U.getByte(bytes, address + 3L);
        return self;
    }

    public MemorySegment storeShort(Byte4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        if (!dest.isNative()) return storeShortOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        U.putShort(address + 6L, self.data[3]);
        return dest;
    }

    private static MemorySegment storeShortOnHeap(Byte4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeShort(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putShort(bytes, address, self.data[0]);
        U.putShort(bytes, address + 2L, self.data[1]);
        U.putShort(bytes, address + 4L, self.data[2]);
        U.putShort(bytes, address + 6L, self.data[3]);
        return dest;
    }

    public Byte4 loadShort(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadShortOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (byte) U.getShort(address);
        self.data[1] = (byte) U.getShort(address + 2L);
        self.data[2] = (byte) U.getShort(address + 4L);
        self.data[3] = (byte) U.getShort(address + 6L);
        return self;
    }

    private static Byte4 loadShortOnHeap(Byte4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadShort(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = (byte) U.getShort(bytes, address);
        self.data[1] = (byte) U.getShort(bytes, address + 2L);
        self.data[2] = (byte) U.getShort(bytes, address + 4L);
        self.data[3] = (byte) U.getShort(bytes, address + 6L);
        return self;
    }
}
