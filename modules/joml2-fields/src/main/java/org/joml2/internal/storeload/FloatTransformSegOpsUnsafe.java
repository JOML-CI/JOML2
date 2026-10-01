// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatTransformSegOpsUnsafe extends FloatTransformSegOps {
    private static final FloatTransformSegOpsMS MS = new FloatTransformSegOpsMS();

    public MemorySegment store(FloatTransformImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.tX);
        _U.putFloat(address + 4L, self.tY);
        _U.putFloat(address + 8L, self.tZ);
        _U.putFloat(address + 12L, self.rX);
        _U.putFloat(address + 16L, self.rY);
        _U.putFloat(address + 20L, self.rZ);
        _U.putFloat(address + 24L, self.rW);
        _U.putFloat(address + 28L, self.sX);
        _U.putFloat(address + 32L, self.sY);
        _U.putFloat(address + 36L, self.sZ);
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatTransformImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.tX);
        _U.putFloat(bytes, address + 4L, self.tY);
        _U.putFloat(bytes, address + 8L, self.tZ);
        _U.putFloat(bytes, address + 12L, self.rX);
        _U.putFloat(bytes, address + 16L, self.rY);
        _U.putFloat(bytes, address + 20L, self.rZ);
        _U.putFloat(bytes, address + 24L, self.rW);
        _U.putFloat(bytes, address + 28L, self.sX);
        _U.putFloat(bytes, address + 32L, self.sY);
        _U.putFloat(bytes, address + 36L, self.sZ);
        return dest;
    }

    public FloatTransform load(FloatTransformImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getFloat(address);
        self.tY = _U.getFloat(address + 4L);
        self.tZ = _U.getFloat(address + 8L);
        self.rX = _U.getFloat(address + 12L);
        self.rY = _U.getFloat(address + 16L);
        self.rZ = _U.getFloat(address + 20L);
        self.rW = _U.getFloat(address + 24L);
        self.sX = _U.getFloat(address + 28L);
        self.sY = _U.getFloat(address + 32L);
        self.sZ = _U.getFloat(address + 36L);
        return self;
    }

    private static FloatTransform loadOnHeap(FloatTransformImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = _U.getFloat(bytes, address);
        self.tY = _U.getFloat(bytes, address + 4L);
        self.tZ = _U.getFloat(bytes, address + 8L);
        self.rX = _U.getFloat(bytes, address + 12L);
        self.rY = _U.getFloat(bytes, address + 16L);
        self.rZ = _U.getFloat(bytes, address + 20L);
        self.rW = _U.getFloat(bytes, address + 24L);
        self.sX = _U.getFloat(bytes, address + 28L);
        self.sY = _U.getFloat(bytes, address + 32L);
        self.sZ = _U.getFloat(bytes, address + 36L);
        return self;
    }

    public MemorySegment storeDouble(FloatTransformImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.tX);
        _U.putDouble(address + 8L, self.tY);
        _U.putDouble(address + 16L, self.tZ);
        _U.putDouble(address + 24L, self.rX);
        _U.putDouble(address + 32L, self.rY);
        _U.putDouble(address + 40L, self.rZ);
        _U.putDouble(address + 48L, self.rW);
        _U.putDouble(address + 56L, self.sX);
        _U.putDouble(address + 64L, self.sY);
        _U.putDouble(address + 72L, self.sZ);
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(FloatTransformImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.tX);
        _U.putDouble(bytes, address + 8L, self.tY);
        _U.putDouble(bytes, address + 16L, self.tZ);
        _U.putDouble(bytes, address + 24L, self.rX);
        _U.putDouble(bytes, address + 32L, self.rY);
        _U.putDouble(bytes, address + 40L, self.rZ);
        _U.putDouble(bytes, address + 48L, self.rW);
        _U.putDouble(bytes, address + 56L, self.sX);
        _U.putDouble(bytes, address + 64L, self.sY);
        _U.putDouble(bytes, address + 72L, self.sZ);
        return dest;
    }

    public FloatTransform loadDouble(FloatTransformImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = (float) _U.getDouble(address);
        self.tY = (float) _U.getDouble(address + 8L);
        self.tZ = (float) _U.getDouble(address + 16L);
        self.rX = (float) _U.getDouble(address + 24L);
        self.rY = (float) _U.getDouble(address + 32L);
        self.rZ = (float) _U.getDouble(address + 40L);
        self.rW = (float) _U.getDouble(address + 48L);
        self.sX = (float) _U.getDouble(address + 56L);
        self.sY = (float) _U.getDouble(address + 64L);
        self.sZ = (float) _U.getDouble(address + 72L);
        return self;
    }

    private static FloatTransform loadDoubleOnHeap(FloatTransformImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.tX = (float) _U.getDouble(bytes, address);
        self.tY = (float) _U.getDouble(bytes, address + 8L);
        self.tZ = (float) _U.getDouble(bytes, address + 16L);
        self.rX = (float) _U.getDouble(bytes, address + 24L);
        self.rY = (float) _U.getDouble(bytes, address + 32L);
        self.rZ = (float) _U.getDouble(bytes, address + 40L);
        self.rW = (float) _U.getDouble(bytes, address + 48L);
        self.sX = (float) _U.getDouble(bytes, address + 56L);
        self.sY = (float) _U.getDouble(bytes, address + 64L);
        self.sZ = (float) _U.getDouble(bytes, address + 72L);
        return self;
    }
}
