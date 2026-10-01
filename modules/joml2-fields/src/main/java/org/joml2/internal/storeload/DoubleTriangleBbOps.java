// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleTriangleBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf);
    public abstract DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
    public abstract DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf);
    public abstract DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
    public abstract DoubleTriangle loadFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf);
}
