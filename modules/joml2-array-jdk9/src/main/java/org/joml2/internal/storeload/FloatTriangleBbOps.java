// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatTriangleBbOps {
    FloatBuffer storeAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf);
    FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
    FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf);
    FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
    FloatTriangle loadDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
}
