// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short3SegOpsUnsafe extends Short3SegOps {

    private static final Short3SegOpsMS MS = new Short3SegOpsMS();

    public MemorySegment store(Short3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        return dest;
    }
    public Short3 load(Short3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getShort(address);
        self.data[1] = U.getShort(address + 2L);
        self.data[2] = U.getShort(address + 4L);
        return self;
    }
    public MemorySegment storeByte(Short3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, (byte) self.data[0]);
        U.putByte(address + 1L, (byte) self.data[1]);
        U.putByte(address + 2L, (byte) self.data[2]);
        return dest;
    }
    public Short3 loadByte(Short3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        return self;
    }
}
