// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleRectBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleRect self, int index, DoubleBuffer buf);
    public abstract DoubleRect loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleRect self, int index, ByteBuffer buf);
    public abstract DoubleRect loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleRect self, int index, FloatBuffer buf);
    public abstract DoubleRect loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleRect self, int index, ByteBuffer buf);
    public abstract DoubleRect loadFloatAbsolute(int index, ByteBuffer buf);
}
