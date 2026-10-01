// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class Int4SegOpsUnsafe implements Int4SegOps {

    private static final Int4SegOpsMS MS = new Int4SegOpsMS();
    private static final Int4RawOpsUnsafe RAW = new Int4RawOpsUnsafe();

    public MemorySegment store(Int4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Int4 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeLong(Int4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        RAW.storeLongUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Int4 loadLong(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(offset, src);
        return RAW.loadLongUnsafe(src.address() + offset);
    }
}
