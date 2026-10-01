// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatTriangleSegOpsMS implements FloatTriangleSegOps {
    public MemorySegment store(FloatTriangleImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.v0X);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.v0Y);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.v0Z);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.v1X);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.v1Y);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.v1Z);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.v2X);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.v2Y);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.v2Z);
        return dest;
    }
    public FloatTriangle load(FloatTriangleImpl self, long offset, MemorySegment src) {
        self.v0X = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.v0Y = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.v0Z = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.v1X = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.v1Y = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.v1Z = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.v2X = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.v2Y = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.v2Z = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        return self;
    }
    public MemorySegment storeDouble(FloatTriangleImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.v0X);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.v0Y);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.v0Z);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.v1X);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.v1Y);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.v1Z);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.v2X);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.v2Y);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.v2Z);
        return dest;
    }
    public FloatTriangle loadDouble(FloatTriangleImpl self, long offset, MemorySegment src) {
        self.v0X = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.v0Y = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.v0Z = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.v1X = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.v1Y = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.v1Z = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.v2X = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.v2Y = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.v2Z = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        return self;
    }
}
