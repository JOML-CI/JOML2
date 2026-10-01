// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float3BbOps {
    FloatBuffer storeAbsolute(Float3 self, int index, FloatBuffer buf);
    Float3 loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(Float3 self, int index, ByteBuffer buf);
    Float3 loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(Float3 self, int index, DoubleBuffer buf);
    Float3 loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(Float3 self, int index, ByteBuffer buf);
    Float3 loadDoubleAbsolute(int index, ByteBuffer buf);
}
