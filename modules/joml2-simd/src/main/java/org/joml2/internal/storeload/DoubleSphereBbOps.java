// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface DoubleSphereBbOps {
    DoubleBuffer storeAbsolute(DoubleSphereImpl self, int index, DoubleBuffer buf);
    DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
    DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
    FloatBuffer storeAbsolute(DoubleSphereImpl self, int index, FloatBuffer buf);
    DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, FloatBuffer buf);
    ByteBuffer storeFloatAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
    DoubleSphere loadFloatAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
}
