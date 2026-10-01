// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoubleRigidSegOpsMS implements DoubleRigidSegOps {
    public MemorySegment store(DoubleRigidImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.tX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.tY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.tZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.rY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.rZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.rW);
        return dest;
    }
    public DoubleRigid load(DoubleRigidImpl self, long offset, MemorySegment src) {
        self.tX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.tY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.tZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.rX = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.rY = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.rZ = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.rW = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        return self;
    }
    public MemorySegment storeFloat(DoubleRigidImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, (float) self.tX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.tY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.tZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.rX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.rY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.rZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.rW);
        return dest;
    }
    public DoubleRigid loadFloat(DoubleRigidImpl self, long offset, MemorySegment src) {
        self.tX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.tY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.tZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.rX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.rY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.rZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.rW = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        return self;
    }
}
