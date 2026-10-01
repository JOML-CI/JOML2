// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleOBBBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleOBBImpl self, int index, DoubleBuffer buf);
    public abstract DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
    public abstract DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleOBBImpl self, int index, FloatBuffer buf);
    public abstract DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
    public abstract DoubleOBB loadFloatAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
}
