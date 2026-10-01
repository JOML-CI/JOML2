// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatRigidBbOps {
    public abstract FloatBuffer storeAbsolute(FloatRigidImpl self, int index, FloatBuffer buf);
    public abstract FloatRigid loadAbsolute(FloatRigidImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
    public abstract FloatRigid loadAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf);
    public abstract FloatRigid loadAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
    public abstract FloatRigid loadDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf);
}
