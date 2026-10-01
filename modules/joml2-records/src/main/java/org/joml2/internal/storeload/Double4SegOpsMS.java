// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double4SegOpsMS implements Double4SegOps {
    public MemorySegment store(Double4 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.w());
        return dest;
    }
    public Double4 load(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return new Double4(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeFloat(Double4 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.x());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.y());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.z());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.w());
        return dest;
    }
    public Double4 loadFloat(long offset, MemorySegment src) {
        double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return new Double4(_c0, _c1, _c2, _c3);
    }
}
