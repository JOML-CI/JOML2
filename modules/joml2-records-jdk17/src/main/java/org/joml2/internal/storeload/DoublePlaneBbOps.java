// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoublePlaneBbOps {
    DoubleBuffer storeAbsolute(DoublePlane self, int index, DoubleBuffer buf);
    DoublePlane loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoublePlane self, int index, ByteBuffer buf);
    DoublePlane loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoublePlane self, int index, FloatBuffer buf);
    DoublePlane loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoublePlane self, int index, ByteBuffer buf);
    DoublePlane loadFloatAbsolute(int index, ByteBuffer buf);
}
