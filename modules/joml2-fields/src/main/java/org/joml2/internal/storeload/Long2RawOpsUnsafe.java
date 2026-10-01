// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2RawOpsUnsafe implements Long2RawOps {
    public Long2 storeUnsafe(Long2Impl self, long address) {
        U.putLong(address + 0L, self.x);
        U.putLong(address + 8L, self.y);
        return self;
    }
    public Long2 loadUnsafe(Long2Impl self, long address) {
        self.x = U.getLong(address + 0L);
        self.y = U.getLong(address + 8L);
        return self;
    }
    public Long2 storeIntUnsafe(Long2Impl self, long address) {
        U.putInt(address + 0L, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        return self;
    }
    public Long2 loadIntUnsafe(Long2Impl self, long address) {
        self.x = U.getInt(address + 0L);
        self.y = U.getInt(address + 4L);
        return self;
    }
}
