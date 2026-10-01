// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleRectBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf);
    public abstract DoubleRect loadAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
    public abstract DoubleRect loadAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleRectImpl self, int index, FloatBuffer buf);
    public abstract DoubleRect loadAbsolute(DoubleRectImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
    public abstract DoubleRect loadFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
}
