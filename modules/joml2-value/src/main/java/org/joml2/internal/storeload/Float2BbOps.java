// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float2BbOps {
    FloatBuffer storeAbsolute(Float2 self, int index, FloatBuffer buf);
    Float2 loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(Float2 self, int index, ByteBuffer buf);
    Float2 loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(Float2 self, int index, DoubleBuffer buf);
    Float2 loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(Float2 self, int index, ByteBuffer buf);
    Float2 loadDoubleAbsolute(int index, ByteBuffer buf);
}
