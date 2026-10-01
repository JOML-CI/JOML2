// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleRayBbOps {
    DoubleBuffer storeAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf);
    DoubleRay loadAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
    DoubleRay loadAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleRayImpl self, int index, FloatBuffer buf);
    DoubleRay loadAbsolute(DoubleRayImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
    DoubleRay loadFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf);
}
