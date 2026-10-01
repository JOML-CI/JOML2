// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3RawOpsUnsafe extends Float3RawOps {
    public Float3 storeUnsafe(Float3Impl self, long address) {
        U.putFloat(address, self.x);
        U.putFloat(address + 4L, self.y);
        U.putFloat(address + 8L, self.z);
        return self;
    }

    public Float3 storeHeap(Float3Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.x);
        U.putFloat(bytes, address + 4L, self.y);
        U.putFloat(bytes, address + 8L, self.z);
        return self;
    }

    public Float3 loadUnsafe(Float3Impl self, long address) {
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        return self;
    }

    public Float3 loadHeap(Float3Impl self, byte[] bytes, long address) {
        self.x = U.getFloat(bytes, address);
        self.y = U.getFloat(bytes, address + 4L);
        self.z = U.getFloat(bytes, address + 8L);
        return self;
    }

    public Float3 storeDoubleUnsafe(Float3Impl self, long address) {
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        return self;
    }

    public Float3 storeDoubleHeap(Float3Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.x);
        U.putDouble(bytes, address + 8L, self.y);
        U.putDouble(bytes, address + 16L, self.z);
        return self;
    }

    public Float3 loadDoubleUnsafe(Float3Impl self, long address) {
        self.x = (float) U.getDouble(address);
        self.y = (float) U.getDouble(address + 8L);
        self.z = (float) U.getDouble(address + 16L);
        return self;
    }

    public Float3 loadDoubleHeap(Float3Impl self, byte[] bytes, long address) {
        self.x = (float) U.getDouble(bytes, address);
        self.y = (float) U.getDouble(bytes, address + 8L);
        self.z = (float) U.getDouble(bytes, address + 16L);
        return self;
    }
}
