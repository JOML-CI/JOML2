// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatOBBBbOps {
    public abstract FloatBuffer storeAbsolute(FloatOBB self, int index, FloatBuffer buf);
    public abstract FloatOBB loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatOBB self, int index, ByteBuffer buf);
    public abstract FloatOBB loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatOBB self, int index, DoubleBuffer buf);
    public abstract FloatOBB loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatOBB self, int index, ByteBuffer buf);
    public abstract FloatOBB loadDoubleAbsolute(int index, ByteBuffer buf);
}
