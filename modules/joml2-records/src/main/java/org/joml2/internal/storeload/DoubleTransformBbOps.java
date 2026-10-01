// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleTransformBbOps {
    DoubleBuffer storeAbsolute(DoubleTransform self, int index, DoubleBuffer buf);
    DoubleTransform loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleTransform self, int index, ByteBuffer buf);
    DoubleTransform loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleTransform self, int index, FloatBuffer buf);
    DoubleTransform loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleTransform self, int index, ByteBuffer buf);
    DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf);
}
