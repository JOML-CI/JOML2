// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatQuatSegOpsMS implements FloatQuatSegOps {
    public MemorySegment storeDouble(FloatQuatImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.data[1]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.data[2]);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.data[3]);
        return dest;
    }
    public FloatQuat loadDouble(FloatQuatImpl self, long offset, MemorySegment src) {
        self.data[0] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.data[1] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.data[2] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.data[3] = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        return self;
    }
}
