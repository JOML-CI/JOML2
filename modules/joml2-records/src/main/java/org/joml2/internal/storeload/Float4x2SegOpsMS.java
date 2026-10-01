// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float4x2SegOpsMS implements Float4x2SegOps {
    public MemorySegment storeCM(Float4x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m31());
        return dest;
    }
    public Float4x2 loadCM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeCMDouble(Float4x2 self, long offset, MemorySegment dest) {
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
    public Float4x2 loadCMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRM(Float4x2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m31());
        return dest;
    }
    public Float4x2 loadRM(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRMDouble(Float4x2 self, long offset, MemorySegment dest) {
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
    public Float4x2 loadRMDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeCM(Float4x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, self.m31());
        return dest;
    }
    public Float4x2 loadCM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeCMDouble(Float4x2 self, long offset, MemorySegment dest, int stride) {
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
    public Float4x2 loadCMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRM(Float4x2 self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m01());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m10());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m20());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, self.m30());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, self.m31());
        return dest;
    }
    public Float4x2 loadRM(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public MemorySegment storeRMDouble(Float4x2 self, long offset, MemorySegment dest, int stride) {
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
    public Float4x2 loadRMDouble(long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
