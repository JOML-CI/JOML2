// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatSphereRawOpsUnsafe extends FloatSphereRawOps {
    public FloatSphere storeUnsafe(FloatSphere self, long address) {
        U.putFloat(address, self.x());
        U.putFloat(address + 4L, self.y());
        U.putFloat(address + 8L, self.z());
        U.putFloat(address + 12L, self.r());
        return self;
    }

    public FloatSphere storeHeap(FloatSphere self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.x());
        U.putFloat(bytes, address + 4L, self.y());
        U.putFloat(bytes, address + 8L, self.z());
        U.putFloat(bytes, address + 12L, self.r());
        return self;
    }

    public FloatSphere loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }

    public FloatSphere loadHeap(byte[] bytes, long address) {
        float _c0 = U.getFloat(bytes, address);
        float _c1 = U.getFloat(bytes, address + 4L);
        float _c2 = U.getFloat(bytes, address + 8L);
        float _c3 = U.getFloat(bytes, address + 12L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }

    public FloatSphere storeDoubleUnsafe(FloatSphere self, long address) {
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        U.putDouble(address + 24L, self.r());
        return self;
    }

    public FloatSphere storeDoubleHeap(FloatSphere self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.x());
        U.putDouble(bytes, address + 8L, self.y());
        U.putDouble(bytes, address + 16L, self.z());
        U.putDouble(bytes, address + 24L, self.r());
        return self;
    }

    public FloatSphere loadDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }

    public FloatSphere loadDoubleHeap(byte[] bytes, long address) {
        float _c0 = (float) U.getDouble(bytes, address);
        float _c1 = (float) U.getDouble(bytes, address + 8L);
        float _c2 = (float) U.getDouble(bytes, address + 16L);
        float _c3 = (float) U.getDouble(bytes, address + 24L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }
}
