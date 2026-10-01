// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int2RawOpsUnsafe extends Int2RawOps {
    public Int2 storeUnsafe(Int2Impl self, long address) {
        U.putInt(address, self.data[0]);
        U.putInt(address + 4L, self.data[1]);
        return self;
    }

    public Int2 storeHeap(Int2Impl self, byte[] bytes, long address) {
        U.putInt(bytes, address, self.data[0]);
        U.putInt(bytes, address + 4L, self.data[1]);
        return self;
    }

    public Int2 loadUnsafe(Int2Impl self, long address) {
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        return self;
    }

    public Int2 loadHeap(Int2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getInt(bytes, address);
        self.data[1] = U.getInt(bytes, address + 4L);
        return self;
    }

    public Int2 storeLongUnsafe(Int2Impl self, long address) {
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        return self;
    }

    public Int2 storeLongHeap(Int2Impl self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.data[0]);
        U.putLong(bytes, address + 8L, self.data[1]);
        return self;
    }

    public Int2 loadLongUnsafe(Int2Impl self, long address) {
        self.data[0] = (int) U.getLong(address);
        self.data[1] = (int) U.getLong(address + 8L);
        return self;
    }

    public Int2 loadLongHeap(Int2Impl self, byte[] bytes, long address) {
        self.data[0] = (int) U.getLong(bytes, address);
        self.data[1] = (int) U.getLong(bytes, address + 8L);
        return self;
    }
}
