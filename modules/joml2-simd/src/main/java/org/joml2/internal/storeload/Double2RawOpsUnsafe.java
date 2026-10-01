// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2RawOpsUnsafe extends Double2RawOps {
    public Double2 storeUnsafe(Double2Impl self, long address) {
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        return self;
    }

    public Double2 storeHeap(Double2Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[1]);
        return self;
    }

    public Double2 loadUnsafe(Double2Impl self, long address) {
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        return self;
    }

    public Double2 loadHeap(Double2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getDouble(bytes, address);
        self.data[1] = U.getDouble(bytes, address + 8L);
        return self;
    }

    public Double2 storeFloatUnsafe(Double2Impl self, long address) {
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        return self;
    }

    public Double2 storeFloatHeap(Double2Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.data[0]);
        U.putFloat(bytes, address + 4L, (float) self.data[1]);
        return self;
    }

    public Double2 loadFloatUnsafe(Double2Impl self, long address) {
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        return self;
    }

    public Double2 loadFloatHeap(Double2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4L);
        return self;
    }
}
