// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int3RawOpsUnsafe implements Int3RawOps {
    public Int3 storeUnsafe(Int3Impl self, long address) {
        U.putInt(address + 0L, self.x);
        U.putInt(address + 4L, self.y);
        U.putInt(address + 8L, self.z);
        return self;
    }
    public Int3 loadUnsafe(Int3Impl self, long address) {
        self.x = U.getInt(address + 0L);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        return self;
    }
    public Int3 storeLongUnsafe(Int3Impl self, long address) {
        U.putLong(address + 0L, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        return self;
    }
    public Int3 loadLongUnsafe(Int3Impl self, long address) {
        self.x = (int) U.getLong(address + 0L);
        self.y = (int) U.getLong(address + 8L);
        self.z = (int) U.getLong(address + 16L);
        return self;
    }
}
