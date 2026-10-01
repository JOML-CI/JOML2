// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleAABBSegOpsMS implements DoubleAABBSegOps {
    public MemorySegment store(DoubleAABBImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.minX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.minY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.minZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.maxX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.maxY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.maxZ);
        return dest;
    }
    public DoubleAABB load(DoubleAABBImpl self, long offset, MemorySegment src) {
        self.minX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.minY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.minZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.maxX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.maxY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.maxZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return self;
    }
    public MemorySegment storeFloat(DoubleAABBImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.minX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.minY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.minZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.maxX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.maxY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.maxZ);
        return dest;
    }
    public DoubleAABB loadFloat(DoubleAABBImpl self, long offset, MemorySegment src) {
        self.minX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.minY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.minZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.maxX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.maxY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.maxZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return self;
    }
}
