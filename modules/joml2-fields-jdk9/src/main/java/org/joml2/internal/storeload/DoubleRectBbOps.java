// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleRectBbOps {
    DoubleBuffer storeAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf);
    DoubleRect loadAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
    DoubleRect loadAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleRectImpl self, int index, FloatBuffer buf);
    DoubleRect loadAbsolute(DoubleRectImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
    DoubleRect loadFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf);
}
