// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatDualQuatBbOps {
    public abstract FloatBuffer storeAbsolute(FloatDualQuat self, int index, FloatBuffer buf);
    public abstract FloatDualQuat loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatDualQuat self, int index, ByteBuffer buf);
    public abstract FloatDualQuat loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatDualQuat self, int index, DoubleBuffer buf);
    public abstract FloatDualQuat loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatDualQuat self, int index, ByteBuffer buf);
    public abstract FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf);
}
