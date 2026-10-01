// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleOBBBbOps {
    DoubleBuffer storeAbsolute(DoubleOBB self, int index, DoubleBuffer buf);
    DoubleOBB loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleOBB self, int index, ByteBuffer buf);
    DoubleOBB loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleOBB self, int index, FloatBuffer buf);
    DoubleOBB loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleOBB self, int index, ByteBuffer buf);
    DoubleOBB loadFloatAbsolute(int index, ByteBuffer buf);
}
