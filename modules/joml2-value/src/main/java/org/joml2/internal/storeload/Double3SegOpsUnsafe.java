// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3SegOpsUnsafe extends Double3SegOps {

    private static final Double3SegOpsMS MS = new Double3SegOpsMS();

    public MemorySegment store(Double3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        return dest;
    }
    public Double3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        return new Double3(_c0, _c1, _c2);
    }
    public MemorySegment storeFloat(Double3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.x());
        U.putFloat(address + 4L, (float) self.y());
        U.putFloat(address + 8L, (float) self.z());
        return dest;
    }
    public Double3 loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(offset, src);
        long address = src.address() + offset;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        return new Double3(_c0, _c1, _c2);
    }
}
