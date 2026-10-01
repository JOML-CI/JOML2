// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double3x4SegOpsMS implements Double3x4SegOps {
    public MemorySegment storeCM(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m13);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m23);
        return dest;
    }
    public Double3x4 loadCM(Double3x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m22 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.m23 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMFloat(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.m13);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.m23);
        return dest;
    }
    public Double3x4 loadCMFloat(Double3x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.m23 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m13);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m23);
        return dest;
    }
    public Double3x4 loadRM(Double3x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.m22 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.m23 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMFloat(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m13);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.m23);
        return dest;
    }
    public Double3x4 loadRMFloat(Double3x4Impl self, long offset, MemorySegment src) {
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.m23 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m13);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.m23);
        return dest;
    }
    public Double3x4 loadCM(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        self.m23 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCMFloat(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.m13);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8, (float) self.m23);
        return dest;
    }
    public Double3x4 loadCMFloat(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 4);
        self.m23 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRM(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m13);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.m23);
        return dest;
    }
    public Double3x4 loadRM(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m02 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m03 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.m10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.m20 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2);
        self.m21 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.m23 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeRMFloat(Double3x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.m13);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12, (float) self.m23);
        return dest;
    }
    public Double3x4 loadRMFloat(Double3x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m02 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m03 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12);
        self.m10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 12);
        self.m20 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2);
        self.m21 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.m23 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public MemorySegment storeCM4x4(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, self.m13);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, self.m23);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeCM4x4Float(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) self.m13);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) self.m23);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.m00);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m03);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m12);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m13);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m20);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m21);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m23);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
    public MemorySegment storeRM4x4Float(Double3x4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.m00);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m02);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m03);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m10);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m12);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m13);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.m20);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.m21);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.m22);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.m23);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) 0.0);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 60L, (float) 1.0);
        return dest;
    }
}
