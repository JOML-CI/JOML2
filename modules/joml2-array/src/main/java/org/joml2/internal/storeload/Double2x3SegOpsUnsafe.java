// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Double2x3SegOpsUnsafe implements Double2x3SegOps {

    private static final Double2x3SegOpsMS MS = new Double2x3SegOpsMS();
    private static final Double2x3RawOpsUnsafe RAW = new Double2x3RawOpsUnsafe();

    public MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        return RAW.loadCMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        return RAW.loadRMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src, stride);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src, stride);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCM3x3(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM3x3(self, offset, dest);
        RAW.storeCM3x3Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeCM3x3Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM3x3Float(self, offset, dest);
        RAW.storeCM3x3FloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM3x3(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM3x3(self, offset, dest);
        RAW.storeRM3x3Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM3x3Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM3x3Float(self, offset, dest);
        RAW.storeRM3x3FloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeCM4x4(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        RAW.storeCM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeCM4x4Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4Float(self, offset, dest);
        RAW.storeCM4x4FloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM4x4(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        RAW.storeRM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM4x4Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4Float(self, offset, dest);
        RAW.storeRM4x4FloatUnsafe(self, dest.address() + offset);
        return dest;
    }
}
