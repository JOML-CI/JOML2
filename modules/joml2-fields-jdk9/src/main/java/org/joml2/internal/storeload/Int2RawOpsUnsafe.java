// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int2RawOpsUnsafe implements Int2RawOps {
    public Int2 storeUnsafe(Int2Impl self, long address) {
        U.putInt(address + 0L, self.x);
        U.putInt(address + 4L, self.y);
        return self;
    }
    public Int2 loadUnsafe(Int2Impl self, long address) {
        self.x = U.getInt(address + 0L);
        self.y = U.getInt(address + 4L);
        return self;
    }
    public Int2 storeLongUnsafe(Int2Impl self, long address) {
        U.putLong(address + 0L, self.x);
        U.putLong(address + 8L, self.y);
        return self;
    }
    public Int2 loadLongUnsafe(Int2Impl self, long address) {
        self.x = (int) U.getLong(address + 0L);
        self.y = (int) U.getLong(address + 8L);
        return self;
    }
}
