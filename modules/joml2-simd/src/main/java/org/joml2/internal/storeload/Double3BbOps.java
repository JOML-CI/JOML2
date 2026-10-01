// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double3BbOps {
    DoubleBuffer storeAbsolute(Double3Impl self, int index, DoubleBuffer buf);
    Double3 loadAbsolute(Double3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(Double3Impl self, int index, ByteBuffer buf);
    Double3 loadAbsolute(Double3Impl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(Double3Impl self, int index, FloatBuffer buf);
    Double3 loadAbsolute(Double3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(Double3Impl self, int index, ByteBuffer buf);
    Double3 loadFloatAbsolute(Double3Impl self, int index, ByteBuffer buf);
}
