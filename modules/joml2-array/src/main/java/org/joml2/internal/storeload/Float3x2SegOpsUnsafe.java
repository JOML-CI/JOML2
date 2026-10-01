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
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        return dest;
    }
    public Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return dest;
    }
    public Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[1]);
        U.putFloat(address + 12L, self.data[4]);
        U.putFloat(address + 16L, self.data[2]);
        U.putFloat(address + 20L, self.data[5]);
        return dest;
    }
    public Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[4] = U.getFloat(address + 12L);
        self.data[2] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[4]);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[5]);
        return dest;
    }
    public Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8L);
        self.data[1] = (float) U.getDouble(address + 16L);
        self.data[4] = (float) U.getDouble(address + 24L);
        self.data[2] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public MemorySegment storeCM(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(_p1, self.data[3]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[5]);
        return dest;
    }
    public Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        return self;
    }
    public MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(_p1, self.data[3]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[5]);
        return dest;
    }
    public Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[5] = (float) U.getDouble(_p1 + 16);
        return self;
    }
    public MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[3]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[5]);
        return dest;
    }
    public Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[2] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        return self;
    }
    public MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[3]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[5]);
        return dest;
    }
    public Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[5] = (float) U.getDouble(_p2 + 8);
        return self;
    }
}
