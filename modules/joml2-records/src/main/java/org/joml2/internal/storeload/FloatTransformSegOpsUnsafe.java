// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class FloatTransformSegOpsUnsafe implements FloatTransformSegOps {

    private static final FloatTransformSegOpsMS MS = new FloatTransformSegOpsMS();
    private static final FloatTransformRawOpsUnsafe RAW = new FloatTransformRawOpsUnsafe();

    public MemorySegment store(FloatTransform self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatTransform load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeDouble(FloatTransform self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatTransform loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(offset, src);
        return RAW.loadDoubleUnsafe(src.address() + offset);
    }
}
