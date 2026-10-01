// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Float3SegOpsMS implements Float3SegOps {
    public MemorySegment store(Float3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.y());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.z());
        return dest;
    }
    public Float3 load(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        return new Float3(_c0, _c1, _c2);
    }
    public MemorySegment storeDouble(Float3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z());
        return dest;
    }
    public Float3 loadDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        return new Float3(_c0, _c1, _c2);
    }
}
