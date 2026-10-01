// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4RawOpsUnsafe extends Float4RawOps {
    public Float4 storeUnsafe(Float4Impl self, long address) {
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        return self;
    }

    public Float4 storeHeap(Float4Impl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.data[0]);
        U.putFloat(bytes, address + 4L, self.data[1]);
        U.putFloat(bytes, address + 8L, self.data[2]);
        U.putFloat(bytes, address + 12L, self.data[3]);
        return self;
    }

    public Float4 loadUnsafe(Float4Impl self, long address) {
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        return self;
    }

    public Float4 loadHeap(Float4Impl self, byte[] bytes, long address) {
        self.data[0] = U.getFloat(bytes, address);
        self.data[1] = U.getFloat(bytes, address + 4L);
        self.data[2] = U.getFloat(bytes, address + 8L);
        self.data[3] = U.getFloat(bytes, address + 12L);
        return self;
    }

    public Float4 storeDoubleUnsafe(Float4Impl self, long address) {
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        return self;
    }

    public Float4 storeDoubleHeap(Float4Impl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.data[0]);
        U.putDouble(bytes, address + 8L, self.data[1]);
        U.putDouble(bytes, address + 16L, self.data[2]);
        U.putDouble(bytes, address + 24L, self.data[3]);
        return self;
    }

    public Float4 loadDoubleUnsafe(Float4Impl self, long address) {
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        return self;
    }

    public Float4 loadDoubleHeap(Float4Impl self, byte[] bytes, long address) {
        self.data[0] = (float) U.getDouble(bytes, address);
        self.data[1] = (float) U.getDouble(bytes, address + 8L);
        self.data[2] = (float) U.getDouble(bytes, address + 16L);
        self.data[3] = (float) U.getDouble(bytes, address + 24L);
        return self;
    }
}
