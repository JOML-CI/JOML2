// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRigidSegOpsUnsafe extends DoubleRigidSegOps {
    private static final DoubleRigidSegOpsMS MS = new DoubleRigidSegOpsMS();

    public MemorySegment store(DoubleRigidImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.tX);
        _U.putDouble(address + 8L, self.tY);
        _U.putDouble(address + 16L, self.tZ);
        _U.putDouble(address + 24L, self.rX);
        _U.putDouble(address + 32L, self.rY);
        _U.putDouble(address + 40L, self.rZ);
        _U.putDouble(address + 48L, self.rW);
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleRigidImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.tX);
        _U.putDouble(bytes, address + 8L, self.tY);
        _U.putDouble(bytes, address + 16L, self.tZ);
        _U.putDouble(bytes, address + 24L, self.rX);
        _U.putDouble(bytes, address + 32L, self.rY);
        _U.putDouble(bytes, address + 40L, self.rZ);
        _U.putDouble(bytes, address + 48L, self.rW);
        return dest;
    }

    public DoubleRigid load(DoubleRigidImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getDouble(address);
        self.tY = _U.getDouble(address + 8L);
        self.tZ = _U.getDouble(address + 16L);
        self.rX = _U.getDouble(address + 24L);
        self.rY = _U.getDouble(address + 32L);
        self.rZ = _U.getDouble(address + 40L);
        self.rW = _U.getDouble(address + 48L);
        return self;
    }

    private static DoubleRigid loadOnHeap(DoubleRigidImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getDouble(bytes, address);
        self.tY = _U.getDouble(bytes, address + 8L);
        self.tZ = _U.getDouble(bytes, address + 16L);
        self.rX = _U.getDouble(bytes, address + 24L);
        self.rY = _U.getDouble(bytes, address + 32L);
        self.rZ = _U.getDouble(bytes, address + 40L);
        self.rW = _U.getDouble(bytes, address + 48L);
        return self;
    }

    public MemorySegment storeFloat(DoubleRigidImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.tX);
        _U.putFloat(address + 4L, (float) self.tY);
        _U.putFloat(address + 8L, (float) self.tZ);
        _U.putFloat(address + 12L, (float) self.rX);
        _U.putFloat(address + 16L, (float) self.rY);
        _U.putFloat(address + 20L, (float) self.rZ);
        _U.putFloat(address + 24L, (float) self.rW);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleRigidImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.tX);
        _U.putFloat(bytes, address + 4L, (float) self.tY);
        _U.putFloat(bytes, address + 8L, (float) self.tZ);
        _U.putFloat(bytes, address + 12L, (float) self.rX);
        _U.putFloat(bytes, address + 16L, (float) self.rY);
        _U.putFloat(bytes, address + 20L, (float) self.rZ);
        _U.putFloat(bytes, address + 24L, (float) self.rW);
        return dest;
    }

    public DoubleRigid loadFloat(DoubleRigidImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getFloat(address);
        self.tY = _U.getFloat(address + 4L);
        self.tZ = _U.getFloat(address + 8L);
        self.rX = _U.getFloat(address + 12L);
        self.rY = _U.getFloat(address + 16L);
        self.rZ = _U.getFloat(address + 20L);
        self.rW = _U.getFloat(address + 24L);
        return self;
    }

    private static DoubleRigid loadFloatOnHeap(DoubleRigidImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getFloat(bytes, address);
        self.tY = _U.getFloat(bytes, address + 4L);
        self.tZ = _U.getFloat(bytes, address + 8L);
        self.rX = _U.getFloat(bytes, address + 12L);
        self.rY = _U.getFloat(bytes, address + 16L);
        self.rZ = _U.getFloat(bytes, address + 20L);
        self.rW = _U.getFloat(bytes, address + 24L);
        return self;
    }
}
