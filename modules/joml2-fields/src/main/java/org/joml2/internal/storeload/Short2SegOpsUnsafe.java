// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short2SegOpsUnsafe extends Short2SegOps {

    private static final Short2SegOpsMS MS = new Short2SegOpsMS();

    public MemorySegment store(Short2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return dest;
    }
    public Short2 load(Short2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getShort(address);
        self.y = U.getShort(address + 2L);
        return self;
    }
    public MemorySegment storeByte(Short2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, (byte) self.x);
        U.putByte(address + 1L, (byte) self.y);
        return dest;
    }
    public Short2 loadByte(Short2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        return self;
    }
}
