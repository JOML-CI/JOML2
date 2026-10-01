// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2x3SegOpsUnsafe extends Double2x3SegOps {

    private static final Double2x3SegOpsMS MS = new Double2x3SegOpsMS();

    public MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return dest;
    }
    public Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.data[4] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        U.putFloat(address + 16L, (float) self.data[4]);
        U.putFloat(address + 20L, (float) self.data[5]);
        return dest;
    }
    public Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[4]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, self.data[5]);
        return dest;
    }
    public Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8L);
        self.data[4] = U.getDouble(address + 16L);
        self.data[1] = U.getDouble(address + 24L);
        self.data[3] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, (float) self.data[4]);
        U.putFloat(address + 12L, (float) self.data[1]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, (float) self.data[5]);
        return dest;
    }
    public Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4L);
        self.data[4] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[3] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(_p1, self.data[2]);
        U.putDouble(_p1 + 8, self.data[3]);
        U.putDouble(_p2, self.data[4]);
        U.putDouble(_p2 + 8, self.data[5]);
        return dest;
    }
    public Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8);
        self.data[2] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.data[4] = U.getDouble(_p2);
        self.data[5] = U.getDouble(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[1]);
        U.putFloat(_p1, (float) self.data[2]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        U.putFloat(_p2, (float) self.data[4]);
        U.putFloat(_p2 + 4, (float) self.data[5]);
        return dest;
    }
    public Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.data[4] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[2]);
        U.putDouble(address + 16, self.data[4]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[3]);
        U.putDouble(_p1 + 16, self.data[5]);
        return dest;
    }
    public Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8);
        self.data[4] = U.getDouble(address + 16);
        self.data[1] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.data[5] = U.getDouble(_p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[2]);
        U.putFloat(address + 8, (float) self.data[4]);
        U.putFloat(_p1, (float) self.data[1]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        U.putFloat(_p1 + 8, (float) self.data[5]);
        return dest;
    }
    public Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMFloat(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4);
        self.data[4] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM3x3(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM3x3(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.data[2]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, self.data[4]);
        U.putDouble(address + 56L, self.data[5]);
        U.putDouble(address + 64L, 1.0);
        return dest;
    }
    public MemorySegment storeCM3x3Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM3x3Float(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, (float) self.data[2]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, (float) self.data[4]);
        U.putFloat(address + 28L, (float) self.data[5]);
        U.putFloat(address + 32L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM3x3(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM3x3(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[4]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return dest;
    }
    public MemorySegment storeRM3x3Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM3x3Float(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, (float) self.data[4]);
        U.putFloat(address + 12L, (float) self.data[1]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, (float) self.data[5]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM4x4(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, self.data[4]);
        U.putDouble(address + 104L, self.data[5]);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeCM4x4Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4Float(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, (float) self.data[2]);
        U.putFloat(address + 20L, (float) self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, (float) self.data[4]);
        U.putFloat(address + 52L, (float) self.data[5]);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM4x4(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.data[4]);
        U.putDouble(address + 32L, self.data[1]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, self.data[5]);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4Float(Double2x3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4Float(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, (float) self.data[4]);
        U.putFloat(address + 16L, (float) self.data[1]);
        U.putFloat(address + 20L, (float) self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, (float) self.data[5]);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return dest;
    }
}
