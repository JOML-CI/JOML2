// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleAABBSegOpsUnsafe extends DoubleAABBSegOps {

    private static final DoubleAABBSegOpsMS MS = new DoubleAABBSegOpsMS();

    public MemorySegment store(DoubleAABBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.minZ);
        U.putDouble(address + 24L, self.maxX);
        U.putDouble(address + 32L, self.maxY);
        U.putDouble(address + 40L, self.maxZ);
        return dest;
    }
    public DoubleAABB load(DoubleAABBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getDouble(address);
        self.minY = U.getDouble(address + 8L);
        self.minZ = U.getDouble(address + 16L);
        self.maxX = U.getDouble(address + 24L);
        self.maxY = U.getDouble(address + 32L);
        self.maxZ = U.getDouble(address + 40L);
        return self;
    }
    public MemorySegment storeFloat(DoubleAABBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.minZ);
        U.putFloat(address + 12L, (float) self.maxX);
        U.putFloat(address + 16L, (float) self.maxY);
        U.putFloat(address + 20L, (float) self.maxZ);
        return dest;
    }
    public DoubleAABB loadFloat(DoubleAABBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.minZ = U.getFloat(address + 8L);
        self.maxX = U.getFloat(address + 12L);
        self.maxY = U.getFloat(address + 16L);
        self.maxZ = U.getFloat(address + 20L);
        return self;
    }
}
