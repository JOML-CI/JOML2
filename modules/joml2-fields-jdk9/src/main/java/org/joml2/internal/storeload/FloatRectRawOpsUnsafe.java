// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRectRawOpsUnsafe implements FloatRectRawOps {
    public FloatRect storeUnsafe(FloatRectImpl self, long address) {
        U.putFloat(address + 0L, self.minX);
        U.putFloat(address + 4L, self.minY);
        U.putFloat(address + 8L, self.maxX);
        U.putFloat(address + 12L, self.maxY);
        return self;
    }
    public FloatRect loadUnsafe(FloatRectImpl self, long address) {
        self.minX = U.getFloat(address + 0L);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }
    public FloatRect storeDoubleUnsafe(FloatRectImpl self, long address) {
        U.putDouble(address + 0L, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return self;
    }
    public FloatRect loadDoubleUnsafe(FloatRectImpl self, long address) {
        self.minX = (float) U.getDouble(address + 0L);
        self.minY = (float) U.getDouble(address + 8L);
        self.maxX = (float) U.getDouble(address + 16L);
        self.maxY = (float) U.getDouble(address + 24L);
        return self;
    }
}
