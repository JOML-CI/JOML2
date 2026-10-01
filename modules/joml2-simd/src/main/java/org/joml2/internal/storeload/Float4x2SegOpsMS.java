// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float4x2SegOpsMS implements Float4x2SegOps {
    public MemorySegment storeCMDouble(Float4x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[7]);
        return dest;
    }
    public Float4x2 loadCMDouble(Float4x2Impl self, long offset, MemorySegment src) {
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[6] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[7] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeRM(Float4x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.data[7]);
        return dest;
    }
    public Float4x2 loadRM(Float4x2Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }
    public MemorySegment storeRMDouble(Float4x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[7]);
        return dest;
    }
    public Float4x2 loadRMDouble(Float4x2Impl self, long offset, MemorySegment src) {
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[6] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[7] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeCM(Float4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, self.data[7]);
        return dest;
    }
    public Float4x2 loadCM(Float4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        return self;
    }
    public MemorySegment storeCMDouble(Float4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.data[7]);
        return dest;
    }
    public Float4x2 loadCMDouble(Float4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[6] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[7] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        return self;
    }
    public MemorySegment storeRM(Float4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, self.data[7]);
        return dest;
    }
    public Float4x2 loadRM(Float4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        return self;
    }
    public MemorySegment storeRMDouble(Float4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.data[7]);
        return dest;
    }
    public Float4x2 loadRMDouble(Float4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[6] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        self.data[7] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        return self;
    }
}
