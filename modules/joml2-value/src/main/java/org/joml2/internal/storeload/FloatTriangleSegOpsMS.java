// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatTriangleSegOpsMS implements FloatTriangleSegOps {
    public MemorySegment store(FloatTriangle self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.v0X());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.v0Y());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.v0Z());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.v1X());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.v1Y());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.v1Z());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.v2X());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L, self.v2Y());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L, self.v2Z());
        return dest;
    }
    public FloatTriangle load(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        float _c7 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 28L);
        float _c8 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 32L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public MemorySegment storeDouble(FloatTriangle self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.v0X());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.v0Y());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.v0Z());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.v1X());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.v1Y());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.v1Z());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.v2X());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L, self.v2Y());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L, self.v2Z());
        return dest;
    }
    public FloatTriangle loadDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        float _c7 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 56L);
        float _c8 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 64L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
