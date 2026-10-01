// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRectRawOpsUnsafe implements DoubleRectRawOps {
    public DoubleRect storeUnsafe(DoubleRectImpl self, long address) {
        U.putDouble(address + 0L, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return self;
    }
    public DoubleRect loadUnsafe(DoubleRectImpl self, long address) {
        self.minX = U.getDouble(address + 0L);
        self.minY = U.getDouble(address + 8L);
        self.maxX = U.getDouble(address + 16L);
        self.maxY = U.getDouble(address + 24L);
        return self;
    }
    public DoubleRect storeFloatUnsafe(DoubleRectImpl self, long address) {
        U.putFloat(address + 0L, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.maxX);
        U.putFloat(address + 12L, (float) self.maxY);
        return self;
    }
    public DoubleRect loadFloatUnsafe(DoubleRectImpl self, long address) {
        self.minX = U.getFloat(address + 0L);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }
}
