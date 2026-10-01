// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Float2x2SegOpsUnsafe implements Float2x2SegOps {

    private static final Float2x2SegOpsMS MS = new Float2x2SegOpsMS();
    private static final Float2x2RawOpsUnsafe RAW = new Float2x2RawOpsUnsafe();

    public MemorySegment storeCM(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x2 loadCM(Float2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        return RAW.loadCMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCMDouble(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x2 loadCMDouble(Float2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRM(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x2 loadRM(Float2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        return RAW.loadRMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRMDouble(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float2x2 loadRMDouble(Float2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCM(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x2 loadCM(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCMDouble(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x2 loadCMDouble(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRM(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x2 loadRM(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRMDouble(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float2x2 loadRMDouble(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCM3x3(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM3x3(self, offset, dest);
        RAW.storeCM3x3Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeCM3x3Double(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM3x3Double(self, offset, dest);
        RAW.storeCM3x3DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM3x3(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM3x3(self, offset, dest);
        RAW.storeRM3x3Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM3x3Double(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM3x3Double(self, offset, dest);
        RAW.storeRM3x3DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeCM4x4(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        RAW.storeCM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeCM4x4Double(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4Double(self, offset, dest);
        RAW.storeCM4x4DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM4x4(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        RAW.storeRM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM4x4Double(Float2x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4Double(self, offset, dest);
        RAW.storeRM4x4DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
}
