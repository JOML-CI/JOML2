// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatDualQuatSegOpsUnsafe extends FloatDualQuatSegOps {

    private static final FloatDualQuatSegOpsMS MS = new FloatDualQuatSegOpsMS();

    public MemorySegment store(FloatDualQuatImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.rX);
        U.putFloat(address + 4L, self.rY);
        U.putFloat(address + 8L, self.rZ);
        U.putFloat(address + 12L, self.rW);
        U.putFloat(address + 16L, self.dX);
        U.putFloat(address + 20L, self.dY);
        U.putFloat(address + 24L, self.dZ);
        U.putFloat(address + 28L, self.dW);
        return dest;
    }
    public FloatDualQuat load(FloatDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.rX = U.getFloat(address);
        self.rY = U.getFloat(address + 4L);
        self.rZ = U.getFloat(address + 8L);
        self.rW = U.getFloat(address + 12L);
        self.dX = U.getFloat(address + 16L);
        self.dY = U.getFloat(address + 20L);
        self.dZ = U.getFloat(address + 24L);
        self.dW = U.getFloat(address + 28L);
        return self;
    }
    public MemorySegment storeDouble(FloatDualQuatImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.rX);
        U.putDouble(address + 8L, self.rY);
        U.putDouble(address + 16L, self.rZ);
        U.putDouble(address + 24L, self.rW);
        U.putDouble(address + 32L, self.dX);
        U.putDouble(address + 40L, self.dY);
        U.putDouble(address + 48L, self.dZ);
        U.putDouble(address + 56L, self.dW);
        return dest;
    }
    public FloatDualQuat loadDouble(FloatDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        long address = src.address() + offset;
        self.rX = (float) U.getDouble(address);
        self.rY = (float) U.getDouble(address + 8L);
        self.rZ = (float) U.getDouble(address + 16L);
        self.rW = (float) U.getDouble(address + 24L);
        self.dX = (float) U.getDouble(address + 32L);
        self.dY = (float) U.getDouble(address + 40L);
        self.dZ = (float) U.getDouble(address + 48L);
        self.dW = (float) U.getDouble(address + 56L);
        return self;
    }
}
