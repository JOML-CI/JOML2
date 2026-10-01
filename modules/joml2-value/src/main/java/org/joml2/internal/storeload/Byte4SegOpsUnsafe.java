// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte4SegOpsUnsafe extends Byte4SegOps {

    private static final Byte4SegOpsMS MS = new Byte4SegOpsMS();

    public MemorySegment store(Byte4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.x());
        U.putByte(address + 1L, self.y());
        U.putByte(address + 2L, self.z());
        U.putByte(address + 3L, self.w());
        return dest;
    }
    public Byte4 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        byte _c0 = U.getByte(address);
        byte _c1 = U.getByte(address + 1L);
        byte _c2 = U.getByte(address + 2L);
        byte _c3 = U.getByte(address + 3L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeShort(Byte4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return dest;
    }
    public Byte4 loadShort(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(offset, src);
        long address = src.address() + offset;
        byte _c0 = (byte) U.getShort(address);
        byte _c1 = (byte) U.getShort(address + 2L);
        byte _c2 = (byte) U.getShort(address + 4L);
        byte _c3 = (byte) U.getShort(address + 6L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
}
