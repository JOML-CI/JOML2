// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2SegOpsUnsafe extends Byte2SegOps {

    private static final Byte2SegOpsMS MS = new Byte2SegOpsMS();

    public MemorySegment store(Byte2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.x);
        U.putByte(address + 1L, self.y);
        return dest;
    }
    public Byte2 load(Byte2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        return self;
    }
    public MemorySegment storeShort(Byte2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return dest;
    }
    public Byte2 loadShort(Byte2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(self, offset, src);
        long address = src.address() + offset;
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        return self;
    }
}
