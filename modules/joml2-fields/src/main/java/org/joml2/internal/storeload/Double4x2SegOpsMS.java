// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double4x2SegOpsMS implements Double4x2SegOps {
    public MemorySegment storeCM(Double4x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31);
        return dest;
    }
    public Double4x2 loadCM(Double4x2Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m30 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m31 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeCMFloat(Double4x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m31);
        return dest;
    }
    public Double4x2 loadCMFloat(Double4x2Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }
    public MemorySegment storeRM(Double4x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31);
        return dest;
    }
    public Double4x2 loadRM(Double4x2Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m30 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m31 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m31);
        return dest;
    }
    public Double4x2 loadRMFloat(Double4x2Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }
    public MemorySegment storeCM(Double4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m31);
        return dest;
    }
    public Double4x2 loadCM(Double4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m30 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m31 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        return self;
    }
    public MemorySegment storeCMFloat(Double4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.m31);
        return dest;
    }
    public Double4x2 loadCMFloat(Double4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        return self;
    }
    public MemorySegment storeRM(Double4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m31);
        return dest;
    }
    public Double4x2 loadRM(Double4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m30 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        self.m31 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, (float) self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.m31);
        return dest;
    }
    public Double4x2 loadRMFloat(Double4x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        return self;
    }
}
