// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2RawOpsUnsafe implements Double2RawOps {
    public Double2 storeUnsafe(Double2 self, long address) {
        U.putDouble(address + 0L, self.x());
        U.putDouble(address + 8L, self.y());
        return self;
    }
    public Double2 loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        return new Double2(_c0, _c1);
    }
    public Double2 storeFloatUnsafe(Double2 self, long address) {
        U.putFloat(address + 0L, (float) self.x());
        U.putFloat(address + 4L, (float) self.y());
        return self;
    }
    public Double2 loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        return new Double2(_c0, _c1);
    }
}
