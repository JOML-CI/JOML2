// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleOBBSegOpsMS implements DoubleOBBSegOps {
    public MemorySegment store(DoubleOBBImpl self, long offset, MemorySegment dest) {
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
    public DoubleOBB load(DoubleOBBImpl self, long offset, MemorySegment src) {
        self.cX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.cY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.cZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.uXx = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.uXy = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.uXz = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.uYx = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.uYy = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.uYz = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.uZx = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.uZy = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.uZz = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.hsX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 96L);
        self.hsY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 104L);
        self.hsZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 112L);
        return self;
    }
    public MemorySegment storeFloat(DoubleOBBImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.cX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.cY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.cZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.uXx);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.uXy);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.uXz);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.uYx);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.uYy);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.uYz);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.uZx);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 40L, (float) self.uZy);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 44L, (float) self.uZz);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 48L, (float) self.hsX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 52L, (float) self.hsY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 56L, (float) self.hsZ);
        return dest;
    }
    public DoubleOBB loadFloat(DoubleOBBImpl self, long offset, MemorySegment src) {
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
}
