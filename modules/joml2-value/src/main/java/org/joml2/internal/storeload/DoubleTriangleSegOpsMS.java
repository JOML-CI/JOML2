// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleTriangleSegOpsMS extends DoubleTriangleSegOps {
    public MemorySegment store(DoubleTriangle self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.v0X());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.v0Y());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.v0Z());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.v1X());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.v1Y());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.v1Z());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.v2X());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.v2Y());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.v2Z());
        return dest;
    }

    public DoubleTriangle load(long offset, MemorySegment src) {
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
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    public MemorySegment storeFloat(DoubleTriangle self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.v0X());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.v0Y());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.v0Z());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.v1X());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.v1Y());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.v1Z());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.v2X());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.v2Y());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.v2Z());
        return dest;
    }

    public DoubleTriangle loadFloat(long offset, MemorySegment src) {
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
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
