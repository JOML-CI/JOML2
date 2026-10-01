// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Byte4SegOpsMS implements Byte4SegOps {
    public MemorySegment store(Byte4 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, self.y());
        dest.set(ValueLayout.JAVA_BYTE, offset + 2L, self.z());
        dest.set(ValueLayout.JAVA_BYTE, offset + 3L, self.w());
        return dest;
    }
    public Byte4 load(long offset, MemorySegment src) {
        byte _c0 = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        byte _c1 = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        byte _c2 = src.get(ValueLayout.JAVA_BYTE, offset + 2L);
        byte _c3 = src.get(ValueLayout.JAVA_BYTE, offset + 3L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeShort(Byte4 self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.x());
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.y());
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L, self.z());
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 6L, self.w());
        return dest;
    }
    public Byte4 loadShort(long offset, MemorySegment src) {
        byte _c0 = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        byte _c1 = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        byte _c2 = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L);
        byte _c3 = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 6L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
}
