// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleTransformSegOpsMS extends DoubleTransformSegOps {
    public MemorySegment store(DoubleTransform self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.tX());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.tY());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.tZ());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rX());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.rY());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.rZ());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.rW());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.sX());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.sY());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.sZ());
        return dest;
    }

    public DoubleTransform load(long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        double _c6 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        double _c7 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        double _c8 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        double _c9 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }

    public MemorySegment storeFloat(DoubleTransform self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.tX());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.tY());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.tZ());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.rX());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.rY());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.rZ());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.rW());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.sX());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.sY());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.sZ());
        return dest;
    }

    public DoubleTransform loadFloat(long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        double _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        double _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        double _c8 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        double _c9 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
}
