// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatRayBbOps {
    public abstract FloatBuffer storeAbsolute(FloatRay self, int index, FloatBuffer buf);
    public abstract FloatRay loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatRay self, int index, ByteBuffer buf);
    public abstract FloatRay loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatRay self, int index, DoubleBuffer buf);
    public abstract FloatRay loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatRay self, int index, ByteBuffer buf);
    public abstract FloatRay loadDoubleAbsolute(int index, ByteBuffer buf);
}
