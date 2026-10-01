// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class FloatRigidSegOpsMS extends FloatRigidSegOps {
    public MemorySegment store(FloatRigidImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.tX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.tY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.tZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.rX);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.rY);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.rZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.rW);
        return dest;
    }

    public FloatRigid load(FloatRigidImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
        self.tX = src.get(_JAVA_FLOAT_UNALIGNED, offset);
        self.tY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
        self.tZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
        self.rX = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
        self.rY = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
        self.rZ = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
        self.rW = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
        return self;
    }

    public MemorySegment storeDouble(FloatRigidImpl self, long offset, MemorySegment dest) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.tX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.tY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.tZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.rY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.rZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.rW);
        return dest;
    }

    public FloatRigid loadDouble(FloatRigidImpl self, long offset, MemorySegment src) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
        self.tX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
        self.tY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
        self.tZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
        self.rX = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
        self.rY = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
        self.rZ = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
        self.rW = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
        return self;
    }
}
