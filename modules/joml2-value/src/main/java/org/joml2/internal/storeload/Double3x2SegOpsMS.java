// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double3x2SegOpsMS implements Double3x2SegOps {
    public MemorySegment storeCM(Double3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21());
        return dest;
    }
    public Double3x2 loadCM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeCMFloat(Double3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m21());
        return dest;
    }
    public Double3x2 loadCMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRM(Double3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21());
        return dest;
    }
    public Double3x2 loadRM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRMFloat(Double3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m21());
        return dest;
    }
    public Double3x2 loadRMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeCM(Double3x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21());
        return dest;
    }
    public Double3x2 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeCMFloat(Double3x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m21());
        return dest;
    }
    public Double3x2 loadCMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRM(Double3x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21());
        return dest;
    }
    public Double3x2 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRMFloat(Double3x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m21());
        return dest;
    }
    public Double3x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
