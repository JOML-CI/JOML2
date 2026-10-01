// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double4x3SegOpsMS implements Double4x3SegOps {
    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[8]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.data[9]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.data[10]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.data[9] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.data[10] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.data[11] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        return self;
    }
    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[8]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[9]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.data[10]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.data[11]);
        return dest;
    }
    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[4] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[8] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[5] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[9] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[2] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[6] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.data[10] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.data[3] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.data[7] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.data[11] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[8]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[9]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[10]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[9] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.data[10] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.data[11] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        return self;
    }
    public MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.data[8]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[9]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.data[10]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.data[11]);
        return dest;
    }
    public Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[2] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[3] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.data[4] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[6] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[7] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.data[8] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[9] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[10] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.data[11] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        return self;
    }
    public MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.data[8]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.data[9]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.data[10]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.data[9] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.data[10] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.data[11] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12);
        return self;
    }
    public MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[8]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[9]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.data[10]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.data[11]);
        return dest;
    }
    public Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[4] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[8] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[9] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[2] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[6] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[10] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.data[3] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        self.data[7] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        self.data[11] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        return self;
    }
    public MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.data[8]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.data[9]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.data[10]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8, (float) self.data[11]);
        return dest;
    }
    public Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[9] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.data[10] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        self.data[11] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8);
        return self;
    }
}
