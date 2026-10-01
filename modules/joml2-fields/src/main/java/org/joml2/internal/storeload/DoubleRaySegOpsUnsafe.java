// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRaySegOpsUnsafe extends DoubleRaySegOps {
    private static final DoubleRaySegOpsMS MS = new DoubleRaySegOpsMS();

    public MemorySegment store(DoubleRayImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.oX);
        _U.putDouble(address + 8L, self.oY);
        _U.putDouble(address + 16L, self.oZ);
        _U.putDouble(address + 24L, self.dX);
        _U.putDouble(address + 32L, self.dY);
        _U.putDouble(address + 40L, self.dZ);
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleRayImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.oX);
        _U.putDouble(bytes, address + 8L, self.oY);
        _U.putDouble(bytes, address + 16L, self.oZ);
        _U.putDouble(bytes, address + 24L, self.dX);
        _U.putDouble(bytes, address + 32L, self.dY);
        _U.putDouble(bytes, address + 40L, self.dZ);
        return dest;
    }

    public DoubleRay load(DoubleRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = _U.getDouble(address);
        self.oY = _U.getDouble(address + 8L);
        self.oZ = _U.getDouble(address + 16L);
        self.dX = _U.getDouble(address + 24L);
        self.dY = _U.getDouble(address + 32L);
        self.dZ = _U.getDouble(address + 40L);
        return self;
    }

    private static DoubleRay loadOnHeap(DoubleRayImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = _U.getDouble(bytes, address);
        self.oY = _U.getDouble(bytes, address + 8L);
        self.oZ = _U.getDouble(bytes, address + 16L);
        self.dX = _U.getDouble(bytes, address + 24L);
        self.dY = _U.getDouble(bytes, address + 32L);
        self.dZ = _U.getDouble(bytes, address + 40L);
        return self;
    }

    public MemorySegment storeFloat(DoubleRayImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.oX);
        _U.putFloat(address + 4L, (float) self.oY);
        _U.putFloat(address + 8L, (float) self.oZ);
        _U.putFloat(address + 12L, (float) self.dX);
        _U.putFloat(address + 16L, (float) self.dY);
        _U.putFloat(address + 20L, (float) self.dZ);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleRayImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.oX);
        _U.putFloat(bytes, address + 4L, (float) self.oY);
        _U.putFloat(bytes, address + 8L, (float) self.oZ);
        _U.putFloat(bytes, address + 12L, (float) self.dX);
        _U.putFloat(bytes, address + 16L, (float) self.dY);
        _U.putFloat(bytes, address + 20L, (float) self.dZ);
        return dest;
    }

    public DoubleRay loadFloat(DoubleRayImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = _U.getFloat(address);
        self.oY = _U.getFloat(address + 4L);
        self.oZ = _U.getFloat(address + 8L);
        self.dX = _U.getFloat(address + 12L);
        self.dY = _U.getFloat(address + 16L);
        self.dZ = _U.getFloat(address + 20L);
        return self;
    }

    private static DoubleRay loadFloatOnHeap(DoubleRayImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = _U.getFloat(bytes, address);
        self.oY = _U.getFloat(bytes, address + 4L);
        self.oZ = _U.getFloat(bytes, address + 8L);
        self.dX = _U.getFloat(bytes, address + 12L);
        self.dY = _U.getFloat(bytes, address + 16L);
        self.dZ = _U.getFloat(bytes, address + 20L);
        return self;
    }
}
