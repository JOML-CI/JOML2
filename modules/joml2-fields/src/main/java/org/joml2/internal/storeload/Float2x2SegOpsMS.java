// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float2x2SegOpsMS implements Float2x2SegOps {
    public MemorySegment storeCM(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m11);
        return dest;
    }
    public Float2x2 loadCM(Float2x2Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
        return dest;
    }
    public Float2x2 loadCMDouble(Float2x2Impl self, long offset, MemorySegment src) {
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m11);
        return dest;
    }
    public Float2x2 loadRM(Float2x2Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
        return dest;
    }
    public Float2x2 loadRMDouble(Float2x2Impl self, long offset, MemorySegment src) {
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        return dest;
    }
    public Float2x2 loadCM(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        return dest;
    }
    public Float2x2 loadCMDouble(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        return dest;
    }
    public Float2x2 loadRM(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float2x2Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        return dest;
    }
    public Float2x2 loadRMDouble(Float2x2Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        self.m00 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM3x3(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM3x3Double(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM3x3(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM3x3Double(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM4x4(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, 1.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM4x4Double(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM4x4(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, 1.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM4x4Double(Float2x2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0f);
        return dest;
    }
}
