// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4SegOpsUnsafe extends Float4x4SegOps {

    private static final Float4x4SegOpsMS MS = new Float4x4SegOpsMS();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public MemorySegment storeCM(Float4x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c4 = U.getFloat(address + 4L);
        float _c8 = U.getFloat(address + 8L);
        float _c12 = U.getFloat(address + 12L);
        float _c1 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c9 = U.getFloat(address + 24L);
        float _c13 = U.getFloat(address + 28L);
        float _c2 = U.getFloat(address + 32L);
        float _c6 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c14 = U.getFloat(address + 44L);
        float _c3 = U.getFloat(address + 48L);
        float _c7 = U.getFloat(address + 52L);
        float _c11 = U.getFloat(address + 56L);
        float _c15 = U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public MemorySegment storeCMDouble(Float4x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadCMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(offset, src);
        return RAW.loadCMDoubleUnsafe(src.address() + offset);
    }
    public MemorySegment storeRM(Float4x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadRM(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        float _c9 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c11 = U.getFloat(address + 44L);
        float _c12 = U.getFloat(address + 48L);
        float _c13 = U.getFloat(address + 52L);
        float _c14 = U.getFloat(address + 56L);
        float _c15 = U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public MemorySegment storeRMDouble(Float4x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadRMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(offset, src);
        return RAW.loadRMDoubleUnsafe(src.address() + offset);
    }
    public MemorySegment storeCM(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadCM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(offset, src, stride);
        return RAW.loadCMUnsafe(src.address() + offset, stride);
    }
    public MemorySegment storeCMDouble(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadCMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(offset, src, stride);
        return RAW.loadCMDoubleUnsafe(src.address() + offset, stride);
    }
    public MemorySegment storeRM(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(offset, src, stride);
        return RAW.loadRMUnsafe(src.address() + offset, stride);
    }
    public MemorySegment storeRMDouble(Float4x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadRMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(offset, src, stride);
        return RAW.loadRMDoubleUnsafe(src.address() + offset, stride);
    }
}
