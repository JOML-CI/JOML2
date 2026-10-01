// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleRaySegOpsMS implements DoubleRaySegOps {
    public MemorySegment store(DoubleRayImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.oX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.oY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.oZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.dX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dZ);
        return dest;
    }
    public DoubleRay load(DoubleRayImpl self, long offset, MemorySegment src) {
        self.oX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.oY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.oZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.dX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.dY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.dZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return self;
    }
    public MemorySegment storeFloat(DoubleRayImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.oX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.oY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.oZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.dX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.dY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.dZ);
        return dest;
    }
    public DoubleRay loadFloat(DoubleRayImpl self, long offset, MemorySegment src) {
        self.oX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.oY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.oZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.dX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.dY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.dZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        return self;
    }
}
