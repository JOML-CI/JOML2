// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short2SegOpsUnsafe extends Short2SegOps {

    private static final Short2SegOpsMS MS = new Short2SegOpsMS();

    public MemorySegment store(Short2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        return dest;
    }
    public Short2 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        short _c0 = U.getShort(address);
        short _c1 = U.getShort(address + 2L);
        return new Short2(_c0, _c1);
    }
    public MemorySegment storeByte(Short2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        return dest;
    }
    public Short2 loadByte(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(offset, src);
        long address = src.address() + offset;
        short _c0 = U.getByte(address);
        short _c1 = U.getByte(address + 1L);
        return new Short2(_c0, _c1);
    }
}
