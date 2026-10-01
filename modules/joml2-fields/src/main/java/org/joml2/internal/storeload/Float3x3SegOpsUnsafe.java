// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x3SegOpsUnsafe extends Float3x3SegOps {

    private static final Float3x3SegOpsMS MS = new Float3x3SegOpsMS();

    public MemorySegment storeCM(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, self.m01);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, self.m21);
        U.putFloat(address + 24L, self.m02);
        U.putFloat(address + 28L, self.m12);
        U.putFloat(address + 32L, self.m22);
        return dest;
    }
    public Float3x3 loadCM(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m01 = U.getFloat(address + 12L);
        self.m11 = U.getFloat(address + 16L);
        self.m21 = U.getFloat(address + 20L);
        self.m02 = U.getFloat(address + 24L);
        self.m12 = U.getFloat(address + 28L);
        self.m22 = U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m01);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, self.m21);
        U.putDouble(address + 48L, self.m02);
        U.putDouble(address + 56L, self.m12);
        U.putDouble(address + 64L, self.m22);
        return dest;
    }
    public Float3x3 loadCMDouble(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        long address = src.address() + offset;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8L);
        self.m20 = (float) U.getDouble(address + 16L);
        self.m01 = (float) U.getDouble(address + 24L);
        self.m11 = (float) U.getDouble(address + 32L);
        self.m21 = (float) U.getDouble(address + 40L);
        self.m02 = (float) U.getDouble(address + 48L);
        self.m12 = (float) U.getDouble(address + 56L);
        self.m22 = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m02);
        U.putFloat(address + 12L, self.m10);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, self.m12);
        U.putFloat(address + 24L, self.m20);
        U.putFloat(address + 28L, self.m21);
        U.putFloat(address + 32L, self.m22);
        return dest;
    }
    public Float3x3 loadRM(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m02 = U.getFloat(address + 8L);
        self.m10 = U.getFloat(address + 12L);
        self.m11 = U.getFloat(address + 16L);
        self.m12 = U.getFloat(address + 20L);
        self.m20 = U.getFloat(address + 24L);
        self.m21 = U.getFloat(address + 28L);
        self.m22 = U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, self.m10);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, self.m12);
        U.putDouble(address + 48L, self.m20);
        U.putDouble(address + 56L, self.m21);
        U.putDouble(address + 64L, self.m22);
        return dest;
    }
    public Float3x3 loadRMDouble(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        long address = src.address() + offset;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8L);
        self.m02 = (float) U.getDouble(address + 16L);
        self.m10 = (float) U.getDouble(address + 24L);
        self.m11 = (float) U.getDouble(address + 32L);
        self.m12 = (float) U.getDouble(address + 40L);
        self.m20 = (float) U.getDouble(address + 48L);
        self.m21 = (float) U.getDouble(address + 56L);
        self.m22 = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m10);
        U.putFloat(address + 8, self.m20);
        U.putFloat(_p1, self.m01);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p1 + 8, self.m21);
        U.putFloat(_p2, self.m02);
        U.putFloat(_p2 + 4, self.m12);
        U.putFloat(_p2 + 8, self.m22);
        return dest;
    }
    public Float3x3 loadCM(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m20 = U.getFloat(address + 8);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m21 = U.getFloat(_p1 + 8);
        self.m02 = U.getFloat(_p2);
        self.m12 = U.getFloat(_p2 + 4);
        self.m22 = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(address + 16, self.m20);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m21);
        U.putDouble(_p2, self.m02);
        U.putDouble(_p2 + 8, self.m12);
        U.putDouble(_p2 + 16, self.m22);
        return dest;
    }
    public Float3x3 loadCMDouble(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8);
        self.m20 = (float) U.getDouble(address + 16);
        self.m01 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m21 = (float) U.getDouble(_p1 + 16);
        self.m02 = (float) U.getDouble(_p2);
        self.m12 = (float) U.getDouble(_p2 + 8);
        self.m22 = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m01);
        U.putFloat(address + 8, self.m02);
        U.putFloat(_p1, self.m10);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p1 + 8, self.m12);
        U.putFloat(_p2, self.m20);
        U.putFloat(_p2 + 4, self.m21);
        U.putFloat(_p2 + 8, self.m22);
        return dest;
    }
    public Float3x3 loadRM(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m02 = U.getFloat(address + 8);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m12 = U.getFloat(_p1 + 8);
        self.m20 = U.getFloat(_p2);
        self.m21 = U.getFloat(_p2 + 4);
        self.m22 = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(address + 16, self.m02);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m12);
        U.putDouble(_p2, self.m20);
        U.putDouble(_p2 + 8, self.m21);
        U.putDouble(_p2 + 16, self.m22);
        return dest;
    }
    public Float3x3 loadRMDouble(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8);
        self.m02 = (float) U.getDouble(address + 16);
        self.m10 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m12 = (float) U.getDouble(_p1 + 16);
        self.m20 = (float) U.getDouble(_p2);
        self.m21 = (float) U.getDouble(_p2 + 8);
        self.m22 = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM4x4(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m01);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m21);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.m02);
        U.putFloat(address + 36L, self.m12);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM4x4Double(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4Double(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m21);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, self.m02);
        U.putDouble(address + 72L, self.m12);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m02);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m10);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m12);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.m20);
        U.putFloat(address + 36L, self.m21);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM4x4Double(Float3x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4Double(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m12);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, self.m20);
        U.putDouble(address + 72L, self.m21);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return dest;
    }
}
