// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float4BbOps {
    FloatBuffer storeAbsolute(Float4 self, int index, FloatBuffer buf);
    Float4 loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(Float4 self, int index, ByteBuffer buf);
    Float4 loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(Float4 self, int index, DoubleBuffer buf);
    Float4 loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(Float4 self, int index, ByteBuffer buf);
    Float4 loadDoubleAbsolute(int index, ByteBuffer buf);
}
