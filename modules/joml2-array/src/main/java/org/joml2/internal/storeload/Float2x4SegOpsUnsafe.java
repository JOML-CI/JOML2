// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2x4SegOpsUnsafe extends Float2x4SegOps {

    private static final Float2x4SegOpsMS MS = new Float2x4SegOpsMS();

    public MemorySegment storeCM(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[4]);
        U.putFloat(address + 8L, self.data[1]);
        U.putFloat(address + 12L, self.data[5]);
        U.putFloat(address + 16L, self.data[2]);
        U.putFloat(address + 20L, self.data[6]);
        U.putFloat(address + 24L, self.data[3]);
        U.putFloat(address + 28L, self.data[7]);
        return dest;
    }
    public Float2x4 loadCM(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[4] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[5] = U.getFloat(address + 12L);
        self.data[2] = U.getFloat(address + 16L);
        self.data[6] = U.getFloat(address + 20L);
        self.data[3] = U.getFloat(address + 24L);
        self.data[7] = U.getFloat(address + 28L);
        return self;
    }
    public MemorySegment storeCMDouble(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[4]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[5]);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[6]);
        U.putDouble(address + 48L, self.data[3]);
        U.putDouble(address + 56L, self.data[7]);
        return dest;
    }
    public Float2x4 loadCMDouble(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (float) U.getDouble(address);
        self.data[4] = (float) U.getDouble(address + 8L);
        self.data[1] = (float) U.getDouble(address + 16L);
        self.data[5] = (float) U.getDouble(address + 24L);
        self.data[2] = (float) U.getDouble(address + 32L);
        self.data[6] = (float) U.getDouble(address + 40L);
        self.data[3] = (float) U.getDouble(address + 48L);
        self.data[7] = (float) U.getDouble(address + 56L);
        return self;
    }
    public MemorySegment storeRM(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        U.putFloat(address + 24L, self.data[6]);
        U.putFloat(address + 28L, self.data[7]);
        return dest;
    }
    public Float2x4 loadRM(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.data[6] = U.getFloat(address + 24L);
        self.data[7] = U.getFloat(address + 28L);
        return self;
    }
    public MemorySegment storeRMDouble(Float2x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[6]);
        U.putDouble(address + 56L, self.data[7]);
        return dest;
    }
    public Float2x4 loadRMDouble(Float2x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        self.data[6] = (float) U.getDouble(address + 48L);
        self.data[7] = (float) U.getDouble(address + 56L);
        return self;
    }
    public MemorySegment storeCM(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[4]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[5]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[6]);
        U.putFloat(_p3, self.data[3]);
        U.putFloat(_p3 + 4, self.data[7]);
        return dest;
    }
    public Float2x4 loadCM(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[4] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[2] = U.getFloat(_p2);
        self.data[6] = U.getFloat(_p2 + 4);
        self.data[3] = U.getFloat(_p3);
        self.data[7] = U.getFloat(_p3 + 4);
        return self;
    }
    public MemorySegment storeCMDouble(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[4]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[6]);
        U.putDouble(_p3, self.data[3]);
        U.putDouble(_p3 + 8, self.data[7]);
        return dest;
    }
    public Float2x4 loadCMDouble(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[4] = (float) U.getDouble(address + 8);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[5] = (float) U.getDouble(_p1 + 8);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[6] = (float) U.getDouble(_p2 + 8);
        self.data[3] = (float) U.getDouble(_p3);
        self.data[7] = (float) U.getDouble(_p3 + 8);
        return self;
    }
    public MemorySegment storeRM(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(address + 12, self.data[3]);
        U.putFloat(_p1, self.data[4]);
        U.putFloat(_p1 + 4, self.data[5]);
        U.putFloat(_p1 + 8, self.data[6]);
        U.putFloat(_p1 + 12, self.data[7]);
        return dest;
    }
    public Float2x4 loadRM(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(address + 12);
        self.data[4] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[6] = U.getFloat(_p1 + 8);
        self.data[7] = U.getFloat(_p1 + 12);
        return self;
    }
    public MemorySegment storeRMDouble(Float2x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(address + 24, self.data[3]);
        U.putDouble(_p1, self.data[4]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p1 + 16, self.data[6]);
        U.putDouble(_p1 + 24, self.data[7]);
        return dest;
    }
    public Float2x4 loadRMDouble(Float2x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(address + 24);
        self.data[4] = (float) U.getDouble(_p1);
        self.data[5] = (float) U.getDouble(_p1 + 8);
        self.data[6] = (float) U.getDouble(_p1 + 16);
        self.data[7] = (float) U.getDouble(_p1 + 24);
        return self;
    }
}
