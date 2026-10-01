// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleAABBBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleAABB self, int index, DoubleBuffer buf);
    public abstract DoubleAABB loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleAABB self, int index, ByteBuffer buf);
    public abstract DoubleAABB loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleAABB self, int index, FloatBuffer buf);
    public abstract DoubleAABB loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleAABB self, int index, ByteBuffer buf);
    public abstract DoubleAABB loadFloatAbsolute(int index, ByteBuffer buf);
}
