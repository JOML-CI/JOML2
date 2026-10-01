// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoublePlaneRawOpsUnsafe extends DoublePlaneRawOps {
    public DoublePlane storeUnsafe(DoublePlane self, long address) {
        U.putDouble(address, self.a());
        U.putDouble(address + 8L, self.b());
        U.putDouble(address + 16L, self.c());
        U.putDouble(address + 24L, self.d());
        return self;
    }

    public DoublePlane storeHeap(DoublePlane self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.a());
        U.putDouble(bytes, address + 8L, self.b());
        U.putDouble(bytes, address + 16L, self.c());
        U.putDouble(bytes, address + 24L, self.d());
        return self;
    }

    public DoublePlane loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }

    public DoublePlane loadHeap(byte[] bytes, long address) {
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8L);
        double _c2 = U.getDouble(bytes, address + 16L);
        double _c3 = U.getDouble(bytes, address + 24L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }

    public DoublePlane storeFloatUnsafe(DoublePlane self, long address) {
        U.putFloat(address, (float) self.a());
        U.putFloat(address + 4L, (float) self.b());
        U.putFloat(address + 8L, (float) self.c());
        U.putFloat(address + 12L, (float) self.d());
        return self;
    }

    public DoublePlane storeFloatHeap(DoublePlane self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.a());
        U.putFloat(bytes, address + 4L, (float) self.b());
        U.putFloat(bytes, address + 8L, (float) self.c());
        U.putFloat(bytes, address + 12L, (float) self.d());
        return self;
    }

    public DoublePlane loadFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }

    public DoublePlane loadFloatHeap(byte[] bytes, long address) {
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4L);
        double _c2 = U.getFloat(bytes, address + 8L);
        double _c3 = U.getFloat(bytes, address + 12L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
}
