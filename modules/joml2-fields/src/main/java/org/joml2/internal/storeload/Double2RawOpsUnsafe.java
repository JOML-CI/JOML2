// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2RawOpsUnsafe extends Double2RawOps {
    public Double2 storeUnsafe(Double2Impl self, long address) {
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        return self;
    }
    public Double2 loadUnsafe(Double2Impl self, long address) {
        self.x = U.getDouble(address);
        self.y = U.getDouble(address + 8L);
        return self;
    }
    public Double2 storeFloatUnsafe(Double2Impl self, long address) {
        U.putFloat(address, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        return self;
    }
    public Double2 loadFloatUnsafe(Double2Impl self, long address) {
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        return self;
    }
}
