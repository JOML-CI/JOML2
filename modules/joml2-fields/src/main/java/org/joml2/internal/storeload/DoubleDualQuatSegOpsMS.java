// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleDualQuatSegOpsMS extends DoubleDualQuatSegOps {
    public MemorySegment store(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
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

    public DoubleDualQuat load(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.rX = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.rY = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.rZ = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.rW = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.dX = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.dY = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.dZ = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.dW = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }

    public MemorySegment storeFloat(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.rX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.rY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.rZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.rW);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.dX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.dY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.dZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.dW);
        return dest;
    }

    public DoubleDualQuat loadFloat(DoubleDualQuatImpl self, long offset, MemorySegment src) {
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
}
