// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatDualQuatBbOps {
    FloatBuffer storeAbsolute(FloatDualQuat self, int index, FloatBuffer buf);
    FloatDualQuat loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatDualQuat self, int index, ByteBuffer buf);
    FloatDualQuat loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatDualQuat self, int index, DoubleBuffer buf);
    FloatDualQuat loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatDualQuat self, int index, ByteBuffer buf);
    FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf);
}
