// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatTriangleBbOps {
    public abstract FloatBuffer storeAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf);
    public abstract FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
    public abstract FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf);
    public abstract FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
    public abstract FloatTriangle loadDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf);
}
