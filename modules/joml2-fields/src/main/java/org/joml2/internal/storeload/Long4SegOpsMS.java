// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Long4SegOpsMS implements Long4SegOps {
    public MemorySegment store(Long4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L, self.y);
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L, self.z);
        dest.set(ValueLayout.JAVA_LONG_UNALIGNED, offset + 24L, self.w);
        return dest;
    }
    public Long4 load(Long4Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 8L);
        self.z = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 16L);
        self.w = src.get(ValueLayout.JAVA_LONG_UNALIGNED, offset + 24L);
        return self;
    }
    public MemorySegment storeInt(Long4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, (int) self.x);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, (int) self.y);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L, (int) self.z);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L, (int) self.w);
        return dest;
    }
    public Long4 loadInt(Long4Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        self.z = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L);
        self.w = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L);
        return self;
    }
}
