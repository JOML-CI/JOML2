// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleDualQuatSegOpsMS implements DoubleDualQuatSegOps {
    public MemorySegment store(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.rX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.rY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.rZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rW);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.dZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.dW);
        return dest;
    }
    public DoubleDualQuat load(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        self.rX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.rY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.rZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.rW = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.dX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.dY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.dZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.dW = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
    public MemorySegment storeFloat(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.rX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.rY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.rZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.rW);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.dX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.dY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.dZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.dW);
        return dest;
    }
    public DoubleDualQuat loadFloat(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        self.rX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.rY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.rZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.rW = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.dX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.dY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.dZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.dW = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }
}
