// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleSphereSegOpsMS implements DoubleSphereSegOps {
    public MemorySegment store(DoubleSphereImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.r);
        return dest;
    }
    public DoubleSphere load(DoubleSphereImpl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.z = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.r = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return self;
    }
    public MemorySegment storeFloat(DoubleSphereImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.x);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.y);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.z);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.r);
        return dest;
    }
    public DoubleSphere loadFloat(DoubleSphereImpl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.z = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.r = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        return self;
    }
}
