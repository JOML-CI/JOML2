// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatOBBSegOpsMS extends FloatOBBSegOps {
    public MemorySegment store(FloatOBBImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.cX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.cY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.cZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.uXx);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.uXy);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.uXz);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.uYx);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.uYy);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.uYz);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.uZx);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.uZy);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, self.uZz);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, self.hsX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, self.hsY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, self.hsZ);
        return dest;
    }

    public FloatOBB load(FloatOBBImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.cX = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.cY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.cZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.uXx = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.uXy = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.uXz = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.uYx = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.uYy = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.uYz = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.uZx = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
        self.uZy = src.get(_JAVA_FLOAT_UNALIGNED, offset + 40L);
        self.uZz = src.get(_JAVA_FLOAT_UNALIGNED, offset + 44L);
        self.hsX = src.get(_JAVA_FLOAT_UNALIGNED, offset + 48L);
        self.hsY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 52L);
        self.hsZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 56L);
        return self;
    }

    public MemorySegment storeDouble(FloatOBBImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.cX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.cY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.cZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.uXx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.uXy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.uXz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.uYx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.uYy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.uYz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.uZx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.uZy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, self.uZz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, self.hsX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, self.hsY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, self.hsZ);
        return dest;
    }

    public FloatOBB loadDouble(FloatOBBImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.cX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.cY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.cZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.uXx = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.uXy = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.uXz = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.uYx = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.uYy = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.uYz = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.uZx = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
        self.uZy = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
        self.uZz = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
        self.hsX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 96L);
        self.hsY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 104L);
        self.hsZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 112L);
        return self;
    }
}
