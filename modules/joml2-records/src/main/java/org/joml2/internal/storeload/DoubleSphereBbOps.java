// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleSphereBbOps {
    DoubleBuffer storeAbsolute(DoubleSphere self, int index, DoubleBuffer buf);
    DoubleSphere loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleSphere self, int index, ByteBuffer buf);
    DoubleSphere loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleSphere self, int index, FloatBuffer buf);
    DoubleSphere loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleSphere self, int index, ByteBuffer buf);
    DoubleSphere loadFloatAbsolute(int index, ByteBuffer buf);
}
