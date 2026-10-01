// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double3x3SegOpsMS implements Double3x3SegOps {
    public MemorySegment storeCM(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22());
        return dest;
    }
    public Double3x3 loadCM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        double _c8 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCMFloat(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m22());
        return dest;
    }
    public Double3x3 loadCMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        double _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRM(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22());
        return dest;
    }
    public Double3x3 loadRM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        double _c8 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRMFloat(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m22());
        return dest;
    }
    public Double3x3 loadRMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        double _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCM(Double3x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22());
        return dest;
    }
    public Double3x3 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        double _c8 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCMFloat(Double3x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.m22());
        return dest;
    }
    public Double3x3 loadCMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        double _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRM(Double3x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22());
        return dest;
    }
    public Double3x3 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        double _c8 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRMFloat(Double3x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.m22());
        return dest;
    }
    public Double3x3 loadRMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        double _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCM4x4(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeCM4x4Float(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4Float(Double3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
}
