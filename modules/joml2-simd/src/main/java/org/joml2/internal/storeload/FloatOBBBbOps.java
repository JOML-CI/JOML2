// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatOBBBbOps {
    FloatBuffer storeAbsolute(FloatOBBImpl self, int index, FloatBuffer buf);
    FloatOBB loadAbsolute(FloatOBBImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
    FloatOBB loadAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf);
    FloatOBB loadAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
    FloatOBB loadDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf);
}
