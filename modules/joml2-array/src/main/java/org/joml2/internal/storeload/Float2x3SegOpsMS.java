// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float2x3SegOpsMS implements Float2x3SegOps {
    public MemorySegment storeCM(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[5]);
        return dest;
    }
    public Float2x3 loadCM(Float2x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        return dest;
    }
    public Float2x3 loadCMDouble(Float2x3Impl self, long offset, MemorySegment src) {
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[5]);
        return dest;
    }
    public Float2x3 loadRM(Float2x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        return dest;
    }
    public Float2x3 loadRMDouble(Float2x3Impl self, long offset, MemorySegment src) {
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM(Float2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, self.data[5]);
        return dest;
    }
    public Float2x3 loadCM(Float2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMDouble(Float2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[5]);
        return dest;
    }
    public Float2x3 loadCMDouble(Float2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Float2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, self.data[5]);
        return dest;
    }
    public Float2x3 loadRM(Float2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMDouble(Float2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[5]);
        return dest;
    }
    public Float2x3 loadRMDouble(Float2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM3x3(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM3x3Double(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM3x3(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM3x3Double(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM4x4(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, 1.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }
    public MemorySegment storeCM4x4Double(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0f);
        return dest;
    }
    public MemorySegment storeRM4x4(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.data[5]);
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
    public MemorySegment storeRM4x4Double(Float2x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0f);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[5]);
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
