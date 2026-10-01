// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatOBBBbOps {
    FloatBuffer storeAbsolute(FloatOBB self, int index, FloatBuffer buf);
    FloatOBB loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatOBB self, int index, ByteBuffer buf);
    FloatOBB loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatOBB self, int index, DoubleBuffer buf);
    FloatOBB loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatOBB self, int index, ByteBuffer buf);
    FloatOBB loadDoubleAbsolute(int index, ByteBuffer buf);
}
