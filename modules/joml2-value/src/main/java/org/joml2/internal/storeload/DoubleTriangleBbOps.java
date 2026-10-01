// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleTriangleBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleTriangle self, int index, DoubleBuffer buf);
    public abstract DoubleTriangle loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleTriangle self, int index, ByteBuffer buf);
    public abstract DoubleTriangle loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleTriangle self, int index, FloatBuffer buf);
    public abstract DoubleTriangle loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleTriangle self, int index, ByteBuffer buf);
    public abstract DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf);
}
