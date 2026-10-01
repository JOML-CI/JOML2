// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleAABBBbOps {
    DoubleBuffer storeAbsolute(DoubleAABBImpl self, int index, DoubleBuffer buf);
    DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
    DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleAABBImpl self, int index, FloatBuffer buf);
    DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
    DoubleAABB loadFloatAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf);
}
