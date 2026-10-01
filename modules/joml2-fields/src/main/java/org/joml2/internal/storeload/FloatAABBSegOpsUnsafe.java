// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatAABBSegOpsUnsafe extends FloatAABBSegOps {
    private static final FloatAABBSegOpsMS MS = new FloatAABBSegOpsMS();

    public MemorySegment store(FloatAABBImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.minX);
        _U.putFloat(address + 4L, self.minY);
        _U.putFloat(address + 8L, self.minZ);
        _U.putFloat(address + 12L, self.maxX);
        _U.putFloat(address + 16L, self.maxY);
        _U.putFloat(address + 20L, self.maxZ);
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatAABBImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.minX);
        _U.putFloat(bytes, address + 4L, self.minY);
        _U.putFloat(bytes, address + 8L, self.minZ);
        _U.putFloat(bytes, address + 12L, self.maxX);
        _U.putFloat(bytes, address + 16L, self.maxY);
        _U.putFloat(bytes, address + 20L, self.maxZ);
        return dest;
    }

    public FloatAABB load(FloatAABBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getFloat(address);
        self.minY = _U.getFloat(address + 4L);
        self.minZ = _U.getFloat(address + 8L);
        self.maxX = _U.getFloat(address + 12L);
        self.maxY = _U.getFloat(address + 16L);
        self.maxZ = _U.getFloat(address + 20L);
        return self;
    }

    private static FloatAABB loadOnHeap(FloatAABBImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getFloat(bytes, address);
        self.minY = _U.getFloat(bytes, address + 4L);
        self.minZ = _U.getFloat(bytes, address + 8L);
        self.maxX = _U.getFloat(bytes, address + 12L);
        self.maxY = _U.getFloat(bytes, address + 16L);
        self.maxZ = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public MemorySegment storeDouble(FloatAABBImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.minX);
        _U.putDouble(address + 8L, self.minY);
        _U.putDouble(address + 16L, self.minZ);
        _U.putDouble(address + 24L, self.maxX);
        _U.putDouble(address + 32L, self.maxY);
        _U.putDouble(address + 40L, self.maxZ);
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(FloatAABBImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.minX);
        _U.putDouble(bytes, address + 8L, self.minY);
        _U.putDouble(bytes, address + 16L, self.minZ);
        _U.putDouble(bytes, address + 24L, self.maxX);
        _U.putDouble(bytes, address + 32L, self.maxY);
        _U.putDouble(bytes, address + 40L, self.maxZ);
        return dest;
    }

    public FloatAABB loadDouble(FloatAABBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = (float) _U.getDouble(address);
        self.minY = (float) _U.getDouble(address + 8L);
        self.minZ = (float) _U.getDouble(address + 16L);
        self.maxX = (float) _U.getDouble(address + 24L);
        self.maxY = (float) _U.getDouble(address + 32L);
        self.maxZ = (float) _U.getDouble(address + 40L);
        return self;
    }

    private static FloatAABB loadDoubleOnHeap(FloatAABBImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = (float) _U.getDouble(bytes, address);
        self.minY = (float) _U.getDouble(bytes, address + 8L);
        self.minZ = (float) _U.getDouble(bytes, address + 16L);
        self.maxX = (float) _U.getDouble(bytes, address + 24L);
        self.maxY = (float) _U.getDouble(bytes, address + 32L);
        self.maxZ = (float) _U.getDouble(bytes, address + 40L);
        return self;
    }
}
