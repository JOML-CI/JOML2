// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleQuatRawOpsUnsafe extends DoubleQuatRawOps {
    public DoubleQuat storeUnsafe(DoubleQuatImpl self, long address) {
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        U.putDouble(address + 24L, self.w);
        return self;
    }

    public DoubleQuat storeHeap(DoubleQuatImpl self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.x);
        U.putDouble(bytes, address + 8L, self.y);
        U.putDouble(bytes, address + 16L, self.z);
        U.putDouble(bytes, address + 24L, self.w);
        return self;
    }

    public DoubleQuat loadUnsafe(DoubleQuatImpl self, long address) {
        self.x = U.getDouble(address);
        self.y = U.getDouble(address + 8L);
        self.z = U.getDouble(address + 16L);
        self.w = U.getDouble(address + 24L);
        return self;
    }

    public DoubleQuat loadHeap(DoubleQuatImpl self, byte[] bytes, long address) {
        self.x = U.getDouble(bytes, address);
        self.y = U.getDouble(bytes, address + 8L);
        self.z = U.getDouble(bytes, address + 16L);
        self.w = U.getDouble(bytes, address + 24L);
        return self;
    }

    public DoubleQuat storeFloatUnsafe(DoubleQuatImpl self, long address) {
        U.putFloat(address, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        U.putFloat(address + 8L, (float) self.z);
        U.putFloat(address + 12L, (float) self.w);
        return self;
    }

    public DoubleQuat storeFloatHeap(DoubleQuatImpl self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.x);
        U.putFloat(bytes, address + 4L, (float) self.y);
        U.putFloat(bytes, address + 8L, (float) self.z);
        U.putFloat(bytes, address + 12L, (float) self.w);
        return self;
    }

    public DoubleQuat loadFloatUnsafe(DoubleQuatImpl self, long address) {
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        self.w = U.getFloat(address + 12L);
        return self;
    }

    public DoubleQuat loadFloatHeap(DoubleQuatImpl self, byte[] bytes, long address) {
        self.x = U.getFloat(bytes, address);
        self.y = U.getFloat(bytes, address + 4L);
        self.z = U.getFloat(bytes, address + 8L);
        self.w = U.getFloat(bytes, address + 12L);
        return self;
    }
}
