// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatTransformBbOps {
    FloatBuffer storeAbsolute(FloatTransform self, int index, FloatBuffer buf);
    FloatTransform loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatTransform self, int index, ByteBuffer buf);
    FloatTransform loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatTransform self, int index, DoubleBuffer buf);
    FloatTransform loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatTransform self, int index, ByteBuffer buf);
    FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf);
}
