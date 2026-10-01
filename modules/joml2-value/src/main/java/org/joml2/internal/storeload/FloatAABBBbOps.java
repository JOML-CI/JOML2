// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatAABBBbOps {
    public abstract FloatBuffer storeAbsolute(FloatAABB self, int index, FloatBuffer buf);
    public abstract FloatAABB loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatAABB self, int index, ByteBuffer buf);
    public abstract FloatAABB loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatAABB self, int index, DoubleBuffer buf);
    public abstract FloatAABB loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatAABB self, int index, ByteBuffer buf);
    public abstract FloatAABB loadDoubleAbsolute(int index, ByteBuffer buf);
}
