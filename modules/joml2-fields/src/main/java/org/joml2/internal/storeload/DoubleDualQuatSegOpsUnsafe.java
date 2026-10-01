// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleDualQuatSegOpsUnsafe extends DoubleDualQuatSegOps {

    private static final DoubleDualQuatSegOpsMS MS = new DoubleDualQuatSegOpsMS();

    public MemorySegment store(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
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
    public DoubleDualQuat load(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.rX = U.getDouble(address);
        self.rY = U.getDouble(address + 8L);
        self.rZ = U.getDouble(address + 16L);
        self.rW = U.getDouble(address + 24L);
        self.dX = U.getDouble(address + 32L);
        self.dY = U.getDouble(address + 40L);
        self.dZ = U.getDouble(address + 48L);
        self.dW = U.getDouble(address + 56L);
        return self;
    }
    public MemorySegment storeFloat(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.rX);
        U.putFloat(address + 4L, (float) self.rY);
        U.putFloat(address + 8L, (float) self.rZ);
        U.putFloat(address + 12L, (float) self.rW);
        U.putFloat(address + 16L, (float) self.dX);
        U.putFloat(address + 20L, (float) self.dY);
        U.putFloat(address + 24L, (float) self.dZ);
        U.putFloat(address + 28L, (float) self.dW);
        return dest;
    }
    public DoubleDualQuat loadFloat(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
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
}
