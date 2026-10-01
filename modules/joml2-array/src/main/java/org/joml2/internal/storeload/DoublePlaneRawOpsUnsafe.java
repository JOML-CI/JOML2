// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoublePlaneRawOpsUnsafe implements DoublePlaneRawOps {
    public DoublePlane storeUnsafe(DoublePlaneImpl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        return self;
    }
    public DoublePlane loadUnsafe(DoublePlaneImpl self, long address) {
        self.data[0] = U.getDouble(address + 0L);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        return self;
    }
    public DoublePlane storeFloatUnsafe(DoublePlaneImpl self, long address) {
        U.putFloat(address + 0L, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        return self;
    }
    public DoublePlane loadFloatUnsafe(DoublePlaneImpl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        return self;
    }
}
