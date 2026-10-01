// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatPlaneSegOpsUnsafe extends FloatPlaneSegOps {
    private static final FloatPlaneSegOpsMS MS = new FloatPlaneSegOpsMS();

    public MemorySegment store(FloatPlaneImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.a);
        U.putFloat(address + 4L, self.b);
        U.putFloat(address + 8L, self.c);
        U.putFloat(address + 12L, self.d);
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatPlaneImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, self.a);
        U.putFloat(bytes, address + 4L, self.b);
        U.putFloat(bytes, address + 8L, self.c);
        U.putFloat(bytes, address + 12L, self.d);
        return dest;
    }

    public FloatPlane load(FloatPlaneImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.a = U.getFloat(address);
        self.b = U.getFloat(address + 4L);
        self.c = U.getFloat(address + 8L);
        self.d = U.getFloat(address + 12L);
        return self;
    }

    private static FloatPlane loadOnHeap(FloatPlaneImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.a = U.getFloat(bytes, address);
        self.b = U.getFloat(bytes, address + 4L);
        self.c = U.getFloat(bytes, address + 8L);
        self.d = U.getFloat(bytes, address + 12L);
        return self;
    }

    public MemorySegment storeDouble(FloatPlaneImpl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.a);
        U.putDouble(address + 8L, self.b);
        U.putDouble(address + 16L, self.c);
        U.putDouble(address + 24L, self.d);
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(FloatPlaneImpl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.a);
        U.putDouble(bytes, address + 8L, self.b);
        U.putDouble(bytes, address + 16L, self.c);
        U.putDouble(bytes, address + 24L, self.d);
        return dest;
    }

    public FloatPlane loadDouble(FloatPlaneImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.a = (float) U.getDouble(address);
        self.b = (float) U.getDouble(address + 8L);
        self.c = (float) U.getDouble(address + 16L);
        self.d = (float) U.getDouble(address + 24L);
        return self;
    }

    private static FloatPlane loadDoubleOnHeap(FloatPlaneImpl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.a = (float) U.getDouble(bytes, address);
        self.b = (float) U.getDouble(bytes, address + 8L);
        self.c = (float) U.getDouble(bytes, address + 16L);
        self.d = (float) U.getDouble(bytes, address + 24L);
        return self;
    }
}
