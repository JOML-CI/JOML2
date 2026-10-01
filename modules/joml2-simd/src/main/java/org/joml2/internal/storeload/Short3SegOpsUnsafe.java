// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short3SegOpsUnsafe extends Short3SegOps {
    private static final Short3SegOpsMS MS = new Short3SegOpsMS();

    public MemorySegment store(Short3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        return dest;
    }

    private static MemorySegment storeOnHeap(Short3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putShort(bytes, address, self.data[0]);
        U.putShort(bytes, address + 2L, self.data[1]);
        U.putShort(bytes, address + 4L, self.data[2]);
        return dest;
    }

    public Short3 load(Short3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getShort(address);
        self.data[1] = U.getShort(address + 2L);
        self.data[2] = U.getShort(address + 4L);
        return self;
    }

    private static Short3 loadOnHeap(Short3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getShort(bytes, address);
        self.data[1] = U.getShort(bytes, address + 2L);
        self.data[2] = U.getShort(bytes, address + 4L);
        return self;
    }

    public MemorySegment storeByte(Short3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        if (!dest.isNative()) return storeByteOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, (byte) self.data[0]);
        U.putByte(address + 1L, (byte) self.data[1]);
        U.putByte(address + 2L, (byte) self.data[2]);
        return dest;
    }

    private static MemorySegment storeByteOnHeap(Short3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeByte(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putByte(bytes, address, (byte) self.data[0]);
        U.putByte(bytes, address + 1L, (byte) self.data[1]);
        U.putByte(bytes, address + 2L, (byte) self.data[2]);
        return dest;
    }

    public Short3 loadByte(Short3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadByteOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        return self;
    }

    private static Short3 loadByteOnHeap(Short3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadByte(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.data[0] = U.getByte(bytes, address);
        self.data[1] = U.getByte(bytes, address + 1L);
        self.data[2] = U.getByte(bytes, address + 2L);
        return self;
    }
}
