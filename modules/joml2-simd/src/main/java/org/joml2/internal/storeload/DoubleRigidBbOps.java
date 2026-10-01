// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleRigidBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleRigidImpl self, int index, DoubleBuffer buf);
    public abstract DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
    public abstract DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleRigidImpl self, int index, FloatBuffer buf);
    public abstract DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
    public abstract DoubleRigid loadFloatAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf);
}
