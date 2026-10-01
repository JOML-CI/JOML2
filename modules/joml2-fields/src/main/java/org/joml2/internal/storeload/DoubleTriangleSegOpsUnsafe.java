// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleTriangleSegOpsUnsafe extends DoubleTriangleSegOps {
    private static final DoubleTriangleSegOpsMS MS = new DoubleTriangleSegOpsMS();

    public MemorySegment store(DoubleTriangleImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.v0X);
        _U.putDouble(address + 8L, self.v0Y);
        _U.putDouble(address + 16L, self.v0Z);
        _U.putDouble(address + 24L, self.v1X);
        _U.putDouble(address + 32L, self.v1Y);
        _U.putDouble(address + 40L, self.v1Z);
        _U.putDouble(address + 48L, self.v2X);
        _U.putDouble(address + 56L, self.v2Y);
        _U.putDouble(address + 64L, self.v2Z);
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleTriangleImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.v0X);
        _U.putDouble(bytes, address + 8L, self.v0Y);
        _U.putDouble(bytes, address + 16L, self.v0Z);
        _U.putDouble(bytes, address + 24L, self.v1X);
        _U.putDouble(bytes, address + 32L, self.v1Y);
        _U.putDouble(bytes, address + 40L, self.v1Z);
        _U.putDouble(bytes, address + 48L, self.v2X);
        _U.putDouble(bytes, address + 56L, self.v2Y);
        _U.putDouble(bytes, address + 64L, self.v2Z);
        return dest;
    }

    public DoubleTriangle load(DoubleTriangleImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = _U.getDouble(address);
        self.v0Y = _U.getDouble(address + 8L);
        self.v0Z = _U.getDouble(address + 16L);
        self.v1X = _U.getDouble(address + 24L);
        self.v1Y = _U.getDouble(address + 32L);
        self.v1Z = _U.getDouble(address + 40L);
        self.v2X = _U.getDouble(address + 48L);
        self.v2Y = _U.getDouble(address + 56L);
        self.v2Z = _U.getDouble(address + 64L);
        return self;
    }

    private static DoubleTriangle loadOnHeap(DoubleTriangleImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = _U.getDouble(bytes, address);
        self.v0Y = _U.getDouble(bytes, address + 8L);
        self.v0Z = _U.getDouble(bytes, address + 16L);
        self.v1X = _U.getDouble(bytes, address + 24L);
        self.v1Y = _U.getDouble(bytes, address + 32L);
        self.v1Z = _U.getDouble(bytes, address + 40L);
        self.v2X = _U.getDouble(bytes, address + 48L);
        self.v2Y = _U.getDouble(bytes, address + 56L);
        self.v2Z = _U.getDouble(bytes, address + 64L);
        return self;
    }

    public MemorySegment storeFloat(DoubleTriangleImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.v0X);
        _U.putFloat(address + 4L, (float) self.v0Y);
        _U.putFloat(address + 8L, (float) self.v0Z);
        _U.putFloat(address + 12L, (float) self.v1X);
        _U.putFloat(address + 16L, (float) self.v1Y);
        _U.putFloat(address + 20L, (float) self.v1Z);
        _U.putFloat(address + 24L, (float) self.v2X);
        _U.putFloat(address + 28L, (float) self.v2Y);
        _U.putFloat(address + 32L, (float) self.v2Z);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleTriangleImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.v0X);
        _U.putFloat(bytes, address + 4L, (float) self.v0Y);
        _U.putFloat(bytes, address + 8L, (float) self.v0Z);
        _U.putFloat(bytes, address + 12L, (float) self.v1X);
        _U.putFloat(bytes, address + 16L, (float) self.v1Y);
        _U.putFloat(bytes, address + 20L, (float) self.v1Z);
        _U.putFloat(bytes, address + 24L, (float) self.v2X);
        _U.putFloat(bytes, address + 28L, (float) self.v2Y);
        _U.putFloat(bytes, address + 32L, (float) self.v2Z);
        return dest;
    }

    public DoubleTriangle loadFloat(DoubleTriangleImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = _U.getFloat(address);
        self.v0Y = _U.getFloat(address + 4L);
        self.v0Z = _U.getFloat(address + 8L);
        self.v1X = _U.getFloat(address + 12L);
        self.v1Y = _U.getFloat(address + 16L);
        self.v1Z = _U.getFloat(address + 20L);
        self.v2X = _U.getFloat(address + 24L);
        self.v2Y = _U.getFloat(address + 28L);
        self.v2Z = _U.getFloat(address + 32L);
        return self;
    }

    private static DoubleTriangle loadFloatOnHeap(DoubleTriangleImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.v0X = _U.getFloat(bytes, address);
        self.v0Y = _U.getFloat(bytes, address + 4L);
        self.v0Z = _U.getFloat(bytes, address + 8L);
        self.v1X = _U.getFloat(bytes, address + 12L);
        self.v1Y = _U.getFloat(bytes, address + 16L);
        self.v1Z = _U.getFloat(bytes, address + 20L);
        self.v2X = _U.getFloat(bytes, address + 24L);
        self.v2Y = _U.getFloat(bytes, address + 28L);
        self.v2Z = _U.getFloat(bytes, address + 32L);
        return self;
    }
}
