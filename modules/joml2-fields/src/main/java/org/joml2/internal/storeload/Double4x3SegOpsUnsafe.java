// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x3SegOpsUnsafe extends Double4x3SegOps {

    private static final Double4x3SegOpsMS MS = new Double4x3SegOpsMS();

    public MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest) {
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
        return dest;
    }
    public Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src) {
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
        return self;
    }
    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m20);
        U.putFloat(address + 12L, (float) self.m30);
        U.putFloat(address + 16L, (float) self.m01);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) self.m21);
        U.putFloat(address + 28L, (float) self.m31);
        U.putFloat(address + 32L, (float) self.m02);
        U.putFloat(address + 36L, (float) self.m12);
        U.putFloat(address + 40L, (float) self.m22);
        U.putFloat(address + 44L, (float) self.m32);
        return dest;
    }
    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src);
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
        return self;
    }
    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
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
        U.putDouble(address + 72L, self.m30);
        U.putDouble(address + 80L, self.m31);
        U.putDouble(address + 88L, self.m32);
        return dest;
    }
    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8L);
        self.m02 = U.getDouble(address + 16L);
        self.m10 = U.getDouble(address + 24L);
        self.m11 = U.getDouble(address + 32L);
        self.m12 = U.getDouble(address + 40L);
        self.m20 = U.getDouble(address + 48L);
        self.m21 = U.getDouble(address + 56L);
        self.m22 = U.getDouble(address + 64L);
        self.m30 = U.getDouble(address + 72L);
        self.m31 = U.getDouble(address + 80L);
        self.m32 = U.getDouble(address + 88L);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m02);
        U.putFloat(address + 12L, (float) self.m10);
        U.putFloat(address + 16L, (float) self.m11);
        U.putFloat(address + 20L, (float) self.m12);
        U.putFloat(address + 24L, (float) self.m20);
        U.putFloat(address + 28L, (float) self.m21);
        U.putFloat(address + 32L, (float) self.m22);
        U.putFloat(address + 36L, (float) self.m30);
        U.putFloat(address + 40L, (float) self.m31);
        U.putFloat(address + 44L, (float) self.m32);
        return dest;
    }
    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src);
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
        self.m30 = U.getFloat(address + 36L);
        self.m31 = U.getFloat(address + 40L);
        self.m32 = U.getFloat(address + 44L);
        return self;
    }
    public MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(address + 16, self.m20);
        U.putDouble(address + 24, self.m30);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m21);
        U.putDouble(_p1 + 24, self.m31);
        U.putDouble(_p2, self.m02);
        U.putDouble(_p2 + 8, self.m12);
        U.putDouble(_p2 + 16, self.m22);
        U.putDouble(_p2 + 24, self.m32);
        return dest;
    }
    public Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8);
        self.m20 = U.getDouble(address + 16);
        self.m30 = U.getDouble(address + 24);
        self.m01 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m21 = U.getDouble(_p1 + 16);
        self.m31 = U.getDouble(_p1 + 24);
        self.m02 = U.getDouble(_p2);
        self.m12 = U.getDouble(_p2 + 8);
        self.m22 = U.getDouble(_p2 + 16);
        self.m32 = U.getDouble(_p2 + 24);
        return self;
    }
    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m10);
        U.putFloat(address + 8, (float) self.m20);
        U.putFloat(address + 12, (float) self.m30);
        U.putFloat(_p1, (float) self.m01);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m21);
        U.putFloat(_p1 + 12, (float) self.m31);
        U.putFloat(_p2, (float) self.m02);
        U.putFloat(_p2 + 4, (float) self.m12);
        U.putFloat(_p2 + 8, (float) self.m22);
        U.putFloat(_p2 + 12, (float) self.m32);
        return dest;
    }
    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m20 = U.getFloat(address + 8);
        self.m30 = U.getFloat(address + 12);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m21 = U.getFloat(_p1 + 8);
        self.m31 = U.getFloat(_p1 + 12);
        self.m02 = U.getFloat(_p2);
        self.m12 = U.getFloat(_p2 + 4);
        self.m22 = U.getFloat(_p2 + 8);
        self.m32 = U.getFloat(_p2 + 12);
        return self;
    }
    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(address + 16, self.m02);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m12);
        U.putDouble(_p2, self.m20);
        U.putDouble(_p2 + 8, self.m21);
        U.putDouble(_p2 + 16, self.m22);
        U.putDouble(_p3, self.m30);
        U.putDouble(_p3 + 8, self.m31);
        U.putDouble(_p3 + 16, self.m32);
        return dest;
    }
    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8);
        self.m02 = U.getDouble(address + 16);
        self.m10 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m12 = U.getDouble(_p1 + 16);
        self.m20 = U.getDouble(_p2);
        self.m21 = U.getDouble(_p2 + 8);
        self.m22 = U.getDouble(_p2 + 16);
        self.m30 = U.getDouble(_p3);
        self.m31 = U.getDouble(_p3 + 8);
        self.m32 = U.getDouble(_p3 + 16);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m01);
        U.putFloat(address + 8, (float) self.m02);
        U.putFloat(_p1, (float) self.m10);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m12);
        U.putFloat(_p2, (float) self.m20);
        U.putFloat(_p2 + 4, (float) self.m21);
        U.putFloat(_p2 + 8, (float) self.m22);
        U.putFloat(_p3, (float) self.m30);
        U.putFloat(_p3 + 4, (float) self.m31);
        U.putFloat(_p3 + 8, (float) self.m32);
        return dest;
    }
    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m02 = U.getFloat(address + 8);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m12 = U.getFloat(_p1 + 8);
        self.m20 = U.getFloat(_p2);
        self.m21 = U.getFloat(_p2 + 4);
        self.m22 = U.getFloat(_p2 + 8);
        self.m30 = U.getFloat(_p3);
        self.m31 = U.getFloat(_p3 + 4);
        self.m32 = U.getFloat(_p3 + 8);
        return self;
    }
}
