// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Short2SegOpsMS extends Short2SegOps {
    public MemorySegment store(Short2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset, self.x);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.y);
        return dest;
    }
    public Short2 load(Short2Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset);
        self.y = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        return self;
    }
    public MemorySegment storeByte(Short2Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset, (byte) self.x);
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, (byte) self.y);
        return dest;
    }
    public Short2 loadByte(Short2Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_BYTE, offset);
        self.y = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        return self;
    }
}
