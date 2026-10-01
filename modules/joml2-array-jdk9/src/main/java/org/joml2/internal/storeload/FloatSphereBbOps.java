// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface FloatSphereBbOps {
    FloatBuffer storeAbsolute(FloatSphereImpl self, int index, FloatBuffer buf);
    FloatSphere loadAbsolute(FloatSphereImpl self, int index, FloatBuffer buf);
    ByteBuffer storeAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
    FloatSphere loadAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
    DoubleBuffer storeAbsolute(FloatSphereImpl self, int index, DoubleBuffer buf);
    FloatSphere loadAbsolute(FloatSphereImpl self, int index, DoubleBuffer buf);
    ByteBuffer storeDoubleAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
    FloatSphere loadDoubleAbsolute(FloatSphereImpl self, int index, ByteBuffer buf);
}
