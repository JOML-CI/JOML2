// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleDualQuatSegOpsMS extends DoubleDualQuatSegOps {
    public MemorySegment store(DoubleDualQuat self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.rX());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.rY());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.rZ());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rW());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dX());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dY());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.dZ());
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.dW());
        return dest;
    }

    public DoubleDualQuat load(long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        double _c6 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        double _c7 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public MemorySegment storeFloat(DoubleDualQuat self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.rX());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.rY());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.rZ());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.rW());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.dX());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.dY());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.dZ());
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.dW());
        return dest;
    }

    public DoubleDualQuat loadFloat(long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        double _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        double _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
