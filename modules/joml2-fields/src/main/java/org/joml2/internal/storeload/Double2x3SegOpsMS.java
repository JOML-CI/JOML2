// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double2x3SegOpsMS extends Double2x3SegOps {
    public MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12);
        return dest;
    }

    public Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m01 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m11 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m02 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m12 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m12);
        return dest;
    }

    public Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12);
        return dest;
    }

    public Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m02 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m10 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m11 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m12 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m12);
        return dest;
    }

    public Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12);
        return dest;
    }

    public Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m01 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m02 = src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.m12 = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m12);
        return dest;
    }

    public Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12);
        return dest;
    }

    public Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m02 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m10 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m12 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m12);
        return dest;
    }

    public Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM3x3(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0);
        return dest;
    }

    public MemorySegment storeCM3x3Float(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM3x3(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0);
        return dest;
    }

    public MemorySegment storeRM3x3Float(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
        return dest;
    }

    public MemorySegment storeCM4x4(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }

    public MemorySegment storeCM4x4Float(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, (float) self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM4x4(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }

    public MemorySegment storeRM4x4Float(Double2x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }
}
