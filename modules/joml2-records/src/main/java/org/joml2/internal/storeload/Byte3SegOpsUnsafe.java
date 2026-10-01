// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte3SegOpsUnsafe extends Byte3SegOps {

    private static final Byte3SegOpsMS MS = new Byte3SegOpsMS();

    public MemorySegment store(Byte3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.x());
        U.putByte(address + 1L, self.y());
        U.putByte(address + 2L, self.z());
        return dest;
    }
    public Byte3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        byte _c0 = U.getByte(address);
        byte _c1 = U.getByte(address + 1L);
        byte _c2 = U.getByte(address + 2L);
        return new Byte3(_c0, _c1, _c2);
    }
    public MemorySegment storeShort(Byte3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        return dest;
    }
    public Byte3 loadShort(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(offset, src);
        long address = src.address() + offset;
        byte _c0 = (byte) U.getShort(address);
        byte _c1 = (byte) U.getShort(address + 2L);
        byte _c2 = (byte) U.getShort(address + 4L);
        return new Byte3(_c0, _c1, _c2);
    }
}
