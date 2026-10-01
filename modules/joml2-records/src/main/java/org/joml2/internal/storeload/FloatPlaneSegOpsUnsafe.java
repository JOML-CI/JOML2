// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatPlaneSegOpsUnsafe extends FloatPlaneSegOps {
    private static final FloatPlaneSegOpsMS MS = new FloatPlaneSegOpsMS();

    public MemorySegment store(FloatPlane self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.a());
        U.putFloat(address + 4L, self.b());
        U.putFloat(address + 8L, self.c());
        U.putFloat(address + 12L, self.d());
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatPlane self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, self.a());
        U.putFloat(bytes, address + 4L, self.b());
        U.putFloat(bytes, address + 8L, self.c());
        U.putFloat(bytes, address + 12L, self.d());
        return dest;
    }

    public FloatPlane load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }

    private static FloatPlane loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = U.getFloat(bytes, address);
        float _c1 = U.getFloat(bytes, address + 4L);
        float _c2 = U.getFloat(bytes, address + 8L);
        float _c3 = U.getFloat(bytes, address + 12L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }

    public MemorySegment storeDouble(FloatPlane self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.a());
        U.putDouble(address + 8L, self.b());
        U.putDouble(address + 16L, self.c());
        U.putDouble(address + 24L, self.d());
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(FloatPlane self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.a());
        U.putDouble(bytes, address + 8L, self.b());
        U.putDouble(bytes, address + 16L, self.c());
        U.putDouble(bytes, address + 24L, self.d());
        return dest;
    }

    public FloatPlane loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }

    private static FloatPlane loadDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = (float) U.getDouble(bytes, address);
        float _c1 = (float) U.getDouble(bytes, address + 8L);
        float _c2 = (float) U.getDouble(bytes, address + 16L);
        float _c3 = (float) U.getDouble(bytes, address + 24L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
}
