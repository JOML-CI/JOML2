// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleTransformRawOpsUnsafe extends DoubleTransformRawOps {
    public DoubleTransform storeUnsafe(DoubleTransformImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.tX);
        _U.putDouble(address + 8L, self.tY);
        _U.putDouble(address + 16L, self.tZ);
        _U.putDouble(address + 24L, self.rX);
        _U.putDouble(address + 32L, self.rY);
        _U.putDouble(address + 40L, self.rZ);
        _U.putDouble(address + 48L, self.rW);
        _U.putDouble(address + 56L, self.sX);
        _U.putDouble(address + 64L, self.sY);
        _U.putDouble(address + 72L, self.sZ);
        return self;
    }

    public DoubleTransform storeHeap(DoubleTransformImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.tX);
        _U.putDouble(bytes, address + 8L, self.tY);
        _U.putDouble(bytes, address + 16L, self.tZ);
        _U.putDouble(bytes, address + 24L, self.rX);
        _U.putDouble(bytes, address + 32L, self.rY);
        _U.putDouble(bytes, address + 40L, self.rZ);
        _U.putDouble(bytes, address + 48L, self.rW);
        _U.putDouble(bytes, address + 56L, self.sX);
        _U.putDouble(bytes, address + 64L, self.sY);
        _U.putDouble(bytes, address + 72L, self.sZ);
        return self;
    }

    public DoubleTransform loadUnsafe(DoubleTransformImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getDouble(address);
        self.tY = _U.getDouble(address + 8L);
        self.tZ = _U.getDouble(address + 16L);
        self.rX = _U.getDouble(address + 24L);
        self.rY = _U.getDouble(address + 32L);
        self.rZ = _U.getDouble(address + 40L);
        self.rW = _U.getDouble(address + 48L);
        self.sX = _U.getDouble(address + 56L);
        self.sY = _U.getDouble(address + 64L);
        self.sZ = _U.getDouble(address + 72L);
        return self;
    }

    public DoubleTransform loadHeap(DoubleTransformImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getDouble(bytes, address);
        self.tY = _U.getDouble(bytes, address + 8L);
        self.tZ = _U.getDouble(bytes, address + 16L);
        self.rX = _U.getDouble(bytes, address + 24L);
        self.rY = _U.getDouble(bytes, address + 32L);
        self.rZ = _U.getDouble(bytes, address + 40L);
        self.rW = _U.getDouble(bytes, address + 48L);
        self.sX = _U.getDouble(bytes, address + 56L);
        self.sY = _U.getDouble(bytes, address + 64L);
        self.sZ = _U.getDouble(bytes, address + 72L);
        return self;
    }

    public DoubleTransform storeFloatUnsafe(DoubleTransformImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.tX);
        _U.putFloat(address + 4L, (float) self.tY);
        _U.putFloat(address + 8L, (float) self.tZ);
        _U.putFloat(address + 12L, (float) self.rX);
        _U.putFloat(address + 16L, (float) self.rY);
        _U.putFloat(address + 20L, (float) self.rZ);
        _U.putFloat(address + 24L, (float) self.rW);
        _U.putFloat(address + 28L, (float) self.sX);
        _U.putFloat(address + 32L, (float) self.sY);
        _U.putFloat(address + 36L, (float) self.sZ);
        return self;
    }

    public DoubleTransform storeFloatHeap(DoubleTransformImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.tX);
        _U.putFloat(bytes, address + 4L, (float) self.tY);
        _U.putFloat(bytes, address + 8L, (float) self.tZ);
        _U.putFloat(bytes, address + 12L, (float) self.rX);
        _U.putFloat(bytes, address + 16L, (float) self.rY);
        _U.putFloat(bytes, address + 20L, (float) self.rZ);
        _U.putFloat(bytes, address + 24L, (float) self.rW);
        _U.putFloat(bytes, address + 28L, (float) self.sX);
        _U.putFloat(bytes, address + 32L, (float) self.sY);
        _U.putFloat(bytes, address + 36L, (float) self.sZ);
        return self;
    }

    public DoubleTransform loadFloatUnsafe(DoubleTransformImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getFloat(address);
        self.tY = _U.getFloat(address + 4L);
        self.tZ = _U.getFloat(address + 8L);
        self.rX = _U.getFloat(address + 12L);
        self.rY = _U.getFloat(address + 16L);
        self.rZ = _U.getFloat(address + 20L);
        self.rW = _U.getFloat(address + 24L);
        self.sX = _U.getFloat(address + 28L);
        self.sY = _U.getFloat(address + 32L);
        self.sZ = _U.getFloat(address + 36L);
        return self;
    }

    public DoubleTransform loadFloatHeap(DoubleTransformImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getFloat(bytes, address);
        self.tY = _U.getFloat(bytes, address + 4L);
        self.tZ = _U.getFloat(bytes, address + 8L);
        self.rX = _U.getFloat(bytes, address + 12L);
        self.rY = _U.getFloat(bytes, address + 16L);
        self.rZ = _U.getFloat(bytes, address + 20L);
        self.rW = _U.getFloat(bytes, address + 24L);
        self.sX = _U.getFloat(bytes, address + 28L);
        self.sY = _U.getFloat(bytes, address + 32L);
        self.sZ = _U.getFloat(bytes, address + 36L);
        return self;
    }
}
