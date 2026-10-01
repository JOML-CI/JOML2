// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatRayBbOps {
    FloatBuffer storeAbsolute(FloatRay self, int index, FloatBuffer buf);
    FloatRay loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatRay self, int index, ByteBuffer buf);
    FloatRay loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatRay self, int index, DoubleBuffer buf);
    FloatRay loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatRay self, int index, ByteBuffer buf);
    FloatRay loadDoubleAbsolute(int index, ByteBuffer buf);
}
