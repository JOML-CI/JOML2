// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatTransformRawOpsUnsafe implements FloatTransformRawOps {
    public FloatTransform storeUnsafe(FloatTransformImpl self, long address) {
        U.putFloat(address + 0L, self.tX);
        U.putFloat(address + 4L, self.tY);
        U.putFloat(address + 8L, self.tZ);
        U.putFloat(address + 12L, self.rX);
        U.putFloat(address + 16L, self.rY);
        U.putFloat(address + 20L, self.rZ);
        U.putFloat(address + 24L, self.rW);
        U.putFloat(address + 28L, self.sX);
        U.putFloat(address + 32L, self.sY);
        U.putFloat(address + 36L, self.sZ);
        return self;
    }
    public FloatTransform loadUnsafe(FloatTransformImpl self, long address) {
        self.tX = U.getFloat(address + 0L);
        self.tY = U.getFloat(address + 4L);
        self.tZ = U.getFloat(address + 8L);
        self.rX = U.getFloat(address + 12L);
        self.rY = U.getFloat(address + 16L);
        self.rZ = U.getFloat(address + 20L);
        self.rW = U.getFloat(address + 24L);
        self.sX = U.getFloat(address + 28L);
        self.sY = U.getFloat(address + 32L);
        self.sZ = U.getFloat(address + 36L);
        return self;
    }
    public FloatTransform storeDoubleUnsafe(FloatTransformImpl self, long address) {
        U.putDouble(address + 0L, self.tX);
        U.putDouble(address + 8L, self.tY);
        U.putDouble(address + 16L, self.tZ);
        U.putDouble(address + 24L, self.rX);
        U.putDouble(address + 32L, self.rY);
        U.putDouble(address + 40L, self.rZ);
        U.putDouble(address + 48L, self.rW);
        U.putDouble(address + 56L, self.sX);
        U.putDouble(address + 64L, self.sY);
        U.putDouble(address + 72L, self.sZ);
        return self;
    }
    public FloatTransform loadDoubleUnsafe(FloatTransformImpl self, long address) {
        self.tX = (float) U.getDouble(address + 0L);
        self.tY = (float) U.getDouble(address + 8L);
        self.tZ = (float) U.getDouble(address + 16L);
        self.rX = (float) U.getDouble(address + 24L);
        self.rY = (float) U.getDouble(address + 32L);
        self.rZ = (float) U.getDouble(address + 40L);
        self.rW = (float) U.getDouble(address + 48L);
        self.sX = (float) U.getDouble(address + 56L);
        self.sY = (float) U.getDouble(address + 64L);
        self.sZ = (float) U.getDouble(address + 72L);
        return self;
    }
}
