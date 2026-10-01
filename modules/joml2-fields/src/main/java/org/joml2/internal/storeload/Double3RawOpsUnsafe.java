// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3RawOpsUnsafe implements Double3RawOps {
    public Double3 storeUnsafe(Double3Impl self, long address) {
        U.putDouble(address + 0L, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        return self;
    }
    public Double3 loadUnsafe(Double3Impl self, long address) {
        self.x = U.getDouble(address + 0L);
        self.y = U.getDouble(address + 8L);
        self.z = U.getDouble(address + 16L);
        return self;
    }
    public Double3 storeFloatUnsafe(Double3Impl self, long address) {
        U.putFloat(address + 0L, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        U.putFloat(address + 8L, (float) self.z);
        return self;
    }
    public Double3 loadFloatUnsafe(Double3Impl self, long address) {
        self.x = U.getFloat(address + 0L);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        return self;
    }
}
