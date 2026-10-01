// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRaySegOpsUnsafe extends DoubleRaySegOps {
    private static final DoubleRaySegOpsMS MS = new DoubleRaySegOpsMS();

    public MemorySegment store(DoubleRay self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.oX());
        _U.putDouble(address + 8L, self.oY());
        _U.putDouble(address + 16L, self.oZ());
        _U.putDouble(address + 24L, self.dX());
        _U.putDouble(address + 32L, self.dY());
        _U.putDouble(address + 40L, self.dZ());
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleRay self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.oX());
        _U.putDouble(bytes, address + 8L, self.oY());
        _U.putDouble(bytes, address + 16L, self.oZ());
        _U.putDouble(bytes, address + 24L, self.dX());
        _U.putDouble(bytes, address + 32L, self.dY());
        _U.putDouble(bytes, address + 40L, self.dZ());
        return dest;
    }

    public DoubleRay load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static DoubleRay loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeFloat(DoubleRay self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.oX());
        _U.putFloat(address + 4L, (float) self.oY());
        _U.putFloat(address + 8L, (float) self.oZ());
        _U.putFloat(address + 12L, (float) self.dX());
        _U.putFloat(address + 16L, (float) self.dY());
        _U.putFloat(address + 20L, (float) self.dZ());
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleRay self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.oX());
        _U.putFloat(bytes, address + 4L, (float) self.oY());
        _U.putFloat(bytes, address + 8L, (float) self.oZ());
        _U.putFloat(bytes, address + 12L, (float) self.dX());
        _U.putFloat(bytes, address + 16L, (float) self.dY());
        _U.putFloat(bytes, address + 20L, (float) self.dZ());
        return dest;
    }

    public DoubleRay loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static DoubleRay loadFloatOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
