// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatSphereBbOps {
    public abstract FloatBuffer storeAbsolute(FloatSphereImpl self, int index, FloatBuffer buf);
    public abstract FloatSphere loadAbsolute(FloatSphereImpl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
    public abstract FloatSphere loadAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatSphereImpl self, int index, DoubleBuffer buf);
    public abstract FloatSphere loadAbsolute(FloatSphereImpl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
    public abstract FloatSphere loadDoubleAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
}
