// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2RawOpsUnsafe implements Float2RawOps {
    public Float2 storeUnsafe(Float2Impl self, long address) {
        U.putFloat(address + 0L, self.x);
        U.putFloat(address + 4L, self.y);
        return self;
    }
    public Float2 loadUnsafe(Float2Impl self, long address) {
        self.x = U.getFloat(address + 0L);
        self.y = U.getFloat(address + 4L);
        return self;
    }
    public Float2 storeDoubleUnsafe(Float2Impl self, long address) {
        U.putDouble(address + 0L, self.x);
        U.putDouble(address + 8L, self.y);
        return self;
    }
    public Float2 loadDoubleUnsafe(Float2Impl self, long address) {
        self.x = (float) U.getDouble(address + 0L);
        self.y = (float) U.getDouble(address + 8L);
        return self;
    }
}
