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
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[6]);
        U.putDouble(address + 56L, self.data[7]);
        U.putDouble(address + 64L, self.data[8]);
        U.putDouble(address + 72L, self.data[9]);
        U.putDouble(address + 80L, self.data[10]);
        U.putDouble(address + 88L, self.data[11]);
        return dest;
    }
    public Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.data[4] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.data[6] = U.getDouble(address + 48L);
        self.data[7] = U.getDouble(address + 56L);
        self.data[8] = U.getDouble(address + 64L);
        self.data[9] = U.getDouble(address + 72L);
        self.data[10] = U.getDouble(address + 80L);
        self.data[11] = U.getDouble(address + 88L);
        return self;
    }
    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        U.putFloat(address + 16L, (float) self.data[4]);
        U.putFloat(address + 20L, (float) self.data[5]);
        U.putFloat(address + 24L, (float) self.data[6]);
        U.putFloat(address + 28L, (float) self.data[7]);
        U.putFloat(address + 32L, (float) self.data[8]);
        U.putFloat(address + 36L, (float) self.data[9]);
        U.putFloat(address + 40L, (float) self.data[10]);
        U.putFloat(address + 44L, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.data[6] = U.getFloat(address + 24L);
        self.data[7] = U.getFloat(address + 28L);
        self.data[8] = U.getFloat(address + 32L);
        self.data[9] = U.getFloat(address + 36L);
        self.data[10] = U.getFloat(address + 40L);
        self.data[11] = U.getFloat(address + 44L);
        return self;
    }
    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[4]);
        U.putDouble(address + 16L, self.data[8]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[5]);
        U.putDouble(address + 40L, self.data[9]);
        U.putDouble(address + 48L, self.data[2]);
        U.putDouble(address + 56L, self.data[6]);
        U.putDouble(address + 64L, self.data[10]);
        U.putDouble(address + 72L, self.data[3]);
        U.putDouble(address + 80L, self.data[7]);
        U.putDouble(address + 88L, self.data[11]);
        return dest;
    }
    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getDouble(address);
        self.data[4] = U.getDouble(address + 8L);
        self.data[8] = U.getDouble(address + 16L);
        self.data[1] = U.getDouble(address + 24L);
        self.data[5] = U.getDouble(address + 32L);
        self.data[9] = U.getDouble(address + 40L);
        self.data[2] = U.getDouble(address + 48L);
        self.data[6] = U.getDouble(address + 56L);
        self.data[10] = U.getDouble(address + 64L);
        self.data[3] = U.getDouble(address + 72L);
        self.data[7] = U.getDouble(address + 80L);
        self.data[11] = U.getDouble(address + 88L);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[4]);
        U.putFloat(address + 8L, (float) self.data[8]);
        U.putFloat(address + 12L, (float) self.data[1]);
        U.putFloat(address + 16L, (float) self.data[5]);
        U.putFloat(address + 20L, (float) self.data[9]);
        U.putFloat(address + 24L, (float) self.data[2]);
        U.putFloat(address + 28L, (float) self.data[6]);
        U.putFloat(address + 32L, (float) self.data[10]);
        U.putFloat(address + 36L, (float) self.data[3]);
        U.putFloat(address + 40L, (float) self.data[7]);
        U.putFloat(address + 44L, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[4] = U.getFloat(address + 4L);
        self.data[8] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[5] = U.getFloat(address + 16L);
        self.data[9] = U.getFloat(address + 20L);
        self.data[2] = U.getFloat(address + 24L);
        self.data[6] = U.getFloat(address + 28L);
        self.data[10] = U.getFloat(address + 32L);
        self.data[3] = U.getFloat(address + 36L);
        self.data[7] = U.getFloat(address + 40L);
        self.data[11] = U.getFloat(address + 44L);
        return self;
    }
    public MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(address + 24, self.data[3]);
        U.putDouble(_p1, self.data[4]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p1 + 16, self.data[6]);
        U.putDouble(_p1 + 24, self.data[7]);
        U.putDouble(_p2, self.data[8]);
        U.putDouble(_p2 + 8, self.data[9]);
        U.putDouble(_p2 + 16, self.data[10]);
        U.putDouble(_p2 + 24, self.data[11]);
        return dest;
    }
    public Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8);
        self.data[2] = U.getDouble(address + 16);
        self.data[3] = U.getDouble(address + 24);
        self.data[4] = U.getDouble(_p1);
        self.data[5] = U.getDouble(_p1 + 8);
        self.data[6] = U.getDouble(_p1 + 16);
        self.data[7] = U.getDouble(_p1 + 24);
        self.data[8] = U.getDouble(_p2);
        self.data[9] = U.getDouble(_p2 + 8);
        self.data[10] = U.getDouble(_p2 + 16);
        self.data[11] = U.getDouble(_p2 + 24);
        return self;
    }
    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[1]);
        U.putFloat(address + 8, (float) self.data[2]);
        U.putFloat(address + 12, (float) self.data[3]);
        U.putFloat(_p1, (float) self.data[4]);
        U.putFloat(_p1 + 4, (float) self.data[5]);
        U.putFloat(_p1 + 8, (float) self.data[6]);
        U.putFloat(_p1 + 12, (float) self.data[7]);
        U.putFloat(_p2, (float) self.data[8]);
        U.putFloat(_p2 + 4, (float) self.data[9]);
        U.putFloat(_p2 + 8, (float) self.data[10]);
        U.putFloat(_p2 + 12, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(address + 12);
        self.data[4] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[6] = U.getFloat(_p1 + 8);
        self.data[7] = U.getFloat(_p1 + 12);
        self.data[8] = U.getFloat(_p2);
        self.data[9] = U.getFloat(_p2 + 4);
        self.data[10] = U.getFloat(_p2 + 8);
        self.data[11] = U.getFloat(_p2 + 12);
        return self;
    }
    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[4]);
        U.putDouble(address + 16, self.data[8]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p1 + 16, self.data[9]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[6]);
        U.putDouble(_p2 + 16, self.data[10]);
        U.putDouble(_p3, self.data[3]);
        U.putDouble(_p3 + 8, self.data[7]);
        U.putDouble(_p3 + 16, self.data[11]);
        return dest;
    }
    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[4] = U.getDouble(address + 8);
        self.data[8] = U.getDouble(address + 16);
        self.data[1] = U.getDouble(_p1);
        self.data[5] = U.getDouble(_p1 + 8);
        self.data[9] = U.getDouble(_p1 + 16);
        self.data[2] = U.getDouble(_p2);
        self.data[6] = U.getDouble(_p2 + 8);
        self.data[10] = U.getDouble(_p2 + 16);
        self.data[3] = U.getDouble(_p3);
        self.data[7] = U.getDouble(_p3 + 8);
        self.data[11] = U.getDouble(_p3 + 16);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[4]);
        U.putFloat(address + 8, (float) self.data[8]);
        U.putFloat(_p1, (float) self.data[1]);
        U.putFloat(_p1 + 4, (float) self.data[5]);
        U.putFloat(_p1 + 8, (float) self.data[9]);
        U.putFloat(_p2, (float) self.data[2]);
        U.putFloat(_p2 + 4, (float) self.data[6]);
        U.putFloat(_p2 + 8, (float) self.data[10]);
        U.putFloat(_p3, (float) self.data[3]);
        U.putFloat(_p3 + 4, (float) self.data[7]);
        U.putFloat(_p3 + 8, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[4] = U.getFloat(address + 4);
        self.data[8] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[9] = U.getFloat(_p1 + 8);
        self.data[2] = U.getFloat(_p2);
        self.data[6] = U.getFloat(_p2 + 4);
        self.data[10] = U.getFloat(_p2 + 8);
        self.data[3] = U.getFloat(_p3);
        self.data[7] = U.getFloat(_p3 + 4);
        self.data[11] = U.getFloat(_p3 + 8);
        return self;
    }
}
