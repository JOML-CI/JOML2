// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatDualQuatSegOpsMS implements FloatDualQuatSegOps {
    public MemorySegment store(FloatDualQuatImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.data[1]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.data[2]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.data[3]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.data[4]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.data[5]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.data[6]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.data[7]);
        return dest;
    }
    public FloatDualQuat load(FloatDualQuatImpl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.data[2] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.data[3] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.data[4] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.data[5] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.data[6] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        self.data[7] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        return self;
    }
    public MemorySegment storeDouble(FloatDualQuatImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[3]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.data[4]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.data[5]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.data[6]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.data[7]);
        return dest;
    }
    public FloatDualQuat loadDouble(FloatDualQuatImpl self, long offset, MemorySegment src) {
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.data[4] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.data[5] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.data[6] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        self.data[7] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        return self;
    }
}
