// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatAABBSegOpsMS extends FloatAABBSegOps {
    public MemorySegment store(FloatAABBImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.minX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.minY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.minZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.maxX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.maxY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.maxZ);
        return dest;
    }

    public FloatAABB load(FloatAABBImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.minX = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.minY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.minZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.maxX = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.maxY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.maxZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        return self;
    }

    public MemorySegment storeDouble(FloatAABBImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.minX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.minY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.minZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.maxX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.maxY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.maxZ);
        return dest;
    }

    public FloatAABB loadDouble(FloatAABBImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.minX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.minY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.minZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.maxX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.maxY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.maxZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return self;
    }
}
