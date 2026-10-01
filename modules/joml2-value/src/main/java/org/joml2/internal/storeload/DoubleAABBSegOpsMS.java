// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleAABBSegOpsMS extends DoubleAABBSegOps {
    public MemorySegment store(DoubleAABB self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.minX());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.minY());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.minZ());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.maxX());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.maxY());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.maxZ());
        return dest;
    }

    public DoubleAABB load(long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public MemorySegment storeFloat(DoubleAABB self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.minX());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.minY());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.minZ());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.maxX());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.maxY());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.maxZ());
        return dest;
    }

    public DoubleAABB loadFloat(long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
