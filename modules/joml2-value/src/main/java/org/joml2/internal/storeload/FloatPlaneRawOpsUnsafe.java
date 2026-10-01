// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatPlaneRawOpsUnsafe implements FloatPlaneRawOps {
    public FloatPlane storeUnsafe(FloatPlane self, long address) {
        U.putFloat(address + 0L, self.a());
        U.putFloat(address + 4L, self.b());
        U.putFloat(address + 8L, self.c());
        U.putFloat(address + 12L, self.d());
        return self;
    }
    public FloatPlane loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
    public FloatPlane storeDoubleUnsafe(FloatPlane self, long address) {
        U.putDouble(address + 0L, self.a());
        U.putDouble(address + 8L, self.b());
        U.putDouble(address + 16L, self.c());
        U.putDouble(address + 24L, self.d());
        return self;
    }
    public FloatPlane loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
}
