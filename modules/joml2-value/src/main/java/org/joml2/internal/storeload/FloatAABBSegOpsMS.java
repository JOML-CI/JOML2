// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatAABBSegOpsMS implements FloatAABBSegOps {
    public MemorySegment store(FloatAABB self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.minX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.minY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.minZ());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.maxX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.maxY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.maxZ());
        return dest;
    }
    public FloatAABB load(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public MemorySegment storeDouble(FloatAABB self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.minX());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.minY());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.minZ());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.maxX());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.maxY());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.maxZ());
        return dest;
    }
    public FloatAABB loadDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
