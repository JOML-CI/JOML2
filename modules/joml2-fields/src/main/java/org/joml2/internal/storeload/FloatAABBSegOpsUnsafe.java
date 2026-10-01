// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatAABBSegOpsUnsafe extends FloatAABBSegOps {

    private static final FloatAABBSegOpsMS MS = new FloatAABBSegOpsMS();

    public MemorySegment store(FloatAABBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.minX);
        U.putFloat(address + 4L, self.minY);
        U.putFloat(address + 8L, self.minZ);
        U.putFloat(address + 12L, self.maxX);
        U.putFloat(address + 16L, self.maxY);
        U.putFloat(address + 20L, self.maxZ);
        return dest;
    }
    public FloatAABB load(FloatAABBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.minZ = U.getFloat(address + 8L);
        self.maxX = U.getFloat(address + 12L);
        self.maxY = U.getFloat(address + 16L);
        self.maxZ = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeDouble(FloatAABBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.minZ);
        U.putDouble(address + 24L, self.maxX);
        U.putDouble(address + 32L, self.maxY);
        U.putDouble(address + 40L, self.maxZ);
        return dest;
    }
    public FloatAABB loadDouble(FloatAABBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        long address = src.address() + offset;
        self.minX = (float) U.getDouble(address);
        self.minY = (float) U.getDouble(address + 8L);
        self.minZ = (float) U.getDouble(address + 16L);
        self.maxX = (float) U.getDouble(address + 24L);
        self.maxY = (float) U.getDouble(address + 32L);
        self.maxZ = (float) U.getDouble(address + 40L);
        return self;
    }
}
