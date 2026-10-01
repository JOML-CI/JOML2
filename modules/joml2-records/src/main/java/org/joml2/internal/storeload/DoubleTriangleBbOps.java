// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleTriangleBbOps {
    DoubleBuffer storeAbsolute(DoubleTriangle self, int index, DoubleBuffer buf);
    DoubleTriangle loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleTriangle self, int index, ByteBuffer buf);
    DoubleTriangle loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleTriangle self, int index, FloatBuffer buf);
    DoubleTriangle loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleTriangle self, int index, ByteBuffer buf);
    DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf);
}
