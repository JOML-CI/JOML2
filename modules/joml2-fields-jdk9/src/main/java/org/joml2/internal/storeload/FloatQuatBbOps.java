// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatQuatBbOps {
    FloatBuffer storeAbsolute(FloatQuatImpl self, int index, FloatBuffer buf);
    FloatQuat loadAbsolute(FloatQuatImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
    FloatQuat loadAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatQuatImpl self, int index, DoubleBuffer buf);
    FloatQuat loadAbsolute(FloatQuatImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
    FloatQuat loadDoubleAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
}
