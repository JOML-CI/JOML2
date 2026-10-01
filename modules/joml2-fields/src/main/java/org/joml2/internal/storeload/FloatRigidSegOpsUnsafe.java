// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRigidSegOpsUnsafe extends FloatRigidSegOps {

    private static final FloatRigidSegOpsMS MS = new FloatRigidSegOpsMS();

    public MemorySegment store(FloatRigidImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.tX);
        U.putFloat(address + 4L, self.tY);
        U.putFloat(address + 8L, self.tZ);
        U.putFloat(address + 12L, self.rX);
        U.putFloat(address + 16L, self.rY);
        U.putFloat(address + 20L, self.rZ);
        U.putFloat(address + 24L, self.rW);
        return dest;
    }
    public FloatRigid load(FloatRigidImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.tX = U.getFloat(address);
        self.tY = U.getFloat(address + 4L);
        self.tZ = U.getFloat(address + 8L);
        self.rX = U.getFloat(address + 12L);
        self.rY = U.getFloat(address + 16L);
        self.rZ = U.getFloat(address + 20L);
        self.rW = U.getFloat(address + 24L);
        return self;
    }
    public MemorySegment storeDouble(FloatRigidImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.tX);
        U.putDouble(address + 8L, self.tY);
        U.putDouble(address + 16L, self.tZ);
        U.putDouble(address + 24L, self.rX);
        U.putDouble(address + 32L, self.rY);
        U.putDouble(address + 40L, self.rZ);
        U.putDouble(address + 48L, self.rW);
        return dest;
    }
    public FloatRigid loadDouble(FloatRigidImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        long address = src.address() + offset;
        self.tX = (float) U.getDouble(address);
        self.tY = (float) U.getDouble(address + 8L);
        self.tZ = (float) U.getDouble(address + 16L);
        self.rX = (float) U.getDouble(address + 24L);
        self.rY = (float) U.getDouble(address + 32L);
        self.rZ = (float) U.getDouble(address + 40L);
        self.rW = (float) U.getDouble(address + 48L);
        return self;
    }
}
