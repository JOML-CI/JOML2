// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatTriangleSegOpsUnsafe extends FloatTriangleSegOps {
    private static final FloatTriangleSegOpsMS MS = new FloatTriangleSegOpsMS();

    public MemorySegment store(FloatTriangle self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.store(self, offset, dest);
        if (!dest.isNative()) return storeOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.v0X());
        _U.putFloat(address + 4L, self.v0Y());
        _U.putFloat(address + 8L, self.v0Z());
        _U.putFloat(address + 12L, self.v1X());
        _U.putFloat(address + 16L, self.v1Y());
        _U.putFloat(address + 20L, self.v1Z());
        _U.putFloat(address + 24L, self.v2X());
        _U.putFloat(address + 28L, self.v2Y());
        _U.putFloat(address + 32L, self.v2Z());
        return dest;
    }

    private static MemorySegment storeOnHeap(FloatTriangle self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.store(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.v0X());
        _U.putFloat(bytes, address + 4L, self.v0Y());
        _U.putFloat(bytes, address + 8L, self.v0Z());
        _U.putFloat(bytes, address + 12L, self.v1X());
        _U.putFloat(bytes, address + 16L, self.v1Y());
        _U.putFloat(bytes, address + 20L, self.v1Z());
        _U.putFloat(bytes, address + 24L, self.v2X());
        _U.putFloat(bytes, address + 28L, self.v2Y());
        _U.putFloat(bytes, address + 32L, self.v2Z());
        return dest;
    }

    public FloatTriangle load(long offset, MemorySegment src) {
        if (!src.isNative()) return loadOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c6 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        float _c8 = _U.getFloat(address + 32L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    private static FloatTriangle loadOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.load(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c6 = _U.getFloat(bytes, address + 24L);
        float _c7 = _U.getFloat(bytes, address + 28L);
        float _c8 = _U.getFloat(bytes, address + 32L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    public MemorySegment storeDouble(FloatTriangle self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.v0X());
        _U.putDouble(address + 8L, self.v0Y());
        _U.putDouble(address + 16L, self.v0Z());
        _U.putDouble(address + 24L, self.v1X());
        _U.putDouble(address + 32L, self.v1Y());
        _U.putDouble(address + 40L, self.v1Z());
        _U.putDouble(address + 48L, self.v2X());
        _U.putDouble(address + 56L, self.v2Y());
        _U.putDouble(address + 64L, self.v2Z());
        return dest;
    }

    private static MemorySegment storeDoubleOnHeap(FloatTriangle self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.v0X());
        _U.putDouble(bytes, address + 8L, self.v0Y());
        _U.putDouble(bytes, address + 16L, self.v0Z());
        _U.putDouble(bytes, address + 24L, self.v1X());
        _U.putDouble(bytes, address + 32L, self.v1Y());
        _U.putDouble(bytes, address + 40L, self.v1Z());
        _U.putDouble(bytes, address + 48L, self.v2X());
        _U.putDouble(bytes, address + 56L, self.v2Y());
        _U.putDouble(bytes, address + 64L, self.v2Z());
        return dest;
    }

    public FloatTriangle loadDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return loadDoubleOnHeap(offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c6 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        float _c8 = (float) _U.getDouble(address + 64L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    private static FloatTriangle loadDoubleOnHeap(long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadDouble(offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        float _c6 = (float) _U.getDouble(bytes, address + 48L);
        float _c7 = (float) _U.getDouble(bytes, address + 56L);
        float _c8 = (float) _U.getDouble(bytes, address + 64L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
