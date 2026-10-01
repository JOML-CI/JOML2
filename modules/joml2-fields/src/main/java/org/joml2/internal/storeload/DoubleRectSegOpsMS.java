// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleRectSegOpsMS implements DoubleRectSegOps {
    public MemorySegment store(DoubleRectImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.minX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.minY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.maxX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.maxY);
        return dest;
    }
    public DoubleRect load(DoubleRectImpl self, long offset, MemorySegment src) {
        self.minX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.minY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.maxX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.maxY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return self;
    }
    public MemorySegment storeFloat(DoubleRectImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.minX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.minY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.maxX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.maxY);
        return dest;
    }
    public DoubleRect loadFloat(DoubleRectImpl self, long offset, MemorySegment src) {
        self.minX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.minY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.maxX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.maxY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return self;
    }
}
