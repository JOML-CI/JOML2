// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatRectSegOpsMS implements FloatRectSegOps {
    public MemorySegment store(FloatRectImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.minX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.minY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.maxX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.maxY);
        return dest;
    }
    public FloatRect load(FloatRectImpl self, long offset, MemorySegment src) {
        self.minX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.minY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.maxX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.maxY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return self;
    }
    public MemorySegment storeDouble(FloatRectImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.minX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.minY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.maxX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.maxY);
        return dest;
    }
    public FloatRect loadDouble(FloatRectImpl self, long offset, MemorySegment src) {
        self.minX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.minY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.maxX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.maxY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return self;
    }
}
