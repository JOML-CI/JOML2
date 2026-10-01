// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatTransformSegOpsMS implements FloatTransformSegOps {
    public MemorySegment store(FloatTransformImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.tX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.tY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.tZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.rX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.rY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.rZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.rW);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.sX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.sY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L, self.sZ);
        return dest;
    }
    public FloatTransform load(FloatTransformImpl self, long offset, MemorySegment src) {
        self.tX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.tY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.tZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.rX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.rY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.rZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.rW = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.sX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        self.sY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        self.sZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 36L);
        return self;
    }
    public MemorySegment storeDouble(FloatTransformImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.tX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.tY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.tZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.rY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.rZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.rW);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.sX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.sY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L, self.sZ);
        return dest;
    }
    public FloatTransform loadDouble(FloatTransformImpl self, long offset, MemorySegment src) {
        self.tX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.tY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.tZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.rX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.rY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.rZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.rW = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.sX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        self.sY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        self.sZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 72L);
        return self;
    }
}
