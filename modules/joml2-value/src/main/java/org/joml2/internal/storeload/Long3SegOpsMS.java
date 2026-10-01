// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Long3SegOpsMS implements Long3SegOps {
    public MemorySegment store(Long3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.y());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L, self.z());
        return dest;
    }
    public Long3 load(long offset, MemorySegment src) {
        long _c0 = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        long _c1 = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        long _c2 = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L);
        return new Long3(_c0, _c1, _c2);
    }
    public MemorySegment storeInt(Long3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, (int) self.x());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, (int) self.y());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L, (int) self.z());
        return dest;
    }
    public Long3 loadInt(long offset, MemorySegment src) {
        long _c0 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        long _c1 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        long _c2 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L);
        return new Long3(_c0, _c1, _c2);
    }
}
