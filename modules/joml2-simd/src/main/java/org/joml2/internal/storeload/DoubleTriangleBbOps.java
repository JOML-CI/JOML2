// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleTriangleBbOps {
    DoubleBuffer storeAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf);
    DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
    DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf);
    DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
    DoubleTriangle loadFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
}
