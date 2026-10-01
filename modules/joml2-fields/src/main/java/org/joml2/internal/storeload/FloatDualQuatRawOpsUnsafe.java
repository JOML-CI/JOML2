// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatDualQuatRawOpsUnsafe implements FloatDualQuatRawOps {
    public FloatDualQuat storeUnsafe(FloatDualQuatImpl self, long address) {
        U.putFloat(address + 0L, self.rX);
        U.putFloat(address + 4L, self.rY);
        U.putFloat(address + 8L, self.rZ);
        U.putFloat(address + 12L, self.rW);
        U.putFloat(address + 16L, self.dX);
        U.putFloat(address + 20L, self.dY);
        U.putFloat(address + 24L, self.dZ);
        U.putFloat(address + 28L, self.dW);
        return self;
    }
    public FloatDualQuat loadUnsafe(FloatDualQuatImpl self, long address) {
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
    public FloatDualQuat storeDoubleUnsafe(FloatDualQuatImpl self, long address) {
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
    public FloatDualQuat loadDoubleUnsafe(FloatDualQuatImpl self, long address) {
        self.rX = (float) U.getDouble(address + 0L);
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
