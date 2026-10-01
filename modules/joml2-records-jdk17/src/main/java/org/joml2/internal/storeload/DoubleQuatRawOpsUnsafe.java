// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleQuatRawOpsUnsafe extends DoubleQuatRawOps {
    public DoubleQuat storeUnsafe(DoubleQuat self, long address) {
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        U.putDouble(address + 24L, self.w());
        return self;
    }

    public DoubleQuat storeHeap(DoubleQuat self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.x());
        U.putDouble(bytes, address + 8L, self.y());
        U.putDouble(bytes, address + 16L, self.z());
        U.putDouble(bytes, address + 24L, self.w());
        return self;
    }

    public DoubleQuat loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }

    public DoubleQuat loadHeap(byte[] bytes, long address) {
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8L);
        double _c2 = U.getDouble(bytes, address + 16L);
        double _c3 = U.getDouble(bytes, address + 24L);
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }

    public DoubleQuat storeFloatUnsafe(DoubleQuat self, long address) {
        U.putFloat(address, (float) self.x());
        U.putFloat(address + 4L, (float) self.y());
        U.putFloat(address + 8L, (float) self.z());
        U.putFloat(address + 12L, (float) self.w());
        return self;
    }

    public DoubleQuat storeFloatHeap(DoubleQuat self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.x());
        U.putFloat(bytes, address + 4L, (float) self.y());
        U.putFloat(bytes, address + 8L, (float) self.z());
        U.putFloat(bytes, address + 12L, (float) self.w());
        return self;
    }

    public DoubleQuat loadFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }

    public DoubleQuat loadFloatHeap(byte[] bytes, long address) {
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4L);
        double _c2 = U.getFloat(bytes, address + 8L);
        double _c3 = U.getFloat(bytes, address + 12L);
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }
}
