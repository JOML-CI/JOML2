// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x4SegOpsUnsafe extends Float3x4SegOps {

    private static final Float3x4SegOpsMS MS = new Float3x4SegOpsMS();
    private static final Float3x4RawOpsUnsafe RAW = new Float3x4RawOpsUnsafe();

    public MemorySegment storeCM(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m20());
        U.putFloat(address + 12L, self.m01());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, self.m21());
        U.putFloat(address + 24L, self.m02());
        U.putFloat(address + 28L, self.m12());
        U.putFloat(address + 32L, self.m22());
        U.putFloat(address + 36L, self.m03());
        U.putFloat(address + 40L, self.m13());
        U.putFloat(address + 44L, self.m23());
        return dest;
    }
    public Float3x4 loadCM(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCM(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c4 = U.getFloat(address + 4L);
        float _c8 = U.getFloat(address + 8L);
        float _c1 = U.getFloat(address + 12L);
        float _c5 = U.getFloat(address + 16L);
        float _c9 = U.getFloat(address + 20L);
        float _c2 = U.getFloat(address + 24L);
        float _c6 = U.getFloat(address + 28L);
        float _c10 = U.getFloat(address + 32L);
        float _c3 = U.getFloat(address + 36L);
        float _c7 = U.getFloat(address + 40L);
        float _c11 = U.getFloat(address + 44L);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeCMDouble(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m20());
        U.putDouble(address + 24L, self.m01());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m21());
        U.putDouble(address + 48L, self.m02());
        U.putDouble(address + 56L, self.m12());
        U.putDouble(address + 64L, self.m22());
        U.putDouble(address + 72L, self.m03());
        U.putDouble(address + 80L, self.m13());
        U.putDouble(address + 88L, self.m23());
        return dest;
    }
    public Float3x4 loadCMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c4 = (float) U.getDouble(address + 8L);
        float _c8 = (float) U.getDouble(address + 16L);
        float _c1 = (float) U.getDouble(address + 24L);
        float _c5 = (float) U.getDouble(address + 32L);
        float _c9 = (float) U.getDouble(address + 40L);
        float _c2 = (float) U.getDouble(address + 48L);
        float _c6 = (float) U.getDouble(address + 56L);
        float _c10 = (float) U.getDouble(address + 64L);
        float _c3 = (float) U.getDouble(address + 72L);
        float _c7 = (float) U.getDouble(address + 80L);
        float _c11 = (float) U.getDouble(address + 88L);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRM(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m02());
        U.putFloat(address + 12L, self.m03());
        U.putFloat(address + 16L, self.m10());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, self.m12());
        U.putFloat(address + 28L, self.m13());
        U.putFloat(address + 32L, self.m20());
        U.putFloat(address + 36L, self.m21());
        U.putFloat(address + 40L, self.m22());
        U.putFloat(address + 44L, self.m23());
        return dest;
    }
    public Float3x4 loadRM(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(offset, src);
        long address = src.address() + offset;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        float _c9 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c11 = U.getFloat(address + 44L);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRMDouble(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m02());
        U.putDouble(address + 24L, self.m03());
        U.putDouble(address + 32L, self.m10());
        U.putDouble(address + 40L, self.m11());
        U.putDouble(address + 48L, self.m12());
        U.putDouble(address + 56L, self.m13());
        U.putDouble(address + 64L, self.m20());
        U.putDouble(address + 72L, self.m21());
        U.putDouble(address + 80L, self.m22());
        U.putDouble(address + 88L, self.m23());
        return dest;
    }
    public Float3x4 loadRMDouble(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(offset, src);
        long address = src.address() + offset;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        float _c9 = (float) U.getDouble(address + 72L);
        float _c10 = (float) U.getDouble(address + 80L);
        float _c11 = (float) U.getDouble(address + 88L);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeCM(Float3x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m10());
        U.putFloat(address + 8, self.m20());
        U.putFloat(_p1, self.m01());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m21());
        U.putFloat(_p2, self.m02());
        U.putFloat(_p2 + 4, self.m12());
        U.putFloat(_p2 + 8, self.m22());
        U.putFloat(_p3, self.m03());
        U.putFloat(_p3 + 4, self.m13());
        U.putFloat(_p3 + 8, self.m23());
        return dest;
    }
    public Float3x4 loadCM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = U.getFloat(address);
        float _c4 = U.getFloat(address + 4);
        float _c8 = U.getFloat(address + 8);
        float _c1 = U.getFloat(_p1);
        float _c5 = U.getFloat(_p1 + 4);
        float _c9 = U.getFloat(_p1 + 8);
        float _c2 = U.getFloat(_p2);
        float _c6 = U.getFloat(_p2 + 4);
        float _c10 = U.getFloat(_p2 + 8);
        float _c3 = U.getFloat(_p3);
        float _c7 = U.getFloat(_p3 + 4);
        float _c11 = U.getFloat(_p3 + 8);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeCMDouble(Float3x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(address + 16, self.m20());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m21());
        U.putDouble(_p2, self.m02());
        U.putDouble(_p2 + 8, self.m12());
        U.putDouble(_p2 + 16, self.m22());
        U.putDouble(_p3, self.m03());
        U.putDouble(_p3 + 8, self.m13());
        U.putDouble(_p3 + 16, self.m23());
        return dest;
    }
    public Float3x4 loadCMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c4 = (float) U.getDouble(address + 8);
        float _c8 = (float) U.getDouble(address + 16);
        float _c1 = (float) U.getDouble(_p1);
        float _c5 = (float) U.getDouble(_p1 + 8);
        float _c9 = (float) U.getDouble(_p1 + 16);
        float _c2 = (float) U.getDouble(_p2);
        float _c6 = (float) U.getDouble(_p2 + 8);
        float _c10 = (float) U.getDouble(_p2 + 16);
        float _c3 = (float) U.getDouble(_p3);
        float _c7 = (float) U.getDouble(_p3 + 8);
        float _c11 = (float) U.getDouble(_p3 + 16);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRM(Float3x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m01());
        U.putFloat(address + 8, self.m02());
        U.putFloat(address + 12, self.m03());
        U.putFloat(_p1, self.m10());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m12());
        U.putFloat(_p1 + 12, self.m13());
        U.putFloat(_p2, self.m20());
        U.putFloat(_p2 + 4, self.m21());
        U.putFloat(_p2 + 8, self.m22());
        U.putFloat(_p2 + 12, self.m23());
        return dest;
    }
    public Float3x4 loadRM(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4);
        float _c2 = U.getFloat(address + 8);
        float _c3 = U.getFloat(address + 12);
        float _c4 = U.getFloat(_p1);
        float _c5 = U.getFloat(_p1 + 4);
        float _c6 = U.getFloat(_p1 + 8);
        float _c7 = U.getFloat(_p1 + 12);
        float _c8 = U.getFloat(_p2);
        float _c9 = U.getFloat(_p2 + 4);
        float _c10 = U.getFloat(_p2 + 8);
        float _c11 = U.getFloat(_p2 + 12);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRMDouble(Float3x4 self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(address + 16, self.m02());
        U.putDouble(address + 24, self.m03());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m12());
        U.putDouble(_p1 + 24, self.m13());
        U.putDouble(_p2, self.m20());
        U.putDouble(_p2 + 8, self.m21());
        U.putDouble(_p2 + 16, self.m22());
        U.putDouble(_p2 + 24, self.m23());
        return dest;
    }
    public Float3x4 loadRMDouble(long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8);
        float _c2 = (float) U.getDouble(address + 16);
        float _c3 = (float) U.getDouble(address + 24);
        float _c4 = (float) U.getDouble(_p1);
        float _c5 = (float) U.getDouble(_p1 + 8);
        float _c6 = (float) U.getDouble(_p1 + 16);
        float _c7 = (float) U.getDouble(_p1 + 24);
        float _c8 = (float) U.getDouble(_p2);
        float _c9 = (float) U.getDouble(_p2 + 8);
        float _c10 = (float) U.getDouble(_p2 + 16);
        float _c11 = (float) U.getDouble(_p2 + 24);
        return new Float3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeCM4x4(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        RAW.storeCM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeCM4x4Double(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM4x4Double(self, offset, dest);
        RAW.storeCM4x4DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM4x4(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        RAW.storeRM4x4Unsafe(self, dest.address() + offset);
        return dest;
    }
    public MemorySegment storeRM4x4Double(Float3x4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM4x4Double(self, offset, dest);
        RAW.storeRM4x4DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
}
