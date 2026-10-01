// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatDualQuatBbOps {
    public abstract FloatBuffer storeAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf);
    public abstract FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
    public abstract FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf);
    public abstract FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
    public abstract FloatDualQuat loadDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf);
}
