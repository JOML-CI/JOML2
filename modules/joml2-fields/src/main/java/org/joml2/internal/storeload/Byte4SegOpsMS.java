// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Byte4SegOpsMS implements Byte4SegOps {
    public MemorySegment store(Byte4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, self.y);
        dest.set(ValueLayout.JAVA_BYTE, offset + 2L, self.z);
        dest.set(ValueLayout.JAVA_BYTE, offset + 3L, self.w);
        return dest;
    }
    public Byte4 load(Byte4Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        self.z = src.get(ValueLayout.JAVA_BYTE, offset + 2L);
        self.w = src.get(ValueLayout.JAVA_BYTE, offset + 3L);
        return self;
    }
    public MemorySegment storeShort(Byte4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.y);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L, self.z);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 6L, self.w);
        return dest;
    }
    public Byte4 loadShort(Byte4Impl self, long offset, MemorySegment src) {
        self.x = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        self.y = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        self.z = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L);
        self.w = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 6L);
        return self;
    }
}
