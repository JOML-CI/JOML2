// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoublePlaneRawOpsUnsafe implements DoublePlaneRawOps {
    public DoublePlane storeUnsafe(DoublePlaneImpl self, long address) {
        U.putDouble(address + 0L, self.a);
        U.putDouble(address + 8L, self.b);
        U.putDouble(address + 16L, self.c);
        U.putDouble(address + 24L, self.d);
        return self;
    }
    public DoublePlane loadUnsafe(DoublePlaneImpl self, long address) {
        self.a = U.getDouble(address + 0L);
        self.b = U.getDouble(address + 8L);
        self.c = U.getDouble(address + 16L);
        self.d = U.getDouble(address + 24L);
        return self;
    }
    public DoublePlane storeFloatUnsafe(DoublePlaneImpl self, long address) {
        U.putFloat(address + 0L, (float) self.a);
        U.putFloat(address + 4L, (float) self.b);
        U.putFloat(address + 8L, (float) self.c);
        U.putFloat(address + 12L, (float) self.d);
        return self;
    }
    public DoublePlane loadFloatUnsafe(DoublePlaneImpl self, long address) {
        self.a = U.getFloat(address + 0L);
        self.b = U.getFloat(address + 4L);
        self.c = U.getFloat(address + 8L);
        self.d = U.getFloat(address + 12L);
        return self;
    }
}
