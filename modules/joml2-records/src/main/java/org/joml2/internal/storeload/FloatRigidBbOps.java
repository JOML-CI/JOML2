// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatRigidBbOps {
    FloatBuffer storeAbsolute(FloatRigid self, int index, FloatBuffer buf);
    FloatRigid loadAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatRigid self, int index, ByteBuffer buf);
    FloatRigid loadAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatRigid self, int index, DoubleBuffer buf);
    FloatRigid loadAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatRigid self, int index, ByteBuffer buf);
    FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf);
}
