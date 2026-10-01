// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleRayBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleRay self, int index, DoubleBuffer buf);
    public abstract DoubleRay loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleRay self, int index, ByteBuffer buf);
    public abstract DoubleRay loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleRay self, int index, FloatBuffer buf);
    public abstract DoubleRay loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleRay self, int index, ByteBuffer buf);
    public abstract DoubleRay loadFloatAbsolute(int index, ByteBuffer buf);
}
