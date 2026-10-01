// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatRectBbOps {
    public abstract FloatBuffer storeAbsolute(FloatRect self, int index, FloatBuffer buf);
    public abstract FloatRect loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatRect self, int index, ByteBuffer buf);
    public abstract FloatRect loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatRect self, int index, DoubleBuffer buf);
    public abstract FloatRect loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatRect self, int index, ByteBuffer buf);
    public abstract FloatRect loadDoubleAbsolute(int index, ByteBuffer buf);
}
