// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double3BbOps {
    DoubleBuffer storeAbsolute(Double3 self, int index, DoubleBuffer buf);
    Double3 loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(Double3 self, int index, ByteBuffer buf);
    Double3 loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(Double3 self, int index, FloatBuffer buf);
    Double3 loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(Double3 self, int index, ByteBuffer buf);
    Double3 loadFloatAbsolute(int index, ByteBuffer buf);
}
