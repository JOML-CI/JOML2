// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class FloatOBBSegOpsUnsafe implements FloatOBBSegOps {

    private static final FloatOBBSegOpsMS MS = new FloatOBBSegOpsMS();
    private static final FloatOBBRawOpsUnsafe RAW = new FloatOBBRawOpsUnsafe();

    public MemorySegment store(FloatOBBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatOBB load(FloatOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeDouble(FloatOBBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatOBB loadDouble(FloatOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        return RAW.loadDoubleUnsafe(self, src.address() + offset);
    }
}
