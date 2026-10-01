// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleTriangleSegOpsMS extends DoubleTriangleSegOps {
    public MemorySegment store(DoubleTriangleImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.v0X);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.v0Y);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.v0Z);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.v1X);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.v1Y);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.v1Z);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.v2X);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.v2Y);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.v2Z);
        return dest;
    }

    public DoubleTriangle load(DoubleTriangleImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.v0X = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.v0Y = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.v0Z = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.v1X = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.v1Y = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.v1Z = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.v2X = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.v2Y = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.v2Z = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
        return self;
    }

    public MemorySegment storeFloat(DoubleTriangleImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.v0X);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.v0Y);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.v0Z);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.v1X);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.v1Y);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.v1Z);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.v2X);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.v2Y);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.v2Z);
        return dest;
    }

    public DoubleTriangle loadFloat(DoubleTriangleImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.v0X = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.v0Y = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.v0Z = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.v1X = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.v1Y = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.v1Z = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.v2X = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.v2Y = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.v2Z = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
        return self;
    }
}
