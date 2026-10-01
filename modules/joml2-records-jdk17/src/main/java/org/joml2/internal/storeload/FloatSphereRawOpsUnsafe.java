// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatSphereRawOpsUnsafe implements FloatSphereRawOps {
    public FloatSphere storeUnsafe(FloatSphere self, long address) {
        U.putFloat(address + 0L, self.x());
        U.putFloat(address + 4L, self.y());
        U.putFloat(address + 8L, self.z());
        U.putFloat(address + 12L, self.r());
        return self;
    }
    public FloatSphere loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }
    public FloatSphere storeDoubleUnsafe(FloatSphere self, long address) {
        U.putDouble(address + 0L, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        U.putDouble(address + 24L, self.r());
        return self;
    }
    public FloatSphere loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }
}
