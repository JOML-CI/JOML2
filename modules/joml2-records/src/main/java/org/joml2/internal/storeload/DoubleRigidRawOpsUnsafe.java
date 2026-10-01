// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRigidRawOpsUnsafe implements DoubleRigidRawOps {
    public DoubleRigid storeUnsafe(DoubleRigid self, long address) {
        U.putDouble(address + 0L, self.tX());
        U.putDouble(address + 8L, self.tY());
        U.putDouble(address + 16L, self.tZ());
        U.putDouble(address + 24L, self.rX());
        U.putDouble(address + 32L, self.rY());
        U.putDouble(address + 40L, self.rZ());
        U.putDouble(address + 48L, self.rW());
        return self;
    }
    public DoubleRigid loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c6 = U.getDouble(address + 48L);
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public DoubleRigid storeFloatUnsafe(DoubleRigid self, long address) {
        U.putFloat(address + 0L, (float) self.tX());
        U.putFloat(address + 4L, (float) self.tY());
        U.putFloat(address + 8L, (float) self.tZ());
        U.putFloat(address + 12L, (float) self.rX());
        U.putFloat(address + 16L, (float) self.rY());
        U.putFloat(address + 20L, (float) self.rZ());
        U.putFloat(address + 24L, (float) self.rW());
        return self;
    }
    public DoubleRigid loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c6 = U.getFloat(address + 24L);
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
}
