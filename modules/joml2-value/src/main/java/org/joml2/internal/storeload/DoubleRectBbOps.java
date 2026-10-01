// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleRectBbOps {
    DoubleBuffer storeAbsolute(DoubleRect self, int index, DoubleBuffer buf);
    DoubleRect loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleRect self, int index, ByteBuffer buf);
    DoubleRect loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleRect self, int index, FloatBuffer buf);
    DoubleRect loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleRect self, int index, ByteBuffer buf);
    DoubleRect loadFloatAbsolute(int index, ByteBuffer buf);
}
