// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Short2SegOpsMS implements Short2SegOps {
    public MemorySegment store(Short2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.y());
        return dest;
    }
    public Short2 load(long offset, MemorySegment src) {
        short _c0 = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        short _c1 = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        return new Short2(_c0, _c1);
    }
    public MemorySegment storeByte(Short2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, (byte) self.x());
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, (byte) self.y());
        return dest;
    }
    public Short2 loadByte(long offset, MemorySegment src) {
        short _c0 = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        short _c1 = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        return new Short2(_c0, _c1);
    }
}
