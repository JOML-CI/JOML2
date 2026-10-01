// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long4RawOpsUnsafe implements Long4RawOps {
    public Long4 storeUnsafe(Long4Impl self, long address) {
        U.putLong(address + 0L, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        U.putLong(address + 24L, self.w);
        return self;
    }
    public Long4 loadUnsafe(Long4Impl self, long address) {
        self.x = U.getLong(address + 0L);
        self.y = U.getLong(address + 8L);
        self.z = U.getLong(address + 16L);
        self.w = U.getLong(address + 24L);
        return self;
    }
    public Long4 storeIntUnsafe(Long4Impl self, long address) {
        U.putInt(address + 0L, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        U.putInt(address + 8L, (int) self.z);
        U.putInt(address + 12L, (int) self.w);
        return self;
    }
    public Long4 loadIntUnsafe(Long4Impl self, long address) {
        self.x = U.getInt(address + 0L);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        self.w = U.getInt(address + 12L);
        return self;
    }
}
