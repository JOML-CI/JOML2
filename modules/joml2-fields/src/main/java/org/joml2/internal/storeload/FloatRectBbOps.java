// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatRectBbOps {
    FloatBuffer storeAbsolute(FloatRectImpl self, int index, FloatBuffer buf);
    FloatRect loadAbsolute(FloatRectImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
    FloatRect loadAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatRectImpl self, int index, DoubleBuffer buf);
    FloatRect loadAbsolute(FloatRectImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
    FloatRect loadDoubleAbsolute(FloatRectImpl self, int index, ByteBuffer buf);
}
