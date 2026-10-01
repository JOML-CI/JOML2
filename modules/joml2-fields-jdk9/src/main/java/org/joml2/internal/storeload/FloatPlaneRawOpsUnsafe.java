// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatPlaneRawOpsUnsafe extends FloatPlaneRawOps {
    public FloatPlane storeUnsafe(FloatPlaneImpl self, long address) {
        U.putFloat(address, self.a);
        U.putFloat(address + 4L, self.b);
        U.putFloat(address + 8L, self.c);
        U.putFloat(address + 12L, self.d);
        return self;
    }

    public FloatPlane storeHeap(FloatPlaneImpl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.a);
        U.putFloat(bytes, address + 4L, self.b);
        U.putFloat(bytes, address + 8L, self.c);
        U.putFloat(bytes, address + 12L, self.d);
        return self;
    }

    public FloatPlane loadUnsafe(FloatPlaneImpl self, long address) {
        self.a = U.getFloat(address);
        self.b = U.getFloat(address + 4L);
        self.c = U.getFloat(address + 8L);
        self.d = U.getFloat(address + 12L);
        return self;
    }

    public FloatPlane loadHeap(FloatPlaneImpl self, byte[] bytes, long address) {
        self.a = U.getFloat(bytes, address);
        self.b = U.getFloat(bytes, address + 4L);
        self.c = U.getFloat(bytes, address + 8L);
        self.d = U.getFloat(bytes, address + 12L);
        return self;
    }

    public FloatPlane storeDoubleUnsafe(FloatPlaneImpl self, long address) {
        U.putDouble(address, self.a);
        U.putDouble(address + 8L, self.b);
        U.putDouble(address + 16L, self.c);
        U.putDouble(address + 24L, self.d);
        return self;
    }

    public FloatPlane storeDoubleHeap(FloatPlaneImpl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.a);
        U.putDouble(bytes, address + 8L, self.b);
        U.putDouble(bytes, address + 16L, self.c);
        U.putDouble(bytes, address + 24L, self.d);
        return self;
    }

    public FloatPlane loadDoubleUnsafe(FloatPlaneImpl self, long address) {
        self.a = (float) U.getDouble(address);
        self.b = (float) U.getDouble(address + 8L);
        self.c = (float) U.getDouble(address + 16L);
        self.d = (float) U.getDouble(address + 24L);
        return self;
    }

    public FloatPlane loadDoubleHeap(FloatPlaneImpl self, byte[] bytes, long address) {
        self.a = (float) U.getDouble(bytes, address);
        self.b = (float) U.getDouble(bytes, address + 8L);
        self.c = (float) U.getDouble(bytes, address + 16L);
        self.d = (float) U.getDouble(bytes, address + 24L);
        return self;
    }
}
