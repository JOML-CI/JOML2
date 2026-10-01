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
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.data[0]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[3]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[4]);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        return dest;
    }

    public DoubleRay load(DoubleRayImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.data[0] = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.data[1] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[2] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[3] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[4] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        return self;
    }

    public MemorySegment storeFloat(DoubleRayImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.data[0]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[1]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.data[2]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.data[3]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.data[4]);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.data[5]);
        return dest;
    }

    public DoubleRay loadFloat(DoubleRayImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.data[0] = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.data[1] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[2] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[3] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[4] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        return self;
    }
}
