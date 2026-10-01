// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatTriangleBbOps {
    FloatBuffer storeAbsolute(FloatTriangle self, int index, FloatBuffer buf);
    FloatTriangle loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatTriangle self, int index, ByteBuffer buf);
    FloatTriangle loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatTriangle self, int index, DoubleBuffer buf);
    FloatTriangle loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatTriangle self, int index, ByteBuffer buf);
    FloatTriangle loadDoubleAbsolute(int index, ByteBuffer buf);
}
