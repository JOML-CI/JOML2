// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRigidRawOpsUnsafe implements FloatRigidRawOps {
    public FloatRigid storeUnsafe(FloatRigidImpl self, long address) {
        U.putFloat(address + 0L, self.tX);
        U.putFloat(address + 4L, self.tY);
        U.putFloat(address + 8L, self.tZ);
        U.putFloat(address + 12L, self.rX);
        U.putFloat(address + 16L, self.rY);
        U.putFloat(address + 20L, self.rZ);
        U.putFloat(address + 24L, self.rW);
        return self;
    }
    public FloatRigid loadUnsafe(FloatRigidImpl self, long address) {
        self.tX = U.getFloat(address + 0L);
        self.tY = U.getFloat(address + 4L);
        self.tZ = U.getFloat(address + 8L);
        self.rX = U.getFloat(address + 12L);
        self.rY = U.getFloat(address + 16L);
        self.rZ = U.getFloat(address + 20L);
        self.rW = U.getFloat(address + 24L);
        return self;
    }
    public FloatRigid storeDoubleUnsafe(FloatRigidImpl self, long address) {
        U.putDouble(address + 0L, self.tX);
        U.putDouble(address + 8L, self.tY);
        U.putDouble(address + 16L, self.tZ);
        U.putDouble(address + 24L, self.rX);
        U.putDouble(address + 32L, self.rY);
        U.putDouble(address + 40L, self.rZ);
        U.putDouble(address + 48L, self.rW);
        return self;
    }
    public FloatRigid loadDoubleUnsafe(FloatRigidImpl self, long address) {
        self.tX = (float) U.getDouble(address + 0L);
        self.tY = (float) U.getDouble(address + 8L);
        self.tZ = (float) U.getDouble(address + 16L);
        self.rX = (float) U.getDouble(address + 24L);
        self.rY = (float) U.getDouble(address + 32L);
        self.rZ = (float) U.getDouble(address + 40L);
        self.rW = (float) U.getDouble(address + 48L);
        return self;
    }
}
