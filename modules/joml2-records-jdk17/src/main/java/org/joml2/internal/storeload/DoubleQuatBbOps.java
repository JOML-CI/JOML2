// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleQuatBbOps {
    DoubleBuffer storeAbsolute(DoubleQuat self, int index, DoubleBuffer buf);
    DoubleQuat loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleQuat self, int index, ByteBuffer buf);
    DoubleQuat loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleQuat self, int index, FloatBuffer buf);
    DoubleQuat loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleQuat self, int index, ByteBuffer buf);
    DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf);
}
