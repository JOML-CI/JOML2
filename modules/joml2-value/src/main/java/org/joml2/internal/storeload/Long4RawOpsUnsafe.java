// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long4RawOpsUnsafe implements Long4RawOps {
    public Long4 storeUnsafe(Long4 self, long address) {
        U.putLong(address + 0L, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return self;
    }
    public Long4 loadUnsafe(long address) {
        long _c0 = U.getLong(address + 0L);
        long _c1 = U.getLong(address + 8L);
        long _c2 = U.getLong(address + 16L);
        long _c3 = U.getLong(address + 24L);
        return new Long4(_c0, _c1, _c2, _c3);
    }
    public Long4 storeIntUnsafe(Long4 self, long address) {
        U.putInt(address + 0L, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        U.putInt(address + 12L, (int) self.w());
        return self;
    }
    public Long4 loadIntUnsafe(long address) {
        long _c0 = U.getInt(address + 0L);
        long _c1 = U.getInt(address + 4L);
        long _c2 = U.getInt(address + 8L);
        long _c3 = U.getInt(address + 12L);
        return new Long4(_c0, _c1, _c2, _c3);
    }
}
