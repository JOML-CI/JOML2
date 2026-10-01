// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int2RawOpsUnsafe implements Int2RawOps {
    public Int2 storeUnsafe(Int2 self, long address) {
        U.putInt(address + 0L, self.x());
        U.putInt(address + 4L, self.y());
        return self;
    }
    public Int2 loadUnsafe(long address) {
        int _c0 = U.getInt(address + 0L);
        int _c1 = U.getInt(address + 4L);
        return new Int2(_c0, _c1);
    }
    public Int2 storeLongUnsafe(Int2 self, long address) {
        U.putLong(address + 0L, self.x());
        U.putLong(address + 8L, self.y());
        return self;
    }
    public Int2 loadLongUnsafe(long address) {
        int _c0 = (int) U.getLong(address + 0L);
        int _c1 = (int) U.getLong(address + 8L);
        return new Int2(_c0, _c1);
    }
}
