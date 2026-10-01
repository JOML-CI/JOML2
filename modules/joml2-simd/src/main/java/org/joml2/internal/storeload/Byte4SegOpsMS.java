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
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, self.data[1]);
        dest.set(ValueLayout.JAVA_BYTE, offset + 2L, self.data[2]);
        dest.set(ValueLayout.JAVA_BYTE, offset + 3L, self.data[3]);
        return dest;
    }
    public Byte4 load(Byte4Impl self, long offset, MemorySegment src) {
        self.data[0] = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        self.data[1] = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        self.data[2] = src.get(ValueLayout.JAVA_BYTE, offset + 2L);
        self.data[3] = src.get(ValueLayout.JAVA_BYTE, offset + 3L);
        return self;
    }
    public MemorySegment storeShort(Byte4Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.data[0]);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.data[1]);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L, self.data[2]);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 6L, self.data[3]);
        return dest;
    }
    public Byte4 loadShort(Byte4Impl self, long offset, MemorySegment src) {
        self.data[0] = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        self.data[1] = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        self.data[2] = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L);
        self.data[3] = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 6L);
        return self;
    }
}
