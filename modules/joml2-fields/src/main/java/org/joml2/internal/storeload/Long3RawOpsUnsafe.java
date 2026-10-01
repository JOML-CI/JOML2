// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long3RawOpsUnsafe extends Long3RawOps {
    public Long3 storeUnsafe(Long3Impl self, long address) {
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        return self;
    }
    public Long3 loadUnsafe(Long3Impl self, long address) {
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        self.z = U.getLong(address + 16L);
        return self;
    }
    public Long3 storeIntUnsafe(Long3Impl self, long address) {
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        U.putInt(address + 8L, (int) self.z);
        return self;
    }
    public Long3 loadIntUnsafe(Long3Impl self, long address) {
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        return self;
    }
}
