// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float4x3SegOpsMS implements Float4x3SegOps {
    public MemorySegment storeCM(Float4x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m31);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, self.m32);
        return dest;
    }
    public Float4x3 loadCM(Float4x3Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.m32 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        return self;
    }
    public MemorySegment storeCMDouble(Float4x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m32);
        return dest;
    }
    public Float4x3 loadCMDouble(Float4x3Impl self, long offset, MemorySegment src) {
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m20 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m30 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m21 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m31 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m02 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.m12 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.m22 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.m32 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        return self;
    }
    public MemorySegment storeRM(Float4x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.m31);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, self.m32);
        return dest;
    }
    public Float4x3 loadRM(Float4x3Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.m32 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        return self;
    }
    public MemorySegment storeRMDouble(Float4x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m31);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m32);
        return dest;
    }
    public Float4x3 loadRMDouble(Float4x3Impl self, long offset, MemorySegment src) {
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m02 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m12 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m20 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m21 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m22 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.m30 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.m31 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.m32 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        return self;
    }
    public MemorySegment storeCM(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, self.m31);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12, self.m32);
        return dest;
    }
    public Float4x3 loadCM(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.m32 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12);
        return self;
    }
    public MemorySegment storeCMDouble(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m31);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.m32);
        return dest;
    }
    public Float4x3 loadCMDouble(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m20 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m30 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m21 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m31 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.m02 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.m12 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.m32 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        return self;
    }
    public MemorySegment storeRM(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, self.m30);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, self.m31);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8, self.m32);
        return dest;
    }
    public Float4x3 loadRM(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.m30 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        self.m31 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        self.m32 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8);
        return self;
    }
    public MemorySegment storeRMDouble(Float4x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.m30);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m31);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.m32);
        return dest;
    }
    public Float4x3 loadRMDouble(Float4x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m02 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m12 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m20 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.m21 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.m30 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        self.m31 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        self.m32 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        return self;
    }
}
