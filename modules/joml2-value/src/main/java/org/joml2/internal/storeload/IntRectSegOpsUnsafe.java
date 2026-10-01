// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class IntRectSegOpsUnsafe implements IntRectSegOps {

    private static final IntRectSegOpsMS MS = new IntRectSegOpsMS();
    private static final IntRectRawOpsUnsafe RAW = new IntRectRawOpsUnsafe();

    public MemorySegment store(IntRect self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public IntRect load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeLong(IntRect self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        RAW.storeLongUnsafe(self, dest.address() + offset);
        return dest;
    }
    public IntRect loadLong(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(offset, src);
        return RAW.loadLongUnsafe(src.address() + offset);
    }
}
