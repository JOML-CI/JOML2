// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatOBBSegOpsMS implements FloatOBBSegOps {
    public MemorySegment store(FloatOBB self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.cX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.cY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.cZ());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.uXx());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.uXy());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.uXz());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.uYx());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.uYy());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.uYz());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.uZx());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.uZy());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, self.uZz());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, self.hsX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, self.hsY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, self.hsZ());
        return dest;
    }
    public FloatOBB load(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        float _c9 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        float _c10 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        float _c11 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        float _c12 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L);
        float _c13 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L);
        float _c14 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public MemorySegment storeDouble(FloatOBB self, long offset, MemorySegment dest) {
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
    public FloatOBB loadDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        float _c9 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        float _c10 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        float _c11 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        float _c12 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L);
        float _c13 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L);
        float _c14 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
