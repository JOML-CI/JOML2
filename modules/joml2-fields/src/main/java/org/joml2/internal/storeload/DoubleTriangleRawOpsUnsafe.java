// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleTriangleRawOpsUnsafe implements DoubleTriangleRawOps {
    public DoubleTriangle storeUnsafe(DoubleTriangleImpl self, long address) {
        U.putDouble(address + 0L, self.v0X);
        U.putDouble(address + 8L, self.v0Y);
        U.putDouble(address + 16L, self.v0Z);
        U.putDouble(address + 24L, self.v1X);
        U.putDouble(address + 32L, self.v1Y);
        U.putDouble(address + 40L, self.v1Z);
        U.putDouble(address + 48L, self.v2X);
        U.putDouble(address + 56L, self.v2Y);
        U.putDouble(address + 64L, self.v2Z);
        return self;
    }
    public DoubleTriangle loadUnsafe(DoubleTriangleImpl self, long address) {
        self.v0X = U.getDouble(address + 0L);
        self.v0Y = U.getDouble(address + 8L);
        self.v0Z = U.getDouble(address + 16L);
        self.v1X = U.getDouble(address + 24L);
        self.v1Y = U.getDouble(address + 32L);
        self.v1Z = U.getDouble(address + 40L);
        self.v2X = U.getDouble(address + 48L);
        self.v2Y = U.getDouble(address + 56L);
        self.v2Z = U.getDouble(address + 64L);
        return self;
    }
    public DoubleTriangle storeFloatUnsafe(DoubleTriangleImpl self, long address) {
        U.putFloat(address + 0L, (float) self.v0X);
        U.putFloat(address + 4L, (float) self.v0Y);
        U.putFloat(address + 8L, (float) self.v0Z);
        U.putFloat(address + 12L, (float) self.v1X);
        U.putFloat(address + 16L, (float) self.v1Y);
        U.putFloat(address + 20L, (float) self.v1Z);
        U.putFloat(address + 24L, (float) self.v2X);
        U.putFloat(address + 28L, (float) self.v2Y);
        U.putFloat(address + 32L, (float) self.v2Z);
        return self;
    }
    public DoubleTriangle loadFloatUnsafe(DoubleTriangleImpl self, long address) {
        self.v0X = U.getFloat(address + 0L);
        self.v0Y = U.getFloat(address + 4L);
        self.v0Z = U.getFloat(address + 8L);
        self.v1X = U.getFloat(address + 12L);
        self.v1Y = U.getFloat(address + 16L);
        self.v1Z = U.getFloat(address + 20L);
        self.v2X = U.getFloat(address + 24L);
        self.v2Y = U.getFloat(address + 28L);
        self.v2Z = U.getFloat(address + 32L);
        return self;
    }
}
