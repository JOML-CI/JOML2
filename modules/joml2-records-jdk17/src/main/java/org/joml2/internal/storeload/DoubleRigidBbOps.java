// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleRigidBbOps {
    DoubleBuffer storeAbsolute(DoubleRigid self, int index, DoubleBuffer buf);
    DoubleRigid loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleRigid self, int index, ByteBuffer buf);
    DoubleRigid loadAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleRigid self, int index, FloatBuffer buf);
    DoubleRigid loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleRigid self, int index, ByteBuffer buf);
    DoubleRigid loadFloatAbsolute(int index, ByteBuffer buf);
}
