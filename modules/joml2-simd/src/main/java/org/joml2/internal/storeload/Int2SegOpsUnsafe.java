// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Int2SegOpsUnsafe implements Int2SegOps {

    private static final Int2SegOpsMS MS = new Int2SegOpsMS();
    private static final Int2RawOpsUnsafe RAW = new Int2RawOpsUnsafe();

    public MemorySegment store(Int2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Int2 load(Int2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeLong(Int2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        RAW.storeLongUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Int2 loadLong(Int2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(self, offset, src);
        return RAW.loadLongUnsafe(self, src.address() + offset);
    }
}
