// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleTransformBbOps {
    DoubleBuffer storeAbsolute(DoubleTransformImpl self, int index, DoubleBuffer buf);
    DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
    DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleTransformImpl self, int index, FloatBuffer buf);
    DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
    DoubleTransform loadFloatAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
}
