// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoublePlaneBbOps {
    public abstract DoubleBuffer storeAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf);
    public abstract DoublePlane loadAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
    public abstract DoublePlane loadAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf);
    public abstract DoublePlane loadAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
    public abstract DoublePlane loadFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
}
