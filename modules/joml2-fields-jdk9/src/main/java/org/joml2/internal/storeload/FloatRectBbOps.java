// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatRectBbOps {
    public abstract FloatBuffer storeAbsolute(FloatRectImpl self, int index, FloatBuffer buf);
    public abstract FloatRect loadAbsolute(FloatRectImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
    public abstract FloatRect loadAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatRectImpl self, int index, DoubleBuffer buf);
    public abstract FloatRect loadAbsolute(FloatRectImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
    public abstract FloatRect loadDoubleAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
}
