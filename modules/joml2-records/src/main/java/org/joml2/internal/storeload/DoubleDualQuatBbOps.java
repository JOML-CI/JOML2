// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleDualQuatBbOps {
    DoubleBuffer storeAbsolute(DoubleDualQuat self, int index, DoubleBuffer buf);
    DoubleDualQuat loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleDualQuat self, int index, ByteBuffer buf);
    DoubleDualQuat loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleDualQuat self, int index, FloatBuffer buf);
    DoubleDualQuat loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleDualQuat self, int index, ByteBuffer buf);
    DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer buf);
}
