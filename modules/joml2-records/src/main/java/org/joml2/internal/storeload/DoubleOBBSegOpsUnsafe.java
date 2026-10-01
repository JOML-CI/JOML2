// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleOBBSegOpsUnsafe extends DoubleOBBSegOps {
    private static final DoubleOBBSegOpsMS MS = new DoubleOBBSegOpsMS();
    private static final DoubleOBBRawOpsUnsafe RAW = new DoubleOBBRawOpsUnsafe();

    public MemorySegment store(DoubleOBB self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeOnHeap(DoubleOBB self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        RAW.storeHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public DoubleOBB load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        double _c8 = _U.getDouble(address + 64L);
        double _c9 = _U.getDouble(address + 72L);
        double _c10 = _U.getDouble(address + 80L);
        double _c11 = _U.getDouble(address + 88L);
        double _c12 = _U.getDouble(address + 96L);
        double _c13 = _U.getDouble(address + 104L);
        double _c14 = _U.getDouble(address + 112L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    private static DoubleOBB loadOnHeap(long offset, MemorySegment src) {
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
        double _c6 = _U.getDouble(bytes, address + 48L);
        double _c7 = _U.getDouble(bytes, address + 56L);
        double _c8 = _U.getDouble(bytes, address + 64L);
        double _c9 = _U.getDouble(bytes, address + 72L);
        double _c10 = _U.getDouble(bytes, address + 80L);
        double _c11 = _U.getDouble(bytes, address + 88L);
        double _c12 = _U.getDouble(bytes, address + 96L);
        double _c13 = _U.getDouble(bytes, address + 104L);
        double _c14 = _U.getDouble(bytes, address + 112L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    public MemorySegment storeFloat(DoubleOBB self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeFloatOnHeap(DoubleOBB self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public DoubleOBB loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return loadFloatOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        double _c8 = _U.getFloat(address + 32L);
        double _c9 = _U.getFloat(address + 36L);
        double _c10 = _U.getFloat(address + 40L);
        double _c11 = _U.getFloat(address + 44L);
        double _c12 = _U.getFloat(address + 48L);
        double _c13 = _U.getFloat(address + 52L);
        double _c14 = _U.getFloat(address + 56L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    private static DoubleOBB loadFloatOnHeap(long offset, MemorySegment src) {
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
        double _c6 = _U.getFloat(bytes, address + 24L);
        double _c7 = _U.getFloat(bytes, address + 28L);
        double _c8 = _U.getFloat(bytes, address + 32L);
        double _c9 = _U.getFloat(bytes, address + 36L);
        double _c10 = _U.getFloat(bytes, address + 40L);
        double _c11 = _U.getFloat(bytes, address + 44L);
        double _c12 = _U.getFloat(bytes, address + 48L);
        double _c13 = _U.getFloat(bytes, address + 52L);
        double _c14 = _U.getFloat(bytes, address + 56L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
