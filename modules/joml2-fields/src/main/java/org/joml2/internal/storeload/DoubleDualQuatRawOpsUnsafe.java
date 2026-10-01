// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleDualQuatRawOpsUnsafe implements DoubleDualQuatRawOps {
    public DoubleDualQuat storeUnsafe(DoubleDualQuatImpl self, long address) {
        U.putDouble(address + 0L, self.rX);
        U.putDouble(address + 8L, self.rY);
        U.putDouble(address + 16L, self.rZ);
        U.putDouble(address + 24L, self.rW);
        U.putDouble(address + 32L, self.dX);
        U.putDouble(address + 40L, self.dY);
        U.putDouble(address + 48L, self.dZ);
        U.putDouble(address + 56L, self.dW);
        return self;
    }
    public DoubleDualQuat loadUnsafe(DoubleDualQuatImpl self, long address) {
        self.rX = U.getDouble(address + 0L);
        self.rY = U.getDouble(address + 8L);
        self.rZ = U.getDouble(address + 16L);
        self.rW = U.getDouble(address + 24L);
        self.dX = U.getDouble(address + 32L);
        self.dY = U.getDouble(address + 40L);
        self.dZ = U.getDouble(address + 48L);
        self.dW = U.getDouble(address + 56L);
        return self;
    }
    public DoubleDualQuat storeFloatUnsafe(DoubleDualQuatImpl self, long address) {
        U.putFloat(address + 0L, (float) self.rX);
        U.putFloat(address + 4L, (float) self.rY);
        U.putFloat(address + 8L, (float) self.rZ);
        U.putFloat(address + 12L, (float) self.rW);
        U.putFloat(address + 16L, (float) self.dX);
        U.putFloat(address + 20L, (float) self.dY);
        U.putFloat(address + 24L, (float) self.dZ);
        U.putFloat(address + 28L, (float) self.dW);
        return self;
    }
    public DoubleDualQuat loadFloatUnsafe(DoubleDualQuatImpl self, long address) {
        self.rX = U.getFloat(address + 0L);
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
