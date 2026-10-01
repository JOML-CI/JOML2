// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoublePlaneBbOps {
    public abstract DoubleBuffer storeAbsolute(DoublePlane self, int index, DoubleBuffer buf);
    public abstract DoublePlane loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoublePlane self, int index, ByteBuffer buf);
    public abstract DoublePlane loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoublePlane self, int index, FloatBuffer buf);
    public abstract DoublePlane loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoublePlane self, int index, ByteBuffer buf);
    public abstract DoublePlane loadFloatAbsolute(int index, ByteBuffer buf);
}
