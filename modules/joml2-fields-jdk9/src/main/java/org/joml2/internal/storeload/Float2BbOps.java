// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float2BbOps {
    FloatBuffer storeAbsolute(Float2Impl self, int index, FloatBuffer buf);
    Float2 loadAbsolute(Float2Impl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(Float2Impl self, int index, ByteBuffer buf);
    Float2 loadAbsolute(Float2Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(Float2Impl self, int index, DoubleBuffer buf);
    Float2 loadAbsolute(Float2Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(Float2Impl self, int index, ByteBuffer buf);
    Float2 loadDoubleAbsolute(Float2Impl self, int index, ByteBuffer buf);
}
