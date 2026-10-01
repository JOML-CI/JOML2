// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatSphereSegOpsUnsafe extends FloatSphereSegOps {

    private static final FloatSphereSegOpsMS MS = new FloatSphereSegOpsMS();

    public MemorySegment store(FloatSphereImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.x);
        U.putFloat(address + 4L, self.y);
        U.putFloat(address + 8L, self.z);
        U.putFloat(address + 12L, self.r);
        return dest;
    }
    public FloatSphere load(FloatSphereImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        self.r = U.getFloat(address + 12L);
        return self;
    }
    public MemorySegment storeDouble(FloatSphereImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        U.putDouble(address + 24L, self.r);
        return dest;
    }
    public FloatSphere loadDouble(FloatSphereImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        long address = src.address() + offset;
        self.x = (float) U.getDouble(address);
        self.y = (float) U.getDouble(address + 8L);
        self.z = (float) U.getDouble(address + 16L);
        self.r = (float) U.getDouble(address + 24L);
        return self;
    }
}
