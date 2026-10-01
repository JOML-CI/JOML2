// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatRayBbOps {
    FloatBuffer storeAbsolute(FloatRayImpl self, int index, FloatBuffer buf);
    FloatRay loadAbsolute(FloatRayImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
    FloatRay loadAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatRayImpl self, int index, DoubleBuffer buf);
    FloatRay loadAbsolute(FloatRayImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
    FloatRay loadDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf);
}
