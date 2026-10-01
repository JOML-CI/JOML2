// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleRigidBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleRigid self, int index, DoubleBuffer buf);
    public abstract DoubleRigid loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleRigid self, int index, ByteBuffer buf);
    public abstract DoubleRigid loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleRigid self, int index, FloatBuffer buf);
    public abstract DoubleRigid loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleRigid self, int index, ByteBuffer buf);
    public abstract DoubleRigid loadFloatAbsolute(int index, ByteBuffer buf);
}
