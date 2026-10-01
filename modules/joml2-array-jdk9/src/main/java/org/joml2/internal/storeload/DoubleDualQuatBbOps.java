// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleDualQuatBbOps {
    DoubleBuffer storeAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf);
    DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
    DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf);
    DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
    DoubleDualQuat loadFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf);
}
