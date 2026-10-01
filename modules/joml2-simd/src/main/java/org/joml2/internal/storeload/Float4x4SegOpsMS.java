// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float4x4SegOpsMS extends Float4x4SegOps {
    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[3]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[4]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[6]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[7]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.data[8]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.data[9]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.data[10]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, self.data[11]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, self.data[12]);
        return storeCMDouble_s557f306a_1(self, offset, dest, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeCMDouble}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeCMDouble_s557f306a_1(Float4x4Impl self, long offset, MemorySegment dest, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, self.data[13]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, self.data[14]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, self.data[15]);
        return dest;
    }

    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[2] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[3] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[4] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[6] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[7] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.data[8] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.data[9] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.data[10] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.data[11] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.data[12] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 96L);
        return loadCMDouble_s44f82bd7_1(self, offset, src, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadCMDouble}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadCMDouble_s44f82bd7_1(Float4x4Impl self, long offset, MemorySegment src, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.data[13] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 104L);
        self.data[14] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 112L);
        self.data[15] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.data[8]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[12]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[5]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.data[9]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.data[13]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.data[6]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.data[10]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, self.data[14]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, self.data[3]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, self.data[7]);
        return storeRM_s97b173e2_1(self, offset, dest, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code storeRM}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRM_s97b173e2_1(Float4x4Impl self, long offset, MemorySegment dest, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, self.data[11]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, self.data[15]);
        return dest;
    }

    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.data[0] = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.data[4] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[8] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[12] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[1] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[9] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[13] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.data[2] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.data[6] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.data[10] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.data[14] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.data[3] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 48L);
        self.data[7] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 52L);
        return loadRM_s87ca6c17_1(self, offset, src, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadRM}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadRM_s87ca6c17_1(Float4x4Impl self, long offset, MemorySegment src, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.data[11] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 56L);
        self.data[15] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[4]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[8]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[12]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[1]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[9]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[13]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.data[2]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.data[6]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.data[10]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, self.data[14]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, self.data[3]);
        return storeRMDouble_s58c371c1_1(self, offset, dest, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeRMDouble}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRMDouble_s58c371c1_1(Float4x4Impl self, long offset, MemorySegment dest, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, self.data[7]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, self.data[11]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, self.data[15]);
        return dest;
    }

    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[4] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[8] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[12] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[1] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[9] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[13] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.data[2] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.data[6] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.data[10] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.data[14] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.data[3] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 96L);
        return loadRMDouble_s3e0ee0d2_1(self, offset, src, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadRMDouble}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadRMDouble_s3e0ee0d2_1(Float4x4Impl self, long offset, MemorySegment src, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.data[7] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 104L);
        self.data[11] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 112L);
        self.data[15] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, self.data[3]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.data[5]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.data[6]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, self.data[7]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.data[8]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.data[9]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.data[10]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 12, self.data[11]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3, self.data[12]);
        return storeCM_s52f103cf_1(self, dest, _p3, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code storeCM}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeCM_s52f103cf_1(Float4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, self.data[13]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 8, self.data[14]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 12, self.data[15]);
        return dest;
    }

    public Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.data[0] = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.data[1] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[2] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[3] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12);
        self.data[4] = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.data[5] = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[6] = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.data[7] = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 12);
        self.data[8] = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
        self.data[9] = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.data[10] = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.data[11] = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 12);
        self.data[12] = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
        return loadCM_sbb5935c0_1(self, src, _p3, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadCM}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadCM_sbb5935c0_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.data[13] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
        self.data[14] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 8);
        self.data[15] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[1]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[2]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24, self.data[3]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.data[4]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[5]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[6]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.data[7]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.data[8]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[9]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.data[10]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.data[11]);
        return storeCMDouble_s90bc03c8_1(self, dest, _p2 + _ps, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeCMDouble}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeCMDouble_s90bc03c8_1(Float4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.data[12]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.data[13]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.data[14]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 24, self.data[15]);
        return dest;
    }

    public Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[2] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[3] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.data[4] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[6] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[7] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.data[8] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[9] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[10] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.data[11] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        return loadCMDouble_s629f4091_1(self, src, _p2 + _ps, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadCMDouble}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadCMDouble_s629f4091_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.data[12] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
        self.data[13] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        self.data[14] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        self.data[15] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.data[8]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, self.data[12]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.data[5]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.data[9]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, self.data[13]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.data[6]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.data[10]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 12, self.data[14]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3, self.data[3]);
        return storeRM_se1021b40_1(self, dest, _p3, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code storeRM}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRM_se1021b40_1(Float4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, self.data[7]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 8, self.data[11]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 12, self.data[15]);
        return dest;
    }

    public Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.data[0] = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.data[4] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
        self.data[8] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
        self.data[12] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12);
        self.data[1] = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
        self.data[5] = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
        self.data[9] = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
        self.data[13] = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 12);
        self.data[2] = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
        self.data[6] = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
        self.data[10] = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
        self.data[14] = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 12);
        self.data[3] = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
        return loadRM_sd0667f51_1(self, src, _p3, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadRM}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadRM_sd0667f51_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.data[7] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
        self.data[11] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 8);
        self.data[15] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.data[4]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.data[8]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24, self.data[12]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.data[1]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.data[5]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.data[9]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.data[13]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.data[2]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.data[6]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.data[10]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.data[14]);
        return storeRMDouble_s88564b97_1(self, dest, _p2 + _ps, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeRMDouble}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRMDouble_s88564b97_1(Float4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.data[3]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.data[7]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.data[11]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 24, self.data[15]);
        return dest;
    }

    public Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[4] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[8] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[12] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.data[1] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[9] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[13] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.data[2] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[6] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[10] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.data[14] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        return loadRMDouble_sdb90a770_1(self, src, _p2 + _ps, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadRMDouble}, split to fit the inline budget; reached only through it. */
    private Float4x4 loadRMDouble_sdb90a770_1(Float4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.data[3] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
        self.data[7] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        self.data[11] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        self.data[15] = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
}
