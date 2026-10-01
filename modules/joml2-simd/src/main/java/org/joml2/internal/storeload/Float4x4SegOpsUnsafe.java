// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4SegOpsUnsafe extends Float4x4SegOps {

    private static final Float4x4SegOpsMS MS = new Float4x4SegOpsMS();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        RAW.storeCMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src);
        return RAW.loadCMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRM(self, offset, src);
        return RAW.loadRMUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        RAW.storeRMDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src);
        return RAW.loadRMDoubleUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        long address = dest.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 1]);
            U.putFloat(_a + 8L, _d[_o + 2]);
            U.putFloat(_a + 12L, _d[_o + 3]);
        }
        return dest;
    }
    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCM(self, offset, src, stride);
        long address = src.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 1] = U.getFloat(_a + 4L);
            _d[_o + 2] = U.getFloat(_a + 8L);
            _d[_o + 3] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 1]);
            U.putDouble(_a + 16L, _d[_o + 2]);
            U.putDouble(_a + 24L, _d[_o + 3]);
        }
        return dest;
    }
    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadCMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 1] = (float) U.getDouble(_a + 8L);
            _d[_o + 2] = (float) U.getDouble(_a + 16L);
            _d[_o + 3] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        long address = dest.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 4]);
            U.putFloat(_a + 8L, _d[_o + 8]);
            U.putFloat(_a + 12L, _d[_o + 12]);
        }
        return dest;
    }
    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRM(self, offset, src, stride);
        long address = src.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 4] = U.getFloat(_a + 4L);
            _d[_o + 8] = U.getFloat(_a + 8L);
            _d[_o + 12] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        long address = dest.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 4]);
            U.putDouble(_a + 16L, _d[_o + 8]);
            U.putDouble(_a + 24L, _d[_o + 12]);
        }
        return dest;
    }
    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return MS.loadRMDouble(self, offset, src, stride);
        long address = src.address() + offset;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 4] = (float) U.getDouble(_a + 8L);
            _d[_o + 8] = (float) U.getDouble(_a + 16L);
            _d[_o + 12] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
}
