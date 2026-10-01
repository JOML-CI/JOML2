// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Long2SegOpsMS implements Long2SegOps {
    public MemorySegment store(Long2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.y());
        return dest;
    }
    public Long2 load(long offset, MemorySegment src) {
        long _c0 = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        long _c1 = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        return new Long2(_c0, _c1);
    }
    public MemorySegment storeInt(Long2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, (int) self.x());
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, (int) self.y());
        return dest;
    }
    public Long2 loadInt(long offset, MemorySegment src) {
        long _c0 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        long _c1 = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        return new Long2(_c0, _c1);
    }
}
