// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatTransformBbOps {
    public abstract FloatBuffer storeAbsolute(FloatTransform self, int index, FloatBuffer buf);
    public abstract FloatTransform loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatTransform self, int index, ByteBuffer buf);
    public abstract FloatTransform loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatTransform self, int index, DoubleBuffer buf);
    public abstract FloatTransform loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatTransform self, int index, ByteBuffer buf);
    public abstract FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf);
}
