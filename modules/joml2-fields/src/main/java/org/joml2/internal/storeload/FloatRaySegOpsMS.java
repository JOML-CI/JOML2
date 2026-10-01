// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatRaySegOpsMS implements FloatRaySegOps {
    public MemorySegment store(FloatRayImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.oX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.oY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.oZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.dX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.dY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.dZ);
        return dest;
    }
    public FloatRay load(FloatRayImpl self, long offset, MemorySegment src) {
        self.oX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.oY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.oZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.dX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.dY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.dZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return self;
    }
    public MemorySegment storeDouble(FloatRayImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.oX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.oY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.oZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.dX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dZ);
        return dest;
    }
    public FloatRay loadDouble(FloatRayImpl self, long offset, MemorySegment src) {
        self.oX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.oY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.oZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.dX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.dY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.dZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return self;
    }
}
