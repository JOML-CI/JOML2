// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Long4SegOpsMS implements Long4SegOps {
    public MemorySegment storeInt(Long4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L, (int) self.data[0]);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L, (int) self.data[1]);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L, (int) self.data[2]);
        dest.set(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L, (int) self.data[3]);
        return dest;
    }
    public Long4 loadInt(Long4Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 4L);
        self.data[2] = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 8L);
        self.data[3] = src.get(ValueLayout.JAVA_INT_UNALIGNED, offset + 12L);
        return self;
    }
}
