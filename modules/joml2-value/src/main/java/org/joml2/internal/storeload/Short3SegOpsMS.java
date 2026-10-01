// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Short3SegOpsMS implements Short3SegOps {
    public MemorySegment store(Short3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.y());
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L, self.z());
        return dest;
    }
    public Short3 load(long offset, MemorySegment src) {
        short _c0 = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        short _c1 = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        short _c2 = src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L);
        return new Short3(_c0, _c1, _c2);
    }
    public MemorySegment storeByte(Short3 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, (byte) self.x());
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, (byte) self.y());
        dest.set(ValueLayout.JAVA_BYTE, offset + 2L, (byte) self.z());
        return dest;
    }
    public Short3 loadByte(long offset, MemorySegment src) {
        short _c0 = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        short _c1 = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        short _c2 = src.get(ValueLayout.JAVA_BYTE, offset + 2L);
        return new Short3(_c0, _c1, _c2);
    }
}
