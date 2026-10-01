// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int3RawOpsUnsafe implements Int3RawOps {
    public Int3 storeUnsafe(Int3Impl self, long address) {
        U.putInt(address + 0L, self.data[0]);
        U.putInt(address + 4L, self.data[1]);
        U.putInt(address + 8L, self.data[2]);
        return self;
    }
    public Int3 loadUnsafe(Int3Impl self, long address) {
        self.data[0] = U.getInt(address + 0L);
        self.data[1] = U.getInt(address + 4L);
        self.data[2] = U.getInt(address + 8L);
        return self;
    }
    public Int3 storeLongUnsafe(Int3Impl self, long address) {
        U.putLong(address + 0L, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        return self;
    }
    public Int3 loadLongUnsafe(Int3Impl self, long address) {
        self.data[0] = (int) U.getLong(address + 0L);
        self.data[1] = (int) U.getLong(address + 8L);
        self.data[2] = (int) U.getLong(address + 16L);
        return self;
    }
}
