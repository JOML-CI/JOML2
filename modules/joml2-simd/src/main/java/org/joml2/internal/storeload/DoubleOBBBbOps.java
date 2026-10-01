// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleOBBBbOps {
    DoubleBuffer storeAbsolute(DoubleOBBImpl self, int index, DoubleBuffer buf);
    DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
    DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleOBBImpl self, int index, FloatBuffer buf);
    DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
    DoubleOBB loadFloatAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf);
}
