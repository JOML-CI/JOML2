// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long4RawOpsUnsafe extends Long4RawOps {
    public Long4 storeUnsafe(Long4Impl self, long address) {
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        U.putLong(address + 24L, self.data[3]);
        return self;
    }

    public Long4 storeHeap(Long4Impl self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.data[0]);
        U.putLong(bytes, address + 8L, self.data[1]);
        U.putLong(bytes, address + 16L, self.data[2]);
        U.putLong(bytes, address + 24L, self.data[3]);
        return self;
    }

    public Long4 loadUnsafe(Long4Impl self, long address) {
        self.data[0] = U.getLong(address);
        self.data[1] = U.getLong(address + 8L);
        self.data[2] = U.getLong(address + 16L);
        self.data[3] = U.getLong(address + 24L);
        return self;
    }

    public Long4 loadHeap(Long4Impl self, byte[] bytes, long address) {
        self.data[0] = U.getLong(bytes, address);
        self.data[1] = U.getLong(bytes, address + 8L);
        self.data[2] = U.getLong(bytes, address + 16L);
        self.data[3] = U.getLong(bytes, address + 24L);
        return self;
    }

    public Long4 storeIntUnsafe(Long4Impl self, long address) {
        U.putInt(address, (int) self.data[0]);
        U.putInt(address + 4L, (int) self.data[1]);
        U.putInt(address + 8L, (int) self.data[2]);
        U.putInt(address + 12L, (int) self.data[3]);
        return self;
    }

    public Long4 storeIntHeap(Long4Impl self, byte[] bytes, long address) {
        U.putInt(bytes, address, (int) self.data[0]);
        U.putInt(bytes, address + 4L, (int) self.data[1]);
        U.putInt(bytes, address + 8L, (int) self.data[2]);
        U.putInt(bytes, address + 12L, (int) self.data[3]);
        return self;
    }

    public Long4 loadIntUnsafe(Long4Impl self, long address) {
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        self.data[2] = U.getInt(address + 8L);
        self.data[3] = U.getInt(address + 12L);
        return self;
    }

    public Long4 loadIntHeap(Long4Impl self, byte[] bytes, long address) {
        self.data[0] = U.getInt(bytes, address);
        self.data[1] = U.getInt(bytes, address + 4L);
        self.data[2] = U.getInt(bytes, address + 8L);
        self.data[3] = U.getInt(bytes, address + 12L);
        return self;
    }
}
