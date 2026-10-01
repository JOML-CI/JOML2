// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class FloatPlaneSegOpsUnsafe implements FloatPlaneSegOps {

    private static final FloatPlaneSegOpsMS MS = new FloatPlaneSegOpsMS();
    private static final FloatPlaneRawOpsUnsafe RAW = new FloatPlaneRawOpsUnsafe();

    public MemorySegment store(FloatPlane self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatPlane load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeDouble(FloatPlane self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatPlane loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(offset, src);
        return RAW.loadDoubleUnsafe(src.address() + offset);
    }
}
