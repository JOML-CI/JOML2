// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Int3SegOpsMS implements Int3SegOps {
    public MemorySegment store(Int3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, self.y);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L, self.z);
        return dest;
    }
    public Int3 load(Int3Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        self.z = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L);
        return self;
    }
    public MemorySegment storeLong(Int3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.y);
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L, self.z);
        return dest;
    }
    public Int3 loadLong(Int3Impl self, long offset, MemorySegment src) {
        self.x = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        self.y = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        self.z = (int) src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L);
        return self;
    }
}
