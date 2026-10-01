// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleTransformBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleTransformImpl self, int index, DoubleBuffer buf);
    public abstract DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
    public abstract DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleTransformImpl self, int index, FloatBuffer buf);
    public abstract DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
    public abstract DoubleTransform loadFloatAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf);
}
