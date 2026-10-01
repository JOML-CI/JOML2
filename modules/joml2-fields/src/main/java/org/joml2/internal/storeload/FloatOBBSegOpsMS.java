// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatOBBSegOpsMS implements FloatOBBSegOps {
    public MemorySegment store(FloatOBBImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.cX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.cY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.cZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.uXx);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.uXy);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.uXz);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.uYx);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.uYy);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.uYz);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.uZx);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, self.uZy);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, self.uZz);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, self.hsX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, self.hsY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, self.hsZ);
        return dest;
    }
    public FloatOBB load(FloatOBBImpl self, long offset, MemorySegment src) {
        self.cX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.cY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.cZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.uXx = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.uXy = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.uXz = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.uYx = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.uYy = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.uYz = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.uZx = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.uZy = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.uZz = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.hsX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L);
        self.hsY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L);
        self.hsZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeDouble(FloatOBBImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.cX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.cY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.cZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.uXx);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.uXy);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.uXz);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.uYx);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.uYy);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.uYz);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.uZx);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L, self.uZy);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L, self.uZz);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L, self.hsX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L, self.hsY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L, self.hsZ);
        return dest;
    }
    public FloatOBB loadDouble(FloatOBBImpl self, long offset, MemorySegment src) {
        self.cX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.cY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.cZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.uXx = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.uXy = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.uXz = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.uYx = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.uYy = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.uYz = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.uZx = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.uZy = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.uZz = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.hsX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L);
        self.hsY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L);
        self.hsZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L);
        return self;
    }
}
