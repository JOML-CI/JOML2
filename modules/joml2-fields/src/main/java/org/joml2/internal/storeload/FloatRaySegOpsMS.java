// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatRaySegOpsMS extends FloatRaySegOps {
    public MemorySegment store(FloatRayImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.oX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.oY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.oZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.dX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.dY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.dZ);
        return dest;
    }

    public FloatRay load(FloatRayImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.oX = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.oY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.oZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.dX = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.dY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.dZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        return self;
    }

    public MemorySegment storeDouble(FloatRayImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.oX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.oY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.oZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.dX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dZ);
        return dest;
    }

    public FloatRay loadDouble(FloatRayImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.oX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.oY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.oZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.dX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.dY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.dZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return self;
    }
}
