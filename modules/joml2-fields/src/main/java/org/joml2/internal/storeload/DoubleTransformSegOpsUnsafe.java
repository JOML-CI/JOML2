// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleTransformSegOpsUnsafe extends DoubleTransformSegOps {

    private static final DoubleTransformSegOpsMS MS = new DoubleTransformSegOpsMS();

    public MemorySegment store(DoubleTransformImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.tX);
        U.putDouble(address + 8L, self.tY);
        U.putDouble(address + 16L, self.tZ);
        U.putDouble(address + 24L, self.rX);
        U.putDouble(address + 32L, self.rY);
        U.putDouble(address + 40L, self.rZ);
        U.putDouble(address + 48L, self.rW);
        U.putDouble(address + 56L, self.sX);
        U.putDouble(address + 64L, self.sY);
        U.putDouble(address + 72L, self.sZ);
        return dest;
    }
    public DoubleTransform load(DoubleTransformImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.tX = U.getDouble(address);
        self.tY = U.getDouble(address + 8L);
        self.tZ = U.getDouble(address + 16L);
        self.rX = U.getDouble(address + 24L);
        self.rY = U.getDouble(address + 32L);
        self.rZ = U.getDouble(address + 40L);
        self.rW = U.getDouble(address + 48L);
        self.sX = U.getDouble(address + 56L);
        self.sY = U.getDouble(address + 64L);
        self.sZ = U.getDouble(address + 72L);
        return self;
    }
    public MemorySegment storeFloat(DoubleTransformImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.tX);
        U.putFloat(address + 4L, (float) self.tY);
        U.putFloat(address + 8L, (float) self.tZ);
        U.putFloat(address + 12L, (float) self.rX);
        U.putFloat(address + 16L, (float) self.rY);
        U.putFloat(address + 20L, (float) self.rZ);
        U.putFloat(address + 24L, (float) self.rW);
        U.putFloat(address + 28L, (float) self.sX);
        U.putFloat(address + 32L, (float) self.sY);
        U.putFloat(address + 36L, (float) self.sZ);
        return dest;
    }
    public DoubleTransform loadFloat(DoubleTransformImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
        long address = src.address() + offset;
        self.tX = U.getFloat(address);
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
}
