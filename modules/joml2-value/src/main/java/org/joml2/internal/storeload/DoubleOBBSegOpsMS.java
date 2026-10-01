// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleOBBSegOpsMS implements DoubleOBBSegOps {
    public MemorySegment store(DoubleOBB self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.cX());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.cY());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.cZ());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.uXx());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.uXy());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.uXz());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.uYx());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.uYy());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.uYz());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.uZx());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.uZy());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.uZz());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, self.hsX());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, self.hsY());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, self.hsZ());
        return dest;
    }
    public DoubleOBB load(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        double _c6 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        double _c7 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        double _c8 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        double _c9 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        double _c10 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        double _c11 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        double _c12 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L);
        double _c13 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L);
        double _c14 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public MemorySegment storeFloat(DoubleOBB self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.cX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.cY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.cZ());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.uXx());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.uXy());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.uXz());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.uYx());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.uYy());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.uYz());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.uZx());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.uZy());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.uZz());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) self.hsX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) self.hsY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) self.hsZ());
        return dest;
    }
    public DoubleOBB loadFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        double _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        double _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        double _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        double _c9 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        double _c10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        double _c11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        double _c12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L);
        double _c13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L);
        double _c14 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
