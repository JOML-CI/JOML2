// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatDualQuatBbOps {
    FloatBuffer storeAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf);
    FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
    FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf);
    FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
    FloatDualQuat loadDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
}
