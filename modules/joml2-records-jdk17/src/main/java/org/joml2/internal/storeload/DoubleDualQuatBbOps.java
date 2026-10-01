// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleDualQuatBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleDualQuat self, int index, DoubleBuffer buf);
    public abstract DoubleDualQuat loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleDualQuat self, int index, ByteBuffer buf);
    public abstract DoubleDualQuat loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleDualQuat self, int index, FloatBuffer buf);
    public abstract DoubleDualQuat loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleDualQuat self, int index, ByteBuffer buf);
    public abstract DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer buf);
}
