// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatPlaneBbOps {
    FloatBuffer storeAbsolute(FloatPlane self, int index, FloatBuffer buf);
    FloatPlane loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatPlane self, int index, ByteBuffer buf);
    FloatPlane loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatPlane self, int index, DoubleBuffer buf);
    FloatPlane loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatPlane self, int index, ByteBuffer buf);
    FloatPlane loadDoubleAbsolute(int index, ByteBuffer buf);
}
