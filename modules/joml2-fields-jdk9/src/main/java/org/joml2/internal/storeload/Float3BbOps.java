// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float3BbOps {
    FloatBuffer storeAbsolute(Float3Impl self, int index, FloatBuffer buf);
    Float3 loadAbsolute(Float3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(Float3Impl self, int index, ByteBuffer buf);
    Float3 loadAbsolute(Float3Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(Float3Impl self, int index, DoubleBuffer buf);
    Float3 loadAbsolute(Float3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(Float3Impl self, int index, ByteBuffer buf);
    Float3 loadDoubleAbsolute(Float3Impl self, int index, ByteBuffer buf);
}
