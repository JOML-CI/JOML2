// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatQuatBbOps {
    public abstract FloatBuffer storeAbsolute(FloatQuatImpl self, int index, FloatBuffer buf);
    public abstract FloatQuat loadAbsolute(FloatQuatImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
    public abstract FloatQuat loadAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatQuatImpl self, int index, DoubleBuffer buf);
    public abstract FloatQuat loadAbsolute(FloatQuatImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
    public abstract FloatQuat loadDoubleAbsolute(FloatQuatImpl self, int index, ByteBuffer buf);
}
