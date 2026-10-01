// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3RawOpsUnsafe extends Float3RawOps {
    public Float3 storeUnsafe(Float3Impl self, long address) {
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        return self;
    }

    public Float3 storeHeap(Float3Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.data[0]);
        U.putFloat(bytes, address + 4L, self.data[1]);
        U.putFloat(bytes, address + 8L, self.data[2]);
        return self;
    }

    public Float3 loadUnsafe(Float3Impl self, long address) {
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        return self;
    }

    public Float3 loadHeap(Float3Impl self, byte[] bytes, long address) {
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4L);
        self.data[2] = U.getFloat(bytes, address + 8L);
        return self;
    }

    public Float3 storeDoubleUnsafe(Float3Impl self, long address) {
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        return self;
    }

    public Float3 storeDoubleHeap(Float3Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[1]);
        U.putDouble(bytes, address + 16L, self.data[2]);
        return self;
    }

    public Float3 loadDoubleUnsafe(Float3Impl self, long address) {
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        return self;
    }

    public Float3 loadDoubleHeap(Float3Impl self, byte[] bytes, long address) {
        self.data[0] = (float) U.getDouble(bytes, address);
        self.data[1] = (float) U.getDouble(bytes, address + 8L);
        self.data[2] = (float) U.getDouble(bytes, address + 16L);
        return self;
    }
}
