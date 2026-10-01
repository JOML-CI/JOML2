// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3SegOpsUnsafe extends Float3SegOps {
    private static final Float3SegOpsMS MS = new Float3SegOpsMS();

    public MemorySegment store(Float3 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.x());
        U.putFloat(address + 4L, self.y());
        U.putFloat(address + 8L, self.z());
        return dest;
    }

    private static MemorySegment storeOnHeap(Float3 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, self.x());
        U.putFloat(bytes, address + 4L, self.y());
        U.putFloat(bytes, address + 8L, self.z());
        return dest;
    }

    public Float3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        return new Float3(_c0, _c1, _c2);
    }

    private static Float3 loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = U.getFloat(bytes, address);
        float _c1 = U.getFloat(bytes, address + 4L);
        float _c2 = U.getFloat(bytes, address + 8L);
        return new Float3(_c0, _c1, _c2);
    }

    public MemorySegment storeDouble(Float3 self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        U.putDouble(address + 16L, self.z());
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(Float3 self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.x());
        U.putDouble(bytes, address + 8L, self.y());
        U.putDouble(bytes, address + 16L, self.z());
        return dest;
    }

    public Float3 loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        return new Float3(_c0, _c1, _c2);
    }

    private static Float3 loadDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(offset, src);
        long address = HeapSegments.offset(src) + offset;
        float _c0 = (float) U.getDouble(bytes, address);
        float _c1 = (float) U.getDouble(bytes, address + 8L);
        float _c2 = (float) U.getDouble(bytes, address + 16L);
        return new Float3(_c0, _c1, _c2);
    }
}
