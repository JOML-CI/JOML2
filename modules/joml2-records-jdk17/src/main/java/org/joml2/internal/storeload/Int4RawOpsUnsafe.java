// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int4RawOpsUnsafe implements Int4RawOps {
    public Int4 storeUnsafe(Int4 self, long address) {
        U.putInt(address + 0L, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        U.putInt(address + 12L, self.w());
        return self;
    }
    public Int4 loadUnsafe(long address) {
        int _c0 = U.getInt(address + 0L);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        int _c3 = U.getInt(address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public Int4 storeLongUnsafe(Int4 self, long address) {
        U.putLong(address + 0L, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return self;
    }
    public Int4 loadLongUnsafe(long address) {
        int _c0 = (int) U.getLong(address + 0L);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        int _c3 = (int) U.getLong(address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
}
