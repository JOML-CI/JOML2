// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleOBBSegOpsUnsafe extends DoubleOBBSegOps {
    private static final DoubleOBBSegOpsMS MS = new DoubleOBBSegOpsMS();
    private static final DoubleOBBRawOpsUnsafe RAW = new DoubleOBBRawOpsUnsafe();

    public MemorySegment store(DoubleOBBImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleOBBImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        RAW.storeHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public DoubleOBB load(DoubleOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8L);
        self.data[2] = _U.getDouble(address + 16L);
        self.data[3] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        self.data[6] = _U.getDouble(address + 48L);
        self.data[7] = _U.getDouble(address + 56L);
        self.data[8] = _U.getDouble(address + 64L);
        self.data[9] = _U.getDouble(address + 72L);
        self.data[10] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.data[12] = _U.getDouble(address + 96L);
        self.data[13] = _U.getDouble(address + 104L);
        self.data[14] = _U.getDouble(address + 112L);
        return self;
    }

    private static DoubleOBB loadOnHeap(DoubleOBBImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        return RAW.loadHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeFloat(DoubleOBBImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleOBBImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public DoubleOBB loadFloat(DoubleOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(self, offset, src);
        return RAW.loadFloatUnsafe(self, src.address() + offset);
    }

    private static DoubleOBB loadFloatOnHeap(DoubleOBBImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadFloat(self, offset, src);
        return RAW.loadFloatHeap(self, bytes, HeapSegments.offset(src) + offset);
    }
}
