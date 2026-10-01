// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x2SegOpsUnsafe extends Float3x2SegOps {

    private static final Float3x2SegOpsMS MS = new Float3x2SegOpsMS();

    public MemorySegment storeCM(Float3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, self.m01);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, self.m21);
        return dest;
    }
    public Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m01 = U.getFloat(address + 12L);
        self.m11 = U.getFloat(address + 16L);
        self.m21 = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m01);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, self.m21);
        return dest;
    }
    public Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        long address = src.address() + offset;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8L);
        self.m20 = (float) U.getDouble(address + 16L);
        self.m01 = (float) U.getDouble(address + 24L);
        self.m11 = (float) U.getDouble(address + 32L);
        self.m21 = (float) U.getDouble(address + 40L);
        return self;
    }
    public MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m10);
        U.putFloat(address + 12L, self.m11);
        U.putFloat(address + 16L, self.m20);
        U.putFloat(address + 20L, self.m21);
        return dest;
    }
    public Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m10 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.m20 = U.getFloat(address + 16L);
        self.m21 = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m10);
        U.putDouble(address + 24L, self.m11);
        U.putDouble(address + 32L, self.m20);
        U.putDouble(address + 40L, self.m21);
        return dest;
    }
    public Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        long address = src.address() + offset;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8L);
        self.m10 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.m20 = (float) U.getDouble(address + 32L);
        self.m21 = (float) U.getDouble(address + 40L);
        return self;
    }
    public MemorySegment storeCM(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m10);
        U.putFloat(address + 8, self.m20);
        U.putFloat(_p1, self.m01);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p1 + 8, self.m21);
        return dest;
    }
    public Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
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
    public MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
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
    public Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8);
        self.m20 = (float) U.getDouble(address + 16);
        self.m01 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m21 = (float) U.getDouble(_p1 + 16);
        return self;
    }
    public MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m01);
        U.putFloat(_p1, self.m10);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p2, self.m20);
        U.putFloat(_p2 + 4, self.m21);
        return dest;
    }
    public Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
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
    public MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
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
    public Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8);
        self.m10 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m20 = (float) U.getDouble(_p2);
        self.m21 = (float) U.getDouble(_p2 + 8);
        return self;
    }
}
