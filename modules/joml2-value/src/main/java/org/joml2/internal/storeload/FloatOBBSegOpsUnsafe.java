// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatOBBSegOpsUnsafe extends FloatOBBSegOps {

    private static final FloatOBBSegOpsMS MS = new FloatOBBSegOpsMS();
    private static final FloatOBBRawOpsUnsafe RAW = new FloatOBBRawOpsUnsafe();

    public MemorySegment store(FloatOBB self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatOBB load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        float _c9 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c11 = U.getFloat(address + 44L);
        float _c12 = U.getFloat(address + 48L);
        float _c13 = U.getFloat(address + 52L);
        float _c14 = U.getFloat(address + 56L);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public MemorySegment storeDouble(FloatOBB self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatOBB loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        float _c9 = (float) U.getDouble(address + 72L);
        float _c10 = (float) U.getDouble(address + 80L);
        float _c11 = (float) U.getDouble(address + 88L);
        float _c12 = (float) U.getDouble(address + 96L);
        float _c13 = (float) U.getDouble(address + 104L);
        float _c14 = (float) U.getDouble(address + 112L);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
