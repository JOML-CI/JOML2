// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRaySegOpsUnsafe extends FloatRaySegOps {
    private static final FloatRaySegOpsMS MS = new FloatRaySegOpsMS();

    public MemorySegment store(FloatRay self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.oX());
        _U.putFloat(address + 4L, self.oY());
        _U.putFloat(address + 8L, self.oZ());
        _U.putFloat(address + 12L, self.dX());
        _U.putFloat(address + 16L, self.dY());
        _U.putFloat(address + 20L, self.dZ());
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatRay self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.oX());
        _U.putFloat(bytes, address + 4L, self.oY());
        _U.putFloat(bytes, address + 8L, self.oZ());
        _U.putFloat(bytes, address + 12L, self.dX());
        _U.putFloat(bytes, address + 16L, self.dY());
        _U.putFloat(bytes, address + 20L, self.dZ());
        return dest;
    }

    public FloatRay load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static FloatRay loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeDouble(FloatRay self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
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

    private static MemorySegment storeDoubleOnHeap(FloatRay self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
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

    public FloatRay loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static FloatRay loadDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
