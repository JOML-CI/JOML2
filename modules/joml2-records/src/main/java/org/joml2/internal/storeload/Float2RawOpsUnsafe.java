// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2RawOpsUnsafe implements Float2RawOps {
    public Float2 storeUnsafe(Float2 self, long address) {
        U.putFloat(address + 0L, self.x());
        U.putFloat(address + 4L, self.y());
        return self;
    }
    public Float2 loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        return new Float2(_c0, _c1);
    }
    public Float2 storeDoubleUnsafe(Float2 self, long address) {
        U.putDouble(address + 0L, self.x());
        U.putDouble(address + 8L, self.y());
        return self;
    }
    public Float2 loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        return new Float2(_c0, _c1);
    }
}
