// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Float2x4SegOpsUnsafe implements Float2x4SegOps {

    private static final Float2x4SegOpsMS MS = new Float2x4SegOpsMS();
    private static final Float2x4RawOpsUnsafe RAW = new Float2x4RawOpsUnsafe();

    public MemorySegment storeCM(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x4 loadCM(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        return RAW.loadCMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCMDouble(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x4 loadCMDouble(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRM(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x4 loadRM(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        return RAW.loadRMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRMDouble(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x4 loadRMDouble(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCM(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x4 loadCM(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCMDouble(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x4 loadCMDouble(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRM(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x4 loadRM(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRMDouble(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x4 loadRMDouble(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset, stride);
    }
}
