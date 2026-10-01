// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3RawOpsUnsafe implements Float3RawOps {
    public Float3 storeUnsafe(Float3 self, long address) {
        U.putFloat(address + 0L, self.x());
        U.putFloat(address + 4L, self.y());
        U.putFloat(address + 8L, self.z());
        return self;
    }
    public Float3 loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        return new Float3(_c0, _c1, _c2);
    }
    public Float3 storeDoubleUnsafe(Float3 self, long address) {
        U.putDouble(address + 0L, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        return self;
    }
    public Float3 loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        return new Float3(_c0, _c1, _c2);
    }
}
