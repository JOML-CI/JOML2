// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class Long3SegOpsUnsafe implements Long3SegOps {

    private static final Long3SegOpsMS MS = new Long3SegOpsMS();
    private static final Long3RawOpsUnsafe RAW = new Long3RawOpsUnsafe();

    public MemorySegment store(Long3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Long3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeInt(Long3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        RAW.storeIntUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Long3 loadInt(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(offset, src);
        return RAW.loadIntUnsafe(src.address() + offset);
    }
}
