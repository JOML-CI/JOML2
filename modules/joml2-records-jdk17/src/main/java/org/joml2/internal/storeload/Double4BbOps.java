// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double4BbOps {
    DoubleBuffer storeAbsolute(Double4 self, int index, DoubleBuffer buf);
    Double4 loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(Double4 self, int index, ByteBuffer buf);
    Double4 loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(Double4 self, int index, FloatBuffer buf);
    Double4 loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(Double4 self, int index, ByteBuffer buf);
    Double4 loadFloatAbsolute(int index, ByteBuffer buf);
}
