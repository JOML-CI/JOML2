// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double2SegOpsMS implements Double2SegOps {
    public MemorySegment store(Double2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        return dest;
    }
    public Double2 load(Double2Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        return self;
    }
    public MemorySegment storeFloat(Double2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.data[0]);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.data[1]);
        return dest;
    }
    public Double2 loadFloat(Double2Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        return self;
    }
}
