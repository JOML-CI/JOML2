// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float3x3SegOpsMS implements Float3x3SegOps {
    public MemorySegment storeCM(Float3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m22());
        return dest;
    }
    public Float3x3 loadCM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCMDouble(Float3x3 self, long offset, MemorySegment dest) {
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
    public Float3x3 loadCMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRM(Float3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m22());
        return dest;
    }
    public Float3x3 loadRM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRMDouble(Float3x3 self, long offset, MemorySegment dest) {
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
    public Float3x3 loadRMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCM(Float3x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22());
        return dest;
    }
    public Float3x3 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCMDouble(Float3x3 self, long offset, MemorySegment dest, int stride) {
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
    public Float3x3 loadCMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRM(Float3x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22());
        return dest;
    }
    public Float3x3 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeRMDouble(Float3x3 self, long offset, MemorySegment dest, int stride) {
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
    public Float3x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeCM4x4(Float3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM4x4Double(Float3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM4x4(Float3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM4x4Double(Float3x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0f);
        return dest;
    }
}
