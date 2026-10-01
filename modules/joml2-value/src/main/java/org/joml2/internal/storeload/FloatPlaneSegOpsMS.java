// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatPlaneSegOpsMS implements FloatPlaneSegOps {
    public MemorySegment store(FloatPlane self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.a());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.b());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.c());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.d());
        return dest;
    }
    public FloatPlane load(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeDouble(FloatPlane self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.a());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.b());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.c());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.d());
        return dest;
    }
    public FloatPlane loadDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
}
