// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleSphereBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleSphereImpl self, int index, DoubleBuffer buf);
    public abstract DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
    public abstract DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleSphereImpl self, int index, FloatBuffer buf);
    public abstract DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
    public abstract DoubleSphere loadFloatAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf);
}
