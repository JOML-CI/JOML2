// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Int3SegOpsUnsafe implements Int3SegOps {

    private static final Int3SegOpsMS MS = new Int3SegOpsMS();
    private static final Int3RawOpsUnsafe RAW = new Int3RawOpsUnsafe();

    public MemorySegment store(Int3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Int3 load(Int3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeLong(Int3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        RAW.storeLongUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Int3 loadLong(Int3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(self, offset, src);
        return RAW.loadLongUnsafe(self, src.address() + offset);
    }
}
