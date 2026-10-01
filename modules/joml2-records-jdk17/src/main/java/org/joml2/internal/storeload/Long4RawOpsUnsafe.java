// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long4RawOpsUnsafe extends Long4RawOps {
    public Long4 storeUnsafe(Long4 self, long address) {
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return self;
    }

    public Long4 storeHeap(Long4 self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.x());
        U.putLong(bytes, address + 8L, self.y());
        U.putLong(bytes, address + 16L, self.z());
        U.putLong(bytes, address + 24L, self.w());
        return self;
    }

    public Long4 loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long _c0 = _U.getLong(address);
        long _c1 = _U.getLong(address + 8L);
        long _c2 = _U.getLong(address + 16L);
        long _c3 = _U.getLong(address + 24L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    public Long4 loadHeap(byte[] bytes, long address) {
        long _c0 = U.getLong(bytes, address);
        long _c1 = U.getLong(bytes, address + 8L);
        long _c2 = U.getLong(bytes, address + 16L);
        long _c3 = U.getLong(bytes, address + 24L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    public Long4 storeIntUnsafe(Long4 self, long address) {
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        U.putInt(address + 12L, (int) self.w());
        return self;
    }

    public Long4 storeIntHeap(Long4 self, byte[] bytes, long address) {
        U.putInt(bytes, address, (int) self.x());
        U.putInt(bytes, address + 4L, (int) self.y());
        U.putInt(bytes, address + 8L, (int) self.z());
        U.putInt(bytes, address + 12L, (int) self.w());
        return self;
    }

    public Long4 loadIntUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long _c0 = _U.getInt(address);
        long _c1 = _U.getInt(address + 4L);
        long _c2 = _U.getInt(address + 8L);
        long _c3 = _U.getInt(address + 12L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    public Long4 loadIntHeap(byte[] bytes, long address) {
        long _c0 = U.getInt(bytes, address);
        long _c1 = U.getInt(bytes, address + 4L);
        long _c2 = U.getInt(bytes, address + 8L);
        long _c3 = U.getInt(bytes, address + 12L);
        return new Long4(_c0, _c1, _c2, _c3);
    }
}
