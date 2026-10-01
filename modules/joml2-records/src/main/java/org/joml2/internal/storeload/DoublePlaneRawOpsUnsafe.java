// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoublePlaneRawOpsUnsafe implements DoublePlaneRawOps {
    public DoublePlane storeUnsafe(DoublePlane self, long address) {
        U.putDouble(address + 0L, self.a());
        U.putDouble(address + 8L, self.b());
        U.putDouble(address + 16L, self.c());
        U.putDouble(address + 24L, self.d());
        return self;
    }
    public DoublePlane loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
    public DoublePlane storeFloatUnsafe(DoublePlane self, long address) {
        U.putFloat(address + 0L, (float) self.a());
        U.putFloat(address + 4L, (float) self.b());
        U.putFloat(address + 8L, (float) self.c());
        U.putFloat(address + 12L, (float) self.d());
        return self;
    }
    public DoublePlane loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
}
