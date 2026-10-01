// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class DoubleRaySegOpsUnsafe implements DoubleRaySegOps {

    private static final DoubleRaySegOpsMS MS = new DoubleRaySegOpsMS();
    private static final DoubleRayRawOpsUnsafe RAW = new DoubleRayRawOpsUnsafe();

    public MemorySegment store(DoubleRayImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleRay load(DoubleRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeFloat(DoubleRayImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleRay loadFloat(DoubleRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
        return RAW.loadFloatUnsafe(self, src.address() + offset);
    }
}
