// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3RawOpsUnsafe implements Double3RawOps {
    public Double3 storeUnsafe(Double3 self, long address) {
        U.putDouble(address + 0L, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        return self;
    }
    public Double3 loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        return new Double3(_c0, _c1, _c2);
    }
    public Double3 storeFloatUnsafe(Double3 self, long address) {
        U.putFloat(address + 0L, (float) self.x());
        U.putFloat(address + 4L, (float) self.y());
        U.putFloat(address + 8L, (float) self.z());
        return self;
    }
    public Double3 loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        return new Double3(_c0, _c1, _c2);
    }
}
