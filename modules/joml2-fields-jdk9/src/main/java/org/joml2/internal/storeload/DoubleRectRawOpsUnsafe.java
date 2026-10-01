// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRectRawOpsUnsafe extends DoubleRectRawOps {
    public DoubleRect storeUnsafe(DoubleRectImpl self, long address) {
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return self;
    }

    public DoubleRect storeHeap(DoubleRectImpl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.minX);
        U.putDouble(bytes, address + 8L, self.minY);
        U.putDouble(bytes, address + 16L, self.maxX);
        U.putDouble(bytes, address + 24L, self.maxY);
        return self;
    }

    public DoubleRect loadUnsafe(DoubleRectImpl self, long address) {
        self.minX = U.getDouble(address);
        self.minY = U.getDouble(address + 8L);
        self.maxX = U.getDouble(address + 16L);
        self.maxY = U.getDouble(address + 24L);
        return self;
    }

    public DoubleRect loadHeap(DoubleRectImpl self, byte[] bytes, long address) {
        self.minX = U.getDouble(bytes, address);
        self.minY = U.getDouble(bytes, address + 8L);
        self.maxX = U.getDouble(bytes, address + 16L);
        self.maxY = U.getDouble(bytes, address + 24L);
        return self;
    }

    public DoubleRect storeFloatUnsafe(DoubleRectImpl self, long address) {
        U.putFloat(address, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.maxX);
        U.putFloat(address + 12L, (float) self.maxY);
        return self;
    }

    public DoubleRect storeFloatHeap(DoubleRectImpl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.minX);
        U.putFloat(bytes, address + 4L, (float) self.minY);
        U.putFloat(bytes, address + 8L, (float) self.maxX);
        U.putFloat(bytes, address + 12L, (float) self.maxY);
        return self;
    }

    public DoubleRect loadFloatUnsafe(DoubleRectImpl self, long address) {
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }

    public DoubleRect loadFloatHeap(DoubleRectImpl self, byte[] bytes, long address) {
        self.minX = U.getFloat(bytes, address);
        self.minY = U.getFloat(bytes, address + 4L);
        self.maxX = U.getFloat(bytes, address + 8L);
        self.maxY = U.getFloat(bytes, address + 12L);
        return self;
    }
}
