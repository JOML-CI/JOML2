// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class FloatTriangleSegOpsUnsafe implements FloatTriangleSegOps {

    private static final FloatTriangleSegOpsMS MS = new FloatTriangleSegOpsMS();
    private static final FloatTriangleRawOpsUnsafe RAW = new FloatTriangleRawOpsUnsafe();

    public MemorySegment store(FloatTriangleImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatTriangle load(FloatTriangleImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeDouble(FloatTriangleImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatTriangle loadDouble(FloatTriangleImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        return RAW.loadDoubleUnsafe(self, src.address() + offset);
    }
}
