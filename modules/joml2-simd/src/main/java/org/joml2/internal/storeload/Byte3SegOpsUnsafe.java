// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte3SegOpsUnsafe extends Byte3SegOps {
    private static final Byte3SegOpsMS MS = new Byte3SegOpsMS();

    public MemorySegment store(Byte3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.data[0]);
        U.putByte(address + 1L, self.data[1]);
        U.putByte(address + 2L, self.data[2]);
        return dest;
    }

    private static MemorySegment storeOnHeap(Byte3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putByte(bytes, address, self.data[0]);
        U.putByte(bytes, address + 1L, self.data[1]);
        U.putByte(bytes, address + 2L, self.data[2]);
        return dest;
    }

    public Byte3 load(Byte3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        return self;
    }

    private static Byte3 loadOnHeap(Byte3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getByte(bytes, address);
        self.data[1] = U.getByte(bytes, address + 1L);
        self.data[2] = U.getByte(bytes, address + 2L);
        return self;
    }

    public MemorySegment storeShort(Byte3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        if (!dest.isNative()) return storeShortOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        return dest;
    }

    private static MemorySegment storeShortOnHeap(Byte3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeShort(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putShort(bytes, address, self.data[0]);
        U.putShort(bytes, address + 2L, self.data[1]);
        U.putShort(bytes, address + 4L, self.data[2]);
        return dest;
    }

    public Byte3 loadShort(Byte3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadShortOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (byte) U.getShort(address);
        self.data[1] = (byte) U.getShort(address + 2L);
        self.data[2] = (byte) U.getShort(address + 4L);
        return self;
    }

    private static Byte3 loadShortOnHeap(Byte3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadShort(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = (byte) U.getShort(bytes, address);
        self.data[1] = (byte) U.getShort(bytes, address + 2L);
        self.data[2] = (byte) U.getShort(bytes, address + 4L);
        return self;
    }
}
