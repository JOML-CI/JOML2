// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatRigidSegOpsMS implements FloatRigidSegOps {
    public MemorySegment store(FloatRigidImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.tX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.tY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.tZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.rX);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.rY);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.rZ);
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.rW);
        return dest;
    }
    public FloatRigid load(FloatRigidImpl self, long offset, MemorySegment src) {
        self.tX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        self.tY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.tZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.rX = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.rY = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.rZ = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.rW = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        return self;
    }
    public MemorySegment storeDouble(FloatRigidImpl self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.tX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.tY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.tZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rX);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.rY);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.rZ);
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.rW);
        return dest;
    }
    public FloatRigid loadDouble(FloatRigidImpl self, long offset, MemorySegment src) {
        self.tX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        self.tY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.tZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.rX = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.rY = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.rZ = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.rW = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        return self;
    }
}
