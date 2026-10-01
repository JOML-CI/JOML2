// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class Short3SegOpsUnsafe implements Short3SegOps {

    private static final Short3SegOpsMS MS = new Short3SegOpsMS();
    private static final Short3RawOpsUnsafe RAW = new Short3RawOpsUnsafe();

    public MemorySegment store(Short3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Short3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeByte(Short3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        RAW.storeByteUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Short3 loadByte(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(offset, src);
        return RAW.loadByteUnsafe(src.address() + offset);
    }
}
