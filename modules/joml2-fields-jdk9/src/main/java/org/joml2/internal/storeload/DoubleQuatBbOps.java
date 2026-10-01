// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleQuatBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleQuatImpl self, int index, DoubleBuffer buf);
    public abstract DoubleQuat loadAbsolute(DoubleQuatImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
    public abstract DoubleQuat loadAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleQuatImpl self, int index, FloatBuffer buf);
    public abstract DoubleQuat loadAbsolute(DoubleQuatImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
    public abstract DoubleQuat loadFloatAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
}
