// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4SegOpsUnsafe extends Double4x4SegOps {

    private static final Double4x4SegOpsMS MS = new Double4x4SegOpsMS();
    private static final Double4x4RawOpsUnsafe RAW = new Double4x4RawOpsUnsafe();

    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m30);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m21);
        U.putDouble(address + 56L, self.m31);
        U.putDouble(address + 64L, self.m02);
        U.putDouble(address + 72L, self.m12);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, self.m32);
        U.putDouble(address + 96L, self.m03);
        U.putDouble(address + 104L, self.m13);
        U.putDouble(address + 112L, self.m23);
        U.putDouble(address + 120L, self.m33);
        return dest;
    }
    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8L);
        self.m20 = U.getDouble(address + 16L);
        self.m30 = U.getDouble(address + 24L);
        self.m01 = U.getDouble(address + 32L);
        self.m11 = U.getDouble(address + 40L);
        self.m21 = U.getDouble(address + 48L);
        self.m31 = U.getDouble(address + 56L);
        self.m02 = U.getDouble(address + 64L);
        self.m12 = U.getDouble(address + 72L);
        self.m22 = U.getDouble(address + 80L);
        self.m32 = U.getDouble(address + 88L);
        self.m03 = U.getDouble(address + 96L);
        self.m13 = U.getDouble(address + 104L);
        self.m23 = U.getDouble(address + 112L);
        self.m33 = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, self.m03);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m12);
        U.putDouble(address + 56L, self.m13);
        U.putDouble(address + 64L, self.m20);
        U.putDouble(address + 72L, self.m21);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, self.m23);
        U.putDouble(address + 96L, self.m30);
        U.putDouble(address + 104L, self.m31);
        U.putDouble(address + 112L, self.m32);
        U.putDouble(address + 120L, self.m33);
        return dest;
    }
    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8L);
        self.m02 = U.getDouble(address + 16L);
        self.m03 = U.getDouble(address + 24L);
        self.m10 = U.getDouble(address + 32L);
        self.m11 = U.getDouble(address + 40L);
        self.m12 = U.getDouble(address + 48L);
        self.m13 = U.getDouble(address + 56L);
        self.m20 = U.getDouble(address + 64L);
        self.m21 = U.getDouble(address + 72L);
        self.m22 = U.getDouble(address + 80L);
        self.m23 = U.getDouble(address + 88L);
        self.m30 = U.getDouble(address + 96L);
        self.m31 = U.getDouble(address + 104L);
        self.m32 = U.getDouble(address + 112L);
        self.m33 = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        RAW.storeCMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        return RAW.loadCMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src, stride);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        RAW.storeRMUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        return RAW.loadRMUnsafe(self, src.address() + offset, stride);
    }
    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset, stride);
        return dest;
    }
    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src, stride);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset, stride);
    }
}
