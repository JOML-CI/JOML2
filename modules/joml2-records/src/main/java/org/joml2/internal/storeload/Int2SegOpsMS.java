// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Int2SegOpsMS implements Int2SegOps {
    public MemorySegment store(Int2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, self.y());
        return dest;
    }
    public Int2 load(long offset, MemorySegment src) {
        int _c0 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        int _c1 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        return new Int2(_c0, _c1);
    }
    public MemorySegment storeLong(Int2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.y());
        return dest;
    }
    public Int2 loadLong(long offset, MemorySegment src) {
        int _c0 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        int _c1 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        return new Int2(_c0, _c1);
    }
}
