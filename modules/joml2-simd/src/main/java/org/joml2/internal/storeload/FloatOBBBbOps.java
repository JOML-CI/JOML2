// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatOBBBbOps {
    public abstract FloatBuffer storeAbsolute(FloatOBBImpl self, int index, FloatBuffer buf);
    public abstract FloatOBB loadAbsolute(FloatOBBImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
    public abstract FloatOBB loadAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf);
    public abstract FloatOBB loadAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
    public abstract FloatOBB loadDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
}
