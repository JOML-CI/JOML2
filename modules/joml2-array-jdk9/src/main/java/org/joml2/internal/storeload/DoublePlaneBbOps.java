// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoublePlaneBbOps {
    DoubleBuffer storeAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf);
    DoublePlane loadAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
    DoublePlane loadAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf);
    DoublePlane loadAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
    DoublePlane loadFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf);
}
