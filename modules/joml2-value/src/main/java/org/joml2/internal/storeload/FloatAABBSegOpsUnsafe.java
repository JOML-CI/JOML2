// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatAABBSegOpsUnsafe extends FloatAABBSegOps {

    private static final FloatAABBSegOpsMS MS = new FloatAABBSegOpsMS();

    public MemorySegment store(FloatAABB self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.minX());
        U.putFloat(address + 4L, self.minY());
        U.putFloat(address + 8L, self.minZ());
        U.putFloat(address + 12L, self.maxX());
        U.putFloat(address + 16L, self.maxY());
        U.putFloat(address + 20L, self.maxZ());
        return dest;
    }
    public FloatAABB load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeDouble(FloatAABB self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.minZ());
        U.putDouble(address + 24L, self.maxX());
        U.putDouble(address + 32L, self.maxY());
        U.putDouble(address + 40L, self.maxZ());
        return dest;
    }
    public FloatAABB loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
