// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float4x4SegOpsMS extends Float4x4SegOps {
    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m30);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.m31);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, self.m32);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, self.m03);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, self.m13);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, self.m23);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, self.m33);
        return dest;
    }

    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m30 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m31 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.m32 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.m03 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 48L);
        self.m13 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 52L);
        self.m23 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 56L);
        self.m33 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m30);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m32);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, self.m03);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, self.m13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, self.m23);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, self.m33);
        return dest;
    }

    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m30 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m31 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.m32 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.m03 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 96L);
        self.m13 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 104L);
        self.m23 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 112L);
        self.m33 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m03);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.m13);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, self.m23);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, self.m30);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, self.m31);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, self.m32);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, self.m33);
        return dest;
    }

    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.m03 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.m13 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.m23 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.m30 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 48L);
        self.m31 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 52L);
        self.m32 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 56L);
        self.m33 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m03);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m23);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, self.m30);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, self.m31);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, self.m32);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, self.m33);
        return dest;
    }

    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.m03 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.m13 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.m23 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.m30 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 96L);
        self.m31 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 104L);
        self.m32 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 112L);
        self.m33 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, self.m30);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, self.m31);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 12, self.m32);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3, self.m03);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, self.m13);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 8, self.m23);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 12, self.m33);
        return dest;
    }

    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m30 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m31 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 12);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.m32 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 12);
        self.m03 = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
        self.m13 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
        return loadCM_sbb5935c0_1(self, src, _p3, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadCM}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadCM_sbb5935c0_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.m23 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 8);
        self.m33 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24, self.m30);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m31);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.m32);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.m03);
        return storeCMDouble_s90bc03c8_1(self, dest, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeCMDouble}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeCMDouble_s90bc03c8_1(Float4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.m23);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 24, self.m33);
        return dest;
    }

    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m30 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m31 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.m32 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        self.m03 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
        self.m13 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        return loadCMDouble_s629f4091_1(self, src, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadCMDouble}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadCMDouble_s629f4091_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.m23 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        self.m33 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, self.m03);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, self.m13);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 12, self.m23);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3, self.m30);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, self.m31);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 8, self.m32);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 12, self.m33);
        return dest;
    }

    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.m00 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.m01 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.m02 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
        self.m03 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12);
        self.m10 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.m11 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.m12 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.m13 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 12);
        self.m20 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
        self.m21 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.m22 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.m23 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 12);
        self.m30 = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
        self.m31 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
        return loadRM_sd0667f51_1(self, src, _p3, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadRM}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadRM_sd0667f51_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.m32 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 8);
        self.m33 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24, self.m03);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.m23);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.m30);
        return storeRMDouble_s88564b97_1(self, dest, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeRMDouble}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRMDouble_s88564b97_1(Float4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m31);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.m32);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 24, self.m33);
        return dest;
    }

    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.m00 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.m01 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.m02 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.m03 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.m10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.m11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.m12 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.m13 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.m20 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.m21 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.m22 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.m23 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        self.m30 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
        self.m31 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        return loadRMDouble_sdb90a770_1(self, src, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadRMDouble}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadRMDouble_sdb90a770_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.m32 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        self.m33 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
}
