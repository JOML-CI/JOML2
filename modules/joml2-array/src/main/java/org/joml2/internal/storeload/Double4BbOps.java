// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double4BbOps {
    DoubleBuffer storeAbsolute(Double4Impl self, int index, DoubleBuffer buf);
    Double4 loadAbsolute(Double4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(Double4Impl self, int index, ByteBuffer buf);
    Double4 loadAbsolute(Double4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(Double4Impl self, int index, FloatBuffer buf);
    Double4 loadAbsolute(Double4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(Double4Impl self, int index, ByteBuffer buf);
    Double4 loadFloatAbsolute(Double4Impl self, int index, ByteBuffer buf);
}
