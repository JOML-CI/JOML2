// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatPlaneBbOps {
    FloatBuffer storeAbsolute(FloatPlaneImpl self, int index, FloatBuffer buf);
    FloatPlane loadAbsolute(FloatPlaneImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
    FloatPlane loadAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatPlaneImpl self, int index, DoubleBuffer buf);
    FloatPlane loadAbsolute(FloatPlaneImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
    FloatPlane loadDoubleAbsolute(FloatPlaneImpl self, int index, ByteBuffer buf);
}
