// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2RawOpsUnsafe extends Long2RawOps {
    public Long2 storeUnsafe(Long2Impl self, long address) {
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        return self;
    }

    public Long2 storeHeap(Long2Impl self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.data[0]);
        U.putLong(bytes, address + 8L, self.data[1]);
        return self;
    }

    public Long2 loadUnsafe(Long2Impl self, long address) {
        self.data[0] = U.getLong(address);
        self.data[1] = U.getLong(address + 8L);
        return self;
    }

    public Long2 loadHeap(Long2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getLong(bytes, address);
        self.data[1] = U.getLong(bytes, address + 8L);
        return self;
    }

    public Long2 storeIntUnsafe(Long2Impl self, long address) {
        U.putInt(address, (int) self.data[0]);
        U.putInt(address + 4L, (int) self.data[1]);
        return self;
    }

    public Long2 storeIntHeap(Long2Impl self, byte[] bytes, long address) {
        U.putInt(bytes, address, (int) self.data[0]);
        U.putInt(bytes, address + 4L, (int) self.data[1]);
        return self;
    }

    public Long2 loadIntUnsafe(Long2Impl self, long address) {
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        return self;
    }

    public Long2 loadIntHeap(Long2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getInt(bytes, address);
        self.data[1] = U.getInt(bytes, address + 4L);
        return self;
    }
}
