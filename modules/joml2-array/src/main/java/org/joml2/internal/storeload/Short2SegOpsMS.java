// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Short2SegOpsMS implements Short2SegOps {
    public MemorySegment store(Short2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.data[1]);
        return dest;
    }
    public Short2 load(Short2Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        return self;
    }
    public MemorySegment storeByte(Short2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, (byte) self.data[0]);
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, (byte) self.data[1]);
        return dest;
    }
    public Short2 loadByte(Short2Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        return self;
    }
}
