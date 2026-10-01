// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short4SegOpsUnsafe extends Short4SegOps {

    private static final Short4SegOpsMS MS = new Short4SegOpsMS();

    public MemorySegment storeByte(Short4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, (byte) self.data[0]);
        U.putByte(address + 1L, (byte) self.data[1]);
        U.putByte(address + 2L, (byte) self.data[2]);
        U.putByte(address + 3L, (byte) self.data[3]);
        return dest;
    }
    public Short4 loadByte(Short4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        self.data[3] = U.getByte(address + 3L);
        return self;
    }
}
