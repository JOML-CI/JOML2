// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatAABBBbOps {
    FloatBuffer storeAbsolute(FloatAABBImpl self, int index, FloatBuffer buf);
    FloatAABB loadAbsolute(FloatAABBImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
    FloatAABB loadAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf);
    FloatAABB loadAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
    FloatAABB loadDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf);
}
