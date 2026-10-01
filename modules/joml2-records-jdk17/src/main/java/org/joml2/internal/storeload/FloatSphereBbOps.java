// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatSphereBbOps {
    FloatBuffer storeAbsolute(FloatSphere self, int index, FloatBuffer buf);
    FloatSphere loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatSphere self, int index, ByteBuffer buf);
    FloatSphere loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatSphere self, int index, DoubleBuffer buf);
    FloatSphere loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatSphere self, int index, ByteBuffer buf);
    FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf);
}
