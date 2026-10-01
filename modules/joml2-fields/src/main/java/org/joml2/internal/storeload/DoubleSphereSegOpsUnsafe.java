// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleSphereSegOpsUnsafe extends DoubleSphereSegOps {

    private static final DoubleSphereSegOpsMS MS = new DoubleSphereSegOpsMS();

    public MemorySegment store(DoubleSphereImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        U.putDouble(address + 24L, self.r);
        return dest;
    }
    public DoubleSphere load(DoubleSphereImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getDouble(address);
        self.y = U.getDouble(address + 8L);
        self.z = U.getDouble(address + 16L);
        self.r = U.getDouble(address + 24L);
        return self;
    }
    public MemorySegment storeFloat(DoubleSphereImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        U.putFloat(address + 8L, (float) self.z);
        U.putFloat(address + 12L, (float) self.r);
        return dest;
    }
    public DoubleSphere loadFloat(DoubleSphereImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        self.r = U.getFloat(address + 12L);
        return self;
    }
}
