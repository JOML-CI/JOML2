// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int3RawOpsUnsafe implements Int3RawOps {
    public Int3 storeUnsafe(Int3 self, long address) {
        U.putInt(address + 0L, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        return self;
    }
    public Int3 loadUnsafe(long address) {
        int _c0 = U.getInt(address + 0L);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        return new Int3(_c0, _c1, _c2);
    }
    public Int3 storeLongUnsafe(Int3 self, long address) {
        U.putLong(address + 0L, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        return self;
    }
    public Int3 loadLongUnsafe(long address) {
        int _c0 = (int) U.getLong(address + 0L);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        return new Int3(_c0, _c1, _c2);
    }
}
