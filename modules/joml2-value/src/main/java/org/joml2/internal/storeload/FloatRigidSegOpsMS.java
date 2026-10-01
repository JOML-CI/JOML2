// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatRigidSegOpsMS implements FloatRigidSegOps {
    public MemorySegment store(FloatRigid self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L, self.tX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, self.tY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, self.tZ());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, self.rX());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L, self.rY());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L, self.rZ());
        dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L, self.rW());
        return dest;
    }
    public FloatRigid load(long offset, MemorySegment src) {
        float _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 0L);
        float _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
        float _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
        float _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
        float _c4 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 16L);
        float _c5 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 20L);
        float _c6 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 24L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public MemorySegment storeDouble(FloatRigid self, long offset, MemorySegment dest) {
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L, self.tX());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.tY());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.tZ());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rX());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L, self.rY());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L, self.rZ());
        dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L, self.rW());
        return dest;
    }
    public FloatRigid loadDouble(long offset, MemorySegment src) {
        float _c0 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 0L);
        float _c1 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
        float _c2 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
        float _c3 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
        float _c4 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 32L);
        float _c5 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 40L);
        float _c6 = (float) src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 48L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
}
