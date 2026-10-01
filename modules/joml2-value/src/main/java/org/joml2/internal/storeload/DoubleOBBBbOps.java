// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleOBBBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleOBB self, int index, DoubleBuffer buf);
    public abstract DoubleOBB loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleOBB self, int index, ByteBuffer buf);
    public abstract DoubleOBB loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleOBB self, int index, FloatBuffer buf);
    public abstract DoubleOBB loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleOBB self, int index, ByteBuffer buf);
    public abstract DoubleOBB loadFloatAbsolute(int index, ByteBuffer buf);
}
