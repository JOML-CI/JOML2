// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatQuatBbOps {
    FloatBuffer storeAbsolute(FloatQuat self, int index, FloatBuffer buf);
    FloatQuat loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatQuat self, int index, ByteBuffer buf);
    FloatQuat loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatQuat self, int index, DoubleBuffer buf);
    FloatQuat loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatQuat self, int index, ByteBuffer buf);
    FloatQuat loadDoubleAbsolute(int index, ByteBuffer buf);
}
