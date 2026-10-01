// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2RawOpsUnsafe extends Long2RawOps {
    public Long2 storeUnsafe(Long2Impl self, long address) {
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        return self;
    }
    public Long2 loadUnsafe(Long2Impl self, long address) {
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        return self;
    }
    public Long2 storeIntUnsafe(Long2Impl self, long address) {
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        return self;
    }
    public Long2 loadIntUnsafe(Long2Impl self, long address) {
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        return self;
    }
}
