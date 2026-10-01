// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatSphereRawOpsUnsafe implements FloatSphereRawOps {
    public FloatSphere storeUnsafe(FloatSphereImpl self, long address) {
        U.putFloat(address + 0L, self.x);
        U.putFloat(address + 4L, self.y);
        U.putFloat(address + 8L, self.z);
        U.putFloat(address + 12L, self.r);
        return self;
    }
    public FloatSphere loadUnsafe(FloatSphereImpl self, long address) {
        self.x = U.getFloat(address + 0L);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        self.r = U.getFloat(address + 12L);
        return self;
    }
    public FloatSphere storeDoubleUnsafe(FloatSphereImpl self, long address) {
        U.putDouble(address + 0L, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        U.putDouble(address + 24L, self.r);
        return self;
    }
    public FloatSphere loadDoubleUnsafe(FloatSphereImpl self, long address) {
        self.x = (float) U.getDouble(address + 0L);
        self.y = (float) U.getDouble(address + 8L);
        self.z = (float) U.getDouble(address + 16L);
        self.r = (float) U.getDouble(address + 24L);
        return self;
    }
}
