// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double4x4SegOpsMS extends Double4x4SegOps {
    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.data[3]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[5]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[6]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.data[7]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[8]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.data[9]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.data[10]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.data[11]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, (float) self.data[12]);
        return storeCMFloat_s618e10a_1(self, offset, dest, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code storeCMFloat}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeCMFloat_s618e10a_1(Double4x4Impl self, long offset, MemorySegment dest, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, (float) self.data[13]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, (float) self.data[14]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, (float) self.data[15]);
        return dest;
    }

    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.data[0] = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.data[1] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[2] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[3] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[4] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[6] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[7] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.data[8] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.data[9] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.data[10] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.data[11] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.data[12] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 48L);
        return loadCMFloat_sfd8b3647_1(self, offset, src, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadCMFloat}, split to fit the inline budget; reached only through it. */
    private Double4x4 loadCMFloat_sfd8b3647_1(Double4x4Impl self, long offset, MemorySegment src, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.data[13] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 52L);
        self.data[14] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 56L);
        self.data[15] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest) {
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
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, self.data[7]);
        return storeRM_s6031da9d_1(self, offset, dest, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeRM}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRM_s6031da9d_1(Double4x4Impl self, long offset, MemorySegment dest, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, self.data[11]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, self.data[15]);
        return dest;
    }

    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[4] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[8] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[12] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[1] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[9] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[13] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.data[2] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.data[6] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.data[10] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.data[14] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.data[3] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 96L);
        self.data[7] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 104L);
        return loadRM_sca19f488_1(self, offset, src, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadRM}, split to fit the inline budget; reached only through it. */
    private Double4x4 loadRM_sca19f488_1(Double4x4Impl self, long offset, MemorySegment src, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.data[11] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 112L);
        self.data[15] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[8]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.data[12]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[5]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.data[9]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.data[13]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.data[6]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.data[10]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.data[14]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, (float) self.data[3]);
        return storeRMFloat_sc97dbc11_1(self, offset, dest, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code storeRMFloat}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRMFloat_sc97dbc11_1(Double4x4Impl self, long offset, MemorySegment dest, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, (float) self.data[7]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, (float) self.data[11]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, (float) self.data[15]);
        return dest;
    }

    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src) {
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
        return loadRMFloat_s652ed1b2_1(self, offset, src, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadRMFloat}, split to fit the inline budget; reached only through it. */
    private Double4x4 loadRMFloat_s652ed1b2_1(Double4x4Impl self, long offset, MemorySegment src, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.data[7] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 52L);
        self.data[11] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 56L);
        self.data[15] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.data[12]);
        return storeCM_s8ad888d2_1(self, dest, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeCM}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeCM_s8ad888d2_1(Double4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.data[13]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.data[14]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 24, self.data[15]);
        return dest;
    }

    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[2] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[3] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.data[4] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[6] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[7] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.data[8] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[9] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[10] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.data[11] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        self.data[12] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
        return loadCM_s1a92a8c9_1(self, src, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadCM}, split to fit the inline budget; reached only through it. */
    private Double4x4 loadCM_s1a92a8c9_1(Double4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.data[13] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        self.data[14] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        self.data[15] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.data[3]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, (float) self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.data[5]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.data[6]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.data[7]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, (float) self.data[8]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.data[9]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.data[10]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 12, (float) self.data[11]);
        return storeCMFloat_s3158dce8_1(self, dest, _p2 + _ps, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code storeCMFloat}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeCMFloat_s3158dce8_1(Double4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3, (float) self.data[12]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.data[13]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 8, (float) self.data[14]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 12, (float) self.data[15]);
        return dest;
    }

    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
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
        return loadCMFloat_s52e5cb81_1(self, src, _p2 + _ps, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadCMFloat}, split to fit the inline budget; reached only through it. */
    private Double4x4 loadCMFloat_s52e5cb81_1(Double4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.data[12] = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
        self.data[13] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
        self.data[14] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 8);
        self.data[15] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.data[3]);
        return storeRM_sde54ae73_1(self, dest, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code storeRM}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRM_sde54ae73_1(Double4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.data[7]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.data[11]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 24, self.data[15]);
        return dest;
    }

    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 8L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[4] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
        self.data[8] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
        self.data[12] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
        self.data[1] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
        self.data[5] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
        self.data[9] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
        self.data[13] = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
        self.data[2] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
        self.data[6] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
        self.data[10] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
        self.data[14] = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 24);
        self.data[3] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
        return loadRM_sd48f2f72_1(self, src, _p3, _JAVA_DOUBLE_UNALIGNED);
    }

    /** Piece 2 of {@code loadRM}, split to fit the inline budget; reached only through it. */
    private Double4x4 loadRM_sd48f2f72_1(Double4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED) {
        self.data[7] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
        self.data[11] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 16);
        self.data[15] = src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.data[8]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.data[12]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1, (float) self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.data[5]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.data[9]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.data[13]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2, (float) self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.data[6]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, (float) self.data[10]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 12, (float) self.data[14]);
        return storeRMFloat_s28c894a7_1(self, dest, _p2 + _ps, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code storeRMFloat}, split to fit the inline budget; reached only through it. */
    private MemorySegment storeRMFloat_s28c894a7_1(Double4x4Impl self, MemorySegment dest, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3, (float) self.data[3]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.data[7]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 8, (float) self.data[11]);
        dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 12, (float) self.data[15]);
        return dest;
    }

    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        long _ps = stride * 4L;
        long _p1 = offset + _ps;
        long _p2 = _p1 + _ps;
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
        return loadRMFloat_s49c1350_1(self, src, _p2 + _ps, _JAVA_FLOAT_UNALIGNED);
    }

    /** Piece 2 of {@code loadRMFloat}, split to fit the inline budget; reached only through it. */
    private Double4x4 loadRMFloat_s49c1350_1(Double4x4Impl self, MemorySegment src, long _p3, java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED) {
        self.data[3] = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
        self.data[7] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
        self.data[11] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 8);
        self.data[15] = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest) {
        MemorySegment.copy(self.data, 0, dest, ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, 16);
        return dest;
    }

    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src) {
        MemorySegment.copy(src, ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.data, 0, 16);
        self.properties = self.determineProperties();
        return self;
    }
}
