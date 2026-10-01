// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2RawOpsUnsafe implements Long2RawOps {
    public Long2 storeUnsafe(Long2 self, long address) {
        U.putLong(address + 0L, self.x());
        U.putLong(address + 8L, self.y());
        return self;
    }
    public Long2 loadUnsafe(long address) {
        long _c0 = U.getLong(address + 0L);
        long _c1 = U.getLong(address + 8L);
        return new Long2(_c0, _c1);
    }
    public Long2 storeIntUnsafe(Long2 self, long address) {
        U.putInt(address + 0L, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        return self;
    }
    public Long2 loadIntUnsafe(long address) {
        long _c0 = U.getInt(address + 0L);
        long _c1 = U.getInt(address + 4L);
        return new Long2(_c0, _c1);
    }
}
