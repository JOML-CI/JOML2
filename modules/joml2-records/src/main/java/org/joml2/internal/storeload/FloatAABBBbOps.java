// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatAABBBbOps {
    FloatBuffer storeAbsolute(FloatAABB self, int index, FloatBuffer buf);
    FloatAABB loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatAABB self, int index, ByteBuffer buf);
    FloatAABB loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatAABB self, int index, DoubleBuffer buf);
    FloatAABB loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatAABB self, int index, ByteBuffer buf);
    FloatAABB loadDoubleAbsolute(int index, ByteBuffer buf);
}
