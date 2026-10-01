// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class FloatRaySegOpsUnsafe implements FloatRaySegOps {

    private static final FloatRaySegOpsMS MS = new FloatRaySegOpsMS();
    private static final FloatRayRawOpsUnsafe RAW = new FloatRayRawOpsUnsafe();

    public MemorySegment store(FloatRayImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatRay load(FloatRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeDouble(FloatRayImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatRay loadDouble(FloatRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        return RAW.loadDoubleUnsafe(self, src.address() + offset);
    }
}
