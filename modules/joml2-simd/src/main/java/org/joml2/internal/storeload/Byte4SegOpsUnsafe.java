// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte4SegOpsUnsafe extends Byte4SegOps {

    private static final Byte4SegOpsMS MS = new Byte4SegOpsMS();

    public MemorySegment store(Byte4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.data[0]);
        U.putByte(address + 1L, self.data[1]);
        U.putByte(address + 2L, self.data[2]);
        U.putByte(address + 3L, self.data[3]);
        return dest;
    }
    public Byte4 load(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        self.data[3] = U.getByte(address + 3L);
        return self;
    }
    public MemorySegment storeShort(Byte4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        U.putShort(address + 6L, self.data[3]);
        return dest;
    }
    public Byte4 loadShort(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (byte) U.getShort(address);
        self.data[1] = (byte) U.getShort(address + 2L);
        self.data[2] = (byte) U.getShort(address + 4L);
        self.data[3] = (byte) U.getShort(address + 6L);
        return self;
    }
}
