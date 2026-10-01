// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatTriangleRawOpsUnsafe extends FloatTriangleRawOps {
    public FloatTriangle storeUnsafe(FloatTriangleImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.v0X);
        _U.putFloat(address + 4L, self.v0Y);
        _U.putFloat(address + 8L, self.v0Z);
        _U.putFloat(address + 12L, self.v1X);
        _U.putFloat(address + 16L, self.v1Y);
        _U.putFloat(address + 20L, self.v1Z);
        _U.putFloat(address + 24L, self.v2X);
        _U.putFloat(address + 28L, self.v2Y);
        _U.putFloat(address + 32L, self.v2Z);
        return self;
    }

    public FloatTriangle storeHeap(FloatTriangleImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.v0X);
        _U.putFloat(bytes, address + 4L, self.v0Y);
        _U.putFloat(bytes, address + 8L, self.v0Z);
        _U.putFloat(bytes, address + 12L, self.v1X);
        _U.putFloat(bytes, address + 16L, self.v1Y);
        _U.putFloat(bytes, address + 20L, self.v1Z);
        _U.putFloat(bytes, address + 24L, self.v2X);
        _U.putFloat(bytes, address + 28L, self.v2Y);
        _U.putFloat(bytes, address + 32L, self.v2Z);
        return self;
    }

    public FloatTriangle loadUnsafe(FloatTriangleImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = _U.getFloat(address);
        self.v0Y = _U.getFloat(address + 4L);
        self.v0Z = _U.getFloat(address + 8L);
        self.v1X = _U.getFloat(address + 12L);
        self.v1Y = _U.getFloat(address + 16L);
        self.v1Z = _U.getFloat(address + 20L);
        self.v2X = _U.getFloat(address + 24L);
        self.v2Y = _U.getFloat(address + 28L);
        self.v2Z = _U.getFloat(address + 32L);
        return self;
    }

    public FloatTriangle loadHeap(FloatTriangleImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = _U.getFloat(bytes, address);
        self.v0Y = _U.getFloat(bytes, address + 4L);
        self.v0Z = _U.getFloat(bytes, address + 8L);
        self.v1X = _U.getFloat(bytes, address + 12L);
        self.v1Y = _U.getFloat(bytes, address + 16L);
        self.v1Z = _U.getFloat(bytes, address + 20L);
        self.v2X = _U.getFloat(bytes, address + 24L);
        self.v2Y = _U.getFloat(bytes, address + 28L);
        self.v2Z = _U.getFloat(bytes, address + 32L);
        return self;
    }

    public FloatTriangle storeDoubleUnsafe(FloatTriangleImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.v0X);
        _U.putDouble(address + 8L, self.v0Y);
        _U.putDouble(address + 16L, self.v0Z);
        _U.putDouble(address + 24L, self.v1X);
        _U.putDouble(address + 32L, self.v1Y);
        _U.putDouble(address + 40L, self.v1Z);
        _U.putDouble(address + 48L, self.v2X);
        _U.putDouble(address + 56L, self.v2Y);
        _U.putDouble(address + 64L, self.v2Z);
        return self;
    }

    public FloatTriangle storeDoubleHeap(FloatTriangleImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.v0X);
        _U.putDouble(bytes, address + 8L, self.v0Y);
        _U.putDouble(bytes, address + 16L, self.v0Z);
        _U.putDouble(bytes, address + 24L, self.v1X);
        _U.putDouble(bytes, address + 32L, self.v1Y);
        _U.putDouble(bytes, address + 40L, self.v1Z);
        _U.putDouble(bytes, address + 48L, self.v2X);
        _U.putDouble(bytes, address + 56L, self.v2Y);
        _U.putDouble(bytes, address + 64L, self.v2Z);
        return self;
    }

    public FloatTriangle loadDoubleUnsafe(FloatTriangleImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = (float) _U.getDouble(address);
        self.v0Y = (float) _U.getDouble(address + 8L);
        self.v0Z = (float) _U.getDouble(address + 16L);
        self.v1X = (float) _U.getDouble(address + 24L);
        self.v1Y = (float) _U.getDouble(address + 32L);
        self.v1Z = (float) _U.getDouble(address + 40L);
        self.v2X = (float) _U.getDouble(address + 48L);
        self.v2Y = (float) _U.getDouble(address + 56L);
        self.v2Z = (float) _U.getDouble(address + 64L);
        return self;
    }

    public FloatTriangle loadDoubleHeap(FloatTriangleImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = (float) _U.getDouble(bytes, address);
        self.v0Y = (float) _U.getDouble(bytes, address + 8L);
        self.v0Z = (float) _U.getDouble(bytes, address + 16L);
        self.v1X = (float) _U.getDouble(bytes, address + 24L);
        self.v1Y = (float) _U.getDouble(bytes, address + 32L);
        self.v1Z = (float) _U.getDouble(bytes, address + 40L);
        self.v2X = (float) _U.getDouble(bytes, address + 48L);
        self.v2Y = (float) _U.getDouble(bytes, address + 56L);
        self.v2Z = (float) _U.getDouble(bytes, address + 64L);
        return self;
    }
}
