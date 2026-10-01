// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleDualQuatSegOpsUnsafe extends DoubleDualQuatSegOps {
    private static final DoubleDualQuatSegOpsMS MS = new DoubleDualQuatSegOpsMS();

    public MemorySegment store(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.rX);
        _U.putDouble(address + 8L, self.rY);
        _U.putDouble(address + 16L, self.rZ);
        _U.putDouble(address + 24L, self.rW);
        _U.putDouble(address + 32L, self.dX);
        _U.putDouble(address + 40L, self.dY);
        _U.putDouble(address + 48L, self.dZ);
        _U.putDouble(address + 56L, self.dW);
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.rX);
        _U.putDouble(bytes, address + 8L, self.rY);
        _U.putDouble(bytes, address + 16L, self.rZ);
        _U.putDouble(bytes, address + 24L, self.rW);
        _U.putDouble(bytes, address + 32L, self.dX);
        _U.putDouble(bytes, address + 40L, self.dY);
        _U.putDouble(bytes, address + 48L, self.dZ);
        _U.putDouble(bytes, address + 56L, self.dW);
        return dest;
    }

    public DoubleDualQuat load(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getDouble(address);
        self.rY = _U.getDouble(address + 8L);
        self.rZ = _U.getDouble(address + 16L);
        self.rW = _U.getDouble(address + 24L);
        self.dX = _U.getDouble(address + 32L);
        self.dY = _U.getDouble(address + 40L);
        self.dZ = _U.getDouble(address + 48L);
        self.dW = _U.getDouble(address + 56L);
        return self;
    }

    private static DoubleDualQuat loadOnHeap(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getDouble(bytes, address);
        self.rY = _U.getDouble(bytes, address + 8L);
        self.rZ = _U.getDouble(bytes, address + 16L);
        self.rW = _U.getDouble(bytes, address + 24L);
        self.dX = _U.getDouble(bytes, address + 32L);
        self.dY = _U.getDouble(bytes, address + 40L);
        self.dZ = _U.getDouble(bytes, address + 48L);
        self.dW = _U.getDouble(bytes, address + 56L);
        return self;
    }

    public MemorySegment storeFloat(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.rX);
        _U.putFloat(address + 4L, (float) self.rY);
        _U.putFloat(address + 8L, (float) self.rZ);
        _U.putFloat(address + 12L, (float) self.rW);
        _U.putFloat(address + 16L, (float) self.dX);
        _U.putFloat(address + 20L, (float) self.dY);
        _U.putFloat(address + 24L, (float) self.dZ);
        _U.putFloat(address + 28L, (float) self.dW);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.rX);
        _U.putFloat(bytes, address + 4L, (float) self.rY);
        _U.putFloat(bytes, address + 8L, (float) self.rZ);
        _U.putFloat(bytes, address + 12L, (float) self.rW);
        _U.putFloat(bytes, address + 16L, (float) self.dX);
        _U.putFloat(bytes, address + 20L, (float) self.dY);
        _U.putFloat(bytes, address + 24L, (float) self.dZ);
        _U.putFloat(bytes, address + 28L, (float) self.dW);
        return dest;
    }

    public DoubleDualQuat loadFloat(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getFloat(address);
        self.rY = _U.getFloat(address + 4L);
        self.rZ = _U.getFloat(address + 8L);
        self.rW = _U.getFloat(address + 12L);
        self.dX = _U.getFloat(address + 16L);
        self.dY = _U.getFloat(address + 20L);
        self.dZ = _U.getFloat(address + 24L);
        self.dW = _U.getFloat(address + 28L);
        return self;
    }

    private static DoubleDualQuat loadFloatOnHeap(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getFloat(bytes, address);
        self.rY = _U.getFloat(bytes, address + 4L);
        self.rZ = _U.getFloat(bytes, address + 8L);
        self.rW = _U.getFloat(bytes, address + 12L);
        self.dX = _U.getFloat(bytes, address + 16L);
        self.dY = _U.getFloat(bytes, address + 20L);
        self.dZ = _U.getFloat(bytes, address + 24L);
        self.dW = _U.getFloat(bytes, address + 28L);
        return self;
    }
}
