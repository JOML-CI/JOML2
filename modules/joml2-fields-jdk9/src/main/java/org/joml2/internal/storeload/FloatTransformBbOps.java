// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatTransformBbOps {
    public abstract FloatBuffer storeAbsolute(FloatTransformImpl self, int index, FloatBuffer buf);
    public abstract FloatTransform loadAbsolute(FloatTransformImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
    public abstract FloatTransform loadAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatTransformImpl self, int index, DoubleBuffer buf);
    public abstract FloatTransform loadAbsolute(FloatTransformImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
    public abstract FloatTransform loadDoubleAbsolute(FloatTransformImpl self, int index, ByteBuffer buf);
}
