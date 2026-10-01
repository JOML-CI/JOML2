// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatQuatBbOps {
    public abstract FloatBuffer storeAbsolute(FloatQuat self, int index, FloatBuffer buf);
    public abstract FloatQuat loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatQuat self, int index, ByteBuffer buf);
    public abstract FloatQuat loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatQuat self, int index, DoubleBuffer buf);
    public abstract FloatQuat loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatQuat self, int index, ByteBuffer buf);
    public abstract FloatQuat loadDoubleAbsolute(int index, ByteBuffer buf);
}
