// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Int3SegOpsMS implements Int3SegOps {
    public MemorySegment store(Int3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, self.y());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L, self.z());
        return dest;
    }
    public Int3 load(long offset, MemorySegment src) {
        int _c0 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        int _c1 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        int _c2 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L);
        return new Int3(_c0, _c1, _c2);
    }
    public MemorySegment storeLong(Int3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.y());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L, self.z());
        return dest;
    }
    public Int3 loadLong(long offset, MemorySegment src) {
        int _c0 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        int _c1 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        int _c2 = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L);
        return new Int3(_c0, _c1, _c2);
    }
}
