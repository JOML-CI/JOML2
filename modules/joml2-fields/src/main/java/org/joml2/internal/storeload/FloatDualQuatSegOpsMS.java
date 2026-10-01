// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatDualQuatSegOpsMS extends FloatDualQuatSegOps {
    public MemorySegment store(FloatDualQuatImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.rX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.rY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.rZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.rW);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.dX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.dY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.dZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.dW);
        return dest;
    }

    public FloatDualQuat load(FloatDualQuatImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.rX = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.rY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.rZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.rW = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.dX = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.dY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.dZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.dW = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }

    public MemorySegment storeDouble(FloatDualQuatImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.rX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.rY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.rZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rW);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.dZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.dW);
        return dest;
    }

    public FloatDualQuat loadDouble(FloatDualQuatImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.rX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.rY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.rZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.rW = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.dX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.dY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.dZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.dW = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
}
