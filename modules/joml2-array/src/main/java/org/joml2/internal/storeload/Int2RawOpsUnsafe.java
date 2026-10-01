// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int2RawOpsUnsafe implements Int2RawOps {
    public Int2 storeUnsafe(Int2Impl self, long address) {
        U.putInt(address + 0L, self.data[0]);
        U.putInt(address + 4L, self.data[1]);
        return self;
    }
    public Int2 loadUnsafe(Int2Impl self, long address) {
        self.data[0] = U.getInt(address + 0L);
        self.data[1] = U.getInt(address + 4L);
        return self;
    }
    public Int2 storeLongUnsafe(Int2Impl self, long address) {
        U.putLong(address + 0L, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        return self;
    }
    public Int2 loadLongUnsafe(Int2Impl self, long address) {
        self.data[0] = (int) U.getLong(address + 0L);
        self.data[1] = (int) U.getLong(address + 8L);
        return self;
    }
}
