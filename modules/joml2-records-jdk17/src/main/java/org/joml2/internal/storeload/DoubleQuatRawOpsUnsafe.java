// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleQuatRawOpsUnsafe implements DoubleQuatRawOps {
    public DoubleQuat storeUnsafe(DoubleQuat self, long address) {
        U.putDouble(address + 0L, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        U.putDouble(address + 24L, self.w());
        return self;
    }
    public DoubleQuat loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }
    public DoubleQuat storeFloatUnsafe(DoubleQuat self, long address) {
        U.putFloat(address + 0L, (float) self.x());
        U.putFloat(address + 4L, (float) self.y());
        U.putFloat(address + 8L, (float) self.z());
        U.putFloat(address + 12L, (float) self.w());
        return self;
    }
    public DoubleQuat loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }
}
