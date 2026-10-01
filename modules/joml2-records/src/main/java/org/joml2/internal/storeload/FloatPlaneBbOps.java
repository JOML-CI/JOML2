// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatPlaneBbOps {
    public abstract FloatBuffer storeAbsolute(FloatPlane self, int index, FloatBuffer buf);
    public abstract FloatPlane loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatPlane self, int index, ByteBuffer buf);
    public abstract FloatPlane loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatPlane self, int index, DoubleBuffer buf);
    public abstract FloatPlane loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatPlane self, int index, ByteBuffer buf);
    public abstract FloatPlane loadDoubleAbsolute(int index, ByteBuffer buf);
}
