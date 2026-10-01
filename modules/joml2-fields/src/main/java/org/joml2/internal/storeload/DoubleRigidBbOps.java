// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleRigidBbOps {
    DoubleBuffer storeAbsolute(DoubleRigidImpl self, int index, DoubleBuffer buf);
    DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
    DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleRigidImpl self, int index, FloatBuffer buf);
    DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
    DoubleRigid loadFloatAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
}
