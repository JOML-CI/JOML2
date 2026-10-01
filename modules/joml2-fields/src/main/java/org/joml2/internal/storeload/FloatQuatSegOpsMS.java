// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatQuatSegOpsMS implements FloatQuatSegOps {
    public MemorySegment store(FloatQuatImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.y);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.z);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.w);
        return dest;
    }
    public FloatQuat load(FloatQuatImpl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.z = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.w = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return self;
    }
    public MemorySegment storeDouble(FloatQuatImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.w);
        return dest;
    }
    public FloatQuat loadDouble(FloatQuatImpl self, long offset, MemorySegment src) {
        self.x = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.y = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.z = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.w = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return self;
    }
}
