// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3RawOpsUnsafe extends Double3RawOps {
    public Double3 storeUnsafe(Double3Impl self, long address) {
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        return self;
    }

    public Double3 storeHeap(Double3Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[1]);
        U.putDouble(bytes, address + 16L, self.data[2]);
        return self;
    }

    public Double3 loadUnsafe(Double3Impl self, long address) {
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        return self;
    }

    public Double3 loadHeap(Double3Impl self, byte[] bytes, long address) {
        self.data[0] = U.getDouble(bytes, address);
        self.data[1] = U.getDouble(bytes, address + 8L);
        self.data[2] = U.getDouble(bytes, address + 16L);
        return self;
    }

    public Double3 storeFloatUnsafe(Double3Impl self, long address) {
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        return self;
    }

    public Double3 storeFloatHeap(Double3Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.data[0]);
        U.putFloat(bytes, address + 4L, (float) self.data[1]);
        U.putFloat(bytes, address + 8L, (float) self.data[2]);
        return self;
    }

    public Double3 loadFloatUnsafe(Double3Impl self, long address) {
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        return self;
    }

    public Double3 loadFloatHeap(Double3Impl self, byte[] bytes, long address) {
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4L);
        self.data[2] = U.getFloat(bytes, address + 8L);
        return self;
    }
}
