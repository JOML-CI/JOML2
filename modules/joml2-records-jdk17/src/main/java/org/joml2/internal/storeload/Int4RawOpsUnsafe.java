// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int4RawOpsUnsafe extends Int4RawOps {
    public Int4 storeUnsafe(Int4 self, long address) {
        U.putInt(address, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        U.putInt(address + 12L, self.w());
        return self;
    }

    public Int4 storeHeap(Int4 self, byte[] bytes, long address) {
        U.putInt(bytes, address, self.x());
        U.putInt(bytes, address + 4L, self.y());
        U.putInt(bytes, address + 8L, self.z());
        U.putInt(bytes, address + 12L, self.w());
        return self;
    }

    public Int4 loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        int _c0 = _U.getInt(address);
        int _c1 = _U.getInt(address + 4L);
        int _c2 = _U.getInt(address + 8L);
        int _c3 = _U.getInt(address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    public Int4 loadHeap(byte[] bytes, long address) {
        int _c0 = U.getInt(bytes, address);
        int _c1 = U.getInt(bytes, address + 4L);
        int _c2 = U.getInt(bytes, address + 8L);
        int _c3 = U.getInt(bytes, address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    public Int4 storeLongUnsafe(Int4 self, long address) {
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return self;
    }

    public Int4 storeLongHeap(Int4 self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.x());
        U.putLong(bytes, address + 8L, self.y());
        U.putLong(bytes, address + 16L, self.z());
        U.putLong(bytes, address + 24L, self.w());
        return self;
    }

    public Int4 loadLongUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        int _c0 = (int) _U.getLong(address);
        int _c1 = (int) _U.getLong(address + 8L);
        int _c2 = (int) _U.getLong(address + 16L);
        int _c3 = (int) _U.getLong(address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    public Int4 loadLongHeap(byte[] bytes, long address) {
        int _c0 = (int) U.getLong(bytes, address);
        int _c1 = (int) U.getLong(bytes, address + 8L);
        int _c2 = (int) U.getLong(bytes, address + 16L);
        int _c3 = (int) U.getLong(bytes, address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
}
