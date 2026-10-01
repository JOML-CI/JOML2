// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatTriangleSegOpsUnsafe extends FloatTriangleSegOps {

    private static final FloatTriangleSegOpsMS MS = new FloatTriangleSegOpsMS();

    public MemorySegment store(FloatTriangle self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.v0X());
        U.putFloat(address + 4L, self.v0Y());
        U.putFloat(address + 8L, self.v0Z());
        U.putFloat(address + 12L, self.v1X());
        U.putFloat(address + 16L, self.v1Y());
        U.putFloat(address + 20L, self.v1Z());
        U.putFloat(address + 24L, self.v2X());
        U.putFloat(address + 28L, self.v2Y());
        U.putFloat(address + 32L, self.v2Z());
        return dest;
    }
    public FloatTriangle load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeDouble(FloatTriangle self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.v0X());
        U.putDouble(address + 8L, self.v0Y());
        U.putDouble(address + 16L, self.v0Z());
        U.putDouble(address + 24L, self.v1X());
        U.putDouble(address + 32L, self.v1Y());
        U.putDouble(address + 40L, self.v1Z());
        U.putDouble(address + 48L, self.v2X());
        U.putDouble(address + 56L, self.v2Y());
        U.putDouble(address + 64L, self.v2Z());
        return dest;
    }
    public FloatTriangle loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
