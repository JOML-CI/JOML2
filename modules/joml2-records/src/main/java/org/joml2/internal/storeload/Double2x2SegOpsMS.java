// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double2x2SegOpsMS implements Double2x2SegOps {
    public MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11());
        return dest;
    }
    public Double2x2 loadCM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11());
        return dest;
    }
    public Double2x2 loadCMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11());
        return dest;
    }
    public Double2x2 loadRM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11());
        return dest;
    }
    public Double2x2 loadRMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        return dest;
    }
    public Double2x2 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        return dest;
    }
    public Double2x2 loadCMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        return dest;
    }
    public Double2x2 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        return dest;
    }
    public Double2x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeCM3x3(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0);
        return dest;
    }
    public MemorySegment storeCM3x3Float(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) 1.0);
        return dest;
    }
    public MemorySegment storeRM3x3(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0);
        return dest;
    }
    public MemorySegment storeRM3x3Float(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) 1.0);
        return dest;
    }
    public MemorySegment storeCM4x4(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeCM4x4Float(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) 1.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4Float(Double2x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) 1.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
}
