// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleRayBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf);
    public abstract DoubleRay loadAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
    public abstract DoubleRay loadAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleRayImpl self, int index, FloatBuffer buf);
    public abstract DoubleRay loadAbsolute(DoubleRayImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
    public abstract DoubleRay loadFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
}
