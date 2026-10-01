// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double4x2SegOpsMS implements Double4x2SegOps {
    public MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31());
        return dest;
    }
    public Double4x2 loadCM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m31());
        return dest;
    }
    public Double4x2 loadCMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31());
        return dest;
    }
    public Double4x2 loadRM(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m31());
        return dest;
    }
    public Double4x2 loadRMFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m31());
        return dest;
    }
    public Double4x2 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.m31());
        return dest;
    }
    public Double4x2 loadCMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m31());
        return dest;
    }
    public Double4x2 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, (float) self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.m31());
        return dest;
    }
    public Double4x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
