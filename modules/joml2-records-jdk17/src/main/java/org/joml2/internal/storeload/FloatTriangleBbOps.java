// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatTriangleBbOps {
    public abstract FloatBuffer storeAbsolute(FloatTriangle self, int index, FloatBuffer buf);
    public abstract FloatTriangle loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatTriangle self, int index, ByteBuffer buf);
    public abstract FloatTriangle loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatTriangle self, int index, DoubleBuffer buf);
    public abstract FloatTriangle loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatTriangle self, int index, ByteBuffer buf);
    public abstract FloatTriangle loadDoubleAbsolute(int index, ByteBuffer buf);
}
