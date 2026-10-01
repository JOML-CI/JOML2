// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class Double4SegOpsUnsafe implements Double4SegOps {

    private static final Double4SegOpsMS MS = new Double4SegOpsMS();
    private static final Double4RawOpsUnsafe RAW = new Double4RawOpsUnsafe();

    public MemorySegment store(Double4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double4 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeFloat(Double4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double4 loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(offset, src);
        return RAW.loadFloatUnsafe(src.address() + offset);
    }
}
