// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4SegOpsUnsafe extends Float4x4SegOps {

    private static final Float4x4SegOpsMS MS = new Float4x4SegOpsMS();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, self.m30);
        U.putFloat(address + 16L, self.m01);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m21);
        U.putFloat(address + 28L, self.m31);
        U.putFloat(address + 32L, self.m02);
        U.putFloat(address + 36L, self.m12);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, self.m32);
        U.putFloat(address + 48L, self.m03);
        U.putFloat(address + 52L, self.m13);
        U.putFloat(address + 56L, self.m23);
        U.putFloat(address + 60L, self.m33);
        return dest;
    }
    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m30 = U.getFloat(address + 12L);
        self.m01 = U.getFloat(address + 16L);
        self.m11 = U.getFloat(address + 20L);
        self.m21 = U.getFloat(address + 24L);
        self.m31 = U.getFloat(address + 28L);
        self.m02 = U.getFloat(address + 32L);
        self.m12 = U.getFloat(address + 36L);
        self.m22 = U.getFloat(address + 40L);
        self.m32 = U.getFloat(address + 44L);
        self.m03 = U.getFloat(address + 48L);
        self.m13 = U.getFloat(address + 52L);
        self.m23 = U.getFloat(address + 56L);
        self.m33 = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m02);
        U.putFloat(address + 12L, self.m03);
        U.putFloat(address + 16L, self.m10);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m12);
        U.putFloat(address + 28L, self.m13);
        U.putFloat(address + 32L, self.m20);
        U.putFloat(address + 36L, self.m21);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, self.m23);
        U.putFloat(address + 48L, self.m30);
        U.putFloat(address + 52L, self.m31);
        U.putFloat(address + 56L, self.m32);
        U.putFloat(address + 60L, self.m33);
        return dest;
    }
    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m02 = U.getFloat(address + 8L);
        self.m03 = U.getFloat(address + 12L);
        self.m10 = U.getFloat(address + 16L);
        self.m11 = U.getFloat(address + 20L);
        self.m12 = U.getFloat(address + 24L);
        self.m13 = U.getFloat(address + 28L);
        self.m20 = U.getFloat(address + 32L);
        self.m21 = U.getFloat(address + 36L);
        self.m22 = U.getFloat(address + 40L);
        self.m23 = U.getFloat(address + 44L);
        self.m30 = U.getFloat(address + 48L);
        self.m31 = U.getFloat(address + 52L);
        self.m32 = U.getFloat(address + 56L);
        self.m33 = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset, stride);
    }
}
