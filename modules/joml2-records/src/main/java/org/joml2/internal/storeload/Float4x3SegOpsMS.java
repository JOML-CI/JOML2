// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float4x3SegOpsMS implements Float4x3SegOps {
    public MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m31());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, self.m32());
        return dest;
    }
    public Float4x3 loadCM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c9 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        float _c11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m32());
        return dest;
    }
    public Float4x3 loadCMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c9 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        float _c11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.m31());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, self.m32());
        return dest;
    }
    public Float4x3 loadRM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        float _c9 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        float _c10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        float _c11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m31());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m32());
        return dest;
    }
    public Float4x3 loadRMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        float _c9 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        float _c10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        float _c11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, self.m31());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12, self.m32());
        return dest;
    }
    public Float4x3 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        float _c9 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        float _c10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        float _c11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m31());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.m32());
        return dest;
    }
    public Float4x3 loadCMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        float _c9 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        float _c10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        float _c11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m02());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m12());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, self.m31());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8, self.m32());
        return dest;
    }
    public Float4x3 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        float _c9 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        float _c10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        float _c11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.m30());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m31());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.m32());
        return dest;
    }
    public Float4x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        float _c9 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        float _c10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        float _c11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
}
