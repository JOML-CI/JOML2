// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float3x2SegOpsMS implements Float3x2SegOps {
    public MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m21());
        return dest;
    }
    public Float3x2 loadCM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21());
        return dest;
    }
    public Float3x2 loadCMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m21());
        return dest;
    }
    public Float3x2 loadRM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21());
        return dest;
    }
    public Float3x2 loadRMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21());
        return dest;
    }
    public Float3x2 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest, int stride) {
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
    public Float3x2 loadCMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21());
        return dest;
    }
    public Float3x2 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest, int stride) {
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
    public Float3x2 loadRMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
