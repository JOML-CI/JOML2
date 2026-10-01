// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRigidRawOpsUnsafe implements DoubleRigidRawOps {
    public DoubleRigid storeUnsafe(DoubleRigidImpl self, long address) {
        U.putDouble(address + 0L, self.tX);
        U.putDouble(address + 8L, self.tY);
        U.putDouble(address + 16L, self.tZ);
        U.putDouble(address + 24L, self.rX);
        U.putDouble(address + 32L, self.rY);
        U.putDouble(address + 40L, self.rZ);
        U.putDouble(address + 48L, self.rW);
        return self;
    }
    public DoubleRigid loadUnsafe(DoubleRigidImpl self, long address) {
        self.tX = U.getDouble(address + 0L);
        self.tY = U.getDouble(address + 8L);
        self.tZ = U.getDouble(address + 16L);
        self.rX = U.getDouble(address + 24L);
        self.rY = U.getDouble(address + 32L);
        self.rZ = U.getDouble(address + 40L);
        self.rW = U.getDouble(address + 48L);
        return self;
    }
    public DoubleRigid storeFloatUnsafe(DoubleRigidImpl self, long address) {
        U.putFloat(address + 0L, (float) self.tX);
        U.putFloat(address + 4L, (float) self.tY);
        U.putFloat(address + 8L, (float) self.tZ);
        U.putFloat(address + 12L, (float) self.rX);
        U.putFloat(address + 16L, (float) self.rY);
        U.putFloat(address + 20L, (float) self.rZ);
        U.putFloat(address + 24L, (float) self.rW);
        return self;
    }
    public DoubleRigid loadFloatUnsafe(DoubleRigidImpl self, long address) {
        self.tX = U.getFloat(address + 0L);
        self.tY = U.getFloat(address + 4L);
        self.tZ = U.getFloat(address + 8L);
        self.rX = U.getFloat(address + 12L);
        self.rY = U.getFloat(address + 16L);
        self.rZ = U.getFloat(address + 20L);
        self.rW = U.getFloat(address + 24L);
        return self;
    }
}
