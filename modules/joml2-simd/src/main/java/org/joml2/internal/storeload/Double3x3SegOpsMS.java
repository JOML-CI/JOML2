// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double3x3SegOpsMS implements Double3x3SegOps {
    public MemorySegment storeCM(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.data[8]);
        return dest;
    }
    public Double3x3 loadCM(Double3x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[2] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[3] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[4] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[6] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[7] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.data[8] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMFloat(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[8]);
        return dest;
    }
    public Double3x3 loadCMFloat(Double3x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.data[8]);
        return dest;
    }
    public Double3x3 loadRM(Double3x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[3] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[6] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[4] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[7] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[2] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[5] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.data[8] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMFloat(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[8]);
        return dest;
    }
    public Double3x3 loadRMFloat(Double3x3Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.data[8]);
        return dest;
    }
    public Double3x3 loadCM(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[2] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[3] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[4] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[5] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[6] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[7] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[8] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMFloat(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.data[8]);
        return dest;
    }
    public Double3x3 loadCMFloat(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.data[8]);
        return dest;
    }
    public Double3x3 loadRM(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.data[3] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[6] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[4] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[7] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[2] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[5] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[8] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMFloat(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.data[8]);
        return dest;
    }
    public Double3x3 loadRMFloat(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.data[8] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM4x4(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.data[8]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeCM4x4Float(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.data[8]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[7]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.data[8]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4Float(Double3x3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[7]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.data[8]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
}
