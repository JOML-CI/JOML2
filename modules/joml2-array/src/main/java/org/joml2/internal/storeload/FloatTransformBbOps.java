// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatTransformBbOps {
    FloatBuffer storeAbsolute(FloatTransformImpl self, int index, FloatBuffer buf);
    FloatTransform loadAbsolute(FloatTransformImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
    FloatTransform loadAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatTransformImpl self, int index, DoubleBuffer buf);
    FloatTransform loadAbsolute(FloatTransformImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
    FloatTransform loadDoubleAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
}
