// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class FloatSphereBbOps {
    public abstract FloatBuffer storeAbsolute(FloatSphere self, int index, FloatBuffer buf);
    public abstract FloatSphere loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(FloatSphere self, int index, ByteBuffer buf);
    public abstract FloatSphere loadAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(FloatSphere self, int index, DoubleBuffer buf);
    public abstract FloatSphere loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(FloatSphere self, int index, ByteBuffer buf);
    public abstract FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf);
}
