// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Byte2SegOpsMS implements Byte2SegOps {
    public MemorySegment store(Byte2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, self.y());
        return dest;
    }
    public Byte2 load(long offset, MemorySegment src) {
        byte _c0 = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        byte _c1 = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        return new Byte2(_c0, _c1);
    }
    public MemorySegment storeShort(Byte2 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.y());
        return dest;
    }
    public Byte2 loadShort(long offset, MemorySegment src) {
        byte _c0 = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        byte _c1 = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        return new Byte2(_c0, _c1);
    }
}
