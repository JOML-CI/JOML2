// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleQuatBbOps {
    DoubleBuffer storeAbsolute(DoubleQuatImpl self, int index, DoubleBuffer buf);
    DoubleQuat loadAbsolute(DoubleQuatImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
    DoubleQuat loadAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleQuatImpl self, int index, FloatBuffer buf);
    DoubleQuat loadAbsolute(DoubleQuatImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
    DoubleQuat loadFloatAbsolute(DoubleQuatImpl self, int index, ByteBuffer buf);
}
