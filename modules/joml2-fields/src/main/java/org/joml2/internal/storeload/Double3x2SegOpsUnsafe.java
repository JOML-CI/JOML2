// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Double3x2SegOpsUnsafe implements Double3x2SegOps {

    private static final Double3x2SegOpsMS MS = new Double3x2SegOpsMS();
    private static final Double3x2RawOpsUnsafe RAW = new Double3x2RawOpsUnsafe();

    public MemorySegment storeCM(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double3x2 loadCM(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        return RAW.loadCMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCMFloat(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double3x2 loadCMFloat(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRM(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double3x2 loadRM(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        return RAW.loadRMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRMFloat(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double3x2 loadRMFloat(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCM(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double3x2 loadCM(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCMFloat(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double3x2 loadCMFloat(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src, stride);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRM(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double3x2 loadRM(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRMFloat(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double3x2 loadRMFloat(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src, stride);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset, stride);
    }
}
