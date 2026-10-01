// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short4SegOpsUnsafe extends Short4SegOps {

    private static final Short4SegOpsMS MS = new Short4SegOpsMS();

    public MemorySegment store(Short4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return dest;
    }
    public Short4 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        short _c0 = U.getShort(address);
        short _c1 = U.getShort(address + 2L);
        short _c2 = U.getShort(address + 4L);
        short _c3 = U.getShort(address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeByte(Short4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        U.putByte(address + 2L, (byte) self.z());
        U.putByte(address + 3L, (byte) self.w());
        return dest;
    }
    public Short4 loadByte(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(offset, src);
        long address = src.address() + offset;
        short _c0 = U.getByte(address);
        short _c1 = U.getByte(address + 1L);
        short _c2 = U.getByte(address + 2L);
        short _c3 = U.getByte(address + 3L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
}
