// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Int4SegOpsMS implements Int4SegOps {
    public MemorySegment store(Int4 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, self.y());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L, self.z());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L, self.w());
        return dest;
    }
    public Int4 load(long offset, MemorySegment src) {
        int _c0 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        int _c1 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        int _c2 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L);
        int _c3 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeLong(Int4 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.y());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L, self.z());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 24L, self.w());
        return dest;
    }
    public Int4 loadLong(long offset, MemorySegment src) {
        int _c0 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        int _c1 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        int _c2 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L);
        int _c3 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
}
