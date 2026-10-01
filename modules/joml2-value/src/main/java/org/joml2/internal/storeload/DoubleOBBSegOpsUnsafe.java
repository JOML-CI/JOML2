// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class DoubleOBBSegOpsUnsafe implements DoubleOBBSegOps {

    private static final DoubleOBBSegOpsMS MS = new DoubleOBBSegOpsMS();
    private static final DoubleOBBRawOpsUnsafe RAW = new DoubleOBBRawOpsUnsafe();

    public MemorySegment store(DoubleOBB self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleOBB load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeFloat(DoubleOBB self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleOBB loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(offset, src);
        return RAW.loadFloatUnsafe(src.address() + offset);
    }
}
