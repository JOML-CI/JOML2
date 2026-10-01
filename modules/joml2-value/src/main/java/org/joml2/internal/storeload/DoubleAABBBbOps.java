// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleAABBBbOps {
    DoubleBuffer storeAbsolute(DoubleAABB self, int index, DoubleBuffer buf);
    DoubleAABB loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleAABB self, int index, ByteBuffer buf);
    DoubleAABB loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleAABB self, int index, FloatBuffer buf);
    DoubleAABB loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleAABB self, int index, ByteBuffer buf);
    DoubleAABB loadFloatAbsolute(int index, ByteBuffer buf);
}
