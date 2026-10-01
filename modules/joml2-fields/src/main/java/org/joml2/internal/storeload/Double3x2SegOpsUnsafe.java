// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x2SegOpsUnsafe extends Double3x2SegOps {

    private static final Double3x2SegOpsMS MS = new Double3x2SegOpsMS();

    public MemorySegment storeCM(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m01);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, self.m21);
        return dest;
    }
    public Double3x2 loadCM(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8L);
        self.m20 = U.getDouble(address + 16L);
        self.m01 = U.getDouble(address + 24L);
        self.m11 = U.getDouble(address + 32L);
        self.m21 = U.getDouble(address + 40L);
        return self;
    }
    public MemorySegment storeCMFloat(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m20);
        U.putFloat(address + 12L, (float) self.m01);
        U.putFloat(address + 16L, (float) self.m11);
        U.putFloat(address + 20L, (float) self.m21);
        return dest;
    }
    public Double3x2 loadCMFloat(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m01 = U.getFloat(address + 12L);
        self.m11 = U.getFloat(address + 16L);
        self.m21 = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeRM(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m10);
        U.putDouble(address + 24L, self.m11);
        U.putDouble(address + 32L, self.m20);
        U.putDouble(address + 40L, self.m21);
        return dest;
    }
    public Double3x2 loadRM(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8L);
        self.m10 = U.getDouble(address + 16L);
        self.m11 = U.getDouble(address + 24L);
        self.m20 = U.getDouble(address + 32L);
        self.m21 = U.getDouble(address + 40L);
        return self;
    }
    public MemorySegment storeRMFloat(Double3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m10);
        U.putFloat(address + 12L, (float) self.m11);
        U.putFloat(address + 16L, (float) self.m20);
        U.putFloat(address + 20L, (float) self.m21);
        return dest;
    }
    public Double3x2 loadRMFloat(Double3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m10 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.m20 = U.getFloat(address + 16L);
        self.m21 = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeCM(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(address + 16, self.m20);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m21);
        return dest;
    }
    public Double3x2 loadCM(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8);
        self.m20 = U.getDouble(address + 16);
        self.m01 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m21 = U.getDouble(_p1 + 16);
        return self;
    }
    public MemorySegment storeCMFloat(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m10);
        U.putFloat(address + 8, (float) self.m20);
        U.putFloat(_p1, (float) self.m01);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m21);
        return dest;
    }
    public Double3x2 loadCMFloat(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m20 = U.getFloat(address + 8);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m21 = U.getFloat(_p1 + 8);
        return self;
    }
    public MemorySegment storeRM(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p2, self.m20);
        U.putDouble(_p2 + 8, self.m21);
        return dest;
    }
    public Double3x2 loadRM(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8);
        self.m10 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m20 = U.getDouble(_p2);
        self.m21 = U.getDouble(_p2 + 8);
        return self;
    }
    public MemorySegment storeRMFloat(Double3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m01);
        U.putFloat(_p1, (float) self.m10);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p2, (float) self.m20);
        U.putFloat(_p2 + 4, (float) self.m21);
        return dest;
    }
    public Double3x2 loadRMFloat(Double3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m20 = U.getFloat(_p2);
        self.m21 = U.getFloat(_p2 + 4);
        return self;
    }
}
