// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleSphereRawOpsUnsafe implements DoubleSphereRawOps {
    public DoubleSphere storeUnsafe(DoubleSphereImpl self, long address) {
        U.putDouble(address + 0L, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        U.putDouble(address + 24L, self.r);
        return self;
    }
    public DoubleSphere loadUnsafe(DoubleSphereImpl self, long address) {
        self.x = U.getDouble(address + 0L);
        self.y = U.getDouble(address + 8L);
        self.z = U.getDouble(address + 16L);
        self.r = U.getDouble(address + 24L);
        return self;
    }
    public DoubleSphere storeFloatUnsafe(DoubleSphereImpl self, long address) {
        U.putFloat(address + 0L, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        U.putFloat(address + 8L, (float) self.z);
        U.putFloat(address + 12L, (float) self.r);
        return self;
    }
    public DoubleSphere loadFloatUnsafe(DoubleSphereImpl self, long address) {
        self.x = U.getFloat(address + 0L);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        self.r = U.getFloat(address + 12L);
        return self;
    }
}
