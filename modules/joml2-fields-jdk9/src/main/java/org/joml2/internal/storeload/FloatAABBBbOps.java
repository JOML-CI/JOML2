// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatAABBBbOps {
    public abstract FloatBuffer storeAbsolute(FloatAABBImpl self, int index, FloatBuffer buf);
    public abstract FloatAABB loadAbsolute(FloatAABBImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
    public abstract FloatAABB loadAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf);
    public abstract FloatAABB loadAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
    public abstract FloatAABB loadDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
}
