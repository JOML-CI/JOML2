// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRaySegOpsUnsafe extends FloatRaySegOps {

    private static final FloatRaySegOpsMS MS = new FloatRaySegOpsMS();

    public MemorySegment store(FloatRay self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.oX());
        U.putFloat(address + 4L, self.oY());
        U.putFloat(address + 8L, self.oZ());
        U.putFloat(address + 12L, self.dX());
        U.putFloat(address + 16L, self.dY());
        U.putFloat(address + 20L, self.dZ());
        return dest;
    }
    public FloatRay load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeDouble(FloatRay self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.oX());
        U.putDouble(address + 8L, self.oY());
        U.putDouble(address + 16L, self.oZ());
        U.putDouble(address + 24L, self.dX());
        U.putDouble(address + 32L, self.dY());
        U.putDouble(address + 40L, self.dZ());
        return dest;
    }
    public FloatRay loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
