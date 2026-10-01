// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleRayBbOps {
    DoubleBuffer storeAbsolute(DoubleRay self, int index, DoubleBuffer buf);
    DoubleRay loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleRay self, int index, ByteBuffer buf);
    DoubleRay loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleRay self, int index, FloatBuffer buf);
    DoubleRay loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleRay self, int index, ByteBuffer buf);
    DoubleRay loadFloatAbsolute(int index, ByteBuffer buf);
}
