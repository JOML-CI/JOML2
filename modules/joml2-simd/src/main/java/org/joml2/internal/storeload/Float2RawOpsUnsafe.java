// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2RawOpsUnsafe extends Float2RawOps {
    public Float2 storeUnsafe(Float2Impl self, long address) {
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        return self;
    }

    public Float2 storeHeap(Float2Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.data[0]);
        U.putFloat(bytes, address + 4L, self.data[1]);
        return self;
    }

    public Float2 loadUnsafe(Float2Impl self, long address) {
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        return self;
    }

    public Float2 loadHeap(Float2Impl self, byte[] bytes, long address) {
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4L);
        return self;
    }

    public Float2 storeDoubleUnsafe(Float2Impl self, long address) {
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        return self;
    }

    public Float2 storeDoubleHeap(Float2Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[1]);
        return self;
    }

    public Float2 loadDoubleUnsafe(Float2Impl self, long address) {
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        return self;
    }

    public Float2 loadDoubleHeap(Float2Impl self, byte[] bytes, long address) {
        self.data[0] = (float) U.getDouble(bytes, address);
        self.data[1] = (float) U.getDouble(bytes, address + 8L);
        return self;
    }
}
