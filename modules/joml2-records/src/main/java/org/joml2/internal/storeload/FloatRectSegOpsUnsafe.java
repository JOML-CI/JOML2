// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRectSegOpsUnsafe extends FloatRectSegOps {

    private static final FloatRectSegOpsMS MS = new FloatRectSegOpsMS();

    public MemorySegment store(FloatRect self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.minX());
        U.putFloat(address + 4L, self.minY());
        U.putFloat(address + 8L, self.maxX());
        U.putFloat(address + 12L, self.maxY());
        return dest;
    }
    public FloatRect load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeDouble(FloatRect self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.maxX());
        U.putDouble(address + 24L, self.maxY());
        return dest;
    }
    public FloatRect loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }
}
