// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatPlaneSegOpsMS implements FloatPlaneSegOps {
    public MemorySegment store(FloatPlaneImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.a);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.b);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.c);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.d);
        return dest;
    }
    public FloatPlane load(FloatPlaneImpl self, long offset, MemorySegment src) {
        self.a = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.b = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.c = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.d = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return self;
    }
    public MemorySegment storeDouble(FloatPlaneImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.a);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.b);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.c);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.d);
        return dest;
    }
    public FloatPlane loadDouble(FloatPlaneImpl self, long offset, MemorySegment src) {
        self.a = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.b = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.c = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.d = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return self;
    }
}
