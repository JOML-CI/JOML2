// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleQuatBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleQuat self, int index, DoubleBuffer buf);
    public abstract DoubleQuat loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleQuat self, int index, ByteBuffer buf);
    public abstract DoubleQuat loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleQuat self, int index, FloatBuffer buf);
    public abstract DoubleQuat loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleQuat self, int index, ByteBuffer buf);
    public abstract DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf);
}
