// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleAABBBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleAABBImpl self, int index, DoubleBuffer buf);
    public abstract DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
    public abstract DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleAABBImpl self, int index, FloatBuffer buf);
    public abstract DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
    public abstract DoubleAABB loadFloatAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
}
