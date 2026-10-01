// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float3x3SegOpsMS extends Float3x3SegOps {
    public MemorySegment storeCM(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m22);
        return dest;
    }

    public Float3x3 loadCM(Float3x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22);
        return dest;
    }

    public Float3x3 loadCMDouble(Float3x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m22);
        return dest;
    }

    public Float3x3 loadRM(Float3x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22);
        return dest;
    }

    public Float3x3 loadRMDouble(Float3x3Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
        return dest;
    }

    public Float3x3 loadCM(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        return dest;
    }

    public Float3x3 loadCMDouble(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
        return dest;
    }

    public Float3x3 loadRM(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        return dest;
    }

    public Float3x3 loadRMDouble(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM4x4(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }

    public MemorySegment storeCM4x4Double(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }

    public MemorySegment storeRM4x4(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM4x4Double(Float3x3Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
        return dest;
    }
}
