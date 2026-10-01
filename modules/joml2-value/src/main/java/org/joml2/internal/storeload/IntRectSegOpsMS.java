// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class IntRectSegOpsMS implements IntRectSegOps {
    public MemorySegment store(IntRect self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, self.minX());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, self.minY());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L, self.maxX());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L, self.maxY());
        return dest;
    }
    public IntRect load(long offset, MemorySegment src) {
        int _c0 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        int _c1 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        int _c2 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L);
        int _c3 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeLong(IntRect self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.minX());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.minY());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L, self.maxX());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 24L, self.maxY());
        return dest;
    }
    public IntRect loadLong(long offset, MemorySegment src) {
        int _c0 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        int _c1 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        int _c2 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L);
        int _c3 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
}
