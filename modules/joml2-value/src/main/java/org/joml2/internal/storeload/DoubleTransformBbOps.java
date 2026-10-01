// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleTransformBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleTransform self, int index, DoubleBuffer buf);
    public abstract DoubleTransform loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleTransform self, int index, ByteBuffer buf);
    public abstract DoubleTransform loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleTransform self, int index, FloatBuffer buf);
    public abstract DoubleTransform loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleTransform self, int index, ByteBuffer buf);
    public abstract DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf);
}
