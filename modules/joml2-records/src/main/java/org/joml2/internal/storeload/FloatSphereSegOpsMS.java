// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatSphereSegOpsMS implements FloatSphereSegOps {
    public MemorySegment store(FloatSphere self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.y());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.z());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.r());
        return dest;
    }
    public FloatSphere load(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeDouble(FloatSphere self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.r());
        return dest;
    }
    public FloatSphere loadDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }
}
