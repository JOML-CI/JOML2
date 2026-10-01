// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Double3SegOpsMS implements Double3SegOps {
    public MemorySegment store(Double3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z);
        return dest;
    }
    public Double3 load(Double3Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.z = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        return self;
    }
    public MemorySegment storeFloat(Double3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.x);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.y);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.z);
        return dest;
    }
    public Double3 loadFloat(Double3Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.z = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        return self;
    }
}
