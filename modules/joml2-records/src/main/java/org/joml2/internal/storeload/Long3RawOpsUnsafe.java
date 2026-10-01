// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long3RawOpsUnsafe implements Long3RawOps {
    public Long3 storeUnsafe(Long3 self, long address) {
        U.putLong(address + 0L, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        return self;
    }
    public Long3 loadUnsafe(long address) {
        long _c0 = U.getLong(address + 0L);
        long _c1 = U.getLong(address + 8L);
        long _c2 = U.getLong(address + 16L);
        return new Long3(_c0, _c1, _c2);
    }
    public Long3 storeIntUnsafe(Long3 self, long address) {
        U.putInt(address + 0L, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        return self;
    }
    public Long3 loadIntUnsafe(long address) {
        long _c0 = U.getInt(address + 0L);
        long _c1 = U.getInt(address + 4L);
        long _c2 = U.getInt(address + 8L);
        return new Long3(_c0, _c1, _c2);
    }
}
