// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long3RawOpsUnsafe implements Long3RawOps {
    public Long3 storeUnsafe(Long3Impl self, long address) {
        U.putLong(address + 0L, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        return self;
    }
    public Long3 loadUnsafe(Long3Impl self, long address) {
        self.data[0] = U.getLong(address + 0L);
        self.data[1] = U.getLong(address + 8L);
        self.data[2] = U.getLong(address + 16L);
        return self;
    }
    public Long3 storeIntUnsafe(Long3Impl self, long address) {
        U.putInt(address + 0L, (int) self.data[0]);
        U.putInt(address + 4L, (int) self.data[1]);
        U.putInt(address + 8L, (int) self.data[2]);
        return self;
    }
    public Long3 loadIntUnsafe(Long3Impl self, long address) {
        self.data[0] = U.getInt(address + 0L);
        self.data[1] = U.getInt(address + 4L);
        self.data[2] = U.getInt(address + 8L);
        return self;
    }
}
