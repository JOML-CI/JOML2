// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRigidRawOpsUnsafe extends DoubleRigidRawOps {
    public DoubleRigid storeUnsafe(DoubleRigid self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.tX());
        _U.putDouble(address + 8L, self.tY());
        _U.putDouble(address + 16L, self.tZ());
        _U.putDouble(address + 24L, self.rX());
        _U.putDouble(address + 32L, self.rY());
        _U.putDouble(address + 40L, self.rZ());
        _U.putDouble(address + 48L, self.rW());
        return self;
    }

    public DoubleRigid storeHeap(DoubleRigid self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.tX());
        _U.putDouble(bytes, address + 8L, self.tY());
        _U.putDouble(bytes, address + 16L, self.tZ());
        _U.putDouble(bytes, address + 24L, self.rX());
        _U.putDouble(bytes, address + 32L, self.rY());
        _U.putDouble(bytes, address + 40L, self.rZ());
        _U.putDouble(bytes, address + 48L, self.rW());
        return self;
    }

    public DoubleRigid loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }

    public DoubleRigid loadHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        double _c6 = _U.getDouble(bytes, address + 48L);
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }

    public DoubleRigid storeFloatUnsafe(DoubleRigid self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.tX());
        _U.putFloat(address + 4L, (float) self.tY());
        _U.putFloat(address + 8L, (float) self.tZ());
        _U.putFloat(address + 12L, (float) self.rX());
        _U.putFloat(address + 16L, (float) self.rY());
        _U.putFloat(address + 20L, (float) self.rZ());
        _U.putFloat(address + 24L, (float) self.rW());
        return self;
    }

    public DoubleRigid storeFloatHeap(DoubleRigid self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.tX());
        _U.putFloat(bytes, address + 4L, (float) self.tY());
        _U.putFloat(bytes, address + 8L, (float) self.tZ());
        _U.putFloat(bytes, address + 12L, (float) self.rX());
        _U.putFloat(bytes, address + 16L, (float) self.rY());
        _U.putFloat(bytes, address + 20L, (float) self.rZ());
        _U.putFloat(bytes, address + 24L, (float) self.rW());
        return self;
    }

    public DoubleRigid loadFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }

    public DoubleRigid loadFloatHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        double _c6 = _U.getFloat(bytes, address + 24L);
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
}
