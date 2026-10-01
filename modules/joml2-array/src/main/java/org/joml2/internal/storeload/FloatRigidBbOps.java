// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatRigidBbOps {
    FloatBuffer storeAbsolute(FloatRigidImpl self, int index, FloatBuffer buf);
    FloatRigid loadAbsolute(FloatRigidImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
    FloatRigid loadAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf);
    FloatRigid loadAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
    FloatRigid loadDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
}
