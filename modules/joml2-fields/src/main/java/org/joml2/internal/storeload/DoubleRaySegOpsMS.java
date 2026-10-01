// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleRaySegOpsMS extends DoubleRaySegOps {
    public MemorySegment store(DoubleRayImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.oX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.oY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.oZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.dX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dZ);
        return dest;
    }

    public DoubleRay load(DoubleRayImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.oX = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.oY = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.oZ = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.dX = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.dY = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.dZ = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return self;
    }

    public MemorySegment storeFloat(DoubleRayImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.oX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.oY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.oZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.dX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.dY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.dZ);
        return dest;
    }

    public DoubleRay loadFloat(DoubleRayImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.oX = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.oY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.oZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.dX = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.dY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.dZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        return self;
    }
}
