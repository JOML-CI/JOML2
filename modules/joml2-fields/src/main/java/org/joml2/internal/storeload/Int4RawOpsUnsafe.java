// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int4RawOpsUnsafe implements Int4RawOps {
    public Int4 storeUnsafe(Int4Impl self, long address) {
        U.putInt(address + 0L, self.x);
        U.putInt(address + 4L, self.y);
        U.putInt(address + 8L, self.z);
        U.putInt(address + 12L, self.w);
        return self;
    }
    public Int4 loadUnsafe(Int4Impl self, long address) {
        self.x = U.getInt(address + 0L);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        self.w = U.getInt(address + 12L);
        return self;
    }
    public Int4 storeLongUnsafe(Int4Impl self, long address) {
        U.putLong(address + 0L, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        U.putLong(address + 24L, self.w);
        return self;
    }
    public Int4 loadLongUnsafe(Int4Impl self, long address) {
        self.x = (int) U.getLong(address + 0L);
        self.y = (int) U.getLong(address + 8L);
        self.z = (int) U.getLong(address + 16L);
        self.w = (int) U.getLong(address + 24L);
        return self;
    }
}
