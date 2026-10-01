// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class Byte3SegOpsMS implements Byte3SegOps {
    public MemorySegment store(Byte3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_BYTE, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_BYTE, offset + 1L, self.y);
        dest.set(ValueLayout.JAVA_BYTE, offset + 2L, self.z);
        return dest;
    }
    public Byte3 load(Byte3Impl self, long offset, MemorySegment src) {
        self.x = src.get(ValueLayout.JAVA_BYTE, offset + 0L);
        self.y = src.get(ValueLayout.JAVA_BYTE, offset + 1L);
        self.z = src.get(ValueLayout.JAVA_BYTE, offset + 2L);
        return self;
    }
    public MemorySegment storeShort(Byte3Impl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L, self.x);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L, self.y);
        dest.set(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L, self.z);
        return dest;
    }
    public Byte3 loadShort(Byte3Impl self, long offset, MemorySegment src) {
        self.x = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 0L);
        self.y = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 2L);
        self.z = (byte) src.get(ValueLayout.JAVA_SHORT_UNALIGNED, offset + 4L);
        return self;
    }
}
