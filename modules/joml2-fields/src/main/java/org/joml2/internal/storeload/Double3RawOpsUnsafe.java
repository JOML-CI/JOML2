// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3RawOpsUnsafe extends Double3RawOps {
    public Double3 storeUnsafe(Double3Impl self, long address) {
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        return self;
    }

    public Double3 storeHeap(Double3Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.x);
        U.putDouble(bytes, address + 8L, self.y);
        U.putDouble(bytes, address + 16L, self.z);
        return self;
    }

    public Double3 loadUnsafe(Double3Impl self, long address) {
        self.x = U.getDouble(address);
        self.y = U.getDouble(address + 8L);
        self.z = U.getDouble(address + 16L);
        return self;
    }

    public Double3 loadHeap(Double3Impl self, byte[] bytes, long address) {
        self.x = U.getDouble(bytes, address);
        self.y = U.getDouble(bytes, address + 8L);
        self.z = U.getDouble(bytes, address + 16L);
        return self;
    }

    public Double3 storeFloatUnsafe(Double3Impl self, long address) {
        U.putFloat(address, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        U.putFloat(address + 8L, (float) self.z);
        return self;
    }

    public Double3 storeFloatHeap(Double3Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.x);
        U.putFloat(bytes, address + 4L, (float) self.y);
        U.putFloat(bytes, address + 8L, (float) self.z);
        return self;
    }

    public Double3 loadFloatUnsafe(Double3Impl self, long address) {
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        return self;
    }

    public Double3 loadFloatHeap(Double3Impl self, byte[] bytes, long address) {
        self.x = U.getFloat(bytes, address);
        self.y = U.getFloat(bytes, address + 4L);
        self.z = U.getFloat(bytes, address + 8L);
        return self;
    }
}
