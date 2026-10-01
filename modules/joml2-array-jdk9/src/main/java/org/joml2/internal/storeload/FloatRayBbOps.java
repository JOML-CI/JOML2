// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatRayBbOps {
    public abstract FloatBuffer storeAbsolute(FloatRayImpl self, int index, FloatBuffer buf);
    public abstract FloatRay loadAbsolute(FloatRayImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
    public abstract FloatRay loadAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatRayImpl self, int index, DoubleBuffer buf);
    public abstract FloatRay loadAbsolute(FloatRayImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
    public abstract FloatRay loadDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
}
