// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int4RawOpsUnsafe extends Int4RawOps {
    public Int4 storeUnsafe(Int4Impl self, long address) {
        U.putInt(address, self.data[0]);
        U.putInt(address + 4L, self.data[1]);
        U.putInt(address + 8L, self.data[2]);
        U.putInt(address + 12L, self.data[3]);
        return self;
    }

    public Int4 storeHeap(Int4Impl self, byte[] bytes, long address) {
        U.putInt(bytes, address, self.data[0]);
        U.putInt(bytes, address + 4L, self.data[1]);
        U.putInt(bytes, address + 8L, self.data[2]);
        U.putInt(bytes, address + 12L, self.data[3]);
        return self;
    }

    public Int4 loadUnsafe(Int4Impl self, long address) {
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        self.data[2] = U.getInt(address + 8L);
        self.data[3] = U.getInt(address + 12L);
        return self;
    }

    public Int4 loadHeap(Int4Impl self, byte[] bytes, long address) {
        self.data[0] = U.getInt(bytes, address);
        self.data[1] = U.getInt(bytes, address + 4L);
        self.data[2] = U.getInt(bytes, address + 8L);
        self.data[3] = U.getInt(bytes, address + 12L);
        return self;
    }

    public Int4 storeLongUnsafe(Int4Impl self, long address) {
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        U.putLong(address + 24L, self.data[3]);
        return self;
    }

    public Int4 storeLongHeap(Int4Impl self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.data[0]);
        U.putLong(bytes, address + 8L, self.data[1]);
        U.putLong(bytes, address + 16L, self.data[2]);
        U.putLong(bytes, address + 24L, self.data[3]);
        return self;
    }

    public Int4 loadLongUnsafe(Int4Impl self, long address) {
        self.data[0] = (int) U.getLong(address);
        self.data[1] = (int) U.getLong(address + 8L);
        self.data[2] = (int) U.getLong(address + 16L);
        self.data[3] = (int) U.getLong(address + 24L);
        return self;
    }

    public Int4 loadLongHeap(Int4Impl self, byte[] bytes, long address) {
        self.data[0] = (int) U.getLong(bytes, address);
        self.data[1] = (int) U.getLong(bytes, address + 8L);
        self.data[2] = (int) U.getLong(bytes, address + 16L);
        self.data[3] = (int) U.getLong(bytes, address + 24L);
        return self;
    }
}
