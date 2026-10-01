// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatDualQuatRawOpsUnsafe implements FloatDualQuatRawOps {
    public FloatDualQuat storeUnsafe(FloatDualQuat self, long address) {
        U.putFloat(address + 0L, self.rX());
        U.putFloat(address + 4L, self.rY());
        U.putFloat(address + 8L, self.rZ());
        U.putFloat(address + 12L, self.rW());
        U.putFloat(address + 16L, self.dX());
        U.putFloat(address + 20L, self.dY());
        U.putFloat(address + 24L, self.dZ());
        U.putFloat(address + 28L, self.dW());
        return self;
    }
    public FloatDualQuat loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address) {
        U.putDouble(address + 0L, self.rX());
        U.putDouble(address + 8L, self.rY());
        U.putDouble(address + 16L, self.rZ());
        U.putDouble(address + 24L, self.rW());
        U.putDouble(address + 32L, self.dX());
        U.putDouble(address + 40L, self.dY());
        U.putDouble(address + 48L, self.dZ());
        U.putDouble(address + 56L, self.dW());
        return self;
    }
    public FloatDualQuat loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
