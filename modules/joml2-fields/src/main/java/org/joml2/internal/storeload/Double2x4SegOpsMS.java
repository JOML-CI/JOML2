// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double2x4SegOpsMS implements Double2x4SegOps {
    public MemorySegment storeCM(Double2x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m13);
        return dest;
    }
    public Double2x4 loadCM(Double2x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeCMFloat(Double2x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m13);
        return dest;
    }
    public Double2x4 loadCMFloat(Double2x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }
    public MemorySegment storeRM(Double2x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m13);
        return dest;
    }
    public Double2x4 loadRM(Double2x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeRMFloat(Double2x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m13);
        return dest;
    }
    public Double2x4 loadRMFloat(Double2x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }
    public MemorySegment storeCM(Double2x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m13);
        return dest;
    }
    public Double2x4 loadCM(Double2x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        return self;
    }
    public MemorySegment storeCMFloat(Double2x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.m13);
        return dest;
    }
    public Double2x4 loadCMFloat(Double2x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        return self;
    }
    public MemorySegment storeRM(Double2x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m13);
        return dest;
    }
    public Double2x4 loadRM(Double2x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        return self;
    }
    public MemorySegment storeRMFloat(Double2x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.m13);
        return dest;
    }
    public Double2x4 loadRMFloat(Double2x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        return self;
    }
}
