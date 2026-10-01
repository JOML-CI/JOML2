// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2SegOpsUnsafe extends Byte2SegOps {

    private static final Byte2SegOpsMS MS = new Byte2SegOpsMS();

    public MemorySegment store(Byte2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.x());
        U.putByte(address + 1L, self.y());
        return dest;
    }
    public Byte2 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        byte _c0 = U.getByte(address);
        byte _c1 = U.getByte(address + 1L);
        return new Byte2(_c0, _c1);
    }
    public MemorySegment storeShort(Byte2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        return dest;
    }
    public Byte2 loadShort(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(offset, src);
        long address = src.address() + offset;
        byte _c0 = (byte) U.getShort(address);
        byte _c1 = (byte) U.getShort(address + 2L);
        return new Byte2(_c0, _c1);
    }
}
