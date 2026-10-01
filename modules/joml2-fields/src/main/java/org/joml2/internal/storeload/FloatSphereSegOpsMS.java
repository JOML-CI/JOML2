// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatSphereSegOpsMS implements FloatSphereSegOps {
    public MemorySegment store(FloatSphereImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.y);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.z);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.r);
        return dest;
    }
    public FloatSphere load(FloatSphereImpl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.z = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.r = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return self;
    }
    public MemorySegment storeDouble(FloatSphereImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.r);
        return dest;
    }
    public FloatSphere loadDouble(FloatSphereImpl self, long offset, MemorySegment src) {
        self.x = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.y = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.z = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.r = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return self;
    }
}
