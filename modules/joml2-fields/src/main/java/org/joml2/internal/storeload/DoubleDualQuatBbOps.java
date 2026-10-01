// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleDualQuatBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf);
    public abstract DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
    public abstract DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf);
    public abstract DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
    public abstract DoubleDualQuat loadFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
}
