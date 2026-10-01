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
        U.putByte(address, self.x);
        U.putByte(address + 1L, self.y);
        U.putByte(address + 2L, self.z);
        U.putByte(address + 3L, self.w);
        return dest;
    }
    public Byte4 load(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        self.z = U.getByte(address + 2L);
        self.w = U.getByte(address + 3L);
        return self;
    }
    public MemorySegment storeShort(Byte4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        U.putShort(address + 6L, self.w);
        return dest;
    }
    public Byte4 loadShort(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(self, offset, src);
        long address = src.address() + offset;
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        self.z = (byte) U.getShort(address + 4L);
        self.w = (byte) U.getShort(address + 6L);
        return self;
    }
}
