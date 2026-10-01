// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRaySegOpsUnsafe extends FloatRaySegOps {

    private static final FloatRaySegOpsMS MS = new FloatRaySegOpsMS();

    public MemorySegment store(FloatRayImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.oX);
        U.putFloat(address + 4L, self.oY);
        U.putFloat(address + 8L, self.oZ);
        U.putFloat(address + 12L, self.dX);
        U.putFloat(address + 16L, self.dY);
        U.putFloat(address + 20L, self.dZ);
        return dest;
    }
    public FloatRay load(FloatRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.oX = U.getFloat(address);
        self.oY = U.getFloat(address + 4L);
        self.oZ = U.getFloat(address + 8L);
        self.dX = U.getFloat(address + 12L);
        self.dY = U.getFloat(address + 16L);
        self.dZ = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeDouble(FloatRayImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.oX);
        U.putDouble(address + 8L, self.oY);
        U.putDouble(address + 16L, self.oZ);
        U.putDouble(address + 24L, self.dX);
        U.putDouble(address + 32L, self.dY);
        U.putDouble(address + 40L, self.dZ);
        return dest;
    }
    public FloatRay loadDouble(FloatRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        long address = src.address() + offset;
        self.oX = (float) U.getDouble(address);
        self.oY = (float) U.getDouble(address + 8L);
        self.oZ = (float) U.getDouble(address + 16L);
        self.dX = (float) U.getDouble(address + 24L);
        self.dY = (float) U.getDouble(address + 32L);
        self.dZ = (float) U.getDouble(address + 40L);
        return self;
    }
}
