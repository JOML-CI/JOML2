// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class Double2x4SegOpsUnsafe implements Double2x4SegOps {

    private static final Double2x4SegOpsMS MS = new Double2x4SegOpsMS();
    private static final Double2x4RawOpsUnsafe RAW = new Double2x4RawOpsUnsafe();

    public MemorySegment storeCM(Double2x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x4 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(offset, src);
        return RAW.loadCMUnsafe(src.address() + offset);
    }
    public MemorySegment storeCMFloat(Double2x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x4 loadCMFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(offset, src);
        return RAW.loadCMFloatUnsafe(src.address() + offset);
    }
    public MemorySegment storeRM(Double2x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x4 loadRM(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(offset, src);
        return RAW.loadRMUnsafe(src.address() + offset);
    }
    public MemorySegment storeRMFloat(Double2x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x4 loadRMFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(offset, src);
        return RAW.loadRMFloatUnsafe(src.address() + offset);
    }
    public MemorySegment storeCM(Double2x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x4 loadCM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(offset, src, stride);
        return RAW.loadCMUnsafe(src.address() + offset, stride);
    }
    public MemorySegment storeCMFloat(Double2x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x4 loadCMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(offset, src, stride);
        return RAW.loadCMFloatUnsafe(src.address() + offset, stride);
    }
    public MemorySegment storeRM(Double2x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x4 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(offset, src, stride);
        return RAW.loadRMUnsafe(src.address() + offset, stride);
    }
    public MemorySegment storeRMFloat(Double2x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x4 loadRMFloat(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(offset, src, stride);
        return RAW.loadRMFloatUnsafe(src.address() + offset, stride);
    }
}
