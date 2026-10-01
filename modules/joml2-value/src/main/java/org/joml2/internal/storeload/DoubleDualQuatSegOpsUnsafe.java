// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleDualQuatSegOpsUnsafe extends DoubleDualQuatSegOps {

    private static final DoubleDualQuatSegOpsMS MS = new DoubleDualQuatSegOpsMS();

    public MemorySegment store(DoubleDualQuat self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.rX());
        U.putDouble(address + 8L, self.rY());
        U.putDouble(address + 16L, self.rZ());
        U.putDouble(address + 24L, self.rW());
        U.putDouble(address + 32L, self.dX());
        U.putDouble(address + 40L, self.dY());
        U.putDouble(address + 48L, self.dZ());
        U.putDouble(address + 56L, self.dW());
        return dest;
    }
    public DoubleDualQuat load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c6 = U.getDouble(address + 48L);
        double _c7 = U.getDouble(address + 56L);
        return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeFloat(DoubleDualQuat self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.rX());
        U.putFloat(address + 4L, (float) self.rY());
        U.putFloat(address + 8L, (float) self.rZ());
        U.putFloat(address + 12L, (float) self.rW());
        U.putFloat(address + 16L, (float) self.dX());
        U.putFloat(address + 20L, (float) self.dY());
        U.putFloat(address + 24L, (float) self.dZ());
        U.putFloat(address + 28L, (float) self.dW());
        return dest;
    }
    public DoubleDualQuat loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c6 = U.getFloat(address + 24L);
        double _c7 = U.getFloat(address + 28L);
        return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
