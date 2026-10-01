// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatPlaneBbOps {
    public abstract FloatBuffer storeAbsolute(FloatPlaneImpl self, int index, FloatBuffer buf);
    public abstract FloatPlane loadAbsolute(FloatPlaneImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
    public abstract FloatPlane loadAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatPlaneImpl self, int index, DoubleBuffer buf);
    public abstract FloatPlane loadAbsolute(FloatPlaneImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
    public abstract FloatPlane loadDoubleAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
}
