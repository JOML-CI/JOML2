// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte3SegOpsUnsafe extends Byte3SegOps {

    private static final Byte3SegOpsMS MS = new Byte3SegOpsMS();

    public MemorySegment store(Byte3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putByte(address, self.data[0]);
        U.putByte(address + 1L, self.data[1]);
        U.putByte(address + 2L, self.data[2]);
        return dest;
    }
    public Byte3 load(Byte3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        return self;
    }
    public MemorySegment storeShort(Byte3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        long address = dest.address() + offset;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        return dest;
    }
    public Byte3 loadShort(Byte3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (byte) U.getShort(address);
        self.data[1] = (byte) U.getShort(address + 2L);
        self.data[2] = (byte) U.getShort(address + 4L);
        return self;
    }
}
