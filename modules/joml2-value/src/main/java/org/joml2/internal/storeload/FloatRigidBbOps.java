// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatRigidBbOps {
    public abstract FloatBuffer storeAbsolute(FloatRigid self, int index, FloatBuffer buf);
    public abstract FloatRigid loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatRigid self, int index, ByteBuffer buf);
    public abstract FloatRigid loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatRigid self, int index, DoubleBuffer buf);
    public abstract FloatRigid loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatRigid self, int index, ByteBuffer buf);
    public abstract FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf);
}
