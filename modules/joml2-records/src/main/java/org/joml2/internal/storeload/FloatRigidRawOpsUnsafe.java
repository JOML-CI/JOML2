// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRigidRawOpsUnsafe implements FloatRigidRawOps {
    public FloatRigid storeUnsafe(FloatRigid self, long address) {
        U.putFloat(address + 0L, self.tX());
        U.putFloat(address + 4L, self.tY());
        U.putFloat(address + 8L, self.tZ());
        U.putFloat(address + 12L, self.rX());
        U.putFloat(address + 16L, self.rY());
        U.putFloat(address + 20L, self.rZ());
        U.putFloat(address + 24L, self.rW());
        return self;
    }
    public FloatRigid loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public FloatRigid storeDoubleUnsafe(FloatRigid self, long address) {
        U.putDouble(address + 0L, self.tX());
        U.putDouble(address + 8L, self.tY());
        U.putDouble(address + 16L, self.tZ());
        U.putDouble(address + 24L, self.rX());
        U.putDouble(address + 32L, self.rY());
        U.putDouble(address + 40L, self.rZ());
        U.putDouble(address + 48L, self.rW());
        return self;
    }
    public FloatRigid loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
}
