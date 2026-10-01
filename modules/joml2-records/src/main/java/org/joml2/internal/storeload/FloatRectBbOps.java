// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatRectBbOps {
    FloatBuffer storeAbsolute(FloatRect self, int index, FloatBuffer buf);
    FloatRect loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatRect self, int index, ByteBuffer buf);
    FloatRect loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatRect self, int index, DoubleBuffer buf);
    FloatRect loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatRect self, int index, ByteBuffer buf);
    FloatRect loadDoubleAbsolute(int index, ByteBuffer buf);
}
