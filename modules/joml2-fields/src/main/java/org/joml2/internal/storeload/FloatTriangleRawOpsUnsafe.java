// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatTriangleRawOpsUnsafe implements FloatTriangleRawOps {
    public FloatTriangle storeUnsafe(FloatTriangleImpl self, long address) {
        U.putFloat(address + 0L, self.v0X);
        U.putFloat(address + 4L, self.v0Y);
        U.putFloat(address + 8L, self.v0Z);
        U.putFloat(address + 12L, self.v1X);
        U.putFloat(address + 16L, self.v1Y);
        U.putFloat(address + 20L, self.v1Z);
        U.putFloat(address + 24L, self.v2X);
        U.putFloat(address + 28L, self.v2Y);
        U.putFloat(address + 32L, self.v2Z);
        return self;
    }
    public FloatTriangle loadUnsafe(FloatTriangleImpl self, long address) {
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
    public FloatTriangle storeDoubleUnsafe(FloatTriangleImpl self, long address) {
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
    public FloatTriangle loadDoubleUnsafe(FloatTriangleImpl self, long address) {
        self.v0X = (float) U.getDouble(address + 0L);
        self.v0Y = (float) U.getDouble(address + 8L);
        self.v0Z = (float) U.getDouble(address + 16L);
        self.v1X = (float) U.getDouble(address + 24L);
        self.v1Y = (float) U.getDouble(address + 32L);
        self.v1Z = (float) U.getDouble(address + 40L);
        self.v2X = (float) U.getDouble(address + 48L);
        self.v2Y = (float) U.getDouble(address + 56L);
        self.v2Z = (float) U.getDouble(address + 64L);
        return self;
    }
}
