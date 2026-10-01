// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatOBBSegOpsUnsafe extends FloatOBBSegOps {
    private static final FloatOBBSegOpsMS MS = new FloatOBBSegOpsMS();
    private static final FloatOBBRawOpsUnsafe RAW = new FloatOBBRawOpsUnsafe();

    public MemorySegment store(FloatOBBImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.cX);
        _U.putFloat(address + 4L, self.cY);
        _U.putFloat(address + 8L, self.cZ);
        _U.putFloat(address + 12L, self.uXx);
        _U.putFloat(address + 16L, self.uXy);
        _U.putFloat(address + 20L, self.uXz);
        _U.putFloat(address + 24L, self.uYx);
        _U.putFloat(address + 28L, self.uYy);
        _U.putFloat(address + 32L, self.uYz);
        _U.putFloat(address + 36L, self.uZx);
        _U.putFloat(address + 40L, self.uZy);
        _U.putFloat(address + 44L, self.uZz);
        _U.putFloat(address + 48L, self.hsX);
        _U.putFloat(address + 52L, self.hsY);
        _U.putFloat(address + 56L, self.hsZ);
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatOBBImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.cX);
        _U.putFloat(bytes, address + 4L, self.cY);
        _U.putFloat(bytes, address + 8L, self.cZ);
        _U.putFloat(bytes, address + 12L, self.uXx);
        _U.putFloat(bytes, address + 16L, self.uXy);
        _U.putFloat(bytes, address + 20L, self.uXz);
        _U.putFloat(bytes, address + 24L, self.uYx);
        _U.putFloat(bytes, address + 28L, self.uYy);
        _U.putFloat(bytes, address + 32L, self.uYz);
        _U.putFloat(bytes, address + 36L, self.uZx);
        _U.putFloat(bytes, address + 40L, self.uZy);
        _U.putFloat(bytes, address + 44L, self.uZz);
        _U.putFloat(bytes, address + 48L, self.hsX);
        _U.putFloat(bytes, address + 52L, self.hsY);
        _U.putFloat(bytes, address + 56L, self.hsZ);
        return dest;
    }

    public FloatOBB load(FloatOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.cX = _U.getFloat(address);
        self.cY = _U.getFloat(address + 4L);
        self.cZ = _U.getFloat(address + 8L);
        self.uXx = _U.getFloat(address + 12L);
        self.uXy = _U.getFloat(address + 16L);
        self.uXz = _U.getFloat(address + 20L);
        self.uYx = _U.getFloat(address + 24L);
        self.uYy = _U.getFloat(address + 28L);
        self.uYz = _U.getFloat(address + 32L);
        self.uZx = _U.getFloat(address + 36L);
        self.uZy = _U.getFloat(address + 40L);
        self.uZz = _U.getFloat(address + 44L);
        self.hsX = _U.getFloat(address + 48L);
        self.hsY = _U.getFloat(address + 52L);
        self.hsZ = _U.getFloat(address + 56L);
        return self;
    }

    private static FloatOBB loadOnHeap(FloatOBBImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.cX = _U.getFloat(bytes, address);
        self.cY = _U.getFloat(bytes, address + 4L);
        self.cZ = _U.getFloat(bytes, address + 8L);
        self.uXx = _U.getFloat(bytes, address + 12L);
        self.uXy = _U.getFloat(bytes, address + 16L);
        self.uXz = _U.getFloat(bytes, address + 20L);
        self.uYx = _U.getFloat(bytes, address + 24L);
        self.uYy = _U.getFloat(bytes, address + 28L);
        self.uYz = _U.getFloat(bytes, address + 32L);
        self.uZx = _U.getFloat(bytes, address + 36L);
        self.uZy = _U.getFloat(bytes, address + 40L);
        self.uZz = _U.getFloat(bytes, address + 44L);
        self.hsX = _U.getFloat(bytes, address + 48L);
        self.hsY = _U.getFloat(bytes, address + 52L);
        self.hsZ = _U.getFloat(bytes, address + 56L);
        return self;
    }

    public MemorySegment storeDouble(FloatOBBImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.cX);
        _U.putDouble(address + 8L, self.cY);
        _U.putDouble(address + 16L, self.cZ);
        _U.putDouble(address + 24L, self.uXx);
        _U.putDouble(address + 32L, self.uXy);
        _U.putDouble(address + 40L, self.uXz);
        _U.putDouble(address + 48L, self.uYx);
        _U.putDouble(address + 56L, self.uYy);
        _U.putDouble(address + 64L, self.uYz);
        _U.putDouble(address + 72L, self.uZx);
        _U.putDouble(address + 80L, self.uZy);
        _U.putDouble(address + 88L, self.uZz);
        _U.putDouble(address + 96L, self.hsX);
        _U.putDouble(address + 104L, self.hsY);
        _U.putDouble(address + 112L, self.hsZ);
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(FloatOBBImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public FloatOBB loadDouble(FloatOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.cX = (float) _U.getDouble(address);
        self.cY = (float) _U.getDouble(address + 8L);
        self.cZ = (float) _U.getDouble(address + 16L);
        self.uXx = (float) _U.getDouble(address + 24L);
        self.uXy = (float) _U.getDouble(address + 32L);
        self.uXz = (float) _U.getDouble(address + 40L);
        self.uYx = (float) _U.getDouble(address + 48L);
        self.uYy = (float) _U.getDouble(address + 56L);
        self.uYz = (float) _U.getDouble(address + 64L);
        self.uZx = (float) _U.getDouble(address + 72L);
        self.uZy = (float) _U.getDouble(address + 80L);
        self.uZz = (float) _U.getDouble(address + 88L);
        self.hsX = (float) _U.getDouble(address + 96L);
        self.hsY = (float) _U.getDouble(address + 104L);
        self.hsZ = (float) _U.getDouble(address + 112L);
        return self;
    }

    private static FloatOBB loadDoubleOnHeap(FloatOBBImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(self, offset, src);
        return RAW.loadDoubleHeap(self, bytes, HeapSegments.offset(src) + offset);
    }
}
